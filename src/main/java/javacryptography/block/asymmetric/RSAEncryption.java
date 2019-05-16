package javacryptography.block.asymmetric;

import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class RSAEncryption {
	private static final String RSA = "RSA";

	/**
	 * Generate public and private key using RSA
	 *
	 * @return key pair
	 * @throws NoSuchAlgorithmException
	 * @throws Exception
	 */
	public static KeyPair generateRSAKeyPair() throws NoSuchAlgorithmException {
		SecureRandom secureRandom = new SecureRandom();
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(RSA);
		keyPairGenerator.initialize(4096, secureRandom); // when running the test, this will take a while due to key size
		return keyPairGenerator.generateKeyPair();
	}

	/**
	 * Perform an encryption using RSA
	 *
	 * @param plainText
	 * @param privateKey
	 * @return encryption
	 * @throws InvalidKeyException
	 * @throws NoSuchPaddingException
	 * @throws NoSuchAlgorithmException
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 * @throws exception
	 */
	public static byte[] performRSAEncryption(String plainText, PrivateKey privateKey)
	        throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		Cipher cipher = Cipher.getInstance(RSA);
		cipher.init(Cipher.ENCRYPT_MODE, privateKey);
		return cipher.doFinal(plainText.getBytes());
	}

	/**
	 * Perform an encryption using RSA
	 *
	 * @param plainText
	 * @param privateKey
	 * @return decryption
	 * @throws NoSuchPaddingException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 * @throws exception
	 */
	public static String performRSADecryption(byte[] cipherText, PublicKey publicKey)
	        throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		Cipher cipher = Cipher.getInstance(RSA);
		cipher.init(Cipher.DECRYPT_MODE, publicKey);
		byte[] result = cipher.doFinal(cipherText);
		return new String(result);
	}
}
