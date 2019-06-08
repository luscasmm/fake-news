package br.ufrn.imd.modelo;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/*
 * WebScrapper: coletor de dados online
 * 
 * Essa classe atua na coleta de manchetes
 * e de suas respectivas datas pela internet.
 */

public class WebScraper {
	
	// Conecta à url passada e coleta manchete e data da publicação
	public static String[] coletar(String url) {
		
		Document document; // Guarda o objeto "DOM"
		
		try {
			
			document = Jsoup.connect(url).get(); // Retorna o documento html
			String[] coleta = new String[4]; // Armazena os resultados da coleta
			
			// Pega a primeira ocorrência de um h1 dentro de um main ou de uma section
			String manchete = document.select("main h1, section h1").get(0).text();
			
			// Pega a primeira ocorrência de qualquer elemento que possua um atributo cujo nome comece com "date"
			String data = document.select("time[datetime]").get(0).attr("datetime"); 
			
			// Verifica se achou alguma manchete, se não, retorna nulo 
			if(! manchete.isEmpty() ) {
				coleta[0] = null;
				coleta[1] = manchete;
				coleta[2] = url;
				
				// Verifica se achou a data, do contrário, define a data como nula
				if(! data.isEmpty()) {
					
					coleta[3] = data;
					
				} else {
					data = null;
				}
				
			} else {
				return null;
			}
			
			return coleta;
			
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		return null;
		
	}

}
