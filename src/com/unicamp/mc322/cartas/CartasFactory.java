package com.unicamp.mc322.cartas;

/**
 * classe CartasFactory, ira ser responsavel por criar novas instancias de cartas, e retornar 
 * o seu tipo
 * utilizado na classe Jogador nos metodos criarDeckAleatorio() e criarDeckDesejado()
 * também utilizado no Enum Efeitos.java
 * @author Richard Matias
 */

public class CartasFactory {
	/**
	 * metodo responsavel por criar as instancias de cada nova carta de acordo com o seu tipo
	 * CAMPEAO / SEGUIDORES / FEITICO 
	 * utilizado na classe Jogador nos metodos criarDeckAleatorio() e criarDeckDesejado()
	 * também utilizado no Enum Efeitos.java
	 * @return Cartas - preenchido com todas as informaoces da nova carta criada
	 * @author Richard Matias
	 */
	public static Cartas criarCarta(TipoCarta tipoCarta, String [] itens) {
		if(TipoCarta.CAMPEAO.equals(tipoCarta)) {
			return new Campeao( itens[2].split(":")[1],  Integer.parseInt(itens[3].split(":")[1]),
					            Integer.parseInt(itens[4].split(":")[1]),  Integer.parseInt(itens[5].split(":")[1]),
					            Integer.parseInt(itens[1].split(":")[1]), Efeitos.valueOf(itens[7].split(":")[1]), 
					            Tracos.valueOf(itens[8].split(":")[1]), Nivel.valueOf(itens[6].split(":")[1] ) );
		}else if(TipoCarta.SEGUIDORES.equals(tipoCarta)) {
			return new Seguidores( itens[2].split(":")[1], Efeitos.valueOf(itens[6].split(":")[1]) , Integer.parseInt(itens[3].split(":")[1]),
			           		       Integer.parseInt(itens[4].split(":")[1]),  Integer.parseInt(itens[5].split(":")[1]),
			           		       Integer.parseInt(itens[1].split(":")[1])  );
		}else if(TipoCarta.FEITICOS.equals(tipoCarta)) {
			return new Feiticos(itens[2].split(":")[1], Integer.parseInt(itens[3].split(":")[1]),Efeitos.valueOf(itens[4].split(":")[1]) ,Tracos.valueOf(itens[5].split(":")[1]) );
		}
		throw new IllegalArgumentException("Nao existe esta carta");
	} 
}
 
