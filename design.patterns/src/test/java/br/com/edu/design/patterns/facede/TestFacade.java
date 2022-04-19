package br.com.edu.design.patterns.facede;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.Test;

import design.patterns.facade.PostmanApiFacade;

/**
 * Class to TestFacade.
 *
 * @author Eduardo
 */
public class TestFacade {

	@Test
	public void testList() throws IOException {
		final var api = new PostmanApiFacade();

		final var result = api.listCollections(Files.readString(Path.of("src/main/resources/credentials.txt")));
		System.out.println(result);

		assertNotNull(result.getCollections());
	}

}
