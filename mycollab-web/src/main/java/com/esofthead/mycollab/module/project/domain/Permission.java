package com.esofthead.mycollab.module.project.domain;

import com.esofthead.mycollab.core.utils.ValuedBean;

public class Permission extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_permission.id
     *
     * @mbggenerated Mon Dec 17 11:20:14 GMT+07:00 2012
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_permission.projectid
     *
     * @mbggenerated Mon Dec 17 11:20:14 GMT+07:00 2012
     */
    private Integer projectid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_permission.pathid
     *
     * @mbggenerated Mon Dec 17 11:20:14 GMT+07:00 2012
     */
    private String pathid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_permission.canAccess
     *
     * @mbggenerated Mon Dec 17 11:20:14 GMT+07:00 2012
     */
    private Boolean canaccess;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_permission.username
     *
     * @mbggenerated Mon Dec 17 11:20:14 GMT+07:00 2012
     */
    private String username;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_permission.id
     *
     * @return the value of m_prj_permission.id
     *
     * @mbggenerated Mon Dec 17 11:20:14 GMT+07:00 2012
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_permission.id
     *
     * @param id the value for m_prj_permission.id
     *
     * @mbggenerated Mon Dec 17 11:20:14 GMT+07:00 2012
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_permission.projectid
     *
     * @return the value of m_prj_permission.projectid
     *
     * @mbggenerated Mon Dec 17 11:20:14 GMT+07:00 2012
     */
    public Integer getProjectid() {
        return projectid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_permission.projectid
     *
     * @param projectid the value for m_prj_permission.projectid
     *
     * @mbggenerated Mon Dec 17 11:20:14 GMT+07:00 2012
     */
    public void setProjectid(Integer projectid) {
        this.projectid = projectid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_permission.pathid
     *
     * @return the value of m_prj_permission.pathid
     *
     * @mbggenerated Mon Dec 17 11:20:14 GMT+07:00 2012
     */
    public String getPathid() {
        return pathid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_permission.pathid
     *
     * @param pathid the value for m_prj_permission.pathid
     *
     * @mbggenerated Mon Dec 17 11:20:14 GMT+07:00 2012
     */
    public void setPathid(String pathid) {
        this.pathid = pathid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_permission.canAccess
     *
     * @return the value of m_prj_permission.canAccess
     *
     * @mbggenerated Mon Dec 17 11:20:14 GMT+07:00 2012
     */
    public Boolean getCanaccess() {
        return canaccess;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_permission.canAccess
     *
     * @param canaccess the value for m_prj_permission.canAccess
     *
     * @mbggenerated Mon Dec 17 11:20:14 GMT+07:00 2012
     */
    public void setCanaccess(Boolean canaccess) {
        this.canaccess = canaccess;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_permission.username
     *
     * @return the value of m_prj_permission.username
     *
     * @mbggenerated Mon Dec 17 11:20:14 GMT+07:00 2012
     */
    public String getUsername() {
        return username;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_permission.username
     *
     * @param username the value for m_prj_permission.username
     *
     * @mbggenerated Mon Dec 17 11:20:14 GMT+07:00 2012
     */
    public void setUsername(String username) {
        this.username = username;
    }
}