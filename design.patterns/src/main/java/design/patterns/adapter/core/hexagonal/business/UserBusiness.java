package design.patterns.adapter.core.hexagonal.business;

import java.util.List;
import java.util.function.Function;

import design.patterns.adapter.core.hexagonal.entity.User;
import design.patterns.adapter.core.hexagonal.repository.UserRepository;

public class UserBusiness {

	private final UserRepository userRepo;

	public UserBusiness(final UserRepository userRepo) {
		super();
		this.userRepo = userRepo;
	}

	public void save(final User u) {

		if (isNotValid(User::getEmail, u)) {
			throw new IllegalStateException("the email is mandatory");
		}

		if (isNotValid(User::getName, u)) {
			throw new IllegalStateException("the name is mandatory");
		}

		if (isNotValid(User::getPassword, u)) {
			throw new IllegalStateException("the password is mandatory");
		}

		userRepo.save(u);
	}

	public List<User> listAll() {
		return userRepo.listAll();
	}

	private boolean isNotValid(final Function<User, String> function, final User user) {
		return function.apply(user) == null || function.apply(user).isBlank();
	}

}
