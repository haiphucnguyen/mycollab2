/**
 * This file is part of mycollab-services.
 *
 * mycollab-services is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-services is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-services.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.common.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@SuppressWarnings("ucd")
public class TimelineTrackingExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table s_timeline_tracking
     *
     * @mbggenerated Tue Nov 10 12:09:23 ICT 2015
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table s_timeline_tracking
     *
     * @mbggenerated Tue Nov 10 12:09:23 ICT 2015
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table s_timeline_tracking
     *
     * @mbggenerated Tue Nov 10 12:09:23 ICT 2015
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_timeline_tracking
     *
     * @mbggenerated Tue Nov 10 12:09:23 ICT 2015
     */
    public TimelineTrackingExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_timeline_tracking
     *
     * @mbggenerated Tue Nov 10 12:09:23 ICT 2015
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_timeline_tracking
     *
     * @mbggenerated Tue Nov 10 12:09:23 ICT 2015
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_timeline_tracking
     *
     * @mbggenerated Tue Nov 10 12:09:23 ICT 2015
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_timeline_tracking
     *
     * @mbggenerated Tue Nov 10 12:09:23 ICT 2015
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_timeline_tracking
     *
     * @mbggenerated Tue Nov 10 12:09:23 ICT 2015
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_timeline_tracking
     *
     * @mbggenerated Tue Nov 10 12:09:23 ICT 2015
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_timeline_tracking
     *
     * @mbggenerated Tue Nov 10 12:09:23 ICT 2015
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
     * @mbggenerated Tue Nov 10 12:09:23 ICT 2015
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
     * @mbggenerated Tue Nov 10 12:09:23 ICT 2015
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_timeline_tracking
     *
     * @mbggenerated Tue Nov 10 12:09:23 ICT 2015
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
     * @mbggenerated Tue Nov 10 12:09:23 ICT 2015
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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
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

        public Criteria andFordayEqualTo(Date value) {
            addCriterionForJDBCDate("forDay =", value, "forday");
            return (Criteria) this;
        }

        public Criteria andFordayNotEqualTo(Date value) {
            addCriterionForJDBCDate("forDay <>", value, "forday");
            return (Criteria) this;
        }

        public Criteria andFordayGreaterThan(Date value) {
            addCriterionForJDBCDate("forDay >", value, "forday");
            return (Criteria) this;
        }

        public Criteria andFordayGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("forDay >=", value, "forday");
            return (Criteria) this;
        }

        public Criteria andFordayLessThan(Date value) {
            addCriterionForJDBCDate("forDay <", value, "forday");
            return (Criteria) this;
        }

        public Criteria andFordayLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("forDay <=", value, "forday");
            return (Criteria) this;
        }

        public Criteria andFordayIn(List<Date> values) {
            addCriterionForJDBCDate("forDay in", values, "forday");
            return (Criteria) this;
        }

        public Criteria andFordayNotIn(List<Date> values) {
            addCriterionForJDBCDate("forDay not in", values, "forday");
            return (Criteria) this;
        }

        public Criteria andFordayBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("forDay between", value1, value2, "forday");
            return (Criteria) this;
        }

        public Criteria andFordayNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("forDay not between", value1, value2, "forday");
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
     * @mbggenerated do_not_delete_during_merge Tue Nov 10 12:09:23 ICT 2015
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
     * @mbggenerated Tue Nov 10 12:09:23 ICT 2015
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