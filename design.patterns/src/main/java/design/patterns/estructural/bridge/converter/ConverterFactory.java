/*
 * COPYRIGHT...
 */
package design.patterns.estructural.bridge.converter;

import java.util.EnumMap;

import design.patterns.estructural.bridge.converter.impl.CsvConverter;
import design.patterns.estructural.bridge.converter.impl.JsonConverter;

/**
 * Class to ConverterFactory.
 *
 * @author Eduardo
 */
public class ConverterFactory {

	public enum ConverterType {
		CSV, JSON;
	}

	// simple memory factory
	private static final EnumMap<ConverterType, BridgeConverter> fac = new EnumMap<>(ConverterType.class);

	static {
		fac.put(ConverterType.CSV, new CsvConverter());
		fac.put(ConverterType.JSON, new JsonConverter());
	}

	public static BridgeConverter get(final ConverterType type) {
		return fac.get(type);
	}

}
