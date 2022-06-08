package br.com.edu.java.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

interface MyInterface {

	int doBla();

}

public class MainSimpleProxy {

	public static void main(final String[] args) {

		final MyInterface proxyInstance = (MyInterface) Proxy.newProxyInstance(MainSimpleProxy.class.getClassLoader(),
				new Class[] {
					MyInterface.class
				}, handler());

		System.out.println("testing...");
		System.out.println(proxyInstance.doBla());
	}

	private static InvocationHandler handler() {
		return (proxy, method, methodArgs) -> {

			if (method.getName().equals("doBla")) {
				return 42;
			} else if (method.getName().equals("put")) {
				return 10;
			} else {
				throw new UnsupportedOperationException("Unsupported method: " + method.getName());
			}

		};
	}

}
