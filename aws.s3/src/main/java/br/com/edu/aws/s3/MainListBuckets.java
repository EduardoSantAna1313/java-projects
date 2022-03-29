package br.com.edu.aws.s3;

import br.com.edu.aws.s3.storage.StorageService;

/**
 * Lista os buckets.
 *
 * @author Eduardo
 */
public class MainListBuckets {

	public static void main(final String[] args) {
		StorageService.getInstance().listBuckets().forEach(System.out::println);
	}

}
