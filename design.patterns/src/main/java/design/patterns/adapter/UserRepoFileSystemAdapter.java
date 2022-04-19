/*
 * COPYRIGHT...
 */
package design.patterns.adapter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import design.patterns.adapter.core.hexagonal.entity.User;
import design.patterns.adapter.core.hexagonal.repository.UserRepository;

/**
 * Adapter to userrepository.
 *
 * @author Eduardo
 */
public class UserRepoFileSystemAdapter implements UserRepository {

	private static final String SAVE_FOLDER = "src/main/resources/users";

	@Override
	public void save(final User user) {

		final File file = Path.of(SAVE_FOLDER, user.getName() + ".ser").toFile();

		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}

		try (final var fos = new FileOutputStream(file); final ObjectOutputStream os = new ObjectOutputStream(fos);) {

			os.writeObject(user);
			os.flush();

		} catch (final IOException error) {
			error.printStackTrace();
		}

	}

	@Override
	public List<User> listAll() {

		final List<User> users = new ArrayList<>();

		try (var s = Files.list(Path.of(SAVE_FOLDER))) {
			s.forEach(f -> users.add(read(f)));
		} catch (final IOException error) {
			error.printStackTrace();
		}

		return users;
	}

	private User read(final Path p) {

		try (FileInputStream is = new FileInputStream(p.toFile()); ObjectInputStream ois = new ObjectInputStream(is);

		) {
			return (User) ois.readObject();
		} catch (final IOException e) {
			e.printStackTrace();
		} catch (final ClassNotFoundException error) {
			error.printStackTrace();
		}

		return null;
	}

}
