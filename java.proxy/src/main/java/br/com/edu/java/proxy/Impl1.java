package br.com.edu.java.proxy;

public class Impl1 implements MyInterface {

	@Override
	public int doBla() {
		System.out.println("Impl1");
		return 1;
	}

}
