package design.patterns.composite;

import java.util.List;

/**
 * Composite example.
 *
 * @author Eduardo
 */
public class MainComposite {

	public static void main(final String[] args) {
		final IFileSystem f1 = new CFile("teste1.txt");
		final IFileSystem f2 = new CFile("teste2.txt");
		final IFileSystem f3 = new Folder("folder1", List.of(f1, f2));

		final IFileSystem f4 = new CFile("teste1.txt");
		final IFileSystem f5 = new Folder("folder2", List.of(f3, f4));

		final IFileSystem f6 = new CFile("teste2.txt");
		final IFileSystem f7 = new Folder("folder3", List.of(f5, f6));

		f7.print("");
	}

}
