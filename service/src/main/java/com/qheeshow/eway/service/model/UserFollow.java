package com.qheeshow.eway.service.model;

/**
 * Created by lihuajun on 17-2-8.
 */
public class UserFollow extends BaseModel {

    private Integer userid;
    private Integer followid;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getFollowid() {
        return followid;
    }

    public void setFollowid(Integer followid) {
        this.followid = followid;
    }
}
