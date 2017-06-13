package com.qheeshow.eway.wechart.filter;

import com.alibaba.fastjson.JSONObject;
import com.qheeshow.eway.common.http.XHttpClient;
import com.qheeshow.eway.common.util.Config;
import com.qheeshow.eway.service.model.User;
import com.qheeshow.eway.service.service.UserService;
import com.qheeshow.eway.wechart.constant.Constant;
import org.apache.http.client.methods.HttpGet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Service
public class OAuthFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(OAuthFilter.class);

    public void destroy() {
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        String code = request.getParameter("code");
        if (StringUtils.isEmpty(code) || session.getAttribute("loginUser") != null) {//放行
            chain.doFilter(request, response);
            return;
        }
        try {
            String result = XHttpClient.doRequest(new HttpGet("https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + Config.get("wechat.appid") + "&secret=" + Config.get("wechat.secret") + "&code=" + code + "&grant_type=authorization_code"));
            LOGGER.debug("请求获取openid的返回结果:{}", request);
            JSONObject jsonObject = JSONObject.parseObject(result);
            String openid = jsonObject.getString("openid");
            if (StringUtils.isEmpty(openid)) {
                LOGGER.error("伪造的code={}", code);
                request.getRequestDispatcher("/pub/404.jsp").forward(request, response);
                return;
            }
            WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletRequest.getServletContext());
            UserService userService = (UserService) webApplicationContext.getBean("userService");
            User loginUser = userService.getByUnionid(jsonObject.getString("unionid"));
            if (loginUser == null)
                loginUser = userService.getByGzhOpenid(openid);
            else {
                if (StringUtils.isEmpty(loginUser.getGzhOpenid())) {
                    loginUser.setGzhOpenid(openid);
                    userService.update(loginUser);
                }
            }
            if (loginUser == null) {
                loginUser = new User();
                //获取用户基本信息
                String jsonResult = XHttpClient.doRequest(new HttpGet("https://api.weixin.qq.com/cgi-bin/user/info?access_token=" + Constant.ACCESS_TOKEN + "&openid=" + openid + "&lang=zh_CN"));
                jsonObject = JSONObject.parseObject(jsonResult);
                loginUser.setGzhOpenid(jsonObject.getString("openid"));
                loginUser.setNickname(jsonObject.getString("nickname"));
                loginUser.setSex(jsonObject.getInteger("sex"));
                loginUser.setCity(jsonObject.getString("city"));
                loginUser.setProvince(jsonObject.getString("province"));
                loginUser.setCountry(jsonObject.getString("country"));
                loginUser.setHeadimgurl(jsonObject.getString("headimgurl"));
                loginUser.setUnionid(jsonObject.getString("unionid"));
                loginUser.setSubscribe(jsonObject.getInteger("subscribe"));
                userService.saveFromGzh(loginUser);
            }
            session.setAttribute("loginUser", loginUser);
        } catch (Exception e) {
            LOGGER.error("获取openid或用户基本信息失败:", e);
        }
        chain.doFilter(request, response);
    }

}