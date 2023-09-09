package br.com.edu.dijkstra;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DijkstraSample03Test {

	Dijkstra dijkstra;

	@BeforeEach
	void setup() throws IOException {
		Graph graph = new Graph();
		graph.load(Path.of("src/main/resources/v3.txt"));
		dijkstra = new Dijkstra(graph);
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
