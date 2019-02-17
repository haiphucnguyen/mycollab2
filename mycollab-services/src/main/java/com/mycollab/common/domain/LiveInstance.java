/*Domain class of table s_live_instances*/
package com.mycollab.common.domain;

import com.mycollab.core.arguments.ValuedBean;
import com.mycollab.db.metadata.Column;
import com.mycollab.db.metadata.Table;
import java.time.LocalDateTime;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.ibatis.type.Alias;
import org.hibernate.validator.constraints.Length;

@SuppressWarnings("ucd")
@Table("s_live_instances")
@Alias("LiveInstance")
public class LiveInstance extends ValuedBean {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_live_instances.id
     *
     * @mbg.generated Sat Feb 09 11:42:26 CST 2019
     */
    @Column("id")
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_live_instances.appVersion
     *
     * @mbg.generated Sat Feb 09 11:42:26 CST 2019
     */
    @NotEmpty
    @Length(max=45, message="Field value is too long")
    @Column("appVersion")
    private String appversion;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_live_instances.javaVersion
     *
     * @mbg.generated Sat Feb 09 11:42:26 CST 2019
     */
    @NotEmpty
    @Length(max=45, message="Field value is too long")
    @Column("javaVersion")
    private String javaversion;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_live_instances.installedDate
     *
     * @mbg.generated Sat Feb 09 11:42:26 CST 2019
     */
    @NotNull
    @Column("installedDate")
    private LocalDateTime installeddate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_live_instances.sysId
     *
     * @mbg.generated Sat Feb 09 11:42:26 CST 2019
     */
    @NotEmpty
    @Length(max=100, message="Field value is too long")
    @Column("sysId")
    private String sysid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_live_instances.sysProperties
     *
     * @mbg.generated Sat Feb 09 11:42:26 CST 2019
     */
    @NotEmpty
    @Length(max=100, message="Field value is too long")
    @Column("sysProperties")
    private String sysproperties;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_live_instances.lastUpdatedDate
     *
     * @mbg.generated Sat Feb 09 11:42:26 CST 2019
     */
    @NotNull
    @Column("lastUpdatedDate")
    private LocalDateTime lastupdateddate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_live_instances.numProjects
     *
     * @mbg.generated Sat Feb 09 11:42:26 CST 2019
     */
    @Column("numProjects")
    private Integer numprojects;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_live_instances.numUsers
     *
     * @mbg.generated Sat Feb 09 11:42:26 CST 2019
     */
    @Column("numUsers")
    private Integer numusers;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_live_instances.edition
     *
     * @mbg.generated Sat Feb 09 11:42:26 CST 2019
     */
    @Length(max=45, message="Field value is too long")
    @Column("edition")
    private String edition;

    private static final long serialVersionUID = 1;

    public final boolean equals(Object obj) {
        if (obj == null) { return false;}
        if (obj == this) { return true;}
        if (!obj.getClass().isAssignableFrom(getClass())) { return false;}
        LiveInstance item = (LiveInstance)obj;
        return new EqualsBuilder().append(id, item.id).build();
    }

