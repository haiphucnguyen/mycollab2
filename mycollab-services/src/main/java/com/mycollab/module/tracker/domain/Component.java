/**
 * mycollab-services - Parent pom providing dependency and plugin management for applications
		built with Maven
 * Copyright © 2017 MyCollab (support@mycollab.com)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
/*Domain class of table m_tracker_component*/
package com.mycollab.module.tracker.domain;

import com.mycollab.core.arguments.ValuedBean;
import com.mycollab.db.metadata.Column;
import com.mycollab.db.metadata.Table;
import java.util.Date;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.ibatis.type.Alias;
import org.hibernate.validator.constraints.Length;

@SuppressWarnings("ucd")
@Table("m_tracker_component")
@Alias("Component")
public class Component extends ValuedBean {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_tracker_component.id
     *
     * @mbg.generated Tue Aug 01 11:17:41 ICT 2017
     */
    @Column("id")
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_tracker_component.projectId
     *
     * @mbg.generated Tue Aug 01 11:17:41 ICT 2017
     */
    @Column("projectId")
    private Integer projectid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_tracker_component.name
     *
     * @mbg.generated Tue Aug 01 11:17:41 ICT 2017
     */
    @Length(max=1000, message="Field value is too long")
    @Column("name")
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_tracker_component.userlead
     *
     * @mbg.generated Tue Aug 01 11:17:41 ICT 2017
     */
    @Length(max=45, message="Field value is too long")
    @Column("userlead")
    private String userlead;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_tracker_component.createdUser
     *
     * @mbg.generated Tue Aug 01 11:17:41 ICT 2017
     */
    @Length(max=45, message="Field value is too long")
    @Column("createdUser")
    private String createduser;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_tracker_component.sAccountId
     *
     * @mbg.generated Tue Aug 01 11:17:41 ICT 2017
     */
    @Column("sAccountId")
    private Integer saccountid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_tracker_component.lastUpdatedTime
     *
     * @mbg.generated Tue Aug 01 11:17:41 ICT 2017
     */
    @Column("lastUpdatedTime")
    private Date lastupdatedtime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_tracker_component.createdTime
     *
     * @mbg.generated Tue Aug 01 11:17:41 ICT 2017
     */
    @Column("createdTime")
    private Date createdtime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_tracker_component.status
     *
     * @mbg.generated Tue Aug 01 11:17:41 ICT 2017
     */
    @Length(max=45, message="Field value is too long")
    @Column("status")
    private String status;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_tracker_component.prjKey
     *
     * @mbg.generated Tue Aug 01 11:17:41 ICT 2017
     */
    @Column("prjKey")
    private Integer prjkey;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_tracker_component.description
     *
     * @mbg.generated Tue Aug 01 11:17:41 ICT 2017
     */
    @Length(max=16777215, message="Field value is too long")
    @Column("description")
    private String description;

    private static final long serialVersionUID = 1;

    public final boolean equals(Object obj) {
        if (obj == null) { return false;}
        if (obj == this) { return true;}
        if (!obj.getClass().isAssignableFrom(getClass())) { return false;}
        Component item = (Component)obj;
        return new EqualsBuilder().append(id, item.id).build();
    }

