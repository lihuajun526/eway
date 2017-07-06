package com.qheeshow.eway.service.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qheeshow.eway.common.constant.ExceptionTypeEnum;
import com.qheeshow.eway.common.exception.CommonException;
import com.qheeshow.eway.common.exception.RequestException;
import com.qheeshow.eway.common.http.XHttpClient;
import com.qheeshow.eway.common.util.Config;
import com.qheeshow.eway.common.util.StrUtil;
import com.qheeshow.eway.service.dao.CallRecordMapper;
import com.qheeshow.eway.service.model.BindMap;
import com.qheeshow.eway.service.model.CallRecord;
import com.qheeshow.eway.service.model.User;
import com.qheeshow.eway.service.service.BindMapService;
import com.qheeshow.eway.service.service.MixcomService;
import com.qheeshow.eway.service.service.UserService;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

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

    private static final Logger LOGGER = LoggerFactory.getLogger(MixcomServiceImpl.class);

    private final String serverRoot = "http://api.mixcaller.com/v2";
    @Autowired
    private BindMapService bindMapService;
    @Autowired
    private CallRecordMapper callRecordMapper;
    @Autowired
    private UserService userService;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackForClassName = "Exception")
    public String bound(String a, String b, int callTime) throws UnsupportedEncodingException, CommonException, RequestException {
        //16600000000，手机号特殊处理
        if(a.indexOf("1660000")!=-1){
            a = Config.get("customer.tel");
        }
        if(b.indexOf("1660000")!=-1){
            b = Config.get("customer.tel");
        }
        a = StrUtil.handleAdd86(a);
        b = StrUtil.handleAdd86(b);

        //如果已经绑定且未解绑则返回
        BindMap bindMap = new BindMap();
        bindMap.setCalling(a);
        bindMap.setCalled(b);
        List<BindMap> list = bindMapService.listBindRecord(bindMap);
        if (list.size() > 0)
            return StrUtil.handleDel86(list.get(0).getMixnum());

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
        nowTime.add(Calendar.MINUTE, 10);//绑定关系10分钟内有效
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        params.add(new BasicNameValuePair("endDate", sdf.format(nowTime.getTime())));
        httpPost.setEntity(new UrlEncodedFormEntity(params, "utf-8"));
        String response = XHttpClient.doRequest(httpPost);
        //{"code":"200","msg":"成功","data":{"subscriptionId":"457b6d3f-50ba-4c9e-a312-d8819d136eda"}}
        JSONObject jsonObject = JSONObject.parseObject(response);
        if (!"200".equals(jsonObject.getString("code")))
            throw new CommonException(ExceptionTypeEnum.Bound_Mixcom_No_ERROR);
        String bindId = jsonObject.getJSONObject("data").getString("subscriptionId");
        bindMap.setBindId(bindId);
        bindMap.setMixnum(mixNo);
        bindMapService.save(bindMap);
        return StrUtil.handleDel86(mixNo);
    }

    @Override
    public void unBound(String mixNo, String a, String b) throws CommonException {
        String appkey = Config.get("mixcom.appkey");
        String requestUrl = serverRoot + "/?m=interfaces&c=virt&a=index&act=unbindnumber&appkey=" + appkey;
        long time = System.currentTimeMillis();
        String sign = StrUtil.md5(appkey + "interfaces" + "virt" + "unbindnumber" + mixNo + Config.get("mixcom.appsecret") + time);
        requestUrl += "&sign=" + sign + "&time=" + time;
        HttpPost httpPost = new HttpPost(requestUrl);
        //设置表单参数
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("virtualnumber", mixNo));
        params.add(new BasicNameValuePair("aparty", a));
        params.add(new BasicNameValuePair("bparty", b));
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(params, "utf-8"));
            String response = XHttpClient.doRequest(httpPost);
            //{code":"200","msg":"成功","data":""}
            JSONObject jsonObject = JSONObject.parseObject(response);
            if (!"200".equals(jsonObject.getString("code")))
                throw new CommonException(ExceptionTypeEnum.UnBound_Mixcom_No_ERROR);
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("error:", e);
            throw new CommonException(ExceptionTypeEnum.UnBound_Mixcom_No_ERROR);
        } catch (RequestException e) {
            LOGGER.error("error:", e);
            throw new CommonException(ExceptionTypeEnum.UnBound_Mixcom_No_ERROR);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackForClassName = "Exception")
    public void saveRecord(CallRecord callRecord) throws CommonException {
        BindMap bindMap = bindMapService.getByBindId(callRecord.getBindId());
        if (bindMap == null)
            throw new CommonException(ExceptionTypeEnum.Bound_Map_Not_Exist_ERROR);
        //保存通话记录
        callRecordMapper.insert(callRecord);
        //更新用户通话时长
        User user = userService.getByMobile(callRecord.getCalling());
        if (user == null)
            throw new CommonException(ExceptionTypeEnum.Calling_Not_Exist_ERROR);
        int duration = 0;
        if (!StringUtils.isEmpty(callRecord.getDuration())) {
            duration = Integer.valueOf(callRecord.getDuration());
        }
        user.setCallTime(user.getCallTime().intValue() - duration);
        userService.update(user);
        //解绑
        this.unBound(callRecord.getVirtualNumber(), callRecord.getCalling(), callRecord.getCalled());
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
        String code = jsonObject.getString("code");
        if (!"200".equals(code)) {
            throw new CommonException(ExceptionTypeEnum.Get_Mixcom_No_ERROR);
        }
        JSONArray jsonArray = jsonObject.getJSONArray("data");
        return jsonArray.getJSONObject(0).getString("number");
    }
}
