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
/*Domain class of table m_comment*/
package com.esofthead.mycollab.common.domain;

import com.esofthead.mycollab.core.arguments.ValuedBean;
import com.esofthead.mycollab.core.db.metadata.Column;
import com.esofthead.mycollab.core.db.metadata.Table;
import java.util.Date;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.Length;

@SuppressWarnings("ucd")
@Table("m_comment")
class Comment extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_comment.id
     *
     * @mbggenerated Wed Jul 29 14:45:42 ICT 2015
     */
    @Column("id")
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_comment.createdUser
     *
     * @mbggenerated Wed Jul 29 14:45:42 ICT 2015
     */
    @Length(max=45, message="Field value is too long")
    @Column("createdUser")
    private String createduser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_comment.createdTime
     *
     * @mbggenerated Wed Jul 29 14:45:42 ICT 2015
     */
    @Column("createdTime")
    private Date createdtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_comment.type
     *
     * @mbggenerated Wed Jul 29 14:45:42 ICT 2015
     */
    @Length(max=45, message="Field value is too long")
    @Column("type")
    private String type;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_comment.sAccountId
     *
     * @mbggenerated Wed Jul 29 14:45:42 ICT 2015
     */
    @Column("sAccountId")
    private Integer saccountid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_comment.extraTypeId
     *
     * @mbggenerated Wed Jul 29 14:45:42 ICT 2015
     */
    @Column("extraTypeId")
    private Integer extratypeid;

    private static final long serialVersionUID = 1;

    public final boolean equals(Object obj) {
        if (obj == null) { return false;}
        if (obj == this) { return true;}
        if (obj.getClass() != getClass()) { return false;}
        Comment item = (Comment)obj;
        return new EqualsBuilder().append(id, item.id).build();
    }

    public final int hashCode() {
        return new HashCodeBuilder(307, 1905).append(id).build();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_comment.id
     *
     * @return the value of m_comment.id
     *
     * @mbggenerated Wed Jul 29 14:45:42 ICT 2015
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
     * @mbggenerated Wed Jul 29 14:45:42 ICT 2015
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
     * @mbggenerated Wed Jul 29 14:45:42 ICT 2015
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
     * @mbggenerated Wed Jul 29 14:45:42 ICT 2015
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
     * @mbggenerated Wed Jul 29 14:45:42 ICT 2015
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
     * @mbggenerated Wed Jul 29 14:45:42 ICT 2015
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
     * @mbggenerated Wed Jul 29 14:45:42 ICT 2015
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
     * @mbggenerated Wed Jul 29 14:45:42 ICT 2015
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
     * @mbggenerated Wed Jul 29 14:45:42 ICT 2015
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
     * @mbggenerated Wed Jul 29 14:45:42 ICT 2015
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
     * @mbggenerated Wed Jul 29 14:45:42 ICT 2015
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
     * @mbggenerated Wed Jul 29 14:45:42 ICT 2015
     */
    public void setExtratypeid(Integer extratypeid) {
        this.extratypeid = extratypeid;
    }
}