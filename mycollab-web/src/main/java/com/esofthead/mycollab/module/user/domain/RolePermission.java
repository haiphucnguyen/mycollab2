package com.esofthead.mycollab.module.user.domain;

public class RolePermission {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_role_permission.id
     *
     * @mbggenerated Wed Nov 14 15:52:02 GMT+07:00 2012
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_role_permission.pathid
     *
     * @mbggenerated Wed Nov 14 15:52:02 GMT+07:00 2012
     */
    private String pathid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_role_permission.isAuthorz
     *
     * @mbggenerated Wed Nov 14 15:52:02 GMT+07:00 2012
     */
    private Boolean isauthorz;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_role_permission.roleid
     *
     * @mbggenerated Wed Nov 14 15:52:02 GMT+07:00 2012
     */
    private Integer roleid;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_role_permission.id
     *
     * @return the value of s_role_permission.id
     *
     * @mbggenerated Wed Nov 14 15:52:02 GMT+07:00 2012
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_role_permission.id
     *
     * @param id the value for s_role_permission.id
     *
     * @mbggenerated Wed Nov 14 15:52:02 GMT+07:00 2012
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_role_permission.pathid
     *
     * @return the value of s_role_permission.pathid
     *
     * @mbggenerated Wed Nov 14 15:52:02 GMT+07:00 2012
     */
    public String getPathid() {
        return pathid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_role_permission.pathid
     *
     * @param pathid the value for s_role_permission.pathid
     *
     * @mbggenerated Wed Nov 14 15:52:02 GMT+07:00 2012
     */
    public void setPathid(String pathid) {
        this.pathid = pathid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_role_permission.isAuthorz
     *
     * @return the value of s_role_permission.isAuthorz
     *
     * @mbggenerated Wed Nov 14 15:52:02 GMT+07:00 2012
     */
    public Boolean getIsauthorz() {
        return isauthorz;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_role_permission.isAuthorz
     *
     * @param isauthorz the value for s_role_permission.isAuthorz
     *
     * @mbggenerated Wed Nov 14 15:52:02 GMT+07:00 2012
     */
    public void setIsauthorz(Boolean isauthorz) {
        this.isauthorz = isauthorz;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_role_permission.roleid
     *
     * @return the value of s_role_permission.roleid
     *
     * @mbggenerated Wed Nov 14 15:52:02 GMT+07:00 2012
     */
    public Integer getRoleid() {
        return roleid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_role_permission.roleid
     *
     * @param roleid the value for s_role_permission.roleid
     *
     * @mbggenerated Wed Nov 14 15:52:02 GMT+07:00 2012
     */
    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }
}