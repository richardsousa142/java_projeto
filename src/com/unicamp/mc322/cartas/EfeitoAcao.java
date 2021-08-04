package com.unicamp.mc322.cartas;

import com.unicamp.mc322.jogador.Jogador;

/**
 * Interface EfeitoAcao, ira determinar o que cada metodo do Enum em Efeitos.java deve implementar
 * @author Richard Matias
 */
public interface EfeitoAcao {
	/**
	 * Motodo que ira servir para implementar os efeitos que cada carta tera ao ser usada durante 
	 * a partida do jogo
	 * metodo utilizado em Efeitos.java
	 * metodo tambem sera utilizado na classe Jogador no metodo setCartasEvocadas()
	 * @author Richard Matias
	 * @param carta - carta que sera afetada quando for conjurada ou durante a partida
	 * @param thisJogador - thisJogador dono da carta e que sera o jogador que sera afetado positivamente pelos efeitos de cada carta
	 * @param jogador - jogador que inimigo que sera afetado negativamente pelos efeitos que cada carta possuir
	 */
	public void acao(Cartas carta, Jogador thisJogador, Jogador jogador); 
}
