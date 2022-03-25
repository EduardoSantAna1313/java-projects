package br.com.edu.factory.factory.impl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import br.com.edu.factory.factory.AbstractFactory;
import br.com.edu.factory.servicoentrega.ServicoEntrega;
import br.com.edu.factory.servicoentrega.ServicoEntregaType;

public class MyFactory implements AbstractFactory {

	private final Map<String, ServicoEntrega> cache = new ConcurrentHashMap<>();

	@Override
	public ServicoEntrega getInstance(final String type) {
		return cache.get(type);
	}

	@Override
	public void register(final Class<? extends ServicoEntrega> service) {

		try {
			final ServicoEntregaType annotation = service.getAnnotation(ServicoEntregaType.class);

			final ServicoEntrega newInstance = service.getConstructor().newInstance();

			cache.put(annotation.value(), newInstance);
		} catch (final Exception e) {
			return;
		}

	}

}
