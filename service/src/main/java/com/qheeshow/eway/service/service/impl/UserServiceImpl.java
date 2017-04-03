package com.qheeshow.eway.service.service.impl;

import com.qheeshow.eway.common.exception.CryptoException;
import com.qheeshow.eway.common.util.AESCryptoUtil;
import com.qheeshow.eway.common.util.MD5Util;
import com.qheeshow.eway.common.util.StrUtil;
import com.qheeshow.eway.service.dao.UserMapper;
import com.qheeshow.eway.service.model.User;
import com.qheeshow.eway.service.model.UserExample;
import com.qheeshow.eway.service.service.MailService;
import com.qheeshow.eway.service.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lihuajun on 17-1-22.
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    UserMapper userMapper;
    @Autowired
    MailService mailService;

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
    public void changePassword(User user) {
        UserExample example = new UserExample();
        example.createCriteria().andMobileEqualTo(user.getMobile());
        user.setPassword(MD5Util.MD5(user.getPassword()));
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
}
