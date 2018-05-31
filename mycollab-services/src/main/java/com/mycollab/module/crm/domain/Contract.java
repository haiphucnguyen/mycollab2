/*Domain class of table m_crm_contract*/
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
@Table("m_crm_contract")
@Alias("Contract")
public class Contract extends ValuedBean {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contract.id
     *
     * @mbg.generated Thu May 31 21:45:17 ICT 2018
     */
    @Column("id")
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contract.contractname
     *
     * @mbg.generated Thu May 31 21:45:17 ICT 2018
     */
    @Length(max=255, message="Field value is too long")
    @Column("contractname")
    private String contractname;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contract.status
     *
     * @mbg.generated Thu May 31 21:45:17 ICT 2018
     */
    @Length(max=45, message="Field value is too long")
    @Column("status")
    private String status;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contract.code
     *
     * @mbg.generated Thu May 31 21:45:17 ICT 2018
     */
    @Length(max=45, message="Field value is too long")
    @Column("code")
    private String code;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contract.accountid
     *
     * @mbg.generated Thu May 31 21:45:17 ICT 2018
     */
    @Column("accountid")
    private Integer accountid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contract.opportunityid
     *
     * @mbg.generated Thu May 31 21:45:17 ICT 2018
     */
    @Column("opportunityid")
    private Integer opportunityid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contract.currencyid
     *
     * @mbg.generated Thu May 31 21:45:17 ICT 2018
     */
    @Length(max=4, message="Field value is too long")
    @Column("currencyid")
    private String currencyid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contract.customersigneddate
     *
     * @mbg.generated Thu May 31 21:45:17 ICT 2018
     */
    @Column("customersigneddate")
    private Date customersigneddate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contract.companysigneddate
     *
     * @mbg.generated Thu May 31 21:45:17 ICT 2018
     */
    @Column("companysigneddate")
    private Date companysigneddate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contract.type
     *
     * @mbg.generated Thu May 31 21:45:17 ICT 2018
     */
    @Length(max=45, message="Field value is too long")
    @Column("type")
    private String type;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contract.description
     *
     * @mbg.generated Thu May 31 21:45:17 ICT 2018
     */
    @Length(max=4000, message="Field value is too long")
    @Column("description")
    private String description;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contract.startdate
     *
     * @mbg.generated Thu May 31 21:45:17 ICT 2018
     */
    @Column("startdate")
    private Date startdate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contract.enddate
     *
     * @mbg.generated Thu May 31 21:45:17 ICT 2018
     */
    @Column("enddate")
    private Date enddate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contract.contractvalue
     *
     * @mbg.generated Thu May 31 21:45:17 ICT 2018
     */
    @Column("contractvalue")
    private Long contractvalue;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contract.createdTime
     *
     * @mbg.generated Thu May 31 21:45:17 ICT 2018
     */
    @Column("createdTime")
    private Date createdtime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contract.createdUser
     *
     * @mbg.generated Thu May 31 21:45:17 ICT 2018
     */
    @Length(max=45, message="Field value is too long")
    @Column("createdUser")
    private String createduser;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contract.sAccountId
     *
     * @mbg.generated Thu May 31 21:45:17 ICT 2018
     */
    @Column("sAccountId")
    private Integer saccountid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contract.assignUser
     *
     * @mbg.generated Thu May 31 21:45:17 ICT 2018
     */
    @Length(max=45, message="Field value is too long")
    @Column("assignUser")
    private String assignuser;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_contract.lastUpdatedTime
     *
     * @mbg.generated Thu May 31 21:45:17 ICT 2018
     */
    @Column("lastUpdatedTime")
    private Date lastupdatedtime;

    private static final long serialVersionUID = 1;

    public final boolean equals(Object obj) {
        if (obj == null) { return false;}
        if (obj == this) { return true;}
        if (!obj.getClass().isAssignableFrom(getClass())) { return false;}
        Contract item = (Contract)obj;
        return new EqualsBuilder().append(id, item.id).build();
    }

