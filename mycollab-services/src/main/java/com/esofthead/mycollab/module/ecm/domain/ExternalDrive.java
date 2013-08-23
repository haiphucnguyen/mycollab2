/*Domain class of table m_ecm_external_drive*/
package com.esofthead.mycollab.module.ecm.domain;

import com.esofthead.mycollab.core.utils.ValuedBean;
import java.util.Date;

public class ExternalDrive extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_ecm_external_drive.id
     *
     * @mbggenerated Thu Aug 08 17:37:41 GMT+07:00 2013
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_ecm_external_drive.storageName
     *
     * @mbggenerated Thu Aug 08 17:37:41 GMT+07:00 2013
     */
    @org.hibernate.validator.constraints.Length(max=100, message="Field value is too long")
    private String storagename;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_ecm_external_drive.owner
     *
     * @mbggenerated Thu Aug 08 17:37:41 GMT+07:00 2013
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    private String owner;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_ecm_external_drive.accessToken
     *
     * @mbggenerated Thu Aug 08 17:37:41 GMT+07:00 2013
     */
    @org.hibernate.validator.constraints.Length(max=100, message="Field value is too long")
    private String accesstoken;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_ecm_external_drive.createdTime
     *
     * @mbggenerated Thu Aug 08 17:37:41 GMT+07:00 2013
     */
    private Date createdtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_ecm_external_drive.lastUpdatedTime
     *
     * @mbggenerated Thu Aug 08 17:37:41 GMT+07:00 2013
     */
    private Date lastupdatedtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_ecm_external_drive.folderName
     *
     * @mbggenerated Thu Aug 08 17:37:41 GMT+07:00 2013
     */
    @org.hibernate.validator.constraints.Length(max=256, message="Field value is too long")
    private String foldername;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_ecm_external_drive.id
     *
     * @return the value of m_ecm_external_drive.id
     *
     * @mbggenerated Thu Aug 08 17:37:41 GMT+07:00 2013
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
     * @mbggenerated Thu Aug 08 17:37:41 GMT+07:00 2013
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
     * @mbggenerated Thu Aug 08 17:37:41 GMT+07:00 2013
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
     * @mbggenerated Thu Aug 08 17:37:41 GMT+07:00 2013
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
     * @mbggenerated Thu Aug 08 17:37:41 GMT+07:00 2013
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
     * @mbggenerated Thu Aug 08 17:37:41 GMT+07:00 2013
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
     * @mbggenerated Thu Aug 08 17:37:41 GMT+07:00 2013
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
     * @mbggenerated Thu Aug 08 17:37:41 GMT+07:00 2013
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
     * @mbggenerated Thu Aug 08 17:37:41 GMT+07:00 2013
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
     * @mbggenerated Thu Aug 08 17:37:41 GMT+07:00 2013
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
     * @mbggenerated Thu Aug 08 17:37:41 GMT+07:00 2013
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
     * @mbggenerated Thu Aug 08 17:37:41 GMT+07:00 2013
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
     * @mbggenerated Thu Aug 08 17:37:41 GMT+07:00 2013
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
     * @mbggenerated Thu Aug 08 17:37:41 GMT+07:00 2013
     */
    public void setFoldername(String foldername) {
        this.foldername = foldername;
    }
}