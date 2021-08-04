package com.unicamp.mc322.cartas;

import com.unicamp.mc322.jogador.Jogador;

/**
 * Enum que ficara responsavel por implementar e aplicar todos os Tracos e as
 * epecificidades de cada carta que 
 * os jogadore tiverem em seu deck 
 * Conceito do Strategy pattern foi utilizado para implementar esta classe e suas
 * funcionalidades
 */
public enum Tracos implements TracoAcao{	
	NULO {
		@Override
		public void acao(Cartas carta1, Cartas carta2) {}
	},
	/**
	 * Metodo SOBREPUJAR que permite que a carta que tenha esse traco
	 * ao atacar a carta do inimigo, o dano que a carta nao absorver 
	 * sera trasferito ao nexus do inimigo
	 * @author Richard Matias
	 */
	SOBREPUJAR {
		@Override
		public void acao(Cartas carta1, Cartas carta2) {
			int poder  = carta1.getPoder() - carta2.getVida();
			Campeao campeao; Seguidores seguidor;
			try {
				try {
					campeao = (Campeao) carta2;
					if(poder >= 0 && !campeao.getTraco().equals(ELUSIVO)) {
						this.jogadorAfetado.setVidaNexus(this.jogadorAfetado.getVidaNexus() - poder);;
						if(!(poder == 0)) jogador.addQtdVezesDanoNexus();
						return;
					}
					if(campeao.getTraco().equals(ELUSIVO)) return;
				} catch (ClassCastException e) {}
				seguidor = (Seguidores) carta2;
				if(poder >= 0) {
					this.jogadorAfetado.setVidaNexus(this.jogadorAfetado.getVidaNexus() - poder);;
					if(!(poder == 0)) jogador.addQtdVezesDanoNexus();
					return;
				}
			} catch (ClassCastException e) {
				throw new ClassCastException("Carta não pertence a classe Seguidores / campeao");
			}
		}  
	},
	ASSUSTADOR {
		@Override
		public void acao(Cartas carta1, Cartas carta2) {}
	},
	/**
	 * Metodo ROBUSTO que permite que a carta que tenha esse traco tanke
	 * 1 a mais de dano de qualquer fonte, ou seja, a carta que possui esse
	 * ganha 1 a mais de vida
	 * @author Richard Matias
	 */
	ROBUSTO {
		@Override
		public void acao(Cartas carta1, Cartas carta2) {
			Campeao carta; Seguidores seguidores;
			try {
				try {
					carta = (Campeao) carta2;
					if(!carta.getTraco().equals(ELUSIVO)) {
						carta1.setVida(carta1.getVida() + 1);
						return;
					}
					if(carta.getTraco().equals(ELUSIVO)) return;
				}catch(ClassCastException e) {}
				seguidores = (Seguidores) carta2;
				carta1.setVida(carta1.getVida() + 1);
				return;
			} catch (ClassCastException e) {
				throw new ClassCastException("Carta não pertence a classe Campeao / Seguidores");
			}
		}
	},
	ATAQUE_RAPIDO {
		@Override
		public void acao(Cartas carta1, Cartas carta2) {}
	},
	/**
	 * Metodo DESAFIADOR que permite que a carta que tenha esse traco decida
	 * durante o ataque quem ela deseja enfrentar
	 * traco é ativado durante um confronto
	 * @author Richard Matias
	 */
	DESAFIADOR {
		@Override
		public void acao(Cartas carta1, Cartas carta2) {}
	},
	/**
	 * Metodo REGENERACAO que permite que a carta que tenha esse traco recupere
	 * toda a vida, esse traco somente é ativado durante uma rodada
	 * traco é ativado durante um confronto
	 * @author Richard Matias
	 */
	REGENERACAO {
		@Override
		public void acao(Cartas carta1, Cartas carta2) {
			Campeao carta;
			try {
				carta = (Campeao) carta1;
				if(carta.getAcao() && carta.getVida() > 0) {
					carta.setVida(carta.getVidaAbatalha());
					carta.setAcao(false);
				}
			}catch (ClassCastException e) {
				throw new ClassCastException("Carta não pertence a classe campeao");
			}
		}
	},
	/**
	 * Metodo BARREIRA que permite que a carta que tenha esse traco tanke um ataque
	 * esse traco somente é ativado durante uma rodada
	 * traco é ativado durante um confronto
	 * @author Richard Matias
	 * 
	 */
	BARREIRA {
		@Override
		public void acao(Cartas carta1, Cartas carta2) {
			Campeao carta;
			try {
				carta = (Campeao) carta1;
				if(carta.getAcao()) {
					carta.setVida(carta.getVida() + carta2.getPoder());
				}
			} catch (ClassCastException e) {
				throw new ClassCastException("Carta não pertence a classe campeao");
			}
		}
	},
	PATRULHEIRO {
		@Override
		public void acao(Cartas carta1, Cartas carta2) {}
	},
	/**
	 * Metodo FURIA que da +1 de poder e +1 de vida a todas as cartas que 
	 * possuem o Traco FURIA, traco é ativado durante um confronto
	 * @author Richard Matias
	 */
	FURIA {
		@Override
		public void acao(Cartas carta1, Cartas carta2) {
			if(carta1.getPoder() > carta2.getVida()) {
				carta1.setVida(carta1.getVida() + 1);
				carta1.setPoder(carta1.getPoder() + 1);
			}
		}
	},
	/**
	 * Metodo ELUSIVO serve para que a carta so possa ser atacada por outra carta elusiva 
	 * caso a outra carta nao tenha o traco ELUSIVO, a carta que tem ELUSIVO ignora a outra
	 * e ataca o nexus do inimigo, traco é ativado durante um confronto
	 * @author Richard Matias
	 */
	ELUSIVO {
		@Override
		public void acao(Cartas carta1, Cartas carta2) {
			Campeao carta;
			try {
				carta = (Campeao) carta2;
				if(!carta.getTraco().equals(ELUSIVO)) {
					jogadorAfetado.setVidaNexus(jogadorAfetado.getVidaNexus() - carta1.getPoder());
					jogador.addQtdVezesDanoNexus();
				}
			} catch (ClassCastException e) {
				throw new ClassCastException("Carta não pertence a classe campeao");
			}
		}
	},
	/**
	 * Metodo para feiticos, feiticos com o traco SUBITO sao ativados instantaneamente
	 * e não vao para as cartas evocadas dos jogadores
	 * @author Richard Matias
	 */
	SUBITO{
		@Override
		public void acao(Cartas carta1, Cartas carta2) {			
		}
	},
	RAPIDO{
		@Override
		public void acao(Cartas carta1, Cartas carta2) {			
		}
	},
	LENTO{
		@Override
		public void acao(Cartas carta1, Cartas carta2) {}
	};
	/**
	 * jogadorAfetado é o jogador que sera afetado negativamente
	 * Ex: no metodo sobrepujar o jogadorAfetado sera o jogador que estara sendo atacado
	 * 
	 *  ja no caso de jogador, este estaria sendo o jogador que seria afetado positivamente
	 *  Ex: seria como se o jogador que esta chamando o metodo acao()
	 *  
	 *  para fazer com que acao afete o jogador2 e não jogador1
	 *  basta fazermos da seguinte forma:
	 *  
	 *  -	campeao.getTraco().setJogador(this); -> aqui seria jogador
	 *  -	campeao.getTraco().setJogadorAfetado(jogador); -> aqui seria o jogador afetado
	 *  
	 *  E para as cartas seria da seguinte foram:
	 *  -	campeaoJ1.getTraco().acao(campeaoJ1, campeaoJ2);
	 *  
	 *  significa que as campeaoJ1 irao atacar as campeaoJ2  
	 *  
	 *  Para que o atual jogadorAfetado seja o jogador, basta invermos as ordens 
	 *  Ex:
	 *  -	campeao.getTraco().setJogador(jogador); -> aqui seria jogador
	 *  -	campeao.getTraco().setJogadorAfetado(this); -> aqui seria o jogador afetado
	 * 
	 *  E para as cartas a mesma coisa
	 */
	protected Jogador jogadorAfetado, jogador;
	
	public void setJogadorAfetado(Jogador jogador) {
		this.jogadorAfetado = jogador; 
	}
	public void setJogador(Jogador jogador) {
		this.jogador = jogador;
	}
}