/**
 * This file is part of mycollab-services.
 *
 * mycollab-services is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-services is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-services.  If not, see <http://www.gnu.org/licenses/>.
 */
/*Domain class of table m_crm_target*/
package com.esofthead.mycollab.module.crm.domain;

import com.esofthead.mycollab.core.arguments.ValuedBean;
import com.esofthead.mycollab.core.db.metadata.Column;
import com.esofthead.mycollab.core.db.metadata.Table;
import java.util.Date;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.Length;

@SuppressWarnings("ucd")
@Table("m_crm_target")
public class Target extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_target.id
     *
     * @mbggenerated Tue Oct 27 22:57:40 ICT 2015
     */
    @Column("id")
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_target.prefixname
     *
     * @mbggenerated Tue Oct 27 22:57:40 ICT 2015
     */
    @Length(max=45, message="Field value is too long")
    @Column("prefixname")
    private String prefixname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_target.firstname
     *
     * @mbggenerated Tue Oct 27 22:57:40 ICT 2015
     */
    @Length(max=45, message="Field value is too long")
    @Column("firstname")
    private String firstname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_target.lastname
     *
     * @mbggenerated Tue Oct 27 22:57:40 ICT 2015
     */
    @Length(max=45, message="Field value is too long")
    @Column("lastname")
    private String lastname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_target.title
     *
     * @mbggenerated Tue Oct 27 22:57:40 ICT 2015
     */
    @Length(max=255, message="Field value is too long")
    @Column("title")
    private String title;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_target.department
     *
     * @mbggenerated Tue Oct 27 22:57:40 ICT 2015
     */
    @Length(max=255, message="Field value is too long")
    @Column("department")
    private String department;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_target.birthday
     *
     * @mbggenerated Tue Oct 27 22:57:40 ICT 2015
     */
    @Column("birthday")
    private Date birthday;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_target.accountname
     *
     * @mbggenerated Tue Oct 27 22:57:40 ICT 2015
     */
    @Length(max=255, message="Field value is too long")
    @Column("accountname")
    private String accountname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_target.isCallable
     *
     * @mbggenerated Tue Oct 27 22:57:40 ICT 2015
     */
    @Column("isCallable")
    private Boolean iscallable;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_target.officePhone
     *
     * @mbggenerated Tue Oct 27 22:57:40 ICT 2015
     */
    @Length(max=45, message="Field value is too long")
    @Column("officePhone")
    private String officephone;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_target.mobile
     *
     * @mbggenerated Tue Oct 27 22:57:40 ICT 2015
     */
    @Length(max=45, message="Field value is too long")
    @Column("mobile")
    private String mobile;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_target.homePhone
     *
     * @mbggenerated Tue Oct 27 22:57:40 ICT 2015
     */
    @Length(max=45, message="Field value is too long")
    @Column("homePhone")
    private String homephone;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_target.otherPhone
     *
     * @mbggenerated Tue Oct 27 22:57:40 ICT 2015
     */
    @Length(max=45, message="Field value is too long")
    @Column("otherPhone")
    private String otherphone;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_target.fax
     *
     * @mbggenerated Tue Oct 27 22:57:40 ICT 2015
     */
    @Length(max=45, message="Field value is too long")
    @Column("fax")
    private String fax;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_target.assistant
     *
     * @mbggenerated Tue Oct 27 22:57:40 ICT 2015
     */
    @Length(max=255, message="Field value is too long")
    @Column("assistant")
    private String assistant;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_target.assistantPhone
     *
     * @mbggenerated Tue Oct 27 22:57:40 ICT 2015
     */
    @Length(max=45, message="Field value is too long")
    @Column("assistantPhone")
    private String assistantphone;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_target.primaryAddress
     *
     * @mbggenerated Tue Oct 27 22:57:40 ICT 2015
     */
    @Length(max=255, message="Field value is too long")
    @Column("primaryAddress")
    private String primaryaddress;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_target.primaryCity
     *
     * @mbggenerated Tue Oct 27 22:57:40 ICT 2015
     */
    @Length(max=45, message="Field value is too long")
    @Column("primaryCity")
    private String primarycity;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_target.primaryState
     *
     * @mbggenerated Tue Oct 27 22:57:40 ICT 2015
     */
    @Length(max=45, message="Field value is too long")
    @Column("primaryState")
    private String primarystate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_target.primaryPostal
     *
     * @mbggenerated Tue Oct 27 22:57:40 ICT 2015
     */
    @Length(max=45, message="Field value is too long")
    @Column("primaryPostal")
    private String primarypostal;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_target.primaryCountryId
     *
     * @mbggenerated Tue Oct 27 22:57:40 ICT 2015
     */
    @Column("primaryCountryId")
    private Integer primarycountryid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_target.description
     *
     * @mbggenerated Tue Oct 27 22:57:40 ICT 2015
     */
    @Length(max=255, message="Field value is too long")
    @Column("description")
    private String description;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_target.email
     *
     * @mbggenerated Tue Oct 27 22:57:40 ICT 2015
     */
    @Length(max=255, message="Field value is too long")
    @Column("email")
    private String email;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_target.createdTime
     *
     * @mbggenerated Tue Oct 27 22:57:40 ICT 2015
     */
    @Column("createdTime")
    private Date createdtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_target.createdUser
     *
     * @mbggenerated Tue Oct 27 22:57:40 ICT 2015
     */
    @Length(max=45, message="Field value is too long")
    @Column("createdUser")
    private String createduser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_target.sAccountId
     *
     * @mbggenerated Tue Oct 27 22:57:40 ICT 2015
     */
    @Column("sAccountId")
    private Integer saccountid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_target.assignUser
     *
     * @mbggenerated Tue Oct 27 22:57:40 ICT 2015
     */
    @Length(max=45, message="Field value is too long")
    @Column("assignUser")
    private String assignuser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_target.lastUpdatedTime
     *
     * @mbggenerated Tue Oct 27 22:57:40 ICT 2015
     */
    @Column("lastUpdatedTime")
    private Date lastupdatedtime;

    private static final long serialVersionUID = 1;

    public final boolean equals(Object obj) {
        if (obj == null) { return false;}
        if (obj == this) { return true;}
        if (obj.getClass() != getClass()) { return false;}
        Target item = (Target)obj;
        return new EqualsBuilder().append(id, item.id).build();
    }

    public final int hashCode() {
        return new HashCodeBuilder(429, 1679).append(id).build();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_target.id
     *
     * @return the value of m_crm_target.id
     *
     * @mbggenerated Tue Oct 27 22:57:40 ICT 2015
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_target.id
     *
     * @param id the value for m_crm_target.id
     *
     * @mbggenerated Tue Oct 27 22:57:40 ICT 2015
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_target.prefixname
     *
     * @return the value of m_crm_target.prefixname
     *
     * @mbggenerated Tue Oct 27 22:57:40 ICT 2015
     */
    public String getPrefixname() {
        return prefixname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_target.prefixname
     *
     * @param prefixname the value for m_crm_target.prefixname
     *
     * @mbggenerated Tue Oct 27 22:57:40 ICT 2015
     */
    public void setPrefixname(String prefixname) {
        this.prefixname = prefixname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_target.firstname
     *
     * @return the value of m_crm_target.firstname
     *
     * @mbggenerated Tue Oct 27 22:57:40 ICT 2015
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_target.firstname
     *
     * @param firstname the value for m_crm_target.firstname
     *
     * @mbggenerated Tue Oct 27 22:57:40 ICT 2015
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_target.lastname
     *
     * @return the value of m_crm_target.lastname
     *
     * @mbggenerated Tue Oct 27 22:57:40 ICT 2015
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_target.lastname
     *
     * @param lastname the value for m_crm_target.lastname
     *
     * @mbggenerated Tue Oct 27 22:57:40 ICT 2015
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_target.title
     *
     * @return the value of m_crm_target.title
     *
     * @mbggenerated Tue Oct 27 22:57:40 ICT 2015
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_target.title
     *
     * @param title the value for m_crm_target.title
     *
     * @mbggenerated Tue Oct 27 22:57:40 ICT 2015
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_target.department
     *
     * @return the value of m_crm_target.department
     *
     * @mbggenerated Tue Oct 27 22:57:40 ICT 2015
     */
    public String getDepartment() {
        return department;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_target.department
     *
     * @param department the value for m_crm_target.department
     *
     * @mbggenerated Tue Oct 27 22:57:40 ICT 2015
     */
    public void setDepartment(String department) {
        this.department = department;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_target.birthday
     *
     * @return the value of m_crm_target.birthday
     *
     * @mbggenerated Tue Oct 27 22:57:40 ICT 2015
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_target.birthday
     *
     * @param birthday the value for m_crm_target.birthday
     *
     * @mbggenerated Tue Oct 27 22:57:40 ICT 2015
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_target.accountname
     *
     * @return the value of m_crm_target.accountname
     *
     * @mbggenerated Tue Oct 27 22:57:40 ICT 2015
     */
    public String getAccountname() {
        return accountname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_target.accountname
     *
     * @param accountname the value for m_crm_target.accountname
     *
     * @mbggenerated Tue Oct 27 22:57:40 ICT 2015
     */
    public void setAccountname(String accountname) {
        this.accountname = accountname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_target.isCallable
     *
     * @return the value of m_crm_target.isCallable
     *
     * @mbggenerated Tue Oct 27 22:57:40 ICT 2015
     */
    public Boolean getIscallable() {
        return iscallable;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_target.isCallable
     *
     * @param iscallable the value for m_crm_target.isCallable
     *
     * @mbggenerated Tue Oct 27 22:57:40 ICT 2015
     */
    public void setIscallable(Boolean iscallable) {
        this.iscallable = iscallable;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_target.officePhone
     *
     * @return the value of m_crm_target.officePhone
     *
     * @mbggenerated Tue Oct 27 22:57:40 ICT 2015
     */
    public String getOfficephone() {
        return officephone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_target.officePhone
     *
     * @param officephone the value for m_crm_target.officePhone
     *
     * @mbggenerated Tue Oct 27 22:57:40 ICT 2015
     */
    public void setOfficephone(String officephone) {
        this.officephone = officephone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_target.mobile
     *
     * @return the value of m_crm_target.mobile
     *
     * @mbggenerated Tue Oct 27 22:57:40 ICT 2015
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_target.mobile
     *
     * @param mobile the value for m_crm_target.mobile
     *
     * @mbggenerated Tue Oct 27 22:57:40 ICT 2015
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_target.homePhone
     *
     * @return the value of m_crm_target.homePhone
     *
     * @mbggenerated Tue Oct 27 22:57:40 ICT 2015
     */
    public String getHomephone() {
        return homephone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_target.homePhone
     *
     * @param homephone the value for m_crm_target.homePhone
     *
     * @mbggenerated Tue Oct 27 22:57:40 ICT 2015
     */
    public void setHomephone(String homephone) {
        this.homephone = homephone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_target.otherPhone
     *
     * @return the value of m_crm_target.otherPhone
     *
     * @mbggenerated Tue Oct 27 22:57:40 ICT 2015
     */
    public String getOtherphone() {
        return otherphone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_target.otherPhone
     *
     * @param otherphone the value for m_crm_target.otherPhone
     *
     * @mbggenerated Tue Oct 27 22:57:40 ICT 2015
     */
    public void setOtherphone(String otherphone) {
        this.otherphone = otherphone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_target.fax
     *
     * @return the value of m_crm_target.fax
     *
     * @mbggenerated Tue Oct 27 22:57:40 ICT 2015
     */
    public String getFax() {
        return fax;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_target.fax
     *
     * @param fax the value for m_crm_target.fax
     *
     * @mbggenerated Tue Oct 27 22:57:40 ICT 2015
     */
    public void setFax(String fax) {
        this.fax = fax;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_target.assistant
     *
     * @return the value of m_crm_target.assistant
     *
     * @mbggenerated Tue Oct 27 22:57:40 ICT 2015
     */
    public String getAssistant() {
        return assistant;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_target.assistant
     *
     * @param assistant the value for m_crm_target.assistant
     *
     * @mbggenerated Tue Oct 27 22:57:40 ICT 2015
     */
    public void setAssistant(String assistant) {
        this.assistant = assistant;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_target.assistantPhone
     *
     * @return the value of m_crm_target.assistantPhone
     *
     * @mbggenerated Tue Oct 27 22:57:40 ICT 2015
     */
    public String getAssistantphone() {
        return assistantphone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_target.assistantPhone
     *
     * @param assistantphone the value for m_crm_target.assistantPhone
     *
     * @mbggenerated Tue Oct 27 22:57:40 ICT 2015
     */
    public void setAssistantphone(String assistantphone) {
        this.assistantphone = assistantphone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_target.primaryAddress
     *
     * @return the value of m_crm_target.primaryAddress
     *
     * @mbggenerated Tue Oct 27 22:57:40 ICT 2015
     */
    public String getPrimaryaddress() {
        return primaryaddress;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_target.primaryAddress
     *
     * @param primaryaddress the value for m_crm_target.primaryAddress
     *
     * @mbggenerated Tue Oct 27 22:57:40 ICT 2015
     */
    public void setPrimaryaddress(String primaryaddress) {
        this.primaryaddress = primaryaddress;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_target.primaryCity
     *
     * @return the value of m_crm_target.primaryCity
     *
     * @mbggenerated Tue Oct 27 22:57:40 ICT 2015
     */
    public String getPrimarycity() {
        return primarycity;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_target.primaryCity
     *
     * @param primarycity the value for m_crm_target.primaryCity
     *
     * @mbggenerated Tue Oct 27 22:57:40 ICT 2015
     */
    public void setPrimarycity(String primarycity) {
        this.primarycity = primarycity;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_target.primaryState
     *
     * @return the value of m_crm_target.primaryState
     *
     * @mbggenerated Tue Oct 27 22:57:40 ICT 2015
     */
    public String getPrimarystate() {
        return primarystate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_target.primaryState
     *
     * @param primarystate the value for m_crm_target.primaryState
     *
     * @mbggenerated Tue Oct 27 22:57:40 ICT 2015
     */
    public void setPrimarystate(String primarystate) {
        this.primarystate = primarystate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_target.primaryPostal
     *
     * @return the value of m_crm_target.primaryPostal
     *
     * @mbggenerated Tue Oct 27 22:57:40 ICT 2015
     */
    public String getPrimarypostal() {
        return primarypostal;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_target.primaryPostal
     *
     * @param primarypostal the value for m_crm_target.primaryPostal
     *
     * @mbggenerated Tue Oct 27 22:57:40 ICT 2015
     */
    public void setPrimarypostal(String primarypostal) {
        this.primarypostal = primarypostal;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_target.primaryCountryId
     *
     * @return the value of m_crm_target.primaryCountryId
     *
     * @mbggenerated Tue Oct 27 22:57:40 ICT 2015
     */
    public Integer getPrimarycountryid() {
        return primarycountryid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_target.primaryCountryId
     *
     * @param primarycountryid the value for m_crm_target.primaryCountryId
     *
     * @mbggenerated Tue Oct 27 22:57:40 ICT 2015
     */
    public void setPrimarycountryid(Integer primarycountryid) {
        this.primarycountryid = primarycountryid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_target.description
     *
     * @return the value of m_crm_target.description
     *
     * @mbggenerated Tue Oct 27 22:57:40 ICT 2015
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_target.description
     *
     * @param description the value for m_crm_target.description
     *
     * @mbggenerated Tue Oct 27 22:57:40 ICT 2015
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_target.email
     *
     * @return the value of m_crm_target.email
     *
     * @mbggenerated Tue Oct 27 22:57:40 ICT 2015
     */
    public String getEmail() {
        return email;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_target.email
     *
     * @param email the value for m_crm_target.email
     *
     * @mbggenerated Tue Oct 27 22:57:40 ICT 2015
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_target.createdTime
     *
     * @return the value of m_crm_target.createdTime
     *
     * @mbggenerated Tue Oct 27 22:57:40 ICT 2015
     */
    public Date getCreatedtime() {
        return createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_target.createdTime
     *
     * @param createdtime the value for m_crm_target.createdTime
     *
     * @mbggenerated Tue Oct 27 22:57:40 ICT 2015
     */
    public void setCreatedtime(Date createdtime) {
        this.createdtime = createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_target.createdUser
     *
     * @return the value of m_crm_target.createdUser
     *
     * @mbggenerated Tue Oct 27 22:57:40 ICT 2015
     */
    public String getCreateduser() {
        return createduser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_target.createdUser
     *
     * @param createduser the value for m_crm_target.createdUser
     *
     * @mbggenerated Tue Oct 27 22:57:40 ICT 2015
     */
    public void setCreateduser(String createduser) {
        this.createduser = createduser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_target.sAccountId
     *
     * @return the value of m_crm_target.sAccountId
     *
     * @mbggenerated Tue Oct 27 22:57:40 ICT 2015
     */
    public Integer getSaccountid() {
        return saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_target.sAccountId
     *
     * @param saccountid the value for m_crm_target.sAccountId
     *
     * @mbggenerated Tue Oct 27 22:57:40 ICT 2015
     */
    public void setSaccountid(Integer saccountid) {
        this.saccountid = saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_target.assignUser
     *
     * @return the value of m_crm_target.assignUser
     *
     * @mbggenerated Tue Oct 27 22:57:40 ICT 2015
     */
    public String getAssignuser() {
        return assignuser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_target.assignUser
     *
     * @param assignuser the value for m_crm_target.assignUser
     *
     * @mbggenerated Tue Oct 27 22:57:40 ICT 2015
     */
    public void setAssignuser(String assignuser) {
        this.assignuser = assignuser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_target.lastUpdatedTime
     *
     * @return the value of m_crm_target.lastUpdatedTime
     *
     * @mbggenerated Tue Oct 27 22:57:40 ICT 2015
     */
    public Date getLastupdatedtime() {
        return lastupdatedtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_target.lastUpdatedTime
     *
     * @param lastupdatedtime the value for m_crm_target.lastUpdatedTime
     *
     * @mbggenerated Tue Oct 27 22:57:40 ICT 2015
     */
    public void setLastupdatedtime(Date lastupdatedtime) {
        this.lastupdatedtime = lastupdatedtime;
    }

    public enum Field {
        id,
        prefixname,
        firstname,
        lastname,
        title,
        department,
        birthday,
        accountname,
        iscallable,
        officephone,
        mobile,
        homephone,
        otherphone,
        fax,
        assistant,
        assistantphone,
        primaryaddress,
        primarycity,
        primarystate,
        primarypostal,
        primarycountryid,
        description,
        email,
        createdtime,
        createduser,
        saccountid,
        assignuser,
        lastupdatedtime;

        public boolean equalTo(Object value) {
            return name().equals(value);
        }
    }
}