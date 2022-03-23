package arraylist;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ConcurrentArrayListExample {

	public static void main(String[] args) {
		final List<String> list = new ArrayList<>(List.of("A", "B", "C"));

		final List<String> concurrentList = new CopyOnWriteArrayList<>();
		list.forEach(concurrentList::add);

		testList(concurrentList);
	}

	public static void testList(final List<String> list) {

		int size = list.size();
		var it = list.iterator();

		int i = 0;
		while (it.hasNext()) {

			System.out.println(it.next());

			if (i++ == 0) {
				list.addAll(List.of("Z", "Y", "E"));
				// operação nao permitida
				// it.remove();
			}

			if (i == size - 2) {
				break;
			}
		}

		System.out.println("forEachRemaining");
		// No CopyOnWriteArrayList o iterator nao é atualizado ao adicionar mais
		// elementos durate a iteração, por isso o forEachRemaining imprime só 1
		// elemento nao percorrido

		// se na linha 15 for informada um arraylist comum, dará
		// ConcurrentModificationException
		it.forEachRemaining(System.out::println);

		System.out.println("Total");
		System.out.println(list);
	}
}
