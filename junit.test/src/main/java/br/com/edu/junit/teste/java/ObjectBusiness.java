package br.com.edu.junit.teste.java;

/**
 * Classe negocial para o ObjectDTO.
 *
 * @author Eduardo
 */
public class ObjectBusiness {

	/**
	 * New instance of ObjectBusiness
	 */
	private ObjectBusiness() {
		// NA
	}

	public static boolean isZero(final ObjectDTO dto) {
		return dto.getId() == 0;
	}

	public static boolean isJava(final ObjectDTO dto) {
		return "JAVA".equalsIgnoreCase(dto.getName());
	}

}
