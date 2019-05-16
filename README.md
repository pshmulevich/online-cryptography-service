# Building a basic cryptographic Web Service with Spring Boot using REST

## Table of Contents
### #1: Setting up the REST service
### #2: Creating the Key Generator
### #3: Creating Encryption and Decryption Service


# Part 1: Creating a basic REST service app:
Either install or begin using eclipse for the project.
Check to make sure JDK version is at least 1.8, Maven 3.0

## Step 1: Create a new Maven project in eclipse

Open pom.xml file and add the following under groupId and artifactId:

``` 
   <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>1.5.6.RELEASE</version>
    </parent>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
    </dependencies>

    <properties>
        <java.version>1.8</java.version>
    </properties>


    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build> 
```

## Step 2:  Create a web controller for the application.
Create a new java controller class and name it responsibly. Here is an example:

```
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class Controller {

    @RequestMapping("/")
    public String index() {
        return "PlaceHolder";
    }
} 
```

## Step 3: Create the application class that will be controlled
Note: this is where the JDK 1.8 version is needed.

``` 
import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {

            System.out.println("Let's inspect the beans provided by Spring Boot:");

            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                System.out.println(beanName);
            }

        };
    }
} 
```

## Step 4:
Open command window and enter this command: `mvn clean package spring-boot:run`.

Make sure the Maven build is successful and the application starts with no errors.

## Step 5: Open up the web browser and type: `http://localhost:8080`.
The page should successfully load and display "Placeholder" in the content area.


# Part 2: Creating the Key Generator:
Congratulations! You now have a fully working restful application. Now the real work begins.

## Background: Cryptography
### Cryptography is the study of secrets (Creating and decoding them, rather).
A cipher is a secret or a way to disguise or conceal a message in plain sight.
There are two kinds of ciphers: Stream and Block:
A Stream cipher is one in which the message is encrypted one bit at a time.
A Block cipher is one in which a chunk of bits are encrypted at a time.
In the example given, the Block Cipher is used.

### Asymmetric Cryptography:
To make cryptography more complex, the use of keys were factored in. A key is used to encrypt and another key is used to decrypt.
Asymmetric Cryptography uses 2 keys to encrypt- public and private.
The public key is known to everyone while the private key is not.
The private key belonging to the sender is used to encrypt and the reciever's private key is used to decrypt.

### About RSA
RSA is an asymmetric algorithm. The algorithm is generated as follows:
1) choose 2 large prime numbers p, q
2) Calculate their product, which will be the modulus for public and private keys
3) Calculate the totient which is the product of one less than each: (p-1)(q-1)
4) Choose an integer e coprime to the totient with no other GCD factors other than 1; (phi(n))
5) Calculate  d=(1+k phi (n))/e) where k is some integer
6) d is kept as private key exponent.

If this is confusing, have no fear, the use of libraries makes creating an RSA function very easy:

### Creating the RSA function:
Some of the libraries which help do the heavy lifting are:
* KeyPair
* KeyPairGenerator
* PrivateKey
* PublicKey
* SecureRandom

The code for generating RSA key pair is straightforward:
```

    public KeyPair makersaKeyPair() throws NoSuchAlgorithmException {
        SecureRandom secureRandom = new SecureRandom();
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(RSA);
        keyPairGenerator.initialize(4096, secureRandom); // 4096 is the key size
        return keyPairGenerator.generateKeyPair();
    }
```
## Now to encode a message:
```

    public byte[] rsaEncrypt(String plainText, PrivateKey privateKey) 
            throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance(RSA);
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        return cipher.doFinal(plainText.getBytes());
    }
```

## And decryption:
```

    public String rsaDecrypt(byte[] cipherText, PublicKey publicKey) 
            throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance(RSA);
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        byte[] result = cipher.doFinal(cipherText);
        return new String(result);
    }
```
