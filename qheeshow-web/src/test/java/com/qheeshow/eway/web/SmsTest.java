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

}
