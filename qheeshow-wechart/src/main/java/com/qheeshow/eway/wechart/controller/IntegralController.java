package com.qheeshow.eway.wechart.controller;

import com.alibaba.fastjson.JSONObject;
import com.vip.integral.base.BaseController;
import com.vip.integral.base.Result;
import com.vip.integral.constant.Constant;
import com.operational.platform.dbservice.service.UserService;
import com.vip.integral.exception.RequestException;
import com.operational.platform.dbservice.model.User;
import com.vip.integral.util.AppConfig;
import com.vip.integral.util.StrUtil;
import com.vip.integral.util.XHttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.DigestException;
import java.sql.Timestamp;
import java.util.*;

/**
 * Created by lihuajun on 16-7-6.
 */
@Controller
@RequestMapping("/integral")
public class IntegralController extends BaseController {

    @Autowired
    private AppConfig appConfig;
    @Autowired
    private UserService userService;

    /**
     * 分享到朋友圈获得积分奖励
     *
     * @param userid
     * @param count
     * @return
     */
    @ResponseBody
    @RequestMapping("/share/encourage")
    public String encourageFromShare(Integer userid, Integer count) {


        Result<Boolean> result = new Result<>();

        //result.set(0, userService.getByOpenid(user.getOpenid()));

        return result.toString();
    }


    @RequestMapping("/rec/populate")
    public ModelAndView integralPopulateRec(HttpServletRequest request) {

        ModelAndView modelAndView = new ModelAndView("integral_populate_rec");
        String openid = (String) request.getSession().getAttribute("openid");
        User user = userService.getByOpenid(openid);
        if (!StringUtils.isEmpty(user.getSpreadRecord())) {
            //id1:status#id2:status
            List<Integer> ids = new ArrayList<>();
            String[] strs = user.getSpreadRecord().split("#");
            for (String str : strs) {
                String[] kv = str.split(":");
                if ("1".equals(kv[1])) {
                    ids.add(Integer.valueOf(kv[0]));
                }
            }
            List<User> list = userService.getByIds(ids);
            modelAndView.addObject("list", list);
        }
        return modelAndView;
    }

    /**
     * 赚积分
     *
     * @return
     */
    @RequestMapping("/earn")
    public ModelAndView integralEarn(HttpServletRequest request) throws DigestException, UnsupportedEncodingException {

        String openid = (String) request.getSession().getAttribute("openid");

        ModelAndView modelAndView = new ModelAndView("integral_earn");
        User user = userService.getByOpenid(openid);

        //创建二维码ticket
        HttpPost httpPost = new HttpPost("https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=" + Constant.ACCESS_TOKEN);
        String body = "{\"expire_seconds\": 2592000, \"action_name\": \"QR_SCENE\", \"action_info\": {\"scene\": {\"scene_id\": " + user.getId() + "}}}";
        StringEntity s = new StringEntity(body);
        s.setContentEncoding("UTF-8");
        s.setContentType("application/json");//发送json数据需要设置contentType
        httpPost.setEntity(s);

        JSONObject jsonObject = null;
        try {
            jsonObject = XHttpClient.doRequest(httpPost);
        } catch (RequestException e) {
            logger.error("创建二维码ticket错误:", e);
        }

        //默认推广时间是从打开赚积分的页面，当用户将二维码分享到朋友圈时再次更新推广时间
        if (user.getPopulateTime() == null) {
            user.setPopulateTime(new Date());
            userService.update(user);
        }

        Map param = this.getParam(appConfig.appDomain + "/integral/earn");

        modelAndView.addObject("appId", appConfig.wechatAppid);
        modelAndView.addObject("timestamp", param.get("time"));
        modelAndView.addObject("nonceStr", param.get("noncestr"));
        modelAndView.addObject("signature", param.get("signature"));
        modelAndView.addObject("ticket", jsonObject == null ? "" : URLEncoder.encode(jsonObject.getString("ticket"), "utf-8"));

        return modelAndView;
    }


    private Map<String, String> getParam(String url) throws DigestException {

        String noncestr = StrUtil.getNoncestr();
        Timestamp d = new Timestamp(System.currentTimeMillis());
        String time = Long.toString(d.getTime() / 1000);

        String str = "jsapi_ticket=" + Constant.JSAPI_TICKET + "&noncestr=" + noncestr + "&timestamp=" + time + "&url=" + url;
        logger.info(str);
        String signature = StrUtil.sha1(str);
        logger.info(signature);
        Map<String, String> data = new HashMap<>();
        data.put("noncestr", noncestr);
        data.put("signature", signature);
        data.put("time", time);

        return data;
    }

}
