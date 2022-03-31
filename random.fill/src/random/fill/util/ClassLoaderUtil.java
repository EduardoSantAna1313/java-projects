/*
 * COPYRIGHT...
 */
package random.fill.util;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import random.fill.populator.generator.AbstractGenerator;

/**
 * Class to .
 *
 * @author Eduardo
 */
public class ClassLoaderUtil {

	private ClassLoaderUtil() {
		// NA
	}

	public static List<AbstractGenerator> loadGenerators() {

		final List<AbstractGenerator> list = new ArrayList<>();

		final var classes = ClassLoaderUtil.getClasses("random.fill.populator.generator.impl");

		for (final var clazz : classes) {

			final Class<AbstractGenerator> cazzGen = clazz;

			list.add(newInstance(cazzGen));
		}

		return list;
	}

	private static AbstractGenerator newInstance(final Class<AbstractGenerator> cazzGen) {

		try {
			return cazzGen.getConstructor().newInstance();
		} catch (final Exception error) {
			error.printStackTrace();

			return null;
		}

	}

	private static List<Class<AbstractGenerator>> getClasses(final String packageName) {

		try {

			final ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			assert classLoader != null;
			final String path = packageName.replace('.', '/');
			final Enumeration<URL> resources = classLoader.getResources(path);
			final List<File> dirs = new ArrayList<>();

			while (resources.hasMoreElements()) {
				final URL resource = resources.nextElement();
				dirs.add(new File(resource.getFile()));
			}

			final ArrayList<Class<AbstractGenerator>> classes = new ArrayList<>();

			for (final File directory : dirs) {
				classes.addAll(findClasses(directory, packageName));
			}

			return classes;
		} catch (final Exception e) {
			return Collections.emptyList();
		}

	}

	private static List<Class<AbstractGenerator>> findClasses(final File directory, final String packageName)
			throws ClassNotFoundException {
		final List<Class<AbstractGenerator>> classes = new ArrayList<>();

		if (!directory.exists()) {
			return classes;
		}

		final File[] files = directory.listFiles();

		for (final File file : files) {

			if (file.isDirectory()) {
				assert !file.getName().contains(".");
				classes.addAll(findClasses(file, packageName + "." + file.getName()));
			} else if (file.getName().endsWith(".class")) {

				@SuppressWarnings("unchecked")
				final Class<AbstractGenerator> forName = (Class<AbstractGenerator>) Class
						.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6));
				classes.add(forName);
			}

		}

		return classes;
	}

}
