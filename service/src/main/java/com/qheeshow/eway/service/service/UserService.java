package com.qheeshow.eway.service.service;

import java.util.List;

import com.qheeshow.eway.common.exception.CryptoException;
import com.qheeshow.eway.service.model.User;

/**
 * Created by lihuajun on 17-1-17.
 */
public interface UserService {
	
	public boolean isRegist(User user);

	public void regist(User user);
	
	public void changePassword(User user);
    
    public List<User> login(User user) throws CryptoException;
    
    public void update(User user);

	User get(Integer id);

}
