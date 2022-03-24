package com.edu.upload.files.business;

import java.io.InputStream;

/**
 * Class to Api Response.
 *
 * @author Eduardo
 */
public class ApiResponse {

	private String fileName;

	private InputStream content;

	private long contentSize;

	/**
	 * New instance of ApiResponse
	 */
	public ApiResponse() {
		super();
	}

	/**
	 * New instance of ApiResponse
	 *
	 * @param fileName
	 * @param content
	 * @param contentSize
	 */
	public ApiResponse(final String fileName, final InputStream content, final long contentSize) {
		super();
		this.fileName = fileName;
		this.content = content;
		this.contentSize = contentSize;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(final String fileName) {
		this.fileName = fileName;
	}

	public InputStream getContent() {
		return content;
	}

	public void setContent(final InputStream content) {
		this.content = content;
	}

	public long getContentLength() {
		return contentSize;
	}

	public void setContentSize(final long contentSize) {
		this.contentSize = contentSize;
	}

}