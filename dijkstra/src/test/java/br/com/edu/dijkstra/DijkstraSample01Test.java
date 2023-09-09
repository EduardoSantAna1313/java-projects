package br.com.edu.dijkstra;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DijkstraSample01Test {

	Dijkstra dijkstra;

	@BeforeEach
	void setup() throws IOException {
		Graph graph = new Graph();
		graph.load(Path.of("src/main/resources/v1.txt"));
		dijkstra = new Dijkstra(graph);
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
