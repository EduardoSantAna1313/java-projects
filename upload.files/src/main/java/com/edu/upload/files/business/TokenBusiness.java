package com.edu.upload.files.business;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;

import com.edu.upload.files.type.Environment;

/**
 * Class to OAuth Token.
 *
 * @author Eduardo
 */
public class TokenBusiness {

	/**
	 * HttpClient - client.
	 */
	private static final HttpClient client = HttpClientBuilder.create().build();

	/**
	 * String - FORMAT_URL.
	 */
	private static final String FORMAT_URL = "%s/oauth2/v1/token?username=%s&password=%s&client_id=%s&client_secret=%s&grant_type=%s";

	/**
	 * String - GRAND_TYPE.
	 */
	private static final String GRANT_TYPE;

	/**
	 * String - CLIENT_SECRET.
	 */
	private static final String CLIENT_SECRET;

	/**
	 * String - CLIENT_ID.
	 */
	private static final String CLIENT_ID;

	static {

		try {
			// load properties
			final Properties props = new Properties();
			props.load(new FileInputStream("resources/oauthkeys.properties"));

			CLIENT_SECRET = props.getProperty("secret");
			CLIENT_ID = props.getProperty("id");
			GRANT_TYPE = props.getProperty("granttype");

		} catch (final Exception error) {
			// NA
			throw new RuntimeException("Não foi possivel encontrar as chaves! " + error.getMessage());
		}

	}

	public String auth(final Environment environment) throws IOException {

		final String url = String.format(FORMAT_URL, environment.getUrl(), environment.getUser(),
				environment.getPassword(), CLIENT_ID, CLIENT_SECRET, GRANT_TYPE);

		final HttpPost postPageRequest = new HttpPost(url);
		postPageRequest.addHeader("Accept", "application/json");
		postPageRequest.addHeader("Content-Type", "application/x-www-form-urlencoded");

		final HttpResponse postPageResponse = client.execute(postPageRequest);

		final HttpEntity pageEntity = postPageResponse.getEntity();

		final String content = IOUtils.toString(pageEntity.getContent(), StandardCharsets.UTF_8);

		if (!content.contains("\"access_token\":")) {
			throw new IOException("Não autenticado" + content);
		}

		return content.substring(17, 49);

	}

}
