package com.unicamp.mc322.cartas;

/**
 * Classe Feiticos, ira conter todas as informacoes possiveis que uma instancia de Feiticos
 * possa ter, definindo assim sua interface de interacao com outras classes
 * @author Richard Matias
 */
public class Feiticos implements Cartas{
	private String nome;
	private int custoMana;
	private Tracos traco;
	private Efeitos efeitos;
	public Feiticos() {
		
	}
	public Feiticos(String nome, int custoMana, Efeitos efeitos, Tracos traco) {
		this.nome = nome;
		this.custoMana = custoMana;
		this.traco = traco;
		this.efeitos = efeitos;
	}
	/**
	 * metodo utilizado para obter o nome da carta
	 * @author Richard Matias
	 * @return nome - contendo o valor do nome da carta 
	 */
	@Override
	public String getNome() {
		return this.nome;
	}
	/**
	 * metodo utilizado para obter o custo de mana da carta
	 * @author Richard Matias
	 * @return custoMana - contendo o valor do custoMana da carta 
	 */
	@Override
	public int getCustoMana() {
		return this.custoMana;
	}
	/**
	 * metodo utilizado para setar o custo de mana da carta
	 * @author Richard Matias
	 * @param custoMana - contendo o valor do custoMana da carta 
	 */
	@Override
	public void setCustoMana(int custoMana) {
		this.custoMana = custoMana;
	}
	/**
	 * metodo utilizado para obter o poder da carta, que vem da interface
	 * porem, como esta carta é um feitico não tem poder, então retorna 0
	 * @author Richard Matias
	 * @return poder - contendo o valor do poder da carta 
	 */
	@Override
	public int getPoder() {
		return 0;
	}
	/**
	 * metodo utilizado para setar o poder da carta, que vem da interface	 
	 * porem, como esta carta é um feitico não tem poder, então nao retorna
	 * nada
	 * @author Richard Matias
	 * @param poder - contendo as informacoes de qual sera o poder da carta
	 */
	@Override
	public void setPoder(int poder) {}
	/**
	 * metodo utilizado para obter a vida da carta, que vem da interface	 
	 * porem, como esta carta é um feitico não tem poder, então retorna 0
	 * @author Richard Matias
	 * @param poder - contendo as informacoes de qual sera o poder da carta
	 */
	@Override
	public int getVida() {
		return 0;
	}
	/**
	 * metodo utilizado para setar a vida da carta, que vem da interface	 
	 * porem, como esta carta é um feitico não tem poder, então nao retorna
	 * nada
	 * @author Richard Matias
	 * @param poder - contendo as informacoes de qual sera o poder da carta
	 */
	@Override
	public void setVida(int vida) {}
	/**
	 * Metodo especifico para ter acesso ao efeito que esta carta tera ao ser invocada 
	 * ou durante a partida do jogo
	 * @return Efeitos - contendo as regras do que a carta fara ao ser invocada
	 * @author Richard Matias
	 */
	public Efeitos getEfeito() {
		return this.efeitos;
	}
	/**
	 * Metodo especifico para ter acesso ao Traco que esta carta tera e saber o que isto 
	 * causara durante a partida
	 * @return Tracos - contendo o traco da carta para realizar a acao durante a partida
	 * @author Richard Matias
	 */
	public Tracos getTraco() {
		return this.traco;
	}
}