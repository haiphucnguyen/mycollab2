/*Domain class of table s_pro_edition_info*/
package com.mycollab.ondemand.module.billing.domain;

import com.mycollab.core.arguments.ValuedBean;
import com.mycollab.db.metadata.Column;
import com.mycollab.db.metadata.Table;
import java.util.Date;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.ibatis.type.Alias;
import org.hibernate.validator.constraints.Length;

@SuppressWarnings("ucd")
@Table("s_pro_edition_info")
@Alias("ProEditionInfo")
public class ProEditionInfo extends ValuedBean {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_pro_edition_info.id
     *
     * @mbg.generated Thu Dec 06 08:34:03 CST 2018
     */
    @Column("id")
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_pro_edition_info.company
     *
     * @mbg.generated Thu Dec 06 08:34:03 CST 2018
     */
    @Length(max=400, message="Field value is too long")
    @Column("company")
    private String company;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_pro_edition_info.email
     *
     * @mbg.generated Thu Dec 06 08:34:03 CST 2018
     */
    @Length(max=400, message="Field value is too long")
    @Column("email")
    private String email;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_pro_edition_info.internalProductName
     *
     * @mbg.generated Thu Dec 06 08:34:03 CST 2018
     */
    @Length(max=400, message="Field value is too long")
    @Column("internalProductName")
    private String internalproductname;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_pro_edition_info.name
     *
     * @mbg.generated Thu Dec 06 08:34:03 CST 2018
     */
    @Length(max=400, message="Field value is too long")
    @Column("name")
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_pro_edition_info.quantity
     *
     * @mbg.generated Thu Dec 06 08:34:03 CST 2018
     */
    @Column("quantity")
    private Integer quantity;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_pro_edition_info.issueDate
     *
     * @mbg.generated Thu Dec 06 08:34:03 CST 2018
     */
    @Column("issueDate")
    private Date issuedate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_pro_edition_info.type
     *
     * @mbg.generated Thu Dec 06 08:34:03 CST 2018
     */
    @Length(max=45, message="Field value is too long")
    @Column("type")
    private String type;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_pro_edition_info.cost
     *
     * @mbg.generated Thu Dec 06 08:34:03 CST 2018
     */
    @Column("cost")
    private Double cost;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_pro_edition_info.orderId
     *
     * @mbg.generated Thu Dec 06 08:34:03 CST 2018
     */
    @Length(max=100, message="Field value is too long")
    @Column("orderId")
    private String orderid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_pro_edition_info.country
     *
     * @mbg.generated Thu Dec 06 08:34:03 CST 2018
     */
    @Length(max=45, message="Field value is too long")
    @Column("country")
    private String country;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_pro_edition_info.phone
     *
     * @mbg.generated Thu Dec 06 08:34:03 CST 2018
     */
    @Length(max=45, message="Field value is too long")
    @Column("phone")
    private String phone;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_pro_edition_info.address1
     *
     * @mbg.generated Thu Dec 06 08:34:03 CST 2018
     */
    @Length(max=400, message="Field value is too long")
    @Column("address1")
    private String address1;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_pro_edition_info.address2
     *
     * @mbg.generated Thu Dec 06 08:34:03 CST 2018
     */
    @Length(max=400, message="Field value is too long")
    @Column("address2")
    private String address2;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_pro_edition_info.city
     *
     * @mbg.generated Thu Dec 06 08:34:03 CST 2018
     */
    @Length(max=400, message="Field value is too long")
    @Column("city")
    private String city;

    private static final long serialVersionUID = 1;

    public final boolean equals(Object obj) {
        if (obj == null) { return false;}
        if (obj == this) { return true;}
        if (!obj.getClass().isAssignableFrom(getClass())) { return false;}
        ProEditionInfo item = (ProEditionInfo)obj;
        return new EqualsBuilder().append(id, item.id).build();
    }

