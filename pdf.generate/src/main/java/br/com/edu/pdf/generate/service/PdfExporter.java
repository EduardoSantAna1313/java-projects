package br.com.edu.pdf.generate.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Map;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 * Classe para exportar para pdf.
 *
 * @author Eduardo
 */
public class PdfExporter {

	private final InputStream inputStream;

	private final Map<String, Object> parameters;

	private Connection conn;

	private JasperPrint jasperPrint;

	/**
	 * New instance of PdfExporter
	 *
	 * @param pInputReport
	 * @param pParams
	 */
	public PdfExporter(final InputStream pInputReport, final Map<String, Object> pParams) {
		super();
		this.inputStream = pInputReport;
		this.parameters = pParams;
	}

	/**
	 * New instance of PdfExporter
	 *
	 * @param pInputReport
	 * @param pParams
	 * @param conn
	 */
	public PdfExporter(final InputStream pInputReport, final Map<String, Object> pParams, final Connection conn) {
		super();
		this.inputStream = pInputReport;
		this.parameters = pParams;
		this.conn = conn;
	}

	/**
	 * Exporta para string em base 64 o relatório jasper.
	 *
	 * @param inputStream
	 *                        template jasper.
	 * @param parameters
	 *                        parâmetros do template.
	 * @param conn
	 *                        conexão com banco de dados.
	 *
	 * @return
	 *
	 * @throws JRException
	 * @throws IOException
	 */
	public byte[] export() throws JRException, IOException {

		if (conn == null) {
			jasperPrint = JasperFillManager.fillReport(inputStream, parameters, new JREmptyDataSource());
		} else {
			jasperPrint = JasperFillManager.fillReport(inputStream, parameters, conn);
		}

		try (final ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {

			// exporta relatório para outsputstream
			JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);

			return outputStream.toByteArray();
		}

	}

	public void showReport() {
		final JasperViewer jrviewer = new JasperViewer(jasperPrint, false);
		jrviewer.setVisible(true);
	}

}
