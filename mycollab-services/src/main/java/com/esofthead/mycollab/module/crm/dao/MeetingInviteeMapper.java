package com.esofthead.mycollab.module.crm.dao;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.module.crm.domain.MeetingInvitee;
import com.esofthead.mycollab.module.crm.domain.MeetingInviteeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MeetingInviteeMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_meeting_invitees
     *
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
     */
    int countByExample(MeetingInviteeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_meeting_invitees
     *
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
     */
    int deleteByExample(MeetingInviteeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_meeting_invitees
     *
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_meeting_invitees
     *
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
     */
    int insert(MeetingInvitee record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_meeting_invitees
     *
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
     */
    int insertSelective(MeetingInvitee record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_meeting_invitees
     *
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
     */
    List<MeetingInvitee> selectByExample(MeetingInviteeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_meeting_invitees
     *
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
     */
    MeetingInvitee selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_meeting_invitees
     *
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
     */
    int updateByExampleSelective(@Param("record") MeetingInvitee record, @Param("example") MeetingInviteeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_meeting_invitees
     *
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
     */
    int updateByExample(@Param("record") MeetingInvitee record, @Param("example") MeetingInviteeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_meeting_invitees
     *
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
     */
    int updateByPrimaryKeySelective(MeetingInvitee record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_meeting_invitees
     *
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
     */
    Integer insertAndReturnKey(MeetingInvitee value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_meeting_invitees
     *
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_meeting_invitees
     *
     * @mbggenerated Wed Jan 15 13:36:12 ICT 2014
     */
    void massUpdateWithSession(@Param("record") MeetingInvitee record, @Param("primaryKeys") List primaryKeys);
}