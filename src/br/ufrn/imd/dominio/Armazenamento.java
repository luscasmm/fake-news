package br.ufrn.imd.dominio;

import br.ufrn.imd.modelo.CSV;
import br.ufrn.imd.modelo.Processador;
import br.ufrn.imd.modelo.Noticia;

import java.util.ArrayList;
import java.util.Map.Entry;

/*
 * Esta classe representa a central armazenamento e manejo
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
		ArrayList<String[]> conteudo = CSV.ler(dataset, ",");
		
		String processado; // armazena o texto processado
		String chave; // armazena a chave
		
		for(String[] linha : conteudo) {
			
			processado = Processador.processar(linha[1]); // Executa o pre-processamento da noticia
			chave = Processador.hash(processado); // Criptografa para conseguir a chave
			
			// Adiciona a nova noticia ao mapa
			this.jornal.adicionar(chave, new Noticia(linha[0], linha[1], processado, linha[2], linha[3]));
		}
		
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
//	}

}
