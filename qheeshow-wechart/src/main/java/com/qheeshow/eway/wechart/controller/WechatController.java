package com.qheeshow.eway.wechart.controller;

import com.alibaba.fastjson.JSONObject;
import com.qheeshow.eway.common.bean.wechat.WechatMsg;
import com.qheeshow.eway.common.http.XHttpClient;
import com.qheeshow.eway.service.AppConfig;
import com.qheeshow.eway.service.model.User;
import com.qheeshow.eway.service.service.UserService;
import com.qheeshow.eway.wechart.constant.Constant;
import com.qheeshow.eway.wechart.util.wechat.WechatProcess;
import org.apache.http.client.methods.HttpGet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;

/**
 * Created by lihuajun on 16-7-6.
 */
@Controller
@RequestMapping("/wechat")
public class WechatController {

    private static final Logger LOGGER = LoggerFactory.getLogger(WechatController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private AppConfig appConfig;

    @RequestMapping("/center")
    public void center(HttpServletRequest request, HttpServletResponse response) throws IOException {

        /** 读取接收到的xml消息 */
        StringBuffer sb = new StringBuffer();
        InputStream is = request.getInputStream();
        InputStreamReader isr = new InputStreamReader(is, "UTF-8");
        BufferedReader br = new BufferedReader(isr);
        String s = null;
        while ((s = br.readLine()) != null) {
            sb.append(s);
        }
        //接收到微信端发送过来的xml数据
        String xml = sb.toString();
        String result = "";
        /** 判断是否是微信接入激活验证，只有首次接入验证时才会收到echostr参数，此时需要把它直接返回 */
        String echostr = request.getParameter("echostr");
        if (!StringUtils.isEmpty(echostr)) {
            LOGGER.info("接入成功");
            LOGGER.info(request.getParameter("nonce"));
            LOGGER.info(request.getParameter("signature"));
            result = echostr;
        } else {
            //正常的微信处理流程
            WechatMsg wechatMsg = new WechatProcess().processWechatMag(xml);
            String openid = wechatMsg.getFromUserName();
            User user = userService.getByOpenid(openid);
            if ("subscribe".equalsIgnoreCase(wechatMsg.getEvent())) {//关注
                if (null == user) {//添加用户
                    user = new User();
                    user.setStatus(1);
                    user.setOpenid(openid);
                    //获取用户基本信息
                    HttpGet httpGet = new HttpGet("https://api.weixin.qq.com/cgi-bin/user/info?access_token=" + Constant.ACCESS_TOKEN + "&openid=" + openid + "&lang=zh_CN");
                    try {
                        String jsonResult = XHttpClient.doRequest(httpGet);
                        JSONObject jsonObject = JSONObject.parseObject(jsonResult);
                        user.setGzhOpenid(jsonObject.getString("openid"));
                        user.setNickname(jsonObject.getString("nickname"));
                        user.setSex(jsonObject.getInteger("sex"));
                        user.setCity(jsonObject.getString("city"));
                        user.setProvince(jsonObject.getString("province"));
                        user.setCountry(jsonObject.getString("country"));
                        user.setHeadimgurl(jsonObject.getString("headimgurl"));
                        user.setUnionid(jsonObject.getString("headimgurl"));
                        user.setSubscribe(1);
                    } catch (Exception e) {
                        LOGGER.error("获取用户基本信息错误：", e);
                    }
                    userService.saveFromGzh(user);
                    LOGGER.info("新用户[{}]关注成功", user.getNickname());
                } else {//更新用户状态
                    User updateUser = new User();
                    updateUser.setId(user.getId());
                    updateUser.setSubscribe(1);
                    userService.update(updateUser);
                    LOGGER.info("老用户[{}]重新关注成功", user.getNickname());
                }
                WechatMsg reply = new WechatMsg();
                reply.setToUserName(openid);
                reply.setFromUserName(appConfig.wechatAccount);
                reply.setCreateTime(new Date());
                reply.setMsgType("text");
                reply.setContent("欢迎关注梧桐e路");
                result = reply.toXml();
                LOGGER.debug("关注后的返回数据：" + result);
            } else if ("unsubscribe".equalsIgnoreCase(wechatMsg.getEvent())) {//取消关注
                User updateUser = new User();
                updateUser.setId(user.getId());
                updateUser.setSubscribe(2);
                userService.update(updateUser);
                LOGGER.info("用户[{}]取消关注成功", user.getNickname());
            } else if ("scan".equalsIgnoreCase(wechatMsg.getEvent())) {
                LOGGER.info("已关注的用户[{}]扫描二维码不做任何处理", user.getNickname());
            } else if ("click".equalsIgnoreCase(wechatMsg.getEvent())) {//点击菜单
                if ("getVip".equalsIgnoreCase(wechatMsg.getEventKey())) {

                }
            }
        }
        PrintWriter writer = response.getWriter();
        writer.print(result);
        writer.flush();
        writer.close();
    }

    /**
     * 异常处理
     *
     * @param e
     * @param response
     */
    @ExceptionHandler(Exception.class)
    private void exception(Exception e, HttpServletResponse response) {

        LOGGER.error("have exception", e);
        try {
            PrintWriter writer = response.getWriter();
            writer.print("success");
            writer.flush();
            writer.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
