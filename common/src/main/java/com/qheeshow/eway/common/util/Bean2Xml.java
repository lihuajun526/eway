package com.qheeshow.eway.common.util;

import com.thoughtworks.xstream.XStream;

/**
 * Created by lihuajun on 2017/4/26.
 */
public class Bean2Xml {

    public static String toXml(Object o) {
        XStream xStream = new XStream();
        xStream.alias("xml", o.getClass());
        return xStream.toXML(o);
    }

    public static Object toBean(String xml, Class clazz) {
        XStream xStream = new XStream();
        xStream.alias("xml", clazz);
        return xStream.fromXML(xml);
    }

}
