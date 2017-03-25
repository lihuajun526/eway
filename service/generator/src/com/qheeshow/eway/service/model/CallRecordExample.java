package com.qheeshow.eway.service.model;

import java.util.ArrayList;
import java.util.List;

public class CallRecordExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CallRecordExample() {
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

        public Criteria andUniqueIdIsNull() {
            addCriterion("unique_id is null");
            return (Criteria) this;
        }

        public Criteria andUniqueIdIsNotNull() {
            addCriterion("unique_id is not null");
            return (Criteria) this;
        }

        public Criteria andUniqueIdEqualTo(String value) {
            addCriterion("unique_id =", value, "uniqueId");
            return (Criteria) this;
        }

        public Criteria andUniqueIdNotEqualTo(String value) {
            addCriterion("unique_id <>", value, "uniqueId");
            return (Criteria) this;
        }

        public Criteria andUniqueIdGreaterThan(String value) {
            addCriterion("unique_id >", value, "uniqueId");
            return (Criteria) this;
        }

        public Criteria andUniqueIdGreaterThanOrEqualTo(String value) {
            addCriterion("unique_id >=", value, "uniqueId");
            return (Criteria) this;
        }

        public Criteria andUniqueIdLessThan(String value) {
            addCriterion("unique_id <", value, "uniqueId");
            return (Criteria) this;
        }

        public Criteria andUniqueIdLessThanOrEqualTo(String value) {
            addCriterion("unique_id <=", value, "uniqueId");
            return (Criteria) this;
        }

        public Criteria andUniqueIdLike(String value) {
            addCriterion("unique_id like", value, "uniqueId");
            return (Criteria) this;
        }

        public Criteria andUniqueIdNotLike(String value) {
            addCriterion("unique_id not like", value, "uniqueId");
            return (Criteria) this;
        }

        public Criteria andUniqueIdIn(List<String> values) {
            addCriterion("unique_id in", values, "uniqueId");
            return (Criteria) this;
        }

        public Criteria andUniqueIdNotIn(List<String> values) {
            addCriterion("unique_id not in", values, "uniqueId");
            return (Criteria) this;
        }

        public Criteria andUniqueIdBetween(String value1, String value2) {
            addCriterion("unique_id between", value1, value2, "uniqueId");
            return (Criteria) this;
        }

