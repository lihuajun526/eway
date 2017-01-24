package com.qheeshow.eway.service.model;

/**
 * Created by lihuajun on 17-1-24.
 */
public class UserClassinfoMapping extends BaseModel {

    private Integer userid;
    private Integer classinfoid;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getClassinfoid() {
        return classinfoid;
    }

    public void setClassinfoid(Integer classinfoid) {
        this.classinfoid = classinfoid;
    }
}
