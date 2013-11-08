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
/*Domain class of table m_prj_notifications*/
package com.esofthead.mycollab.module.project.domain;

import com.esofthead.mycollab.core.utils.ValuedBean;

public class ProjectNotificationSetting extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_notifications.id
     *
     * @mbggenerated Fri Sep 27 10:37:17 ICT 2013
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_notifications.username
     *
     * @mbggenerated Fri Sep 27 10:37:17 ICT 2013
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    private String username;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_notifications.projectId
     *
     * @mbggenerated Fri Sep 27 10:37:17 ICT 2013
     */
    private Integer projectid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_notifications.sAccountId
     *
     * @mbggenerated Fri Sep 27 10:37:17 ICT 2013
     */
    private Integer saccountid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_notifications.level
     *
     * @mbggenerated Fri Sep 27 10:37:17 ICT 2013
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    private String level;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_notifications.id
     *
     * @return the value of m_prj_notifications.id
     *
     * @mbggenerated Fri Sep 27 10:37:17 ICT 2013
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_notifications.id
     *
     * @param id the value for m_prj_notifications.id
     *
     * @mbggenerated Fri Sep 27 10:37:17 ICT 2013
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_notifications.username
     *
     * @return the value of m_prj_notifications.username
     *
     * @mbggenerated Fri Sep 27 10:37:17 ICT 2013
     */
    public String getUsername() {
        return username;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_notifications.username
     *
     * @param username the value for m_prj_notifications.username
     *
     * @mbggenerated Fri Sep 27 10:37:17 ICT 2013
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_notifications.projectId
     *
     * @return the value of m_prj_notifications.projectId
     *
     * @mbggenerated Fri Sep 27 10:37:17 ICT 2013
     */
    public Integer getProjectid() {
        return projectid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_notifications.projectId
     *
     * @param projectid the value for m_prj_notifications.projectId
     *
     * @mbggenerated Fri Sep 27 10:37:17 ICT 2013
     */
    public void setProjectid(Integer projectid) {
        this.projectid = projectid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_notifications.sAccountId
     *
     * @return the value of m_prj_notifications.sAccountId
     *
     * @mbggenerated Fri Sep 27 10:37:17 ICT 2013
     */
    public Integer getSaccountid() {
        return saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_notifications.sAccountId
     *
     * @param saccountid the value for m_prj_notifications.sAccountId
     *
     * @mbggenerated Fri Sep 27 10:37:17 ICT 2013
     */
    public void setSaccountid(Integer saccountid) {
        this.saccountid = saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_notifications.level
     *
     * @return the value of m_prj_notifications.level
     *
     * @mbggenerated Fri Sep 27 10:37:17 ICT 2013
     */
    public String getLevel() {
        return level;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_notifications.level
     *
     * @param level the value for m_prj_notifications.level
     *
     * @mbggenerated Fri Sep 27 10:37:17 ICT 2013
     */
    public void setLevel(String level) {
        this.level = level;
    }
}