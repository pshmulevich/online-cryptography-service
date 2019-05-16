package javacryptography.app.service.impl;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import org.springframework.stereotype.Service;

import javacryptography.app.service.EncryptionAlgorithm;
import javacryptography.app.service.KeyGenerator;

@Service
public class KeyGeneratorImpl implements KeyGenerator {

	@Override
	public KeyPair generateKeyPair(EncryptionAlgorithm algorithm) throws NoSuchAlgorithmException {
		SecureRandom secureRandom = new SecureRandom();
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(algorithm.name());
		keyPairGenerator.initialize(4096, secureRandom); // this will take a while due to key size
		return keyPairGenerator.generateKeyPair();
	}

}
