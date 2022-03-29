package br.com.edu.aws.s3;

import br.com.edu.aws.s3.storage.StorageService;
import br.com.edu.aws.s3.util.StorageUtil;

/**
 * Class to list objects.
 *
 * @author Eduardo
 */
public class MainListObjects {

	public static void main(final String[] args) {
		final var bucket = StorageUtil.getTestProperty("bucket_name");

		StorageService.getInstance().listObjects(bucket).forEach(System.out::println);
	}

}
