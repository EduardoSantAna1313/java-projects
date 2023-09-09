package br.com.edu.dijkstra;

import java.io.IOException;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.Map;

public class MainGenerateGraphs {
	public static void main(String[] args) throws IOException {
		v1();
		v2();
		v3();
	}

	private static void v1() throws IOException {
		final Map<String, Map<String, Double>> nodes = new LinkedHashMap<>();
		nodes.put("INICIO", Map.of("A", 6d, "B", 2d));
		nodes.put("A", Map.of("FIM", 1d));
		nodes.put("B", Map.of("A", 3d, "FIM", 5d));
		nodes.put("FIM", Map.of());

		Graph graph = new Graph(nodes);
		graph.save(Path.of("src/main/resources/v1.txt"));
	}

	private static void v2() throws IOException {
		final Map<String, Map<String, Double>> nodes = new LinkedHashMap<>();
		nodes.put("INICIO", Map.of("A", 5d, "B", 2d));
		nodes.put("A", Map.of("C", 4d, "D", 2d));
		nodes.put("B", Map.of("A", 8d, "D", 7d));
		nodes.put("C", Map.of("D", 6d, "FIM", 3d));
		nodes.put("D", Map.of("FIM", 1d));
		nodes.put("FIM", Map.of());

		Graph graph = new Graph(nodes);
		graph.save(Path.of("src/main/resources/v2.txt"));
	}

	private static void v3() throws IOException {
		final Map<String, Map<String, Double>> nodes = new LinkedHashMap<>();
		nodes.put("INICIO", Map.of("A", 10d));
		nodes.put("A", Map.of("B", 20d));
		nodes.put("B", Map.of("C", 1d, "FIM", 30d));
		nodes.put("C", Map.of("A", 1d));
		nodes.put("D", Map.of("FIM", 1d));
		nodes.put("FIM", Map.of());

		Graph graph = new Graph(nodes);
		graph.save(Path.of("src/main/resources/v3.txt"));
	}

}
