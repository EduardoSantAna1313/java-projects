package br.com.edu.dijkstra;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class DijkstraV2 {

	static int menorCaminho(int matCid[][], int origem, int destino) {

		int qtde_cidades = matCid.length;

		Map<Integer, Integer> pais = new HashMap<>();

		int noAtual, pesoAtual;
		var noAnterior = new int[qtde_cidades];
		var pesos = new int[qtde_cidades];
		var caminho = new boolean[qtde_cidades];

		for (int i = 0; i < qtde_cidades; i++) {
			noAnterior[i] = 0;
			pesos[i] = 0;
			caminho[i] = false;
		}

		noAtual = origem;
		pesoAtual = 0;
		while (noAtual != destino) {
			caminho[noAtual] = true;
			for (int j = 0; j < qtde_cidades; j++) {
				if (matCid[noAtual][j] != 0 && (matCid[noAtual][j] + pesoAtual < pesos[j] || pesos[j] == 0)) {
					pesos[j] = matCid[noAtual][j] + pesoAtual;
					pais.put(noAnterior[j], noAtual);
					noAnterior[j] = noAtual;
				}
			}
			for (int j = 0; j < qtde_cidades; j++) {
				if (caminho[j] == false && pesos[j] != 0 && (pesos[j] < pesos[noAtual] || caminho[noAtual] == true)) {
					noAtual = j;
					pesoAtual = pesos[noAtual];
				}
			}
		}

		System.out.println("Pesos: " + Arrays.toString(pesos));
		System.out.println("pais: " + pais);

		return pesoAtual;
	}

	public static void main(String[] args) {
		test01();
		test02();
	}

	private static void test01() {
		int[][] matriz = { { 0, 6, 2, 0 }, { 0, 0, 0, 1 }, { 0, 3, 0, 5 }, { 0, 0, 0, 0 } };

		int origem = 0;
		int destino = 3;
		int dist = menorCaminho(matriz, origem, destino);
		System.out.println("Menor caminho = " + dist);
	}

	private static void test02() {
		int[][] matriz = { { 0, 10, 0, 0, 0 }, { 0, 0, 20, 0, 0 }, { 0, 0, 0, 1, 30 }, { 0, 1, 0, 0, 0 },
				{ 0, 0, 0, 0, 0 } };

		int origem = 0;
		int destino = 4;
		int dist = menorCaminho(matriz, origem, destino);
		System.out.println("Menor caminho = " + dist);
	}

}
