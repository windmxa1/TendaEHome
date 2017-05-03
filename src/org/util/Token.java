package org.util;

import java.util.Date;

public class Token {
	String authToken;// 授权token
	Date expiry;// 有效期

	public Token() {

	}

	public Token(String authToken, Date expiry) {
		super();
		this.authToken = authToken;
		this.expiry = expiry;
	}

	public String getAuthToken() {
		return authToken;
	}

	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}

	public Date getExpiry() {
		return expiry;
	}

	public void setExpiry(Date expiry) {
		this.expiry = expiry;
	}

}
