package br.com.edu.dijkstra;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DijkstraSample03Test {

	Dijkstra dijkstra;

	@BeforeEach
	void setup() throws IOException {
		final Map<String, Map<String, Double>> grafo = new LinkedHashMap<>();
		grafo.put("INICIO", Map.of("A", 10d));
		grafo.put("A", Map.of("B", 20d));
		grafo.put("B", Map.of("C", 1d, "FIM", 30d));
		grafo.put("C", Map.of("A", 1d));
		grafo.put("D", Map.of("FIM", 1d));
		grafo.put("FIM", Map.of());

		dijkstra = new Dijkstra(grafo);
	}

	@Test
	void testCost0To3() throws IOException {

		var response = dijkstra.menorCaminho("INICIO", "FIM");
		System.out.println(response);
		assertNotNull(response);
		assertEquals(60d, response.peso());
//		assertEquals(Arrays.mismatch(List.of("INICIO", "A", "B", "FIM").toArray(new String[0]),
//				response.menorCaminho().toArray(new String[0])), -1);
	}

}
