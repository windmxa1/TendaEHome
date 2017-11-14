package org.util;

import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class AESUtil {
	/**
	 * 密钥算法
	 */
	private static final String ALGORITHM = "AES";
	/**
	 * 加解密算法/工作模式/填充方式
	 */
	private static final String ALGORITHM_MODE_PADDING = "AES/ECB/PKCS7Padding";
	/**
	 * 生成key
	 */
	private static SecretKeySpec key = new SecretKeySpec(MD5Util
			.MD5Encode(Constants.key, "UTF-8").toLowerCase().getBytes(),
			ALGORITHM);

	static {
		Security.addProvider(new BouncyCastleProvider());
	}

	/**
	 * AES加密
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static String encryptData(String data) throws Exception {
		// 创建密码器
		Cipher cipher = Cipher.getInstance(ALGORITHM_MODE_PADDING, "BC");
		// 初始化
		cipher.init(Cipher.ENCRYPT_MODE, key);
		return Base64Util.encode(cipher.doFinal(data.getBytes()));
	}

	/**
	 * AES解密
	 * 
	 * @param base64Data
	 * @return
	 * @throws Exception
	 */
	public static String decryptData(String base64Data) throws Exception {
		Cipher cipher = Cipher.getInstance(ALGORITHM_MODE_PADDING, "BC");
		cipher.init(Cipher.DECRYPT_MODE, key);
		return new String(cipher.doFinal(Base64Util.decode(base64Data)));
	}
//	public static void main(String[] args) throws Exception {
//		String a = "BVN4zZ99bXY87E509itRtDJ9Lr9o17TLLlE0v3vPoC4bOo71Bd+YPFYSKE947uboMjjORFkq0WsMESQrnHg8VtVR7ixOz3aAsuKpyi0xcHB33MLKqr1E2QwYie2dMCxsDQqj6NipelfpTdlUy17BUok5XARu/t4LjaF7M6FVlrXwSsyilZJPmMSwdje7C1iDn5nAIiF0HygSy6ds8ehx+fSsLjzH0S3gB+CFpRytQhzXidYqx4DBUrjEk750RNxhR31VTtRO9DJEBoynbSx0o8qA03ch8qdo5k9UXrycTCtwRvjwP+dzvFWDtaCXYbnY7wcqE63qKb6SBUN65y7rzi9ntfNJzXTBCOEacw3qb/UV7gfVfLYAAo4uDA/sHzypGJdzv5kHMmI8mZ++ey2LwvOCsTK+vDCf7lWeO7bu8hMNDYvyDe2A9E8JXgK0YygGYKl7j2eAKd9JlMJ6bfxsCNkO2j9vHt3i8OwsbHelFUT0ZUXLUxqeLal/4EAT2TWZUEigIXjTJ5FfvjCZeHIDcYTfQv5vP8/Q22ZTfa9TXZStigpo2s5RXbnPxCOeOSAiuylEtUxt3NScw9ltJbLuqknf8PTS6P2wmnXTMI1MkkDI/pEcPG8rRkaZpoig2P9UmaMhsYpB6GRdms1PIYU2Z98SLfVjIbsbHW7JYZFds402t27xwT0J4KXMtn3GlPboEAf3BHAWsOOrVWjJIK6SJOZ5Y4TM3g/3vHz8NxZlen8wXQBeD7KpkudXQko+g7Y/yoDTdyHyp2jmT1RevJxMKx3uwwC2NhJTy7Okjbqk17Yr8fqZDgF9o+AojBZ83b3rhxmXAtw9aptG8ir3rfOpSFAepmBnbYsSIv+cVzV7chRWECc40tyfSoJKkORbmdZ0yB6SgzLXOFZEkj1qVKPibLH0f5GJESrKn29dJN/NZRueDThB8LeUUg/MCYrqYHYvlm/YJGXdcJX07TUYKla3xyfPs/xPMjFd1AfDlBSPcrzYHqUGqhCAWjAwFZkfWbaiP9bBUQcHdkEfEgpt0iVeRbp8oFiRQvsNRW/6yAWebSw=";
//		System.out.println(a);
//		System.out.println(AESUtil.decryptData(a));
//	}
}
