package com.esofthead.mycollab.module.ecm.domain;

import java.util.ArrayList;
import java.util.List;

public class DriveInfoExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table m_ecm_driveinfo
     *
     * @mbggenerated Fri Sep 20 15:38:46 ICT 2013
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table m_ecm_driveinfo
     *
     * @mbggenerated Fri Sep 20 15:38:46 ICT 2013
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table m_ecm_driveinfo
     *
     * @mbggenerated Fri Sep 20 15:38:46 ICT 2013
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_ecm_driveinfo
     *
     * @mbggenerated Fri Sep 20 15:38:46 ICT 2013
     */
    public DriveInfoExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_ecm_driveinfo
     *
     * @mbggenerated Fri Sep 20 15:38:46 ICT 2013
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_ecm_driveinfo
     *
     * @mbggenerated Fri Sep 20 15:38:46 ICT 2013
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_ecm_driveinfo
     *
     * @mbggenerated Fri Sep 20 15:38:46 ICT 2013
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_ecm_driveinfo
     *
     * @mbggenerated Fri Sep 20 15:38:46 ICT 2013
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_ecm_driveinfo
     *
     * @mbggenerated Fri Sep 20 15:38:46 ICT 2013
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_ecm_driveinfo
     *
     * @mbggenerated Fri Sep 20 15:38:46 ICT 2013
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_ecm_driveinfo
     *
     * @mbggenerated Fri Sep 20 15:38:46 ICT 2013
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_ecm_driveinfo
     *
     * @mbggenerated Fri Sep 20 15:38:46 ICT 2013
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
     * This method corresponds to the database table m_ecm_driveinfo
     *
     * @mbggenerated Fri Sep 20 15:38:46 ICT 2013
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_ecm_driveinfo
     *
     * @mbggenerated Fri Sep 20 15:38:46 ICT 2013
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table m_ecm_driveinfo
     *
     * @mbggenerated Fri Sep 20 15:38:46 ICT 2013
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

        public Criteria andDocvolumeIsNull() {
            addCriterion("docVolume is null");
            return (Criteria) this;
        }

        public Criteria andDocvolumeIsNotNull() {
            addCriterion("docVolume is not null");
            return (Criteria) this;
        }

        public Criteria andDocvolumeEqualTo(Double value) {
            addCriterion("docVolume =", value, "docvolume");
            return (Criteria) this;
        }

        public Criteria andDocvolumeNotEqualTo(Double value) {
            addCriterion("docVolume <>", value, "docvolume");
            return (Criteria) this;
        }

        public Criteria andDocvolumeGreaterThan(Double value) {
            addCriterion("docVolume >", value, "docvolume");
            return (Criteria) this;
        }

        public Criteria andDocvolumeGreaterThanOrEqualTo(Double value) {
            addCriterion("docVolume >=", value, "docvolume");
            return (Criteria) this;
        }

        public Criteria andDocvolumeLessThan(Double value) {
            addCriterion("docVolume <", value, "docvolume");
            return (Criteria) this;
        }

        public Criteria andDocvolumeLessThanOrEqualTo(Double value) {
            addCriterion("docVolume <=", value, "docvolume");
            return (Criteria) this;
        }

        public Criteria andDocvolumeIn(List<Double> values) {
            addCriterion("docVolume in", values, "docvolume");
            return (Criteria) this;
        }

        public Criteria andDocvolumeNotIn(List<Double> values) {
            addCriterion("docVolume not in", values, "docvolume");
            return (Criteria) this;
        }

        public Criteria andDocvolumeBetween(Double value1, Double value2) {
            addCriterion("docVolume between", value1, value2, "docvolume");
            return (Criteria) this;
        }

        public Criteria andDocvolumeNotBetween(Double value1, Double value2) {
            addCriterion("docVolume not between", value1, value2, "docvolume");
            return (Criteria) this;
        }

        public Criteria andTextvolumeIsNull() {
            addCriterion("textVolume is null");
            return (Criteria) this;
        }

        public Criteria andTextvolumeIsNotNull() {
            addCriterion("textVolume is not null");
            return (Criteria) this;
        }

        public Criteria andTextvolumeEqualTo(Double value) {
            addCriterion("textVolume =", value, "textvolume");
            return (Criteria) this;
        }

        public Criteria andTextvolumeNotEqualTo(Double value) {
            addCriterion("textVolume <>", value, "textvolume");
            return (Criteria) this;
        }

        public Criteria andTextvolumeGreaterThan(Double value) {
            addCriterion("textVolume >", value, "textvolume");
            return (Criteria) this;
        }

        public Criteria andTextvolumeGreaterThanOrEqualTo(Double value) {
            addCriterion("textVolume >=", value, "textvolume");
            return (Criteria) this;
        }

        public Criteria andTextvolumeLessThan(Double value) {
            addCriterion("textVolume <", value, "textvolume");
            return (Criteria) this;
        }

        public Criteria andTextvolumeLessThanOrEqualTo(Double value) {
            addCriterion("textVolume <=", value, "textvolume");
            return (Criteria) this;
        }

        public Criteria andTextvolumeIn(List<Double> values) {
            addCriterion("textVolume in", values, "textvolume");
            return (Criteria) this;
        }

        public Criteria andTextvolumeNotIn(List<Double> values) {
            addCriterion("textVolume not in", values, "textvolume");
            return (Criteria) this;
        }

        public Criteria andTextvolumeBetween(Double value1, Double value2) {
            addCriterion("textVolume between", value1, value2, "textvolume");
            return (Criteria) this;
        }

        public Criteria andTextvolumeNotBetween(Double value1, Double value2) {
            addCriterion("textVolume not between", value1, value2, "textvolume");
            return (Criteria) this;
        }

        public Criteria andBinaryvolumeIsNull() {
            addCriterion("binaryVolume is null");
            return (Criteria) this;
        }

        public Criteria andBinaryvolumeIsNotNull() {
            addCriterion("binaryVolume is not null");
            return (Criteria) this;
        }

        public Criteria andBinaryvolumeEqualTo(Double value) {
            addCriterion("binaryVolume =", value, "binaryvolume");
            return (Criteria) this;
        }

        public Criteria andBinaryvolumeNotEqualTo(Double value) {
            addCriterion("binaryVolume <>", value, "binaryvolume");
            return (Criteria) this;
        }

        public Criteria andBinaryvolumeGreaterThan(Double value) {
            addCriterion("binaryVolume >", value, "binaryvolume");
            return (Criteria) this;
        }

        public Criteria andBinaryvolumeGreaterThanOrEqualTo(Double value) {
            addCriterion("binaryVolume >=", value, "binaryvolume");
            return (Criteria) this;
        }

        public Criteria andBinaryvolumeLessThan(Double value) {
            addCriterion("binaryVolume <", value, "binaryvolume");
            return (Criteria) this;
        }

        public Criteria andBinaryvolumeLessThanOrEqualTo(Double value) {
            addCriterion("binaryVolume <=", value, "binaryvolume");
            return (Criteria) this;
        }

        public Criteria andBinaryvolumeIn(List<Double> values) {
            addCriterion("binaryVolume in", values, "binaryvolume");
            return (Criteria) this;
        }

        public Criteria andBinaryvolumeNotIn(List<Double> values) {
            addCriterion("binaryVolume not in", values, "binaryvolume");
            return (Criteria) this;
        }

        public Criteria andBinaryvolumeBetween(Double value1, Double value2) {
            addCriterion("binaryVolume between", value1, value2, "binaryvolume");
            return (Criteria) this;
        }

        public Criteria andBinaryvolumeNotBetween(Double value1, Double value2) {
            addCriterion("binaryVolume not between", value1, value2, "binaryvolume");
            return (Criteria) this;
        }

        public Criteria andAudiovolumeIsNull() {
            addCriterion("audioVolume is null");
            return (Criteria) this;
        }

        public Criteria andAudiovolumeIsNotNull() {
            addCriterion("audioVolume is not null");
            return (Criteria) this;
        }

        public Criteria andAudiovolumeEqualTo(Double value) {
            addCriterion("audioVolume =", value, "audiovolume");
            return (Criteria) this;
        }

        public Criteria andAudiovolumeNotEqualTo(Double value) {
            addCriterion("audioVolume <>", value, "audiovolume");
            return (Criteria) this;
        }

        public Criteria andAudiovolumeGreaterThan(Double value) {
            addCriterion("audioVolume >", value, "audiovolume");
            return (Criteria) this;
        }

        public Criteria andAudiovolumeGreaterThanOrEqualTo(Double value) {
            addCriterion("audioVolume >=", value, "audiovolume");
            return (Criteria) this;
        }

        public Criteria andAudiovolumeLessThan(Double value) {
            addCriterion("audioVolume <", value, "audiovolume");
            return (Criteria) this;
        }

        public Criteria andAudiovolumeLessThanOrEqualTo(Double value) {
            addCriterion("audioVolume <=", value, "audiovolume");
            return (Criteria) this;
        }

        public Criteria andAudiovolumeIn(List<Double> values) {
            addCriterion("audioVolume in", values, "audiovolume");
            return (Criteria) this;
        }

        public Criteria andAudiovolumeNotIn(List<Double> values) {
            addCriterion("audioVolume not in", values, "audiovolume");
            return (Criteria) this;
        }

        public Criteria andAudiovolumeBetween(Double value1, Double value2) {
            addCriterion("audioVolume between", value1, value2, "audiovolume");
            return (Criteria) this;
        }

        public Criteria andAudiovolumeNotBetween(Double value1, Double value2) {
            addCriterion("audioVolume not between", value1, value2, "audiovolume");
            return (Criteria) this;
        }

        public Criteria andVideovolumeIsNull() {
            addCriterion("videoVolume is null");
            return (Criteria) this;
        }

        public Criteria andVideovolumeIsNotNull() {
            addCriterion("videoVolume is not null");
            return (Criteria) this;
        }

        public Criteria andVideovolumeEqualTo(Double value) {
            addCriterion("videoVolume =", value, "videovolume");
            return (Criteria) this;
        }

        public Criteria andVideovolumeNotEqualTo(Double value) {
            addCriterion("videoVolume <>", value, "videovolume");
            return (Criteria) this;
        }

        public Criteria andVideovolumeGreaterThan(Double value) {
            addCriterion("videoVolume >", value, "videovolume");
            return (Criteria) this;
        }

        public Criteria andVideovolumeGreaterThanOrEqualTo(Double value) {
            addCriterion("videoVolume >=", value, "videovolume");
            return (Criteria) this;
        }

        public Criteria andVideovolumeLessThan(Double value) {
            addCriterion("videoVolume <", value, "videovolume");
            return (Criteria) this;
        }

        public Criteria andVideovolumeLessThanOrEqualTo(Double value) {
            addCriterion("videoVolume <=", value, "videovolume");
            return (Criteria) this;
        }

        public Criteria andVideovolumeIn(List<Double> values) {
            addCriterion("videoVolume in", values, "videovolume");
            return (Criteria) this;
        }

        public Criteria andVideovolumeNotIn(List<Double> values) {
            addCriterion("videoVolume not in", values, "videovolume");
            return (Criteria) this;
        }

        public Criteria andVideovolumeBetween(Double value1, Double value2) {
            addCriterion("videoVolume between", value1, value2, "videovolume");
            return (Criteria) this;
        }

        public Criteria andVideovolumeNotBetween(Double value1, Double value2) {
            addCriterion("videoVolume not between", value1, value2, "videovolume");
            return (Criteria) this;
        }

        public Criteria andImagevolumeIsNull() {
            addCriterion("imageVolume is null");
            return (Criteria) this;
        }

        public Criteria andImagevolumeIsNotNull() {
            addCriterion("imageVolume is not null");
            return (Criteria) this;
        }

        public Criteria andImagevolumeEqualTo(Double value) {
            addCriterion("imageVolume =", value, "imagevolume");
            return (Criteria) this;
        }

        public Criteria andImagevolumeNotEqualTo(Double value) {
            addCriterion("imageVolume <>", value, "imagevolume");
            return (Criteria) this;
        }

        public Criteria andImagevolumeGreaterThan(Double value) {
            addCriterion("imageVolume >", value, "imagevolume");
            return (Criteria) this;
        }

        public Criteria andImagevolumeGreaterThanOrEqualTo(Double value) {
            addCriterion("imageVolume >=", value, "imagevolume");
            return (Criteria) this;
        }

        public Criteria andImagevolumeLessThan(Double value) {
            addCriterion("imageVolume <", value, "imagevolume");
            return (Criteria) this;
        }

        public Criteria andImagevolumeLessThanOrEqualTo(Double value) {
            addCriterion("imageVolume <=", value, "imagevolume");
            return (Criteria) this;
        }

        public Criteria andImagevolumeIn(List<Double> values) {
            addCriterion("imageVolume in", values, "imagevolume");
            return (Criteria) this;
        }

        public Criteria andImagevolumeNotIn(List<Double> values) {
            addCriterion("imageVolume not in", values, "imagevolume");
            return (Criteria) this;
        }

        public Criteria andImagevolumeBetween(Double value1, Double value2) {
            addCriterion("imageVolume between", value1, value2, "imagevolume");
            return (Criteria) this;
        }

        public Criteria andImagevolumeNotBetween(Double value1, Double value2) {
            addCriterion("imageVolume not between", value1, value2, "imagevolume");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table m_ecm_driveinfo
     *
     * @mbggenerated do_not_delete_during_merge Fri Sep 20 15:38:46 ICT 2013
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table m_ecm_driveinfo
     *
     * @mbggenerated Fri Sep 20 15:38:46 ICT 2013
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