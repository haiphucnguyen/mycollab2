package com.esofthead.mycollab.common.domain;

import java.util.ArrayList;
import java.util.List;

public class CurrencyExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table s_currency
     *
     * @mbggenerated Thu Jun 06 11:18:20 GMT+07:00 2013
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table s_currency
     *
     * @mbggenerated Thu Jun 06 11:18:20 GMT+07:00 2013
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table s_currency
     *
     * @mbggenerated Thu Jun 06 11:18:20 GMT+07:00 2013
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_currency
     *
     * @mbggenerated Thu Jun 06 11:18:20 GMT+07:00 2013
     */
    public CurrencyExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_currency
     *
     * @mbggenerated Thu Jun 06 11:18:20 GMT+07:00 2013
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_currency
     *
     * @mbggenerated Thu Jun 06 11:18:20 GMT+07:00 2013
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_currency
     *
     * @mbggenerated Thu Jun 06 11:18:20 GMT+07:00 2013
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_currency
     *
     * @mbggenerated Thu Jun 06 11:18:20 GMT+07:00 2013
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_currency
     *
     * @mbggenerated Thu Jun 06 11:18:20 GMT+07:00 2013
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_currency
     *
     * @mbggenerated Thu Jun 06 11:18:20 GMT+07:00 2013
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_currency
     *
     * @mbggenerated Thu Jun 06 11:18:20 GMT+07:00 2013
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_currency
     *
     * @mbggenerated Thu Jun 06 11:18:20 GMT+07:00 2013
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
     * This method corresponds to the database table s_currency
     *
     * @mbggenerated Thu Jun 06 11:18:20 GMT+07:00 2013
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_currency
     *
     * @mbggenerated Thu Jun 06 11:18:20 GMT+07:00 2013
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table s_currency
     *
     * @mbggenerated Thu Jun 06 11:18:20 GMT+07:00 2013
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

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andIsocodeIsNull() {
            addCriterion("isocode is null");
            return (Criteria) this;
        }

        public Criteria andIsocodeIsNotNull() {
            addCriterion("isocode is not null");
            return (Criteria) this;
        }

        public Criteria andIsocodeEqualTo(String value) {
            addCriterion("isocode =", value, "isocode");
            return (Criteria) this;
        }

        public Criteria andIsocodeNotEqualTo(String value) {
            addCriterion("isocode <>", value, "isocode");
            return (Criteria) this;
        }

        public Criteria andIsocodeGreaterThan(String value) {
            addCriterion("isocode >", value, "isocode");
            return (Criteria) this;
        }

        public Criteria andIsocodeGreaterThanOrEqualTo(String value) {
            addCriterion("isocode >=", value, "isocode");
            return (Criteria) this;
        }

        public Criteria andIsocodeLessThan(String value) {
            addCriterion("isocode <", value, "isocode");
            return (Criteria) this;
        }

        public Criteria andIsocodeLessThanOrEqualTo(String value) {
            addCriterion("isocode <=", value, "isocode");
            return (Criteria) this;
        }

        public Criteria andIsocodeLike(String value) {
            addCriterion("isocode like", value, "isocode");
            return (Criteria) this;
        }

        public Criteria andIsocodeNotLike(String value) {
            addCriterion("isocode not like", value, "isocode");
            return (Criteria) this;
        }

        public Criteria andIsocodeIn(List<String> values) {
            addCriterion("isocode in", values, "isocode");
            return (Criteria) this;
        }

        public Criteria andIsocodeNotIn(List<String> values) {
            addCriterion("isocode not in", values, "isocode");
            return (Criteria) this;
        }

        public Criteria andIsocodeBetween(String value1, String value2) {
            addCriterion("isocode between", value1, value2, "isocode");
            return (Criteria) this;
        }

        public Criteria andIsocodeNotBetween(String value1, String value2) {
            addCriterion("isocode not between", value1, value2, "isocode");
            return (Criteria) this;
        }

        public Criteria andSymbolIsNull() {
            addCriterion("symbol is null");
            return (Criteria) this;
        }

        public Criteria andSymbolIsNotNull() {
            addCriterion("symbol is not null");
            return (Criteria) this;
        }

        public Criteria andSymbolEqualTo(String value) {
            addCriterion("symbol =", value, "symbol");
            return (Criteria) this;
        }

        public Criteria andSymbolNotEqualTo(String value) {
            addCriterion("symbol <>", value, "symbol");
            return (Criteria) this;
        }

        public Criteria andSymbolGreaterThan(String value) {
            addCriterion("symbol >", value, "symbol");
            return (Criteria) this;
        }

        public Criteria andSymbolGreaterThanOrEqualTo(String value) {
            addCriterion("symbol >=", value, "symbol");
            return (Criteria) this;
        }

        public Criteria andSymbolLessThan(String value) {
            addCriterion("symbol <", value, "symbol");
            return (Criteria) this;
        }

        public Criteria andSymbolLessThanOrEqualTo(String value) {
            addCriterion("symbol <=", value, "symbol");
            return (Criteria) this;
        }

        public Criteria andSymbolLike(String value) {
            addCriterion("symbol like", value, "symbol");
            return (Criteria) this;
        }

        public Criteria andSymbolNotLike(String value) {
            addCriterion("symbol not like", value, "symbol");
            return (Criteria) this;
        }

        public Criteria andSymbolIn(List<String> values) {
            addCriterion("symbol in", values, "symbol");
            return (Criteria) this;
        }

        public Criteria andSymbolNotIn(List<String> values) {
            addCriterion("symbol not in", values, "symbol");
            return (Criteria) this;
        }

        public Criteria andSymbolBetween(String value1, String value2) {
            addCriterion("symbol between", value1, value2, "symbol");
            return (Criteria) this;
        }

        public Criteria andSymbolNotBetween(String value1, String value2) {
            addCriterion("symbol not between", value1, value2, "symbol");
            return (Criteria) this;
        }

        public Criteria andConversionrateIsNull() {
            addCriterion("conversionrate is null");
            return (Criteria) this;
        }

        public Criteria andConversionrateIsNotNull() {
            addCriterion("conversionrate is not null");
            return (Criteria) this;
        }

        public Criteria andConversionrateEqualTo(Double value) {
            addCriterion("conversionrate =", value, "conversionrate");
            return (Criteria) this;
        }

        public Criteria andConversionrateNotEqualTo(Double value) {
            addCriterion("conversionrate <>", value, "conversionrate");
            return (Criteria) this;
        }

        public Criteria andConversionrateGreaterThan(Double value) {
            addCriterion("conversionrate >", value, "conversionrate");
            return (Criteria) this;
        }

        public Criteria andConversionrateGreaterThanOrEqualTo(Double value) {
            addCriterion("conversionrate >=", value, "conversionrate");
            return (Criteria) this;
        }

        public Criteria andConversionrateLessThan(Double value) {
            addCriterion("conversionrate <", value, "conversionrate");
            return (Criteria) this;
        }

        public Criteria andConversionrateLessThanOrEqualTo(Double value) {
            addCriterion("conversionrate <=", value, "conversionrate");
            return (Criteria) this;
        }

        public Criteria andConversionrateIn(List<Double> values) {
            addCriterion("conversionrate in", values, "conversionrate");
            return (Criteria) this;
        }

        public Criteria andConversionrateNotIn(List<Double> values) {
            addCriterion("conversionrate not in", values, "conversionrate");
            return (Criteria) this;
        }

        public Criteria andConversionrateBetween(Double value1, Double value2) {
            addCriterion("conversionrate between", value1, value2, "conversionrate");
            return (Criteria) this;
        }

        public Criteria andConversionrateNotBetween(Double value1, Double value2) {
            addCriterion("conversionrate not between", value1, value2, "conversionrate");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table s_currency
     *
     * @mbggenerated do_not_delete_during_merge Thu Jun 06 11:18:20 GMT+07:00 2013
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table s_currency
     *
     * @mbggenerated Thu Jun 06 11:18:20 GMT+07:00 2013
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