    public final int hashCode() {
        return new HashCodeBuilder(737, 1129).append(id).build();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_live_instances.id
     *
     * @return the value of s_live_instances.id
     *
     * @mbg.generated Sat Feb 09 11:42:26 CST 2019
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_live_instances
     *
     * @mbg.generated Sat Feb 09 11:42:26 CST 2019
     */
    public LiveInstance withId(Integer id) {
        this.setId(id);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_live_instances.id
     *
     * @param id the value for s_live_instances.id
     *
     * @mbg.generated Sat Feb 09 11:42:26 CST 2019
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_live_instances.appVersion
     *
     * @return the value of s_live_instances.appVersion
     *
     * @mbg.generated Sat Feb 09 11:42:26 CST 2019
     */
    public String getAppversion() {
        return appversion;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_live_instances
     *
     * @mbg.generated Sat Feb 09 11:42:26 CST 2019
     */
    public LiveInstance withAppversion(String appversion) {
        this.setAppversion(appversion);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_live_instances.appVersion
     *
     * @param appversion the value for s_live_instances.appVersion
     *
     * @mbg.generated Sat Feb 09 11:42:26 CST 2019
     */
    public void setAppversion(String appversion) {
        this.appversion = appversion;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_live_instances.javaVersion
     *
     * @return the value of s_live_instances.javaVersion
     *
     * @mbg.generated Sat Feb 09 11:42:26 CST 2019
     */
    public String getJavaversion() {
        return javaversion;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_live_instances
     *
     * @mbg.generated Sat Feb 09 11:42:26 CST 2019
     */
    public LiveInstance withJavaversion(String javaversion) {
        this.setJavaversion(javaversion);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_live_instances.javaVersion
     *
     * @param javaversion the value for s_live_instances.javaVersion
     *
     * @mbg.generated Sat Feb 09 11:42:26 CST 2019
     */
    public void setJavaversion(String javaversion) {
        this.javaversion = javaversion;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_live_instances.installedDate
     *
     * @return the value of s_live_instances.installedDate
     *
     * @mbg.generated Sat Feb 09 11:42:26 CST 2019
     */
    public LocalDateTime getInstalleddate() {
        return installeddate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_live_instances
     *
     * @mbg.generated Sat Feb 09 11:42:26 CST 2019
     */
    public LiveInstance withInstalleddate(LocalDateTime installeddate) {
        this.setInstalleddate(installeddate);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_live_instances.installedDate
     *
     * @param installeddate the value for s_live_instances.installedDate
     *
     * @mbg.generated Sat Feb 09 11:42:26 CST 2019
     */
    public void setInstalleddate(LocalDateTime installeddate) {
        this.installeddate = installeddate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_live_instances.sysId
     *
     * @return the value of s_live_instances.sysId
     *
     * @mbg.generated Sat Feb 09 11:42:26 CST 2019
     */
    public String getSysid() {
        return sysid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_live_instances
     *
     * @mbg.generated Sat Feb 09 11:42:26 CST 2019
     */
    public LiveInstance withSysid(String sysid) {
        this.setSysid(sysid);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_live_instances.sysId
     *
     * @param sysid the value for s_live_instances.sysId
     *
     * @mbg.generated Sat Feb 09 11:42:26 CST 2019
     */
    public void setSysid(String sysid) {
        this.sysid = sysid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_live_instances.sysProperties
     *
     * @return the value of s_live_instances.sysProperties
     *
     * @mbg.generated Sat Feb 09 11:42:26 CST 2019
     */
    public String getSysproperties() {
        return sysproperties;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_live_instances
     *
     * @mbg.generated Sat Feb 09 11:42:26 CST 2019
     */
    public LiveInstance withSysproperties(String sysproperties) {
        this.setSysproperties(sysproperties);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_live_instances.sysProperties
     *
     * @param sysproperties the value for s_live_instances.sysProperties
     *
     * @mbg.generated Sat Feb 09 11:42:26 CST 2019
     */
    public void setSysproperties(String sysproperties) {
        this.sysproperties = sysproperties;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_live_instances.lastUpdatedDate
     *
     * @return the value of s_live_instances.lastUpdatedDate
     *
     * @mbg.generated Sat Feb 09 11:42:26 CST 2019
     */
    public LocalDateTime getLastupdateddate() {
        return lastupdateddate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_live_instances
     *
     * @mbg.generated Sat Feb 09 11:42:26 CST 2019
     */
    public LiveInstance withLastupdateddate(LocalDateTime lastupdateddate) {
        this.setLastupdateddate(lastupdateddate);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_live_instances.lastUpdatedDate
     *
     * @param lastupdateddate the value for s_live_instances.lastUpdatedDate
     *
     * @mbg.generated Sat Feb 09 11:42:26 CST 2019
     */
    public void setLastupdateddate(LocalDateTime lastupdateddate) {
        this.lastupdateddate = lastupdateddate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_live_instances.numProjects
     *
     * @return the value of s_live_instances.numProjects
     *
     * @mbg.generated Sat Feb 09 11:42:26 CST 2019
     */
    public Integer getNumprojects() {
        return numprojects;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_live_instances
     *
     * @mbg.generated Sat Feb 09 11:42:26 CST 2019
     */
    public LiveInstance withNumprojects(Integer numprojects) {
        this.setNumprojects(numprojects);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_live_instances.numProjects
     *
     * @param numprojects the value for s_live_instances.numProjects
     *
     * @mbg.generated Sat Feb 09 11:42:26 CST 2019
     */
    public void setNumprojects(Integer numprojects) {
        this.numprojects = numprojects;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_live_instances.numUsers
     *
     * @return the value of s_live_instances.numUsers
     *
     * @mbg.generated Sat Feb 09 11:42:26 CST 2019
     */
    public Integer getNumusers() {
        return numusers;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_live_instances
     *
     * @mbg.generated Sat Feb 09 11:42:26 CST 2019
     */
    public LiveInstance withNumusers(Integer numusers) {
        this.setNumusers(numusers);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_live_instances.numUsers
     *
     * @param numusers the value for s_live_instances.numUsers
     *
     * @mbg.generated Sat Feb 09 11:42:26 CST 2019
     */
    public void setNumusers(Integer numusers) {
        this.numusers = numusers;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_live_instances.edition
     *
     * @return the value of s_live_instances.edition
     *
     * @mbg.generated Sat Feb 09 11:42:26 CST 2019
     */
    public String getEdition() {
        return edition;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_live_instances
     *
     * @mbg.generated Sat Feb 09 11:42:26 CST 2019
     */
    public LiveInstance withEdition(String edition) {
        this.setEdition(edition);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_live_instances.edition
     *
     * @param edition the value for s_live_instances.edition
     *
     * @mbg.generated Sat Feb 09 11:42:26 CST 2019
     */
    public void setEdition(String edition) {
        this.edition = edition;
    }

    public enum Field {
        id,
        appversion,
        javaversion,
        installeddate,
        sysid,
        sysproperties,
        lastupdateddate,
        numprojects,
        numusers,
        edition;

        public boolean equalTo(Object value) {
            return name().equals(value);
        }
    }
}