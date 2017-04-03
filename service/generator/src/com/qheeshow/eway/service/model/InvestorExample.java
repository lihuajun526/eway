package com.qheeshow.eway.service.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InvestorExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public InvestorExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andPhotoIsNull() {
            addCriterion("photo is null");
            return (Criteria) this;
        }

        public Criteria andPhotoIsNotNull() {
            addCriterion("photo is not null");
            return (Criteria) this;
        }

        public Criteria andPhotoEqualTo(String value) {
            addCriterion("photo =", value, "photo");
            return (Criteria) this;
        }

        public Criteria andPhotoNotEqualTo(String value) {
            addCriterion("photo <>", value, "photo");
            return (Criteria) this;
        }

        public Criteria andPhotoGreaterThan(String value) {
            addCriterion("photo >", value, "photo");
            return (Criteria) this;
        }

        public Criteria andPhotoGreaterThanOrEqualTo(String value) {
            addCriterion("photo >=", value, "photo");
            return (Criteria) this;
        }

        public Criteria andPhotoLessThan(String value) {
            addCriterion("photo <", value, "photo");
            return (Criteria) this;
        }

        public Criteria andPhotoLessThanOrEqualTo(String value) {
            addCriterion("photo <=", value, "photo");
            return (Criteria) this;
        }

        public Criteria andPhotoLike(String value) {
            addCriterion("photo like", value, "photo");
            return (Criteria) this;
        }

        public Criteria andPhotoNotLike(String value) {
            addCriterion("photo not like", value, "photo");
            return (Criteria) this;
        }

        public Criteria andPhotoIn(List<String> values) {
            addCriterion("photo in", values, "photo");
            return (Criteria) this;
        }

        public Criteria andPhotoNotIn(List<String> values) {
            addCriterion("photo not in", values, "photo");
            return (Criteria) this;
        }

        public Criteria andPhotoBetween(String value1, String value2) {
            addCriterion("photo between", value1, value2, "photo");
            return (Criteria) this;
        }

        public Criteria andPhotoNotBetween(String value1, String value2) {
            addCriterion("photo not between", value1, value2, "photo");
            return (Criteria) this;
        }

        public Criteria andTrueNameIsNull() {
            addCriterion("true_name is null");
            return (Criteria) this;
        }

        public Criteria andTrueNameIsNotNull() {
            addCriterion("true_name is not null");
            return (Criteria) this;
        }

        public Criteria andTrueNameEqualTo(String value) {
            addCriterion("true_name =", value, "trueName");
            return (Criteria) this;
        }

        public Criteria andTrueNameNotEqualTo(String value) {
            addCriterion("true_name <>", value, "trueName");
            return (Criteria) this;
        }

        public Criteria andTrueNameGreaterThan(String value) {
            addCriterion("true_name >", value, "trueName");
            return (Criteria) this;
        }

        public Criteria andTrueNameGreaterThanOrEqualTo(String value) {
            addCriterion("true_name >=", value, "trueName");
            return (Criteria) this;
        }

        public Criteria andTrueNameLessThan(String value) {
            addCriterion("true_name <", value, "trueName");
            return (Criteria) this;
        }

        public Criteria andTrueNameLessThanOrEqualTo(String value) {
            addCriterion("true_name <=", value, "trueName");
            return (Criteria) this;
        }

        public Criteria andTrueNameLike(String value) {
            addCriterion("true_name like", value, "trueName");
            return (Criteria) this;
        }

        public Criteria andTrueNameNotLike(String value) {
            addCriterion("true_name not like", value, "trueName");
            return (Criteria) this;
        }

        public Criteria andTrueNameIn(List<String> values) {
            addCriterion("true_name in", values, "trueName");
            return (Criteria) this;
        }

        public Criteria andTrueNameNotIn(List<String> values) {
            addCriterion("true_name not in", values, "trueName");
            return (Criteria) this;
        }

        public Criteria andTrueNameBetween(String value1, String value2) {
            addCriterion("true_name between", value1, value2, "trueName");
            return (Criteria) this;
        }

        public Criteria andTrueNameNotBetween(String value1, String value2) {
            addCriterion("true_name not between", value1, value2, "trueName");
            return (Criteria) this;
        }

        public Criteria andInvestorTypeIsNull() {
            addCriterion("investor_type is null");
            return (Criteria) this;
        }

        public Criteria andInvestorTypeIsNotNull() {
            addCriterion("investor_type is not null");
            return (Criteria) this;
        }

        public Criteria andInvestorTypeEqualTo(Integer value) {
            addCriterion("investor_type =", value, "investorType");
            return (Criteria) this;
        }

        public Criteria andInvestorTypeNotEqualTo(Integer value) {
            addCriterion("investor_type <>", value, "investorType");
            return (Criteria) this;
        }

        public Criteria andInvestorTypeGreaterThan(Integer value) {
            addCriterion("investor_type >", value, "investorType");
            return (Criteria) this;
        }

        public Criteria andInvestorTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("investor_type >=", value, "investorType");
            return (Criteria) this;
        }

        public Criteria andInvestorTypeLessThan(Integer value) {
            addCriterion("investor_type <", value, "investorType");
            return (Criteria) this;
        }

        public Criteria andInvestorTypeLessThanOrEqualTo(Integer value) {
            addCriterion("investor_type <=", value, "investorType");
            return (Criteria) this;
        }

        public Criteria andInvestorTypeIn(List<Integer> values) {
            addCriterion("investor_type in", values, "investorType");
            return (Criteria) this;
        }

        public Criteria andInvestorTypeNotIn(List<Integer> values) {
            addCriterion("investor_type not in", values, "investorType");
            return (Criteria) this;
        }

        public Criteria andInvestorTypeBetween(Integer value1, Integer value2) {
            addCriterion("investor_type between", value1, value2, "investorType");
            return (Criteria) this;
        }

        public Criteria andInvestorTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("investor_type not between", value1, value2, "investorType");
            return (Criteria) this;
        }

        public Criteria andCompanyNameIsNull() {
            addCriterion("company_name is null");
            return (Criteria) this;
        }

        public Criteria andCompanyNameIsNotNull() {
            addCriterion("company_name is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyNameEqualTo(String value) {
            addCriterion("company_name =", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameNotEqualTo(String value) {
            addCriterion("company_name <>", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameGreaterThan(String value) {
            addCriterion("company_name >", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameGreaterThanOrEqualTo(String value) {
            addCriterion("company_name >=", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameLessThan(String value) {
            addCriterion("company_name <", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameLessThanOrEqualTo(String value) {
            addCriterion("company_name <=", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameLike(String value) {
            addCriterion("company_name like", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameNotLike(String value) {
            addCriterion("company_name not like", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameIn(List<String> values) {
            addCriterion("company_name in", values, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameNotIn(List<String> values) {
            addCriterion("company_name not in", values, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameBetween(String value1, String value2) {
            addCriterion("company_name between", value1, value2, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameNotBetween(String value1, String value2) {
            addCriterion("company_name not between", value1, value2, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyRankIsNull() {
            addCriterion("company_rank is null");
            return (Criteria) this;
        }

        public Criteria andCompanyRankIsNotNull() {
            addCriterion("company_rank is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyRankEqualTo(String value) {
            addCriterion("company_rank =", value, "companyRank");
            return (Criteria) this;
        }

        public Criteria andCompanyRankNotEqualTo(String value) {
            addCriterion("company_rank <>", value, "companyRank");
            return (Criteria) this;
        }

        public Criteria andCompanyRankGreaterThan(String value) {
            addCriterion("company_rank >", value, "companyRank");
            return (Criteria) this;
        }

        public Criteria andCompanyRankGreaterThanOrEqualTo(String value) {
            addCriterion("company_rank >=", value, "companyRank");
            return (Criteria) this;
        }

        public Criteria andCompanyRankLessThan(String value) {
            addCriterion("company_rank <", value, "companyRank");
            return (Criteria) this;
        }

        public Criteria andCompanyRankLessThanOrEqualTo(String value) {
            addCriterion("company_rank <=", value, "companyRank");
            return (Criteria) this;
        }

        public Criteria andCompanyRankLike(String value) {
            addCriterion("company_rank like", value, "companyRank");
            return (Criteria) this;
        }

        public Criteria andCompanyRankNotLike(String value) {
            addCriterion("company_rank not like", value, "companyRank");
            return (Criteria) this;
        }

        public Criteria andCompanyRankIn(List<String> values) {
            addCriterion("company_rank in", values, "companyRank");
            return (Criteria) this;
        }

        public Criteria andCompanyRankNotIn(List<String> values) {
            addCriterion("company_rank not in", values, "companyRank");
            return (Criteria) this;
        }

        public Criteria andCompanyRankBetween(String value1, String value2) {
            addCriterion("company_rank between", value1, value2, "companyRank");
            return (Criteria) this;
        }

        public Criteria andCompanyRankNotBetween(String value1, String value2) {
            addCriterion("company_rank not between", value1, value2, "companyRank");
            return (Criteria) this;
        }

        public Criteria andWechatIdIsNull() {
            addCriterion("wechat_id is null");
            return (Criteria) this;
        }

        public Criteria andWechatIdIsNotNull() {
            addCriterion("wechat_id is not null");
            return (Criteria) this;
        }

        public Criteria andWechatIdEqualTo(String value) {
            addCriterion("wechat_id =", value, "wechatId");
            return (Criteria) this;
        }

        public Criteria andWechatIdNotEqualTo(String value) {
            addCriterion("wechat_id <>", value, "wechatId");
            return (Criteria) this;
        }

        public Criteria andWechatIdGreaterThan(String value) {
            addCriterion("wechat_id >", value, "wechatId");
            return (Criteria) this;
        }

        public Criteria andWechatIdGreaterThanOrEqualTo(String value) {
            addCriterion("wechat_id >=", value, "wechatId");
            return (Criteria) this;
        }

        public Criteria andWechatIdLessThan(String value) {
            addCriterion("wechat_id <", value, "wechatId");
            return (Criteria) this;
        }

        public Criteria andWechatIdLessThanOrEqualTo(String value) {
            addCriterion("wechat_id <=", value, "wechatId");
            return (Criteria) this;
        }

        public Criteria andWechatIdLike(String value) {
            addCriterion("wechat_id like", value, "wechatId");
            return (Criteria) this;
        }

        public Criteria andWechatIdNotLike(String value) {
            addCriterion("wechat_id not like", value, "wechatId");
            return (Criteria) this;
        }

        public Criteria andWechatIdIn(List<String> values) {
            addCriterion("wechat_id in", values, "wechatId");
            return (Criteria) this;
        }

        public Criteria andWechatIdNotIn(List<String> values) {
            addCriterion("wechat_id not in", values, "wechatId");
            return (Criteria) this;
        }

        public Criteria andWechatIdBetween(String value1, String value2) {
            addCriterion("wechat_id between", value1, value2, "wechatId");
            return (Criteria) this;
        }

        public Criteria andWechatIdNotBetween(String value1, String value2) {
            addCriterion("wechat_id not between", value1, value2, "wechatId");
            return (Criteria) this;
        }

        public Criteria andCityIdIsNull() {
            addCriterion("city_id is null");
            return (Criteria) this;
        }

        public Criteria andCityIdIsNotNull() {
            addCriterion("city_id is not null");
            return (Criteria) this;
        }

        public Criteria andCityIdEqualTo(String value) {
            addCriterion("city_id =", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdNotEqualTo(String value) {
            addCriterion("city_id <>", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdGreaterThan(String value) {
            addCriterion("city_id >", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdGreaterThanOrEqualTo(String value) {
            addCriterion("city_id >=", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdLessThan(String value) {
            addCriterion("city_id <", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdLessThanOrEqualTo(String value) {
            addCriterion("city_id <=", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdLike(String value) {
            addCriterion("city_id like", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdNotLike(String value) {
            addCriterion("city_id not like", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdIn(List<String> values) {
            addCriterion("city_id in", values, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdNotIn(List<String> values) {
            addCriterion("city_id not in", values, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdBetween(String value1, String value2) {
            addCriterion("city_id between", value1, value2, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdNotBetween(String value1, String value2) {
            addCriterion("city_id not between", value1, value2, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityNameIsNull() {
            addCriterion("city_name is null");
            return (Criteria) this;
        }

        public Criteria andCityNameIsNotNull() {
            addCriterion("city_name is not null");
            return (Criteria) this;
        }

        public Criteria andCityNameEqualTo(String value) {
            addCriterion("city_name =", value, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameNotEqualTo(String value) {
            addCriterion("city_name <>", value, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameGreaterThan(String value) {
            addCriterion("city_name >", value, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameGreaterThanOrEqualTo(String value) {
            addCriterion("city_name >=", value, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameLessThan(String value) {
            addCriterion("city_name <", value, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameLessThanOrEqualTo(String value) {
            addCriterion("city_name <=", value, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameLike(String value) {
            addCriterion("city_name like", value, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameNotLike(String value) {
            addCriterion("city_name not like", value, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameIn(List<String> values) {
            addCriterion("city_name in", values, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameNotIn(List<String> values) {
            addCriterion("city_name not in", values, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameBetween(String value1, String value2) {
            addCriterion("city_name between", value1, value2, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameNotBetween(String value1, String value2) {
            addCriterion("city_name not between", value1, value2, "cityName");
            return (Criteria) this;
        }

        public Criteria andStageIdIsNull() {
            addCriterion("stage_id is null");
            return (Criteria) this;
        }

        public Criteria andStageIdIsNotNull() {
            addCriterion("stage_id is not null");
            return (Criteria) this;
        }

        public Criteria andStageIdEqualTo(String value) {
            addCriterion("stage_id =", value, "stageId");
            return (Criteria) this;
        }

        public Criteria andStageIdNotEqualTo(String value) {
            addCriterion("stage_id <>", value, "stageId");
            return (Criteria) this;
        }

        public Criteria andStageIdGreaterThan(String value) {
            addCriterion("stage_id >", value, "stageId");
            return (Criteria) this;
        }

        public Criteria andStageIdGreaterThanOrEqualTo(String value) {
            addCriterion("stage_id >=", value, "stageId");
            return (Criteria) this;
        }

        public Criteria andStageIdLessThan(String value) {
            addCriterion("stage_id <", value, "stageId");
            return (Criteria) this;
        }

        public Criteria andStageIdLessThanOrEqualTo(String value) {
            addCriterion("stage_id <=", value, "stageId");
            return (Criteria) this;
        }

        public Criteria andStageIdLike(String value) {
            addCriterion("stage_id like", value, "stageId");
            return (Criteria) this;
        }

        public Criteria andStageIdNotLike(String value) {
            addCriterion("stage_id not like", value, "stageId");
            return (Criteria) this;
        }

        public Criteria andStageIdIn(List<String> values) {
            addCriterion("stage_id in", values, "stageId");
            return (Criteria) this;
        }

        public Criteria andStageIdNotIn(List<String> values) {
            addCriterion("stage_id not in", values, "stageId");
            return (Criteria) this;
        }

        public Criteria andStageIdBetween(String value1, String value2) {
            addCriterion("stage_id between", value1, value2, "stageId");
            return (Criteria) this;
        }

        public Criteria andStageIdNotBetween(String value1, String value2) {
            addCriterion("stage_id not between", value1, value2, "stageId");
            return (Criteria) this;
        }

        public Criteria andStageNameIsNull() {
            addCriterion("stage_name is null");
            return (Criteria) this;
        }

        public Criteria andStageNameIsNotNull() {
            addCriterion("stage_name is not null");
            return (Criteria) this;
        }

        public Criteria andStageNameEqualTo(String value) {
            addCriterion("stage_name =", value, "stageName");
            return (Criteria) this;
        }

        public Criteria andStageNameNotEqualTo(String value) {
            addCriterion("stage_name <>", value, "stageName");
            return (Criteria) this;
        }

        public Criteria andStageNameGreaterThan(String value) {
            addCriterion("stage_name >", value, "stageName");
            return (Criteria) this;
        }

        public Criteria andStageNameGreaterThanOrEqualTo(String value) {
            addCriterion("stage_name >=", value, "stageName");
            return (Criteria) this;
        }

        public Criteria andStageNameLessThan(String value) {
            addCriterion("stage_name <", value, "stageName");
            return (Criteria) this;
        }

        public Criteria andStageNameLessThanOrEqualTo(String value) {
            addCriterion("stage_name <=", value, "stageName");
            return (Criteria) this;
        }

        public Criteria andStageNameLike(String value) {
            addCriterion("stage_name like", value, "stageName");
            return (Criteria) this;
        }

        public Criteria andStageNameNotLike(String value) {
            addCriterion("stage_name not like", value, "stageName");
            return (Criteria) this;
        }

        public Criteria andStageNameIn(List<String> values) {
            addCriterion("stage_name in", values, "stageName");
            return (Criteria) this;
        }

        public Criteria andStageNameNotIn(List<String> values) {
            addCriterion("stage_name not in", values, "stageName");
            return (Criteria) this;
        }

        public Criteria andStageNameBetween(String value1, String value2) {
            addCriterion("stage_name between", value1, value2, "stageName");
            return (Criteria) this;
        }

        public Criteria andStageNameNotBetween(String value1, String value2) {
            addCriterion("stage_name not between", value1, value2, "stageName");
            return (Criteria) this;
        }

        public Criteria andIndustryIdIsNull() {
            addCriterion("industry_id is null");
            return (Criteria) this;
        }

        public Criteria andIndustryIdIsNotNull() {
            addCriterion("industry_id is not null");
            return (Criteria) this;
        }

        public Criteria andIndustryIdEqualTo(String value) {
            addCriterion("industry_id =", value, "industryId");
            return (Criteria) this;
        }

        public Criteria andIndustryIdNotEqualTo(String value) {
            addCriterion("industry_id <>", value, "industryId");
            return (Criteria) this;
        }

        public Criteria andIndustryIdGreaterThan(String value) {
            addCriterion("industry_id >", value, "industryId");
            return (Criteria) this;
        }

        public Criteria andIndustryIdGreaterThanOrEqualTo(String value) {
            addCriterion("industry_id >=", value, "industryId");
            return (Criteria) this;
        }

        public Criteria andIndustryIdLessThan(String value) {
            addCriterion("industry_id <", value, "industryId");
            return (Criteria) this;
        }

        public Criteria andIndustryIdLessThanOrEqualTo(String value) {
            addCriterion("industry_id <=", value, "industryId");
            return (Criteria) this;
        }

        public Criteria andIndustryIdLike(String value) {
            addCriterion("industry_id like", value, "industryId");
            return (Criteria) this;
        }

        public Criteria andIndustryIdNotLike(String value) {
            addCriterion("industry_id not like", value, "industryId");
            return (Criteria) this;
        }

        public Criteria andIndustryIdIn(List<String> values) {
            addCriterion("industry_id in", values, "industryId");
            return (Criteria) this;
        }

        public Criteria andIndustryIdNotIn(List<String> values) {
            addCriterion("industry_id not in", values, "industryId");
            return (Criteria) this;
        }

        public Criteria andIndustryIdBetween(String value1, String value2) {
            addCriterion("industry_id between", value1, value2, "industryId");
            return (Criteria) this;
        }

        public Criteria andIndustryIdNotBetween(String value1, String value2) {
            addCriterion("industry_id not between", value1, value2, "industryId");
            return (Criteria) this;
        }

        public Criteria andIndustryNameIsNull() {
            addCriterion("industry_name is null");
            return (Criteria) this;
        }

        public Criteria andIndustryNameIsNotNull() {
            addCriterion("industry_name is not null");
            return (Criteria) this;
        }

        public Criteria andIndustryNameEqualTo(String value) {
            addCriterion("industry_name =", value, "industryName");
            return (Criteria) this;
        }

        public Criteria andIndustryNameNotEqualTo(String value) {
            addCriterion("industry_name <>", value, "industryName");
            return (Criteria) this;
        }

        public Criteria andIndustryNameGreaterThan(String value) {
            addCriterion("industry_name >", value, "industryName");
            return (Criteria) this;
        }

        public Criteria andIndustryNameGreaterThanOrEqualTo(String value) {
            addCriterion("industry_name >=", value, "industryName");
            return (Criteria) this;
        }

        public Criteria andIndustryNameLessThan(String value) {
            addCriterion("industry_name <", value, "industryName");
            return (Criteria) this;
        }

        public Criteria andIndustryNameLessThanOrEqualTo(String value) {
            addCriterion("industry_name <=", value, "industryName");
            return (Criteria) this;
        }

        public Criteria andIndustryNameLike(String value) {
            addCriterion("industry_name like", value, "industryName");
            return (Criteria) this;
        }

        public Criteria andIndustryNameNotLike(String value) {
            addCriterion("industry_name not like", value, "industryName");
            return (Criteria) this;
        }

        public Criteria andIndustryNameIn(List<String> values) {
            addCriterion("industry_name in", values, "industryName");
            return (Criteria) this;
        }

        public Criteria andIndustryNameNotIn(List<String> values) {
            addCriterion("industry_name not in", values, "industryName");
            return (Criteria) this;
        }

        public Criteria andIndustryNameBetween(String value1, String value2) {
            addCriterion("industry_name between", value1, value2, "industryName");
            return (Criteria) this;
        }

        public Criteria andIndustryNameNotBetween(String value1, String value2) {
            addCriterion("industry_name not between", value1, value2, "industryName");
            return (Criteria) this;
        }

        public Criteria andSinglePriceIdIsNull() {
            addCriterion("single_price_id is null");
            return (Criteria) this;
        }

        public Criteria andSinglePriceIdIsNotNull() {
            addCriterion("single_price_id is not null");
            return (Criteria) this;
        }

        public Criteria andSinglePriceIdEqualTo(Integer value) {
            addCriterion("single_price_id =", value, "singlePriceId");
            return (Criteria) this;
        }

        public Criteria andSinglePriceIdNotEqualTo(Integer value) {
            addCriterion("single_price_id <>", value, "singlePriceId");
            return (Criteria) this;
        }

        public Criteria andSinglePriceIdGreaterThan(Integer value) {
            addCriterion("single_price_id >", value, "singlePriceId");
            return (Criteria) this;
        }

        public Criteria andSinglePriceIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("single_price_id >=", value, "singlePriceId");
            return (Criteria) this;
        }

        public Criteria andSinglePriceIdLessThan(Integer value) {
            addCriterion("single_price_id <", value, "singlePriceId");
            return (Criteria) this;
        }

        public Criteria andSinglePriceIdLessThanOrEqualTo(Integer value) {
            addCriterion("single_price_id <=", value, "singlePriceId");
            return (Criteria) this;
        }

        public Criteria andSinglePriceIdIn(List<Integer> values) {
            addCriterion("single_price_id in", values, "singlePriceId");
            return (Criteria) this;
        }

        public Criteria andSinglePriceIdNotIn(List<Integer> values) {
            addCriterion("single_price_id not in", values, "singlePriceId");
            return (Criteria) this;
        }

        public Criteria andSinglePriceIdBetween(Integer value1, Integer value2) {
            addCriterion("single_price_id between", value1, value2, "singlePriceId");
            return (Criteria) this;
        }

        public Criteria andSinglePriceIdNotBetween(Integer value1, Integer value2) {
            addCriterion("single_price_id not between", value1, value2, "singlePriceId");
            return (Criteria) this;
        }

        public Criteria andSinglePriceIsNull() {
            addCriterion("single_price is null");
            return (Criteria) this;
        }

        public Criteria andSinglePriceIsNotNull() {
            addCriterion("single_price is not null");
            return (Criteria) this;
        }

        public Criteria andSinglePriceEqualTo(String value) {
            addCriterion("single_price =", value, "singlePrice");
            return (Criteria) this;
        }

        public Criteria andSinglePriceNotEqualTo(String value) {
            addCriterion("single_price <>", value, "singlePrice");
            return (Criteria) this;
        }

        public Criteria andSinglePriceGreaterThan(String value) {
            addCriterion("single_price >", value, "singlePrice");
            return (Criteria) this;
        }

        public Criteria andSinglePriceGreaterThanOrEqualTo(String value) {
            addCriterion("single_price >=", value, "singlePrice");
            return (Criteria) this;
        }

        public Criteria andSinglePriceLessThan(String value) {
            addCriterion("single_price <", value, "singlePrice");
            return (Criteria) this;
        }

        public Criteria andSinglePriceLessThanOrEqualTo(String value) {
            addCriterion("single_price <=", value, "singlePrice");
            return (Criteria) this;
        }

        public Criteria andSinglePriceLike(String value) {
            addCriterion("single_price like", value, "singlePrice");
            return (Criteria) this;
        }

        public Criteria andSinglePriceNotLike(String value) {
            addCriterion("single_price not like", value, "singlePrice");
            return (Criteria) this;
        }

        public Criteria andSinglePriceIn(List<String> values) {
            addCriterion("single_price in", values, "singlePrice");
            return (Criteria) this;
        }

        public Criteria andSinglePriceNotIn(List<String> values) {
            addCriterion("single_price not in", values, "singlePrice");
            return (Criteria) this;
        }

        public Criteria andSinglePriceBetween(String value1, String value2) {
            addCriterion("single_price between", value1, value2, "singlePrice");
            return (Criteria) this;
        }

        public Criteria andSinglePriceNotBetween(String value1, String value2) {
            addCriterion("single_price not between", value1, value2, "singlePrice");
            return (Criteria) this;
        }

        public Criteria andStyleIdIsNull() {
            addCriterion("style_id is null");
            return (Criteria) this;
        }

        public Criteria andStyleIdIsNotNull() {
            addCriterion("style_id is not null");
            return (Criteria) this;
        }

        public Criteria andStyleIdEqualTo(Integer value) {
            addCriterion("style_id =", value, "styleId");
            return (Criteria) this;
        }

        public Criteria andStyleIdNotEqualTo(Integer value) {
            addCriterion("style_id <>", value, "styleId");
            return (Criteria) this;
        }

        public Criteria andStyleIdGreaterThan(Integer value) {
            addCriterion("style_id >", value, "styleId");
            return (Criteria) this;
        }

        public Criteria andStyleIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("style_id >=", value, "styleId");
            return (Criteria) this;
        }

        public Criteria andStyleIdLessThan(Integer value) {
            addCriterion("style_id <", value, "styleId");
            return (Criteria) this;
        }

        public Criteria andStyleIdLessThanOrEqualTo(Integer value) {
            addCriterion("style_id <=", value, "styleId");
            return (Criteria) this;
        }

        public Criteria andStyleIdIn(List<Integer> values) {
            addCriterion("style_id in", values, "styleId");
            return (Criteria) this;
        }

        public Criteria andStyleIdNotIn(List<Integer> values) {
            addCriterion("style_id not in", values, "styleId");
            return (Criteria) this;
        }

        public Criteria andStyleIdBetween(Integer value1, Integer value2) {
            addCriterion("style_id between", value1, value2, "styleId");
            return (Criteria) this;
        }

        public Criteria andStyleIdNotBetween(Integer value1, Integer value2) {
            addCriterion("style_id not between", value1, value2, "styleId");
            return (Criteria) this;
        }

        public Criteria andStyleIsNull() {
            addCriterion("style is null");
            return (Criteria) this;
        }

        public Criteria andStyleIsNotNull() {
            addCriterion("style is not null");
            return (Criteria) this;
        }

        public Criteria andStyleEqualTo(String value) {
            addCriterion("style =", value, "style");
            return (Criteria) this;
        }

        public Criteria andStyleNotEqualTo(String value) {
            addCriterion("style <>", value, "style");
            return (Criteria) this;
        }

        public Criteria andStyleGreaterThan(String value) {
            addCriterion("style >", value, "style");
            return (Criteria) this;
        }

        public Criteria andStyleGreaterThanOrEqualTo(String value) {
            addCriterion("style >=", value, "style");
            return (Criteria) this;
        }

        public Criteria andStyleLessThan(String value) {
            addCriterion("style <", value, "style");
            return (Criteria) this;
        }

        public Criteria andStyleLessThanOrEqualTo(String value) {
            addCriterion("style <=", value, "style");
            return (Criteria) this;
        }

        public Criteria andStyleLike(String value) {
            addCriterion("style like", value, "style");
            return (Criteria) this;
        }

        public Criteria andStyleNotLike(String value) {
            addCriterion("style not like", value, "style");
            return (Criteria) this;
        }

        public Criteria andStyleIn(List<String> values) {
            addCriterion("style in", values, "style");
            return (Criteria) this;
        }

        public Criteria andStyleNotIn(List<String> values) {
            addCriterion("style not in", values, "style");
            return (Criteria) this;
        }

        public Criteria andStyleBetween(String value1, String value2) {
            addCriterion("style between", value1, value2, "style");
            return (Criteria) this;
        }

        public Criteria andStyleNotBetween(String value1, String value2) {
            addCriterion("style not between", value1, value2, "style");
            return (Criteria) this;
        }

        public Criteria andPreferenceIdIsNull() {
            addCriterion("preference_id is null");
            return (Criteria) this;
        }

        public Criteria andPreferenceIdIsNotNull() {
            addCriterion("preference_id is not null");
            return (Criteria) this;
        }

        public Criteria andPreferenceIdEqualTo(Integer value) {
            addCriterion("preference_id =", value, "preferenceId");
            return (Criteria) this;
        }

        public Criteria andPreferenceIdNotEqualTo(Integer value) {
            addCriterion("preference_id <>", value, "preferenceId");
            return (Criteria) this;
        }

        public Criteria andPreferenceIdGreaterThan(Integer value) {
            addCriterion("preference_id >", value, "preferenceId");
            return (Criteria) this;
        }

        public Criteria andPreferenceIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("preference_id >=", value, "preferenceId");
            return (Criteria) this;
        }

        public Criteria andPreferenceIdLessThan(Integer value) {
            addCriterion("preference_id <", value, "preferenceId");
            return (Criteria) this;
        }

        public Criteria andPreferenceIdLessThanOrEqualTo(Integer value) {
            addCriterion("preference_id <=", value, "preferenceId");
            return (Criteria) this;
        }

        public Criteria andPreferenceIdIn(List<Integer> values) {
            addCriterion("preference_id in", values, "preferenceId");
            return (Criteria) this;
        }

        public Criteria andPreferenceIdNotIn(List<Integer> values) {
            addCriterion("preference_id not in", values, "preferenceId");
            return (Criteria) this;
        }

        public Criteria andPreferenceIdBetween(Integer value1, Integer value2) {
            addCriterion("preference_id between", value1, value2, "preferenceId");
            return (Criteria) this;
        }

        public Criteria andPreferenceIdNotBetween(Integer value1, Integer value2) {
            addCriterion("preference_id not between", value1, value2, "preferenceId");
            return (Criteria) this;
        }

        public Criteria andPreferenceIsNull() {
            addCriterion("preference is null");
            return (Criteria) this;
        }

        public Criteria andPreferenceIsNotNull() {
            addCriterion("preference is not null");
            return (Criteria) this;
        }

        public Criteria andPreferenceEqualTo(String value) {
            addCriterion("preference =", value, "preference");
            return (Criteria) this;
        }

        public Criteria andPreferenceNotEqualTo(String value) {
            addCriterion("preference <>", value, "preference");
            return (Criteria) this;
        }

        public Criteria andPreferenceGreaterThan(String value) {
            addCriterion("preference >", value, "preference");
            return (Criteria) this;
        }

        public Criteria andPreferenceGreaterThanOrEqualTo(String value) {
            addCriterion("preference >=", value, "preference");
            return (Criteria) this;
        }

        public Criteria andPreferenceLessThan(String value) {
            addCriterion("preference <", value, "preference");
            return (Criteria) this;
        }

        public Criteria andPreferenceLessThanOrEqualTo(String value) {
            addCriterion("preference <=", value, "preference");
            return (Criteria) this;
        }

        public Criteria andPreferenceLike(String value) {
            addCriterion("preference like", value, "preference");
            return (Criteria) this;
        }

        public Criteria andPreferenceNotLike(String value) {
            addCriterion("preference not like", value, "preference");
            return (Criteria) this;
        }

        public Criteria andPreferenceIn(List<String> values) {
            addCriterion("preference in", values, "preference");
            return (Criteria) this;
        }

        public Criteria andPreferenceNotIn(List<String> values) {
            addCriterion("preference not in", values, "preference");
            return (Criteria) this;
        }

        public Criteria andPreferenceBetween(String value1, String value2) {
            addCriterion("preference between", value1, value2, "preference");
            return (Criteria) this;
        }

        public Criteria andPreferenceNotBetween(String value1, String value2) {
            addCriterion("preference not between", value1, value2, "preference");
            return (Criteria) this;
        }

        public Criteria andInvestorCaseIsNull() {
            addCriterion("investor_case is null");
            return (Criteria) this;
        }

        public Criteria andInvestorCaseIsNotNull() {
            addCriterion("investor_case is not null");
            return (Criteria) this;
        }

        public Criteria andInvestorCaseEqualTo(String value) {
            addCriterion("investor_case =", value, "investorCase");
            return (Criteria) this;
        }

        public Criteria andInvestorCaseNotEqualTo(String value) {
            addCriterion("investor_case <>", value, "investorCase");
            return (Criteria) this;
        }

        public Criteria andInvestorCaseGreaterThan(String value) {
            addCriterion("investor_case >", value, "investorCase");
            return (Criteria) this;
        }

        public Criteria andInvestorCaseGreaterThanOrEqualTo(String value) {
            addCriterion("investor_case >=", value, "investorCase");
            return (Criteria) this;
        }

        public Criteria andInvestorCaseLessThan(String value) {
            addCriterion("investor_case <", value, "investorCase");
            return (Criteria) this;
        }

        public Criteria andInvestorCaseLessThanOrEqualTo(String value) {
            addCriterion("investor_case <=", value, "investorCase");
            return (Criteria) this;
        }

        public Criteria andInvestorCaseLike(String value) {
            addCriterion("investor_case like", value, "investorCase");
            return (Criteria) this;
        }

        public Criteria andInvestorCaseNotLike(String value) {
            addCriterion("investor_case not like", value, "investorCase");
            return (Criteria) this;
        }

        public Criteria andInvestorCaseIn(List<String> values) {
            addCriterion("investor_case in", values, "investorCase");
            return (Criteria) this;
        }

        public Criteria andInvestorCaseNotIn(List<String> values) {
            addCriterion("investor_case not in", values, "investorCase");
            return (Criteria) this;
        }

        public Criteria andInvestorCaseBetween(String value1, String value2) {
            addCriterion("investor_case between", value1, value2, "investorCase");
            return (Criteria) this;
        }

        public Criteria andInvestorCaseNotBetween(String value1, String value2) {
            addCriterion("investor_case not between", value1, value2, "investorCase");
            return (Criteria) this;
        }

        public Criteria andRecommenderIsNull() {
            addCriterion("recommender is null");
            return (Criteria) this;
        }

        public Criteria andRecommenderIsNotNull() {
            addCriterion("recommender is not null");
            return (Criteria) this;
        }

        public Criteria andRecommenderEqualTo(String value) {
            addCriterion("recommender =", value, "recommender");
            return (Criteria) this;
        }

        public Criteria andRecommenderNotEqualTo(String value) {
            addCriterion("recommender <>", value, "recommender");
            return (Criteria) this;
        }

        public Criteria andRecommenderGreaterThan(String value) {
            addCriterion("recommender >", value, "recommender");
            return (Criteria) this;
        }

        public Criteria andRecommenderGreaterThanOrEqualTo(String value) {
            addCriterion("recommender >=", value, "recommender");
            return (Criteria) this;
        }

        public Criteria andRecommenderLessThan(String value) {
            addCriterion("recommender <", value, "recommender");
            return (Criteria) this;
        }

        public Criteria andRecommenderLessThanOrEqualTo(String value) {
            addCriterion("recommender <=", value, "recommender");
            return (Criteria) this;
        }

        public Criteria andRecommenderLike(String value) {
            addCriterion("recommender like", value, "recommender");
            return (Criteria) this;
        }

        public Criteria andRecommenderNotLike(String value) {
            addCriterion("recommender not like", value, "recommender");
            return (Criteria) this;
        }

        public Criteria andRecommenderIn(List<String> values) {
            addCriterion("recommender in", values, "recommender");
            return (Criteria) this;
        }

        public Criteria andRecommenderNotIn(List<String> values) {
            addCriterion("recommender not in", values, "recommender");
            return (Criteria) this;
        }

        public Criteria andRecommenderBetween(String value1, String value2) {
            addCriterion("recommender between", value1, value2, "recommender");
            return (Criteria) this;
        }

        public Criteria andRecommenderNotBetween(String value1, String value2) {
            addCriterion("recommender not between", value1, value2, "recommender");
            return (Criteria) this;
        }

        public Criteria andPersonalProfileIsNull() {
            addCriterion("personal_profile is null");
            return (Criteria) this;
        }

        public Criteria andPersonalProfileIsNotNull() {
            addCriterion("personal_profile is not null");
            return (Criteria) this;
        }

        public Criteria andPersonalProfileEqualTo(String value) {
            addCriterion("personal_profile =", value, "personalProfile");
            return (Criteria) this;
        }

        public Criteria andPersonalProfileNotEqualTo(String value) {
            addCriterion("personal_profile <>", value, "personalProfile");
            return (Criteria) this;
        }

        public Criteria andPersonalProfileGreaterThan(String value) {
            addCriterion("personal_profile >", value, "personalProfile");
            return (Criteria) this;
        }

        public Criteria andPersonalProfileGreaterThanOrEqualTo(String value) {
            addCriterion("personal_profile >=", value, "personalProfile");
            return (Criteria) this;
        }

        public Criteria andPersonalProfileLessThan(String value) {
            addCriterion("personal_profile <", value, "personalProfile");
            return (Criteria) this;
        }

        public Criteria andPersonalProfileLessThanOrEqualTo(String value) {
            addCriterion("personal_profile <=", value, "personalProfile");
            return (Criteria) this;
        }

        public Criteria andPersonalProfileLike(String value) {
            addCriterion("personal_profile like", value, "personalProfile");
            return (Criteria) this;
        }

        public Criteria andPersonalProfileNotLike(String value) {
            addCriterion("personal_profile not like", value, "personalProfile");
            return (Criteria) this;
        }

        public Criteria andPersonalProfileIn(List<String> values) {
            addCriterion("personal_profile in", values, "personalProfile");
            return (Criteria) this;
        }

        public Criteria andPersonalProfileNotIn(List<String> values) {
            addCriterion("personal_profile not in", values, "personalProfile");
            return (Criteria) this;
        }

        public Criteria andPersonalProfileBetween(String value1, String value2) {
            addCriterion("personal_profile between", value1, value2, "personalProfile");
            return (Criteria) this;
        }

        public Criteria andPersonalProfileNotBetween(String value1, String value2) {
            addCriterion("personal_profile not between", value1, value2, "personalProfile");
            return (Criteria) this;
        }

        public Criteria andMobileIsNull() {
            addCriterion("mobile is null");
            return (Criteria) this;
        }

        public Criteria andMobileIsNotNull() {
            addCriterion("mobile is not null");
            return (Criteria) this;
        }

        public Criteria andMobileEqualTo(String value) {
            addCriterion("mobile =", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotEqualTo(String value) {
            addCriterion("mobile <>", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileGreaterThan(String value) {
            addCriterion("mobile >", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileGreaterThanOrEqualTo(String value) {
            addCriterion("mobile >=", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileLessThan(String value) {
            addCriterion("mobile <", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileLessThanOrEqualTo(String value) {
            addCriterion("mobile <=", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileLike(String value) {
            addCriterion("mobile like", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotLike(String value) {
            addCriterion("mobile not like", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileIn(List<String> values) {
            addCriterion("mobile in", values, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotIn(List<String> values) {
            addCriterion("mobile not in", values, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileBetween(String value1, String value2) {
            addCriterion("mobile between", value1, value2, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotBetween(String value1, String value2) {
            addCriterion("mobile not between", value1, value2, "mobile");
            return (Criteria) this;
        }

        public Criteria andEmailIsNull() {
            addCriterion("email is null");
            return (Criteria) this;
        }

        public Criteria andEmailIsNotNull() {
            addCriterion("email is not null");
            return (Criteria) this;
        }

        public Criteria andEmailEqualTo(String value) {
            addCriterion("email =", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotEqualTo(String value) {
            addCriterion("email <>", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThan(String value) {
            addCriterion("email >", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThanOrEqualTo(String value) {
            addCriterion("email >=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThan(String value) {
            addCriterion("email <", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThanOrEqualTo(String value) {
            addCriterion("email <=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLike(String value) {
            addCriterion("email like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotLike(String value) {
            addCriterion("email not like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailIn(List<String> values) {
            addCriterion("email in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotIn(List<String> values) {
            addCriterion("email not in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailBetween(String value1, String value2) {
            addCriterion("email between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotBetween(String value1, String value2) {
            addCriterion("email not between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andIdCardIsNull() {
            addCriterion("id_card is null");
            return (Criteria) this;
        }

        public Criteria andIdCardIsNotNull() {
            addCriterion("id_card is not null");
            return (Criteria) this;
        }

        public Criteria andIdCardEqualTo(String value) {
            addCriterion("id_card =", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardNotEqualTo(String value) {
            addCriterion("id_card <>", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardGreaterThan(String value) {
            addCriterion("id_card >", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardGreaterThanOrEqualTo(String value) {
            addCriterion("id_card >=", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardLessThan(String value) {
            addCriterion("id_card <", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardLessThanOrEqualTo(String value) {
            addCriterion("id_card <=", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardLike(String value) {
            addCriterion("id_card like", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardNotLike(String value) {
            addCriterion("id_card not like", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardIn(List<String> values) {
            addCriterion("id_card in", values, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardNotIn(List<String> values) {
            addCriterion("id_card not in", values, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardBetween(String value1, String value2) {
            addCriterion("id_card between", value1, value2, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardNotBetween(String value1, String value2) {
            addCriterion("id_card not between", value1, value2, "idCard");
            return (Criteria) this;
        }

        public Criteria andBusinessCardPositiveIsNull() {
            addCriterion("business_card_positive is null");
            return (Criteria) this;
        }

        public Criteria andBusinessCardPositiveIsNotNull() {
            addCriterion("business_card_positive is not null");
            return (Criteria) this;
        }

        public Criteria andBusinessCardPositiveEqualTo(String value) {
            addCriterion("business_card_positive =", value, "businessCardPositive");
            return (Criteria) this;
        }

        public Criteria andBusinessCardPositiveNotEqualTo(String value) {
            addCriterion("business_card_positive <>", value, "businessCardPositive");
            return (Criteria) this;
        }

        public Criteria andBusinessCardPositiveGreaterThan(String value) {
            addCriterion("business_card_positive >", value, "businessCardPositive");
            return (Criteria) this;
        }

        public Criteria andBusinessCardPositiveGreaterThanOrEqualTo(String value) {
            addCriterion("business_card_positive >=", value, "businessCardPositive");
            return (Criteria) this;
        }

        public Criteria andBusinessCardPositiveLessThan(String value) {
            addCriterion("business_card_positive <", value, "businessCardPositive");
            return (Criteria) this;
        }

        public Criteria andBusinessCardPositiveLessThanOrEqualTo(String value) {
            addCriterion("business_card_positive <=", value, "businessCardPositive");
            return (Criteria) this;
        }

        public Criteria andBusinessCardPositiveLike(String value) {
            addCriterion("business_card_positive like", value, "businessCardPositive");
            return (Criteria) this;
        }

        public Criteria andBusinessCardPositiveNotLike(String value) {
            addCriterion("business_card_positive not like", value, "businessCardPositive");
            return (Criteria) this;
        }

        public Criteria andBusinessCardPositiveIn(List<String> values) {
            addCriterion("business_card_positive in", values, "businessCardPositive");
            return (Criteria) this;
        }

        public Criteria andBusinessCardPositiveNotIn(List<String> values) {
            addCriterion("business_card_positive not in", values, "businessCardPositive");
            return (Criteria) this;
        }

        public Criteria andBusinessCardPositiveBetween(String value1, String value2) {
            addCriterion("business_card_positive between", value1, value2, "businessCardPositive");
            return (Criteria) this;
        }

        public Criteria andBusinessCardPositiveNotBetween(String value1, String value2) {
            addCriterion("business_card_positive not between", value1, value2, "businessCardPositive");
            return (Criteria) this;
        }

        public Criteria andBusinessCardOppositeIsNull() {
            addCriterion("business_card_opposite is null");
            return (Criteria) this;
        }

        public Criteria andBusinessCardOppositeIsNotNull() {
            addCriterion("business_card_opposite is not null");
            return (Criteria) this;
        }

        public Criteria andBusinessCardOppositeEqualTo(String value) {
            addCriterion("business_card_opposite =", value, "businessCardOpposite");
            return (Criteria) this;
        }

        public Criteria andBusinessCardOppositeNotEqualTo(String value) {
            addCriterion("business_card_opposite <>", value, "businessCardOpposite");
            return (Criteria) this;
        }

        public Criteria andBusinessCardOppositeGreaterThan(String value) {
            addCriterion("business_card_opposite >", value, "businessCardOpposite");
            return (Criteria) this;
        }

        public Criteria andBusinessCardOppositeGreaterThanOrEqualTo(String value) {
            addCriterion("business_card_opposite >=", value, "businessCardOpposite");
            return (Criteria) this;
        }

        public Criteria andBusinessCardOppositeLessThan(String value) {
            addCriterion("business_card_opposite <", value, "businessCardOpposite");
            return (Criteria) this;
        }

        public Criteria andBusinessCardOppositeLessThanOrEqualTo(String value) {
            addCriterion("business_card_opposite <=", value, "businessCardOpposite");
            return (Criteria) this;
        }

        public Criteria andBusinessCardOppositeLike(String value) {
            addCriterion("business_card_opposite like", value, "businessCardOpposite");
            return (Criteria) this;
        }

        public Criteria andBusinessCardOppositeNotLike(String value) {
            addCriterion("business_card_opposite not like", value, "businessCardOpposite");
            return (Criteria) this;
        }

        public Criteria andBusinessCardOppositeIn(List<String> values) {
            addCriterion("business_card_opposite in", values, "businessCardOpposite");
            return (Criteria) this;
        }

        public Criteria andBusinessCardOppositeNotIn(List<String> values) {
            addCriterion("business_card_opposite not in", values, "businessCardOpposite");
            return (Criteria) this;
        }

        public Criteria andBusinessCardOppositeBetween(String value1, String value2) {
            addCriterion("business_card_opposite between", value1, value2, "businessCardOpposite");
            return (Criteria) this;
        }

        public Criteria andBusinessCardOppositeNotBetween(String value1, String value2) {
            addCriterion("business_card_opposite not between", value1, value2, "businessCardOpposite");
            return (Criteria) this;
        }

        public Criteria andPersonalAssetsIdIsNull() {
            addCriterion("personal_assets_id is null");
            return (Criteria) this;
        }

        public Criteria andPersonalAssetsIdIsNotNull() {
            addCriterion("personal_assets_id is not null");
            return (Criteria) this;
        }

        public Criteria andPersonalAssetsIdEqualTo(Integer value) {
            addCriterion("personal_assets_id =", value, "personalAssetsId");
            return (Criteria) this;
        }

        public Criteria andPersonalAssetsIdNotEqualTo(Integer value) {
            addCriterion("personal_assets_id <>", value, "personalAssetsId");
            return (Criteria) this;
        }

        public Criteria andPersonalAssetsIdGreaterThan(Integer value) {
            addCriterion("personal_assets_id >", value, "personalAssetsId");
            return (Criteria) this;
        }

        public Criteria andPersonalAssetsIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("personal_assets_id >=", value, "personalAssetsId");
            return (Criteria) this;
        }

        public Criteria andPersonalAssetsIdLessThan(Integer value) {
            addCriterion("personal_assets_id <", value, "personalAssetsId");
            return (Criteria) this;
        }

        public Criteria andPersonalAssetsIdLessThanOrEqualTo(Integer value) {
            addCriterion("personal_assets_id <=", value, "personalAssetsId");
            return (Criteria) this;
        }

        public Criteria andPersonalAssetsIdIn(List<Integer> values) {
            addCriterion("personal_assets_id in", values, "personalAssetsId");
            return (Criteria) this;
        }

        public Criteria andPersonalAssetsIdNotIn(List<Integer> values) {
            addCriterion("personal_assets_id not in", values, "personalAssetsId");
            return (Criteria) this;
        }

        public Criteria andPersonalAssetsIdBetween(Integer value1, Integer value2) {
            addCriterion("personal_assets_id between", value1, value2, "personalAssetsId");
            return (Criteria) this;
        }

        public Criteria andPersonalAssetsIdNotBetween(Integer value1, Integer value2) {
            addCriterion("personal_assets_id not between", value1, value2, "personalAssetsId");
            return (Criteria) this;
        }

        public Criteria andPersonalAssetsIsNull() {
            addCriterion("personal_assets is null");
            return (Criteria) this;
        }

        public Criteria andPersonalAssetsIsNotNull() {
            addCriterion("personal_assets is not null");
            return (Criteria) this;
        }

        public Criteria andPersonalAssetsEqualTo(String value) {
            addCriterion("personal_assets =", value, "personalAssets");
            return (Criteria) this;
        }

        public Criteria andPersonalAssetsNotEqualTo(String value) {
            addCriterion("personal_assets <>", value, "personalAssets");
            return (Criteria) this;
        }

        public Criteria andPersonalAssetsGreaterThan(String value) {
            addCriterion("personal_assets >", value, "personalAssets");
            return (Criteria) this;
        }

        public Criteria andPersonalAssetsGreaterThanOrEqualTo(String value) {
            addCriterion("personal_assets >=", value, "personalAssets");
            return (Criteria) this;
        }

        public Criteria andPersonalAssetsLessThan(String value) {
            addCriterion("personal_assets <", value, "personalAssets");
            return (Criteria) this;
        }

        public Criteria andPersonalAssetsLessThanOrEqualTo(String value) {
            addCriterion("personal_assets <=", value, "personalAssets");
            return (Criteria) this;
        }

        public Criteria andPersonalAssetsLike(String value) {
            addCriterion("personal_assets like", value, "personalAssets");
            return (Criteria) this;
        }

        public Criteria andPersonalAssetsNotLike(String value) {
            addCriterion("personal_assets not like", value, "personalAssets");
            return (Criteria) this;
        }

        public Criteria andPersonalAssetsIn(List<String> values) {
            addCriterion("personal_assets in", values, "personalAssets");
            return (Criteria) this;
        }

        public Criteria andPersonalAssetsNotIn(List<String> values) {
            addCriterion("personal_assets not in", values, "personalAssets");
            return (Criteria) this;
        }

        public Criteria andPersonalAssetsBetween(String value1, String value2) {
            addCriterion("personal_assets between", value1, value2, "personalAssets");
            return (Criteria) this;
        }

        public Criteria andPersonalAssetsNotBetween(String value1, String value2) {
            addCriterion("personal_assets not between", value1, value2, "personalAssets");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andAuthStatusIsNull() {
            addCriterion("auth_status is null");
            return (Criteria) this;
        }

        public Criteria andAuthStatusIsNotNull() {
            addCriterion("auth_status is not null");
            return (Criteria) this;
        }

        public Criteria andAuthStatusEqualTo(Integer value) {
            addCriterion("auth_status =", value, "authStatus");
            return (Criteria) this;
        }

        public Criteria andAuthStatusNotEqualTo(Integer value) {
            addCriterion("auth_status <>", value, "authStatus");
            return (Criteria) this;
        }

        public Criteria andAuthStatusGreaterThan(Integer value) {
            addCriterion("auth_status >", value, "authStatus");
            return (Criteria) this;
        }

        public Criteria andAuthStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("auth_status >=", value, "authStatus");
            return (Criteria) this;
        }

        public Criteria andAuthStatusLessThan(Integer value) {
            addCriterion("auth_status <", value, "authStatus");
            return (Criteria) this;
        }

        public Criteria andAuthStatusLessThanOrEqualTo(Integer value) {
            addCriterion("auth_status <=", value, "authStatus");
            return (Criteria) this;
        }

        public Criteria andAuthStatusIn(List<Integer> values) {
            addCriterion("auth_status in", values, "authStatus");
            return (Criteria) this;
        }

        public Criteria andAuthStatusNotIn(List<Integer> values) {
            addCriterion("auth_status not in", values, "authStatus");
            return (Criteria) this;
        }

        public Criteria andAuthStatusBetween(Integer value1, Integer value2) {
            addCriterion("auth_status between", value1, value2, "authStatus");
            return (Criteria) this;
        }

        public Criteria andAuthStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("auth_status not between", value1, value2, "authStatus");
            return (Criteria) this;
        }

        public Criteria andIsSignIsNull() {
            addCriterion("is_sign is null");
            return (Criteria) this;
        }

        public Criteria andIsSignIsNotNull() {
            addCriterion("is_sign is not null");
            return (Criteria) this;
        }

        public Criteria andIsSignEqualTo(Integer value) {
            addCriterion("is_sign =", value, "isSign");
            return (Criteria) this;
        }

        public Criteria andIsSignNotEqualTo(Integer value) {
            addCriterion("is_sign <>", value, "isSign");
            return (Criteria) this;
        }

        public Criteria andIsSignGreaterThan(Integer value) {
            addCriterion("is_sign >", value, "isSign");
            return (Criteria) this;
        }

        public Criteria andIsSignGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_sign >=", value, "isSign");
            return (Criteria) this;
        }

        public Criteria andIsSignLessThan(Integer value) {
            addCriterion("is_sign <", value, "isSign");
            return (Criteria) this;
        }

        public Criteria andIsSignLessThanOrEqualTo(Integer value) {
            addCriterion("is_sign <=", value, "isSign");
            return (Criteria) this;
        }

        public Criteria andIsSignIn(List<Integer> values) {
            addCriterion("is_sign in", values, "isSign");
            return (Criteria) this;
        }

        public Criteria andIsSignNotIn(List<Integer> values) {
            addCriterion("is_sign not in", values, "isSign");
            return (Criteria) this;
        }

        public Criteria andIsSignBetween(Integer value1, Integer value2) {
            addCriterion("is_sign between", value1, value2, "isSign");
            return (Criteria) this;
        }

        public Criteria andIsSignNotBetween(Integer value1, Integer value2) {
            addCriterion("is_sign not between", value1, value2, "isSign");
            return (Criteria) this;
        }

        public Criteria andIsBestIsNull() {
            addCriterion("is_best is null");
            return (Criteria) this;
        }

        public Criteria andIsBestIsNotNull() {
            addCriterion("is_best is not null");
            return (Criteria) this;
        }

        public Criteria andIsBestEqualTo(Integer value) {
            addCriterion("is_best =", value, "isBest");
            return (Criteria) this;
        }

        public Criteria andIsBestNotEqualTo(Integer value) {
            addCriterion("is_best <>", value, "isBest");
            return (Criteria) this;
        }

        public Criteria andIsBestGreaterThan(Integer value) {
            addCriterion("is_best >", value, "isBest");
            return (Criteria) this;
        }

        public Criteria andIsBestGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_best >=", value, "isBest");
            return (Criteria) this;
        }

        public Criteria andIsBestLessThan(Integer value) {
            addCriterion("is_best <", value, "isBest");
            return (Criteria) this;
        }

        public Criteria andIsBestLessThanOrEqualTo(Integer value) {
            addCriterion("is_best <=", value, "isBest");
            return (Criteria) this;
        }

        public Criteria andIsBestIn(List<Integer> values) {
            addCriterion("is_best in", values, "isBest");
            return (Criteria) this;
        }

        public Criteria andIsBestNotIn(List<Integer> values) {
            addCriterion("is_best not in", values, "isBest");
            return (Criteria) this;
        }

        public Criteria andIsBestBetween(Integer value1, Integer value2) {
            addCriterion("is_best between", value1, value2, "isBest");
            return (Criteria) this;
        }

        public Criteria andIsBestNotBetween(Integer value1, Integer value2) {
            addCriterion("is_best not between", value1, value2, "isBest");
            return (Criteria) this;
        }

        public Criteria andUseridIsNull() {
            addCriterion("userid is null");
            return (Criteria) this;
        }

        public Criteria andUseridIsNotNull() {
            addCriterion("userid is not null");
            return (Criteria) this;
        }

        public Criteria andUseridEqualTo(Integer value) {
            addCriterion("userid =", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotEqualTo(Integer value) {
            addCriterion("userid <>", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridGreaterThan(Integer value) {
            addCriterion("userid >", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridGreaterThanOrEqualTo(Integer value) {
            addCriterion("userid >=", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridLessThan(Integer value) {
            addCriterion("userid <", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridLessThanOrEqualTo(Integer value) {
            addCriterion("userid <=", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridIn(List<Integer> values) {
            addCriterion("userid in", values, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotIn(List<Integer> values) {
            addCriterion("userid not in", values, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridBetween(Integer value1, Integer value2) {
            addCriterion("userid between", value1, value2, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotBetween(Integer value1, Integer value2) {
            addCriterion("userid not between", value1, value2, "userid");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}