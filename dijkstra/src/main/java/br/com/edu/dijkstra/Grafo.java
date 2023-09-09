package br.com.edu.dijkstra;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class Grafo {
	private Map<Integer, List<Double>> nodes;

	public Grafo() {
		nodes = new HashMap<>();
	}

	public Grafo(final Path file) throws IOException {
		this();

		final var lines = Files.readAllLines(file);
		for (int i = 0; i < lines.size(); i++) {
			var array = lines.get(i).split(";");
			addNode(i, Stream.of(array).map(Double::valueOf).toList());
		}
	}

	public void addNode(Integer n, List<Double> list) {
		this.nodes.put(n, list);
	}

	public List<Double> distancias(Integer index) {
		return nodes.get(index);
	}

	public int count() {
		return nodes.size();
	}

	@Override
	public String toString() {
		return "Grafo [nodes=" + nodes + "]";
	}

}
