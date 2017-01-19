package com.qheeshow.eway.web.base;

import com.alibaba.fastjson.JSON;

public class Result<T> {

    /**
     * code>=0，成功
     * code<0，失败
     */
    private int code;
    private String message = "";
    private T data;

    public Result() {

    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void set(int code, String message, T data) {
        this.setCode(code);
        this.setMessage(message);
        this.setData(data);
    }

    public void set(int code, String message) {
        this.set(code, message, null);
    }

    public void set(int code, T data) {
        this.set(code, null, data);
    }

    public void set(String message, T data) {
        this.set(0, message, data);
    }

    public void set(String message) {
        this.set(0, message);
    }

    public String toString() {
        return JSON.toJSONString(this);
    }

}
