/*Domain class of table m_comment*/
package com.mycollab.common.domain;

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
@Table("m_comment")
@Alias("Comment")
public class Comment extends ValuedBean {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_comment.id
     *
     * @mbg.generated Wed Jan 09 17:30:11 CST 2019
     */
    @Column("id")
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_comment.createdUser
     *
     * @mbg.generated Wed Jan 09 17:30:11 CST 2019
     */
    @NotEmpty
    @Length(max=45, message="Field value is too long")
    @Column("createdUser")
    private String createduser;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_comment.createdTime
     *
     * @mbg.generated Wed Jan 09 17:30:11 CST 2019
     */
    @NotNull
    @Column("createdTime")
    private LocalDateTime createdtime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_comment.type
     *
     * @mbg.generated Wed Jan 09 17:30:11 CST 2019
     */
    @Length(max=45, message="Field value is too long")
    @Column("type")
    private String type;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_comment.typeId
     *
     * @mbg.generated Wed Jan 09 17:30:11 CST 2019
     */
    @Length(max=100, message="Field value is too long")
    @Column("typeId")
    private String typeid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_comment.sAccountId
     *
     * @mbg.generated Wed Jan 09 17:30:11 CST 2019
     */
    @Column("sAccountId")
    private Integer saccountid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_comment.extraTypeId
     *
     * @mbg.generated Wed Jan 09 17:30:11 CST 2019
     */
    @Column("extraTypeId")
    private Integer extratypeid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_comment.comment
     *
     * @mbg.generated Wed Jan 09 17:30:11 CST 2019
     */
    @Length(max=65535, message="Field value is too long")
    @Column("comment")
    private String comment;

    private static final long serialVersionUID = 1;

    public final boolean equals(Object obj) {
        if (obj == null) { return false;}
        if (obj == this) { return true;}
        if (!obj.getClass().isAssignableFrom(getClass())) { return false;}
        Comment item = (Comment)obj;
        return new EqualsBuilder().append(id, item.id).build();
    }

    public final int hashCode() {
        return new HashCodeBuilder(1801, 1543).append(id).build();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_comment.id
     *
     * @return the value of m_comment.id
     *
     * @mbg.generated Wed Jan 09 17:30:11 CST 2019
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_comment.id
     *
     * @param id the value for m_comment.id
     *
     * @mbg.generated Wed Jan 09 17:30:11 CST 2019
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_comment.createdUser
     *
     * @return the value of m_comment.createdUser
     *
     * @mbg.generated Wed Jan 09 17:30:11 CST 2019
     */
    public String getCreateduser() {
        return createduser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_comment.createdUser
     *
     * @param createduser the value for m_comment.createdUser
     *
     * @mbg.generated Wed Jan 09 17:30:11 CST 2019
     */
    public void setCreateduser(String createduser) {
        this.createduser = createduser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_comment.createdTime
     *
     * @return the value of m_comment.createdTime
     *
     * @mbg.generated Wed Jan 09 17:30:11 CST 2019
     */
    public LocalDateTime getCreatedtime() {
        return createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_comment.createdTime
     *
     * @param createdtime the value for m_comment.createdTime
     *
     * @mbg.generated Wed Jan 09 17:30:11 CST 2019
     */
    public void setCreatedtime(LocalDateTime createdtime) {
        this.createdtime = createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_comment.type
     *
     * @return the value of m_comment.type
     *
     * @mbg.generated Wed Jan 09 17:30:11 CST 2019
     */
    public String getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_comment.type
     *
     * @param type the value for m_comment.type
     *
     * @mbg.generated Wed Jan 09 17:30:11 CST 2019
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_comment.typeId
     *
     * @return the value of m_comment.typeId
     *
     * @mbg.generated Wed Jan 09 17:30:11 CST 2019
     */
    public String getTypeid() {
        return typeid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_comment.typeId
     *
     * @param typeid the value for m_comment.typeId
     *
     * @mbg.generated Wed Jan 09 17:30:11 CST 2019
     */
    public void setTypeid(String typeid) {
        this.typeid = typeid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_comment.sAccountId
     *
     * @return the value of m_comment.sAccountId
     *
     * @mbg.generated Wed Jan 09 17:30:11 CST 2019
     */
    public Integer getSaccountid() {
        return saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_comment.sAccountId
     *
     * @param saccountid the value for m_comment.sAccountId
     *
     * @mbg.generated Wed Jan 09 17:30:11 CST 2019
     */
    public void setSaccountid(Integer saccountid) {
        this.saccountid = saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_comment.extraTypeId
     *
     * @return the value of m_comment.extraTypeId
     *
     * @mbg.generated Wed Jan 09 17:30:11 CST 2019
     */
    public Integer getExtratypeid() {
        return extratypeid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_comment.extraTypeId
     *
     * @param extratypeid the value for m_comment.extraTypeId
     *
     * @mbg.generated Wed Jan 09 17:30:11 CST 2019
     */
    public void setExtratypeid(Integer extratypeid) {
        this.extratypeid = extratypeid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_comment.comment
     *
     * @return the value of m_comment.comment
     *
     * @mbg.generated Wed Jan 09 17:30:11 CST 2019
     */
    public String getComment() {
        return comment;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_comment.comment
     *
     * @param comment the value for m_comment.comment
     *
     * @mbg.generated Wed Jan 09 17:30:11 CST 2019
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    public enum Field {
        id,
        createduser,
        createdtime,
        type,
        typeid,
        saccountid,
        extratypeid,
        comment;

        public boolean equalTo(Object value) {
            return name().equals(value);
        }
    }
}