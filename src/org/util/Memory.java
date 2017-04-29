//package org.util;
//
//import javax.annotation.PreDestroy;
//
//import org.model.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import net.sf.ehcache.Cache;
//import net.sf.ehcache.Element;
//
//@Component
//public class Memory {
//
//	@Autowired
//	private Cache ehcache; // 注意这里引入的Cache是net.sf.ehcache.Cache
//
//	/**
//	 * 关闭缓存管理器
//	 */
//	@PreDestroy
//	protected void shutdown() {
//		if (ehcache != null) {
//			ehcache.getCacheManager().shutdown();
//		}
//	}
//
//	/**
//	 * 保存当前登录用户信息
//	 * 
//	 * @param loginUser
//	 */
//	public void saveLoginUser(User user) {
//		// 生成seed和token值
//		String seed = MD5.string2MD5(user.getPhone());
//		String token = TokenUtils.proccessToken(user);
//
//		// 清空之前的登录信息
//		clearLoginInfoBySeed(seed);
//		// 保存新的token和登录信息
//		ehcache.put(new Element(seed, token));
//		// ehcache.put(new Element(seed, token, false, ttiExpiry, 0));
//	}
//
//	/**
//	 * 获取当前线程中的用户信息
//	 * 
//	 * @return
//	 */
//	public User currentLoginUser() {
//		Element element = ehcache.get(ThreadTokenHolder.getToken());
//		return element == null ? null : (User) element.getValue();
//	}
//
//	/**
//	 * 根据token检查用户是否登录
//	 * 
//	 * @param token
//	 * @return
//	 */
//	public boolean checkLoginInfo(String token) {
//		Element element = ehcache.get(token);
//		return element != null && (User) element.getValue() != null;
//	}
//
//	/**
//	 * 清空登录信息
//	 */
//	public void clearLoginInfo() {
//		LoginUser loginUser = currentLoginUser();
//		if (loginUser != null) {
//			// 根据登录的用户名生成seed，然后清除登录信息
//			String seed = MD5Util.getMD5Code(loginUser.getUsername());
//			clearLoginInfoBySeed(seed);
//		}
//	}
//
//	/**
//	 * 根据seed清空登录信息
//	 * 
//	 * @param seed
//	 */
//	public void clearLoginInfoBySeed(String seed) {
//		// 根据seed找到对应的token
//		Element element = ehcache.get(seed);
//		if (element != null) {
//			// 根据token清空之前的登录信息
//			ehcache.remove(seed);
//			ehcache.remove(element.getValue());
//		}
//	}
//
//	public void setValue(String key, String value) {
//		ehcache.put(new Element(key, value));
//	}
//
//	public Object getValue(String key) {
//		Element element = ehcache.get(key);
//		return element != null ? element.getValue() : null;
//	}
//}