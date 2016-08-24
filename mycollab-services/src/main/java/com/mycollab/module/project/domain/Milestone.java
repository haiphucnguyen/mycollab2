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
/*Domain class of table m_prj_milestone*/
package com.mycollab.module.project.domain;

import com.mycollab.core.arguments.ValuedBean;
import com.mycollab.db.metadata.Column;
import com.mycollab.db.metadata.Table;
import java.util.Date;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.Length;

@SuppressWarnings("ucd")
@Table("m_prj_milestone")
public class Milestone extends ValuedBean {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_milestone.id
     *
     * @mbggenerated Wed Aug 24 11:44:39 ICT 2016
     */
    @Column("id")
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_milestone.name
     *
     * @mbggenerated Wed Aug 24 11:44:39 ICT 2016
     */
    @Length(max=255, message="Field value is too long")
    @Column("name")
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_milestone.startdate
     *
     * @mbggenerated Wed Aug 24 11:44:39 ICT 2016
     */
    @Column("startdate")
    private Date startdate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_milestone.enddate
     *
     * @mbggenerated Wed Aug 24 11:44:39 ICT 2016
     */
    @Column("enddate")
    private Date enddate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_milestone.owner
     *
     * @mbggenerated Wed Aug 24 11:44:39 ICT 2016
     */
    @Length(max=45, message="Field value is too long")
    @Column("owner")
    private String owner;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_milestone.flag
     *
     * @mbggenerated Wed Aug 24 11:44:39 ICT 2016
     */
    @Length(max=45, message="Field value is too long")
    @Column("flag")
    private String flag;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_milestone.projectId
     *
     * @mbggenerated Wed Aug 24 11:44:39 ICT 2016
     */
    @Column("projectId")
    private Integer projectid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_milestone.createdTime
     *
     * @mbggenerated Wed Aug 24 11:44:39 ICT 2016
     */
    @Column("createdTime")
    private Date createdtime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_milestone.lastUpdatedTime
     *
     * @mbggenerated Wed Aug 24 11:44:39 ICT 2016
     */
    @Column("lastUpdatedTime")
    private Date lastupdatedtime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_milestone.sAccountId
     *
     * @mbggenerated Wed Aug 24 11:44:39 ICT 2016
     */
    @Column("sAccountId")
    private Integer saccountid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_milestone.status
     *
     * @mbggenerated Wed Aug 24 11:44:39 ICT 2016
     */
    @Length(max=45, message="Field value is too long")
    @Column("status")
    private String status;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_milestone.createduser
     *
     * @mbggenerated Wed Aug 24 11:44:39 ICT 2016
     */
    @Length(max=45, message="Field value is too long")
    @Column("createduser")
    private String createduser;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_milestone.prjKey
     *
     * @mbggenerated Wed Aug 24 11:44:39 ICT 2016
     */
    @Column("prjKey")
    private Integer prjkey;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_milestone.deadline
     *
     * @mbggenerated Wed Aug 24 11:44:39 ICT 2016
     */
    @Column("deadline")
    private Date deadline;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_milestone.ganttIndex
     *
     * @mbggenerated Wed Aug 24 11:44:39 ICT 2016
     */
    @Column("ganttIndex")
    private Integer ganttindex;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_milestone.description
     *
     * @mbggenerated Wed Aug 24 11:44:39 ICT 2016
     */
    @Length(max=16777215, message="Field value is too long")
    @Column("description")
    private String description;

    private static final long serialVersionUID = 1;

    public final boolean equals(Object obj) {
        if (obj == null) { return false;}
        if (obj == this) { return true;}
        if (!obj.getClass().isAssignableFrom(getClass())) { return false;}
        Milestone item = (Milestone)obj;
        return new EqualsBuilder().append(id, item.id).build();
    }

