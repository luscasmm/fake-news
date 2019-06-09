package br.ufrn.imd.dominio;

import br.ufrn.imd.modelo.Noticia;
import br.ufrn.imd.modelo.Processador;

import java.util.HashMap;
import java.util.Map.Entry;

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
	
	// Remove uma noticia do mapa com base na chave passada
	public void remover(String chave) {
		this.noticias.remove(chave);
	}
	
	// Retorna a notícia sob a chave passada
	public Noticia getNoticia(String chave) {
		return this.noticias.get(chave);
	}
	
	// Retorna as notícias armazenadas
	public HashMap<String, Noticia> getNoticias() {
		return this.noticias;
	}
	
	// Gera um objeto notícia de acordo com o vetor passado
	public Entry<String, Noticia> gerarNoticia(String[] campos) {
		String processado = Processador.processar(campos[1]); // Executa o pre-processamento da noticia
		String chave = Processador.hash(processado); // Criptografa para conseguir a chave
		
		return new java.util.AbstractMap.SimpleEntry<String, Noticia>(chave, new Noticia(campos[0], campos[1], processado, campos[2], campos[3]));		
	}
	
	// Gera um objeto notícia de acordo com os dados passaddos
	public Entry<String, Noticia> gerarNoticia(String manchete, String link, String data) {
		String processado = Processador.processar(manchete); // Executa o pre-processamento da noticia
		String chave = Processador.hash(processado); // Criptografa para conseguir a chave
		return new java.util.AbstractMap.SimpleEntry<String, Noticia>(chave, new Noticia(null, manchete, processado, link, data));
	}
	
}
