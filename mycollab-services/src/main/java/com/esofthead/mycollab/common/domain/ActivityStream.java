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
/*Domain class of table s_activitystream*/
package com.esofthead.mycollab.common.domain;

import com.esofthead.mycollab.core.arguments.ValuedBean;
import com.esofthead.mycollab.core.db.metadata.Column;
import com.esofthead.mycollab.core.db.metadata.Table;
import java.util.Date;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.Length;

@SuppressWarnings("ucd")
@Table("s_activitystream")
class ActivityStream extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_activitystream.id
     *
     * @mbggenerated Wed Oct 28 16:06:51 ICT 2015
     */
    @Column("id")
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_activitystream.sAccountId
     *
     * @mbggenerated Wed Oct 28 16:06:51 ICT 2015
     */
    @Column("sAccountId")
    private Integer saccountid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_activitystream.type
     *
     * @mbggenerated Wed Oct 28 16:06:51 ICT 2015
     */
    @Length(max=45, message="Field value is too long")
    @Column("type")
    private String type;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_activitystream.createdTime
     *
     * @mbggenerated Wed Oct 28 16:06:51 ICT 2015
     */
    @Column("createdTime")
    private Date createdtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_activitystream.action
     *
     * @mbggenerated Wed Oct 28 16:06:51 ICT 2015
     */
    @Length(max=45, message="Field value is too long")
    @Column("action")
    private String action;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_activitystream.createdUser
     *
     * @mbggenerated Wed Oct 28 16:06:51 ICT 2015
     */
    @Length(max=45, message="Field value is too long")
    @Column("createdUser")
    private String createduser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_activitystream.module
     *
     * @mbggenerated Wed Oct 28 16:06:51 ICT 2015
     */
    @Length(max=45, message="Field value is too long")
    @Column("module")
    private String module;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_activitystream.extraTypeId
     *
     * @mbggenerated Wed Oct 28 16:06:51 ICT 2015
     */
    @Column("extraTypeId")
    private Integer extratypeid;

    private static final long serialVersionUID = 1;

    public final boolean equals(Object obj) {
        if (obj == null) { return false;}
        if (obj == this) { return true;}
        if (obj.getClass() != getClass()) { return false;}
        ActivityStream item = (ActivityStream)obj;
        return new EqualsBuilder().append(id, item.id).build();
    }

    public final int hashCode() {
        return new HashCodeBuilder(1407, 191).append(id).build();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_activitystream.id
     *
     * @return the value of s_activitystream.id
     *
     * @mbggenerated Wed Oct 28 16:06:51 ICT 2015
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_activitystream.id
     *
     * @param id the value for s_activitystream.id
     *
     * @mbggenerated Wed Oct 28 16:06:51 ICT 2015
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_activitystream.sAccountId
     *
     * @return the value of s_activitystream.sAccountId
     *
     * @mbggenerated Wed Oct 28 16:06:51 ICT 2015
     */
    public Integer getSaccountid() {
        return saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_activitystream.sAccountId
     *
     * @param saccountid the value for s_activitystream.sAccountId
     *
     * @mbggenerated Wed Oct 28 16:06:51 ICT 2015
     */
    public void setSaccountid(Integer saccountid) {
        this.saccountid = saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_activitystream.type
     *
     * @return the value of s_activitystream.type
     *
     * @mbggenerated Wed Oct 28 16:06:51 ICT 2015
     */
    public String getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_activitystream.type
     *
     * @param type the value for s_activitystream.type
     *
     * @mbggenerated Wed Oct 28 16:06:51 ICT 2015
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_activitystream.createdTime
     *
     * @return the value of s_activitystream.createdTime
     *
     * @mbggenerated Wed Oct 28 16:06:51 ICT 2015
     */
    public Date getCreatedtime() {
        return createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_activitystream.createdTime
     *
     * @param createdtime the value for s_activitystream.createdTime
     *
     * @mbggenerated Wed Oct 28 16:06:51 ICT 2015
     */
    public void setCreatedtime(Date createdtime) {
        this.createdtime = createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_activitystream.action
     *
     * @return the value of s_activitystream.action
     *
     * @mbggenerated Wed Oct 28 16:06:51 ICT 2015
     */
    public String getAction() {
        return action;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_activitystream.action
     *
     * @param action the value for s_activitystream.action
     *
     * @mbggenerated Wed Oct 28 16:06:51 ICT 2015
     */
    public void setAction(String action) {
        this.action = action;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_activitystream.createdUser
     *
     * @return the value of s_activitystream.createdUser
     *
     * @mbggenerated Wed Oct 28 16:06:51 ICT 2015
     */
    public String getCreateduser() {
        return createduser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_activitystream.createdUser
     *
     * @param createduser the value for s_activitystream.createdUser
     *
     * @mbggenerated Wed Oct 28 16:06:51 ICT 2015
     */
    public void setCreateduser(String createduser) {
        this.createduser = createduser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_activitystream.module
     *
     * @return the value of s_activitystream.module
     *
     * @mbggenerated Wed Oct 28 16:06:51 ICT 2015
     */
    public String getModule() {
        return module;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_activitystream.module
     *
     * @param module the value for s_activitystream.module
     *
     * @mbggenerated Wed Oct 28 16:06:51 ICT 2015
     */
    public void setModule(String module) {
        this.module = module;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_activitystream.extraTypeId
     *
     * @return the value of s_activitystream.extraTypeId
     *
     * @mbggenerated Wed Oct 28 16:06:51 ICT 2015
     */
    public Integer getExtratypeid() {
        return extratypeid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_activitystream.extraTypeId
     *
     * @param extratypeid the value for s_activitystream.extraTypeId
     *
     * @mbggenerated Wed Oct 28 16:06:51 ICT 2015
     */
    public void setExtratypeid(Integer extratypeid) {
        this.extratypeid = extratypeid;
    }
}