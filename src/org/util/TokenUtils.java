package org.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;

import java.security.Key;

public class TokenUtils {

	public static String generateToken() {
		Key key = MacProvider.generateKey();

		String compactJws = Jwts.builder().setSubject("Joe")
				.signWith(SignatureAlgorithm.HS512, key).compact();
		
		
		return null;
	}
}
