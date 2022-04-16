/*
 * COPYRIGHT...
 */
package br.com.edu.postman.api.workspace;

import java.util.function.Consumer;
import java.util.function.Predicate;

import br.com.edu.postman.api.service.PostmanService;
import br.com.edu.postman.api.service.PostmanServiceBuilder;
import br.com.edu.postman.api.service.dto.workspace.Workspace;
import br.com.edu.postman.api.util.PropertiesUtil;

/**
 * List workspaces.
 *
 * @author Eduardo
 */
public class DetailWorkspace {

	public static void main(final String[] args) {

		final var apiKey = PropertiesUtil.getPropery("api_key");

		final PostmanService serv = new PostmanServiceBuilder(apiKey).build();

		final Predicate<Workspace> filter = w -> w.getType().equals("personal");

		final Consumer<Workspace> action = w -> System.out.println(serv.workspace(w.getId()));

		final Runnable elseAction = () -> System.out.println("It was not find a personal workspace");

		serv.workspaces().stream().filter(filter).findAny().ifPresentOrElse(action, elseAction);

	}

}
