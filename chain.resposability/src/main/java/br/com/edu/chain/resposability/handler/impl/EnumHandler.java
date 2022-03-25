package br.com.edu.chain.resposability.handler.impl;

import java.util.List;

import br.com.edu.chain.resposability.business.bo.TesteBO;
import br.com.edu.chain.resposability.handler.ValidationHandler;

public class EnumHandler extends ValidationHandler<TesteBO> {

	@Override
	public List<String> process(TesteBO bo) {
		return super.process(bo);
	}

}