package br.com.edu.dijkstra;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class Dijkstra {

	private final Map<String, Double> costs = new HashMap<>();

	private final Map<String, String> parents = new HashMap<>();

	private Map<String, Map<String, Double>> graph;

	private Set<String> processed = new HashSet<>();

	public Dijkstra(final Map<String, Map<String, Double>> grafo) {
		super();
		this.graph = grafo;

	}

	public Response menorCaminho(final String inicio, final String fim) {

		// custo de tudo inicia com infinito
		graph.keySet().forEach(key -> {
			if (!key.equals(inicio)) {
				costs.put(key, Double.POSITIVE_INFINITY);
			}
		});

		graph.keySet().forEach(key -> {
			if (!key.equals(inicio)) {
				parents.put(key, inicio);
			}
		});

		costs.putAll(graph.get(inicio));

		var noAtual = vizinhoMenorCusto();

		while (noAtual != null) {
			for (final var vizinho : graph.get(noAtual).keySet()) {
				var custoVizinho = costs.get(vizinho); // 6

				var custoNoAtual = costs.getOrDefault(noAtual, Double.POSITIVE_INFINITY); // 2
				double valorTotal;
				if (custoNoAtual == Double.POSITIVE_INFINITY) {
					valorTotal = graph.get(noAtual).get(vizinho);
				} else {
					valorTotal = graph.get(noAtual).get(vizinho) + custoNoAtual;
				}

				if (valorTotal < custoVizinho) {
					costs.put(vizinho, valorTotal);
					parents.put(vizinho, noAtual);
				}
			}
			processed.add(noAtual);
			noAtual = vizinhoMenorCusto();
		}

		System.out.println(costs);
		System.out.println(parents);

		return new Response(costs.get(fim), null);

	}

	private String vizinhoMenorCusto() {
		Double lowestCost = Double.POSITIVE_INFINITY;
		String lowestCostNode = null;

		for (Map.Entry<String, Double> node : costs.entrySet()) {
			Double cost = node.getValue();
			if (cost < lowestCost && !processed.contains(node.getKey())) {
				lowestCost = cost;
				lowestCostNode = node.getKey();
			}
		}

		return lowestCostNode;
	}

	public static void main(String[] args) {
		test1();
		test2();

	}

	private static void test1() {
		final Map<String, Map<String, Double>> grafo = new LinkedHashMap<>();
		grafo.put("INICIO", Map.of("A", 6d, "B", 2d));
		grafo.put("A", Map.of("FIM", 1d));
		grafo.put("B", Map.of("A", 3d, "FIM", 5d));
		grafo.put("FIM", Map.of());

		final var v2 = new Dijkstra(grafo);
		final var response = v2.menorCaminho("INICIO", "FIM");

		System.out.println(response);
	}

	private static void test2() {
		final Map<String, Map<String, Double>> grafo = new LinkedHashMap<>();
		grafo.put("INICIO", Map.of("A", 10d));
		grafo.put("A", Map.of("B", 20d));
		grafo.put("B", Map.of("C", 1d, "FIM", 30d));
		grafo.put("C", Map.of("A", 1d));
		grafo.put("D", Map.of("FIM", 1d));
		grafo.put("FIM", Map.of());

		final var v2 = new Dijkstra(grafo);
		final var response = v2.menorCaminho("INICIO", "FIM");

		System.out.println(response);
	}

}
