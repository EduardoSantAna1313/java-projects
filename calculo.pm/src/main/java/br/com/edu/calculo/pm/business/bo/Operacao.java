package br.com.edu.calculo.pm.business.bo;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.Locale;

public class Operacao {

	private LocalDate data;

	private int qtde;

	private BigDecimal valor;

	public Operacao(LocalDate data, int qtde, BigDecimal valor) {
		super();
		this.data = data;
		this.qtde = qtde;
		this.valor = valor;
	}

	public int getQtde() {
		return qtde;
	}

	public void setQtde(int qtde) {
		this.qtde = qtde;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	@Override
	public String toString() {
		return data + "\tPM = " + qtde + " x R$ "
				+ NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(valor);
	}

}