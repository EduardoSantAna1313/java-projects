package br.com.edu.aws.s3.storage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

public class CredentialsService {

	private static final CredentialsService INSTANCE = new CredentialsService();

	private final Properties properties;

	private CredentialsService() {
		super();
		properties = new Properties();

		try (var is = new FileInputStream("src/main/resources/credentials.properties")) {
			properties.load(is);
		} catch (final IOException error) {
			error.printStackTrace();
		}

	}

	protected static CredentialsService getInstance() {
		return INSTANCE;
	}

	protected AmazonS3Client loadS3Service() {

		final var c = loadCredentials();

		AmazonS3ClientBuilder builder = AmazonS3ClientBuilder.standard();

		final String region = properties.getProperty("region");

		if (region != null) {
			builder = builder.withRegion(region);
		}

		return (AmazonS3Client) builder.withCredentials(c).build();
	}

	private AWSCredentialsProvider loadCredentials() {

		return new AWSCredentialsProvider() {

			@Override
			public void refresh() {
				// NA
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
