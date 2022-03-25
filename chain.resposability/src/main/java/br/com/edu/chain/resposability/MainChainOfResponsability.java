package br.com.edu.chain.resposability;

import java.util.Arrays;
import java.util.List;

import br.com.edu.chain.resposability.business.bo.TesteBO;
import br.com.edu.chain.resposability.handler.ValidationHandler;
import br.com.edu.chain.resposability.handler.impl.EnumHandler;
import br.com.edu.chain.resposability.handler.impl.FkHandler;
import br.com.edu.chain.resposability.handler.impl.NotNullHandler;
import br.com.edu.chain.resposability.handler.impl.NotNullOrEmptyHandler;
import br.com.edu.chain.resposability.handler.impl.SizeHandler;

/**
 * Exemplo de Chain Of Responsability para validações de campos
 * obrigatórios.
 *
 * @author Eduardo
 */
public class MainChainOfResponsability {

	public static void main(final String[] args) {

		var handler = getHandler();

		final var bo = teste1();
		System.out.println("Teste 1");
		System.out.println("\t" + handler.process(bo));

		handler = getHandler();
		teste2(bo);
		System.out.println("Teste 2");
		System.out.println("\t" + handler.process(bo));

		teste3(bo);
		handler = getHandler();
		System.out.println("Teste 3");
		System.out.println("\t" + handler.process(bo));

		teste4(bo);
		handler = getHandler();
		System.out.println("Teste 4");
		System.out.println("\t" + handler.process(bo));
	}

	private static void teste4(final TesteBO bo) {
		bo.setDescription("a");
		bo.setFk1(3);
		bo.setFk2(2);
		bo.setHelp("das");
		bo.setId(1);
		bo.setName("dsa");
	}

	private static void teste3(final TesteBO bo) {
		bo.setDescription("a");
		bo.setFk1(0);
		bo.setFk2(0);
		bo.setHelp("das");
		bo.setId(0);
		bo.setName("dsa");
	}

	private static void teste2(final TesteBO bo) {
		bo.setDescription("teste");
		bo.setFk1(0);
		bo.setFk2(0);
		bo.setHelp("");
		bo.setId(0);
		bo.setName("");
	}

	private static TesteBO teste1() {
		final var bo = new TesteBO();
		bo.setDescription(null);
		bo.setFk1(0);
		bo.setFk2(0);
		bo.setHelp("");
		bo.setId(0);
		bo.setName("");
		return bo;
	}

	private static ValidationHandler<TesteBO> getHandler() {
		final List<ValidationHandler<TesteBO>> handles = Arrays.asList(
				// 1
				new NotNullHandler(),
				// 2
				new NotNullOrEmptyHandler(),
				// 3
				new FkHandler(),
				// 4
				new SizeHandler(),
				// 5
				new EnumHandler()
		// Fim
		);

		for (int i = 0; i < handles.size() - 1; i++) {
			handles.get(i).setNext(handles.get(i + 1));
		}

		return handles.get(0);
	}

}
