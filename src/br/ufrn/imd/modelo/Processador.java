package br.ufrn.imd.modelo;

import java.text.Normalizer;
import java.util.TreeSet;

/* 
 * Esta classe implementa as rotinas
 * de pre-processamento de uma notícia.
 */

public class Processador {
	
	static int LIMIAR = 3; // Define o limiar para desconsiderar palavras
	
	// Processa o texto original da notícia
	public static String processar(String str) {
		
		str = str.toLowerCase(); // Deixa tudo em minúsculo
		
		str = normalizar(str); // Remove acentos e caracteres não alfanuméricos
		
		String[] tokens = str.split(" "); // Divide texto entre espaços em branco
		
		str = filtrar(tokens); // Ordena removendo palavras repetidas e de tamanho inferior ao limiar
		
		return str;
		
	}
	
	// Remove acentos e caracteres não alfanúmericos da string passada	
	public static String normalizar(String str) {
		
		str = Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", ""); // Remove acentos
		str = str.replaceAll("[^a-z0-9\\s]", " "); // Remove caracteres não alfanuméricos
		str = str.replaceAll("\\s+", " "); // Remove espaços em branco extra
		return str;
		
	}
	
	// Remove palavras com tamanho menor que o limiar e repetições
	public static String filtrar(String[] palavras) {
		
		TreeSet<String> set = new TreeSet<String>(); // Ávora rubro-negra auxiliar
		String str = "";	
		
		// Percorre palavra por palavra
		for(String palavra : palavras) {
			if(palavra.length() > LIMIAR) { // Filtra pelo Limiar
				set.add(palavra); // Evita repetições e ordena as palavras
			}	
		}
		
		// Passa o conjunto de palavras para string
		for(String palavra : set) {
			str += palavra + " ";
		}
		
		return str;
		
	}
}
