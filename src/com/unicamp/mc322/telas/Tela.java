package com.unicamp.mc322.telas;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import javax.swing.*;

import com.unicamp.mc322.cartas.Campeao;
import com.unicamp.mc322.cartas.Cartas;
import com.unicamp.mc322.cartas.Efeitos;
import com.unicamp.mc322.cartas.Feiticos;
import com.unicamp.mc322.cartas.Tracos;
import com.unicamp.mc322.jogador.Jogador;
/**
 * Classe Tela, que ira ser responsavel por criar todas a interface grafica do jogo
 * contendo todas as informacoes do jogador1 e jogador2
 * e como as outras classs irao interagir com ela
 * @author Richard Matias
 */
public class Tela implements ActionListener{
	private JFrame container;
	private JPanel panel, telaLateral, telaLateralEsquerda;
	private Jogador jogador1, jogador2, jogadorAtual;
	
	private JButton ataque, passe, pronto, defende, trocar; 
	private JButton[] cartasJogador1 = new JButton[6], cartasJogador2 = new JButton[6];//cartas evocadas
	//cartas selecionadas entre as evocadas para efetuar o ataque (jogador1)/(jogador2)
	private JButton[] cartasAtaque1 = new JButton[6], cartasAtaque2 = new JButton[6];
	private ArrayList<JButton> cartasMaoJogador1 = new ArrayList<JButton>(), cartasMaoJogador2 = new ArrayList<JButton>();;//cartas mao
	private Integer pos;
	private boolean atacarJg1, atacarJg2, passarJg1, passarJg2, defenderJg1, defenderJg2, turno;
	
