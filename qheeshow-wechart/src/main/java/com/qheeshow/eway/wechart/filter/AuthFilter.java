package com.qheeshow.eway.wechart.filter;

import com.qheeshow.eway.service.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Service
public class AuthFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthFilter.class);

    public void destroy() {
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        String url = request.getRequestURI();
        if (url.indexOf("/v_auth") == -1 && url.indexOf("/v_login") == -1) {
            chain.doFilter(request, response);
            return;
        }
        if (session.getAttribute("loginUser") == null) {
            request.getRequestDispatcher("/user/login.jsp").forward(request, response);
            return;
        }
        User loginUser = (User) session.getAttribute("loginUser");
        if (url.indexOf("/v_authj") != -1) {
            if (loginUser.getRoleid() == null) {
                request.getRequestDispatcher("/user/appendj").forward(request, response);
                return;
            }
        }
        chain.doFilter(request, response);
    }

}