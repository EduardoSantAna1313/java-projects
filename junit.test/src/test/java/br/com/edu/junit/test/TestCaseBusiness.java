package br.com.edu.junit.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.com.edu.junit.teste.java.Business;

/**
 * @author Eduardo
 */
public class TestCaseBusiness {

	private Business b;

	@Before
	public void before() {
		b = new Business();
	}

	@Test(timeout = 1)
	public void validaListNull() throws Exception {
		final List<String> list = b.getList();
		assertNotNull(list);
	}

	@Test
	public void validaList() {
		final List<String> list = b.getList();
		assertFalse(list.isEmpty());
	}

}
