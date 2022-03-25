package br.com.edu.chain.resposability.handler.impl;

import java.util.List;
import java.util.function.Function;

import br.com.edu.chain.resposability.business.bo.TesteBO;
import br.com.edu.chain.resposability.handler.ValidationHandler;

public class NotNullOrEmptyHandler extends ValidationHandler<TesteBO> {

	@Override
	public List<String> process(TesteBO bo) {
		if (isNullOrEmpty(bo, TesteBO::getDescription)) {
			this.addMessage("Description null or empty");
		}

		if (isNullOrEmpty(bo, TesteBO::getName)) {
			this.addMessage("Name null or empty");
		}

		if (isNullOrEmpty(bo, TesteBO::getHelp)) {
			this.addMessage("Help null or empty");
		}

		if (isNullOrEmpty(bo, TesteBO::getFk1)) {
			this.addMessage("fk1 null or empty");
		}

		if (isNullOrEmpty(bo, TesteBO::getFk2)) {
			this.addMessage("fk2 null or empty");
		}

		if (isNullOrEmpty(bo, TesteBO::getId)) {
			this.addMessage("id null or empty");
		}

		if (!valid(bo)) {
			return this.getMessages();
		}

		return super.process(bo);
	}

	private boolean isNullOrEmpty(TesteBO bo, Function<TesteBO, Object> comp) {
		return comp.apply(bo) == null || comp.apply(bo).toString().isEmpty();
	}

	private boolean isNotNullOrEmpty(TesteBO bo, Function<TesteBO, Object> comp) {
		return !isNullOrEmpty(bo, comp);
	}

	private boolean valid(TesteBO bo) {
		return isNotNullOrEmpty(bo, TesteBO::getDescription) && isNotNullOrEmpty(bo, TesteBO::getFk1)
				&& isNotNullOrEmpty(bo, TesteBO::getFk2) && isNotNullOrEmpty(bo, TesteBO::getHelp)
				&& isNotNullOrEmpty(bo, TesteBO::getId) && isNotNullOrEmpty(bo, TesteBO::getName);
	}

}
