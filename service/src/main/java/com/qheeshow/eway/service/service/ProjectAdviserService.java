package com.qheeshow.eway.service.service;

import com.qheeshow.eway.service.exception.CommonException;
import com.qheeshow.eway.service.model.Investor;
import com.qheeshow.eway.service.model.ProjectAdviser;
import com.qheeshow.eway.service.model.User;

import java.util.List;

/**
 * Created by lihuajun on 17-2-8.
 */
public interface ProjectAdviserService {

    void apply(Integer projectid, Integer userid) throws CommonException;

    List<Investor> list(Integer projectid);

    List<ProjectAdviser> listByStatus(Integer status);

    ProjectAdviser getByProjectAndUser(Integer projectid,Integer userid);

    void save(ProjectAdviser projectAdviser);

    Boolean isAbleToBeAdviser(ProjectAdviser projectAdviser);

    Boolean isAdviser(ProjectAdviser projectAdviser);

}
