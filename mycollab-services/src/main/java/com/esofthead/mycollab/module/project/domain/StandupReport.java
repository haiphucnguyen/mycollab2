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

import com.esofthead.mycollab.core.utils.ValuedBean;
import java.util.Date;

class StandupReport extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_standup.id
     *
     * @mbggenerated Fri Sep 27 10:37:17 ICT 2013
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_standup.sAccountId
     *
     * @mbggenerated Fri Sep 27 10:37:17 ICT 2013
     */
    private Integer saccountid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_standup.projectId
     *
     * @mbggenerated Fri Sep 27 10:37:17 ICT 2013
     */
    private Integer projectid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_standup.forday
     *
     * @mbggenerated Fri Sep 27 10:37:17 ICT 2013
     */
    private Date forday;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_standup.logBy
     *
     * @mbggenerated Fri Sep 27 10:37:17 ICT 2013
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    private String logby;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_standup.createdTime
     *
     * @mbggenerated Fri Sep 27 10:37:17 ICT 2013
     */
    private Date createdtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_standup.lastUpdatedTime
     *
     * @mbggenerated Fri Sep 27 10:37:17 ICT 2013
     */
    private Date lastupdatedtime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_standup.id
     *
     * @return the value of m_prj_standup.id
     *
     * @mbggenerated Fri Sep 27 10:37:17 ICT 2013
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
     * @mbggenerated Fri Sep 27 10:37:17 ICT 2013
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
     * @mbggenerated Fri Sep 27 10:37:17 ICT 2013
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
     * @mbggenerated Fri Sep 27 10:37:17 ICT 2013
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
     * @mbggenerated Fri Sep 27 10:37:17 ICT 2013
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
     * @mbggenerated Fri Sep 27 10:37:17 ICT 2013
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
     * @mbggenerated Fri Sep 27 10:37:17 ICT 2013
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
     * @mbggenerated Fri Sep 27 10:37:17 ICT 2013
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
     * @mbggenerated Fri Sep 27 10:37:17 ICT 2013
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
     * @mbggenerated Fri Sep 27 10:37:17 ICT 2013
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
     * @mbggenerated Fri Sep 27 10:37:17 ICT 2013
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
     * @mbggenerated Fri Sep 27 10:37:17 ICT 2013
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
     * @mbggenerated Fri Sep 27 10:37:17 ICT 2013
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
     * @mbggenerated Fri Sep 27 10:37:17 ICT 2013
     */
    public void setLastupdatedtime(Date lastupdatedtime) {
        this.lastupdatedtime = lastupdatedtime;
    }
}