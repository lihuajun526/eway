package com.qheeshow.eway.service.service.impl;

import com.qheeshow.eway.service.dao.ProjectQaMapper;
import com.qheeshow.eway.service.model.ProjectQa;
import com.qheeshow.eway.service.model.ProjectQaExample;
import com.qheeshow.eway.service.service.ProjectQaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lihuajun on 17-3-13.
 */
@Service
public class ProjectQaServiceImpl implements ProjectQaService {

    @Autowired
    private ProjectQaMapper projectQaMapper;

    @Override
    public List<ProjectQa> list() {
        return projectQaMapper.selectByExample(new ProjectQaExample());
    }

    @Override
    public Map<String, Object> listByPage(ProjectQa commonQa, Integer pageIndex) {

        commonQa.setStartRow((pageIndex - 1) * commonQa.getPageSize());
        Map<String, Object> map = new HashMap<>();
        map.put("commonQas", projectQaMapper.listByPage(commonQa));
        map.put("count", projectQaMapper.countByPage(commonQa));
        return map;
    }

    @Override
    public ProjectQa get(Integer id) {
        return projectQaMapper.selectByPrimaryKey(id);
    }

    @Override
    public void save(ProjectQa commonQa) {
        projectQaMapper.insert(commonQa);
    }

    @Override
    public List<ProjectQa> listQByProject(Integer projectid) {
        return projectQaMapper.listQByProject(projectid);
    }

    @Override
    public List<ProjectQa> listA(List<Integer> ids) {
        ProjectQaExample example = new ProjectQaExample();
        ProjectQaExample.Criteria criteria = example.createCriteria();
        criteria.andParentidIn(ids);
        return projectQaMapper.selectByExample(example);
    }
}
