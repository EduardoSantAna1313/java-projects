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
	private static final Path NOTAS = Path.of("/home/edusilva/Documents/notas corretagem inter");

	public static void main(final String[] args) throws IOException {

		final var calculadora = new CalculadoraNotas(NOTAS);
		final var map = calculadora.calcular();

		// map.forEach((k, v) -> System.out.println(v + "\n\n\n"));
		map.forEach((k, v) -> {
			System.out.println(v.getTicket());
			System.out.println("Data\tQtde\tValor\tQtde Total\tPM");
			v.getOperacoes().forEach(op -> {
				System.out.println(
						op.getData() + "\t" + op.getQtde() + "\t" + op.getValor().toString().replace(".", ","));
			});
		});
	}

}
