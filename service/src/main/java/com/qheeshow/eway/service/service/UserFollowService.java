package com.qheeshow.eway.service.service;

import com.qheeshow.eway.service.model.User;

import java.util.List;

/**
 * Created by lihuajun on 17-2-8.
 */
public interface UserFollowService {

    void follow(Integer userid,Integer followid);

    void unFollow(Integer userid,Integer followid);

    boolean isFollow(Integer userid,Integer followid);

    List<User> list(Integer userid);

}
