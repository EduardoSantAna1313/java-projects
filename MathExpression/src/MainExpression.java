import com.towel.math.Expression;

public class MainExpression {

	public static void main(final String[] args) {
		final Expression exp = new Expression("v1 - v3 * v2 - (v4 / v5) - v6");
		exp.setVariable("v1", -12.5);
		exp.setVariable("v2", 4324.96);
		exp.setVariable("v3", 312.32);
		exp.setVariable("v4", 434543.65);
		exp.setVariable("v5", 321.21);
		exp.setVariable("v6", 312.12);
		final double result = exp.resolve();
		System.out.println(result);
	}

}