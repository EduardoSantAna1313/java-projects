package br.com.edu.junit.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

import br.com.edu.junit.teste.java.ObjectBusiness;
import br.com.edu.junit.teste.java.ObjectDTO;

/**
 * Testes usando mocks do Mockito.
 *
 * @author Eduardo
 */
public class TestMocks {

	@Test
	public void testIdIsZero() {

		// mock do dto, a classe ObjectDTO nao possui um construtor padrão!!!
		final var dto = mock(ObjectDTO.class);

		assertNotNull(dto);

		// força retornar 0 quando o método getId for chamado
		when(dto.getId()).thenReturn(0);
		assertTrue(ObjectBusiness.isZero(dto));
	}

	@Test
	public void testNameIsJava() {

		final var dto = mock(ObjectDTO.class);

		assertNotNull(dto);

		when(dto.getName()).thenReturn("JAVA");

		assertTrue(ObjectBusiness.isJava(dto));

		when(dto.getName()).thenReturn("JaVa");

		assertTrue(ObjectBusiness.isJava(dto));
	}

}
