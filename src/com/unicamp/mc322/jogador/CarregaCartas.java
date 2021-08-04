package com.unicamp.mc322.jogador;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Classe CarregaCartas, que ira ser responsavel por ir na base de dados e buscar todas as cartas 
 * e retornar para o metodo que a chamou.
 * @author Richard Matias
 */
public class CarregaCartas {
	private ArrayList<String[]> carta = new ArrayList<String[]>();
	private ArrayList<String[]> cartasReino = new ArrayList<String[]>();
	public CarregaCartas() {
		this.carregarCartas();
	}
	/**
	 * Metodo responsavel por carregar todas as cartas na base de dados 
	 * e adc cada item ao ArrayList carta, 
	 * metodo sera utilizado na classe Jogador no construtor para criar 
	 * um deck aleatorio
	 * @author Richard Matias 
	 */
	private void carregarCartas() {
		try {
			File file = new File("cartas\\cartas.txt");
			Scanner myReader = new Scanner(file);
			while (myReader.hasNextLine() && myReader.hasNext() != false) {
				String data = myReader.nextLine();
				carta.add(data.split(","));
			}
			myReader.close();
		}catch (FileNotFoundException e) {
			System.out.println("Erro.");
			e.printStackTrace();     
		}
	}
	/**
	 * Metodo responsavel por retornar as cartas do ArrayList carta
	 * e com isso retornar todas as cartas carregadas da base de dados
	 * sera utilizado para criar o deck aleatorio
	 * @return ArrayList<String[]> - contendo todas as cartas e todas as informações de cada carta da base de dados
	 * @author Richard Matias 
	 */
	ArrayList<String[]> getCartas(){
		return this.carta;
	}
	/**
	 * Metodo responsavel por carregar as cartas dos reinos que o jogador selecionou
	 * e adc cada item ao ArrayList carta, 
	 * metodo sera utilizado na classe Jogador no construtor para criar 
	 * um deck especifico para o jogador.
	 * @param reino - nome dos reinos que o jogador selecionar para criar seu deck
	 * @author Richard Matias 
	 */
	private void carregaCartasReino(String reino) {
		try {
			File file = new File("cartas\\"+reino+".txt");
			Scanner myReader = new Scanner(file);
			while (myReader.hasNextLine() && myReader.hasNext() != false) {
				String data = myReader.nextLine();
				cartasReino.add(data.split(","));
			}
			myReader.close();
		}catch (FileNotFoundException e) {
			System.out.println("Erro.");
			e.printStackTrace();     
		}
	}
	/**
	 * Metodo responsavel por retornar as cartas do ArrayList cartasReino
	 * e com isso retornar todas as cartas dos reinos selecionados pelo 
	 * jogador e sera utilizado para criar o deck desejado
	 * @return ArrayList<String[]> - contendo as cartas dos reinos e todas as informações de cada carta da base de dados
	 * @param reino - nome dos reinos que o jogador selecionar para criar seu deck
	 * @author Richard Matias 
	 */
	ArrayList<String[]> getCartasReino(String nome){
		carregaCartasReino(nome);
		return this.cartasReino;
	}
}
