package com.qheeshow.eway.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.qheeshow.eway.common.constant.ExceptionTypeEnum;
import com.qheeshow.eway.common.exception.CommonException;
import com.qheeshow.eway.common.exception.CryptoException;
import com.qheeshow.eway.common.http.XHttpClient;
import com.qheeshow.eway.common.util.AESCryptoUtil;
import com.qheeshow.eway.common.util.Config;
import com.qheeshow.eway.common.web.HaResponse;
import com.qheeshow.eway.service.model.User;
import com.qheeshow.eway.service.service.UserService;
import com.qheeshow.eway.web.base.BaseController;
import com.qheeshow.eway.web.base.Result;
import org.apache.http.client.methods.HttpGet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by lihuajun on 16-6-14.
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/regist", method = RequestMethod.POST)
    @ResponseBody
    public String regist(User user, String smsCode, String rePassword, HttpServletRequest request) {
        Result<Boolean> result = new Result<>();
        result.set("注册成功", true);

        if (StringUtils.isEmpty(user.getName())) {
            result.set("真实姓名不能为空", false);
            return result.toString();
        }
        if (StringUtils.isEmpty(user.getMobile())) {
            result.set("手机号不能为空", false);
            return result.toString();
        }
        if (StringUtils.isEmpty(user.getEmail())) {
            result.set("邮箱不能为空", false);
            return result.toString();
        }
        if (StringUtils.isEmpty(smsCode)) {
            result.set("短信验证码不能为空", false);
            return result.toString();
        }
        if (StringUtils.isEmpty(user.getPassword())) {
            result.set("密码不能为空", false);
            return result.toString();
        }
        if (user.getRoleid() == null) {
            result.set("身份不能为空", false);
            return result.toString();
        }
        if (!user.getPassword().equals(rePassword)) {
            result.set("两次密码输入不一致", false);
            return result.toString();
        }
        HttpSession httpSession = request.getSession();
        if (httpSession.getAttribute(user.getMobile() + "_regist_smsCode") == null) {
            result.set("短信验证码错误", false);
            return result.toString();
        }
        String sSmsCode = (String) httpSession.getAttribute(user.getMobile() + "_" + "regist_smsCode");
        if (sSmsCode == null || !sSmsCode.equals(smsCode)) {
            result.set("短信验证码错误", false);
            return result.toString();
        }
        if (userService.isRegist(user)) {
            result.set("该手机号码已被注册", false);
            return result.toString();
        }
        userService.regist(user);
        LOGGER.debug("用户ID={}", user.getId());
        httpSession.setAttribute("loginUser", user);
        return result.toString();
    }


    /**
     * @param session
     * @param user
     * @return
     * @Title: SaveAddress
     * @Description: 登录并将当前用户相关信息放入缓存
     * @author yue
     * @date 2017年2月25日 下午2:14:12
     */
    @RequestMapping(value = "/login")
    @ResponseBody
    public HaResponse login(HttpSession session, User user) throws CryptoException {
        List<User> users = userService.login(user);
        if (users.size() > 0) {
            user = users.get(0);
            session.setAttribute("loginUser", user);
            user.setPassword("");
            return HaResponse.sussess(user);
        } else {
            return HaResponse.fail("用户名密码错误");
        }
    }

    /**
     * 微信统一登录
     *
     * @param code
     * @param state
     * @return
     * @throws CryptoException
     */
    @RequestMapping(value = "/login/wechat")
    public String loginFromWechat(String code, String state, HttpSession session) throws CryptoException, CommonException {

        if (StringUtils.isEmpty(code)) {
            return "redirect:/user/login.jsp";
        }
        //获取用户的微信unionid
        String unionid = null;
        JSONObject jsonObject = null;
        try {
            /*{
                "access_token":"ACCESS_TOKEN",
                    "expires_in":7200,
                    "refresh_token":"REFRESH_TOKEN",
                    "openid":"OPENID",
                    "scope":"SCOPE",
                    "unionid": "o6_bmasdasdsad6_2sgVt7hMZOPfL"
            }*/
            String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + Config.get("wechat.open.app.id") + "&secret=" + Config.get("wechat.open.app.secret") + "&code=" + code + "&grant_type=authorization_code";
            HttpGet httpGet = new HttpGet(url);
            String response = XHttpClient.doRequest(httpGet);
            jsonObject = JSONObject.parseObject(response);
            unionid = jsonObject.getString("unionid");
            if (!StringUtils.isEmpty(jsonObject.getString("errcode")) || StringUtils.isEmpty(unionid)) {
                LOGGER.error("errcode={}", jsonObject.getString("errcode"));
                throw new CommonException(ExceptionTypeEnum.Get_Unionid_ERROR);
            }
        } catch (Exception e) {
            LOGGER.error("获取unionid失败", e);
            throw new CommonException(ExceptionTypeEnum.Get_Unionid_ERROR);
        }
        User user = userService.getByUnionid(unionid);
        if (user != null && !StringUtils.isEmpty(user.getMobile())) {//已经绑定手机号
            session.setAttribute("loginUser", user);
            return "redirect:/index";
        }

        if (user == null) {//保存用户的微信基本信息
            try {
                String accessToken = jsonObject.getString("access_token");
                String openid = jsonObject.getString("openid");
                String url = "https://api.weixin.qq.com/sns/userinfo?access_token=" + accessToken + "&openid=" + openid;
                HttpGet httpGet = new HttpGet(url);
                String response = XHttpClient.doRequest(httpGet);
                jsonObject = JSONObject.parseObject(response);
                if (!StringUtils.isEmpty(jsonObject.getString("errcode"))) {
                    LOGGER.error("errcode={}", jsonObject.getString("errcode"));
                    throw new CommonException(ExceptionTypeEnum.Get_User_Info_ERROR);
                }
                user = new User();
                user.setOpenid(jsonObject.getString("openid"));
                user.setNickname(jsonObject.getString("nickname"));
                user.setSex(jsonObject.getInteger("sex"));
                user.setProvince(jsonObject.getString("province"));
                user.setCity(jsonObject.getString("city"));
                user.setCountry(jsonObject.getString("country"));
                user.setHeadimgurl(jsonObject.getString("headimgurl"));
                user.setPrivilege(jsonObject.getString("privilege"));
                user.setUnionid(jsonObject.getString("unionid"));
                userService.saveFromWechat(user);
            } catch (Exception e) {
                LOGGER.error("获取微信用户基本信息失败", e);
            }
        }

        session.setAttribute("unionid", unionid);
        return "redirect:/user/mobile_bind.jsp";
    }

    @RequestMapping(value = "/mobile/bind")
    @ResponseBody
    public String bindMobile(User user, String smsCode, HttpSession session) {

        Result<Boolean> result = new Result<>();
        result.setData(false);

        if (StringUtils.isEmpty(user.getName())) {
            result.set("真实姓名不能为空");
            return result.toString();
        }
        if (StringUtils.isEmpty(user.getMobile())) {
            result.set("手机号不能为空");
            return result.toString();
        }
        if (StringUtils.isEmpty(user.getEmail())) {
            result.set("邮箱不能为空");
            return result.toString();
        }
        if (StringUtils.isEmpty(smsCode)) {
            result.set("短信验证码不能为空");
            return result.toString();
        }
        if (user.getRoleid() == null) {
            result.set("身份不能为空");
            return result.toString();
        }

        if (StringUtils.isEmpty(user.getUnionid())) {
            result.setMessage("unionid为空，无法绑定");
            return result.toString();
        }

        User wechatUser = userService.getByUnionid(user.getUnionid());
        if (wechatUser == null) {
            result.setMessage("unionid不存在，无法绑定");
            return result.toString();
        }

        if (session.getAttribute(user.getMobile() + "_bind_smsCode") == null) {
            result.set("短信验证码错误");
            return result.toString();
        }
        String sSmsCode = (String) session.getAttribute(user.getMobile() + "_bind_smsCode");
        if (sSmsCode == null || !sSmsCode.equals(smsCode)) {
            result.set("短信验证码错误");
            return result.toString();
        }

        User dbUser = userService.getByMobile(user.getMobile());
        if (dbUser == null) {
            wechatUser.setMobile(user.getMobile());
            wechatUser.setEmail(user.getEmail());
            wechatUser.setRoleid(user.getRoleid());
            wechatUser.setStatus(1);
            userService.update(wechatUser);
            session.setAttribute("loginUser", wechatUser);
        } else {//合并用户
            session.setAttribute("loginUser", userService.merge(dbUser, wechatUser));
            result.setCode(1);
        }

        result.setData(true);
        return result.toString();
    }

    @RequestMapping(value = "/logout")
    public ModelAndView logout(HttpSession session) {

        session.removeAttribute("loginUser");

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/index");

        return modelAndView;
    }

    /**
     * @param session
     * @param user
     * @param smsCode
     * @return
     * @Title: changePassword
     * @Description: 修改密码
     * @author yue
     * @date 2017年2月25日 下午3:48:11
     */
    @RequestMapping(value = "/changePassword", method = RequestMethod.GET)
    @ResponseBody
    public HaResponse changePassword(HttpSession session, User user, String smsCode) {
        if (smsCode.equals((String) session.getAttribute("regist" + "smsCode"))) {
            userService.changePassword(user);
            return HaResponse.sussess();
        } else {
            return HaResponse.fail("短信验证码错误");
        }
    }

    @RequestMapping(value = "/password/reset")
    @ResponseBody
    public String resetPassword(HttpSession session, User user, String smsCode, String rePassword) {
        Result<Boolean> result = new Result<>();
        result.setData(false);
        User userDB = userService.getByMobile(user.getMobile());
        if (userDB == null) {
            result.setMessage("该手机号码不存在，请先注册");
            return result.toString();
        }
        Object object = session.getAttribute(user.getMobile() + "_resetPwd_smsCode");
        if (StringUtils.isEmpty(user.getMobile())) {
            result.setMessage("手机号不能为空");
            return result.toString();
        }
        if (StringUtils.isEmpty(smsCode)) {
            result.setMessage("短信验证码不能为空");
            return result.toString();
        }
        if (StringUtils.isEmpty(user.getPassword())) {
            result.setMessage("新密码不能为空");
            return result.toString();
        }
        if (!user.getPassword().equals(rePassword)) {
            result.setMessage("两次密码不匹配");
            return result.toString();
        }
        if (object == null) {
            result.setMessage("请先获取短信验证码");
            return result.toString();
        }
        if (!smsCode.equals((String) object)) {
            result.setMessage("短信验证码错误");
            return result.toString();
        }
        userService.changePassword(user);
        result.setData(true);
        return result.toString();
    }

    /**
     * @param session
     * @param user
     * @return
     * @Title: update
     * @Description: 更新当前用户个人信息
     * @author yue
     * @date 2017年3月19日 下午2:58:07
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public HaResponse update(HttpSession session, User user) {
        User record = (User) session.getAttribute("loginUser");
        user.setId(record.getId());
        userService.update(user);
        return HaResponse.sussess();
    }

    /**
     * @return
     * @Title: reLogin
     * @Description: 未登录时，请求需登录才可访问的请求时统一转向该接口返回
     * @author yue
     * @date 2017年3月5日11:35:34
     */
    @RequestMapping(value = "/reLogin")
    @ResponseBody
    public String reLogin() {
        Result result = new Result();
        result.set(-1, "对不起，请先登录");
        return result.toString();
    }


    @RequestMapping(value = "/photo/upload/authj")
    @ResponseBody
    public String uploadPhoto(String path, HttpSession session) {

        Result result = new Result();

        User loginUser = (User) session.getAttribute("loginUser");
        loginUser.setPhoto(path);

        User user = new User();
        user.setId(loginUser.getId());
        user.setPhoto(path);

        userService.update(user);
        return result.toString();
    }

    @RequestMapping(value = "/name/modify/authj")
    @ResponseBody
    public String modifyName(String name, HttpSession session) {

        Result result = new Result();

        User loginUser = (User) session.getAttribute("loginUser");

        User user = new User();
        user.setName(name);
        user.setId(loginUser.getId());

        loginUser.setName(name);

        userService.update(user);
        return result.toString();
    }

    @RequestMapping(value = "/email/modify/authj")
    @ResponseBody
    public String modifyEmail(String email, HttpSession session) {

        Result result = new Result();

        User loginUser = (User) session.getAttribute("loginUser");

        User user = new User();
        user.setEmail(email);
        user.setId(loginUser.getId());

        loginUser.setEmail(email);

        userService.update(user);
        return result.toString();
    }

    @RequestMapping(value = "/password/modify/authj")
    @ResponseBody
    public String modifyPwd(String password, HttpSession session) throws CryptoException {

        Result result = new Result();

        User loginUser = (User) session.getAttribute("loginUser");

        User user = new User();
        user.setPassword(AESCryptoUtil.encrypt(password));
        user.setId(loginUser.getId());

        userService.update(user);
        return result.toString();
    }

}
