package com.qheeshow.eway.service.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BindMapExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BindMapExample() {
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

        public Criteria andBindIdIsNull() {
            addCriterion("bind_id is null");
            return (Criteria) this;
        }

        public Criteria andBindIdIsNotNull() {
            addCriterion("bind_id is not null");
            return (Criteria) this;
        }

        public Criteria andBindIdEqualTo(String value) {
            addCriterion("bind_id =", value, "bindId");
            return (Criteria) this;
        }

        public Criteria andBindIdNotEqualTo(String value) {
            addCriterion("bind_id <>", value, "bindId");
            return (Criteria) this;
        }

        public Criteria andBindIdGreaterThan(String value) {
            addCriterion("bind_id >", value, "bindId");
            return (Criteria) this;
        }

        public Criteria andBindIdGreaterThanOrEqualTo(String value) {
            addCriterion("bind_id >=", value, "bindId");
            return (Criteria) this;
        }

        public Criteria andBindIdLessThan(String value) {
            addCriterion("bind_id <", value, "bindId");
            return (Criteria) this;
        }

        public Criteria andBindIdLessThanOrEqualTo(String value) {
            addCriterion("bind_id <=", value, "bindId");
            return (Criteria) this;
        }

        public Criteria andBindIdLike(String value) {
            addCriterion("bind_id like", value, "bindId");
            return (Criteria) this;
        }

        public Criteria andBindIdNotLike(String value) {
            addCriterion("bind_id not like", value, "bindId");
            return (Criteria) this;
        }

        public Criteria andBindIdIn(List<String> values) {
            addCriterion("bind_id in", values, "bindId");
            return (Criteria) this;
        }

        public Criteria andBindIdNotIn(List<String> values) {
            addCriterion("bind_id not in", values, "bindId");
            return (Criteria) this;
        }

        public Criteria andBindIdBetween(String value1, String value2) {
            addCriterion("bind_id between", value1, value2, "bindId");
            return (Criteria) this;
        }

        public Criteria andBindIdNotBetween(String value1, String value2) {
            addCriterion("bind_id not between", value1, value2, "bindId");
            return (Criteria) this;
        }

        public Criteria andCallingIsNull() {
            addCriterion("calling is null");
            return (Criteria) this;
        }

        public Criteria andCallingIsNotNull() {
            addCriterion("calling is not null");
            return (Criteria) this;
        }

        public Criteria andCallingEqualTo(String value) {
            addCriterion("calling =", value, "calling");
            return (Criteria) this;
        }

        public Criteria andCallingNotEqualTo(String value) {
            addCriterion("calling <>", value, "calling");
            return (Criteria) this;
        }

        public Criteria andCallingGreaterThan(String value) {
            addCriterion("calling >", value, "calling");
            return (Criteria) this;
        }

        public Criteria andCallingGreaterThanOrEqualTo(String value) {
            addCriterion("calling >=", value, "calling");
            return (Criteria) this;
        }

        public Criteria andCallingLessThan(String value) {
            addCriterion("calling <", value, "calling");
            return (Criteria) this;
        }

        public Criteria andCallingLessThanOrEqualTo(String value) {
            addCriterion("calling <=", value, "calling");
            return (Criteria) this;
        }

        public Criteria andCallingLike(String value) {
            addCriterion("calling like", value, "calling");
            return (Criteria) this;
        }

        public Criteria andCallingNotLike(String value) {
            addCriterion("calling not like", value, "calling");
            return (Criteria) this;
        }

        public Criteria andCallingIn(List<String> values) {
            addCriterion("calling in", values, "calling");
            return (Criteria) this;
        }

        public Criteria andCallingNotIn(List<String> values) {
            addCriterion("calling not in", values, "calling");
            return (Criteria) this;
        }

        public Criteria andCallingBetween(String value1, String value2) {
            addCriterion("calling between", value1, value2, "calling");
            return (Criteria) this;
        }

        public Criteria andCallingNotBetween(String value1, String value2) {
            addCriterion("calling not between", value1, value2, "calling");
            return (Criteria) this;
        }

        public Criteria andCalledIsNull() {
            addCriterion("called is null");
            return (Criteria) this;
        }

        public Criteria andCalledIsNotNull() {
            addCriterion("called is not null");
            return (Criteria) this;
        }

        public Criteria andCalledEqualTo(String value) {
            addCriterion("called =", value, "called");
            return (Criteria) this;
        }

        public Criteria andCalledNotEqualTo(String value) {
            addCriterion("called <>", value, "called");
            return (Criteria) this;
        }

        public Criteria andCalledGreaterThan(String value) {
            addCriterion("called >", value, "called");
            return (Criteria) this;
        }

        public Criteria andCalledGreaterThanOrEqualTo(String value) {
            addCriterion("called >=", value, "called");
            return (Criteria) this;
        }

        public Criteria andCalledLessThan(String value) {
            addCriterion("called <", value, "called");
            return (Criteria) this;
        }

        public Criteria andCalledLessThanOrEqualTo(String value) {
            addCriterion("called <=", value, "called");
            return (Criteria) this;
        }

        public Criteria andCalledLike(String value) {
            addCriterion("called like", value, "called");
            return (Criteria) this;
        }

        public Criteria andCalledNotLike(String value) {
            addCriterion("called not like", value, "called");
            return (Criteria) this;
        }

        public Criteria andCalledIn(List<String> values) {
            addCriterion("called in", values, "called");
            return (Criteria) this;
        }

        public Criteria andCalledNotIn(List<String> values) {
            addCriterion("called not in", values, "called");
            return (Criteria) this;
        }

        public Criteria andCalledBetween(String value1, String value2) {
            addCriterion("called between", value1, value2, "called");
            return (Criteria) this;
        }

        public Criteria andCalledNotBetween(String value1, String value2) {
            addCriterion("called not between", value1, value2, "called");
            return (Criteria) this;
        }

        public Criteria andMixnumIsNull() {
            addCriterion("mixnum is null");
            return (Criteria) this;
        }

        public Criteria andMixnumIsNotNull() {
            addCriterion("mixnum is not null");
            return (Criteria) this;
        }

        public Criteria andMixnumEqualTo(String value) {
            addCriterion("mixnum =", value, "mixnum");
            return (Criteria) this;
        }

        public Criteria andMixnumNotEqualTo(String value) {
            addCriterion("mixnum <>", value, "mixnum");
            return (Criteria) this;
        }

        public Criteria andMixnumGreaterThan(String value) {
            addCriterion("mixnum >", value, "mixnum");
            return (Criteria) this;
        }

        public Criteria andMixnumGreaterThanOrEqualTo(String value) {
            addCriterion("mixnum >=", value, "mixnum");
            return (Criteria) this;
        }

        public Criteria andMixnumLessThan(String value) {
            addCriterion("mixnum <", value, "mixnum");
            return (Criteria) this;
        }

        public Criteria andMixnumLessThanOrEqualTo(String value) {
            addCriterion("mixnum <=", value, "mixnum");
            return (Criteria) this;
        }

        public Criteria andMixnumLike(String value) {
            addCriterion("mixnum like", value, "mixnum");
            return (Criteria) this;
        }

        public Criteria andMixnumNotLike(String value) {
            addCriterion("mixnum not like", value, "mixnum");
            return (Criteria) this;
        }

        public Criteria andMixnumIn(List<String> values) {
            addCriterion("mixnum in", values, "mixnum");
            return (Criteria) this;
        }

        public Criteria andMixnumNotIn(List<String> values) {
            addCriterion("mixnum not in", values, "mixnum");
            return (Criteria) this;
        }

        public Criteria andMixnumBetween(String value1, String value2) {
            addCriterion("mixnum between", value1, value2, "mixnum");
            return (Criteria) this;
        }

        public Criteria andMixnumNotBetween(String value1, String value2) {
            addCriterion("mixnum not between", value1, value2, "mixnum");
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