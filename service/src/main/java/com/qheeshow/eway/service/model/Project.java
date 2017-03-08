package com.qheeshow.eway.service.model;

import java.util.Date;

public class Project extends BaseModel{
    private Integer id;

    private String title;

    private String demand;

    private String highlights;

    private String lastOne;

    private String lastTwo;

    private String description;

    private Integer type;

    private Integer industry;

    private String industryName;

    private Integer area;

    private String areaName;

    private Integer financingLimit;

    private String financingLimitName;

    private Integer stage;

    private String stageName;

    private String videoLink;

    private String proLink;

    private Integer userid;

    private String username;

    private Integer status;

    private String logo;

    private String tags;

    private String bp;

    private String bpName;

    private Integer percent;

    private String referee;

    private String lastInvestment;

    private String lastStage;

    private String lastLimit;

    private Date createTime;

    private Date updateTime;

    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getDemand() {
        return demand;
    }

    public void setDemand(String demand) {
        this.demand = demand == null ? null : demand.trim();
    }

    public String getHighlights() {
        return highlights;
    }

    public void setHighlights(String highlights) {
        this.highlights = highlights == null ? null : highlights.trim();
    }

    public String getLastOne() {
        return lastOne;
    }

    public void setLastOne(String lastOne) {
        this.lastOne = lastOne == null ? null : lastOne.trim();
    }

    public String getLastTwo() {
        return lastTwo;
    }

    public void setLastTwo(String lastTwo) {
        this.lastTwo = lastTwo == null ? null : lastTwo.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getIndustry() {
        return industry;
    }

    public void setIndustry(Integer industry) {
        this.industry = industry;
    }

    public String getIndustryName() {
        return industryName;
    }

    public void setIndustryName(String industryName) {
        this.industryName = industryName == null ? null : industryName.trim();
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName == null ? null : areaName.trim();
    }

    public Integer getFinancingLimit() {
        return financingLimit;
    }

    public void setFinancingLimit(Integer financingLimit) {
        this.financingLimit = financingLimit;
    }

    public String getFinancingLimitName() {
        return financingLimitName;
    }

    public void setFinancingLimitName(String financingLimitName) {
        this.financingLimitName = financingLimitName == null ? null : financingLimitName.trim();
    }

    public Integer getStage() {
        return stage;
    }

    public void setStage(Integer stage) {
        this.stage = stage;
    }

    public String getStageName() {
        return stageName;
    }

    public void setStageName(String stageName) {
        this.stageName = stageName == null ? null : stageName.trim();
    }

    public String getVideoLink() {
        return videoLink;
    }

    public void setVideoLink(String videoLink) {
        this.videoLink = videoLink == null ? null : videoLink.trim();
    }

    public String getProLink() {
        return proLink;
    }

    public void setProLink(String proLink) {
        this.proLink = proLink == null ? null : proLink.trim();
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
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
        this.logo = logo == null ? null : logo.trim();
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags == null ? null : tags.trim();
    }

    public String getBp() {
        return bp;
    }

    public void setBp(String bp) {
        this.bp = bp == null ? null : bp.trim();
    }

    public String getBpName() {
        return bpName;
    }

    public void setBpName(String bpName) {
        this.bpName = bpName == null ? null : bpName.trim();
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
        this.referee = referee == null ? null : referee.trim();
    }

    public String getLastInvestment() {
        return lastInvestment;
    }

    public void setLastInvestment(String lastInvestment) {
        this.lastInvestment = lastInvestment == null ? null : lastInvestment.trim();
    }

    public String getLastStage() {
        return lastStage;
    }

    public void setLastStage(String lastStage) {
        this.lastStage = lastStage == null ? null : lastStage.trim();
    }

    public String getLastLimit() {
        return lastLimit;
    }

    public void setLastLimit(String lastLimit) {
        this.lastLimit = lastLimit == null ? null : lastLimit.trim();
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}