package com.esofthead.mycollab.module.project.domain;

import com.esofthead.mycollab.core.utils.ValuedBean;

public class Role extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_role.id
     *
     * @mbggenerated Thu Jan 24 16:30:29 GMT+07:00 2013
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_role.rolename
     *
     * @mbggenerated Thu Jan 24 16:30:29 GMT+07:00 2013
     */
    private String rolename;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_role.description
     *
     * @mbggenerated Thu Jan 24 16:30:29 GMT+07:00 2013
     */
    private String description;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_role.sAccountId
     *
     * @mbggenerated Thu Jan 24 16:30:29 GMT+07:00 2013
     */
    private Integer saccountid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_role.projectid
     *
     * @mbggenerated Thu Jan 24 16:30:29 GMT+07:00 2013
     */
    private Integer projectid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_role.permissionVal
     *
     * @mbggenerated Thu Jan 24 16:30:29 GMT+07:00 2013
     */
    private String permissionval;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_role.id
     *
     * @return the value of m_prj_role.id
     *
     * @mbggenerated Thu Jan 24 16:30:29 GMT+07:00 2013
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
     * @mbggenerated Thu Jan 24 16:30:29 GMT+07:00 2013
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
     * @mbggenerated Thu Jan 24 16:30:29 GMT+07:00 2013
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
     * @mbggenerated Thu Jan 24 16:30:29 GMT+07:00 2013
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
     * @mbggenerated Thu Jan 24 16:30:29 GMT+07:00 2013
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
     * @mbggenerated Thu Jan 24 16:30:29 GMT+07:00 2013
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
     * @mbggenerated Thu Jan 24 16:30:29 GMT+07:00 2013
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
     * @mbggenerated Thu Jan 24 16:30:29 GMT+07:00 2013
     */
    public void setSaccountid(Integer saccountid) {
        this.saccountid = saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_role.projectid
     *
     * @return the value of m_prj_role.projectid
     *
     * @mbggenerated Thu Jan 24 16:30:29 GMT+07:00 2013
     */
    public Integer getProjectid() {
        return projectid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_role.projectid
     *
     * @param projectid the value for m_prj_role.projectid
     *
     * @mbggenerated Thu Jan 24 16:30:29 GMT+07:00 2013
     */
    public void setProjectid(Integer projectid) {
        this.projectid = projectid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_role.permissionVal
     *
     * @return the value of m_prj_role.permissionVal
     *
     * @mbggenerated Thu Jan 24 16:30:29 GMT+07:00 2013
     */
    public String getPermissionval() {
        return permissionval;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_role.permissionVal
     *
     * @param permissionval the value for m_prj_role.permissionVal
     *
     * @mbggenerated Thu Jan 24 16:30:29 GMT+07:00 2013
     */
    public void setPermissionval(String permissionval) {
        this.permissionval = permissionval;
    }
}