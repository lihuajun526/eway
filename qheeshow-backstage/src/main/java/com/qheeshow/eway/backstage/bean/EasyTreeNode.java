package com.qheeshow.eway.backstage.bean;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by lihuajun on 16-7-25.
 */
public class EasyTreeNode {

    private Integer id;

    private String text;

    private String state;

    private JSONObject attributes;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public JSONObject getAttributes() {
        return attributes;
    }

    public void setAttributes(JSONObject attributes) {
        this.attributes = attributes;
    }
}
