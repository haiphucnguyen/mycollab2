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
/*Domain class of table m_crm_contact*/
package com.esofthead.mycollab.module.crm.domain;

import com.esofthead.mycollab.core.arguments.ValuedBean;
import com.esofthead.mycollab.core.db.metadata.Column;
import com.esofthead.mycollab.core.db.metadata.Table;
import java.util.Date;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.Length;

@SuppressWarnings("ucd")
@Table("m_crm_contact")
public class Contact extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contact.id
     *
     * @mbggenerated Thu Sep 17 14:19:52 ICT 2015
     */
    @Column("id")
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contact.prefix
     *
     * @mbggenerated Thu Sep 17 14:19:52 ICT 2015
     */
    @Length(max=45, message="Field value is too long")
    @Column("prefix")
    private String prefix;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contact.firstname
     *
     * @mbggenerated Thu Sep 17 14:19:52 ICT 2015
     */
    @Length(max=45, message="Field value is too long")
    @Column("firstname")
    private String firstname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contact.lastname
     *
     * @mbggenerated Thu Sep 17 14:19:52 ICT 2015
     */
    @Length(max=45, message="Field value is too long")
    @Column("lastname")
    private String lastname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contact.leadSource
     *
     * @mbggenerated Thu Sep 17 14:19:52 ICT 2015
     */
    @Length(max=45, message="Field value is too long")
    @Column("leadSource")
    private String leadsource;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contact.campaignId
     *
     * @mbggenerated Thu Sep 17 14:19:52 ICT 2015
     */
    @Column("campaignId")
    private Integer campaignid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contact.isCallable
     *
     * @mbggenerated Thu Sep 17 14:19:52 ICT 2015
     */
    @Column("isCallable")
    private Boolean iscallable;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contact.officePhone
     *
     * @mbggenerated Thu Sep 17 14:19:52 ICT 2015
     */
    @Length(max=45, message="Field value is too long")
    @Column("officePhone")
    private String officephone;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contact.mobile
     *
     * @mbggenerated Thu Sep 17 14:19:52 ICT 2015
     */
    @Length(max=45, message="Field value is too long")
    @Column("mobile")
    private String mobile;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contact.homePhone
     *
     * @mbggenerated Thu Sep 17 14:19:52 ICT 2015
     */
    @Length(max=45, message="Field value is too long")
    @Column("homePhone")
    private String homephone;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contact.otherPhone
     *
     * @mbggenerated Thu Sep 17 14:19:52 ICT 2015
     */
    @Length(max=45, message="Field value is too long")
    @Column("otherPhone")
    private String otherphone;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contact.fax
     *
     * @mbggenerated Thu Sep 17 14:19:52 ICT 2015
     */
    @Length(max=45, message="Field value is too long")
    @Column("fax")
    private String fax;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contact.birthday
     *
     * @mbggenerated Thu Sep 17 14:19:52 ICT 2015
     */
    @Column("birthday")
    private Date birthday;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contact.assistant
     *
     * @mbggenerated Thu Sep 17 14:19:52 ICT 2015
     */
    @Length(max=100, message="Field value is too long")
    @Column("assistant")
    private String assistant;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contact.primAddress
     *
     * @mbggenerated Thu Sep 17 14:19:52 ICT 2015
     */
    @Length(max=255, message="Field value is too long")
    @Column("primAddress")
    private String primaddress;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contact.primCity
     *
     * @mbggenerated Thu Sep 17 14:19:52 ICT 2015
     */
    @Length(max=255, message="Field value is too long")
    @Column("primCity")
    private String primcity;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contact.primState
     *
     * @mbggenerated Thu Sep 17 14:19:52 ICT 2015
     */
    @Length(max=255, message="Field value is too long")
    @Column("primState")
    private String primstate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contact.primPostalCode
     *
     * @mbggenerated Thu Sep 17 14:19:52 ICT 2015
     */
    @Length(max=45, message="Field value is too long")
    @Column("primPostalCode")
    private String primpostalcode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contact.primCountry
     *
     * @mbggenerated Thu Sep 17 14:19:52 ICT 2015
     */
    @Length(max=45, message="Field value is too long")
    @Column("primCountry")
    private String primcountry;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contact.title
     *
     * @mbggenerated Thu Sep 17 14:19:52 ICT 2015
     */
    @Length(max=100, message="Field value is too long")
    @Column("title")
    private String title;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contact.assistantPhone
     *
     * @mbggenerated Thu Sep 17 14:19:52 ICT 2015
     */
    @Length(max=45, message="Field value is too long")
    @Column("assistantPhone")
    private String assistantphone;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contact.email
     *
     * @mbggenerated Thu Sep 17 14:19:52 ICT 2015
     */
    @Length(max=255, message="Field value is too long")
    @Column("email")
    private String email;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contact.department
     *
     * @mbggenerated Thu Sep 17 14:19:52 ICT 2015
     */
    @Length(max=255, message="Field value is too long")
    @Column("department")
    private String department;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contact.createdTime
     *
     * @mbggenerated Thu Sep 17 14:19:52 ICT 2015
     */
    @Column("createdTime")
    private Date createdtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contact.createdUser
     *
     * @mbggenerated Thu Sep 17 14:19:52 ICT 2015
     */
    @Length(max=45, message="Field value is too long")
    @Column("createdUser")
    private String createduser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contact.sAccountId
     *
     * @mbggenerated Thu Sep 17 14:19:52 ICT 2015
     */
    @Column("sAccountId")
    private Integer saccountid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contact.assignUser
     *
     * @mbggenerated Thu Sep 17 14:19:52 ICT 2015
     */
    @Length(max=45, message="Field value is too long")
    @Column("assignUser")
    private String assignuser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contact.otherAddress
     *
     * @mbggenerated Thu Sep 17 14:19:52 ICT 2015
     */
    @Length(max=255, message="Field value is too long")
    @Column("otherAddress")
    private String otheraddress;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contact.otherCity
     *
     * @mbggenerated Thu Sep 17 14:19:52 ICT 2015
     */
    @Length(max=255, message="Field value is too long")
    @Column("otherCity")
    private String othercity;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contact.otherState
     *
     * @mbggenerated Thu Sep 17 14:19:52 ICT 2015
     */
    @Length(max=255, message="Field value is too long")
    @Column("otherState")
    private String otherstate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contact.otherPostalCode
     *
     * @mbggenerated Thu Sep 17 14:19:52 ICT 2015
     */
    @Length(max=45, message="Field value is too long")
    @Column("otherPostalCode")
    private String otherpostalcode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contact.otherCountry
     *
     * @mbggenerated Thu Sep 17 14:19:52 ICT 2015
     */
    @Length(max=45, message="Field value is too long")
    @Column("otherCountry")
    private String othercountry;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contact.lastUpdatedTime
     *
     * @mbggenerated Thu Sep 17 14:19:52 ICT 2015
     */
    @Column("lastUpdatedTime")
    private Date lastupdatedtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contact.accountId
     *
     * @mbggenerated Thu Sep 17 14:19:52 ICT 2015
     */
    @Column("accountId")
    private Integer accountid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contact.avatarId
     *
     * @mbggenerated Thu Sep 17 14:19:52 ICT 2015
     */
    @Length(max=100, message="Field value is too long")
    @Column("avatarId")
    private String avatarid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contact.description
     *
     * @mbggenerated Thu Sep 17 14:19:52 ICT 2015
     */
    @Length(max=65535, message="Field value is too long")
    @Column("description")
    private String description;

    private static final long serialVersionUID = 1;

    public final boolean equals(Object obj) {
        if (obj == null) { return false;}
        if (obj == this) { return true;}
        if (obj.getClass() != getClass()) { return false;}
        Contact item = (Contact)obj;
        return new EqualsBuilder().append(id, item.id).build();
    }

    public final int hashCode() {
        return new HashCodeBuilder(465, 217).append(id).build();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_contact.id
     *
     * @return the value of m_crm_contact.id
     *
     * @mbggenerated Thu Sep 17 14:19:52 ICT 2015
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_contact.id
     *
     * @param id the value for m_crm_contact.id
     *
     * @mbggenerated Thu Sep 17 14:19:52 ICT 2015
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_contact.prefix
     *
     * @return the value of m_crm_contact.prefix
     *
     * @mbggenerated Thu Sep 17 14:19:52 ICT 2015
     */
    public String getPrefix() {
        return prefix;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_contact.prefix
     *
     * @param prefix the value for m_crm_contact.prefix
     *
     * @mbggenerated Thu Sep 17 14:19:52 ICT 2015
     */
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_contact.firstname
     *
     * @return the value of m_crm_contact.firstname
     *
     * @mbggenerated Thu Sep 17 14:19:52 ICT 2015
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_contact.firstname
     *
     * @param firstname the value for m_crm_contact.firstname
     *
     * @mbggenerated Thu Sep 17 14:19:52 ICT 2015
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_contact.lastname
     *
     * @return the value of m_crm_contact.lastname
     *
     * @mbggenerated Thu Sep 17 14:19:52 ICT 2015
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_contact.lastname
     *
     * @param lastname the value for m_crm_contact.lastname
     *
     * @mbggenerated Thu Sep 17 14:19:52 ICT 2015
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_contact.leadSource
     *
     * @return the value of m_crm_contact.leadSource
     *
     * @mbggenerated Thu Sep 17 14:19:52 ICT 2015
     */
    public String getLeadsource() {
        return leadsource;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_contact.leadSource
     *
     * @param leadsource the value for m_crm_contact.leadSource
     *
     * @mbggenerated Thu Sep 17 14:19:52 ICT 2015
     */
    public void setLeadsource(String leadsource) {
        this.leadsource = leadsource;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_contact.campaignId
     *
     * @return the value of m_crm_contact.campaignId
     *
     * @mbggenerated Thu Sep 17 14:19:52 ICT 2015
     */
    public Integer getCampaignid() {
        return campaignid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_contact.campaignId
     *
     * @param campaignid the value for m_crm_contact.campaignId
     *
     * @mbggenerated Thu Sep 17 14:19:52 ICT 2015
     */
    public void setCampaignid(Integer campaignid) {
        this.campaignid = campaignid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_contact.isCallable
     *
     * @return the value of m_crm_contact.isCallable
     *
     * @mbggenerated Thu Sep 17 14:19:52 ICT 2015
     */
    public Boolean getIscallable() {
        return iscallable;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_contact.isCallable
     *
     * @param iscallable the value for m_crm_contact.isCallable
     *
     * @mbggenerated Thu Sep 17 14:19:52 ICT 2015
     */
    public void setIscallable(Boolean iscallable) {
        this.iscallable = iscallable;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_contact.officePhone
     *
     * @return the value of m_crm_contact.officePhone
     *
     * @mbggenerated Thu Sep 17 14:19:52 ICT 2015
     */
    public String getOfficephone() {
        return officephone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_contact.officePhone
     *
     * @param officephone the value for m_crm_contact.officePhone
     *
     * @mbggenerated Thu Sep 17 14:19:52 ICT 2015
     */
    public void setOfficephone(String officephone) {
        this.officephone = officephone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_contact.mobile
     *
     * @return the value of m_crm_contact.mobile
     *
     * @mbggenerated Thu Sep 17 14:19:52 ICT 2015
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_contact.mobile
     *
     * @param mobile the value for m_crm_contact.mobile
     *
     * @mbggenerated Thu Sep 17 14:19:52 ICT 2015
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_contact.homePhone
     *
     * @return the value of m_crm_contact.homePhone
     *
     * @mbggenerated Thu Sep 17 14:19:52 ICT 2015
     */
    public String getHomephone() {
        return homephone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_contact.homePhone
     *
     * @param homephone the value for m_crm_contact.homePhone
     *
     * @mbggenerated Thu Sep 17 14:19:52 ICT 2015
     */
    public void setHomephone(String homephone) {
        this.homephone = homephone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_contact.otherPhone
     *
     * @return the value of m_crm_contact.otherPhone
     *
     * @mbggenerated Thu Sep 17 14:19:52 ICT 2015
     */
    public String getOtherphone() {
        return otherphone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_contact.otherPhone
     *
     * @param otherphone the value for m_crm_contact.otherPhone
     *
     * @mbggenerated Thu Sep 17 14:19:52 ICT 2015
     */
    public void setOtherphone(String otherphone) {
        this.otherphone = otherphone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_contact.fax
     *
     * @return the value of m_crm_contact.fax
     *
     * @mbggenerated Thu Sep 17 14:19:52 ICT 2015
     */
    public String getFax() {
        return fax;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_contact.fax
     *
     * @param fax the value for m_crm_contact.fax
     *
     * @mbggenerated Thu Sep 17 14:19:52 ICT 2015
     */
    public void setFax(String fax) {
        this.fax = fax;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_contact.birthday
     *
     * @return the value of m_crm_contact.birthday
     *
     * @mbggenerated Thu Sep 17 14:19:52 ICT 2015
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_contact.birthday
     *
     * @param birthday the value for m_crm_contact.birthday
     *
     * @mbggenerated Thu Sep 17 14:19:52 ICT 2015
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_contact.assistant
     *
     * @return the value of m_crm_contact.assistant
     *
     * @mbggenerated Thu Sep 17 14:19:52 ICT 2015
     */
    public String getAssistant() {
        return assistant;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_contact.assistant
     *
     * @param assistant the value for m_crm_contact.assistant
     *
     * @mbggenerated Thu Sep 17 14:19:52 ICT 2015
     */
    public void setAssistant(String assistant) {
        this.assistant = assistant;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_contact.primAddress
     *
     * @return the value of m_crm_contact.primAddress
     *
     * @mbggenerated Thu Sep 17 14:19:52 ICT 2015
     */
    public String getPrimaddress() {
        return primaddress;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_contact.primAddress
     *
     * @param primaddress the value for m_crm_contact.primAddress
     *
     * @mbggenerated Thu Sep 17 14:19:52 ICT 2015
     */
    public void setPrimaddress(String primaddress) {
        this.primaddress = primaddress;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_contact.primCity
     *
     * @return the value of m_crm_contact.primCity
     *
     * @mbggenerated Thu Sep 17 14:19:52 ICT 2015
     */
    public String getPrimcity() {
        return primcity;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_contact.primCity
     *
     * @param primcity the value for m_crm_contact.primCity
     *
     * @mbggenerated Thu Sep 17 14:19:52 ICT 2015
     */
    public void setPrimcity(String primcity) {
        this.primcity = primcity;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_contact.primState
     *
     * @return the value of m_crm_contact.primState
     *
     * @mbggenerated Thu Sep 17 14:19:52 ICT 2015
     */
    public String getPrimstate() {
        return primstate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_contact.primState
     *
     * @param primstate the value for m_crm_contact.primState
     *
     * @mbggenerated Thu Sep 17 14:19:52 ICT 2015
     */
    public void setPrimstate(String primstate) {
        this.primstate = primstate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_contact.primPostalCode
     *
     * @return the value of m_crm_contact.primPostalCode
     *
     * @mbggenerated Thu Sep 17 14:19:52 ICT 2015
     */
    public String getPrimpostalcode() {
        return primpostalcode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_contact.primPostalCode
     *
     * @param primpostalcode the value for m_crm_contact.primPostalCode
     *
     * @mbggenerated Thu Sep 17 14:19:52 ICT 2015
     */
    public void setPrimpostalcode(String primpostalcode) {
        this.primpostalcode = primpostalcode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_contact.primCountry
     *
     * @return the value of m_crm_contact.primCountry
     *
     * @mbggenerated Thu Sep 17 14:19:52 ICT 2015
     */
    public String getPrimcountry() {
        return primcountry;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_contact.primCountry
     *
     * @param primcountry the value for m_crm_contact.primCountry
     *
     * @mbggenerated Thu Sep 17 14:19:52 ICT 2015
     */
    public void setPrimcountry(String primcountry) {
        this.primcountry = primcountry;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_contact.title
     *
     * @return the value of m_crm_contact.title
     *
     * @mbggenerated Thu Sep 17 14:19:52 ICT 2015
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_contact.title
     *
     * @param title the value for m_crm_contact.title
     *
     * @mbggenerated Thu Sep 17 14:19:52 ICT 2015
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_contact.assistantPhone
     *
     * @return the value of m_crm_contact.assistantPhone
     *
     * @mbggenerated Thu Sep 17 14:19:52 ICT 2015
     */
    public String getAssistantphone() {
        return assistantphone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_contact.assistantPhone
     *
     * @param assistantphone the value for m_crm_contact.assistantPhone
     *
     * @mbggenerated Thu Sep 17 14:19:52 ICT 2015
     */
    public void setAssistantphone(String assistantphone) {
        this.assistantphone = assistantphone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_contact.email
     *
     * @return the value of m_crm_contact.email
     *
     * @mbggenerated Thu Sep 17 14:19:52 ICT 2015
     */
    public String getEmail() {
        return email;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_contact.email
     *
     * @param email the value for m_crm_contact.email
     *
     * @mbggenerated Thu Sep 17 14:19:52 ICT 2015
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_contact.department
     *
     * @return the value of m_crm_contact.department
     *
     * @mbggenerated Thu Sep 17 14:19:52 ICT 2015
     */
    public String getDepartment() {
        return department;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_contact.department
     *
     * @param department the value for m_crm_contact.department
     *
     * @mbggenerated Thu Sep 17 14:19:52 ICT 2015
     */
    public void setDepartment(String department) {
        this.department = department;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_contact.createdTime
     *
     * @return the value of m_crm_contact.createdTime
     *
     * @mbggenerated Thu Sep 17 14:19:52 ICT 2015
     */
    public Date getCreatedtime() {
        return createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_contact.createdTime
     *
     * @param createdtime the value for m_crm_contact.createdTime
     *
     * @mbggenerated Thu Sep 17 14:19:52 ICT 2015
     */
    public void setCreatedtime(Date createdtime) {
        this.createdtime = createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_contact.createdUser
     *
     * @return the value of m_crm_contact.createdUser
     *
     * @mbggenerated Thu Sep 17 14:19:52 ICT 2015
     */
    public String getCreateduser() {
        return createduser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_contact.createdUser
     *
     * @param createduser the value for m_crm_contact.createdUser
     *
     * @mbggenerated Thu Sep 17 14:19:52 ICT 2015
     */
    public void setCreateduser(String createduser) {
        this.createduser = createduser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_contact.sAccountId
     *
     * @return the value of m_crm_contact.sAccountId
     *
     * @mbggenerated Thu Sep 17 14:19:52 ICT 2015
     */
    public Integer getSaccountid() {
        return saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_contact.sAccountId
     *
     * @param saccountid the value for m_crm_contact.sAccountId
     *
     * @mbggenerated Thu Sep 17 14:19:52 ICT 2015
     */
    public void setSaccountid(Integer saccountid) {
        this.saccountid = saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_contact.assignUser
     *
     * @return the value of m_crm_contact.assignUser
     *
     * @mbggenerated Thu Sep 17 14:19:52 ICT 2015
     */
    public String getAssignuser() {
        return assignuser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_contact.assignUser
     *
     * @param assignuser the value for m_crm_contact.assignUser
     *
     * @mbggenerated Thu Sep 17 14:19:52 ICT 2015
     */
    public void setAssignuser(String assignuser) {
        this.assignuser = assignuser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_contact.otherAddress
     *
     * @return the value of m_crm_contact.otherAddress
     *
     * @mbggenerated Thu Sep 17 14:19:52 ICT 2015
     */
    public String getOtheraddress() {
        return otheraddress;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_contact.otherAddress
     *
     * @param otheraddress the value for m_crm_contact.otherAddress
     *
     * @mbggenerated Thu Sep 17 14:19:52 ICT 2015
     */
    public void setOtheraddress(String otheraddress) {
        this.otheraddress = otheraddress;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_contact.otherCity
     *
     * @return the value of m_crm_contact.otherCity
     *
     * @mbggenerated Thu Sep 17 14:19:52 ICT 2015
     */
    public String getOthercity() {
        return othercity;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_contact.otherCity
     *
     * @param othercity the value for m_crm_contact.otherCity
     *
     * @mbggenerated Thu Sep 17 14:19:52 ICT 2015
     */
    public void setOthercity(String othercity) {
        this.othercity = othercity;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_contact.otherState
     *
     * @return the value of m_crm_contact.otherState
     *
     * @mbggenerated Thu Sep 17 14:19:52 ICT 2015
     */
    public String getOtherstate() {
        return otherstate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_contact.otherState
     *
     * @param otherstate the value for m_crm_contact.otherState
     *
     * @mbggenerated Thu Sep 17 14:19:52 ICT 2015
     */
    public void setOtherstate(String otherstate) {
        this.otherstate = otherstate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_contact.otherPostalCode
     *
     * @return the value of m_crm_contact.otherPostalCode
     *
     * @mbggenerated Thu Sep 17 14:19:52 ICT 2015
     */
    public String getOtherpostalcode() {
        return otherpostalcode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_contact.otherPostalCode
     *
     * @param otherpostalcode the value for m_crm_contact.otherPostalCode
     *
     * @mbggenerated Thu Sep 17 14:19:52 ICT 2015
     */
    public void setOtherpostalcode(String otherpostalcode) {
        this.otherpostalcode = otherpostalcode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_contact.otherCountry
     *
     * @return the value of m_crm_contact.otherCountry
     *
     * @mbggenerated Thu Sep 17 14:19:52 ICT 2015
     */
    public String getOthercountry() {
        return othercountry;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_contact.otherCountry
     *
     * @param othercountry the value for m_crm_contact.otherCountry
     *
     * @mbggenerated Thu Sep 17 14:19:52 ICT 2015
     */
    public void setOthercountry(String othercountry) {
        this.othercountry = othercountry;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_contact.lastUpdatedTime
     *
     * @return the value of m_crm_contact.lastUpdatedTime
     *
     * @mbggenerated Thu Sep 17 14:19:52 ICT 2015
     */
    public Date getLastupdatedtime() {
        return lastupdatedtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_contact.lastUpdatedTime
     *
     * @param lastupdatedtime the value for m_crm_contact.lastUpdatedTime
     *
     * @mbggenerated Thu Sep 17 14:19:52 ICT 2015
     */
    public void setLastupdatedtime(Date lastupdatedtime) {
        this.lastupdatedtime = lastupdatedtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_contact.accountId
     *
     * @return the value of m_crm_contact.accountId
     *
     * @mbggenerated Thu Sep 17 14:19:52 ICT 2015
     */
    public Integer getAccountid() {
        return accountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_contact.accountId
     *
     * @param accountid the value for m_crm_contact.accountId
     *
     * @mbggenerated Thu Sep 17 14:19:52 ICT 2015
     */
    public void setAccountid(Integer accountid) {
        this.accountid = accountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_contact.avatarId
     *
     * @return the value of m_crm_contact.avatarId
     *
     * @mbggenerated Thu Sep 17 14:19:52 ICT 2015
     */
    public String getAvatarid() {
        return avatarid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_contact.avatarId
     *
     * @param avatarid the value for m_crm_contact.avatarId
     *
     * @mbggenerated Thu Sep 17 14:19:52 ICT 2015
     */
    public void setAvatarid(String avatarid) {
        this.avatarid = avatarid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_contact.description
     *
     * @return the value of m_crm_contact.description
     *
     * @mbggenerated Thu Sep 17 14:19:52 ICT 2015
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_contact.description
     *
     * @param description the value for m_crm_contact.description
     *
     * @mbggenerated Thu Sep 17 14:19:52 ICT 2015
     */
    public void setDescription(String description) {
        this.description = description;
    }

    public enum Field {
        id,
        prefix,
        firstname,
        lastname,
        leadsource,
        campaignid,
        iscallable,
        officephone,
        mobile,
        homephone,
        otherphone,
        fax,
        birthday,
        assistant,
        primaddress,
        primcity,
        primstate,
        primpostalcode,
        primcountry,
        title,
        assistantphone,
        email,
        department,
        createdtime,
        createduser,
        saccountid,
        assignuser,
        otheraddress,
        othercity,
        otherstate,
        otherpostalcode,
        othercountry,
        lastupdatedtime,
        accountid,
        avatarid,
        description;

        public boolean equalTo(Object value) {
            return name().equals(value);
        }
    }
}