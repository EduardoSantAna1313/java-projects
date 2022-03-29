package br.com.edu.aws.s3;

import java.io.File;

import br.com.edu.aws.s3.storage.StorageObject;
import br.com.edu.aws.s3.storage.StorageProgressListener;
import br.com.edu.aws.s3.storage.StorageService;
import br.com.edu.aws.s3.util.StorageUtil;

/**
 * Class to upload large objects bigger than 5MB.
 *
 * @author Eduardo
 */
public class MainPutLargeObject {

	public static void main(final String[] args) {

		final var bucket = StorageUtil.getTestProperty("bucket_name");
		final File file = new File("src/main/resources/out/lage_file.pdf");

		final StorageObject object = new StorageObject();
		object.setBucket(bucket);
		object.setKey("diretorio_teste/lage_file.pdf");
		object.setFile(file);

		final var result = StorageService.getInstance().putLargeObject(object,
				new StorageProgressListener(file.length()));

		System.out.println(result);
	}

}
