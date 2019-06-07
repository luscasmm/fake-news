package br.ufrn.imd.modelo;

/*
 * Esta classe representa uma notícia.
 * 
 */

public class Noticia {
	
	private String id; // Id
	private String original; // Texto original
	private String processado; // Texto processado
	private String link; // Link
	private String timestamp; // Intervalo de tempo
	
	public Noticia(String id, String original, String processado, String link, String timestamp) {
		
		this.id = id;
		this.original = original;
		this.processado = processado; 
		this.link = link;
		this.timestamp = timestamp;
			
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
