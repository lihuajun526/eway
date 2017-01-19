package com.qheeshow.eway.common;

import com.qheeshow.eway.common.exception.RequestException;
import com.qheeshow.eway.common.http.XHttpClient;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lihuajun on 17-1-19.
 */
public class SmsTest {

    @Test
    public void test1() {
        HttpPost httpPost = new HttpPost("http://sms.253.com/msg/send");

        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("un", "N5030495"));
        params.add(new BasicNameValuePair("pw", "ecgtvrmxJ2bb43"));
        params.add(new BasicNameValuePair("phone", "13148376469"));
        params.add(new BasicNameValuePair("rd", "1"));
        params.add(new BasicNameValuePair("msg", "【253云通讯】您好，你的验证码是123456"));
        params.add(new BasicNameValuePair("ex", null));
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(params, "utf-8"));
            System.out.println(XHttpClient.doRequest(httpPost));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (RequestException e) {
            e.printStackTrace();
        }

    }

}
