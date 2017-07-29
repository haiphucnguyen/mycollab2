/*Domain class of table s_user_permission*/
package com.mycollab.module.user.domain;

import com.mycollab.core.arguments.ValuedBean;
import com.mycollab.db.metadata.Column;
import com.mycollab.db.metadata.Table;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.Length;

@SuppressWarnings("ucd")
@Table("s_user_permission")
public class UserPermission extends ValuedBean {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user_permission.Id
     *
     * @mbg.generated Sun Jun 25 11:59:47 ICT 2017
     */
    @Column("Id")
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user_permission.module
     *
     * @mbg.generated Sun Jun 25 11:59:47 ICT 2017
     */
    @Length(max=45, message="Field value is too long")
    @Column("module")
    private String module;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user_permission.type
     *
     * @mbg.generated Sun Jun 25 11:59:47 ICT 2017
     */
    @Length(max=45, message="Field value is too long")
    @Column("type")
    private String type;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user_permission.hasPermission
     *
     * @mbg.generated Sun Jun 25 11:59:47 ICT 2017
     */
    @Length(max=45, message="Field value is too long")
    @Column("hasPermission")
    private String haspermission;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user_permission.username
     *
     * @mbg.generated Sun Jun 25 11:59:47 ICT 2017
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
        return new HashCodeBuilder(1049, 431).append(id).build();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_user_permission.Id
     *
     * @return the value of s_user_permission.Id
     *
     * @mbg.generated Sun Jun 25 11:59:47 ICT 2017
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
     * @mbg.generated Sun Jun 25 11:59:47 ICT 2017
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
     * @mbg.generated Sun Jun 25 11:59:47 ICT 2017
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
     * @mbg.generated Sun Jun 25 11:59:47 ICT 2017
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
     * @mbg.generated Sun Jun 25 11:59:47 ICT 2017
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
     * @mbg.generated Sun Jun 25 11:59:47 ICT 2017
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
     * @mbg.generated Sun Jun 25 11:59:47 ICT 2017
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
     * @mbg.generated Sun Jun 25 11:59:47 ICT 2017
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
     * @mbg.generated Sun Jun 25 11:59:47 ICT 2017
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
     * @mbg.generated Sun Jun 25 11:59:47 ICT 2017
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