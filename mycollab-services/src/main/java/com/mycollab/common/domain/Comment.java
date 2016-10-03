/*Domain class of table m_comment*/
package com.mycollab.common.domain;

import com.mycollab.core.arguments.ValuedBean;
import com.mycollab.db.metadata.Column;
import com.mycollab.db.metadata.Table;
import java.util.Date;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.Length;

@SuppressWarnings("ucd")
@Table("m_comment")
class Comment extends ValuedBean {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_comment.id
     *
     * @mbg.generated Mon Oct 03 14:51:45 ICT 2016
     */
    @Column("id")
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_comment.createdUser
     *
     * @mbg.generated Mon Oct 03 14:51:45 ICT 2016
     */
    @Length(max=45, message="Field value is too long")
    @Column("createdUser")
    private String createduser;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_comment.createdTime
     *
     * @mbg.generated Mon Oct 03 14:51:45 ICT 2016
     */
    @Column("createdTime")
    private Date createdtime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_comment.type
     *
     * @mbg.generated Mon Oct 03 14:51:45 ICT 2016
     */
    @Length(max=45, message="Field value is too long")
    @Column("type")
    private String type;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_comment.sAccountId
     *
     * @mbg.generated Mon Oct 03 14:51:45 ICT 2016
     */
    @Column("sAccountId")
    private Integer saccountid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_comment.extraTypeId
     *
     * @mbg.generated Mon Oct 03 14:51:45 ICT 2016
     */
    @Column("extraTypeId")
    private Integer extratypeid;

    private static final long serialVersionUID = 1;

    public final boolean equals(Object obj) {
        if (obj == null) { return false;}
        if (obj == this) { return true;}
        if (!obj.getClass().isAssignableFrom(getClass())) { return false;}
        Comment item = (Comment)obj;
        return new EqualsBuilder().append(id, item.id).build();
    }

    public final int hashCode() {
        return new HashCodeBuilder(731, 525).append(id).build();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_comment.id
     *
     * @return the value of m_comment.id
     *
     * @mbg.generated Mon Oct 03 14:51:45 ICT 2016
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
     * @mbg.generated Mon Oct 03 14:51:45 ICT 2016
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
     * @mbg.generated Mon Oct 03 14:51:45 ICT 2016
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
     * @mbg.generated Mon Oct 03 14:51:45 ICT 2016
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
     * @mbg.generated Mon Oct 03 14:51:45 ICT 2016
     */
    public Date getCreatedtime() {
        return createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_comment.createdTime
     *
     * @param createdtime the value for m_comment.createdTime
     *
     * @mbg.generated Mon Oct 03 14:51:45 ICT 2016
     */
    public void setCreatedtime(Date createdtime) {
        this.createdtime = createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_comment.type
     *
     * @return the value of m_comment.type
     *
     * @mbg.generated Mon Oct 03 14:51:45 ICT 2016
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
     * @mbg.generated Mon Oct 03 14:51:45 ICT 2016
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_comment.sAccountId
     *
     * @return the value of m_comment.sAccountId
     *
     * @mbg.generated Mon Oct 03 14:51:45 ICT 2016
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
     * @mbg.generated Mon Oct 03 14:51:45 ICT 2016
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
     * @mbg.generated Mon Oct 03 14:51:45 ICT 2016
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
     * @mbg.generated Mon Oct 03 14:51:45 ICT 2016
     */
    public void setExtratypeid(Integer extratypeid) {
        this.extratypeid = extratypeid;
    }
}