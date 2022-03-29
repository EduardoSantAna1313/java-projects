package br.com.edu.aws.rekognition.service.s3;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

/**
 * S3 client provider.
 *
 * @author Eduardo
 */
public final class S3ClientProvider {

	private static final S3ClientProvider INSTANCE = new S3ClientProvider();

	private final Properties properties;

	private final AWSCredentialsProvider provider = new AWSCredentialsProvider() {

		@Override
		public final AWSCredentials getCredentials() {
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

		@Override
		public void refresh() {
			// NA
		}

	};

	/**
	 * New instance of S3ClientProvider
	 */
	private S3ClientProvider() {
		super();
		properties = new Properties();

		// load the credentials
		try (var is = new FileInputStream("src/main/resources/credentials.properties")) {
			properties.load(is);
		} catch (final IOException error) {
			error.printStackTrace();
		}

	}

	/**
	 * Get the instance.
	 *
	 * @return
	 */
	public static S3ClientProvider getInstance() {
		return INSTANCE;
	}

	public AmazonS3Client client() {
		final AmazonS3ClientBuilder builder = AmazonS3ClientBuilder.standard();

		builder.setCredentials(provider);
		builder.setRegion(properties.getProperty("region"));

		return (AmazonS3Client) builder.build();
	}

}