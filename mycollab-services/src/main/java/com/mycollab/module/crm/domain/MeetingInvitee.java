/*Domain class of table m_crm_meeting_invitees*/
package com.mycollab.module.crm.domain;

import com.mycollab.core.arguments.ValuedBean;
import com.mycollab.db.metadata.Column;
import com.mycollab.db.metadata.Table;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.ibatis.type.Alias;
import org.hibernate.validator.constraints.Length;

@SuppressWarnings("ucd")
@Table("m_crm_meeting_invitees")
@Alias("MeetingInvitee")
public class MeetingInvitee extends ValuedBean {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_meeting_invitees.id
     *
     * @mbg.generated Fri Nov 30 08:06:38 CST 2018
     */
    @Column("id")
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_meeting_invitees.meetingId
     *
     * @mbg.generated Fri Nov 30 08:06:38 CST 2018
     */
    @Column("meetingId")
    private Integer meetingid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_meeting_invitees.username
     *
     * @mbg.generated Fri Nov 30 08:06:38 CST 2018
     */
    @Length(max=45, message="Field value is too long")
    @Column("username")
    private String username;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_meeting_invitees.isModerator
     *
     * @mbg.generated Fri Nov 30 08:06:38 CST 2018
     */
    @Column("isModerator")
    private Boolean ismoderator;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_meeting_invitees.status
     *
     * @mbg.generated Fri Nov 30 08:06:38 CST 2018
     */
    @Length(max=45, message="Field value is too long")
    @Column("status")
    private String status;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_meeting_invitees.source
     *
     * @mbg.generated Fri Nov 30 08:06:38 CST 2018
     */
    @Length(max=45, message="Field value is too long")
    @Column("source")
    private String source;

    private static final long serialVersionUID = 1;

    public final boolean equals(Object obj) {
        if (obj == null) { return false;}
        if (obj == this) { return true;}
        if (!obj.getClass().isAssignableFrom(getClass())) { return false;}
        MeetingInvitee item = (MeetingInvitee)obj;
        return new EqualsBuilder().append(id, item.id).build();
    }

    public final int hashCode() {
        return new HashCodeBuilder(1397, 1461).append(id).build();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_meeting_invitees.id
     *
     * @return the value of m_crm_meeting_invitees.id
     *
     * @mbg.generated Fri Nov 30 08:06:38 CST 2018
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_meeting_invitees.id
     *
     * @param id the value for m_crm_meeting_invitees.id
     *
     * @mbg.generated Fri Nov 30 08:06:38 CST 2018
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_meeting_invitees.meetingId
     *
     * @return the value of m_crm_meeting_invitees.meetingId
     *
     * @mbg.generated Fri Nov 30 08:06:38 CST 2018
     */
    public Integer getMeetingid() {
        return meetingid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_meeting_invitees.meetingId
     *
     * @param meetingid the value for m_crm_meeting_invitees.meetingId
     *
     * @mbg.generated Fri Nov 30 08:06:38 CST 2018
     */
    public void setMeetingid(Integer meetingid) {
        this.meetingid = meetingid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_meeting_invitees.username
     *
     * @return the value of m_crm_meeting_invitees.username
     *
     * @mbg.generated Fri Nov 30 08:06:38 CST 2018
     */
    public String getUsername() {
        return username;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_meeting_invitees.username
     *
     * @param username the value for m_crm_meeting_invitees.username
     *
     * @mbg.generated Fri Nov 30 08:06:38 CST 2018
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_meeting_invitees.isModerator
     *
     * @return the value of m_crm_meeting_invitees.isModerator
     *
     * @mbg.generated Fri Nov 30 08:06:38 CST 2018
     */
    public Boolean getIsmoderator() {
        return ismoderator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_meeting_invitees.isModerator
     *
     * @param ismoderator the value for m_crm_meeting_invitees.isModerator
     *
     * @mbg.generated Fri Nov 30 08:06:38 CST 2018
     */
    public void setIsmoderator(Boolean ismoderator) {
        this.ismoderator = ismoderator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_meeting_invitees.status
     *
     * @return the value of m_crm_meeting_invitees.status
     *
     * @mbg.generated Fri Nov 30 08:06:38 CST 2018
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_meeting_invitees.status
     *
     * @param status the value for m_crm_meeting_invitees.status
     *
     * @mbg.generated Fri Nov 30 08:06:38 CST 2018
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_meeting_invitees.source
     *
     * @return the value of m_crm_meeting_invitees.source
     *
     * @mbg.generated Fri Nov 30 08:06:38 CST 2018
     */
    public String getSource() {
        return source;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column m_crm_meeting_invitees.source
     *
     * @param source the value for m_crm_meeting_invitees.source
     *
     * @mbg.generated Fri Nov 30 08:06:38 CST 2018
     */
    public void setSource(String source) {
        this.source = source;
    }

    public enum Field {
        id,
        meetingid,
        username,
        ismoderator,
        status,
        source;

        public boolean equalTo(Object value) {
            return name().equals(value);
        }
    }
}