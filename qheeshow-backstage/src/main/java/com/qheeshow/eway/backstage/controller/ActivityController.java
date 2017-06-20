package com.qheeshow.eway.backstage.controller;

import com.alibaba.fastjson.JSON;
import com.google.zxing.WriterException;
import com.qheeshow.eway.backstage.base.BaseController;
import com.qheeshow.eway.backstage.base.Result;
import com.qheeshow.eway.backstage.base.ResultDg;
import com.qheeshow.eway.service.model.Activity;
import com.qheeshow.eway.service.model.ActivitySign;
import com.qheeshow.eway.service.model.User;
import com.qheeshow.eway.service.service.ActivityService;
import com.qheeshow.eway.service.service.ActivitySignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by lihuajun on 16-6-14.
 */
@Controller
@RequestMapping("/activity")
public class ActivityController extends BaseController {

    @Autowired
    private ActivityService activityService;
    @Autowired
    private ActivitySignService activitySignService;

    @RequestMapping("/list/{activityClass}")
    @ResponseBody
    public String list(@PathVariable Integer activityClass, Integer page, Integer rows) {

        ResultDg<List<Activity>> resultDg = new ResultDg<>();

        Activity activity = new Activity();
        activity.setActivityClass(activityClass);
        activity.setPageSize(rows);
        activity.setStartRow(rows * (page - 1));
        Map<String, Object> map = activityService.listByClassAndPage(activity);
        resultDg.setTotal((Integer) map.get("count"));
        resultDg.setRows((List<Activity>) map.get("activities"));

        return JSON.toJSONString(resultDg);
    }

    @RequestMapping("/get/{id}")
    @ResponseBody
    public String get(@PathVariable Integer id) {

        Result<Activity> result = new Result();
        Activity activity = activityService.get(id);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        activity.setsBeginTime(sdf.format(activity.getBeginTime()));
        activity.setsEndTime(sdf.format(activity.getEndTime()));
        activity.setsSignEndTime(sdf.format(activity.getSignEndTime()));

        result.setData(activity);
        return result.toString();
    }

    @RequestMapping("/save")
    @ResponseBody
    public String save(Activity activity) throws ParseException, IOException, WriterException {

        Result<Boolean> result = new Result();
        result.setData(false);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        activity.setBeginTime(sdf.parse(activity.getsBeginTime()));
        activity.setEndTime(sdf.parse(activity.getsEndTime()));
        activity.setSignEndTime(sdf.parse(activity.getsSignEndTime()));
        activityService.save(activity);

        result.setData(true);
        return result.toString();
    }

    @RequestMapping("/status/update/{activityid}/{status}")
    @ResponseBody
    public String updateStatus(@PathVariable Integer activityid, @PathVariable Integer status) throws ParseException, IOException, WriterException {

        Result<Boolean> result = new Result();
        result.setData(false);

        Activity activity = new Activity();
        activity.setId(activityid);
        activity.setDocStatus(status);
        activityService.save(activity);

        result.setData(true);
        return result.toString();
    }

    @RequestMapping("/head/{activityid}/{ishead}")
    @ResponseBody
    public String head(@PathVariable Integer activityid, @PathVariable Integer ishead) throws ParseException, IOException, WriterException {

        Result<Boolean> result = new Result();
        result.setData(false);

        Activity activity = new Activity();
        activity.setId(activityid);
        activity.setIsHead(ishead);
        activityService.save(activity);

        result.setData(true);
        return result.toString();
    }

    @RequestMapping("/sign/list/{activityid}")
    @ResponseBody
    public String listSign(@PathVariable Integer activityid, Integer page, Integer rows) {

        ResultDg<List<User>> resultDg = new ResultDg<>();

        ActivitySign activitySign = new ActivitySign();
        activitySign.setPageSize(rows);
        activitySign.setStartRow(rows * (page - 1));
        activitySign.setActivityId(activityid);
        Map<String, Object> map = activitySignService.listByActivityAndPage(activitySign);
        resultDg.setTotal((Integer) map.get("count"));

        List<User> userList = (List<User>) map.get("users");
        if (userList == null || userList.size() == 0) {
            resultDg.setRows(new ArrayList<>());
            return JSON.toJSONString(resultDg);
        }
        for (User user : userList) {
            if (StringUtils.isEmpty(user.getName())) {
                if (!StringUtils.isEmpty(user.getNickname())) {
                    try {
                        user.setName(URLDecoder.decode(user.getNickname(), "utf-8"));
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                } else
                    user.setName("");
            }
            if (StringUtils.isEmpty(user.getMobile())) {
                if (!StringUtils.isEmpty(user.getNickname())) {
                    try {
                        user.setMobile("微信昵称：" + URLDecoder.decode(user.getNickname(), "utf-8"));
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                } else {
                    user.setMobile("");
                }
            }
        }

        resultDg.setRows((List<User>) map.get("users"));

        return JSON.toJSONString(resultDg);
    }


}
