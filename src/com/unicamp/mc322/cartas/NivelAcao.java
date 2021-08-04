package com.unicamp.mc322.cartas;

import com.unicamp.mc322.jogador.Jogador;
/**
 * Interface NivelAcao, ira determinar o que cada metodo do Enum em Nivel.java deve implementar
 * @author Richard Matias
 */
public interface NivelAcao {
	/**
	 * Motodo que ira servir para implementar os niveis que cada carta tera que cumprir para poder upar
	 * metodo utilizado em Nivel.java 
	 * @author Richard Matias
	 * @param carta - carta que sera afetada quando for conjurada ou durante a partida
	 * @param jogador - dono da carta e sera o jogador que sera afetado positivamente pelos level up de cada carta
	 */
	public void acao(Cartas carta, Jogador jogador);
}
