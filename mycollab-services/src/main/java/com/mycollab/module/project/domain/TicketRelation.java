/*Domain class of table m_prj_ticket_relation*/
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
@Table("m_prj_ticket_relation")
@Alias("TicketRelation")
public class TicketRelation extends ValuedBean {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_ticket_relation.id
     *
     * @mbg.generated Mon Apr 08 15:53:02 CDT 2019
     */
    @Column("id")
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_ticket_relation.ticketId
     *
     * @mbg.generated Mon Apr 08 15:53:02 CDT 2019
     */
    @NotNull
    @Column("ticketId")
    private Integer ticketid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_ticket_relation.ticketType
     *
     * @mbg.generated Mon Apr 08 15:53:02 CDT 2019
     */
    @NotEmpty
    @Length(max=45, message="Field value is too long")
    @Column("ticketType")
    private String tickettype;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_ticket_relation.type
     *
     * @mbg.generated Mon Apr 08 15:53:02 CDT 2019
     */
    @NotEmpty
    @Length(max=45, message="Field value is too long")
    @Column("type")
    private String type;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_ticket_relation.typeId
     *
     * @mbg.generated Mon Apr 08 15:53:02 CDT 2019
     */
    @NotNull
    @Column("typeId")
    private Integer typeid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_ticket_relation.rel
     *
     * @mbg.generated Mon Apr 08 15:53:02 CDT 2019
     */
    @NotEmpty
    @Length(max=45, message="Field value is too long")
    @Column("rel")
    private String rel;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_ticket_relation.comment
     *
     * @mbg.generated Mon Apr 08 15:53:02 CDT 2019
     */
    @Length(max=65535, message="Field value is too long")
    @Column("comment")
    private String comment;

    private static final long serialVersionUID = 1;

    public final boolean equals(Object obj) {
        if (obj == null) { return false;}
        if (obj == this) { return true;}
        if (!obj.getClass().isAssignableFrom(getClass())) { return false;}
        TicketRelation item = (TicketRelation)obj;
        return new EqualsBuilder().append(id, item.id).build();
    }

    public final int hashCode() {
        return new HashCodeBuilder(1919, 575).append(id).build();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_ticket_relation.id
     *
     * @return the value of m_prj_ticket_relation.id
     *
     * @mbg.generated Mon Apr 08 15:53:02 CDT 2019
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_ticket_relation
     *
     * @mbg.generated Mon Apr 08 15:53:02 CDT 2019
     */
    public TicketRelation withId(Integer id) {
        this.setId(id);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_ticket_relation.id
     *
     * @param id the value for m_prj_ticket_relation.id
     *
     * @mbg.generated Mon Apr 08 15:53:02 CDT 2019
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_ticket_relation.ticketId
     *
     * @return the value of m_prj_ticket_relation.ticketId
     *
     * @mbg.generated Mon Apr 08 15:53:02 CDT 2019
     */
    public Integer getTicketid() {
        return ticketid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_ticket_relation
     *
     * @mbg.generated Mon Apr 08 15:53:02 CDT 2019
     */
    public TicketRelation withTicketid(Integer ticketid) {
        this.setTicketid(ticketid);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_ticket_relation.ticketId
     *
     * @param ticketid the value for m_prj_ticket_relation.ticketId
     *
     * @mbg.generated Mon Apr 08 15:53:02 CDT 2019
     */
    public void setTicketid(Integer ticketid) {
        this.ticketid = ticketid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_ticket_relation.ticketType
     *
     * @return the value of m_prj_ticket_relation.ticketType
     *
     * @mbg.generated Mon Apr 08 15:53:02 CDT 2019
     */
    public String getTickettype() {
        return tickettype;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_ticket_relation
     *
     * @mbg.generated Mon Apr 08 15:53:02 CDT 2019
     */
    public TicketRelation withTickettype(String tickettype) {
        this.setTickettype(tickettype);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_ticket_relation.ticketType
     *
     * @param tickettype the value for m_prj_ticket_relation.ticketType
     *
     * @mbg.generated Mon Apr 08 15:53:02 CDT 2019
     */
    public void setTickettype(String tickettype) {
        this.tickettype = tickettype;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_ticket_relation.type
     *
     * @return the value of m_prj_ticket_relation.type
     *
     * @mbg.generated Mon Apr 08 15:53:02 CDT 2019
     */
    public String getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_ticket_relation
     *
     * @mbg.generated Mon Apr 08 15:53:02 CDT 2019
     */
    public TicketRelation withType(String type) {
        this.setType(type);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_ticket_relation.type
     *
     * @param type the value for m_prj_ticket_relation.type
     *
     * @mbg.generated Mon Apr 08 15:53:02 CDT 2019
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_ticket_relation.typeId
     *
     * @return the value of m_prj_ticket_relation.typeId
     *
     * @mbg.generated Mon Apr 08 15:53:02 CDT 2019
     */
    public Integer getTypeid() {
        return typeid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_ticket_relation
     *
     * @mbg.generated Mon Apr 08 15:53:02 CDT 2019
     */
    public TicketRelation withTypeid(Integer typeid) {
        this.setTypeid(typeid);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_ticket_relation.typeId
     *
     * @param typeid the value for m_prj_ticket_relation.typeId
     *
     * @mbg.generated Mon Apr 08 15:53:02 CDT 2019
     */
    public void setTypeid(Integer typeid) {
        this.typeid = typeid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_ticket_relation.rel
     *
     * @return the value of m_prj_ticket_relation.rel
     *
     * @mbg.generated Mon Apr 08 15:53:02 CDT 2019
     */
    public String getRel() {
        return rel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_ticket_relation
     *
     * @mbg.generated Mon Apr 08 15:53:02 CDT 2019
     */
    public TicketRelation withRel(String rel) {
        this.setRel(rel);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_ticket_relation.rel
     *
     * @param rel the value for m_prj_ticket_relation.rel
     *
     * @mbg.generated Mon Apr 08 15:53:02 CDT 2019
     */
    public void setRel(String rel) {
        this.rel = rel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_ticket_relation.comment
     *
     * @return the value of m_prj_ticket_relation.comment
     *
     * @mbg.generated Mon Apr 08 15:53:02 CDT 2019
     */
    public String getComment() {
        return comment;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_ticket_relation
     *
     * @mbg.generated Mon Apr 08 15:53:02 CDT 2019
     */
    public TicketRelation withComment(String comment) {
        this.setComment(comment);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_ticket_relation.comment
     *
     * @param comment the value for m_prj_ticket_relation.comment
     *
     * @mbg.generated Mon Apr 08 15:53:02 CDT 2019
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    public enum Field {
        id,
        ticketid,
        tickettype,
        type,
        typeid,
        rel,
        comment;

        public boolean equalTo(Object value) {
            return name().equals(value);
        }
    }
}