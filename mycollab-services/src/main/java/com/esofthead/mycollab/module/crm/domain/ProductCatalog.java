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
/*Domain class of table m_crm_product_catalog*/
package com.esofthead.mycollab.module.crm.domain;

import com.esofthead.mycollab.core.arguments.ValuedBean;

import java.util.Date;

public class ProductCatalog extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_product_catalog.id
     *
     * @mbggenerated Mon Nov 11 00:19:40 ICT 2013
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_product_catalog.url
     *
     * @mbggenerated Mon Nov 11 00:19:40 ICT 2013
     */
    @org.hibernate.validator.constraints.Length(max=255, message="Field value is too long")
    private String url;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_product_catalog.taxclass
     *
     * @mbggenerated Mon Nov 11 00:19:40 ICT 2013
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    private String taxclass;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_product_catalog.mft_partnumber
     *
     * @mbggenerated Mon Nov 11 00:19:40 ICT 2013
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    private String mftPartnumber;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_product_catalog.vendor_partnumber
     *
     * @mbggenerated Mon Nov 11 00:19:40 ICT 2013
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    private String vendorPartnumber;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_product_catalog.currencyid
     *
     * @mbggenerated Mon Nov 11 00:19:40 ICT 2013
     */
    private Integer currencyid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_product_catalog.cost
     *
     * @mbggenerated Mon Nov 11 00:19:40 ICT 2013
     */
    private Double cost;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_product_catalog.listprice
     *
     * @mbggenerated Mon Nov 11 00:19:40 ICT 2013
     */
    private Double listprice;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_product_catalog.discountprice
     *
     * @mbggenerated Mon Nov 11 00:19:40 ICT 2013
     */
    private Double discountprice;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_product_catalog.pricing_formula
     *
     * @mbggenerated Mon Nov 11 00:19:40 ICT 2013
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    private String pricingFormula;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_product_catalog.description
     *
     * @mbggenerated Mon Nov 11 00:19:40 ICT 2013
     */
    @org.hibernate.validator.constraints.Length(max=4000, message="Field value is too long")
    private String description;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_product_catalog.date_available
     *
     * @mbggenerated Mon Nov 11 00:19:40 ICT 2013
     */
    private Date dateAvailable;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_product_catalog.availability
     *
     * @mbggenerated Mon Nov 11 00:19:40 ICT 2013
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    private String availability;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_product_catalog.quantity_in_stock
     *
     * @mbggenerated Mon Nov 11 00:19:40 ICT 2013
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    private String quantityInStock;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_product_catalog.support_name
     *
     * @mbggenerated Mon Nov 11 00:19:40 ICT 2013
     */
    @org.hibernate.validator.constraints.Length(max=100, message="Field value is too long")
    private String supportName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_product_catalog.support_contact
     *
     * @mbggenerated Mon Nov 11 00:19:40 ICT 2013
     */
    @org.hibernate.validator.constraints.Length(max=100, message="Field value is too long")
    private String supportContact;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_product_catalog.support_desc
     *
     * @mbggenerated Mon Nov 11 00:19:40 ICT 2013
     */
    @org.hibernate.validator.constraints.Length(max=100, message="Field value is too long")
    private String supportDesc;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_product_catalog.support_term
     *
     * @mbggenerated Mon Nov 11 00:19:40 ICT 2013
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    private String supportTerm;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_product_catalog.productname
     *
     * @mbggenerated Mon Nov 11 00:19:40 ICT 2013
     */
    @org.hibernate.validator.constraints.Length(max=100, message="Field value is too long")
    private String productname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_product_catalog.sAccountId
     *
     * @mbggenerated Mon Nov 11 00:19:40 ICT 2013
     */
    private Integer saccountid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_product_catalog.createdTime
     *
     * @mbggenerated Mon Nov 11 00:19:40 ICT 2013
     */
    private Date createdtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_product_catalog.lastUpdatedTime
     *
     * @mbggenerated Mon Nov 11 00:19:40 ICT 2013
     */
    private Date lastupdatedtime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_product_catalog.id
     *
     * @return the value of m_crm_product_catalog.id
     *
     * @mbggenerated Mon Nov 11 00:19:40 ICT 2013
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_product_catalog.id
     *
     * @param id the value for m_crm_product_catalog.id
     *
     * @mbggenerated Mon Nov 11 00:19:40 ICT 2013
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_product_catalog.url
     *
     * @return the value of m_crm_product_catalog.url
     *
     * @mbggenerated Mon Nov 11 00:19:40 ICT 2013
     */
    public String getUrl() {
        return url;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_product_catalog.url
     *
     * @param url the value for m_crm_product_catalog.url
     *
     * @mbggenerated Mon Nov 11 00:19:40 ICT 2013
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_product_catalog.taxclass
     *
     * @return the value of m_crm_product_catalog.taxclass
     *
     * @mbggenerated Mon Nov 11 00:19:40 ICT 2013
     */
    public String getTaxclass() {
        return taxclass;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_product_catalog.taxclass
     *
     * @param taxclass the value for m_crm_product_catalog.taxclass
     *
     * @mbggenerated Mon Nov 11 00:19:40 ICT 2013
     */
    public void setTaxclass(String taxclass) {
        this.taxclass = taxclass;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_product_catalog.mft_partnumber
     *
     * @return the value of m_crm_product_catalog.mft_partnumber
     *
     * @mbggenerated Mon Nov 11 00:19:40 ICT 2013
     */
    public String getMftPartnumber() {
        return mftPartnumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_product_catalog.mft_partnumber
     *
     * @param mftPartnumber the value for m_crm_product_catalog.mft_partnumber
     *
     * @mbggenerated Mon Nov 11 00:19:40 ICT 2013
     */
    public void setMftPartnumber(String mftPartnumber) {
        this.mftPartnumber = mftPartnumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_product_catalog.vendor_partnumber
     *
     * @return the value of m_crm_product_catalog.vendor_partnumber
     *
     * @mbggenerated Mon Nov 11 00:19:40 ICT 2013
     */
    public String getVendorPartnumber() {
        return vendorPartnumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_product_catalog.vendor_partnumber
     *
     * @param vendorPartnumber the value for m_crm_product_catalog.vendor_partnumber
     *
     * @mbggenerated Mon Nov 11 00:19:40 ICT 2013
     */
    public void setVendorPartnumber(String vendorPartnumber) {
        this.vendorPartnumber = vendorPartnumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_product_catalog.currencyid
     *
     * @return the value of m_crm_product_catalog.currencyid
     *
     * @mbggenerated Mon Nov 11 00:19:40 ICT 2013
     */
    public Integer getCurrencyid() {
        return currencyid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_product_catalog.currencyid
     *
     * @param currencyid the value for m_crm_product_catalog.currencyid
     *
     * @mbggenerated Mon Nov 11 00:19:40 ICT 2013
     */
    public void setCurrencyid(Integer currencyid) {
        this.currencyid = currencyid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_product_catalog.cost
     *
     * @return the value of m_crm_product_catalog.cost
     *
     * @mbggenerated Mon Nov 11 00:19:40 ICT 2013
     */
    public Double getCost() {
        return cost;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_product_catalog.cost
     *
     * @param cost the value for m_crm_product_catalog.cost
     *
     * @mbggenerated Mon Nov 11 00:19:40 ICT 2013
     */
    public void setCost(Double cost) {
        this.cost = cost;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_product_catalog.listprice
     *
     * @return the value of m_crm_product_catalog.listprice
     *
     * @mbggenerated Mon Nov 11 00:19:40 ICT 2013
     */
    public Double getListprice() {
        return listprice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_product_catalog.listprice
     *
     * @param listprice the value for m_crm_product_catalog.listprice
     *
     * @mbggenerated Mon Nov 11 00:19:40 ICT 2013
     */
    public void setListprice(Double listprice) {
        this.listprice = listprice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_product_catalog.discountprice
     *
     * @return the value of m_crm_product_catalog.discountprice
     *
     * @mbggenerated Mon Nov 11 00:19:40 ICT 2013
     */
    public Double getDiscountprice() {
        return discountprice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_product_catalog.discountprice
     *
     * @param discountprice the value for m_crm_product_catalog.discountprice
     *
     * @mbggenerated Mon Nov 11 00:19:40 ICT 2013
     */
    public void setDiscountprice(Double discountprice) {
        this.discountprice = discountprice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_product_catalog.pricing_formula
     *
     * @return the value of m_crm_product_catalog.pricing_formula
     *
     * @mbggenerated Mon Nov 11 00:19:40 ICT 2013
     */
    public String getPricingFormula() {
        return pricingFormula;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_product_catalog.pricing_formula
     *
     * @param pricingFormula the value for m_crm_product_catalog.pricing_formula
     *
     * @mbggenerated Mon Nov 11 00:19:40 ICT 2013
     */
    public void setPricingFormula(String pricingFormula) {
        this.pricingFormula = pricingFormula;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_product_catalog.description
     *
     * @return the value of m_crm_product_catalog.description
     *
     * @mbggenerated Mon Nov 11 00:19:40 ICT 2013
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_product_catalog.description
     *
     * @param description the value for m_crm_product_catalog.description
     *
     * @mbggenerated Mon Nov 11 00:19:40 ICT 2013
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_product_catalog.date_available
     *
     * @return the value of m_crm_product_catalog.date_available
     *
     * @mbggenerated Mon Nov 11 00:19:40 ICT 2013
     */
    public Date getDateAvailable() {
        return dateAvailable;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_product_catalog.date_available
     *
     * @param dateAvailable the value for m_crm_product_catalog.date_available
     *
     * @mbggenerated Mon Nov 11 00:19:40 ICT 2013
     */
    public void setDateAvailable(Date dateAvailable) {
        this.dateAvailable = dateAvailable;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_product_catalog.availability
     *
     * @return the value of m_crm_product_catalog.availability
     *
     * @mbggenerated Mon Nov 11 00:19:40 ICT 2013
     */
    public String getAvailability() {
        return availability;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_product_catalog.availability
     *
     * @param availability the value for m_crm_product_catalog.availability
     *
     * @mbggenerated Mon Nov 11 00:19:40 ICT 2013
     */
    public void setAvailability(String availability) {
        this.availability = availability;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_product_catalog.quantity_in_stock
     *
     * @return the value of m_crm_product_catalog.quantity_in_stock
     *
     * @mbggenerated Mon Nov 11 00:19:40 ICT 2013
     */
    public String getQuantityInStock() {
        return quantityInStock;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_product_catalog.quantity_in_stock
     *
     * @param quantityInStock the value for m_crm_product_catalog.quantity_in_stock
     *
     * @mbggenerated Mon Nov 11 00:19:40 ICT 2013
     */
    public void setQuantityInStock(String quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_product_catalog.support_name
     *
     * @return the value of m_crm_product_catalog.support_name
     *
     * @mbggenerated Mon Nov 11 00:19:40 ICT 2013
     */
    public String getSupportName() {
        return supportName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_product_catalog.support_name
     *
     * @param supportName the value for m_crm_product_catalog.support_name
     *
     * @mbggenerated Mon Nov 11 00:19:40 ICT 2013
     */
    public void setSupportName(String supportName) {
        this.supportName = supportName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_product_catalog.support_contact
     *
     * @return the value of m_crm_product_catalog.support_contact
     *
     * @mbggenerated Mon Nov 11 00:19:40 ICT 2013
     */
    public String getSupportContact() {
        return supportContact;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_product_catalog.support_contact
     *
     * @param supportContact the value for m_crm_product_catalog.support_contact
     *
     * @mbggenerated Mon Nov 11 00:19:40 ICT 2013
     */
    public void setSupportContact(String supportContact) {
        this.supportContact = supportContact;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_product_catalog.support_desc
     *
     * @return the value of m_crm_product_catalog.support_desc
     *
     * @mbggenerated Mon Nov 11 00:19:40 ICT 2013
     */
    public String getSupportDesc() {
        return supportDesc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_product_catalog.support_desc
     *
     * @param supportDesc the value for m_crm_product_catalog.support_desc
     *
     * @mbggenerated Mon Nov 11 00:19:40 ICT 2013
     */
    public void setSupportDesc(String supportDesc) {
        this.supportDesc = supportDesc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_product_catalog.support_term
     *
     * @return the value of m_crm_product_catalog.support_term
     *
     * @mbggenerated Mon Nov 11 00:19:40 ICT 2013
     */
    public String getSupportTerm() {
        return supportTerm;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_product_catalog.support_term
     *
     * @param supportTerm the value for m_crm_product_catalog.support_term
     *
     * @mbggenerated Mon Nov 11 00:19:40 ICT 2013
     */
    public void setSupportTerm(String supportTerm) {
        this.supportTerm = supportTerm;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_product_catalog.productname
     *
     * @return the value of m_crm_product_catalog.productname
     *
     * @mbggenerated Mon Nov 11 00:19:40 ICT 2013
     */
    public String getProductname() {
        return productname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_product_catalog.productname
     *
     * @param productname the value for m_crm_product_catalog.productname
     *
     * @mbggenerated Mon Nov 11 00:19:40 ICT 2013
     */
    public void setProductname(String productname) {
        this.productname = productname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_product_catalog.sAccountId
     *
     * @return the value of m_crm_product_catalog.sAccountId
     *
     * @mbggenerated Mon Nov 11 00:19:40 ICT 2013
     */
    public Integer getSaccountid() {
        return saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_product_catalog.sAccountId
     *
     * @param saccountid the value for m_crm_product_catalog.sAccountId
     *
     * @mbggenerated Mon Nov 11 00:19:40 ICT 2013
     */
    public void setSaccountid(Integer saccountid) {
        this.saccountid = saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_product_catalog.createdTime
     *
     * @return the value of m_crm_product_catalog.createdTime
     *
     * @mbggenerated Mon Nov 11 00:19:40 ICT 2013
     */
    public Date getCreatedtime() {
        return createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_product_catalog.createdTime
     *
     * @param createdtime the value for m_crm_product_catalog.createdTime
     *
     * @mbggenerated Mon Nov 11 00:19:40 ICT 2013
     */
    public void setCreatedtime(Date createdtime) {
        this.createdtime = createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_product_catalog.lastUpdatedTime
     *
     * @return the value of m_crm_product_catalog.lastUpdatedTime
     *
     * @mbggenerated Mon Nov 11 00:19:40 ICT 2013
     */
    public Date getLastupdatedtime() {
        return lastupdatedtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_product_catalog.lastUpdatedTime
     *
     * @param lastupdatedtime the value for m_crm_product_catalog.lastUpdatedTime
     *
     * @mbggenerated Mon Nov 11 00:19:40 ICT 2013
     */
    public void setLastupdatedtime(Date lastupdatedtime) {
        this.lastupdatedtime = lastupdatedtime;
    }
}