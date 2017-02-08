package com.qheeshow.eway.service.model;

/**
 * Created by lihuajun on 17-1-18.
 */
public class TeamMember extends BaseModel {

    private Integer projectid;//项目id
    private String memberName;//团队成员
    private String photo;//成员头像
    private String position;//职位
    private String summary;//个人介绍
    private Integer isFounder;//是否是创始人:1是,0不是

    public Integer getProjectid() {
        return projectid;
    }

    public void setProjectid(Integer projectid) {
        this.projectid = projectid;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Integer getIsFounder() {
        return isFounder;
    }

    public void setIsFounder(Integer isFounder) {
        this.isFounder = isFounder;
    }
}
