package br.com.edu.aws.s3.storage;

import com.amazonaws.event.ProgressEvent;
import com.amazonaws.event.ProgressListener;

/**
 * Class to .
 *
 * @author Eduardo
 */
public class StorageProgressListener implements ProgressListener {

	private final long contentLenght;

	private long totalTransferred;

	public StorageProgressListener(final long contentLenght) {
		super();
		this.contentLenght = contentLenght;
	}

	@Override
	public void progressChanged(final ProgressEvent progressEvent) {
		totalTransferred += progressEvent.getBytesTransferred();

		final var percent = String.format("%.2f", (double) totalTransferred / contentLenght * 100d);

		System.out.println("Transferred: " + format(totalTransferred) + "\t" + percent + "%");
	}

	public static String format(final long bytes) {

		final int mb = 1024 * 1024;

		final int gb = mb * 1024;

		if (bytes < 1024) {
			return String.valueOf(bytes);
		} else if (bytes < mb) {
			final var result = (double) bytes / 1024;
			return String.format("%.2f kb", result);
		} else if (bytes < gb) {
			final var result = (double) bytes / mb;
			return String.format("%.2f MB", result);
		}

		final var result = (double) bytes / gb;
		return String.format("%.2f Gb", result);
	}

}
