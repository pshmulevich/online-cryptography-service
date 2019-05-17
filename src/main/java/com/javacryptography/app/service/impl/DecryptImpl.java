package com.javacryptography.app.service.impl;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.springframework.stereotype.Service;

import com.javacryptography.app.service.Decryptor;
import com.javacryptography.app.service.EncryptionAlgorithm;

@Service
public class DecryptImpl implements Decryptor {
	@Override
	public String performDecryption(EncryptionAlgorithm algorithm, byte[] cipherText, PrivateKey privateKey)
	        throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		Cipher cipher = Cipher.getInstance(algorithm.name());
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		byte[] result = cipher.doFinal(cipherText);
		return new String(result);
	}

}
