/*
 * COPYRIGHT...
 */
package br.com.edu.postman.api.service;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import com.google.gson.Gson;

import br.com.edu.postman.api.service.dto.Collection;
import br.com.edu.postman.api.service.dto.CollectionDetail;
import br.com.edu.postman.api.service.dto.CollectionResponse;
import br.com.edu.postman.api.service.dto.collection.ResponseCreate;
import br.com.edu.postman.api.service.dto.workspace.ResponseDetailW;
import br.com.edu.postman.api.service.dto.workspace.ResponseListW;
import br.com.edu.postman.api.service.dto.workspace.Workspace;

/**
 * Class to PostmanService.
 *
 * @author Eduardo
 */
public class PostmanService {

	/**
	 * String - apiKey.
	 */
	private final String apiKey;

	/**
	 * Gson - gson.
	 */
	private final Gson gson = new Gson();

	/**
	 * New instance of PostmanService
	 */
	protected PostmanService(final String apiKey) {
		this.apiKey = apiKey;
	}

	/**
	 * List the collections.
	 *
	 * @return
	 */
	public List<Collection> collections() {

		try {
			final var result = PostmanConnection.get("collections", apiKey);

			final CollectionResponse response = gson.fromJson(result, CollectionResponse.class);

			return response.getCollections();
		} catch (final IOException error) {
			error.printStackTrace();
		}

		return Collections.emptyList();

	}

	/**
	 * List the collections.
	 *
	 * @return
	 */
	public List<Workspace> workspaces() {

		try {
			final var result = PostmanConnection.get("workspaces", apiKey);

			final ResponseListW response = gson.fromJson(result, ResponseListW.class);

			return response.getWorkspaces();
		} catch (final IOException error) {
			error.printStackTrace();
		}

		return Collections.emptyList();

	}

	/**
	 * Detail a collection.
	 *
	 * @param uid
	 *
	 * @return
	 *
	 * @throws IOException
	 */
	public Collection collection(final String uid) throws IOException {
		final var result = PostmanConnection.get("collections/" + uid, apiKey);

		final CollectionDetail response = gson.fromJson(result, CollectionDetail.class);

		return response.getCollection();
	}

	/**
	 * Detail a workspace.
	 *
	 * @param uid
	 *
	 * @return
	 *
	 * @throws IOException
	 */
	public Workspace workspace(final String uid) {

		try {
			final var result = PostmanConnection.get("workspaces/" + uid, apiKey);

			final ResponseDetailW response = gson.fromJson(result, ResponseDetailW.class);

			return response.getWorkspace();
		} catch (final Exception e) {
			return null;
		}

	}

	/**
	 * Create a collection.
	 *
	 * @param collection
	 *
	 * @return
	 *
	 * @throws IOException
	 */
	public Collection save(final CollectionDetail collection) throws IOException {

		final var content = gson.toJson(collection);

		final String json = PostmanConnection.post("collections", apiKey, content);

		return gson.fromJson(json, ResponseCreate.class).getCollection();

	}

	/**
	 * Update a collection.
	 *
	 * @param uid
	 * @param collection
	 *
	 * @return
	 *
	 * @throws IOException
	 */
	public Collection update(final String uid, final CollectionDetail collection) throws IOException {

		final var content = gson.toJson(collection);

		final String json = PostmanConnection.put("collections/" + uid, apiKey, content);

		return gson.fromJson(json, ResponseCreate.class).getCollection();

	}

}
