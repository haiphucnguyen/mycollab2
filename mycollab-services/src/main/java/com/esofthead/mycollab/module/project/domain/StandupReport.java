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
/*Domain class of table m_prj_standup*/
package com.esofthead.mycollab.module.project.domain;

import com.esofthead.mycollab.core.arguments.ValuedBean;
import com.esofthead.mycollab.core.db.metadata.Column;
import com.esofthead.mycollab.core.db.metadata.Table;
import java.util.Date;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.Length;

@SuppressWarnings("ucd")
@Table("m_prj_standup")
class StandupReport extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_standup.id
     *
     * @mbggenerated Tue Sep 01 11:38:27 ICT 2015
     */
    @Column("id")
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_standup.sAccountId
     *
     * @mbggenerated Tue Sep 01 11:38:27 ICT 2015
     */
    @Column("sAccountId")
    private Integer saccountid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_standup.projectId
     *
     * @mbggenerated Tue Sep 01 11:38:27 ICT 2015
     */
    @Column("projectId")
    private Integer projectid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_standup.forday
     *
     * @mbggenerated Tue Sep 01 11:38:27 ICT 2015
     */
    @Column("forday")
    private Date forday;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_standup.logBy
     *
     * @mbggenerated Tue Sep 01 11:38:27 ICT 2015
     */
    @Length(max=45, message="Field value is too long")
    @Column("logBy")
    private String logby;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_standup.createdTime
     *
     * @mbggenerated Tue Sep 01 11:38:27 ICT 2015
     */
    @Column("createdTime")
    private Date createdtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_standup.lastUpdatedTime
     *
     * @mbggenerated Tue Sep 01 11:38:27 ICT 2015
     */
    @Column("lastUpdatedTime")
    private Date lastupdatedtime;

    private static final long serialVersionUID = 1;

    public final boolean equals(Object obj) {
        if (obj == null) { return false;}
        if (obj == this) { return true;}
        if (obj.getClass() != getClass()) { return false;}
        StandupReport item = (StandupReport)obj;
        return new EqualsBuilder().append(id, item.id).build();
    }

    public final int hashCode() {
        return new HashCodeBuilder(1605, 779).append(id).build();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_standup.id
     *
     * @return the value of m_prj_standup.id
     *
     * @mbggenerated Tue Sep 01 11:38:27 ICT 2015
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_standup.id
     *
     * @param id the value for m_prj_standup.id
     *
     * @mbggenerated Tue Sep 01 11:38:27 ICT 2015
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_standup.sAccountId
     *
     * @return the value of m_prj_standup.sAccountId
     *
     * @mbggenerated Tue Sep 01 11:38:27 ICT 2015
     */
    public Integer getSaccountid() {
        return saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_standup.sAccountId
     *
     * @param saccountid the value for m_prj_standup.sAccountId
     *
     * @mbggenerated Tue Sep 01 11:38:27 ICT 2015
     */
    public void setSaccountid(Integer saccountid) {
        this.saccountid = saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_standup.projectId
     *
     * @return the value of m_prj_standup.projectId
     *
     * @mbggenerated Tue Sep 01 11:38:27 ICT 2015
     */
    public Integer getProjectid() {
        return projectid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_standup.projectId
     *
     * @param projectid the value for m_prj_standup.projectId
     *
     * @mbggenerated Tue Sep 01 11:38:27 ICT 2015
     */
    public void setProjectid(Integer projectid) {
        this.projectid = projectid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_standup.forday
     *
     * @return the value of m_prj_standup.forday
     *
     * @mbggenerated Tue Sep 01 11:38:27 ICT 2015
     */
    public Date getForday() {
        return forday;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_standup.forday
     *
     * @param forday the value for m_prj_standup.forday
     *
     * @mbggenerated Tue Sep 01 11:38:27 ICT 2015
     */
    public void setForday(Date forday) {
        this.forday = forday;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_standup.logBy
     *
     * @return the value of m_prj_standup.logBy
     *
     * @mbggenerated Tue Sep 01 11:38:27 ICT 2015
     */
    public String getLogby() {
        return logby;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_standup.logBy
     *
     * @param logby the value for m_prj_standup.logBy
     *
     * @mbggenerated Tue Sep 01 11:38:27 ICT 2015
     */
    public void setLogby(String logby) {
        this.logby = logby;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_standup.createdTime
     *
     * @return the value of m_prj_standup.createdTime
     *
     * @mbggenerated Tue Sep 01 11:38:27 ICT 2015
     */
    public Date getCreatedtime() {
        return createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_standup.createdTime
     *
     * @param createdtime the value for m_prj_standup.createdTime
     *
     * @mbggenerated Tue Sep 01 11:38:27 ICT 2015
     */
    public void setCreatedtime(Date createdtime) {
        this.createdtime = createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_standup.lastUpdatedTime
     *
     * @return the value of m_prj_standup.lastUpdatedTime
     *
     * @mbggenerated Tue Sep 01 11:38:27 ICT 2015
     */
    public Date getLastupdatedtime() {
        return lastupdatedtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_standup.lastUpdatedTime
     *
     * @param lastupdatedtime the value for m_prj_standup.lastUpdatedTime
     *
     * @mbggenerated Tue Sep 01 11:38:27 ICT 2015
     */
    public void setLastupdatedtime(Date lastupdatedtime) {
        this.lastupdatedtime = lastupdatedtime;
    }
}