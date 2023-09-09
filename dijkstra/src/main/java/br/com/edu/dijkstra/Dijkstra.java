package br.com.edu.dijkstra;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Dijkstra {

	private final Map<String, Double> costs = new HashMap<>();

	private final Map<String, String> parents = new HashMap<>();

	private Graph graph;

	private Set<String> processed = new HashSet<>();

	public Dijkstra(Graph graph) {
		super();
		this.graph = graph;

	}

	public Response menorCaminho(final String inicio, final String fim) {

		var nodes = graph.nodes();

		// custo de tudo inicia com infinito
		nodes.keySet().forEach(key -> {
			if (!key.equals(inicio)) {
				costs.put(key, Double.POSITIVE_INFINITY);
			}
		});

		nodes.keySet().forEach(key -> {
			if (!key.equals(inicio)) {
				parents.put(key, inicio);
			}
		});

		costs.putAll(nodes.get(inicio));

		var noAtual = vizinhoMenorCusto();

		while (noAtual != null) {
			for (final var vizinho : nodes.get(noAtual).keySet()) {
				var custoVizinho = costs.get(vizinho); // 6

				var custoNoAtual = costs.getOrDefault(noAtual, Double.POSITIVE_INFINITY); // 2
				double valorTotal;
				if (custoNoAtual == Double.POSITIVE_INFINITY) {
					valorTotal = nodes.get(noAtual).get(vizinho);
				} else {
					valorTotal = nodes.get(noAtual).get(vizinho) + custoNoAtual;
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

}
