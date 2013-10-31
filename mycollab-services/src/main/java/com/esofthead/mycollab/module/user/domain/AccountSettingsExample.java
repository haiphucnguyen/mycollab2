package com.esofthead.mycollab.module.user.domain;

import java.util.ArrayList;
import java.util.List;

public class AccountSettingsExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table s_account_settings
     *
     * @mbggenerated Thu Oct 31 11:24:43 ICT 2013
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table s_account_settings
     *
     * @mbggenerated Thu Oct 31 11:24:43 ICT 2013
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table s_account_settings
     *
     * @mbggenerated Thu Oct 31 11:24:43 ICT 2013
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_account_settings
     *
     * @mbggenerated Thu Oct 31 11:24:43 ICT 2013
     */
    public AccountSettingsExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_account_settings
     *
     * @mbggenerated Thu Oct 31 11:24:43 ICT 2013
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_account_settings
     *
     * @mbggenerated Thu Oct 31 11:24:43 ICT 2013
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_account_settings
     *
     * @mbggenerated Thu Oct 31 11:24:43 ICT 2013
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_account_settings
     *
     * @mbggenerated Thu Oct 31 11:24:43 ICT 2013
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_account_settings
     *
     * @mbggenerated Thu Oct 31 11:24:43 ICT 2013
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_account_settings
     *
     * @mbggenerated Thu Oct 31 11:24:43 ICT 2013
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_account_settings
     *
     * @mbggenerated Thu Oct 31 11:24:43 ICT 2013
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_account_settings
     *
     * @mbggenerated Thu Oct 31 11:24:43 ICT 2013
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
     * This method corresponds to the database table s_account_settings
     *
     * @mbggenerated Thu Oct 31 11:24:43 ICT 2013
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_account_settings
     *
     * @mbggenerated Thu Oct 31 11:24:43 ICT 2013
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table s_account_settings
     *
     * @mbggenerated Thu Oct 31 11:24:43 ICT 2013
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

        public Criteria andSaccountidIsNull() {
            addCriterion("sAccountId is null");
            return (Criteria) this;
        }

        public Criteria andSaccountidIsNotNull() {
            addCriterion("sAccountId is not null");
            return (Criteria) this;
        }

        public Criteria andSaccountidEqualTo(Integer value) {
            addCriterion("sAccountId =", value, "saccountid");
            return (Criteria) this;
        }

        public Criteria andSaccountidNotEqualTo(Integer value) {
            addCriterion("sAccountId <>", value, "saccountid");
            return (Criteria) this;
        }

        public Criteria andSaccountidGreaterThan(Integer value) {
            addCriterion("sAccountId >", value, "saccountid");
            return (Criteria) this;
        }

        public Criteria andSaccountidGreaterThanOrEqualTo(Integer value) {
            addCriterion("sAccountId >=", value, "saccountid");
            return (Criteria) this;
        }

        public Criteria andSaccountidLessThan(Integer value) {
            addCriterion("sAccountId <", value, "saccountid");
            return (Criteria) this;
        }

        public Criteria andSaccountidLessThanOrEqualTo(Integer value) {
            addCriterion("sAccountId <=", value, "saccountid");
            return (Criteria) this;
        }

        public Criteria andSaccountidIn(List<Integer> values) {
            addCriterion("sAccountId in", values, "saccountid");
            return (Criteria) this;
        }

        public Criteria andSaccountidNotIn(List<Integer> values) {
            addCriterion("sAccountId not in", values, "saccountid");
            return (Criteria) this;
        }

        public Criteria andSaccountidBetween(Integer value1, Integer value2) {
            addCriterion("sAccountId between", value1, value2, "saccountid");
            return (Criteria) this;
        }

        public Criteria andSaccountidNotBetween(Integer value1, Integer value2) {
            addCriterion("sAccountId not between", value1, value2, "saccountid");
            return (Criteria) this;
        }

        public Criteria andLogopathIsNull() {
            addCriterion("logoPath is null");
            return (Criteria) this;
        }

        public Criteria andLogopathIsNotNull() {
            addCriterion("logoPath is not null");
            return (Criteria) this;
        }

        public Criteria andLogopathEqualTo(String value) {
            addCriterion("logoPath =", value, "logopath");
            return (Criteria) this;
        }

        public Criteria andLogopathNotEqualTo(String value) {
            addCriterion("logoPath <>", value, "logopath");
            return (Criteria) this;
        }

        public Criteria andLogopathGreaterThan(String value) {
            addCriterion("logoPath >", value, "logopath");
            return (Criteria) this;
        }

        public Criteria andLogopathGreaterThanOrEqualTo(String value) {
            addCriterion("logoPath >=", value, "logopath");
            return (Criteria) this;
        }

        public Criteria andLogopathLessThan(String value) {
            addCriterion("logoPath <", value, "logopath");
            return (Criteria) this;
        }

        public Criteria andLogopathLessThanOrEqualTo(String value) {
            addCriterion("logoPath <=", value, "logopath");
            return (Criteria) this;
        }

        public Criteria andLogopathLike(String value) {
            addCriterion("logoPath like", value, "logopath");
            return (Criteria) this;
        }

        public Criteria andLogopathNotLike(String value) {
            addCriterion("logoPath not like", value, "logopath");
            return (Criteria) this;
        }

        public Criteria andLogopathIn(List<String> values) {
            addCriterion("logoPath in", values, "logopath");
            return (Criteria) this;
        }

        public Criteria andLogopathNotIn(List<String> values) {
            addCriterion("logoPath not in", values, "logopath");
            return (Criteria) this;
        }

        public Criteria andLogopathBetween(String value1, String value2) {
            addCriterion("logoPath between", value1, value2, "logopath");
            return (Criteria) this;
        }

        public Criteria andLogopathNotBetween(String value1, String value2) {
            addCriterion("logoPath not between", value1, value2, "logopath");
            return (Criteria) this;
        }

        public Criteria andDefaulttimezoneIsNull() {
            addCriterion("defaultTimezone is null");
            return (Criteria) this;
        }

        public Criteria andDefaulttimezoneIsNotNull() {
            addCriterion("defaultTimezone is not null");
            return (Criteria) this;
        }

        public Criteria andDefaulttimezoneEqualTo(String value) {
            addCriterion("defaultTimezone =", value, "defaulttimezone");
            return (Criteria) this;
        }

        public Criteria andDefaulttimezoneNotEqualTo(String value) {
            addCriterion("defaultTimezone <>", value, "defaulttimezone");
            return (Criteria) this;
        }

        public Criteria andDefaulttimezoneGreaterThan(String value) {
            addCriterion("defaultTimezone >", value, "defaulttimezone");
            return (Criteria) this;
        }

        public Criteria andDefaulttimezoneGreaterThanOrEqualTo(String value) {
            addCriterion("defaultTimezone >=", value, "defaulttimezone");
            return (Criteria) this;
        }

        public Criteria andDefaulttimezoneLessThan(String value) {
            addCriterion("defaultTimezone <", value, "defaulttimezone");
            return (Criteria) this;
        }

        public Criteria andDefaulttimezoneLessThanOrEqualTo(String value) {
            addCriterion("defaultTimezone <=", value, "defaulttimezone");
            return (Criteria) this;
        }

        public Criteria andDefaulttimezoneLike(String value) {
            addCriterion("defaultTimezone like", value, "defaulttimezone");
            return (Criteria) this;
        }

        public Criteria andDefaulttimezoneNotLike(String value) {
            addCriterion("defaultTimezone not like", value, "defaulttimezone");
            return (Criteria) this;
        }

        public Criteria andDefaulttimezoneIn(List<String> values) {
            addCriterion("defaultTimezone in", values, "defaulttimezone");
            return (Criteria) this;
        }

        public Criteria andDefaulttimezoneNotIn(List<String> values) {
            addCriterion("defaultTimezone not in", values, "defaulttimezone");
            return (Criteria) this;
        }

        public Criteria andDefaulttimezoneBetween(String value1, String value2) {
            addCriterion("defaultTimezone between", value1, value2, "defaulttimezone");
            return (Criteria) this;
        }

        public Criteria andDefaulttimezoneNotBetween(String value1, String value2) {
            addCriterion("defaultTimezone not between", value1, value2, "defaulttimezone");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table s_account_settings
     *
     * @mbggenerated do_not_delete_during_merge Thu Oct 31 11:24:43 ICT 2013
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table s_account_settings
     *
     * @mbggenerated Thu Oct 31 11:24:43 ICT 2013
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