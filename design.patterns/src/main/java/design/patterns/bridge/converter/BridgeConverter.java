/*
 * COPYRIGHT...
 */
package design.patterns.bridge.converter;

import design.patterns.bridge.arquivo.Arquivo;

/**
 * Class to Converter.
 *
 * @author Eduardo
 */
public interface BridgeConverter {

	String convert(Arquivo arquivo);

}
