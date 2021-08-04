package com.unicamp.mc322.cartas;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import com.unicamp.mc322.jogador.Jogador;
/**
 * Enum que ficara responsavel por implementar e aplicar todos os efeitos e as
 * epecificidades de cada carta que os jogadores tiverem 
 * Conceito do Strategy pattern foi utilizado para implementar esta classe e suas
 * funcionalidades
 */
public enum Nivel implements NivelAcao {
	/**
	 * Metodo responsavel por verificar se a carta Fizz
	 * cumpre os requisitos para upar (level up), ou seja,
	 * se getNumFeiticosConjurados() e getPodeUpar() satisfazem as condições
	 * @author Richard Matias 
	 */
	CONJURAR_6_FEITICOS{
		@Override
		public void acao(Cartas carta, Jogador jogador) {
			Campeao campeao;ImageIcon icon;
			try {
				campeao = (Campeao) carta;
				if(jogador.getNumFeiticosConjurados() == 6 && campeao.getPodeUpar()) {
					icon = new ImageIcon("imagens\\"+campeao.getNome().toUpperCase()+"-m.png");
					JOptionPane.showOptionDialog(null,""+carta.getNome(),"Level up",JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,icon,null,null );
					campeao.setVida(campeao.getVida() + 1);
					campeao.setPoder(campeao.getPoder() + 1);
					campeao.setPodeUpar(false);
				}
			} catch (ClassCastException e) {
				throw new ClassCastException("Carta não pertence a classe campeao");
			}
		}
	},
	/**
	 * Metodo responsavel por verificar se a carta Gang Plank
	 * cumpre os requisitos para upar (level up), ou seja,
	 * se getQtdVezesDanoNexus() e getPodeUpar() satisfazem as condições
	 * @author Richard Matias 
	 */
	DANO_5_VEZES_NEXUS{
		@Override
		public void acao(Cartas carta, Jogador jogador) {
			Campeao campeao;ImageIcon icon;
			try {
				campeao = (Campeao) carta;
				if(jogador.getQtdVezesDanoNexus() == 5 && campeao.getPodeUpar()) {
					icon = new ImageIcon("imagens\\"+campeao.getNome().toUpperCase()+"-m.png");
					JOptionPane.showOptionDialog(null,""+carta.getNome(),"Level up",JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,icon,null,null );
					campeao.setVida(campeao.getVida() + 1);
					campeao.setPoder(campeao.getPoder() + 1);
					campeao.setPodeUpar(false);
				}
			} catch (ClassCastException e) {
				throw new ClassCastException("Carta não pertence a classe campeao");
			}
		}
	},
	/**
	 * Metodo responsavel por verificar se a carta Miss Fortune
	 * cumpre os requisitos para upar (level up), ou seja,
	 * se getQtdXAliadosAtacaram() e getPodeUpar() satisfazem as condições
	 * @author Richard Matias 
	 */
	ALIADOS_ATACAR_4_VEZES{
		@Override
		public void acao(Cartas carta, Jogador jogador) {
			Campeao campeao;ImageIcon icon;
			try {
				campeao = (Campeao) carta;
				if(jogador.getQtdXAliadosAtacaram() == 4 && campeao.getPodeUpar()) {
					icon = new ImageIcon("imagens\\"+campeao.getNome().toUpperCase()+"-m.png");
					JOptionPane.showOptionDialog(null,""+carta.getNome(),"Level up",JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,icon,null,null );
					campeao.setVida(campeao.getVida() + 1);
					campeao.setPoder(campeao.getPoder() + 1);
					campeao.setPodeUpar(false);
				}
			} catch (ClassCastException e) {
				throw new ClassCastException("Carta não pertence a classe campeao");
			}
		}
	},
	/**
	 * Metodo responsavel por verificar se a carta Twisted Fate
	 * cumpre os requisitos para upar (level up), ou seja,
	 * se getQtdCartasCompradas() e getPodeUpar() satisfazem as condições
	 * @author Richard Matias 
	 */
	COMPRAR_9_CARTAS{
		@Override
		public void acao(Cartas carta, Jogador jogador) {
			Campeao campeao;ImageIcon icon;
			try {
				campeao = (Campeao) carta;
				if(jogador.getQtdCartasCompradas() == 9 && campeao.getPodeUpar()) {
					icon = new ImageIcon("imagens\\"+campeao.getNome().toUpperCase()+"-m.png");
					JOptionPane.showOptionDialog(null,""+carta.getNome(),"Level up",JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,icon,null,null );
					campeao.setVida(campeao.getVida() + 1);
					campeao.setPoder(campeao.getPoder() + 1);
					campeao.setPodeUpar(false);
				}
			} catch (ClassCastException e) {
				throw new ClassCastException("Carta não pertence a classe campeao");
			}
		}
	},
	/**
	 * Metodo responsavel por verificar se a carta Fiora
	 * cumpre os requisitos para upar (level up), ou seja,
	 * se getQtdAbatidos() e getPodeUpar() satisfazem as condições
	 * @author Richard Matias 
	 */
	ABATER_2_INIMIGOS{
		@Override
		public void acao(Cartas carta, Jogador jogador) {
			Campeao campeao;ImageIcon icon;
			try {
				campeao = (Campeao) carta;
				if(jogador.getQtdAbatidos() == 2 && campeao.getPodeUpar()) {
					icon = new ImageIcon("imagens\\"+campeao.getNome().toUpperCase()+"-m.png");
					JOptionPane.showOptionDialog(null,""+carta.getNome(),"Level up",JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,icon,null,null );
					campeao.setVida(campeao.getVida() + 1);
					campeao.setPoder(campeao.getPoder() + 1);
					campeao.setPodeUpar(false);
				}
			} catch (ClassCastException e) {
				throw new ClassCastException("Carta não pertence a classe campeao");
			}
		}
	},
	/**
	 * Metodo responsavel por verificar se a carta Garen, Katarina, 
	 * cumpre os requisitos para upar (level up), ou seja,
	 * se getGolpear() e getPodeUpar() satisfazem as condições
	 * @author Richard Matias 
	 */
	GOLPEAR_2_VEZES{
		@Override
		public void acao(Cartas carta, Jogador jogador) {
			Campeao campeao;ImageIcon icon;
			try {
				campeao = (Campeao) carta;
				if(campeao.getGolpear() == 2 && campeao.getPodeUpar()) {
					icon = new ImageIcon("imagens\\"+campeao.getNome().toUpperCase()+"-m.png");
					JOptionPane.showOptionDialog(null,""+carta.getNome(),"Level up",JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,icon,null,null );
					campeao.setVida(campeao.getVida() + 1 );
					campeao.setPoder(campeao.getPoder() + 1);
				}
			} catch (ClassCastException e) {
				throw new ClassCastException("Carta não pertence a classe campeao");
			}
		}
	},
	/**
	 * Metodo responsavel por verificar se a carta Jarvan
	 * cumpre os requisitos para upar (level up), ou seja,
	 * se getQtdAliadosSobreviveram() e getPodeUpar() satisfazem as condições
	 * @author Richard Matias 
	 */
	ALIADOS_S_4_GOLPES{
		@Override
		public void acao(Cartas carta, Jogador jogador) {
			Campeao campeao;ImageIcon icon;
			try {
				campeao = (Campeao) carta;
				if(jogador.getQtdAliadosSobreviveram() == 4 && campeao.getPodeUpar()) {
					icon = new ImageIcon("imagens\\"+campeao.getNome().toUpperCase()+"-m.png");
					JOptionPane.showOptionDialog(null,""+carta.getNome(),"Level up",JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,icon,null,null );
					campeao.setVida(campeao.getVida() + 1);
					campeao.setPoder(campeao.getPoder() + 1);
					campeao.setPodeUpar(false);
				}
			} catch (ClassCastException e) {
				throw new ClassCastException("Carta não pertence a classe campeao");
			}
		}
	},
	/**
	 * Metodo responsavel por verificar se a carta Lucian
	 * cumpre os requisitos para upar (level up), ou seja,
	 * se getQtdAbatidos() e getPodeUpar() satisfazem as condições
	 * @author Richard Matias 
	 */
	MORTE_4_ALIADOS{
		@Override
		public void acao(Cartas carta, Jogador jogador) {
			Campeao campeao;ImageIcon icon;
			try {
				campeao = (Campeao) carta;
				if(jogador.getQtdAbatidos() == 4 && campeao.getPodeUpar()) {
					icon = new ImageIcon("imagens\\"+campeao.getNome().toUpperCase()+"-m.png");
					JOptionPane.showOptionDialog(null,""+carta.getNome(),"Level up",JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,icon,null,null );
					campeao.setVida(campeao.getVida() + 1);
					campeao.setPoder(campeao.getPoder() + 1);
					campeao.setPodeUpar(false);
				}
			} catch (ClassCastException e) {
				throw new ClassCastException("Carta não pertence a classe campeao");
			}
		}
	},
	/**
	 * Metodo responsavel por verificar se a carta Lux
	 * cumpre os requisitos para upar (level up), ou seja,
	 * se getManaGasta() e getPodeUpar() satisfazem as condições
	 * @author Richard Matias 
	 */
	GASTAR_6_MANA_FEITICOS{
		@Override
		public void acao(Cartas carta, Jogador jogador) {
			Campeao campeao;ImageIcon icon;
			try {
				campeao = (Campeao) carta;
				if(jogador.getManaGasta() >= 6 && campeao.getPodeUpar()) {
					icon = new ImageIcon("imagens\\"+campeao.getNome().toUpperCase()+"-m.png");
					JOptionPane.showOptionDialog(null,""+carta.getNome(),"Level up",JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,icon,null,null );
					campeao.setVida(campeao.getVida() + 1);
					campeao.setPoder(campeao.getPoder() + 1);
					campeao.setPodeUpar(false);
				}
			} catch (ClassCastException e) {
				throw new ClassCastException("Carta não pertence a classe campeao");
			}
		}
	},
	/**
	 * Metodo responsavel por verificar se a carta Shyvana
	 * cumpre os requisitos para upar (level up), ou seja,
	 * se getDanoTotal() e getPodeUpar() satisfazem as condições
	 * @author Richard Matias 
	 */
	CAUSAR_12_DANO{
		@Override
		public void acao(Cartas carta, Jogador jogador) {
			Campeao campeao;ImageIcon icon;
			try {
				campeao = (Campeao) carta;
				if(jogador.getDanoTotal() >= 12 && campeao.getPodeUpar()) {
					icon = new ImageIcon("imagens\\"+campeao.getNome().toUpperCase()+"-m.png");
					JOptionPane.showOptionDialog(null,""+carta.getNome(),"Level up",JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,icon,null,null );
					campeao.setVida(campeao.getVida() + 1);
					campeao.setPoder(campeao.getPoder() + 1);
					campeao.setPodeUpar(false);
				}
			} catch (ClassCastException e) {
				throw new ClassCastException("Carta não pertence a classe campeao");
			}
		}
	},
	/**
	 * Metodo responsavel por verificar se a carta Braum
	 * cumpre os requisitos para upar (level up), ou seja,
	 * se getDanoTomado() e getPodeUpar() satisfazem as condições
	 * @author Richard Matias 
	 */
	SOBREVIVER_10_DANO_TOTAL{
		@Override
		public void acao(Cartas carta, Jogador jogador) {
			Campeao campeao;ImageIcon icon;
			try {
				campeao = (Campeao) carta;
				if(campeao.getDanoTomado() >= 10 && campeao.getPodeUpar()) {
					icon = new ImageIcon("imagens\\"+campeao.getNome().toUpperCase()+"-m.png");
					JOptionPane.showOptionDialog(null,""+carta.getNome(),"Level up",JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,icon,null,null );
					campeao.setVida(campeao.getVida() + 1 );
					campeao.setPoder(campeao.getPoder() + 1);
				}
			} catch (ClassCastException e) {
				throw new ClassCastException("Carta não pertence a classe campeao");
			}
		}
	},
	/**
	 * Metodo responsavel por verificar se a carta Lissandra
	 * cumpre os requisitos para upar (level up), ou seja,
	 * se getQtdAliado8Custo() e getPodeUpar() satisfazem as condições
	 * @author Richard Matias 
	 */
	INVOCAR_2_ALIADOS_8_CUSTO{
		@Override
		public void acao(Cartas carta, Jogador jogador) {
			Campeao campeao;ImageIcon icon;
			try {
				campeao = (Campeao) carta;
				if(jogador.getQtdAliado8Custo() >= 2 && campeao.getPodeUpar()) {
					icon = new ImageIcon("imagens\\"+campeao.getNome().toUpperCase()+"-m.png");
					JOptionPane.showOptionDialog(null,""+carta.getNome(),"Level up",JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,icon,null,null );
					campeao.setVida(campeao.getVida() + 1);
					campeao.setPoder(campeao.getPoder() + 1);
					campeao.setPodeUpar(false);
				}
			} catch (ClassCastException e) {
				throw new ClassCastException("Carta não pertence a classe campeao");
			}
		}
	},
	/**
	 * Metodo responsavel por verificar se a carta Tryndamere
	 * cumpre os requisitos para upar (level up), ou seja,
	 * se getVida() e getPodeUpar() satisfazem as condições
	 * @author Richard Matias 
	 */
	INVES_DE_MORRER_UPA{
		@Override
		public void acao(Cartas carta, Jogador jogador) {
			Campeao campeao;ImageIcon icon;
			try {
				campeao = (Campeao) carta;
				if(campeao.getVida() <= 0 && campeao.getPodeUpar()) {
					icon = new ImageIcon("imagens\\"+campeao.getNome().toUpperCase()+"-m.png");
					JOptionPane.showOptionDialog(null,""+carta.getNome(),"Level up",JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,icon,null,null );
					campeao.setVida(campeao.getVidaAbatalha() + 1 );
					campeao.setPoder(campeao.getPoder() + 1);
				}
			} catch (ClassCastException e) {
				throw new ClassCastException("Carta não pertence a classe campeao");
			}
		}
	},
	/**
	 * Metodo responsavel por verificar se a carta Hecarim
	 * cumpre os requisitos para upar (level up), ou seja,
	 * se getQtdXAliadosAtacaram() e getPodeUpar() satisfazem as condições
	 * @author Richard Matias 
	 */
	ATACAR_COM_6{
		@Override
		public void acao(Cartas carta, Jogador jogador) {
			Campeao campeao;ImageIcon icon;
			try {
				campeao = (Campeao) carta;
				if(jogador.getQtdXAliadosAtacaram() >= 6 && campeao.getPodeUpar()) {
					icon = new ImageIcon("imagens\\"+campeao.getNome().toUpperCase()+"-m.png");
					JOptionPane.showOptionDialog(null,""+carta.getNome(),"Level up",JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,icon,null,null );
					campeao.setVida(campeao.getVida() + 1);
					campeao.setPoder(campeao.getPoder() + 1);
					campeao.setPodeUpar(false);
				}
			} catch (ClassCastException e) {
				throw new ClassCastException("Carta não pertence a classe campeao");
			}
		}
	},
	/**
	 * Metodo responsavel por verificar se a carta Kalista
	 * cumpre os requisitos para upar (level up), ou seja,
	 * se getQtdAbatidos() e getPodeUpar() satisfazem as condições
	 * @author Richard Matias 
	 */
	TRES_ALIADOS_MORREREM{
		@Override
		public void acao(Cartas carta, Jogador jogador) {
			Campeao campeao; ImageIcon icon;
			try {
				campeao = (Campeao) carta;
				if(jogador.getQtdAbatidos() >= 3 && campeao.getPodeUpar()) {
					icon = new ImageIcon("imagens\\"+campeao.getNome().toUpperCase()+"-m.png");
					JOptionPane.showOptionDialog(null,""+carta.getNome(),"Level up",JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,icon,null,null );
					campeao.setVida(campeao.getVida() + 1);
					campeao.setPoder(campeao.getPoder() + 1);
					campeao.setPodeUpar(false);
				}
			} catch (ClassCastException e) {
				throw new ClassCastException("Carta não pertence a classe campeao");
			}
		}
	},
	/**
	 * Metodo responsavel por verificar se a carta Thresh
	 * cumpre os requisitos para upar (level up), ou seja,
	 * se getQtdAbatidos() e getPodeUpar() satisfazem as condições
	 * @author Richard Matias 
	 */
	SEIS_UNIDADES_MORREREM{
		@Override
		public void acao(Cartas carta, Jogador jogador) {
			Campeao campeao;ImageIcon icon;
			try {
				campeao = (Campeao) carta;
				if(jogador.getQtdAbatidos() >= 6 && campeao.getPodeUpar()) {
					icon = new ImageIcon("imagens\\"+campeao.getNome().toUpperCase()+"-m.png");
					JOptionPane.showOptionDialog(null,""+carta.getNome(),"Level up",JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,icon,null,null );
					campeao.setVida(campeao.getVida() + 1);
					campeao.setPoder(campeao.getPoder() + 1);
					campeao.setPodeUpar(false);
				}
			} catch (ClassCastException e) {
				throw new ClassCastException("Carta não pertence a classe campeao");
			}
		}
	},
	/**
	 * Metodo responsavel por verificar se a carta Irelia
	 * cumpre os requisitos para upar (level up), ou seja,
	 * se getQtdXAliadosAtacaram() e getPodeUpar() satisfazem as condições
	 * @author Richard Matias 
	 */
	DOZE_ALIADOS_ATACARAM{
		@Override
		public void acao(Cartas carta, Jogador jogador) {
			Campeao campeao;ImageIcon icon;
			try {
				campeao = (Campeao) carta;
				if(jogador.getQtdXAliadosAtacaram() >= 12 && campeao.getPodeUpar()) {
					icon = new ImageIcon("imagens\\"+campeao.getNome().toUpperCase()+"-m.png");
					JOptionPane.showOptionDialog(null,""+carta.getNome(),"Level up",JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,icon,null,null );
					campeao.setVida(campeao.getVida() + 1);
					campeao.setPoder(campeao.getPoder() + 1);
					campeao.setPodeUpar(false);
				}
			} catch (ClassCastException e) {
				throw new ClassCastException("Carta não pertence a classe campeao");
			}
		}
	},
	/**
	 * Metodo responsavel por verificar se a carta Darius
	 * cumpre os requisitos para upar (level up), ou seja,
	 * se getVidaNexus() e getPodeUpar() satisfazem as condições
	 * @author Richard Matias 
	 */
	NEXUS_INIMIGO_10{
		@Override
		public void acao(Cartas carta, Jogador jogador) {
			Campeao campeao;ImageIcon icon;
			try {
				campeao = (Campeao) carta;
				if(jogador.getVidaNexus() <= 10 && campeao.getPodeUpar()) {
					icon = new ImageIcon("imagens\\"+campeao.getNome().toUpperCase()+"-m.png");
					JOptionPane.showOptionDialog(null,""+carta.getNome(),"Level up",JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,icon,null,null );
					campeao.setVida(campeao.getVida() + 1);
					campeao.setPoder(campeao.getPoder() + 1);
					campeao.setPodeUpar(false);
				}
			} catch (ClassCastException e) {
				throw new ClassCastException("Carta não pertence a classe campeao");
			}
		}
	},
	/**
	 * Metodo responsavel por verificar se a carta Leblanc
	 * cumpre os requisitos para upar (level up), ou seja,
	 * se getDanoTotal() e getPodeUpar() satisfazem as condições
	 * @author Richard Matias 
	 */
	CAUSAR_15_DANO{
		@Override
		public void acao(Cartas carta, Jogador jogador) {
			Campeao campeao;ImageIcon icon;
			try {
				campeao = (Campeao) carta;
				if(jogador.getDanoTotal() >= 15 && campeao.getPodeUpar()) {
					icon = new ImageIcon("imagens\\"+campeao.getNome().toUpperCase()+"-m.png");
					JOptionPane.showOptionDialog(null,""+carta.getNome(),"Level up",JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,icon,null,null );
					campeao.setVida(campeao.getVida() + 1);
					campeao.setPoder(campeao.getPoder() + 1);
					campeao.setPodeUpar(false);
				}
			} catch (ClassCastException e) {
				throw new ClassCastException("Carta não pertence a classe campeao");
			}
		}
	},	
	/**
	 * Metodo responsavel por verificar se a carta Swain
	 * cumpre os requisitos para upar (level up), ou seja,
	 * se getDanoTotal() e getPodeUpar() satisfazem as condições
	 * @author Richard Matias 
	 */
	DOZE_DANO_FORA_COMBATE{
		@Override
		public void acao(Cartas carta, Jogador jogador) {
			Campeao campeao;ImageIcon icon;
			try {
				campeao = (Campeao) carta;
				if(jogador.getDanoTotal() >= 12 && campeao.getPodeUpar()) {
					icon = new ImageIcon("imagens\\"+campeao.getNome().toUpperCase()+"-m.png");
					JOptionPane.showOptionDialog(null,""+carta.getNome(),"Level up",JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,icon,null,null );
					campeao.setVida(campeao.getVida() + 1);
					campeao.setPoder(campeao.getPoder() + 1);
					campeao.setPodeUpar(false);
				}
			} catch (ClassCastException e) {
				throw new ClassCastException("Carta não pertence a classe campeao");
			}
		}
	},
	/**
	 * Metodo responsavel por verificar se a carta Vladimir
	 * cumpre os requisitos para upar (level up), ou seja,
	 * se getQtdAliadosSobreviveram() e getPodeUpar() satisfazem as condições
	 * @author Richard Matias 
	 */
	CINCO_TIME_SOBREVIVERAM{
		@Override
		public void acao(Cartas carta, Jogador jogador) {
			Campeao campeao;ImageIcon icon;
			try {
				campeao = (Campeao) carta;
				if(jogador.getQtdAliadosSobreviveram() >= 5 && campeao.getPodeUpar()) {
					icon = new ImageIcon("imagens\\"+campeao.getNome().toUpperCase()+"-m.png");
					JOptionPane.showOptionDialog(null,""+carta.getNome(),"Level up",JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,icon,null,null );
					campeao.setVida(campeao.getVida() + 1);
					campeao.setPoder(campeao.getPoder() + 1);
					campeao.setPodeUpar(false);
				}
			} catch (ClassCastException e) {
				throw new ClassCastException("Carta não pertence a classe campeao");
			}
		}
	},
	/**
	 * Metodo responsavel por verificar se a carta Ezreal
	 * cumpre os requisitos para upar (level up), ou seja,
	 * se getQtdXAliadosAtacaram() e getPodeUpar() satisfazem as condições
	 * @author Richard Matias 
	 */
	ATACAR_INIMIGO_6_VEZES{
		@Override
		public void acao(Cartas carta, Jogador jogador) {
			Campeao campeao;ImageIcon icon;
			try {
				campeao = (Campeao) carta;
				if(jogador.getQtdXAliadosAtacaram() >= 6 && campeao.getPodeUpar()) {
					icon = new ImageIcon("imagens\\"+campeao.getNome().toUpperCase()+"-m.png");
					JOptionPane.showOptionDialog(null,""+carta.getNome(),"Level up",JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,icon,null,null );
					campeao.setVida(campeao.getVida() + 1);
					campeao.setPoder(campeao.getPoder() + 1);
					campeao.setPodeUpar(false);
				}
			} catch (ClassCastException e) {
				throw new ClassCastException("Carta não pertence a classe campeao");
			}
		}
	},
	/**
	 * Metodo responsavel por verificar se a carta Jinx
	 * cumpre os requisitos para upar (level up), ou seja,
	 * se getCartasMao().isEmpty() e getPodeUpar() satisfazem as condições
	 * @author Richard Matias 
	 */
	MAO_VAZIA{
		@Override
		public void acao(Cartas carta, Jogador jogador) {
			Campeao campeao; ImageIcon icon;
			try {
				campeao = (Campeao) carta;
				if(jogador.getCartasMao().isEmpty() && campeao.getPodeUpar()) {
					icon = new ImageIcon("imagens\\"+campeao.getNome().toUpperCase()+"-m.png");
					JOptionPane.showOptionDialog(null,""+carta.getNome(),"Level up",JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,icon,null,null );
					campeao.setVida(campeao.getVida() + 1);
					campeao.setPoder(campeao.getPoder() + 1);
					campeao.setPodeUpar(false);
				}
			} catch (ClassCastException e) {
				throw new ClassCastException("Carta não pertence a classe campeao");
			}
		}
	},
	/**
	 * Metodo responsavel por verificar se a carta Sivir, Renekton, Nasus, Vi, 
	 * cumpre os requisitos para upar (level up), ou seja,
	 * se getDanoTotal() e getPodeUpar() satisfazem as condições
	 * @author Richard Matias 
	 */
	CAUSAR_10_DANO{
		@Override
		public void acao(Cartas carta, Jogador jogador) {
			Campeao campeao; ImageIcon icon;
			try {
				campeao = (Campeao) carta;
				if(jogador.getDanoTotal() >= 10 && campeao.getPodeUpar()) {
					icon = new ImageIcon("imagens\\"+campeao.getNome().toUpperCase()+"-m.png");
					JOptionPane.showOptionDialog(null,""+carta.getNome(),"Level up",JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,icon,null,null );
					campeao.setVida(campeao.getVida() + 1);
					campeao.setPoder(campeao.getPoder() + 1);
					campeao.setPodeUpar(false);
				}
			} catch (ClassCastException e) {
				throw new ClassCastException("Carta não pertence a classe campeao");
			}
		}
	},
	/**
	 * Metodo responsavel por verificar se a carta Aurelion Sol
	 * cumpre os requisitos para upar (level up), ou seja,
	 * se poder e getPodeUpar() satisfazem as condições
	 * @author Richard Matias 
	 */
	PODER_25_TOTAL{
		@Override
		public void acao(Cartas carta, Jogador jogador) {
			Campeao campeao;  ImageIcon icon; int poder = 0;
			try {
				campeao = (Campeao) carta;
				if(campeao.getPodeUpar()) {
					for(Cartas cartas : jogador.getEvocadas()) {
						poder += cartas.getPoder();
						if(poder >= 25) {
							icon = new ImageIcon("imagens\\"+campeao.getNome().toUpperCase()+"-m.png");
							JOptionPane.showOptionDialog(null,""+carta.getNome(),"Level up",JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,icon,null,null );
							carta.setVida(carta.getVida() + 1 );
							carta.setPoder(carta.getPoder() + 1);
							campeao.setPodeUpar(false);
							break;
						}
					}
				}
			} catch (ClassCastException e) {
				throw new ClassCastException("Carta não pertence a classe campeao");
			}
		}
	},
	/**
	 * Metodo responsavel por verificar se a carta Malphite
	 * cumpre os requisitos para upar (level up), ou seja,
	 * se getManaGastaTotal() e getPodeUpar() satisfazem as condições
	 * @author Richard Matias 
	 */
	CARTA_10_MANA{
		@Override
		public void acao(Cartas carta, Jogador jogador) {
			Campeao campeao; ImageIcon icon;
			try {
				campeao = (Campeao) carta;
				if(jogador.getManaGastaTotal() >= 10 && campeao.getPodeUpar()) {
					icon = new ImageIcon("imagens\\"+campeao.getNome().toUpperCase()+"-m.png");
					JOptionPane.showOptionDialog(null,""+carta.getNome(),"Level up",JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,icon,null,null );
					campeao.setVida(campeao.getVida() + 1);
					campeao.setPoder(campeao.getPoder() + 1);
					campeao.setPodeUpar(false);
				}
			} catch (ClassCastException e) {
				throw new ClassCastException("Carta não pertence a classe campeao");
			}
		}
	},
	/**
	 * Metodo responsavel por verificar se a carta Zoe
	 * cumpre os requisitos para upar (level up), ou seja,
	 * se getQtdXAliadosAtacaram() e getPodeUpar() satisfazem as condições
	 * @author Richard Matias 
	 */
	ATACAR_INIMIGO_10_VEZES{
		@Override
		public void acao(Cartas carta, Jogador jogador) {
			Campeao campeao;ImageIcon icon;
			try {
				campeao = (Campeao) carta;
				if(jogador.getQtdXAliadosAtacaram() >= 10 && campeao.getPodeUpar()) {
					icon = new ImageIcon("imagens\\"+campeao.getNome().toUpperCase()+"-m.png");
					JOptionPane.showOptionDialog(null,""+carta.getNome(),"Level up",JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,icon,null,null );
					campeao.setVida(campeao.getVida() + 1);
					campeao.setPoder(campeao.getPoder() + 1);
					campeao.setPodeUpar(false);
				}
			} catch (ClassCastException e) {
				throw new ClassCastException("Carta não pertence a classe campeao");
			}
		}
	},
	/**
	 * Metodo responsavel por verificar se a carta Riven
	 * cumpre os requisitos para upar (level up), ou seja,
	 * se getBladeOfExile() e getPodeUpar() satisfazem as condições
	 * @author Richard Matias 
	 */
	LAMINA_DO_EXILIO{
		@Override
		public void acao(Cartas carta, Jogador jogador) {
			Campeao campeao;ImageIcon icon;
			try {
				campeao = (Campeao) carta;
				if(campeao.getBladeOfExile() && campeao.getPodeUpar()) {
					icon = new ImageIcon("imagens\\"+campeao.getNome().toUpperCase()+"-m.png");
					JOptionPane.showOptionDialog(null,""+carta.getNome(),"Level up",JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,icon,null,null );
					campeao.setVida(campeao.getVida() + 1);
					campeao.setPoder(campeao.getPoder() + 1);
					campeao.setPodeUpar(false);
				}
			} catch (ClassCastException e) {
				throw new ClassCastException("Carta não pertence a classe campeao");
			}
		}
	},
	/**
	 * Metodo responsavel por verificar se a carta Draven
	 * cumpre os requisitos para upar (level up), ou seja,
	 * getPodeUpar() satisfazem as condições
	 * @author Richard Matias 
	 */
	GOLPEAR_1_REV_MACHADO{//n sei como fazer ainda
		@Override
		public void acao(Cartas carta, Jogador jogador) {
			Campeao campeao;ImageIcon icon;
			try {
				campeao = (Campeao) carta;
				if(campeao.getRevMachado() && campeao.getPodeUpar()) {
					icon = new ImageIcon("imagens\\"+campeao.getNome().toUpperCase()+"-m.png");
					JOptionPane.showOptionDialog(null,""+carta.getNome(),"Level up",JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,icon,null,null );
					campeao.setVida(campeao.getVida() + 1);
					campeao.setPoder(campeao.getPoder() + 1);
					campeao.setPodeUpar(false);
				}
			} catch (ClassCastException e) {
				throw new ClassCastException("Carta não pertence a classe campeao");
			}
		}
	},
	DOZE_PODER_TORRE{//n sei como fazer ainda
		@Override
		public void acao(Cartas carta, Jogador jogador) {}
	},
	COGUMELOS_15{//n sei como fazer ainda
		@Override
		public void acao(Cartas carta, Jogador jogador) {}
	},
	CURAR_ALIADOS_4_VEZES{//n sei como fazer ainda
		@Override
		public void acao(Cartas carta, Jogador jogador) {}
	},
	VI_AUXILIAR_ALIADOS_7VEZES{//n sei como fazer ainda
		@Override
		public void acao(Cartas carta, Jogador jogador) {}
	},
	ANOITECER_4_VEZES{//n sei como fazer ainda
		@Override
		public void acao(Cartas carta, Jogador jogador) {}
	},
	/**
	 * Metodo responsavel por verificar se a carta Leaona
	 * cumpre os requisitos para upar (level up), ou seja,
	 * se getAmanhecer() e getPodeUpar() satisfazem as condições
	 * @author Richard Matias 
	 */
	AMANHECER_4_VEZES{//n sei como fazer ainda
		@Override
		public void acao(Cartas carta, Jogador jogador) {
			Campeao campeao;ImageIcon icon;
			try {
				campeao = (Campeao) carta;
				if(campeao.getAmanhecer() >= 4 && campeao.getPodeUpar()) {
					icon = new ImageIcon("imagens\\"+campeao.getNome().toUpperCase()+"-m.png");
					JOptionPane.showOptionDialog(null,""+carta.getNome(),"Level up",JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,icon,null,null );
					campeao.setVida(campeao.getVida() + 1);
					campeao.setPoder(campeao.getPoder() + 1);
					campeao.setPodeUpar(false);
				}
			} catch (ClassCastException e) {
				throw new ClassCastException("Carta não pertence a classe campeao");
			}
		}
	},
	JOGAR_7_CARTAS_CRIADAS{//n sei como fazer ainda
		@Override
		public void acao(Cartas carta, Jogador jogador) {}
	},
	/**
	 * Metodo responsavel por verificar se a carta Azir
	 * cumpre os requisitos para upar (level up), ou seja,
	 * se getQtdCartasCompradas() e getPodeUpar() satisfazem as condições
	 * @author Richard Matias 
	 */
	INVOCAR_10_UNIDADES{
		@Override
		public void acao(Cartas carta, Jogador jogador) {
			Campeao campeao;ImageIcon icon;
			try {
				campeao = (Campeao) carta;
				if(jogador.getQtdCartasCompradas() >= 10 && campeao.getPodeUpar()) {
					icon = new ImageIcon("imagens\\"+campeao.getNome().toUpperCase()+"-m.png");
					JOptionPane.showOptionDialog(null,""+carta.getNome(),"Level up",JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,icon,null,null );
					campeao.setVida(campeao.getVida() + 1);
					campeao.setPoder(campeao.getPoder() + 1);
					campeao.setPodeUpar(false);
				}
			} catch (ClassCastException e) {
				throw new ClassCastException("Carta não pertence a classe campeao");
			}
		}
	},
	CRIAR_5_LENDMARKS{//n sei como fazer ainda
		@Override
		public void acao(Cartas carta, Jogador jogador) {}
	},
	DESTRUIR_2_BOMBAS{//n sei como fazer ainda
		@Override
		public void acao(Cartas carta, Jogador jogador) {}
	},
	ARMA_LUA_4_VEZES{//n sei como fazer ainda
		@Override
		public void acao(Cartas carta, Jogador jogador) {}
	},
	AUZILIAR_3_VEZES{//n sei como fazer ainda
		@Override
		public void acao(Cartas carta, Jogador jogador) {}
	},
	VI_ALIADOS_BARREIRA_4{//n sei como fazer ainda
		@Override
		public void acao(Cartas carta, Jogador jogador) {}
	},
	/**
	 * Metodo responsavel por verificar se a carta Yasuo
	 * cumpre os requisitos para upar (level up), ou seja,
	 * se getAtordoar() e getPodeUpar() satisfazem as condições
	 * @author Richard Matias 
	 */
	ATORDOAR{//n sei como fazer ainda
		@Override
		public void acao(Cartas carta, Jogador jogador) {
			Campeao campeao; ImageIcon icon;
			try {
				campeao = (Campeao) carta;
				if(campeao.getAtordoar() && campeao.getPodeUpar()) {
					icon = new ImageIcon("imagens\\"+campeao.getNome().toUpperCase()+"-m.png");
					JOptionPane.showOptionDialog(null,""+carta.getNome(),"Level up",JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,icon,null,null );
					campeao.setVida(campeao.getVida() + 1);
					campeao.setPoder(campeao.getPoder() + 1);
					campeao.setPodeUpar(false);
				}
			} catch (ClassCastException e) {
				throw new ClassCastException("Carta não pertence a classe campeao");
			}
		}
	},
	/**
	 * Metodo responsavel por verificar se a carta Zed
	 * cumpre os requisitos para upar (level up), ou seja,
	 * getPodeUpar() satisfazem as condições
	 * @author Richard Matias 
	 */
	ATACAR_NEXUS{//n sei como fazer ainda ZED
		@Override
		public void acao(Cartas carta, Jogador jogador) {
			Campeao campeao; ImageIcon icon;
			try {
				campeao = (Campeao) carta;
				if(campeao.getZedPodeUpar() && campeao.getPodeUpar()) {
					icon = new ImageIcon("imagens\\"+campeao.getNome().toUpperCase()+"-m.png");
					JOptionPane.showOptionDialog(null,""+carta.getNome(),"Level up",JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,icon,null,null );
					campeao.setVida(campeao.getVida() + 1);
					campeao.setPoder(campeao.getPoder() + 1);
					campeao.setPodeUpar(false);
				}
			} catch (ClassCastException e) {
				throw new ClassCastException("Carta não pertence a classe campeao");
			}
		}
	},
	/**
	 * Metodo responsavel por verificar se a carta Anivia
	 * cumpre os requisitos para upar (level up), ou seja,
	 * se getMana() e getPodeUpar() satisfazem as condições
	 * @author Richard Matias 
	 */
	ILUMINADO{//nao sei como fazer ainda
		@Override
		public void acao(Cartas carta, Jogador jogador) {
			Campeao campeao; ImageIcon icon;
			try {
				campeao = (Campeao) carta;
				if(jogador.getMana() >= 10 && campeao.getPodeUpar()) {
					icon = new ImageIcon("imagens\\"+campeao.getNome().toUpperCase()+"-m.png");
					JOptionPane.showOptionDialog(null,""+carta.getNome(),"Level up",JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,icon,null,null );
					campeao.setVida(campeao.getVida() + 1);
					campeao.setPoder(campeao.getPoder() + 1);
					campeao.setPodeUpar(false);
				}
			} catch (ClassCastException e) {
				throw new ClassCastException("Carta não pertence a classe campeao");
			}
		}
	},
	/**
	 * Metodo responsavel por verificar se a carta Ashe
	 * cumpre os requisitos para upar (level up), ou seja,
	 * se getCongelar() e getPodeUpar() satisfazem as condições
	 * @author Richard Matias 
	 */
	CONGELAR_5_INIMIGOS{//n sei como fazer ainda
		@Override
		public void acao(Cartas carta, Jogador jogador) {
			Campeao campeao; ImageIcon icon;
			try {
				campeao = (Campeao) carta;
				if(campeao.getCongelar() >= 5 && campeao.getPodeUpar()) {
					icon = new ImageIcon("imagens\\"+campeao.getNome().toUpperCase()+"-m.png");
					JOptionPane.showOptionDialog(null,""+carta.getNome(),"Level up",JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,icon,null,null );
					campeao.setVida(campeao.getVida() + 1);
					campeao.setPoder(campeao.getPoder() + 1);
					campeao.setPodeUpar(false);
				}
			} catch (ClassCastException e) {
				throw new ClassCastException("Carta não pertence a classe campeao");
			}
		}
	},
	PROFUNDEZAS{//ainda n sei como fazer
		@Override
		public void acao(Cartas carta, Jogador jogador) {
			
		}
	},
	CAPTURAR_3_UNIDADES{// ainda nao sei como fazer
		@Override
		public void acao(Cartas carta, Jogador jogador) {}
	},
	/**
	 * Metodo responsavel por verificar se a carta Trundle
	 * cumpre os requisitos para upar (level up), ou seja,
	 * se getPilarGelo() e getPodeUpar() satisfazem as condições
	 * @author Richard Matias 
	 */
	PILAR_DE_GELO{//n sei como fazer ainda
		@Override
		public void acao(Cartas carta, Jogador jogador) {
			Campeao campeao; ImageIcon icon;
			try {
				campeao = (Campeao) carta;
				if(campeao.getPilarGelo() && campeao.getPodeUpar()) {
					icon = new ImageIcon("imagens\\"+campeao.getNome().toUpperCase()+"-m.png");
					JOptionPane.showOptionDialog(null,""+carta.getNome(),"Level up",JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,icon,null,null );
					campeao.setVida(campeao.getVida() + 1);
					campeao.setPoder(campeao.getPoder() + 1);
					campeao.setPodeUpar(false);
				}
			} catch (ClassCastException e) {
				throw new ClassCastException("Carta não pertence a classe campeao");
			}
		}
	},
	TEM_3_ARANHA{// ainda n sei como fazer
		@Override
		public void acao(Cartas carta, Jogador jogador) {}
	},
	VER_INIMIGOS_MARCADOS_MORREREM{//n sei como fazer ainda
		@Override
		public void acao(Cartas carta, Jogador jogador) {}
	},
	UNIDADES_MORRERAM{//n sei como fazer ainda
		@Override
		public void acao(Cartas carta, Jogador jogador) {}
	},
	/**
	 * Metodo responsavel por verificar se a carta Diana
	 * cumpre os requisitos para upar (level up), ou seja,
	 * se getAnoitecer() e getPodeUpar() satisfazem as condições
	 * @author Richard Matias 
	 */
	ANOITECER{//n sei como fazer ainda
		@Override
		public void acao(Cartas carta, Jogador jogador) {
			Campeao campeao; ImageIcon icon;
			try {
				campeao = (Campeao) carta;
				if(campeao.getAnoitecer() >= 5 && campeao.getPodeUpar()) {
					icon = new ImageIcon("imagens\\"+campeao.getNome().toUpperCase()+"-m.png");
					JOptionPane.showOptionDialog(null,""+carta.getNome(),"Level up",JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,icon,null,null );
					campeao.setVida(campeao.getVida() + 1);
					campeao.setPoder(campeao.getPoder() + 1);
					campeao.setPodeUpar(false);
				}
			} catch (ClassCastException e) {
				throw new ClassCastException("Carta não pertence a classe campeao");
			}
		}
	},
}