    public final int hashCode() {
        return new HashCodeBuilder(1599, 595).append(id).build();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_milestone.id
     *
     * @return the value of m_prj_milestone.id
     *
     * @mbggenerated Wed Aug 24 11:44:39 ICT 2016
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_milestone.id
     *
     * @param id the value for m_prj_milestone.id
     *
     * @mbggenerated Wed Aug 24 11:44:39 ICT 2016
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_milestone.name
     *
     * @return the value of m_prj_milestone.name
     *
     * @mbggenerated Wed Aug 24 11:44:39 ICT 2016
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_milestone.name
     *
     * @param name the value for m_prj_milestone.name
     *
     * @mbggenerated Wed Aug 24 11:44:39 ICT 2016
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_milestone.startdate
     *
     * @return the value of m_prj_milestone.startdate
     *
     * @mbggenerated Wed Aug 24 11:44:39 ICT 2016
     */
    public Date getStartdate() {
        return startdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_milestone.startdate
     *
     * @param startdate the value for m_prj_milestone.startdate
     *
     * @mbggenerated Wed Aug 24 11:44:39 ICT 2016
     */
    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_milestone.enddate
     *
     * @return the value of m_prj_milestone.enddate
     *
     * @mbggenerated Wed Aug 24 11:44:39 ICT 2016
     */
    public Date getEnddate() {
        return enddate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_milestone.enddate
     *
     * @param enddate the value for m_prj_milestone.enddate
     *
     * @mbggenerated Wed Aug 24 11:44:39 ICT 2016
     */
    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_milestone.owner
     *
     * @return the value of m_prj_milestone.owner
     *
     * @mbggenerated Wed Aug 24 11:44:39 ICT 2016
     */
    public String getOwner() {
        return owner;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_milestone.owner
     *
     * @param owner the value for m_prj_milestone.owner
     *
     * @mbggenerated Wed Aug 24 11:44:39 ICT 2016
     */
    public void setOwner(String owner) {
        this.owner = owner;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_milestone.flag
     *
     * @return the value of m_prj_milestone.flag
     *
     * @mbggenerated Wed Aug 24 11:44:39 ICT 2016
     */
    public String getFlag() {
        return flag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_milestone.flag
     *
     * @param flag the value for m_prj_milestone.flag
     *
     * @mbggenerated Wed Aug 24 11:44:39 ICT 2016
     */
    public void setFlag(String flag) {
        this.flag = flag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_milestone.projectId
     *
     * @return the value of m_prj_milestone.projectId
     *
     * @mbggenerated Wed Aug 24 11:44:39 ICT 2016
     */
    public Integer getProjectid() {
        return projectid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_milestone.projectId
     *
     * @param projectid the value for m_prj_milestone.projectId
     *
     * @mbggenerated Wed Aug 24 11:44:39 ICT 2016
     */
    public void setProjectid(Integer projectid) {
        this.projectid = projectid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_milestone.createdTime
     *
     * @return the value of m_prj_milestone.createdTime
     *
     * @mbggenerated Wed Aug 24 11:44:39 ICT 2016
     */
    public Date getCreatedtime() {
        return createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_milestone.createdTime
     *
     * @param createdtime the value for m_prj_milestone.createdTime
     *
     * @mbggenerated Wed Aug 24 11:44:39 ICT 2016
     */
    public void setCreatedtime(Date createdtime) {
        this.createdtime = createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_milestone.lastUpdatedTime
     *
     * @return the value of m_prj_milestone.lastUpdatedTime
     *
     * @mbggenerated Wed Aug 24 11:44:39 ICT 2016
     */
    public Date getLastupdatedtime() {
        return lastupdatedtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_milestone.lastUpdatedTime
     *
     * @param lastupdatedtime the value for m_prj_milestone.lastUpdatedTime
     *
     * @mbggenerated Wed Aug 24 11:44:39 ICT 2016
     */
    public void setLastupdatedtime(Date lastupdatedtime) {
        this.lastupdatedtime = lastupdatedtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_milestone.sAccountId
     *
     * @return the value of m_prj_milestone.sAccountId
     *
     * @mbggenerated Wed Aug 24 11:44:39 ICT 2016
     */
    public Integer getSaccountid() {
        return saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_milestone.sAccountId
     *
     * @param saccountid the value for m_prj_milestone.sAccountId
     *
     * @mbggenerated Wed Aug 24 11:44:39 ICT 2016
     */
    public void setSaccountid(Integer saccountid) {
        this.saccountid = saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_milestone.status
     *
     * @return the value of m_prj_milestone.status
     *
     * @mbggenerated Wed Aug 24 11:44:39 ICT 2016
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_milestone.status
     *
     * @param status the value for m_prj_milestone.status
     *
     * @mbggenerated Wed Aug 24 11:44:39 ICT 2016
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_milestone.createduser
     *
     * @return the value of m_prj_milestone.createduser
     *
     * @mbggenerated Wed Aug 24 11:44:39 ICT 2016
     */
    public String getCreateduser() {
        return createduser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_milestone.createduser
     *
     * @param createduser the value for m_prj_milestone.createduser
     *
     * @mbggenerated Wed Aug 24 11:44:39 ICT 2016
     */
    public void setCreateduser(String createduser) {
        this.createduser = createduser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_milestone.prjKey
     *
     * @return the value of m_prj_milestone.prjKey
     *
     * @mbggenerated Wed Aug 24 11:44:39 ICT 2016
     */
    public Integer getPrjkey() {
        return prjkey;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_milestone.prjKey
     *
     * @param prjkey the value for m_prj_milestone.prjKey
     *
     * @mbggenerated Wed Aug 24 11:44:39 ICT 2016
     */
    public void setPrjkey(Integer prjkey) {
        this.prjkey = prjkey;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_milestone.deadline
     *
     * @return the value of m_prj_milestone.deadline
     *
     * @mbggenerated Wed Aug 24 11:44:39 ICT 2016
     */
    public Date getDeadline() {
        return deadline;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_milestone.deadline
     *
     * @param deadline the value for m_prj_milestone.deadline
     *
     * @mbggenerated Wed Aug 24 11:44:39 ICT 2016
     */
    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_milestone.ganttIndex
     *
     * @return the value of m_prj_milestone.ganttIndex
     *
     * @mbggenerated Wed Aug 24 11:44:39 ICT 2016
     */
    public Integer getGanttindex() {
        return ganttindex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_milestone.ganttIndex
     *
     * @param ganttindex the value for m_prj_milestone.ganttIndex
     *
     * @mbggenerated Wed Aug 24 11:44:39 ICT 2016
     */
    public void setGanttindex(Integer ganttindex) {
        this.ganttindex = ganttindex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_milestone.description
     *
     * @return the value of m_prj_milestone.description
     *
     * @mbggenerated Wed Aug 24 11:44:39 ICT 2016
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_milestone.description
     *
     * @param description the value for m_prj_milestone.description
     *
     * @mbggenerated Wed Aug 24 11:44:39 ICT 2016
     */
    public void setDescription(String description) {
        this.description = description;
    }

    public enum Field {
        id,
        name,
        startdate,
        enddate,
        owner,
        flag,
        projectid,
        createdtime,
        lastupdatedtime,
        saccountid,
        status,
        createduser,
        prjkey,
        deadline,
        ganttindex,
        description;

        public boolean equalTo(Object value) {
            return name().equals(value);
        }
    }
}