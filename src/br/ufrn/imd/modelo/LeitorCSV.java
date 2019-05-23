package br.ufrn.imd.modelo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/*
 * Leitor CSV 
 * 
 * Esta classe implementa os métodos necessários
 * à abertura e leitura de um arquivo CSV
 * 
 */

public class LeitorCSV {
	private BufferedReader csv; // Objeto de leitura de arquivo
	
	public LeitorCSV(String caminho) {
		try { // Tenta abrir o arquivo localizado em caminho
			
			csv = new BufferedReader(new FileReader(caminho)); // Instancia o objeto de leitura de arquivos
			
			
			lerLinha(); // Ler o cabeçalho
			
		} catch (FileNotFoundException e) { // Captura exceção caso o arquivo não exista
			
			e.printStackTrace();
			
		}
	}
	
	public String lerLinha() {
		String linha = null;
		
		try {
			linha = csv.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return linha;
	}
}
