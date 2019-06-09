package br.ufrn.imd.visao;

import java.util.HashMap;

import java.util.Scanner;
import java.util.Map.Entry;

import br.ufrn.imd.dominio.Armazenamento;
import br.ufrn.imd.modelo.Noticia;
import br.ufrn.imd.modelo.Processador;
import br.ufrn.imd.modelo.WebScraper;


/*
 *  Representa a execução do projeto como um todo
 */

public class Fakenews {
	
	private static Scanner scanner;

	public static void main(String args[]) {
		
		scanner = new Scanner(System.in);
		
		Armazenamento armazenamento; // Objeto da central de armazenamento
		
		System.out.println("Fakenews\n"); // Título do sistema
		
		
		// Verifica se o caminho do dataset já foi informado
		if(args.length <= 0) {
			
			// Requisita o caminho
			System.out.print("Insira o caminho do dataset de noticias: ");
			
			// Instancia o objeto de armazenamento
			armazenamento = new Armazenamento(scanner.nextLine());
			
		} else {
			// Instancia o objeto de armazenamento
			armazenamento = new Armazenamento(args[0]); 
		}
		
		boolean sair = false;
		
		do {
			// Menu principal
			System.out.print("\n1. Carregar Dataset\n2. Verficar notícia \n3. Sair\n\nOpção: ");
			
			switch(scanner.nextInt()) {
			
				// Carregar Dataset
				case 1: System.out.print("\nDeseja mudar limiar de desconsideração de palavras (s/n)? ");
						// Escolha do limiar
						if(scanner.next().equals("s")) {
							System.out.print("\nInforme limiar de desconsideração de palavras (atual: " + Processador.LIMIAR_DESCONSIDERACAO + "): ");
							int limiar = scanner.nextInt();
							if(limiar != 3) Processador.LIMIAR_DESCONSIDERACAO = limiar;
						}
						
						// Carrega os dados do dataset
						System.out.print("\nCarregando...");
						armazenamento.carregar(); // Carrega os dados do dataset
						System.out.print(" Feito\n");
						break;
				
				case 2: HashMap<String, String> coleta = null; // Armazena os dados do web scraping
						boolean falha = false; // Flag de erro
						String url = null; // Armazena link a ser informado pelo usuário
						
						// Requisita o link enquanto ocorrer falhas
						do {
							System.out.print("\nInforme o link da notícia: ");
							url = scanner.next();
							
							// Tenta coletar dados do url passado
							try {
								System.out.print("\nColetando...");
								coleta =  WebScraper.coletar(url);
								falha = false;
								
								if(coleta == null) {
									System.out.print(" Erro\nNão foi possível coletar dados de: " + url + "\n");
									falha = true;
								}
							} catch (Exception e) {
								falha = true;
								System.out.print(" Erro\nNão foi possível acessar a url: " + url + "\n");
							}
							
						} while (falha);
						
						String manchete = coleta.get("manchete"); // Manchete da notícia coletada
						String data = coleta.get("data"); // Data da notícia coletada
						
						System.out.print(" Feito\n");
						
						System.out.println("\nNotícia: " + manchete); // Mostra a manchete
						
						// Gera uma nova notícia com base na coleta
						Entry<String, Noticia> noticiaColetada = armazenamento.getJornal().gerarNoticia(manchete, url, data);
						
						// Verifica se notícia não consta no sistema
						if(armazenamento.getJornal().getNoticia(noticiaColetada.getKey()) == null) {
							
							boolean fakenews = false; // Flag se notícia é falsa
							double similaridade; // Similaridade por iteração
							double maiorSimilaridade = 0; // Maior similaride com as notícias já cadastradas
							String chaveMaiorSimilaridade = null; // Referencia a notícia já cadastrada mais similar
							
							System.out.println("\nNotícia não consta no sistema");
							System.out.print("\nVerficação de similaridade: O limiar de confiança está definido como: " + Processador.LIMIAR_SIMILARIDADE + "%.\nDeseja mudar (s/n)? ");
							
							// Escolha do limiar
							if(scanner.next().equals("s")) {
								System.out.print("\nInforme limiar de confiança (sem %): ");
								double limiarSimilaridade = scanner.nextInt();
								if(limiarSimilaridade != 3) Processador.LIMIAR_SIMILARIDADE = limiarSimilaridade;
							}
							
							// Execução de similaridade
							System.out.print("\nComparando...");
							for(Entry<String, Noticia> noticia : armazenamento.getJornal().getNoticias().entrySet()) {
								
								// Compara similaridade entre as notícias com base no algoritmo cosseno
								similaridade = Processador.comparar(noticiaColetada.getValue().getProcessado(), noticia.getValue().getProcessado());
								
								// Passa a similaride para "porcentagem"
								similaridade *= 100;
								
								// Define maior similaridade
								if(similaridade > maiorSimilaridade) {
									maiorSimilaridade = similaridade;
									chaveMaiorSimilaridade = noticia.getKey();
								}
								 
								// Verifica se notícia é falsa ou não de acordo com o limiar
								if(Processador.LIMIAR_SIMILARIDADE <= similaridade) fakenews = true;
								
							}
							System.out.print(" Feito\n");
							
							// Armazena no sistema a nova fakenews
							if(fakenews) {
								System.out.println("\nNotícia falsa!");
								
								armazenamento.adicionar(manchete, url, data);
								
							}
							else System.out.println("\nNotícia verdadeira!");
							
							System.out.printf("Maior similaridade entre a notícia coletada e as armazenadas foi de: %.2f%%\n", maiorSimilaridade );
							System.out.println("Notícia armazenada com maior correspondência:\n\n" + armazenamento.getJornal().getNoticia(chaveMaiorSimilaridade).getOriginal());
							
							
						} else {
							System.out.println("\nNotícia falsa!");
							System.out.println("Já cadastrada no sistema: \n\n" + armazenamento.getJornal().getNoticia(noticiaColetada.getKey()).getOriginal());			
						}
						
						break;
						
				case 3: sair = true;
						break;
			}	
		}while(sair != true);
		
		
		System.out.println("\nFinalizado.");
		
	}
}
