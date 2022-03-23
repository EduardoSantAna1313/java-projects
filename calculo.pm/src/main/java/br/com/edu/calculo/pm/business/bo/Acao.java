package br.com.edu.calculo.pm.business.bo;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * Representa uma Ação..
 *
 * @author Eduardo
 */
public class Acao {

	private String ticket;

	private int qtde;

	private List<Operacao> operacoes;

	public Acao(final String ticket) {
		super();
		this.ticket = ticket;
	}

	public String getTicket() {
		return ticket;
	}

	public void setTicket(final String ticket) {
		this.ticket = ticket;
	}

	public BigDecimal getPrecoMedio() {

		final var qtdeTotal = getQtdeTotal();

		return getValorTotal().divide(BigDecimal.valueOf(qtdeTotal), RoundingMode.HALF_UP);
	}

	public int getQtdeTotal() {
		return getOperacoes().stream().mapToInt(Operacao::getQtde).sum();
	}

	public BigDecimal getValorTotal() {
		return getOperacoes().stream().map(o -> o.getValor().multiply(BigDecimal.valueOf(o.getQtde())))
				.reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	public int getQtde() {
		return qtde;
	}

	public void setQtde(final int qtde) {
		this.qtde = qtde;
	}

	public List<Operacao> getOperacoes() {
		if (operacoes == null) {
			operacoes = new ArrayList<>();
		}
		return operacoes;
	}

	public void setOperacoes(final List<Operacao> operacoes) {
		this.operacoes = operacoes;
	}

	public void addOperacao(final LocalDate data, final int qtde, final BigDecimal valor) {
		this.getOperacoes().add(new Operacao(data, qtde, valor));
	}

	private String getResumo() {
		return getQtdeTotal() + " cotas de " + getTicket() + " com o Preço Médio de " + formatValue(getPrecoMedio())
				+ ". Custo total de : " + formatValue(getValorTotal())
				+ " através da corretora Inter DTVM(Inter Distribuidora de Títulos e Valores Mobiliários).";
	}

	private static String formatValue(final BigDecimal value) {
		return NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(value);
	}

	@Override
	public String toString() {
		final var string = new StringBuilder();

		string.append("Ticket: " + this.getTicket());
		string.append("\n\tPM: " + formatValue(getPrecoMedio()));
		string.append("\n\tQtde: " + getQtdeTotal());
		string.append("\n\tValor Total: " + formatValue(getValorTotal()));
		string.append("\n\tResumo Operacoes: " + getResumo());
		string.append("\n\tOperacoes:\n"
				+ getOperacoes().stream().map(o -> "\t\t".concat(o.toString())).collect(Collectors.joining("\n")));
		return string.toString();
	}
}