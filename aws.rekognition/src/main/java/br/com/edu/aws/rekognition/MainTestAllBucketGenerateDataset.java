package br.com.edu.aws.rekognition;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.amazonaws.services.s3.model.S3ObjectSummary;

import br.com.edu.aws.rekognition.service.RekognitionService;
import br.com.edu.aws.rekognition.service.s3.S3Service;
import br.com.edu.aws.rekognition.util.TestUtil;
import br.com.edu.rekognition.labeling.dto.ImageSize;
import br.com.edu.rekognition.labeling.dto.LabelAnnotation;
import br.com.edu.rekognition.labeling.dto.LabelFile;
import br.com.edu.rekognition.labeling.dto.LabelMetadata;
import br.com.edu.rekognition.labeling.dto.LabelObject;
import software.amazon.awssdk.services.rekognition.model.CustomLabel;

/**
 * Teste de extração de labels customizadas do Rekognition de um bucket inteiro
 * e filtro por prefix e salvando resultado.
 *
 * @author Eduardo
 */
public class MainTestAllBucketGenerateDataset {

	private static final Map<String, String> CLASS_MAP = Map.of("0", "condicao", "1", "numero", "2", "data");

	static AtomicInteger index = new AtomicInteger(0);

	public static void main(final String[] args) throws IOException {

		final String bucket = TestUtil.getTestProperty("bucket");

		final String prefix = TestUtil.getTestProperty("prefix");

		try (final BufferedWriter out = new BufferedWriter(new FileWriter(Path.of("test_dataset.json").toFile()));
				final var service = RekognitionService.getInstance()) {

			// list objects from bucket and prefix
			final var objects = S3Service.getInstance().listObjects(bucket, prefix);

			for (final var s3ObjectSummary : objects) {

				final String key = s3ObjectSummary.getKey();

				if (!key.endsWith(".png")) {
					continue;
				}

				System.out.println(key);

				// detect custom labels on Rekognition
				final var labels = service.detectImageCustomLabels(bucket, key);

				for (final var customLabel : labels) {
					System.out.println("\t" + customLabel.name() + ": " + customLabel.confidence().toString());
				}

				final LabelFile labelDataset = create(s3ObjectSummary, labels);
				out.append(labelDataset.toString());
				out.newLine();
				out.flush();

			}

		}

	}

	private static LabelFile create(final S3ObjectSummary s3ObjectSummary, final List<CustomLabel> labels) {
		final int id = index.getAndIncrement();
		final LabelFile file = new LabelFile();
		file.setSourceRef("s3://" + s3ObjectSummary.getBucketName() + "/" + s3ObjectSummary.getKey());
		file.setLineIndex(id);
		file.setObjectId(id);

		file.setCustomLabel(getCustomLabel(labels));

		file.setLabelsTraingMetadata(getMetadata(labels));

		return file;
	}

	private static br.com.edu.rekognition.labeling.dto.CustomLabel getCustomLabel(final List<CustomLabel> labels) {
		final var label = new br.com.edu.rekognition.labeling.dto.CustomLabel();

		final int imgWidth = 496;
		final int imgHeight = 702;
		label.addImageSize(ImageSize.builder().width(imgWidth).height(imgHeight).depth(3).build());

		if (labels == null || labels.isEmpty()) {
			System.out.println("Sem labels...");
			return label;
		}

		final Map<String, String> mapClassId = CLASS_MAP.entrySet().stream()
				.collect(Collectors.toMap(Entry::getValue, Entry::getKey));

		final Map<String, CustomLabel> mapLabels = labels.stream()
				.collect(Collectors.toMap(CustomLabel::name, Function.identity(), (o, n) -> {
					final int compare = Float.compare(o.confidence(), n.confidence());

					if (compare == 0 || compare != 1) {
						return o;
					} else {
						return n;
					}

				}));

		for (final var l : mapLabels.entrySet()) {

			final String classIdTxt = mapClassId.get(l.getKey());

			if (classIdTxt == null || classIdTxt.isBlank()) {
				continue;
			}

			final int classId = Integer.valueOf(classIdTxt);

			final var bb = l.getValue().geometry().boundingBox();
			final int left = (int) (bb.left() * imgWidth);
			final int top = (int) (bb.top() * imgHeight);
			final int width = (int) (bb.width() * imgWidth);
			final int height = (int) (bb.height() * imgHeight);
			label.addAnnotion(
					LabelAnnotation.builder().left(left).top(top).width(width).height(height).classid(classId).build());
		}

		return label;
	}

	private static LabelMetadata getMetadata(final List<CustomLabel> labels) {
		final LabelMetadata metadata = LabelMetadata.builder().jobName("labeling-job/nfse-train_BB").classMap(CLASS_MAP)
				.humanAnnotated("yes").creationDate("2022-03-30T20:49:08.391Z").type("groundtruth/object-detection")
				.build();

		for (final var v : labels) {
			metadata.addObject(LabelObject.builder().confidence(1).build());
		}

		return metadata;
	}

}
