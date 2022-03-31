package random.fill.test;

import java.math.BigDecimal;
import java.util.List;

public class Obj {

	private List<String> listText;

	String text;

	int num;

	Integer num2;

	Boolean b1;

	boolean b2;

	Float f1;

	float f2;

	Double d1;

	double d2;

	private BigDecimal bd1;

	Child c;

	private List<Integer> listIntegers;

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("Obj [\n\tlistText: ");
		builder.append(listText);
		builder.append(",\n\ttext: ");
		builder.append(text);
		builder.append(",\n\tnum: ");
		builder.append(num);
		builder.append(",\n\tnum2: ");
		builder.append(num2);
		builder.append(",\n\tb1: ");
		builder.append(b1);
		builder.append(",\n\tb2: ");
		builder.append(b2);
		builder.append(",\n\tf1: ");
		builder.append(f1);
		builder.append(",\n\tf2: ");
		builder.append(f2);
		builder.append(",\n\td1: ");
		builder.append(d1);
		builder.append(",\n\td2: ");
		builder.append(d2);
		builder.append(",\n\tbd1: ");
		builder.append(bd1);
		builder.append(",\n\tc: ");
		builder.append(c);
		builder.append(",\n\tlistIntegers: ");
		builder.append(listIntegers);
		builder.append("\n]");
		return builder.toString();
	}

}
