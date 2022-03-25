package br.com.edu.factory.factory;

import br.com.edu.factory.servicoentrega.ServicoEntrega;

public interface AbstractFactory {

	ServicoEntrega getInstance(String type);

	void register(Class<? extends ServicoEntrega> service);

}
