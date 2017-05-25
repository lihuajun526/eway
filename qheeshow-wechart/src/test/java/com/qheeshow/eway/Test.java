package com.qheeshow.eway;

import com.qheeshow.eway.wechart.util.wechat.Menus;

import java.io.UnsupportedEncodingException;

/**
 * Created by lihuajun on 2017/5/24.
 */
public class Test {

    @org.junit.Test
    public void t(){
        Menus menus = new Menus();
        try {
            System.out.println(menus.create());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

}
