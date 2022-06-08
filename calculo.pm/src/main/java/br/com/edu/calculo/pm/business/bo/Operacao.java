package br.com.edu.calculo.pm.business.bo;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.Locale;

public class Operacao {

	private final LocalDate data;

	private int qtde;

	private BigDecimal valor;

	public Operacao(final LocalDate data, final int qtde, final BigDecimal valor) {
		super();
		this.data = data;
		this.qtde = qtde;
		this.valor = valor;
	}

	public int getQtde() {
		return qtde;
	}

	public void setQtde(final int qtde) {
		this.qtde = qtde;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(final BigDecimal valor) {
		this.valor = valor;
	}

	public LocalDate getData() {
		return data;
	}

	@Override
	public String toString() {
		return data + "\tPM = " + qtde + " x R$ "
				+ NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(valor);
	}

}