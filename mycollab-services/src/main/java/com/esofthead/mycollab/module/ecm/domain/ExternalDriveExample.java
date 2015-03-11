package com.esofthead.mycollab.module.ecm.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SuppressWarnings("ucd")
public class ExternalDriveExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table m_ecm_external_drive
     *
     * @mbggenerated Wed Mar 11 09:10:41 ICT 2015
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table m_ecm_external_drive
     *
     * @mbggenerated Wed Mar 11 09:10:41 ICT 2015
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table m_ecm_external_drive
     *
     * @mbggenerated Wed Mar 11 09:10:41 ICT 2015
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_ecm_external_drive
     *
     * @mbggenerated Wed Mar 11 09:10:41 ICT 2015
     */
    public ExternalDriveExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_ecm_external_drive
     *
     * @mbggenerated Wed Mar 11 09:10:41 ICT 2015
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_ecm_external_drive
     *
     * @mbggenerated Wed Mar 11 09:10:41 ICT 2015
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_ecm_external_drive
     *
     * @mbggenerated Wed Mar 11 09:10:41 ICT 2015
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_ecm_external_drive
     *
     * @mbggenerated Wed Mar 11 09:10:41 ICT 2015
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_ecm_external_drive
     *
     * @mbggenerated Wed Mar 11 09:10:41 ICT 2015
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_ecm_external_drive
     *
     * @mbggenerated Wed Mar 11 09:10:41 ICT 2015
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_ecm_external_drive
     *
     * @mbggenerated Wed Mar 11 09:10:41 ICT 2015
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_ecm_external_drive
     *
     * @mbggenerated Wed Mar 11 09:10:41 ICT 2015
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
     * This method corresponds to the database table m_ecm_external_drive
     *
     * @mbggenerated Wed Mar 11 09:10:41 ICT 2015
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_ecm_external_drive
     *
     * @mbggenerated Wed Mar 11 09:10:41 ICT 2015
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table m_ecm_external_drive
     *
     * @mbggenerated Wed Mar 11 09:10:41 ICT 2015
     */
    @SuppressWarnings("ucd")
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

        public Criteria andStoragenameIsNull() {
            addCriterion("storageName is null");
            return (Criteria) this;
        }

        public Criteria andStoragenameIsNotNull() {
            addCriterion("storageName is not null");
            return (Criteria) this;
        }

        public Criteria andStoragenameEqualTo(String value) {
            addCriterion("storageName =", value, "storagename");
            return (Criteria) this;
        }

        public Criteria andStoragenameNotEqualTo(String value) {
            addCriterion("storageName <>", value, "storagename");
            return (Criteria) this;
        }

        public Criteria andStoragenameGreaterThan(String value) {
            addCriterion("storageName >", value, "storagename");
            return (Criteria) this;
        }

        public Criteria andStoragenameGreaterThanOrEqualTo(String value) {
            addCriterion("storageName >=", value, "storagename");
            return (Criteria) this;
        }

        public Criteria andStoragenameLessThan(String value) {
            addCriterion("storageName <", value, "storagename");
            return (Criteria) this;
        }

        public Criteria andStoragenameLessThanOrEqualTo(String value) {
            addCriterion("storageName <=", value, "storagename");
            return (Criteria) this;
        }

        public Criteria andStoragenameLike(String value) {
            addCriterion("storageName like", value, "storagename");
            return (Criteria) this;
        }

        public Criteria andStoragenameNotLike(String value) {
            addCriterion("storageName not like", value, "storagename");
            return (Criteria) this;
        }

        public Criteria andStoragenameIn(List<String> values) {
            addCriterion("storageName in", values, "storagename");
            return (Criteria) this;
        }

        public Criteria andStoragenameNotIn(List<String> values) {
            addCriterion("storageName not in", values, "storagename");
            return (Criteria) this;
        }

        public Criteria andStoragenameBetween(String value1, String value2) {
            addCriterion("storageName between", value1, value2, "storagename");
            return (Criteria) this;
        }

        public Criteria andStoragenameNotBetween(String value1, String value2) {
            addCriterion("storageName not between", value1, value2, "storagename");
            return (Criteria) this;
        }

        public Criteria andOwnerIsNull() {
            addCriterion("owner is null");
            return (Criteria) this;
        }

        public Criteria andOwnerIsNotNull() {
            addCriterion("owner is not null");
            return (Criteria) this;
        }

        public Criteria andOwnerEqualTo(String value) {
            addCriterion("owner =", value, "owner");
            return (Criteria) this;
        }

        public Criteria andOwnerNotEqualTo(String value) {
            addCriterion("owner <>", value, "owner");
            return (Criteria) this;
        }

        public Criteria andOwnerGreaterThan(String value) {
            addCriterion("owner >", value, "owner");
            return (Criteria) this;
        }

        public Criteria andOwnerGreaterThanOrEqualTo(String value) {
            addCriterion("owner >=", value, "owner");
            return (Criteria) this;
        }

        public Criteria andOwnerLessThan(String value) {
            addCriterion("owner <", value, "owner");
            return (Criteria) this;
        }

        public Criteria andOwnerLessThanOrEqualTo(String value) {
            addCriterion("owner <=", value, "owner");
            return (Criteria) this;
        }

        public Criteria andOwnerLike(String value) {
            addCriterion("owner like", value, "owner");
            return (Criteria) this;
        }

        public Criteria andOwnerNotLike(String value) {
            addCriterion("owner not like", value, "owner");
            return (Criteria) this;
        }

        public Criteria andOwnerIn(List<String> values) {
            addCriterion("owner in", values, "owner");
            return (Criteria) this;
        }

        public Criteria andOwnerNotIn(List<String> values) {
            addCriterion("owner not in", values, "owner");
            return (Criteria) this;
        }

        public Criteria andOwnerBetween(String value1, String value2) {
            addCriterion("owner between", value1, value2, "owner");
            return (Criteria) this;
        }

        public Criteria andOwnerNotBetween(String value1, String value2) {
            addCriterion("owner not between", value1, value2, "owner");
            return (Criteria) this;
        }

        public Criteria andAccesstokenIsNull() {
            addCriterion("accessToken is null");
            return (Criteria) this;
        }

        public Criteria andAccesstokenIsNotNull() {
            addCriterion("accessToken is not null");
            return (Criteria) this;
        }

        public Criteria andAccesstokenEqualTo(String value) {
            addCriterion("accessToken =", value, "accesstoken");
            return (Criteria) this;
        }

        public Criteria andAccesstokenNotEqualTo(String value) {
            addCriterion("accessToken <>", value, "accesstoken");
            return (Criteria) this;
        }

        public Criteria andAccesstokenGreaterThan(String value) {
            addCriterion("accessToken >", value, "accesstoken");
            return (Criteria) this;
        }

        public Criteria andAccesstokenGreaterThanOrEqualTo(String value) {
            addCriterion("accessToken >=", value, "accesstoken");
            return (Criteria) this;
        }

        public Criteria andAccesstokenLessThan(String value) {
            addCriterion("accessToken <", value, "accesstoken");
            return (Criteria) this;
        }

        public Criteria andAccesstokenLessThanOrEqualTo(String value) {
            addCriterion("accessToken <=", value, "accesstoken");
            return (Criteria) this;
        }

        public Criteria andAccesstokenLike(String value) {
            addCriterion("accessToken like", value, "accesstoken");
            return (Criteria) this;
        }

        public Criteria andAccesstokenNotLike(String value) {
            addCriterion("accessToken not like", value, "accesstoken");
            return (Criteria) this;
        }

        public Criteria andAccesstokenIn(List<String> values) {
            addCriterion("accessToken in", values, "accesstoken");
            return (Criteria) this;
        }

        public Criteria andAccesstokenNotIn(List<String> values) {
            addCriterion("accessToken not in", values, "accesstoken");
            return (Criteria) this;
        }

        public Criteria andAccesstokenBetween(String value1, String value2) {
            addCriterion("accessToken between", value1, value2, "accesstoken");
            return (Criteria) this;
        }

        public Criteria andAccesstokenNotBetween(String value1, String value2) {
            addCriterion("accessToken not between", value1, value2, "accesstoken");
            return (Criteria) this;
        }

        public Criteria andCreatedtimeIsNull() {
            addCriterion("createdTime is null");
            return (Criteria) this;
        }

        public Criteria andCreatedtimeIsNotNull() {
            addCriterion("createdTime is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedtimeEqualTo(Date value) {
            addCriterion("createdTime =", value, "createdtime");
            return (Criteria) this;
        }

        public Criteria andCreatedtimeNotEqualTo(Date value) {
            addCriterion("createdTime <>", value, "createdtime");
            return (Criteria) this;
        }

        public Criteria andCreatedtimeGreaterThan(Date value) {
            addCriterion("createdTime >", value, "createdtime");
            return (Criteria) this;
        }

        public Criteria andCreatedtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("createdTime >=", value, "createdtime");
            return (Criteria) this;
        }

        public Criteria andCreatedtimeLessThan(Date value) {
            addCriterion("createdTime <", value, "createdtime");
            return (Criteria) this;
        }

        public Criteria andCreatedtimeLessThanOrEqualTo(Date value) {
            addCriterion("createdTime <=", value, "createdtime");
            return (Criteria) this;
        }

        public Criteria andCreatedtimeIn(List<Date> values) {
            addCriterion("createdTime in", values, "createdtime");
            return (Criteria) this;
        }

        public Criteria andCreatedtimeNotIn(List<Date> values) {
            addCriterion("createdTime not in", values, "createdtime");
            return (Criteria) this;
        }

        public Criteria andCreatedtimeBetween(Date value1, Date value2) {
            addCriterion("createdTime between", value1, value2, "createdtime");
            return (Criteria) this;
        }

        public Criteria andCreatedtimeNotBetween(Date value1, Date value2) {
            addCriterion("createdTime not between", value1, value2, "createdtime");
            return (Criteria) this;
        }

        public Criteria andLastupdatedtimeIsNull() {
            addCriterion("lastUpdatedTime is null");
            return (Criteria) this;
        }

        public Criteria andLastupdatedtimeIsNotNull() {
            addCriterion("lastUpdatedTime is not null");
            return (Criteria) this;
        }

        public Criteria andLastupdatedtimeEqualTo(Date value) {
            addCriterion("lastUpdatedTime =", value, "lastupdatedtime");
            return (Criteria) this;
        }

        public Criteria andLastupdatedtimeNotEqualTo(Date value) {
            addCriterion("lastUpdatedTime <>", value, "lastupdatedtime");
            return (Criteria) this;
        }

        public Criteria andLastupdatedtimeGreaterThan(Date value) {
            addCriterion("lastUpdatedTime >", value, "lastupdatedtime");
            return (Criteria) this;
        }

        public Criteria andLastupdatedtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("lastUpdatedTime >=", value, "lastupdatedtime");
            return (Criteria) this;
        }

        public Criteria andLastupdatedtimeLessThan(Date value) {
            addCriterion("lastUpdatedTime <", value, "lastupdatedtime");
            return (Criteria) this;
        }

        public Criteria andLastupdatedtimeLessThanOrEqualTo(Date value) {
            addCriterion("lastUpdatedTime <=", value, "lastupdatedtime");
            return (Criteria) this;
        }

        public Criteria andLastupdatedtimeIn(List<Date> values) {
            addCriterion("lastUpdatedTime in", values, "lastupdatedtime");
            return (Criteria) this;
        }

        public Criteria andLastupdatedtimeNotIn(List<Date> values) {
            addCriterion("lastUpdatedTime not in", values, "lastupdatedtime");
            return (Criteria) this;
        }

        public Criteria andLastupdatedtimeBetween(Date value1, Date value2) {
            addCriterion("lastUpdatedTime between", value1, value2, "lastupdatedtime");
            return (Criteria) this;
        }

        public Criteria andLastupdatedtimeNotBetween(Date value1, Date value2) {
            addCriterion("lastUpdatedTime not between", value1, value2, "lastupdatedtime");
            return (Criteria) this;
        }

        public Criteria andFoldernameIsNull() {
            addCriterion("folderName is null");
            return (Criteria) this;
        }

        public Criteria andFoldernameIsNotNull() {
            addCriterion("folderName is not null");
            return (Criteria) this;
        }

        public Criteria andFoldernameEqualTo(String value) {
            addCriterion("folderName =", value, "foldername");
            return (Criteria) this;
        }

        public Criteria andFoldernameNotEqualTo(String value) {
            addCriterion("folderName <>", value, "foldername");
            return (Criteria) this;
        }

        public Criteria andFoldernameGreaterThan(String value) {
            addCriterion("folderName >", value, "foldername");
            return (Criteria) this;
        }

        public Criteria andFoldernameGreaterThanOrEqualTo(String value) {
            addCriterion("folderName >=", value, "foldername");
            return (Criteria) this;
        }

        public Criteria andFoldernameLessThan(String value) {
            addCriterion("folderName <", value, "foldername");
            return (Criteria) this;
        }

        public Criteria andFoldernameLessThanOrEqualTo(String value) {
            addCriterion("folderName <=", value, "foldername");
            return (Criteria) this;
        }

        public Criteria andFoldernameLike(String value) {
            addCriterion("folderName like", value, "foldername");
            return (Criteria) this;
        }

        public Criteria andFoldernameNotLike(String value) {
            addCriterion("folderName not like", value, "foldername");
            return (Criteria) this;
        }

        public Criteria andFoldernameIn(List<String> values) {
            addCriterion("folderName in", values, "foldername");
            return (Criteria) this;
        }

        public Criteria andFoldernameNotIn(List<String> values) {
            addCriterion("folderName not in", values, "foldername");
            return (Criteria) this;
        }

        public Criteria andFoldernameBetween(String value1, String value2) {
            addCriterion("folderName between", value1, value2, "foldername");
            return (Criteria) this;
        }

        public Criteria andFoldernameNotBetween(String value1, String value2) {
            addCriterion("folderName not between", value1, value2, "foldername");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table m_ecm_external_drive
     *
     * @mbggenerated do_not_delete_during_merge Wed Mar 11 09:10:41 ICT 2015
     */
    @SuppressWarnings("ucd")
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table m_ecm_external_drive
     *
     * @mbggenerated Wed Mar 11 09:10:41 ICT 2015
     */
    @SuppressWarnings("ucd")
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