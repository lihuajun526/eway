package com.qheeshow.eway.backstage.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Created by lihuajun on 2016/9/17.
 */
@Service("appConfig")
public class AppConfig {

    @Value("${app.name}")
    public String appName;

    @Value("${app.domain}")
    public String appDomain;

    @Value("${wechat.account}")
    public String wechatAccount;

    @Value("${wechat.appid}")
    public String wechatAppid;

    @Value("${wechat.secret}")
    public String wechatSecret;

    @Value("${httpclient.request.timeout}")
    public Integer httpclientRequestTimeout;

    @Value("${httpclient.connect.timeout}")
    public Integer httpclientConnectTimeout;

    @Value("${httpclient.socket.timeout}")
    public Integer httpclientSocketTimeout;

}
