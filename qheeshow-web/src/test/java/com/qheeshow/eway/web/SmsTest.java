package com.qheeshow.eway.web;

import com.qheeshow.eway.common.exception.SendSMSException;
import com.qheeshow.eway.common.util.SmsSender;
import org.junit.Test;

/**
 * Created by lihuajun on 17-1-19.
 */
public class SmsTest {

    @Test
    public void test1() {
        try {
            SmsSender.send("18857107097");
        } catch (SendSMSException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2() {

        long a = System.currentTimeMillis();
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long b = System.currentTimeMillis();
        System.out.println(b - a);

    }
}
