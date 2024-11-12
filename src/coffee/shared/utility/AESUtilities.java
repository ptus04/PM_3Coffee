package coffee.shared.utility;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class AESUtilities {

	private static final String PRIVATE_KEY = "3CoffeePriAESKey"; // 128 bits - 16 bytes

	private static SecretKeySpec getAESKey(String key) {
		byte[] keyBytes = key.getBytes();
		return new SecretKeySpec(keyBytes, "AES");
	}

	public static String encrypt(String plainText) throws Exception {
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, getAESKey(PRIVATE_KEY));
		byte[] cipherText = cipher.doFinal(plainText.getBytes());
		return Base64.getEncoder().encodeToString(cipherText);
	}

	public static String decrypt(String cipherText) throws Exception {
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, getAESKey(PRIVATE_KEY));
		byte[] plainText = cipher.doFinal(Base64.getDecoder().decode(cipherText));
		return new String(plainText);
	}

}
