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
/*Domain class of table m_prj_member*/
package com.esofthead.mycollab.module.project.domain;

import com.esofthead.mycollab.core.arguments.ValuedBean;
import java.util.Date;

@SuppressWarnings("ucd")
@com.esofthead.mycollab.core.db.metadata.Table("m_prj_member")
public class ProjectMember extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_member.id
     *
     * @mbggenerated Sun Jul 20 11:11:56 ICT 2014
     */
    @com.esofthead.mycollab.core.db.metadata.Column("id")
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_member.username
     *
     * @mbggenerated Sun Jul 20 11:11:56 ICT 2014
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    @com.esofthead.mycollab.core.db.metadata.Column("username")
    private String username;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_member.projectId
     *
     * @mbggenerated Sun Jul 20 11:11:56 ICT 2014
     */
    @com.esofthead.mycollab.core.db.metadata.Column("projectId")
    private Integer projectid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_member.joinDate
     *
     * @mbggenerated Sun Jul 20 11:11:56 ICT 2014
     */
    @com.esofthead.mycollab.core.db.metadata.Column("joinDate")
    private Date joindate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_member.projectRoleId
     *
     * @mbggenerated Sun Jul 20 11:11:56 ICT 2014
     */
    @com.esofthead.mycollab.core.db.metadata.Column("projectRoleId")
    private Integer projectroleid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_member.isAdmin
     *
     * @mbggenerated Sun Jul 20 11:11:56 ICT 2014
     */
    @com.esofthead.mycollab.core.db.metadata.Column("isAdmin")
    private Boolean isadmin;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_member.status
     *
     * @mbggenerated Sun Jul 20 11:11:56 ICT 2014
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    @com.esofthead.mycollab.core.db.metadata.Column("status")
    private String status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_member.sAccountId
     *
     * @mbggenerated Sun Jul 20 11:11:56 ICT 2014
     */
    @com.esofthead.mycollab.core.db.metadata.Column("sAccountId")
    private Integer saccountid;

    private static final long serialVersionUID = 1;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_member.id
     *
     * @return the value of m_prj_member.id
     *
     * @mbggenerated Sun Jul 20 11:11:56 ICT 2014
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_member.id
     *
     * @param id the value for m_prj_member.id
     *
     * @mbggenerated Sun Jul 20 11:11:56 ICT 2014
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_member.username
     *
     * @return the value of m_prj_member.username
     *
     * @mbggenerated Sun Jul 20 11:11:56 ICT 2014
     */
    public String getUsername() {
        return username;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_member.username
     *
     * @param username the value for m_prj_member.username
     *
     * @mbggenerated Sun Jul 20 11:11:56 ICT 2014
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_member.projectId
     *
     * @return the value of m_prj_member.projectId
     *
     * @mbggenerated Sun Jul 20 11:11:56 ICT 2014
     */
    public Integer getProjectid() {
        return projectid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_member.projectId
     *
     * @param projectid the value for m_prj_member.projectId
     *
     * @mbggenerated Sun Jul 20 11:11:56 ICT 2014
     */
    public void setProjectid(Integer projectid) {
        this.projectid = projectid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_member.joinDate
     *
     * @return the value of m_prj_member.joinDate
     *
     * @mbggenerated Sun Jul 20 11:11:56 ICT 2014
     */
    public Date getJoindate() {
        return joindate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_member.joinDate
     *
     * @param joindate the value for m_prj_member.joinDate
     *
     * @mbggenerated Sun Jul 20 11:11:56 ICT 2014
     */
    public void setJoindate(Date joindate) {
        this.joindate = joindate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_member.projectRoleId
     *
     * @return the value of m_prj_member.projectRoleId
     *
     * @mbggenerated Sun Jul 20 11:11:56 ICT 2014
     */
    public Integer getProjectroleid() {
        return projectroleid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_member.projectRoleId
     *
     * @param projectroleid the value for m_prj_member.projectRoleId
     *
     * @mbggenerated Sun Jul 20 11:11:56 ICT 2014
     */
    public void setProjectroleid(Integer projectroleid) {
        this.projectroleid = projectroleid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_member.isAdmin
     *
     * @return the value of m_prj_member.isAdmin
     *
     * @mbggenerated Sun Jul 20 11:11:56 ICT 2014
     */
    public Boolean getIsadmin() {
        return isadmin;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_member.isAdmin
     *
     * @param isadmin the value for m_prj_member.isAdmin
     *
     * @mbggenerated Sun Jul 20 11:11:56 ICT 2014
     */
    public void setIsadmin(Boolean isadmin) {
        this.isadmin = isadmin;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_member.status
     *
     * @return the value of m_prj_member.status
     *
     * @mbggenerated Sun Jul 20 11:11:56 ICT 2014
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_member.status
     *
     * @param status the value for m_prj_member.status
     *
     * @mbggenerated Sun Jul 20 11:11:56 ICT 2014
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_member.sAccountId
     *
     * @return the value of m_prj_member.sAccountId
     *
     * @mbggenerated Sun Jul 20 11:11:56 ICT 2014
     */
    public Integer getSaccountid() {
        return saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_member.sAccountId
     *
     * @param saccountid the value for m_prj_member.sAccountId
     *
     * @mbggenerated Sun Jul 20 11:11:56 ICT 2014
     */
    public void setSaccountid(Integer saccountid) {
        this.saccountid = saccountid;
    }
}