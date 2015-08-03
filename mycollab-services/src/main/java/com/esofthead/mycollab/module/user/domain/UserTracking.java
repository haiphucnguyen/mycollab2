/*Domain class of table s_user_tracking*/
package com.esofthead.mycollab.module.user.domain;

import com.esofthead.mycollab.core.arguments.ValuedBean;
import com.esofthead.mycollab.core.db.metadata.Column;
import com.esofthead.mycollab.core.db.metadata.Table;
import java.util.Date;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.Length;

@SuppressWarnings("ucd")
@Table("s_user_tracking")
public class UserTracking extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user_tracking.id
     *
     * @mbggenerated Mon Aug 03 15:23:14 ICT 2015
     */
    @Column("id")
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user_tracking.username
     *
     * @mbggenerated Mon Aug 03 15:23:14 ICT 2015
     */
    @Length(max=45, message="Field value is too long")
    @Column("username")
    private String username;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user_tracking.createdTime
     *
     * @mbggenerated Mon Aug 03 15:23:14 ICT 2015
     */
    @Column("createdTime")
    private Date createdtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user_tracking.sAccountId
     *
     * @mbggenerated Mon Aug 03 15:23:14 ICT 2015
     */
    @Column("sAccountId")
    private Integer saccountid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user_tracking.userAgent
     *
     * @mbggenerated Mon Aug 03 15:23:14 ICT 2015
     */
    @Length(max=65535, message="Field value is too long")
    @Column("userAgent")
    private String useragent;

    private static final long serialVersionUID = 1;

    public final boolean equals(Object obj) {
        if (obj == null) { return false;}
        if (obj == this) { return true;}
        if (obj.getClass() != getClass()) { return false;}
        UserTracking item = (UserTracking)obj;
        return new EqualsBuilder().append(id, item.id).build();
    }

    public final int hashCode() {
        return new HashCodeBuilder(401, 313).append(id).build();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_user_tracking.id
     *
     * @return the value of s_user_tracking.id
     *
     * @mbggenerated Mon Aug 03 15:23:14 ICT 2015
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_user_tracking.id
     *
     * @param id the value for s_user_tracking.id
     *
     * @mbggenerated Mon Aug 03 15:23:14 ICT 2015
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_user_tracking.username
     *
     * @return the value of s_user_tracking.username
     *
     * @mbggenerated Mon Aug 03 15:23:14 ICT 2015
     */
    public String getUsername() {
        return username;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_user_tracking.username
     *
     * @param username the value for s_user_tracking.username
     *
     * @mbggenerated Mon Aug 03 15:23:14 ICT 2015
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_user_tracking.createdTime
     *
     * @return the value of s_user_tracking.createdTime
     *
     * @mbggenerated Mon Aug 03 15:23:14 ICT 2015
     */
    public Date getCreatedtime() {
        return createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_user_tracking.createdTime
     *
     * @param createdtime the value for s_user_tracking.createdTime
     *
     * @mbggenerated Mon Aug 03 15:23:14 ICT 2015
     */
    public void setCreatedtime(Date createdtime) {
        this.createdtime = createdtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_user_tracking.sAccountId
     *
     * @return the value of s_user_tracking.sAccountId
     *
     * @mbggenerated Mon Aug 03 15:23:14 ICT 2015
     */
    public Integer getSaccountid() {
        return saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_user_tracking.sAccountId
     *
     * @param saccountid the value for s_user_tracking.sAccountId
     *
     * @mbggenerated Mon Aug 03 15:23:14 ICT 2015
     */
    public void setSaccountid(Integer saccountid) {
        this.saccountid = saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_user_tracking.userAgent
     *
     * @return the value of s_user_tracking.userAgent
     *
     * @mbggenerated Mon Aug 03 15:23:14 ICT 2015
     */
    public String getUseragent() {
        return useragent;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_user_tracking.userAgent
     *
     * @param useragent the value for s_user_tracking.userAgent
     *
     * @mbggenerated Mon Aug 03 15:23:14 ICT 2015
     */
    public void setUseragent(String useragent) {
        this.useragent = useragent;
    }

    public enum Field {
        id,
        username,
        createdtime,
        saccountid,
        useragent;

        public boolean equalTo(Object value) {
            return name().equals(value);
        }
    }
}