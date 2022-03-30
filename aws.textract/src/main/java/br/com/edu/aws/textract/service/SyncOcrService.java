package br.com.edu.aws.textract.service;

import java.nio.ByteBuffer;
import java.util.List;
import java.util.stream.Collectors;

import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.amazonaws.services.textract.AmazonTextract;
import com.amazonaws.services.textract.AmazonTextractClientBuilder;
import com.amazonaws.services.textract.model.DetectDocumentTextRequest;
import com.amazonaws.services.textract.model.DetectDocumentTextResult;
import com.amazonaws.services.textract.model.Document;

import br.com.edu.aws.textract.service.dto.BlockConverter;
import br.com.edu.aws.textract.service.dto.BlockDTO;

/**
 * Sync textract.
 *
 * @author Eduardo
 */
public class SyncOcrService {

	private static final SyncOcrService INSTANCE = new SyncOcrService();

	private final AmazonTextract client;

	private SyncOcrService() {

		final EndpointConfiguration endpoint = new EndpointConfiguration("https://textract.us-east-1.amazonaws.com",
				"us-east-1");

		client = AmazonTextractClientBuilder.standard().withEndpointConfiguration(endpoint)
				.withCredentials(TextractCredentials.getInstance().getProvider()).build();
	}

	public static SyncOcrService getInstance() {
		return INSTANCE;
	}

	public List<BlockDTO> extract(final byte[] image) {
		final ByteBuffer pImage = ByteBuffer.wrap(image);

		final DetectDocumentTextRequest request = new DetectDocumentTextRequest()
				.withDocument(new Document().withBytes(pImage));

		final DetectDocumentTextResult result = client.detectDocumentText(request);

		return result.getBlocks().stream().map(BlockConverter::convertToDTO).collect(Collectors.toList());
	}

}
