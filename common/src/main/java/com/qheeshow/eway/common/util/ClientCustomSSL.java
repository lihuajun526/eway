package com.qheeshow.eway.common.util;

import com.qheeshow.eway.common.exception.RequestException;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;

/**
 * Created by lihuajun on 2017/6/6.
 */
public class ClientCustomSSL {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientCustomSSL.class);

    public static String doPost(HttpPost httpPost) throws Exception {

        String response = null;
        CloseableHttpResponse httpResponse = null;

        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        FileInputStream instream = new FileInputStream(new File(Config.get("wechat.cert.path")));
        try {
            keyStore.load(instream, Config.get("wechat.mchid").toCharArray());
        } finally {
            instream.close();
        }

        // Trust own CA and all self-signed certs
        SSLContext sslcontext = SSLContexts.custom()
                .loadKeyMaterial(keyStore, Config.get("wechat.mchid").toCharArray())
                .build();
        // Allow TLSv1 protocol only
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                sslcontext,
                new String[]{"TLSv1"},
                null,
                SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
        CloseableHttpClient httpclient = HttpClients.custom()
                .setSSLSocketFactory(sslsf)
                .build();
        try {
            httpResponse = httpclient.execute(httpPost);
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            if (statusCode != HttpStatus.SC_OK) {
                LOGGER.warn("请求{}返回{}", httpPost.getURI().toString(), statusCode);
                throw new RequestException();
            } else {
                HttpEntity httpEntity = httpResponse.getEntity();
                InputStream in = httpEntity.getContent();
                response = IOUtils.toString(in, "utf-8");
                in.close();
            }
        } catch (Exception e) {
            LOGGER.error("error:", e);
            throw new RequestException(httpPost.getURI().toString());
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
