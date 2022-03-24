package br.com.edu.download.nfse.curitiba.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.text.StringEscapeUtils;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;

import br.com.edu.download.nfse.curitiba.service.dto.RequestFilter;

import org.jsoup.Jsoup;

/**
 * Class to .
 *
 * @author Eduardo
 */
public class DanfseDownloadService {

	/**
	 * String - URL.
	 */
	private static final String URL = "https://isscuritiba.curitiba.pr.gov.br/NotaCuritibana/NotaRPS/AutenticidadeNotaImagemNfse";

	/**
	 * Realiza o download.
	 *
	 * @param pCodigo
	 * @param pDocumento
	 * @param pNumero
	 *
	 * @return
	 *
	 * @throws Exception
	 */
	public byte[] download(final String pCodigo, final String pDocumento, final Integer pNumero) throws Exception {
		return download(new RequestFilter(pCodigo, pDocumento, pNumero));
	}

	/**
	 * Realiza o download.
	 *
	 * @return
	 *
	 * @throws Exception
	 */
	public byte[] download(final RequestFilter pRequest) throws Exception {

		final Response responsePOST = Jsoup.connect(URL).method(Method.POST)
				// headers
				.header("Content-Type", "application/json")
				// body
				.requestBody(pRequest.toString())
				// call
				.execute();

		final Pattern pattern = Pattern.compile(".*?src=\"data:image\\/png;base64, (?<base64>(.+?))\"");

		final Matcher matcher = pattern.matcher(responsePOST.body());

		if (matcher.find()) {

			final String base64 = matcher.group("base64");

			return StringEscapeUtils.unescapeHtml4(base64).getBytes();
		}

		return new byte[0];
	}

}
