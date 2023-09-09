package br.com.edu.dijkstra;

import java.util.LinkedHashMap;
import java.util.Map;

public class Main {

	public static void main(String[] args) {
		final Map<String, Map<String, Double>> grafo = new LinkedHashMap<>();
		grafo.put("INICIO", Map.of("A", 6d, "B", 2d));
		grafo.put("A", Map.of("FIM", 1d));
		grafo.put("B", Map.of("A", 3d, "FIM", 5d));
		grafo.put("FIM", Map.of());

		final var v2 = new Dijkstra(grafo);
		final var response = v2.menorCaminho("INICIO", "FIM");

		System.out.println(response);

	}
}
