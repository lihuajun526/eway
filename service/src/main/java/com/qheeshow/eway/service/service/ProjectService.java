package com.qheeshow.eway.service.service;

import com.qheeshow.eway.service.model.Project;
import com.qheeshow.eway.service.model.ProjectAdviser;
import com.qheeshow.eway.service.model.ProjectFollow;
import com.qheeshow.eway.service.model.ProjectSuggest;

import java.util.List;
import java.util.Map;

/**
 * Created by lihuajun on 17-1-17.
 */
public interface ProjectService {

    void save(Project project);

    List<Project> listByStatus(Integer status);

    Map<String,Object> listByStatusAndPage(Project project);

    Project get(Integer id);

    Map<String, Object> listByCondition(Integer type, Integer areaid,
                                        Integer financingLimit, Integer industry, Integer pageIndex, Integer pageSize,String keyword);

    List<Project> search(String keyword);

    List<Project> listByUser(Integer userid);

    Map<String, Object> listSuggest(ProjectSuggest projectSuggest);

    Map<String, Object> listFollow(ProjectFollow projectFollow);

    Map<String, Object> listAdviser(ProjectAdviser projectAdviser);

    void recommendOrNot(Integer status, Integer projectid);

    Map<String,Object> listPayProject(Project project);

    List<Project> bestSuggest(Integer num);

    List<Project> bestCase(Integer num);

    void delCase(Integer projectid);

    void addCase(Integer projectid);

}
