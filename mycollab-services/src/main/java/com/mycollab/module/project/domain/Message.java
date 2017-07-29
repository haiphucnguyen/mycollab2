/*Domain class of table m_prj_message*/
package com.mycollab.module.project.domain;

import com.mycollab.core.arguments.ValuedBean;
import com.mycollab.db.metadata.Column;
import com.mycollab.db.metadata.Table;
import java.util.Date;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.Length;

@SuppressWarnings("ucd")
@Table("m_prj_message")
public class Message extends ValuedBean {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_message.id
     *
     * @mbg.generated Sun Jun 25 11:59:49 ICT 2017
     */
    @Column("id")
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_message.title
     *
     * @mbg.generated Sun Jun 25 11:59:49 ICT 2017
     */
    @Length(max=1000, message="Field value is too long")
    @Column("title")
    private String title;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_message.posteddate
     *
     * @mbg.generated Sun Jun 25 11:59:49 ICT 2017
     */
    @Column("posteddate")
    private Date posteddate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_message.posteduser
     *
     * @mbg.generated Sun Jun 25 11:59:49 ICT 2017
     */
    @Length(max=45, message="Field value is too long")
    @Column("posteduser")
    private String posteduser;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_message.projectid
     *
     * @mbg.generated Sun Jun 25 11:59:49 ICT 2017
     */
    @Column("projectid")
    private Integer projectid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_message.category
     *
     * @mbg.generated Sun Jun 25 11:59:49 ICT 2017
     */
    @Length(max=45, message="Field value is too long")
    @Column("category")
    private String category;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_message.createdTime
     *
     * @mbg.generated Sun Jun 25 11:59:49 ICT 2017
     */
    @Column("createdTime")
    private Date createdtime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_message.lastUpdatedTime
     *
     * @mbg.generated Sun Jun 25 11:59:49 ICT 2017
     */
    @Column("lastUpdatedTime")
    private Date lastupdatedtime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_message.sAccountId
     *
     * @mbg.generated Sun Jun 25 11:59:49 ICT 2017
     */
    @Column("sAccountId")
    private Integer saccountid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_message.isStick
     *
     * @mbg.generated Sun Jun 25 11:59:49 ICT 2017
     */
    @Column("isStick")
    private Boolean isstick;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_message.prjKey
     *
     * @mbg.generated Sun Jun 25 11:59:49 ICT 2017
     */
    @Column("prjKey")
    private Integer prjkey;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_message.message
     *
     * @mbg.generated Sun Jun 25 11:59:49 ICT 2017
     */
    @Length(max=16777215, message="Field value is too long")
    @Column("message")
    private String message;

    private static final long serialVersionUID = 1;

    public final boolean equals(Object obj) {
        if (obj == null) { return false;}
        if (obj == this) { return true;}
        if (!obj.getClass().isAssignableFrom(getClass())) { return false;}
        Message item = (Message)obj;
        return new EqualsBuilder().append(id, item.id).build();
    }

    public final int hashCode() {
        return new HashCodeBuilder(309, 1025).append(id).build();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_message.id
     *
     * @return the value of m_prj_message.id
     *
     * @mbg.generated Sun Jun 25 11:59:49 ICT 2017
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
     * @mbg.generated Sun Jun 25 11:59:49 ICT 2017
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
     * @mbg.generated Sun Jun 25 11:59:49 ICT 2017
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
     * @mbg.generated Sun Jun 25 11:59:49 ICT 2017
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
     * @mbg.generated Sun Jun 25 11:59:49 ICT 2017
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
     * @mbg.generated Sun Jun 25 11:59:49 ICT 2017
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
     * @mbg.generated Sun Jun 25 11:59:49 ICT 2017
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
     * @mbg.generated Sun Jun 25 11:59:49 ICT 2017
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
     * @mbg.generated Sun Jun 25 11:59:49 ICT 2017
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
     * @mbg.generated Sun Jun 25 11:59:49 ICT 2017
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
     * @mbg.generated Sun Jun 25 11:59:49 ICT 2017
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
     * @mbg.generated Sun Jun 25 11:59:49 ICT 2017
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
     * @mbg.generated Sun Jun 25 11:59:49 ICT 2017
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
     * @mbg.generated Sun Jun 25 11:59:49 ICT 2017
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
     * @mbg.generated Sun Jun 25 11:59:49 ICT 2017
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
     * @mbg.generated Sun Jun 25 11:59:49 ICT 2017
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
     * @mbg.generated Sun Jun 25 11:59:49 ICT 2017
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
     * @mbg.generated Sun Jun 25 11:59:49 ICT 2017
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
     * @mbg.generated Sun Jun 25 11:59:49 ICT 2017
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
     * @mbg.generated Sun Jun 25 11:59:49 ICT 2017
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
     * @mbg.generated Sun Jun 25 11:59:49 ICT 2017
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
     * @mbg.generated Sun Jun 25 11:59:49 ICT 2017
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
     * @mbg.generated Sun Jun 25 11:59:49 ICT 2017
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
     * @mbg.generated Sun Jun 25 11:59:49 ICT 2017
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