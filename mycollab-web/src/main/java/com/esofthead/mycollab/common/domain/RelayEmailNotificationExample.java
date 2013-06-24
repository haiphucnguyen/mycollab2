package com.esofthead.mycollab.common.domain;

import java.util.ArrayList;
import java.util.List;

public class RelayEmailNotificationExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table s_relay_email_notification
     *
     * @mbggenerated Mon Jun 24 10:16:27 GMT+07:00 2013
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table s_relay_email_notification
     *
     * @mbggenerated Mon Jun 24 10:16:27 GMT+07:00 2013
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table s_relay_email_notification
     *
     * @mbggenerated Mon Jun 24 10:16:27 GMT+07:00 2013
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_relay_email_notification
     *
     * @mbggenerated Mon Jun 24 10:16:27 GMT+07:00 2013
     */
    public RelayEmailNotificationExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_relay_email_notification
     *
     * @mbggenerated Mon Jun 24 10:16:27 GMT+07:00 2013
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_relay_email_notification
     *
     * @mbggenerated Mon Jun 24 10:16:27 GMT+07:00 2013
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_relay_email_notification
     *
     * @mbggenerated Mon Jun 24 10:16:27 GMT+07:00 2013
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_relay_email_notification
     *
     * @mbggenerated Mon Jun 24 10:16:27 GMT+07:00 2013
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_relay_email_notification
     *
     * @mbggenerated Mon Jun 24 10:16:27 GMT+07:00 2013
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_relay_email_notification
     *
     * @mbggenerated Mon Jun 24 10:16:27 GMT+07:00 2013
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_relay_email_notification
     *
     * @mbggenerated Mon Jun 24 10:16:27 GMT+07:00 2013
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_relay_email_notification
     *
     * @mbggenerated Mon Jun 24 10:16:27 GMT+07:00 2013
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
     * This method corresponds to the database table s_relay_email_notification
     *
     * @mbggenerated Mon Jun 24 10:16:27 GMT+07:00 2013
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_relay_email_notification
     *
     * @mbggenerated Mon Jun 24 10:16:27 GMT+07:00 2013
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table s_relay_email_notification
     *
     * @mbggenerated Mon Jun 24 10:16:27 GMT+07:00 2013
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

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(String value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(String value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(String value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(String value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(String value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(String value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLike(String value) {
            addCriterion("type like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotLike(String value) {
            addCriterion("type not like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<String> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<String> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(String value1, String value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(String value1, String value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeidIsNull() {
            addCriterion("typeid is null");
            return (Criteria) this;
        }

        public Criteria andTypeidIsNotNull() {
            addCriterion("typeid is not null");
            return (Criteria) this;
        }

        public Criteria andTypeidEqualTo(Integer value) {
            addCriterion("typeid =", value, "typeid");
            return (Criteria) this;
        }

        public Criteria andTypeidNotEqualTo(Integer value) {
            addCriterion("typeid <>", value, "typeid");
            return (Criteria) this;
        }

        public Criteria andTypeidGreaterThan(Integer value) {
            addCriterion("typeid >", value, "typeid");
            return (Criteria) this;
        }

        public Criteria andTypeidGreaterThanOrEqualTo(Integer value) {
            addCriterion("typeid >=", value, "typeid");
            return (Criteria) this;
        }

        public Criteria andTypeidLessThan(Integer value) {
            addCriterion("typeid <", value, "typeid");
            return (Criteria) this;
        }

        public Criteria andTypeidLessThanOrEqualTo(Integer value) {
            addCriterion("typeid <=", value, "typeid");
            return (Criteria) this;
        }

        public Criteria andTypeidIn(List<Integer> values) {
            addCriterion("typeid in", values, "typeid");
            return (Criteria) this;
        }

        public Criteria andTypeidNotIn(List<Integer> values) {
            addCriterion("typeid not in", values, "typeid");
            return (Criteria) this;
        }

        public Criteria andTypeidBetween(Integer value1, Integer value2) {
            addCriterion("typeid between", value1, value2, "typeid");
            return (Criteria) this;
        }

        public Criteria andTypeidNotBetween(Integer value1, Integer value2) {
            addCriterion("typeid not between", value1, value2, "typeid");
            return (Criteria) this;
        }

        public Criteria andActionIsNull() {
            addCriterion("action is null");
            return (Criteria) this;
        }

        public Criteria andActionIsNotNull() {
            addCriterion("action is not null");
            return (Criteria) this;
        }

        public Criteria andActionEqualTo(String value) {
            addCriterion("action =", value, "action");
            return (Criteria) this;
        }

        public Criteria andActionNotEqualTo(String value) {
            addCriterion("action <>", value, "action");
            return (Criteria) this;
        }

        public Criteria andActionGreaterThan(String value) {
            addCriterion("action >", value, "action");
            return (Criteria) this;
        }

        public Criteria andActionGreaterThanOrEqualTo(String value) {
            addCriterion("action >=", value, "action");
            return (Criteria) this;
        }

        public Criteria andActionLessThan(String value) {
            addCriterion("action <", value, "action");
            return (Criteria) this;
        }

        public Criteria andActionLessThanOrEqualTo(String value) {
            addCriterion("action <=", value, "action");
            return (Criteria) this;
        }

        public Criteria andActionLike(String value) {
            addCriterion("action like", value, "action");
            return (Criteria) this;
        }

        public Criteria andActionNotLike(String value) {
            addCriterion("action not like", value, "action");
            return (Criteria) this;
        }

        public Criteria andActionIn(List<String> values) {
            addCriterion("action in", values, "action");
            return (Criteria) this;
        }

        public Criteria andActionNotIn(List<String> values) {
            addCriterion("action not in", values, "action");
            return (Criteria) this;
        }

        public Criteria andActionBetween(String value1, String value2) {
            addCriterion("action between", value1, value2, "action");
            return (Criteria) this;
        }

        public Criteria andActionNotBetween(String value1, String value2) {
            addCriterion("action not between", value1, value2, "action");
            return (Criteria) this;
        }

        public Criteria andChangebyIsNull() {
            addCriterion("changeBy is null");
            return (Criteria) this;
        }

        public Criteria andChangebyIsNotNull() {
            addCriterion("changeBy is not null");
            return (Criteria) this;
        }

        public Criteria andChangebyEqualTo(String value) {
            addCriterion("changeBy =", value, "changeby");
            return (Criteria) this;
        }

        public Criteria andChangebyNotEqualTo(String value) {
            addCriterion("changeBy <>", value, "changeby");
            return (Criteria) this;
        }

        public Criteria andChangebyGreaterThan(String value) {
            addCriterion("changeBy >", value, "changeby");
            return (Criteria) this;
        }

        public Criteria andChangebyGreaterThanOrEqualTo(String value) {
            addCriterion("changeBy >=", value, "changeby");
            return (Criteria) this;
        }

        public Criteria andChangebyLessThan(String value) {
            addCriterion("changeBy <", value, "changeby");
            return (Criteria) this;
        }

        public Criteria andChangebyLessThanOrEqualTo(String value) {
            addCriterion("changeBy <=", value, "changeby");
            return (Criteria) this;
        }

        public Criteria andChangebyLike(String value) {
            addCriterion("changeBy like", value, "changeby");
            return (Criteria) this;
        }

        public Criteria andChangebyNotLike(String value) {
            addCriterion("changeBy not like", value, "changeby");
            return (Criteria) this;
        }

        public Criteria andChangebyIn(List<String> values) {
            addCriterion("changeBy in", values, "changeby");
            return (Criteria) this;
        }

        public Criteria andChangebyNotIn(List<String> values) {
            addCriterion("changeBy not in", values, "changeby");
            return (Criteria) this;
        }

        public Criteria andChangebyBetween(String value1, String value2) {
            addCriterion("changeBy between", value1, value2, "changeby");
            return (Criteria) this;
        }

        public Criteria andChangebyNotBetween(String value1, String value2) {
            addCriterion("changeBy not between", value1, value2, "changeby");
            return (Criteria) this;
        }

        public Criteria andExtratypeidIsNull() {
            addCriterion("extraTypeId is null");
            return (Criteria) this;
        }

        public Criteria andExtratypeidIsNotNull() {
            addCriterion("extraTypeId is not null");
            return (Criteria) this;
        }

        public Criteria andExtratypeidEqualTo(Integer value) {
            addCriterion("extraTypeId =", value, "extratypeid");
            return (Criteria) this;
        }

        public Criteria andExtratypeidNotEqualTo(Integer value) {
            addCriterion("extraTypeId <>", value, "extratypeid");
            return (Criteria) this;
        }

        public Criteria andExtratypeidGreaterThan(Integer value) {
            addCriterion("extraTypeId >", value, "extratypeid");
            return (Criteria) this;
        }

        public Criteria andExtratypeidGreaterThanOrEqualTo(Integer value) {
            addCriterion("extraTypeId >=", value, "extratypeid");
            return (Criteria) this;
        }

        public Criteria andExtratypeidLessThan(Integer value) {
            addCriterion("extraTypeId <", value, "extratypeid");
            return (Criteria) this;
        }

        public Criteria andExtratypeidLessThanOrEqualTo(Integer value) {
            addCriterion("extraTypeId <=", value, "extratypeid");
            return (Criteria) this;
        }

        public Criteria andExtratypeidIn(List<Integer> values) {
            addCriterion("extraTypeId in", values, "extratypeid");
            return (Criteria) this;
        }

        public Criteria andExtratypeidNotIn(List<Integer> values) {
            addCriterion("extraTypeId not in", values, "extratypeid");
            return (Criteria) this;
        }

        public Criteria andExtratypeidBetween(Integer value1, Integer value2) {
            addCriterion("extraTypeId between", value1, value2, "extratypeid");
            return (Criteria) this;
        }

        public Criteria andExtratypeidNotBetween(Integer value1, Integer value2) {
            addCriterion("extraTypeId not between", value1, value2, "extratypeid");
            return (Criteria) this;
        }

        public Criteria andEmailhandlerbeanIsNull() {
            addCriterion("emailHandlerBean is null");
            return (Criteria) this;
        }

        public Criteria andEmailhandlerbeanIsNotNull() {
            addCriterion("emailHandlerBean is not null");
            return (Criteria) this;
        }

        public Criteria andEmailhandlerbeanEqualTo(String value) {
            addCriterion("emailHandlerBean =", value, "emailhandlerbean");
            return (Criteria) this;
        }

        public Criteria andEmailhandlerbeanNotEqualTo(String value) {
            addCriterion("emailHandlerBean <>", value, "emailhandlerbean");
            return (Criteria) this;
        }

        public Criteria andEmailhandlerbeanGreaterThan(String value) {
            addCriterion("emailHandlerBean >", value, "emailhandlerbean");
            return (Criteria) this;
        }

        public Criteria andEmailhandlerbeanGreaterThanOrEqualTo(String value) {
            addCriterion("emailHandlerBean >=", value, "emailhandlerbean");
            return (Criteria) this;
        }

        public Criteria andEmailhandlerbeanLessThan(String value) {
            addCriterion("emailHandlerBean <", value, "emailhandlerbean");
            return (Criteria) this;
        }

        public Criteria andEmailhandlerbeanLessThanOrEqualTo(String value) {
            addCriterion("emailHandlerBean <=", value, "emailhandlerbean");
            return (Criteria) this;
        }

        public Criteria andEmailhandlerbeanLike(String value) {
            addCriterion("emailHandlerBean like", value, "emailhandlerbean");
            return (Criteria) this;
        }

        public Criteria andEmailhandlerbeanNotLike(String value) {
            addCriterion("emailHandlerBean not like", value, "emailhandlerbean");
            return (Criteria) this;
        }

        public Criteria andEmailhandlerbeanIn(List<String> values) {
            addCriterion("emailHandlerBean in", values, "emailhandlerbean");
            return (Criteria) this;
        }

        public Criteria andEmailhandlerbeanNotIn(List<String> values) {
            addCriterion("emailHandlerBean not in", values, "emailhandlerbean");
            return (Criteria) this;
        }

        public Criteria andEmailhandlerbeanBetween(String value1, String value2) {
            addCriterion("emailHandlerBean between", value1, value2, "emailhandlerbean");
            return (Criteria) this;
        }

        public Criteria andEmailhandlerbeanNotBetween(String value1, String value2) {
            addCriterion("emailHandlerBean not between", value1, value2, "emailhandlerbean");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table s_relay_email_notification
     *
     * @mbggenerated do_not_delete_during_merge Mon Jun 24 10:16:27 GMT+07:00 2013
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table s_relay_email_notification
     *
     * @mbggenerated Mon Jun 24 10:16:27 GMT+07:00 2013
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