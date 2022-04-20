/*
 * COPYRIGHT...
 */
package design.patterns.estructural.bridge.converter.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import design.patterns.estructural.bridge.arquivo.Arquivo;
import design.patterns.estructural.bridge.converter.BridgeConverter;

/**
 * Class to JsonConverter.
 *
 * @author Eduardo
 */
public class JsonConverter implements BridgeConverter {

	private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

	@Override
	public String convert(final Arquivo arquivo) {
		return gson.toJson(arquivo);
	}

}
