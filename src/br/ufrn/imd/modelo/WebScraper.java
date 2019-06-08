package br.ufrn.imd.modelo;

import java.io.IOException;
import java.util.HashMap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/*
 * WebScrapper: coletor de dados online
 * 
 * Essa classe atua na coleta de manchetes
 * e de suas respectivas datas pela internet.
 */

public class WebScraper {
	
	// Conecta à url passada e coleta manchete e data da publicação
	public static HashMap<String, String> coletar(String url) {
		
		Document document; // Guarda o objeto "DOM"
		
		try {
			
			document = Jsoup.connect(url).get(); // Retorna o documento html
			
			
			HashMap<String, String> coleta = new HashMap<String, String>();
			
			String manchete; // Armazena manchete coletaa
			String data; // Armazena data coletada
			
			// Pega a primeira ocorrência de um h1 dentro de um main ou de uma section
			Elements elementos = document.select("main h1, section h1");
			
			if(! elementos.isEmpty()) {
				manchete = elementos.get(0).text();
			} else {
				return null;
			}
			
			// Pega a primeira ocorrência de qualquer elemento que possua um atributo cujo nome comece com "date"
			elementos = document.select("time[datetime]");
			
			if(! elementos.isEmpty()) {
				data = document.select("time[datetime]").get(0).attr("datetime"); 
			} else {
				data = null;
			}		
			
			coleta.put("manchete", manchete);
			coleta.put("data", data);
			
			return coleta;
			
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		return null;
		
	}

}
