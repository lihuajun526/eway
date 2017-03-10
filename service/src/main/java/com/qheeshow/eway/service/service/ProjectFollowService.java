package com.qheeshow.eway.service.service;

import com.qheeshow.eway.service.exception.CommonException;
import com.qheeshow.eway.service.model.Investor;
import com.qheeshow.eway.service.model.ProjectFollow;
import com.qheeshow.eway.service.model.User;

import java.util.List;

/**
 * Created by lihuajun on 17-2-8.
 */
public interface ProjectFollowService {

    void follow(ProjectFollow projectFollow) throws CommonException;

    void unFollow(ProjectFollow projectFollow);

    boolean isFollow(ProjectFollow projectFollow);

    List<Investor> list(Integer projectid);

}
