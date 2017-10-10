/**
 * mycollab-services - Parent pom providing dependency and plugin management for applications
		built with Maven
 * Copyright © 2017 MyCollab (support@mycollab.com)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
/**
 * mycollab-services - Parent pom providing dependency and plugin management for applications
		built with Maven
 * Copyright © ${project.inceptionYear} MyCollab (support@mycollab.com)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.mycollab.module.crm.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class QuoteGroupProductExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table m_crm_quote_group_product
     *
     * @mbggenerated Mon Jan 14 14:41:39 GMT+07:00 2013
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table m_crm_quote_group_product
     *
     * @mbggenerated Mon Jan 14 14:41:39 GMT+07:00 2013
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table m_crm_quote_group_product
     *
     * @mbggenerated Mon Jan 14 14:41:39 GMT+07:00 2013
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_quote_group_product
     *
     * @mbggenerated Mon Jan 14 14:41:39 GMT+07:00 2013
     */
    public QuoteGroupProductExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_quote_group_product
     *
     * @mbggenerated Mon Jan 14 14:41:39 GMT+07:00 2013
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_quote_group_product
     *
     * @mbggenerated Mon Jan 14 14:41:39 GMT+07:00 2013
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_quote_group_product
     *
     * @mbggenerated Mon Jan 14 14:41:39 GMT+07:00 2013
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_quote_group_product
     *
     * @mbggenerated Mon Jan 14 14:41:39 GMT+07:00 2013
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_quote_group_product
     *
     * @mbggenerated Mon Jan 14 14:41:39 GMT+07:00 2013
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_quote_group_product
     *
     * @mbggenerated Mon Jan 14 14:41:39 GMT+07:00 2013
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_quote_group_product
     *
     * @mbggenerated Mon Jan 14 14:41:39 GMT+07:00 2013
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_quote_group_product
     *
     * @mbggenerated Mon Jan 14 14:41:39 GMT+07:00 2013
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
     * This method corresponds to the database table m_crm_quote_group_product
     *
     * @mbggenerated Mon Jan 14 14:41:39 GMT+07:00 2013
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_quote_group_product
     *
     * @mbggenerated Mon Jan 14 14:41:39 GMT+07:00 2013
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table m_crm_quote_group_product
     *
     * @mbggenerated Mon Jan 14 14:41:39 GMT+07:00 2013
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

        public Criteria andGroupnameIsNull() {
            addCriterion("groupname is null");
            return (Criteria) this;
        }

        public Criteria andGroupnameIsNotNull() {
            addCriterion("groupname is not null");
            return (Criteria) this;
        }

        public Criteria andGroupnameEqualTo(String value) {
            addCriterion("groupname =", value, "groupname");
            return (Criteria) this;
        }

        public Criteria andGroupnameNotEqualTo(String value) {
            addCriterion("groupname <>", value, "groupname");
            return (Criteria) this;
        }

        public Criteria andGroupnameGreaterThan(String value) {
            addCriterion("groupname >", value, "groupname");
            return (Criteria) this;
        }

        public Criteria andGroupnameGreaterThanOrEqualTo(String value) {
            addCriterion("groupname >=", value, "groupname");
            return (Criteria) this;
        }

        public Criteria andGroupnameLessThan(String value) {
            addCriterion("groupname <", value, "groupname");
            return (Criteria) this;
        }

        public Criteria andGroupnameLessThanOrEqualTo(String value) {
            addCriterion("groupname <=", value, "groupname");
            return (Criteria) this;
        }

        public Criteria andGroupnameLike(String value) {
            addCriterion("groupname like", value, "groupname");
            return (Criteria) this;
        }

        public Criteria andGroupnameNotLike(String value) {
            addCriterion("groupname not like", value, "groupname");
            return (Criteria) this;
        }

        public Criteria andGroupnameIn(List<String> values) {
            addCriterion("groupname in", values, "groupname");
            return (Criteria) this;
        }

        public Criteria andGroupnameNotIn(List<String> values) {
            addCriterion("groupname not in", values, "groupname");
            return (Criteria) this;
        }

        public Criteria andGroupnameBetween(String value1, String value2) {
            addCriterion("groupname between", value1, value2, "groupname");
            return (Criteria) this;
        }

        public Criteria andGroupnameNotBetween(String value1, String value2) {
            addCriterion("groupname not between", value1, value2, "groupname");
            return (Criteria) this;
        }

        public Criteria andGroupstateIsNull() {
            addCriterion("groupstate is null");
            return (Criteria) this;
        }

        public Criteria andGroupstateIsNotNull() {
            addCriterion("groupstate is not null");
            return (Criteria) this;
        }

        public Criteria andGroupstateEqualTo(String value) {
            addCriterion("groupstate =", value, "groupstate");
            return (Criteria) this;
        }

        public Criteria andGroupstateNotEqualTo(String value) {
            addCriterion("groupstate <>", value, "groupstate");
            return (Criteria) this;
        }

        public Criteria andGroupstateGreaterThan(String value) {
            addCriterion("groupstate >", value, "groupstate");
            return (Criteria) this;
        }

        public Criteria andGroupstateGreaterThanOrEqualTo(String value) {
            addCriterion("groupstate >=", value, "groupstate");
            return (Criteria) this;
        }

        public Criteria andGroupstateLessThan(String value) {
            addCriterion("groupstate <", value, "groupstate");
            return (Criteria) this;
        }

        public Criteria andGroupstateLessThanOrEqualTo(String value) {
            addCriterion("groupstate <=", value, "groupstate");
            return (Criteria) this;
        }

        public Criteria andGroupstateLike(String value) {
            addCriterion("groupstate like", value, "groupstate");
            return (Criteria) this;
        }

        public Criteria andGroupstateNotLike(String value) {
            addCriterion("groupstate not like", value, "groupstate");
            return (Criteria) this;
        }

        public Criteria andGroupstateIn(List<String> values) {
            addCriterion("groupstate in", values, "groupstate");
            return (Criteria) this;
        }

        public Criteria andGroupstateNotIn(List<String> values) {
            addCriterion("groupstate not in", values, "groupstate");
            return (Criteria) this;
        }

        public Criteria andGroupstateBetween(String value1, String value2) {
            addCriterion("groupstate between", value1, value2, "groupstate");
            return (Criteria) this;
        }

        public Criteria andGroupstateNotBetween(String value1, String value2) {
            addCriterion("groupstate not between", value1, value2, "groupstate");
            return (Criteria) this;
        }

        public Criteria andTaxIsNull() {
            addCriterion("tax is null");
            return (Criteria) this;
        }

        public Criteria andTaxIsNotNull() {
            addCriterion("tax is not null");
            return (Criteria) this;
        }

        public Criteria andTaxEqualTo(String value) {
            addCriterion("tax =", value, "tax");
            return (Criteria) this;
        }

        public Criteria andTaxNotEqualTo(String value) {
            addCriterion("tax <>", value, "tax");
            return (Criteria) this;
        }

        public Criteria andTaxGreaterThan(String value) {
            addCriterion("tax >", value, "tax");
            return (Criteria) this;
        }

        public Criteria andTaxGreaterThanOrEqualTo(String value) {
            addCriterion("tax >=", value, "tax");
            return (Criteria) this;
        }

        public Criteria andTaxLessThan(String value) {
            addCriterion("tax <", value, "tax");
            return (Criteria) this;
        }

        public Criteria andTaxLessThanOrEqualTo(String value) {
            addCriterion("tax <=", value, "tax");
            return (Criteria) this;
        }

        public Criteria andTaxLike(String value) {
            addCriterion("tax like", value, "tax");
            return (Criteria) this;
        }

        public Criteria andTaxNotLike(String value) {
            addCriterion("tax not like", value, "tax");
            return (Criteria) this;
        }

        public Criteria andTaxIn(List<String> values) {
            addCriterion("tax in", values, "tax");
            return (Criteria) this;
        }

        public Criteria andTaxNotIn(List<String> values) {
            addCriterion("tax not in", values, "tax");
            return (Criteria) this;
        }

        public Criteria andTaxBetween(String value1, String value2) {
            addCriterion("tax between", value1, value2, "tax");
            return (Criteria) this;
        }

        public Criteria andTaxNotBetween(String value1, String value2) {
            addCriterion("tax not between", value1, value2, "tax");
            return (Criteria) this;
        }

        public Criteria andShippingIsNull() {
            addCriterion("shipping is null");
            return (Criteria) this;
        }

        public Criteria andShippingIsNotNull() {
            addCriterion("shipping is not null");
            return (Criteria) this;
        }

        public Criteria andShippingEqualTo(String value) {
            addCriterion("shipping =", value, "shipping");
            return (Criteria) this;
        }

        public Criteria andShippingNotEqualTo(String value) {
            addCriterion("shipping <>", value, "shipping");
            return (Criteria) this;
        }

        public Criteria andShippingGreaterThan(String value) {
            addCriterion("shipping >", value, "shipping");
            return (Criteria) this;
        }

        public Criteria andShippingGreaterThanOrEqualTo(String value) {
            addCriterion("shipping >=", value, "shipping");
            return (Criteria) this;
        }

        public Criteria andShippingLessThan(String value) {
            addCriterion("shipping <", value, "shipping");
            return (Criteria) this;
        }

        public Criteria andShippingLessThanOrEqualTo(String value) {
            addCriterion("shipping <=", value, "shipping");
            return (Criteria) this;
        }

        public Criteria andShippingLike(String value) {
            addCriterion("shipping like", value, "shipping");
            return (Criteria) this;
        }

        public Criteria andShippingNotLike(String value) {
            addCriterion("shipping not like", value, "shipping");
            return (Criteria) this;
        }

        public Criteria andShippingIn(List<String> values) {
            addCriterion("shipping in", values, "shipping");
            return (Criteria) this;
        }

        public Criteria andShippingNotIn(List<String> values) {
            addCriterion("shipping not in", values, "shipping");
            return (Criteria) this;
        }

        public Criteria andShippingBetween(String value1, String value2) {
            addCriterion("shipping between", value1, value2, "shipping");
            return (Criteria) this;
        }

        public Criteria andShippingNotBetween(String value1, String value2) {
            addCriterion("shipping not between", value1, value2, "shipping");
            return (Criteria) this;
        }

        public Criteria andQuoteidIsNull() {
            addCriterion("quoteid is null");
            return (Criteria) this;
        }

        public Criteria andQuoteidIsNotNull() {
            addCriterion("quoteid is not null");
            return (Criteria) this;
        }

        public Criteria andQuoteidEqualTo(Integer value) {
            addCriterion("quoteid =", value, "quoteid");
            return (Criteria) this;
        }

        public Criteria andQuoteidNotEqualTo(Integer value) {
            addCriterion("quoteid <>", value, "quoteid");
            return (Criteria) this;
        }

        public Criteria andQuoteidGreaterThan(Integer value) {
            addCriterion("quoteid >", value, "quoteid");
            return (Criteria) this;
        }

        public Criteria andQuoteidGreaterThanOrEqualTo(Integer value) {
            addCriterion("quoteid >=", value, "quoteid");
            return (Criteria) this;
        }

        public Criteria andQuoteidLessThan(Integer value) {
            addCriterion("quoteid <", value, "quoteid");
            return (Criteria) this;
        }

        public Criteria andQuoteidLessThanOrEqualTo(Integer value) {
            addCriterion("quoteid <=", value, "quoteid");
            return (Criteria) this;
        }

        public Criteria andQuoteidIn(List<Integer> values) {
            addCriterion("quoteid in", values, "quoteid");
            return (Criteria) this;
        }

        public Criteria andQuoteidNotIn(List<Integer> values) {
            addCriterion("quoteid not in", values, "quoteid");
            return (Criteria) this;
        }

        public Criteria andQuoteidBetween(Integer value1, Integer value2) {
            addCriterion("quoteid between", value1, value2, "quoteid");
            return (Criteria) this;
        }

        public Criteria andQuoteidNotBetween(Integer value1, Integer value2) {
            addCriterion("quoteid not between", value1, value2, "quoteid");
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
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table m_crm_quote_group_product
     *
     * @mbggenerated do_not_delete_during_merge Mon Jan 14 14:41:39 GMT+07:00 2013
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table m_crm_quote_group_product
     *
     * @mbggenerated Mon Jan 14 14:41:39 GMT+07:00 2013
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