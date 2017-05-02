package com.qheeshow.eway.service.service.impl;

import com.google.zxing.WriterException;
import com.qheeshow.eway.common.bean.wechat.pay.ResultOrder;
import com.qheeshow.eway.common.bean.wechat.pay.WechatNotify;
import com.qheeshow.eway.common.bean.wechat.pay.exception.ResponseWechat;
import com.qheeshow.eway.common.exception.CommonException;
import com.qheeshow.eway.common.exception.RequestException;
import com.qheeshow.eway.common.http.XHttpClient;
import com.qheeshow.eway.common.util.Bean2Xml;
import com.qheeshow.eway.common.util.Config;
import com.qheeshow.eway.common.util.MatrixToImageWriter;
import com.qheeshow.eway.common.util.StrUtil;
import com.qheeshow.eway.service.dao.GoodsMapper;
import com.qheeshow.eway.service.model.Goods;
import com.qheeshow.eway.service.model.Order;
import com.qheeshow.eway.service.model.OrderWechat;
import com.qheeshow.eway.service.service.GoodsService;
import com.qheeshow.eway.service.service.PayService;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by lihuajun on 2017/4/26.
 */
@Service("payService")
public class PayServiceImpl implements PayService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PayServiceImpl.class);

    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public String createWechatORCode(String url, String fileType) throws IOException, WriterException {

        String[] strs = StrUtil.getFilePath(fileType);
        String fileName = System.currentTimeMillis() + ".gif";
        File dir = new File(strs[0]);
        if (!dir.exists())
            dir.mkdir();
        File f = new File(dir, fileName);
        MatrixToImageWriter.createQRCode(url, f);
        return strs[1] + "/" + fileName;
    }

    @Override
    public ResultOrder order(OrderWechat orderWechat) throws UnsupportedEncodingException, RequestException, CommonException {

        Map<String, String> params = new TreeMap<>();
        params.put("appid", Config.get("wechat.appid"));
        params.put("mch_id", Config.get("wechat.mchid"));
        params.put("device_info", Config.get("wechat.device.info"));
        params.put("nonce_str", StrUtil.getRandomString(32));
        //params.put("body", orderWechat.getDescription());
        params.put("body", new String("套餐".getBytes("UTF-8"), "UTF-8"));
        params.put("out_trade_no", orderWechat.getOrderno());
        params.put("total_fee", orderWechat.getTotalFee());
        params.put("spbill_create_ip", Config.get("server.ip"));
        params.put("notify_url", Config.get("wechat.notify.url"));
        params.put("trade_type", "NATIVE");
        params.put("sign", StrUtil.sign(params));

        String xml = StrUtil.map2Xml(params);
        System.out.println(new String(xml.getBytes("UTF-8"), "UTF-8"));
        StringEntity stringEntity = new StringEntity(new String(xml.getBytes("UTF-8"), "UTF-8"),"UTF-8");

        HttpPost httpPost = new HttpPost("https://api.mch.weixin.qq.com/pay/unifiedorder");
        httpPost.setEntity(stringEntity);
        httpPost.setHeader("Content-Type","text/xml;charset=UTF-8");
        String response = XHttpClient.doRequest(httpPost);
        ResultOrder resultOrder = (ResultOrder) Bean2Xml.toBean(response, ResultOrder.class);
        if (!resultOrder.getReturn_code().equalsIgnoreCase("SUCCESS")) {
            throw new CommonException(resultOrder.getReturn_msg());
        }
        if (!resultOrder.getResult_code().equalsIgnoreCase("SUCCESS")) {
            LOGGER.error("下单失败：{}", resultOrder.getErr_code_des());
            throw new CommonException(resultOrder.getErr_code());
        }
        return resultOrder;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackForClassName = "Exception")
    public String order(WechatNotify wechatNotify) {
        ResponseWechat responseWechat = new ResponseWechat();
        //查询商品
        Goods goods = goodsMapper.selectByPrimaryKey(Integer.valueOf(wechatNotify.getProduct_id()));
        responseWechat.setAppid(wechatNotify.getAppid());
        responseWechat.setMch_id(wechatNotify.getMch_id());
        if (goods == null) {
            responseWechat.setResult_code("FAIL");
            return Bean2Xml.toXml(responseWechat);
        }
        //下单（商户本地）
        Order order = new Order();
        order.setTitle(goods.getTitle());
        order.setPrice(goods.getPrice());
        order.setOrderNo(StrUtil.getOrderno());
        order.setStatus(1);
        order.setUserid(1);

        //统一下单
        OrderWechat orderWechat = new OrderWechat();
        orderWechat.setTotalFee(String.valueOf(goods.getPrice().multiply(new BigDecimal(100))));


        ResultOrder resultOrder;
        try {
            resultOrder = this.order(orderWechat);
        } catch (RequestException e) {
            e.printStackTrace();
        } catch (CommonException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }


}
