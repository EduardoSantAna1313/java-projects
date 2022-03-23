package br.com.edu.java.proxy;

public class Impl2 implements MyInterface {

	@Override
	public int doBla() {
		System.out.println("Impl2");
		return 2;
	}

}
