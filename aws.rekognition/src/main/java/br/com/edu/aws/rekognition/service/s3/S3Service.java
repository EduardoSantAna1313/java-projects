package br.com.edu.aws.rekognition.service.s3;

import java.util.List;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;

/**
 * Service to S3.
 *
 * @author Eduardo
 */
public class S3Service {

	private static final S3Service INSTANCE = new S3Service();

	private final AmazonS3Client client;

	private S3Service() {
		client = S3ClientProvider.getInstance().client();
	}

	public static S3Service getInstance() {
		return INSTANCE;
	}

	public S3Object getObject(final String bucket, final String key) {
		return client.getObject(bucket, key);
	}

	public List<S3ObjectSummary> listObjects(final String bucket, final String prefix) {
		return client.listObjects(bucket, prefix).getObjectSummaries();
	}

}
