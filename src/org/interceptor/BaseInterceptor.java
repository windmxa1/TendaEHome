package org.interceptor;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.util.Constants;
import org.util.TokenUtils;

public class BaseInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {

	}

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse arg1, Object arg2) throws Exception {
		TokenUtils.rootPath = request.getSession().getServletContext()
				.getRealPath("/");
		Constants.pdfDir = TokenUtils.rootPath + "pdf" + File.separator;
		String ipAddress = request.getLocalAddr();
		if (ipAddress.equals("172.18.222.230")) {// 如果等于云服务器内网IP就说明是云服务器，转换为公网IP
			ipAddress = "39.108.82.55";
		}
		Constants.pdfUrl = "http://" + ipAddress + ":" + request.getLocalPort()
				+ request.getContextPath() + "/" + "pdf/";
		return true;
	}
}
