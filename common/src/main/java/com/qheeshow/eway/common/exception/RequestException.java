package com.qheeshow.eway.common.exception;

/**
 * Created by lihuajun on 16-12-21.
 */
public class RequestException extends Exception {

    private String url;

    public RequestException(String url) {
        this.url = url;
    }

    public RequestException() {

    }

    @Override
    public String getMessage() {
        return "request[" + url + "]error";
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
