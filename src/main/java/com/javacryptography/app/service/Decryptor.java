package com.javacryptography.app.service;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 * Decryption interface
 */
public interface Decryptor {
	/**
	 * Decrypt ciphertext.
	 *
	 * @param algorithm encryption algorithm
	 * @param cipherText encrypted text
	 * @param privateKey private key
	 * @return decrypted text as String
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeyException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 */
	public String performDecryption(EncryptionAlgorithm algorithm, byte[] cipherText, PrivateKey privateKey)
	        throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException;
}