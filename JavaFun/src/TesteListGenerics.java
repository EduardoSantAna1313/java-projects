import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TesteListGenerics {

	public static void main(final String[] args) {
		final List<String> list = new ArrayList<>();
		add(list);
		list.add("bla");
		System.out.println(list);
	}

	/**
	 * As listas são tipadas em tempo de compilação. Para manter a compatibilidade <
	 * Java5, todas as listas são genéricas. Ao misturar uma lista tipada com uma
	 * lista genérica podemos adicionar qualquer tipo de objeto.
	 *
	 * @param list
	 */
	public static void add(final List list) {
		list.add(new Integer(1));
		list.add(BigDecimal.valueOf(12.34));
	}

}
