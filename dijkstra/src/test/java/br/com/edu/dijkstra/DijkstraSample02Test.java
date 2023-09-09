package br.com.edu.dijkstra;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DijkstraSample02Test {

	Dijkstra dijkstra;

	@BeforeEach
	void setup() throws IOException {
		Graph graph = new Graph();
		graph.load(Path.of("src/main/resources/v2.txt"));
		dijkstra = new Dijkstra(graph);
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
