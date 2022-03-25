package br.com.edu.template.generator;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import br.com.edu.template.generator.service.TemplateService;

/**
 * @author Eduardo
 */
public class MainTestTemplate {

	static class Person {

		String name;

		public Person(final String name) {
			super();
			this.name = name;
		}

	}

	public static void main(final String[] args) throws IOException {
		final TemplateService service = new TemplateService(MainTestTemplate.class, "templates");

		final Map<String, Object> params = new HashMap<>();
		params.put("class_name", MainTestTemplate.class.getSimpleName());
		params.put("list", Arrays.asList(new Person("Eduardo")));
		params.put("abc", 2);

		final String result = service.parse("template.ftl", params);

		System.out.println(result);
	}

}
