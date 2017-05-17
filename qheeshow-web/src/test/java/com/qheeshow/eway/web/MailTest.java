package com.qheeshow.eway.web;

import com.qheeshow.eway.service.model.MailBean;
import com.qheeshow.eway.service.service.MailService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by lihuajun on 2017/5/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring.xml"})
public class MailTest {

    @Autowired
    private MailService mailService;

    @org.junit.Test
    public void test() {
        MailBean mailBean = new MailBean();
        mailBean.setToAddress("515182557@qq.com");
        mailBean.setContent("<body><h1>你好！</h1></body>");
        mailBean.setFromAddress("service@qheefund.com");
        mailBean.setMailServerHost("smtp.exmail.qq.com");
        mailBean.setMailServerPort("465");
        mailBean.setUserName("service@qheefund.com");
        mailBean.setPassword("wutongE123456");


        mailService.sendHtmlMail(mailBean);
    }


}
