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
import java.util.Date;

public class Product extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_product.id
     *
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_product.productname
     *
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
     */
    @org.hibernate.validator.constraints.Length(max=255, message="Field value is too long")
    private String productname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_product.status
     *
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    private String status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_product.accountid
     *
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
     */
    private Integer accountid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_product.website
     *
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
     */
    @org.hibernate.validator.constraints.Length(max=255, message="Field value is too long")
    private String website;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_product.quantity
     *
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
     */
    private Integer quantity;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_product.serialnumber
     *
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    private String serialnumber;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_product.assessnumber
     *
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    private String assessnumber;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_product.contactid
     *
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
     */
    private Integer contactid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_product.supportstartdate
     *
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
     */
    private Date supportstartdate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_product.supportenddate
     *
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
     */
    private Date supportenddate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_product.salesdate
     *
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
     */
    private Date salesdate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_product.unitprice
     *
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
     */
    private Double unitprice;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_product.description
     *
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
     */
    @org.hibernate.validator.constraints.Length(max=4000, message="Field value is too long")
    private String description;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_product.producturl
     *
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
     */
    @org.hibernate.validator.constraints.Length(max=255, message="Field value is too long")
    private String producturl;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_product.supportcontact
     *
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
     */
    @org.hibernate.validator.constraints.Length(max=255, message="Field value is too long")
    private String supportcontact;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_product.supportterm
     *
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    private String supportterm;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_product.supportdesc
     *
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
     */
    @org.hibernate.validator.constraints.Length(max=100, message="Field value is too long")
    private String supportdesc;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_product.cost
     *
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
     */
    private Double cost;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_product.listprice
     *
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
     */
    private Double listprice;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_product.groupid
     *
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
     */
    private Integer groupid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_product.tax
     *
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
     */
    private Double tax;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_product.taxClass
     *
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    private String taxclass;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_product.mftNumber
     *
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    private String mftnumber;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_product.vendorPartNumber
     *
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    private String vendorpartnumber;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_product.createdTime
     *
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
     */
    private Date createdtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_product.createdUser
     *
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    private String createduser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_product.sAccountId
     *
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
     */
    private Integer saccountid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_product.lastUpdatedTime
     *
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
     */
    private Date lastupdatedtime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_product.id
     *
     * @return the value of m_crm_product.id
     *
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
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
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
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
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
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
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
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
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
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
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
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
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
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
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
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
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
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
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
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
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
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
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
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
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
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
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
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
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
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
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
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
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
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
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
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
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
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
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
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
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
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
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
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
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
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
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
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
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
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
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
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
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
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
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
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
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
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
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
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
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
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
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
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
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
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
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
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
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
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
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
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
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
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
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
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
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
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
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
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
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
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
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
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
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
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
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
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
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
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
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
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
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
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
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
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
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
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
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
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
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
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
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
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
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
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
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
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
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
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
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
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
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
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
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
     */
    public void setLastupdatedtime(Date lastupdatedtime) {
        this.lastupdatedtime = lastupdatedtime;
    }
}