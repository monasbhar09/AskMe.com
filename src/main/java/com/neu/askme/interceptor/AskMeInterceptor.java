package com.neu.askme.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AskMeInterceptor extends HandlerInterceptorAdapter {
	String errorPage;

	public String getErrorPage() {
		return errorPage;
	}

	public void setErrorPage(String errorPage) {
		this.errorPage = errorPage;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {		

		HttpSession session = (HttpSession) request.getSession();
		if (session.getAttribute("user") != null) {
			return true;
		}
		response.sendRedirect(errorPage);
		return false;

	}

}
