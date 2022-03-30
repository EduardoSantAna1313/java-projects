package com.edu.upload.files.type;

import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Environment {

	private final String key;

	private String url;

	private String user;

	private String password;

	private Integer roleId;

	public Environment(final Environment e) {
		super();
		key = e.toString();
		url = e.getUrl();
		password = e.getPassword();
		user = e.getUser();
		roleId = e.getRoleId();
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(final String url) {
		this.url = url;
	}

	public String getUser() {
		return user;
	}

	public void setUser(final String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(final Integer roleId) {
		this.roleId = roleId;
	}

	public String getKey() {
		return key;
	}

	public static Environment load(final String key) {

		try {
			final Gson gson = new Gson();

			final Type type = new TypeToken<List<Environment>>() {
			}.getType();

			final String json = Files.readString(Path.of("resources/environments.json"));

			final List<Environment> list = gson.fromJson(json, type);

			return list.stream().filter(e -> e.getKey().trim().equals(key)).findFirst().orElse(null);
		} catch (final Exception error) {
			return null;
		}

	}

}