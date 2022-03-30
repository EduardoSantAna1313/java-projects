package br.com.edu.aws.textract.service;

import java.io.FileInputStream;
import java.util.Properties;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;

public class TextractCredentials {

	private static final TextractCredentials INSTANCE = new TextractCredentials();

	private final Properties properties;

	private TextractCredentials() {
		properties = new Properties();

		try (var is = new FileInputStream("src/main/resources/credentials.properties")) {
			properties.load(is);
		} catch (final Exception e) {
			// NA
		}

	}

	public static TextractCredentials getInstance() {
		return INSTANCE;
	}

	public AWSCredentialsProvider getProvider() {
		return new AWSCredentialsProvider() {

			@Override
			public void refresh() {

			}

			@Override
			public AWSCredentials getCredentials() {
				return new AWSCredentials() {

					@Override
					public String getAWSSecretKey() {
						return properties.getProperty("secret_key");
					}

					@Override
					public String getAWSAccessKeyId() {
						return properties.getProperty("access_key_id");
					}

				};
			}

		};
	}

}
