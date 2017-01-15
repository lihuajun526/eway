package com.qheeshow.eway.wechart.controller;

import com.alibaba.fastjson.JSONObject;
import com.vip.integral.base.BaseController;
import com.vip.integral.util.AppConfig;
import com.vip.integral.util.XHttpClient;
import com.vip.integral.exception.RequestException;
import org.apache.http.client.methods.HttpGet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by lihuajun on 16-7-6.
 */
@Controller
@RequestMapping("/index")
public class IndexController extends BaseController {

    @Autowired
    private AppConfig appConfig;

    /**
     * 投诉/建议
     *
     * @return
     */
    @RequestMapping("/suggest")
    public ModelAndView suggest(String type, String code) {

        ModelAndView modelAndView = new ModelAndView("suggest");

        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + appConfig.wechatAppid + "&secret=" + appConfig.wechatSecret + "&code=" + code + "&grant_type=authorization_code";
        HttpGet httpGet = new HttpGet(url);
        try {
            JSONObject jsonObject = XHttpClient.doRequest(httpGet);
            modelAndView.addObject("openid", jsonObject.getString("openid"));
        } catch (RequestException e) {
            e.printStackTrace();
        }

        modelAndView.addObject("type", type);
        return modelAndView;
    }
}
