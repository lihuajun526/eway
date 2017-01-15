package com.qheeshow.eway.wechart.exception;


import com.qheeshow.eway.wechart.constant.ExceptionTypeEnum;

/**
 * 请求异常
 *
 * @author: Zhou Xuanang
 * @Date: 14:04 16/7/5.
 */
public class RequestException extends Exception {
    private static final long serialVersionUID = 1L;

    public RequestException(ExceptionTypeEnum exceptionTypeEnum) {
    }
}
