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
/*Domain class of table m_crm_product*/
package com.esofthead.mycollab.module.crm.domain;

import com.esofthead.mycollab.core.arguments.ValuedBean;
import com.esofthead.mycollab.core.db.metadata.Column;
import com.esofthead.mycollab.core.db.metadata.Table;
import java.util.Date;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.Length;

@SuppressWarnings("ucd")
@Table("m_crm_product")
public class Product extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_product.id
     *
     * @mbggenerated Tue Jun 28 09:46:19 ICT 2016
     */
    @Column("id")
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_product.productname
     *
     * @mbggenerated Tue Jun 28 09:46:19 ICT 2016
     */
    @Length(max=255, message="Field value is too long")
    @Column("productname")
    private String productname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_product.status
     *
     * @mbggenerated Tue Jun 28 09:46:19 ICT 2016
     */
    @Length(max=45, message="Field value is too long")
    @Column("status")
    private String status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_product.accountid
     *
     * @mbggenerated Tue Jun 28 09:46:19 ICT 2016
     */
    @Column("accountid")
    private Integer accountid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_product.website
     *
     * @mbggenerated Tue Jun 28 09:46:19 ICT 2016
     */
    @Length(max=255, message="Field value is too long")
    @Column("website")
    private String website;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_product.quantity
     *
     * @mbggenerated Tue Jun 28 09:46:19 ICT 2016
     */
    @Column("quantity")
    private Integer quantity;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_product.serialnumber
     *
     * @mbggenerated Tue Jun 28 09:46:19 ICT 2016
     */
    @Length(max=45, message="Field value is too long")
    @Column("serialnumber")
    private String serialnumber;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_product.assessnumber
     *
     * @mbggenerated Tue Jun 28 09:46:19 ICT 2016
     */
    @Length(max=45, message="Field value is too long")
    @Column("assessnumber")
    private String assessnumber;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_product.contactid
     *
     * @mbggenerated Tue Jun 28 09:46:19 ICT 2016
     */
    @Column("contactid")
    private Integer contactid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_product.supportstartdate
     *
     * @mbggenerated Tue Jun 28 09:46:19 ICT 2016
     */
    @Column("supportstartdate")
    private Date supportstartdate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_product.supportenddate
     *
     * @mbggenerated Tue Jun 28 09:46:19 ICT 2016
     */
    @Column("supportenddate")
    private Date supportenddate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_product.salesdate
     *
     * @mbggenerated Tue Jun 28 09:46:19 ICT 2016
     */
    @Column("salesdate")
    private Date salesdate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_product.unitprice
     *
     * @mbggenerated Tue Jun 28 09:46:19 ICT 2016
     */
    @Column("unitprice")
    private Double unitprice;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_product.description
     *
     * @mbggenerated Tue Jun 28 09:46:19 ICT 2016
     */
    @Length(max=4000, message="Field value is too long")
    @Column("description")
    private String description;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_product.producturl
     *
     * @mbggenerated Tue Jun 28 09:46:19 ICT 2016
     */
    @Length(max=255, message="Field value is too long")
    @Column("producturl")
    private String producturl;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_product.supportcontact
     *
     * @mbggenerated Tue Jun 28 09:46:19 ICT 2016
     */
    @Length(max=255, message="Field value is too long")
    @Column("supportcontact")
    private String supportcontact;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_product.supportterm
     *
     * @mbggenerated Tue Jun 28 09:46:19 ICT 2016
     */
    @Length(max=45, message="Field value is too long")
    @Column("supportterm")
    private String supportterm;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_product.supportdesc
     *
     * @mbggenerated Tue Jun 28 09:46:19 ICT 2016
     */
    @Length(max=100, message="Field value is too long")
    @Column("supportdesc")
    private String supportdesc;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_product.cost
     *
     * @mbggenerated Tue Jun 28 09:46:19 ICT 2016
     */
    @Column("cost")
    private Double cost;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_product.listprice
     *
     * @mbggenerated Tue Jun 28 09:46:19 ICT 2016
     */
    @Column("listprice")
    private Double listprice;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_product.groupid
     *
     * @mbggenerated Tue Jun 28 09:46:19 ICT 2016
     */
    @Column("groupid")
    private Integer groupid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_product.tax
     *
     * @mbggenerated Tue Jun 28 09:46:19 ICT 2016
     */
    @Column("tax")
    private Double tax;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_product.taxClass
     *
     * @mbggenerated Tue Jun 28 09:46:19 ICT 2016
     */
    @Length(max=45, message="Field value is too long")
    @Column("taxClass")
    private String taxclass;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_product.mftNumber
     *
     * @mbggenerated Tue Jun 28 09:46:19 ICT 2016
     */
    @Length(max=45, message="Field value is too long")
    @Column("mftNumber")
    private String mftnumber;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_product.vendorPartNumber
     *
     * @mbggenerated Tue Jun 28 09:46:19 ICT 2016
     */
    @Length(max=45, message="Field value is too long")
    @Column("vendorPartNumber")
    private String vendorpartnumber;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_product.createdTime
     *
     * @mbggenerated Tue Jun 28 09:46:19 ICT 2016
     */
    @Column("createdTime")
    private Date createdtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_product.createdUser
     *
     * @mbggenerated Tue Jun 28 09:46:19 ICT 2016
     */
    @Length(max=45, message="Field value is too long")
    @Column("createdUser")
    private String createduser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_product.sAccountId
     *
     * @mbggenerated Tue Jun 28 09:46:19 ICT 2016
     */
    @Column("sAccountId")
    private Integer saccountid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_product.lastUpdatedTime
     *
     * @mbggenerated Tue Jun 28 09:46:19 ICT 2016
     */
    @Column("lastUpdatedTime")
    private Date lastupdatedtime;

    private static final long serialVersionUID = 1;

    public final boolean equals(Object obj) {
        if (obj == null) { return false;}
        if (obj == this) { return true;}
        if (!obj.getClass().isAssignableFrom(getClass())) { return false;}
        Product item = (Product)obj;
        return new EqualsBuilder().append(id, item.id).build();
    }

    public final int hashCode() {
        return new HashCodeBuilder(439, 987).append(id).build();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_product.id
     *
     * @return the value of m_crm_product.id
     *
     * @mbggenerated Tue Jun 28 09:46:19 ICT 2016
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_product.id
     *
     * @param id the value for m_crm_product.id
     *
     * @mbggenerated Tue Jun 28 09:46:19 ICT 2016
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_product.productname
     *
     * @return the value of m_crm_product.productname
     *
     * @mbggenerated Tue Jun 28 09:46:19 ICT 2016
     */
    public String getProductname() {
        return productname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_product.productname
     *
     * @param productname the value for m_crm_product.productname
     *
     * @mbggenerated Tue Jun 28 09:46:19 ICT 2016
     */
    public void setProductname(String productname) {
        this.productname = productname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_product.status
     *
     * @return the value of m_crm_product.status
     *
     * @mbggenerated Tue Jun 28 09:46:19 ICT 2016
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_product.status
     *
     * @param status the value for m_crm_product.status
     *
     * @mbggenerated Tue Jun 28 09:46:19 ICT 2016
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_product.accountid
     *
     * @return the value of m_crm_product.accountid
     *
     * @mbggenerated Tue Jun 28 09:46:19 ICT 2016
     */
    public Integer getAccountid() {
        return accountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_product.accountid
     *
     * @param accountid the value for m_crm_product.accountid
     *
     * @mbggenerated Tue Jun 28 09:46:19 ICT 2016
     */
    public void setAccountid(Integer accountid) {
        this.accountid = accountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_product.website
     *
     * @return the value of m_crm_product.website
     *
     * @mbggenerated Tue Jun 28 09:46:19 ICT 2016
     */
    public String getWebsite() {
        return website;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_product.website
     *
     * @param website the value for m_crm_product.website
     *
     * @mbggenerated Tue Jun 28 09:46:19 ICT 2016
     */
    public void setWebsite(String website) {
        this.website = website;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_product.quantity
     *
     * @return the value of m_crm_product.quantity
     *
     * @mbggenerated Tue Jun 28 09:46:19 ICT 2016
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_product.quantity
     *
     * @param quantity the value for m_crm_product.quantity
     *
     * @mbggenerated Tue Jun 28 09:46:19 ICT 2016
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_product.serialnumber
     *
     * @return the value of m_crm_product.serialnumber
     *
     * @mbggenerated Tue Jun 28 09:46:19 ICT 2016
     */
    public String getSerialnumber() {
        return serialnumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_product.serialnumber
     *
     * @param serialnumber the value for m_crm_product.serialnumber
     *
     * @mbggenerated Tue Jun 28 09:46:19 ICT 2016
     */
    public void setSerialnumber(String serialnumber) {
        this.serialnumber = serialnumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_product.assessnumber
     *
     * @return the value of m_crm_product.assessnumber
     *
     * @mbggenerated Tue Jun 28 09:46:19 ICT 2016
     */
    public String getAssessnumber() {
        return assessnumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_product.assessnumber
     *
     * @param assessnumber the value for m_crm_product.assessnumber
     *
     * @mbggenerated Tue Jun 28 09:46:19 ICT 2016
     */
    public void setAssessnumber(String assessnumber) {
        this.assessnumber = assessnumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_product.contactid
     *
     * @return the value of m_crm_product.contactid
     *
     * @mbggenerated Tue Jun 28 09:46:19 ICT 2016
     */
    public Integer getContactid() {
        return contactid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_product.contactid
     *
     * @param contactid the value for m_crm_product.contactid
     *
     * @mbggenerated Tue Jun 28 09:46:19 ICT 2016
     */
    public void setContactid(Integer contactid) {
        this.contactid = contactid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_product.supportstartdate
     *
     * @return the value of m_crm_product.supportstartdate
     *
     * @mbggenerated Tue Jun 28 09:46:19 ICT 2016
     */
    public Date getSupportstartdate() {
        return supportstartdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_product.supportstartdate
     *
     * @param supportstartdate the value for m_crm_product.supportstartdate
     *
     * @mbggenerated Tue Jun 28 09:46:19 ICT 2016
     */
    public void setSupportstartdate(Date supportstartdate) {
        this.supportstartdate = supportstartdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_product.supportenddate
     *
     * @return the value of m_crm_product.supportenddate
     *
     * @mbggenerated Tue Jun 28 09:46:19 ICT 2016
     */
    public Date getSupportenddate() {
        return supportenddate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_product.supportenddate
     *
     * @param supportenddate the value for m_crm_product.supportenddate
     *
     * @mbggenerated Tue Jun 28 09:46:19 ICT 2016
     */
    public void setSupportenddate(Date supportenddate) {
        this.supportenddate = supportenddate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_product.salesdate
     *
     * @return the value of m_crm_product.salesdate
     *
     * @mbggenerated Tue Jun 28 09:46:19 ICT 2016
     */
    public Date getSalesdate() {
        return salesdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_product.salesdate
     *
     * @param salesdate the value for m_crm_product.salesdate
     *
     * @mbggenerated Tue Jun 28 09:46:19 ICT 2016
     */
    public void setSalesdate(Date salesdate) {
        this.salesdate = salesdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_product.unitprice
     *
     * @return the value of m_crm_product.unitprice
     *
     * @mbggenerated Tue Jun 28 09:46:19 ICT 2016
     */
    public Double getUnitprice() {
        return unitprice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_product.unitprice
     *
     * @param unitprice the value for m_crm_product.unitprice
     *
     * @mbggenerated Tue Jun 28 09:46:19 ICT 2016
     */
    public void setUnitprice(Double unitprice) {
        this.unitprice = unitprice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_product.description
     *
     * @return the value of m_crm_product.description
     *
     * @mbggenerated Tue Jun 28 09:46:19 ICT 2016
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_product.description
     *
     * @param description the value for m_crm_product.description
     *
     * @mbggenerated Tue Jun 28 09:46:19 ICT 2016
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_product.producturl
     *
     * @return the value of m_crm_product.producturl
     *
     * @mbggenerated Tue Jun 28 09:46:19 ICT 2016
     */
    public String getProducturl() {
        return producturl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_product.producturl
     *
     * @param producturl the value for m_crm_product.producturl
     *
     * @mbggenerated Tue Jun 28 09:46:19 ICT 2016
     */
    public void setProducturl(String producturl) {
        this.producturl = producturl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_product.supportcontact
     *
     * @return the value of m_crm_product.supportcontact
     *
     * @mbggenerated Tue Jun 28 09:46:19 ICT 2016
     */
    public String getSupportcontact() {
        return supportcontact;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_product.supportcontact
     *
     * @param supportcontact the value for m_crm_product.supportcontact
     *
     * @mbggenerated Tue Jun 28 09:46:19 ICT 2016
     */
    public void setSupportcontact(String supportcontact) {
        this.supportcontact = supportcontact;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_product.supportterm
     *
     * @return the value of m_crm_product.supportterm
     *
     * @mbggenerated Tue Jun 28 09:46:19 ICT 2016
     */
    public String getSupportterm() {
        return supportterm;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_product.supportterm
     *
     * @param supportterm the value for m_crm_product.supportterm
     *
     * @mbggenerated Tue Jun 28 09:46:19 ICT 2016
     */
    public void setSupportterm(String supportterm) {
        this.supportterm = supportterm;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_product.supportdesc
     *
     * @return the value of m_crm_product.supportdesc
     *
     * @mbggenerated Tue Jun 28 09:46:19 ICT 2016
     */
    public String getSupportdesc() {
        return supportdesc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_product.supportdesc
     *
     * @param supportdesc the value for m_crm_product.supportdesc
     *
     * @mbggenerated Tue Jun 28 09:46:19 ICT 2016
     */
    public void setSupportdesc(String supportdesc) {
        this.supportdesc = supportdesc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_product.cost
     *
     * @return the value of m_crm_product.cost
     *
     * @mbggenerated Tue Jun 28 09:46:19 ICT 2016
     */
    public Double getCost() {
        return cost;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_product.cost
     *
     * @param cost the value for m_crm_product.cost
     *
     * @mbggenerated Tue Jun 28 09:46:19 ICT 2016
     */
    public void setCost(Double cost) {
        this.cost = cost;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_product.listprice
     *
     * @return the value of m_crm_product.listprice
     *
     * @mbggenerated Tue Jun 28 09:46:19 ICT 2016
     */
    public Double getListprice() {
        return listprice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_product.listprice
     *
     * @param listprice the value for m_crm_product.listprice
     *
     * @mbggenerated Tue Jun 28 09:46:19 ICT 2016
     */
    public void setListprice(Double listprice) {
        this.listprice = listprice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_product.groupid
     *
     * @return the value of m_crm_product.groupid
     *
     * @mbggenerated Tue Jun 28 09:46:19 ICT 2016
     */
    public Integer getGroupid() {
        return groupid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_product.groupid
     *
     * @param groupid the value for m_crm_product.groupid
     *
     * @mbggenerated Tue Jun 28 09:46:19 ICT 2016
     */
    public void setGroupid(Integer groupid) {
        this.groupid = groupid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_product.tax
     *
     * @return the value of m_crm_product.tax
     *
     * @mbggenerated Tue Jun 28 09:46:19 ICT 2016
     */
    public Double getTax() {
        return tax;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_product.tax
     *
     * @param tax the value for m_crm_product.tax
     *
     * @mbggenerated Tue Jun 28 09:46:19 ICT 2016
     */
    public void setTax(Double tax) {
        this.tax = tax;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_product.taxClass
     *
     * @return the value of m_crm_product.taxClass
     *
     * @mbggenerated Tue Jun 28 09:46:19 ICT 2016
     */
    public String getTaxclass() {
        return taxclass;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_product.taxClass
     *
     * @param taxclass the value for m_crm_product.taxClass
     *
     * @mbggenerated Tue Jun 28 09:46:19 ICT 2016
     */
    public void setTaxclass(String taxclass) {
        this.taxclass = taxclass;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_product.mftNumber
     *
     * @return the value of m_crm_product.mftNumber
     *
     * @mbggenerated Tue Jun 28 09:46:19 ICT 2016
     */
    public String getMftnumber() {
        return mftnumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_product.mftNumber
     *
     * @param mftnumber the value for m_crm_product.mftNumber
     *
     * @mbggenerated Tue Jun 28 09:46:19 ICT 2016
     */
    public void setMftnumber(String mftnumber) {
        this.mftnumber = mftnumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_product.vendorPartNumber
     *
     * @return the value of m_crm_product.vendorPartNumber
     *
     * @mbggenerated Tue Jun 28 09:46:19 ICT 2016
     */
    public String getVendorpartnumber() {
        return vendorpartnumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_product.vendorPartNumber
     *
     * @param vendorpartnumber the value for m_crm_product.vendorPartNumber
     *
     * @mbggenerated Tue Jun 28 09:46:19 ICT 2016
     */
    public void setVendorpartnumber(String vendorpartnumber) {
        this.vendorpartnumber = vendorpartnumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_product.createdTime
     *
     * @return the value of m_crm_product.createdTime
     *
     * @mbggenerated Tue Jun 28 09:46:19 ICT 2016
     */
    public Date getCreatedtime() {
        return createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_product.createdTime
     *
     * @param createdtime the value for m_crm_product.createdTime
     *
     * @mbggenerated Tue Jun 28 09:46:19 ICT 2016
     */
    public void setCreatedtime(Date createdtime) {
        this.createdtime = createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_product.createdUser
     *
     * @return the value of m_crm_product.createdUser
     *
     * @mbggenerated Tue Jun 28 09:46:19 ICT 2016
     */
    public String getCreateduser() {
        return createduser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_product.createdUser
     *
     * @param createduser the value for m_crm_product.createdUser
     *
     * @mbggenerated Tue Jun 28 09:46:19 ICT 2016
     */
    public void setCreateduser(String createduser) {
        this.createduser = createduser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_product.sAccountId
     *
     * @return the value of m_crm_product.sAccountId
     *
     * @mbggenerated Tue Jun 28 09:46:19 ICT 2016
     */
    public Integer getSaccountid() {
        return saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_product.sAccountId
     *
     * @param saccountid the value for m_crm_product.sAccountId
     *
     * @mbggenerated Tue Jun 28 09:46:19 ICT 2016
     */
    public void setSaccountid(Integer saccountid) {
        this.saccountid = saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_product.lastUpdatedTime
     *
     * @return the value of m_crm_product.lastUpdatedTime
     *
     * @mbggenerated Tue Jun 28 09:46:19 ICT 2016
     */
    public Date getLastupdatedtime() {
        return lastupdatedtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_product.lastUpdatedTime
     *
     * @param lastupdatedtime the value for m_crm_product.lastUpdatedTime
     *
     * @mbggenerated Tue Jun 28 09:46:19 ICT 2016
     */
    public void setLastupdatedtime(Date lastupdatedtime) {
        this.lastupdatedtime = lastupdatedtime;
    }

    public enum Field {
        id,
        productname,
        status,
        accountid,
        website,
        quantity,
        serialnumber,
        assessnumber,
        contactid,
        supportstartdate,
        supportenddate,
        salesdate,
        unitprice,
        description,
        producturl,
        supportcontact,
        supportterm,
        supportdesc,
        cost,
        listprice,
        groupid,
        tax,
        taxclass,
        mftnumber,
        vendorpartnumber,
        createdtime,
        createduser,
        saccountid,
        lastupdatedtime;

        public boolean equalTo(Object value) {
            return name().equals(value);
        }
    }
}