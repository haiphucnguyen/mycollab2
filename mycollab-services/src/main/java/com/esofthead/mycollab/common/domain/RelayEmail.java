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
     * @mbggenerated Fri Mar 18 20:26:09 ICT 2016
     */
    @Column("id")
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_relay_mail.sAccountId
     *
     * @mbggenerated Fri Mar 18 20:26:09 ICT 2016
     */
    @Column("sAccountId")
    private Integer saccountid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_relay_mail.fromName
     *
     * @mbggenerated Fri Mar 18 20:26:09 ICT 2016
     */
    @Length(max=255, message="Field value is too long")
    @Column("fromName")
    private String fromname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_relay_mail.fromEmail
     *
     * @mbggenerated Fri Mar 18 20:26:09 ICT 2016
     */
    @Length(max=255, message="Field value is too long")
    @Column("fromEmail")
    private String fromemail;

    private static final long serialVersionUID = 1;

    public final boolean equals(Object obj) {
        if (obj == null) { return false;}
        if (obj == this) { return true;}
        if (obj.getClass() != getClass()) { return false;}
        RelayEmail item = (RelayEmail)obj;
        return new EqualsBuilder().append(id, item.id).build();
    }

    public final int hashCode() {
        return new HashCodeBuilder(1145, 749).append(id).build();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_relay_mail.id
     *
     * @return the value of s_relay_mail.id
     *
     * @mbggenerated Fri Mar 18 20:26:09 ICT 2016
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
     * @mbggenerated Fri Mar 18 20:26:09 ICT 2016
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
     * @mbggenerated Fri Mar 18 20:26:09 ICT 2016
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
     * @mbggenerated Fri Mar 18 20:26:09 ICT 2016
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
     * @mbggenerated Fri Mar 18 20:26:09 ICT 2016
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
     * @mbggenerated Fri Mar 18 20:26:09 ICT 2016
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
     * @mbggenerated Fri Mar 18 20:26:09 ICT 2016
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
     * @mbggenerated Fri Mar 18 20:26:09 ICT 2016
     */
    public void setFromemail(String fromemail) {
        this.fromemail = fromemail;
    }
}