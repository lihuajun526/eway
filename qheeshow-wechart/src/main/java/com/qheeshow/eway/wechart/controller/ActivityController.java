package com.qheeshow.eway.wechart.controller;

import com.qheeshow.eway.common.bean.wechat.pay.ResultOrder;
import com.qheeshow.eway.common.bean.wechat.pay.exception.OrderWechatException;
import com.qheeshow.eway.common.util.StrUtil;
import com.qheeshow.eway.service.model.Activity;
import com.qheeshow.eway.service.model.ActivitySign;
import com.qheeshow.eway.service.model.User;
import com.qheeshow.eway.service.service.ActivityService;
import com.qheeshow.eway.service.service.ActivitySignService;
import com.qheeshow.eway.wechart.base.BaseController;
import com.qheeshow.eway.wechart.base.Result;
import com.qheeshow.eway.wechart.base.Tip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by lihuajun on 16-7-6.
 */
@Controller
@RequestMapping("/activity/do")
public class ActivityController extends BaseController {

    @Autowired
    private ActivityService activityService;
    @Autowired
    private ActivitySignService activitySignService;

    @RequestMapping("/list/{pageIndex}")
    @ResponseBody
    public String listByPage(@PathVariable Integer pageIndex) {

        Integer pageSize = 5;

        Result<List<Activity>> result = new Result<>();

        Activity activity = new Activity();
        activity.setStartRow((pageIndex - 1) * pageSize);
        activity.setPageSize(pageSize);
        List<Activity> list = activityService.listByPage(activity);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        for (Activity activity1 : list) {
            activity1.setsBeginTime(sdf.format(activity1.getBeginTime()));
            activity1.setStyle(activity1.getSignEndTime().getTime() > System.currentTimeMillis() ? "on1" : "on2");
        }

        result.setData(list);

        return result.toString();
    }

    @RequestMapping("/get/v_login/{id}")
    public ModelAndView get(@PathVariable Integer id, HttpSession session) {

        User loginUser = (User)session.getAttribute("loginUser");

        Activity activity = activityService.get(id);
        activity.setStyle("on2");
        String content = activity.getContent();
        if (!StringUtils.isEmpty(content)) {
            content = content.replaceAll("<img", "<img width='100%'");
            activity.setContent(content);
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("activity/activity_detail");
        modelAndView.addObject("activity", activity);

        if (activity.getSignEndTime().getTime() <= System.currentTimeMillis()) {
            activity.setTip("已结束");
            return modelAndView;
        }

        ActivitySign activitySign = new ActivitySign();
        activitySign.setActivityId(id);
        activitySign.setUserid(loginUser.getId());
        if(activitySignService.issign(activitySign)){
            activity.setTip("已报名");
            return modelAndView;
        }

        int signed = activitySignService.countSign(id);
        if (signed >= activity.getLimitNum()) {
            activity.setTip("已爆满");
            return modelAndView;
        }

        activity.setStyle("on1");
        activity.setTip("立即报名");
        return modelAndView;
    }

    /**
     * 付费报名
     *
     * @param payType
     * @param activityid
     * @param session
     * @return
     */
    @RequestMapping("/preorder/{payType}/{activityid}/v_authj")
    @ResponseBody
    public String preOrder(@PathVariable String payType, @PathVariable Integer activityid, HttpSession session) {

        Result<Tip<ResultOrder>> result = new Result<>();
        Tip<ResultOrder> tip = new Tip<>();
        result.setData(tip);

        User loginUser = (User) session.getAttribute("loginUser");

        ActivitySign activitySign = new ActivitySign();
        activitySign.setUserid(loginUser.getId());
        activitySign.setActivityId(activityid);

        if (activitySignService.issign(activitySign)) {
            result.setMessage("您已报名，谢谢");
            return result.toString();
        }

        ResultOrder resultOrder = null;

        try {
            resultOrder = activitySignService.preOrder(activitySign, payType, loginUser.getGzhOpenid());
            resultOrder.setTimeStamp(String.valueOf(System.currentTimeMillis()));
            //签名
            Map<String, String> params = new TreeMap<>();
            params.put("appId", resultOrder.getAppid());
            params.put("timeStamp", resultOrder.getTimeStamp());
            params.put("nonceStr", resultOrder.getNonce_str());
            params.put("package", "prepay_id=" + resultOrder.getPrepay_id());
            params.put("signType", "MD5");
            resultOrder.setSign(StrUtil.sign(params));
            tip.setData(resultOrder);
        } catch (OrderWechatException e) {
            LOGGER.error("error", e);
            result.setCode(-1);
            Activity activity = activityService.get(activityid);
            if (StringUtils.isEmpty(activity.getTel())) {
                result.setMessage("对不起，报名失败，请联系主办方");
            } else {
                result.setMessage("对不起，报名失败，请联系主办方，电话：<a href='tel:" + activity.getTel() + "'>" + activity.getTel() + "</a>");
            }
        }

        return result.toString();
    }

    /**
     * 免费报名
     *
     * @param activityid
     * @param session
     * @return
     */
    @RequestMapping("/sign/{activityid}/v_authj")
    @ResponseBody
    public String sign(@PathVariable Integer activityid, HttpSession session) {

        Result<Tip<ResultOrder>> result = new Result<>();
        Tip<ResultOrder> tip = new Tip<>();
        result.setData(tip);

        User loginUser = (User) session.getAttribute("loginUser");

        ActivitySign activitySign = new ActivitySign();
        activitySign.setActivityId(activityid);
        activitySign.setUserid(loginUser.getId());
        if (activitySignService.issign(activitySign)) {
            result.setMessage("您已报名，谢谢");
            return result.toString();
        }

        activitySign.setStatus(1);
        activitySignService.save(activitySign);
        result.setMessage("恭喜您，报名成功");
        return result.toString();
    }

}
