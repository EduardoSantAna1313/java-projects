package br.com.edu.java.proxy;

import java.lang.reflect.Proxy;

public class MainProxy {
	public static void main(String[] args) {

		MyInterface proxyInstance = (MyInterface) Proxy.newProxyInstance(MainProxy.class.getClassLoader(),
				new Class[] { MyInterface.class }, (proxy, method, methodArgs) -> {
					if (method.getName().equals("doBla")) {
						return 42;
					} else if (method.getName().equals("put")) {
						return 10;
					} else {
						throw new UnsupportedOperationException("Unsupported method: " + method.getName());
					}
				});

		System.out.println(proxyInstance.doBla());
	}

}
