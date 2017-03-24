package com.qheeshow.eway.common.http;

import com.qheeshow.eway.common.exception.RequestException;
import com.qheeshow.eway.common.util.Config;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by lihuajun on 2016/8/26.
 */
public class XHttpClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(XHttpClient.class);

    private static PoolingHttpClientConnectionManager cm = null;
    private static RequestConfig requestConfig = null;

    static {
        ConnectionSocketFactory plainsf = PlainConnectionSocketFactory.getSocketFactory();
        LayeredConnectionSocketFactory sslsf = SSLConnectionSocketFactory.getSocketFactory();
        Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", plainsf)
                .register("https", sslsf)
                .build();
        cm = new PoolingHttpClientConnectionManager(registry);
        // 最大连接数
        cm.setMaxTotal(Config.getInt("httpclient.pool.max.connection"));
        // 每个路由基础的连接数
        cm.setDefaultMaxPerRoute(Config.getInt("httpclient.pool.max.route"));
        requestConfig = RequestConfig.custom()
                // 从连接池获取连接的最大超时时间
                .setConnectionRequestTimeout(Config.getInt("httpclient.request.timeout"))
                        // 建立连接的最大超时时间
                .setConnectTimeout(Config.getInt("httpclient.connect.timeout"))
                        // 数据传输处理的最大超时时间
                .setSocketTimeout(Config.getInt("httpclient.socket.timeout"))
                        // 设置代理
                        //.setProxy(new HttpHost(ProxyHost, ProxyPort))
                        // 什么意思
                .setExpectContinueEnabled(false)
                .build();
    }

    public static String doRequest(HttpRequestBase httpRequestBase) throws RequestException {
        return doRequest(httpRequestBase, "utf-8");
    }

    public static String doRequest(HttpRequestBase httpRequestBase, String charset) throws RequestException {

        String response = null;
        CloseableHttpResponse httpResponse = null;
        try {
            // 设置请求参数
            httpRequestBase.setConfig(requestConfig);
            // 获得httpclient
            CloseableHttpClient httpClient = HttpClients.custom()
                    .setConnectionManager(cm)
                    .build();
            httpResponse = httpClient.execute(httpRequestBase);
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            if (statusCode != HttpStatus.SC_OK) {
                LOGGER.warn("请求{}返回{}", httpRequestBase.getURI().toString(), statusCode);
                throw new RequestException();
            } else {
                HttpEntity httpEntity = httpResponse.getEntity();
                InputStream in = httpEntity.getContent();
                response = IOUtils.toString(in, charset);
                in.close();
            }
        } catch (Exception e) {
            LOGGER.error("error:", e);
            throw new RequestException(httpRequestBase.getURI().toString());
        } finally {
            try {
                if (httpResponse != null)
                    httpResponse.close();
            } catch (IOException e) {
                LOGGER.error("httpResponse close exception:", e);
            }
        }
        return response;
    }
}
