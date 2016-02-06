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
/*Domain class of table s_customer_feedback*/
package com.esofthead.mycollab.common.domain;

import com.esofthead.mycollab.core.arguments.ValuedBean;
import com.esofthead.mycollab.core.db.metadata.Column;
import com.esofthead.mycollab.core.db.metadata.Table;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.Length;

@SuppressWarnings("ucd")
@Table("s_customer_feedback")
class CustomerFeedback extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_customer_feedback.id
     *
     * @mbggenerated Sat Feb 06 17:34:26 ICT 2016
     */
    @Column("id")
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_customer_feedback.sAccountId
     *
     * @mbggenerated Sat Feb 06 17:34:26 ICT 2016
     */
    @Column("sAccountId")
    private Integer saccountid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_customer_feedback.username
     *
     * @mbggenerated Sat Feb 06 17:34:26 ICT 2016
     */
    @Length(max=45, message="Field value is too long")
    @Column("username")
    private String username;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_customer_feedback.leaveType
     *
     * @mbggenerated Sat Feb 06 17:34:26 ICT 2016
     */
    @Column("leaveType")
    private Integer leavetype;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_customer_feedback.otherTool
     *
     * @mbggenerated Sat Feb 06 17:34:26 ICT 2016
     */
    @Length(max=400, message="Field value is too long")
    @Column("otherTool")
    private String othertool;

    private static final long serialVersionUID = 1;

    public final boolean equals(Object obj) {
        if (obj == null) { return false;}
        if (obj == this) { return true;}
        if (obj.getClass() != getClass()) { return false;}
        CustomerFeedback item = (CustomerFeedback)obj;
        return new EqualsBuilder().append(id, item.id).build();
    }

    public final int hashCode() {
        return new HashCodeBuilder(1615, 1207).append(id).build();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_customer_feedback.id
     *
     * @return the value of s_customer_feedback.id
     *
     * @mbggenerated Sat Feb 06 17:34:26 ICT 2016
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_customer_feedback.id
     *
     * @param id the value for s_customer_feedback.id
     *
     * @mbggenerated Sat Feb 06 17:34:26 ICT 2016
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_customer_feedback.sAccountId
     *
     * @return the value of s_customer_feedback.sAccountId
     *
     * @mbggenerated Sat Feb 06 17:34:26 ICT 2016
     */
    public Integer getSaccountid() {
        return saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_customer_feedback.sAccountId
     *
     * @param saccountid the value for s_customer_feedback.sAccountId
     *
     * @mbggenerated Sat Feb 06 17:34:26 ICT 2016
     */
    public void setSaccountid(Integer saccountid) {
        this.saccountid = saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_customer_feedback.username
     *
     * @return the value of s_customer_feedback.username
     *
     * @mbggenerated Sat Feb 06 17:34:26 ICT 2016
     */
    public String getUsername() {
        return username;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_customer_feedback.username
     *
     * @param username the value for s_customer_feedback.username
     *
     * @mbggenerated Sat Feb 06 17:34:26 ICT 2016
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_customer_feedback.leaveType
     *
     * @return the value of s_customer_feedback.leaveType
     *
     * @mbggenerated Sat Feb 06 17:34:26 ICT 2016
     */
    public Integer getLeavetype() {
        return leavetype;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_customer_feedback.leaveType
     *
     * @param leavetype the value for s_customer_feedback.leaveType
     *
     * @mbggenerated Sat Feb 06 17:34:26 ICT 2016
     */
    public void setLeavetype(Integer leavetype) {
        this.leavetype = leavetype;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_customer_feedback.otherTool
     *
     * @return the value of s_customer_feedback.otherTool
     *
     * @mbggenerated Sat Feb 06 17:34:26 ICT 2016
     */
    public String getOthertool() {
        return othertool;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_customer_feedback.otherTool
     *
     * @param othertool the value for s_customer_feedback.otherTool
     *
     * @mbggenerated Sat Feb 06 17:34:26 ICT 2016
     */
    public void setOthertool(String othertool) {
        this.othertool = othertool;
    }
}