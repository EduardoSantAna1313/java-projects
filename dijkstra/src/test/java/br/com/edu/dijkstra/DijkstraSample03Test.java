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
class DijkstraSample03Test {

	Dijkstra dijkstra;

	@BeforeAll
	void setup() throws IOException {
		dijkstra = new Dijkstra(new Grafo(Path.of("src/test/resources/sample03.txt")));
	}

	@Test
	void testCost0To3() throws IOException {

		var response = dijkstra.menorCaminho(0, 5);
		System.out.println(response);
		assertNotNull(response);
		assertEquals(60d, response.peso());
		assertEquals(Arrays.mismatch(List.of(4, 3, 1, 0).toArray(new Integer[0]),
				response.menorCaminho().toArray(new Integer[0])), -1);
	}

}
