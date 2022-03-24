package br.com.edu.download.nfse.curitiba.service.dto;

import java.io.FileInputStream;
import java.util.Properties;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

/**
 * DTO para o request de Download.
 *
 * @author Eduardo
 */
public class RequestFilter {

	/**
	 * String - cod.
	 */
	@SerializedName("CodigoVerificacao")
	private String cod;

	/**
	 * String - doc.
	 */
	@SerializedName("DocumentoPrestador")
	private String doc;

	/**
	 * Integer - num.
	 */
	@SerializedName("NumeroNotaFiscal")
	private Integer num;

	/**
	 * Create a new instance of RequestBO
	 */
	public RequestFilter() {
		super();
	}

	/**
	 * Create a new instance of RequestBO
	 *
	 * @param pCodigo
	 * @param pDocumento
	 * @param pNumero
	 */
	public RequestFilter(final String pCodigo, final String pDocumento, final Integer pNumero) {
		super();
		this.cod = pCodigo;
		this.doc = pDocumento;
		this.num = pNumero;
	}

	/**
	 * Retrieve the value of cod.
	 *
	 * @return the cod
	 */
	public String getCod() {
		return cod;
	}

	/**
	 * Set a new value to cod.
	 *
	 * @param cod
	 *                the cod to set
	 */
	public void setCod(final String cod) {
		this.cod = cod;
	}

	/**
	 * Retrieve the value of doc.
	 *
	 * @return the doc
	 */
	public String getDoc() {
		return doc;
	}

	/**
	 * Set a new value to doc.
	 *
	 * @param doc
	 *                the doc to set
	 */
	public void setDoc(final String doc) {
		this.doc = doc;
	}

	/**
	 * Retrieve the value of num.
	 *
	 * @return the num
	 */
	public Integer getNum() {
		return num;
	}

	/**
	 * Set a new value to num.
	 *
	 * @param num
	 *                the num to set
	 */
	public void setNum(final Integer num) {
		this.num = num;
	}

	/**
	 * Cria nome de arquivo.
	 *
	 * @return
	 */
	public String createFileName() {
		return num + "_" + doc;
	}

	/**
	 * Returns the default configuration.
	 *
	 * @return
	 */
	public static RequestFilter getDefault() {

		try (final var is = new FileInputStream("src/main/resources/params.properties");) {

			final Properties props = new Properties();
			props.load(is);

			return new RequestFilter(props.getProperty("cod"), props.getProperty("doc"),
					Integer.valueOf(props.getProperty("num")));
		} catch (final Exception errir) {
			throw new RuntimeException(errir.getMessage());
		}

	}

	/**
	 * /* (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new Gson().toJson(this);
	}

}