    public final int hashCode() {
        return new HashCodeBuilder(1433, 861).append(id).build();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_tracker_component.id
     *
     * @return the value of m_tracker_component.id
     *
     * @mbg.generated Tue Aug 01 11:17:41 ICT 2017
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_tracker_component.id
     *
     * @param id the value for m_tracker_component.id
     *
     * @mbg.generated Tue Aug 01 11:17:41 ICT 2017
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_tracker_component.projectId
     *
     * @return the value of m_tracker_component.projectId
     *
     * @mbg.generated Tue Aug 01 11:17:41 ICT 2017
     */
    public Integer getProjectid() {
        return projectid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_tracker_component.projectId
     *
     * @param projectid the value for m_tracker_component.projectId
     *
     * @mbg.generated Tue Aug 01 11:17:41 ICT 2017
     */
    public void setProjectid(Integer projectid) {
        this.projectid = projectid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_tracker_component.name
     *
     * @return the value of m_tracker_component.name
     *
     * @mbg.generated Tue Aug 01 11:17:41 ICT 2017
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_tracker_component.name
     *
     * @param name the value for m_tracker_component.name
     *
     * @mbg.generated Tue Aug 01 11:17:41 ICT 2017
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_tracker_component.userlead
     *
     * @return the value of m_tracker_component.userlead
     *
     * @mbg.generated Tue Aug 01 11:17:41 ICT 2017
     */
    public String getUserlead() {
        return userlead;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_tracker_component.userlead
     *
     * @param userlead the value for m_tracker_component.userlead
     *
     * @mbg.generated Tue Aug 01 11:17:41 ICT 2017
     */
    public void setUserlead(String userlead) {
        this.userlead = userlead;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_tracker_component.createdUser
     *
     * @return the value of m_tracker_component.createdUser
     *
     * @mbg.generated Tue Aug 01 11:17:41 ICT 2017
     */
    public String getCreateduser() {
        return createduser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_tracker_component.createdUser
     *
     * @param createduser the value for m_tracker_component.createdUser
     *
     * @mbg.generated Tue Aug 01 11:17:41 ICT 2017
     */
    public void setCreateduser(String createduser) {
        this.createduser = createduser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_tracker_component.sAccountId
     *
     * @return the value of m_tracker_component.sAccountId
     *
     * @mbg.generated Tue Aug 01 11:17:41 ICT 2017
     */
    public Integer getSaccountid() {
        return saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_tracker_component.sAccountId
     *
     * @param saccountid the value for m_tracker_component.sAccountId
     *
     * @mbg.generated Tue Aug 01 11:17:41 ICT 2017
     */
    public void setSaccountid(Integer saccountid) {
        this.saccountid = saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_tracker_component.lastUpdatedTime
     *
     * @return the value of m_tracker_component.lastUpdatedTime
     *
     * @mbg.generated Tue Aug 01 11:17:41 ICT 2017
     */
    public Date getLastupdatedtime() {
        return lastupdatedtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_tracker_component.lastUpdatedTime
     *
     * @param lastupdatedtime the value for m_tracker_component.lastUpdatedTime
     *
     * @mbg.generated Tue Aug 01 11:17:41 ICT 2017
     */
    public void setLastupdatedtime(Date lastupdatedtime) {
        this.lastupdatedtime = lastupdatedtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_tracker_component.createdTime
     *
     * @return the value of m_tracker_component.createdTime
     *
     * @mbg.generated Tue Aug 01 11:17:41 ICT 2017
     */
    public Date getCreatedtime() {
        return createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_tracker_component.createdTime
     *
     * @param createdtime the value for m_tracker_component.createdTime
     *
     * @mbg.generated Tue Aug 01 11:17:41 ICT 2017
     */
    public void setCreatedtime(Date createdtime) {
        this.createdtime = createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_tracker_component.status
     *
     * @return the value of m_tracker_component.status
     *
     * @mbg.generated Tue Aug 01 11:17:41 ICT 2017
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_tracker_component.status
     *
     * @param status the value for m_tracker_component.status
     *
     * @mbg.generated Tue Aug 01 11:17:41 ICT 2017
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_tracker_component.prjKey
     *
     * @return the value of m_tracker_component.prjKey
     *
     * @mbg.generated Tue Aug 01 11:17:41 ICT 2017
     */
    public Integer getPrjkey() {
        return prjkey;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_tracker_component.prjKey
     *
     * @param prjkey the value for m_tracker_component.prjKey
     *
     * @mbg.generated Tue Aug 01 11:17:41 ICT 2017
     */
    public void setPrjkey(Integer prjkey) {
        this.prjkey = prjkey;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_tracker_component.description
     *
     * @return the value of m_tracker_component.description
     *
     * @mbg.generated Tue Aug 01 11:17:41 ICT 2017
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_tracker_component.description
     *
     * @param description the value for m_tracker_component.description
     *
     * @mbg.generated Tue Aug 01 11:17:41 ICT 2017
     */
    public void setDescription(String description) {
        this.description = description;
    }

    public enum Field {
        id,
        projectid,
        name,
        userlead,
        createduser,
        saccountid,
        lastupdatedtime,
        createdtime,
        status,
        prjkey,
        description;

        public boolean equalTo(Object value) {
            return name().equals(value);
        }
    }
}