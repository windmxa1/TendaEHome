package org.util;

import java.util.HashMap;
import java.util.Map;

public class ResultUtils {
	/**
	 * 将结果按格式输出
	 * 
	 * @param code
	 *            提示码
	 * @param msg
	 *            给用户的提示信息，没有则为空串
	 * @param data
	 *            返回给客户端的数据
	 * @return
	 */
	public static Map<String, Object> toJson(int code, String msg, Object data) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", code);
		result.put("msg", msg);
		result.put("data", data);
		return result;
	}
	public final static String ERROR="<?xml version=\"1.0\" encoding=\"UTF-8\"?><xml><return_msg>校验失败，请重试</return_msg><return_code>FAIL</return_code></xml>";
}
