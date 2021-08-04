package com.unicamp.mc322.game;

import javax.swing.JOptionPane;

import com.unicamp.mc322.jogador.Jogador;
import com.unicamp.mc322.telas.Tela;
/**
 * Classe Game, que ira ser responsavel rodar todo o sistema do jogo
 * @author Richard Matias
 */
public class Game {
	public void start() {
		Jogador jogador1 = new Jogador("Jogador 1");
		Jogador jogador2 = new Jogador("Jogador 2");	
		Tela tela = new Tela(jogador1, jogador2);
		tela.criaTela();
		//enquanto a vida de um dos dois jogadores for > 0, o sitema de rodada continuara
		while((jogador1.getVidaNexus() > 0) && (jogador2.getVidaNexus() > 0) ) {
			System.out.print("");
			//caso jogador1 nao queira atacar, e resolver passar a vez
			if(tela.getJogadorAtual().equals(jogador1) && tela.getPassarJg1() && !(tela.getAtacarJg2())) {
				tela.atacar();
				tela.setJogadorAtual(jogador2); 
				tela.setPassarJg1(false);
				JOptionPane.showMessageDialog (null,"Jogador 2\nEscolha entre atacar ou passar", "Jogador 1 - Escolheu passar", JOptionPane.INFORMATION_MESSAGE);
			}
			//caso jogador2 nao queira atacar, e resolver passar a vez
			if(tela.getJogadorAtual().equals(jogador2) && tela.getPassarJg2() && !(tela.getAtacarJg1())) {
				tela.atacar();
				tela.setJogadorAtual(jogador1); 
				tela.setPassarJg2(false);
				JOptionPane.showMessageDialog (null,"Jogador 1\nEscolha entre atacar ou passar", "Jogador 2 - Escolheu passar", JOptionPane.INFORMATION_MESSAGE);
			}
			//caso jogador1 decida atacar o jogador 2
			if(tela.getJogadorAtual().equals(jogador1)) {
				if(tela.getAtacarJg1()) {
					JOptionPane.showMessageDialog (null,"Jogador 1\nEscolha entre suas cartas", "Escolha", JOptionPane.INFORMATION_MESSAGE);
					//while com sleep para gerar um loop enquanto o jogador escolhe sua carta
					while(tela.getAtacarJg1()) {
						try {
							Thread.sleep(500);
						} catch (InterruptedException  e) {}
					}
					tela.setJogadorAtual(jogador2);
					tela.setAtacarJg1(true);
					JOptionPane.showMessageDialog (null,"Jogador 2\nPode escolher entre defender ou passar", "Escolha", JOptionPane.INFORMATION_MESSAGE);
				}
			} 
			//caso jogador2 decida se defender do ataque do jogador1
			if(tela.getJogadorAtual().equals(jogador2) && tela.getAtacarJg1() && tela.getDefenderJg2()) {
				if(tela.getDefenderJg2()) {
					tela.setAtacarJg2(true);
					JOptionPane.showMessageDialog (null,"Jogador 2\nEscolha entre suas cartas", "Escolha", JOptionPane.INFORMATION_MESSAGE);
					//while com sleep para gerar um loop enquanto o jogador escolhe sua carta
					while(tela.getAtacarJg2()) {
						try {
							Thread.sleep(500);
						} catch (InterruptedException  e) {}
					}
					tela.setAtacarJg1(true); tela.setJogadorAtual(jogador1);
					tela.atacar(); 
					tela.setDefenderJg2(false);
					tela.setAtacarJg1(false); tela.setJogadorAtual(jogador2);
					JOptionPane.showMessageDialog (null,"Jogador 2\nPode escolher entre atacar ou passar", "Escolha", JOptionPane.INFORMATION_MESSAGE);
				}
			}
			//caso jogador1 decida atacar o jogador 2 e o jogador2 decida nao se defender
			if(tela.getJogadorAtual().equals(jogador2) && tela.getAtacarJg1() && tela.getPassarJg2()) {
				JOptionPane.showMessageDialog (null,"Jogador 2\nEscolheu passar", "", JOptionPane.INFORMATION_MESSAGE);
				tela.setJogadorAtual(jogador1);
				tela.atacar();
				tela.setAtacarJg1(false); tela.setPassarJg2(false);
				JOptionPane.showMessageDialog (null,"Jogador 1\nPode escolher entre atacar ou passar ", "Escolha", JOptionPane.INFORMATION_MESSAGE);
			}
			//caso jogador2 decida atacar o jogador 1
			if(tela.getJogadorAtual().equals(jogador2) && tela.getAtacarJg2()) {
				if(tela.getAtacarJg2()) {
					JOptionPane.showMessageDialog (null,"Jogador 2\nEscolha entre suas cartas", "Escolha", JOptionPane.INFORMATION_MESSAGE);
					//while com sleep para gerar um loop enquanto o jogador escolhe sua carta
					while(tela.getAtacarJg2()) {
						try {
							Thread.sleep(500);
						} catch (InterruptedException  e) {}
					}
					tela.setJogadorAtual(jogador1); tela.setAtacarJg2(true);
					JOptionPane.showMessageDialog (null,"Jogador 1\nPode escolher entre defender ou passar", "Escolha", JOptionPane.INFORMATION_MESSAGE);
					System.out.println(tela.getPassarJg1());
				}
			} 
			//caso jogador1 decida se defender do ataque do jogador2
			if(tela.getJogadorAtual().equals(jogador1) && tela.getAtacarJg2() && tela.getDefenderJg1()) {
				if(tela.getDefenderJg1()) {
					tela.setAtacarJg1(true);
					JOptionPane.showMessageDialog (null,"Jogador 1\nEscolha entre suas cartas", "Escolha", JOptionPane.INFORMATION_MESSAGE);
					//while com sleep para gerar um loop enquanto o jogador escolhe sua carta
					while(tela.getAtacarJg1()) {
						try {
							Thread.sleep(500);
						} catch (InterruptedException  e) {}
					}
					tela.setJogadorAtual(jogador2);
					tela.setTurno(true);
					tela.atacar();
					tela.setJogadorAtual(jogador1); 
					tela.setAtacarJg2(false); tela.setDefenderJg1(false);
					JOptionPane.showMessageDialog (null,"Jogador 1\nPode escolher entre atacar ou passar", "Escolha", JOptionPane.INFORMATION_MESSAGE);
				}
			}
			//caso jogador2 decida atacar o jogador1 e o jogador1 decida nao se defender
			if(tela.getJogadorAtual().equals(jogador1) && tela.getAtacarJg2() && tela.getPassarJg1()) {
				JOptionPane.showMessageDialog (null,"Jogador 1\nEscolheu passar", "", JOptionPane.INFORMATION_MESSAGE);
				tela.setJogadorAtual(jogador2);
				tela.atacar();
				tela.setJogadorAtual(jogador1);
				tela.setAtacarJg2(false); tela.setPassarJg1(false);
				JOptionPane.showMessageDialog (null,"Jogador 2\nPode escolher entre atacar ou passar ", "Escolha", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
}
