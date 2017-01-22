package com.qheeshow.eway.service.service.impl;

import com.qheeshow.eway.service.exception.UserExistException;
import com.qheeshow.eway.service.model.User;
import com.qheeshow.eway.service.service.UserService;
import org.springframework.stereotype.Service;

/**
 * Created by lihuajun on 17-1-22.
 */
@Service("userService")
public class UserServiceImpl implements UserService {


    @Override public void regist(User user) throws UserExistException {

        // TODO: 17-1-22 验证用户是否已注册 
        // TODO: 17-1-22 密码加密
        //throw new UserExistException();


    }
}
