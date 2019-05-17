package com.javacryptography.app.service.impl;

import java.io.UnsupportedEncodingException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import org.springframework.stereotype.Service;

import com.javacryptography.app.service.EncryptionAlgorithm;
import com.javacryptography.app.service.KeyGenerator;

@Service
public class KeyGeneratorImpl implements KeyGenerator {

	@Override
	public KeyPair generateKeyPair(EncryptionAlgorithm algorithm) throws NoSuchAlgorithmException {
		SecureRandom secureRandom = new SecureRandom();
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(algorithm.name());
		keyPairGenerator.initialize(4096, secureRandom); // this will take a while due to key size
		return keyPairGenerator.generateKeyPair();
	}

	@Override
	public PrivateKey privateKeyFromString(EncryptionAlgorithm algorithm, String privateKeyStr)
	        throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeySpecException {
		byte[] sigBytes = Base64.getDecoder().decode(privateKeyStr.getBytes("UTF-8"));
		PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(sigBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(algorithm.toString());
		return keyFactory.generatePrivate(privateKeySpec);
	}

	@Override
	public PublicKey publicKeyFromString(EncryptionAlgorithm algorithm, String publicKeyStr)
	        throws InvalidKeySpecException, UnsupportedEncodingException, NoSuchAlgorithmException {
		System.out.println("received public key string: " + publicKeyStr);
		byte[] sigBytes = Base64.getDecoder().decode(publicKeyStr.getBytes("UTF-8"));
		X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(sigBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(algorithm.toString());
		return keyFactory.generatePublic(publicKeySpec);
	}

}
