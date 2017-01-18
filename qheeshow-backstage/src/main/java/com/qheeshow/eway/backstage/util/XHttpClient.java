package com.qheeshow.eway.backstage.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qheeshow.eway.wechart.constant.ExceptionTypeEnum;
import com.qheeshow.eway.wechart.exception.RequestException;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by lihuajun on 16-6-21.
 */
public class XHttpClient {

    private final static Logger LOGGER = LoggerFactory.getLogger(XHttpClient.class);

    private static RequestConfig requestConfig = null;

    static {

        requestConfig = RequestConfig.custom()
                .setConnectionRequestTimeout(30000)
                .setConnectTimeout(30000)
                .setSocketTimeout(30000)
                .setExpectContinueEnabled(false)
                        //.setProxy(new HttpHost("127.0.0.1", 8888))
                .build();
    }

    public static JSONObject doRequest(HttpUriRequest httpUriRequest)
            throws RequestException {

        JSONObject jsonObject = null;
        CloseableHttpResponse httpResponse = null;
        HttpRequestBase httpRequestBase = null;
        try {
            httpRequestBase = (HttpRequestBase) httpUriRequest;
            //设置请求参数
            httpRequestBase.setConfig(requestConfig);
            //获得httpclient
            CloseableHttpClient httpClient = HttpConnectionManager.getHttpClient();

            //请求
            httpResponse = httpClient.execute(httpRequestBase);
            if (httpResponse.getStatusLine().getStatusCode() >= 400) {
                LOGGER.warn("http请求状态码为{}，请求地址{}", httpResponse.getStatusLine().getStatusCode(), httpRequestBase.getURI().toString());
                throw new RequestException(ExceptionTypeEnum.HTTP_REQUEST_ERROR);
            }
            HttpEntity httpEntity = httpResponse.getEntity();
            InputStream in = httpEntity.getContent();
            String response = IOUtils.toString(in, "utf-8");
            in.close();
            //{"errcode":40013,"errmsg":"invalid appid"}
            jsonObject = JSON.parseObject(response);
            String errcode = jsonObject.getString("errcode");
            if (!StringUtils.isEmpty(errcode) && !"0".equals(errcode)) {
                LOGGER.error("请求微信服务器返回错误[errcode={},errmsg={}]", jsonObject.getString("errcode"), jsonObject.getString("errmsg"));
                throw new RequestException(ExceptionTypeEnum.REQUEST_WECHAT_SERVER_ERROR);
            }

        } catch (Exception e) {
            LOGGER.error("request {} error:", httpUriRequest.getURI(), e);
            throw new RequestException(ExceptionTypeEnum.HTTP_REQUEST_ERROR);
        } finally {
            try {
                if (httpResponse != null)
                    httpResponse.close();
            } catch (IOException e) {
                LOGGER.error("error:", e);
            }
        }
        return jsonObject;
    }
}
