package org.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.Key;
import java.util.Date;

public class TokenUtils {
	public static String rootPath;

	/**
	 * 生成用户token
	 */
	public static String buildJwt1(Key key, Date exp, Long userid) {
		String jwt = Jwts.builder()
		// .setExpiration(exp)// expTime是过期时间
				.setIssuedAt(new Date())// 设置当前时间
				.setIssuer("Marshall")// 设置发行人
				.setSubject("authentic")// 设置主题
				.setAudience("panshi")// 设置用户群
				.claim("userid", userid)// 该方法是在JWT中加入值为vaule的key字段
				.signWith(SignatureAlgorithm.HS512, key)// SECRET_KEY是加密算法对应的密钥，这里使用额是HS256加密算法
				.compact();
		return jwt;
	}

	/**
	 * 生成员工用户token
	 */
	public static String buildJwt3(Key key, Date exp, Integer staffId) {
		String jwt = Jwts.builder()
		// .setExpiration(exp)// expTime是过期时间
				.setIssuedAt(new Date())// 设置当前时间
				.setIssuer("Marshall")// 设置发行人
				.setSubject("authentic")// 设置主题
				.setAudience("panshi")// 设置用户群
				.claim("staffId", staffId)// 该方法是在JWT中加入值为vaule的key字段
				.signWith(SignatureAlgorithm.HS512, key)// SECRET_KEY是加密算法对应的密钥，这里使用额是HS256加密算法
				.compact();
		return jwt;
	}

	/**
	 * 生成管理员token
	 */
	public static String buildJwt2(Key key, Date exp, Long adminId) {
		String jwt = Jwts.builder()
		// .setExpiration(exp)// expTime是过期时间
				.setIssuedAt(new Date())// 设置当前时间
				.setIssuer("Marshall")// 设置发行人
				.setSubject("authentic")// 设置主题
				.setAudience("panshi")// 设置用户群
				.claim("adminId", adminId)// 该方法是在JWT中加入值为vaule的key字段
				.signWith(SignatureAlgorithm.HS512, key)// SECRET_KEY是加密算法对应的密钥，这里使用额是HS256加密算法
				.compact();
		return jwt;
	}

	/**
	 * 验证token是否合法
	 * 
	 * @param token
	 * @param key
	 * @return
	 */
	public static Boolean isValid(String token, Key key) {
		try {
			Jwts.parser().setSigningKey(key).parseClaimsJws(token.trim());
			return true;
		} catch (Exception e) {
			// e.printStackTrace();
			System.out.println("验证不通过");
			return false;
		}
	}

	/**
	 * 获取token中存储的参数
	 * 
	 * @param jwsToken
	 * @param key
	 * @param obj
	 * @return
	 */
	public static Object getValue(String jwsToken, Key key, String obj) {
		if (isValid(jwsToken, key)) {
			Jws<Claims> claimsJws = Jwts.parser().setSigningKey(key)
					.parseClaimsJws(jwsToken);

			return claimsJws.getBody().get(obj);
		}
		return null;
	}

	public static Key getKey() {
		File file = new File(rootPath + "key.txt");
		// File file = new File("F:\\" + "key.txt");
		try {
			if (file.exists()) {
				// System.out.println("key文件存在");
				ObjectInputStream ois = new ObjectInputStream(
						new FileInputStream(file));
				Key key = (Key) ois.readObject();
				ois.close();
				return key;
			} else {
				// System.out.println("key文件不存在，重新生成");
				file.createNewFile();
				Key key = MacProvider.generateKey(SignatureAlgorithm.HS512);
				ObjectOutputStream oo = new ObjectOutputStream(
						new FileOutputStream(file));
				oo.writeObject(key);
				oo.close();
				return key;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
		}
	}
}
