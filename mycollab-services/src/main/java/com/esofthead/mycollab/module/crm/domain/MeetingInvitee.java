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
/*Domain class of table m_crm_meeting_invitees*/
package com.esofthead.mycollab.module.crm.domain;

import com.esofthead.mycollab.core.arguments.ValuedBean;

public class MeetingInvitee extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_meeting_invitees.id
     *
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_meeting_invitees.meetingId
     *
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
     */
    private Integer meetingid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_meeting_invitees.username
     *
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    private String username;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_meeting_invitees.isModerator
     *
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
     */
    private Boolean ismoderator;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_meeting_invitees.status
     *
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    private String status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_crm_meeting_invitees.source
     *
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
     */
    @org.hibernate.validator.constraints.Length(max=45, message="Field value is too long")
    private String source;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column m_crm_meeting_invitees.id
     *
     * @return the value of m_crm_meeting_invitees.id
     *
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
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
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
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
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
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
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
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
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
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
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
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
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
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
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
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
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
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
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
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
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
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
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
     */
    public void setSource(String source) {
        this.source = source;
    }
}