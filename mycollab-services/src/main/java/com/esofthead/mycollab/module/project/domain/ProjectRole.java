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
/*Domain class of table m_prj_role*/
package com.esofthead.mycollab.module.project.domain;

import com.esofthead.mycollab.core.arguments.ValuedBean;
import com.esofthead.mycollab.core.db.metadata.Column;
import com.esofthead.mycollab.core.db.metadata.Table;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.Length;

@SuppressWarnings("ucd")
@Table("m_prj_role")
public class ProjectRole extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_role.id
     *
     * @mbggenerated Wed Aug 19 10:48:09 ICT 2015
     */
    @Column("id")
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_role.rolename
     *
     * @mbggenerated Wed Aug 19 10:48:09 ICT 2015
     */
    @Length(max=255, message="Field value is too long")
    @Column("rolename")
    private String rolename;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_role.sAccountId
     *
     * @mbggenerated Wed Aug 19 10:48:09 ICT 2015
     */
    @Column("sAccountId")
    private Integer saccountid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_role.projectid
     *
     * @mbggenerated Wed Aug 19 10:48:09 ICT 2015
     */
    @Column("projectid")
    private Integer projectid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_role.isSystemRole
     *
     * @mbggenerated Wed Aug 19 10:48:09 ICT 2015
     */
    @Column("isSystemRole")
    private Boolean issystemrole;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_role.description
     *
     * @mbggenerated Wed Aug 19 10:48:09 ICT 2015
     */
    @Length(max=65535, message="Field value is too long")
    @Column("description")
    private String description;

    private static final long serialVersionUID = 1;

    public final boolean equals(Object obj) {
        if (obj == null) { return false;}
        if (obj == this) { return true;}
        if (obj.getClass() != getClass()) { return false;}
        ProjectRole item = (ProjectRole)obj;
        return new EqualsBuilder().append(id, item.id).build();
    }

    public final int hashCode() {
        return new HashCodeBuilder(1735, 1253).append(id).build();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_role.id
     *
     * @return the value of m_prj_role.id
     *
     * @mbggenerated Wed Aug 19 10:48:09 ICT 2015
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_role.id
     *
     * @param id the value for m_prj_role.id
     *
     * @mbggenerated Wed Aug 19 10:48:09 ICT 2015
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_role.rolename
     *
     * @return the value of m_prj_role.rolename
     *
     * @mbggenerated Wed Aug 19 10:48:09 ICT 2015
     */
    public String getRolename() {
        return rolename;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_role.rolename
     *
     * @param rolename the value for m_prj_role.rolename
     *
     * @mbggenerated Wed Aug 19 10:48:09 ICT 2015
     */
    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_role.sAccountId
     *
     * @return the value of m_prj_role.sAccountId
     *
     * @mbggenerated Wed Aug 19 10:48:09 ICT 2015
     */
    public Integer getSaccountid() {
        return saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_role.sAccountId
     *
     * @param saccountid the value for m_prj_role.sAccountId
     *
     * @mbggenerated Wed Aug 19 10:48:09 ICT 2015
     */
    public void setSaccountid(Integer saccountid) {
        this.saccountid = saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_role.projectid
     *
     * @return the value of m_prj_role.projectid
     *
     * @mbggenerated Wed Aug 19 10:48:09 ICT 2015
     */
    public Integer getProjectid() {
        return projectid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_role.projectid
     *
     * @param projectid the value for m_prj_role.projectid
     *
     * @mbggenerated Wed Aug 19 10:48:09 ICT 2015
     */
    public void setProjectid(Integer projectid) {
        this.projectid = projectid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_role.isSystemRole
     *
     * @return the value of m_prj_role.isSystemRole
     *
     * @mbggenerated Wed Aug 19 10:48:09 ICT 2015
     */
    public Boolean getIssystemrole() {
        return issystemrole;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_role.isSystemRole
     *
     * @param issystemrole the value for m_prj_role.isSystemRole
     *
     * @mbggenerated Wed Aug 19 10:48:09 ICT 2015
     */
    public void setIssystemrole(Boolean issystemrole) {
        this.issystemrole = issystemrole;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_role.description
     *
     * @return the value of m_prj_role.description
     *
     * @mbggenerated Wed Aug 19 10:48:09 ICT 2015
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_role.description
     *
     * @param description the value for m_prj_role.description
     *
     * @mbggenerated Wed Aug 19 10:48:09 ICT 2015
     */
    public void setDescription(String description) {
        this.description = description;
    }

    public enum Field {
        id,
        rolename,
        saccountid,
        projectid,
        issystemrole,
        description;

        public boolean equalTo(Object value) {
            return name().equals(value);
        }
    }
}