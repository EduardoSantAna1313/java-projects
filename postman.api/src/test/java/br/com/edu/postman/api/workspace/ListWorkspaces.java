/*
 * COPYRIGHT...
 */
package br.com.edu.postman.api.workspace;

import br.com.edu.postman.api.service.PostmanService;
import br.com.edu.postman.api.service.PostmanServiceBuilder;
import br.com.edu.postman.api.util.PropertiesUtil;

/**
 * List workspaces.
 *
 * @author Eduardo
 */
public class ListWorkspaces {

	public static void main(final String[] args) {
		final var apiKey = PropertiesUtil.getPropery("api_key");

		final PostmanService serv = new PostmanServiceBuilder(apiKey).build();

		serv.workspaces().forEach(System.out::println);
	}

}
