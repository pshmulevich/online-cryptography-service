package javacryptography.app.service;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 * Perform an encryption
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
public interface Encryptor {
	public byte[] performEncryption(EncryptionAlgorithm algorithm, String plainText, PrivateKey privateKey)
	        throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException;
}