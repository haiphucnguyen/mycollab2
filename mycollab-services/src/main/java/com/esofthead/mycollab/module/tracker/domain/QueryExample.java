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
package com.esofthead.mycollab.module.tracker.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SuppressWarnings("ucd")
public class QueryExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table m_tracker_query
     *
     * @mbggenerated Fri Mar 06 17:04:14 ICT 2015
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table m_tracker_query
     *
     * @mbggenerated Fri Mar 06 17:04:14 ICT 2015
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table m_tracker_query
     *
     * @mbggenerated Fri Mar 06 17:04:14 ICT 2015
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_query
     *
     * @mbggenerated Fri Mar 06 17:04:14 ICT 2015
     */
    public QueryExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_query
     *
     * @mbggenerated Fri Mar 06 17:04:14 ICT 2015
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_query
     *
     * @mbggenerated Fri Mar 06 17:04:14 ICT 2015
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_query
     *
     * @mbggenerated Fri Mar 06 17:04:14 ICT 2015
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_query
     *
     * @mbggenerated Fri Mar 06 17:04:14 ICT 2015
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_query
     *
     * @mbggenerated Fri Mar 06 17:04:14 ICT 2015
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_query
     *
     * @mbggenerated Fri Mar 06 17:04:14 ICT 2015
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_query
     *
     * @mbggenerated Fri Mar 06 17:04:14 ICT 2015
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_query
     *
     * @mbggenerated Fri Mar 06 17:04:14 ICT 2015
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
     * This method corresponds to the database table m_tracker_query
     *
     * @mbggenerated Fri Mar 06 17:04:14 ICT 2015
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_query
     *
     * @mbggenerated Fri Mar 06 17:04:14 ICT 2015
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table m_tracker_query
     *
     * @mbggenerated Fri Mar 06 17:04:14 ICT 2015
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

        public Criteria andQuerynameIsNull() {
            addCriterion("queryname is null");
            return (Criteria) this;
        }

        public Criteria andQuerynameIsNotNull() {
            addCriterion("queryname is not null");
            return (Criteria) this;
        }

        public Criteria andQuerynameEqualTo(String value) {
            addCriterion("queryname =", value, "queryname");
            return (Criteria) this;
        }

        public Criteria andQuerynameNotEqualTo(String value) {
            addCriterion("queryname <>", value, "queryname");
            return (Criteria) this;
        }

        public Criteria andQuerynameGreaterThan(String value) {
            addCriterion("queryname >", value, "queryname");
            return (Criteria) this;
        }

        public Criteria andQuerynameGreaterThanOrEqualTo(String value) {
            addCriterion("queryname >=", value, "queryname");
            return (Criteria) this;
        }

        public Criteria andQuerynameLessThan(String value) {
            addCriterion("queryname <", value, "queryname");
            return (Criteria) this;
        }

        public Criteria andQuerynameLessThanOrEqualTo(String value) {
            addCriterion("queryname <=", value, "queryname");
            return (Criteria) this;
        }

        public Criteria andQuerynameLike(String value) {
            addCriterion("queryname like", value, "queryname");
            return (Criteria) this;
        }

        public Criteria andQuerynameNotLike(String value) {
            addCriterion("queryname not like", value, "queryname");
            return (Criteria) this;
        }

        public Criteria andQuerynameIn(List<String> values) {
            addCriterion("queryname in", values, "queryname");
            return (Criteria) this;
        }

        public Criteria andQuerynameNotIn(List<String> values) {
            addCriterion("queryname not in", values, "queryname");
            return (Criteria) this;
        }

        public Criteria andQuerynameBetween(String value1, String value2) {
            addCriterion("queryname between", value1, value2, "queryname");
            return (Criteria) this;
        }

        public Criteria andQuerynameNotBetween(String value1, String value2) {
            addCriterion("queryname not between", value1, value2, "queryname");
            return (Criteria) this;
        }

        public Criteria andSharetypeIsNull() {
            addCriterion("sharetype is null");
            return (Criteria) this;
        }

        public Criteria andSharetypeIsNotNull() {
            addCriterion("sharetype is not null");
            return (Criteria) this;
        }

        public Criteria andSharetypeEqualTo(Integer value) {
            addCriterion("sharetype =", value, "sharetype");
            return (Criteria) this;
        }

        public Criteria andSharetypeNotEqualTo(Integer value) {
            addCriterion("sharetype <>", value, "sharetype");
            return (Criteria) this;
        }

        public Criteria andSharetypeGreaterThan(Integer value) {
            addCriterion("sharetype >", value, "sharetype");
            return (Criteria) this;
        }

        public Criteria andSharetypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("sharetype >=", value, "sharetype");
            return (Criteria) this;
        }

        public Criteria andSharetypeLessThan(Integer value) {
            addCriterion("sharetype <", value, "sharetype");
            return (Criteria) this;
        }

        public Criteria andSharetypeLessThanOrEqualTo(Integer value) {
            addCriterion("sharetype <=", value, "sharetype");
            return (Criteria) this;
        }

        public Criteria andSharetypeIn(List<Integer> values) {
            addCriterion("sharetype in", values, "sharetype");
            return (Criteria) this;
        }

        public Criteria andSharetypeNotIn(List<Integer> values) {
            addCriterion("sharetype not in", values, "sharetype");
            return (Criteria) this;
        }

        public Criteria andSharetypeBetween(Integer value1, Integer value2) {
            addCriterion("sharetype between", value1, value2, "sharetype");
            return (Criteria) this;
        }

        public Criteria andSharetypeNotBetween(Integer value1, Integer value2) {
            addCriterion("sharetype not between", value1, value2, "sharetype");
            return (Criteria) this;
        }

        public Criteria andCreateddateIsNull() {
            addCriterion("createddate is null");
            return (Criteria) this;
        }

        public Criteria andCreateddateIsNotNull() {
            addCriterion("createddate is not null");
            return (Criteria) this;
        }

        public Criteria andCreateddateEqualTo(Date value) {
            addCriterion("createddate =", value, "createddate");
            return (Criteria) this;
        }

        public Criteria andCreateddateNotEqualTo(Date value) {
            addCriterion("createddate <>", value, "createddate");
            return (Criteria) this;
        }

        public Criteria andCreateddateGreaterThan(Date value) {
            addCriterion("createddate >", value, "createddate");
            return (Criteria) this;
        }

        public Criteria andCreateddateGreaterThanOrEqualTo(Date value) {
            addCriterion("createddate >=", value, "createddate");
            return (Criteria) this;
        }

        public Criteria andCreateddateLessThan(Date value) {
            addCriterion("createddate <", value, "createddate");
            return (Criteria) this;
        }

        public Criteria andCreateddateLessThanOrEqualTo(Date value) {
            addCriterion("createddate <=", value, "createddate");
            return (Criteria) this;
        }

        public Criteria andCreateddateIn(List<Date> values) {
            addCriterion("createddate in", values, "createddate");
            return (Criteria) this;
        }

        public Criteria andCreateddateNotIn(List<Date> values) {
            addCriterion("createddate not in", values, "createddate");
            return (Criteria) this;
        }

        public Criteria andCreateddateBetween(Date value1, Date value2) {
            addCriterion("createddate between", value1, value2, "createddate");
            return (Criteria) this;
        }

        public Criteria andCreateddateNotBetween(Date value1, Date value2) {
            addCriterion("createddate not between", value1, value2, "createddate");
            return (Criteria) this;
        }

        public Criteria andUpdateddateIsNull() {
            addCriterion("updateddate is null");
            return (Criteria) this;
        }

        public Criteria andUpdateddateIsNotNull() {
            addCriterion("updateddate is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateddateEqualTo(Date value) {
            addCriterion("updateddate =", value, "updateddate");
            return (Criteria) this;
        }

        public Criteria andUpdateddateNotEqualTo(Date value) {
            addCriterion("updateddate <>", value, "updateddate");
            return (Criteria) this;
        }

        public Criteria andUpdateddateGreaterThan(Date value) {
            addCriterion("updateddate >", value, "updateddate");
            return (Criteria) this;
        }

        public Criteria andUpdateddateGreaterThanOrEqualTo(Date value) {
            addCriterion("updateddate >=", value, "updateddate");
            return (Criteria) this;
        }

        public Criteria andUpdateddateLessThan(Date value) {
            addCriterion("updateddate <", value, "updateddate");
            return (Criteria) this;
        }

        public Criteria andUpdateddateLessThanOrEqualTo(Date value) {
            addCriterion("updateddate <=", value, "updateddate");
            return (Criteria) this;
        }

        public Criteria andUpdateddateIn(List<Date> values) {
            addCriterion("updateddate in", values, "updateddate");
            return (Criteria) this;
        }

        public Criteria andUpdateddateNotIn(List<Date> values) {
            addCriterion("updateddate not in", values, "updateddate");
            return (Criteria) this;
        }

        public Criteria andUpdateddateBetween(Date value1, Date value2) {
            addCriterion("updateddate between", value1, value2, "updateddate");
            return (Criteria) this;
        }

        public Criteria andUpdateddateNotBetween(Date value1, Date value2) {
            addCriterion("updateddate not between", value1, value2, "updateddate");
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

        public Criteria andQuerytextIsNull() {
            addCriterion("querytext is null");
            return (Criteria) this;
        }

        public Criteria andQuerytextIsNotNull() {
            addCriterion("querytext is not null");
            return (Criteria) this;
        }

        public Criteria andQuerytextEqualTo(String value) {
            addCriterion("querytext =", value, "querytext");
            return (Criteria) this;
        }

        public Criteria andQuerytextNotEqualTo(String value) {
            addCriterion("querytext <>", value, "querytext");
            return (Criteria) this;
        }

        public Criteria andQuerytextGreaterThan(String value) {
            addCriterion("querytext >", value, "querytext");
            return (Criteria) this;
        }

        public Criteria andQuerytextGreaterThanOrEqualTo(String value) {
            addCriterion("querytext >=", value, "querytext");
            return (Criteria) this;
        }

        public Criteria andQuerytextLessThan(String value) {
            addCriterion("querytext <", value, "querytext");
            return (Criteria) this;
        }

        public Criteria andQuerytextLessThanOrEqualTo(String value) {
            addCriterion("querytext <=", value, "querytext");
            return (Criteria) this;
        }

        public Criteria andQuerytextLike(String value) {
            addCriterion("querytext like", value, "querytext");
            return (Criteria) this;
        }

        public Criteria andQuerytextNotLike(String value) {
            addCriterion("querytext not like", value, "querytext");
            return (Criteria) this;
        }

        public Criteria andQuerytextIn(List<String> values) {
            addCriterion("querytext in", values, "querytext");
            return (Criteria) this;
        }

        public Criteria andQuerytextNotIn(List<String> values) {
            addCriterion("querytext not in", values, "querytext");
            return (Criteria) this;
        }

        public Criteria andQuerytextBetween(String value1, String value2) {
            addCriterion("querytext between", value1, value2, "querytext");
            return (Criteria) this;
        }

        public Criteria andQuerytextNotBetween(String value1, String value2) {
            addCriterion("querytext not between", value1, value2, "querytext");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNull() {
            addCriterion("description is null");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNotNull() {
            addCriterion("description is not null");
            return (Criteria) this;
        }

        public Criteria andDescriptionEqualTo(String value) {
            addCriterion("description =", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotEqualTo(String value) {
            addCriterion("description <>", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThan(String value) {
            addCriterion("description >", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("description >=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThan(String value) {
            addCriterion("description <", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThanOrEqualTo(String value) {
            addCriterion("description <=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLike(String value) {
            addCriterion("description like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotLike(String value) {
            addCriterion("description not like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionIn(List<String> values) {
            addCriterion("description in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotIn(List<String> values) {
            addCriterion("description not in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionBetween(String value1, String value2) {
            addCriterion("description between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotBetween(String value1, String value2) {
            addCriterion("description not between", value1, value2, "description");
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
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table m_tracker_query
     *
     * @mbggenerated do_not_delete_during_merge Fri Mar 06 17:04:14 ICT 2015
     */
    @SuppressWarnings("ucd")
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table m_tracker_query
     *
     * @mbggenerated Fri Mar 06 17:04:14 ICT 2015
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