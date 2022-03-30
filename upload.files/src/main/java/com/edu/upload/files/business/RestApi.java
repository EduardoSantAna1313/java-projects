package com.edu.upload.files.business;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import com.edu.upload.files.business.stream.CountingInputStreamEntity;
import com.edu.upload.files.business.stream.UploadChangeListener;
import com.edu.upload.files.type.Environment;

/**
 * Class to RestApi.
 *
 * @author Eduardo
 */
public class RestApi {

	private static final HttpClient client = HttpClientBuilder.create().build();

	private final Environment environment;

	private final String token;

	private int timeout;

	public RestApi(final Environment environment) throws IOException {
		super();
		this.environment = environment;

		final TokenBusiness business = new TokenBusiness();
		token = business.auth(environment);
	}

	public RestApi timeout(final int timeout) {
		this.timeout = timeout;
		return this;
	}

	public ApiResponse get(final Integer pFileId) throws Exception {

		HttpEntity pageEntity = null;

		try {

			final RequestConfig config = RequestConfig.custom().setConnectionRequestTimeout(timeout)
					.setConnectTimeout(timeout).setSocketTimeout(timeout).build();

			final String url = environment.getUrl() + "/api/rest/system/tool/v1/download?id=" + pFileId;

			System.out.println(url);

			final HttpGet postPageRequest = new HttpGet(url);
			postPageRequest.setConfig(config);
			postPageRequest.addHeader("Accept", "application/json");
			postPageRequest.addHeader("content-type", "application/json");
			postPageRequest.addHeader("Authorization", token);
			postPageRequest.addHeader("Language", "pt_BR");
			postPageRequest.addHeader("SourceSystem", "APP");
			postPageRequest.addHeader("RoleID", String.valueOf(environment.getRoleId()));

			final HttpResponse postPageResponse = client.execute(postPageRequest);

			pageEntity = postPageResponse.getEntity();

			final var contentDisposition = postPageResponse.getHeaders("Content-Disposition");

			if (postPageResponse.getStatusLine().getStatusCode() == 500) {
				throw new IOException("Files não upado!");
			}

			if (contentDisposition.length == 0 || contentDisposition[0].getElements() == null
					|| contentDisposition[0].getElements()[0].getParameterCount() == 0) {

				throw new IOException(new String(pageEntity.getContent().readAllBytes()));
			}

			final String fileName = contentDisposition[0].getElements()[0].getParameter(0).getValue();

			final var is = pageEntity.getContent();

			return new ApiResponse(fileName, is, pageEntity.getContentLength());

		} finally {
			// EntityUtils.consume(pageEntity);
		}

	}

	public String post(final String fileName, final InputStream inputStream, final long contentLenght,
			final UploadChangeListener listener) throws IOException {

		HttpEntity pageEntity = null;

		try {

			final RequestConfig config = RequestConfig.custom().setConnectionRequestTimeout(timeout)
					.setConnectTimeout(timeout).setSocketTimeout(timeout).build();

			final String url = environment.getUrl() + "/api/rest/system/tool/v1/upload?name="
					+ URLEncoder.encode(fileName, StandardCharsets.UTF_8);

			System.out.println(url);

			final HttpPost postPageRequest = new HttpPost(url);
			postPageRequest.setConfig(config);
			postPageRequest.addHeader("Accept", "application/octet-stream");
			postPageRequest.addHeader("content-type", "application/octet-stream");
			postPageRequest.addHeader("content-lenght", String.valueOf(contentLenght));
			postPageRequest.addHeader("Authorization", token);
			postPageRequest.addHeader("Language", "pt_BR");
			postPageRequest.addHeader("SourceSystem", "APP");
			postPageRequest.addHeader("RoleID", String.valueOf(environment.getRoleId()));

			final CountingInputStreamEntity entity = new CountingInputStreamEntity(inputStream, contentLenght,
					ContentType.APPLICATION_OCTET_STREAM);
			postPageRequest.setEntity(entity);
			entity.setUploadListener(listener);

			final HttpResponse postPageResponse = client.execute(postPageRequest);

			pageEntity = postPageResponse.getEntity();

			return IOUtils.toString(pageEntity.getContent(), StandardCharsets.UTF_8);

		} finally {
			EntityUtils.consume(pageEntity);
		}

	}

}
