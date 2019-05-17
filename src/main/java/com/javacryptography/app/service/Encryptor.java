package com.javacryptography.app.service;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;

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
	byte[] performEncryption(EncryptionAlgorithm algorithm, String plainText, PublicKey publicKey)
	        throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException;
}