package com.qheeshow.eway.web.controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.qheeshow.eway.common.util.UrlFilterConfig;

public class AuthFilter implements Filter {
	
	private String localLoginUrl = null;
	
	public void destroy() {
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		HttpSession session = httpRequest.getSession();
		String url = httpRequest.getRequestURI();
		// 如果是不需要用户登录认证的服务或者用户已经进行登录认证并未过期，则放行
		if (session.getAttribute("userId") != null || UrlFilterConfig.getInstance().matchExcludeUrl(httpRequest.getContextPath(), httpRequest.getServletPath())) {
			try{
				chain.doFilter(request, response);
			}finally{
			}
		} else {
			// 对于需要用户认证的服务，但是用户又没有认证或者已经过期，则需要重新认证
			if(url.indexOf(".html") < 0 && url.indexOf(".jsp") < 0){
//				chain.doFilter(request, response);
				httpRequest.getRequestDispatcher("/user/reLogin.json").forward(httpRequest, httpResponse);
			}else{
				httpResponse.sendRedirect(localLoginUrl);
			}
		}
	}
	
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		localLoginUrl = filterConfig.getInitParameter("localLoginUrl");
	}

}