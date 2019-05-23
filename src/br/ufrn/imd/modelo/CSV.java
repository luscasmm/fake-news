package br.ufrn.imd.modelo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/*
 * CSV 
 * 
 * Esta classe implementa os métodos necessários
 * à abertura e leitura de um arquivo CSV
 * 
 */

public class CSV {
	
//	public LeitorCSV(String caminho) {
//		try { // Tenta abrir o arquivo localizado em caminho
//			
//			csv = new BufferedReader(new FileReader(caminho)); // Instancia o objeto de leitura de arquivos
//			
//			
//			lerLinha(); // Ler o cabeçalho
//			
//		} catch (FileNotFoundException e) { // Captura exceção caso o arquivo não exista
//			
//			e.printStackTrace();
//			
//		} catch (IOException e) {
//	
//			e.printStackTrace();
//		}
//	}
	
	// Abre, lê e formata um arquivo CSV
	public static ArrayList<String[]> ler(String caminho, String separador){
		
		ArrayList<String[]> conteudo = new ArrayList<String[]>();
		String[] tokens = null;
		BufferedReader csv = null;
		
		try { // Tenta abrir, ler e formatar o arquivo CSV
			
			csv = new BufferedReader(new FileReader(caminho)); // Instancia o objeto de leitura de arquivos
			String linha = csv.readLine(); // Lê o cabeçalho
			
			while((linha = csv.readLine()) != null) { // Lê o resto do conteúdo
				linha = linha.toLowerCase(); // Deixa tudo em minúsculo
				// TODO: remover caracteres especiais
				tokens = linha.split(separador);
				conteudo.add(tokens);
			}
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
			
		} catch (IOException e) {
			
	        e.printStackTrace();
	        
	    } finally {
	    	
	        if (csv != null) {
	            try {
	                csv.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	            
	        }
	    }
		
		return conteudo;
		
	}
	
	public static void main(String args[]) {
		ArrayList<String[]> dataSet = CSV.ler("data/boatos.csv", ",");
		
		for(String[] linha : dataSet) {
			for(String subString : linha) {
				System.out.print(subString + " ");
			}
			System.out.print("\n");
		}
	}
	
//	public String lerLinha() throws IOException {
//		String linha = null;
//		
//		try {
//			linha = csv.readLine();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
//		if(linha == null) csv.close();
//		
//		return linha;
//	}
//	
//	public static void main(String[] args) {
//		LeitorCSV leitor = new LeitorCSV("data/boatos.csv");
//		
//		try {
//			String linha = leitor.lerLinha();
//			int i = 0;
//			
//			while(linha != null) {
//				System.out.println(i + " :" + linha);
//				linha = leitor.lerLinha();
//				i++;
//			}
//			
//			System.out.println(linha);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
//		
//	
//	}

}