	public Tela() {
		this.atacarJg1 = this.atacarJg2 = false;
		this.defenderJg1 = this.defenderJg2 = false;
		this.passarJg1 = this.passarJg2 = false;
		this.turno = false;
	}
	public Tela(Jogador jogador1, Jogador jogador2) {
		this.jogador1 = jogador1;
		this.jogador2 = jogador2;
		this.jogadorAtual = jogador1;
	}
	/**
	 * Metodo utilizado para concentrar todas as caracteristicas comuns que um button
	 * tem ao ser criado, dessa forma evitando escrever todo esse codigo para cada
	 * button escrito no codigo.
	 * @param icon - string com o nome do icon q ficara no button, fornecido pelos metodos telaCartasEvocadas() e telaCartasMao() em Tela.java
	 * @param actionCommand - string que sera responsavel por setar a str de comando, que sera usado para ativar as funcionalidades do botao
	 * @param name - nome que o botao tera e que sera responsavel por ajudar a achar qual o index dessa carta nas cartas do jogador
	 * @param buttonText - string contendo o hp / poder de cada carta e sera usado para criar a interface e tambem atualizar esses attr apos o confronto
	 * @return JButton - preenchido com todas as informacões que este button tera
	 * @author Richard Matias
	 */
	private JButton button(String icon, String actionCommand, String name, String buttonText) {
		JButton button = new JButton(buttonText);
		button.setFont(new Font("Arial", Font.BOLD, 13));
		button.setForeground(Color.WHITE);
		button.setIcon(new ImageIcon("imagens\\"+ icon.toUpperCase()+".png"));
		button.setPreferredSize(new Dimension(76, 114));
		button.setBorder(BorderFactory.createEmptyBorder());
		button.addActionListener(this);
		button.setActionCommand(actionCommand);
		button.setHorizontalTextPosition(SwingConstants.CENTER  );
		button.setName(name);
		return button;
	}
	/**
	 * Metodo utilizado para construir a string que sera usada para exibir a vida e poder em cada carta da interface
	 * verifica se a carta é um feitico, pois, como feiticos não possuem nem poder, nem vida entao não deve ser construida
	 * esta string para exibir na carta
	 * @param carta - carta contendo as informacoes necessarias, fornecido pelos metodos telaCartasEvocadas() e telaCartasMao() em Tela.java
	 * @return String - preenchido com todas as informacões que a string tera
	 * @author Richard Matias
	 */
	private String textButton(Cartas carta) {
		String str = "";
		if(!(carta instanceof Feiticos))
			str = "<html><br><br><br><br><br><br>"+ "<h6>"+ carta.getPoder() + "&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&ensp;&nbsp;&nbsp;"+ carta.getVida() + "</h6>" +"</html>";
		return str;
		
	}
	/**
	 * Metodo utilizado para obter a primeira posicao vazia nas cartas evocadas do jogador
	 * com isso podemos adicionar a nova carta evocada a posicao correta e evitar bugs
	 * @param jogador - jogador fornecido pelo metodo setCartasEvocadas() 
	 * @return int - preenchido com a informação da primeira pos vazia nas cartas evocadas do jogador
	 * @author Richard Matias
	 */
	private int vazio(Jogador jogador) {
		for(int i = 0; i < jogador.getEvocadas().length; i++) {
			if(Objects.isNull(jogador.getEvocadas()[i])) {
				return i;
			}
		}
		return -1;
	}
	/**
	 * Metodo utilizado para obter a primeira posicao vazia nas cartas de ataque do jogador
	 * com isso podemos adcionar a nova carta de ataque na posicao correta e evitar bugs
	 * @param jogador - jogador fornecido pelo metodo setCartasAtaque() 
	 * @return int - preenchido com a informação da primeira pos vazia nas cartas de ataque do jogador
	 * @author Richard Matias
	 */
	private int vazio(Cartas[] ataque) {
		for(int i = 0; i < ataque.length; i++) {
			if(ataque[i] == null) {
				return i;
			}
		}
		return -1;
	}
	/**
	 * Metodo utilizado para atribuir a nova carta as cartas evocadas do jogador e com isso
	 * poder construir a nova interface com a nova carta
	 * @param jogador - jogador fornecido pelo metodo setCartaTelaEvocada() 
	 * @param i - index da carta no array cartasMao do jogador, fornecido pelo metodo setCartaTelaEvocada()
	 * @author Richard Matias
	 */
	private void setCartasEvocadas(Jogador jogador, int i) {
		int j = jogador.equals(jogador1) ? vazio(jogador1) : vazio(jogador2);
		if(jogador.equals(jogador1)) this.jogador1.setCartaEvocada(j, jogador1.getCartasMao().get(i), jogador2);
		if(jogador.equals(jogador2)) this.jogador2.setCartaEvocada(j, jogador2.getCartasMao().get(i), jogador1);
	}
	/**
	 * Metodo utilizado para atribuir a nova carta as cartas de ataque do jogador e com isso
	 * poder construir a nova interface com a nova carta
	 * @param jogador - jogador fornecido pelo metodo setCartaTelaAtaque() 
	 * @param i - index da carta no array cartasMao do jogador, fornecido pelo metodo setCartaTelaEvocada()
	 * @author Richard Matias
	 */
	private void setCartasAtaque(Jogador jogador, int i) {
		String str = jogador.equals(jogador1) ? textButton(this.jogador1.getEvocadas()[i]) : textButton(this.jogador2.getEvocadas()[i]);
		int posicao = jogador.equals(jogador1)? vazio(jogador1.getCartasAtaque()) : vazio(jogador2.getCartasAtaque());
		if(posicao != -1) {
			if(jogador.equals(jogador1)) {
				this.cartasAtaque1[posicao] = button(this.cartasJogador1[i].getName()+"-m", "Jogador1", this.cartasJogador1[i].getName(),str);
				this.jogador1.setCartasAtaque(posicao, jogador1.getEvocadas()[i]);	
			}else {
				this.cartasAtaque2[posicao] = button(this.cartasJogador2[i].getName()+"-m", "Jogador2", this.cartasJogador2[i].getName(),str);
				this.jogador2.setCartasAtaque(posicao, jogador2.getEvocadas()[i]);
			}
		}
	}
	/**
	 * Metodo utilizado para atribuir a nova carta as cartas de ataque do jogador e com isso
	 * poder construir a nova interface com a nova carta
	 * este metodo é utilizado quando o jogador for se defender de um ataque e por isso o 
	 * mesmo pode escolher contra qual carta ele vai lutar
	 * @param jogador - jogador fornecido pelo metodo setCartaTelaDefesa() 
	 * @param posicao - posicao fornecida pelo metodo setCartaTelaDefesa(), e sera onde o jogador escolheu que a carta fique (de 1 a 6)
	 * @param i - index da carta no array cartasMao do jogador, fornecido pelo metodo setCartaTelaDefesa()
	 * @author Richard Matias
	 */
	private void setCartasAtaque(Jogador jogador, int posicao, int i) {
		String str = jogador.equals(jogador1) ? textButton(this.jogador1.getEvocadas()[i]) : textButton(this.jogador2.getEvocadas()[i]);
		if(jogador.equals(jogador1)) {
			this.cartasAtaque1[posicao-1] = button(this.cartasJogador1[i].getName()+"-m", "Jogador1", this.cartasJogador1[i].getName(),str);
			this.jogador1.setCartasAtaque(posicao-1, jogador1.getEvocadas()[i]);
		}else {
			this.cartasAtaque2[posicao-1] = button(this.cartasJogador2[i].getName()+"-m", "Jogador1", this.cartasJogador2[i].getName(),str);
			this.jogador2.setCartasAtaque(posicao-1, jogador2.getEvocadas()[i]);	
		}
	}
	/**
	 * Metodo utilizado para construir a interface do jogador, e mostrar suas cartas evocadas
	 * @param jogador - jogador fornecido pelo metodo telaSuperior() 
	 * @return JPanel - preenchido com todas as informacoes sobre as cartas evocadas do jogador
	 * @author Richard Matias
	 */
	private JPanel telaCartasEvocadas(Jogador jogador) {
		JPanel panel = new JPanel(new BorderLayout()); JPanel cartas = new JPanel();
		for(int i = 0; i < jogador.getEvocadas().length; i++) {
			if(jogador.getEvocadas()[i] != null) {
				String str = textButton(jogador.getEvocadas()[i]);
				String cmd = jogador.equals(jogador1)? "Jogador1" : "Jogador2";
				JButton button = button(jogador.getEvocadas()[i].getNome()+"-m", cmd, jogador.getEvocadas()[i].getNome(), str);
				if(jogador.equals(jogador1)) { this.cartasJogador1[i] = button; cartas.add(this.cartasJogador1[i], BorderLayout.SOUTH );}
				if(jogador.equals(jogador2)) { this.cartasJogador2[i] = button; cartas.add(this.cartasJogador2[i], BorderLayout.SOUTH );}
			}else {
				if(jogador.equals(jogador1)) this.cartasJogador1[i] = button("preenche","","","");
				if(jogador.equals(jogador2)) this.cartasJogador2[i] = button("preenche","","","");
			}
		}
		panel.add(cartas, BorderLayout.CENTER );
		return panel;
	}
	/**
	 * Metodo utilizado para construir a interface do jogador, e mostrar as cartas que o jogador 
	 * tem na mao que ele podera comprar caso tenha mana
	 * @param jogador - jogador fornecido pelo metodo telaSuperior() 
	 * @return JPanel - preenchido com todas as informacoes sobre as cartas que o jogador tem na mao
	 * @author Richard Matias
	 */
	private JPanel telaCartasMao(Jogador jogador) {
		JPanel cartasMao = new JPanel(new BorderLayout()); JPanel cartas = new JPanel(); JScrollPane scroll = new JScrollPane(cartas); 
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		for(int i = 0; i < jogador.getCartasMao().size(); i++) {
			String str = textButton(jogador.getCartasMao().get(i));
			String cmd = jogador.equals(jogador1)? "Cartas Jogador1" : "Cartas Jogador2";
			JButton button = button(jogador.getCartasMao().get(i).getNome()+"-m", cmd, jogador.getCartasMao().get(i).getNome(), str);
			if(jogador.equals(jogador1)) this.cartasMaoJogador1.add(button);
			if(jogador.equals(jogador2)) this.cartasMaoJogador2.add(button);
			cartas.add(button, BorderLayout.SOUTH);
		}
		cartasMao.add(scroll);
		return cartasMao;
	}	
	/**
	 * Metodo utilizado para construir a interface do jogador1, e mostrar as cartas que o jogador1 
	 * escolheu para efetuar o ataque contra o jogador2
	 * @return JPanel - preenchido com todas as informacoes sobre as cartas de ataque do jogador1
	 * @author Richard Matias
	 */
	private JPanel telaCartasAtaque1() {
		JPanel panel = new JPanel(new BorderLayout()); JPanel cartas = new JPanel();
		for(int j = 0; j < 6; j++) {
			if(this.cartasAtaque1[j] != null) 
				cartas.add(this.cartasAtaque1[j], BorderLayout.SOUTH);
			else 
				this.cartasAtaque1[j] = button("preenche","","","");
		}
		panel.add( cartas, BorderLayout.CENTER );
		return panel;
	}
	/**
	 * Metodo utilizado para construir a interface do jogador2, e mostrar as cartas que o jogador2 
	 * escolheu para efetuar o ataque contra o jogador1
	 * @return JPanel - preenchido com todas as informacoes sobre as cartas de ataque do jogador2
	 * @author Richard Matias
	 */
	private JPanel telaCartasAtaque2() {
		JPanel panel = new JPanel(new BorderLayout()); JPanel cartas = new JPanel();
		for(int j = 0; j < 6; j++) {
			if(this.cartasAtaque2[j] != null) 
				cartas.add(this.cartasAtaque2[j], BorderLayout.SOUTH);
			else 
				this.cartasAtaque2[j] = button("preenche","","","");
		}
		panel.add( cartas, BorderLayout.CENTER );
		return panel;
	}
	/**
	 * Metodo utilizado para agrupar todas as informações de interface do jogador1 e montar sua interface grafica 
	 * corretamente, com cada Panel ocupando seu espaço na tela
	 * @return JPanel - preenchido com todas as informacoes sobre o jogador1
	 * @author Richard Matias
	 */
	private JPanel telaSuperior() {
		JPanel telaSuperior = new JPanel(new BorderLayout());
		telaSuperior.add(telaCartasMao(jogador1), BorderLayout.NORTH ); telaSuperior.add(telaCartasEvocadas(jogador1), BorderLayout.CENTER );	
		telaSuperior.add(telaCartasAtaque1(), BorderLayout.SOUTH);
		return telaSuperior;
	}
	/**
	 * Metodo utilizado para agrupar todas as informações de interface do jogador2 e montar sua interface grafica 
	 * corretamente, com cada Panel ocupando seu espaço na tela
	 * @return JPanel - preenchido com todas as informacoes sobre o jogador2
	 * @author Richard Matias
	 */
	private JPanel telaInferior() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(telaCartasMao(jogador2), BorderLayout.SOUTH );  panel.add(telaCartasEvocadas(jogador2), BorderLayout.CENTER);
		panel.add(telaCartasAtaque2(), BorderLayout.NORTH);
		return panel;
	}
	/**
	 * Metodo utilizado para construir a interface que contem a mana atual e a mana guardada 
	 * dos jogadores
	 * @return JPanel - preenchido com todas as informacoes sobre a mana dos jogadores
	 * @param jogador - jogador fornecido pelo metodo telaLateralDireita()
	 * @author Richard Matias
	 */
	private JPanel panelMana(Jogador jogador) {
		JPanel panel = new JPanel(new BorderLayout()); JPanel jogadorAzu = new JPanel();
		
		JLabel mana1 = new JLabel(""+jogador.getMana());
		mana1.setIcon(new ImageIcon( "imagens\\mana.png" ));
		mana1.setVerticalTextPosition(SwingConstants.CENTER );
		mana1.setHorizontalTextPosition(SwingConstants.CENTER  );
		jogadorAzu.add(mana1, BorderLayout.EAST);
		
		JLabel manaG1 = new JLabel(""+jogador.getManaGuardada());
		manaG1.setIcon(new ImageIcon( "imagens\\mana.png" ));
		manaG1.setVerticalTextPosition(SwingConstants.CENTER );
		manaG1.setHorizontalTextPosition(SwingConstants.CENTER  );
		jogadorAzu.add(manaG1, BorderLayout.WEST);
		
		panel.add(jogadorAzu);
		return panel;
	}
	/**
	 * Metodo utilizado para construir toda a interface da lateral direita dos jogadores
	 * contendo as informações como a mana e também os botoes que o usuario vai poder interagir
	 * durante as rodadas do jogo
	 * @return JPanel - preenchido com todas as informacoes sobre a mana e interacao dos jogadores
	 * @author Richard Matias
	 */
	private JPanel telaLateralDireita() {
		JPanel telaLateral = new JPanel(new BorderLayout()); JPanel panel = new JPanel(new GridBagLayout()); 
		
		ataque  = button("", "Atacar", "","Atacar"); 	  ataque.setForeground(Color.black);
		defende = button("", "Defender", "", "Defender"); defende.setForeground(Color.black);
		pronto  = button("", "Pronto", "", "Pronto"); 	  pronto.setForeground(Color.black);
		trocar  = button("", "Trocar","","Trocar"); 	  trocar.setForeground(Color.black);
		passe   = button("", "Passar","","Passar"); 	  passe.setForeground(Color.black);
		
		panel.add(defende, new GridBagConstraints(1, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(5,5,5,5), 0, 0));
		panel.add(ataque, new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(5,5,5,5), 0, 0));
		panel.add(pronto, new GridBagConstraints(0, 1, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(5,5,5,5), 0, 0));
		panel.add(trocar, new GridBagConstraints(1, 1, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(5,5,5,5), 0, 0));
		panel.add(passe, new GridBagConstraints(0, 2, 2, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(5,5,5,5), 0, 0));		
		
		telaLateral.add(panelMana(jogador1), BorderLayout.NORTH);
		telaLateral.add(	panel, BorderLayout.CENTER);
		telaLateral.add(panelMana(jogador2), BorderLayout.SOUTH);
		return telaLateral;
	}
	/**
	 * Metodo utilizado para construir toda a interface da lateral esquerda dos jogadores
	 * contendo as informações como a vida atual do nexus e também uma imagem para ilustrar o deck
	 * @return JPanel - preenchido com todas as informacoes sobre a vida do nexus dos jogadores
	 * @param jogador - jogador fornecido pelo metodo telaLateralEsquerda()
	 * @author Richard Matias
	 */
	private JPanel panelDeckNexus(Jogador jogador) {
		JPanel panel = new JPanel(new BorderLayout()); 
		
		JLabel deck = new JLabel();  
		deck.setIcon(new ImageIcon( "imagens\\deck.png" )); 
				
		JLabel nexus = new JLabel(""+jogador.getVidaNexus());
		String iconNexus = jogador.equals(jogador1)?  "imagens\\LoR_Red_Nexus.png"  : "imagens\\LoR_Blue_Nexus.png";
		nexus.setIcon(new ImageIcon( iconNexus ));
		nexus.setBorder(BorderFactory.createEmptyBorder());
		nexus.setVerticalTextPosition(SwingConstants.BOTTOM );
		nexus.setHorizontalTextPosition(SwingConstants.CENTER  );
		if(jogador.equals(jogador1)) {
			panel.add(deck, BorderLayout.NORTH);
			panel.add(nexus, BorderLayout.SOUTH); 
		}else {
			panel.add(deck, BorderLayout.SOUTH);
			panel.add(nexus, BorderLayout.NORTH); 	
		}
		return panel;
	}
	/**
	 * Metodo utilizado para agrupar todas as informações de interface dos jogadores e montar a interface grafica 
	 * corretamente, com cada Panel ocupando seu espaço na tela
	 * @return JPanel - preenchido com todas as informacoes sobre o jogadores
	 * @author Richard Matias
	 */
	private JPanel panelLateralEsquerda() {	
		JPanel panel = new JPanel(new BorderLayout()); 
		panel.add(panelDeckNexus(jogador1), BorderLayout.NORTH);
		panel.add(panelDeckNexus(jogador2), BorderLayout.SOUTH);
		return panel;
	}
	/**
	 * Metodo utilizado para agrupar todas as informações dos jogadores e montar a interface grafica 
	 * principal que é o container com cada item de cada jogador ocupando a posicao correta
	 * metodo utilizado para iniciar a interface grafica
	 * também é utilizado na classe Game.java no metodo start()
	 * @author Richard Matias
	 */
	public void criaTela()  {
		panel = new JPanel(new BorderLayout()); telaLateral = new JPanel(new BorderLayout());
		telaLateralEsquerda = new JPanel(new BorderLayout());
		container = new JFrame(); 
		
		panel.add(telaSuperior(), BorderLayout.NORTH); 
		telaLateralEsquerda.add(panelLateralEsquerda(), BorderLayout.WEST); 
		telaLateral.add(telaLateralDireita(), BorderLayout.EAST); 
		panel.add(telaInferior(), BorderLayout.SOUTH); 
		
		container.setSize(1200, 960);
		container.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		container.add(panel, BorderLayout.CENTER);
		container.add(telaLateral, BorderLayout.EAST);
		container.add(telaLateralEsquerda, BorderLayout.WEST);
		container.setVisible(true);
		container.setLocationRelativeTo(null);
	}
	/**
	 * Metodo utilizado para atualizar as mudanças feitas na tela devido a interação dos jogadores
	 * com a interface durante o jogo
	 * @author Richard Matias
	 */
	private void attTela() {
		panel.add(telaSuperior(), BorderLayout.NORTH);
		telaLateralEsquerda.add(panelLateralEsquerda(), BorderLayout.WEST);
		telaLateral.add(telaLateralDireita(), BorderLayout.EAST);
		panel.add(telaInferior(), BorderLayout.SOUTH);
		
		panel.validate();  telaLateral.validate(); telaLateralEsquerda.validate();
		panel.repaint();   telaLateral.repaint();  telaLateralEsquerda.repaint();
		
		container.validate(); container.repaint();
	}
	/**
	 * Metodo utilizado para obter a posicao que o jogador deseja que a carta ocupe na 
	 * hora de se defener de um ataque
	 * sempre verifica se o jogador passou uma posicao valida de 1 a 6 e se o valor
	 * é um numero
	 * também pode ser usado por cartas que contem o Traco.java de DESAFIAR
	 * @return int - prenchido com a posicao que o usuario escolheu
	 * @author Richard Matias
	 */
	private int getPos() {
		while(true) {
			String result = JOptionPane.showInputDialog("Qual posição deseja que a carta fique de 1 a 6?");
			if(result == null || result.isEmpty()) return -1;
			try {
				pos = Integer.parseInt( result );
				while(pos <= 0 || pos >= 7) {
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
	 * Metodo utilizado para obter o index de uma carta que o jogador escolheu para suas cartas evocadas
	 * metedo utilizado em Tela.java no metodo setCartaTelaEvocada()
	 * @return int - prenchido com o index da carta, se a carta nao for encontrada retorna -1 
	 * @param name - fornecido pelo metodo setCartaTelaEvocada(), contem o nome da carta
	 * @param jogador - fornecido pelo metodo setCartaTelaEvocada()
	 * @author Richard Matias
	 */
	private int index(String name, Jogador jogador) {
		for(int i = 0; i < jogador.getCartasMao().size(); i++) {
			if(jogador.getCartasMao().get(i).getNome().equals(name)) return i;
		}
		return -1;
	}
	/**
	 * Metodo utilizado para setar uma carta as cartas evocada quando o jogador escolher na 
	 * tela.
	 * verifica se o jogador tem mana suficiente para comprar aquela carta, caso nao tenha
	 * um jpanel com um aviso de mana insuficiente sera disparado, caso as cartas evocadad do jogador
	 * ja esteja cheia, com 6 cartas um jpanel com um aviso sera disparado informando que o mesmo nao
	 * pode comprar mais cartas até que uma posicao fique vaga
	 * metodo utilizado em Tela.java no metodo actionPerformed()
	 * @param event - fornecido pelo metodo actionPerformed() e contem as informacoes de qual objeto disparou o evento
	 * @param jogador - fornecido pelo metodo actionPerformed()
	 * @author Richard Matias
	 */
	private void setCartaTelaEvocada(ActionEvent event, Jogador jogador) {
		int index = jogador.equals(jogador1) ? index(cartasMaoJogador1.get(cartasMaoJogador1.indexOf(event.getSource())).getName(), jogador1 )
											 : index(cartasMaoJogador2.get(cartasMaoJogador2.indexOf(event.getSource())).getName(), jogador2 );
		if(jogador.equals(jogador1)) {
			if( !jogador1.getDeckIsFull(jogador1) && jogador.getCartasMao().get(index).getCustoMana() <= jogador1.getMana()) {
				jogador1.setMana(jogador.getMana() - jogador.getCartasMao().get(index).getCustoMana());
				if(jogador.equals(jogador1)) this.setCartasEvocadas(jogador1, index);
				if(jogador.equals(jogador1)) this.jogador1.removerCartaMao(jogador1.getCartasMao().get(index));
				if(jogador.equals(jogador1)) this.cartasMaoJogador1.clear();
			}else {
				if(jogador1.getDeckIsFull(jogador1))
					JOptionPane.showMessageDialog(null, "Cartas evocadas ja contem 6 (Cheio)!", "Aviso", JOptionPane.WARNING_MESSAGE);
				else
					JOptionPane.showMessageDialog(null, "Mana insuficiente!", "Aviso", JOptionPane.WARNING_MESSAGE);
			}
		}else {
			if( !jogador2.getDeckIsFull(jogador2) && jogador.getCartasMao().get(index).getCustoMana() <= jogador2.getMana()) {
				jogador2.setMana(jogador.getMana() - jogador.getCartasMao().get(index).getCustoMana());
				if(jogador.equals(jogador2)) this.setCartasEvocadas(jogador2, index);
				if(jogador.equals(jogador2)) this.jogador2.removerCartaMao(jogador2.getCartasMao().get(index));
				if(jogador.equals(jogador2)) this.cartasMaoJogador2.clear();	
			}else {
				if(jogador2.getDeckIsFull(jogador2))
					JOptionPane.showMessageDialog(null, "Cartas evocadas ja contem 6 (Cheio)!", "Aviso", JOptionPane.WARNING_MESSAGE);
				else
					JOptionPane.showMessageDialog(null, "Mana insuficiente!", "Aviso", JOptionPane.WARNING_MESSAGE);
			}
		}
		panel.removeAll(); telaLateral.removeAll();
		this.attTela();
	}
	/**
	 * Metodo utilizado para setar uma carta as cartas de ataque quando o jogador escolher na 
	 * tela.
	 * caso a carta carta tenha o Traco.java DESAFIADOR ou ANOITECER da o direito do jogador escolhr a posicao 
	 * que ele quer colocar a carta
	 * metodo utilizado em Tela.java no metodo actionPerformed()
	 * @param event - fornecido pelo metodo actionPerformed() e contem as informacoes de qual objeto disparou o evento
	 * @param jogador - fornecido pelo metodo actionPerformed()
	 * @author Richard Matias
	 */
	private void setCartaTelaAtaque(ActionEvent event, Jogador jogador) {
		int index = jogador.equals(jogador1) ? Arrays.asList(this.cartasJogador1).indexOf(event.getSource()) 
											 : Arrays.asList(this.cartasJogador2).indexOf(event.getSource());
		if(jogador.getEvocadas()[index] instanceof Campeao) {
			Campeao campeao = (Campeao) jogador.getEvocadas()[index];
			if(campeao.getTraco().equals(Tracos.DESAFIADOR) 
			|| (campeao.getEfeitos().equals(Efeitos.ANOITECER) && !campeao.getNome().equals("Nocturne"))) {
				setCartaTelaDefesa(event, jogador);
				return;
			}
		}
		if(jogador.equals(jogador1)) this.setCartasAtaque(jogador1, index); 
		if(jogador.equals(jogador2)) this.setCartasAtaque(jogador2, index);
		panel.removeAll();
		this.attTela();
	}
	/**
	 * Metodo utilizado para setar uma carta as cartas de ataque quando o jogador escolher se defender
	 * como o jogador esta se defendendo, ele pode escolher contra quais cartas ele quer lutar 
	 * metodo utilizado em Tela.java no metodo actionPerformed()
	 * @param event - fornecido pelo metodo actionPerformed() e contem as informacoes de qual objeto disparou o evento
	 * @param jogador - fornecido pelo metodo actionPerformed()
	 * @author Richard Matias
	 */
	private void setCartaTelaDefesa(ActionEvent event, Jogador jogador) {
		int index = jogador.equals(jogador1) ? Arrays.asList(this.cartasJogador1).indexOf(event.getSource()) 
										     : Arrays.asList(this.cartasJogador2).indexOf(event.getSource()); 
		int posicao = getPos();
		if(posicao != -1) {
			panel.removeAll();
			if(jogador.equals(jogador1)) this.setCartasAtaque(jogador1,posicao, index);
			if(jogador.equals(jogador2)) this.setCartasAtaque(jogador2,posicao, index);
			this.attTela();
		}
		
	}
	/**
	 * Metodo utilizado para capturar o evento disparado pelos componentes da interface grafica
	 * e com essa informação saber qual interação sera posivel fazer no jogo
	 * @param event - fornecido pelo metodo actionPerformed() e contem as informacoes de qual objeto disparou o evento
	 * @author Richard Matias
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getActionCommand().equals("Passar")) {
			if(this.jogadorAtual.equals(jogador1)) setPassarJg1(true);
			if(this.jogadorAtual.equals(jogador2)) setPassarJg2(true);
		}
		if(event.getActionCommand().equals("Pronto")) {
			if(this.jogadorAtual.equals(jogador1)) setAtacarJg1(false);
			if(this.jogadorAtual.equals(jogador2)) setAtacarJg2(false);
		}
		if(event.getActionCommand().equals("Atacar")){
			if(this.jogadorAtual.equals(jogador1)) setAtacarJg1(true);
			if(this.jogadorAtual.equals(jogador2)) setAtacarJg2(true);
		}
		if(event.getActionCommand().equals("Defender")){
			if(this.jogadorAtual.equals(jogador1)) setDefenderJg1(true);
			if(this.jogadorAtual.equals(jogador2)) setDefenderJg2(true);
		}
		if(event.getActionCommand().equals("Cartas Jogador1") && (this.jogadorAtual.equals(jogador1))) {
			if(cartasMaoJogador1.contains( event.getSource()) ) setCartaTelaEvocada(event, jogador1);
		}
		if(event.getActionCommand().equals("Cartas Jogador2") && (this.jogadorAtual.equals(jogador2)) ) {
			if(cartasMaoJogador2.contains( event.getSource()) ) setCartaTelaEvocada(event, jogador2);
		}
		if(event.getActionCommand().equals("Jogador1") && (this.jogadorAtual.equals(jogador1)) && (getAtacarJg1()) 
		  || event.getActionCommand().equals("Jogador1") && (this.jogadorAtual.equals(jogador1)) && ( getDefenderJg1() ) ) {
			if(getDefenderJg1())
				setCartaTelaDefesa(event, jogador1);
			else
				setCartaTelaAtaque(event, jogador1);
		}
		if(event.getActionCommand().equals("Jogador2") && (this.jogadorAtual.equals(jogador2)) && (getAtacarJg2() ) 
		   || event.getActionCommand().equals("Jogador2") && (this.jogadorAtual.equals(jogador2)) && (getDefenderJg2()) ) {
			if(getDefenderJg2())
				setCartaTelaDefesa(event, jogador2);
			else
				setCartaTelaAtaque(event, jogador2);
		}
	}
	/**
	 * Metodo utilizado para setar qual o jogador estara controlando a 
	 * rodada atual, caso seja o jogador 1, ele podera atacar ou passar, e 
	 * assim o jogador 2 podera se defender ou passar. Caso seja o jogador 2
	 * os papeis se invertem.
	 * metodo utilizado pelo metodo start() na classe Game.java
	 * @param jogador - fornecido pelo metodo start() na classe Game.java
	 * @author Richard Matias
	 */
	public void setJogadorAtual(Jogador jogador) {
		this.jogadorAtual = jogador;
	}
	/**
	 * Metodo utilizado para obter qual o jogador estara controlando a 
	 * rodada atual, caso seja o jogador 1, ele podera atacar ou passar, e 
	 * assim o jogador 2 podera se defender ou passar. Caso seja o jogador 2
	 * os papeis se invertem.
	 * metodo utilizado pelo metodo start() na classe Game.java
	 * @return jogador - preenchido com as informacoes do jogadorAtual que controla a rodada
	 * @author Richard Matias
	 */
	public Jogador getJogadorAtual() {
		return this.jogadorAtual;
	}
	/**
	 * Metodo utilizado para obter se o jogador 1 resolveu se atacar o jogador 2
	 * caso tenha decidido que sim, o jogador 1 podera escolher entre suas cartas
	 * para atacar o jogador 2
	 * metodo utilizado pelo metodo start() na classe Game.java
	 * @return boolean - contendo a informação de atacar do jogador 1
	 * @author Richard Matias
	 */
	public boolean getAtacarJg1() {
		return this.atacarJg1;
	}
	/**
	 * Metodo utilizado para obter se o jogador 2 resolveu se atacar o jogador 1
	 * caso tenha decidido que sim, o jogador 2 podera escolher entre suas cartas
	 * para atacar o jogador 1
	 * metodo utilizado pelo metodo start() na classe Game.java
	 *  @return boolean - contendo a informação de atacar do jogador 2
	 * @author Richard Matias
	 */
	public boolean getAtacarJg2() {
		return this.atacarJg2;
	}
	/**
	 * Metodo utilizado para setar quando o jogador 1 resolver atacar o
	 * jogador 2
	 * @param atacar - fornecido pelo metodo start() na classe Game.java
	 * @author Richard Matias
	 */
	public void setAtacarJg1(boolean atacar) {
		this.atacarJg1 = atacar;
	}
	/**
	 * Metodo utilizado para setar quando o jogador 2 resolver atacar o
	 * jogador 1
	 * @param atacar - fornecido pelo metodo start() na classe Game.java
	 * @author Richard Matias
	 */
	public void setAtacarJg2(boolean atacar) {
		this.atacarJg2 = atacar;
	}
	/**
	 * Metodo utilizado para obter se o jogador 1 resolveu se defender
	 * caso tenha decidido que sim, o jogador 1 podera escolher entre suas cartas
	 * para se defender
	 * metodo utilizado pelo metodo start() na classe Game.java
	 * @return boolean - contendo a informação de defender do jogado 1
	 * @author Richard Matias
	 */
	public boolean getDefenderJg1() {
		return this.defenderJg1;
	}
	/**
	 * Metodo utilizado para obter se o jogador 2 resolveu se defender
	 * caso tenha decidido que sim, o jogador 2 podera escolher entre suas cartas
	 * para se defender
	 * metodo utilizado pelo metodo start() na classe Game.java
	 * @return boolean - contendo a informação de defender do jogador 2
	 * @author Richard Matias
	 */
	public boolean getDefenderJg2() {
		return this.defenderJg2;
	}
	/**
	 * Metodo utilizado para setar quando o jogador 1 resolver se defender e
	 * apos o jogador 2 resolver atacar 
	 * @param defender - fornecido pelo metodo start() na classe Game.java
	 * @author Richard Matias
	 */
	public void setDefenderJg1(boolean defender) {
		this.defenderJg1 = defender;
	}
	/**
	 * Metodo utilizado para setar quando o jogador 2 resolver se defender e
	 * apos o jogador 1 resolver atacar 
	 * @param defender - fornecido pelo metodo start() na classe Game.java
	 * @author Richard Matias
	 */
	public void setDefenderJg2(boolean defender) {
		this.defenderJg2 = defender;
	}
	/**
	 * Metodo utilizado para obter se o jogador 1 resolver passar sua
	 * vez na rodada e não se defender do ataque do jogador 2
	 * metodo utilizado pelo metodo start() na classe Game.java
	 * @return boolean - contendo a informação de passar do jogador 1
	 * @author Richard Matias
	 */
	public boolean getPassarJg1() {
		return this.passarJg1;
	}
	/**
	 * Metodo utilizado para setar quando o jogador 1 resolver passar e
	 * apos a rodada ser completada o valor de passar volta a ser falso 
	 * novamente
	 * @param passar - fornecido pelo metodo start() na classe Game.java
	 * @author Richard Matias
	 */
	public void setPassarJg1(boolean passar) {
		this.passarJg1 = passar;
	}
	/**
	 * Metodo utilizado para obter se o jogador 2 resolver passar sua
	 * vez na rodada e não se defender do ataque do jogador 1
	 * metodo utilizado pelo metodo start() na classe Game.java
	 * @return boolean - contendo a informação de passar do jogador 2
	 * @author Richard Matias
	 */
	public boolean getPassarJg2() {
		return this.passarJg2;
	}
	/**
	 * Metodo utilizado para setar quando o jogador 2 resolver passar e
	 * apos a rodada ser completada o valor de passar volta a ser falso 
	 * novamente
	 * @param passar - fornecido pelo metodo start() na classe Game.java
	 * @author Richard Matias
	 */
	public void setPassarJg2(boolean passar) {
		this.passarJg2 = passar;
	}
	/**
	 * Metodo utilizado para saber quando 1 turno foi completado, ou seja,
	 * quando o jogador 1 atacou o jogador 2 e vice versa
	 * @param turno - fornecido pelo metodo start() na classe Game.java
	 * @author Richard Matias
	 */
	public void setTurno(boolean turno) {
		this.turno = turno;
	} 
	/**
	 * Metodo utilizado para remover as cartas da setCartaTelaAtaque()
	 * para dar uma interacao de que a carta que estava atacando agora 
	 * voltou para as evocadas do jogador
	 * metodo tambem utilizado na classe Tela.java no metodo atacar()
	 * @param jogador - fornecido pelo metodo atacar() na classe Tela.java
	 * @author Richard Matias
	 */
	private void removeCartaAtaque(Jogador jogador) {
		for(int i = 0; i < jogador.getCartasAtaque().length; i++) {
			//if(jogador.getCartasAtaque()[i] != null) {
				jogador.getCartasAtaque()[i] = null;
				if(jogador.equals(jogador1)) this.cartasAtaque1[i] = null;
				if(jogador.equals(jogador2)) this.cartasAtaque2[i] = null;
			//}
		}
	}
	/**
	 * Metodo utilizado para quando uma rodada for completada
	 * as cartas selecionadas por cada jogador se atacarem
	 * metodo tambem utilizado na classe Game.java no metodo start()
	 * @author Richard Matias
	 */
	public void atacar() {
		//codar quando o jogador ataca e o outro jogador escolhe se defender.
		if(this.jogadorAtual.equals(jogador1) && getAtacarJg1() && getDefenderJg2()) {
			this.jogador1.atacar(jogador2);
			this.removeCartaAtaque(jogador1);
			this.removeCartaAtaque(jogador2);
			jogador1.rodada(jogador1);
		}
		if(this.jogadorAtual.equals(jogador2) && getAtacarJg2() && getDefenderJg1()) {
			this.jogador2.atacar(jogador1);
			this.removeCartaAtaque(jogador1);
			this.removeCartaAtaque(jogador2);
			jogador2.rodada(jogador2);
		}
		//codar quando o jogador x atacar o jogador y, porem o jogador y passa e nao se defender (V)
		if(this.jogadorAtual.equals(jogador1) && getAtacarJg1() &&  !getDefenderJg2() && getPassarJg2()) {
			this.jogador1.atacar(jogador2);
			this.removeCartaAtaque(jogador1);
			jogador1.rodada(jogador1);
		}
		if(this.jogadorAtual.equals(jogador2) && getAtacarJg2() && !getDefenderJg1() && getPassarJg1()) {
			this.jogador2.atacar(jogador1);
			this.removeCartaAtaque(jogador2);
			jogador2.rodada(jogador2);
		}
		if(this.jogadorAtual.equals(jogador1) && getPassarJg1()) {
			jogador1.rodada(jogador1);
		}
		if(this.jogadorAtual.equals(jogador2) && getPassarJg2()) {
			jogador2.rodada(jogador2);
		}
		if(turno) {mudaAcaoCartas(jogador1); mudaAcaoCartas(jogador2); }
			
		panel.removeAll(); telaLateral.removeAll(); telaLateralEsquerda.removeAll();
		this.attTela();
	}
	/**
	 * Metodo utilizado para mudar a acao das cartas que contem o Traco.java 
	 * de REGENERACAO e BARREIRA, assim garantindo que esses tracos nao sejam ativados
	 * o tempo todo
	 * metodo tambem utilizado na classe Tela.java no metodo atacar()
	 * @param jogador - fornecido pelo metodo atacar() na classe Tela.java
	 * @author Richard Matias
	 */
	public void mudaAcaoCartas(Jogador jogador) {
		jogador.mudaAcaoCartas();
	}
}