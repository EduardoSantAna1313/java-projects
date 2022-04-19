/*
 * COPYRIGHT...
 */
package design.patterns.facade;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;

import design.patterns.facade.dto.PostmanResponse;

/**
 * Simplifies the access to postman rest api.
 *
 * @author Eduardo
 */
public class PostmanApiFacade {

	private static final String GET = "GET";

	private static final String HEADER = "X-Api-Key";

	private static final String URL = "https://api.getpostman.com/collections";

	private final Gson gson = new Gson();

	public PostmanResponse collections(final String token) throws IOException {
		final URL url = new URL(URL);

		final var conn = (HttpURLConnection) url.openConnection();
		conn.addRequestProperty(HEADER, token);
		conn.setRequestMethod(GET);

		conn.getDoInput();

		final String json = readStream(conn);

		return gson.fromJson(json, PostmanResponse.class);
	}

	private String readStream(final HttpURLConnection conn) throws IOException {
		final var is = conn.getInputStream();

		final byte[] bytes = new byte[10240];

		final StringBuilder s = new StringBuilder();

		int r = 0;

		while ((r = is.read(bytes, 0, bytes.length)) != -1) {
			s.append(new String(bytes, 0, r));
		}

		return s.toString();
	}

}
