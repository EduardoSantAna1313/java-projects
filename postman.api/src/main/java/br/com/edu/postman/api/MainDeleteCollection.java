package br.com.edu.postman.api;

import br.com.edu.postman.api.service.PostmanService;
import br.com.edu.postman.api.service.PostmanServiceBuilder;
import br.com.edu.postman.api.service.dto.collection.Collection;
import br.com.edu.postman.api.service.dto.collection.CollectionDetail;
import br.com.edu.postman.api.util.PropertiesUtil;

/**
 * Create new collection.
 *
 * @author Eduardo
 */
public class MainDeleteCollection {

	public static void main(final String[] args) throws Exception {

		final var apiKey = PropertiesUtil.getPropery("api_key");

		final PostmanService service = new PostmanServiceBuilder(apiKey).build();

		final var list = service.collections().get(0);

		final var collection = service.collection(list.getUid());

		collection.getInfo().setName("Test Name 2");
		collection.getInfo().setDescription("Test Description Collection");
		collection.getInfo().setPostmanId(null);

		System.out.println("Creating a new collection...");

		final CollectionDetail detail = new CollectionDetail();

		// generate a copy collection
		detail.setCollection(new Collection(collection));
		final var response = service.save(detail);

		Thread.sleep(5_000);

		System.out.println("Deleting the collection...\n\n");

		System.out.println(service.delete(response.getId()));

	}

}
