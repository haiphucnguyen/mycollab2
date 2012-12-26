package com.esofthead.mycollab.module.project.domain;

import com.esofthead.mycollab.core.utils.ValuedBean;

public class Role extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_role.id
     *
     * @mbggenerated Wed Dec 26 18:11:48 GMT+07:00 2012
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_role.rolename
     *
     * @mbggenerated Wed Dec 26 18:11:48 GMT+07:00 2012
     */
    private String rolename;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_role.description
     *
     * @mbggenerated Wed Dec 26 18:11:48 GMT+07:00 2012
     */
    private String description;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_role.sAccountId
     *
     * @mbggenerated Wed Dec 26 18:11:48 GMT+07:00 2012
     */
    private Integer saccountid;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_role.id
     *
     * @return the value of m_prj_role.id
     *
     * @mbggenerated Wed Dec 26 18:11:48 GMT+07:00 2012
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_role.id
     *
     * @param id the value for m_prj_role.id
     *
     * @mbggenerated Wed Dec 26 18:11:48 GMT+07:00 2012
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_role.rolename
     *
     * @return the value of m_prj_role.rolename
     *
     * @mbggenerated Wed Dec 26 18:11:48 GMT+07:00 2012
     */
    public String getRolename() {
        return rolename;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_role.rolename
     *
     * @param rolename the value for m_prj_role.rolename
     *
     * @mbggenerated Wed Dec 26 18:11:48 GMT+07:00 2012
     */
    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_role.description
     *
     * @return the value of m_prj_role.description
     *
     * @mbggenerated Wed Dec 26 18:11:48 GMT+07:00 2012
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_role.description
     *
     * @param description the value for m_prj_role.description
     *
     * @mbggenerated Wed Dec 26 18:11:48 GMT+07:00 2012
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_role.sAccountId
     *
     * @return the value of m_prj_role.sAccountId
     *
     * @mbggenerated Wed Dec 26 18:11:48 GMT+07:00 2012
     */
    public Integer getSaccountid() {
        return saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_role.sAccountId
     *
     * @param saccountid the value for m_prj_role.sAccountId
     *
     * @mbggenerated Wed Dec 26 18:11:48 GMT+07:00 2012
     */
    public void setSaccountid(Integer saccountid) {
        this.saccountid = saccountid;
    }
}