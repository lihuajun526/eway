package com.qheeshow.eway.service.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qheeshow.eway.service.dao.ClassinfoMapper;
import com.qheeshow.eway.service.dao.XWClassInfoMapper;
import com.qheeshow.eway.service.model.Classinfo;
import com.qheeshow.eway.service.model.ClassinfoExample;
import com.qheeshow.eway.service.model.XWClassInfo;
import com.qheeshow.eway.service.model.XWClassInfoExample;
import com.qheeshow.eway.service.service.ClassinfoService;

/**
 * Created by lihuajun on 17-1-23.
 */
@Service
public class ClassinfoServiceImpl implements ClassinfoService {

    @Autowired
    private ClassinfoMapper classinfoMapper;
    
    @Autowired
    private XWClassInfoMapper XWClassInfoMapper;


    @Override public List<Classinfo> listByParent(Integer pid) {
        List<Classinfo> list = new ArrayList<>();

        return null;
    }

    @Override public List<Classinfo> listByRoot(Integer rootid) {
        List<Classinfo> list = new ArrayList<>();
        Classinfo classinfo1 = new Classinfo();
        classinfo1.setName("123");
        classinfo1.setId(1);

        Classinfo classinfo2 = new Classinfo();
        classinfo2.setName("abc");
        classinfo2.setId(2);

        Classinfo classinfo3 = new Classinfo();
        classinfo3.setName("456");
        classinfo3.setId(3);

        list.add(classinfo1);
        list.add(classinfo2);
        list.add(classinfo3);

        return list;
    }

    @Override public Classinfo get(Integer id) {
        Classinfo classinfo1 = new Classinfo();
        classinfo1.setName("互联网");
        return classinfo1;
    }

    @Override
    public void synData() {

    }

    @Override
    public Map<String,List<XWClassInfo>> getTypeList(){
    	Map<String,List<XWClassInfo>> result = new HashMap<String,List<XWClassInfo>>();
    	XWClassInfoExample example = new XWClassInfoExample();
    	example.createCriteria().andParentidEqualTo(0);
    	List<XWClassInfo> parents = XWClassInfoMapper.selectByExample(example);
    	for(XWClassInfo classinfo : parents){
    		XWClassInfoExample exampleAnother = new XWClassInfoExample();
    		exampleAnother.createCriteria().andParentidEqualTo(classinfo.getClassinfoid());
        	List<XWClassInfo> parentsAnother = XWClassInfoMapper.selectByExample(exampleAnother);
        	result.put(classinfo.getClassinfoid().toString(), parentsAnother);
    	}
    	return result;
    }

}
