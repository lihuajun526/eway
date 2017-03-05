package com.qheeshow.eway.web;

import com.alibaba.fastjson.JSONArray;
import com.qheeshow.eway.common.util.SpringContext;
import com.qheeshow.eway.service.dao.XwcmclassinfoMapper;
import com.qheeshow.eway.service.model.Xwcmclassinfo;
import com.qheeshow.eway.service.service.XwcmclassinfoService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.util.List;

/**
 * Created by lihuajun on 17-1-19.
 */
public class ClassinfoTest {

    @Test
    public void test1() {
        SpringContext.init("classpath:spring/spring.xml");
        ApplicationContext ctx = SpringContext.getContext();
        XwcmclassinfoService xwcmclassinfoService = (XwcmclassinfoService) ctx.getBean("xwcmclassinfoService");
        System.out.println(JSONArray.toJSONString(xwcmclassinfoService.listByRoot(44)));
    }

}
