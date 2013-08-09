/*Domain class of table s_role_permission*/
package com.esofthead.mycollab.module.user.domain;

import com.esofthead.mycollab.core.utils.ValuedBean;

public class RolePermission extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_role_permission.id
     *
     * @mbggenerated Thu Aug 08 17:37:40 GMT+07:00 2013
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_role_permission.roleid
     *
     * @mbggenerated Thu Aug 08 17:37:40 GMT+07:00 2013
     */
    private Integer roleid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_role_permission.roleVal
     *
     * @mbggenerated Thu Aug 08 17:37:40 GMT+07:00 2013
     */
    @org.hibernate.validator.constraints.Length(max=65535, message="Field value is too long")
    private String roleval;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_role_permission.id
     *
     * @return the value of s_role_permission.id
     *
     * @mbggenerated Thu Aug 08 17:37:40 GMT+07:00 2013
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
     * @mbggenerated Thu Aug 08 17:37:40 GMT+07:00 2013
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_role_permission.roleid
     *
     * @return the value of s_role_permission.roleid
     *
     * @mbggenerated Thu Aug 08 17:37:40 GMT+07:00 2013
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
     * @mbggenerated Thu Aug 08 17:37:40 GMT+07:00 2013
     */
    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_role_permission.roleVal
     *
     * @return the value of s_role_permission.roleVal
     *
     * @mbggenerated Thu Aug 08 17:37:40 GMT+07:00 2013
     */
    public String getRoleval() {
        return roleval;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_role_permission.roleVal
     *
     * @param roleval the value for s_role_permission.roleVal
     *
     * @mbggenerated Thu Aug 08 17:37:40 GMT+07:00 2013
     */
    public void setRoleval(String roleval) {
        this.roleval = roleval;
    }
}