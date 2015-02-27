/*Domain class of table m_prj_milestone*/
package com.esofthead.mycollab.module.project.domain;

import com.esofthead.mycollab.core.arguments.ValuedBean;
import java.util.Date;

@SuppressWarnings("ucd")
@com.esofthead.mycollab.core.db.metadata.Table("m_prj_milestone")
public class Milestone extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_milestone.id
     *
     * @mbggenerated Fri Feb 27 09:55:30 ICT 2015
     */
    @com.esofthead.mycollab.core.db.metadata.Column("id")
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_milestone.name
     *
     * @mbggenerated Fri Feb 27 09:55:30 ICT 2015
     */
    @org.hibernate.validator.constraints.Length(max=255, message="Field value is too long")
    @com.esofthead.mycollab.core.db.metadata.Column("name")
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_milestone.startdate
     *
     * @mbggenerated Fri Feb 27 09:55:30 ICT 2015
     */
    @com.esofthead.mycollab.core.db.metadata.Column("startdate")
    private Date startdate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_milestone.enddate
     *
     * @mbggenerated Fri Feb 27 09:55:30 ICT 2015
     */
    @com.esofthead.mycollab.core.db.metadata.Column("enddate")
    private Date enddate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_milestone.owner
     *
     * @mbggenerated Fri Feb 27 09:55:30 ICT 2015
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    @com.esofthead.mycollab.core.db.metadata.Column("owner")
    private String owner;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_milestone.flag
     *
     * @mbggenerated Fri Feb 27 09:55:30 ICT 2015
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    @com.esofthead.mycollab.core.db.metadata.Column("flag")
    private String flag;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_milestone.projectid
     *
     * @mbggenerated Fri Feb 27 09:55:30 ICT 2015
     */
    @com.esofthead.mycollab.core.db.metadata.Column("projectid")
    private Integer projectid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_milestone.createdTime
     *
     * @mbggenerated Fri Feb 27 09:55:30 ICT 2015
     */
    @com.esofthead.mycollab.core.db.metadata.Column("createdTime")
    private Date createdtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_milestone.lastUpdatedTime
     *
     * @mbggenerated Fri Feb 27 09:55:30 ICT 2015
     */
    @com.esofthead.mycollab.core.db.metadata.Column("lastUpdatedTime")
    private Date lastupdatedtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_milestone.sAccountId
     *
     * @mbggenerated Fri Feb 27 09:55:30 ICT 2015
     */
    @com.esofthead.mycollab.core.db.metadata.Column("sAccountId")
    private Integer saccountid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_milestone.status
     *
     * @mbggenerated Fri Feb 27 09:55:30 ICT 2015
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    @com.esofthead.mycollab.core.db.metadata.Column("status")
    private String status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_milestone.createduser
     *
     * @mbggenerated Fri Feb 27 09:55:30 ICT 2015
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    @com.esofthead.mycollab.core.db.metadata.Column("createduser")
    private String createduser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_milestone.prjKey
     *
     * @mbggenerated Fri Feb 27 09:55:30 ICT 2015
     */
    @com.esofthead.mycollab.core.db.metadata.Column("prjKey")
    private Integer prjkey;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_milestone.description
     *
     * @mbggenerated Fri Feb 27 09:55:30 ICT 2015
     */
    @org.hibernate.validator.constraints.Length(max=65535, message="Field value is too long")
    @com.esofthead.mycollab.core.db.metadata.Column("description")
    private String description;

    private static final long serialVersionUID = 1;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_milestone.id
     *
     * @return the value of m_prj_milestone.id
     *
     * @mbggenerated Fri Feb 27 09:55:30 ICT 2015
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_milestone.id
     *
     * @param id the value for m_prj_milestone.id
     *
     * @mbggenerated Fri Feb 27 09:55:30 ICT 2015
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_milestone.name
     *
     * @return the value of m_prj_milestone.name
     *
     * @mbggenerated Fri Feb 27 09:55:30 ICT 2015
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_milestone.name
     *
     * @param name the value for m_prj_milestone.name
     *
     * @mbggenerated Fri Feb 27 09:55:30 ICT 2015
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_milestone.startdate
     *
     * @return the value of m_prj_milestone.startdate
     *
     * @mbggenerated Fri Feb 27 09:55:30 ICT 2015
     */
    public Date getStartdate() {
        return startdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_milestone.startdate
     *
     * @param startdate the value for m_prj_milestone.startdate
     *
     * @mbggenerated Fri Feb 27 09:55:30 ICT 2015
     */
    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_milestone.enddate
     *
     * @return the value of m_prj_milestone.enddate
     *
     * @mbggenerated Fri Feb 27 09:55:30 ICT 2015
     */
    public Date getEnddate() {
        return enddate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_milestone.enddate
     *
     * @param enddate the value for m_prj_milestone.enddate
     *
     * @mbggenerated Fri Feb 27 09:55:30 ICT 2015
     */
    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_milestone.owner
     *
     * @return the value of m_prj_milestone.owner
     *
     * @mbggenerated Fri Feb 27 09:55:30 ICT 2015
     */
    public String getOwner() {
        return owner;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_milestone.owner
     *
     * @param owner the value for m_prj_milestone.owner
     *
     * @mbggenerated Fri Feb 27 09:55:30 ICT 2015
     */
    public void setOwner(String owner) {
        this.owner = owner;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_milestone.flag
     *
     * @return the value of m_prj_milestone.flag
     *
     * @mbggenerated Fri Feb 27 09:55:30 ICT 2015
     */
    public String getFlag() {
        return flag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_milestone.flag
     *
     * @param flag the value for m_prj_milestone.flag
     *
     * @mbggenerated Fri Feb 27 09:55:30 ICT 2015
     */
    public void setFlag(String flag) {
        this.flag = flag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_milestone.projectid
     *
     * @return the value of m_prj_milestone.projectid
     *
     * @mbggenerated Fri Feb 27 09:55:30 ICT 2015
     */
    public Integer getProjectid() {
        return projectid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_milestone.projectid
     *
     * @param projectid the value for m_prj_milestone.projectid
     *
     * @mbggenerated Fri Feb 27 09:55:30 ICT 2015
     */
    public void setProjectid(Integer projectid) {
        this.projectid = projectid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_milestone.createdTime
     *
     * @return the value of m_prj_milestone.createdTime
     *
     * @mbggenerated Fri Feb 27 09:55:30 ICT 2015
     */
    public Date getCreatedtime() {
        return createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_milestone.createdTime
     *
     * @param createdtime the value for m_prj_milestone.createdTime
     *
     * @mbggenerated Fri Feb 27 09:55:30 ICT 2015
     */
    public void setCreatedtime(Date createdtime) {
        this.createdtime = createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_milestone.lastUpdatedTime
     *
     * @return the value of m_prj_milestone.lastUpdatedTime
     *
     * @mbggenerated Fri Feb 27 09:55:30 ICT 2015
     */
    public Date getLastupdatedtime() {
        return lastupdatedtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_milestone.lastUpdatedTime
     *
     * @param lastupdatedtime the value for m_prj_milestone.lastUpdatedTime
     *
     * @mbggenerated Fri Feb 27 09:55:30 ICT 2015
     */
    public void setLastupdatedtime(Date lastupdatedtime) {
        this.lastupdatedtime = lastupdatedtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_milestone.sAccountId
     *
     * @return the value of m_prj_milestone.sAccountId
     *
     * @mbggenerated Fri Feb 27 09:55:30 ICT 2015
     */
    public Integer getSaccountid() {
        return saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_milestone.sAccountId
     *
     * @param saccountid the value for m_prj_milestone.sAccountId
     *
     * @mbggenerated Fri Feb 27 09:55:30 ICT 2015
     */
    public void setSaccountid(Integer saccountid) {
        this.saccountid = saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_milestone.status
     *
     * @return the value of m_prj_milestone.status
     *
     * @mbggenerated Fri Feb 27 09:55:30 ICT 2015
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_milestone.status
     *
     * @param status the value for m_prj_milestone.status
     *
     * @mbggenerated Fri Feb 27 09:55:30 ICT 2015
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_milestone.createduser
     *
     * @return the value of m_prj_milestone.createduser
     *
     * @mbggenerated Fri Feb 27 09:55:30 ICT 2015
     */
    public String getCreateduser() {
        return createduser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_milestone.createduser
     *
     * @param createduser the value for m_prj_milestone.createduser
     *
     * @mbggenerated Fri Feb 27 09:55:30 ICT 2015
     */
    public void setCreateduser(String createduser) {
        this.createduser = createduser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_milestone.prjKey
     *
     * @return the value of m_prj_milestone.prjKey
     *
     * @mbggenerated Fri Feb 27 09:55:30 ICT 2015
     */
    public Integer getPrjkey() {
        return prjkey;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_milestone.prjKey
     *
     * @param prjkey the value for m_prj_milestone.prjKey
     *
     * @mbggenerated Fri Feb 27 09:55:30 ICT 2015
     */
    public void setPrjkey(Integer prjkey) {
        this.prjkey = prjkey;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_milestone.description
     *
     * @return the value of m_prj_milestone.description
     *
     * @mbggenerated Fri Feb 27 09:55:30 ICT 2015
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_milestone.description
     *
     * @param description the value for m_prj_milestone.description
     *
     * @mbggenerated Fri Feb 27 09:55:30 ICT 2015
     */
    public void setDescription(String description) {
        this.description = description;
    }

    public static enum Field {
        id,
        name,
        startdate,
        enddate,
        owner,
        flag,
        projectid,
        createdtime,
        lastupdatedtime,
        saccountid,
        status,
        createduser,
        prjkey,
        description;

        public boolean equalTo(Object value) {
            return name().equals(value);
        }
    }
}