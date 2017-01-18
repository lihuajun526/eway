package com.qheeshow.eway.service.model;

/**
 * Created by lihuajun on 17-1-18.
 */
public class Message extends BaseModel {

    private Integer toUserid;
    private String content;

    public Integer getToUserid() {
        return toUserid;
    }

    public void setToUserid(Integer toUserid) {
        this.toUserid = toUserid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
