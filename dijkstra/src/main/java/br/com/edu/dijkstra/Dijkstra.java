package br.com.edu.dijkstra;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.plaf.synth.SynthOptionPaneUI;

public class DijkstraV2 {

	private final Map<String, Double> custos = new HashMap<>();

	private final Map<String, String> pais = new HashMap<>();

	private Map<String, Map<String, Double>> grafo;

	public DijkstraV2(final Map<String, Map<String, Double>> grafo) {
		super();
		this.grafo = grafo;
	}

	public Response menorCaminho(final String inicio, final String fim) {

		var noAtual = menorCusto(grafo.get(inicio));
		System.out.println("Noatual =  " + noAtual);

		for (var entry : grafo.entrySet()) {
			System.out.println(entry.getKey());
			var arestas = grafo.getOrDefault(entry.getKey(), null);

			var menorCusto = menorCusto(arestas);
			if (menorCusto == null) {
				continue;
			}

			System.out.println("Menor custo " + entry.getKey() + " é " + menorCusto);
			var oldValue = custos.get(menorCusto);
			var currentValue = arestas.get(menorCusto);
			if (oldValue == null || currentValue < oldValue) {
				var custoNode = custos.get(entry.getKey());

				if (custoNode != null) {
					currentValue += custoNode;
				}

				pais.put(menorCusto, entry.getKey());
				custos.put(menorCusto, currentValue);
			}

		}
		System.out.println("Custos: " + custos);
		System.out.println(pais);
		return null;

	}

	private String menorCusto(Map<String, Double> arestas) {

		String key = null;
		Double menor = Double.MAX_VALUE;
		for (var e : arestas.entrySet()) {
			if (e.getValue() < menor) {
				menor = e.getValue();
				key = e.getKey();
			}
		}

		return key;
	}

	public static void main(String[] args) {
		final Map<String, Map<String, Double>> grafo = new LinkedHashMap<>();
		grafo.put("INICIO", Map.of("A", 6d, "B", 2d));
		grafo.put("A", Map.of("FIM", 1d));
		grafo.put("B", Map.of("A", 3d, "FIM", 5d));
		grafo.put("FIM", Map.of());

		final var v2 = new DijkstraV2(grafo);
		final var response = v2.menorCaminho("INICIO", "FIM");

		System.out.println(response);

	}
}
