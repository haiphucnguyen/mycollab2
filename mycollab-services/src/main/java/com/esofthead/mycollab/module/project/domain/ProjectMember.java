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
import com.esofthead.mycollab.core.db.metadata.Column;
import com.esofthead.mycollab.core.db.metadata.Table;
import java.util.Date;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.Length;

@SuppressWarnings("ucd")
@Table("m_prj_member")
public class ProjectMember extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_member.id
     *
     * @mbggenerated Tue Aug 11 12:50:57 ICT 2015
     */
    @Column("id")
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_member.username
     *
     * @mbggenerated Tue Aug 11 12:50:57 ICT 2015
     */
    @Length(max=45, message="Field value is too long")
    @Column("username")
    private String username;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_member.projectId
     *
     * @mbggenerated Tue Aug 11 12:50:57 ICT 2015
     */
    @Column("projectId")
    private Integer projectid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_member.joinDate
     *
     * @mbggenerated Tue Aug 11 12:50:57 ICT 2015
     */
    @Column("joinDate")
    private Date joindate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_member.projectRoleId
     *
     * @mbggenerated Tue Aug 11 12:50:57 ICT 2015
     */
    @Column("projectRoleId")
    private Integer projectroleid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_member.isAdmin
     *
     * @mbggenerated Tue Aug 11 12:50:57 ICT 2015
     */
    @Column("isAdmin")
    private Boolean isadmin;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_member.status
     *
     * @mbggenerated Tue Aug 11 12:50:57 ICT 2015
     */
    @Length(max=45, message="Field value is too long")
    @Column("status")
    private String status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_member.sAccountId
     *
     * @mbggenerated Tue Aug 11 12:50:57 ICT 2015
     */
    @Column("sAccountId")
    private Integer saccountid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_member.billingRate
     *
     * @mbggenerated Tue Aug 11 12:50:57 ICT 2015
     */
    @Column("billingRate")
    private Double billingrate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_member.overtimeBillingRate
     *
     * @mbggenerated Tue Aug 11 12:50:57 ICT 2015
     */
    @Column("overtimeBillingRate")
    private Double overtimebillingrate;

    private static final long serialVersionUID = 1;

    public final boolean equals(Object obj) {
        if (obj == null) { return false;}
        if (obj == this) { return true;}
        if (obj.getClass() != getClass()) { return false;}
        ProjectMember item = (ProjectMember)obj;
        return new EqualsBuilder().append(id, item.id).build();
    }

    public final int hashCode() {
        return new HashCodeBuilder(887, 1385).append(id).build();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_member.id
     *
     * @return the value of m_prj_member.id
     *
     * @mbggenerated Tue Aug 11 12:50:57 ICT 2015
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
     * @mbggenerated Tue Aug 11 12:50:57 ICT 2015
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
     * @mbggenerated Tue Aug 11 12:50:57 ICT 2015
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
     * @mbggenerated Tue Aug 11 12:50:57 ICT 2015
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
     * @mbggenerated Tue Aug 11 12:50:57 ICT 2015
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
     * @mbggenerated Tue Aug 11 12:50:57 ICT 2015
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
     * @mbggenerated Tue Aug 11 12:50:57 ICT 2015
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
     * @mbggenerated Tue Aug 11 12:50:57 ICT 2015
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
     * @mbggenerated Tue Aug 11 12:50:57 ICT 2015
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
     * @mbggenerated Tue Aug 11 12:50:57 ICT 2015
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
     * @mbggenerated Tue Aug 11 12:50:57 ICT 2015
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
     * @mbggenerated Tue Aug 11 12:50:57 ICT 2015
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
     * @mbggenerated Tue Aug 11 12:50:57 ICT 2015
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
     * @mbggenerated Tue Aug 11 12:50:57 ICT 2015
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
     * @mbggenerated Tue Aug 11 12:50:57 ICT 2015
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
     * @mbggenerated Tue Aug 11 12:50:57 ICT 2015
     */
    public void setSaccountid(Integer saccountid) {
        this.saccountid = saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_member.billingRate
     *
     * @return the value of m_prj_member.billingRate
     *
     * @mbggenerated Tue Aug 11 12:50:57 ICT 2015
     */
    public Double getBillingrate() {
        return billingrate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_member.billingRate
     *
     * @param billingrate the value for m_prj_member.billingRate
     *
     * @mbggenerated Tue Aug 11 12:50:57 ICT 2015
     */
    public void setBillingrate(Double billingrate) {
        this.billingrate = billingrate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_member.overtimeBillingRate
     *
     * @return the value of m_prj_member.overtimeBillingRate
     *
     * @mbggenerated Tue Aug 11 12:50:57 ICT 2015
     */
    public Double getOvertimebillingrate() {
        return overtimebillingrate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_member.overtimeBillingRate
     *
     * @param overtimebillingrate the value for m_prj_member.overtimeBillingRate
     *
     * @mbggenerated Tue Aug 11 12:50:57 ICT 2015
     */
    public void setOvertimebillingrate(Double overtimebillingrate) {
        this.overtimebillingrate = overtimebillingrate;
    }

    public enum Field {
        id,
        username,
        projectid,
        joindate,
        projectroleid,
        isadmin,
        status,
        saccountid,
        billingrate,
        overtimebillingrate;

        public boolean equalTo(Object value) {
            return name().equals(value);
        }
    }
}