package br.com.edu.junit.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.com.edu.junit.teste.java.MyBusiness;

/**
 * @author Eduardo
 */
public class TestCaseBusiness {

	private MyBusiness b;

	@Before
	public void before() {
		b = new MyBusiness();
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
