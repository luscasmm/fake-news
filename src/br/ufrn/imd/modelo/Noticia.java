package br.ufrn.imd.modelo;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.TreeSet;

/*
 * Esta classe representa uma notícia.
 * 
 * Implementa os métodos necessários
 * ao pre-processamento e à formatação
 * de uma notícia
 * 
 */
public class Noticia {
	private String id;
	private String original;
	private String processado;
	private String link;
	private String timestamp;
	
	static int LIMIAR = 3; // Define o limiar para desconsiderar palavras
	
	public Noticia(String id, String original, String link, String timestamp) {
		
		this.id = id;
		this.link = link;
		this.timestamp = timestamp;
		this.original = original;
		this.processado = processar(original); 
		
	}
	
	// Processa o texto original da notícia
	public String processar(String str) {
		
		str = str.toLowerCase(); // Deixa tudo em minúsculo
		
		str = normalizar(str); // Remove acentos e caracteres não alfanuméricos
		
		String[] tokens = str.split(" "); // Divide texto entre espaços em branco
		
		str = filtrar(tokens); // Ordena removendo palavras repetidas e de tamanho inferior ao limiar
		
		return str;
		
	}
	
	// Remove acentos e caracteres não alfanúmericos da string passada	
	public String normalizar(String str) {
		
		str = Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", ""); // Remove acentos
		str = str.replaceAll("[^a-z0-9\\s]", " "); // Remove caracteres não alfanuméricos
		str = str.replaceAll("\\s+", " "); // Remove espaços em branco extra
		return str;
		
	}
	
	// Remove palavras com tamanho menor que o limiar e repetições
	public String filtrar(String[] palavras) {
		
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

	
	// Getters
	
	public String getId() {
		return this.id;
	}
	
	public String getOriginal() {
		return this.original;
	}
	
	public String getProcessado() {
		return this.processado;
	}
	
	public String getLink() {
		return this.link;
	}
	
	public String getTimestamp() {
		return this.timestamp;
	}
	
	// Setters
	
	public void setId(String id) {
		this.id = id;
	}
	
	public void setOriginal(String original) {
		this.original = original;
	}
	
	public void setProcessado(String processado) {
		this.processado = processado;
	}
	
	public void setLink(String link) {
		this.link = link;
	}
	
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
		
}
