//package br.ufrn.imd.modelo;
//
//import java.util.ArrayList;
//
///*
// * Esta classe representa uma notícia.
// * 
// * Implementa os métodos necessários
// * ao pre-processamento e à formatação
// * de uma notícia
// * 
// */
//public class Noticia {
//	private String id;
//	private String conteudo;
//	private String link;
//	private String timestamp;
//	
//	static int limiar = 3; // Define o limiar para desconsiderar palavras
//	
//	public Noticia(String id, String conteudo, String link, String timestamp) {
//		this.id = id;
//		this.conteudo = conteudo;
//		this.link = link;
//		this.timestamp = timestamp;
//		
//		
//		String[] tokens = this.conteudo.split(" ");
//		this.conteudo = "";
//		
//		for(String palavra : tokens) {
//			if(palavra.length() > limiar) {
//				if(this.conteudo != "") {
//					this.conteudo += " ";
//				}
//				this.conteudo += palavra;
//			}	
//		}
//	}
//	
//	// Getters
//	
//	String getId() {
//		return this.id;
//	}
//	
//	String getConteudo() {
//		return this.conteudo;
//	}
//	
//	String getLink() {
//		return this.link;
//	}
//	
//	String getTimestamp() {
//		return this.timestamp;
//	}
//	
//	// Setters
//	
//	void setId(String id) {
//		this.id = id;
//	}
//	
//	void setConteudo(String conteudo) {
//		this.conteudo = conteudo;
//	}
//	
//	void setLink(String link) {
//		this.link = link;
//	}
//	
//	void setTimestamp(String timestamp) {
//		this.timestamp = timestamp;
//	}
//	
//	// Testando a classe	
//	public static void main(String args[]) {
//		ArrayList<ArrayList<String>> dataset = CSV.ler("data/boatos.csv", ",");
//		ArrayList<Noticia> noticias = new ArrayList<Noticia>();
//		
//		for(String[] linha : dataset) {
//			noticias.add(new Noticia(linha[0], linha[1], linha[2], linha[3]));
//		}
//		
//		for(Noticia n: noticias) {
//			System.out.println("ID: " + n.getId() + " Conteúdo: " + n.getConteudo());
//		}
//	}
//	
//	
//}
