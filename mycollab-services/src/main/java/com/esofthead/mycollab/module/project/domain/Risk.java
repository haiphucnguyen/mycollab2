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
/*Domain class of table m_prj_risk*/
package com.esofthead.mycollab.module.project.domain;

import com.esofthead.mycollab.core.arguments.ValuedBean;
import com.esofthead.mycollab.core.db.metadata.Column;
import com.esofthead.mycollab.core.db.metadata.Table;
import java.util.Date;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.Length;

@SuppressWarnings("ucd")
@Table("m_prj_risk")
public class Risk extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_risk.id
     *
     * @mbggenerated Fri Aug 28 22:04:32 ICT 2015
     */
    @Column("id")
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_risk.riskname
     *
     * @mbggenerated Fri Aug 28 22:04:32 ICT 2015
     */
    @Length(max=400, message="Field value is too long")
    @Column("riskname")
    private String riskname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_risk.projectid
     *
     * @mbggenerated Fri Aug 28 22:04:32 ICT 2015
     */
    @Column("projectid")
    private Integer projectid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_risk.raisedbyuser
     *
     * @mbggenerated Fri Aug 28 22:04:32 ICT 2015
     */
    @Length(max=45, message="Field value is too long")
    @Column("raisedbyuser")
    private String raisedbyuser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_risk.assigntouser
     *
     * @mbggenerated Fri Aug 28 22:04:32 ICT 2015
     */
    @Length(max=45, message="Field value is too long")
    @Column("assigntouser")
    private String assigntouser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_risk.consequence
     *
     * @mbggenerated Fri Aug 28 22:04:32 ICT 2015
     */
    @Length(max=45, message="Field value is too long")
    @Column("consequence")
    private String consequence;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_risk.probalitity
     *
     * @mbggenerated Fri Aug 28 22:04:32 ICT 2015
     */
    @Length(max=45, message="Field value is too long")
    @Column("probalitity")
    private String probalitity;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_risk.status
     *
     * @mbggenerated Fri Aug 28 22:04:32 ICT 2015
     */
    @Length(max=45, message="Field value is too long")
    @Column("status")
    private String status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_risk.dateraised
     *
     * @mbggenerated Fri Aug 28 22:04:32 ICT 2015
     */
    @Column("dateraised")
    private Date dateraised;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_risk.datedue
     *
     * @mbggenerated Fri Aug 28 22:04:32 ICT 2015
     */
    @Column("datedue")
    private Date datedue;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_risk.response
     *
     * @mbggenerated Fri Aug 28 22:04:32 ICT 2015
     */
    @Length(max=255, message="Field value is too long")
    @Column("response")
    private String response;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_risk.resolution
     *
     * @mbggenerated Fri Aug 28 22:04:32 ICT 2015
     */
    @Length(max=4000, message="Field value is too long")
    @Column("resolution")
    private String resolution;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_risk.level
     *
     * @mbggenerated Fri Aug 28 22:04:32 ICT 2015
     */
    @Column("level")
    private Double level;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_risk.source
     *
     * @mbggenerated Fri Aug 28 22:04:32 ICT 2015
     */
    @Length(max=45, message="Field value is too long")
    @Column("source")
    private String source;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_risk.createdTime
     *
     * @mbggenerated Fri Aug 28 22:04:32 ICT 2015
     */
    @Column("createdTime")
    private Date createdtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_risk.lastUpdatedTime
     *
     * @mbggenerated Fri Aug 28 22:04:32 ICT 2015
     */
    @Column("lastUpdatedTime")
    private Date lastupdatedtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_risk.type
     *
     * @mbggenerated Fri Aug 28 22:04:32 ICT 2015
     */
    @Length(max=45, message="Field value is too long")
    @Column("type")
    private String type;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_risk.typeid
     *
     * @mbggenerated Fri Aug 28 22:04:32 ICT 2015
     */
    @Column("typeid")
    private Integer typeid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_risk.sAccountId
     *
     * @mbggenerated Fri Aug 28 22:04:32 ICT 2015
     */
    @Column("sAccountId")
    private Integer saccountid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_risk.prjKey
     *
     * @mbggenerated Fri Aug 28 22:04:32 ICT 2015
     */
    @Column("prjKey")
    private Integer prjkey;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_prj_risk.description
     *
     * @mbggenerated Fri Aug 28 22:04:32 ICT 2015
     */
    @Length(max=65535, message="Field value is too long")
    @Column("description")
    private String description;

    private static final long serialVersionUID = 1;

    public final boolean equals(Object obj) {
        if (obj == null) { return false;}
        if (obj == this) { return true;}
        if (obj.getClass() != getClass()) { return false;}
        Risk item = (Risk)obj;
        return new EqualsBuilder().append(id, item.id).build();
    }

    public final int hashCode() {
        return new HashCodeBuilder(659, 1195).append(id).build();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_risk.id
     *
     * @return the value of m_prj_risk.id
     *
     * @mbggenerated Fri Aug 28 22:04:32 ICT 2015
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_risk.id
     *
     * @param id the value for m_prj_risk.id
     *
     * @mbggenerated Fri Aug 28 22:04:32 ICT 2015
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_risk.riskname
     *
     * @return the value of m_prj_risk.riskname
     *
     * @mbggenerated Fri Aug 28 22:04:32 ICT 2015
     */
    public String getRiskname() {
        return riskname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_risk.riskname
     *
     * @param riskname the value for m_prj_risk.riskname
     *
     * @mbggenerated Fri Aug 28 22:04:32 ICT 2015
     */
    public void setRiskname(String riskname) {
        this.riskname = riskname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_risk.projectid
     *
     * @return the value of m_prj_risk.projectid
     *
     * @mbggenerated Fri Aug 28 22:04:32 ICT 2015
     */
    public Integer getProjectid() {
        return projectid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_risk.projectid
     *
     * @param projectid the value for m_prj_risk.projectid
     *
     * @mbggenerated Fri Aug 28 22:04:32 ICT 2015
     */
    public void setProjectid(Integer projectid) {
        this.projectid = projectid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_risk.raisedbyuser
     *
     * @return the value of m_prj_risk.raisedbyuser
     *
     * @mbggenerated Fri Aug 28 22:04:32 ICT 2015
     */
    public String getRaisedbyuser() {
        return raisedbyuser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_risk.raisedbyuser
     *
     * @param raisedbyuser the value for m_prj_risk.raisedbyuser
     *
     * @mbggenerated Fri Aug 28 22:04:32 ICT 2015
     */
    public void setRaisedbyuser(String raisedbyuser) {
        this.raisedbyuser = raisedbyuser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_risk.assigntouser
     *
     * @return the value of m_prj_risk.assigntouser
     *
     * @mbggenerated Fri Aug 28 22:04:32 ICT 2015
     */
    public String getAssigntouser() {
        return assigntouser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_risk.assigntouser
     *
     * @param assigntouser the value for m_prj_risk.assigntouser
     *
     * @mbggenerated Fri Aug 28 22:04:32 ICT 2015
     */
    public void setAssigntouser(String assigntouser) {
        this.assigntouser = assigntouser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_risk.consequence
     *
     * @return the value of m_prj_risk.consequence
     *
     * @mbggenerated Fri Aug 28 22:04:32 ICT 2015
     */
    public String getConsequence() {
        return consequence;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_risk.consequence
     *
     * @param consequence the value for m_prj_risk.consequence
     *
     * @mbggenerated Fri Aug 28 22:04:32 ICT 2015
     */
    public void setConsequence(String consequence) {
        this.consequence = consequence;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_risk.probalitity
     *
     * @return the value of m_prj_risk.probalitity
     *
     * @mbggenerated Fri Aug 28 22:04:32 ICT 2015
     */
    public String getProbalitity() {
        return probalitity;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_risk.probalitity
     *
     * @param probalitity the value for m_prj_risk.probalitity
     *
     * @mbggenerated Fri Aug 28 22:04:32 ICT 2015
     */
    public void setProbalitity(String probalitity) {
        this.probalitity = probalitity;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_risk.status
     *
     * @return the value of m_prj_risk.status
     *
     * @mbggenerated Fri Aug 28 22:04:32 ICT 2015
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_risk.status
     *
     * @param status the value for m_prj_risk.status
     *
     * @mbggenerated Fri Aug 28 22:04:32 ICT 2015
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_risk.dateraised
     *
     * @return the value of m_prj_risk.dateraised
     *
     * @mbggenerated Fri Aug 28 22:04:32 ICT 2015
     */
    public Date getDateraised() {
        return dateraised;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_risk.dateraised
     *
     * @param dateraised the value for m_prj_risk.dateraised
     *
     * @mbggenerated Fri Aug 28 22:04:32 ICT 2015
     */
    public void setDateraised(Date dateraised) {
        this.dateraised = dateraised;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_risk.datedue
     *
     * @return the value of m_prj_risk.datedue
     *
     * @mbggenerated Fri Aug 28 22:04:32 ICT 2015
     */
    public Date getDatedue() {
        return datedue;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_risk.datedue
     *
     * @param datedue the value for m_prj_risk.datedue
     *
     * @mbggenerated Fri Aug 28 22:04:32 ICT 2015
     */
    public void setDatedue(Date datedue) {
        this.datedue = datedue;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_risk.response
     *
     * @return the value of m_prj_risk.response
     *
     * @mbggenerated Fri Aug 28 22:04:32 ICT 2015
     */
    public String getResponse() {
        return response;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_risk.response
     *
     * @param response the value for m_prj_risk.response
     *
     * @mbggenerated Fri Aug 28 22:04:32 ICT 2015
     */
    public void setResponse(String response) {
        this.response = response;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_risk.resolution
     *
     * @return the value of m_prj_risk.resolution
     *
     * @mbggenerated Fri Aug 28 22:04:32 ICT 2015
     */
    public String getResolution() {
        return resolution;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_risk.resolution
     *
     * @param resolution the value for m_prj_risk.resolution
     *
     * @mbggenerated Fri Aug 28 22:04:32 ICT 2015
     */
    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_risk.level
     *
     * @return the value of m_prj_risk.level
     *
     * @mbggenerated Fri Aug 28 22:04:32 ICT 2015
     */
    public Double getLevel() {
        return level;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_risk.level
     *
     * @param level the value for m_prj_risk.level
     *
     * @mbggenerated Fri Aug 28 22:04:32 ICT 2015
     */
    public void setLevel(Double level) {
        this.level = level;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_risk.source
     *
     * @return the value of m_prj_risk.source
     *
     * @mbggenerated Fri Aug 28 22:04:32 ICT 2015
     */
    public String getSource() {
        return source;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_risk.source
     *
     * @param source the value for m_prj_risk.source
     *
     * @mbggenerated Fri Aug 28 22:04:32 ICT 2015
     */
    public void setSource(String source) {
        this.source = source;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_risk.createdTime
     *
     * @return the value of m_prj_risk.createdTime
     *
     * @mbggenerated Fri Aug 28 22:04:32 ICT 2015
     */
    public Date getCreatedtime() {
        return createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_risk.createdTime
     *
     * @param createdtime the value for m_prj_risk.createdTime
     *
     * @mbggenerated Fri Aug 28 22:04:32 ICT 2015
     */
    public void setCreatedtime(Date createdtime) {
        this.createdtime = createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_risk.lastUpdatedTime
     *
     * @return the value of m_prj_risk.lastUpdatedTime
     *
     * @mbggenerated Fri Aug 28 22:04:32 ICT 2015
     */
    public Date getLastupdatedtime() {
        return lastupdatedtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_risk.lastUpdatedTime
     *
     * @param lastupdatedtime the value for m_prj_risk.lastUpdatedTime
     *
     * @mbggenerated Fri Aug 28 22:04:32 ICT 2015
     */
    public void setLastupdatedtime(Date lastupdatedtime) {
        this.lastupdatedtime = lastupdatedtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_risk.type
     *
     * @return the value of m_prj_risk.type
     *
     * @mbggenerated Fri Aug 28 22:04:32 ICT 2015
     */
    public String getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_risk.type
     *
     * @param type the value for m_prj_risk.type
     *
     * @mbggenerated Fri Aug 28 22:04:32 ICT 2015
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_risk.typeid
     *
     * @return the value of m_prj_risk.typeid
     *
     * @mbggenerated Fri Aug 28 22:04:32 ICT 2015
     */
    public Integer getTypeid() {
        return typeid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_risk.typeid
     *
     * @param typeid the value for m_prj_risk.typeid
     *
     * @mbggenerated Fri Aug 28 22:04:32 ICT 2015
     */
    public void setTypeid(Integer typeid) {
        this.typeid = typeid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_risk.sAccountId
     *
     * @return the value of m_prj_risk.sAccountId
     *
     * @mbggenerated Fri Aug 28 22:04:32 ICT 2015
     */
    public Integer getSaccountid() {
        return saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_risk.sAccountId
     *
     * @param saccountid the value for m_prj_risk.sAccountId
     *
     * @mbggenerated Fri Aug 28 22:04:32 ICT 2015
     */
    public void setSaccountid(Integer saccountid) {
        this.saccountid = saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_risk.prjKey
     *
     * @return the value of m_prj_risk.prjKey
     *
     * @mbggenerated Fri Aug 28 22:04:32 ICT 2015
     */
    public Integer getPrjkey() {
        return prjkey;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_risk.prjKey
     *
     * @param prjkey the value for m_prj_risk.prjKey
     *
     * @mbggenerated Fri Aug 28 22:04:32 ICT 2015
     */
    public void setPrjkey(Integer prjkey) {
        this.prjkey = prjkey;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_prj_risk.description
     *
     * @return the value of m_prj_risk.description
     *
     * @mbggenerated Fri Aug 28 22:04:32 ICT 2015
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_prj_risk.description
     *
     * @param description the value for m_prj_risk.description
     *
     * @mbggenerated Fri Aug 28 22:04:32 ICT 2015
     */
    public void setDescription(String description) {
        this.description = description;
    }

    public enum Field {
        id,
        riskname,
        projectid,
        raisedbyuser,
        assigntouser,
        consequence,
        probalitity,
        status,
        dateraised,
        datedue,
        response,
        resolution,
        level,
        source,
        createdtime,
        lastupdatedtime,
        type,
        typeid,
        saccountid,
        prjkey,
        description;

        public boolean equalTo(Object value) {
            return name().equals(value);
        }
    }
}