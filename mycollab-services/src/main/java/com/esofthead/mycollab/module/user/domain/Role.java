/*Domain class of table s_roles*/
package com.esofthead.mycollab.module.user.domain;

import com.esofthead.mycollab.core.arguments.ValuedBean;
import com.esofthead.mycollab.core.db.metadata.Column;
import com.esofthead.mycollab.core.db.metadata.Table;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.Length;

@SuppressWarnings("ucd")
@Table("s_roles")
public class Role extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_roles.id
     *
     * @mbggenerated Sun May 15 02:13:09 ICT 2016
     */
    @Column("id")
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_roles.rolename
     *
     * @mbggenerated Sun May 15 02:13:09 ICT 2016
     */
    @Length(max=45, message="Field value is too long")
    @Column("rolename")
    private String rolename;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_roles.description
     *
     * @mbggenerated Sun May 15 02:13:09 ICT 2016
     */
    @Length(max=45, message="Field value is too long")
    @Column("description")
    private String description;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_roles.sAccountId
     *
     * @mbggenerated Sun May 15 02:13:09 ICT 2016
     */
    @Column("sAccountId")
    private Integer saccountid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_roles.isSystemRole
     *
     * @mbggenerated Sun May 15 02:13:09 ICT 2016
     */
    @Column("isSystemRole")
    private Boolean issystemrole;

    private static final long serialVersionUID = 1;

    public final boolean equals(Object obj) {
        if (obj == null) { return false;}
        if (obj == this) { return true;}
        if (!obj.getClass().isAssignableFrom(getClass())) { return false;}
        Role item = (Role)obj;
        return new EqualsBuilder().append(id, item.id).build();
    }

    public final int hashCode() {
        return new HashCodeBuilder(765, 1913).append(id).build();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_roles.id
     *
     * @return the value of s_roles.id
     *
     * @mbggenerated Sun May 15 02:13:09 ICT 2016
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_roles.id
     *
     * @param id the value for s_roles.id
     *
     * @mbggenerated Sun May 15 02:13:09 ICT 2016
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_roles.rolename
     *
     * @return the value of s_roles.rolename
     *
     * @mbggenerated Sun May 15 02:13:09 ICT 2016
     */
    public String getRolename() {
        return rolename;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_roles.rolename
     *
     * @param rolename the value for s_roles.rolename
     *
     * @mbggenerated Sun May 15 02:13:09 ICT 2016
     */
    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_roles.description
     *
     * @return the value of s_roles.description
     *
     * @mbggenerated Sun May 15 02:13:09 ICT 2016
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_roles.description
     *
     * @param description the value for s_roles.description
     *
     * @mbggenerated Sun May 15 02:13:09 ICT 2016
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_roles.sAccountId
     *
     * @return the value of s_roles.sAccountId
     *
     * @mbggenerated Sun May 15 02:13:09 ICT 2016
     */
    public Integer getSaccountid() {
        return saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_roles.sAccountId
     *
     * @param saccountid the value for s_roles.sAccountId
     *
     * @mbggenerated Sun May 15 02:13:09 ICT 2016
     */
    public void setSaccountid(Integer saccountid) {
        this.saccountid = saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_roles.isSystemRole
     *
     * @return the value of s_roles.isSystemRole
     *
     * @mbggenerated Sun May 15 02:13:09 ICT 2016
     */
    public Boolean getIssystemrole() {
        return issystemrole;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_roles.isSystemRole
     *
     * @param issystemrole the value for s_roles.isSystemRole
     *
     * @mbggenerated Sun May 15 02:13:09 ICT 2016
     */
    public void setIssystemrole(Boolean issystemrole) {
        this.issystemrole = issystemrole;
    }

    public enum Field {
        id,
        rolename,
        description,
        saccountid,
        issystemrole;

        public boolean equalTo(Object value) {
            return name().equals(value);
        }
    }
}