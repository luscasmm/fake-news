package br.ufrn.imd.modelo;

import java.security.MessageDigest;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException; 
import java.text.Normalizer;
import java.util.TreeSet;

/* 
 * Esta classe implementa as rotinas
 * de processamento e de criptografia.
 */

public class Processador {
	
	public static int LIMIAR_DESCONSIDERACAO = 3; // Define o limiar para desconsiderar palavras
	public static double LIMIAR_SIMILARIDADE = 85; // Define o limiar de considerar notícias falsas ou não
	
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
	
	// Remove palavras com tamanho menor que o limiar de desconsideração e repetições
	public static String filtrar(String[] palavras) {
		
		TreeSet<String> set = new TreeSet<String>(); // Ávore rubro-negra auxiliar
		String str = "";	
		
		// Percorre palavra por palavra
		for(String palavra : palavras) {
			if(palavra.length() > LIMIAR_DESCONSIDERACAO) { // Filtra pelo Limiar de desconsideração
				set.add(palavra); // Evita repetições e ordena as palavras
			}	
		}
			
		// Passa o conjunto de palavras para string
		for(String palavra : set) {
			str += palavra + " ";
		}
		
		return str;
		
	}
	
	// Criptografa a string passada usando o algoritmo SHA-1
	public static String hash(String str) {
		try {
			// Instancia o "hasher" sob algoritmo SHA-1
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			
			// Criptografa a string passada
			byte[] bytes = md.digest(str.getBytes());
			
			// Converte de byte para BigInteger
			BigInteger bi = new BigInteger(1, bytes); 
			
			// Converte a criptografia para hexadecimal
			str = bi.toString(16);
			
			// Caso a chave não seja de 40 dígitos, adciona 0s a esquerda
			while (str.length() < 40) { 
                str = "0" + str; 
            } 
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		 
		return str;
	}
	
	// Retorna a similaride de string
	public static double comparar(String s1, String s2) {
		
		// Caso as strings estejam vazias, retorna 0
		if(s1.isEmpty() || s2.isEmpty()) return 0;
		
		// Caso as strings sejam iguais, retorna 1
		if(s1.equals(s2)) return 1;
		
		String[] str1 = s1.split(" ");
		String[] str2 = s2.split(" ");
		
		return SimilaridadeCosseno.similaridade(str1, str2);
	}
	
}
