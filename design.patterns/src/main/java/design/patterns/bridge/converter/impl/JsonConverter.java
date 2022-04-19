/*
 * COPYRIGHT...
 */
package design.patterns.bridge.converter.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import design.patterns.bridge.arquivo.Arquivo;
import design.patterns.bridge.converter.BridgeConverter;

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
