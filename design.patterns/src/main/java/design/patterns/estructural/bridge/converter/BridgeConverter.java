/*
 * COPYRIGHT...
 */
package design.patterns.estructural.bridge.converter;

import design.patterns.estructural.bridge.arquivo.Arquivo;

/**
 * Class to Converter.
 *
 * @author Eduardo
 */
public interface BridgeConverter {

	String convert(Arquivo arquivo);

}
