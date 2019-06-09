package br.ufrn.imd.visao;

import java.util.HashMap;

// TODO: Criar classe no pacote dominio que manipule a webscrapper
// Será necessario um metdo render que transforme em noticia o resultado da coleta
// Melhorar "interface"
// Implementar o algoritmo de similaridade

import java.util.Map.Entry;
import java.util.Scanner;

import br.ufrn.imd.dominio.Armazenamento;
import br.ufrn.imd.modelo.Noticia;
import br.ufrn.imd.modelo.Processador;
import br.ufrn.imd.modelo.WebScraper;

public class Fakenews {
	
//	public static int menu() {
//		
//		Scanner scanner = new Scanner(System.in);
//		
//		System.out.println("1. Ler notícia da internet");
//		System.out.println("2. Sair");
//		
//		return scanner.nextInt();
//		
//	}
	
	public static void main(String args[]) {
		
		Scanner scanner = new Scanner(System.in); // Objeto de leitura
		
		Armazenamento armazenamento; // Objeto da central de armazenamento
		
		System.out.println("Fakenes\n"); // Título do sistema
		
		
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
				case 1: System.out.print("\nDeseja mudar limiar de desconsideração de palavras (s/n)? ");
						if(scanner.next() == "s") {
							System.out.print("\nInforme limiar de desconsideração de palavras (padrão 3): ");
							int limiar = scanner.nextInt();
							if(limiar != 3) Processador.LIMIAR = limiar;
						}
						
						System.out.print("\nCarregando...");
						armazenamento.carregar(); // Carrega os dados do dataset
						System.out.print(" Feito\n");
						break;
				
				case 2: System.out.print("\nInforme o link da notícia: ");
						String url = scanner.next();
						
						HashMap<String, String> coleta =  WebScraper.coletar(url);
						String hash = Processador.hash(coleta.get("manchete"));
						
						// Verifica se notícia já consta no sistema
						if(armazenamento.getJornal().noticia(hash) == null) {
							
		//					armazenamento.adicionar(coleta.get("manchete"), url, coleta.get("data"));
							
						} else {
							
							System.out.println("Notícia falsa");
							
						}
						
						break;
						
				case 3: sair = true;
						break;
			}	
		}while(sair != true);
		
		
		System.out.println("\nFinalizado.");
		
		
//		if(armazenamento.getJornal().noticia())
//		
//		for(Entry<String, Noticia> tupla : armazenamento.getJornal().noticias().entrySet()) {
//			System.out.println(tupla.getKey());
//		    System.out.println(tupla.getValue().getProcessado());
//		}
		
		
	}
}
