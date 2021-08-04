package com.unicamp.mc322.cartas;

/**
 * Interface cartas, ira conter as informacoes basicas que os tres tipos de cartas existentes
 * devem respeitar
 * utilizado para criar novas instancias de cartas na classe CartasFactory no metodo criarCarta()
 * @author Richard Matias
 */
public interface Cartas {
	/**
	 * metodo utilizado para obter o nome da carta
	 * @author Richard Matias
	 * @return nome - contendo o valor do nome da carta 
	 */
	String getNome();
	/**
	 * metodo utilizado para obter o custo de mana da carta
	 * @author Richard Matias
	 * @return custoMana - contendo o valor do custoMana da carta 
	 */
	int getCustoMana();
	/**
	 * metodo utilizado para setar o custo de mana da carta
	 * @author Richard Matias
	 * @param custoMana - contendo o valor do custoMana da carta 
	 */
	void setCustoMana(int custoMana);
	/**
	 * metodo utilizado para obter o poder da carta
	 * @author Richard Matias
	 * @return poder - contendo o valor do poder da carta 
	 */
	int getPoder();
	/**
	 * metodo utilizado para setar o poder da carta
	 * @author Richard Matias
	 * @param poder - contendo as informacoes de qual sera o poder da carta
	 */
	void setPoder(int poder);
	/**
	 * metodo utilizado para obter a vida da carta
	 * @author Richard Matias
	 * @return vida - contendo o valor de vida da carta 
	 */
	int getVida();
	/**
	 * metodo utilizado para setar a vida da carta
	 * @author Richard Matias
	 * @param vida - contendo o valor de vida da carta 
	 */
	void setVida(int vida);
}

