package com.qheeshow.eway.wechart.listener;

import com.vip.integral.task.WechatConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by lihuajun on 2016/9/12.
 */
public class ApplicationInit implements ServletContextListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationInit.class);

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        LOGGER.info("##############################系统初始化开始##############################");

        WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContextEvent.getServletContext());
        WechatConstant wechatConstant = (WechatConstant) webApplicationContext.getBean("wechatConstant");
        wechatConstant.init();

        LOGGER.info("##############################系统初始化结束##############################");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
