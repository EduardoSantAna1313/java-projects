import java.nio.charset.StandardCharsets;
import java.util.Random;

/**
 * @author Eduardo
 */
public class CriptoMain {

	public static void main(final String[] args) throws Exception {
		final byte[] key = getKey().getBytes(StandardCharsets.UTF_8);
		final byte[] password = "PASSWORD".getBytes(StandardCharsets.UTF_8);
		final byte[] cipherText = AESCripto.encrypt(password, key);
		final byte[] decryptedCipherText = AESCripto.decrypt(cipherText, key);
		System.out.println(new String(key));
		System.out.println(new String(decryptedCipherText));
	}

	private static String getKey() {
		final StringBuilder key = new StringBuilder();
		final Random rand = new Random();

		for (int i = 0; i < 16; i++) {
			key.append((char) rand.nextInt(64));
		}

		return key.toString();
	}

}
