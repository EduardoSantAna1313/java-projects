package br.com.edu.dijkstra;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DijkstraSample01Test {

	Dijkstra dijkstra;

	@BeforeEach
	void setup() throws IOException {
		final Map<String, Map<String, Double>> grafo = new LinkedHashMap<>();
		grafo.put("INICIO", Map.of("A", 6d, "B", 2d));
		grafo.put("A", Map.of("FIM", 1d));
		grafo.put("B", Map.of("A", 3d, "FIM", 5d));
		grafo.put("FIM", Map.of());

		dijkstra = new Dijkstra(grafo);
	}

	@Test
	void testCost0To1() throws IOException {
		var response = dijkstra.menorCaminho("INICIO", "A");
		assertNotNull(response);
		assertEquals(5d, response.peso());
	}

	@Test
	void testCost0To3() throws IOException {
		var response = dijkstra.menorCaminho("INICIO", "FIM");
		assertNotNull(response);
		assertEquals(6d, response.peso());
	}

}
