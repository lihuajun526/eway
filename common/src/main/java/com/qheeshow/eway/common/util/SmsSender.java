package com.qheeshow.eway.common.util;

import com.qheeshow.eway.common.exception.SendSMSException;
import com.qheeshow.eway.common.http.XHttpClient;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lihuajun on 17-1-19.
 */
public class SmsSender {

    private static final Logger LOGGER = LoggerFactory.getLogger(SmsSender.class);

    public static void send(String phone) throws SendSMSException {

        String account = Config.get("cl.account");
        String password = Config.get("cl.password");
        String url = Config.get("cl.url");
        String sign = Config.get("cl.sign");
        String vcode = SmsNumbeUtil.createRandom(true, 6);

        HttpPost httpPost = new HttpPost(url);
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("un", account));
        params.add(new BasicNameValuePair("pw", password));
        params.add(new BasicNameValuePair("phone", phone));
        params.add(new BasicNameValuePair("rd", "1"));
        params.add(new BasicNameValuePair("msg", "【" + sign + "】您好，您的验证码是" + vcode));
        params.add(new BasicNameValuePair("ex", null));
        String response = null;
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(params, "utf-8"));
            response = XHttpClient.doRequest(httpPost);
            String[] strs = response.split("\\n");
            if (strs.length != 2)
                throw new SendSMSException();
            String[] results = strs[0].split(",");
            if (!results[1].equals("0"))
                throw new SendSMSException();
        } catch (Exception e) {
            LOGGER.error("发送短信验证码到{}失败,返回{}", phone, response, e);
            throw new SendSMSException();
        }
    }
}
