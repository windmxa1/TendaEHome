package org.util;

import org.model.User;

public class TokenUtils {
	public static String proccessToken(User user) {
		String token = MD5.string2MD5(user.getPhone() + user.getPassword()
				+ (System.currentTimeMillis() / 1000));
		return token;
	}
}
