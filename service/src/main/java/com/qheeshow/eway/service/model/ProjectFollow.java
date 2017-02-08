package com.qheeshow.eway.service.model;

/**
 * Created by lihuajun on 17-2-8.
 */
public class ProjectFollow extends BaseModel {

    private Integer projectid;
    private Integer userid;

    public Integer getProjectid() {
        return projectid;
    }

    public void setProjectid(Integer projectid) {
        this.projectid = projectid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }
}
