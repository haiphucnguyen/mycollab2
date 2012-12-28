package com.esofthead.mycollab.module.project.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ResourceExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table m_prj_resource
     *
     * @mbggenerated Fri Dec 28 00:40:18 GMT+07:00 2012
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table m_prj_resource
     *
     * @mbggenerated Fri Dec 28 00:40:18 GMT+07:00 2012
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table m_prj_resource
     *
     * @mbggenerated Fri Dec 28 00:40:18 GMT+07:00 2012
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_resource
     *
     * @mbggenerated Fri Dec 28 00:40:18 GMT+07:00 2012
     */
    public ResourceExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_resource
     *
     * @mbggenerated Fri Dec 28 00:40:18 GMT+07:00 2012
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_resource
     *
     * @mbggenerated Fri Dec 28 00:40:18 GMT+07:00 2012
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_resource
     *
     * @mbggenerated Fri Dec 28 00:40:18 GMT+07:00 2012
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_resource
     *
     * @mbggenerated Fri Dec 28 00:40:18 GMT+07:00 2012
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_resource
     *
     * @mbggenerated Fri Dec 28 00:40:18 GMT+07:00 2012
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_resource
     *
     * @mbggenerated Fri Dec 28 00:40:18 GMT+07:00 2012
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_resource
     *
     * @mbggenerated Fri Dec 28 00:40:18 GMT+07:00 2012
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_resource
     *
     * @mbggenerated Fri Dec 28 00:40:18 GMT+07:00 2012
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
     * This method corresponds to the database table m_prj_resource
     *
     * @mbggenerated Fri Dec 28 00:40:18 GMT+07:00 2012
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_resource
     *
     * @mbggenerated Fri Dec 28 00:40:18 GMT+07:00 2012
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table m_prj_resource
     *
     * @mbggenerated Fri Dec 28 00:40:18 GMT+07:00 2012
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

        public Criteria andResourcenameIsNull() {
            addCriterion("resourcename is null");
            return (Criteria) this;
        }

        public Criteria andResourcenameIsNotNull() {
            addCriterion("resourcename is not null");
            return (Criteria) this;
        }

        public Criteria andResourcenameEqualTo(String value) {
            addCriterion("resourcename =", value, "resourcename");
            return (Criteria) this;
        }

        public Criteria andResourcenameNotEqualTo(String value) {
            addCriterion("resourcename <>", value, "resourcename");
            return (Criteria) this;
        }

        public Criteria andResourcenameGreaterThan(String value) {
            addCriterion("resourcename >", value, "resourcename");
            return (Criteria) this;
        }

        public Criteria andResourcenameGreaterThanOrEqualTo(String value) {
            addCriterion("resourcename >=", value, "resourcename");
            return (Criteria) this;
        }

        public Criteria andResourcenameLessThan(String value) {
            addCriterion("resourcename <", value, "resourcename");
            return (Criteria) this;
        }

        public Criteria andResourcenameLessThanOrEqualTo(String value) {
            addCriterion("resourcename <=", value, "resourcename");
            return (Criteria) this;
        }

        public Criteria andResourcenameLike(String value) {
            addCriterion("resourcename like", value, "resourcename");
            return (Criteria) this;
        }

        public Criteria andResourcenameNotLike(String value) {
            addCriterion("resourcename not like", value, "resourcename");
            return (Criteria) this;
        }

        public Criteria andResourcenameIn(List<String> values) {
            addCriterion("resourcename in", values, "resourcename");
            return (Criteria) this;
        }

        public Criteria andResourcenameNotIn(List<String> values) {
            addCriterion("resourcename not in", values, "resourcename");
            return (Criteria) this;
        }

        public Criteria andResourcenameBetween(String value1, String value2) {
            addCriterion("resourcename between", value1, value2, "resourcename");
            return (Criteria) this;
        }

        public Criteria andResourcenameNotBetween(String value1, String value2) {
            addCriterion("resourcename not between", value1, value2, "resourcename");
            return (Criteria) this;
        }

        public Criteria andBookingtypeIsNull() {
            addCriterion("bookingtype is null");
            return (Criteria) this;
        }

        public Criteria andBookingtypeIsNotNull() {
            addCriterion("bookingtype is not null");
            return (Criteria) this;
        }

        public Criteria andBookingtypeEqualTo(String value) {
            addCriterion("bookingtype =", value, "bookingtype");
            return (Criteria) this;
        }

        public Criteria andBookingtypeNotEqualTo(String value) {
            addCriterion("bookingtype <>", value, "bookingtype");
            return (Criteria) this;
        }

        public Criteria andBookingtypeGreaterThan(String value) {
            addCriterion("bookingtype >", value, "bookingtype");
            return (Criteria) this;
        }

        public Criteria andBookingtypeGreaterThanOrEqualTo(String value) {
            addCriterion("bookingtype >=", value, "bookingtype");
            return (Criteria) this;
        }

        public Criteria andBookingtypeLessThan(String value) {
            addCriterion("bookingtype <", value, "bookingtype");
            return (Criteria) this;
        }

        public Criteria andBookingtypeLessThanOrEqualTo(String value) {
            addCriterion("bookingtype <=", value, "bookingtype");
            return (Criteria) this;
        }

        public Criteria andBookingtypeLike(String value) {
            addCriterion("bookingtype like", value, "bookingtype");
            return (Criteria) this;
        }

        public Criteria andBookingtypeNotLike(String value) {
            addCriterion("bookingtype not like", value, "bookingtype");
            return (Criteria) this;
        }

        public Criteria andBookingtypeIn(List<String> values) {
            addCriterion("bookingtype in", values, "bookingtype");
            return (Criteria) this;
        }

        public Criteria andBookingtypeNotIn(List<String> values) {
            addCriterion("bookingtype not in", values, "bookingtype");
            return (Criteria) this;
        }

        public Criteria andBookingtypeBetween(String value1, String value2) {
            addCriterion("bookingtype between", value1, value2, "bookingtype");
            return (Criteria) this;
        }

        public Criteria andBookingtypeNotBetween(String value1, String value2) {
            addCriterion("bookingtype not between", value1, value2, "bookingtype");
            return (Criteria) this;
        }

        public Criteria andNotesIsNull() {
            addCriterion("notes is null");
            return (Criteria) this;
        }

        public Criteria andNotesIsNotNull() {
            addCriterion("notes is not null");
            return (Criteria) this;
        }

        public Criteria andNotesEqualTo(String value) {
            addCriterion("notes =", value, "notes");
            return (Criteria) this;
        }

        public Criteria andNotesNotEqualTo(String value) {
            addCriterion("notes <>", value, "notes");
            return (Criteria) this;
        }

        public Criteria andNotesGreaterThan(String value) {
            addCriterion("notes >", value, "notes");
            return (Criteria) this;
        }

        public Criteria andNotesGreaterThanOrEqualTo(String value) {
            addCriterion("notes >=", value, "notes");
            return (Criteria) this;
        }

        public Criteria andNotesLessThan(String value) {
            addCriterion("notes <", value, "notes");
            return (Criteria) this;
        }

        public Criteria andNotesLessThanOrEqualTo(String value) {
            addCriterion("notes <=", value, "notes");
            return (Criteria) this;
        }

        public Criteria andNotesLike(String value) {
            addCriterion("notes like", value, "notes");
            return (Criteria) this;
        }

        public Criteria andNotesNotLike(String value) {
            addCriterion("notes not like", value, "notes");
            return (Criteria) this;
        }

        public Criteria andNotesIn(List<String> values) {
            addCriterion("notes in", values, "notes");
            return (Criteria) this;
        }

        public Criteria andNotesNotIn(List<String> values) {
            addCriterion("notes not in", values, "notes");
            return (Criteria) this;
        }

        public Criteria andNotesBetween(String value1, String value2) {
            addCriterion("notes between", value1, value2, "notes");
            return (Criteria) this;
        }

        public Criteria andNotesNotBetween(String value1, String value2) {
            addCriterion("notes not between", value1, value2, "notes");
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

        public Criteria andAllocationIsNull() {
            addCriterion("allocation is null");
            return (Criteria) this;
        }

        public Criteria andAllocationIsNotNull() {
            addCriterion("allocation is not null");
            return (Criteria) this;
        }

        public Criteria andAllocationEqualTo(Integer value) {
            addCriterion("allocation =", value, "allocation");
            return (Criteria) this;
        }

        public Criteria andAllocationNotEqualTo(Integer value) {
            addCriterion("allocation <>", value, "allocation");
            return (Criteria) this;
        }

        public Criteria andAllocationGreaterThan(Integer value) {
            addCriterion("allocation >", value, "allocation");
            return (Criteria) this;
        }

        public Criteria andAllocationGreaterThanOrEqualTo(Integer value) {
            addCriterion("allocation >=", value, "allocation");
            return (Criteria) this;
        }

        public Criteria andAllocationLessThan(Integer value) {
            addCriterion("allocation <", value, "allocation");
            return (Criteria) this;
        }

        public Criteria andAllocationLessThanOrEqualTo(Integer value) {
            addCriterion("allocation <=", value, "allocation");
            return (Criteria) this;
        }

        public Criteria andAllocationIn(List<Integer> values) {
            addCriterion("allocation in", values, "allocation");
            return (Criteria) this;
        }

        public Criteria andAllocationNotIn(List<Integer> values) {
            addCriterion("allocation not in", values, "allocation");
            return (Criteria) this;
        }

        public Criteria andAllocationBetween(Integer value1, Integer value2) {
            addCriterion("allocation between", value1, value2, "allocation");
            return (Criteria) this;
        }

        public Criteria andAllocationNotBetween(Integer value1, Integer value2) {
            addCriterion("allocation not between", value1, value2, "allocation");
            return (Criteria) this;
        }

        public Criteria andStartdateIsNull() {
            addCriterion("startdate is null");
            return (Criteria) this;
        }

        public Criteria andStartdateIsNotNull() {
            addCriterion("startdate is not null");
            return (Criteria) this;
        }

        public Criteria andStartdateEqualTo(Date value) {
            addCriterion("startdate =", value, "startdate");
            return (Criteria) this;
        }

        public Criteria andStartdateNotEqualTo(Date value) {
            addCriterion("startdate <>", value, "startdate");
            return (Criteria) this;
        }

        public Criteria andStartdateGreaterThan(Date value) {
            addCriterion("startdate >", value, "startdate");
            return (Criteria) this;
        }

        public Criteria andStartdateGreaterThanOrEqualTo(Date value) {
            addCriterion("startdate >=", value, "startdate");
            return (Criteria) this;
        }

        public Criteria andStartdateLessThan(Date value) {
            addCriterion("startdate <", value, "startdate");
            return (Criteria) this;
        }

        public Criteria andStartdateLessThanOrEqualTo(Date value) {
            addCriterion("startdate <=", value, "startdate");
            return (Criteria) this;
        }

        public Criteria andStartdateIn(List<Date> values) {
            addCriterion("startdate in", values, "startdate");
            return (Criteria) this;
        }

        public Criteria andStartdateNotIn(List<Date> values) {
            addCriterion("startdate not in", values, "startdate");
            return (Criteria) this;
        }

        public Criteria andStartdateBetween(Date value1, Date value2) {
            addCriterion("startdate between", value1, value2, "startdate");
            return (Criteria) this;
        }

        public Criteria andStartdateNotBetween(Date value1, Date value2) {
            addCriterion("startdate not between", value1, value2, "startdate");
            return (Criteria) this;
        }

        public Criteria andEnddateIsNull() {
            addCriterion("enddate is null");
            return (Criteria) this;
        }

        public Criteria andEnddateIsNotNull() {
            addCriterion("enddate is not null");
            return (Criteria) this;
        }

        public Criteria andEnddateEqualTo(Date value) {
            addCriterion("enddate =", value, "enddate");
            return (Criteria) this;
        }

        public Criteria andEnddateNotEqualTo(Date value) {
            addCriterion("enddate <>", value, "enddate");
            return (Criteria) this;
        }

        public Criteria andEnddateGreaterThan(Date value) {
            addCriterion("enddate >", value, "enddate");
            return (Criteria) this;
        }

        public Criteria andEnddateGreaterThanOrEqualTo(Date value) {
            addCriterion("enddate >=", value, "enddate");
            return (Criteria) this;
        }

        public Criteria andEnddateLessThan(Date value) {
            addCriterion("enddate <", value, "enddate");
            return (Criteria) this;
        }

        public Criteria andEnddateLessThanOrEqualTo(Date value) {
            addCriterion("enddate <=", value, "enddate");
            return (Criteria) this;
        }

        public Criteria andEnddateIn(List<Date> values) {
            addCriterion("enddate in", values, "enddate");
            return (Criteria) this;
        }

        public Criteria andEnddateNotIn(List<Date> values) {
            addCriterion("enddate not in", values, "enddate");
            return (Criteria) this;
        }

        public Criteria andEnddateBetween(Date value1, Date value2) {
            addCriterion("enddate between", value1, value2, "enddate");
            return (Criteria) this;
        }

        public Criteria andEnddateNotBetween(Date value1, Date value2) {
            addCriterion("enddate not between", value1, value2, "enddate");
            return (Criteria) this;
        }

        public Criteria andWorkingtimerateIsNull() {
            addCriterion("workingtimerate is null");
            return (Criteria) this;
        }

        public Criteria andWorkingtimerateIsNotNull() {
            addCriterion("workingtimerate is not null");
            return (Criteria) this;
        }

        public Criteria andWorkingtimerateEqualTo(Double value) {
            addCriterion("workingtimerate =", value, "workingtimerate");
            return (Criteria) this;
        }

        public Criteria andWorkingtimerateNotEqualTo(Double value) {
            addCriterion("workingtimerate <>", value, "workingtimerate");
            return (Criteria) this;
        }

        public Criteria andWorkingtimerateGreaterThan(Double value) {
            addCriterion("workingtimerate >", value, "workingtimerate");
            return (Criteria) this;
        }

        public Criteria andWorkingtimerateGreaterThanOrEqualTo(Double value) {
            addCriterion("workingtimerate >=", value, "workingtimerate");
            return (Criteria) this;
        }

        public Criteria andWorkingtimerateLessThan(Double value) {
            addCriterion("workingtimerate <", value, "workingtimerate");
            return (Criteria) this;
        }

        public Criteria andWorkingtimerateLessThanOrEqualTo(Double value) {
            addCriterion("workingtimerate <=", value, "workingtimerate");
            return (Criteria) this;
        }

        public Criteria andWorkingtimerateIn(List<Double> values) {
            addCriterion("workingtimerate in", values, "workingtimerate");
            return (Criteria) this;
        }

        public Criteria andWorkingtimerateNotIn(List<Double> values) {
            addCriterion("workingtimerate not in", values, "workingtimerate");
            return (Criteria) this;
        }

        public Criteria andWorkingtimerateBetween(Double value1, Double value2) {
            addCriterion("workingtimerate between", value1, value2, "workingtimerate");
            return (Criteria) this;
        }

        public Criteria andWorkingtimerateNotBetween(Double value1, Double value2) {
            addCriterion("workingtimerate not between", value1, value2, "workingtimerate");
            return (Criteria) this;
        }

        public Criteria andOvertimerateIsNull() {
            addCriterion("overtimerate is null");
            return (Criteria) this;
        }

        public Criteria andOvertimerateIsNotNull() {
            addCriterion("overtimerate is not null");
            return (Criteria) this;
        }

        public Criteria andOvertimerateEqualTo(Double value) {
            addCriterion("overtimerate =", value, "overtimerate");
            return (Criteria) this;
        }

        public Criteria andOvertimerateNotEqualTo(Double value) {
            addCriterion("overtimerate <>", value, "overtimerate");
            return (Criteria) this;
        }

        public Criteria andOvertimerateGreaterThan(Double value) {
            addCriterion("overtimerate >", value, "overtimerate");
            return (Criteria) this;
        }

        public Criteria andOvertimerateGreaterThanOrEqualTo(Double value) {
            addCriterion("overtimerate >=", value, "overtimerate");
            return (Criteria) this;
        }

        public Criteria andOvertimerateLessThan(Double value) {
            addCriterion("overtimerate <", value, "overtimerate");
            return (Criteria) this;
        }

        public Criteria andOvertimerateLessThanOrEqualTo(Double value) {
            addCriterion("overtimerate <=", value, "overtimerate");
            return (Criteria) this;
        }

        public Criteria andOvertimerateIn(List<Double> values) {
            addCriterion("overtimerate in", values, "overtimerate");
            return (Criteria) this;
        }

        public Criteria andOvertimerateNotIn(List<Double> values) {
            addCriterion("overtimerate not in", values, "overtimerate");
            return (Criteria) this;
        }

        public Criteria andOvertimerateBetween(Double value1, Double value2) {
            addCriterion("overtimerate between", value1, value2, "overtimerate");
            return (Criteria) this;
        }

        public Criteria andOvertimerateNotBetween(Double value1, Double value2) {
            addCriterion("overtimerate not between", value1, value2, "overtimerate");
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
     * This class corresponds to the database table m_prj_resource
     *
     * @mbggenerated do_not_delete_during_merge Fri Dec 28 00:40:18 GMT+07:00 2012
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table m_prj_resource
     *
     * @mbggenerated Fri Dec 28 00:40:18 GMT+07:00 2012
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