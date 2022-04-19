/*
 * COPYRIGHT...
 */
package design.patterns.bridge.arquivo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class to Arquivo.
 *
 * @author Eduardo
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Arquivo {

	private String name;

	private String content;

}
