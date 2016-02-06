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

import com.esofthead.mycollab.core.arguments.ValuedBean;
import com.esofthead.mycollab.core.db.metadata.Column;
import com.esofthead.mycollab.core.db.metadata.Table;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.Length;

@SuppressWarnings("ucd")
@Table("m_prj_notifications")
public class ProjectNotificationSetting extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_notifications.id
     *
     * @mbggenerated Sat Feb 06 17:34:26 ICT 2016
     */
    @Column("id")
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_notifications.username
     *
     * @mbggenerated Sat Feb 06 17:34:26 ICT 2016
     */
    @Length(max=45, message="Field value is too long")
    @Column("username")
    private String username;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_notifications.projectId
     *
     * @mbggenerated Sat Feb 06 17:34:26 ICT 2016
     */
    @Column("projectId")
    private Integer projectid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_notifications.sAccountId
     *
     * @mbggenerated Sat Feb 06 17:34:26 ICT 2016
     */
    @Column("sAccountId")
    private Integer saccountid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_notifications.level
     *
     * @mbggenerated Sat Feb 06 17:34:26 ICT 2016
     */
    @Length(max=45, message="Field value is too long")
    @Column("level")
    private String level;

    private static final long serialVersionUID = 1;

    public final boolean equals(Object obj) {
        if (obj == null) { return false;}
        if (obj == this) { return true;}
        if (obj.getClass() != getClass()) { return false;}
        ProjectNotificationSetting item = (ProjectNotificationSetting)obj;
        return new EqualsBuilder().append(id, item.id).build();
    }

    public final int hashCode() {
        return new HashCodeBuilder(1579, 467).append(id).build();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_notifications.id
     *
     * @return the value of m_prj_notifications.id
     *
     * @mbggenerated Sat Feb 06 17:34:26 ICT 2016
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
     * @mbggenerated Sat Feb 06 17:34:26 ICT 2016
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
     * @mbggenerated Sat Feb 06 17:34:26 ICT 2016
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
     * @mbggenerated Sat Feb 06 17:34:26 ICT 2016
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
     * @mbggenerated Sat Feb 06 17:34:26 ICT 2016
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
     * @mbggenerated Sat Feb 06 17:34:26 ICT 2016
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
     * @mbggenerated Sat Feb 06 17:34:26 ICT 2016
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
     * @mbggenerated Sat Feb 06 17:34:26 ICT 2016
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
     * @mbggenerated Sat Feb 06 17:34:26 ICT 2016
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
     * @mbggenerated Sat Feb 06 17:34:26 ICT 2016
     */
    public void setLevel(String level) {
        this.level = level;
    }

    public enum Field {
        id,
        username,
        projectid,
        saccountid,
        level;

        public boolean equalTo(Object value) {
            return name().equals(value);
        }
    }
}