package com.edu.upload.files;

import java.io.File;
import java.io.FileInputStream;

import com.edu.upload.files.business.RestApi;
import com.edu.upload.files.business.stream.UploadChangeListener;
import com.edu.upload.files.type.Environment;
import com.edu.upload.files.util.FileUtils;

/**
 * @author Eduardo
 */
public class MainUpload {

	private static final File FILE = new File("resources/teste0.pdf");

	public static void main(final String[] args) throws Exception {

		final Environment env = Environment.load("LOCAL");

		final String fileName = FILE.getName().replaceAll("[^A-Za-z0-9\\.]", "");

		final long contentLenght = FILE.length();

		System.out.println("Uploading " + fileName + " " + FileUtils.format(contentLenght));

		final UploadChangeListener listener = percent -> System.out.println("Uploaded: " + percent + " %");

		final var api = new RestApi(env);
		final var response = api.post(fileName, new FileInputStream(FILE), contentLenght, listener);

		System.out.println(response);
	}

}
