package com.qheeshow.eway.service.service.impl;

import java.util.List;

import com.qheeshow.eway.common.exception.CryptoException;
import com.qheeshow.eway.common.util.AESCryptoUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qheeshow.eway.common.util.MD5Util;
import com.qheeshow.eway.service.dao.UserMapper;
import com.qheeshow.eway.service.model.MailBean;
import com.qheeshow.eway.service.model.User;
import com.qheeshow.eway.service.model.UserExample;
import com.qheeshow.eway.service.service.MailService;
import com.qheeshow.eway.service.service.UserService;

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
        example.or().andMobileEqualTo(user.getMobile());
        List<User> users = userMapper.selectByExample(example);
        if (users.size() > 0) {
            return true;
        }
        return false;
    }

    @Override
    public void regist(User user) {
        user.setStatus(1);
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
    public List<User> login(User user) {

//		MailBean mailInfo = new MailBean();
//		mailInfo.setMailServerHost("smtp.exmail.qq.com");
//		mailInfo.setUserName("service@qheefund.com");
//		mailInfo.setPassword("wutongE123456");
//		mailInfo.setFromAddress("service@qheefund.com");
//		mailInfo.setToAddress("1065443674@qq.com");
//		mailInfo.setSubject("你好");
//		mailInfo.setContent("大家好");
//		
//		String[] files = {"D:\\work_fykj\\IMG_20170216_163938.jpg","D:\\work_fykj\\IMG_20170216_163938中文.jpg"};
//		mailInfo.setAttachFileNames(files);
//		
//		mailService.sendHtmlMail(mailInfo);

        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andMobileEqualTo(user.getMobile());
        criteria.andPasswordEqualTo(MD5Util.MD5(user.getPassword()));
        List<User> users = userMapper.selectByExample(example);
        return users;
    }

}