    public final int hashCode() {
        return new HashCodeBuilder(793, 1981).append(id).build();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_contract.id
     *
     * @return the value of m_crm_contract.id
     *
     * @mbg.generated Thu May 31 21:45:17 ICT 2018
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_contract.id
     *
     * @param id the value for m_crm_contract.id
     *
     * @mbg.generated Thu May 31 21:45:17 ICT 2018
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_contract.contractname
     *
     * @return the value of m_crm_contract.contractname
     *
     * @mbg.generated Thu May 31 21:45:17 ICT 2018
     */
    public String getContractname() {
        return contractname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_contract.contractname
     *
     * @param contractname the value for m_crm_contract.contractname
     *
     * @mbg.generated Thu May 31 21:45:17 ICT 2018
     */
    public void setContractname(String contractname) {
        this.contractname = contractname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_contract.status
     *
     * @return the value of m_crm_contract.status
     *
     * @mbg.generated Thu May 31 21:45:17 ICT 2018
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_contract.status
     *
     * @param status the value for m_crm_contract.status
     *
     * @mbg.generated Thu May 31 21:45:17 ICT 2018
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_contract.code
     *
     * @return the value of m_crm_contract.code
     *
     * @mbg.generated Thu May 31 21:45:17 ICT 2018
     */
    public String getCode() {
        return code;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_contract.code
     *
     * @param code the value for m_crm_contract.code
     *
     * @mbg.generated Thu May 31 21:45:17 ICT 2018
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_contract.accountid
     *
     * @return the value of m_crm_contract.accountid
     *
     * @mbg.generated Thu May 31 21:45:17 ICT 2018
     */
    public Integer getAccountid() {
        return accountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_contract.accountid
     *
     * @param accountid the value for m_crm_contract.accountid
     *
     * @mbg.generated Thu May 31 21:45:17 ICT 2018
     */
    public void setAccountid(Integer accountid) {
        this.accountid = accountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_contract.opportunityid
     *
     * @return the value of m_crm_contract.opportunityid
     *
     * @mbg.generated Thu May 31 21:45:17 ICT 2018
     */
    public Integer getOpportunityid() {
        return opportunityid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_contract.opportunityid
     *
     * @param opportunityid the value for m_crm_contract.opportunityid
     *
     * @mbg.generated Thu May 31 21:45:17 ICT 2018
     */
    public void setOpportunityid(Integer opportunityid) {
        this.opportunityid = opportunityid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_contract.currencyid
     *
     * @return the value of m_crm_contract.currencyid
     *
     * @mbg.generated Thu May 31 21:45:17 ICT 2018
     */
    public String getCurrencyid() {
        return currencyid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_contract.currencyid
     *
     * @param currencyid the value for m_crm_contract.currencyid
     *
     * @mbg.generated Thu May 31 21:45:17 ICT 2018
     */
    public void setCurrencyid(String currencyid) {
        this.currencyid = currencyid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_contract.customersigneddate
     *
     * @return the value of m_crm_contract.customersigneddate
     *
     * @mbg.generated Thu May 31 21:45:17 ICT 2018
     */
    public Date getCustomersigneddate() {
        return customersigneddate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_contract.customersigneddate
     *
     * @param customersigneddate the value for m_crm_contract.customersigneddate
     *
     * @mbg.generated Thu May 31 21:45:17 ICT 2018
     */
    public void setCustomersigneddate(Date customersigneddate) {
        this.customersigneddate = customersigneddate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_contract.companysigneddate
     *
     * @return the value of m_crm_contract.companysigneddate
     *
     * @mbg.generated Thu May 31 21:45:17 ICT 2018
     */
    public Date getCompanysigneddate() {
        return companysigneddate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_contract.companysigneddate
     *
     * @param companysigneddate the value for m_crm_contract.companysigneddate
     *
     * @mbg.generated Thu May 31 21:45:17 ICT 2018
     */
    public void setCompanysigneddate(Date companysigneddate) {
        this.companysigneddate = companysigneddate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_contract.type
     *
     * @return the value of m_crm_contract.type
     *
     * @mbg.generated Thu May 31 21:45:17 ICT 2018
     */
    public String getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_contract.type
     *
     * @param type the value for m_crm_contract.type
     *
     * @mbg.generated Thu May 31 21:45:17 ICT 2018
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_contract.description
     *
     * @return the value of m_crm_contract.description
     *
     * @mbg.generated Thu May 31 21:45:17 ICT 2018
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_contract.description
     *
     * @param description the value for m_crm_contract.description
     *
     * @mbg.generated Thu May 31 21:45:17 ICT 2018
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_contract.startdate
     *
     * @return the value of m_crm_contract.startdate
     *
     * @mbg.generated Thu May 31 21:45:17 ICT 2018
     */
    public Date getStartdate() {
        return startdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_contract.startdate
     *
     * @param startdate the value for m_crm_contract.startdate
     *
     * @mbg.generated Thu May 31 21:45:17 ICT 2018
     */
    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_contract.enddate
     *
     * @return the value of m_crm_contract.enddate
     *
     * @mbg.generated Thu May 31 21:45:17 ICT 2018
     */
    public Date getEnddate() {
        return enddate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_contract.enddate
     *
     * @param enddate the value for m_crm_contract.enddate
     *
     * @mbg.generated Thu May 31 21:45:17 ICT 2018
     */
    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_contract.contractvalue
     *
     * @return the value of m_crm_contract.contractvalue
     *
     * @mbg.generated Thu May 31 21:45:17 ICT 2018
     */
    public Long getContractvalue() {
        return contractvalue;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_contract.contractvalue
     *
     * @param contractvalue the value for m_crm_contract.contractvalue
     *
     * @mbg.generated Thu May 31 21:45:17 ICT 2018
     */
    public void setContractvalue(Long contractvalue) {
        this.contractvalue = contractvalue;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_contract.createdTime
     *
     * @return the value of m_crm_contract.createdTime
     *
     * @mbg.generated Thu May 31 21:45:17 ICT 2018
     */
    public Date getCreatedtime() {
        return createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_contract.createdTime
     *
     * @param createdtime the value for m_crm_contract.createdTime
     *
     * @mbg.generated Thu May 31 21:45:17 ICT 2018
     */
    public void setCreatedtime(Date createdtime) {
        this.createdtime = createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_contract.createdUser
     *
     * @return the value of m_crm_contract.createdUser
     *
     * @mbg.generated Thu May 31 21:45:17 ICT 2018
     */
    public String getCreateduser() {
        return createduser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_contract.createdUser
     *
     * @param createduser the value for m_crm_contract.createdUser
     *
     * @mbg.generated Thu May 31 21:45:17 ICT 2018
     */
    public void setCreateduser(String createduser) {
        this.createduser = createduser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_contract.sAccountId
     *
     * @return the value of m_crm_contract.sAccountId
     *
     * @mbg.generated Thu May 31 21:45:17 ICT 2018
     */
    public Integer getSaccountid() {
        return saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_contract.sAccountId
     *
     * @param saccountid the value for m_crm_contract.sAccountId
     *
     * @mbg.generated Thu May 31 21:45:17 ICT 2018
     */
    public void setSaccountid(Integer saccountid) {
        this.saccountid = saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_contract.assignUser
     *
     * @return the value of m_crm_contract.assignUser
     *
     * @mbg.generated Thu May 31 21:45:17 ICT 2018
     */
    public String getAssignuser() {
        return assignuser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_contract.assignUser
     *
     * @param assignuser the value for m_crm_contract.assignUser
     *
     * @mbg.generated Thu May 31 21:45:17 ICT 2018
     */
    public void setAssignuser(String assignuser) {
        this.assignuser = assignuser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_contract.lastUpdatedTime
     *
     * @return the value of m_crm_contract.lastUpdatedTime
     *
     * @mbg.generated Thu May 31 21:45:17 ICT 2018
     */
    public Date getLastupdatedtime() {
        return lastupdatedtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_contract.lastUpdatedTime
     *
     * @param lastupdatedtime the value for m_crm_contract.lastUpdatedTime
     *
     * @mbg.generated Thu May 31 21:45:17 ICT 2018
     */
    public void setLastupdatedtime(Date lastupdatedtime) {
        this.lastupdatedtime = lastupdatedtime;
    }

    public enum Field {
        id,
        contractname,
        status,
        code,
        accountid,
        opportunityid,
        currencyid,
        customersigneddate,
        companysigneddate,
        type,
        description,
        startdate,
        enddate,
        contractvalue,
        createdtime,
        createduser,
        saccountid,
        assignuser,
        lastupdatedtime,
        id,
        contractname,
        status,
        code,
        accountid,
        opportunityid,
        currencyid,
        customersigneddate,
        companysigneddate,
        type,
        description,
        startdate,
        enddate,
        contractvalue,
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