package com.javacryptography.app.service;

import java.io.UnsupportedEncodingException;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;

/**
 * Generate public and private key
 *
 * @param algorithm encryption algorithm
 * @return key pair
 * @throws NoSuchAlgorithmException
 * @throws Exception
 */
public interface KeyGenerator {
	public KeyPair generateKeyPair(EncryptionAlgorithm algorithm) throws NoSuchAlgorithmException;

	public PrivateKey privateKeyFromString(EncryptionAlgorithm algorithm, String privateKeyStr)
	        throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeySpecException;

	public PublicKey publicKeyFromString(EncryptionAlgorithm algorithm, String publicKeyStr) throws InvalidKeySpecException, UnsupportedEncodingException, NoSuchAlgorithmException;
}
