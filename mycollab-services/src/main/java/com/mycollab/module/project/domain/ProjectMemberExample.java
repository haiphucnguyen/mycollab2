package com.mycollab.module.project.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("ucd")
public class ProjectMemberExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table m_prj_member
     *
     * @mbg.generated Mon Jan 21 09:49:22 CST 2019
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table m_prj_member
     *
     * @mbg.generated Mon Jan 21 09:49:22 CST 2019
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table m_prj_member
     *
     * @mbg.generated Mon Jan 21 09:49:22 CST 2019
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_member
     *
     * @mbg.generated Mon Jan 21 09:49:22 CST 2019
     */
    public ProjectMemberExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_member
     *
     * @mbg.generated Mon Jan 21 09:49:22 CST 2019
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_member
     *
     * @mbg.generated Mon Jan 21 09:49:22 CST 2019
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_member
     *
     * @mbg.generated Mon Jan 21 09:49:22 CST 2019
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_member
     *
     * @mbg.generated Mon Jan 21 09:49:22 CST 2019
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_member
     *
     * @mbg.generated Mon Jan 21 09:49:22 CST 2019
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_member
     *
     * @mbg.generated Mon Jan 21 09:49:22 CST 2019
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_member
     *
     * @mbg.generated Mon Jan 21 09:49:22 CST 2019
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_member
     *
     * @mbg.generated Mon Jan 21 09:49:22 CST 2019
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
     * This method corresponds to the database table m_prj_member
     *
     * @mbg.generated Mon Jan 21 09:49:22 CST 2019
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_member
     *
     * @mbg.generated Mon Jan 21 09:49:22 CST 2019
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table m_prj_member
     *
     * @mbg.generated Mon Jan 21 09:49:22 CST 2019
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

        public Criteria andProjectidIsNull() {
            addCriterion("projectId is null");
            return (Criteria) this;
        }

        public Criteria andProjectidIsNotNull() {
            addCriterion("projectId is not null");
            return (Criteria) this;
        }

        public Criteria andProjectidEqualTo(Integer value) {
            addCriterion("projectId =", value, "projectid");
            return (Criteria) this;
        }

        public Criteria andProjectidNotEqualTo(Integer value) {
            addCriterion("projectId <>", value, "projectid");
            return (Criteria) this;
        }

        public Criteria andProjectidGreaterThan(Integer value) {
            addCriterion("projectId >", value, "projectid");
            return (Criteria) this;
        }

        public Criteria andProjectidGreaterThanOrEqualTo(Integer value) {
            addCriterion("projectId >=", value, "projectid");
            return (Criteria) this;
        }

        public Criteria andProjectidLessThan(Integer value) {
            addCriterion("projectId <", value, "projectid");
            return (Criteria) this;
        }

        public Criteria andProjectidLessThanOrEqualTo(Integer value) {
            addCriterion("projectId <=", value, "projectid");
            return (Criteria) this;
        }

        public Criteria andProjectidIn(List<Integer> values) {
            addCriterion("projectId in", values, "projectid");
            return (Criteria) this;
        }

        public Criteria andProjectidNotIn(List<Integer> values) {
            addCriterion("projectId not in", values, "projectid");
            return (Criteria) this;
        }

        public Criteria andProjectidBetween(Integer value1, Integer value2) {
            addCriterion("projectId between", value1, value2, "projectid");
            return (Criteria) this;
        }

        public Criteria andProjectidNotBetween(Integer value1, Integer value2) {
            addCriterion("projectId not between", value1, value2, "projectid");
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

        public Criteria andCreatedtimeEqualTo(LocalDateTime value) {
            addCriterion("createdTime =", value, "createdtime");
            return (Criteria) this;
        }

        public Criteria andCreatedtimeNotEqualTo(LocalDateTime value) {
            addCriterion("createdTime <>", value, "createdtime");
            return (Criteria) this;
        }

        public Criteria andCreatedtimeGreaterThan(LocalDateTime value) {
            addCriterion("createdTime >", value, "createdtime");
            return (Criteria) this;
        }

        public Criteria andCreatedtimeGreaterThanOrEqualTo(LocalDateTime value) {
            addCriterion("createdTime >=", value, "createdtime");
            return (Criteria) this;
        }

        public Criteria andCreatedtimeLessThan(LocalDateTime value) {
            addCriterion("createdTime <", value, "createdtime");
            return (Criteria) this;
        }

        public Criteria andCreatedtimeLessThanOrEqualTo(LocalDateTime value) {
            addCriterion("createdTime <=", value, "createdtime");
            return (Criteria) this;
        }

        public Criteria andCreatedtimeIn(List<LocalDateTime> values) {
            addCriterion("createdTime in", values, "createdtime");
            return (Criteria) this;
        }

        public Criteria andCreatedtimeNotIn(List<LocalDateTime> values) {
            addCriterion("createdTime not in", values, "createdtime");
            return (Criteria) this;
        }

        public Criteria andCreatedtimeBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("createdTime between", value1, value2, "createdtime");
            return (Criteria) this;
        }

        public Criteria andCreatedtimeNotBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("createdTime not between", value1, value2, "createdtime");
            return (Criteria) this;
        }

        public Criteria andProjectroleidIsNull() {
            addCriterion("projectRoleId is null");
            return (Criteria) this;
        }

        public Criteria andProjectroleidIsNotNull() {
            addCriterion("projectRoleId is not null");
            return (Criteria) this;
        }

        public Criteria andProjectroleidEqualTo(Integer value) {
            addCriterion("projectRoleId =", value, "projectroleid");
            return (Criteria) this;
        }

        public Criteria andProjectroleidNotEqualTo(Integer value) {
            addCriterion("projectRoleId <>", value, "projectroleid");
            return (Criteria) this;
        }

        public Criteria andProjectroleidGreaterThan(Integer value) {
            addCriterion("projectRoleId >", value, "projectroleid");
            return (Criteria) this;
        }

        public Criteria andProjectroleidGreaterThanOrEqualTo(Integer value) {
            addCriterion("projectRoleId >=", value, "projectroleid");
            return (Criteria) this;
        }

        public Criteria andProjectroleidLessThan(Integer value) {
            addCriterion("projectRoleId <", value, "projectroleid");
            return (Criteria) this;
        }

        public Criteria andProjectroleidLessThanOrEqualTo(Integer value) {
            addCriterion("projectRoleId <=", value, "projectroleid");
            return (Criteria) this;
        }

        public Criteria andProjectroleidIn(List<Integer> values) {
            addCriterion("projectRoleId in", values, "projectroleid");
            return (Criteria) this;
        }

        public Criteria andProjectroleidNotIn(List<Integer> values) {
            addCriterion("projectRoleId not in", values, "projectroleid");
            return (Criteria) this;
        }

        public Criteria andProjectroleidBetween(Integer value1, Integer value2) {
            addCriterion("projectRoleId between", value1, value2, "projectroleid");
            return (Criteria) this;
        }

        public Criteria andProjectroleidNotBetween(Integer value1, Integer value2) {
            addCriterion("projectRoleId not between", value1, value2, "projectroleid");
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

        public Criteria andStatusEqualTo(String value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("status like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("status not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("status not between", value1, value2, "status");
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

        public Criteria andBillingrateIsNull() {
            addCriterion("billingRate is null");
            return (Criteria) this;
        }

        public Criteria andBillingrateIsNotNull() {
            addCriterion("billingRate is not null");
            return (Criteria) this;
        }

        public Criteria andBillingrateEqualTo(Double value) {
            addCriterion("billingRate =", value, "billingrate");
            return (Criteria) this;
        }

        public Criteria andBillingrateNotEqualTo(Double value) {
            addCriterion("billingRate <>", value, "billingrate");
            return (Criteria) this;
        }

        public Criteria andBillingrateGreaterThan(Double value) {
            addCriterion("billingRate >", value, "billingrate");
            return (Criteria) this;
        }

        public Criteria andBillingrateGreaterThanOrEqualTo(Double value) {
            addCriterion("billingRate >=", value, "billingrate");
            return (Criteria) this;
        }

        public Criteria andBillingrateLessThan(Double value) {
            addCriterion("billingRate <", value, "billingrate");
            return (Criteria) this;
        }

        public Criteria andBillingrateLessThanOrEqualTo(Double value) {
            addCriterion("billingRate <=", value, "billingrate");
            return (Criteria) this;
        }

        public Criteria andBillingrateIn(List<Double> values) {
            addCriterion("billingRate in", values, "billingrate");
            return (Criteria) this;
        }

        public Criteria andBillingrateNotIn(List<Double> values) {
            addCriterion("billingRate not in", values, "billingrate");
            return (Criteria) this;
        }

        public Criteria andBillingrateBetween(Double value1, Double value2) {
            addCriterion("billingRate between", value1, value2, "billingrate");
            return (Criteria) this;
        }

        public Criteria andBillingrateNotBetween(Double value1, Double value2) {
            addCriterion("billingRate not between", value1, value2, "billingrate");
            return (Criteria) this;
        }

        public Criteria andOvertimebillingrateIsNull() {
            addCriterion("overtimeBillingRate is null");
            return (Criteria) this;
        }

        public Criteria andOvertimebillingrateIsNotNull() {
            addCriterion("overtimeBillingRate is not null");
            return (Criteria) this;
        }

        public Criteria andOvertimebillingrateEqualTo(Double value) {
            addCriterion("overtimeBillingRate =", value, "overtimebillingrate");
            return (Criteria) this;
        }

        public Criteria andOvertimebillingrateNotEqualTo(Double value) {
            addCriterion("overtimeBillingRate <>", value, "overtimebillingrate");
            return (Criteria) this;
        }

        public Criteria andOvertimebillingrateGreaterThan(Double value) {
            addCriterion("overtimeBillingRate >", value, "overtimebillingrate");
            return (Criteria) this;
        }

        public Criteria andOvertimebillingrateGreaterThanOrEqualTo(Double value) {
            addCriterion("overtimeBillingRate >=", value, "overtimebillingrate");
            return (Criteria) this;
        }

        public Criteria andOvertimebillingrateLessThan(Double value) {
            addCriterion("overtimeBillingRate <", value, "overtimebillingrate");
            return (Criteria) this;
        }

        public Criteria andOvertimebillingrateLessThanOrEqualTo(Double value) {
            addCriterion("overtimeBillingRate <=", value, "overtimebillingrate");
            return (Criteria) this;
        }

        public Criteria andOvertimebillingrateIn(List<Double> values) {
            addCriterion("overtimeBillingRate in", values, "overtimebillingrate");
            return (Criteria) this;
        }

        public Criteria andOvertimebillingrateNotIn(List<Double> values) {
            addCriterion("overtimeBillingRate not in", values, "overtimebillingrate");
            return (Criteria) this;
        }

        public Criteria andOvertimebillingrateBetween(Double value1, Double value2) {
            addCriterion("overtimeBillingRate between", value1, value2, "overtimebillingrate");
            return (Criteria) this;
        }

        public Criteria andOvertimebillingrateNotBetween(Double value1, Double value2) {
            addCriterion("overtimeBillingRate not between", value1, value2, "overtimebillingrate");
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

        public Criteria andLastupdatedtimeEqualTo(LocalDateTime value) {
            addCriterion("lastUpdatedTime =", value, "lastupdatedtime");
            return (Criteria) this;
        }

        public Criteria andLastupdatedtimeNotEqualTo(LocalDateTime value) {
            addCriterion("lastUpdatedTime <>", value, "lastupdatedtime");
            return (Criteria) this;
        }

        public Criteria andLastupdatedtimeGreaterThan(LocalDateTime value) {
            addCriterion("lastUpdatedTime >", value, "lastupdatedtime");
            return (Criteria) this;
        }

        public Criteria andLastupdatedtimeGreaterThanOrEqualTo(LocalDateTime value) {
            addCriterion("lastUpdatedTime >=", value, "lastupdatedtime");
            return (Criteria) this;
        }

        public Criteria andLastupdatedtimeLessThan(LocalDateTime value) {
            addCriterion("lastUpdatedTime <", value, "lastupdatedtime");
            return (Criteria) this;
        }

        public Criteria andLastupdatedtimeLessThanOrEqualTo(LocalDateTime value) {
            addCriterion("lastUpdatedTime <=", value, "lastupdatedtime");
            return (Criteria) this;
        }

        public Criteria andLastupdatedtimeIn(List<LocalDateTime> values) {
            addCriterion("lastUpdatedTime in", values, "lastupdatedtime");
            return (Criteria) this;
        }

        public Criteria andLastupdatedtimeNotIn(List<LocalDateTime> values) {
            addCriterion("lastUpdatedTime not in", values, "lastupdatedtime");
            return (Criteria) this;
        }

        public Criteria andLastupdatedtimeBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("lastUpdatedTime between", value1, value2, "lastupdatedtime");
            return (Criteria) this;
        }

        public Criteria andLastupdatedtimeNotBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("lastUpdatedTime not between", value1, value2, "lastupdatedtime");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table m_prj_member
     *
     * @mbg.generated do_not_delete_during_merge Mon Jan 21 09:49:22 CST 2019
     */
    @SuppressWarnings("ucd")
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table m_prj_member
     *
     * @mbg.generated Mon Jan 21 09:49:22 CST 2019
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