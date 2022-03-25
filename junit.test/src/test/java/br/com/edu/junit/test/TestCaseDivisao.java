package br.com.edu.junit.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.edu.junit.teste.java.MyBusiness;

/**
 * Test Case para Divisao.
 *
 * @author Eduardo
 */
public class TestCaseDivisao {

	/**
	 * Tolerância de erro do cálculo.
	 */
	private static final double TOLERANCIA = 0.1;

	// define timeout máximo
	@Test(timeout = 1)
	public void validaTimeout() throws Exception {
		final double div = MyBusiness.dividir(10, 1);
		assertEquals("", 10, div, 0.1);
	}

	// define exception esperada
	@Test(expected = ArithmeticException.class)
	public void validaException() throws Exception {
		final double div = MyBusiness.dividir(10, 0);
		assertEquals("", 10, div, 0.1);
	}

	@Test
	public void validaDivisao() throws Exception {
		final double div = MyBusiness.dividir(10, 1);
		assertEquals("", 10, div, TOLERANCIA);
	}

}