        public Criteria andUniqueIdNotBetween(String value1, String value2) {
            addCriterion("unique_id not between", value1, value2, "uniqueId");
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

        public Criteria andStartTimeIsNull() {
            addCriterion("start_time is null");
            return (Criteria) this;
        }

        public Criteria andStartTimeIsNotNull() {
            addCriterion("start_time is not null");
            return (Criteria) this;
        }

        public Criteria andStartTimeEqualTo(String value) {
            addCriterion("start_time =", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotEqualTo(String value) {
            addCriterion("start_time <>", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThan(String value) {
            addCriterion("start_time >", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThanOrEqualTo(String value) {
            addCriterion("start_time >=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThan(String value) {
            addCriterion("start_time <", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThanOrEqualTo(String value) {
            addCriterion("start_time <=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLike(String value) {
            addCriterion("start_time like", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotLike(String value) {
            addCriterion("start_time not like", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeIn(List<String> values) {
            addCriterion("start_time in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotIn(List<String> values) {
            addCriterion("start_time not in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeBetween(String value1, String value2) {
            addCriterion("start_time between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotBetween(String value1, String value2) {
            addCriterion("start_time not between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andCallidentifierIsNull() {
            addCriterion("callIdentifier is null");
            return (Criteria) this;
        }

        public Criteria andCallidentifierIsNotNull() {
            addCriterion("callIdentifier is not null");
            return (Criteria) this;
        }

        public Criteria andCallidentifierEqualTo(String value) {
            addCriterion("callIdentifier =", value, "callidentifier");
            return (Criteria) this;
        }

        public Criteria andCallidentifierNotEqualTo(String value) {
            addCriterion("callIdentifier <>", value, "callidentifier");
            return (Criteria) this;
        }

        public Criteria andCallidentifierGreaterThan(String value) {
            addCriterion("callIdentifier >", value, "callidentifier");
            return (Criteria) this;
        }

        public Criteria andCallidentifierGreaterThanOrEqualTo(String value) {
            addCriterion("callIdentifier >=", value, "callidentifier");
            return (Criteria) this;
        }

        public Criteria andCallidentifierLessThan(String value) {
            addCriterion("callIdentifier <", value, "callidentifier");
            return (Criteria) this;
        }

        public Criteria andCallidentifierLessThanOrEqualTo(String value) {
            addCriterion("callIdentifier <=", value, "callidentifier");
            return (Criteria) this;
        }

        public Criteria andCallidentifierLike(String value) {
            addCriterion("callIdentifier like", value, "callidentifier");
            return (Criteria) this;
        }

        public Criteria andCallidentifierNotLike(String value) {
            addCriterion("callIdentifier not like", value, "callidentifier");
            return (Criteria) this;
        }

        public Criteria andCallidentifierIn(List<String> values) {
            addCriterion("callIdentifier in", values, "callidentifier");
            return (Criteria) this;
        }

        public Criteria andCallidentifierNotIn(List<String> values) {
            addCriterion("callIdentifier not in", values, "callidentifier");
            return (Criteria) this;
        }

        public Criteria andCallidentifierBetween(String value1, String value2) {
            addCriterion("callIdentifier between", value1, value2, "callidentifier");
            return (Criteria) this;
        }

        public Criteria andCallidentifierNotBetween(String value1, String value2) {
            addCriterion("callIdentifier not between", value1, value2, "callidentifier");
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

        public Criteria andEventIsNull() {
            addCriterion("event is null");
            return (Criteria) this;
        }

        public Criteria andEventIsNotNull() {
            addCriterion("event is not null");
            return (Criteria) this;
        }

        public Criteria andEventEqualTo(String value) {
            addCriterion("event =", value, "event");
            return (Criteria) this;
        }

        public Criteria andEventNotEqualTo(String value) {
            addCriterion("event <>", value, "event");
            return (Criteria) this;
        }

        public Criteria andEventGreaterThan(String value) {
            addCriterion("event >", value, "event");
            return (Criteria) this;
        }

        public Criteria andEventGreaterThanOrEqualTo(String value) {
            addCriterion("event >=", value, "event");
            return (Criteria) this;
        }

        public Criteria andEventLessThan(String value) {
            addCriterion("event <", value, "event");
            return (Criteria) this;
        }

        public Criteria andEventLessThanOrEqualTo(String value) {
            addCriterion("event <=", value, "event");
            return (Criteria) this;
        }

        public Criteria andEventLike(String value) {
            addCriterion("event like", value, "event");
            return (Criteria) this;
        }

        public Criteria andEventNotLike(String value) {
            addCriterion("event not like", value, "event");
            return (Criteria) this;
        }

        public Criteria andEventIn(List<String> values) {
            addCriterion("event in", values, "event");
            return (Criteria) this;
        }

        public Criteria andEventNotIn(List<String> values) {
            addCriterion("event not in", values, "event");
            return (Criteria) this;
        }

        public Criteria andEventBetween(String value1, String value2) {
            addCriterion("event between", value1, value2, "event");
            return (Criteria) this;
        }

        public Criteria andEventNotBetween(String value1, String value2) {
            addCriterion("event not between", value1, value2, "event");
            return (Criteria) this;
        }

        public Criteria andDurationIsNull() {
            addCriterion("duration is null");
            return (Criteria) this;
        }

        public Criteria andDurationIsNotNull() {
            addCriterion("duration is not null");
            return (Criteria) this;
        }

        public Criteria andDurationEqualTo(String value) {
            addCriterion("duration =", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationNotEqualTo(String value) {
            addCriterion("duration <>", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationGreaterThan(String value) {
            addCriterion("duration >", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationGreaterThanOrEqualTo(String value) {
            addCriterion("duration >=", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationLessThan(String value) {
            addCriterion("duration <", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationLessThanOrEqualTo(String value) {
            addCriterion("duration <=", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationLike(String value) {
            addCriterion("duration like", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationNotLike(String value) {
            addCriterion("duration not like", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationIn(List<String> values) {
            addCriterion("duration in", values, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationNotIn(List<String> values) {
            addCriterion("duration not in", values, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationBetween(String value1, String value2) {
            addCriterion("duration between", value1, value2, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationNotBetween(String value1, String value2) {
            addCriterion("duration not between", value1, value2, "duration");
            return (Criteria) this;
        }

        public Criteria andReleaseReasonIsNull() {
            addCriterion("release_reason is null");
            return (Criteria) this;
        }

        public Criteria andReleaseReasonIsNotNull() {
            addCriterion("release_reason is not null");
            return (Criteria) this;
        }

        public Criteria andReleaseReasonEqualTo(String value) {
            addCriterion("release_reason =", value, "releaseReason");
            return (Criteria) this;
        }

        public Criteria andReleaseReasonNotEqualTo(String value) {
            addCriterion("release_reason <>", value, "releaseReason");
            return (Criteria) this;
        }

        public Criteria andReleaseReasonGreaterThan(String value) {
            addCriterion("release_reason >", value, "releaseReason");
            return (Criteria) this;
        }

        public Criteria andReleaseReasonGreaterThanOrEqualTo(String value) {
            addCriterion("release_reason >=", value, "releaseReason");
            return (Criteria) this;
        }

        public Criteria andReleaseReasonLessThan(String value) {
            addCriterion("release_reason <", value, "releaseReason");
            return (Criteria) this;
        }

        public Criteria andReleaseReasonLessThanOrEqualTo(String value) {
            addCriterion("release_reason <=", value, "releaseReason");
            return (Criteria) this;
        }

        public Criteria andReleaseReasonLike(String value) {
            addCriterion("release_reason like", value, "releaseReason");
            return (Criteria) this;
        }

        public Criteria andReleaseReasonNotLike(String value) {
            addCriterion("release_reason not like", value, "releaseReason");
            return (Criteria) this;
        }

        public Criteria andReleaseReasonIn(List<String> values) {
            addCriterion("release_reason in", values, "releaseReason");
            return (Criteria) this;
        }

        public Criteria andReleaseReasonNotIn(List<String> values) {
            addCriterion("release_reason not in", values, "releaseReason");
            return (Criteria) this;
        }

        public Criteria andReleaseReasonBetween(String value1, String value2) {
            addCriterion("release_reason between", value1, value2, "releaseReason");
            return (Criteria) this;
        }

        public Criteria andReleaseReasonNotBetween(String value1, String value2) {
            addCriterion("release_reason not between", value1, value2, "releaseReason");
            return (Criteria) this;
        }

        public Criteria andVirtualNumberIsNull() {
            addCriterion("virtual_number is null");
            return (Criteria) this;
        }

        public Criteria andVirtualNumberIsNotNull() {
            addCriterion("virtual_number is not null");
            return (Criteria) this;
        }

        public Criteria andVirtualNumberEqualTo(String value) {
            addCriterion("virtual_number =", value, "virtualNumber");
            return (Criteria) this;
        }

        public Criteria andVirtualNumberNotEqualTo(String value) {
            addCriterion("virtual_number <>", value, "virtualNumber");
            return (Criteria) this;
        }

        public Criteria andVirtualNumberGreaterThan(String value) {
            addCriterion("virtual_number >", value, "virtualNumber");
            return (Criteria) this;
        }

        public Criteria andVirtualNumberGreaterThanOrEqualTo(String value) {
            addCriterion("virtual_number >=", value, "virtualNumber");
            return (Criteria) this;
        }

        public Criteria andVirtualNumberLessThan(String value) {
            addCriterion("virtual_number <", value, "virtualNumber");
            return (Criteria) this;
        }

        public Criteria andVirtualNumberLessThanOrEqualTo(String value) {
            addCriterion("virtual_number <=", value, "virtualNumber");
            return (Criteria) this;
        }

        public Criteria andVirtualNumberLike(String value) {
            addCriterion("virtual_number like", value, "virtualNumber");
            return (Criteria) this;
        }

        public Criteria andVirtualNumberNotLike(String value) {
            addCriterion("virtual_number not like", value, "virtualNumber");
            return (Criteria) this;
        }

        public Criteria andVirtualNumberIn(List<String> values) {
            addCriterion("virtual_number in", values, "virtualNumber");
            return (Criteria) this;
        }

        public Criteria andVirtualNumberNotIn(List<String> values) {
            addCriterion("virtual_number not in", values, "virtualNumber");
            return (Criteria) this;
        }

        public Criteria andVirtualNumberBetween(String value1, String value2) {
            addCriterion("virtual_number between", value1, value2, "virtualNumber");
            return (Criteria) this;
        }

        public Criteria andVirtualNumberNotBetween(String value1, String value2) {
            addCriterion("virtual_number not between", value1, value2, "virtualNumber");
            return (Criteria) this;
        }

        public Criteria andTimeStampIsNull() {
            addCriterion("time_stamp is null");
            return (Criteria) this;
        }

        public Criteria andTimeStampIsNotNull() {
            addCriterion("time_stamp is not null");
            return (Criteria) this;
        }

        public Criteria andTimeStampEqualTo(String value) {
            addCriterion("time_stamp =", value, "timeStamp");
            return (Criteria) this;
        }

        public Criteria andTimeStampNotEqualTo(String value) {
            addCriterion("time_stamp <>", value, "timeStamp");
            return (Criteria) this;
        }

        public Criteria andTimeStampGreaterThan(String value) {
            addCriterion("time_stamp >", value, "timeStamp");
            return (Criteria) this;
        }

        public Criteria andTimeStampGreaterThanOrEqualTo(String value) {
            addCriterion("time_stamp >=", value, "timeStamp");
            return (Criteria) this;
        }

        public Criteria andTimeStampLessThan(String value) {
            addCriterion("time_stamp <", value, "timeStamp");
            return (Criteria) this;
        }

        public Criteria andTimeStampLessThanOrEqualTo(String value) {
            addCriterion("time_stamp <=", value, "timeStamp");
            return (Criteria) this;
        }

        public Criteria andTimeStampLike(String value) {
            addCriterion("time_stamp like", value, "timeStamp");
            return (Criteria) this;
        }

        public Criteria andTimeStampNotLike(String value) {
            addCriterion("time_stamp not like", value, "timeStamp");
            return (Criteria) this;
        }

        public Criteria andTimeStampIn(List<String> values) {
            addCriterion("time_stamp in", values, "timeStamp");
            return (Criteria) this;
        }

        public Criteria andTimeStampNotIn(List<String> values) {
            addCriterion("time_stamp not in", values, "timeStamp");
            return (Criteria) this;
        }

        public Criteria andTimeStampBetween(String value1, String value2) {
            addCriterion("time_stamp between", value1, value2, "timeStamp");
            return (Criteria) this;
        }

        public Criteria andTimeStampNotBetween(String value1, String value2) {
            addCriterion("time_stamp not between", value1, value2, "timeStamp");
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