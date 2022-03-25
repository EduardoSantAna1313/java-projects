package br.com.edu.chain.resposability.handler.impl;

import java.util.List;
import java.util.function.Function;

import br.com.edu.chain.resposability.business.bo.TesteBO;
import br.com.edu.chain.resposability.handler.ValidationHandler;

public class NotNullHandler extends ValidationHandler<TesteBO> {

	@Override
	public List<String> process(TesteBO bo) {
		if (isNull(bo, TesteBO::getDescription)) {
			this.addMessage("Description null");
		}

		if (isNull(bo, TesteBO::getName)) {
			this.addMessage("Name null");
		}

		if (isNull(bo, TesteBO::getHelp)) {
			this.addMessage("Help null");
		}

		if (isNull(bo, TesteBO::getFk1)) {
			this.addMessage("fk1 null");
		}

		if (isNull(bo, TesteBO::getFk2)) {
			this.addMessage("fk2 null");
		}

		if (isNull(bo, TesteBO::getId)) {
			this.addMessage("id null");
		}

		if (!valid(bo)) {
			return this.getMessages();
		}

		return super.process(bo);
	}

	private boolean isNotNull(TesteBO bo, Function<TesteBO, Object> comp) {
		return comp.apply(bo) != null;
	}

	private boolean isNull(TesteBO bo, Function<TesteBO, Object> comp) {
		return comp.apply(bo) == null;
	}

	private boolean valid(TesteBO bo) {
		return isNotNull(bo, TesteBO::getDescription) && isNotNull(bo, TesteBO::getFk1)
				&& isNotNull(bo, TesteBO::getFk2) && isNotNull(bo, TesteBO::getHelp) && isNotNull(bo, TesteBO::getId)
				&& isNotNull(bo, TesteBO::getName);
	}

}
