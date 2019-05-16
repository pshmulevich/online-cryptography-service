package javacryptography.app.service.impl;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.springframework.stereotype.Service;

import javacryptography.app.service.EncryptionAlgorithm;
import javacryptography.app.service.Encryptor;

@Service
public class EncryptImpl implements Encryptor {

	@Override
	public byte[] performEncryption(EncryptionAlgorithm algorithm, String plainText, PrivateKey privateKey)
	        throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		Cipher cipher = Cipher.getInstance(algorithm.name());
		cipher.init(Cipher.ENCRYPT_MODE, privateKey);
		return cipher.doFinal(plainText.getBytes());
	}

}
