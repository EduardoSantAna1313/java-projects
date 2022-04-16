package br.com.edu.postman.api;

import java.io.IOException;

import br.com.edu.postman.api.service.PostmanService;
import br.com.edu.postman.api.service.PostmanServiceBuilder;
import br.com.edu.postman.api.util.PropertiesUtil;

/**
 * Detail a collection.
 *
 * @author Eduardo
 */
public class MainDetailCollection {

	public static void main(final String[] args) throws IOException {

		final var apiKey = PropertiesUtil.getPropery("api_key");

		final PostmanService serv = new PostmanServiceBuilder(apiKey).build();

		final var list = serv.collections().get(0);

		System.out.println("Detail the collection: " + list.getName());

		final var collection = serv.collection(list.getUid());

		System.out.println("\n\nInfo:\n\n");
		System.out.println(collection.getInfo());
		System.out.println("\n\nItems:\n\n");
		System.out.println(collection.getItem());
	}

}
