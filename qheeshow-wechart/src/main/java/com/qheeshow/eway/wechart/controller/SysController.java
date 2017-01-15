package com.qheeshow.eway.wechart.controller;

import com.vip.integral.base.BaseController;
import com.vip.integral.exception.RequestException;
import com.vip.integral.util.XHttpClient;
import com.vip.integral.constant.Constant;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public String updateMenu() throws RequestException {

        String menu = "{\"button\":[{\"type\":\"view\",\"name\":\"VIP会员\",\"url\":\"http://lihuajun526.xicp.net/goods/list\"},{\"type\":\"click\",\"name\":\"会员账号\",\"key\":\"getVip\"},{\"name\":\"黑眼圈365\",\"sub_button\":[{\"type\":\"view\",\"name\":\"投诉\",\"url\":\"http://www.soso.com/\"},{\"type\":\"view\",\"name\":\"建议\",\"url\":\"http://v.qq.com/\"}]}]}";

        HttpPost httpPost = new HttpPost("https://api.weixin.qq.com/cgi-bin/menu/create");

        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("access_token", Constant.ACCESS_TOKEN));
        params.add(new BasicNameValuePair("body", menu));

        XHttpClient.doRequest(httpPost);

        return "";
    }


}
