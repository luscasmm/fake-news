package br.ufrn.imd.modelo;

import java.text.Normalizer;
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
	private String conteudo;
	private String link;
	private String timestamp;
	
	static int LIMIAR = 3; // Define o limiar para desconsiderar palavras
	
	public Noticia(String id, String conteudo, String link, String timestamp) {
		
		this.id = id;
		this.conteudo = conteudo;
		this.link = link;
		this.timestamp = timestamp;
		
		
		// Tratamento do conteúdo da notícia
		
		this.conteudo = this.conteudo.toLowerCase(); // Deixa tudo em minúsculo
		
		this.conteudo = normalizar(this.conteudo); // Remove acentos e caracteres não alfanuméricos
		
		String[] tokens = this.conteudo.split(" "); // Divide texto entre espaços em branco
		
		this.conteudo = filtrar(tokens); // Ordena removendo palavras repetidas e de tamanho inferior ao limiar
		
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
	
	String getId() {
		return this.id;
	}
	
	String getConteudo() {
		return this.conteudo;
	}
	
	String getLink() {
		return this.link;
	}
	
	String getTimestamp() {
		return this.timestamp;
	}
	
	// Setters
	
	void setId(String id) {
		this.id = id;
	}
	
	void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}
	
	void setLink(String link) {
		this.link = link;
	}
	
	void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
		
}
