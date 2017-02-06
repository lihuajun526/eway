package com.qheeshow.eway.service.model;

import java.math.BigDecimal;

/**
 * Created by lihuajun on 17-1-11.
 */
public class Project extends BaseModel {

    private String title;//项目标题
    private String summary;//项目简介
    private String desc;//项目介绍
    private Integer industry;//所属行业
    private Integer area;//所在区域
    private String content;//项目内容
    private String videoLink;//宣传视频
    private String proLink;//项目网址
    private Integer userid;//项目联系人id
    private String username;//项目联系人
    private Integer status;//项目状态:1新项目,2审核通过,3审核未通过
    private String logo;//项目logo
    private String tags;//项目标签
    private String bp;//商业计划书
    private String bpName;//上传时商业计划书的名称
    private Integer financingLimit;//融资额度
    private Integer percent;//出让股份百分比
    private String referee;//推荐人姓名

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getIndustry() {
        return industry;
    }

    public void setIndustry(Integer industry) {
        this.industry = industry;
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getVideoLink() {
        return videoLink;
    }

    public void setVideoLink(String videoLink) {
        this.videoLink = videoLink;
    }

    public String getProLink() {
        return proLink;
    }

    public void setProLink(String proLink) {
        this.proLink = proLink;
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

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getBp() {
        return bp;
    }

    public void setBp(String bp) {
        this.bp = bp;
    }

    public Integer getFinancingLimit() {
        return financingLimit;
    }

    public void setFinancingLimit(Integer financingLimit) {
        this.financingLimit = financingLimit;
    }

    public Integer getPercent() {
        return percent;
    }

    public void setPercent(Integer percent) {
        this.percent = percent;
    }

    public String getReferee() {
        return referee;
    }

    public void setReferee(String referee) {
        this.referee = referee;
    }

    public String getBpName() {
        return bpName;
    }

    public void setBpName(String bpName) {
        this.bpName = bpName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
