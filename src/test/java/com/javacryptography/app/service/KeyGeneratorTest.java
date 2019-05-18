package com.javacryptography.app.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.xml.bind.DatatypeConverter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KeyGeneratorTest {

	@Autowired
	private KeyGenerator keyGenerator;

	@Autowired
	private Encryptor encryptor;

	@Autowired
	private Decryptor decryptor;

	@Test
	public void testKeyGeneration() throws NoSuchAlgorithmException {
		KeyPair keyPair = keyGenerator.generateKeyPair(EncryptionAlgorithm.RSA);
		assertNotNull(keyPair);
		System.out.println("The old Private Key is: " + DatatypeConverter.printHexBinary(keyPair.getPrivate().getEncoded()));
		System.out.println("The old Public Key is:  " + DatatypeConverter.printHexBinary(keyPair.getPublic().getEncoded()));
	}

	@Test
	public void testPrivateKeyFromString() throws NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeySpecException, InvalidKeyException, NoSuchPaddingException,
	        IllegalBlockSizeException, BadPaddingException {
		EncryptionAlgorithm algorithm = EncryptionAlgorithm.RSA;
		KeyPair keyPair = keyGenerator.generateKeyPair(algorithm);
		assertNotNull(keyPair);
		String privateKeyStr = new String(Base64.getEncoder().encode(keyPair.getPrivate().getEncoded()));
		System.out.println("The Private Key is: " + privateKeyStr);
		// String publicKeyStr = new String(Base64.getEncoder().encode(keyPair.getPublic().getEncoded()));
		// System.out.println("The Public Key is: " + publicKeyStr);
		PrivateKey privateKey = keyGenerator.privateKeyFromString(algorithm, privateKeyStr);

		// Try to encrypt with new created key and if the matching public key can decrypt, it matches.
		String plainText = "This is a string for encryption";

		byte[] cipherText = encryptor.performEncryption(EncryptionAlgorithm.RSA, plainText, keyPair.getPublic());
		assertNotNull(cipherText);
		System.out.println(DatatypeConverter.printHexBinary(cipherText));

		String decryptedText = decryptor.performDecryption(EncryptionAlgorithm.RSA, cipherText, privateKey);
		assertEquals(plainText, decryptedText);
	}

	@Test
	public void testPublicKeyFromString() throws NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeySpecException, InvalidKeyException, NoSuchPaddingException,
	        IllegalBlockSizeException, BadPaddingException {
		EncryptionAlgorithm algorithm = EncryptionAlgorithm.RSA;
		KeyPair keyPair = keyGenerator.generateKeyPair(algorithm);
		assertNotNull(keyPair);
		String publicKeyStr = new String(Base64.getEncoder().encode(keyPair.getPublic().getEncoded()));
		// System.out.println("The Private Key is: " + publicKeyStr);
		// String publicKeyStr = new String(Base64.getEncoder().encode(keyPair.getPublic().getEncoded()));
		System.out.println("The Public Key is:  " + publicKeyStr);
		PublicKey publicKey = keyGenerator.publicKeyFromString(algorithm, publicKeyStr);

		// Try to encrypt with new created key and if the matching public key can decrypt, it matches.
		String plainText = "This is a string for encryption";

		byte[] cipherText = encryptor.performEncryption(EncryptionAlgorithm.RSA, plainText, publicKey);
		assertNotNull(cipherText);
		System.out.println(DatatypeConverter.printHexBinary(cipherText));

		String decryptedText = decryptor.performDecryption(EncryptionAlgorithm.RSA, cipherText, keyPair.getPrivate());
		assertEquals(plainText, decryptedText);
	}
}
