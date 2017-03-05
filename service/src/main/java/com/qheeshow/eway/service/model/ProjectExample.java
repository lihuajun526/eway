package com.qheeshow.eway.service.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProjectExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ProjectExample() {
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

        public Criteria andTitleIsNull() {
            addCriterion("title is null");
            return (Criteria) this;
        }

        public Criteria andTitleIsNotNull() {
            addCriterion("title is not null");
            return (Criteria) this;
        }

        public Criteria andTitleEqualTo(String value) {
            addCriterion("title =", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotEqualTo(String value) {
            addCriterion("title <>", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThan(String value) {
            addCriterion("title >", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThanOrEqualTo(String value) {
            addCriterion("title >=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThan(String value) {
            addCriterion("title <", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThanOrEqualTo(String value) {
            addCriterion("title <=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLike(String value) {
            addCriterion("title like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotLike(String value) {
            addCriterion("title not like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleIn(List<String> values) {
            addCriterion("title in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotIn(List<String> values) {
            addCriterion("title not in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleBetween(String value1, String value2) {
            addCriterion("title between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotBetween(String value1, String value2) {
            addCriterion("title not between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andDemandIsNull() {
            addCriterion("demand is null");
            return (Criteria) this;
        }

        public Criteria andDemandIsNotNull() {
            addCriterion("demand is not null");
            return (Criteria) this;
        }

        public Criteria andDemandEqualTo(String value) {
            addCriterion("demand =", value, "demand");
            return (Criteria) this;
        }

        public Criteria andDemandNotEqualTo(String value) {
            addCriterion("demand <>", value, "demand");
            return (Criteria) this;
        }

        public Criteria andDemandGreaterThan(String value) {
            addCriterion("demand >", value, "demand");
            return (Criteria) this;
        }

        public Criteria andDemandGreaterThanOrEqualTo(String value) {
            addCriterion("demand >=", value, "demand");
            return (Criteria) this;
        }

        public Criteria andDemandLessThan(String value) {
            addCriterion("demand <", value, "demand");
            return (Criteria) this;
        }

        public Criteria andDemandLessThanOrEqualTo(String value) {
            addCriterion("demand <=", value, "demand");
            return (Criteria) this;
        }

        public Criteria andDemandLike(String value) {
            addCriterion("demand like", value, "demand");
            return (Criteria) this;
        }

        public Criteria andDemandNotLike(String value) {
            addCriterion("demand not like", value, "demand");
            return (Criteria) this;
        }

        public Criteria andDemandIn(List<String> values) {
            addCriterion("demand in", values, "demand");
            return (Criteria) this;
        }

        public Criteria andDemandNotIn(List<String> values) {
            addCriterion("demand not in", values, "demand");
            return (Criteria) this;
        }

        public Criteria andDemandBetween(String value1, String value2) {
            addCriterion("demand between", value1, value2, "demand");
            return (Criteria) this;
        }

        public Criteria andDemandNotBetween(String value1, String value2) {
            addCriterion("demand not between", value1, value2, "demand");
            return (Criteria) this;
        }

        public Criteria andHighlightsIsNull() {
            addCriterion("highlights is null");
            return (Criteria) this;
        }

        public Criteria andHighlightsIsNotNull() {
            addCriterion("highlights is not null");
            return (Criteria) this;
        }

        public Criteria andHighlightsEqualTo(String value) {
            addCriterion("highlights =", value, "highlights");
            return (Criteria) this;
        }

        public Criteria andHighlightsNotEqualTo(String value) {
            addCriterion("highlights <>", value, "highlights");
            return (Criteria) this;
        }

        public Criteria andHighlightsGreaterThan(String value) {
            addCriterion("highlights >", value, "highlights");
            return (Criteria) this;
        }

        public Criteria andHighlightsGreaterThanOrEqualTo(String value) {
            addCriterion("highlights >=", value, "highlights");
            return (Criteria) this;
        }

        public Criteria andHighlightsLessThan(String value) {
            addCriterion("highlights <", value, "highlights");
            return (Criteria) this;
        }

        public Criteria andHighlightsLessThanOrEqualTo(String value) {
            addCriterion("highlights <=", value, "highlights");
            return (Criteria) this;
        }

        public Criteria andHighlightsLike(String value) {
            addCriterion("highlights like", value, "highlights");
            return (Criteria) this;
        }

        public Criteria andHighlightsNotLike(String value) {
            addCriterion("highlights not like", value, "highlights");
            return (Criteria) this;
        }

        public Criteria andHighlightsIn(List<String> values) {
            addCriterion("highlights in", values, "highlights");
            return (Criteria) this;
        }

        public Criteria andHighlightsNotIn(List<String> values) {
            addCriterion("highlights not in", values, "highlights");
            return (Criteria) this;
        }

        public Criteria andHighlightsBetween(String value1, String value2) {
            addCriterion("highlights between", value1, value2, "highlights");
            return (Criteria) this;
        }

        public Criteria andHighlightsNotBetween(String value1, String value2) {
            addCriterion("highlights not between", value1, value2, "highlights");
            return (Criteria) this;
        }

        public Criteria andLastOneIsNull() {
            addCriterion("last_one is null");
            return (Criteria) this;
        }

        public Criteria andLastOneIsNotNull() {
            addCriterion("last_one is not null");
            return (Criteria) this;
        }

        public Criteria andLastOneEqualTo(String value) {
            addCriterion("last_one =", value, "lastOne");
            return (Criteria) this;
        }

        public Criteria andLastOneNotEqualTo(String value) {
            addCriterion("last_one <>", value, "lastOne");
            return (Criteria) this;
        }

        public Criteria andLastOneGreaterThan(String value) {
            addCriterion("last_one >", value, "lastOne");
            return (Criteria) this;
        }

        public Criteria andLastOneGreaterThanOrEqualTo(String value) {
            addCriterion("last_one >=", value, "lastOne");
            return (Criteria) this;
        }

        public Criteria andLastOneLessThan(String value) {
            addCriterion("last_one <", value, "lastOne");
            return (Criteria) this;
        }

        public Criteria andLastOneLessThanOrEqualTo(String value) {
            addCriterion("last_one <=", value, "lastOne");
            return (Criteria) this;
        }

        public Criteria andLastOneLike(String value) {
            addCriterion("last_one like", value, "lastOne");
            return (Criteria) this;
        }

        public Criteria andLastOneNotLike(String value) {
            addCriterion("last_one not like", value, "lastOne");
            return (Criteria) this;
        }

        public Criteria andLastOneIn(List<String> values) {
            addCriterion("last_one in", values, "lastOne");
            return (Criteria) this;
        }

        public Criteria andLastOneNotIn(List<String> values) {
            addCriterion("last_one not in", values, "lastOne");
            return (Criteria) this;
        }

        public Criteria andLastOneBetween(String value1, String value2) {
            addCriterion("last_one between", value1, value2, "lastOne");
            return (Criteria) this;
        }

        public Criteria andLastOneNotBetween(String value1, String value2) {
            addCriterion("last_one not between", value1, value2, "lastOne");
            return (Criteria) this;
        }

        public Criteria andLastTwoIsNull() {
            addCriterion("last_two is null");
            return (Criteria) this;
        }

        public Criteria andLastTwoIsNotNull() {
            addCriterion("last_two is not null");
            return (Criteria) this;
        }

        public Criteria andLastTwoEqualTo(String value) {
            addCriterion("last_two =", value, "lastTwo");
            return (Criteria) this;
        }

        public Criteria andLastTwoNotEqualTo(String value) {
            addCriterion("last_two <>", value, "lastTwo");
            return (Criteria) this;
        }

        public Criteria andLastTwoGreaterThan(String value) {
            addCriterion("last_two >", value, "lastTwo");
            return (Criteria) this;
        }

        public Criteria andLastTwoGreaterThanOrEqualTo(String value) {
            addCriterion("last_two >=", value, "lastTwo");
            return (Criteria) this;
        }

        public Criteria andLastTwoLessThan(String value) {
            addCriterion("last_two <", value, "lastTwo");
            return (Criteria) this;
        }

        public Criteria andLastTwoLessThanOrEqualTo(String value) {
            addCriterion("last_two <=", value, "lastTwo");
            return (Criteria) this;
        }

        public Criteria andLastTwoLike(String value) {
            addCriterion("last_two like", value, "lastTwo");
            return (Criteria) this;
        }

        public Criteria andLastTwoNotLike(String value) {
            addCriterion("last_two not like", value, "lastTwo");
            return (Criteria) this;
        }

        public Criteria andLastTwoIn(List<String> values) {
            addCriterion("last_two in", values, "lastTwo");
            return (Criteria) this;
        }

        public Criteria andLastTwoNotIn(List<String> values) {
            addCriterion("last_two not in", values, "lastTwo");
            return (Criteria) this;
        }

        public Criteria andLastTwoBetween(String value1, String value2) {
            addCriterion("last_two between", value1, value2, "lastTwo");
            return (Criteria) this;
        }

        public Criteria andLastTwoNotBetween(String value1, String value2) {
            addCriterion("last_two not between", value1, value2, "lastTwo");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNull() {
            addCriterion("description is null");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNotNull() {
            addCriterion("description is not null");
            return (Criteria) this;
        }

        public Criteria andDescriptionEqualTo(String value) {
            addCriterion("description =", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotEqualTo(String value) {
            addCriterion("description <>", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThan(String value) {
            addCriterion("description >", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("description >=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThan(String value) {
            addCriterion("description <", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThanOrEqualTo(String value) {
            addCriterion("description <=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLike(String value) {
            addCriterion("description like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotLike(String value) {
            addCriterion("description not like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionIn(List<String> values) {
            addCriterion("description in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotIn(List<String> values) {
            addCriterion("description not in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionBetween(String value1, String value2) {
            addCriterion("description between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotBetween(String value1, String value2) {
            addCriterion("description not between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(Integer value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(Integer value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(Integer value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(Integer value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(Integer value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<Integer> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<Integer> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(Integer value1, Integer value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andIndustryIsNull() {
            addCriterion("industry is null");
            return (Criteria) this;
        }

        public Criteria andIndustryIsNotNull() {
            addCriterion("industry is not null");
            return (Criteria) this;
        }

        public Criteria andIndustryEqualTo(Integer value) {
            addCriterion("industry =", value, "industry");
            return (Criteria) this;
        }

        public Criteria andIndustryNotEqualTo(Integer value) {
            addCriterion("industry <>", value, "industry");
            return (Criteria) this;
        }

        public Criteria andIndustryGreaterThan(Integer value) {
            addCriterion("industry >", value, "industry");
            return (Criteria) this;
        }

        public Criteria andIndustryGreaterThanOrEqualTo(Integer value) {
            addCriterion("industry >=", value, "industry");
            return (Criteria) this;
        }

        public Criteria andIndustryLessThan(Integer value) {
            addCriterion("industry <", value, "industry");
            return (Criteria) this;
        }

        public Criteria andIndustryLessThanOrEqualTo(Integer value) {
            addCriterion("industry <=", value, "industry");
            return (Criteria) this;
        }

        public Criteria andIndustryIn(List<Integer> values) {
            addCriterion("industry in", values, "industry");
            return (Criteria) this;
        }

        public Criteria andIndustryNotIn(List<Integer> values) {
            addCriterion("industry not in", values, "industry");
            return (Criteria) this;
        }

        public Criteria andIndustryBetween(Integer value1, Integer value2) {
            addCriterion("industry between", value1, value2, "industry");
            return (Criteria) this;
        }

        public Criteria andIndustryNotBetween(Integer value1, Integer value2) {
            addCriterion("industry not between", value1, value2, "industry");
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

        public Criteria andAreaIsNull() {
            addCriterion("area is null");
            return (Criteria) this;
        }

        public Criteria andAreaIsNotNull() {
            addCriterion("area is not null");
            return (Criteria) this;
        }

        public Criteria andAreaEqualTo(Integer value) {
            addCriterion("area =", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaNotEqualTo(Integer value) {
            addCriterion("area <>", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaGreaterThan(Integer value) {
            addCriterion("area >", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaGreaterThanOrEqualTo(Integer value) {
            addCriterion("area >=", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaLessThan(Integer value) {
            addCriterion("area <", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaLessThanOrEqualTo(Integer value) {
            addCriterion("area <=", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaIn(List<Integer> values) {
            addCriterion("area in", values, "area");
            return (Criteria) this;
        }

        public Criteria andAreaNotIn(List<Integer> values) {
            addCriterion("area not in", values, "area");
            return (Criteria) this;
        }

        public Criteria andAreaBetween(Integer value1, Integer value2) {
            addCriterion("area between", value1, value2, "area");
            return (Criteria) this;
        }

        public Criteria andAreaNotBetween(Integer value1, Integer value2) {
            addCriterion("area not between", value1, value2, "area");
            return (Criteria) this;
        }

        public Criteria andAreaNameIsNull() {
            addCriterion("area_name is null");
            return (Criteria) this;
        }

        public Criteria andAreaNameIsNotNull() {
            addCriterion("area_name is not null");
            return (Criteria) this;
        }

        public Criteria andAreaNameEqualTo(String value) {
            addCriterion("area_name =", value, "areaName");
            return (Criteria) this;
        }

        public Criteria andAreaNameNotEqualTo(String value) {
            addCriterion("area_name <>", value, "areaName");
            return (Criteria) this;
        }

        public Criteria andAreaNameGreaterThan(String value) {
            addCriterion("area_name >", value, "areaName");
            return (Criteria) this;
        }

        public Criteria andAreaNameGreaterThanOrEqualTo(String value) {
            addCriterion("area_name >=", value, "areaName");
            return (Criteria) this;
        }

        public Criteria andAreaNameLessThan(String value) {
            addCriterion("area_name <", value, "areaName");
            return (Criteria) this;
        }

        public Criteria andAreaNameLessThanOrEqualTo(String value) {
            addCriterion("area_name <=", value, "areaName");
            return (Criteria) this;
        }

        public Criteria andAreaNameLike(String value) {
            addCriterion("area_name like", value, "areaName");
            return (Criteria) this;
        }

        public Criteria andAreaNameNotLike(String value) {
            addCriterion("area_name not like", value, "areaName");
            return (Criteria) this;
        }

        public Criteria andAreaNameIn(List<String> values) {
            addCriterion("area_name in", values, "areaName");
            return (Criteria) this;
        }

        public Criteria andAreaNameNotIn(List<String> values) {
            addCriterion("area_name not in", values, "areaName");
            return (Criteria) this;
        }

        public Criteria andAreaNameBetween(String value1, String value2) {
            addCriterion("area_name between", value1, value2, "areaName");
            return (Criteria) this;
        }

        public Criteria andAreaNameNotBetween(String value1, String value2) {
            addCriterion("area_name not between", value1, value2, "areaName");
            return (Criteria) this;
        }

        public Criteria andFinancingLimitIsNull() {
            addCriterion("financing_limit is null");
            return (Criteria) this;
        }

        public Criteria andFinancingLimitIsNotNull() {
            addCriterion("financing_limit is not null");
            return (Criteria) this;
        }

        public Criteria andFinancingLimitEqualTo(Integer value) {
            addCriterion("financing_limit =", value, "financingLimit");
            return (Criteria) this;
        }

        public Criteria andFinancingLimitNotEqualTo(Integer value) {
            addCriterion("financing_limit <>", value, "financingLimit");
            return (Criteria) this;
        }

        public Criteria andFinancingLimitGreaterThan(Integer value) {
            addCriterion("financing_limit >", value, "financingLimit");
            return (Criteria) this;
        }

        public Criteria andFinancingLimitGreaterThanOrEqualTo(Integer value) {
            addCriterion("financing_limit >=", value, "financingLimit");
            return (Criteria) this;
        }

        public Criteria andFinancingLimitLessThan(Integer value) {
            addCriterion("financing_limit <", value, "financingLimit");
            return (Criteria) this;
        }

        public Criteria andFinancingLimitLessThanOrEqualTo(Integer value) {
            addCriterion("financing_limit <=", value, "financingLimit");
            return (Criteria) this;
        }

        public Criteria andFinancingLimitIn(List<Integer> values) {
            addCriterion("financing_limit in", values, "financingLimit");
            return (Criteria) this;
        }

        public Criteria andFinancingLimitNotIn(List<Integer> values) {
            addCriterion("financing_limit not in", values, "financingLimit");
            return (Criteria) this;
        }

        public Criteria andFinancingLimitBetween(Integer value1, Integer value2) {
            addCriterion("financing_limit between", value1, value2, "financingLimit");
            return (Criteria) this;
        }

        public Criteria andFinancingLimitNotBetween(Integer value1, Integer value2) {
            addCriterion("financing_limit not between", value1, value2, "financingLimit");
            return (Criteria) this;
        }

        public Criteria andFinancingLimitNameIsNull() {
            addCriterion("financing_limit_name is null");
            return (Criteria) this;
        }

        public Criteria andFinancingLimitNameIsNotNull() {
            addCriterion("financing_limit_name is not null");
            return (Criteria) this;
        }

        public Criteria andFinancingLimitNameEqualTo(String value) {
            addCriterion("financing_limit_name =", value, "financingLimitName");
            return (Criteria) this;
        }

        public Criteria andFinancingLimitNameNotEqualTo(String value) {
            addCriterion("financing_limit_name <>", value, "financingLimitName");
            return (Criteria) this;
        }

        public Criteria andFinancingLimitNameGreaterThan(String value) {
            addCriterion("financing_limit_name >", value, "financingLimitName");
            return (Criteria) this;
        }

        public Criteria andFinancingLimitNameGreaterThanOrEqualTo(String value) {
            addCriterion("financing_limit_name >=", value, "financingLimitName");
            return (Criteria) this;
        }

        public Criteria andFinancingLimitNameLessThan(String value) {
            addCriterion("financing_limit_name <", value, "financingLimitName");
            return (Criteria) this;
        }

        public Criteria andFinancingLimitNameLessThanOrEqualTo(String value) {
            addCriterion("financing_limit_name <=", value, "financingLimitName");
            return (Criteria) this;
        }

        public Criteria andFinancingLimitNameLike(String value) {
            addCriterion("financing_limit_name like", value, "financingLimitName");
            return (Criteria) this;
        }

        public Criteria andFinancingLimitNameNotLike(String value) {
            addCriterion("financing_limit_name not like", value, "financingLimitName");
            return (Criteria) this;
        }

        public Criteria andFinancingLimitNameIn(List<String> values) {
            addCriterion("financing_limit_name in", values, "financingLimitName");
            return (Criteria) this;
        }

        public Criteria andFinancingLimitNameNotIn(List<String> values) {
            addCriterion("financing_limit_name not in", values, "financingLimitName");
            return (Criteria) this;
        }

        public Criteria andFinancingLimitNameBetween(String value1, String value2) {
            addCriterion("financing_limit_name between", value1, value2, "financingLimitName");
            return (Criteria) this;
        }

        public Criteria andFinancingLimitNameNotBetween(String value1, String value2) {
            addCriterion("financing_limit_name not between", value1, value2, "financingLimitName");
            return (Criteria) this;
        }

        public Criteria andStageIsNull() {
            addCriterion("stage is null");
            return (Criteria) this;
        }

        public Criteria andStageIsNotNull() {
            addCriterion("stage is not null");
            return (Criteria) this;
        }

        public Criteria andStageEqualTo(Integer value) {
            addCriterion("stage =", value, "stage");
            return (Criteria) this;
        }

        public Criteria andStageNotEqualTo(Integer value) {
            addCriterion("stage <>", value, "stage");
            return (Criteria) this;
        }

        public Criteria andStageGreaterThan(Integer value) {
            addCriterion("stage >", value, "stage");
            return (Criteria) this;
        }

        public Criteria andStageGreaterThanOrEqualTo(Integer value) {
            addCriterion("stage >=", value, "stage");
            return (Criteria) this;
        }

        public Criteria andStageLessThan(Integer value) {
            addCriterion("stage <", value, "stage");
            return (Criteria) this;
        }

        public Criteria andStageLessThanOrEqualTo(Integer value) {
            addCriterion("stage <=", value, "stage");
            return (Criteria) this;
        }

        public Criteria andStageIn(List<Integer> values) {
            addCriterion("stage in", values, "stage");
            return (Criteria) this;
        }

        public Criteria andStageNotIn(List<Integer> values) {
            addCriterion("stage not in", values, "stage");
            return (Criteria) this;
        }

        public Criteria andStageBetween(Integer value1, Integer value2) {
            addCriterion("stage between", value1, value2, "stage");
            return (Criteria) this;
        }

        public Criteria andStageNotBetween(Integer value1, Integer value2) {
            addCriterion("stage not between", value1, value2, "stage");
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

        public Criteria andVideoLinkIsNull() {
            addCriterion("video_link is null");
            return (Criteria) this;
        }

        public Criteria andVideoLinkIsNotNull() {
            addCriterion("video_link is not null");
            return (Criteria) this;
        }

        public Criteria andVideoLinkEqualTo(String value) {
            addCriterion("video_link =", value, "videoLink");
            return (Criteria) this;
        }

        public Criteria andVideoLinkNotEqualTo(String value) {
            addCriterion("video_link <>", value, "videoLink");
            return (Criteria) this;
        }

        public Criteria andVideoLinkGreaterThan(String value) {
            addCriterion("video_link >", value, "videoLink");
            return (Criteria) this;
        }

        public Criteria andVideoLinkGreaterThanOrEqualTo(String value) {
            addCriterion("video_link >=", value, "videoLink");
            return (Criteria) this;
        }

        public Criteria andVideoLinkLessThan(String value) {
            addCriterion("video_link <", value, "videoLink");
            return (Criteria) this;
        }

        public Criteria andVideoLinkLessThanOrEqualTo(String value) {
            addCriterion("video_link <=", value, "videoLink");
            return (Criteria) this;
        }

        public Criteria andVideoLinkLike(String value) {
            addCriterion("video_link like", value, "videoLink");
            return (Criteria) this;
        }

        public Criteria andVideoLinkNotLike(String value) {
            addCriterion("video_link not like", value, "videoLink");
            return (Criteria) this;
        }

        public Criteria andVideoLinkIn(List<String> values) {
            addCriterion("video_link in", values, "videoLink");
            return (Criteria) this;
        }

        public Criteria andVideoLinkNotIn(List<String> values) {
            addCriterion("video_link not in", values, "videoLink");
            return (Criteria) this;
        }

        public Criteria andVideoLinkBetween(String value1, String value2) {
            addCriterion("video_link between", value1, value2, "videoLink");
            return (Criteria) this;
        }

        public Criteria andVideoLinkNotBetween(String value1, String value2) {
            addCriterion("video_link not between", value1, value2, "videoLink");
            return (Criteria) this;
        }

        public Criteria andProLinkIsNull() {
            addCriterion("pro_link is null");
            return (Criteria) this;
        }

        public Criteria andProLinkIsNotNull() {
            addCriterion("pro_link is not null");
            return (Criteria) this;
        }

        public Criteria andProLinkEqualTo(String value) {
            addCriterion("pro_link =", value, "proLink");
            return (Criteria) this;
        }

        public Criteria andProLinkNotEqualTo(String value) {
            addCriterion("pro_link <>", value, "proLink");
            return (Criteria) this;
        }

        public Criteria andProLinkGreaterThan(String value) {
            addCriterion("pro_link >", value, "proLink");
            return (Criteria) this;
        }

        public Criteria andProLinkGreaterThanOrEqualTo(String value) {
            addCriterion("pro_link >=", value, "proLink");
            return (Criteria) this;
        }

        public Criteria andProLinkLessThan(String value) {
            addCriterion("pro_link <", value, "proLink");
            return (Criteria) this;
        }

        public Criteria andProLinkLessThanOrEqualTo(String value) {
            addCriterion("pro_link <=", value, "proLink");
            return (Criteria) this;
        }

        public Criteria andProLinkLike(String value) {
            addCriterion("pro_link like", value, "proLink");
            return (Criteria) this;
        }

        public Criteria andProLinkNotLike(String value) {
            addCriterion("pro_link not like", value, "proLink");
            return (Criteria) this;
        }

        public Criteria andProLinkIn(List<String> values) {
            addCriterion("pro_link in", values, "proLink");
            return (Criteria) this;
        }

        public Criteria andProLinkNotIn(List<String> values) {
            addCriterion("pro_link not in", values, "proLink");
            return (Criteria) this;
        }

        public Criteria andProLinkBetween(String value1, String value2) {
            addCriterion("pro_link between", value1, value2, "proLink");
            return (Criteria) this;
        }

        public Criteria andProLinkNotBetween(String value1, String value2) {
            addCriterion("pro_link not between", value1, value2, "proLink");
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

        public Criteria andUsernameIsNull() {
            addCriterion("username is null");
            return (Criteria) this;
        }

        public Criteria andUsernameIsNotNull() {
            addCriterion("username is not null");
            return (Criteria) this;
        }

        public Criteria andUsernameEqualTo(String value) {
            addCriterion("username =", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotEqualTo(String value) {
            addCriterion("username <>", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameGreaterThan(String value) {
            addCriterion("username >", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameGreaterThanOrEqualTo(String value) {
            addCriterion("username >=", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLessThan(String value) {
            addCriterion("username <", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLessThanOrEqualTo(String value) {
            addCriterion("username <=", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLike(String value) {
            addCriterion("username like", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotLike(String value) {
            addCriterion("username not like", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameIn(List<String> values) {
            addCriterion("username in", values, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotIn(List<String> values) {
            addCriterion("username not in", values, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameBetween(String value1, String value2) {
            addCriterion("username between", value1, value2, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotBetween(String value1, String value2) {
            addCriterion("username not between", value1, value2, "username");
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

        public Criteria andLogoIsNull() {
            addCriterion("logo is null");
            return (Criteria) this;
        }

        public Criteria andLogoIsNotNull() {
            addCriterion("logo is not null");
            return (Criteria) this;
        }

        public Criteria andLogoEqualTo(String value) {
            addCriterion("logo =", value, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoNotEqualTo(String value) {
            addCriterion("logo <>", value, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoGreaterThan(String value) {
            addCriterion("logo >", value, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoGreaterThanOrEqualTo(String value) {
            addCriterion("logo >=", value, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoLessThan(String value) {
            addCriterion("logo <", value, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoLessThanOrEqualTo(String value) {
            addCriterion("logo <=", value, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoLike(String value) {
            addCriterion("logo like", value, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoNotLike(String value) {
            addCriterion("logo not like", value, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoIn(List<String> values) {
            addCriterion("logo in", values, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoNotIn(List<String> values) {
            addCriterion("logo not in", values, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoBetween(String value1, String value2) {
            addCriterion("logo between", value1, value2, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoNotBetween(String value1, String value2) {
            addCriterion("logo not between", value1, value2, "logo");
            return (Criteria) this;
        }

        public Criteria andTagsIsNull() {
            addCriterion("tags is null");
            return (Criteria) this;
        }

        public Criteria andTagsIsNotNull() {
            addCriterion("tags is not null");
            return (Criteria) this;
        }

        public Criteria andTagsEqualTo(String value) {
            addCriterion("tags =", value, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsNotEqualTo(String value) {
            addCriterion("tags <>", value, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsGreaterThan(String value) {
            addCriterion("tags >", value, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsGreaterThanOrEqualTo(String value) {
            addCriterion("tags >=", value, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsLessThan(String value) {
            addCriterion("tags <", value, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsLessThanOrEqualTo(String value) {
            addCriterion("tags <=", value, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsLike(String value) {
            addCriterion("tags like", value, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsNotLike(String value) {
            addCriterion("tags not like", value, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsIn(List<String> values) {
            addCriterion("tags in", values, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsNotIn(List<String> values) {
            addCriterion("tags not in", values, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsBetween(String value1, String value2) {
            addCriterion("tags between", value1, value2, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsNotBetween(String value1, String value2) {
            addCriterion("tags not between", value1, value2, "tags");
            return (Criteria) this;
        }

        public Criteria andBpIsNull() {
            addCriterion("bp is null");
            return (Criteria) this;
        }

        public Criteria andBpIsNotNull() {
            addCriterion("bp is not null");
            return (Criteria) this;
        }

        public Criteria andBpEqualTo(String value) {
            addCriterion("bp =", value, "bp");
            return (Criteria) this;
        }

        public Criteria andBpNotEqualTo(String value) {
            addCriterion("bp <>", value, "bp");
            return (Criteria) this;
        }

        public Criteria andBpGreaterThan(String value) {
            addCriterion("bp >", value, "bp");
            return (Criteria) this;
        }

        public Criteria andBpGreaterThanOrEqualTo(String value) {
            addCriterion("bp >=", value, "bp");
            return (Criteria) this;
        }

        public Criteria andBpLessThan(String value) {
            addCriterion("bp <", value, "bp");
            return (Criteria) this;
        }

        public Criteria andBpLessThanOrEqualTo(String value) {
            addCriterion("bp <=", value, "bp");
            return (Criteria) this;
        }

        public Criteria andBpLike(String value) {
            addCriterion("bp like", value, "bp");
            return (Criteria) this;
        }

        public Criteria andBpNotLike(String value) {
            addCriterion("bp not like", value, "bp");
            return (Criteria) this;
        }

        public Criteria andBpIn(List<String> values) {
            addCriterion("bp in", values, "bp");
            return (Criteria) this;
        }

        public Criteria andBpNotIn(List<String> values) {
            addCriterion("bp not in", values, "bp");
            return (Criteria) this;
        }

        public Criteria andBpBetween(String value1, String value2) {
            addCriterion("bp between", value1, value2, "bp");
            return (Criteria) this;
        }

        public Criteria andBpNotBetween(String value1, String value2) {
            addCriterion("bp not between", value1, value2, "bp");
            return (Criteria) this;
        }

        public Criteria andBpNameIsNull() {
            addCriterion("bp_name is null");
            return (Criteria) this;
        }

        public Criteria andBpNameIsNotNull() {
            addCriterion("bp_name is not null");
            return (Criteria) this;
        }

        public Criteria andBpNameEqualTo(String value) {
            addCriterion("bp_name =", value, "bpName");
            return (Criteria) this;
        }

        public Criteria andBpNameNotEqualTo(String value) {
            addCriterion("bp_name <>", value, "bpName");
            return (Criteria) this;
        }

        public Criteria andBpNameGreaterThan(String value) {
            addCriterion("bp_name >", value, "bpName");
            return (Criteria) this;
        }

        public Criteria andBpNameGreaterThanOrEqualTo(String value) {
            addCriterion("bp_name >=", value, "bpName");
            return (Criteria) this;
        }

        public Criteria andBpNameLessThan(String value) {
            addCriterion("bp_name <", value, "bpName");
            return (Criteria) this;
        }

        public Criteria andBpNameLessThanOrEqualTo(String value) {
            addCriterion("bp_name <=", value, "bpName");
            return (Criteria) this;
        }

        public Criteria andBpNameLike(String value) {
            addCriterion("bp_name like", value, "bpName");
            return (Criteria) this;
        }

        public Criteria andBpNameNotLike(String value) {
            addCriterion("bp_name not like", value, "bpName");
            return (Criteria) this;
        }

        public Criteria andBpNameIn(List<String> values) {
            addCriterion("bp_name in", values, "bpName");
            return (Criteria) this;
        }

        public Criteria andBpNameNotIn(List<String> values) {
            addCriterion("bp_name not in", values, "bpName");
            return (Criteria) this;
        }

        public Criteria andBpNameBetween(String value1, String value2) {
            addCriterion("bp_name between", value1, value2, "bpName");
            return (Criteria) this;
        }

        public Criteria andBpNameNotBetween(String value1, String value2) {
            addCriterion("bp_name not between", value1, value2, "bpName");
            return (Criteria) this;
        }

        public Criteria andPercentIsNull() {
            addCriterion("percent is null");
            return (Criteria) this;
        }

        public Criteria andPercentIsNotNull() {
            addCriterion("percent is not null");
            return (Criteria) this;
        }

        public Criteria andPercentEqualTo(Integer value) {
            addCriterion("percent =", value, "percent");
            return (Criteria) this;
        }

        public Criteria andPercentNotEqualTo(Integer value) {
            addCriterion("percent <>", value, "percent");
            return (Criteria) this;
        }

        public Criteria andPercentGreaterThan(Integer value) {
            addCriterion("percent >", value, "percent");
            return (Criteria) this;
        }

        public Criteria andPercentGreaterThanOrEqualTo(Integer value) {
            addCriterion("percent >=", value, "percent");
            return (Criteria) this;
        }

        public Criteria andPercentLessThan(Integer value) {
            addCriterion("percent <", value, "percent");
            return (Criteria) this;
        }

        public Criteria andPercentLessThanOrEqualTo(Integer value) {
            addCriterion("percent <=", value, "percent");
            return (Criteria) this;
        }

        public Criteria andPercentIn(List<Integer> values) {
            addCriterion("percent in", values, "percent");
            return (Criteria) this;
        }

        public Criteria andPercentNotIn(List<Integer> values) {
            addCriterion("percent not in", values, "percent");
            return (Criteria) this;
        }

        public Criteria andPercentBetween(Integer value1, Integer value2) {
            addCriterion("percent between", value1, value2, "percent");
            return (Criteria) this;
        }

        public Criteria andPercentNotBetween(Integer value1, Integer value2) {
            addCriterion("percent not between", value1, value2, "percent");
            return (Criteria) this;
        }

        public Criteria andRefereeIsNull() {
            addCriterion("referee is null");
            return (Criteria) this;
        }

        public Criteria andRefereeIsNotNull() {
            addCriterion("referee is not null");
            return (Criteria) this;
        }

        public Criteria andRefereeEqualTo(String value) {
            addCriterion("referee =", value, "referee");
            return (Criteria) this;
        }

        public Criteria andRefereeNotEqualTo(String value) {
            addCriterion("referee <>", value, "referee");
            return (Criteria) this;
        }

        public Criteria andRefereeGreaterThan(String value) {
            addCriterion("referee >", value, "referee");
            return (Criteria) this;
        }

        public Criteria andRefereeGreaterThanOrEqualTo(String value) {
            addCriterion("referee >=", value, "referee");
            return (Criteria) this;
        }

        public Criteria andRefereeLessThan(String value) {
            addCriterion("referee <", value, "referee");
            return (Criteria) this;
        }

        public Criteria andRefereeLessThanOrEqualTo(String value) {
            addCriterion("referee <=", value, "referee");
            return (Criteria) this;
        }

        public Criteria andRefereeLike(String value) {
            addCriterion("referee like", value, "referee");
            return (Criteria) this;
        }

        public Criteria andRefereeNotLike(String value) {
            addCriterion("referee not like", value, "referee");
            return (Criteria) this;
        }

        public Criteria andRefereeIn(List<String> values) {
            addCriterion("referee in", values, "referee");
            return (Criteria) this;
        }

        public Criteria andRefereeNotIn(List<String> values) {
            addCriterion("referee not in", values, "referee");
            return (Criteria) this;
        }

        public Criteria andRefereeBetween(String value1, String value2) {
            addCriterion("referee between", value1, value2, "referee");
            return (Criteria) this;
        }

        public Criteria andRefereeNotBetween(String value1, String value2) {
            addCriterion("referee not between", value1, value2, "referee");
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