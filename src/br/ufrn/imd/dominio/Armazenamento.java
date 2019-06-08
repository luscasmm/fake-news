package br.ufrn.imd.dominio;

import br.ufrn.imd.modelo.CSV;
import br.ufrn.imd.modelo.Processador;
import br.ufrn.imd.modelo.Noticia;

import java.util.ArrayList;

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
		
		this.dataset = dataset;
		
	}
	
	// Carrega as noticias armazenadas no dataset
	public void carregar() {
		ArrayList<String[]> conteudo = CSV.ler(dataset, ",");
		String str;
		
		for(String[] linha : conteudo) {
			str = Processador.processar(linha[1]);
			System.out.println(Processador.hash(str) + " | " + str);
			//n.add(new Noticia(linha[0], linha[1], Processador.processar(linha[1]), linha[2], linha[3]));
		}
		
	}
	
	// Getters
	
	public String getDataset() {
		return this.dataset;
	}
	
	// Setters
	
	public void setDataset(String dataset) {
		this.dataset = dataset;
	}
	
	public static void main(String[] args) {
		Armazenamento amz = new Armazenamento("data/boatos.csv");
		
		amz.carregar();
	}

}
