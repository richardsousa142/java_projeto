package com.unicamp.mc322.telas;

import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * Classe TelaEscolherDeck, que ira ser responsavel por criar todas as informacoes de quando um jogador 
 * selecionar criar um deck personalizado por ele
 * e como as outras classs irao interagir com ela
 * @author Richard Matias
 */
public class TelaEscolherDeck {
	private ArrayList<Integer> indexReinos = new ArrayList<Integer>();
	private Integer pos;
	private int qtd = 0;
	private String msg = "Reinos possiveis para montar o deck\n"+ 
						 "1 - bilgewater\n" +
						 "2 - demacia\n" + 
						 "3 - freljord\n"+
						 "4 - ilhadasSombras\n"+
						 "5 - ionia\n"+
						 "6 - noxus\n"+
						 "7 - piltover&Zaun\n"+
						 "8 - shurima\n"+
						 "9 - targon\n"+
						 "Escolha apenas uma opção, de 1 a 9";
	/**
	 * Metodo que ficara responsavel por pegar os index dos reinos que o jogador quer 
	 * selecionar para fazer parte do deck dele e filtrar se ele esta digitando um valor
	 * valido, se esta digitando um numero e não um caractere
	 * @return int - contendo o index do reino desejado
	 * @author Richard Matias 
	 */
	private int getPos() {
		while(true) {
			String result = JOptionPane.showInputDialog(msg);
			if(result == null || result.isEmpty()) return -1;
			try {
				pos = Integer.parseInt( result );
				while(pos <= 0 || pos >= 10) {
					JOptionPane.showMessageDialog (null,"Somente números de 1 a 6.", "Mensagem", JOptionPane.ERROR_MESSAGE);
					pos = Integer.parseInt( JOptionPane.showInputDialog(null, "Qual posição deseja que a carta fique de 1 a 6?") );
				}
				return pos;
			}catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Somente números!", "Mensagem", JOptionPane.ERROR_MESSAGE);
				System.out.println(e);
			}
		}
	}
	/**
	 * Metodo que sera responsavel por pedir ao jogador por fornecer até 4 reinos e verificar
	 * se os reinos não sao repetidos, e adicionar ao ArrayList de Integers, quando selecionar
	 * 4 reinos validos o while termina
	 * metodo tambem sera utilizado na classe Jogador.java no metodo criarDeckDesejado()
	 * @author Richard Matias 
	 */
	public void reinos() {
		JOptionPane.showMessageDialog (null,"Escolha até 4 reinos, 1 por vez", "Mensagem", JOptionPane.INFORMATION_MESSAGE);
		while(true) {
			int posicao = getPos();
			if(indexReinos.contains(posicao))
				JOptionPane.showMessageDialog (null,"Você ja escolheu está opção", "Mensagem", JOptionPane.ERROR_MESSAGE);
			if(!indexReinos.contains(pos)) {
				indexReinos.add(posicao);
				qtd++;
				if(qtd == 4) break;
			}
		}
	}
	/**
	 * Metodo que sera responsavel por fornecer todos os index dos reinos selecionados pelo jogador.
	 * metodo tambem sera utilizado na classe Jogador.java no metodo criarDeckDesejado()
	 * @return ArrayList<Integer> - contendo os index de cada reino valido selecionado pelo jogador.
	 * @author Richard Matias 
	 */
	public ArrayList<Integer> getReinosIndex(){
		return this.indexReinos;
	}
}


