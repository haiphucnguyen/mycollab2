package com.esofthead.mycollab.module.crm.domain;

import com.esofthead.mycollab.core.utils.ValuedBean;

public class TypeRelationship extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_type_relationship.id
     *
     * @mbggenerated Mon Feb 04 22:03:51 GMT+07:00 2013
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_type_relationship.type1id
     *
     * @mbggenerated Mon Feb 04 22:03:51 GMT+07:00 2013
     */
    private Integer type1id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_type_relationship.type2id
     *
     * @mbggenerated Mon Feb 04 22:03:51 GMT+07:00 2013
     */
    private Integer type2id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_type_relationship.type
     *
     * @mbggenerated Mon Feb 04 22:03:51 GMT+07:00 2013
     */
    private Integer type;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_type_relationship.id
     *
     * @return the value of m_crm_type_relationship.id
     *
     * @mbggenerated Mon Feb 04 22:03:51 GMT+07:00 2013
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_type_relationship.id
     *
     * @param id the value for m_crm_type_relationship.id
     *
     * @mbggenerated Mon Feb 04 22:03:51 GMT+07:00 2013
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_type_relationship.type1id
     *
     * @return the value of m_crm_type_relationship.type1id
     *
     * @mbggenerated Mon Feb 04 22:03:51 GMT+07:00 2013
     */
    public Integer getType1id() {
        return type1id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_type_relationship.type1id
     *
     * @param type1id the value for m_crm_type_relationship.type1id
     *
     * @mbggenerated Mon Feb 04 22:03:51 GMT+07:00 2013
     */
    public void setType1id(Integer type1id) {
        this.type1id = type1id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_type_relationship.type2id
     *
     * @return the value of m_crm_type_relationship.type2id
     *
     * @mbggenerated Mon Feb 04 22:03:51 GMT+07:00 2013
     */
    public Integer getType2id() {
        return type2id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_type_relationship.type2id
     *
     * @param type2id the value for m_crm_type_relationship.type2id
     *
     * @mbggenerated Mon Feb 04 22:03:51 GMT+07:00 2013
     */
    public void setType2id(Integer type2id) {
        this.type2id = type2id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_type_relationship.type
     *
     * @return the value of m_crm_type_relationship.type
     *
     * @mbggenerated Mon Feb 04 22:03:51 GMT+07:00 2013
     */
    public Integer getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_type_relationship.type
     *
     * @param type the value for m_crm_type_relationship.type
     *
     * @mbggenerated Mon Feb 04 22:03:51 GMT+07:00 2013
     */
    public void setType(Integer type) {
        this.type = type;
    }
}