package br.com.edu.pdf.generate;

import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Map;

import br.com.edu.pdf.generate.service.PdfExporter;

/**
 * @author Eduardo
 */
public class MainGeneratePdf {

	public static void main(final String[] args) throws Exception {

		// report
		final InputStream input = new FileInputStream("src/main/resources/teste.jasper");

		// report parameters
		final Map<String, Object> params = new HashMap<>();
		params.put("cnpjPrestador", "123.456.789.0001-001");
		params.put("razaoSocialPrestador", "Razão Social do prestador");

		final PdfExporter exporter = new PdfExporter(input, params);
		final byte[] bytes = exporter.export();

		exporter.showReport();

		Files.write(Path.of("src/main/resources/out.pdf"), bytes, StandardOpenOption.CREATE);
	}

}
