/*Domain class of table s_relay_mail*/
package com.esofthead.mycollab.common.domain;

import com.esofthead.mycollab.core.arguments.ValuedBean;
import com.esofthead.mycollab.core.db.metadata.Column;
import com.esofthead.mycollab.core.db.metadata.Table;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.Length;

@SuppressWarnings("ucd")
@Table("s_relay_mail")
class RelayEmail extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_relay_mail.id
     *
     * @mbggenerated Sun May 15 02:13:10 ICT 2016
     */
    @Column("id")
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_relay_mail.sAccountId
     *
     * @mbggenerated Sun May 15 02:13:10 ICT 2016
     */
    @Column("sAccountId")
    private Integer saccountid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_relay_mail.fromName
     *
     * @mbggenerated Sun May 15 02:13:10 ICT 2016
     */
    @Length(max=255, message="Field value is too long")
    @Column("fromName")
    private String fromname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_relay_mail.fromEmail
     *
     * @mbggenerated Sun May 15 02:13:10 ICT 2016
     */
    @Length(max=255, message="Field value is too long")
    @Column("fromEmail")
    private String fromemail;

    private static final long serialVersionUID = 1;

    public final boolean equals(Object obj) {
        if (obj == null) { return false;}
        if (obj == this) { return true;}
        if (!obj.getClass().isAssignableFrom(getClass())) { return false;}
        RelayEmail item = (RelayEmail)obj;
        return new EqualsBuilder().append(id, item.id).build();
    }

    public final int hashCode() {
        return new HashCodeBuilder(81, 1841).append(id).build();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_relay_mail.id
     *
     * @return the value of s_relay_mail.id
     *
     * @mbggenerated Sun May 15 02:13:10 ICT 2016
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_relay_mail.id
     *
     * @param id the value for s_relay_mail.id
     *
     * @mbggenerated Sun May 15 02:13:10 ICT 2016
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_relay_mail.sAccountId
     *
     * @return the value of s_relay_mail.sAccountId
     *
     * @mbggenerated Sun May 15 02:13:10 ICT 2016
     */
    public Integer getSaccountid() {
        return saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_relay_mail.sAccountId
     *
     * @param saccountid the value for s_relay_mail.sAccountId
     *
     * @mbggenerated Sun May 15 02:13:10 ICT 2016
     */
    public void setSaccountid(Integer saccountid) {
        this.saccountid = saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_relay_mail.fromName
     *
     * @return the value of s_relay_mail.fromName
     *
     * @mbggenerated Sun May 15 02:13:10 ICT 2016
     */
    public String getFromname() {
        return fromname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_relay_mail.fromName
     *
     * @param fromname the value for s_relay_mail.fromName
     *
     * @mbggenerated Sun May 15 02:13:10 ICT 2016
     */
    public void setFromname(String fromname) {
        this.fromname = fromname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_relay_mail.fromEmail
     *
     * @return the value of s_relay_mail.fromEmail
     *
     * @mbggenerated Sun May 15 02:13:10 ICT 2016
     */
    public String getFromemail() {
        return fromemail;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_relay_mail.fromEmail
     *
     * @param fromemail the value for s_relay_mail.fromEmail
     *
     * @mbggenerated Sun May 15 02:13:10 ICT 2016
     */
    public void setFromemail(String fromemail) {
        this.fromemail = fromemail;
    }
}