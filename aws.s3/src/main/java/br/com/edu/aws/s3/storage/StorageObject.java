package br.com.edu.aws.s3.storage;

import java.io.File;
import java.io.InputStream;
import java.util.Date;

public class StorageObject {

	private String bucket;

	private String key;

	private File file;

	private InputStream is;

	private long contentLength;

	private String md5;

	private String eTag;

	private Date updated;

	public StorageObject() {
		super();
	}

	public StorageObject(final String bucket, final String key, final File file) {
		super();
		this.bucket = bucket;
		this.key = key;
		this.file = file;
	}

	public StorageObject(final String bucket, final String key, final InputStream is, final long contentLength) {
		super();
		this.bucket = bucket;
		this.key = key;
		this.is = is;
		this.contentLength = contentLength;
	}

	public String getBucket() {
		return bucket;
	}

	public void setBucket(final String bucket) {
		this.bucket = bucket;
	}

	public String getKey() {
		return key;
	}

	public void setKey(final String key) {
		this.key = key;
	}

	public File getFile() {
		return file;
	}

	public void setFile(final File file) {
		this.file = file;
	}

	public InputStream getIs() {
		return is;
	}

	public void setIs(final InputStream is) {
		this.is = is;
	}

	public long getContentLength() {
		return contentLength;
	}

	public void setContentLength(final long contentLength) {
		this.contentLength = contentLength;
	}

	public String getMd5() {
		return md5;
	}

	public void setMd5(final String md5) {
		this.md5 = md5;
	}

	public String geteTag() {
		return eTag;
	}

	public void seteTag(final String eTag) {
		this.eTag = eTag;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(final Date updated) {
		this.updated = updated;
	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("StorageObject [\n\tbucket: ");
		builder.append(bucket);
		builder.append(",\n\tkey: ");
		builder.append(key);
		builder.append(",\n\tcontentLength: ");
		builder.append(contentLength);
		builder.append(",\n\teTag: ");
		builder.append(eTag);
		builder.append(",\n\tupdated: ");
		builder.append(updated);
		builder.append("\n]");
		return builder.toString();
	}

	public StorageObject copy() {
		final StorageObject result = new StorageObject();
		result.bucket = this.bucket;
		result.contentLength = this.contentLength;
		result.eTag = this.eTag;
		result.file = this.file;
		result.is = this.is;
		result.key = this.key;
		result.md5 = this.md5;

		if (this.getUpdated() != null) {
			result.updated = new Date(this.updated.getTime());
		}

		return result;
	}

}
