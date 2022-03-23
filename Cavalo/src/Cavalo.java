/**
 * @author Eduardo
 */
public class Cavalo extends Thread {

	/**
	 * Integer[][] - tabuleiro.
	 */
	private final Integer[][] tabuleiro;

	/**
	 * Integer[][] - proxPosicao.
	 */
	private final Integer[][] proxPosicao = {
			{
					-2, -1 },
			{
					-1, -2 },
			{
					1, -2 },
			{
					2, -1 },
			{
					2, 1 },
			{
					1, 2 },
			{
					-1, 2 },
			{
					-2, 1 } };

	/**
	 * int - rodada.
	 */
	private int rodada = 0;

	/**
	 * int - x.
	 */
	private int x = 0;

	/**
	 * int - y.
	 */
	private int y = 0;

	/**
	 * Create a new instance of Cavalo
	 *
	 * @param x
	 * @param y
	 */
	public Cavalo(final int x, final int y) {
		tabuleiro = new Integer[8][8];
		this.x = x;
		this.y = y;
	}

	/**
	 * Roda o jogo.
	 */
	@Override
	public void run() {
		while (rodada < 65) {
			++rodada;
			final var validaPosicao = validaPosicao(x, y);
			if (validaPosicao) {
				tabuleiro[x][y] = rodada;
				printTabuleiro();
			}
			proxPosicao();
		}
		System.out.println("FIM");
	}

	/**
	 * Valida se a posicao selecionada é valida.
	 *
	 * @param x
	 * @param y
	 *
	 * @return
	 */
	private boolean validaPosicao(final int x, final int y) {
		return x >= 0 && x <= 7 && y >= 0 && y <= 7 && tabuleiro[x][y] == null;
	}

	/**
	 * Imprime tabuleiro.
	 */
	private void printTabuleiro() {
		System.out.println("Rodada " + rodada);
		for (var i = 0; i < 8; i++) {
			for (var j = 0; j < 8; j++) {
				System.out.print(String.format("%d\t", tabuleiro[i][j] == null ? 0 : tabuleiro[i][j]));
			}
			System.out.println();
		}
	}

	/**
	 * Seleciona a próxima posição.
	 */
	private void proxPosicao() {
		final var pesos = pesosProxPosicao();
		final var indice = menorPosicao(pesos);
		x += proxPosicao[indice][0];
		y += proxPosicao[indice][1];
	}

	/**
	 * Calcula os pesos para a proxima posição.
	 *
	 * @return
	 */
	private Integer[] pesosProxPosicao() {
		final Integer[] posicoes = {
				0, 0, 0, 0, 0, 0, 0, 0 };
		for (var i = 0; i < 8; i++) {
			x += proxPosicao[i][0];
			y += proxPosicao[i][1];
			if (validaPosicao(x, y)) {
				posicoes[i] = somaProxPosicao();
			}
			x -= proxPosicao[i][0];
			y -= proxPosicao[i][1];
		}
		return posicoes;
	}

	/**
	 * Calcula o menor peso.
	 *
	 * @param vetor
	 *
	 * @return
	 */
	private int menorPosicao(final Integer[] vetor) {
		var min = 7;
		var indice = 0;
		for (var i = 0; i < 8; i++) {
			if (vetor[i] < min && vetor[i] != 0) {
				min = vetor[i];
				indice = i;
			}
		}
		return indice;
	}

	/**
	 * Realiza a soma de pesos da posição.
	 *
	 * @return
	 */
	private int somaProxPosicao() {
		var soma = 0;
		for (var i = 0; i < 8; i++) {
			if (validaPosicao(x + proxPosicao[i][0], y + proxPosicao[i][1])) {
				soma++;
			}
		}
		return soma;
	}

}
