package com.qheeshow.eway.service.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qheeshow.eway.common.constant.ExceptionTypeEnum;
import com.qheeshow.eway.common.exception.CommonException;
import com.qheeshow.eway.common.exception.RequestException;
import com.qheeshow.eway.common.http.XHttpClient;
import com.qheeshow.eway.common.util.Config;
import com.qheeshow.eway.common.util.StrUtil;
import com.qheeshow.eway.service.service.MixcomService;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by lihuajun on 2017/3/24.
 */
@Service
public class MixcomServiceImpl implements MixcomService {

    private final String serverRoot = "http://api.mixcom.cn/v2";

    @Override
    public String bound(String a, String b, int callTime) throws UnsupportedEncodingException, CommonException, RequestException {
        a = StrUtil.handle(a);
        b = StrUtil.handle(b);
        String appkey = Config.get("mixcom.appkey");
        long time = System.currentTimeMillis();
        //拉取小号
        String mixNo = this.getMixNo(a, b);
        //绑定
        String requestUrl = serverRoot + "/?m=interfaces&c=virt&a=index&act=bindnumber&appkey=" + appkey;
        String sign = StrUtil.md5(appkey + "interfaces" + "virt" + "bindnumber" + mixNo + a + b + Config.get("mixcom.appsecret") + time);
        requestUrl += "&sign=" + sign + "&time=" + time;
        HttpPost httpPost = new HttpPost(requestUrl);
        //设置表单参数
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("virtualnumber", mixNo));
        params.add(new BasicNameValuePair("aparty", a));
        params.add(new BasicNameValuePair("bparty", b));
        Calendar nowTime = Calendar.getInstance();
        nowTime.add(Calendar.MINUTE, 30 + callTime);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        params.add(new BasicNameValuePair("endDate", sdf.format(nowTime.getTime())));
        httpPost.setEntity(new UrlEncodedFormEntity(params, "utf-8"));
        String response = XHttpClient.doRequest(httpPost);
        //{"code":"200","msg":"成功","data":{"subscriptionId":"457b6d3f-50ba-4c9e-a312-d8819d136eda"}}
        JSONObject jsonObject = JSONObject.parseObject(response);
        if (!"200".equals(jsonObject.getString("code")))
            throw new CommonException(ExceptionTypeEnum.Bound_Mixcom_No_ERROR);
        return mixNo;
    }

    private String getMixNo(String a, String b) throws UnsupportedEncodingException, RequestException, CommonException {
        //{"code":"200","msg":"成功","data":[{"number":"8617080346804"}]}
        long time = System.currentTimeMillis();
        String appkey = Config.get("mixcom.appkey");
        String requestUrl = serverRoot + "/?m=interfaces&c=virt&a=index&act=queryfreenumber&appkey=" + appkey;
        String sign = StrUtil.md5(appkey + "interfaces" + "virt" + "queryfreenumber" + a + b + Config.get("mixcom.appsecret") + time);
        requestUrl += "&sign=" + sign + "&time=" + time;
        HttpPost httpPost = new HttpPost(requestUrl);
        //设置表单参数
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("aparty", a));
        params.add(new BasicNameValuePair("bparty", b));
        httpPost.setEntity(new UrlEncodedFormEntity(params, "utf-8"));
        String response = XHttpClient.doRequest(httpPost);
        JSONObject jsonObject = JSONObject.parseObject(response);
        if (!"200".equals(jsonObject.getString("code")))
            throw new CommonException(ExceptionTypeEnum.Get_Mixcom_No_ERROR);
        JSONArray jsonArray = jsonObject.getJSONArray("data");
        return jsonArray.getJSONObject(0).getString("number");
    }
}
