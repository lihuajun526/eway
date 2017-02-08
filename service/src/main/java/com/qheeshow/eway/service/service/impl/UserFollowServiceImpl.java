package com.qheeshow.eway.service.service.impl;

import com.qheeshow.eway.service.model.User;
import com.qheeshow.eway.service.service.UserFollowService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lihuajun on 17-2-8.
 */
@Service
public class UserFollowServiceImpl implements UserFollowService {

    @Override public void follow(Integer userid,Integer followid) {

    }

    @Override public void unFollow(Integer userid,Integer followid) {

    }

    @Override public boolean isFollow(Integer userid,Integer followid) {
        return false;
    }

    @Override public List<User> list(Integer userid) {
        return null;
    }
}
