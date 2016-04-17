/*Domain class of table s_testimonial*/
package com.esofthead.mycollab.ondemand.module.support.domain;

import com.esofthead.mycollab.core.arguments.ValuedBean;

@SuppressWarnings("ucd")
@com.esofthead.mycollab.core.db.metadata.Table("s_testimonial")
public class Testimonial extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_testimonial.id
     *
     * @mbggenerated Wed May 06 12:49:59 ICT 2015
     */
    @com.esofthead.mycollab.core.db.metadata.Column("id")
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_testimonial.displayName
     *
     * @mbggenerated Wed May 06 12:49:59 ICT 2015
     */
    @org.hibernate.validator.constraints.Length(max=100, message="Field value is too long")
    @com.esofthead.mycollab.core.db.metadata.Column("displayName")
    private String displayname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_testimonial.jobRole
     *
     * @mbggenerated Wed May 06 12:49:59 ICT 2015
     */
    @org.hibernate.validator.constraints.Length(max=100, message="Field value is too long")
    @com.esofthead.mycollab.core.db.metadata.Column("jobRole")
    private String jobrole;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_testimonial.company
     *
     * @mbggenerated Wed May 06 12:49:59 ICT 2015
     */
    @org.hibernate.validator.constraints.Length(max=100, message="Field value is too long")
    @com.esofthead.mycollab.core.db.metadata.Column("company")
    private String company;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_testimonial.website
     *
     * @mbggenerated Wed May 06 12:49:59 ICT 2015
     */
    @org.hibernate.validator.constraints.Length(max=100, message="Field value is too long")
    @com.esofthead.mycollab.core.db.metadata.Column("website")
    private String website;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_testimonial.email
     *
     * @mbggenerated Wed May 06 12:49:59 ICT 2015
     */
    @org.hibernate.validator.constraints.Length(max=100, message="Field value is too long")
    @com.esofthead.mycollab.core.db.metadata.Column("email")
    private String email;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_testimonial.testimonial
     *
     * @mbggenerated Wed May 06 12:49:59 ICT 2015
     */
    @org.hibernate.validator.constraints.Length(max=65535, message="Field value is too long")
    @com.esofthead.mycollab.core.db.metadata.Column("testimonial")
    private String testimonial;

    private static final long serialVersionUID = 1;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_testimonial.id
     *
     * @return the value of s_testimonial.id
     *
     * @mbggenerated Wed May 06 12:49:59 ICT 2015
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_testimonial.id
     *
     * @param id the value for s_testimonial.id
     *
     * @mbggenerated Wed May 06 12:49:59 ICT 2015
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_testimonial.displayName
     *
     * @return the value of s_testimonial.displayName
     *
     * @mbggenerated Wed May 06 12:49:59 ICT 2015
     */
    public String getDisplayname() {
        return displayname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_testimonial.displayName
     *
     * @param displayname the value for s_testimonial.displayName
     *
     * @mbggenerated Wed May 06 12:49:59 ICT 2015
     */
    public void setDisplayname(String displayname) {
        this.displayname = displayname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_testimonial.jobRole
     *
     * @return the value of s_testimonial.jobRole
     *
     * @mbggenerated Wed May 06 12:49:59 ICT 2015
     */
    public String getJobrole() {
        return jobrole;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_testimonial.jobRole
     *
     * @param jobrole the value for s_testimonial.jobRole
     *
     * @mbggenerated Wed May 06 12:49:59 ICT 2015
     */
    public void setJobrole(String jobrole) {
        this.jobrole = jobrole;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_testimonial.company
     *
     * @return the value of s_testimonial.company
     *
     * @mbggenerated Wed May 06 12:49:59 ICT 2015
     */
    public String getCompany() {
        return company;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_testimonial.company
     *
     * @param company the value for s_testimonial.company
     *
     * @mbggenerated Wed May 06 12:49:59 ICT 2015
     */
    public void setCompany(String company) {
        this.company = company;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_testimonial.website
     *
     * @return the value of s_testimonial.website
     *
     * @mbggenerated Wed May 06 12:49:59 ICT 2015
     */
    public String getWebsite() {
        return website;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_testimonial.website
     *
     * @param website the value for s_testimonial.website
     *
     * @mbggenerated Wed May 06 12:49:59 ICT 2015
     */
    public void setWebsite(String website) {
        this.website = website;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_testimonial.email
     *
     * @return the value of s_testimonial.email
     *
     * @mbggenerated Wed May 06 12:49:59 ICT 2015
     */
    public String getEmail() {
        return email;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_testimonial.email
     *
     * @param email the value for s_testimonial.email
     *
     * @mbggenerated Wed May 06 12:49:59 ICT 2015
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_testimonial.testimonial
     *
     * @return the value of s_testimonial.testimonial
     *
     * @mbggenerated Wed May 06 12:49:59 ICT 2015
     */
    public String getTestimonial() {
        return testimonial;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_testimonial.testimonial
     *
     * @param testimonial the value for s_testimonial.testimonial
     *
     * @mbggenerated Wed May 06 12:49:59 ICT 2015
     */
    public void setTestimonial(String testimonial) {
        this.testimonial = testimonial;
    }

    public enum Field {
        id,
        displayname,
        jobrole,
        company,
        website,
        email,
        testimonial;

        public boolean equalTo(Object value) {
            return name().equals(value);
        }
    }
}