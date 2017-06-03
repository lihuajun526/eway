package com.qheeshow.eway.wechart.controller;

import com.qheeshow.eway.common.util.StrUtil;
import com.qheeshow.eway.service.model.User;
import com.qheeshow.eway.service.service.MixcomService;
import com.qheeshow.eway.service.service.UserService;
import com.qheeshow.eway.wechart.base.BaseController;
import com.qheeshow.eway.wechart.base.Result;
import com.qheeshow.eway.wechart.base.Tip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by lihuajun on 16-6-14.
 */
@Controller
@RequestMapping("/mixcom/do")
public class MixcomController extends BaseController {

    @Autowired
    private UserService userService;
    @Autowired
    private MixcomService mixcomService;

    @RequestMapping("/bound/{userid}/v_authj")
    @ResponseBody
    public String bound(@PathVariable Integer userid, HttpSession session) {

        Result<Tip> result = new Result<>();
        Tip tip = new Tip();
        result.setData(tip);

        User user = userService.get(userid);
        if (user == null) {
            result.setMessage("此联系人不存在");
            tip.setAction("关闭");
            return result.toString();
        }
        //角色：10超级管理员,11普通管理员,20创业者,30普通投资人,31认证投资人,32签约投资人
        User loginUser = (User) session.getAttribute("loginUser");
        loginUser = userService.get(loginUser.getId());

        if (loginUser.getId().intValue() == userid.intValue()) {
            result.setMessage("联系电话：" + StrUtil.handleAdd86(loginUser.getMobile()));
            return result.toString();
        }
        if (loginUser.getRoleid().intValue() == 20) {//企业/创业者
            if (user.getRoleid().intValue() == 20) {//企业/创业者
                result.setMessage("您不能获得企业/创业者的联系方式");
                return result.toString();
            }
            if (loginUser.getCallTime().intValue() <= 0) {
                result.setMessage("您没有购买套餐或您的通话时长已用完，请购买套餐");
                tip.setLink("/goods/goods_list");
                tip.setAction("立即购买");
                return result.toString();
            }
            if (loginUser.getCallTime().intValue() <= 2) {
                result.setMessage("您的通话时长小于2分钟，请购买套餐");
                tip.setLink("/goods/goods_list");
                tip.setAction("立即购买");
                return result.toString();
            }
        } else if (loginUser.getRoleid().intValue() >= 30 && loginUser.getRoleid().intValue() < 40) {//投资人
            if (user.getRoleid().intValue() >= 30 && user.getRoleid().intValue() < 40) {//投资人
                result.setMessage("您不能获得投资人的联系方式");
                return result.toString();
            }
            if (loginUser.getRoleid().intValue() == 30) {//未认证
                result.setMessage("请先认证投资人身份");
                tip.setLink("/investor/investor_auth");
                tip.setAction("去认证");
                return result.toString();
            }
        }
        try {
            String mixNo = mixcomService.bound(loginUser.getMobile(), user.getMobile(), loginUser.getCallTime());
            result.setMessage("联系电话：" + mixNo);
            tip.setLink("tel:" + mixNo);
            tip.setAction("呼叫");
        } catch (Exception e) {
            LOGGER.error("error", e);
            result.setMessage("获取失败");
            tip.setAction("关闭");
            return result.toString();
        }
        return result.toString();
    }
}
