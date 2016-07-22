/*Domain class of table s_email_preference*/
package com.mycollab.ondemand.module.support.domain;

import com.mycollab.core.arguments.ValuedBean;
import com.mycollab.db.metadata.Column;
import com.mycollab.db.metadata.Table;
import java.util.Date;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.Length;

@SuppressWarnings("ucd")
@Table("s_email_preference")
public class EmailReference extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_email_preference.email
     *
     * @mbggenerated Fri Jul 22 15:43:23 ICT 2016
     */
    @Length(max=45, message="Field value is too long")
    @Column("email")
    private String email;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_email_preference.createdTime
     *
     * @mbggenerated Fri Jul 22 15:43:23 ICT 2016
     */
    @Column("createdTime")
    private Date createdtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_email_preference.subscribe
     *
     * @mbggenerated Fri Jul 22 15:43:23 ICT 2016
     */
    @Column("subscribe")
    private Boolean subscribe;

    private static final long serialVersionUID = 1;

    public final boolean equals(Object obj) {
        if (obj == null) { return false;}
        if (obj == this) { return true;}
        if (!obj.getClass().isAssignableFrom(getClass())) { return false;}
        EmailReference item = (EmailReference)obj;
        return new EqualsBuilder().append(email, item.email).build();
    }

    public final int hashCode() {
        return new HashCodeBuilder(1265, 1285).append(email).build();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_email_preference.email
     *
     * @return the value of s_email_preference.email
     *
     * @mbggenerated Fri Jul 22 15:43:23 ICT 2016
     */
    public String getEmail() {
        return email;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_email_preference.email
     *
     * @param email the value for s_email_preference.email
     *
     * @mbggenerated Fri Jul 22 15:43:23 ICT 2016
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_email_preference.createdTime
     *
     * @return the value of s_email_preference.createdTime
     *
     * @mbggenerated Fri Jul 22 15:43:23 ICT 2016
     */
    public Date getCreatedtime() {
        return createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_email_preference.createdTime
     *
     * @param createdtime the value for s_email_preference.createdTime
     *
     * @mbggenerated Fri Jul 22 15:43:23 ICT 2016
     */
    public void setCreatedtime(Date createdtime) {
        this.createdtime = createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_email_preference.subscribe
     *
     * @return the value of s_email_preference.subscribe
     *
     * @mbggenerated Fri Jul 22 15:43:23 ICT 2016
     */
    public Boolean getSubscribe() {
        return subscribe;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_email_preference.subscribe
     *
     * @param subscribe the value for s_email_preference.subscribe
     *
     * @mbggenerated Fri Jul 22 15:43:23 ICT 2016
     */
    public void setSubscribe(Boolean subscribe) {
        this.subscribe = subscribe;
    }

    public enum Field {
        email,
        createdtime,
        subscribe;

        public boolean equalTo(Object value) {
            return name().equals(value);
        }
    }
}