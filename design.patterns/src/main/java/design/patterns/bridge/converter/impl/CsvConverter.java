/*
 * COPYRIGHT...
 */
package design.patterns.bridge.converter.impl;

import java.util.StringJoiner;

import design.patterns.bridge.arquivo.Arquivo;
import design.patterns.bridge.converter.BridgeConverter;

/**
 * Class to CsvConverter.
 *
 * @author Eduardo
 */
public class CsvConverter implements BridgeConverter {

	@Override
	public String convert(final Arquivo arquivo) {
		final StringJoiner joiner = new StringJoiner(";");
		joiner.add(arquivo.getName());
		joiner.add(arquivo.getContent());
		return joiner.toString();
	}

}
