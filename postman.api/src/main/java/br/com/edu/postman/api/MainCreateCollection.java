package br.com.edu.postman.api;

import java.io.IOException;

import br.com.edu.postman.api.service.PostmanService;
import br.com.edu.postman.api.service.PostmanServiceBuilder;
import br.com.edu.postman.api.service.dto.Collection;
import br.com.edu.postman.api.service.dto.CollectionDetail;
import br.com.edu.postman.api.util.PropertiesUtil;

/**
 * Create new collection.
 *
 * @author Eduardo
 */
public class MainCreateCollection {

	public static void main(final String[] args) throws IOException {

		final var apiKey = PropertiesUtil.getPropery("api_key");

		final PostmanService serv = new PostmanServiceBuilder(apiKey).build();

		final var list = serv.collections().get(0);

		System.out.println("Detalhando a collection " + list.getName());

		final var collection = serv.collection(list.getUid());

		collection.getInfo().setName("Test Name 2");
		collection.getInfo().setDescription("Test Description Collection");
		collection.getInfo().setPostmanId(null);

		System.out.println("Creating a new collection: ");

		final CollectionDetail detail = new CollectionDetail();

		// generate a copy collection
		detail.setCollection(new Collection(collection));
		final var response = serv.save(detail);

		System.out.println(response);
	}

}
