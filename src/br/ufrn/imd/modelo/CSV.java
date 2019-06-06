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
			
	// Abre, lê e formata um arquivo CSV
	public static ArrayList<ArrayList<String>> ler(String caminho, String separador){
		ArrayList<ArrayList<String>> conteudo = new ArrayList<ArrayList<String>>();
		ArrayList<String> tokens = new ArrayList<String>();
		String token = null;
		String[] split = null; 
		BufferedReader csv = null;
		
		try { // Tenta abrir, ler e formatar o arquivo CSV
			
			csv = new BufferedReader(new FileReader(caminho)); // Instancia o objeto de leitura de arquivos

			String linha = csv.readLine(); // Lê o cabeçalho
			
			while((linha = csv.readLine()) != null) { // Lê o resto do conteúdo

				linha = linha.toLowerCase(); // Deixa tudo em minúsculo
				split = linha.split(separador, 2); // Divide a string em duas partes
				token = split[0]; // Pega o id
				
				try { // Verifiaca se linha possui id
					Integer.parseInt(token);
					linha = split[1]; // Remove o primeiro token da linha
				} catch(NumberFormatException e) {
					token = null;
				}
				
				tokens.add(token); // Adciona o id à lista de tokens da linha
						
				split = linha.split("\""+ separador); // Divide a linha entre separadores depois de aspas
				token = split[0]; // Pega texto da linha
				tokens.add(token);
				
				try { // Verifica se linha possui mais informações além do texto
					linha = split[1]; // Remove o texto da linha
				} catch (ArrayIndexOutOfBoundsException e) { // Caso não possua, pula a linha
					conteudo.add(tokens);
					continue;
				}
				
				split = linha.split(separador); // Divide linha entre os separadores
				
				token = split[0];	// Pega o link	
				tokens.add(token);
				
				try { // Verifica se linha possui mais informações além do link
					token = split[1]; // Pega o timestamp
				} catch (ArrayIndexOutOfBoundsException e) { // Caso não possua, pula a linha
					conteudo.add(tokens);
					continue;
				}
				
				tokens.add(token);
				conteudo.add(tokens);
				
				tokens.clear();
			}
			
			return conteudo;
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
			
		} catch (IOException e) {
			
	        e.printStackTrace();
	        
	    } finally { // Encerra o objeto BufferdReader
	    	
	        if (csv != null) {
	            try {
	                csv.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	            
	        }
	    }
		
		return null;
		
	}
	
	// Testando a classe	
	public static void main(String args[]) {
		ArrayList<ArrayList<String>> dataSet = CSV.ler("data/boatos.csv", ",");
//		System.out.println(dataSet);
		
	}

}
