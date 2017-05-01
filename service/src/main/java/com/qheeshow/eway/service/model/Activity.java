package com.qheeshow.eway.service.model;

import java.math.BigDecimal;
import java.util.Date;

public class Activity extends BaseModel{

    private String logo;

    private String title;

    private Date beginTime;

    private Date endTime;

    private String summary;

    private Date signEndTime;

    private String address;

    private Integer limitNum;

    private String tel;

    private String sponsor;

    private Integer pv;

    private String longitude;

    private String latitude;

    private Integer status;

    private BigDecimal cost;

    private Integer isHead;

    private Integer docStatus;

    private String baiduMap;

    private Integer activityClass;

    private String sBeginTime;

    private String sEndTime;

    private String sSignEndTime;

    private String content;

    private String qrcode;

    public String getQrcode() {
        return qrcode;
    }

    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo == null ? null : logo.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary == null ? null : summary.trim();
    }

    public Date getSignEndTime() {
        return signEndTime;
    }

    public void setSignEndTime(Date signEndTime) {
        this.signEndTime = signEndTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Integer getLimitNum() {
        return limitNum;
    }

    public void setLimitNum(Integer limitNum) {
        this.limitNum = limitNum;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    public String getSponsor() {
        return sponsor;
    }

    public void setSponsor(String sponsor) {
        this.sponsor = sponsor == null ? null : sponsor.trim();
    }

    public Integer getPv() {
        return pv;
    }

    public void setPv(Integer pv) {
        this.pv = pv;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude == null ? null : longitude.trim();
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude == null ? null : latitude.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public Integer getIsHead() {
        return isHead;
    }

    public void setIsHead(Integer isHead) {
        this.isHead = isHead;
    }

    public Integer getDocStatus() {
        return docStatus;
    }

    public void setDocStatus(Integer docStatus) {
        this.docStatus = docStatus;
    }

    public String getBaiduMap() {
        return baiduMap;
    }

    public void setBaiduMap(String baiduMap) {
        this.baiduMap = baiduMap == null ? null : baiduMap.trim();
    }

    public Integer getActivityClass() {
        return activityClass;
    }

    public void setActivityClass(Integer activityClass) {
        this.activityClass = activityClass;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getsBeginTime() {
        return sBeginTime;
    }

    public void setsBeginTime(String sBeginTime) {
        this.sBeginTime = sBeginTime;
    }

    public String getsEndTime() {
        return sEndTime;
    }

    public void setsEndTime(String sEndTime) {
        this.sEndTime = sEndTime;
    }

    public String getsSignEndTime() {
        return sSignEndTime;
    }

    public void setsSignEndTime(String sSignEndTime) {
        this.sSignEndTime = sSignEndTime;
    }
}