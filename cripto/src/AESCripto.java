import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author Eduardo
 */
public abstract class AESCripto {

	private static final String ALGORITHM = "AES";

	public static byte[] encrypt(final byte[] plainText, final byte[] key) throws Exception {
		final SecretKeySpec secretKey = new SecretKeySpec(key, ALGORITHM);
		final Cipher cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, secretKey);
		return cipher.doFinal(plainText);
	}

	public static byte[] decrypt(final byte[] cipherText, final byte[] key) throws Exception {
		final SecretKeySpec secretKey = new SecretKeySpec(key, ALGORITHM);
		final Cipher cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(Cipher.DECRYPT_MODE, secretKey);
		return cipher.doFinal(cipherText);
	}

}
