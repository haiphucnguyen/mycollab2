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
package com.esofthead.mycollab.module.project.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SuppressWarnings("ucd")
public class PredecessorExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table m_prj_predecessor
     *
     * @mbggenerated Tue May 24 18:34:13 ICT 2016
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table m_prj_predecessor
     *
     * @mbggenerated Tue May 24 18:34:13 ICT 2016
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table m_prj_predecessor
     *
     * @mbggenerated Tue May 24 18:34:13 ICT 2016
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_predecessor
     *
     * @mbggenerated Tue May 24 18:34:13 ICT 2016
     */
    public PredecessorExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_predecessor
     *
     * @mbggenerated Tue May 24 18:34:13 ICT 2016
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_predecessor
     *
     * @mbggenerated Tue May 24 18:34:13 ICT 2016
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_predecessor
     *
     * @mbggenerated Tue May 24 18:34:13 ICT 2016
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_predecessor
     *
     * @mbggenerated Tue May 24 18:34:13 ICT 2016
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_predecessor
     *
     * @mbggenerated Tue May 24 18:34:13 ICT 2016
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_predecessor
     *
     * @mbggenerated Tue May 24 18:34:13 ICT 2016
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_predecessor
     *
     * @mbggenerated Tue May 24 18:34:13 ICT 2016
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_predecessor
     *
     * @mbggenerated Tue May 24 18:34:13 ICT 2016
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
     * This method corresponds to the database table m_prj_predecessor
     *
     * @mbggenerated Tue May 24 18:34:13 ICT 2016
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_predecessor
     *
     * @mbggenerated Tue May 24 18:34:13 ICT 2016
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table m_prj_predecessor
     *
     * @mbggenerated Tue May 24 18:34:13 ICT 2016
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

        public Criteria andSourcetypeIsNull() {
            addCriterion("sourceType is null");
            return (Criteria) this;
        }

        public Criteria andSourcetypeIsNotNull() {
            addCriterion("sourceType is not null");
            return (Criteria) this;
        }

        public Criteria andSourcetypeEqualTo(String value) {
            addCriterion("sourceType =", value, "sourcetype");
            return (Criteria) this;
        }

        public Criteria andSourcetypeNotEqualTo(String value) {
            addCriterion("sourceType <>", value, "sourcetype");
            return (Criteria) this;
        }

        public Criteria andSourcetypeGreaterThan(String value) {
            addCriterion("sourceType >", value, "sourcetype");
            return (Criteria) this;
        }

        public Criteria andSourcetypeGreaterThanOrEqualTo(String value) {
            addCriterion("sourceType >=", value, "sourcetype");
            return (Criteria) this;
        }

        public Criteria andSourcetypeLessThan(String value) {
            addCriterion("sourceType <", value, "sourcetype");
            return (Criteria) this;
        }

        public Criteria andSourcetypeLessThanOrEqualTo(String value) {
            addCriterion("sourceType <=", value, "sourcetype");
            return (Criteria) this;
        }

        public Criteria andSourcetypeLike(String value) {
            addCriterion("sourceType like", value, "sourcetype");
            return (Criteria) this;
        }

        public Criteria andSourcetypeNotLike(String value) {
            addCriterion("sourceType not like", value, "sourcetype");
            return (Criteria) this;
        }

        public Criteria andSourcetypeIn(List<String> values) {
            addCriterion("sourceType in", values, "sourcetype");
            return (Criteria) this;
        }

        public Criteria andSourcetypeNotIn(List<String> values) {
            addCriterion("sourceType not in", values, "sourcetype");
            return (Criteria) this;
        }

        public Criteria andSourcetypeBetween(String value1, String value2) {
            addCriterion("sourceType between", value1, value2, "sourcetype");
            return (Criteria) this;
        }

        public Criteria andSourcetypeNotBetween(String value1, String value2) {
            addCriterion("sourceType not between", value1, value2, "sourcetype");
            return (Criteria) this;
        }

        public Criteria andPredestypeIsNull() {
            addCriterion("predestype is null");
            return (Criteria) this;
        }

        public Criteria andPredestypeIsNotNull() {
            addCriterion("predestype is not null");
            return (Criteria) this;
        }

        public Criteria andPredestypeEqualTo(String value) {
            addCriterion("predestype =", value, "predestype");
            return (Criteria) this;
        }

        public Criteria andPredestypeNotEqualTo(String value) {
            addCriterion("predestype <>", value, "predestype");
            return (Criteria) this;
        }

        public Criteria andPredestypeGreaterThan(String value) {
            addCriterion("predestype >", value, "predestype");
            return (Criteria) this;
        }

        public Criteria andPredestypeGreaterThanOrEqualTo(String value) {
            addCriterion("predestype >=", value, "predestype");
            return (Criteria) this;
        }

        public Criteria andPredestypeLessThan(String value) {
            addCriterion("predestype <", value, "predestype");
            return (Criteria) this;
        }

        public Criteria andPredestypeLessThanOrEqualTo(String value) {
            addCriterion("predestype <=", value, "predestype");
            return (Criteria) this;
        }

        public Criteria andPredestypeLike(String value) {
            addCriterion("predestype like", value, "predestype");
            return (Criteria) this;
        }

        public Criteria andPredestypeNotLike(String value) {
            addCriterion("predestype not like", value, "predestype");
            return (Criteria) this;
        }

        public Criteria andPredestypeIn(List<String> values) {
            addCriterion("predestype in", values, "predestype");
            return (Criteria) this;
        }

        public Criteria andPredestypeNotIn(List<String> values) {
            addCriterion("predestype not in", values, "predestype");
            return (Criteria) this;
        }

        public Criteria andPredestypeBetween(String value1, String value2) {
            addCriterion("predestype between", value1, value2, "predestype");
            return (Criteria) this;
        }

        public Criteria andPredestypeNotBetween(String value1, String value2) {
            addCriterion("predestype not between", value1, value2, "predestype");
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

        public Criteria andCreateduserIsNull() {
            addCriterion("createdUser is null");
            return (Criteria) this;
        }

        public Criteria andCreateduserIsNotNull() {
            addCriterion("createdUser is not null");
            return (Criteria) this;
        }

        public Criteria andCreateduserEqualTo(String value) {
            addCriterion("createdUser =", value, "createduser");
            return (Criteria) this;
        }

        public Criteria andCreateduserNotEqualTo(String value) {
            addCriterion("createdUser <>", value, "createduser");
            return (Criteria) this;
        }

        public Criteria andCreateduserGreaterThan(String value) {
            addCriterion("createdUser >", value, "createduser");
            return (Criteria) this;
        }

        public Criteria andCreateduserGreaterThanOrEqualTo(String value) {
            addCriterion("createdUser >=", value, "createduser");
            return (Criteria) this;
        }

        public Criteria andCreateduserLessThan(String value) {
            addCriterion("createdUser <", value, "createduser");
            return (Criteria) this;
        }

        public Criteria andCreateduserLessThanOrEqualTo(String value) {
            addCriterion("createdUser <=", value, "createduser");
            return (Criteria) this;
        }

        public Criteria andCreateduserLike(String value) {
            addCriterion("createdUser like", value, "createduser");
            return (Criteria) this;
        }

        public Criteria andCreateduserNotLike(String value) {
            addCriterion("createdUser not like", value, "createduser");
            return (Criteria) this;
        }

        public Criteria andCreateduserIn(List<String> values) {
            addCriterion("createdUser in", values, "createduser");
            return (Criteria) this;
        }

        public Criteria andCreateduserNotIn(List<String> values) {
            addCriterion("createdUser not in", values, "createduser");
            return (Criteria) this;
        }

        public Criteria andCreateduserBetween(String value1, String value2) {
            addCriterion("createdUser between", value1, value2, "createduser");
            return (Criteria) this;
        }

        public Criteria andCreateduserNotBetween(String value1, String value2) {
            addCriterion("createdUser not between", value1, value2, "createduser");
            return (Criteria) this;
        }

        public Criteria andLagdayIsNull() {
            addCriterion("lagDay is null");
            return (Criteria) this;
        }

        public Criteria andLagdayIsNotNull() {
            addCriterion("lagDay is not null");
            return (Criteria) this;
        }

        public Criteria andLagdayEqualTo(Integer value) {
            addCriterion("lagDay =", value, "lagday");
            return (Criteria) this;
        }

        public Criteria andLagdayNotEqualTo(Integer value) {
            addCriterion("lagDay <>", value, "lagday");
            return (Criteria) this;
        }

        public Criteria andLagdayGreaterThan(Integer value) {
            addCriterion("lagDay >", value, "lagday");
            return (Criteria) this;
        }

        public Criteria andLagdayGreaterThanOrEqualTo(Integer value) {
            addCriterion("lagDay >=", value, "lagday");
            return (Criteria) this;
        }

        public Criteria andLagdayLessThan(Integer value) {
            addCriterion("lagDay <", value, "lagday");
            return (Criteria) this;
        }

        public Criteria andLagdayLessThanOrEqualTo(Integer value) {
            addCriterion("lagDay <=", value, "lagday");
            return (Criteria) this;
        }

        public Criteria andLagdayIn(List<Integer> values) {
            addCriterion("lagDay in", values, "lagday");
            return (Criteria) this;
        }

        public Criteria andLagdayNotIn(List<Integer> values) {
            addCriterion("lagDay not in", values, "lagday");
            return (Criteria) this;
        }

        public Criteria andLagdayBetween(Integer value1, Integer value2) {
            addCriterion("lagDay between", value1, value2, "lagday");
            return (Criteria) this;
        }

        public Criteria andLagdayNotBetween(Integer value1, Integer value2) {
            addCriterion("lagDay not between", value1, value2, "lagday");
            return (Criteria) this;
        }

        public Criteria andSourceidIsNull() {
            addCriterion("sourceId is null");
            return (Criteria) this;
        }

        public Criteria andSourceidIsNotNull() {
            addCriterion("sourceId is not null");
            return (Criteria) this;
        }

        public Criteria andSourceidEqualTo(Integer value) {
            addCriterion("sourceId =", value, "sourceid");
            return (Criteria) this;
        }

        public Criteria andSourceidNotEqualTo(Integer value) {
            addCriterion("sourceId <>", value, "sourceid");
            return (Criteria) this;
        }

        public Criteria andSourceidGreaterThan(Integer value) {
            addCriterion("sourceId >", value, "sourceid");
            return (Criteria) this;
        }

        public Criteria andSourceidGreaterThanOrEqualTo(Integer value) {
            addCriterion("sourceId >=", value, "sourceid");
            return (Criteria) this;
        }

        public Criteria andSourceidLessThan(Integer value) {
            addCriterion("sourceId <", value, "sourceid");
            return (Criteria) this;
        }

        public Criteria andSourceidLessThanOrEqualTo(Integer value) {
            addCriterion("sourceId <=", value, "sourceid");
            return (Criteria) this;
        }

        public Criteria andSourceidIn(List<Integer> values) {
            addCriterion("sourceId in", values, "sourceid");
            return (Criteria) this;
        }

        public Criteria andSourceidNotIn(List<Integer> values) {
            addCriterion("sourceId not in", values, "sourceid");
            return (Criteria) this;
        }

        public Criteria andSourceidBetween(Integer value1, Integer value2) {
            addCriterion("sourceId between", value1, value2, "sourceid");
            return (Criteria) this;
        }

        public Criteria andSourceidNotBetween(Integer value1, Integer value2) {
            addCriterion("sourceId not between", value1, value2, "sourceid");
            return (Criteria) this;
        }

        public Criteria andDescidIsNull() {
            addCriterion("descId is null");
            return (Criteria) this;
        }

        public Criteria andDescidIsNotNull() {
            addCriterion("descId is not null");
            return (Criteria) this;
        }

        public Criteria andDescidEqualTo(Integer value) {
            addCriterion("descId =", value, "descid");
            return (Criteria) this;
        }

        public Criteria andDescidNotEqualTo(Integer value) {
            addCriterion("descId <>", value, "descid");
            return (Criteria) this;
        }

        public Criteria andDescidGreaterThan(Integer value) {
            addCriterion("descId >", value, "descid");
            return (Criteria) this;
        }

        public Criteria andDescidGreaterThanOrEqualTo(Integer value) {
            addCriterion("descId >=", value, "descid");
            return (Criteria) this;
        }

        public Criteria andDescidLessThan(Integer value) {
            addCriterion("descId <", value, "descid");
            return (Criteria) this;
        }

        public Criteria andDescidLessThanOrEqualTo(Integer value) {
            addCriterion("descId <=", value, "descid");
            return (Criteria) this;
        }

        public Criteria andDescidIn(List<Integer> values) {
            addCriterion("descId in", values, "descid");
            return (Criteria) this;
        }

        public Criteria andDescidNotIn(List<Integer> values) {
            addCriterion("descId not in", values, "descid");
            return (Criteria) this;
        }

        public Criteria andDescidBetween(Integer value1, Integer value2) {
            addCriterion("descId between", value1, value2, "descid");
            return (Criteria) this;
        }

        public Criteria andDescidNotBetween(Integer value1, Integer value2) {
            addCriterion("descId not between", value1, value2, "descid");
            return (Criteria) this;
        }

        public Criteria andDesctypeIsNull() {
            addCriterion("descType is null");
            return (Criteria) this;
        }

        public Criteria andDesctypeIsNotNull() {
            addCriterion("descType is not null");
            return (Criteria) this;
        }

        public Criteria andDesctypeEqualTo(String value) {
            addCriterion("descType =", value, "desctype");
            return (Criteria) this;
        }

        public Criteria andDesctypeNotEqualTo(String value) {
            addCriterion("descType <>", value, "desctype");
            return (Criteria) this;
        }

        public Criteria andDesctypeGreaterThan(String value) {
            addCriterion("descType >", value, "desctype");
            return (Criteria) this;
        }

        public Criteria andDesctypeGreaterThanOrEqualTo(String value) {
            addCriterion("descType >=", value, "desctype");
            return (Criteria) this;
        }

        public Criteria andDesctypeLessThan(String value) {
            addCriterion("descType <", value, "desctype");
            return (Criteria) this;
        }

        public Criteria andDesctypeLessThanOrEqualTo(String value) {
            addCriterion("descType <=", value, "desctype");
            return (Criteria) this;
        }

        public Criteria andDesctypeLike(String value) {
            addCriterion("descType like", value, "desctype");
            return (Criteria) this;
        }

        public Criteria andDesctypeNotLike(String value) {
            addCriterion("descType not like", value, "desctype");
            return (Criteria) this;
        }

        public Criteria andDesctypeIn(List<String> values) {
            addCriterion("descType in", values, "desctype");
            return (Criteria) this;
        }

        public Criteria andDesctypeNotIn(List<String> values) {
            addCriterion("descType not in", values, "desctype");
            return (Criteria) this;
        }

        public Criteria andDesctypeBetween(String value1, String value2) {
            addCriterion("descType between", value1, value2, "desctype");
            return (Criteria) this;
        }

        public Criteria andDesctypeNotBetween(String value1, String value2) {
            addCriterion("descType not between", value1, value2, "desctype");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table m_prj_predecessor
     *
     * @mbggenerated do_not_delete_during_merge Tue May 24 18:34:13 ICT 2016
     */
    @SuppressWarnings("ucd")
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table m_prj_predecessor
     *
     * @mbggenerated Tue May 24 18:34:13 ICT 2016
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