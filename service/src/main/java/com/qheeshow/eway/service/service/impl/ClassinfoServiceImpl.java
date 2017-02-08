package com.qheeshow.eway.service.service.impl;

import com.qheeshow.eway.service.model.Classinfo;
import com.qheeshow.eway.service.service.ClassinfoService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lihuajun on 17-1-23.
 */
@Service
public class ClassinfoServiceImpl implements ClassinfoService {
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
}
