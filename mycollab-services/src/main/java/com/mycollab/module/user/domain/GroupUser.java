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
/*Domain class of table m_group_user*/
package com.mycollab.module.user.domain;

import com.mycollab.core.arguments.ValuedBean;
import com.mycollab.db.metadata.Column;
import com.mycollab.db.metadata.Table;
import java.util.Date;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.Length;

@SuppressWarnings("ucd")
@Table("m_group_user")
public class GroupUser extends ValuedBean {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_group_user.id
     *
     * @mbg.generated Sun Jun 25 11:59:47 ICT 2017
     */
    @Column("id")
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_group_user.groupId
     *
     * @mbg.generated Sun Jun 25 11:59:47 ICT 2017
     */
    @Column("groupId")
    private Integer groupid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_group_user.user
     *
     * @mbg.generated Sun Jun 25 11:59:47 ICT 2017
     */
    @Length(max=45, message="Field value is too long")
    @Column("user")
    private String user;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_group_user.createdTime
     *
     * @mbg.generated Sun Jun 25 11:59:47 ICT 2017
     */
    @Column("createdTime")
    private Date createdtime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_group_user.isOwner
     *
     * @mbg.generated Sun Jun 25 11:59:47 ICT 2017
     */
    @Column("isOwner")
    private Boolean isowner;

    private static final long serialVersionUID = 1;

    public final boolean equals(Object obj) {
        if (obj == null) { return false;}
        if (obj == this) { return true;}
        if (!obj.getClass().isAssignableFrom(getClass())) { return false;}
        GroupUser item = (GroupUser)obj;
        return new EqualsBuilder().append(id, item.id).build();
    }

    public final int hashCode() {
        return new HashCodeBuilder(1027, 1477).append(id).build();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_group_user.id
     *
     * @return the value of m_group_user.id
     *
     * @mbg.generated Sun Jun 25 11:59:47 ICT 2017
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_group_user.id
     *
     * @param id the value for m_group_user.id
     *
     * @mbg.generated Sun Jun 25 11:59:47 ICT 2017
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_group_user.groupId
     *
     * @return the value of m_group_user.groupId
     *
     * @mbg.generated Sun Jun 25 11:59:47 ICT 2017
     */
    public Integer getGroupid() {
        return groupid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_group_user.groupId
     *
     * @param groupid the value for m_group_user.groupId
     *
     * @mbg.generated Sun Jun 25 11:59:47 ICT 2017
     */
    public void setGroupid(Integer groupid) {
        this.groupid = groupid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_group_user.user
     *
     * @return the value of m_group_user.user
     *
     * @mbg.generated Sun Jun 25 11:59:47 ICT 2017
     */
    public String getUser() {
        return user;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_group_user.user
     *
     * @param user the value for m_group_user.user
     *
     * @mbg.generated Sun Jun 25 11:59:47 ICT 2017
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_group_user.createdTime
     *
     * @return the value of m_group_user.createdTime
     *
     * @mbg.generated Sun Jun 25 11:59:47 ICT 2017
     */
    public Date getCreatedtime() {
        return createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_group_user.createdTime
     *
     * @param createdtime the value for m_group_user.createdTime
     *
     * @mbg.generated Sun Jun 25 11:59:47 ICT 2017
     */
    public void setCreatedtime(Date createdtime) {
        this.createdtime = createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_group_user.isOwner
     *
     * @return the value of m_group_user.isOwner
     *
     * @mbg.generated Sun Jun 25 11:59:47 ICT 2017
     */
    public Boolean getIsowner() {
        return isowner;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_group_user.isOwner
     *
     * @param isowner the value for m_group_user.isOwner
     *
     * @mbg.generated Sun Jun 25 11:59:47 ICT 2017
     */
    public void setIsowner(Boolean isowner) {
        this.isowner = isowner;
    }

    public enum Field {
        id,
        groupid,
        user,
        createdtime,
        isowner;

        public boolean equalTo(Object value) {
            return name().equals(value);
        }
    }
}