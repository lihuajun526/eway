package com.qheeshow.eway.backstage.base;

import com.alibaba.fastjson.JSONObject;
import com.qheeshow.eway.wechart.exception.BaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

public class BaseController {

    public static final Logger logger = LoggerFactory.getLogger(BaseController.class);

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public String exception(Exception e, HttpServletRequest request) {

        logger.error("have exception", e);
        if (e instanceof BaseException) {// BaseException
            return e.toString();
        } else { // unknown exception
            JSONObject error = new JSONObject();
            error.put("code", "-1");
            error.put("message", e.toString());
            return error.toJSONString();
        }
    }
}