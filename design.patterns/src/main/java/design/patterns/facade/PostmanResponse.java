/*
 * COPYRIGHT...
 */
package design.patterns.facade;

import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * Class to PostmanResponse.
 *
 * @author Eduardo
 */
@Data
public class PostmanResponse {

	private List<Collection> collections;

	@Data
	private static class Collection {

		private String id;

		private String name;

		private String owner;

		private Date createdAt;

	}

}
