package com.unicamp.mc322.cartas;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import com.unicamp.mc322.jogador.Jogador;
/**
 * Enum que ficara responsavel por implementar e aplicar todos os efeitos e as
 * epecificidades de cada carta que os jogadore tiverem em seu deck 
 * Conceito do Strategy pattern foi utilizado para implementar esta classe e suas
 * funcionalidades
 * 
 */
public enum Efeitos implements EfeitoAcao{
	/**
	 * este metodo é somente para preencher os campos efeito das cartas que não tem nenhum tipo de efeito
	 * e com isso nao gerar erro na criacao das cartas 
	 * @author Richard Matias
	 */
	NULO{
		@Override
		public void acao(Cartas carta, Jogador thisJogador, Jogador jogador) {				
		}
	},
	/**
	 * Metodo utilizado para ativar os efeitos da carta Gang Plank que ao ser conjurado
	 * ira invocar uma carta seguidor chamada Barril de Polvora e ira adicionar esta carta
	 * as evocada de thisJogador
	 * @author Richard Matias
	 */
	BARRIL_POLVORA{
		@Override
		public void acao(Cartas carta, Jogador thisJogador, Jogador jogador) {
			String[] itens = InvocarCarta.invocarCarta("Barril de Polvora");
			for(int i = 0; i < thisJogador.getEvocadas().length; i++) {
				if(Objects.isNull(thisJogador.getEvocadas()[i])) {
					thisJogador.getEvocadas()[i] = CartasFactory.criarCarta(TipoCarta.valueOf(itens[0].split(":")[1]), itens);
					break;
				}
			}
		} 
	},
	/**
	 * Metodo utilizado para ativar os efeitos da carta Swain que ao ser conjurado
	 * ira causar 3 de dano ao nexus inimigo, ou seja, ao nexus de jogador.
	 * @author Richard Matias
	 */
	DANO_3_NEXUS{
		@Override
		public void acao(Cartas carta, Jogador thisJogador, Jogador jogador) {
			jogador.setVidaNexus(jogador.getVidaNexus() - 3);
		}
	},
	/**
	 * Metodo utilizado para ativar os efeitos da carta Miss Fortune que ao ser conjurada
	 * ira incrementar 1 de dano a todos os aliados evocados de thisJogador
	 * @author Richard Matias
	 */
	MAIS_1_DANO_ALIADOS{
		@Override
		public void acao(Cartas carta, Jogador thisJogador, Jogador jogador) {
			for(int i = 0; i < thisJogador.getEvocadas().length; i++) {
				if(Objects.nonNull(thisJogador.getEvocadas()[i]))
					thisJogador.getEvocadas()[i].setPoder(thisJogador.getEvocadas()[i].getPoder() + 1);
			}
		}
	},
	/**
	 * Metodo utilizado para ativar os efeitos da carta Anivia que ao ser conjurada
	 * ira causar 1 de dano a todas as cartas evocadas do inimigo, ou seja, jogador.
	 * E logo apos tudo isso também ira causar 1 de dano ao nexus do inimigo.
	 * @author Richard Matias
	 */
	CAUSA_1_DANO_INIMIGOS_NEXUS{
		@Override
		public void acao(Cartas carta, Jogador thisJogador, Jogador jogador) {
			Campeao campeao;
			try {
				campeao = (Campeao) carta;
				for(int i = 0; i < jogador.getEvocadas().length; i++) {
					if(Objects.nonNull(jogador.getEvocadas()[i])) {
						jogador.getEvocadas()[i].setVida(jogador.getEvocadas()[i].getVida() - 1 );
					}
				}
				jogador.setVidaNexus(jogador.getVidaNexus() - 1);
			} catch (ClassCastException e) {
				throw new ClassCastException("Carta não pertence a classe campeao");
			}
		}
	},
	/**
	 * Metodo utilizado para ativar os efeitos da carta Braum que ao sobreviver
	 * a dano pela primeira vez ira invocar uma carta seguidor chamada Poro Poderoso
	 * e ira adicionar esta carta as evocada de thisJogador
	 * @author Richard Matias
	 */
	SOBREVIVER_P_PORO{
		@Override
		public void acao(Cartas carta, Jogador thisJogador, Jogador jogador) {
			Campeao campeao;
			String[] itens = InvocarCarta.invocarCarta("Poro Poderoso");
			for(int i = 0; i < thisJogador.getEvocadas().length; i++) {
				if(Objects.isNull(thisJogador.getEvocadas()[i])) {
					try {
						campeao = (Campeao) thisJogador.getEvocadas()[i];
						if(campeao.getSobreviberDanopx())
							thisJogador.getEvocadas()[i] = CartasFactory.criarCarta(TipoCarta.valueOf(itens[0].split(":")[1]), itens);
					} catch (Exception e) {
					}
					break;
				}
			}
		}
	},
	/**
	 * Metodo utilizado para ativar os efeitos da carta Maokai que ao ser conjurada
	 * ira invocar uma carta seguidor chamada Muda e ira adicionar esta carta
	 * as evocada de thisJogador
	 * @author Richard Matias
	 */
	EVOCA_1_MUDA{
		@Override
		public void acao(Cartas carta, Jogador thisJogador, Jogador jogador) {
			String[] itens = InvocarCarta.invocarCarta("Muda");
			for(int i = 0; i < thisJogador.getEvocadas().length; i++) {
				if(Objects.isNull(thisJogador.getEvocadas()[i])) {
					thisJogador.getEvocadas()[i] = CartasFactory.criarCarta(TipoCarta.valueOf(itens[0].split(":")[1]), itens);
					break;
				}
			}
		}
	},
	/**
	 * Metodo utilizado para ativar os efeitos da carta Karma que ao ser conjurada
	 * ira invocar uma carta feitico chamada Perspicacia das Eras e ira automaticamente
	 * aplicar os efeitos do feitico que é invocar um novo feitico, porem, diferente do atual
	 * e aplicar os efeitos automaticamente 
	 * @author Richard Matias
	 */
	CRIAR_FEITICO{
		@Override
		public void acao(Cartas carta, Jogador thisJogador, Jogador jogador) {
			Feiticos feiticos; Cartas novaCarta; ImageIcon icon;
			String[] itens = InvocarCarta.invocarCarta("Perspicacia das Eras");
			for(int i = 0; i < thisJogador.getEvocadas().length; i++) {
				if(Objects.isNull(thisJogador.getEvocadas()[i])) {
					Cartas cartas = CartasFactory.criarCarta(TipoCarta.valueOf(itens[0].split(":")[1]), itens);
					icon = new ImageIcon("imagens\\"+cartas.getNome().toUpperCase()+"-m.png");
					JOptionPane.showOptionDialog(null,""+cartas.getNome(),"Feitico",JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,icon,null,null );
					while(true) {
						itens = InvocarCarta.invocarFeiticoAleatorio("Perspicacia das Eras");
						novaCarta = CartasFactory.criarCarta(TipoCarta.valueOf(itens[0].split(":")[1]), itens);
						if(!carta.equals(novaCarta)) break;
					}
					icon = new ImageIcon("imagens\\"+novaCarta.getNome().toUpperCase()+"-m.png");
					JOptionPane.showOptionDialog(null,""+novaCarta.getNome(),"Feitico",JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,icon,null,null );
					try {
						feiticos = (Feiticos) novaCarta;
						if(feiticos.getTraco().equals(Tracos.SUBITO))
							feiticos.getEfeito().acao(feiticos, thisJogador, jogador);
					} catch (ClassCastException e) {}
					break;
				}
			}
		}
	},
	/**
	 * Metodo utilizado para ativar os efeitos da carta Draven que ao ser conjurada
	 * ira invocar uma carta feitico chamada Revolucao do Machado e ira automaticamente
	 * aplicar os efeitos do feitico no inimigo, ou seja, jogador
	 * @author Richard Matias
	 */
	REV_MACHADO{
		@Override
		public void acao(Cartas carta, Jogador thisJogador, Jogador jogador) {
			Feiticos feiticos; ImageIcon icon; Cartas cartas; Campeao campeao;
			String[] itens = InvocarCarta.invocarCarta("Revolucao do Machado");
			for(int i = 0; i < thisJogador.getEvocadas().length; i++) {
				if(Objects.isNull(thisJogador.getEvocadas()[i])) {
					cartas = CartasFactory.criarCarta(TipoCarta.valueOf(itens[0].split(":")[1]), itens);
					icon = new ImageIcon("imagens\\"+cartas.getNome().toUpperCase()+"-m.png");
					JOptionPane.showOptionDialog(null,""+cartas.getNome(),"Feitico",JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,icon,null,null );
					try {
						feiticos = (Feiticos) cartas;
						if(feiticos.getTraco().equals(Tracos.SUBITO))
							feiticos.getEfeito().acao(feiticos, thisJogador, jogador);
					} catch (ClassCastException e) {}
					try {
						 campeao = (Campeao) carta;
						 campeao.setRevMachado(true);
						 campeao.setPodeUpar(false);
					} catch (ClassCastException e) {}
					break;
				}
			}
		}
	},
	/**
	 * Metodo utilizado para ativar os efeitos de todas as cartas que possuem auxiliar
	 * como efeito e que ao ser conjurada ira incrementar +1 ao poder da carta mais forte
	 * de thisJogador
	 * @author Richard Matias
	 */
	AUXILIAR{
		@Override
		public void acao(Cartas carta, Jogador thisJogador, Jogador jogador) {
			Cartas cartas = thisJogador.getEvocadas()[0];
			for(int i = 0; i < thisJogador.getEvocadas().length;i++) {
				if(Objects.isNull(thisJogador.getEvocadas()[i])) {
					if(thisJogador.getEvocadas()[i].getPoder() > cartas.getPoder()) {
						cartas = thisJogador.getEvocadas()[i];
					}
				}
			}
			cartas.setPoder(cartas.getPoder() + 1);
		}
	},
	/**
	 * Metodo utilizado para ativar os efeitos da carta Azir que ao ser conjurada
	 * ira invocar uma carta seguidor chamada Soldado de Areia e ira adicionar esta carta
	 * as evocada de thisJogador
	 * @author Richard Matias
	 */
	INVOCAR_SOLDADO_AREIA{
		@Override
		public void acao(Cartas carta, Jogador thisJogador, Jogador jogador) {
			String[] itens = InvocarCarta.invocarCarta("Soldado de Areia");
			for(int i = 0; i < thisJogador.getEvocadas().length; i++) {
				if(Objects.isNull(thisJogador.getEvocadas()[i])) {
					thisJogador.getEvocadas()[i] = CartasFactory.criarCarta(TipoCarta.valueOf(itens[0].split(":")[1]), itens);
					break;
				}
			}
		}
	},
	/**
	 * Metodo utilizado para ativar os efeitos da carta Leona que ao ser conjurada
	 * ira causar 1 de dano a carta mais forte de jogador. A carta Diana ira invocar
	 * o metodo addAmanhecer() e quando este atingir o valor de 5, esta carta podera 
	 * upar
	 * @author Richard Matias
	 */
	AMANHECER{
		@Override
		public void acao(Cartas carta, Jogador thisJogador, Jogador jogador) {
			Campeao campeao= null;
			int poder = 0; Cartas cartas = jogador.getEvocadas()[0];
			for(int i = 0; i < jogador.getEvocadas().length; i++) {
				if(Objects.isNull(jogador.getEvocadas()[i])) {
					try {
						campeao = (Campeao)jogador.getEvocadas()[i];
						if(jogador.getEvocadas()[i].getPoder() > poder) {
							cartas = jogador.getEvocadas()[i];
						}
					} catch (Exception e) {}
				}
			}
			campeao.setPoder(campeao.getPoder() - 1);
			campeao.addAmanhecer();
		}
	},
	/**
	 * Metodo utilizado para ativar os efeitos da carta Quinn que ao ser conjurada
	 * ira invocar uma carta seguidor chamada Valor e ira adicionar esta carta
	 * as evocada de thisJogador
	 * @author Richard Matias
	 */
	VALOR{
		@Override
		public void acao(Cartas carta, Jogador thisJogador, Jogador jogador) {
			String[] itens = InvocarCarta.invocarCarta("Valor");
			for(int i = 0; i < thisJogador.getEvocadas().length; i++) {
				if(Objects.isNull(thisJogador.getEvocadas()[i])) {
					thisJogador.getEvocadas()[i] = CartasFactory.criarCarta(TipoCarta.valueOf(itens[0].split(":")[1]), itens);
					break;
				}
			}
		}
	},
	/**
	 * Metodo utilizado para ativar os efeitos da carta Shyvana que ao ser conjurada
	 * ira ganhar +1 de vida e +1 de poder 
	 * @author Richard Matias
	 */
	MAIS_DANO_VIDA{
		@Override
		public void acao(Cartas carta, Jogador thisJogador, Jogador jogador) {
			carta.setVida(carta.getVida() + 1);
			carta.setPoder(carta.getPoder() + 1);
		}
	},
	/**
	 * Metodo utilizado para ativar os efeitos da carta Lissandra que ao ser conjurada
	 * ira invocar uma carta seguidor chamada Servo Congelado e ira adicionar esta carta
	 * as evocada de thisJogador
	 * @author Richard Matias
	 */
	SERVO_CONGELADO{
		@Override
		public void acao(Cartas carta, Jogador thisJogador, Jogador jogador) {
			String[] itens = InvocarCarta.invocarCarta("Servo Congelado");
			for(int i = 0; i < thisJogador.getEvocadas().length; i++) {
				if(Objects.isNull(thisJogador.getEvocadas()[i])) {
					thisJogador.getEvocadas()[i] = CartasFactory.criarCarta(TipoCarta.valueOf(itens[0].split(":")[1]), itens);
					break;
				}
			}
		}
	},
	/**
	 * Metodo utilizado para ativar os efeitos da carta Trundle que ao ser conjurada
	 * ira invocar uma carta seguidor chamada Pilar de Gelo e ira adicionar esta carta
	 * as evocada de thisJogador
	 * @author Richard Matias
	 */
	PILAR_GELO{
		@Override
		public void acao(Cartas carta, Jogador thisJogador, Jogador jogador) {
			Campeao campeao;
			String[] itens = InvocarCarta.invocarCarta("Pilar de Gelo");
			for(int i = 0; i < thisJogador.getEvocadas().length; i++) {
				if(Objects.isNull(thisJogador.getEvocadas()[i])) {
					thisJogador.getEvocadas()[i] = CartasFactory.criarCarta(TipoCarta.valueOf(itens[0].split(":")[1]), itens);
					try {
						campeao = (Campeao) carta;
						campeao.setPilarGelo(true);
					} catch (Exception e) {	}
					break;
				}
			}
		}
	},
	/**
	 * Metodo utilizado para ativar os efeitos da carta Elise que ao ser conjurada
	 * ira invocar uma carta seguidor chamada SPIDERLING e ira adicionar esta carta
	 * as evocada de thisJogador
	 * @author Richard Matias
	 */
	CRIA_ARACNIDEA{
		@Override
		public void acao(Cartas carta, Jogador thisJogador, Jogador jogador) {
			String[] itens = InvocarCarta.invocarCarta("SPIDERLING");
			for(int i = 0; i < thisJogador.getEvocadas().length; i++) {
				if(Objects.isNull(thisJogador.getEvocadas()[i])) {
					thisJogador.getEvocadas()[i] = CartasFactory.criarCarta(TipoCarta.valueOf(itens[0].split(":")[1]), itens);
					break;
				}
			}
		}
	},
	/**
	 * Metodo utilizado para ativar os efeitos da carta Hecarim que ao ser conjurada
	 * ira invocar uma carta seguidor chamada Spectral Rider e ira adicionar esta carta
	 * as evocada de thisJogador
	 * @author Richard Matias
	 */
	SPECTRAL_RIDERS{
		@Override
		public void acao(Cartas carta, Jogador thisJogador, Jogador jogador) {
			String[] itens = InvocarCarta.invocarCarta("Spectral Rider");
			for(int i = 0; i < thisJogador.getEvocadas().length; i++) {
				if(Objects.isNull(thisJogador.getEvocadas()[i])) {
					thisJogador.getEvocadas()[i] = CartasFactory.criarCarta(TipoCarta.valueOf(itens[0].split(":")[1]), itens);
					break;
				}
			}
		}
	},
	/**
	 * Metodo utilizado para ativar os efeitos da carta Kindred que ao ser evocada
	 * ira marcar a carta mais forte do inimigo e esta morrera na proxima rodada
	 * @author Richard Matias
	 */
	MARCAR{
		@Override
		public void acao(Cartas carta, Jogador thisJogador, Jogador jogador) {
			Campeao campeao; Seguidores seguidor;
			int vida = jogador.getEvocadas()[0].getVida(); int pos = 0;
			for(int i = 0; i < jogador.getEvocadas().length; i++) {
				if(Objects.nonNull(jogador.getEvocadas()[i]))
					if(jogador.getEvocadas()[i].getVida() < vida) { 
						vida = jogador.getEvocadas()[i].getVida();
						pos = i;
					}
			}
			try {
				try {
					campeao = (Campeao) jogador.getEvocadas()[pos];
					campeao.setMarcar(true);
				} catch (Exception e) {}
				seguidor = (Seguidores) jogador.getEvocadas()[pos];
				seguidor.setMarcar(true);
			} catch (ClassCastException e) {
				throw new ClassCastException("Carta não pertence a classe campeao / seguidores");
			}		
		}
	},
	/**
	 * Metodo utilizado para ativar os efeitos da carta Nocturne que ao ser evocada
	 * tera o metodo addAnoitecer() incrementado em 1, e quando atingir 5, esta carta
	 * estara apta a poder upar
	 * @author Richard Matias
	 */
	ANOITECER{
		@Override
		public void acao(Cartas carta, Jogador thisJogador, Jogador jogador) {
			//feito
			Campeao campeao;
			if(carta.getNome().equals("Nocturne")) {
				try {
					campeao = (Campeao) carta;
					for(int i = 0; i < jogador.getEvocadas().length;i++) {
						if(Objects.nonNull(jogador.getEvocadas()[i])) {
							campeao.addAnoitecer();
						}
					}
				} catch (Exception e) {
				}
			}
		}
	},
	FLAWLESS_DUET{
		@Override
		public void acao(Cartas carta, Jogador thisJogador, Jogador jogador) {
		}
	},
	/**
	 * Metodo utilizado para ativar os efeitos da carta Zed que ao ser conjurada
	 * ira invocar uma carta seguidor chamada Sombra viva e ira adicionar esta carta
	 * as evocada de thisJogador
	 * @author Richard Matias
	 */
	SOMBRA_VIVA{
		@Override
		public void acao(Cartas carta, Jogador thisJogador, Jogador jogador) {
			String[] itens = InvocarCarta.invocarCarta("Sombra Viva");
			for(int i = 0; i < thisJogador.getEvocadas().length; i++) {
				if(Objects.isNull(thisJogador.getEvocadas()[i])) {
					thisJogador.getEvocadas()[i] = CartasFactory.criarCarta(TipoCarta.valueOf(itens[0].split(":")[1]), itens);
					break;
				}
			}
		}
	},
	/**
	 * Metodo utilizado para ativar os efeitos da carta Tiana que ao ser evocada
	 * uma unidade das cartas evocadas de thisJogador ira atacar o nexus de jogador
	 * @author Richard Matias
	 */
	UNIDADE_E_ATACA_NUXUS{
		@Override
		public void acao(Cartas carta, Jogador thisJogador, Jogador jogador) {
			Random rand = new Random();
			while(true) {
				Cartas cartas = thisJogador.getEvocadas()[rand.nextInt(thisJogador.getEvocadas().length)];
				if(Objects.nonNull(cartas)) {
					jogador.setVidaNexus(jogador.getVidaNexus() - cartas.getPoder());
					break;
				}
			}
		}
	},
	/**
	 * Metodo utilizado para ativar os efeitos da carta Vanguarda que ao ser evocada
	 * ira conceder +1 de vida e +1 de poder a todos os aliados
	 * @author Richard Matias
	 */
	MAIS_1_1_ALIADOS{
		@Override
		public void acao(Cartas carta, Jogador thisJogador, Jogador jogador) {
			for(int i = 0; i < thisJogador.getEvocadas().length; i++) {
				if(Objects.nonNull(thisJogador.getEvocadas()[i])) {
					thisJogador.getEvocadas()[i].setVida(thisJogador.getEvocadas()[i].getVida() + 1);
					thisJogador.getEvocadas()[i].setPoder(thisJogador.getEvocadas()[i].getPoder() + 1);
				}
			}
		}
	},
	/**
	 * Metodo utilizado para ativar os efeitos da carta Duelista que ao destruir uma carta seguidor
	 * do inimigo o thisJogador ira ganhar uma carta seguidor poro nas suas cartas evocadas
	 * @author Richard Matias
	 */
	DESTRUIR_CS_PORO{
		@Override
		public void acao(Cartas carta, Jogador thisJogador, Jogador jogador) {
			String[] itens = InvocarCarta.invocarCarta("Poro");
			Seguidores seguidor;
			for(int i = 0; i < thisJogador.getEvocadas().length; i++) {
				if(Objects.isNull(thisJogador.getEvocadas()[i])) {
					try {
						seguidor = (Seguidores) thisJogador.getEvocadas()[i];
						if(seguidor.getDestruiuCS()) {
							thisJogador.getEvocadas()[i] = CartasFactory.criarCarta(TipoCarta.valueOf(itens[0].split(":")[1]), itens);							
						}
					} catch (Exception e) {
					}
					break;
				}
			}
		}
	},
	/**
	 * Metodo utilizado para ativar os efeitos de todas as cartas que contem furia como efeito
	 * e que ao ser conjurada ganha +1 de vida 
	 * @author Richard Matias
	 */
	FURIA{
		@Override
		public void acao(Cartas carta, Jogador thisJogador, Jogador jogador) {
			carta.setVida(carta.getVida() + 1);
		}
	},
	/**
	 * Metodo utilizado para ativar os efeitos da carta Poro Defensor quando esta for destruida
	 * o thisJogador ira ganhar uma nova carta e esta sera adicionada as suas cartas evocadas
	 * @author Richard Matias
	 */
	D_GANHA_CARTA{
		@Override
		public void acao(Cartas carta, Jogador thisJogador, Jogador jogador) {
			if(carta.getVida() <= 0) {
				for(int i = 0; i < thisJogador.getEvocadas().length; i++) {
					if(Objects.isNull(thisJogador.getEvocadas()[i])) {
						Cartas cartas = thisJogador.getGanharCarta();
						if(Objects.nonNull(cartas)) thisJogador.getEvocadas()[i] = cartas; 
						break;
					}
				}
			}
		}
	},
	ABDICADOS{
		@Override
		public void acao(Cartas carta, Jogador thisJogador, Jogador jogador) {
		}
	},
	/**
	 * Metodo utilizado para ativar os efeitos da carta Katarina quando esta for conjurada
	 * ira invocar um feitico chamado fio da lamina, logo apos ira ativar o efeito desse
	 * feitico que é causar 1 de dano ao nexus do inimigo, ou seja, jogador
	 * @author Richard Matias
	 */
	FIO_LAMINA{
		@Override
		public void acao(Cartas carta, Jogador thisJogador, Jogador jogador) {
			Feiticos feitico;
			String[] itens = InvocarCarta.invocarCarta("Fio da Lamina");
			for(int i = 0; i < thisJogador.getEvocadas().length; i++) {
				if(Objects.isNull(thisJogador.getEvocadas()[i])) {
					feitico = (Feiticos)CartasFactory.criarCarta(TipoCarta.valueOf(itens[0].split(":")[1]), itens);
					feitico.getEfeito().acao(feitico, thisJogador, jogador);
					break;
				}
			}
			
		}
	},
	/**
	 * Metodo utilizado para ativar os efeitos da carta Yasuo quando esta carta atacar 
	 * alguem das cartas do inimigo, este efeito causara que a carta do inimigo nao podera
	 * atacar por uma rodada.
	 * @author Richard Matias
	 */
	ATORDOAR{
		@Override
		public void acao(Cartas carta, Jogador thisJogador, Jogador jogador) {
			Campeao campeao; Seguidores seguidor;
			Cartas cartas = jogador.getEvocadas()[0];
			for(int i = 0; i < jogador.getEvocadas().length; i++) {
				if(Objects.nonNull(jogador.getEvocadas()[i])) 
					if(jogador.getEvocadas()[i].getPoder() > cartas.getPoder()) 
						cartas = jogador.getEvocadas()[i];
			}
			try {
				try {
					campeao = (Campeao) cartas;
					campeao.setAtordoar(true);
				} catch (ClassCastException e) {}
				seguidor = (Seguidores) cartas;
				seguidor.setAtordoar(true);
			} catch (ClassCastException e) {
				throw new ClassCastException("Carta não pertence a classe campeao / seguidores"); 
			}
		}
	},
	SUMONAR_LENDMARKS{
		@Override
		public void acao(Cartas carta, Jogador thisJogador, Jogador jogador) {
		}
	},
	CRIAR_4_BOMBAS{
		@Override
		public void acao(Cartas carta, Jogador thisJogador, Jogador jogador) {
		}
	},
	ARMA_DA_LUA{
		@Override
		public void acao(Cartas carta, Jogador thisJogador, Jogador jogador) {
		}
	},
	/**
	 * Metodo utilizado para ativar os efeitos da carta Riven ao ser conjurada
	 * este efeito ira alterar o valor do atributo setBladeOfExile() na classe campeao
	 * e com isso a Riven passara a ser elegivel para upar de level
	 * @author Richard Matias
	 */
	REFORJAR{
		@Override
		public void acao(Cartas carta, Jogador thisJogador, Jogador jogador) {
			Campeao campeao;
			try {
				campeao = (Campeao) carta;
				if(campeao.getNome().equals("Riven")) campeao.setBladeOfExile(true);
			} catch (ClassCastException e) {
				throw new ClassCastException("Carta não pertence a classe campeao");
			}
		}
	},
	UM_FEITICO_TORRE{
		@Override
		public void acao(Cartas carta, Jogador thisJogador, Jogador jogador) {
		}
	},
	/**
	 * Metodo utilizado para ativar os efeitos da carta Ashe
	 * este efeito congela o inimigo mais forte entre as cartas do jogador
	 * ou seja, causa 1 de dano a carta, decrementando 1 na vida
	 * @author Richard Matias
	 */
	CONGELA_INIMIGO_FORTE{
		@Override
		public void acao(Cartas carta, Jogador thisJogador, Jogador jogador) {
			Campeao cartaCampeao = (Campeao) carta;
			Campeao campeao; Seguidores seguidor;
			Cartas cartas = jogador.getEvocadas()[0];
			for(int i = 0; i < jogador.getEvocadas().length; i++) {
				if(Objects.nonNull(jogador.getEvocadas()[i])) 
					if(jogador.getEvocadas()[i].getPoder() > cartas.getPoder()) 
						cartas = jogador.getEvocadas()[i];
			}
			try {
				try {
					campeao = (Campeao) cartas;
					campeao.setVida(campeao.getVida() - 1);
					cartaCampeao.addCongelar();
				} catch (ClassCastException e) {}
				seguidor = (Seguidores) cartas;
				seguidor.setVida(seguidor.getVida() - 1);
				cartaCampeao.addCongelar();
			} catch (ClassCastException e) {
				throw new ClassCastException("Carta não pertence a classe campeao / seguidores"); 
			}
		}
	},
	UM_GOSTO_ADQUIRIDO_MAO{
		@Override
		public void acao(Cartas carta, Jogador thisJogador, Jogador jogador) {
		}
	},
	/**
	 * Metodo utilizado para ativar os efeitos do feitico Fugitivo
	 * este feitico ira invocar uma carta seguidor que custe até 1 de mana
	 * e ira adicionar esta carta as unidades evocada de thisJogador
	 * @author Richard Matias
	 */
	INVOQUE_1S_1MANA{//ok
		@Override
		public void acao(Cartas carta, Jogador thisJogador, Jogador jogador) {
			Random rand = new Random();
			ArrayList<String[]> seguidores = InvocarCarta.invocarSeguidorAleatorio();
			Cartas cartas;
			while(true) {
				 String[] itens = seguidores.get( rand.nextInt(seguidores.size()) );
				 cartas = CartasFactory.criarCarta(TipoCarta.valueOf(itens[0].split(":")[1]), itens);
				 if(cartas.getCustoMana() <= 1) {
					 for(int i = 0; i < thisJogador.getEvocadas().length; i++) {
							if(Objects.isNull(thisJogador.getEvocadas()[i])) { 
								thisJogador.getEvocadas()[i] = cartas;
								break;
							}
					}
					break; 
				}
			}
		}
	},
	/**
	 * Metodo utilizado para ativar os efeitos do feitico Tiro de Aviso
	 * este feitico ira causar 1 de danos a vida do nexus do jogador inimigo
	 * ou seja, jogador
	 * @author Richard Matias
	 */
	CAUSE_1_DANO_NEXUS{//ok
		@Override
		public void acao(Cartas carta, Jogador thisJogador, Jogador jogador) {
			jogador.setVidaNexus(jogador.getVidaNexus() - 1);
		}
	},
	/**
	 * Metodo utilizado para ativar os efeitos do feitico Elixir da Ira
	 * este feitico ira incrementar +3 de poder a carta mais forte do thisJogador
	 * @author Richard Matias
	 */
	CONCEDA_3_ALIADO_FORTE{//ok
		@Override
		public void acao(Cartas carta, Jogador thisJogador, Jogador jogador) {
			int poder = thisJogador.getEvocadas()[0].getPoder();
			Cartas cartas = thisJogador.getEvocadas()[0];
			for(int i = 0; i < thisJogador.getEvocadas().length; i++) {
				if(Objects.nonNull(thisJogador.getEvocadas()[i])) {
					if(thisJogador.getEvocadas()[i].getPoder() > poder) {
						cartas = thisJogador.getEvocadas()[i];
						poder = thisJogador.getEvocadas()[i].getPoder();
					}
				}
			}
			cartas.setPoder(cartas.getPoder() + 3);
		}
	},
	/**
	 * Metodo utilizado para ativar os efeitos do feitico Bando Voraz
	 * este feitico ira causar 4 de dano a uma carta do inimigo que ja
	 * tenha sofrido dano(jogador).
	 * @author Richard Matias
	 */
	CAUSE_4_DANO_INIMIGO_DANO{//ok
		@Override
		public void acao(Cartas carta, Jogador thisJogador, Jogador jogador) {
			Campeao campeao; Seguidores seguidor;
			for(int i = 0; i < jogador.getEvocadas().length; i++) {
				if(Objects.nonNull(jogador.getEvocadas()[i])) {
					try {
						try {
							campeao = (Campeao) jogador.getEvocadas()[i];
							if(campeao.getVida() != campeao.getVidaAbatalha()) {
								campeao.setVida(campeao.getVida() - 4);
								if(campeao.getVida() <= 0) jogador.getEvocadas()[i] = null;
								return;
							} 
						} catch (Exception e) {}
						seguidor = (Seguidores) jogador.getEvocadas()[i];
						if(seguidor.getVida() != seguidor.getVidaAbatalha()) {
							seguidor.setVida(seguidor.getVida() - 4);
							if(seguidor.getVida() <= 0) jogador.getEvocadas()[i] = null;
							return;
						}
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
			}
		}
	},
	/**
	 * Metodo utilizado para ativar os efeitos do feitico Laco Fraterno
	 * que é incrementar +2 ao poder e +0 a vida de duas cartas sorteadas aleatoriamente 
	 * entre as cartas evocadas deste thisJogador 
	 * @author Richard Matias
	 */
	DE_2_0_2_ALIADOS{//ok
		@Override
		public void acao(Cartas carta, Jogador thisJogador, Jogador jogador) {
			Random rand = new Random();
			Cartas cartas; Cartas aux = null; int i = 0;
			while(true) {
				cartas = thisJogador.getEvocadas()[rand.nextInt(thisJogador.getEvocadas().length)];
				if(Objects.nonNull(cartas) && !cartas.equals(aux)) {
					aux = cartas;
					cartas.setPoder(cartas.getPoder() + 2);
					i++;
					if(i == 2) break;
				}
			}
		}
	},
	/**
	 * Metodo utilizado para ativar os efeitos do feitico Golpe Radiante
	 * que é incrementar +1 ao poder e +1 a vida da carta sorteada aleatoriamente 
	 * entre as cartas evocadas deste thisJogador 
	 * @author Richard Matias
	 */
	DE_1_1_ALIADO{//ok
		@Override
		public void acao(Cartas carta, Jogador thisJogador, Jogador jogador) {
			Random rand = new Random();
			Cartas cartas; Cartas aux = null; 
			while(true) {
				cartas = thisJogador.getEvocadas()[rand.nextInt(thisJogador.getEvocadas().length)];
				if(Objects.nonNull(cartas) && !cartas.equals(aux)) {
					aux = cartas;
					cartas.setPoder(cartas.getPoder() + 1);
					cartas.setVida(cartas.getVida() + 1);
					break;
				}
			}
		}
	},
	/**
	 * Metodo utilizado para ativar os efeitos do feitico Cota de Malha
	 * que é da robusto para apanas uma carta aleatoria entre as
	 * evocados de thisJogador, ou seja, incrementar 1 na vida de acada um 
	 * @author Richard Matias
	 */
	DE_ROBUSTO_1ALIADOS{//ok
		@Override
		//NAO FUNCIONOU
		public void acao(Cartas carta, Jogador thisJogador, Jogador jogador) {
			Random rand = new Random();
			Cartas cartas; Cartas aux = null;
			if(!thisJogador.getDeckIsEmpty(thisJogador)) {
				while(true) {
					cartas = thisJogador.getEvocadas()[rand.nextInt(thisJogador.getEvocadas().length)];
					if(Objects.nonNull(cartas) && !cartas.equals(aux)) {
						aux = cartas;
						cartas.setVida(cartas.getVida() + 1);
						break;
					}
				}
			}
		}
	},
	/**
	 * Metodo utilizado para ativar os efeitos do Feitico Determinacao Guardia
	 * que é da robusto para todos as cartas evocados de thisJogador, ou seja, incrementar 1
	 * na vida de acada um
	 * @author Richard Matias 
	 */
	DE_ROBUSTO_TOTOS_ALIADOS{//ok
		@Override
		public void acao(Cartas carta, Jogador thisJogador, Jogador jogador) {
			for(int i = 0; i < thisJogador.getEvocadas().length; i++) {
				if(Objects.nonNull(thisJogador.getEvocadas()[i])) {
					thisJogador.getEvocadas()[i].setVida(thisJogador.getEvocadas()[i].getVida() + 1);
				}
			}
		}
	};
}
