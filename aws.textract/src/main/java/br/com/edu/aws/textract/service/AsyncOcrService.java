package br.com.edu.aws.textract.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.amazonaws.services.textract.AmazonTextract;
import com.amazonaws.services.textract.AmazonTextractClientBuilder;
import com.amazonaws.services.textract.model.DocumentLocation;
import com.amazonaws.services.textract.model.GetDocumentTextDetectionRequest;
import com.amazonaws.services.textract.model.GetDocumentTextDetectionResult;
import com.amazonaws.services.textract.model.JobStatus;
import com.amazonaws.services.textract.model.S3Object;
import com.amazonaws.services.textract.model.StartDocumentTextDetectionRequest;
import com.amazonaws.services.textract.model.StartDocumentTextDetectionResult;

import br.com.edu.aws.textract.service.dto.BlockConverter;
import br.com.edu.aws.textract.service.dto.BlockDTO;

/**
 * Async textract.
 *
 * @author Eduardo
 */
public class AsyncOcrService {

	private static final AsyncOcrService INSTANCE = new AsyncOcrService();

	private final AmazonTextract client;

	private AsyncOcrService() {

		final EndpointConfiguration endpoint = new EndpointConfiguration("https://textract.sa-east-1.amazonaws.com",
				"sa-east-1");

		client = AmazonTextractClientBuilder.standard().withEndpointConfiguration(endpoint)
				.withCredentials(TextractCredentials.getInstance().getProvider()).withRegion("sa-east-1").build();
	}

	public static AsyncOcrService getInstance() {
		return INSTANCE;
	}

	public String extract(final String bucket, final String key) {
		final StartDocumentTextDetectionRequest request = new StartDocumentTextDetectionRequest()
				.withDocumentLocation(new DocumentLocation()
						// load the file to extract text inside of S3
						.withS3Object(new S3Object().withBucket(bucket).withName(key)))
				.withJobTag("DetectingText");

		final StartDocumentTextDetectionResult result = client.startDocumentTextDetection(request);

		return result.getJobId();
	}

	public boolean finished(final String jobId) {
		final GetDocumentTextDetectionRequest documentTextDetectionRequest = new GetDocumentTextDetectionRequest()
				.withJobId(jobId);

		final GetDocumentTextDetectionResult response = client.getDocumentTextDetection(documentTextDetectionRequest);

		final String jobStatus = response.getJobStatus();

		return !JobStatus.IN_PROGRESS.name().equals(jobStatus);
	}

	public List<BlockDTO> getBlocks(final String jobId) {

		String nextToken = null;

		boolean finished = false;

		final List<BlockDTO> result = new ArrayList<>();

		while (!finished) {
			// Create request
			final GetDocumentTextDetectionRequest documentTextDetectionRequest = new GetDocumentTextDetectionRequest()
					.withNextToken(nextToken).withJobId(jobId);

			// Job result
			final GetDocumentTextDetectionResult response = client
					.getDocumentTextDetection(documentTextDetectionRequest);

			// get the request status
			final String jobStatus = response.getJobStatus();

			if (jobStatus.equals(JobStatus.IN_PROGRESS.toString())) {
				throw new IllegalStateException("Textract nao finalizado!");
			}

			result.addAll(response.getBlocks().stream().map(BlockConverter::convertToDTO).collect(Collectors.toList()));

			nextToken = response.getNextToken();

			finished = nextToken == null;
		}

		return result;
	}

}
