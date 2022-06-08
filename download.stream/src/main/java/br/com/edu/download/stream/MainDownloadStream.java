package br.com.edu.download.stream;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.text.NumberFormat;

import javax.net.ssl.HttpsURLConnection;

public class MainDownloadStream {

	private static final String URL = "";

	public static void main(final String[] args) throws IOException {
		final NumberFormat format = NumberFormat.getNumberInstance();
		format.setMaximumFractionDigits(2);

		final var file = getFile(123);

		try (final FileOutputStream out = new FileOutputStream(Path.of(file.fileName).toFile())) {

			final byte[] b = new byte[10240];
			int r = 0;
			double downloaded = 0;

			while ((r = file.content.read(b, 0, b.length)) != -1) {
				out.write(b, 0, r);
				downloaded += r;

				final double percent = downloaded / file.contentLenght * 100;

				System.out.println("Downloaded: " + format.format(percent) + "%");
			}

		}

		System.out.println("Finish!");

	}

	public static DownloadBO getFile(final Integer id) throws IOException {

		final URL url = new URL(URL);

		final HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
		conn.setRequestMethod("GET");

		final DownloadBO result = new DownloadBO();
		result.content = conn.getInputStream();
		result.contentLenght = conn.getContentLength();
		result.fileName = URL.substring(URL.lastIndexOf('/') + 1);

		return result;
	}

}
