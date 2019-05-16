package javacryptography.app.service;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

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
public interface Decryptor {
	public String performDecryption(EncryptionAlgorithm algorithm, byte[] cipherText, PublicKey publicKey)
	        throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException;
}