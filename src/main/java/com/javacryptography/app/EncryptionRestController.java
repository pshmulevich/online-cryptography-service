package com.javacryptography.app;

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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javacryptography.app.service.Decryptor;
import com.javacryptography.app.service.EncryptionAlgorithm;
import com.javacryptography.app.service.Encryptor;
import com.javacryptography.app.service.KeyGenerator;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", maxAge = 3600)
public class EncryptionRestController {
	@Autowired
	private KeyGenerator keyGenerator;

	@Autowired
	private Encryptor encryptor;

	@Autowired
	private Decryptor decryptor;

	@GetMapping(path = "/keys")
	public ResponseEntity<Object> keys() {
		try {
			KeyPair keyPair = keyGenerator.generateKeyPair(EncryptionAlgorithm.RSA);
			return new ResponseEntity<>(
			        new GeneratedKeyPair(
			                new String(Base64.getEncoder().encode(keyPair.getPublic().getEncoded())),
			                new String(Base64.getEncoder().encode(keyPair.getPrivate().getEncoded()))),
			        HttpStatus.OK);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<>(null, null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/encrypt")
	public ResponseEntity<GeneratedCiphertext> encrypt(@RequestBody PlaintextWithKey plaintextWithKey) {

		try {
			PublicKey publicKey = keyGenerator.publicKeyFromString(EncryptionAlgorithm.RSA, plaintextWithKey.getPublicKey());
			byte[] cipherText = encryptor.performEncryption(EncryptionAlgorithm.RSA, plaintextWithKey.getPlaintext(), publicKey);
			return new ResponseEntity<>(new GeneratedCiphertext(new String(Base64.getEncoder().encode(cipherText))),
			        HttpStatus.OK);
		} catch (InvalidKeySpecException | UnsupportedEncodingException | NoSuchAlgorithmException | InvalidKeyException | NoSuchPaddingException | IllegalBlockSizeException
		        | BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<>(null, null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/decrypt")
	public ResponseEntity<DecryptedPlaintext> decrypt(@RequestBody CiphertextWithKey ciphertextWithKey) {

		try {
			PrivateKey privateKey = keyGenerator.privateKeyFromString(EncryptionAlgorithm.RSA, ciphertextWithKey.getPrivateKey());
			byte[] cipherText = Base64.getDecoder().decode(ciphertextWithKey.getCiphertext());
			String plainText = decryptor.performDecryption(EncryptionAlgorithm.RSA, cipherText, privateKey);
			return new ResponseEntity<>(new DecryptedPlaintext(plainText),
			        HttpStatus.OK);
		} catch (InvalidKeySpecException | UnsupportedEncodingException | NoSuchAlgorithmException | InvalidKeyException | NoSuchPaddingException | IllegalBlockSizeException
		        | BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<>(null, null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}