/*
 * COPYRIGHT...
 */
package design.patterns.composite;

/**
 * Class to .
 *
 * @author Eduardo
 */
public class CFile implements IFileSystem {

	private final String name;

	public CFile(final String name) {
		super();
		this.name = name;
	}

	@Override
	public void print(final String structure) {
		System.out.println(structure + name);
	}

}
