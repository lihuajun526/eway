package com.qheeshow.eway.service.service;

import com.qheeshow.eway.service.exception.UserExistException;
import com.qheeshow.eway.service.model.User;

/**
 * Created by lihuajun on 17-1-17.
 */
public interface UserService {

    void regist(User user) throws UserExistException;

}
