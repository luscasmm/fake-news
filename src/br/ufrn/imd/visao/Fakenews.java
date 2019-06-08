package br.ufrn.imd.visao;

// TODO: Criar classe no pacote dominio que manipule a webscrapper
// Será necessario um metdo render que transforme em noticia o resultado da cole
// Melhorar "interface"
// Implementar o algoritmo de similaridade

import java.util.Map.Entry;
import java.util.Scanner;

import br.ufrn.imd.dominio.Armazenamento;
import br.ufrn.imd.modelo.Noticia;
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
		
		
		armazenamento.carregar(); // Carrega os dados do dataset
		
		System.out.print("Informe o link da notícia: ");
		
		String[] coleta =  WebScraper.coletar(scanner.nextLine());
		
		for(String resultado : coleta) {
			System.out.print(resultado + " ");
		}
		
//		if(armazenamento.getJornal().noticia())
//		
//		for(Entry<String, Noticia> tupla : armazenamento.getJornal().noticias().entrySet()) {
//			System.out.println(tupla.getKey());
//		    System.out.println(tupla.getValue().getProcessado());
//		}
		
		
	}
}
