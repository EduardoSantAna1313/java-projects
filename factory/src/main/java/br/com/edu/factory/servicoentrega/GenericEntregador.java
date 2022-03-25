package br.com.edu.factory.servicoentrega;

public class GenericEntregador implements ServicoEntrega {

	@Override
	public void entregar(final String encomenda) {
		System.out.println(this.getClass().getSimpleName() + " entregou " + encomenda);
	}

}
