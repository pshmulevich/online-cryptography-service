package com.javacryptography.block.asymmetric;

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

import javacryptography.block.asymmetric.RSAEncryption;

public class RSAEncryptionTest {

	@Test
	public void testKeyPairGeneration() throws NoSuchAlgorithmException {
		KeyPair keyPair = RSAEncryption.generateRSAKeyPair();
		assertNotNull(keyPair);
		System.out.println("The Private Key is: " + DatatypeConverter.printHexBinary(keyPair.getPrivate().getEncoded()));
		System.out.println("The Public Key is:  " + DatatypeConverter.printHexBinary(keyPair.getPublic().getEncoded()));
	}

	@Test
	public void testRSAEncryptDecrypt()
	        throws NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		KeyPair keyPair = RSAEncryption.generateRSAKeyPair();
		String plainText = "This is a string for encryption";
		byte[] cipherText = RSAEncryption.performRSAEncryption(plainText, keyPair.getPrivate());
		assertNotNull(cipherText);
		System.out.println(DatatypeConverter.printHexBinary(cipherText));
		String decryptedText = RSAEncryption.performRSADecryption(cipherText, keyPair.getPublic());
		assertEquals(plainText, decryptedText);
	}

}
