package com.qheeshow.eway.service.model;

/**
 * Created by lihuajun on 17-1-15.
 */
public class Classinfo extends BaseModel {

    private Integer rootid;
    private Integer parentid;
    private String name;
    private Integer status;

    public Integer getRootid() {
        return rootid;
    }

    public void setRootid(Integer rootid) {
        this.rootid = rootid;
    }

    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
