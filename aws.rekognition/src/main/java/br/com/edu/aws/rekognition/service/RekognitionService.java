package br.com.edu.aws.rekognition.service;

import java.io.FileInputStream;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.rekognition.RekognitionClient;
import software.amazon.awssdk.services.rekognition.model.CustomLabel;
import software.amazon.awssdk.services.rekognition.model.DescribeProjectVersionsRequest;
import software.amazon.awssdk.services.rekognition.model.DetectCustomLabelsRequest;
import software.amazon.awssdk.services.rekognition.model.DetectCustomLabelsResponse;
import software.amazon.awssdk.services.rekognition.model.Image;
import software.amazon.awssdk.services.rekognition.model.ListDatasetEntriesRequest;
import software.amazon.awssdk.services.rekognition.model.ProjectVersionStatus;
import software.amazon.awssdk.services.rekognition.model.RekognitionException;
import software.amazon.awssdk.services.rekognition.model.S3Object;
import software.amazon.awssdk.services.rekognition.model.StartProjectVersionRequest;
import software.amazon.awssdk.services.rekognition.model.StopProjectVersionRequest;

/**
 * Rekognition service.
 *
 * @author Eduardo
 */
public final class RekognitionService implements AutoCloseable {

	/**
	 * Singleton
	 */
	private static final RekognitionService INSTANCE = new RekognitionService();

	private final Properties props = new Properties();

	private String projectArn;

	private String modelArn;

	private String versionName;

	private String region;

	private RekognitionClient client;

	/**
	 * New instance of RekognitionService
	 */
	private RekognitionService() {

		try {
			props.load(new FileInputStream("src/main/resources/test.properties"));

			modelArn = props.getProperty("model_arn");
			projectArn = props.getProperty("project_arn");
			versionName = props.getProperty("version_name");
			region = props.getProperty("region");

			client = RekognitionClient.builder().credentialsProvider(RekognitionCredentials.getInstance())
					.region(Region.of(region)).build();
		} catch (final Exception e) {
			// NA
			e.printStackTrace();
		}

	}

	public static RekognitionService getInstance() {
		return INSTANCE;
	}

	/**
	 * Detect custom labels.
	 *
	 * @param bucket
	 * @param key
	 *
	 * @return
	 */
	public List<CustomLabel> detectImageCustomLabels(final String bucket, final String key) {

		try {

			final S3Object s3Object = S3Object.builder().bucket(bucket).name(key).build();

			final Image s3Image = Image.builder().s3Object(s3Object).build();

			final DetectCustomLabelsRequest detectCustomLabelsRequest = DetectCustomLabelsRequest.builder()
					.image(s3Image).projectVersionArn(projectArn).build();

			final DetectCustomLabelsResponse customLabelsResponse = client
					.detectCustomLabels(detectCustomLabelsRequest);

			return customLabelsResponse.customLabels();

		} catch (final RekognitionException error) {
			error.printStackTrace();
			return Collections.emptyList();
		}

	}

	/**
	 * Start the model.
	 */
	public void startModel() {

		if (started()) {
			System.out.println("The model has already been started!");
			return;
		}

		System.out.println("Starting the model...");

		final StartProjectVersionRequest request = StartProjectVersionRequest.builder().projectVersionArn(modelArn)
				.minInferenceUnits(1).build();

		final var response = client.startProjectVersion(request);

		System.out.println("Status: " + response.status());

	}

	public boolean started() {

		final var resp = client.describeProjectVersions(
				DescribeProjectVersionsRequest.builder().projectArn(projectArn).versionNames(versionName).build());

		if (resp.hasProjectVersionDescriptions()) {

			final var desc = resp.projectVersionDescriptions();

			final var status = desc.get(0).status();

			System.out.println("Status: " + status);

			return status.equals(ProjectVersionStatus.RUNNING);
		}

		return false;
	}

	public boolean stoped() {

		final var resp = client.describeProjectVersions(
				DescribeProjectVersionsRequest.builder().projectArn(projectArn).versionNames(versionName).build());

		if (resp.hasProjectVersionDescriptions()) {

			final var desc = resp.projectVersionDescriptions();

			final var status = desc.get(0).status();

			System.out.println("Status: " + status);

			return status.equals(ProjectVersionStatus.STOPPED);
		}

		return false;
	}

	public void collection() {
		System.out.println(client.listDatasetEntries(
				ListDatasetEntriesRequest.builder().datasetArn(projectArn).containsLabels("_01").build()));
	}

	/**
	 * Stop the model
	 */
	public void stopModel() {
		final StopProjectVersionRequest request = StopProjectVersionRequest.builder().projectVersionArn(modelArn)
				.build();
		client.stopProjectVersion(request);
	}

	@Override
	public void close() {
		client.close();
	}

}
