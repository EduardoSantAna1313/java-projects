package br.com.edu.calculo.pm;

import java.io.IOException;
import java.nio.file.Path;

import br.com.edu.calculo.pm.business.CalculadoraNotas;

/**
 * Calcular notas de corretagem.
 *
 * @author Eduardo
 */
public class Main {

	/**
	 * Diretório das notas.
	 */
	private static final Path NOTAS = Path.of("");

	public static void main(final String[] args) throws IOException {

		final var calculadora = new CalculadoraNotas(NOTAS);
		final var map = calculadora.calcular();

		map.forEach((k, v) -> System.out.println(v + "\n\n\n"));
	}

}
