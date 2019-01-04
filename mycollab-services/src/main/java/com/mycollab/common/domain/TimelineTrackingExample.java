package com.mycollab.common.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("ucd")
public class TimelineTrackingExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table s_timeline_tracking
     *
     * @mbg.generated Thu Jan 03 18:09:39 CST 2019
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table s_timeline_tracking
     *
     * @mbg.generated Thu Jan 03 18:09:39 CST 2019
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table s_timeline_tracking
     *
     * @mbg.generated Thu Jan 03 18:09:39 CST 2019
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_timeline_tracking
     *
     * @mbg.generated Thu Jan 03 18:09:39 CST 2019
     */
    public TimelineTrackingExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_timeline_tracking
     *
     * @mbg.generated Thu Jan 03 18:09:39 CST 2019
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_timeline_tracking
     *
     * @mbg.generated Thu Jan 03 18:09:39 CST 2019
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_timeline_tracking
     *
     * @mbg.generated Thu Jan 03 18:09:39 CST 2019
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_timeline_tracking
     *
     * @mbg.generated Thu Jan 03 18:09:39 CST 2019
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_timeline_tracking
     *
     * @mbg.generated Thu Jan 03 18:09:39 CST 2019
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_timeline_tracking
     *
     * @mbg.generated Thu Jan 03 18:09:39 CST 2019
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_timeline_tracking
     *
     * @mbg.generated Thu Jan 03 18:09:39 CST 2019
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_timeline_tracking
     *
     * @mbg.generated Thu Jan 03 18:09:39 CST 2019
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
     * This method corresponds to the database table s_timeline_tracking
     *
     * @mbg.generated Thu Jan 03 18:09:39 CST 2019
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_timeline_tracking
     *
     * @mbg.generated Thu Jan 03 18:09:39 CST 2019
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table s_timeline_tracking
     *
     * @mbg.generated Thu Jan 03 18:09:39 CST 2019
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
            addCriterion("typeId is null");
            return (Criteria) this;
        }

        public Criteria andTypeidIsNotNull() {
            addCriterion("typeId is not null");
            return (Criteria) this;
        }

        public Criteria andTypeidEqualTo(Integer value) {
            addCriterion("typeId =", value, "typeid");
            return (Criteria) this;
        }

        public Criteria andTypeidNotEqualTo(Integer value) {
            addCriterion("typeId <>", value, "typeid");
            return (Criteria) this;
        }

        public Criteria andTypeidGreaterThan(Integer value) {
            addCriterion("typeId >", value, "typeid");
            return (Criteria) this;
        }

        public Criteria andTypeidGreaterThanOrEqualTo(Integer value) {
            addCriterion("typeId >=", value, "typeid");
            return (Criteria) this;
        }

        public Criteria andTypeidLessThan(Integer value) {
            addCriterion("typeId <", value, "typeid");
            return (Criteria) this;
        }

        public Criteria andTypeidLessThanOrEqualTo(Integer value) {
            addCriterion("typeId <=", value, "typeid");
            return (Criteria) this;
        }

        public Criteria andTypeidIn(List<Integer> values) {
            addCriterion("typeId in", values, "typeid");
            return (Criteria) this;
        }

        public Criteria andTypeidNotIn(List<Integer> values) {
            addCriterion("typeId not in", values, "typeid");
            return (Criteria) this;
        }

        public Criteria andTypeidBetween(Integer value1, Integer value2) {
            addCriterion("typeId between", value1, value2, "typeid");
            return (Criteria) this;
        }

        public Criteria andTypeidNotBetween(Integer value1, Integer value2) {
            addCriterion("typeId not between", value1, value2, "typeid");
            return (Criteria) this;
        }

        public Criteria andFieldvalIsNull() {
            addCriterion("fieldval is null");
            return (Criteria) this;
        }

        public Criteria andFieldvalIsNotNull() {
            addCriterion("fieldval is not null");
            return (Criteria) this;
        }

        public Criteria andFieldvalEqualTo(String value) {
            addCriterion("fieldval =", value, "fieldval");
            return (Criteria) this;
        }

        public Criteria andFieldvalNotEqualTo(String value) {
            addCriterion("fieldval <>", value, "fieldval");
            return (Criteria) this;
        }

        public Criteria andFieldvalGreaterThan(String value) {
            addCriterion("fieldval >", value, "fieldval");
            return (Criteria) this;
        }

        public Criteria andFieldvalGreaterThanOrEqualTo(String value) {
            addCriterion("fieldval >=", value, "fieldval");
            return (Criteria) this;
        }

        public Criteria andFieldvalLessThan(String value) {
            addCriterion("fieldval <", value, "fieldval");
            return (Criteria) this;
        }

        public Criteria andFieldvalLessThanOrEqualTo(String value) {
            addCriterion("fieldval <=", value, "fieldval");
            return (Criteria) this;
        }

        public Criteria andFieldvalLike(String value) {
            addCriterion("fieldval like", value, "fieldval");
            return (Criteria) this;
        }

        public Criteria andFieldvalNotLike(String value) {
            addCriterion("fieldval not like", value, "fieldval");
            return (Criteria) this;
        }

        public Criteria andFieldvalIn(List<String> values) {
            addCriterion("fieldval in", values, "fieldval");
            return (Criteria) this;
        }

        public Criteria andFieldvalNotIn(List<String> values) {
            addCriterion("fieldval not in", values, "fieldval");
            return (Criteria) this;
        }

        public Criteria andFieldvalBetween(String value1, String value2) {
            addCriterion("fieldval between", value1, value2, "fieldval");
            return (Criteria) this;
        }

        public Criteria andFieldvalNotBetween(String value1, String value2) {
            addCriterion("fieldval not between", value1, value2, "fieldval");
            return (Criteria) this;
        }

        public Criteria andFieldgroupIsNull() {
            addCriterion("fieldgroup is null");
            return (Criteria) this;
        }

        public Criteria andFieldgroupIsNotNull() {
            addCriterion("fieldgroup is not null");
            return (Criteria) this;
        }

        public Criteria andFieldgroupEqualTo(String value) {
            addCriterion("fieldgroup =", value, "fieldgroup");
            return (Criteria) this;
        }

        public Criteria andFieldgroupNotEqualTo(String value) {
            addCriterion("fieldgroup <>", value, "fieldgroup");
            return (Criteria) this;
        }

        public Criteria andFieldgroupGreaterThan(String value) {
            addCriterion("fieldgroup >", value, "fieldgroup");
            return (Criteria) this;
        }

        public Criteria andFieldgroupGreaterThanOrEqualTo(String value) {
            addCriterion("fieldgroup >=", value, "fieldgroup");
            return (Criteria) this;
        }

        public Criteria andFieldgroupLessThan(String value) {
            addCriterion("fieldgroup <", value, "fieldgroup");
            return (Criteria) this;
        }

        public Criteria andFieldgroupLessThanOrEqualTo(String value) {
            addCriterion("fieldgroup <=", value, "fieldgroup");
            return (Criteria) this;
        }

        public Criteria andFieldgroupLike(String value) {
            addCriterion("fieldgroup like", value, "fieldgroup");
            return (Criteria) this;
        }

        public Criteria andFieldgroupNotLike(String value) {
            addCriterion("fieldgroup not like", value, "fieldgroup");
            return (Criteria) this;
        }

        public Criteria andFieldgroupIn(List<String> values) {
            addCriterion("fieldgroup in", values, "fieldgroup");
            return (Criteria) this;
        }

        public Criteria andFieldgroupNotIn(List<String> values) {
            addCriterion("fieldgroup not in", values, "fieldgroup");
            return (Criteria) this;
        }

        public Criteria andFieldgroupBetween(String value1, String value2) {
            addCriterion("fieldgroup between", value1, value2, "fieldgroup");
            return (Criteria) this;
        }

        public Criteria andFieldgroupNotBetween(String value1, String value2) {
            addCriterion("fieldgroup not between", value1, value2, "fieldgroup");
            return (Criteria) this;
        }

        public Criteria andExtratypeidIsNull() {
            addCriterion("extratypeid is null");
            return (Criteria) this;
        }

        public Criteria andExtratypeidIsNotNull() {
            addCriterion("extratypeid is not null");
            return (Criteria) this;
        }

        public Criteria andExtratypeidEqualTo(Integer value) {
            addCriterion("extratypeid =", value, "extratypeid");
            return (Criteria) this;
        }

        public Criteria andExtratypeidNotEqualTo(Integer value) {
            addCriterion("extratypeid <>", value, "extratypeid");
            return (Criteria) this;
        }

        public Criteria andExtratypeidGreaterThan(Integer value) {
            addCriterion("extratypeid >", value, "extratypeid");
            return (Criteria) this;
        }

        public Criteria andExtratypeidGreaterThanOrEqualTo(Integer value) {
            addCriterion("extratypeid >=", value, "extratypeid");
            return (Criteria) this;
        }

        public Criteria andExtratypeidLessThan(Integer value) {
            addCriterion("extratypeid <", value, "extratypeid");
            return (Criteria) this;
        }

        public Criteria andExtratypeidLessThanOrEqualTo(Integer value) {
            addCriterion("extratypeid <=", value, "extratypeid");
            return (Criteria) this;
        }

        public Criteria andExtratypeidIn(List<Integer> values) {
            addCriterion("extratypeid in", values, "extratypeid");
            return (Criteria) this;
        }

        public Criteria andExtratypeidNotIn(List<Integer> values) {
            addCriterion("extratypeid not in", values, "extratypeid");
            return (Criteria) this;
        }

        public Criteria andExtratypeidBetween(Integer value1, Integer value2) {
            addCriterion("extratypeid between", value1, value2, "extratypeid");
            return (Criteria) this;
        }

        public Criteria andExtratypeidNotBetween(Integer value1, Integer value2) {
            addCriterion("extratypeid not between", value1, value2, "extratypeid");
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

        public Criteria andFordayIsNull() {
            addCriterion("forDay is null");
            return (Criteria) this;
        }

        public Criteria andFordayIsNotNull() {
            addCriterion("forDay is not null");
            return (Criteria) this;
        }

        public Criteria andFordayEqualTo(LocalDate value) {
            addCriterion("forDay =", value, "forday");
            return (Criteria) this;
        }

        public Criteria andFordayNotEqualTo(LocalDate value) {
            addCriterion("forDay <>", value, "forday");
            return (Criteria) this;
        }

        public Criteria andFordayGreaterThan(LocalDate value) {
            addCriterion("forDay >", value, "forday");
            return (Criteria) this;
        }

        public Criteria andFordayGreaterThanOrEqualTo(LocalDate value) {
            addCriterion("forDay >=", value, "forday");
            return (Criteria) this;
        }

        public Criteria andFordayLessThan(LocalDate value) {
            addCriterion("forDay <", value, "forday");
            return (Criteria) this;
        }

        public Criteria andFordayLessThanOrEqualTo(LocalDate value) {
            addCriterion("forDay <=", value, "forday");
            return (Criteria) this;
        }

        public Criteria andFordayIn(List<LocalDate> values) {
            addCriterion("forDay in", values, "forday");
            return (Criteria) this;
        }

        public Criteria andFordayNotIn(List<LocalDate> values) {
            addCriterion("forDay not in", values, "forday");
            return (Criteria) this;
        }

        public Criteria andFordayBetween(LocalDate value1, LocalDate value2) {
            addCriterion("forDay between", value1, value2, "forday");
            return (Criteria) this;
        }

        public Criteria andFordayNotBetween(LocalDate value1, LocalDate value2) {
            addCriterion("forDay not between", value1, value2, "forday");
            return (Criteria) this;
        }

        public Criteria andFlagIsNull() {
            addCriterion("flag is null");
            return (Criteria) this;
        }

        public Criteria andFlagIsNotNull() {
            addCriterion("flag is not null");
            return (Criteria) this;
        }

        public Criteria andFlagEqualTo(Byte value) {
            addCriterion("flag =", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagNotEqualTo(Byte value) {
            addCriterion("flag <>", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagGreaterThan(Byte value) {
            addCriterion("flag >", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagGreaterThanOrEqualTo(Byte value) {
            addCriterion("flag >=", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagLessThan(Byte value) {
            addCriterion("flag <", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagLessThanOrEqualTo(Byte value) {
            addCriterion("flag <=", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagIn(List<Byte> values) {
            addCriterion("flag in", values, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagNotIn(List<Byte> values) {
            addCriterion("flag not in", values, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagBetween(Byte value1, Byte value2) {
            addCriterion("flag between", value1, value2, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagNotBetween(Byte value1, Byte value2) {
            addCriterion("flag not between", value1, value2, "flag");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table s_timeline_tracking
     *
     * @mbg.generated do_not_delete_during_merge Thu Jan 03 18:09:39 CST 2019
     */
    @SuppressWarnings("ucd")
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table s_timeline_tracking
     *
     * @mbg.generated Thu Jan 03 18:09:39 CST 2019
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