package com.qheeshow.eway.service.service.impl;

import com.qheeshow.eway.service.exception.CommonException;
import com.qheeshow.eway.service.model.ProjectAdviser;
import com.qheeshow.eway.service.model.User;
import com.qheeshow.eway.service.service.ProjectAdviserService;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.qheeshow.eway.service.constant.ExceptionTypeEnum.Project_Adviser_Apply_Exist_ERROR;

/**
 * Created by lihuajun on 17-2-8.
 */
@Service
public class ProjectAdviserServiceImpl implements ProjectAdviserService {

    @Override public void apply(Integer projectid, Integer userid) throws CommonException {
        // TODO: 17-2-8 检查是否已申请过
        if (true) {//如果已申请过,则抛异常
            throw new CommonException(Project_Adviser_Apply_Exist_ERROR);
        }
    }

    @Override public List<User> list(Integer projectid) {
        return null;
    }

    @Override public List<ProjectAdviser> listByStatus(Integer status) {
        return null;
    }

    @Override public ProjectAdviser getByProjectAndUser(Integer projectid, Integer userid) {
        return null;
    }

    @Override public void save(ProjectAdviser projectAdviser) {
        // TODO: 17-2-8 保存和更新
    }

    @Override public Boolean isAbleToBeAdviser(ProjectAdviser projectAdviser) {

        if (null != getByProjectAndUser(projectAdviser.getProjectid(), projectAdviser.getUserid()))
            return false;

        // TODO: 17-2-8 该项目有多少顾问
        List<ProjectAdviser> list = null;

        if (list.size() >= 10)
            return false;

        return true;
    }
}
