package com.qheeshow.eway.wechart.task;

import com.alibaba.fastjson.JSONObject;
import com.qheeshow.eway.wechart.constant.Constant;
import com.qheeshow.eway.wechart.exception.RequestException;
import com.qheeshow.eway.service.AppConfig;
import com.qheeshow.eway.wechart.util.XHttpClient;
import org.apache.http.client.methods.HttpGet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by lihuajun on 2016/9/12.
 */
@Service("wechatConstant")
public class WechatConstant {

    private static final Logger LOGGER = LoggerFactory.getLogger(WechatConstant.class);

    @Autowired
    private AppConfig appConfig;

    public void init() {

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                //更新ACCESS_TOKEN
                HttpGet httpGet = new HttpGet("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appConfig.wechatAppid + "&secret=" + appConfig.wechatSecret);
                try {
                    JSONObject jsonObject = XHttpClient.doRequest(httpGet);
                    Constant.ACCESS_TOKEN = jsonObject.getString("access_token");
                    LOGGER.info("最新的ACCESS_TOKEN=" + Constant.ACCESS_TOKEN);
                } catch (RequestException e) {
                    LOGGER.error("更新ACCESS_TOKEN失败:", e);
                }

                //更新JSAPI_TICKET
                httpGet = new HttpGet("https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=" + Constant.ACCESS_TOKEN + "&type=jsapi");
                try {
                    JSONObject jsonObject = XHttpClient.doRequest(httpGet);
                    Constant.JSAPI_TICKET = jsonObject.getString("ticket");
                    LOGGER.info("最新的JSAPI_TICKET=" + Constant.JSAPI_TICKET);
                } catch (RequestException e) {
                    LOGGER.error("更新JSAPI_TICKET失败:", e);
                }
            }
        };
        Timer timer = new Timer();
        long delay = 0;
        long intevalPeriod = 7000 * 1000;
        timer.scheduleAtFixedRate(task, delay, intevalPeriod);
    }
}
