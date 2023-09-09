package br.com.edu.dijkstra;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@TestInstance(Lifecycle.PER_CLASS)
public class DijkstraSample01Test {

	Dijkstra dijkstra;

	@BeforeAll
	void setup() throws IOException {
		dijkstra = new Dijkstra(new Grafo(Path.of("src/test/resources/sample01.txt")));
	}

	@Test
	void testCost0To3() throws IOException {
		var response = dijkstra.menorCaminho(0, 3);
		System.out.println(response);
		assertNotNull(response);
		assertEquals(6d, response.peso());
		assertEquals(Arrays.mismatch(List.of(3, 1, 2, 0).toArray(new Integer[0]),
				response.menorCaminho().toArray(new Integer[0])), -1);
	}

	@Test
	void testCost0To1() throws IOException {
		var response = dijkstra.menorCaminho(0, 1);
		assertNotNull(response);
		assertEquals(5d, response.peso());
		assertEquals(Arrays.mismatch(List.of(1, 2, 0).toArray(new Integer[0]),
				response.menorCaminho().toArray(new Integer[0])), -1);
	}

}
