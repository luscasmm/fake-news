package br.ufrn.imd.dominio;

import br.ufrn.imd.modelo.CSV;
import br.ufrn.imd.modelo.Processador;
import br.ufrn.imd.modelo.Noticia;
//import br.ufrn.imd.modelo.WebScraper;

import java.util.ArrayList;
//import java.util.Map.Entry;

/*
 * Esta classe representa a central de armazenamento e manejo
 * dos dados do sistema. implementando as operações
 * sobre os principais modelos
 * 
 */

public class Armazenamento {
	
	private Jornal jornal; // HashMap com as notícias
	private String dataset; // Caminho para datset que alimentará o sistema
	
	
	public Armazenamento(String dataset) {
		
		this.jornal = new Jornal();
		this.dataset = dataset;
		
	}
	
	// Carrega as noticias armazenadas no dataset
	public void carregar() {
		ArrayList<String[]> conteudo = CSV.ler(dataset, ","); // Lê o dataset
		
		for(String[] linha : conteudo) {			
			adicionar(linha);
		}
		
	}
	
	
	public void adicionar(String[] campos) {
		String processado = Processador.processar(campos[1]); // Executa o pre-processamento da noticia
		String chave = Processador.hash(processado); // Criptografa para conseguir a chave
		
		this.jornal.adicionar(chave, new Noticia(campos[0], campos[1], processado, campos[2], campos[3]));
		
	}
	
	public void adicionar(String manchete, String link, String data) {
		String processado = Processador.processar(manchete); // Executa o pre-processamento da noticia
		String chave = Processador.hash(processado); // Criptografa para conseguir a chave
		
		this.jornal.adicionar(chave, new Noticia(null, manchete, processado, link, data));
	}
	
	// Getters
	
	public String getDataset() {
		return this.dataset;
	}
	
	public Jornal getJornal() {
		return this.jornal;
	}
	
	// Setters
	
	public void setDataset(String dataset) {
		this.dataset = dataset;
	}
	
	public void setJornal(Jornal jornal) {
		this.jornal = jornal;
	}
	
//	public static void main(String[] args) {
//		Armazenamento amz = new Armazenamento("data/boatos.csv");
//		
//		amz.carregar();
//		
//		
//		Jornal j = amz.getJornal();
//		
//		for(Entry<String, Noticia> tupla : j.noticias().entrySet()) {
//			System.out.println(tupla.getKey());
//		    System.out.println(tupla.getValue().getProcessado());
//		}
//		
//		String[] coleta = WebScraper.coletar("https://g1.globo.com/ba/bahia/noticia/2019/06/07/justica-da-bahia-manda-mec-suspender-bloqueio-nas-universidades.ghtml");
//		
//		System.out.println(coleta[1]);
//	}

}
