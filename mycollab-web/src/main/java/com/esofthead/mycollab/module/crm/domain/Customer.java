package com.esofthead.mycollab.module.crm.domain;

import com.esofthead.mycollab.core.utils.ValuedBean;
import java.util.Date;

public class Customer extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_customer.id
     *
     * @mbggenerated Sun Dec 02 12:54:53 GMT+07:00 2012
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_customer.title
     *
     * @mbggenerated Sun Dec 02 12:54:53 GMT+07:00 2012
     */
    private Integer title;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_customer.firstname
     *
     * @mbggenerated Sun Dec 02 12:54:53 GMT+07:00 2012
     */
    private String firstname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_customer.lastname
     *
     * @mbggenerated Sun Dec 02 12:54:53 GMT+07:00 2012
     */
    private String lastname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_customer.leadsource
     *
     * @mbggenerated Sun Dec 02 12:54:53 GMT+07:00 2012
     */
    private Integer leadsource;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_customer.assignto
     *
     * @mbggenerated Sun Dec 02 12:54:53 GMT+07:00 2012
     */
    private Integer assignto;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_customer.department
     *
     * @mbggenerated Sun Dec 02 12:54:53 GMT+07:00 2012
     */
    private String department;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_customer.officePhone
     *
     * @mbggenerated Sun Dec 02 12:54:53 GMT+07:00 2012
     */
    private String officephone;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_customer.mobile
     *
     * @mbggenerated Sun Dec 02 12:54:53 GMT+07:00 2012
     */
    private String mobile;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_customer.homePhone
     *
     * @mbggenerated Sun Dec 02 12:54:53 GMT+07:00 2012
     */
    private String homephone;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_customer.assisstant
     *
     * @mbggenerated Sun Dec 02 12:54:53 GMT+07:00 2012
     */
    private String assisstant;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_customer.assisstantPhone
     *
     * @mbggenerated Sun Dec 02 12:54:53 GMT+07:00 2012
     */
    private String assisstantphone;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_customer.birthday
     *
     * @mbggenerated Sun Dec 02 12:54:53 GMT+07:00 2012
     */
    private Date birthday;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_customer.createdTime
     *
     * @mbggenerated Sun Dec 02 12:54:53 GMT+07:00 2012
     */
    private Date createdtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_customer.createdUser
     *
     * @mbggenerated Sun Dec 02 12:54:53 GMT+07:00 2012
     */
    private String createduser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_customer.sAccountId
     *
     * @mbggenerated Sun Dec 02 12:54:53 GMT+07:00 2012
     */
    private Integer saccountid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_customer.lastUpdatedTime
     *
     * @mbggenerated Sun Dec 02 12:54:53 GMT+07:00 2012
     */
    private Date lastupdatedtime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_customer.id
     *
     * @return the value of m_crm_customer.id
     *
     * @mbggenerated Sun Dec 02 12:54:53 GMT+07:00 2012
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_customer.id
     *
     * @param id the value for m_crm_customer.id
     *
     * @mbggenerated Sun Dec 02 12:54:53 GMT+07:00 2012
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_customer.title
     *
     * @return the value of m_crm_customer.title
     *
     * @mbggenerated Sun Dec 02 12:54:53 GMT+07:00 2012
     */
    public Integer getTitle() {
        return title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_customer.title
     *
     * @param title the value for m_crm_customer.title
     *
     * @mbggenerated Sun Dec 02 12:54:53 GMT+07:00 2012
     */
    public void setTitle(Integer title) {
        this.title = title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_customer.firstname
     *
     * @return the value of m_crm_customer.firstname
     *
     * @mbggenerated Sun Dec 02 12:54:53 GMT+07:00 2012
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_customer.firstname
     *
     * @param firstname the value for m_crm_customer.firstname
     *
     * @mbggenerated Sun Dec 02 12:54:53 GMT+07:00 2012
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_customer.lastname
     *
     * @return the value of m_crm_customer.lastname
     *
     * @mbggenerated Sun Dec 02 12:54:53 GMT+07:00 2012
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_customer.lastname
     *
     * @param lastname the value for m_crm_customer.lastname
     *
     * @mbggenerated Sun Dec 02 12:54:53 GMT+07:00 2012
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_customer.leadsource
     *
     * @return the value of m_crm_customer.leadsource
     *
     * @mbggenerated Sun Dec 02 12:54:53 GMT+07:00 2012
     */
    public Integer getLeadsource() {
        return leadsource;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_customer.leadsource
     *
     * @param leadsource the value for m_crm_customer.leadsource
     *
     * @mbggenerated Sun Dec 02 12:54:53 GMT+07:00 2012
     */
    public void setLeadsource(Integer leadsource) {
        this.leadsource = leadsource;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_customer.assignto
     *
     * @return the value of m_crm_customer.assignto
     *
     * @mbggenerated Sun Dec 02 12:54:53 GMT+07:00 2012
     */
    public Integer getAssignto() {
        return assignto;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_customer.assignto
     *
     * @param assignto the value for m_crm_customer.assignto
     *
     * @mbggenerated Sun Dec 02 12:54:53 GMT+07:00 2012
     */
    public void setAssignto(Integer assignto) {
        this.assignto = assignto;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_customer.department
     *
     * @return the value of m_crm_customer.department
     *
     * @mbggenerated Sun Dec 02 12:54:53 GMT+07:00 2012
     */
    public String getDepartment() {
        return department;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_customer.department
     *
     * @param department the value for m_crm_customer.department
     *
     * @mbggenerated Sun Dec 02 12:54:53 GMT+07:00 2012
     */
    public void setDepartment(String department) {
        this.department = department;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_customer.officePhone
     *
     * @return the value of m_crm_customer.officePhone
     *
     * @mbggenerated Sun Dec 02 12:54:53 GMT+07:00 2012
     */
    public String getOfficephone() {
        return officephone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_customer.officePhone
     *
     * @param officephone the value for m_crm_customer.officePhone
     *
     * @mbggenerated Sun Dec 02 12:54:53 GMT+07:00 2012
     */
    public void setOfficephone(String officephone) {
        this.officephone = officephone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_customer.mobile
     *
     * @return the value of m_crm_customer.mobile
     *
     * @mbggenerated Sun Dec 02 12:54:53 GMT+07:00 2012
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_customer.mobile
     *
     * @param mobile the value for m_crm_customer.mobile
     *
     * @mbggenerated Sun Dec 02 12:54:53 GMT+07:00 2012
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_customer.homePhone
     *
     * @return the value of m_crm_customer.homePhone
     *
     * @mbggenerated Sun Dec 02 12:54:53 GMT+07:00 2012
     */
    public String getHomephone() {
        return homephone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_customer.homePhone
     *
     * @param homephone the value for m_crm_customer.homePhone
     *
     * @mbggenerated Sun Dec 02 12:54:53 GMT+07:00 2012
     */
    public void setHomephone(String homephone) {
        this.homephone = homephone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_customer.assisstant
     *
     * @return the value of m_crm_customer.assisstant
     *
     * @mbggenerated Sun Dec 02 12:54:53 GMT+07:00 2012
     */
    public String getAssisstant() {
        return assisstant;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_customer.assisstant
     *
     * @param assisstant the value for m_crm_customer.assisstant
     *
     * @mbggenerated Sun Dec 02 12:54:53 GMT+07:00 2012
     */
    public void setAssisstant(String assisstant) {
        this.assisstant = assisstant;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_customer.assisstantPhone
     *
     * @return the value of m_crm_customer.assisstantPhone
     *
     * @mbggenerated Sun Dec 02 12:54:53 GMT+07:00 2012
     */
    public String getAssisstantphone() {
        return assisstantphone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_customer.assisstantPhone
     *
     * @param assisstantphone the value for m_crm_customer.assisstantPhone
     *
     * @mbggenerated Sun Dec 02 12:54:53 GMT+07:00 2012
     */
    public void setAssisstantphone(String assisstantphone) {
        this.assisstantphone = assisstantphone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_customer.birthday
     *
     * @return the value of m_crm_customer.birthday
     *
     * @mbggenerated Sun Dec 02 12:54:53 GMT+07:00 2012
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_customer.birthday
     *
     * @param birthday the value for m_crm_customer.birthday
     *
     * @mbggenerated Sun Dec 02 12:54:53 GMT+07:00 2012
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_customer.createdTime
     *
     * @return the value of m_crm_customer.createdTime
     *
     * @mbggenerated Sun Dec 02 12:54:53 GMT+07:00 2012
     */
    public Date getCreatedtime() {
        return createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_customer.createdTime
     *
     * @param createdtime the value for m_crm_customer.createdTime
     *
     * @mbggenerated Sun Dec 02 12:54:53 GMT+07:00 2012
     */
    public void setCreatedtime(Date createdtime) {
        this.createdtime = createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_customer.createdUser
     *
     * @return the value of m_crm_customer.createdUser
     *
     * @mbggenerated Sun Dec 02 12:54:53 GMT+07:00 2012
     */
    public String getCreateduser() {
        return createduser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_customer.createdUser
     *
     * @param createduser the value for m_crm_customer.createdUser
     *
     * @mbggenerated Sun Dec 02 12:54:53 GMT+07:00 2012
     */
    public void setCreateduser(String createduser) {
        this.createduser = createduser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_customer.sAccountId
     *
     * @return the value of m_crm_customer.sAccountId
     *
     * @mbggenerated Sun Dec 02 12:54:53 GMT+07:00 2012
     */
    public Integer getSaccountid() {
        return saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_customer.sAccountId
     *
     * @param saccountid the value for m_crm_customer.sAccountId
     *
     * @mbggenerated Sun Dec 02 12:54:53 GMT+07:00 2012
     */
    public void setSaccountid(Integer saccountid) {
        this.saccountid = saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_customer.lastUpdatedTime
     *
     * @return the value of m_crm_customer.lastUpdatedTime
     *
     * @mbggenerated Sun Dec 02 12:54:53 GMT+07:00 2012
     */
    public Date getLastupdatedtime() {
        return lastupdatedtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_customer.lastUpdatedTime
     *
     * @param lastupdatedtime the value for m_crm_customer.lastUpdatedTime
     *
     * @mbggenerated Sun Dec 02 12:54:53 GMT+07:00 2012
     */
    public void setLastupdatedtime(Date lastupdatedtime) {
        this.lastupdatedtime = lastupdatedtime;
    }
}