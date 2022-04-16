package br.com.edu.postman.api;

import br.com.edu.postman.api.service.PostmanService;
import br.com.edu.postman.api.service.PostmanServiceBuilder;
import br.com.edu.postman.api.util.PropertiesUtil;

/**
 * List the collections.
 *
 * @author Eduardo
 */
public class MainListCollections {

	public static void main(final String[] args) {

		final var apiKey = PropertiesUtil.getPropery("api_key");

		final PostmanService serv = new PostmanServiceBuilder(apiKey).build();

		serv.collections().forEach(System.out::println);
	}

}
