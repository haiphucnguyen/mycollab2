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
/*Domain class of table m_prj_time_logging*/
package com.esofthead.mycollab.module.project.domain;

import com.esofthead.mycollab.core.arguments.ValuedBean;
import java.util.Date;

@SuppressWarnings("ucd")
@com.esofthead.mycollab.core.db.metadata.Table("m_prj_time_logging")
public class ItemTimeLogging extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_time_logging.id
     *
     * @mbggenerated Mon May 18 10:06:58 ICT 2015
     */
    @com.esofthead.mycollab.core.db.metadata.Column("id")
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_time_logging.projectId
     *
     * @mbggenerated Mon May 18 10:06:58 ICT 2015
     */
    @com.esofthead.mycollab.core.db.metadata.Column("projectId")
    private Integer projectid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_time_logging.type
     *
     * @mbggenerated Mon May 18 10:06:58 ICT 2015
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    @com.esofthead.mycollab.core.db.metadata.Column("type")
    private String type;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_time_logging.typeid
     *
     * @mbggenerated Mon May 18 10:06:58 ICT 2015
     */
    @com.esofthead.mycollab.core.db.metadata.Column("typeid")
    private Integer typeid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_time_logging.logValue
     *
     * @mbggenerated Mon May 18 10:06:58 ICT 2015
     */
    @com.esofthead.mycollab.core.db.metadata.Column("logValue")
    private Double logvalue;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_time_logging.loguser
     *
     * @mbggenerated Mon May 18 10:06:58 ICT 2015
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    @com.esofthead.mycollab.core.db.metadata.Column("loguser")
    private String loguser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_time_logging.createdTime
     *
     * @mbggenerated Mon May 18 10:06:58 ICT 2015
     */
    @com.esofthead.mycollab.core.db.metadata.Column("createdTime")
    private Date createdtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_time_logging.lastUpdatedTime
     *
     * @mbggenerated Mon May 18 10:06:58 ICT 2015
     */
    @com.esofthead.mycollab.core.db.metadata.Column("lastUpdatedTime")
    private Date lastupdatedtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_time_logging.sAccountId
     *
     * @mbggenerated Mon May 18 10:06:58 ICT 2015
     */
    @com.esofthead.mycollab.core.db.metadata.Column("sAccountId")
    private Integer saccountid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_time_logging.logForDay
     *
     * @mbggenerated Mon May 18 10:06:58 ICT 2015
     */
    @com.esofthead.mycollab.core.db.metadata.Column("logForDay")
    private Date logforday;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_time_logging.isBillable
     *
     * @mbggenerated Mon May 18 10:06:58 ICT 2015
     */
    @com.esofthead.mycollab.core.db.metadata.Column("isBillable")
    private Boolean isbillable;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_time_logging.createdUser
     *
     * @mbggenerated Mon May 18 10:06:58 ICT 2015
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    @com.esofthead.mycollab.core.db.metadata.Column("createdUser")
    private String createduser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_time_logging.note
     *
     * @mbggenerated Mon May 18 10:06:58 ICT 2015
     */
    @org.hibernate.validator.constraints.Length(max=65535, message="Field value is too long")
    @com.esofthead.mycollab.core.db.metadata.Column("note")
    private String note;

    private static final long serialVersionUID = 1;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_time_logging.id
     *
     * @return the value of m_prj_time_logging.id
     *
     * @mbggenerated Mon May 18 10:06:58 ICT 2015
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_time_logging.id
     *
     * @param id the value for m_prj_time_logging.id
     *
     * @mbggenerated Mon May 18 10:06:58 ICT 2015
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_time_logging.projectId
     *
     * @return the value of m_prj_time_logging.projectId
     *
     * @mbggenerated Mon May 18 10:06:58 ICT 2015
     */
    public Integer getProjectid() {
        return projectid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_time_logging.projectId
     *
     * @param projectid the value for m_prj_time_logging.projectId
     *
     * @mbggenerated Mon May 18 10:06:58 ICT 2015
     */
    public void setProjectid(Integer projectid) {
        this.projectid = projectid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_time_logging.type
     *
     * @return the value of m_prj_time_logging.type
     *
     * @mbggenerated Mon May 18 10:06:58 ICT 2015
     */
    public String getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_time_logging.type
     *
     * @param type the value for m_prj_time_logging.type
     *
     * @mbggenerated Mon May 18 10:06:58 ICT 2015
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_time_logging.typeid
     *
     * @return the value of m_prj_time_logging.typeid
     *
     * @mbggenerated Mon May 18 10:06:58 ICT 2015
     */
    public Integer getTypeid() {
        return typeid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_time_logging.typeid
     *
     * @param typeid the value for m_prj_time_logging.typeid
     *
     * @mbggenerated Mon May 18 10:06:58 ICT 2015
     */
    public void setTypeid(Integer typeid) {
        this.typeid = typeid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_time_logging.logValue
     *
     * @return the value of m_prj_time_logging.logValue
     *
     * @mbggenerated Mon May 18 10:06:58 ICT 2015
     */
    public Double getLogvalue() {
        return logvalue;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_time_logging.logValue
     *
     * @param logvalue the value for m_prj_time_logging.logValue
     *
     * @mbggenerated Mon May 18 10:06:58 ICT 2015
     */
    public void setLogvalue(Double logvalue) {
        this.logvalue = logvalue;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_time_logging.loguser
     *
     * @return the value of m_prj_time_logging.loguser
     *
     * @mbggenerated Mon May 18 10:06:58 ICT 2015
     */
    public String getLoguser() {
        return loguser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_time_logging.loguser
     *
     * @param loguser the value for m_prj_time_logging.loguser
     *
     * @mbggenerated Mon May 18 10:06:58 ICT 2015
     */
    public void setLoguser(String loguser) {
        this.loguser = loguser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_time_logging.createdTime
     *
     * @return the value of m_prj_time_logging.createdTime
     *
     * @mbggenerated Mon May 18 10:06:58 ICT 2015
     */
    public Date getCreatedtime() {
        return createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_time_logging.createdTime
     *
     * @param createdtime the value for m_prj_time_logging.createdTime
     *
     * @mbggenerated Mon May 18 10:06:58 ICT 2015
     */
    public void setCreatedtime(Date createdtime) {
        this.createdtime = createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_time_logging.lastUpdatedTime
     *
     * @return the value of m_prj_time_logging.lastUpdatedTime
     *
     * @mbggenerated Mon May 18 10:06:58 ICT 2015
     */
    public Date getLastupdatedtime() {
        return lastupdatedtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_time_logging.lastUpdatedTime
     *
     * @param lastupdatedtime the value for m_prj_time_logging.lastUpdatedTime
     *
     * @mbggenerated Mon May 18 10:06:58 ICT 2015
     */
    public void setLastupdatedtime(Date lastupdatedtime) {
        this.lastupdatedtime = lastupdatedtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_time_logging.sAccountId
     *
     * @return the value of m_prj_time_logging.sAccountId
     *
     * @mbggenerated Mon May 18 10:06:58 ICT 2015
     */
    public Integer getSaccountid() {
        return saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_time_logging.sAccountId
     *
     * @param saccountid the value for m_prj_time_logging.sAccountId
     *
     * @mbggenerated Mon May 18 10:06:58 ICT 2015
     */
    public void setSaccountid(Integer saccountid) {
        this.saccountid = saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_time_logging.logForDay
     *
     * @return the value of m_prj_time_logging.logForDay
     *
     * @mbggenerated Mon May 18 10:06:58 ICT 2015
     */
    public Date getLogforday() {
        return logforday;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_time_logging.logForDay
     *
     * @param logforday the value for m_prj_time_logging.logForDay
     *
     * @mbggenerated Mon May 18 10:06:58 ICT 2015
     */
    public void setLogforday(Date logforday) {
        this.logforday = logforday;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_time_logging.isBillable
     *
     * @return the value of m_prj_time_logging.isBillable
     *
     * @mbggenerated Mon May 18 10:06:58 ICT 2015
     */
    public Boolean getIsbillable() {
        return isbillable;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_time_logging.isBillable
     *
     * @param isbillable the value for m_prj_time_logging.isBillable
     *
     * @mbggenerated Mon May 18 10:06:58 ICT 2015
     */
    public void setIsbillable(Boolean isbillable) {
        this.isbillable = isbillable;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_time_logging.createdUser
     *
     * @return the value of m_prj_time_logging.createdUser
     *
     * @mbggenerated Mon May 18 10:06:58 ICT 2015
     */
    public String getCreateduser() {
        return createduser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_time_logging.createdUser
     *
     * @param createduser the value for m_prj_time_logging.createdUser
     *
     * @mbggenerated Mon May 18 10:06:58 ICT 2015
     */
    public void setCreateduser(String createduser) {
        this.createduser = createduser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_time_logging.note
     *
     * @return the value of m_prj_time_logging.note
     *
     * @mbggenerated Mon May 18 10:06:58 ICT 2015
     */
    public String getNote() {
        return note;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_time_logging.note
     *
     * @param note the value for m_prj_time_logging.note
     *
     * @mbggenerated Mon May 18 10:06:58 ICT 2015
     */
    public void setNote(String note) {
        this.note = note;
    }

    public static enum Field {
        id,
        projectid,
        type,
        typeid,
        logvalue,
        loguser,
        createdtime,
        lastupdatedtime,
        saccountid,
        logforday,
        isbillable,
        createduser,
        note;

        public boolean equalTo(Object value) {
            return name().equals(value);
        }
    }
}