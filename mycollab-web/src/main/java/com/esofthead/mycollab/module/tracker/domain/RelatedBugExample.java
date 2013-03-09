package com.esofthead.mycollab.module.tracker.domain;

import java.util.ArrayList;
import java.util.List;

public class RelatedBugExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table m_tracker_related_bug
     *
     * @mbggenerated Sat Mar 09 20:52:03 GMT+07:00 2013
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table m_tracker_related_bug
     *
     * @mbggenerated Sat Mar 09 20:52:03 GMT+07:00 2013
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table m_tracker_related_bug
     *
     * @mbggenerated Sat Mar 09 20:52:03 GMT+07:00 2013
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_related_bug
     *
     * @mbggenerated Sat Mar 09 20:52:03 GMT+07:00 2013
     */
    public RelatedBugExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_related_bug
     *
     * @mbggenerated Sat Mar 09 20:52:03 GMT+07:00 2013
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_related_bug
     *
     * @mbggenerated Sat Mar 09 20:52:03 GMT+07:00 2013
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_related_bug
     *
     * @mbggenerated Sat Mar 09 20:52:03 GMT+07:00 2013
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_related_bug
     *
     * @mbggenerated Sat Mar 09 20:52:03 GMT+07:00 2013
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_related_bug
     *
     * @mbggenerated Sat Mar 09 20:52:03 GMT+07:00 2013
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_related_bug
     *
     * @mbggenerated Sat Mar 09 20:52:03 GMT+07:00 2013
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_related_bug
     *
     * @mbggenerated Sat Mar 09 20:52:03 GMT+07:00 2013
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_related_bug
     *
     * @mbggenerated Sat Mar 09 20:52:03 GMT+07:00 2013
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_related_bug
     *
     * @mbggenerated Sat Mar 09 20:52:03 GMT+07:00 2013
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_related_bug
     *
     * @mbggenerated Sat Mar 09 20:52:03 GMT+07:00 2013
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table m_tracker_related_bug
     *
     * @mbggenerated Sat Mar 09 20:52:03 GMT+07:00 2013
     */
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

        public Criteria andBugidIsNull() {
            addCriterion("bugid is null");
            return (Criteria) this;
        }

        public Criteria andBugidIsNotNull() {
            addCriterion("bugid is not null");
            return (Criteria) this;
        }

        public Criteria andBugidEqualTo(Integer value) {
            addCriterion("bugid =", value, "bugid");
            return (Criteria) this;
        }

        public Criteria andBugidNotEqualTo(Integer value) {
            addCriterion("bugid <>", value, "bugid");
            return (Criteria) this;
        }

        public Criteria andBugidGreaterThan(Integer value) {
            addCriterion("bugid >", value, "bugid");
            return (Criteria) this;
        }

        public Criteria andBugidGreaterThanOrEqualTo(Integer value) {
            addCriterion("bugid >=", value, "bugid");
            return (Criteria) this;
        }

        public Criteria andBugidLessThan(Integer value) {
            addCriterion("bugid <", value, "bugid");
            return (Criteria) this;
        }

        public Criteria andBugidLessThanOrEqualTo(Integer value) {
            addCriterion("bugid <=", value, "bugid");
            return (Criteria) this;
        }

        public Criteria andBugidIn(List<Integer> values) {
            addCriterion("bugid in", values, "bugid");
            return (Criteria) this;
        }

        public Criteria andBugidNotIn(List<Integer> values) {
            addCriterion("bugid not in", values, "bugid");
            return (Criteria) this;
        }

        public Criteria andBugidBetween(Integer value1, Integer value2) {
            addCriterion("bugid between", value1, value2, "bugid");
            return (Criteria) this;
        }

        public Criteria andBugidNotBetween(Integer value1, Integer value2) {
            addCriterion("bugid not between", value1, value2, "bugid");
            return (Criteria) this;
        }

        public Criteria andRelatedidIsNull() {
            addCriterion("relatedid is null");
            return (Criteria) this;
        }

        public Criteria andRelatedidIsNotNull() {
            addCriterion("relatedid is not null");
            return (Criteria) this;
        }

        public Criteria andRelatedidEqualTo(Integer value) {
            addCriterion("relatedid =", value, "relatedid");
            return (Criteria) this;
        }

        public Criteria andRelatedidNotEqualTo(Integer value) {
            addCriterion("relatedid <>", value, "relatedid");
            return (Criteria) this;
        }

        public Criteria andRelatedidGreaterThan(Integer value) {
            addCriterion("relatedid >", value, "relatedid");
            return (Criteria) this;
        }

        public Criteria andRelatedidGreaterThanOrEqualTo(Integer value) {
            addCriterion("relatedid >=", value, "relatedid");
            return (Criteria) this;
        }

        public Criteria andRelatedidLessThan(Integer value) {
            addCriterion("relatedid <", value, "relatedid");
            return (Criteria) this;
        }

        public Criteria andRelatedidLessThanOrEqualTo(Integer value) {
            addCriterion("relatedid <=", value, "relatedid");
            return (Criteria) this;
        }

        public Criteria andRelatedidIn(List<Integer> values) {
            addCriterion("relatedid in", values, "relatedid");
            return (Criteria) this;
        }

        public Criteria andRelatedidNotIn(List<Integer> values) {
            addCriterion("relatedid not in", values, "relatedid");
            return (Criteria) this;
        }

        public Criteria andRelatedidBetween(Integer value1, Integer value2) {
            addCriterion("relatedid between", value1, value2, "relatedid");
            return (Criteria) this;
        }

        public Criteria andRelatedidNotBetween(Integer value1, Integer value2) {
            addCriterion("relatedid not between", value1, value2, "relatedid");
            return (Criteria) this;
        }

        public Criteria andRelatetypeIsNull() {
            addCriterion("relatetype is null");
            return (Criteria) this;
        }

        public Criteria andRelatetypeIsNotNull() {
            addCriterion("relatetype is not null");
            return (Criteria) this;
        }

        public Criteria andRelatetypeEqualTo(Long value) {
            addCriterion("relatetype =", value, "relatetype");
            return (Criteria) this;
        }

        public Criteria andRelatetypeNotEqualTo(Long value) {
            addCriterion("relatetype <>", value, "relatetype");
            return (Criteria) this;
        }

        public Criteria andRelatetypeGreaterThan(Long value) {
            addCriterion("relatetype >", value, "relatetype");
            return (Criteria) this;
        }

        public Criteria andRelatetypeGreaterThanOrEqualTo(Long value) {
            addCriterion("relatetype >=", value, "relatetype");
            return (Criteria) this;
        }

        public Criteria andRelatetypeLessThan(Long value) {
            addCriterion("relatetype <", value, "relatetype");
            return (Criteria) this;
        }

        public Criteria andRelatetypeLessThanOrEqualTo(Long value) {
            addCriterion("relatetype <=", value, "relatetype");
            return (Criteria) this;
        }

        public Criteria andRelatetypeIn(List<Long> values) {
            addCriterion("relatetype in", values, "relatetype");
            return (Criteria) this;
        }

        public Criteria andRelatetypeNotIn(List<Long> values) {
            addCriterion("relatetype not in", values, "relatetype");
            return (Criteria) this;
        }

        public Criteria andRelatetypeBetween(Long value1, Long value2) {
            addCriterion("relatetype between", value1, value2, "relatetype");
            return (Criteria) this;
        }

        public Criteria andRelatetypeNotBetween(Long value1, Long value2) {
            addCriterion("relatetype not between", value1, value2, "relatetype");
            return (Criteria) this;
        }

        public Criteria andCommentIsNull() {
            addCriterion("comment is null");
            return (Criteria) this;
        }

        public Criteria andCommentIsNotNull() {
            addCriterion("comment is not null");
            return (Criteria) this;
        }

        public Criteria andCommentEqualTo(String value) {
            addCriterion("comment =", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentNotEqualTo(String value) {
            addCriterion("comment <>", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentGreaterThan(String value) {
            addCriterion("comment >", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentGreaterThanOrEqualTo(String value) {
            addCriterion("comment >=", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentLessThan(String value) {
            addCriterion("comment <", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentLessThanOrEqualTo(String value) {
            addCriterion("comment <=", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentLike(String value) {
            addCriterion("comment like", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentNotLike(String value) {
            addCriterion("comment not like", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentIn(List<String> values) {
            addCriterion("comment in", values, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentNotIn(List<String> values) {
            addCriterion("comment not in", values, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentBetween(String value1, String value2) {
            addCriterion("comment between", value1, value2, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentNotBetween(String value1, String value2) {
            addCriterion("comment not between", value1, value2, "comment");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table m_tracker_related_bug
     *
     * @mbggenerated do_not_delete_during_merge Sat Mar 09 20:52:03 GMT+07:00 2013
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table m_tracker_related_bug
     *
     * @mbggenerated Sat Mar 09 20:52:03 GMT+07:00 2013
     */
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