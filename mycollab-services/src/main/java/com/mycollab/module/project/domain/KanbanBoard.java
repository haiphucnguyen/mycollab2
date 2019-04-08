/*Domain class of table m_prj_kanban_board*/
package com.mycollab.module.project.domain;

import com.mycollab.core.arguments.ValuedBean;
import com.mycollab.db.metadata.Column;
import com.mycollab.db.metadata.Table;
import java.time.LocalDateTime;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.ibatis.type.Alias;
import org.hibernate.validator.constraints.Length;

@SuppressWarnings("ucd")
@Table("m_prj_kanban_board")
@Alias("KanbanBoard")
public class KanbanBoard extends ValuedBean {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_kanban_board.id
     *
     * @mbg.generated Mon Apr 08 15:53:02 CDT 2019
     */
    @NotNull
    @Column("id")
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_kanban_board.name
     *
     * @mbg.generated Mon Apr 08 15:53:02 CDT 2019
     */
    @NotEmpty
    @Length(max=255, message="Field value is too long")
    @Column("name")
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_kanban_board.projectId
     *
     * @mbg.generated Mon Apr 08 15:53:02 CDT 2019
     */
    @NotNull
    @Column("projectId")
    private Integer projectid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_kanban_board.sAccountId
     *
     * @mbg.generated Mon Apr 08 15:53:02 CDT 2019
     */
    @NotNull
    @Column("sAccountId")
    private Integer saccountid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_kanban_board.lead
     *
     * @mbg.generated Mon Apr 08 15:53:02 CDT 2019
     */
    @Length(max=45, message="Field value is too long")
    @Column("lead")
    private String lead;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_kanban_board.createdTime
     *
     * @mbg.generated Mon Apr 08 15:53:02 CDT 2019
     */
    @Column("createdTime")
    private LocalDateTime createdtime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_kanban_board.lastUpdatedTime
     *
     * @mbg.generated Mon Apr 08 15:53:02 CDT 2019
     */
    @Column("lastUpdatedTime")
    private LocalDateTime lastupdatedtime;

    private static final long serialVersionUID = 1;

    public final boolean equals(Object obj) {
        if (obj == null) { return false;}
        if (obj == this) { return true;}
        if (!obj.getClass().isAssignableFrom(getClass())) { return false;}
        KanbanBoard item = (KanbanBoard)obj;
        return new EqualsBuilder().append(id, item.id).build();
    }

    public final int hashCode() {
        return new HashCodeBuilder(1339, 207).append(id).build();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_kanban_board.id
     *
     * @return the value of m_prj_kanban_board.id
     *
     * @mbg.generated Mon Apr 08 15:53:02 CDT 2019
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_kanban_board
     *
     * @mbg.generated Mon Apr 08 15:53:02 CDT 2019
     */
    public KanbanBoard withId(Integer id) {
        this.setId(id);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_kanban_board.id
     *
     * @param id the value for m_prj_kanban_board.id
     *
     * @mbg.generated Mon Apr 08 15:53:02 CDT 2019
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_kanban_board.name
     *
     * @return the value of m_prj_kanban_board.name
     *
     * @mbg.generated Mon Apr 08 15:53:02 CDT 2019
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_kanban_board
     *
     * @mbg.generated Mon Apr 08 15:53:02 CDT 2019
     */
    public KanbanBoard withName(String name) {
        this.setName(name);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_kanban_board.name
     *
     * @param name the value for m_prj_kanban_board.name
     *
     * @mbg.generated Mon Apr 08 15:53:02 CDT 2019
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_kanban_board.projectId
     *
     * @return the value of m_prj_kanban_board.projectId
     *
     * @mbg.generated Mon Apr 08 15:53:02 CDT 2019
     */
    public Integer getProjectid() {
        return projectid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_kanban_board
     *
     * @mbg.generated Mon Apr 08 15:53:02 CDT 2019
     */
    public KanbanBoard withProjectid(Integer projectid) {
        this.setProjectid(projectid);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_kanban_board.projectId
     *
     * @param projectid the value for m_prj_kanban_board.projectId
     *
     * @mbg.generated Mon Apr 08 15:53:02 CDT 2019
     */
    public void setProjectid(Integer projectid) {
        this.projectid = projectid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_kanban_board.sAccountId
     *
     * @return the value of m_prj_kanban_board.sAccountId
     *
     * @mbg.generated Mon Apr 08 15:53:02 CDT 2019
     */
    public Integer getSaccountid() {
        return saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_kanban_board
     *
     * @mbg.generated Mon Apr 08 15:53:02 CDT 2019
     */
    public KanbanBoard withSaccountid(Integer saccountid) {
        this.setSaccountid(saccountid);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_kanban_board.sAccountId
     *
     * @param saccountid the value for m_prj_kanban_board.sAccountId
     *
     * @mbg.generated Mon Apr 08 15:53:02 CDT 2019
     */
    public void setSaccountid(Integer saccountid) {
        this.saccountid = saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_kanban_board.lead
     *
     * @return the value of m_prj_kanban_board.lead
     *
     * @mbg.generated Mon Apr 08 15:53:02 CDT 2019
     */
    public String getLead() {
        return lead;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_kanban_board
     *
     * @mbg.generated Mon Apr 08 15:53:02 CDT 2019
     */
    public KanbanBoard withLead(String lead) {
        this.setLead(lead);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_kanban_board.lead
     *
     * @param lead the value for m_prj_kanban_board.lead
     *
     * @mbg.generated Mon Apr 08 15:53:02 CDT 2019
     */
    public void setLead(String lead) {
        this.lead = lead;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_kanban_board.createdTime
     *
     * @return the value of m_prj_kanban_board.createdTime
     *
     * @mbg.generated Mon Apr 08 15:53:02 CDT 2019
     */
    public LocalDateTime getCreatedtime() {
        return createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_kanban_board
     *
     * @mbg.generated Mon Apr 08 15:53:02 CDT 2019
     */
    public KanbanBoard withCreatedtime(LocalDateTime createdtime) {
        this.setCreatedtime(createdtime);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_kanban_board.createdTime
     *
     * @param createdtime the value for m_prj_kanban_board.createdTime
     *
     * @mbg.generated Mon Apr 08 15:53:02 CDT 2019
     */
    public void setCreatedtime(LocalDateTime createdtime) {
        this.createdtime = createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_kanban_board.lastUpdatedTime
     *
     * @return the value of m_prj_kanban_board.lastUpdatedTime
     *
     * @mbg.generated Mon Apr 08 15:53:02 CDT 2019
     */
    public LocalDateTime getLastupdatedtime() {
        return lastupdatedtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_kanban_board
     *
     * @mbg.generated Mon Apr 08 15:53:02 CDT 2019
     */
    public KanbanBoard withLastupdatedtime(LocalDateTime lastupdatedtime) {
        this.setLastupdatedtime(lastupdatedtime);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_kanban_board.lastUpdatedTime
     *
     * @param lastupdatedtime the value for m_prj_kanban_board.lastUpdatedTime
     *
     * @mbg.generated Mon Apr 08 15:53:02 CDT 2019
     */
    public void setLastupdatedtime(LocalDateTime lastupdatedtime) {
        this.lastupdatedtime = lastupdatedtime;
    }

    public enum Field {
        id,
        name,
        projectid,
        saccountid,
        lead,
        createdtime,
        lastupdatedtime;

        public boolean equalTo(Object value) {
            return name().equals(value);
        }
    }
}