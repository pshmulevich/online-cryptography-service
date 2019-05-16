package javacryptography.app;

import java.security.KeyPair;

import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javacryptography.app.service.EncryptionAlgorithm;
import javacryptography.app.service.KeyGenerator;
import javacryptography.block.asymmetric.RSAEncryption;

@RestController
public class Controller {
	@Autowired
	private KeyGenerator keyGenerator;

	// Will need to call AsymmetricEncryptionUtils.generateRSAKeyPair() here
	private static final RSAEncryption asymmetricEncryptionUtils = new RSAEncryption();

	// This will generate public and private key.
	// for now, display them

	@RequestMapping("/")
	public String index() throws Exception {
		KeyPair keyPair = keyGenerator.generateKeyPair(EncryptionAlgorithm.RSA);

		return "<h1>Welcome to Cryptography Spring Application with Boot.</h1>"
		        + "<h2>You will be issued a private key and a public key now... </h2>"
		        + "<p>This may take a moment... please wait.<p>"
		        + "<p><b>Public Key</b>: " + "<input type=\"text\" value=\"" + DatatypeConverter.printHexBinary(keyPair.getPublic().getEncoded()) + "\"></p>"
		        + "<p><b>Private Key</b>: " + "<input type=\"text\" value=\"" + DatatypeConverter.printHexBinary(keyPair.getPrivate().getEncoded()) + "\"></p>"
		        + "<p>Please copy and save your private key for encryption use.</p>";

		// Question: How to display them?
		// Will they be used immediately?
		// Will clicking back change it?
	}

	@RequestMapping("/encrypt")
	public String page1() {
		return "<h2>Welcome to Encryption Page</h2>"
		        + "<p>Please enter your private key and your message to encrypt</p>"
		        + "<p>Private Key: " + "<input type=\"text\"></p>"
		        + "<p>Your message: " + "<input type=\"text\" value=\"Enter text\"></p>"
		        + "<p>Press submit to encrypt and send your message</p>"
		        + "<p><input type=\"submit\" value=\"Submit\"></p>";
	}

	@RequestMapping("/decrypt")
	public String page2() {
		return "<h2>Welcome to Decryption Page</h2>"
		        + "<p>Please enter your public and the encrypted message to decrypt</p>"
		        + "<p>Public Key: " + "<input type=\"text\"></p>"
		        + "<p>Encrypted message: " + "<input type=\"text\" value=\"Enter text\"></p>"
		        + "<p>Press submit to encrypt and send your message</p>"
		        + "<p><input type=\"submit\" value=\"Submit\"></p>";
	}
}