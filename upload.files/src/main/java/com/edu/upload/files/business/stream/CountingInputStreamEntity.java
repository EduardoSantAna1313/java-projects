package com.edu.upload.files.business.stream;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.http.entity.ContentType;
import org.apache.http.entity.InputStreamEntity;

/**
 * Class to Counting Input Stream.
 *
 * @author Eduardo
 */
public class CountingInputStreamEntity extends InputStreamEntity {

	private UploadChangeListener listener;

	private final long length;

	public CountingInputStreamEntity(final InputStream instream, final long length, final ContentType contentType) {
		super(instream, length, contentType);
		this.length = length;
	}

	public void setUploadListener(final UploadChangeListener listener) {
		this.listener = listener;
	}

	@Override
	public void writeTo(final OutputStream outstream) throws IOException {
		super.writeTo(new CountingOutputStream(outstream));
	}

	class CountingOutputStream extends OutputStream {

		private long counter = 0L;

		private int lastPercent = 0;

		private final OutputStream outputStream;

		public CountingOutputStream(final OutputStream outputStream) {
			this.outputStream = outputStream;
		}

		@Override
		public void write(final int oneByte) throws IOException {
			this.outputStream.write(oneByte);
			counter++;

			calculatePercent();

		}

		@Override
		public void write(final byte[] b, final int off, final int len) throws IOException {
			outputStream.write(b, off, len);

			counter += len;

			calculatePercent();

		}

		private void calculatePercent() {

			if (listener != null) {
				final int percent = (int) (counter * 100 / length);

				if (lastPercent != percent) {
					listener.onChange(percent);
					lastPercent = percent;
				}

			}

		}

	}

}
