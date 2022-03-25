package br.com.edu.chain.resposability.handler.impl;

import java.util.List;

import br.com.edu.chain.resposability.business.bo.TesteBO;
import br.com.edu.chain.resposability.handler.ValidationHandler;

public class FkHandler extends ValidationHandler<TesteBO> {

	@Override
	public List<String> process(TesteBO bo) {
		if (bo.getFk1() == 0) {
			this.addMessage("Fk1 invalida");

			return this.getMessages();
		}

		return super.process(bo);
	}

}
