package br.com.edu.aws.s3.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.amazonaws.event.ProgressListener;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CompleteMultipartUploadRequest;
import com.amazonaws.services.s3.model.InitiateMultipartUploadRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PartETag;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.amazonaws.services.s3.transfer.internal.UploadPartRequestFactory;

/**
 * Class to StorageService.
 *
 * @author Eduardo
 */
public class StorageService {

	private static final int MIN_FILE_SIZE_PER_PART = 5 * 1024 * 1024;

	private static final StorageService INSTANCE = new StorageService();

	private StorageService() {
		// NA
	}

	public static StorageService getInstance() {
		return INSTANCE;
	}

	private AmazonS3Client client;

	public StorageObject putObject(final StorageObject object) {

		final AmazonS3Client s3Client = getClient();

		PutObjectResult objResult;

		if (object.getFile() != null) {
			objResult = s3Client.putObject(object.getBucket(), object.getKey(), object.getFile());
		} else {

			final ObjectMetadata metadata = new ObjectMetadata();
			metadata.setContentLength(object.getContentLength());

			objResult = s3Client.putObject(object.getBucket(), object.getKey(), object.getIs(), metadata);
		}

		final StorageObject result = object.copy();
		result.setMd5(objResult.getContentMd5());
		result.seteTag(objResult.getETag());

		return result;
	}

	public StorageObject putLargeObject(final StorageObject object, final ProgressListener progressListener) {
		final AmazonS3Client s3Client = getClient();

		final var request = new PutObjectRequest(object.getBucket(), object.getKey(), object.getFile());

		final var multipartUploadRequest = new InitiateMultipartUploadRequest(request.getBucketName(), request.getKey(),
				request.getMetadata());

		final var initResponse = s3Client.initiateMultipartUpload(multipartUploadRequest);

		final String uploadId = initResponse.getUploadId();

		final var factory = new UploadPartRequestFactory(request, uploadId, MIN_FILE_SIZE_PER_PART);

		final var partETags = new ArrayList<PartETag>();

		System.out.println("Parts: " + factory.getTotalNumberOfParts());

		while (factory.hasMoreRequests()) {

			final var part = factory.getNextUploadPartRequest();
			part.setGeneralProgressListener(progressListener);

			final var result = s3Client.uploadPart(part);

			partETags.add(result.getPartETag());
		}

		final var compRequest = new CompleteMultipartUploadRequest(request.getBucketName(), request.getKey(), uploadId,
				partETags);
		final var uploadResult = s3Client.completeMultipartUpload(compRequest);

		final var result = new StorageObject();
		result.setBucket(uploadResult.getBucketName());
		result.setKey(uploadResult.getKey());
		return result;

	}

	public List<BucketDTO> listBuckets() {
		final AmazonS3Client s3Client = getClient();

		return s3Client.listBuckets().stream().map(BucketDTO::new).collect(Collectors.toList());
	}

	public List<StorageObject> listObjects(final String bucket) {
		final AmazonS3Client s3Client = getClient();

		return s3Client.listObjects(bucket).getObjectSummaries().stream().map((final S3ObjectSummary s) -> {
			final StorageObject o = new StorageObject();
			o.setBucket(s.getBucketName());
			o.setKey(s.getKey());
			o.setContentLength(s.getSize());

			s.getETag();
			return o;
		}).collect(Collectors.toList());
	}

	public StorageObject getObject(final String bucket, final String key) {
		final AmazonS3Client s3Client = getClient();

		final var obj = s3Client.getObject(bucket, key);

		final StorageObject result = new StorageObject();
		result.setBucket(obj.getBucketName());
		result.setContentLength(obj.getObjectMetadata().getContentLength());
		result.setIs(obj.getObjectContent());
		result.setKey(obj.getKey());
		return result;
	}

	public List<S3ObjectSummary> listObjects(final String bucket, final String prefix) {
		final AmazonS3Client s3Client = getClient();

		return s3Client.listObjects(bucket, prefix).getObjectSummaries();
	}

	private AmazonS3Client getClient() {

		if (client == null) {
			final CredentialsService service = CredentialsService.getInstance();

			client = service.loadS3Service();
		}

		return client;
	}

}
