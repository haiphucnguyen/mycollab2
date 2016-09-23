package com.mycollab.module.project.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SuppressWarnings("ucd")
public class MessageExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table m_prj_message
     *
     * @mbg.generated Fri Sep 23 08:39:37 ICT 2016
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table m_prj_message
     *
     * @mbg.generated Fri Sep 23 08:39:37 ICT 2016
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table m_prj_message
     *
     * @mbg.generated Fri Sep 23 08:39:37 ICT 2016
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_message
     *
     * @mbg.generated Fri Sep 23 08:39:37 ICT 2016
     */
    public MessageExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_message
     *
     * @mbg.generated Fri Sep 23 08:39:37 ICT 2016
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_message
     *
     * @mbg.generated Fri Sep 23 08:39:37 ICT 2016
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_message
     *
     * @mbg.generated Fri Sep 23 08:39:37 ICT 2016
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_message
     *
     * @mbg.generated Fri Sep 23 08:39:37 ICT 2016
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_message
     *
     * @mbg.generated Fri Sep 23 08:39:37 ICT 2016
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_message
     *
     * @mbg.generated Fri Sep 23 08:39:37 ICT 2016
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_message
     *
     * @mbg.generated Fri Sep 23 08:39:37 ICT 2016
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_message
     *
     * @mbg.generated Fri Sep 23 08:39:37 ICT 2016
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
     * This method corresponds to the database table m_prj_message
     *
     * @mbg.generated Fri Sep 23 08:39:37 ICT 2016
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_message
     *
     * @mbg.generated Fri Sep 23 08:39:37 ICT 2016
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table m_prj_message
     *
     * @mbg.generated Fri Sep 23 08:39:37 ICT 2016
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

        public Criteria andPosteddateIsNull() {
            addCriterion("posteddate is null");
            return (Criteria) this;
        }

        public Criteria andPosteddateIsNotNull() {
            addCriterion("posteddate is not null");
            return (Criteria) this;
        }

        public Criteria andPosteddateEqualTo(Date value) {
            addCriterion("posteddate =", value, "posteddate");
            return (Criteria) this;
        }

        public Criteria andPosteddateNotEqualTo(Date value) {
            addCriterion("posteddate <>", value, "posteddate");
            return (Criteria) this;
        }

        public Criteria andPosteddateGreaterThan(Date value) {
            addCriterion("posteddate >", value, "posteddate");
            return (Criteria) this;
        }

        public Criteria andPosteddateGreaterThanOrEqualTo(Date value) {
            addCriterion("posteddate >=", value, "posteddate");
            return (Criteria) this;
        }

        public Criteria andPosteddateLessThan(Date value) {
            addCriterion("posteddate <", value, "posteddate");
            return (Criteria) this;
        }

        public Criteria andPosteddateLessThanOrEqualTo(Date value) {
            addCriterion("posteddate <=", value, "posteddate");
            return (Criteria) this;
        }

        public Criteria andPosteddateIn(List<Date> values) {
            addCriterion("posteddate in", values, "posteddate");
            return (Criteria) this;
        }

        public Criteria andPosteddateNotIn(List<Date> values) {
            addCriterion("posteddate not in", values, "posteddate");
            return (Criteria) this;
        }

        public Criteria andPosteddateBetween(Date value1, Date value2) {
            addCriterion("posteddate between", value1, value2, "posteddate");
            return (Criteria) this;
        }

        public Criteria andPosteddateNotBetween(Date value1, Date value2) {
            addCriterion("posteddate not between", value1, value2, "posteddate");
            return (Criteria) this;
        }

        public Criteria andPosteduserIsNull() {
            addCriterion("posteduser is null");
            return (Criteria) this;
        }

        public Criteria andPosteduserIsNotNull() {
            addCriterion("posteduser is not null");
            return (Criteria) this;
        }

        public Criteria andPosteduserEqualTo(String value) {
            addCriterion("posteduser =", value, "posteduser");
            return (Criteria) this;
        }

        public Criteria andPosteduserNotEqualTo(String value) {
            addCriterion("posteduser <>", value, "posteduser");
            return (Criteria) this;
        }

        public Criteria andPosteduserGreaterThan(String value) {
            addCriterion("posteduser >", value, "posteduser");
            return (Criteria) this;
        }

        public Criteria andPosteduserGreaterThanOrEqualTo(String value) {
            addCriterion("posteduser >=", value, "posteduser");
            return (Criteria) this;
        }

        public Criteria andPosteduserLessThan(String value) {
            addCriterion("posteduser <", value, "posteduser");
            return (Criteria) this;
        }

        public Criteria andPosteduserLessThanOrEqualTo(String value) {
            addCriterion("posteduser <=", value, "posteduser");
            return (Criteria) this;
        }

        public Criteria andPosteduserLike(String value) {
            addCriterion("posteduser like", value, "posteduser");
            return (Criteria) this;
        }

        public Criteria andPosteduserNotLike(String value) {
            addCriterion("posteduser not like", value, "posteduser");
            return (Criteria) this;
        }

        public Criteria andPosteduserIn(List<String> values) {
            addCriterion("posteduser in", values, "posteduser");
            return (Criteria) this;
        }

        public Criteria andPosteduserNotIn(List<String> values) {
            addCriterion("posteduser not in", values, "posteduser");
            return (Criteria) this;
        }

        public Criteria andPosteduserBetween(String value1, String value2) {
            addCriterion("posteduser between", value1, value2, "posteduser");
            return (Criteria) this;
        }

        public Criteria andPosteduserNotBetween(String value1, String value2) {
            addCriterion("posteduser not between", value1, value2, "posteduser");
            return (Criteria) this;
        }

        public Criteria andProjectidIsNull() {
            addCriterion("projectid is null");
            return (Criteria) this;
        }

        public Criteria andProjectidIsNotNull() {
            addCriterion("projectid is not null");
            return (Criteria) this;
        }

        public Criteria andProjectidEqualTo(Integer value) {
            addCriterion("projectid =", value, "projectid");
            return (Criteria) this;
        }

        public Criteria andProjectidNotEqualTo(Integer value) {
            addCriterion("projectid <>", value, "projectid");
            return (Criteria) this;
        }

        public Criteria andProjectidGreaterThan(Integer value) {
            addCriterion("projectid >", value, "projectid");
            return (Criteria) this;
        }

        public Criteria andProjectidGreaterThanOrEqualTo(Integer value) {
            addCriterion("projectid >=", value, "projectid");
            return (Criteria) this;
        }

        public Criteria andProjectidLessThan(Integer value) {
            addCriterion("projectid <", value, "projectid");
            return (Criteria) this;
        }

        public Criteria andProjectidLessThanOrEqualTo(Integer value) {
            addCriterion("projectid <=", value, "projectid");
            return (Criteria) this;
        }

        public Criteria andProjectidIn(List<Integer> values) {
            addCriterion("projectid in", values, "projectid");
            return (Criteria) this;
        }

        public Criteria andProjectidNotIn(List<Integer> values) {
            addCriterion("projectid not in", values, "projectid");
            return (Criteria) this;
        }

        public Criteria andProjectidBetween(Integer value1, Integer value2) {
            addCriterion("projectid between", value1, value2, "projectid");
            return (Criteria) this;
        }

        public Criteria andProjectidNotBetween(Integer value1, Integer value2) {
            addCriterion("projectid not between", value1, value2, "projectid");
            return (Criteria) this;
        }

        public Criteria andCategoryIsNull() {
            addCriterion("category is null");
            return (Criteria) this;
        }

        public Criteria andCategoryIsNotNull() {
            addCriterion("category is not null");
            return (Criteria) this;
        }

        public Criteria andCategoryEqualTo(String value) {
            addCriterion("category =", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryNotEqualTo(String value) {
            addCriterion("category <>", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryGreaterThan(String value) {
            addCriterion("category >", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryGreaterThanOrEqualTo(String value) {
            addCriterion("category >=", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryLessThan(String value) {
            addCriterion("category <", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryLessThanOrEqualTo(String value) {
            addCriterion("category <=", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryLike(String value) {
            addCriterion("category like", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryNotLike(String value) {
            addCriterion("category not like", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryIn(List<String> values) {
            addCriterion("category in", values, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryNotIn(List<String> values) {
            addCriterion("category not in", values, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryBetween(String value1, String value2) {
            addCriterion("category between", value1, value2, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryNotBetween(String value1, String value2) {
            addCriterion("category not between", value1, value2, "category");
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

        public Criteria andIsstickIsNull() {
            addCriterion("isStick is null");
            return (Criteria) this;
        }

        public Criteria andIsstickIsNotNull() {
            addCriterion("isStick is not null");
            return (Criteria) this;
        }

        public Criteria andIsstickEqualTo(Boolean value) {
            addCriterion("isStick =", value, "isstick");
            return (Criteria) this;
        }

        public Criteria andIsstickNotEqualTo(Boolean value) {
            addCriterion("isStick <>", value, "isstick");
            return (Criteria) this;
        }

        public Criteria andIsstickGreaterThan(Boolean value) {
            addCriterion("isStick >", value, "isstick");
            return (Criteria) this;
        }

        public Criteria andIsstickGreaterThanOrEqualTo(Boolean value) {
            addCriterion("isStick >=", value, "isstick");
            return (Criteria) this;
        }

        public Criteria andIsstickLessThan(Boolean value) {
            addCriterion("isStick <", value, "isstick");
            return (Criteria) this;
        }

        public Criteria andIsstickLessThanOrEqualTo(Boolean value) {
            addCriterion("isStick <=", value, "isstick");
            return (Criteria) this;
        }

        public Criteria andIsstickIn(List<Boolean> values) {
            addCriterion("isStick in", values, "isstick");
            return (Criteria) this;
        }

        public Criteria andIsstickNotIn(List<Boolean> values) {
            addCriterion("isStick not in", values, "isstick");
            return (Criteria) this;
        }

        public Criteria andIsstickBetween(Boolean value1, Boolean value2) {
            addCriterion("isStick between", value1, value2, "isstick");
            return (Criteria) this;
        }

        public Criteria andIsstickNotBetween(Boolean value1, Boolean value2) {
            addCriterion("isStick not between", value1, value2, "isstick");
            return (Criteria) this;
        }

        public Criteria andPrjkeyIsNull() {
            addCriterion("prjKey is null");
            return (Criteria) this;
        }

        public Criteria andPrjkeyIsNotNull() {
            addCriterion("prjKey is not null");
            return (Criteria) this;
        }

        public Criteria andPrjkeyEqualTo(Integer value) {
            addCriterion("prjKey =", value, "prjkey");
            return (Criteria) this;
        }

        public Criteria andPrjkeyNotEqualTo(Integer value) {
            addCriterion("prjKey <>", value, "prjkey");
            return (Criteria) this;
        }

        public Criteria andPrjkeyGreaterThan(Integer value) {
            addCriterion("prjKey >", value, "prjkey");
            return (Criteria) this;
        }

        public Criteria andPrjkeyGreaterThanOrEqualTo(Integer value) {
            addCriterion("prjKey >=", value, "prjkey");
            return (Criteria) this;
        }

        public Criteria andPrjkeyLessThan(Integer value) {
            addCriterion("prjKey <", value, "prjkey");
            return (Criteria) this;
        }

        public Criteria andPrjkeyLessThanOrEqualTo(Integer value) {
            addCriterion("prjKey <=", value, "prjkey");
            return (Criteria) this;
        }

        public Criteria andPrjkeyIn(List<Integer> values) {
            addCriterion("prjKey in", values, "prjkey");
            return (Criteria) this;
        }

        public Criteria andPrjkeyNotIn(List<Integer> values) {
            addCriterion("prjKey not in", values, "prjkey");
            return (Criteria) this;
        }

        public Criteria andPrjkeyBetween(Integer value1, Integer value2) {
            addCriterion("prjKey between", value1, value2, "prjkey");
            return (Criteria) this;
        }

        public Criteria andPrjkeyNotBetween(Integer value1, Integer value2) {
            addCriterion("prjKey not between", value1, value2, "prjkey");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table m_prj_message
     *
     * @mbg.generated do_not_delete_during_merge Fri Sep 23 08:39:37 ICT 2016
     */
    @SuppressWarnings("ucd")
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table m_prj_message
     *
     * @mbg.generated Fri Sep 23 08:39:37 ICT 2016
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