package com.qheeshow.eway.service.service.impl;

import com.qheeshow.eway.common.bean.wechat.pay.ResultOrder;
import com.qheeshow.eway.common.exception.CommonException;
import com.qheeshow.eway.common.exception.RequestException;
import com.qheeshow.eway.common.http.XHttpClient;
import com.qheeshow.eway.common.util.Bean2Xml;
import com.qheeshow.eway.common.util.Config;
import com.qheeshow.eway.common.util.MatrixToImageWriter;
import com.qheeshow.eway.common.util.StrUtil;
import com.qheeshow.eway.service.model.OrderWechat;
import com.qheeshow.eway.service.service.PayService;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by lihuajun on 2017/4/26.
 */
@Service("payService")
public class PayServiceImpl implements PayService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PayServiceImpl.class);

    @Override
    public String createWechatORCode(String url) {

        String[] strs = StrUtil.getFilePath("qrcode");
        String fileName = System.currentTimeMillis() + ".gif";
        try {
            File dir = new File(strs[0]);
            if (!dir.exists())
                dir.mkdir();
            File f = new File(dir, fileName);

            MatrixToImageWriter.createQRCode(url, f);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strs[1] + "/" + fileName;
    }

    @Override
    public String order(OrderWechat orderWechat) throws UnsupportedEncodingException, RequestException, CommonException {

        Map<String, String> params = new TreeMap<>();
        params.put("appid", Config.get("wechat.appid"));
        params.put("mch_id", Config.get("wechat.mchid"));
        params.put("device_info", Config.get("wechat.device.info"));
        params.put("nonce_str", StrUtil.getRandomString(32));
        params.put("body", orderWechat.getDescription());
        params.put("out_trade_no", orderWechat.getOrderno());
        params.put("total_fee", orderWechat.getTotalFee());
        params.put("spbill_create_ip", Config.get("server.ip"));
        params.put("notify_url", Config.get("wechat.notify.url"));
        params.put("trade_type", "NATIVE");
        params.put("sign", StrUtil.sign(params));

        String xml = StrUtil.map2Xml(params);
        StringEntity stringEntity = new StringEntity(new String(xml.getBytes(), "ISO8859-1"));
        HttpPost httpPost = new HttpPost("https://api.mch.weixin.qq.com/pay/unifiedorder");
        httpPost.setEntity(stringEntity);
        String response = XHttpClient.doRequest(httpPost);
        ResultOrder resultOrder = (ResultOrder) Bean2Xml.toBean(response, ResultOrder.class);
        if (!resultOrder.getReturn_code().equalsIgnoreCase("SUCCESS")) {
            throw new CommonException(resultOrder.getReturn_msg());
        }
        if (!resultOrder.getResult_code().equalsIgnoreCase("SUCCESS")) {
            LOGGER.error("下单失败：{}", resultOrder.getErr_code_des());
            throw new CommonException(resultOrder.getErr_code());
        }
        return resultOrder.getCode_url();
    }

    public static void main(String[] args) {

        Map<String, String> params = new TreeMap<>();
        params.put("b", "b");
        params.put("a", "a");
        params.put("e", "e");
        params.put("c", "c");

        for (Map.Entry entry : params.entrySet()) {
            System.out.println(params.get(entry.getKey()));
        }
    }
}
