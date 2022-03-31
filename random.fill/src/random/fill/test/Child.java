package random.fill.test;

import java.math.BigDecimal;
import java.util.List;

import random.fill.util.ToStringUtil;

public class Child {

	String text;

	private BigDecimal bd1;

	private List<BigDecimal> decimals;

	public Child() {
		super();
	}

	@Override
	public String toString() {
		return ToStringUtil.toString(this, ", ");
	}

}