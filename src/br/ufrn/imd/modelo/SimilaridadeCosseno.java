package br.ufrn.imd.modelo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;


/*
 * Essa classe implementa os métodos necessários
 * ao cálculo de similaridade por cosseno entre
 * vetores de string
 */

public class SimilaridadeCosseno {
	
	// Recebe duas strings e retorna as palavras que as duas possuem (evita repeições)
	public static Object[] vocabulario(String[] str1, String[] str2) {
		TreeSet<String> vocabulario = new TreeSet<String>(); // Ávore rubro-negra auxiliar
		
		// Adiciona à árvore as palvras da primeira string
		for(String str : str1) {
			vocabulario.add(str);
		}
		
		// Adiciona à árvore as palvras da segunda string
		for(String str : str2) {
			vocabulario.add(str);
		}
		
		return vocabulario.toArray();
	}
	
	/*
	 * Retorna um vetor de correlações entre o vetor de string e o vocabulário.
	 * 
	 * O vetor resultante pode ter valores 1: caso o vetor de string possua
	 * a palavra da iteração do vocabulário; ou 2: caso o vetor de string não possua
	 * 
	 */
	public static Integer[] convergencias(String[] str, Object[] vocabulario) {
		Integer[] convergencias = new Integer[vocabulario.length];
		ArrayList<String> palavras = new ArrayList<String>(Arrays.asList(str));
				
		for(int i = 0; i < vocabulario.length; i++) {
			convergencias[i] = 0;
			
			if(palavras.contains(vocabulario[i])) convergencias[i] = 1;
		}	
		
		return convergencias;
	}
	
	// Calcula o produto escalar entre 2 vetores de Integer de mesmo tamanho
	public static int produtoEscalar(Integer[] v1, Integer[] v2) {
		int produto = 0;
		
		for(int i = 0; i < v1.length; i++) {
			produto += v1[i] * v2[i];
		}
		
		return produto;
	}
	
	// Calcula o módulo de um vetor
	public static double modulo(Integer[] v) {
		int soma = 0;
		
		for(int i = 0; i < v.length; i++) {
			soma += v[i] * v[i];
		}
		
		return (double) Math.sqrt(soma);
	}
	
	// Calcula o cosseno entre dois vetores
	public static double cosseno(Integer[] v1, Integer[] v2) {
		return (double) produtoEscalar(v1, v2) / (modulo(v1) * modulo(v2));
	}
	
	// Calcula a similaridade por cosseno entre dois vetores de string
	public static double similaridade(String[] str1, String[] str2) {
		Object[] vocabulario = vocabulario(str1, str2);
		
		Integer[] v1 = convergencias(str1, vocabulario);
		Integer[] v2 = convergencias(str2, vocabulario);
		
		return cosseno(v1, v2);
	}
}
