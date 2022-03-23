package br.com.edutwitter.twitter.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Authentication...
 *
 * @author Eduardo
 */
public class Authentication {

	private String apiKey;

	private String apiSecretKey;

	private String accessToken;

	private String accessTokenSecret;

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(final String apiKey) {
		this.apiKey = apiKey;
	}

	public String getApiSecretKey() {
		return apiSecretKey;
	}

	public void setApiSecretKey(final String apiSecretKey) {
		this.apiSecretKey = apiSecretKey;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(final String accessToken) {
		this.accessToken = accessToken;
	}

	public String getAccessTokenSecret() {
		return accessTokenSecret;
	}

	public void setAccessTokenSecret(final String accessTokenSecret) {
		this.accessTokenSecret = accessTokenSecret;
	}

	public static Authentication getDefault() throws FileNotFoundException, IOException {

		try (final var is = new FileInputStream("twitter.properties")) {
			final Properties props = new Properties();
			props.load(is);

			final Authentication result = new Authentication();
			result.setApiKey(props.getProperty("api_key"));
			result.setApiSecretKey(props.getProperty("secret_key"));
			result.setAccessToken(props.getProperty("access_token"));
			result.setAccessTokenSecret(props.getProperty("access_token_secret"));
			return result;
		}

	}

}
