package com.qheeshow.eway.common.util;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringContext {
    private static ClassPathXmlApplicationContext context;

    public static void init(String... configLocations) {
        context = new ClassPathXmlApplicationContext(configLocations);
    }

    public static ClassPathXmlApplicationContext getContext() {
        return context;
    }
}
