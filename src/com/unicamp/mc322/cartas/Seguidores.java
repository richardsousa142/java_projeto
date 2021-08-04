package com.unicamp.mc322.cartas;

/**
 * Classe Seguidores, ira conter todas as informacoes possiveis que uma instancia de Seguidores
 * possa ter, definindo assim sua interface de interacao com outras classes
 * @author Richard Matias
 */
public class Seguidores implements Cartas{
	private String nome;
	private int custoMana;
	private int poder;
	private int vida, vidaABatalha;
	@SuppressWarnings("unused")
	private int chave;
	private Efeitos efeito;
	private boolean marcar, atordoar, atacarNexus, destruiuCS;
	public Seguidores() {
		
	}
	/**
	 * Construtor da classe Seguidores que sera usado para inicializar cada nova carta 
	 * utilizado na classe CartasFactory no metodo criarCarta()
	 * @author Richard Matias
	 */
	public Seguidores(String nome, Efeitos efeito, int custoMana, int poder, int vida, int chave) {
		this.nome = nome;
		this.efeito = efeito;
		this.custoMana = custoMana;
		this.poder = poder;
		this.vida = vida;
		this.vidaABatalha = vida;
		this.chave = chave;
		this.atacarNexus = false;
		this.destruiuCS = false;
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
	@Override
	public String toString() {
		return "["+"Seguidores: "+this.nome+", Custo mana: "+this.custoMana+", Poder: "+this.poder+", Vida: "+this.vida+", Efeito: "+this.efeito+"]";
	}
	/**
	 * metodo utilizado para obter o poder da carta
	 * @author Richard Matias
	 * @return poder - contendo o valor do poder da carta 
	 */
	@Override
	public int getPoder() {
		return this.poder;
	}
	/**
	 * metodo utilizado para setar o poder da carta
	 * @author Richard Matias
	 * @param poder - contendo as informacoes de qual sera o poder da carta
	 */
	@Override
	public void setPoder(int poder) {
		this.poder = poder;
	}
	/**
	 * metodo utilizado para obter a vida da carta
	 * @author Richard Matias
	 * @return vida - contendo o valor de vida da carta 
	 */
	@Override
	public int getVida() {
		return this.vida;
	}
	/**
	 * metodo utilizado para setar a vida da carta
	 * @author Richard Matias
	 * @param vida - contendo o valor de vida da carta 
	 */
	@Override
	public void setVida(int vida) {
		this.vida = vida;
	}
	/**
	 * Metodo específico para a carta do Kindred, para que Kindred upe(Level up) precisa marcar
	 * inimigos, inimigos marcados morreram
	 * no Enum Nivel.java contem um metodo chamado VER_INIMIGOS_MARCADOS_MORREREM e este metodo ira invocar
	 * getMarcar() para modificar o estado de marcar 
	 * @author Richard Matias
	 * @return marcar - preenchido com a informacao sobre o valor booleano de marcar
	 */
	public boolean getMarcar() {
		return this.marcar ;
	}
	/**
	 * Metodo específico para a carta do Kindred, para que Kindred upe(Level up) precisa marcar
	 * inimigos, inimigos marcados morreram
	 * no Enum Efeitos.java contem um metodo chamado MARCAR e este metodo ira invocar
	 * setMarcar() para modificar o estado de marcar 
	 * @author Richard Matias
	 * @param marcar - preenchido com a informacao sobre o valor booleano de marcar
	 */
	public void setMarcar(boolean marcar) {
		this.marcar = marcar;
	}
	/**
	 * Metodo específico para a carta do Yasuo, para que Yasuo upe(Level up) precisa atordoar
	 * no Enum Nivel.java contem um metodo chamado ATORDOAR e este metodo ira invocar
	 * getAtordoar() para verificar se a carta ja cumpre as condições e se pode upar ou nao
	 * @author Richard Matias
	 * @return boolean preenchido com a informação sobre o atordoar
	 */
	public boolean getAtordoar() {
		return this.atordoar;
	}
	/**
	 * Metodo específico para a carta do Yasuo, para que Yasuo upe(Level up) precisa atordoar
	 * no Enum Efeitos.java contem um metodo chamado ATORDOAR e este metodo ira invocar
	 * setAtordoar() para modificar o estado de atordoar 
	 * @author Richard Matias
	 */
	public void setAtordoar(boolean atordoar) {
		this.atordoar = atordoar;
	}
	/**
	 * Metodo específico para a carta seguidor Sombra Viva, este metodo sera invocado no Enum Efeitos.java
	 * no metodo SOMBRA_VIVA para que Sombra Viva possa ativar seu efeito, e possibilitando
	 * o zed de upar(level up)
	 * @author Richard Matias
	 * @return atacarNexus - preenchido com o valor booleano
	 */
	public boolean getAtacarNexus() {
		return this.atacarNexus;
	}
	/**
	 * Metodo específico para a carta seguidor Sombra Viva, este metodo sera invocado na classe jogador
	 * no metodo setEspecificoSeguidor() para que Sombra Viva possa ativar seu efeito, que sera possibilitando
	 * o zed de upar(level up)
	 * @author Richard Matias
	 * @param atacarNexus - preenchido com o valor booleano
	 */
	public void setAtacarNexus(boolean atacarNexus) {
		this.atacarNexus = atacarNexus;
	}
	/**
	 * Metodo especifico para ter acesso ao efeito que esta carta tera ao ser invocada 
	 * ou durante a partida do jogo
	 * @return Efeitos - contendo as regras do que a carta fara ao ser invocada
	 * @author Richard Matias
	 */
	public Efeitos getEfeitos() {
		return this.efeito;
	}
	/**
	 * Metodo utilizado em para saber qual era a vida de uma carta antes da batalha
	 * sera usado em Enum Efeitos.java no metodo CAUSE_4_DANO_INIMIGO_DANO que invocara 
	 * getVidaAbatalha() para saber se esta carta ja tomou dano
	 * também sera usada em Nivel.java para upar uma carta especifica, Tryndamere, que 
	 * ao morrer a carta upa(level up)
	 * utilizada também em Tracos.java para ativar o traco de REGENERACAO
	 * @return int - contento as informacoes da vida da carta antes de uma batalha
	 * @author Richard Matias
	 */
	public int getVidaAbatalha() {
		return this.vidaABatalha;
	}
	/**
	 * Metodo específico para a carta seguidor Duelista, este metodo sera invocado no Enum Efeitos.java
	 * no metodo DESTRUIR_CS_PORO para que Duelista possa ativar seu efeito e ganhar uma 
	 * carta poro que sera adicionada as cartas evocadas
	 * @author Richard Matias
	 * @return destruiuCS - preenchido com o valor booleano
	 */
	public boolean getDestruiuCS() {
		return this.destruiuCS;
	}
	/**
	 * Metodo específico para a carta seguidor Duelista, este metodo sera invocado na classe jogador
	 * no metodo setEspecificoSeguidor() para que Duelista possa ativar seu efeito e ganhar uma 
	 * carta poro que sera adicionada as cartas evocadas
	 * @author Richard Matias
	 * @param destruiuCS - preenchido com o valor booleano
	 */
	public void setDestruiuCS(boolean destruiuCS) {
		this.destruiuCS = destruiuCS;
	}
}

