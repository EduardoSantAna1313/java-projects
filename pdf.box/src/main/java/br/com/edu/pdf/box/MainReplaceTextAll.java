package br.com.edu.pdf.box;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import br.com.edu.pdf.box.service.PdfService;

/**
 * Replace de texto no pdf.
 *
 * @author Eduardo
 */
public class MainReplaceTextAll {

	private static final int MAX_FILES = 200;

	private static final String OUTPUT_DIR = "src/main/resources/out";

	public static void main(final String[] args) throws IOException {
		final var bytes = Files.readAllBytes(Path.of("src/main/resources/original.pdf"));

		for (int i = 0; i < MAX_FILES; i++) {

			final String replace = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"))
					+ String.format("%04d", i);

			final var map = loadReplaces(replace);

			final PdfService serv = new PdfService(bytes);
			serv.replaceText(map);

			final String fileName = "out" + i + ".pdf";
			serv.save(Path.of(OUTPUT_DIR, fileName));

			System.out.println(fileName + " salvo!");
		}

	}

	private static Map<String, String> loadReplaces(final String replace) throws IOException {

		final Properties props = new Properties();
		props.load(new FileInputStream("src/main/resources/replaces.properties"));
		final Map<String, String> result = new HashMap<>();
		props.forEach((k, v) -> {
			result.put((String) k, (String) v);
		});
		result.put("NUMERO_NOTA", replace);
		result.put("DATA_EMISSAO", LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/mm/yy HH:mm:ss")));
		return result;
	}

}
