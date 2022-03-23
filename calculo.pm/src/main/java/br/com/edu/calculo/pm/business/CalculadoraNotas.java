package br.com.edu.calculo.pm.business;

import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import br.com.edu.calculo.pm.business.bo.Acao;

/**
 * Calculadora.
 *
 * @author Eduardo
 */
public class CalculadoraNotas {

	private static final Logger LOGGER = Logger.getLogger(CalculadoraNotas.class);

	private final Map<String, Acao> map = Collections.synchronizedSortedMap(new TreeMap<String, Acao>());

	private final Path path;

	public CalculadoraNotas(final Path path) {
		super();
		this.path = path;

		final var props = new Properties();
		try {
			props.load(new FileInputStream("src/main/resources/log4j2.properties"));
		} catch (final Exception error) {
			error.printStackTrace();
		}
		PropertyConfigurator.configure(props);
	}

	public Map<String, Acao> calcular() throws IOException {

		LOGGER.info("Iniciando o calculo das notas de corretagem do diretório " + path);

		try (var st = Files.list(path)) {
			st.sorted().forEach(this::processar);
		}
		return map;
	}

	private void processar(final Path nota) {

		LOGGER.info("Processando a nota " + path);

		try (final var document = PDDocument.load(nota.toFile())) {

			final var text = new PDFTextStripper().getText(document);

			var regex = ".*?Praça C.V Tipo Mercado  Especificação do Título  OBS.{0,4}  Quantidade  Preço Liquidação .{0,4}  Compra.{0,3}Venda .{0,4}  D.?C";
			regex += "\n(.+?)";
			regex += "\nResumo dos Negócios";
			final var p = Pattern.compile(regex, Pattern.MULTILINE | Pattern.DOTALL);
			final var matcher = p.matcher(text);

			final var date = getDate(text);

			if (matcher.find()) {
				processarTexto(matcher.group(1), date);
			}

		} catch (final IOException error) {
			error.printStackTrace();
		}
	}

	private LocalDate getDate(final String text) {

		final var patterb = Pattern.compile("Data pregão: (?<date>(.+?))   Nº Nota.*?");

		final var matcher = patterb.matcher(text);

		if (matcher.find()) {

			final var group = matcher.group("date").trim();

			return LocalDate.parse(group, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		}

		return null;
	}

	private void processarTexto(final String text, final LocalDate date) {

		final var pattern = Pattern.compile(
				"  1-Bovespa C  VIS  (?<ticket>([A-Z0-9]{5,6})).+?\s(?<qtde>([0-9]*?))  (?<valoruni>([\\d\\,\\.]*?))  (?<valortot>([\\d\\,\\.]*?))  D",
				Pattern.MULTILINE | Pattern.DOTALL);

		final var matcher = pattern.matcher(text);

		while (matcher.find()) {
			final var ticket = matcher.group("ticket");
			final int qtde = Integer.valueOf(matcher.group("qtde"));

			final var valor = new BigDecimal(matcher.group("valoruni").replace(".", "").replace(",", "."));

			final var acao = map.getOrDefault(ticket, new Acao(ticket));
			acao.addOperacao(date, qtde, valor);
			map.put(ticket, acao);
		}

	}
}
