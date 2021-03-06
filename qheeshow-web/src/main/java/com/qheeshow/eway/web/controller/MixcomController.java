package com.qheeshow.eway.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.qheeshow.eway.common.exception.CommonException;
import com.qheeshow.eway.service.model.CallRecord;
import com.qheeshow.eway.service.model.User;
import com.qheeshow.eway.service.service.BindMapService;
import com.qheeshow.eway.service.service.InvestorService;
import com.qheeshow.eway.service.service.MixcomService;
import com.qheeshow.eway.service.service.UserService;
import com.qheeshow.eway.web.base.BaseController;
import com.qheeshow.eway.web.base.MixcomResult;
import com.qheeshow.eway.web.base.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by lihuajun on 16-6-14.
 */
@Controller
@RequestMapping("/mixcom")
public class MixcomController extends BaseController {

    @Autowired
    private UserService userService;
    @Autowired
    private MixcomService mixcomService;
    @Autowired
    private InvestorService investorService;

    @RequestMapping("/bound/{userid}/authj")
    @ResponseBody
    public String bound(@PathVariable Integer userid, HttpSession session) {

        Result<String> result = new Result<>();
        result.setCode(-2);
        User user = userService.get(userid);
        if (user == null) {
            result.setMessage("此联系人不存在");
            return result.toString();
        }
        //角色：10超级管理员,11普通管理员,20创业者,30普通投资人,31认证投资人,32签约投资人
        User loginUser = (User) session.getAttribute("loginUser");
        loginUser = userService.get(loginUser.getId());

        if (loginUser.getId().intValue() == userid.intValue()) {
            result.setCode(0);
            result.setData(loginUser.getMobile());
            return result.toString();
        }
        if (loginUser.getRoleid().intValue() == 20) {//企业/创业者
            if (user.getRoleid().intValue() == 20) {//企业/创业者
                result.setMessage("对不起，您是企业/创业者，所以您不能获得企业的联系方式");
                return result.toString();
            }
            if (loginUser.getCallTime().intValue() <= 0) {
                //result.setMessage("您没有购买套餐或您的通话时长已用完，请购买套餐，<a href='../goods/list/0'>立即购买</a>");
                result.setMessage("您没有购买套餐或您的通话时长已用完，请购买套餐");
                result.setCode(-3);
                return result.toString();
            }
            if (loginUser.getCallTime().intValue() <= 2) {
                result.setMessage("您的通话时长小于2分钟，请购买套餐");
                result.setCode(-3);
                return result.toString();
            }
        } else if (loginUser.getRoleid().intValue() >= 30 && loginUser.getRoleid().intValue() < 40) {//投资人
            if (user.getRoleid().intValue() >= 30 && user.getRoleid().intValue() < 40) {//投资人
                result.setMessage("对不起，您是投资人，所以您不能获得投资人的联系方式");
                return result.toString();
            }
            if (loginUser.getRoleid().intValue() == 30) {//未认证
                if (investorService.getByUser(loginUser.getId()) == null) {
                    result.setMessage("亲爱的投资人，请先完善并认证您的信息！");
                    result.setCode(-5);
                }else{
                    result.setMessage("亲爱的投资人，请先认证成为合格投资人才能查看联系方式哦！");
                    result.setCode(-4);
                }

                return result.toString();
            }
        }
        try {
            String mixNo = mixcomService.bound(loginUser.getMobile(), user.getMobile(), loginUser.getCallTime());
            result.setData(mixNo);
        } catch (Exception e) {
            LOGGER.error("error", e);
            result.setMessage("获取失败");
            return result.toString();
        }
        result.setCode(0);
        return result.toString();
    }


    @RequestMapping("/receive")
    @ResponseBody
    public String receive(HttpServletRequest request) {
        MixcomResult result = new MixcomResult();
        CallRecord callRecord = new CallRecord();
        callRecord.setBindId(request.getParameter("BindID"));
        if (StringUtils.isEmpty(callRecord.getBindId())) {
            result.set("200", "成功接收", "成功");
            LOGGER.info("未建立绑定");
            return JSONObject.toJSONString(result);
        }
        callRecord.setDuration(request.getParameter("Duration"));
        if (StringUtils.isEmpty(callRecord.getDuration())) {
            result.set("200", "成功接收", "成功");
            LOGGER.info("通话时长为空");
            return JSONObject.toJSONString(result);
        }
        callRecord.setCalling(request.getParameter("calling"));
        callRecord.setCalled(request.getParameter("called"));
        callRecord.setCallidentifier(request.getParameter("callIdentifier"));

        callRecord.setEvent(request.getParameter("event"));
        callRecord.setReleaseReason(request.getParameter("ReleaseReason"));
        callRecord.setStartTime(request.getParameter("StartTime"));
        callRecord.setTimeStamp(request.getParameter("timeStamp"));
        callRecord.setUniqueId(request.getParameter("UniqueId"));
        callRecord.setVirtualNumber(request.getParameter("virtualNumber"));
        try {
            mixcomService.saveRecord(callRecord);
        } catch (CommonException e) {
            LOGGER.error("保存通话记录失败,code:" + e.getCode() + ",desc:" + e.getDesc());
        }
        result.set("200", "成功接收", "成功");
        return JSONObject.toJSONString(result);
    }
}
