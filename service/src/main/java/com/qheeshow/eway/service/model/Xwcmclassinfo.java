package com.qheeshow.eway.service.model;

import java.util.Date;

public class Xwcmclassinfo {
    private Integer classinfoid;

    private Integer classorder;

    private String cname;

    private String cdesc;

    private Integer parentid;

    private Integer rootid;

    private String cruser;

    private Date crtime;

    private String ccode;

    public Integer getClassinfoid() {
        return classinfoid;
    }

    public void setClassinfoid(Integer classinfoid) {
        this.classinfoid = classinfoid;
    }

    public Integer getClassorder() {
        return classorder;
    }

    public void setClassorder(Integer classorder) {
        this.classorder = classorder;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname == null ? null : cname.trim();
    }

    public String getCdesc() {
        return cdesc;
    }

    public void setCdesc(String cdesc) {
        this.cdesc = cdesc == null ? null : cdesc.trim();
    }

    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

    public Integer getRootid() {
        return rootid;
    }

    public void setRootid(Integer rootid) {
        this.rootid = rootid;
    }

    public String getCruser() {
        return cruser;
    }

    public void setCruser(String cruser) {
        this.cruser = cruser == null ? null : cruser.trim();
    }

    public Date getCrtime() {
        return crtime;
    }

    public void setCrtime(Date crtime) {
        this.crtime = crtime;
    }

    public String getCcode() {
        return ccode;
    }

    public void setCcode(String ccode) {
        this.ccode = ccode == null ? null : ccode.trim();
    }
}