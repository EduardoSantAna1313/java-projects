/*
 * COPYRIGHT...
 */
package design.patterns.facade;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;

/**
 * Simplifies the access to postman rest api.
 *
 * @author Eduardo
 */
public class PostmanApiFacade {

	private static final String URL = "https://api.getpostman.com/collections";

	public PostmanResponse listCollections(final String token) throws IOException {
		final URL url = new URL(URL);

		final var conn = (HttpURLConnection) url.openConnection();
		conn.addRequestProperty("X-Api-Key", token);
		conn.setRequestMethod("GET");

		conn.getDoInput();

		final var is = conn.getInputStream();

		final byte[] b = new byte[10240];

		final StringBuilder s = new StringBuilder();
		int r = 0;

		while ((r = is.read(b, 0, b.length)) != -1) {
			s.append(new String(b, 0, r));
		}

		final Gson g = new Gson();

		return g.fromJson(s.toString(), PostmanResponse.class);
	}

}
