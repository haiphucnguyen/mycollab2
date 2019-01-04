package com.mycollab.common.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("ucd")
public class OptionValExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table m_options
     *
     * @mbg.generated Fri Jan 04 16:43:08 CST 2019
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table m_options
     *
     * @mbg.generated Fri Jan 04 16:43:08 CST 2019
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table m_options
     *
     * @mbg.generated Fri Jan 04 16:43:08 CST 2019
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_options
     *
     * @mbg.generated Fri Jan 04 16:43:08 CST 2019
     */
    public OptionValExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_options
     *
     * @mbg.generated Fri Jan 04 16:43:08 CST 2019
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_options
     *
     * @mbg.generated Fri Jan 04 16:43:08 CST 2019
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_options
     *
     * @mbg.generated Fri Jan 04 16:43:08 CST 2019
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_options
     *
     * @mbg.generated Fri Jan 04 16:43:08 CST 2019
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_options
     *
     * @mbg.generated Fri Jan 04 16:43:08 CST 2019
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_options
     *
     * @mbg.generated Fri Jan 04 16:43:08 CST 2019
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_options
     *
     * @mbg.generated Fri Jan 04 16:43:08 CST 2019
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_options
     *
     * @mbg.generated Fri Jan 04 16:43:08 CST 2019
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
     * This method corresponds to the database table m_options
     *
     * @mbg.generated Fri Jan 04 16:43:08 CST 2019
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_options
     *
     * @mbg.generated Fri Jan 04 16:43:08 CST 2019
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table m_options
     *
     * @mbg.generated Fri Jan 04 16:43:08 CST 2019
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

        public Criteria andTypevalIsNull() {
            addCriterion("typeVal is null");
            return (Criteria) this;
        }

        public Criteria andTypevalIsNotNull() {
            addCriterion("typeVal is not null");
            return (Criteria) this;
        }

        public Criteria andTypevalEqualTo(String value) {
            addCriterion("typeVal =", value, "typeval");
            return (Criteria) this;
        }

        public Criteria andTypevalNotEqualTo(String value) {
            addCriterion("typeVal <>", value, "typeval");
            return (Criteria) this;
        }

        public Criteria andTypevalGreaterThan(String value) {
            addCriterion("typeVal >", value, "typeval");
            return (Criteria) this;
        }

        public Criteria andTypevalGreaterThanOrEqualTo(String value) {
            addCriterion("typeVal >=", value, "typeval");
            return (Criteria) this;
        }

        public Criteria andTypevalLessThan(String value) {
            addCriterion("typeVal <", value, "typeval");
            return (Criteria) this;
        }

        public Criteria andTypevalLessThanOrEqualTo(String value) {
            addCriterion("typeVal <=", value, "typeval");
            return (Criteria) this;
        }

        public Criteria andTypevalLike(String value) {
            addCriterion("typeVal like", value, "typeval");
            return (Criteria) this;
        }

        public Criteria andTypevalNotLike(String value) {
            addCriterion("typeVal not like", value, "typeval");
            return (Criteria) this;
        }

        public Criteria andTypevalIn(List<String> values) {
            addCriterion("typeVal in", values, "typeval");
            return (Criteria) this;
        }

        public Criteria andTypevalNotIn(List<String> values) {
            addCriterion("typeVal not in", values, "typeval");
            return (Criteria) this;
        }

        public Criteria andTypevalBetween(String value1, String value2) {
            addCriterion("typeVal between", value1, value2, "typeval");
            return (Criteria) this;
        }

        public Criteria andTypevalNotBetween(String value1, String value2) {
            addCriterion("typeVal not between", value1, value2, "typeval");
            return (Criteria) this;
        }

        public Criteria andOrderindexIsNull() {
            addCriterion("orderIndex is null");
            return (Criteria) this;
        }

        public Criteria andOrderindexIsNotNull() {
            addCriterion("orderIndex is not null");
            return (Criteria) this;
        }

        public Criteria andOrderindexEqualTo(Integer value) {
            addCriterion("orderIndex =", value, "orderindex");
            return (Criteria) this;
        }

        public Criteria andOrderindexNotEqualTo(Integer value) {
            addCriterion("orderIndex <>", value, "orderindex");
            return (Criteria) this;
        }

        public Criteria andOrderindexGreaterThan(Integer value) {
            addCriterion("orderIndex >", value, "orderindex");
            return (Criteria) this;
        }

        public Criteria andOrderindexGreaterThanOrEqualTo(Integer value) {
            addCriterion("orderIndex >=", value, "orderindex");
            return (Criteria) this;
        }

        public Criteria andOrderindexLessThan(Integer value) {
            addCriterion("orderIndex <", value, "orderindex");
            return (Criteria) this;
        }

        public Criteria andOrderindexLessThanOrEqualTo(Integer value) {
            addCriterion("orderIndex <=", value, "orderindex");
            return (Criteria) this;
        }

        public Criteria andOrderindexIn(List<Integer> values) {
            addCriterion("orderIndex in", values, "orderindex");
            return (Criteria) this;
        }

        public Criteria andOrderindexNotIn(List<Integer> values) {
            addCriterion("orderIndex not in", values, "orderindex");
            return (Criteria) this;
        }

        public Criteria andOrderindexBetween(Integer value1, Integer value2) {
            addCriterion("orderIndex between", value1, value2, "orderindex");
            return (Criteria) this;
        }

        public Criteria andOrderindexNotBetween(Integer value1, Integer value2) {
            addCriterion("orderIndex not between", value1, value2, "orderindex");
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

        public Criteria andExtraidIsNull() {
            addCriterion("extraId is null");
            return (Criteria) this;
        }

        public Criteria andExtraidIsNotNull() {
            addCriterion("extraId is not null");
            return (Criteria) this;
        }

        public Criteria andExtraidEqualTo(Integer value) {
            addCriterion("extraId =", value, "extraid");
            return (Criteria) this;
        }

        public Criteria andExtraidNotEqualTo(Integer value) {
            addCriterion("extraId <>", value, "extraid");
            return (Criteria) this;
        }

        public Criteria andExtraidGreaterThan(Integer value) {
            addCriterion("extraId >", value, "extraid");
            return (Criteria) this;
        }

        public Criteria andExtraidGreaterThanOrEqualTo(Integer value) {
            addCriterion("extraId >=", value, "extraid");
            return (Criteria) this;
        }

        public Criteria andExtraidLessThan(Integer value) {
            addCriterion("extraId <", value, "extraid");
            return (Criteria) this;
        }

        public Criteria andExtraidLessThanOrEqualTo(Integer value) {
            addCriterion("extraId <=", value, "extraid");
            return (Criteria) this;
        }

        public Criteria andExtraidIn(List<Integer> values) {
            addCriterion("extraId in", values, "extraid");
            return (Criteria) this;
        }

        public Criteria andExtraidNotIn(List<Integer> values) {
            addCriterion("extraId not in", values, "extraid");
            return (Criteria) this;
        }

        public Criteria andExtraidBetween(Integer value1, Integer value2) {
            addCriterion("extraId between", value1, value2, "extraid");
            return (Criteria) this;
        }

        public Criteria andExtraidNotBetween(Integer value1, Integer value2) {
            addCriterion("extraId not between", value1, value2, "extraid");
            return (Criteria) this;
        }

        public Criteria andIsdefaultIsNull() {
            addCriterion("isDefault is null");
            return (Criteria) this;
        }

        public Criteria andIsdefaultIsNotNull() {
            addCriterion("isDefault is not null");
            return (Criteria) this;
        }

        public Criteria andIsdefaultEqualTo(Boolean value) {
            addCriterion("isDefault =", value, "isdefault");
            return (Criteria) this;
        }

        public Criteria andIsdefaultNotEqualTo(Boolean value) {
            addCriterion("isDefault <>", value, "isdefault");
            return (Criteria) this;
        }

        public Criteria andIsdefaultGreaterThan(Boolean value) {
            addCriterion("isDefault >", value, "isdefault");
            return (Criteria) this;
        }

        public Criteria andIsdefaultGreaterThanOrEqualTo(Boolean value) {
            addCriterion("isDefault >=", value, "isdefault");
            return (Criteria) this;
        }

        public Criteria andIsdefaultLessThan(Boolean value) {
            addCriterion("isDefault <", value, "isdefault");
            return (Criteria) this;
        }

        public Criteria andIsdefaultLessThanOrEqualTo(Boolean value) {
            addCriterion("isDefault <=", value, "isdefault");
            return (Criteria) this;
        }

        public Criteria andIsdefaultIn(List<Boolean> values) {
            addCriterion("isDefault in", values, "isdefault");
            return (Criteria) this;
        }

        public Criteria andIsdefaultNotIn(List<Boolean> values) {
            addCriterion("isDefault not in", values, "isdefault");
            return (Criteria) this;
        }

        public Criteria andIsdefaultBetween(Boolean value1, Boolean value2) {
            addCriterion("isDefault between", value1, value2, "isdefault");
            return (Criteria) this;
        }

        public Criteria andIsdefaultNotBetween(Boolean value1, Boolean value2) {
            addCriterion("isDefault not between", value1, value2, "isdefault");
            return (Criteria) this;
        }

        public Criteria andRefoptionIsNull() {
            addCriterion("refOption is null");
            return (Criteria) this;
        }

        public Criteria andRefoptionIsNotNull() {
            addCriterion("refOption is not null");
            return (Criteria) this;
        }

        public Criteria andRefoptionEqualTo(Integer value) {
            addCriterion("refOption =", value, "refoption");
            return (Criteria) this;
        }

        public Criteria andRefoptionNotEqualTo(Integer value) {
            addCriterion("refOption <>", value, "refoption");
            return (Criteria) this;
        }

        public Criteria andRefoptionGreaterThan(Integer value) {
            addCriterion("refOption >", value, "refoption");
            return (Criteria) this;
        }

        public Criteria andRefoptionGreaterThanOrEqualTo(Integer value) {
            addCriterion("refOption >=", value, "refoption");
            return (Criteria) this;
        }

        public Criteria andRefoptionLessThan(Integer value) {
            addCriterion("refOption <", value, "refoption");
            return (Criteria) this;
        }

        public Criteria andRefoptionLessThanOrEqualTo(Integer value) {
            addCriterion("refOption <=", value, "refoption");
            return (Criteria) this;
        }

        public Criteria andRefoptionIn(List<Integer> values) {
            addCriterion("refOption in", values, "refoption");
            return (Criteria) this;
        }

        public Criteria andRefoptionNotIn(List<Integer> values) {
            addCriterion("refOption not in", values, "refoption");
            return (Criteria) this;
        }

        public Criteria andRefoptionBetween(Integer value1, Integer value2) {
            addCriterion("refOption between", value1, value2, "refoption");
            return (Criteria) this;
        }

        public Criteria andRefoptionNotBetween(Integer value1, Integer value2) {
            addCriterion("refOption not between", value1, value2, "refoption");
            return (Criteria) this;
        }

        public Criteria andColorIsNull() {
            addCriterion("color is null");
            return (Criteria) this;
        }

        public Criteria andColorIsNotNull() {
            addCriterion("color is not null");
            return (Criteria) this;
        }

        public Criteria andColorEqualTo(String value) {
            addCriterion("color =", value, "color");
            return (Criteria) this;
        }

        public Criteria andColorNotEqualTo(String value) {
            addCriterion("color <>", value, "color");
            return (Criteria) this;
        }

        public Criteria andColorGreaterThan(String value) {
            addCriterion("color >", value, "color");
            return (Criteria) this;
        }

        public Criteria andColorGreaterThanOrEqualTo(String value) {
            addCriterion("color >=", value, "color");
            return (Criteria) this;
        }

        public Criteria andColorLessThan(String value) {
            addCriterion("color <", value, "color");
            return (Criteria) this;
        }

        public Criteria andColorLessThanOrEqualTo(String value) {
            addCriterion("color <=", value, "color");
            return (Criteria) this;
        }

        public Criteria andColorLike(String value) {
            addCriterion("color like", value, "color");
            return (Criteria) this;
        }

        public Criteria andColorNotLike(String value) {
            addCriterion("color not like", value, "color");
            return (Criteria) this;
        }

        public Criteria andColorIn(List<String> values) {
            addCriterion("color in", values, "color");
            return (Criteria) this;
        }

        public Criteria andColorNotIn(List<String> values) {
            addCriterion("color not in", values, "color");
            return (Criteria) this;
        }

        public Criteria andColorBetween(String value1, String value2) {
            addCriterion("color between", value1, value2, "color");
            return (Criteria) this;
        }

        public Criteria andColorNotBetween(String value1, String value2) {
            addCriterion("color not between", value1, value2, "color");
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

        public Criteria andIsshowIsNull() {
            addCriterion("isShow is null");
            return (Criteria) this;
        }

        public Criteria andIsshowIsNotNull() {
            addCriterion("isShow is not null");
            return (Criteria) this;
        }

        public Criteria andIsshowEqualTo(Boolean value) {
            addCriterion("isShow =", value, "isshow");
            return (Criteria) this;
        }

        public Criteria andIsshowNotEqualTo(Boolean value) {
            addCriterion("isShow <>", value, "isshow");
            return (Criteria) this;
        }

        public Criteria andIsshowGreaterThan(Boolean value) {
            addCriterion("isShow >", value, "isshow");
            return (Criteria) this;
        }

        public Criteria andIsshowGreaterThanOrEqualTo(Boolean value) {
            addCriterion("isShow >=", value, "isshow");
            return (Criteria) this;
        }

        public Criteria andIsshowLessThan(Boolean value) {
            addCriterion("isShow <", value, "isshow");
            return (Criteria) this;
        }

        public Criteria andIsshowLessThanOrEqualTo(Boolean value) {
            addCriterion("isShow <=", value, "isshow");
            return (Criteria) this;
        }

        public Criteria andIsshowIn(List<Boolean> values) {
            addCriterion("isShow in", values, "isshow");
            return (Criteria) this;
        }

        public Criteria andIsshowNotIn(List<Boolean> values) {
            addCriterion("isShow not in", values, "isshow");
            return (Criteria) this;
        }

        public Criteria andIsshowBetween(Boolean value1, Boolean value2) {
            addCriterion("isShow between", value1, value2, "isshow");
            return (Criteria) this;
        }

        public Criteria andIsshowNotBetween(Boolean value1, Boolean value2) {
            addCriterion("isShow not between", value1, value2, "isshow");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table m_options
     *
     * @mbg.generated do_not_delete_during_merge Fri Jan 04 16:43:08 CST 2019
     */
    @SuppressWarnings("ucd")
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table m_options
     *
     * @mbg.generated Fri Jan 04 16:43:08 CST 2019
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