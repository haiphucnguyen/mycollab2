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
/*Domain class of table m_prj_message*/
package com.esofthead.mycollab.module.project.domain;

import com.esofthead.mycollab.core.arguments.ValuedBean;
import com.esofthead.mycollab.core.db.metadata.Column;
import com.esofthead.mycollab.core.db.metadata.Table;
import java.util.Date;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.Length;

@SuppressWarnings("ucd")
@Table("m_prj_message")
public class Message extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_message.id
     *
     * @mbggenerated Wed Jul 29 12:20:03 ICT 2015
     */
    @Column("id")
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_message.title
     *
     * @mbggenerated Wed Jul 29 12:20:03 ICT 2015
     */
    @Length(max=1000, message="Field value is too long")
    @Column("title")
    private String title;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_message.posteddate
     *
     * @mbggenerated Wed Jul 29 12:20:03 ICT 2015
     */
    @Column("posteddate")
    private Date posteddate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_message.posteduser
     *
     * @mbggenerated Wed Jul 29 12:20:03 ICT 2015
     */
    @Length(max=45, message="Field value is too long")
    @Column("posteduser")
    private String posteduser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_message.projectid
     *
     * @mbggenerated Wed Jul 29 12:20:03 ICT 2015
     */
    @Column("projectid")
    private Integer projectid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_message.category
     *
     * @mbggenerated Wed Jul 29 12:20:03 ICT 2015
     */
    @Length(max=45, message="Field value is too long")
    @Column("category")
    private String category;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_message.createdTime
     *
     * @mbggenerated Wed Jul 29 12:20:03 ICT 2015
     */
    @Column("createdTime")
    private Date createdtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_message.lastUpdatedTime
     *
     * @mbggenerated Wed Jul 29 12:20:03 ICT 2015
     */
    @Column("lastUpdatedTime")
    private Date lastupdatedtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_message.sAccountId
     *
     * @mbggenerated Wed Jul 29 12:20:03 ICT 2015
     */
    @Column("sAccountId")
    private Integer saccountid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_message.isStick
     *
     * @mbggenerated Wed Jul 29 12:20:03 ICT 2015
     */
    @Column("isStick")
    private Boolean isstick;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_message.prjKey
     *
     * @mbggenerated Wed Jul 29 12:20:03 ICT 2015
     */
    @Column("prjKey")
    private Integer prjkey;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_message.message
     *
     * @mbggenerated Wed Jul 29 12:20:03 ICT 2015
     */
    @Length(max=65535, message="Field value is too long")
    @Column("message")
    private String message;

    private static final long serialVersionUID = 1;

    public final boolean equals(Object obj) {
        if (obj == null) { return false;}
        if (obj == this) { return true;}
        if (obj.getClass() != getClass()) { return false;}
        Message item = (Message)obj;
        return new EqualsBuilder().append(id, item.id).build();
    }

    public final int hashCode() {
        return new HashCodeBuilder(1035, 467).append(id).build();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_message.id
     *
     * @return the value of m_prj_message.id
     *
     * @mbggenerated Wed Jul 29 12:20:03 ICT 2015
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_message.id
     *
     * @param id the value for m_prj_message.id
     *
     * @mbggenerated Wed Jul 29 12:20:03 ICT 2015
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_message.title
     *
     * @return the value of m_prj_message.title
     *
     * @mbggenerated Wed Jul 29 12:20:03 ICT 2015
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_message.title
     *
     * @param title the value for m_prj_message.title
     *
     * @mbggenerated Wed Jul 29 12:20:03 ICT 2015
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_message.posteddate
     *
     * @return the value of m_prj_message.posteddate
     *
     * @mbggenerated Wed Jul 29 12:20:03 ICT 2015
     */
    public Date getPosteddate() {
        return posteddate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_message.posteddate
     *
     * @param posteddate the value for m_prj_message.posteddate
     *
     * @mbggenerated Wed Jul 29 12:20:03 ICT 2015
     */
    public void setPosteddate(Date posteddate) {
        this.posteddate = posteddate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_message.posteduser
     *
     * @return the value of m_prj_message.posteduser
     *
     * @mbggenerated Wed Jul 29 12:20:03 ICT 2015
     */
    public String getPosteduser() {
        return posteduser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_message.posteduser
     *
     * @param posteduser the value for m_prj_message.posteduser
     *
     * @mbggenerated Wed Jul 29 12:20:03 ICT 2015
     */
    public void setPosteduser(String posteduser) {
        this.posteduser = posteduser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_message.projectid
     *
     * @return the value of m_prj_message.projectid
     *
     * @mbggenerated Wed Jul 29 12:20:03 ICT 2015
     */
    public Integer getProjectid() {
        return projectid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_message.projectid
     *
     * @param projectid the value for m_prj_message.projectid
     *
     * @mbggenerated Wed Jul 29 12:20:03 ICT 2015
     */
    public void setProjectid(Integer projectid) {
        this.projectid = projectid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_message.category
     *
     * @return the value of m_prj_message.category
     *
     * @mbggenerated Wed Jul 29 12:20:03 ICT 2015
     */
    public String getCategory() {
        return category;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_message.category
     *
     * @param category the value for m_prj_message.category
     *
     * @mbggenerated Wed Jul 29 12:20:03 ICT 2015
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_message.createdTime
     *
     * @return the value of m_prj_message.createdTime
     *
     * @mbggenerated Wed Jul 29 12:20:03 ICT 2015
     */
    public Date getCreatedtime() {
        return createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_message.createdTime
     *
     * @param createdtime the value for m_prj_message.createdTime
     *
     * @mbggenerated Wed Jul 29 12:20:03 ICT 2015
     */
    public void setCreatedtime(Date createdtime) {
        this.createdtime = createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_message.lastUpdatedTime
     *
     * @return the value of m_prj_message.lastUpdatedTime
     *
     * @mbggenerated Wed Jul 29 12:20:03 ICT 2015
     */
    public Date getLastupdatedtime() {
        return lastupdatedtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_message.lastUpdatedTime
     *
     * @param lastupdatedtime the value for m_prj_message.lastUpdatedTime
     *
     * @mbggenerated Wed Jul 29 12:20:03 ICT 2015
     */
    public void setLastupdatedtime(Date lastupdatedtime) {
        this.lastupdatedtime = lastupdatedtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_message.sAccountId
     *
     * @return the value of m_prj_message.sAccountId
     *
     * @mbggenerated Wed Jul 29 12:20:03 ICT 2015
     */
    public Integer getSaccountid() {
        return saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_message.sAccountId
     *
     * @param saccountid the value for m_prj_message.sAccountId
     *
     * @mbggenerated Wed Jul 29 12:20:03 ICT 2015
     */
    public void setSaccountid(Integer saccountid) {
        this.saccountid = saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_message.isStick
     *
     * @return the value of m_prj_message.isStick
     *
     * @mbggenerated Wed Jul 29 12:20:03 ICT 2015
     */
    public Boolean getIsstick() {
        return isstick;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_message.isStick
     *
     * @param isstick the value for m_prj_message.isStick
     *
     * @mbggenerated Wed Jul 29 12:20:03 ICT 2015
     */
    public void setIsstick(Boolean isstick) {
        this.isstick = isstick;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_message.prjKey
     *
     * @return the value of m_prj_message.prjKey
     *
     * @mbggenerated Wed Jul 29 12:20:03 ICT 2015
     */
    public Integer getPrjkey() {
        return prjkey;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_message.prjKey
     *
     * @param prjkey the value for m_prj_message.prjKey
     *
     * @mbggenerated Wed Jul 29 12:20:03 ICT 2015
     */
    public void setPrjkey(Integer prjkey) {
        this.prjkey = prjkey;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_message.message
     *
     * @return the value of m_prj_message.message
     *
     * @mbggenerated Wed Jul 29 12:20:03 ICT 2015
     */
    public String getMessage() {
        return message;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_message.message
     *
     * @param message the value for m_prj_message.message
     *
     * @mbggenerated Wed Jul 29 12:20:03 ICT 2015
     */
    public void setMessage(String message) {
        this.message = message;
    }

    public enum Field {
        id,
        title,
        posteddate,
        posteduser,
        projectid,
        category,
        createdtime,
        lastupdatedtime,
        saccountid,
        isstick,
        prjkey,
        message;

        public boolean equalTo(Object value) {
            return name().equals(value);
        }
    }
}