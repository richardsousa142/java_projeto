package com.unicamp.mc322.cartas;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * Classe InvocarCarta, que ira ser responsavel por ir na base de dados e buscar a carta 
 * e tornar para o metodo que a chamou.
 * @author Richard Matias
 */
public class InvocarCarta {
	/**
	 * Metodo responsavel por buscar uma carta especifica na base de dados e retornar seus valores
	 * As respostas são enviadas para a Enum Efeitos.java
	 * @param nome - contem o nome fornecido pelo metodo especifico no Enum.java
	 * @author Richard Matias 
	 */
	public static String[] invocarCarta(String nome) {
		String[] itens = null;
		try {
			File file = new File("cartas\\invocaveis.txt");
			Scanner myReader = new Scanner(file);
			while (myReader.hasNextLine() && myReader.hasNext() != false) {
				String data = myReader.nextLine();
				itens = data.split(",");
				if(itens[2].split(":")[1].equals(nome)) break;
			}
			myReader.close();
		}catch (FileNotFoundException e) {
			System.out.println("Erro.");
			e.printStackTrace();     
		}
		return itens;
	}
	/**
	 * Metodo responsavel por buscar uma carta feitico aleatoriamente na base de dados e retornar seus valores
	 * As respostas são enviadas para a Enum Efeitos.java
	 * @param nome - contem o nome fornecido pelo metodo especifico no Enum.java
	 * @author Richard Matias 
	 */
	public static String[] invocarFeiticoAleatorio(String nome) {
		String[] itens = null;
		try {
			File file = new File("cartas\\invocaveis.txt");
			Scanner myReader = new Scanner(file);
			while (myReader.hasNextLine() && myReader.hasNext() != false) {
				String data = myReader.nextLine();
				itens = data.split(",");
				if(itens[0].split(":")[1].equals("FEITICOS") && !itens[2].split(":")[1].equals(nome) ) {
					break;
				}
			}
			myReader.close();
		}catch (FileNotFoundException e) {
			System.out.println("Erro.");
			e.printStackTrace();     
		}
		return itens;
	}
	/**
	 * Metodo responsavel por buscar uma carta seguidor aleatoriamente na base de dados e retornar seus valores
	 * As respostas são enviadas para a Enum Efeitos.java
	 * @author Richard Matias 
	 */
	public static ArrayList<String[]> invocarSeguidorAleatorio() {
		ArrayList<String[]> itens = new ArrayList<String[]>(); 
		String[] dados ;
		try {
			File file = new File("cartas\\invocaveis.txt");
			Scanner myReader = new Scanner(file);
			while (myReader.hasNextLine() && myReader.hasNext() != false) {
				String data = myReader.nextLine();
				dados = data.split(",");
				if(dados[0].split(":")[1].equals("SEGUIDORES"))
					itens.add(dados);
			}
			myReader.close();
		}catch (FileNotFoundException e) {
			System.out.println("Erro.");
			e.printStackTrace();     
		}
		return itens;
	}
}
