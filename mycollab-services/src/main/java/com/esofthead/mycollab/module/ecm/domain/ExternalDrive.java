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
/*Domain class of table m_ecm_external_drive*/
package com.esofthead.mycollab.module.ecm.domain;

import com.esofthead.mycollab.core.arguments.ValuedBean;
import java.util.Date;

@SuppressWarnings("ucd")
@com.esofthead.mycollab.core.db.metadata.Table("m_ecm_external_drive")
public class ExternalDrive extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_ecm_external_drive.id
     *
     * @mbggenerated Mon Dec 22 11:25:13 ICT 2014
     */
    @com.esofthead.mycollab.core.db.metadata.Column("id")
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_ecm_external_drive.storageName
     *
     * @mbggenerated Mon Dec 22 11:25:13 ICT 2014
     */
    @org.hibernate.validator.constraints.Length(max=100, message="Field value is too long")
    @com.esofthead.mycollab.core.db.metadata.Column("storageName")
    private String storagename;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_ecm_external_drive.owner
     *
     * @mbggenerated Mon Dec 22 11:25:13 ICT 2014
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    @com.esofthead.mycollab.core.db.metadata.Column("owner")
    private String owner;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_ecm_external_drive.accessToken
     *
     * @mbggenerated Mon Dec 22 11:25:13 ICT 2014
     */
    @org.hibernate.validator.constraints.Length(max=100, message="Field value is too long")
    @com.esofthead.mycollab.core.db.metadata.Column("accessToken")
    private String accesstoken;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_ecm_external_drive.createdTime
     *
     * @mbggenerated Mon Dec 22 11:25:13 ICT 2014
     */
    @com.esofthead.mycollab.core.db.metadata.Column("createdTime")
    private Date createdtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_ecm_external_drive.lastUpdatedTime
     *
     * @mbggenerated Mon Dec 22 11:25:13 ICT 2014
     */
    @com.esofthead.mycollab.core.db.metadata.Column("lastUpdatedTime")
    private Date lastupdatedtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_ecm_external_drive.folderName
     *
     * @mbggenerated Mon Dec 22 11:25:13 ICT 2014
     */
    @org.hibernate.validator.constraints.Length(max=256, message="Field value is too long")
    @com.esofthead.mycollab.core.db.metadata.Column("folderName")
    private String foldername;

    private static final long serialVersionUID = 1;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_ecm_external_drive.id
     *
     * @return the value of m_ecm_external_drive.id
     *
     * @mbggenerated Mon Dec 22 11:25:13 ICT 2014
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_ecm_external_drive.id
     *
     * @param id the value for m_ecm_external_drive.id
     *
     * @mbggenerated Mon Dec 22 11:25:13 ICT 2014
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_ecm_external_drive.storageName
     *
     * @return the value of m_ecm_external_drive.storageName
     *
     * @mbggenerated Mon Dec 22 11:25:13 ICT 2014
     */
    public String getStoragename() {
        return storagename;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_ecm_external_drive.storageName
     *
     * @param storagename the value for m_ecm_external_drive.storageName
     *
     * @mbggenerated Mon Dec 22 11:25:13 ICT 2014
     */
    public void setStoragename(String storagename) {
        this.storagename = storagename;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_ecm_external_drive.owner
     *
     * @return the value of m_ecm_external_drive.owner
     *
     * @mbggenerated Mon Dec 22 11:25:13 ICT 2014
     */
    public String getOwner() {
        return owner;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_ecm_external_drive.owner
     *
     * @param owner the value for m_ecm_external_drive.owner
     *
     * @mbggenerated Mon Dec 22 11:25:13 ICT 2014
     */
    public void setOwner(String owner) {
        this.owner = owner;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_ecm_external_drive.accessToken
     *
     * @return the value of m_ecm_external_drive.accessToken
     *
     * @mbggenerated Mon Dec 22 11:25:13 ICT 2014
     */
    public String getAccesstoken() {
        return accesstoken;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_ecm_external_drive.accessToken
     *
     * @param accesstoken the value for m_ecm_external_drive.accessToken
     *
     * @mbggenerated Mon Dec 22 11:25:13 ICT 2014
     */
    public void setAccesstoken(String accesstoken) {
        this.accesstoken = accesstoken;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_ecm_external_drive.createdTime
     *
     * @return the value of m_ecm_external_drive.createdTime
     *
     * @mbggenerated Mon Dec 22 11:25:13 ICT 2014
     */
    public Date getCreatedtime() {
        return createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_ecm_external_drive.createdTime
     *
     * @param createdtime the value for m_ecm_external_drive.createdTime
     *
     * @mbggenerated Mon Dec 22 11:25:13 ICT 2014
     */
    public void setCreatedtime(Date createdtime) {
        this.createdtime = createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_ecm_external_drive.lastUpdatedTime
     *
     * @return the value of m_ecm_external_drive.lastUpdatedTime
     *
     * @mbggenerated Mon Dec 22 11:25:13 ICT 2014
     */
    public Date getLastupdatedtime() {
        return lastupdatedtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_ecm_external_drive.lastUpdatedTime
     *
     * @param lastupdatedtime the value for m_ecm_external_drive.lastUpdatedTime
     *
     * @mbggenerated Mon Dec 22 11:25:13 ICT 2014
     */
    public void setLastupdatedtime(Date lastupdatedtime) {
        this.lastupdatedtime = lastupdatedtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_ecm_external_drive.folderName
     *
     * @return the value of m_ecm_external_drive.folderName
     *
     * @mbggenerated Mon Dec 22 11:25:13 ICT 2014
     */
    public String getFoldername() {
        return foldername;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_ecm_external_drive.folderName
     *
     * @param foldername the value for m_ecm_external_drive.folderName
     *
     * @mbggenerated Mon Dec 22 11:25:13 ICT 2014
     */
    public void setFoldername(String foldername) {
        this.foldername = foldername;
    }

    public static enum Field {
        id,
        storagename,
        owner,
        accesstoken,
        createdtime,
        lastupdatedtime,
        foldername;

        public boolean equalTo(Object value) {
            return name().equals(value);
        }
    }
}