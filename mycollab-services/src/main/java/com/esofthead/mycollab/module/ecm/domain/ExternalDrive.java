/*Domain class of table m_ecm_external_drive*/
package com.esofthead.mycollab.module.ecm.domain;

import com.esofthead.mycollab.core.arguments.ValuedBean;
import com.esofthead.mycollab.core.db.metadata.Column;
import com.esofthead.mycollab.core.db.metadata.Table;
import java.util.Date;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.Length;

@SuppressWarnings("ucd")
@Table("m_ecm_external_drive")
public class ExternalDrive extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_ecm_external_drive.id
     *
     * @mbggenerated Tue May 24 18:34:15 ICT 2016
     */
    @Column("id")
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_ecm_external_drive.storageName
     *
     * @mbggenerated Tue May 24 18:34:15 ICT 2016
     */
    @Length(max=100, message="Field value is too long")
    @Column("storageName")
    private String storagename;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_ecm_external_drive.owner
     *
     * @mbggenerated Tue May 24 18:34:15 ICT 2016
     */
    @Length(max=45, message="Field value is too long")
    @Column("owner")
    private String owner;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_ecm_external_drive.accessToken
     *
     * @mbggenerated Tue May 24 18:34:15 ICT 2016
     */
    @Length(max=100, message="Field value is too long")
    @Column("accessToken")
    private String accesstoken;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_ecm_external_drive.createdTime
     *
     * @mbggenerated Tue May 24 18:34:15 ICT 2016
     */
    @Column("createdTime")
    private Date createdtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_ecm_external_drive.lastUpdatedTime
     *
     * @mbggenerated Tue May 24 18:34:15 ICT 2016
     */
    @Column("lastUpdatedTime")
    private Date lastupdatedtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_ecm_external_drive.folderName
     *
     * @mbggenerated Tue May 24 18:34:15 ICT 2016
     */
    @Length(max=256, message="Field value is too long")
    @Column("folderName")
    private String foldername;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_ecm_external_drive.shareAccountId
     *
     * @mbggenerated Tue May 24 18:34:15 ICT 2016
     */
    @Column("shareAccountId")
    private Integer shareaccountid;

    private static final long serialVersionUID = 1;

    public final boolean equals(Object obj) {
        if (obj == null) { return false;}
        if (obj == this) { return true;}
        if (!obj.getClass().isAssignableFrom(getClass())) { return false;}
        ExternalDrive item = (ExternalDrive)obj;
        return new EqualsBuilder().append(id, item.id).build();
    }

    public final int hashCode() {
        return new HashCodeBuilder(109, 1997).append(id).build();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_ecm_external_drive.id
     *
     * @return the value of m_ecm_external_drive.id
     *
     * @mbggenerated Tue May 24 18:34:15 ICT 2016
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
     * @mbggenerated Tue May 24 18:34:15 ICT 2016
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
     * @mbggenerated Tue May 24 18:34:15 ICT 2016
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
     * @mbggenerated Tue May 24 18:34:15 ICT 2016
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
     * @mbggenerated Tue May 24 18:34:15 ICT 2016
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
     * @mbggenerated Tue May 24 18:34:15 ICT 2016
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
     * @mbggenerated Tue May 24 18:34:15 ICT 2016
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
     * @mbggenerated Tue May 24 18:34:15 ICT 2016
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
     * @mbggenerated Tue May 24 18:34:15 ICT 2016
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
     * @mbggenerated Tue May 24 18:34:15 ICT 2016
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
     * @mbggenerated Tue May 24 18:34:15 ICT 2016
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
     * @mbggenerated Tue May 24 18:34:15 ICT 2016
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
     * @mbggenerated Tue May 24 18:34:15 ICT 2016
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
     * @mbggenerated Tue May 24 18:34:15 ICT 2016
     */
    public void setFoldername(String foldername) {
        this.foldername = foldername;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_ecm_external_drive.shareAccountId
     *
     * @return the value of m_ecm_external_drive.shareAccountId
     *
     * @mbggenerated Tue May 24 18:34:15 ICT 2016
     */
    public Integer getShareaccountid() {
        return shareaccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_ecm_external_drive.shareAccountId
     *
     * @param shareaccountid the value for m_ecm_external_drive.shareAccountId
     *
     * @mbggenerated Tue May 24 18:34:15 ICT 2016
     */
    public void setShareaccountid(Integer shareaccountid) {
        this.shareaccountid = shareaccountid;
    }

    public enum Field {
        id,
        storagename,
        owner,
        accesstoken,
        createdtime,
        lastupdatedtime,
        foldername,
        shareaccountid;

        public boolean equalTo(Object value) {
            return name().equals(value);
        }
    }
}