package br.com.edu.dijkstra;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DijkstraSample02Test {

	Dijkstra dijkstra;

	@BeforeEach
	void setup() throws IOException {
		final Map<String, Map<String, Double>> grafo = new LinkedHashMap<>();
		grafo.put("INICIO", Map.of("A", 5d, "B", 2d));
		grafo.put("A", Map.of("C", 4d, "D", 2d));
		grafo.put("B", Map.of("A", 8d, "D", 7d));
		grafo.put("C", Map.of("D", 6d, "FIM", 3d));
		grafo.put("D", Map.of("FIM", 1d));
		grafo.put("FIM", Map.of());

		dijkstra = new Dijkstra(grafo);
	}

	@Test
	void testCost0To3() throws IOException {

		var response = dijkstra.menorCaminho("INICIO", "FIM");
		assertNotNull(response);
		assertEquals(8d, response.peso());
//		assertEquals(Arrays.mismatch(List.of(5, 4, 1, 0).toArray(new Integer[0]),
//				response.menorCaminho().toArray(new Integer[0])), -1);
	}

}
