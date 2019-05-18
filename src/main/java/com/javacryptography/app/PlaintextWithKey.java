package com.javacryptography.app;

public class PlaintextWithKey {
	private String publicKey;
	private String plaintext;

	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}

	public void setPlaintext(String plaintext) {
		this.plaintext = plaintext;
	}

	/**
	 * @return the publicKey
	 */
	public String getPublicKey() {
		return publicKey;
	}

	/**
	 * @return the plaintext
	 */
	public String getPlaintext() {
		return plaintext;
	}
}
