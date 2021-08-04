package com.unicamp.mc322.jogador;


import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import javax.swing.JOptionPane;

import com.unicamp.mc322.cartas.Campeao;
import com.unicamp.mc322.cartas.Cartas;
import com.unicamp.mc322.cartas.CartasFactory;
import com.unicamp.mc322.cartas.Conjuraveis;
import com.unicamp.mc322.cartas.Feiticos;
import com.unicamp.mc322.cartas.Seguidores;
import com.unicamp.mc322.cartas.TipoCarta;
import com.unicamp.mc322.cartas.Tracos;
import com.unicamp.mc322.telas.TelaEscolherDeck;
/**
 * Classe Jogador, que ira ser responsavel por criar todas as informacoes dos jogadores
 * e como as outras classs irao interagir com ela
 * @author Richard Matias
 */
public class Jogador {
	private String nome;
	private int vidaNexus;
	private int mana, manaRodada;
	private int manaGuardada;
	private Cartas[] deck = new Cartas[40];//deck do jogador, criado de forma aleatoria
	private Cartas[] cartasEvocadas = new Cartas[6];//cartas evocadas do jogador
	private Cartas[] cartasAtaque = new Cartas[6];//cartas escolhidas para atacar
	private ArrayList<Cartas> cartasMao = new ArrayList<Cartas>();//cartas que o jogador ganhara a cada rodada
	private ArrayList<String[]> carta = new ArrayList<String[]>();//cartas armazenadas que vao ser pegas da 'base de dados'
	private CarregaCartas cartasCarregadas  = new CarregaCartas();//classe que pega as cartas na 'base de dados'
	private int qtdCartas, numFeiticosConjurados, qtdXDanoNexus, qtdXAliadosAtacaram,
				qtdCartasCompradas, qtdAbatidos, aliadosSobreviveram, manaGasta, qtdAliados8Custo,
				danoTotal, manaGastaTotal;
	private int tipoDeck;
	private String[] reinos = {"bilgewater","demacia","freljord","ilhadasSombras","ionia",
							   "noxus","piltover&Zaun","shurima","targon"};
	public Jogador(String nome) {
		this.nome = nome;
		this.qtdCartas = 0;
		this.numFeiticosConjurados = 0;
		this.qtdXDanoNexus = this.qtdXAliadosAtacaram = 0;
		this.qtdCartasCompradas = this.qtdAbatidos = 0;
		this.aliadosSobreviveram = this.manaGasta = 0;
		this.qtdAliados8Custo = 0;
		this.danoTotal = 0;
		this.vidaNexus = 20;
		this.mana = this.manaRodada = 1;
		this.manaGuardada = 0;
		this.carta = cartasCarregadas.getCartas();
		this.criarDeck();
		this.maoJogador();
	} 
	/**
	 * Metodo responsavel por criar um deck aletario para o jogador
	 * cria uma instancia de Cartas usando o factory method, passa para 
	 * criarCarta o tipo da carta no primeiro parametro e no segundo
	 * passa o vetor de string com todas as informacoes de cada carta 
	 * para que a carta seja criada.
	 * chamada do metodo existe() passando como parametro o nome da carta
	 * para verificar se a mesma ja esta presente dentro do deck, caso sim, 
	 * nao sera adicionada e outra sera sorteada
	 * Cartas não se repetem.
	 * @author Richard Matias
	 */
	private void criarDeckAleatorio() {
		Random rand = new Random();
		int i = 0;
		while(i < 40) {
			String[] itens = this.carta.get( rand.nextInt(this.carta.size()) ) ;
			Cartas cartas = CartasFactory.criarCarta(TipoCarta.valueOf(itens[0].split(":")[1]), itens);
			if(existe(cartas.getNome()) != false) {
				this.deck[this.qtdCartas] = cartas;
				this.qtdCartas++;
				i++;
			}
		}
	}
	/**
	 * Metodo responsavel por criar um deck com os reinos desejado pelo jogador
	 * cria uma instancia de Cartas usando o factory method, passa para 
	 * criarCarta o tipo da carta no primeiro parametro e no segundo
	 * passa o vetor de string com todas as informacoes de cada carta 
	 * para que a carta seja criada. 
	 * Cria uma instancia de TelaEscolherDeck que é uma interface onde o jogador
	 * podera escolher quais reinos quer escolher
	 * Apos o jogador escolher quais reinos deseja escolher, o metodo GetCartasReino 
	 * na classe CartasCarregadas sera chamado para retornar todas as cartas de cada reino
	 * chamada do metodo existe() passando como parametro o nome da carta
	 * para verificar se a mesma ja esta presente dentro do deck, caso sim, 
	 * nao sera adicionada e outra sera sorteada
	 * Cartas não se repetem.
	 * @author Richard Matias
	 */
	private void criarDeckDesejado() {
		TelaEscolherDeck telaEscolherDeck = new TelaEscolherDeck();
		telaEscolherDeck.reinos();
		ArrayList<Integer> indexReinos = telaEscolherDeck.getReinosIndex();
		for(int i = 0; i < indexReinos.size(); i++) {
			ArrayList<String[]> cartas = cartasCarregadas.getCartasReino(reinos[indexReinos.get(i)-1]);
			for(int j = 0; j < cartas.size();j++) {
				String[] itens = cartas.get(j);
				Cartas carta = CartasFactory.criarCarta(TipoCarta.valueOf(itens[0].split(":")[1]), itens);
				this.deck[this.qtdCartas] = carta;
				this.qtdCartas++;
				if(this.qtdCartas == 40) break;
			}
			cartas.clear();
			if(this.qtdCartas == 40) break;
		}
	}
	/**
	 * Metodo responsavel para perguntar ao jogador qual tipo de deck
	 * ele deseja, caso queira um deck aleatorio, ou seja, tipoDeck = 0
	 * o metodo criarDeckAleatorio() sera chamado.
	 * caso contrario, e o jogador escolha criar um deck com os reinos 
	 * desejados, ou seja, tipoDeck = 1, o metodo criarDeckDesejado() é chamado.
	 * @author Richard Matias
	 */
	private void criarDeck() {
		String[] options = {"Aleatorio", "Criar deck"};
		this.tipoDeck = JOptionPane.showOptionDialog(null,this.getNome()+"\nEscolha entre as opçoes","Deseja criar seu deck, sim \\ nao?",
				  JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,null,options,options[0]);
		if(this.tipoDeck == -1) this.tipoDeck = 0;
		if(this.tipoDeck == 0) criarDeckAleatorio();
		if(this.tipoDeck == 1) criarDeckDesejado();
	}
	/**
	 * Metodo utilizado para saber se uma carta ja esta presente dentro do 
	 * deck, para isso utilizamos o nome da carta e verificamos pelo nome
	 * se esta carta ja esta contida no vetor 
	 * @param nome - string nome fornecida pelos metodos criarDeckDesejado() / criarDeckAleatorio()
	 * @return boolean - caso a carta ja exista no deck, retorna false e do contrario retorna true
	 * @author Richard Matias
	 */
	private boolean existe(String nome) {
		for(int i = 0; i < this.qtdCartas; i++) {
			if(nome.equals(this.deck[i].getNome())) return false;
		}
		return true;
	}
	/**
	 * Metodo utilizado para saber se uma carta ja esta presente dentro do
	 * ArrayList cartasMao, para isso utilizamos a carta e verificamos 
	 * se esta carta ja esta contida no ArrayList
	 * @param carta - carta fornecida pelo metodo maoJogador()
	 * @return boolean - caso a carta ja exista em cartasMao retorna false e do contrario retorna true
	 * @author Richard Matias
	 */
	private boolean existe(Cartas carta) {
		for(int i = 0; i < this.cartasMao.size(); i++) {
			if(carta.equals(this.cartasMao.get(i))) return false;
		}
		return true;
	}
	/**
	 * Metodo utilizado para remover uma carta do deck como o deck sera 
	 * sera preenchido com mais de 40 cartas no caso de ser criado alea
	 * toriamente, precisamos remover a carta do deck quando esta for 
	 * adicionada a cartasMao, assim evitamos ter cartas repetidas no deck   
	 * @param carta - carta fornecida pelo metodo maoJogador()
	 * @author Richard Matias
	 */
	private void removerCartaDeck(Cartas carta) {
		for(int i = 0; i < this.qtdCartas; i++) {
			if(this.deck[i].equals(carta)) {
				this.deck[i] = this.deck[this.qtdCartas-1];
				this.deck[this.qtdCartas-1] = null;
				this.qtdCartas--;
			}
		}
	}
	/**
	 * Metodo utilizado para criar as cartas que serao a mao do jogador
	 * chamada do metodo existe() passando como parametro para saber se a
	 * carta ja esta contida no ArrayList cartasMao, caso esteja nao sera 
	 * adicionada e outra carta sera sorteada
	 * cartasMao sera criada inicialmente com apenas 4 cartas
	 * @author Richard Matias
	 */
	private void maoJogador() {
		Random rand = new Random();
		int i = 0;
		while (i < 4) {
			Cartas carta = this.deck[rand.nextInt(this.qtdCartas)];
			if( existe(carta) != false) {
				this.cartasMao.add(carta);
				removerCartaDeck(carta);
				i++;
			}
		}
	}
	/**
	 * Metodo utilizado para retornar todas as cartas que o jogador tem na mao e assim sera montada a 
	 * interface grafica do jogador
	 * @return ArrayList<Cartas> - preenchido e contendo todas as informacoes sobre as cartas que estao na mao do jogador
	 * @author Richard Matias
	 */
	public ArrayList<Cartas> getCartasMao(){
		return this.cartasMao;
	}
	/**
	 * Metodo utilizado para saber qual a vida atual do nexus do jogador e também sera usada para montar a interface
	 * grafica do jogador na classe Tela.java no metodo panelDeckNexus(), porem, também sera utilizado na classe Game no metodo start() para controlar o loop e saber
	 * quando o jogo deve ser finalizado pq a vida do nexus de algum dos jogadores zerou
	 * @return int - preenchido e contendo a informacao da vida do nexus
	 * @author Richard Matias
	 */
	public int getVidaNexus() {
		return this.vidaNexus;
	}
	/**
	 * Metodo utilizado para atualizar a vida do nexus do jogador
	 * devido a alguma alteracao no confronto
	 * este metodo sera utilizado em Traco.java, Efeitos.java, e para montar a interface
	 * e nesta classe no metodo atacar()
	 * @param vida - preenchido e contendo a informacao da nova vida do nexus
	 * @author Richard Matias
	 */
	public void setVidaNexus(int vida) {
		this.vidaNexus = vida;
	}
	/**
	 * metodo utilizado para obter a mana atual do jogador
	 * também sera utilizado para contruir a interface grafica
	 * na classe Tela.java no metodo panelMana()
	 * @author Richard Matias
	 * @return int - contendo o valor atual da mana do jogador
	 */
	public int getMana() {
		return this.mana;
	}
	/**
	 * metodo utilizado para setar a mana atual do jogador
	 * metodo sera usado aqui nesta classe no metodo rodada()
	 * @author Richard Matias
	 * @param mana - contendo o valor da nova mana do jogador 
	 */
	public void setMana(int mana) {
		this.mana = mana;
	}
	/**
	 * metodo utilizado para obter a mana guardada do jogador
	 * também sera utilizado para construir a interface grafica
	 * na classe Tela.java no metodo panelMana()
	 * @author Richard Matias
	 * @return int - contendo o valor atual da mana guardada do jogador
	 */
	public int getManaGuardada() {
		return this.manaGuardada;
	}
	/**
	 * metodo utilizado para setar a mana guardada do jogador
	 * metodo sera usado aqui nesta classe no metodo rodada()
	 * @author Richard Matias
	 * @param manaGuardada - contendo o valor da nova mana do jogador 
	 */
	public void setManaGuardada(int manaGuardada){
		this.manaGuardada = manaGuardada;
	}
	/**
	 * Metodo utilizado para adicionar uma carta nova a mao do jogador
	 * chamada do metodo existe() passando carta como parametro para saber se a
	 * carta ja esta contida no ArrayList cartasMao, caso esteja nao sera 
	 * adicionada e outra carta sera sorteada
	 * @author Richard Matias
	 */
	public void addCartaMaoRodada() {
		Random rand = new Random();
		while (true) {
			Cartas carta = this.deck[rand.nextInt(this.qtdCartas)];
			if( existe(carta) != false) {
				this.cartasMao.add(carta);
				break;
			}
		}
	}
	/**
	 * Metodo utilizado para ganhar uma nova carta e que sera invocado
	 * no Enum Efeitos.java no metodo D_GANHA_CARTA
	 * @author Richard Matias
	 * @return Cartas - preenchido com as informacoes da carta ganhada e que sera adicionada a cartas evocadas do jogador
	 */
	public Cartas getGanharCarta() {
		Random rand = new Random();
		for(int i = 0; i < this.deck.length; i++) {
			Cartas carta = this.deck[rand.nextInt(this.qtdCartas)];
			if( existe(carta) != false) {
				return carta;
			}
		}
		return null;
	}
	/**
	 * Metodo utilizado para remover uma carta da mao do jogador, este metodo é
	 * utilizado para atualizar as cartas da mao do jogador. Quando o jogador selecionar 
	 * uma carta da mao, esta sera incluida em cartas evocadas e entao deve ser removida da 
	 * mao do jogador.
	 * metodo também é utilizado para contruir a interface grafica na classe Tela.java
	 * nos metodo setCartaTelaEvocada()
	 * @author Richard Matias
	 * @param Carta - carta fornecida pela classe Tela.java e que devera ser removida
	 */
	public void removerCartaMao(Cartas carta) {
		this.cartasMao.remove(carta);
	}
	/**
	 * Metodo utilizado para saber o nome do jogador e tambem montar a interface grafica.
	 * @author Richard Matias
	 * @return nome - preenchido com o nome do jogador
	 */
	public String getNome() {
		return this.nome;
	}
	/**
	 * Metodo utilizado para setar uma nova carta as cartas evocadas do jogador
	 * caso a carta seja um feitico, não deve ir para cartas evocadas, entao tera seu
	 * seu efeito ativado instantaneamente.
	 * Caso a carta seja um campeao, ela sera incluida em cartas evocadas e tera seu efeito
	 * do Enum Efeitos.java ativado ao ser conjurada.
	 * Caso a carta seja um seguidor, ela sera incluida em cartas evocadas e tera seu efeito
	 * do Enum Efeitos.java ativado ao ser conjurada
	 * @author Richard Matias
	 * @param pos - posicao fornecida pela classe Tela.java no metodo setCartasEvocadas()
	 * @param carta - carta fornecida pela classe Tela.java no metodo setCartasEvocadas()
	 * @param jogador - jogador fornecido pela classe Tela.java no metodo setCartasEvocadas() 
	 */
	public void setCartaEvocada(int pos, Cartas carta, Jogador jogador) {
		Feiticos feitico; Campeao campeao; Seguidores seguidor;
		try {
			feitico = (Feiticos) carta;
			feitico.getEfeito().acao(carta, this, jogador);
			this.numFeiticosConjurados ++;
			this.manaGasta += feitico.getCustoMana();
			return;
		} catch (Exception e) {}
		try {
			try {
				campeao = (Campeao) carta;
				this.cartasEvocadas[pos] = carta;
				if(Conjuraveis.valueOf(campeao.getNome().replaceAll(" ", "")) != null)
					campeao.getEfeitos().acao(carta, this, jogador);
			} catch (Exception e) {}
			seguidor = (Seguidores) carta;
			this.cartasEvocadas[pos] = carta;
			seguidor.getEfeitos().acao(carta, this, jogador);
		} catch (Exception e) {
		}
		if(carta.getCustoMana() >= 8) this.qtdAliados8Custo++;
		this.qtdCartasCompradas++;
		this.manaGastaTotal += carta.getCustoMana();
	}
	/**
	 * Metodo utilizado para saber as cartas evocadas do jogador
	 * @author Richard Matias
	 * @return Cartas - preenchido com as informacoes da carta 
	 */
	public Cartas getCartasEvocadas(String nome) {
		for(int i = 0; i < this.cartasEvocadas.length; i++) {
			if(this.cartasEvocadas[i].getNome().equals(nome)) 
				return this.cartasEvocadas[i];
		}
		return null;
	}
	/**
	 * Metodo utilizado para setar as cartas de ataque do jogador e sera utilizado 
	 * tambem sera utilizado na classe Tela.java nos metodos setCartasAtaque(), setCartaTelaAtaque()
	 * @author Richard Matias
	 * @param pos - posicao fornecida pela classe Tela.java no metodo setCartasAtaque()
	 * @param carta - carta fornecida pela classe Tela.java no metodo setCartasAtaque()
	 */
	public void setCartasAtaque(int pos, Cartas carta) {
		this.cartasAtaque[pos] = carta;
	}
	/**
	 * Metodo utilizado para saber as cartas de ataque do jogador e sera utilizado nesta classe
	 * no metodo atacar() e aplicarDano()
	 * tambem sera utilizado na classe Tela.java nos metodos removeCartaAtaque(), setCartasAtaque()
	 * @author Richard Matias
	 * @return Cartas[] - preenchido com o vetor com todas as cartas do jogador
	 */
	public Cartas[] getCartasAtaque(){
		return this.cartasAtaque;
	}
	/**
	 * Metodo utilizado para saber as cartas evocadas do jogador e sera utilizado para montar 
	 * a interface grafica, sera utilizado na classe Tela.java nos metodos setCartasAtaque(), 
	 * telaCartasEvocadas(), setCartaTelaAtaque(), vazio()
	 * @author Richard Matias
	 * @return Cartas[] - preenchido com o vetor com todas as cartas do jogador
	 */
	public Cartas[] getEvocadas() {
		return this.cartasEvocadas;
	}
	/**
	 * Metodo utilizado para aplicar o traco da carta do jogador, e com isso
	 * ativar o traco dela durante o confronto com o jogador inimigo
	 * @author Richard Matias
	 * @param jogador - fornecido pelo metodo atacar(), nesta classe
	 * @param campeaoJ1 - fornecido pelo metodo atacar(), nesta classe
	 * @param campeaoJ2 - fornecido pelo metodo atacar(), nesta classe
	 */
	private void calculoDeDano(Jogador jogador, Campeao campeaoJ1, Campeao campeaoJ2) {
		campeaoJ1.getTraco().setJogador(this);
		campeaoJ1.getTraco().setJogadorAfetado(jogador);
		if(!campeaoJ1.getTraco().equals(Tracos.REGENERACAO)) {
			campeaoJ1.getTraco().acao(campeaoJ1, campeaoJ2);
		}
	}
	/**
	 * Metodo utilizado para aplicar o traco da carta do jogador, e com isso
	 * ativar o traco dela durante o confronto com o jogador inimigo
	 * @author Richard Matias
	 * @param jogador - fornecido pelo metodo atacar(), nesta classe
	 * @param campeaoJ1 - fornecido pelo metodo atacar(), nesta classe
	 * @param seguidorJ2 - fornecido pelo metodo atacar(), nesta classe
	 */
	private void calculoDeDano(Jogador jogador, Campeao campeaoJ1, Seguidores seguidorJ2) {
		campeaoJ1.getTraco().setJogador(this);
		campeaoJ1.getTraco().setJogadorAfetado(jogador);
		if(!campeaoJ1.getTraco().equals(Tracos.REGENERACAO)) {
			campeaoJ1.getTraco().acao(campeaoJ1, seguidorJ2);
		}
	}
	/**
	 * Metodo utilizado para aplicar o dano que uma carta ira efetuar uma na outra
	 * e com isso atualizar a vida de ambas as cartas.
	 * Caso a carta recebida seja o seguidor Duelista, verificamos se ele destruiu 
	 * uma carta do inimigo e se esta carta destruida é um seguidor, se for, ativamos
	 * o Efeitos.java dele
	 * @author Richard Matias
	 * @param i- posicao fornecido pelo metodo atacar(), nesta classe
	 * @param jogador - fornecido pelo metodo atacar(), nesta classe
	 */
	private void aplicarDano(int i, Jogador jogador) {
		this.cartasAtaque[i].setVida(this.cartasAtaque[i].getVida() - jogador.getCartasAtaque()[i].getPoder() );
		jogador.getCartasAtaque()[i].setVida(jogador.getCartasAtaque()[i].getVida() - this.cartasAtaque[i].getPoder() );						 	
		this.qtdXAliadosAtacaram++;
		this.danoTotal += jogador.getCartasAtaque()[i].getPoder() - this.cartasAtaque[i].getVida();
		jogador.danoTotal += this.cartasAtaque[i].getPoder() - jogador.getCartasAtaque()[i].getVida();
		
		if(this.cartasAtaque[i].getNome().equals("Duelista") ) 
			if(jogador.getCartasAtaque()[i].getVida() < 0 && jogador.getCartasAtaque()[i] instanceof Seguidores) 
				setEspecificoSeguidor((Seguidores)this.cartasAtaque[i]);
		if(jogador.getCartasAtaque()[i].getNome().equals("Duelista")) 
			if(this.cartasAtaque[i].getVida() < 0 && this.cartasAtaque[i] instanceof Seguidores) 
				setEspecificoSeguidor((Seguidores) jogador.getCartasAtaque()[i]);
	}
	/**
	 * Metodo utilizado para verificar se uma carta esta viva ainda
	 * com isso poderemos saber se ela deve ser removida das cartas evocadas ou 
	 * nao
	 * meotodo utilizado nesta classe, Jogador.java no metodo atacar()
	 * @author Richard Matias
	 * @return boolean - preenchido com as informacoes da vida da carta 
	 */
	private boolean checkIsAlive(Cartas carta) {
		//if(Objects.nonNull(carta))
			return carta.getVida() > 0;
		//return false;
	}
	/**
	 * Metodo utilizado para aplicar alguns comportamentos especificos de algumas
	 * cartas Campeao que precisam ser setados durante um confronto com o inimigo
	 * meotodo utilizado nesta classe, Jogador.java no metodo atacar()
	 * @author Richard Matias
	 * @param campeao - fornecido pelo metodo atacar() aqui nesta classe, Jogador.java
	 * @param dano - fornecido pelo metodo atacar() aqui nesta classe, Jogador.java
	 */
	private void setEspecificoCampeao(Campeao campeao, int dano) {
		if(campeao.getNome().equals("Garen")) campeao.addGolpear();
		if(campeao.getNome().equals("Katarina")) campeao.addGolpear();
		if(campeao.getNome().equals("Braum") ) {campeao.setVida(dano); campeao.setSobreviverDanoPx(true);}
		if(campeao.getNome().equals("Tryndamere") && !checkIsAlive(campeao)) campeao.upar(campeao, this);
	}
	/**
	 * Metodo utilizado para aplicar alguns comportamentos especificos de algumas
	 * cartas Seguidores que precisam ser setados durante um confronto com o inimigo
	 * meotodo utilizado nesta classe, Jogador.java no metodo atacar()
	 * @author Richard Matias
	 * @param seguidor - fornecido pelo metodo atacar() aqui nesta classe, Jogador.java
	 */
	private void setEspecificoSeguidor(Seguidores seguidor) {
		if(seguidor.getNome().equals("Sombra Viva")) {
			seguidor.setAtacarNexus(true);
			Campeao campeao;
			for(Cartas cartas : this.cartasEvocadas) {
				if(cartas.getNome().equals("Zed")) {
					try {
						campeao = (Campeao) cartas;
						campeao.setZedPodeUpar(true);
						break;
					} catch (Exception e) {}
				}
			}
		}
		if(seguidor.getNome().equals("Duelista")) {
			seguidor.setDestruiuCS(true);
		}
	}
	/**
	 * Metodo utilizado para remover uma carta das cartas evocadas do jogador,
	 * quando esta carta possuir uma vida < 0 ela deve ser removida para atualizar
	 * as cartas do jogador e também a interface grafica
	 * meotodo utilizado nesta classe, Jogador.java no metodo atacar()
	 * @author Richard Matias
	 * @param carta - fornecido pelo metodo atacar() aqui nesta classe, Jogador.java
	 * @param jogador - fornecido pelo metodo atacar() aqui nesta classe, Jogador.java
	 */
	private void removerCarta(Cartas carta, Jogador jogador) {
		for(int j = 0; j < jogador.getEvocadas().length;j++) {
			if(Objects.nonNull(jogador.getEvocadas()[j])) {
				if(jogador.getEvocadas()[j].equals(carta)) {
					jogador.getEvocadas()[j] = null;
				}
			}
		}
	} 
	/**
	 * Metodo utilizado para efetuar o ataque entre as cartas que estao na mesma posicao 
	 * no vetor de cartasAtaque, ou efetuar um ataque direto ao nexus do inimigo caso ele
	 * não tenha selecionado uma carta para defender.
	 * Metodo calculoDeDano() (  Explicacao na classe Tracos.java  )
	 * 	- Invocado para ativar o traco da carta1 para atacar a carta2 e também o jogador(jogador) se necessario
	 * 	- Invocado para ativar o traco da carta2 para atacar a carta1 e tambem o this(jogador) se necessario
	 * Metodo aplicarDano() 
	 * 	- Sera usado para aplicar o dano tomado em cadaa carta, e realizar o confronto direto
	 * Metodo setEspecificoCampeao()
	 * 	- Sera invocado e passado a carta para verificar se esta carta deveria ta tendo suas especificidades incrementadas ou atualizadas
	 * Metodo checkIsAlive()
	 * 	- metodo para saber se a carta esta viva, caso esteja incrementar +1 a quantidade de cartas que sobreviveram ao combate
	 * Metodo !checkIsAlive() 
	 * 	- metodo para saber se a carta esta viva, caso nao esteja, remover ela das cartas evocadas e incrementar +1 a quantidade de cartas abatidas
	 * Metodo setEspecificoSeguidor()
	 * 	- Sera invocado e passado a carta para verificar se esta carta deveria ta tendo suas especificidades incrementadas ou atualizadas
	 * 
	 * Caso ocorra de ter uma cartaAtaque na pos i e o jogador inimigo(jogador) não tiver selecionado uma carta para lutar contra 
	 * a cartaAtaque na pos i ira atacar diretamente o nexus do inimigo, logo apos isso, ataualizamos a vida do nexus do jogador e
	 * incrementamos +1 a quantidade de vezes que this atacou o nexus inimigo.
	 * @author Richard Matias
	 * @param jogador - fornecido pelo metodo atacar() na classe, Tela.java
	 */
	public void atacar(Jogador jogador) {
		for(int i = 0; i < this.cartasAtaque.length; i++) {
			if( Objects.nonNull(this.cartasAtaque[i]) ) {
				if(Objects.nonNull( jogador.getCartasAtaque()[i] ) ) {
					if(this.cartasAtaque[i] instanceof Campeao && jogador.getCartasAtaque()[i] instanceof Campeao) {
						Campeao campeao1 = (Campeao) this.cartasAtaque[i]; Campeao campeao2 = (Campeao) jogador.getCartasAtaque()[i];
						calculoDeDano(jogador, campeao1, campeao2);
						calculoDeDano(this, campeao2, campeao1);
						if(( 	campeao1.getTraco().equals(Tracos.ELUSIVO) && !campeao2.getTraco().equals(Tracos.ELUSIVO) ) 
					 	    ||	!campeao1.getTraco().equals(Tracos.ELUSIVO) && campeao2.getTraco().equals(Tracos.ELUSIVO)) {	
						}else { aplicarDano(i, jogador); }
						int dano = campeao1.getDanoTomado()+(jogador.getCartasAtaque()[i].getPoder() - this.cartasAtaque[i].getVida());
						setEspecificoCampeao(campeao1, dano); setEspecificoCampeao(campeao2, dano);
					}
					if( this.cartasAtaque[i] instanceof Campeao && jogador.getCartasAtaque()[i] instanceof Seguidores ) {
						Campeao campeao = (Campeao) this.cartasAtaque[i]; Seguidores seguidor = (Seguidores) jogador.getCartasAtaque()[i];
						calculoDeDano(jogador, campeao, seguidor);
						if(( 	campeao.getTraco().equals(Tracos.ELUSIVO))){}
						else { aplicarDano(i, jogador); }
						int dano = campeao.getDanoTomado()+(jogador.getCartasAtaque()[i].getPoder() - this.cartasAtaque[i].getVida());
						setEspecificoCampeao(campeao, dano); 
					}
					if( this.cartasAtaque[i] instanceof Seguidores && jogador.getCartasAtaque()[i] instanceof Campeao ) {
						Seguidores seguidor = (Seguidores) this.cartasAtaque[i]; Campeao campeao = (Campeao) jogador.getCartasAtaque()[i];
						calculoDeDano(this, campeao, seguidor);
						if(( 	campeao.getTraco().equals(Tracos.ELUSIVO))){}
						else { aplicarDano(i, jogador); }
						int dano = campeao.getDanoTomado()+(jogador.getCartasAtaque()[i].getPoder() - this.cartasAtaque[i].getVida());
						setEspecificoCampeao(campeao, dano); 
					}
					if(this.cartasAtaque[i] instanceof Seguidores && jogador.getCartasAtaque()[i] instanceof Seguidores ) {
						aplicarDano(i, jogador); 
					}
					if(checkIsAlive(this.cartasAtaque[i])) this.aliadosSobreviveram++;
					if(checkIsAlive(jogador.getCartasAtaque()[i])) jogador.aliadosSobreviveram++;
					if(this.cartasAtaque[i].getNome().equals("Tryndamere")) setEspecificoCampeao((Campeao) this.cartasAtaque[i], 0);
					if(jogador.getCartasAtaque()[i].getNome().equals("Tryndamere")) setEspecificoCampeao((Campeao) jogador.getCartasAtaque()[i], 0);
					if(!checkIsAlive(this.cartasAtaque[i])) {removerCarta(this.cartasAtaque[i], this); jogador.setQtdAbatidos();}
					if(!checkIsAlive(jogador.getCartasAtaque()[i])) {removerCarta(jogador.getCartasAtaque()[i], jogador); this.qtdAbatidos++;}
				}else {
					if(this.cartasAtaque[i].getNome().equals("Sombra Viva")) {setEspecificoSeguidor((Seguidores)this.cartasAtaque[i]);}
					jogador.setVidaNexus(jogador.getVidaNexus() - this.cartasAtaque[i].getPoder());
					this.qtdXAliadosAtacaram++;
				}
			}
		}
	} 
	/**
	 * Metodo utilizado para alterar a acao de algumas cartas que funcionam por rodada
	 * como as cartas com o traco de REGENERACAO E BARREIRA, este metodo ira certificar 
	 * que o traco dessas cartas so ative a cada rodada 
	 * @author Richard Matias
	 */
	public void mudaAcaoCartas() {
		for(int i = 0; i < this.getEvocadas().length; i++) {
			if(this.getEvocadas()[i] instanceof Campeao){
				Campeao campeao = (Campeao) this.getEvocadas()[i];
				if(campeao.getTraco().equals(Tracos.REGENERACAO )) {
					campeao.getTraco().setJogadorAfetado(this);
					campeao.getTraco().acao(campeao, null);
				}
				if(campeao.getTraco().equals(Tracos.REGENERACAO ) || campeao.getTraco().equals(Tracos.BARREIRA))
					campeao.setAcao(true);
			}
		}
	}
	/**
	 * Metodo utilizado para atualizar os dados do jogador por rodada como
	 * mana, manaGuardadam, adicionar uma nova carta ao fim de cada rodada e 
	 * verificar quais cartas que o jogador tem nas cartas evocadas e na mao 
	 * podem upar 
	 * @author Richard Matias
	 * @param jogador - fornecido pela classe Tela.java no metodo atacar()
	 */
	public void rodada(Jogador jogador) {
		this.manaRodada = this.manaRodada == 10? 10 : this.manaRodada + 1;
		jogador.setManaGuardada( jogador.getMana() >= 3 ? 3 : jogador.getMana() );
		jogador.setMana(manaRodada);
		Campeao campeao;
		for(Cartas carta : jogador.getEvocadas()) {
			try {
				campeao = (Campeao) carta;
				campeao.upar(campeao, jogador);
			} catch (Exception e) {}
		}
		for(Cartas carta : jogador.getCartasMao()) {
			try {
				campeao = (Campeao) carta;
				campeao.upar(campeao, jogador);
			} catch (Exception e) {}
		}
		jogador.addCartaMaoRodada();
	}
	/**
	 * Metodo utilizado para verificar se o vetor de cartas evocadas do jogador
	 * ja esta cheio, caso esteja cheio o jogador não podera adicionar mais nenhuma 
	 * carta.
	 * metodo utilizado para controlar a infertace grafica e não adicionar mais do que 6
	 * cartas a tela
	 * @author Richard Matias
	 * @param jogador - fornecido pela classe Tela.java no metodo setCartaTelaEvocada()
	 * @return boolean - contendo a informação se o deck esta full (false) ou vazio(true)
	 */
	public boolean getDeckIsFull(Jogador jogador) {
		for(Cartas cartas : jogador.getEvocadas()) 
			if(Objects.isNull(cartas))
				return false;
		return true;
	}
	/**
	 * Metodo utilizado para verificar se o vetor de cartas evocadas do jogador
	 * ja esta vazio, assim evitamos um loop infinito 
	 * em Enum Efeitos.java no metodo DE_ROBUSTO_1ALIADOS
	 * @author Richard Matias
	 * @param jogador - fornecido pela classe Efeitos.java no metodo DE_ROBUSTO_1ALIADOS
	 * @return boolean - contendo a informação se o deck esta vazio ou nao
	 */
	public boolean getDeckIsEmpty(Jogador jogador) {
		for(Cartas cartas : jogador.getEvocadas()) 
			if(Objects.nonNull(cartas))
				return false;
		return true;
	}
	/**
	 * Metodo utilizado para obter a quantidade de feiticos conjurados e 
	 * metodo utilizado em Enum Nivel.java no metodo CONJURAR_6_FEITICOS, 
	 * @author Richard Matias
	 * @return int - preenchido com a informação de qts feiticos foram conjurados
	 */
	public int getNumFeiticosConjurados(){
		return this.numFeiticosConjurados;
	}
	/**
	 * Metodo utilizado para setar a quantidade de vezes o jogador atacou o nexus inimigo
	 * metodo utilizado em Enum Tracos.java no metodo SOBREPUJAR, 
	 * @author Richard Matias
	 */
	public void addQtdVezesDanoNexus() {
		this.qtdXDanoNexus++;
	}
	/**
	 * Metodo utilizado para obter a quantidade de vezes o jogador atacou o nexus inimigo
	 * metodo utilizado em Enum Efeitos.java no metodo DANO_5_VEZES_NEXUS, 
	 * @author Richard Matias
	 * @return int - preenchido com a informação de vezes o jogador atacou o nexus inimigo
	 */
	public int getQtdVezesDanoNexus() {
		return this.qtdXDanoNexus;
	}
	/**
	 * Metodo utilizado para obter a quantidade de vezes cartas aliadas ja atacaram
	 * metodo utilizado em Enum Efeitos.java nos metodos ALIADOS_ATACAR_4_VEZES, ATACAR_COM_6,
	 * DOZE_ALIADOS_ATACARAM, ATACAR_INIMIGO_6_VEZES, ATACAR_INIMIGO_10_VEZES
	 * @author Richard Matias
	 * @return int - preenchido com a informação quantidade de vezes cartas aliadas ja atacaram
	 */
	public int getQtdXAliadosAtacaram() {
		return this.qtdXAliadosAtacaram;
	}
	/**
	 * Metodo utilizado para obter a quantidade de cartas o jogador ja comprou
	 * metodo utilizado em Enum Efeitos.java no metodo COMPRAR_9_CARTAS, INVOCAR_10_UNIDADES
	 * @author Richard Matias
	 * @return int - preenchido com a quantidade de cartas o jogador ja comprou
	 */
	public int getQtdCartasCompradas() {
		return this.qtdCartasCompradas;
	}
	/**
	 * Metodo utilizado para obter a quantidade de cartas o jogador ja abateu
	 * metodo utilizado em Enum Efeitos.java no metodo ABATER_2_INIMIGOS, MORTE_4_ALIADOS, 
	 * TRES_ALIADOS_MORREREM, SEIS_UNIDADES_MORREREM
	 * @author Richard Matias
	 * @return int - preenchido com a quantidade de cartas o jogador ja abateu
	 */
	public int getQtdAbatidos() {
		return this.qtdAbatidos;
	}
	/**
	 * Metodo utilizado para setar a quantidade de cartas o jogador ja abateu
	 * metodo utilizado nesta classe, Jogador.java, no metodo atacar()
	 * @author Richard Matias
	 */
	public void setQtdAbatidos() {
		this.qtdAbatidos++;
	}
	/**
	 * Metodo utilizado para obter a quantidade de cartas do jogador sobreviveram ao
	 * confronto.
	 * metodo utilizado em Enum Efeitos.java no metodo ALIADOS_S_4_GOLPES, CINCO_TIME_SOBREVIVERAM, 
	 * @author Richard Matias
	 * @return int - preenchido com a quantidade de cartas do jogador sobreviveram
	 */
	public int getQtdAliadosSobreviveram() {
		return this.aliadosSobreviveram;
	}
	/**
	 * Metodo utilizado para obter a quantidade de mana o jogador ja gastou com feiticos
	 * metodo utilizado em Enum Efeitos.java no metodo GASTAR_6_MANA_FEITICOS, CARTA_10_MANA, 
	 * @author Richard Matias
	 * @return int - preenchido com a quantidade de mana o jogador ja gastou com feiticos
	 */
	public int getManaGasta() {
		return this.manaGasta;
	}
	/**
	 * Metodo utilizado para obter a quantidade de dano total o jogador ja aplicou no inimigo
	 * metodo utilizado em Enum Efeitos.java no metodo CAUSAR_12_DANO, CAUSAR_15_DANO, DOZE_DANO_FORA_COMBATE,
	 * CAUSAR_10_DANO
	 * @author Richard Matias
	 * @return int - preenchido com a quantidade de dano total o jogador ja aplicou no inimigo
	 */
	public int getDanoTotal() {
		return this.danoTotal;
	}
	/**
	 * Metodo utilizado para obter a quantidade de cartas com custo maior ou igual a 8 o jogador ja comprou
	 * metodo utilizado em Enum Efeitos.java no metodo INVOCAR_2_ALIADOS_8_CUSTO
	 * @author Richard Matias
	 * @return int - preenchido com a quantidade de cartas com custo maior ou igual a 8 o jogador ja comprou
	 */
	public int getQtdAliado8Custo() {
		return this.qtdAliados8Custo;
	}
	/**
	 * Metodo utilizado para obter a quantidade de mana total o jogador ja gastou comprando cartas
	 * metodo utilizado em Enum Efeitos.java no metodo CARTA_10_MANA
	 * @author Richard Matias
	 * @return int - preenchido com a quantidade de mana total o jogador ja gastou comprando cartas
	 */
	public int getManaGastaTotal() {
		return this.manaGastaTotal;
	}
}