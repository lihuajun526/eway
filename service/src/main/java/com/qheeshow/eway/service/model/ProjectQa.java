package com.qheeshow.eway.service.model;

import java.util.Date;

public class ProjectQa extends BaseModel{
    private Integer id;

    private Integer parentid;

    private Integer projectid;

    private String question;

    private String content;

    private Integer qUserid;

    private String qName;

    private Integer userid;

    private Integer status;

    private Date createTime;

    private Date updateTime;

    private String name;

    private String photo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question == null ? null : question.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getqUserid() {
        return qUserid;
    }

    public void setqUserid(Integer qUserid) {
        this.qUserid = qUserid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getqName() {
        return qName;
    }

    public void setqName(String qName) {
        this.qName = qName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getProjectid() {
        return projectid;
    }

    public void setProjectid(Integer projectid) {
        this.projectid = projectid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }
}