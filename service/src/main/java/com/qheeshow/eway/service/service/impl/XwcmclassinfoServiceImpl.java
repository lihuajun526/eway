package com.qheeshow.eway.service.service.impl;

import com.qheeshow.eway.service.dao.XwcmclassinfoMapper;
import com.qheeshow.eway.service.model.Xwcmclassinfo;
import com.qheeshow.eway.service.model.XwcmclassinfoExample;
import com.qheeshow.eway.service.service.XwcmclassinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lihuajun on 17-1-23.
 */
@Service("xwcmclassinfoService")
public class XwcmclassinfoServiceImpl implements XwcmclassinfoService {

    @Autowired
    private XwcmclassinfoMapper xwcmclassinfoMapper;


    @Override
    public List<Xwcmclassinfo> listByParent(Integer pid) {
        XwcmclassinfoExample xwcmclassinfoExample = new XwcmclassinfoExample();
        XwcmclassinfoExample.Criteria criteria = xwcmclassinfoExample.createCriteria();
        criteria.andParentidEqualTo(pid);
        List<Xwcmclassinfo> list = xwcmclassinfoMapper.selectByExample(xwcmclassinfoExample);
        return list;
    }

    @Override
    public List<Xwcmclassinfo> listByRoot(Integer rootid) {
        XwcmclassinfoExample xwcmclassinfoExample = new XwcmclassinfoExample();
        XwcmclassinfoExample.Criteria criteria = xwcmclassinfoExample.createCriteria();
        criteria.andRootidEqualTo(rootid);
        List<Xwcmclassinfo> list = xwcmclassinfoMapper.selectByExample(xwcmclassinfoExample);
        return list;
    }

    @Override
    public Xwcmclassinfo get(Integer id) {
        return xwcmclassinfoMapper.selectByPrimaryKey(id);
    }


}
