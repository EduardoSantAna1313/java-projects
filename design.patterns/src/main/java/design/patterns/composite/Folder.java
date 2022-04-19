/*
 * COPYRIGHT...
 */
package design.patterns.composite;

import java.util.List;

/**
 * Class to .
 *
 * @author Eduardo
 */
public class Folder implements IFileSystem {

	private final String name;

	private final List<IFileSystem> children;

	public Folder(final String name, final List<IFileSystem> children) {
		super();
		this.name = name;
		this.children = children;
	}

	@Override
	public void print(final String structure) {
		System.out.println(structure + name);

		for (final IFileSystem c : children) {
			c.print(structure + "| ");
		}

	}

}
