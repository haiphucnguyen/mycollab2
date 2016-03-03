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
import java.util.List;

@SuppressWarnings("ucd")
public class RelayEmailExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table s_relay_mail
     *
     * @mbggenerated Thu Mar 03 12:34:02 ICT 2016
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table s_relay_mail
     *
     * @mbggenerated Thu Mar 03 12:34:02 ICT 2016
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table s_relay_mail
     *
     * @mbggenerated Thu Mar 03 12:34:02 ICT 2016
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_relay_mail
     *
     * @mbggenerated Thu Mar 03 12:34:02 ICT 2016
     */
    public RelayEmailExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_relay_mail
     *
     * @mbggenerated Thu Mar 03 12:34:02 ICT 2016
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_relay_mail
     *
     * @mbggenerated Thu Mar 03 12:34:02 ICT 2016
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_relay_mail
     *
     * @mbggenerated Thu Mar 03 12:34:02 ICT 2016
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_relay_mail
     *
     * @mbggenerated Thu Mar 03 12:34:02 ICT 2016
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_relay_mail
     *
     * @mbggenerated Thu Mar 03 12:34:02 ICT 2016
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_relay_mail
     *
     * @mbggenerated Thu Mar 03 12:34:02 ICT 2016
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_relay_mail
     *
     * @mbggenerated Thu Mar 03 12:34:02 ICT 2016
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_relay_mail
     *
     * @mbggenerated Thu Mar 03 12:34:02 ICT 2016
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
     * This method corresponds to the database table s_relay_mail
     *
     * @mbggenerated Thu Mar 03 12:34:02 ICT 2016
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_relay_mail
     *
     * @mbggenerated Thu Mar 03 12:34:02 ICT 2016
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table s_relay_mail
     *
     * @mbggenerated Thu Mar 03 12:34:02 ICT 2016
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

        public Criteria andFromnameIsNull() {
            addCriterion("fromName is null");
            return (Criteria) this;
        }

        public Criteria andFromnameIsNotNull() {
            addCriterion("fromName is not null");
            return (Criteria) this;
        }

        public Criteria andFromnameEqualTo(String value) {
            addCriterion("fromName =", value, "fromname");
            return (Criteria) this;
        }

        public Criteria andFromnameNotEqualTo(String value) {
            addCriterion("fromName <>", value, "fromname");
            return (Criteria) this;
        }

        public Criteria andFromnameGreaterThan(String value) {
            addCriterion("fromName >", value, "fromname");
            return (Criteria) this;
        }

        public Criteria andFromnameGreaterThanOrEqualTo(String value) {
            addCriterion("fromName >=", value, "fromname");
            return (Criteria) this;
        }

        public Criteria andFromnameLessThan(String value) {
            addCriterion("fromName <", value, "fromname");
            return (Criteria) this;
        }

        public Criteria andFromnameLessThanOrEqualTo(String value) {
            addCriterion("fromName <=", value, "fromname");
            return (Criteria) this;
        }

        public Criteria andFromnameLike(String value) {
            addCriterion("fromName like", value, "fromname");
            return (Criteria) this;
        }

        public Criteria andFromnameNotLike(String value) {
            addCriterion("fromName not like", value, "fromname");
            return (Criteria) this;
        }

        public Criteria andFromnameIn(List<String> values) {
            addCriterion("fromName in", values, "fromname");
            return (Criteria) this;
        }

        public Criteria andFromnameNotIn(List<String> values) {
            addCriterion("fromName not in", values, "fromname");
            return (Criteria) this;
        }

        public Criteria andFromnameBetween(String value1, String value2) {
            addCriterion("fromName between", value1, value2, "fromname");
            return (Criteria) this;
        }

        public Criteria andFromnameNotBetween(String value1, String value2) {
            addCriterion("fromName not between", value1, value2, "fromname");
            return (Criteria) this;
        }

        public Criteria andFromemailIsNull() {
            addCriterion("fromEmail is null");
            return (Criteria) this;
        }

        public Criteria andFromemailIsNotNull() {
            addCriterion("fromEmail is not null");
            return (Criteria) this;
        }

        public Criteria andFromemailEqualTo(String value) {
            addCriterion("fromEmail =", value, "fromemail");
            return (Criteria) this;
        }

        public Criteria andFromemailNotEqualTo(String value) {
            addCriterion("fromEmail <>", value, "fromemail");
            return (Criteria) this;
        }

        public Criteria andFromemailGreaterThan(String value) {
            addCriterion("fromEmail >", value, "fromemail");
            return (Criteria) this;
        }

        public Criteria andFromemailGreaterThanOrEqualTo(String value) {
            addCriterion("fromEmail >=", value, "fromemail");
            return (Criteria) this;
        }

        public Criteria andFromemailLessThan(String value) {
            addCriterion("fromEmail <", value, "fromemail");
            return (Criteria) this;
        }

        public Criteria andFromemailLessThanOrEqualTo(String value) {
            addCriterion("fromEmail <=", value, "fromemail");
            return (Criteria) this;
        }

        public Criteria andFromemailLike(String value) {
            addCriterion("fromEmail like", value, "fromemail");
            return (Criteria) this;
        }

        public Criteria andFromemailNotLike(String value) {
            addCriterion("fromEmail not like", value, "fromemail");
            return (Criteria) this;
        }

        public Criteria andFromemailIn(List<String> values) {
            addCriterion("fromEmail in", values, "fromemail");
            return (Criteria) this;
        }

        public Criteria andFromemailNotIn(List<String> values) {
            addCriterion("fromEmail not in", values, "fromemail");
            return (Criteria) this;
        }

        public Criteria andFromemailBetween(String value1, String value2) {
            addCriterion("fromEmail between", value1, value2, "fromemail");
            return (Criteria) this;
        }

        public Criteria andFromemailNotBetween(String value1, String value2) {
            addCriterion("fromEmail not between", value1, value2, "fromemail");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table s_relay_mail
     *
     * @mbggenerated do_not_delete_during_merge Thu Mar 03 12:34:02 ICT 2016
     */
    @SuppressWarnings("ucd")
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table s_relay_mail
     *
     * @mbggenerated Thu Mar 03 12:34:02 ICT 2016
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