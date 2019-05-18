package com.javacryptography.app;

public class GeneratedKeyPair {
	private final String privateKey;
	private final String publicKey;

	public GeneratedKeyPair(String publicKey, String privateKey) {
		this.publicKey = publicKey;
		this.privateKey = privateKey;
	}

	/**
	 * @return the privateKey
	 */
	public String getPrivateKey() {
		return privateKey;
	}

	/**
	 * @return the publicKey
	 */
	public String getPublicKey() {
		return publicKey;
	}
}
