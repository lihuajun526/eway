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
        if (url.indexOf("/auth") == -1 || session.getAttribute("loginUser") != null) {
            try {
                chain.doFilter(request, response);
            } finally {
            }
        } else {
            if (url.indexOf("/authj") != -1) {
                httpRequest.getRequestDispatcher("/user/reLogin").forward(httpRequest, httpResponse);
            } else if (url.indexOf("/auth") != -1) {
                httpResponse.sendRedirect(localLoginUrl);
            }
        }
    }


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        localLoginUrl = filterConfig.getInitParameter("localLoginUrl");
    }

}