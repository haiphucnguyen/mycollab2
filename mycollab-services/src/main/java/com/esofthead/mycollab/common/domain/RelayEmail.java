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

@SuppressWarnings("ucd")
@com.esofthead.mycollab.core.db.metadata.Table("s_relay_mail")
class RelayEmail extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_relay_mail.id
     *
     * @mbggenerated Fri Mar 06 17:04:13 ICT 2015
     */
    @com.esofthead.mycollab.core.db.metadata.Column("id")
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_relay_mail.sAccountId
     *
     * @mbggenerated Fri Mar 06 17:04:13 ICT 2015
     */
    @com.esofthead.mycollab.core.db.metadata.Column("sAccountId")
    private Integer saccountid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_relay_mail.fromName
     *
     * @mbggenerated Fri Mar 06 17:04:13 ICT 2015
     */
    @org.hibernate.validator.constraints.Length(max=255, message="Field value is too long")
    @com.esofthead.mycollab.core.db.metadata.Column("fromName")
    private String fromname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_relay_mail.fromEmail
     *
     * @mbggenerated Fri Mar 06 17:04:13 ICT 2015
     */
    @org.hibernate.validator.constraints.Length(max=255, message="Field value is too long")
    @com.esofthead.mycollab.core.db.metadata.Column("fromEmail")
    private String fromemail;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_relay_mail.emailHandlerBean
     *
     * @mbggenerated Fri Mar 06 17:04:13 ICT 2015
     */
    @org.hibernate.validator.constraints.Length(max=400, message="Field value is too long")
    @com.esofthead.mycollab.core.db.metadata.Column("emailHandlerBean")
    private String emailhandlerbean;

    private static final long serialVersionUID = 1;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_relay_mail.id
     *
     * @return the value of s_relay_mail.id
     *
     * @mbggenerated Fri Mar 06 17:04:13 ICT 2015
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
     * @mbggenerated Fri Mar 06 17:04:13 ICT 2015
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
     * @mbggenerated Fri Mar 06 17:04:13 ICT 2015
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
     * @mbggenerated Fri Mar 06 17:04:13 ICT 2015
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
     * @mbggenerated Fri Mar 06 17:04:13 ICT 2015
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
     * @mbggenerated Fri Mar 06 17:04:13 ICT 2015
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
     * @mbggenerated Fri Mar 06 17:04:13 ICT 2015
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
     * @mbggenerated Fri Mar 06 17:04:13 ICT 2015
     */
    public void setFromemail(String fromemail) {
        this.fromemail = fromemail;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_relay_mail.emailHandlerBean
     *
     * @return the value of s_relay_mail.emailHandlerBean
     *
     * @mbggenerated Fri Mar 06 17:04:13 ICT 2015
     */
    public String getEmailhandlerbean() {
        return emailhandlerbean;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_relay_mail.emailHandlerBean
     *
     * @param emailhandlerbean the value for s_relay_mail.emailHandlerBean
     *
     * @mbggenerated Fri Mar 06 17:04:13 ICT 2015
     */
    public void setEmailhandlerbean(String emailhandlerbean) {
        this.emailhandlerbean = emailhandlerbean;
    }
}