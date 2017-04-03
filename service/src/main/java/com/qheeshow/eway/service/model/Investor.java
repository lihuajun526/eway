package com.qheeshow.eway.service.model;

import java.util.Date;

public class Investor extends BaseModel {
    private Integer id;

    private String photo;

    private String trueName;

    private Integer investorType;

    private String companyName;

    private String companyRank;

    private String wechatId;

    private String cityId;

    private String cityName;

    private String stageId;

    private String stageName;

    private String industryId;

    private String industryName;

    private Integer singlePriceId;

    private String singlePrice;

    private Integer styleId;

    private String style;

    private String tags;

    private String summary;

    private String firstCity;

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    private Integer preferenceId;

    private String preference;

    private String investorCase;

    private String recommender;

    private String personalProfile;

    private String mobile;

    private String email;

    private String idCard;

    private String businessCardPositive;

    private String businessCardOpposite;

    private Integer personalAssetsId;

    private String personalAssets;

    private Integer status;

    private Integer authStatus;

    private Integer isSign;

    private Integer isBest;

    private Integer userid;

    private Date createTime;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo == null ? null : photo.trim();
    }

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName == null ? null : trueName.trim();
    }

    public Integer getInvestorType() {
        return investorType;
    }

    public void setInvestorType(Integer investorType) {
        this.investorType = investorType;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    public String getCompanyRank() {
        return companyRank;
    }

    public void setCompanyRank(String companyRank) {
        this.companyRank = companyRank == null ? null : companyRank.trim();
    }

    public String getWechatId() {
        return wechatId;
    }

    public void setWechatId(String wechatId) {
        this.wechatId = wechatId == null ? null : wechatId.trim();
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId == null ? null : cityId.trim();
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName == null ? null : cityName.trim();
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId == null ? null : stageId.trim();
    }

    public String getStageName() {
        return stageName;
    }

    public void setStageName(String stageName) {
        this.stageName = stageName == null ? null : stageName.trim();
    }

    public String getIndustryId() {
        return industryId;
    }

    public void setIndustryId(String industryId) {
        this.industryId = industryId == null ? null : industryId.trim();
    }

    public String getIndustryName() {
        return industryName;
    }

    public void setIndustryName(String industryName) {
        this.industryName = industryName == null ? null : industryName.trim();
    }

    public Integer getSinglePriceId() {
        return singlePriceId;
    }

    public void setSinglePriceId(Integer singlePriceId) {
        this.singlePriceId = singlePriceId;
    }

    public String getSinglePrice() {
        return singlePrice;
    }

    public void setSinglePrice(String singlePrice) {
        this.singlePrice = singlePrice == null ? null : singlePrice.trim();
    }

    public Integer getStyleId() {
        return styleId;
    }

    public void setStyleId(Integer styleId) {
        this.styleId = styleId;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style == null ? null : style.trim();
    }

    public Integer getPreferenceId() {
        return preferenceId;
    }

    public void setPreferenceId(Integer preferenceId) {
        this.preferenceId = preferenceId;
    }

    public String getPreference() {
        return preference;
    }

    public void setPreference(String preference) {
        this.preference = preference == null ? null : preference.trim();
    }

    public String getInvestorCase() {
        return investorCase;
    }

    public void setInvestorCase(String investorCase) {
        this.investorCase = investorCase == null ? null : investorCase.trim();
    }

    public String getRecommender() {
        return recommender;
    }

    public void setRecommender(String recommender) {
        this.recommender = recommender == null ? null : recommender.trim();
    }

    public String getPersonalProfile() {
        return personalProfile;
    }

    public void setPersonalProfile(String personalProfile) {
        this.personalProfile = personalProfile == null ? null : personalProfile.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard == null ? null : idCard.trim();
    }

    public String getBusinessCardPositive() {
        return businessCardPositive;
    }

    public void setBusinessCardPositive(String businessCardPositive) {
        this.businessCardPositive = businessCardPositive == null ? null : businessCardPositive.trim();
    }

    public String getBusinessCardOpposite() {
        return businessCardOpposite;
    }

    public void setBusinessCardOpposite(String businessCardOpposite) {
        this.businessCardOpposite = businessCardOpposite == null ? null : businessCardOpposite.trim();
    }

    public Integer getPersonalAssetsId() {
        return personalAssetsId;
    }

    public void setPersonalAssetsId(Integer personalAssetsId) {
        this.personalAssetsId = personalAssetsId;
    }

    public String getPersonalAssets() {
        return personalAssets;
    }

    public void setPersonalAssets(String personalAssets) {
        this.personalAssets = personalAssets == null ? null : personalAssets.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
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

    public Integer getIsBest() {
        return isBest;
    }

    public void setIsBest(Integer isBest) {
        this.isBest = isBest;
    }

    public Integer getAuthStatus() {
        return authStatus;
    }

    public void setAuthStatus(Integer authStatus) {
        this.authStatus = authStatus;
    }

    public Integer getIsSign() {
        return isSign;
    }

    public void setIsSign(Integer isSign) {
        this.isSign = isSign;
    }

    public String getFirstCity() {
        return firstCity;
    }

    public void setFirstCity(String firstCity) {
        this.firstCity = firstCity;
    }
}