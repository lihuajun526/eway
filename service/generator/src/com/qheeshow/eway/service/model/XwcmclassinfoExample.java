package com.qheeshow.eway.service.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class XwcmclassinfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public XwcmclassinfoExample() {
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

        public Criteria andClassinfoidIsNull() {
            addCriterion("CLASSINFOID is null");
            return (Criteria) this;
        }

        public Criteria andClassinfoidIsNotNull() {
            addCriterion("CLASSINFOID is not null");
            return (Criteria) this;
        }

        public Criteria andClassinfoidEqualTo(Integer value) {
            addCriterion("CLASSINFOID =", value, "classinfoid");
            return (Criteria) this;
        }

        public Criteria andClassinfoidNotEqualTo(Integer value) {
            addCriterion("CLASSINFOID <>", value, "classinfoid");
            return (Criteria) this;
        }

        public Criteria andClassinfoidGreaterThan(Integer value) {
            addCriterion("CLASSINFOID >", value, "classinfoid");
            return (Criteria) this;
        }

        public Criteria andClassinfoidGreaterThanOrEqualTo(Integer value) {
            addCriterion("CLASSINFOID >=", value, "classinfoid");
            return (Criteria) this;
        }

        public Criteria andClassinfoidLessThan(Integer value) {
            addCriterion("CLASSINFOID <", value, "classinfoid");
            return (Criteria) this;
        }

        public Criteria andClassinfoidLessThanOrEqualTo(Integer value) {
            addCriterion("CLASSINFOID <=", value, "classinfoid");
            return (Criteria) this;
        }

        public Criteria andClassinfoidIn(List<Integer> values) {
            addCriterion("CLASSINFOID in", values, "classinfoid");
            return (Criteria) this;
        }

        public Criteria andClassinfoidNotIn(List<Integer> values) {
            addCriterion("CLASSINFOID not in", values, "classinfoid");
            return (Criteria) this;
        }

        public Criteria andClassinfoidBetween(Integer value1, Integer value2) {
            addCriterion("CLASSINFOID between", value1, value2, "classinfoid");
            return (Criteria) this;
        }

        public Criteria andClassinfoidNotBetween(Integer value1, Integer value2) {
            addCriterion("CLASSINFOID not between", value1, value2, "classinfoid");
            return (Criteria) this;
        }

        public Criteria andClassorderIsNull() {
            addCriterion("CLASSORDER is null");
            return (Criteria) this;
        }

        public Criteria andClassorderIsNotNull() {
            addCriterion("CLASSORDER is not null");
            return (Criteria) this;
        }

        public Criteria andClassorderEqualTo(Integer value) {
            addCriterion("CLASSORDER =", value, "classorder");
            return (Criteria) this;
        }

        public Criteria andClassorderNotEqualTo(Integer value) {
            addCriterion("CLASSORDER <>", value, "classorder");
            return (Criteria) this;
        }

        public Criteria andClassorderGreaterThan(Integer value) {
            addCriterion("CLASSORDER >", value, "classorder");
            return (Criteria) this;
        }

        public Criteria andClassorderGreaterThanOrEqualTo(Integer value) {
            addCriterion("CLASSORDER >=", value, "classorder");
            return (Criteria) this;
        }

        public Criteria andClassorderLessThan(Integer value) {
            addCriterion("CLASSORDER <", value, "classorder");
            return (Criteria) this;
        }

        public Criteria andClassorderLessThanOrEqualTo(Integer value) {
            addCriterion("CLASSORDER <=", value, "classorder");
            return (Criteria) this;
        }

        public Criteria andClassorderIn(List<Integer> values) {
            addCriterion("CLASSORDER in", values, "classorder");
            return (Criteria) this;
        }

        public Criteria andClassorderNotIn(List<Integer> values) {
            addCriterion("CLASSORDER not in", values, "classorder");
            return (Criteria) this;
        }

        public Criteria andClassorderBetween(Integer value1, Integer value2) {
            addCriterion("CLASSORDER between", value1, value2, "classorder");
            return (Criteria) this;
        }

        public Criteria andClassorderNotBetween(Integer value1, Integer value2) {
            addCriterion("CLASSORDER not between", value1, value2, "classorder");
            return (Criteria) this;
        }

        public Criteria andCnameIsNull() {
            addCriterion("CNAME is null");
            return (Criteria) this;
        }

        public Criteria andCnameIsNotNull() {
            addCriterion("CNAME is not null");
            return (Criteria) this;
        }

        public Criteria andCnameEqualTo(String value) {
            addCriterion("CNAME =", value, "cname");
            return (Criteria) this;
        }

        public Criteria andCnameNotEqualTo(String value) {
            addCriterion("CNAME <>", value, "cname");
            return (Criteria) this;
        }

        public Criteria andCnameGreaterThan(String value) {
            addCriterion("CNAME >", value, "cname");
            return (Criteria) this;
        }

        public Criteria andCnameGreaterThanOrEqualTo(String value) {
            addCriterion("CNAME >=", value, "cname");
            return (Criteria) this;
        }

        public Criteria andCnameLessThan(String value) {
            addCriterion("CNAME <", value, "cname");
            return (Criteria) this;
        }

        public Criteria andCnameLessThanOrEqualTo(String value) {
            addCriterion("CNAME <=", value, "cname");
            return (Criteria) this;
        }

        public Criteria andCnameLike(String value) {
            addCriterion("CNAME like", value, "cname");
            return (Criteria) this;
        }

        public Criteria andCnameNotLike(String value) {
            addCriterion("CNAME not like", value, "cname");
            return (Criteria) this;
        }

        public Criteria andCnameIn(List<String> values) {
            addCriterion("CNAME in", values, "cname");
            return (Criteria) this;
        }

        public Criteria andCnameNotIn(List<String> values) {
            addCriterion("CNAME not in", values, "cname");
            return (Criteria) this;
        }

        public Criteria andCnameBetween(String value1, String value2) {
            addCriterion("CNAME between", value1, value2, "cname");
            return (Criteria) this;
        }

        public Criteria andCnameNotBetween(String value1, String value2) {
            addCriterion("CNAME not between", value1, value2, "cname");
            return (Criteria) this;
        }

        public Criteria andCdescIsNull() {
            addCriterion("CDESC is null");
            return (Criteria) this;
        }

        public Criteria andCdescIsNotNull() {
            addCriterion("CDESC is not null");
            return (Criteria) this;
        }

        public Criteria andCdescEqualTo(String value) {
            addCriterion("CDESC =", value, "cdesc");
            return (Criteria) this;
        }

        public Criteria andCdescNotEqualTo(String value) {
            addCriterion("CDESC <>", value, "cdesc");
            return (Criteria) this;
        }

        public Criteria andCdescGreaterThan(String value) {
            addCriterion("CDESC >", value, "cdesc");
            return (Criteria) this;
        }

        public Criteria andCdescGreaterThanOrEqualTo(String value) {
            addCriterion("CDESC >=", value, "cdesc");
            return (Criteria) this;
        }

        public Criteria andCdescLessThan(String value) {
            addCriterion("CDESC <", value, "cdesc");
            return (Criteria) this;
        }

        public Criteria andCdescLessThanOrEqualTo(String value) {
            addCriterion("CDESC <=", value, "cdesc");
            return (Criteria) this;
        }

        public Criteria andCdescLike(String value) {
            addCriterion("CDESC like", value, "cdesc");
            return (Criteria) this;
        }

        public Criteria andCdescNotLike(String value) {
            addCriterion("CDESC not like", value, "cdesc");
            return (Criteria) this;
        }

        public Criteria andCdescIn(List<String> values) {
            addCriterion("CDESC in", values, "cdesc");
            return (Criteria) this;
        }

        public Criteria andCdescNotIn(List<String> values) {
            addCriterion("CDESC not in", values, "cdesc");
            return (Criteria) this;
        }

        public Criteria andCdescBetween(String value1, String value2) {
            addCriterion("CDESC between", value1, value2, "cdesc");
            return (Criteria) this;
        }

        public Criteria andCdescNotBetween(String value1, String value2) {
            addCriterion("CDESC not between", value1, value2, "cdesc");
            return (Criteria) this;
        }

        public Criteria andParentidIsNull() {
            addCriterion("PARENTID is null");
            return (Criteria) this;
        }

        public Criteria andParentidIsNotNull() {
            addCriterion("PARENTID is not null");
            return (Criteria) this;
        }

        public Criteria andParentidEqualTo(Integer value) {
            addCriterion("PARENTID =", value, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidNotEqualTo(Integer value) {
            addCriterion("PARENTID <>", value, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidGreaterThan(Integer value) {
            addCriterion("PARENTID >", value, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidGreaterThanOrEqualTo(Integer value) {
            addCriterion("PARENTID >=", value, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidLessThan(Integer value) {
            addCriterion("PARENTID <", value, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidLessThanOrEqualTo(Integer value) {
            addCriterion("PARENTID <=", value, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidIn(List<Integer> values) {
            addCriterion("PARENTID in", values, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidNotIn(List<Integer> values) {
            addCriterion("PARENTID not in", values, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidBetween(Integer value1, Integer value2) {
            addCriterion("PARENTID between", value1, value2, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidNotBetween(Integer value1, Integer value2) {
            addCriterion("PARENTID not between", value1, value2, "parentid");
            return (Criteria) this;
        }

        public Criteria andRootidIsNull() {
            addCriterion("ROOTID is null");
            return (Criteria) this;
        }

        public Criteria andRootidIsNotNull() {
            addCriterion("ROOTID is not null");
            return (Criteria) this;
        }

        public Criteria andRootidEqualTo(Integer value) {
            addCriterion("ROOTID =", value, "rootid");
            return (Criteria) this;
        }

        public Criteria andRootidNotEqualTo(Integer value) {
            addCriterion("ROOTID <>", value, "rootid");
            return (Criteria) this;
        }

        public Criteria andRootidGreaterThan(Integer value) {
            addCriterion("ROOTID >", value, "rootid");
            return (Criteria) this;
        }

        public Criteria andRootidGreaterThanOrEqualTo(Integer value) {
            addCriterion("ROOTID >=", value, "rootid");
            return (Criteria) this;
        }

        public Criteria andRootidLessThan(Integer value) {
            addCriterion("ROOTID <", value, "rootid");
            return (Criteria) this;
        }

        public Criteria andRootidLessThanOrEqualTo(Integer value) {
            addCriterion("ROOTID <=", value, "rootid");
            return (Criteria) this;
        }

        public Criteria andRootidIn(List<Integer> values) {
            addCriterion("ROOTID in", values, "rootid");
            return (Criteria) this;
        }

        public Criteria andRootidNotIn(List<Integer> values) {
            addCriterion("ROOTID not in", values, "rootid");
            return (Criteria) this;
        }

        public Criteria andRootidBetween(Integer value1, Integer value2) {
            addCriterion("ROOTID between", value1, value2, "rootid");
            return (Criteria) this;
        }

        public Criteria andRootidNotBetween(Integer value1, Integer value2) {
            addCriterion("ROOTID not between", value1, value2, "rootid");
            return (Criteria) this;
        }

        public Criteria andCruserIsNull() {
            addCriterion("CRUSER is null");
            return (Criteria) this;
        }

        public Criteria andCruserIsNotNull() {
            addCriterion("CRUSER is not null");
            return (Criteria) this;
        }

        public Criteria andCruserEqualTo(String value) {
            addCriterion("CRUSER =", value, "cruser");
            return (Criteria) this;
        }

        public Criteria andCruserNotEqualTo(String value) {
            addCriterion("CRUSER <>", value, "cruser");
            return (Criteria) this;
        }

        public Criteria andCruserGreaterThan(String value) {
            addCriterion("CRUSER >", value, "cruser");
            return (Criteria) this;
        }

        public Criteria andCruserGreaterThanOrEqualTo(String value) {
            addCriterion("CRUSER >=", value, "cruser");
            return (Criteria) this;
        }

        public Criteria andCruserLessThan(String value) {
            addCriterion("CRUSER <", value, "cruser");
            return (Criteria) this;
        }

        public Criteria andCruserLessThanOrEqualTo(String value) {
            addCriterion("CRUSER <=", value, "cruser");
            return (Criteria) this;
        }

        public Criteria andCruserLike(String value) {
            addCriterion("CRUSER like", value, "cruser");
            return (Criteria) this;
        }

        public Criteria andCruserNotLike(String value) {
            addCriterion("CRUSER not like", value, "cruser");
            return (Criteria) this;
        }

        public Criteria andCruserIn(List<String> values) {
            addCriterion("CRUSER in", values, "cruser");
            return (Criteria) this;
        }

        public Criteria andCruserNotIn(List<String> values) {
            addCriterion("CRUSER not in", values, "cruser");
            return (Criteria) this;
        }

        public Criteria andCruserBetween(String value1, String value2) {
            addCriterion("CRUSER between", value1, value2, "cruser");
            return (Criteria) this;
        }

        public Criteria andCruserNotBetween(String value1, String value2) {
            addCriterion("CRUSER not between", value1, value2, "cruser");
            return (Criteria) this;
        }

        public Criteria andCrtimeIsNull() {
            addCriterion("CRTIME is null");
            return (Criteria) this;
        }

        public Criteria andCrtimeIsNotNull() {
            addCriterion("CRTIME is not null");
            return (Criteria) this;
        }

        public Criteria andCrtimeEqualTo(Date value) {
            addCriterion("CRTIME =", value, "crtime");
            return (Criteria) this;
        }

        public Criteria andCrtimeNotEqualTo(Date value) {
            addCriterion("CRTIME <>", value, "crtime");
            return (Criteria) this;
        }

        public Criteria andCrtimeGreaterThan(Date value) {
            addCriterion("CRTIME >", value, "crtime");
            return (Criteria) this;
        }

        public Criteria andCrtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("CRTIME >=", value, "crtime");
            return (Criteria) this;
        }

        public Criteria andCrtimeLessThan(Date value) {
            addCriterion("CRTIME <", value, "crtime");
            return (Criteria) this;
        }

        public Criteria andCrtimeLessThanOrEqualTo(Date value) {
            addCriterion("CRTIME <=", value, "crtime");
            return (Criteria) this;
        }

        public Criteria andCrtimeIn(List<Date> values) {
            addCriterion("CRTIME in", values, "crtime");
            return (Criteria) this;
        }

        public Criteria andCrtimeNotIn(List<Date> values) {
            addCriterion("CRTIME not in", values, "crtime");
            return (Criteria) this;
        }

        public Criteria andCrtimeBetween(Date value1, Date value2) {
            addCriterion("CRTIME between", value1, value2, "crtime");
            return (Criteria) this;
        }

        public Criteria andCrtimeNotBetween(Date value1, Date value2) {
            addCriterion("CRTIME not between", value1, value2, "crtime");
            return (Criteria) this;
        }

        public Criteria andCcodeIsNull() {
            addCriterion("CCODE is null");
            return (Criteria) this;
        }

        public Criteria andCcodeIsNotNull() {
            addCriterion("CCODE is not null");
            return (Criteria) this;
        }

        public Criteria andCcodeEqualTo(String value) {
            addCriterion("CCODE =", value, "ccode");
            return (Criteria) this;
        }

        public Criteria andCcodeNotEqualTo(String value) {
            addCriterion("CCODE <>", value, "ccode");
            return (Criteria) this;
        }

        public Criteria andCcodeGreaterThan(String value) {
            addCriterion("CCODE >", value, "ccode");
            return (Criteria) this;
        }

        public Criteria andCcodeGreaterThanOrEqualTo(String value) {
            addCriterion("CCODE >=", value, "ccode");
            return (Criteria) this;
        }

        public Criteria andCcodeLessThan(String value) {
            addCriterion("CCODE <", value, "ccode");
            return (Criteria) this;
        }

        public Criteria andCcodeLessThanOrEqualTo(String value) {
            addCriterion("CCODE <=", value, "ccode");
            return (Criteria) this;
        }

        public Criteria andCcodeLike(String value) {
            addCriterion("CCODE like", value, "ccode");
            return (Criteria) this;
        }

        public Criteria andCcodeNotLike(String value) {
            addCriterion("CCODE not like", value, "ccode");
            return (Criteria) this;
        }

        public Criteria andCcodeIn(List<String> values) {
            addCriterion("CCODE in", values, "ccode");
            return (Criteria) this;
        }

        public Criteria andCcodeNotIn(List<String> values) {
            addCriterion("CCODE not in", values, "ccode");
            return (Criteria) this;
        }

        public Criteria andCcodeBetween(String value1, String value2) {
            addCriterion("CCODE between", value1, value2, "ccode");
            return (Criteria) this;
        }

        public Criteria andCcodeNotBetween(String value1, String value2) {
            addCriterion("CCODE not between", value1, value2, "ccode");
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