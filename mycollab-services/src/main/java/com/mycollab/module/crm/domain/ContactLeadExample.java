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
package com.mycollab.module.crm.domain;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("ucd")
public class ContactLeadExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table m_crm_contacts_leads
     *
     * @mbg.generated Tue Oct 18 22:31:55 ICT 2016
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table m_crm_contacts_leads
     *
     * @mbg.generated Tue Oct 18 22:31:55 ICT 2016
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table m_crm_contacts_leads
     *
     * @mbg.generated Tue Oct 18 22:31:55 ICT 2016
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contacts_leads
     *
     * @mbg.generated Tue Oct 18 22:31:55 ICT 2016
     */
    public ContactLeadExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contacts_leads
     *
     * @mbg.generated Tue Oct 18 22:31:55 ICT 2016
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contacts_leads
     *
     * @mbg.generated Tue Oct 18 22:31:55 ICT 2016
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contacts_leads
     *
     * @mbg.generated Tue Oct 18 22:31:55 ICT 2016
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contacts_leads
     *
     * @mbg.generated Tue Oct 18 22:31:55 ICT 2016
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contacts_leads
     *
     * @mbg.generated Tue Oct 18 22:31:55 ICT 2016
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contacts_leads
     *
     * @mbg.generated Tue Oct 18 22:31:55 ICT 2016
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contacts_leads
     *
     * @mbg.generated Tue Oct 18 22:31:55 ICT 2016
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contacts_leads
     *
     * @mbg.generated Tue Oct 18 22:31:55 ICT 2016
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
     * This method corresponds to the database table m_crm_contacts_leads
     *
     * @mbg.generated Tue Oct 18 22:31:55 ICT 2016
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contacts_leads
     *
     * @mbg.generated Tue Oct 18 22:31:55 ICT 2016
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table m_crm_contacts_leads
     *
     * @mbg.generated Tue Oct 18 22:31:55 ICT 2016
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

        public Criteria andContactidIsNull() {
            addCriterion("contactId is null");
            return (Criteria) this;
        }

        public Criteria andContactidIsNotNull() {
            addCriterion("contactId is not null");
            return (Criteria) this;
        }

        public Criteria andContactidEqualTo(Integer value) {
            addCriterion("contactId =", value, "contactid");
            return (Criteria) this;
        }

        public Criteria andContactidNotEqualTo(Integer value) {
            addCriterion("contactId <>", value, "contactid");
            return (Criteria) this;
        }

        public Criteria andContactidGreaterThan(Integer value) {
            addCriterion("contactId >", value, "contactid");
            return (Criteria) this;
        }

        public Criteria andContactidGreaterThanOrEqualTo(Integer value) {
            addCriterion("contactId >=", value, "contactid");
            return (Criteria) this;
        }

        public Criteria andContactidLessThan(Integer value) {
            addCriterion("contactId <", value, "contactid");
            return (Criteria) this;
        }

        public Criteria andContactidLessThanOrEqualTo(Integer value) {
            addCriterion("contactId <=", value, "contactid");
            return (Criteria) this;
        }

        public Criteria andContactidIn(List<Integer> values) {
            addCriterion("contactId in", values, "contactid");
            return (Criteria) this;
        }

        public Criteria andContactidNotIn(List<Integer> values) {
            addCriterion("contactId not in", values, "contactid");
            return (Criteria) this;
        }

        public Criteria andContactidBetween(Integer value1, Integer value2) {
            addCriterion("contactId between", value1, value2, "contactid");
            return (Criteria) this;
        }

        public Criteria andContactidNotBetween(Integer value1, Integer value2) {
            addCriterion("contactId not between", value1, value2, "contactid");
            return (Criteria) this;
        }

        public Criteria andLeadidIsNull() {
            addCriterion("leadId is null");
            return (Criteria) this;
        }

        public Criteria andLeadidIsNotNull() {
            addCriterion("leadId is not null");
            return (Criteria) this;
        }

        public Criteria andLeadidEqualTo(Integer value) {
            addCriterion("leadId =", value, "leadid");
            return (Criteria) this;
        }

        public Criteria andLeadidNotEqualTo(Integer value) {
            addCriterion("leadId <>", value, "leadid");
            return (Criteria) this;
        }

        public Criteria andLeadidGreaterThan(Integer value) {
            addCriterion("leadId >", value, "leadid");
            return (Criteria) this;
        }

        public Criteria andLeadidGreaterThanOrEqualTo(Integer value) {
            addCriterion("leadId >=", value, "leadid");
            return (Criteria) this;
        }

        public Criteria andLeadidLessThan(Integer value) {
            addCriterion("leadId <", value, "leadid");
            return (Criteria) this;
        }

        public Criteria andLeadidLessThanOrEqualTo(Integer value) {
            addCriterion("leadId <=", value, "leadid");
            return (Criteria) this;
        }

        public Criteria andLeadidIn(List<Integer> values) {
            addCriterion("leadId in", values, "leadid");
            return (Criteria) this;
        }

        public Criteria andLeadidNotIn(List<Integer> values) {
            addCriterion("leadId not in", values, "leadid");
            return (Criteria) this;
        }

        public Criteria andLeadidBetween(Integer value1, Integer value2) {
            addCriterion("leadId between", value1, value2, "leadid");
            return (Criteria) this;
        }

        public Criteria andLeadidNotBetween(Integer value1, Integer value2) {
            addCriterion("leadId not between", value1, value2, "leadid");
            return (Criteria) this;
        }

        public Criteria andIsconvertrelIsNull() {
            addCriterion("isConvertRel is null");
            return (Criteria) this;
        }

        public Criteria andIsconvertrelIsNotNull() {
            addCriterion("isConvertRel is not null");
            return (Criteria) this;
        }

        public Criteria andIsconvertrelEqualTo(Boolean value) {
            addCriterion("isConvertRel =", value, "isconvertrel");
            return (Criteria) this;
        }

        public Criteria andIsconvertrelNotEqualTo(Boolean value) {
            addCriterion("isConvertRel <>", value, "isconvertrel");
            return (Criteria) this;
        }

        public Criteria andIsconvertrelGreaterThan(Boolean value) {
            addCriterion("isConvertRel >", value, "isconvertrel");
            return (Criteria) this;
        }

        public Criteria andIsconvertrelGreaterThanOrEqualTo(Boolean value) {
            addCriterion("isConvertRel >=", value, "isconvertrel");
            return (Criteria) this;
        }

        public Criteria andIsconvertrelLessThan(Boolean value) {
            addCriterion("isConvertRel <", value, "isconvertrel");
            return (Criteria) this;
        }

        public Criteria andIsconvertrelLessThanOrEqualTo(Boolean value) {
            addCriterion("isConvertRel <=", value, "isconvertrel");
            return (Criteria) this;
        }

        public Criteria andIsconvertrelIn(List<Boolean> values) {
            addCriterion("isConvertRel in", values, "isconvertrel");
            return (Criteria) this;
        }

        public Criteria andIsconvertrelNotIn(List<Boolean> values) {
            addCriterion("isConvertRel not in", values, "isconvertrel");
            return (Criteria) this;
        }

        public Criteria andIsconvertrelBetween(Boolean value1, Boolean value2) {
            addCriterion("isConvertRel between", value1, value2, "isconvertrel");
            return (Criteria) this;
        }

        public Criteria andIsconvertrelNotBetween(Boolean value1, Boolean value2) {
            addCriterion("isConvertRel not between", value1, value2, "isconvertrel");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table m_crm_contacts_leads
     *
     * @mbg.generated do_not_delete_during_merge Tue Oct 18 22:31:55 ICT 2016
     */
    @SuppressWarnings("ucd")
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table m_crm_contacts_leads
     *
     * @mbg.generated Tue Oct 18 22:31:55 ICT 2016
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