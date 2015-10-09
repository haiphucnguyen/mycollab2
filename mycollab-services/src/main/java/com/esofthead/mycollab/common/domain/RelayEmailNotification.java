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
/*Domain class of table s_relay_email_notification*/
package com.esofthead.mycollab.common.domain;

import com.esofthead.mycollab.core.arguments.ValuedBean;
import com.esofthead.mycollab.core.db.metadata.Column;
import com.esofthead.mycollab.core.db.metadata.Table;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.Length;

@SuppressWarnings("ucd")
@Table("s_relay_email_notification")
class RelayEmailNotification extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_relay_email_notification.id
     *
     * @mbggenerated Fri Oct 09 11:22:06 ICT 2015
     */
    @Column("id")
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_relay_email_notification.sAccountId
     *
     * @mbggenerated Fri Oct 09 11:22:06 ICT 2015
     */
    @Column("sAccountId")
    private Integer saccountid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_relay_email_notification.type
     *
     * @mbggenerated Fri Oct 09 11:22:06 ICT 2015
     */
    @Length(max=45, message="Field value is too long")
    @Column("type")
    private String type;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_relay_email_notification.action
     *
     * @mbggenerated Fri Oct 09 11:22:06 ICT 2015
     */
    @Length(max=45, message="Field value is too long")
    @Column("action")
    private String action;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_relay_email_notification.changeBy
     *
     * @mbggenerated Fri Oct 09 11:22:06 ICT 2015
     */
    @Length(max=45, message="Field value is too long")
    @Column("changeBy")
    private String changeby;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_relay_email_notification.extraTypeId
     *
     * @mbggenerated Fri Oct 09 11:22:06 ICT 2015
     */
    @Column("extraTypeId")
    private Integer extratypeid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_relay_email_notification.emailHandlerBean
     *
     * @mbggenerated Fri Oct 09 11:22:06 ICT 2015
     */
    @Length(max=400, message="Field value is too long")
    @Column("emailHandlerBean")
    private String emailhandlerbean;

    private static final long serialVersionUID = 1;

    public final boolean equals(Object obj) {
        if (obj == null) { return false;}
        if (obj == this) { return true;}
        if (obj.getClass() != getClass()) { return false;}
        RelayEmailNotification item = (RelayEmailNotification)obj;
        return new EqualsBuilder().append(id, item.id).build();
    }

    public final int hashCode() {
        return new HashCodeBuilder(445, 1399).append(id).build();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_relay_email_notification.id
     *
     * @return the value of s_relay_email_notification.id
     *
     * @mbggenerated Fri Oct 09 11:22:06 ICT 2015
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_relay_email_notification.id
     *
     * @param id the value for s_relay_email_notification.id
     *
     * @mbggenerated Fri Oct 09 11:22:06 ICT 2015
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_relay_email_notification.sAccountId
     *
     * @return the value of s_relay_email_notification.sAccountId
     *
     * @mbggenerated Fri Oct 09 11:22:06 ICT 2015
     */
    public Integer getSaccountid() {
        return saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_relay_email_notification.sAccountId
     *
     * @param saccountid the value for s_relay_email_notification.sAccountId
     *
     * @mbggenerated Fri Oct 09 11:22:06 ICT 2015
     */
    public void setSaccountid(Integer saccountid) {
        this.saccountid = saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_relay_email_notification.type
     *
     * @return the value of s_relay_email_notification.type
     *
     * @mbggenerated Fri Oct 09 11:22:06 ICT 2015
     */
    public String getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_relay_email_notification.type
     *
     * @param type the value for s_relay_email_notification.type
     *
     * @mbggenerated Fri Oct 09 11:22:06 ICT 2015
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_relay_email_notification.action
     *
     * @return the value of s_relay_email_notification.action
     *
     * @mbggenerated Fri Oct 09 11:22:06 ICT 2015
     */
    public String getAction() {
        return action;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_relay_email_notification.action
     *
     * @param action the value for s_relay_email_notification.action
     *
     * @mbggenerated Fri Oct 09 11:22:06 ICT 2015
     */
    public void setAction(String action) {
        this.action = action;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_relay_email_notification.changeBy
     *
     * @return the value of s_relay_email_notification.changeBy
     *
     * @mbggenerated Fri Oct 09 11:22:06 ICT 2015
     */
    public String getChangeby() {
        return changeby;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_relay_email_notification.changeBy
     *
     * @param changeby the value for s_relay_email_notification.changeBy
     *
     * @mbggenerated Fri Oct 09 11:22:06 ICT 2015
     */
    public void setChangeby(String changeby) {
        this.changeby = changeby;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_relay_email_notification.extraTypeId
     *
     * @return the value of s_relay_email_notification.extraTypeId
     *
     * @mbggenerated Fri Oct 09 11:22:06 ICT 2015
     */
    public Integer getExtratypeid() {
        return extratypeid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_relay_email_notification.extraTypeId
     *
     * @param extratypeid the value for s_relay_email_notification.extraTypeId
     *
     * @mbggenerated Fri Oct 09 11:22:06 ICT 2015
     */
    public void setExtratypeid(Integer extratypeid) {
        this.extratypeid = extratypeid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_relay_email_notification.emailHandlerBean
     *
     * @return the value of s_relay_email_notification.emailHandlerBean
     *
     * @mbggenerated Fri Oct 09 11:22:06 ICT 2015
     */
    public String getEmailhandlerbean() {
        return emailhandlerbean;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_relay_email_notification.emailHandlerBean
     *
     * @param emailhandlerbean the value for s_relay_email_notification.emailHandlerBean
     *
     * @mbggenerated Fri Oct 09 11:22:06 ICT 2015
     */
    public void setEmailhandlerbean(String emailhandlerbean) {
        this.emailhandlerbean = emailhandlerbean;
    }
}