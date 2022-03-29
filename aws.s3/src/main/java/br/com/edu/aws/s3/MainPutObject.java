package br.com.edu.aws.s3;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import br.com.edu.aws.s3.storage.StorageObject;
import br.com.edu.aws.s3.storage.StorageService;
import br.com.edu.aws.s3.util.StorageUtil;

/**
 * Class to list objects.
 *
 * @author Eduardo
 */
public class MainPutObject {

	public static void main(final String[] args) throws FileNotFoundException {

		final var bucket = StorageUtil.getTestProperty("bucket_name");

		final StorageObject object = new StorageObject();
		object.setBucket(bucket);
		object.setKey("diretorio_teste/out.png");
		final File file = new File("src/main/resources/out/test.png");
		object.setIs(new FileInputStream(file));
		object.setContentLength(file.length());

		final var result = StorageService.getInstance().putObject(object);
		System.out.println("Md5: " + result.getMd5());
		System.out.println("eTag: " + result.geteTag());
	}

}
