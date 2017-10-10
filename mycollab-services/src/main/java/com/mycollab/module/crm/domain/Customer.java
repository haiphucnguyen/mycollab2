/**
 * mycollab-services - Parent pom providing dependency and plugin management for applications
		built with Maven
 * Copyright © 2017 MyCollab (support@mycollab.com)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
/**
 * mycollab-services - Parent pom providing dependency and plugin management for applications
		built with Maven
 * Copyright © ${project.inceptionYear} MyCollab (support@mycollab.com)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
/*Domain class of table m_crm_customer*/
package com.mycollab.module.crm.domain;

import com.mycollab.core.arguments.ValuedBean;
import com.mycollab.db.metadata.Column;
import com.mycollab.db.metadata.Table;
import java.util.Date;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.ibatis.type.Alias;
import org.hibernate.validator.constraints.Length;

@SuppressWarnings("ucd")
@Table("m_crm_customer")
@Alias("Customer")
public class Customer extends ValuedBean {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_customer.id
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    @Column("id")
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_customer.firstname
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    @Length(max=45, message="Field value is too long")
    @Column("firstname")
    private String firstname;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_customer.lastname
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    @Length(max=45, message="Field value is too long")
    @Column("lastname")
    private String lastname;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_customer.leadsource
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    @Column("leadsource")
    private Integer leadsource;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_customer.assignUser
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    @Length(max=45, message="Field value is too long")
    @Column("assignUser")
    private String assignuser;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_customer.department
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    @Length(max=255, message="Field value is too long")
    @Column("department")
    private String department;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_customer.officePhone
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    @Length(max=45, message="Field value is too long")
    @Column("officePhone")
    private String officephone;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_customer.mobile
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    @Length(max=45, message="Field value is too long")
    @Column("mobile")
    private String mobile;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_customer.homePhone
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    @Length(max=45, message="Field value is too long")
    @Column("homePhone")
    private String homephone;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_customer.assisstant
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    @Length(max=80, message="Field value is too long")
    @Column("assisstant")
    private String assisstant;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_customer.assisstantPhone
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    @Length(max=45, message="Field value is too long")
    @Column("assisstantPhone")
    private String assisstantphone;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_customer.birthday
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    @Column("birthday")
    private Date birthday;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_customer.createdTime
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    @Column("createdTime")
    private Date createdtime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_customer.createdUser
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    @Length(max=45, message="Field value is too long")
    @Column("createdUser")
    private String createduser;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_customer.sAccountId
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    @Column("sAccountId")
    private Integer saccountid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_customer.lastUpdatedTime
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    @Column("lastUpdatedTime")
    private Date lastupdatedtime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_customer.title
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    @Length(max=45, message="Field value is too long")
    @Column("title")
    private String title;

    private static final long serialVersionUID = 1;

    public final boolean equals(Object obj) {
        if (obj == null) { return false;}
        if (obj == this) { return true;}
        if (!obj.getClass().isAssignableFrom(getClass())) { return false;}
        Customer item = (Customer)obj;
        return new EqualsBuilder().append(id, item.id).build();
    }

    public final int hashCode() {
        return new HashCodeBuilder(1367, 497).append(id).build();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_customer.id
     *
     * @return the value of m_crm_customer.id
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
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
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_customer.firstname
     *
     * @return the value of m_crm_customer.firstname
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
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
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
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
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
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
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
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
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
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
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    public void setLeadsource(Integer leadsource) {
        this.leadsource = leadsource;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_customer.assignUser
     *
     * @return the value of m_crm_customer.assignUser
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    public String getAssignuser() {
        return assignuser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_customer.assignUser
     *
     * @param assignuser the value for m_crm_customer.assignUser
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    public void setAssignuser(String assignuser) {
        this.assignuser = assignuser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_customer.department
     *
     * @return the value of m_crm_customer.department
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
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
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
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
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
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
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
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
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
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
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
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
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
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
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
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
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
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
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
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
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
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
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
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
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
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
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
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
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
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
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
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
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
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
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
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
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
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
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
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
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
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
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    public void setLastupdatedtime(Date lastupdatedtime) {
        this.lastupdatedtime = lastupdatedtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_customer.title
     *
     * @return the value of m_crm_customer.title
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_customer.title
     *
     * @param title the value for m_crm_customer.title
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    public void setTitle(String title) {
        this.title = title;
    }

    public enum Field {
        id,
        firstname,
        lastname,
        leadsource,
        assignuser,
        department,
        officephone,
        mobile,
        homephone,
        assisstant,
        assisstantphone,
        birthday,
        createdtime,
        createduser,
        saccountid,
        lastupdatedtime,
        title;

        public boolean equalTo(Object value) {
            return name().equals(value);
        }
    }
}