/**
 * 
 */
package br.com.edu.gameoflife.business;

import java.util.Random;

import br.com.edu.gameoflife.type.Resultado;

/**
 * @author eduardo
 *
 */
public class GameOfLifeBusiness {

	/**
	 * largua - int</br>
	 */
	private int largura;

	/**
	 * altura - int</br>
	 */
	private int altura;

	/**
	 * população - double</br>
	 */
	private double populacao;

	/**
	 * rodada - int</br>
	 */
	private int rodada;

	/**
	 * quantidade de células vivas.</br>
	 * qtdeVivo - int</br>
	 */
	private int qtdeVivo = 0;

	/**
	 * matriz</br>
	 * matriz - boolean[][]</br>
	 */
	private boolean[][] matriz;

	/**
	 * matriz auxiliar</br>
	 * matrizProxRodada - boolean[][]</br>
	 */
	private boolean[][] matrizProxRodada;

	/**
	 * 
	 * Create a new instance of Tabuleiro
	 * 
	 * @param pMatriz
	 */
	public GameOfLifeBusiness(final boolean[][] pMatriz) {
		this.matriz = pMatriz;
		this.largura = pMatriz.length;
		this.altura = pMatriz[0].length;
		this.qtdeVivo = countVivos();
	}

	/**
	 * Retorna a quantidade de células vivas.</br>
	 * 
	 * @return
	 */
	private int countVivos() {
		int vivos = 0;
		for (int i = 0; i < this.largura; i++) {
			for (int j = 0; j < this.altura; j++) {
				if (this.matriz[i][j]) {
					vivos++;
				}
			}
		}
		return vivos;
	}

	/**
	 * 
	 * Create a new instance of Tabuleiro
	 * 
	 * @param pLargura
	 * @param pAltura
	 * @param pPopulacao
	 */
	public GameOfLifeBusiness(final int pLargura, final int pAltura, final double pPopulacao) {
		this.largura = pLargura;
		this.altura = pAltura;
		this.populacao = pPopulacao;

		this.matriz = preencheMatriz(this.largura, this.altura, this.populacao);
	}

	/**
	 * Preenche a matriz.
	 * 
	 * @param pLargura
	 * @param pAltura
	 * @param pPopulacao
	 * @return
	 */
	private boolean[][] preencheMatriz(final int pLargura, final int pAltura, final double pPopulacao) {

		final boolean[][] matriz = new boolean[pLargura][pAltura];
		final Random rand = new Random();
		for (int i = 0; i < pLargura; i++) {
			for (int j = 0; j < pAltura; j++) {
				matriz[i][j] = rand.nextDouble() < pPopulacao;
				if (matriz[i][j]) {
					this.qtdeVivo++;
				}
			}
		}

		return matriz;
	}

	/**
	 * proxima rodada.
	 */
	public void proximaRodada() {
		rodada++;
		int vivos = 0;
		this.matrizProxRodada = new boolean[this.largura][this.altura];
		for (int i = 0; i < this.largura; i++) {
			for (int j = 0; j < this.altura; j++) {
				final int vizinhos = qtdeVizinhos(matriz, i, j);
				final Resultado tipo = getResultado(vizinhos);
				if (tipo.equals(Resultado.MANTEM)) {
					this.matrizProxRodada[i][j] = this.matriz[i][j];
				} else if (tipo.equals(Resultado.MORRE)) {
					this.matrizProxRodada[i][j] = false;
				} else {
					this.matrizProxRodada[i][j] = true;
				}

				if (this.matrizProxRodada[i][j]) {
					vivos++;
				}

			}
		}
		this.matriz = this.matrizProxRodada.clone();
		this.qtdeVivo = vivos;
	}

	/**
	 * Calcula resultado de acordo com o número de vizinhos.
	 * 
	 * @param matriz
	 * @param i
	 * @param j
	 * @return
	 */
	public Resultado getResultado(final int pVizinhos) {
		if (pVizinhos <= 1 || pVizinhos >= 4) {
			return Resultado.MORRE;
		} else if (pVizinhos == 2) {
			return Resultado.MANTEM;
		} else {
			return Resultado.PROGRIDE;
		}
	}

	/**
	 * Retorna a quantidade de vizinhos da célula.
	 * 
	 * @param pMatriz
	 * @param pX
	 * @param pY
	 * @return
	 */
	public int qtdeVizinhos(final boolean[][] pMatriz, final int pX, final int pY) {
		int qtde = 0;
		for (int x = -1; x <= 1; x++) {
			for (int y = -1; y <= 1; y++) {
				if ((x != 0 || y != 0) && x + pX >= 0 && y + pY >= 0 && x + pX < pMatriz.length
						&& y + pY < pMatriz[0].length && pMatriz[x + pX][y + pY]) {
					qtde++;
				}
			}
		}
		return qtde;
	}

	/**
	 * Retorna a rodada do jogo.
	 * 
	 * @return
	 */
	public synchronized int getRodada() {
		return rodada;
	}

	/**
	 * @return the matriz.
	 */
	public boolean[][] getMatriz() {
		return matriz;
	}

	/**
	 * @return the tabuleiroProx
	 */
	public boolean[][] getTabuleiroProx() {
		return matrizProxRodada;
	}

	/**
	 * Retorna a quantidade de células vivas.
	 * 
	 * @return
	 */
	public int getQtdeVivo() {
		return this.qtdeVivo;
	}

}
