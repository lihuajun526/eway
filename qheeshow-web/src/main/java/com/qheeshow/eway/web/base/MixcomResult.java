package com.qheeshow.eway.web.base;

public class MixcomResult {

    private String code;
    private String msg;
    private String result;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void set(String code, String mgs, String result) {
        this.code = code;
        this.msg = msg;
        this.result = result;
    }
}
