package com.qheeshow.eway.service.service.impl;

import com.qheeshow.eway.common.exception.CryptoException;
import com.qheeshow.eway.common.util.AESCryptoUtil;
import com.qheeshow.eway.common.util.StrUtil;
import com.qheeshow.eway.service.dao.ProjectMapper;
import com.qheeshow.eway.service.dao.UserMapper;
import com.qheeshow.eway.service.model.Project;
import com.qheeshow.eway.service.model.User;
import com.qheeshow.eway.service.model.UserExample;
import com.qheeshow.eway.service.service.MailService;
import com.qheeshow.eway.service.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

/**
 * Created by lihuajun on 17-1-22.
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MailService mailService;
    @Autowired
    private ProjectMapper projectMapper;

    @Override
    public boolean isRegist(User user) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andMobileEqualTo(user.getMobile());
        if (user.getOpenid() != null) {
            //判断该手机号是否已经绑定微信账号
            criteria.andOpenidIsNotNull();
        }
        List<User> users = userMapper.selectByExample(example);
        if (users.size() > 0) {
            return true;
        }
        return false;
    }

    @Override
    public void regist(User user) {
        user.setStatus(1);
        user.setCallTime(0);
        try {
            user.setPassword(AESCryptoUtil.encrypt(user.getPassword()));
        } catch (CryptoException e) {
            LOGGER.error("密码[{}]加密失败", user.getPassword());
        }
        userMapper.insert(user);
    }

    @Override
    public void saveFromWechat(User user) {
        user.setStatus(4);
        user.setCallTime(0);
        if (!StringUtils.isEmpty(user.getNickname())) {
            try {
                user.setNickname(URLEncoder.encode(user.getNickname(), "utf-8"));
            } catch (UnsupportedEncodingException e) {
                LOGGER.error("转nickname失败");
            }
        }
        userMapper.insert(user);
    }

    @Override
    public void saveFromGzh(User user) {
        user.setStatus(5);
        user.setCallTime(0);
        if (!StringUtils.isEmpty(user.getNickname())) {
            try {
                user.setNickname(URLEncoder.encode(user.getNickname(), "utf-8"));
            } catch (UnsupportedEncodingException e) {
                LOGGER.error("转nickname失败");
            }
        }
        userMapper.insert(user);
    }

    @Override
    public void importUserAndPro(Project project, User user) {

        userMapper.insert(user);
        project.setUserid(user.getId());
        projectMapper.insert(project);
    }

    @Override
    public void changePassword(User user) {
        UserExample example = new UserExample();
        example.createCriteria().andMobileEqualTo(user.getMobile());
        try {
            user.setPassword(AESCryptoUtil.encrypt(user.getPassword()));
        } catch (CryptoException e) {
            LOGGER.error("error:", e);
        }
        userMapper.updateByExampleSelective(user, example);
    }

    @Override
    public List<User> login(User user) throws CryptoException {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andMobileEqualTo(user.getMobile());
        criteria.andPasswordEqualTo(AESCryptoUtil.encrypt(user.getPassword()));
        List<User> users = userMapper.selectByExample(example);
        return users;
    }

    @Override
    public void update(User user) {
        userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackForClassName = "Exception")
    public User merge(User user1, User user2) {
        user1.setOpenid(user2.getOpenid());
        user1.setNickname(user2.getNickname());
        user1.setSex(user2.getSex());
        user1.setProvince(user2.getProvince());
        user1.setCity(user2.getCity());
        user1.setCountry(user2.getCountry());
        user1.setHeadimgurl(user2.getHeadimgurl());
        user1.setPrivilege(user2.getPrivilege());
        user1.setUnionid(user2.getUnionid());
        user1.setGzhOpenid(user2.getGzhOpenid());
        user1.setSubscribe(user2.getSubscribe());
        userMapper.updateByPrimaryKeySelective(user1);
        userMapper.deleteByPrimaryKey(user2.getId());
        return user1;
    }

    @Override
    public User get(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public User getByMobile(String mobile) {
        UserExample example = new UserExample();
        example.createCriteria().andMobileEqualTo(StrUtil.handleDel86(mobile));
        List<User> list = userMapper.selectByExample(example);
        if (list.size() == 0)
            return null;
        return list.get(0);
    }

    @Override
    public User getByOpenid(String openid) {
        UserExample example = new UserExample();
        example.createCriteria().andOpenidEqualTo(openid);
        List<User> list = userMapper.selectByExample(example);
        if (list.size() == 0)
            return null;
        return list.get(0);
    }

    @Override
    public User getByGzhOpenid(String openid) {
        UserExample example = new UserExample();
        example.createCriteria().andGzhOpenidEqualTo(openid);
        List<User> list = userMapper.selectByExample(example);
        if (list.size() == 0)
            return null;
        return list.get(0);
    }

    @Override
    public User getByUnionid(String unionid) {
        UserExample example = new UserExample();
        example.createCriteria().andUnionidEqualTo(unionid);
        List<User> list = userMapper.selectByExample(example);
        if (list.size() == 0)
            return null;
        return list.get(0);
    }
}
