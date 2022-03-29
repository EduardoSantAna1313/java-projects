package br.com.edu.aws.rekognition.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;

/**
 * Class to Rekognition Credentials.
 *
 * @author Eduardo
 */
public class RekognitionCredentials implements AwsCredentialsProvider {

	/**
	 * Singleton
	 */
	private static final RekognitionCredentials INSTANCE = new RekognitionCredentials();

	private final Properties properties;

	/**
	 * New instance of RekognitionCredentials
	 */
	private RekognitionCredentials() {
		super();
		properties = new Properties();

		try (var is = new FileInputStream("src/main/resources/credentials.properties")) {
			properties.load(is);
		} catch (final IOException error) {
			error.printStackTrace();
		}

	}

	/**
	 * Get instance.
	 *
	 * @return
	 */
	public static RekognitionCredentials getInstance() {
		return INSTANCE;
	}

	@Override
	public AwsCredentials resolveCredentials() {
		return new AwsCredentials() {

			@Override
			public String secretAccessKey() {
				return properties.getProperty("secret_key");
			}

			@Override
			public String accessKeyId() {
				return properties.getProperty("access_key_id");
			}

		};
	}

}