package br.ufrn.imd.dominio;

import br.ufrn.imd.modelo.CSV;
import br.ufrn.imd.modelo.Processador;
import br.ufrn.imd.modelo.Noticia;

import java.util.ArrayList;
import java.util.HashMap;

/*
 * Esta classe representa o armazenamento
 * dos dados do sistema. implementando as operações
 * sobre os principais modelos
 * 
 */

public class Armazenamento {
	
	private HashMap<String, Noticia> noticias; // Mapa de notícias
	private String dataset; // Caminho para datset que alimentará o sistema
	
	
	public Armazenamento(String dataset) {
		ArrayList<Noticia> n = new ArrayList<Noticia>();
		this.dataset = dataset;
		
		ArrayList<String[]> conteudo = CSV.ler(dataset, ",");	
		
		for(String[] linha : conteudo) {
			n.add(new Noticia(linha[0], linha[1], Processador.processar(linha[1]), linha[2], linha[3]));
		}
	
		for(Noticia news : n) {
			System.out.println(news.getProcessado());
		}
	}
	
	public static void main(String[] args) {
		Armazenamento a = new Armazenamento("data/boatos.csv");
	}

}
