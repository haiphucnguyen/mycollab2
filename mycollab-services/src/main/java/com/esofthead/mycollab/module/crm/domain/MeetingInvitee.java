/*Domain class of table m_crm_meeting_invitees*/
package com.esofthead.mycollab.module.crm.domain;

import com.esofthead.mycollab.core.utils.ValuedBean;

public class MeetingInvitee extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_meeting_invitees.id
     *
     * @mbggenerated Wed Nov 06 10:30:07 ICT 2013
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_meeting_invitees.meetingId
     *
     * @mbggenerated Wed Nov 06 10:30:07 ICT 2013
     */
    private Integer meetingid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_meeting_invitees.username
     *
     * @mbggenerated Wed Nov 06 10:30:07 ICT 2013
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    private String username;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_meeting_invitees.isModerator
     *
     * @mbggenerated Wed Nov 06 10:30:07 ICT 2013
     */
    private Boolean ismoderator;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_meeting_invitees.status
     *
     * @mbggenerated Wed Nov 06 10:30:07 ICT 2013
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    private String status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_meeting_invitees.source
     *
     * @mbggenerated Wed Nov 06 10:30:07 ICT 2013
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    private String source;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_meeting_invitees.id
     *
     * @return the value of m_crm_meeting_invitees.id
     *
     * @mbggenerated Wed Nov 06 10:30:07 ICT 2013
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
     * @mbggenerated Wed Nov 06 10:30:07 ICT 2013
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
     * @mbggenerated Wed Nov 06 10:30:07 ICT 2013
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
     * @mbggenerated Wed Nov 06 10:30:07 ICT 2013
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
     * @mbggenerated Wed Nov 06 10:30:07 ICT 2013
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
     * @mbggenerated Wed Nov 06 10:30:07 ICT 2013
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
     * @mbggenerated Wed Nov 06 10:30:07 ICT 2013
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
     * @mbggenerated Wed Nov 06 10:30:07 ICT 2013
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
     * @mbggenerated Wed Nov 06 10:30:07 ICT 2013
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
     * @mbggenerated Wed Nov 06 10:30:07 ICT 2013
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
     * @mbggenerated Wed Nov 06 10:30:07 ICT 2013
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
     * @mbggenerated Wed Nov 06 10:30:07 ICT 2013
     */
    public void setSource(String source) {
        this.source = source;
    }
}