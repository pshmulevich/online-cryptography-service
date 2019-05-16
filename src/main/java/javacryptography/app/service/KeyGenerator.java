package javacryptography.app.service;

import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;

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
}
