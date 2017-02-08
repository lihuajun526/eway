package com.qheeshow.eway.service.model;

/**
 * Created by lihuajun on 17-1-18.
 */
public class Message extends BaseModel {

    private Integer toUserid;
    private String content;
    private Integer status;//状态:1新消息,2已读

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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
