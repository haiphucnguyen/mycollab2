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
/**
 * mycollab-services - Parent pom providing dependency and plugin management for applications
		built with Maven
 * Copyright © ${project.inceptionYear} MyCollab (support@mycollab.com)
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
/*Domain class of table s_user_permission*/
package com.mycollab.module.user.domain;

import com.mycollab.core.arguments.ValuedBean;
import com.mycollab.db.metadata.Column;
import com.mycollab.db.metadata.Table;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.ibatis.type.Alias;
import org.hibernate.validator.constraints.Length;

@SuppressWarnings("ucd")
@Table("s_user_permission")
@Alias("UserPermission")
public class UserPermission extends ValuedBean {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user_permission.Id
     *
     * @mbg.generated Tue Aug 01 11:17:31 ICT 2017
     */
    @Column("Id")
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user_permission.module
     *
     * @mbg.generated Tue Aug 01 11:17:31 ICT 2017
     */
    @Length(max=45, message="Field value is too long")
    @Column("module")
    private String module;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user_permission.type
     *
     * @mbg.generated Tue Aug 01 11:17:31 ICT 2017
     */
    @Length(max=45, message="Field value is too long")
    @Column("type")
    private String type;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user_permission.hasPermission
     *
     * @mbg.generated Tue Aug 01 11:17:31 ICT 2017
     */
    @Length(max=45, message="Field value is too long")
    @Column("hasPermission")
    private String haspermission;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user_permission.username
     *
     * @mbg.generated Tue Aug 01 11:17:31 ICT 2017
     */
    @Length(max=45, message="Field value is too long")
    @Column("username")
    private String username;

    private static final long serialVersionUID = 1;

    public final boolean equals(Object obj) {
        if (obj == null) { return false;}
        if (obj == this) { return true;}
        if (!obj.getClass().isAssignableFrom(getClass())) { return false;}
        UserPermission item = (UserPermission)obj;
        return new EqualsBuilder().append(id, item.id).build();
    }

    public final int hashCode() {
        return new HashCodeBuilder(1545, 843).append(id).build();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_user_permission.Id
     *
     * @return the value of s_user_permission.Id
     *
     * @mbg.generated Tue Aug 01 11:17:31 ICT 2017
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_user_permission.Id
     *
     * @param id the value for s_user_permission.Id
     *
     * @mbg.generated Tue Aug 01 11:17:31 ICT 2017
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_user_permission.module
     *
     * @return the value of s_user_permission.module
     *
     * @mbg.generated Tue Aug 01 11:17:31 ICT 2017
     */
    public String getModule() {
        return module;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_user_permission.module
     *
     * @param module the value for s_user_permission.module
     *
     * @mbg.generated Tue Aug 01 11:17:31 ICT 2017
     */
    public void setModule(String module) {
        this.module = module;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_user_permission.type
     *
     * @return the value of s_user_permission.type
     *
     * @mbg.generated Tue Aug 01 11:17:31 ICT 2017
     */
    public String getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_user_permission.type
     *
     * @param type the value for s_user_permission.type
     *
     * @mbg.generated Tue Aug 01 11:17:31 ICT 2017
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_user_permission.hasPermission
     *
     * @return the value of s_user_permission.hasPermission
     *
     * @mbg.generated Tue Aug 01 11:17:31 ICT 2017
     */
    public String getHaspermission() {
        return haspermission;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_user_permission.hasPermission
     *
     * @param haspermission the value for s_user_permission.hasPermission
     *
     * @mbg.generated Tue Aug 01 11:17:31 ICT 2017
     */
    public void setHaspermission(String haspermission) {
        this.haspermission = haspermission;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_user_permission.username
     *
     * @return the value of s_user_permission.username
     *
     * @mbg.generated Tue Aug 01 11:17:31 ICT 2017
     */
    public String getUsername() {
        return username;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_user_permission.username
     *
     * @param username the value for s_user_permission.username
     *
     * @mbg.generated Tue Aug 01 11:17:31 ICT 2017
     */
    public void setUsername(String username) {
        this.username = username;
    }

    public enum Field {
        id,
        module,
        type,
        haspermission,
        username;

        public boolean equalTo(Object value) {
            return name().equals(value);
        }
    }
}