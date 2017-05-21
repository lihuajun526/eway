package com.qheeshow.eway.wechart.controller;

import com.qheeshow.eway.common.exception.RequestException;
import com.qheeshow.eway.common.http.XHttpClient;
import com.qheeshow.eway.wechart.base.BaseController;
import com.qheeshow.eway.wechart.constant.Constant;
import com.qheeshow.eway.wechart.util.wechat.Menus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lihuajun on 16-7-6.
 */
@Controller
@RequestMapping("/sys")
public class SysController extends BaseController {

    /**
     * 更新菜单
     *
     * @return
     */
    @RequestMapping("/menu/update")
    @ResponseBody
    public String updateMenu() throws  UnsupportedEncodingException, RequestException {

        String menus = new Menus().create();
        HttpPost httpPost = new HttpPost("https://api.weixin.qq.com/cgi-bin/menu/create");

        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("access_token", Constant.ACCESS_TOKEN));
        params.add(new BasicNameValuePair("body", menus));

        httpPost.setEntity(new UrlEncodedFormEntity(params, "utf-8"));

        LOGGER.info(XHttpClient.doRequest(httpPost));

        return "{'status':'success'}";
    }


}
