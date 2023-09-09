package br.com.edu.dijkstra;

import java.io.IOException;
import java.nio.file.Path;

public class Main {
	public static void main(String[] args) throws IOException {
		final var grafo = new Grafo(Path.of("src/test/resources/sample02.txt"));

		final var dijkstra = new Dijkstra(grafo);
		final var result = dijkstra.menorCaminho(0, 5);

		System.out.println(result);
	}
}
