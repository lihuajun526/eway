package com.qheeshow.eway.service.model;

/**
 * Created by lihuajun on 17-1-18.
 */
public class ProjectQa extends BaseModel {

    private String projectName;//项目名称
    private Integer parentid;//项目或问题id
    private String content;//问题或答案内容
    private Integer userid;//提问者或回答者id
    private Integer status;//状态:1新问题或新回答,2审核通过,3审核未通过

    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
}
