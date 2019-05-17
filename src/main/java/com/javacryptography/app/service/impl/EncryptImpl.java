package com.javacryptography.app.service.impl;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.springframework.stereotype.Service;

import com.javacryptography.app.service.EncryptionAlgorithm;
import com.javacryptography.app.service.Encryptor;

@Service
public class EncryptImpl implements Encryptor {

	@Override
	public byte[] performEncryption(EncryptionAlgorithm algorithm, String plainText, PublicKey publicKey)
	        throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		Cipher cipher = Cipher.getInstance(algorithm.name());
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		return cipher.doFinal(plainText.getBytes());
	}

}
