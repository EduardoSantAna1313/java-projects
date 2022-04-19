/*
 * COPYRIGHT...
 */
package br.com.edu.design.patterns.bridge;

import static design.patterns.bridge.converter.ConverterFactory.ConverterType.CSV;
import static design.patterns.bridge.converter.ConverterFactory.ConverterType.JSON;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import design.patterns.bridge.arquivo.Arquivo;
import design.patterns.bridge.converter.BridgeConverter;
import design.patterns.bridge.converter.ConverterFactory;

/**
 * Class to BridgeTest.
 *
 * @author Eduardo
 */
public class BridgeTest {

	private final Arquivo arquivo = new Arquivo("bla.txt", "AAAA1234==");

	@Test
	public void testCsv() {

		// In the bridge pattern we dont need to create all the implementations, like
		// ArquivoJsonConverter, ArquivoCsvConverter, ArquivoContabilJsonConverter,
		// ArquivoContabilCsvConverter....
		// The converter interface is the bridge between the implementations of arquivo
		// and converters.
		final BridgeConverter converter = ConverterFactory.get(CSV);
		assertEquals("" + arquivo.getName() + ";" + arquivo.getContent(), converter.convert(arquivo));
	}

	@Test
	public void testJson() {

		final String json = "{\n" + "  \"name\": \"bla.txt\",\n" + "  \"content\": \"AAAA1234\\u003d\\u003d\"\n" + "}";

		final BridgeConverter converter = ConverterFactory.get(JSON);
		assertEquals(json, converter.convert(arquivo));
	}

}
