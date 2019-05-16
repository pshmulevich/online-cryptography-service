package javacryptography.app.service.impl;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.springframework.stereotype.Service;

import javacryptography.app.service.Decryptor;
import javacryptography.app.service.EncryptionAlgorithm;

@Service
public class DecryptImpl implements Decryptor {

	@Override
	public String performDecryption(EncryptionAlgorithm algorithm, byte[] cipherText, PublicKey publicKey)
	        throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		Cipher cipher = Cipher.getInstance(algorithm.name());
		cipher.init(Cipher.DECRYPT_MODE, publicKey);
		byte[] result = cipher.doFinal(cipherText);
		return new String(result);
	}

}
