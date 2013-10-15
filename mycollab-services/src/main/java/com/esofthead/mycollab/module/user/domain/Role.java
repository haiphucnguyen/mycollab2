/*Domain class of table s_roles*/
package com.esofthead.mycollab.module.user.domain;

import com.esofthead.mycollab.core.utils.ValuedBean;

public class Role extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_roles.id
     *
     * @mbggenerated Tue Oct 15 11:01:51 ICT 2013
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_roles.rolename
     *
     * @mbggenerated Tue Oct 15 11:01:51 ICT 2013
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    private String rolename;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_roles.description
     *
     * @mbggenerated Tue Oct 15 11:01:51 ICT 2013
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    private String description;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_roles.sAccountId
     *
     * @mbggenerated Tue Oct 15 11:01:51 ICT 2013
     */
    private Integer saccountid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_roles.isSystemRole
     *
     * @mbggenerated Tue Oct 15 11:01:51 ICT 2013
     */
    private Boolean issystemrole;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_roles.id
     *
     * @return the value of s_roles.id
     *
     * @mbggenerated Tue Oct 15 11:01:51 ICT 2013
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_roles.id
     *
     * @param id the value for s_roles.id
     *
     * @mbggenerated Tue Oct 15 11:01:51 ICT 2013
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_roles.rolename
     *
     * @return the value of s_roles.rolename
     *
     * @mbggenerated Tue Oct 15 11:01:51 ICT 2013
     */
    public String getRolename() {
        return rolename;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_roles.rolename
     *
     * @param rolename the value for s_roles.rolename
     *
     * @mbggenerated Tue Oct 15 11:01:51 ICT 2013
     */
    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_roles.description
     *
     * @return the value of s_roles.description
     *
     * @mbggenerated Tue Oct 15 11:01:51 ICT 2013
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_roles.description
     *
     * @param description the value for s_roles.description
     *
     * @mbggenerated Tue Oct 15 11:01:51 ICT 2013
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_roles.sAccountId
     *
     * @return the value of s_roles.sAccountId
     *
     * @mbggenerated Tue Oct 15 11:01:51 ICT 2013
     */
    public Integer getSaccountid() {
        return saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_roles.sAccountId
     *
     * @param saccountid the value for s_roles.sAccountId
     *
     * @mbggenerated Tue Oct 15 11:01:51 ICT 2013
     */
    public void setSaccountid(Integer saccountid) {
        this.saccountid = saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_roles.isSystemRole
     *
     * @return the value of s_roles.isSystemRole
     *
     * @mbggenerated Tue Oct 15 11:01:51 ICT 2013
     */
    public Boolean getIssystemrole() {
        return issystemrole;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_roles.isSystemRole
     *
     * @param issystemrole the value for s_roles.isSystemRole
     *
     * @mbggenerated Tue Oct 15 11:01:51 ICT 2013
     */
    public void setIssystemrole(Boolean issystemrole) {
        this.issystemrole = issystemrole;
    }
}