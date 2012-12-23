package com.esofthead.mycollab.module.user.domain;

import java.util.ArrayList;
import java.util.List;

public class RolePermissionExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table s_role_permission
     *
     * @mbggenerated Sun Dec 23 17:29:19 GMT+07:00 2012
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table s_role_permission
     *
     * @mbggenerated Sun Dec 23 17:29:19 GMT+07:00 2012
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table s_role_permission
     *
     * @mbggenerated Sun Dec 23 17:29:19 GMT+07:00 2012
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_role_permission
     *
     * @mbggenerated Sun Dec 23 17:29:19 GMT+07:00 2012
     */
    public RolePermissionExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_role_permission
     *
     * @mbggenerated Sun Dec 23 17:29:19 GMT+07:00 2012
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_role_permission
     *
     * @mbggenerated Sun Dec 23 17:29:19 GMT+07:00 2012
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_role_permission
     *
     * @mbggenerated Sun Dec 23 17:29:19 GMT+07:00 2012
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_role_permission
     *
     * @mbggenerated Sun Dec 23 17:29:19 GMT+07:00 2012
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_role_permission
     *
     * @mbggenerated Sun Dec 23 17:29:19 GMT+07:00 2012
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_role_permission
     *
     * @mbggenerated Sun Dec 23 17:29:19 GMT+07:00 2012
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_role_permission
     *
     * @mbggenerated Sun Dec 23 17:29:19 GMT+07:00 2012
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_role_permission
     *
     * @mbggenerated Sun Dec 23 17:29:19 GMT+07:00 2012
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
     * This method corresponds to the database table s_role_permission
     *
     * @mbggenerated Sun Dec 23 17:29:19 GMT+07:00 2012
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_role_permission
     *
     * @mbggenerated Sun Dec 23 17:29:19 GMT+07:00 2012
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table s_role_permission
     *
     * @mbggenerated Sun Dec 23 17:29:19 GMT+07:00 2012
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

        public Criteria andPathidIsNull() {
            addCriterion("pathid is null");
            return (Criteria) this;
        }

        public Criteria andPathidIsNotNull() {
            addCriterion("pathid is not null");
            return (Criteria) this;
        }

        public Criteria andPathidEqualTo(String value) {
            addCriterion("pathid =", value, "pathid");
            return (Criteria) this;
        }

        public Criteria andPathidNotEqualTo(String value) {
            addCriterion("pathid <>", value, "pathid");
            return (Criteria) this;
        }

        public Criteria andPathidGreaterThan(String value) {
            addCriterion("pathid >", value, "pathid");
            return (Criteria) this;
        }

        public Criteria andPathidGreaterThanOrEqualTo(String value) {
            addCriterion("pathid >=", value, "pathid");
            return (Criteria) this;
        }

        public Criteria andPathidLessThan(String value) {
            addCriterion("pathid <", value, "pathid");
            return (Criteria) this;
        }

        public Criteria andPathidLessThanOrEqualTo(String value) {
            addCriterion("pathid <=", value, "pathid");
            return (Criteria) this;
        }

        public Criteria andPathidLike(String value) {
            addCriterion("pathid like", value, "pathid");
            return (Criteria) this;
        }

        public Criteria andPathidNotLike(String value) {
            addCriterion("pathid not like", value, "pathid");
            return (Criteria) this;
        }

        public Criteria andPathidIn(List<String> values) {
            addCriterion("pathid in", values, "pathid");
            return (Criteria) this;
        }

        public Criteria andPathidNotIn(List<String> values) {
            addCriterion("pathid not in", values, "pathid");
            return (Criteria) this;
        }

        public Criteria andPathidBetween(String value1, String value2) {
            addCriterion("pathid between", value1, value2, "pathid");
            return (Criteria) this;
        }

        public Criteria andPathidNotBetween(String value1, String value2) {
            addCriterion("pathid not between", value1, value2, "pathid");
            return (Criteria) this;
        }

        public Criteria andIsauthorzIsNull() {
            addCriterion("isAuthorz is null");
            return (Criteria) this;
        }

        public Criteria andIsauthorzIsNotNull() {
            addCriterion("isAuthorz is not null");
            return (Criteria) this;
        }

        public Criteria andIsauthorzEqualTo(Boolean value) {
            addCriterion("isAuthorz =", value, "isauthorz");
            return (Criteria) this;
        }

        public Criteria andIsauthorzNotEqualTo(Boolean value) {
            addCriterion("isAuthorz <>", value, "isauthorz");
            return (Criteria) this;
        }

        public Criteria andIsauthorzGreaterThan(Boolean value) {
            addCriterion("isAuthorz >", value, "isauthorz");
            return (Criteria) this;
        }

        public Criteria andIsauthorzGreaterThanOrEqualTo(Boolean value) {
            addCriterion("isAuthorz >=", value, "isauthorz");
            return (Criteria) this;
        }

        public Criteria andIsauthorzLessThan(Boolean value) {
            addCriterion("isAuthorz <", value, "isauthorz");
            return (Criteria) this;
        }

        public Criteria andIsauthorzLessThanOrEqualTo(Boolean value) {
            addCriterion("isAuthorz <=", value, "isauthorz");
            return (Criteria) this;
        }

        public Criteria andIsauthorzIn(List<Boolean> values) {
            addCriterion("isAuthorz in", values, "isauthorz");
            return (Criteria) this;
        }

        public Criteria andIsauthorzNotIn(List<Boolean> values) {
            addCriterion("isAuthorz not in", values, "isauthorz");
            return (Criteria) this;
        }

        public Criteria andIsauthorzBetween(Boolean value1, Boolean value2) {
            addCriterion("isAuthorz between", value1, value2, "isauthorz");
            return (Criteria) this;
        }

        public Criteria andIsauthorzNotBetween(Boolean value1, Boolean value2) {
            addCriterion("isAuthorz not between", value1, value2, "isauthorz");
            return (Criteria) this;
        }

        public Criteria andRoleidIsNull() {
            addCriterion("roleid is null");
            return (Criteria) this;
        }

        public Criteria andRoleidIsNotNull() {
            addCriterion("roleid is not null");
            return (Criteria) this;
        }

        public Criteria andRoleidEqualTo(Integer value) {
            addCriterion("roleid =", value, "roleid");
            return (Criteria) this;
        }

        public Criteria andRoleidNotEqualTo(Integer value) {
            addCriterion("roleid <>", value, "roleid");
            return (Criteria) this;
        }

        public Criteria andRoleidGreaterThan(Integer value) {
            addCriterion("roleid >", value, "roleid");
            return (Criteria) this;
        }

        public Criteria andRoleidGreaterThanOrEqualTo(Integer value) {
            addCriterion("roleid >=", value, "roleid");
            return (Criteria) this;
        }

        public Criteria andRoleidLessThan(Integer value) {
            addCriterion("roleid <", value, "roleid");
            return (Criteria) this;
        }

        public Criteria andRoleidLessThanOrEqualTo(Integer value) {
            addCriterion("roleid <=", value, "roleid");
            return (Criteria) this;
        }

        public Criteria andRoleidIn(List<Integer> values) {
            addCriterion("roleid in", values, "roleid");
            return (Criteria) this;
        }

        public Criteria andRoleidNotIn(List<Integer> values) {
            addCriterion("roleid not in", values, "roleid");
            return (Criteria) this;
        }

        public Criteria andRoleidBetween(Integer value1, Integer value2) {
            addCriterion("roleid between", value1, value2, "roleid");
            return (Criteria) this;
        }

        public Criteria andRoleidNotBetween(Integer value1, Integer value2) {
            addCriterion("roleid not between", value1, value2, "roleid");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table s_role_permission
     *
     * @mbggenerated do_not_delete_during_merge Sun Dec 23 17:29:19 GMT+07:00 2012
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table s_role_permission
     *
     * @mbggenerated Sun Dec 23 17:29:19 GMT+07:00 2012
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