    public final int hashCode() {
        return new HashCodeBuilder(1799, 1245).append(id).build();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_pro_edition_info.id
     *
     * @return the value of s_pro_edition_info.id
     *
     * @mbg.generated Thu Dec 06 08:34:03 CST 2018
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_pro_edition_info.id
     *
     * @param id the value for s_pro_edition_info.id
     *
     * @mbg.generated Thu Dec 06 08:34:03 CST 2018
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_pro_edition_info.company
     *
     * @return the value of s_pro_edition_info.company
     *
     * @mbg.generated Thu Dec 06 08:34:03 CST 2018
     */
    public String getCompany() {
        return company;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_pro_edition_info.company
     *
     * @param company the value for s_pro_edition_info.company
     *
     * @mbg.generated Thu Dec 06 08:34:03 CST 2018
     */
    public void setCompany(String company) {
        this.company = company;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_pro_edition_info.email
     *
     * @return the value of s_pro_edition_info.email
     *
     * @mbg.generated Thu Dec 06 08:34:03 CST 2018
     */
    public String getEmail() {
        return email;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_pro_edition_info.email
     *
     * @param email the value for s_pro_edition_info.email
     *
     * @mbg.generated Thu Dec 06 08:34:03 CST 2018
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_pro_edition_info.internalProductName
     *
     * @return the value of s_pro_edition_info.internalProductName
     *
     * @mbg.generated Thu Dec 06 08:34:03 CST 2018
     */
    public String getInternalproductname() {
        return internalproductname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_pro_edition_info.internalProductName
     *
     * @param internalproductname the value for s_pro_edition_info.internalProductName
     *
     * @mbg.generated Thu Dec 06 08:34:03 CST 2018
     */
    public void setInternalproductname(String internalproductname) {
        this.internalproductname = internalproductname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_pro_edition_info.name
     *
     * @return the value of s_pro_edition_info.name
     *
     * @mbg.generated Thu Dec 06 08:34:03 CST 2018
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_pro_edition_info.name
     *
     * @param name the value for s_pro_edition_info.name
     *
     * @mbg.generated Thu Dec 06 08:34:03 CST 2018
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_pro_edition_info.quantity
     *
     * @return the value of s_pro_edition_info.quantity
     *
     * @mbg.generated Thu Dec 06 08:34:03 CST 2018
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_pro_edition_info.quantity
     *
     * @param quantity the value for s_pro_edition_info.quantity
     *
     * @mbg.generated Thu Dec 06 08:34:03 CST 2018
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_pro_edition_info.issueDate
     *
     * @return the value of s_pro_edition_info.issueDate
     *
     * @mbg.generated Thu Dec 06 08:34:03 CST 2018
     */
    public Date getIssuedate() {
        return issuedate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_pro_edition_info.issueDate
     *
     * @param issuedate the value for s_pro_edition_info.issueDate
     *
     * @mbg.generated Thu Dec 06 08:34:03 CST 2018
     */
    public void setIssuedate(Date issuedate) {
        this.issuedate = issuedate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_pro_edition_info.type
     *
     * @return the value of s_pro_edition_info.type
     *
     * @mbg.generated Thu Dec 06 08:34:03 CST 2018
     */
    public String getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_pro_edition_info.type
     *
     * @param type the value for s_pro_edition_info.type
     *
     * @mbg.generated Thu Dec 06 08:34:03 CST 2018
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_pro_edition_info.cost
     *
     * @return the value of s_pro_edition_info.cost
     *
     * @mbg.generated Thu Dec 06 08:34:03 CST 2018
     */
    public Double getCost() {
        return cost;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_pro_edition_info.cost
     *
     * @param cost the value for s_pro_edition_info.cost
     *
     * @mbg.generated Thu Dec 06 08:34:03 CST 2018
     */
    public void setCost(Double cost) {
        this.cost = cost;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_pro_edition_info.orderId
     *
     * @return the value of s_pro_edition_info.orderId
     *
     * @mbg.generated Thu Dec 06 08:34:03 CST 2018
     */
    public String getOrderid() {
        return orderid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_pro_edition_info.orderId
     *
     * @param orderid the value for s_pro_edition_info.orderId
     *
     * @mbg.generated Thu Dec 06 08:34:03 CST 2018
     */
    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_pro_edition_info.country
     *
     * @return the value of s_pro_edition_info.country
     *
     * @mbg.generated Thu Dec 06 08:34:03 CST 2018
     */
    public String getCountry() {
        return country;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_pro_edition_info.country
     *
     * @param country the value for s_pro_edition_info.country
     *
     * @mbg.generated Thu Dec 06 08:34:03 CST 2018
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_pro_edition_info.phone
     *
     * @return the value of s_pro_edition_info.phone
     *
     * @mbg.generated Thu Dec 06 08:34:03 CST 2018
     */
    public String getPhone() {
        return phone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_pro_edition_info.phone
     *
     * @param phone the value for s_pro_edition_info.phone
     *
     * @mbg.generated Thu Dec 06 08:34:03 CST 2018
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_pro_edition_info.address1
     *
     * @return the value of s_pro_edition_info.address1
     *
     * @mbg.generated Thu Dec 06 08:34:03 CST 2018
     */
    public String getAddress1() {
        return address1;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_pro_edition_info.address1
     *
     * @param address1 the value for s_pro_edition_info.address1
     *
     * @mbg.generated Thu Dec 06 08:34:03 CST 2018
     */
    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_pro_edition_info.address2
     *
     * @return the value of s_pro_edition_info.address2
     *
     * @mbg.generated Thu Dec 06 08:34:03 CST 2018
     */
    public String getAddress2() {
        return address2;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_pro_edition_info.address2
     *
     * @param address2 the value for s_pro_edition_info.address2
     *
     * @mbg.generated Thu Dec 06 08:34:03 CST 2018
     */
    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_pro_edition_info.city
     *
     * @return the value of s_pro_edition_info.city
     *
     * @mbg.generated Thu Dec 06 08:34:03 CST 2018
     */
    public String getCity() {
        return city;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_pro_edition_info.city
     *
     * @param city the value for s_pro_edition_info.city
     *
     * @mbg.generated Thu Dec 06 08:34:03 CST 2018
     */
    public void setCity(String city) {
        this.city = city;
    }

    public enum Field {
        id,
        company,
        email,
        internalproductname,
        name,
        quantity,
        issuedate,
        type,
        cost,
        orderid,
        country,
        phone,
        address1,
        address2,
        city;

        public boolean equalTo(Object value) {
            return name().equals(value);
        }
    }
}