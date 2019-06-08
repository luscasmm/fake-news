package br.ufrn.imd.dominio;

import br.ufrn.imd.modelo.Noticia;

import java.util.HashMap;

/*
 * Essa classe representa o conceito de um jornal de fakenews.
 * 
 * Implementa os métodos necessarios às operações básicas com noticias
 * 
 */

public class Jornal {
	private HashMap<String, Noticia> noticias;  // Mapa de notícias
	
	// Construtor padrão
	public Jornal() {
		this.noticias = new HashMap<String, Noticia>();
	}
	
	// Construtor parametrizado
	public Jornal(HashMap<String, Noticia> noticias) {
		this.noticias = noticias;
	}
	
	
	// Adiciona a noticia passada ao mapa
	public void adicionar(String chave, Noticia noticia) {
		this.noticias.put(chave, noticia);
	}
	
	// Adiciona uma noticia do mapa com base na chave passada
	public void remover(String chave) {
		this.noticias.remove(chave);
	}
	
	// Retorna a notícia sob a chave passada
	public Noticia noticia(String chave) {
		return this.noticias.get(chave);
	}
	
	// Retorna as notícias armazenadas
	public HashMap<String, Noticia> noticias() {
		return this.noticias;
	}
}
