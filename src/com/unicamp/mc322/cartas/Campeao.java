package com.unicamp.mc322.cartas;

import com.unicamp.mc322.jogador.Jogador;
/**
 * Classe campeao, ira conter todas as informacoes possiveis que uma instancia de campeao
 * possa ter, definindo assim sua interface de interacao com outras classes
 * @author Richard Matias
 */
public class Campeao implements Cartas{
	private String nome;
	private int custoMana;
	private int poder, amanhecer;
	private int vida, vidaABatalha, golpear, danoTomado;
	@SuppressWarnings("unused")
	private int chave, congelar, anoitecer; 
	private Nivel nivel;
	private Efeitos efeito;
	private Tracos traco;
	private boolean acao, upar, marcar, bladeOfExile, atordoar,
					pilarGelo, sobreverDanoPx, zedPodeUpar, revMachado;
	public Campeao() {
	}
	/**
	 * Construtor da classe compeao que sera usado para inicializar cada nova carta 
	 * utilizado em CartasFactory no metodo criarCarta()
	 * @author Richard Matias
	 */
	public Campeao(String nome, int custoMana, int poder, int vida, int chave, Efeitos efeito, Tracos traco, Nivel nivel) {
		this.nome = nome;
		this.custoMana = custoMana;
		this.poder = poder;
		this.vida = vida;
		this.chave = chave;
		this.efeito = efeito;
		this.traco = traco;
		this.acao = this.upar = true;
		this.pilarGelo = false;
		this.zedPodeUpar = false;
		this.revMachado = false;
		this.vidaABatalha = vida;
		this.nivel = nivel;
		this.golpear = this.danoTomado = this.amanhecer = 0;
		this.congelar = this.anoitecer = 0;
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
		return "["+"Campeao: "+this.nome+", Custo mana: "+this.custoMana+", "
				+ "Poder: "+this.poder+", Vida: "+this.vida+", Efeito: "+
				this.efeito+", Traço: "+this.traco+", Nivel: "+this.nivel + "]";
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
	 * Metodo especifico para ter acesso ao Traco que esta carta tera e saber o que isto 
	 * causara durante a partida
	 * @return Tracos - contendo o traco da carta para realizar a acao durante a partida
	 * @author Richard Matias
	 */
	public Tracos getTraco() {
		return this.traco;
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
	 * Metodo especifico para ter acesso as regras que esta carta precisara cumprir para upar
	 * @return Nivel - contendo as regras do que a carta upe ou nao
	 * @author Richard Matias
	 */
	private Nivel getNivel() {
		return this.nivel;
	}
	/**
	 * Metodo específico para cartas que funcionam com traço por rodada como Regeneracao e Barreira
	 * no Enum Tracos.java getAcao() é invocado para saber se a carta pode regenerar nesta rodada ou nao
	 * @author Richard Matias
	 * @return boolean - contendo o valor de acao true / false
	 */
	public boolean getAcao() {
		return this.acao;
	}
	/**
	 * Metodo específico para cartas que funcionam com traço por rodada como Regeneracao e Barreira
	 * Cada vez que uma carta ativa estes traços a acao é mudada para true, e assim a carta não ativa 
	 * esta caracteristicas a todo momento. 
	 * este metodo é usado na classe jogador no metodo mudaAcaoCartas()
	 * @param acao - preenchido com o valor de booleano de acao
	 * @author Richard Matias
	 */
	public void setAcao(boolean acao) {
		this.acao = acao;
	} 
	/**
	 * Metodo específico para a carta do Garen, para que Garen upe(Level up) precisa atacar 2 vezes
	 * Enum Nivel.java chamado GOLPEAR_2_VEZES e sera invocado a cada nova rodada.
	 * na classe jogador o metodo setespecíficoCampeao() ira invocar  addGolpear() para cada vez que garen golpear alguem
	 * @author Richard Matias
	 */
	public void addGolpear() {
		if( this.golpear <= 2) this.golpear++;
	}
	/**
	 * Metodo específico para a carta do Garen, para que Garen upe(Level up) precisa atacar 2 vezes
	 * Enum Nivel.java chamado GOLPEAR_2_VEZES e invocara getGolpear() para verificar se a carta 
	 * cumpre os requisitor para upar ou nao.
	 * @author Richard Matias
	 */
	public int getGolpear() {
		return this.golpear;
	}
	/**
	 * Metodo específico para a carta do Braum, para que Braum upe(Level up) 
	 * Enum Nivel.java chamado SOBREVIVER_10_DANO_TOTAL sera invocado
	 * e este invocara getDanoTomado() para verificar se a carta ja cumpre as condições e se pode upar ou nao
	 * @author Richard Matias
	 */
	public int getDanoTomado() {
		return this.danoTomado;
	}
	/**
	 * Metodo específico para a carta do Braum, para que Braum upe(Level up) precisa tankar 10 de dano total
	 * então o metodo setespecíficoCampeao() na classe Jogador, ira invocar setDanoTomado() 
	 * e este sera chamado toda vez que Braum sofre dano
	 * @param int - contendo as informacoes sobre o dano sofrido pelo campeao
	 * @author Richard Matias
	 */
	public void setDanoTomado(int danoTomado) {
		this.danoTomado = danoTomado;
	}
	/**
	 * Metodo usado para upar uma carta, a cada rodada completada verificamos se uma carta evocada ou que esteja
	 * na mao do jogador pode upar
	 * @param carta - recebe a carta que sera usada para verificar se pode upar
	 * @param jogador - recebe o jogador que tera as cartas upadas
	 * @author Richard Matias
	 */
	public void upar(Campeao carta, Jogador jogador) {
		carta.getNivel().acao(carta, jogador);
	}
	/**
	 * Metodo usado no Enum Nivel.java e serve par saber se uma carta pode pode upar(level up)
	 * como uma carta pode upar somente uma vez, apos upar a carta seta setPodeUpar() para false
	 * @param upar - preenchido com o valor de upar
	 * @author Richard Matias
	 */
	public void setPodeUpar(boolean upar) {
		this.upar = upar;
	}
	/**
	 * Em Enum Nivel.javagetPodeUpar é invocado e serve par saber se uma carta pode pode upar(level up)
	 * como uma carta pode upar somente uma vez, upar inicia com valor true e apos a carta upar
	 * passa a ser false
	 * @return boolean - preenchido com o valor de upar
	 * @author Richard Matias
	 */
	public boolean getPodeUpar() {
		return this.upar;
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
	 * Metodo específico para a carta do Riven, para que Riven upe(Level up) 
	 * ao invocar a carta de riven um metodo no Enum Nivel.java chamdo LAMINA_DO_EXILIO sera invocado
	 * e este invocara getBladeOfExile() para verificar se a carta ja cumpre as condições e se pode upar ou nao
	 * setBladeOfExile() para modificar o estado de bladeOfExile
	 * @return boolean - preenchido com a informação sobre o bladeOfExile
	 * @author Richard Matias
	 */
	public boolean getBladeOfExile() {
		return this.bladeOfExile;
	}
	/**
	 * Metodo específico para a carta do Riven, para que Riven upe(Level up) 
	 * ao invocar a carta de riven um metodo no Enum Efeitos.java chamdo REFORJAR sera invocado
	 * e este invocara setBladeOfExile() e ira setar um valor
	 * setBladeOfExile() para modificar o estado de bladeOfExile
	 * @param  bladeOfExile - preenchido com a informação sobre o bladeOfExile
	 * @author Richard Matias
	 */
	public void setBladeOfExile(boolean bladeOfExile) {
		this.bladeOfExile = bladeOfExile;
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
	 * Metodo específico para a carta da Leona, quando Leona for evocada 
	 * no Enum Efeitos.java contem um metodo chamado AMANHECER e este metodo ira invocar
	 * addAmanhecer() para adicionar +1 ao contador para a carta mais forte que ela atordoar
	 * @author Richard Matias
	 */
	public void addAmanhecer() {
		this.amanhecer++;
	}
	/**
	 * Metodo específico para a carta da Leona, para que Leona upe (level up)
	 * no Enum Nivel.java contem um metodo chamado AMANHECER_4_VEZES e este metodo ira invocar
	 * getAmanhecer() para verificar se a carta ja cumpre as condições e se pode upar ou nao.
	 * @author Richard Matias
	 * @return int preenchido com a informação sobre o contador
	 */
	public int getAmanhecer() {
		return this.amanhecer;
	}
	/**
	 * Metodo específico para a carta da Ashe, para que Ashe upe (level up)
	 * no Enum Nivel.java contem um metodo chamado CONGELAR_5_INIMIGOS e este metodo ira invocar
	 * getCongelar() para verificar se a carta ja cumpre as condições e se pode upar ou nao.
	 * @author Richard Matias
	 * @return int preenchido com a informação sobre o contador
	 */
	public int getCongelar() {
		return this.congelar;
	}
	/**
	 * Metodo específico para a carta da Ashe, quando Ashe for evocada 
	 * no Enum Efeitos.java contem um metodo chamado CONGELA_INIMIGO_FORTE e este metodo ira invocar
	 * addCongelar() para adicionar +1 ao contador para a carta mais forte que ela congelar
	 * @author Richard Matias
	 */
	public void addCongelar() {
		this.congelar++;
	}
	/**
	 * Metodo específico para a carta do trundle, para que trundle upe(Level up)
	 * no Enum Nivel.java contem um metodo chamado PILAR_DE_GELO e este metodo ira invocar
	 * getPilarGelo() para verificar se a carta ja cumpre as condições e se pode upar ou nao.
	 * @return boolean preenchido com a informação sobre o pilarGelo
	 * @author Richard Matias
	 */
	public boolean getPilarGelo() {
		return this.pilarGelo;
	}
	/**
	 * Metodo específico para a carta do trundle, para que trundle upe(Level up)
	 * precisa invocar o pilar de gelo.
	 * No Enum Efeitos.java tem um efeito PILAR_GELO que invocara este metodo
	 * @param pilarGelo - Valor booleano que ira ser setado de acordo com o fato do pilar ter sido ou invocado
	 * @author Richard Matias
	 */
	public void setPilarGelo(boolean pilarGelo) {
		this.pilarGelo = pilarGelo;
	}
	/**
	 * Metodo específico para a carta do nocturne, para que nocturne upe(Level up)
	 * no Enum Nivel.java contem um metodo chamado anoitecer e este metodo ira invocar
	 * getAnoitecer() para verificar se a carta pode upar ou nao.
	 * @return int preenchido com a informação sobre o contador
	 * @author Richard Matias
	 */
	public int getAnoitecer() {
		return this.anoitecer;
	}
	/**
	 *Metodo específico para a carta do nocturne, quando nocturne for evocado e afetar 5 cartas inimigas 
	 *Nocturne upa de level(level up), então para cada carta evocada o inimigo tiver, este metodo é chamado 
	 *no Enum Efeitos.java no enum anoitecer e adc +1 ao contador
	 *@autor Richard Matias
	 */
	public void addAnoitecer() {
		this.anoitecer++;
	}
	/**
	 * Metodo específico para a carta do Braum, este metodo sera invocado na classe jogador
	 * no metodo setEspecificoCampeao() para que braum possa ativar seu efeito
	 * @author Richard Matias
	 * @param sobreviverDanoPx - preenchido com o valor booleano
	 */
	public void setSobreviverDanoPx(boolean sobreviverDanoPx) {
		this.sobreverDanoPx = sobreviverDanoPx;
	}
	/**
	 * Metodo específico para a carta do Braum, para que Braum ative seu efeito
	 * Enum Efeitos.java chamado SOBREVIVER_P_PORO sera invocado e ira ativer o efeito da carta
	 * @author Richard Matias
	 * @return boolean - preenchido com o valor de sovreviverDanoPx
	 */
	public boolean getSobreviberDanopx() {
		return this.sobreverDanoPx;
	}
	/**
	 * Metodo específico para a carta do Zed, este metodo sera invocado na classe jogador
	 * no metodo setEspecificoSeguidores() para que Zed possa ativar possa upar(level up)
	 * @author Richard Matias
	 * @param zedPodeUpar - preenchido com o valor booleano
	 */
	public void setZedPodeUpar(boolean zedPodeUpar) {
		this.zedPodeUpar = zedPodeUpar;
	}
	/**
	 * Metodo específico para a carta do Zed, este metodo sera invocado em Enum Nivel.java
	 * no metodo ATACAR_NEXUS para verificar se a carta pode upar ou nao.
	 * @author Richard Matias
	 * @return zedPodeUpar - preenchido com o valor booleano true / false
	 */
	public boolean getZedPodeUpar() {
		return this.zedPodeUpar;
	}
	/**
	 * Metodo específico para a carta do Draven, este metodo sera invocado em Enum Efeitos.java
	 * no metodo REV_MACHADO para aplicar os efeitos de draven. Logo em seguida este metodo 
	 * sera invocado e sera setado o valor para true, assim zed ficara apto a poder upar
	 * @author Richard Matias
	 * @param revMachado - preenchido com o valor booleano true / false
	 */
	public void setRevMachado(boolean revMachado) {
		this.revMachado = revMachado;
	}
	/**
	 * Metodo específico para a carta do Draven, este metodo sera invocado em Enum Nivel.java
	 * no metodo GOLPEAR_1_REV_MACHADO ara verificar se a carta pode upar ou nao. 
	 * @author Richard Matias
	 * @return revMachado - preenchido com o valor booleano true / false
	 */
	public boolean getRevMachado() {
		return this.revMachado;
	}
}
