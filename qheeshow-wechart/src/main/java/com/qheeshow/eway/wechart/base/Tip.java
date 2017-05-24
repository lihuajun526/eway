package com.qheeshow.eway.wechart.base;

/**
 * Created by lihuajun on 2017/5/21.
 */
public class Tip<T> {

    private String link = "close";
    private String action = "知道了";
    private T data;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
