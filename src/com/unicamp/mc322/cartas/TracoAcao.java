package com.unicamp.mc322.cartas;

/**
 * Interface TracoAcao, ira determinar o que cada metodo do Enum em Traco.java deve implementar
 * @author Richard Matias
 */
public interface TracoAcao {
	/**
	 * Motodo que ira servir para implementar os tracos que cada carta tera ao ser usada durante 
	 * a partida no jogo
	 * metodo utilizado em Traco.java
	 * metodo também sera utilizado na classe Jogador no metodo calculoDeDano()
	 * 
	 * as cartas passadas como parametro teram seus atributos usados para afetar ou o jogador1 ou 
	 * o jogador 2, dependendo o tipo do Traco que a carta possuir
	 * @author Richard Matias
	 * @param carta1 - carta que sera o ponto de referencia para afetar negativamente o jogador2
	 * @param carta2 - carta que sera o ponto de referencia para afetar negativamente o jogador1
	 */
	public void acao(Cartas carta1, Cartas carta2);
}
     