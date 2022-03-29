package br.com.edu.aws.s3;

import java.io.IOException;
import java.nio.file.Path;

import br.com.edu.aws.s3.storage.StorageService;
import br.com.edu.aws.s3.util.StorageUtil;

/**
 * Class to get object.
 *
 * @author Eduardo
 */
public class MainGetObject {

	public static void main(final String[] args) throws IOException {

		final var bucket = StorageUtil.getTestProperty("bucket_name");

		final var key = StorageUtil.getTestProperty("key");

		final var obj = StorageService.getInstance().getObject(bucket, key);

		System.out.println("Key: " + obj.getKey());
		System.out.println("Content-Lenght: " + obj.getContentLength());

		StorageUtil.save(obj, Path.of("src/main/resources/input"));
	}

}
