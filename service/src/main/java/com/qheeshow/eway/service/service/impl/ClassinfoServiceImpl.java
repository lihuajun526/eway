package com.qheeshow.eway.service.service.impl;

import com.qheeshow.eway.service.model.Classinfo;
import com.qheeshow.eway.service.service.ClassinfoService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lihuajun on 17-1-23.
 */
@Service("classinfoService")
public class ClassinfoServiceImpl implements ClassinfoService {
    @Override public List<Classinfo> listByParent(Integer pid) {
        List<Classinfo> list = new ArrayList<>();

        return null;
    }

    @Override public List<Classinfo> listByRoot(Integer rootid) {
        List<Classinfo> list = new ArrayList<>();
        Classinfo classinfo1 = new Classinfo();
        classinfo1.setName("123");
        Classinfo classinfo2 = new Classinfo();
        classinfo2.setName("abc");
        Classinfo classinfo3 = new Classinfo();
        classinfo3.setName("456");
        return null;
    }
}
