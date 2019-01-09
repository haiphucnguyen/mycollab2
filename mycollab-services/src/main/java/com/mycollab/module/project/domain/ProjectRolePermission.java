/*Domain class of table m_prj_role_permission*/
package com.mycollab.module.project.domain;

import com.mycollab.core.arguments.ValuedBean;
import com.mycollab.db.metadata.Column;
import com.mycollab.db.metadata.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.ibatis.type.Alias;
import org.hibernate.validator.constraints.Length;

@SuppressWarnings("ucd")
@Table("m_prj_role_permission")
@Alias("ProjectRolePermission")
public class ProjectRolePermission extends ValuedBean {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_role_permission.id
     *
     * @mbg.generated Wed Jan 09 14:10:22 CST 2019
     */
    @Column("id")
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_role_permission.roleId
     *
     * @mbg.generated Wed Jan 09 14:10:22 CST 2019
     */
    @NotNull
    @Column("roleId")
    private Integer roleid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_role_permission.projectId
     *
     * @mbg.generated Wed Jan 09 14:10:22 CST 2019
     */
    @NotNull
    @Column("projectId")
    private Integer projectid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_role_permission.roleVal
     *
     * @mbg.generated Wed Jan 09 14:10:22 CST 2019
     */
    @NotEmpty
    @Length(max=65535, message="Field value is too long")
    @Column("roleVal")
    private String roleval;

    private static final long serialVersionUID = 1;

    public final boolean equals(Object obj) {
        if (obj == null) { return false;}
        if (obj == this) { return true;}
        if (!obj.getClass().isAssignableFrom(getClass())) { return false;}
        ProjectRolePermission item = (ProjectRolePermission)obj;
        return new EqualsBuilder().append(id, item.id).build();
    }

    public final int hashCode() {
        return new HashCodeBuilder(961, 1299).append(id).build();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_role_permission.id
     *
     * @return the value of m_prj_role_permission.id
     *
     * @mbg.generated Wed Jan 09 14:10:22 CST 2019
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_role_permission.id
     *
     * @param id the value for m_prj_role_permission.id
     *
     * @mbg.generated Wed Jan 09 14:10:22 CST 2019
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_role_permission.roleId
     *
     * @return the value of m_prj_role_permission.roleId
     *
     * @mbg.generated Wed Jan 09 14:10:22 CST 2019
     */
    public Integer getRoleid() {
        return roleid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_role_permission.roleId
     *
     * @param roleid the value for m_prj_role_permission.roleId
     *
     * @mbg.generated Wed Jan 09 14:10:22 CST 2019
     */
    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_role_permission.projectId
     *
     * @return the value of m_prj_role_permission.projectId
     *
     * @mbg.generated Wed Jan 09 14:10:22 CST 2019
     */
    public Integer getProjectid() {
        return projectid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_role_permission.projectId
     *
     * @param projectid the value for m_prj_role_permission.projectId
     *
     * @mbg.generated Wed Jan 09 14:10:22 CST 2019
     */
    public void setProjectid(Integer projectid) {
        this.projectid = projectid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_role_permission.roleVal
     *
     * @return the value of m_prj_role_permission.roleVal
     *
     * @mbg.generated Wed Jan 09 14:10:22 CST 2019
     */
    public String getRoleval() {
        return roleval;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_role_permission.roleVal
     *
     * @param roleval the value for m_prj_role_permission.roleVal
     *
     * @mbg.generated Wed Jan 09 14:10:22 CST 2019
     */
    public void setRoleval(String roleval) {
        this.roleval = roleval;
    }

    public enum Field {
        id,
        roleid,
        projectid,
        roleval;

        public boolean equalTo(Object value) {
            return name().equals(value);
        }
    }
}