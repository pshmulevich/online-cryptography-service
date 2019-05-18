package com.javacryptography.app.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;

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
public class EncryptionDecryptionTest {

	@Autowired
	private KeyGenerator keyGenerator;

	@Autowired
	private Encryptor encryptor;

	@Autowired
	private Decryptor decryptor;

	@Test
	public void testEncryptDecrypt() throws NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {

		KeyPair keyPair = keyGenerator.generateKeyPair(EncryptionAlgorithm.RSA);

		String plainText = "This is a string for encryption";

		byte[] cipherText = encryptor.performEncryption(EncryptionAlgorithm.RSA, plainText, keyPair.getPublic());
		assertNotNull(cipherText);
		System.out.println(DatatypeConverter.printHexBinary(cipherText));

		String decryptedText = decryptor.performDecryption(EncryptionAlgorithm.RSA, cipherText, keyPair.getPrivate());
		assertEquals(plainText, decryptedText);
	}

}
