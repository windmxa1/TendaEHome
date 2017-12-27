package org.interceptor;

import java.security.Key;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.util.ResultUtils;
import org.util.TokenUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class BackInterceptor implements HandlerInterceptor {

	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {

	}

	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {

	}

	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object object) throws Exception {
		Key key = TokenUtils.getKey();
		String token = request.getHeader("token");
		if (TokenUtils.isValid(token, key)) {
			// System.out.println(request.getServletPath());
			return true;
		} else {
			Map<String, Object> result = ResultUtils.toJson(400,
					"您的账户信息已过期或已在其他客户端登录，请重新登录", "");
			ObjectMapper objectMapper = new ObjectMapper();
			String s = objectMapper.writeValueAsString(result);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(s);
			return false;
		}
	}

}
