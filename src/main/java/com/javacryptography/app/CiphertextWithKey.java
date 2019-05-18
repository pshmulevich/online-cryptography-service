package com.javacryptography.app;

public class CiphertextWithKey {
	private String privateKey;
	private String ciphertext;

	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}

	public void setCiphertext(String ciphertext) {
		this.ciphertext = ciphertext;
	}

	/**
	 * @return the privateKey
	 */
	public String getPrivateKey() {
		return privateKey;
	}

	/**
	 * @return the ciphertext
	 */
	public String getCiphertext() {
		return ciphertext;
	}
}
