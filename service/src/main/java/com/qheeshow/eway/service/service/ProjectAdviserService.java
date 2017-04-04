package com.qheeshow.eway.service.service;

import com.qheeshow.eway.common.exception.CommonException;
import com.qheeshow.eway.service.model.Investor;
import com.qheeshow.eway.service.model.ProjectAdviser;

import java.util.List;
import java.util.Map;

/**
 * Created by lihuajun on 17-2-8.
 */
public interface ProjectAdviserService {

    void apply(Integer projectid, Integer userid) throws CommonException;

    List<Investor> list(Integer projectid);

    List<ProjectAdviser> listByStatus(Integer status);

    Map<String, Object> listByStatusAndPage(ProjectAdviser projectAdviser);

    ProjectAdviser getByProjectAndUser(Integer projectid, Integer userid);

    void save(ProjectAdviser projectAdviser);

    Boolean isAbleToBeAdviser(ProjectAdviser projectAdviser);

    Boolean isAdviser(ProjectAdviser projectAdviser);

    void update(ProjectAdviser projectAdviser);

}
