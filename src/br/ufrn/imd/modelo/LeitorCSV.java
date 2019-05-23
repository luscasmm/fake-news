package br.ufrn.imd.modelo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;

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
			
		} catch (IOException e) {
	
			e.printStackTrace();
		}
	}
	
	public String lerLinha() throws IOException {
		String linha = null;
		
		try {
			linha = csv.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if(linha == null) csv.close();
		
		return linha;
	}
	
	public static void main(String[] args) {
		LeitorCSV leitor = new LeitorCSV("data/boatos.csv");
		
		try {
			String linha = leitor.lerLinha();
			int i = 0;
			
			while(linha != null) {
				System.out.println(i + " :" + linha);
				linha = leitor.lerLinha();
				i++;
			}
			
			System.out.println(linha);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	
	}

}
