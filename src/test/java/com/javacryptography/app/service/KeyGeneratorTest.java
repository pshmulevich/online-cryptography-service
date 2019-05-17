package javacryptography.app.service;

import static org.junit.Assert.assertNotNull;

import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;

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

	@Test
	public void keyGenerationTest() throws NoSuchAlgorithmException {
		KeyPair keyPair = keyGenerator.generateKeyPair(EncryptionAlgorithm.RSA);
		assertNotNull(keyPair);
		System.out.println("The Private Key is: " + DatatypeConverter.printHexBinary(keyPair.getPrivate().getEncoded()));
		System.out.println("The Public Key is:  " + DatatypeConverter.printHexBinary(keyPair.getPublic().getEncoded()));
	}

}
