package br.com.edu.template.generator.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.Version;

/**
 * @author Eduardo
 */
public class TemplateService {

	private static final Configuration cfg = new Configuration(new Version("2.3.23"));

	public TemplateService(final Class<?> pClass, final String pPackage) {
		super();
		cfg.setLogTemplateExceptions(false);
		cfg.setDefaultEncoding("UTF-8");
		cfg.setClassForTemplateLoading(pClass, pPackage);
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
	}

	public String parse(final String pTemplateName, final Map<String, Object> pParams) throws IOException {

		final Template template = cfg.getTemplate(pTemplateName);

		try (final StringWriter writer = new StringWriter()) {
			template.process(pParams, writer);
			return writer.getBuffer().toString();
		} catch (final TemplateException error) {
			final StringWriter stringWriter = new StringWriter();
			final PrintWriter printWriter = new PrintWriter(stringWriter);
			error.printStackTrace(printWriter);
			throw new IOException(error.getMessage());
		}

	}

}