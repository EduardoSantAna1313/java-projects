package br.com.edu.factory;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import br.com.edu.factory.factory.AbstractFactory;
import br.com.edu.factory.factory.impl.MyFactory;
import br.com.edu.factory.servicoentrega.ServicoEntrega;
import br.com.edu.factory.servicoentrega.impl.Amazon;

public class MainFactory {

	public static void main(final String[] args) {

		final AbstractFactory factory = new MyFactory();
		final List<Class<ServicoEntrega>> listClasses = listClasses("teste/");

		// registras as classes compiladas .class
		for (final Class<ServicoEntrega> classEntrega : listClasses) {
			factory.register(classEntrega);
		}

		factory.register(Amazon.class);

		factory.getInstance("correio").entregar("carta");
		factory.getInstance("ml").entregar("carta");
		factory.getInstance("amazon").entregar("carta");
	}

	public static List<Class<ServicoEntrega>> listClasses(final String classesPath) {

		final File parentPath = new File(classesPath);

		try {
			return listClasses(parentPath);
		} catch (final Exception e) {
			return Collections.emptyList();
		}

	}

	private static List<Class<ServicoEntrega>> listClasses(final File parentPath)
			throws IOException, ClassNotFoundException {
		final List<String> classes = listClassNames(parentPath);

		if (classes.isEmpty()) {
			return Collections.emptyList();
		}

		final URL url = parentPath.toURI().toURL();

		try (final URLClassLoader cl = new URLClassLoader(new URL[] {
			url
		})) {
			return listClasses(classes, cl);
		}

	}

	private static List<String> listClassNames(final File parentPath) {

		try (final Stream<Path> find = Files.find(parentPath.toPath(), 10, MainFactory::onlyFiles)) {
			return find.map(p -> getClassName(parentPath, p)).collect(Collectors.toList());
		} catch (final IOException error) {
			return Collections.emptyList();
		}

	}

	private static boolean onlyFiles(final Path path, final BasicFileAttributes attr) {
		return !attr.isDirectory();
	}

	private static List<Class<ServicoEntrega>> listClasses(final List<String> classes, final URLClassLoader classLoader)
			throws IOException, ClassNotFoundException {
		final List<Class<ServicoEntrega>> list = new ArrayList<>();

		for (final String path : classes) {
			final Class<ServicoEntrega> loadClass = getClass(classLoader, path);

			if (loadClass != null) {
				list.add(loadClass);
			}

		}

		classLoader.close();
		return list;
	}

	@SuppressWarnings("unchecked")
	private static Class<ServicoEntrega> getClass(final URLClassLoader classLoader, final String className)
			throws ClassNotFoundException {

		final Class<?> loadClass = classLoader.loadClass(className);

		if (ServicoEntrega.class.isAssignableFrom(loadClass)) {
			return (Class<ServicoEntrega>) loadClass;
		}

		return null;
	}

	private static String getClassName(final File parentPath, final Path path) {
		return path.toFile().getAbsolutePath().replace(parentPath.getAbsolutePath(), "").replace("/", ".").substring(1)
				.replace(".class", "");
	}

}
