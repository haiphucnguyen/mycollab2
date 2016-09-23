package com.mycollab.module.crm.dao;

import com.mycollab.db.persistence.ICrudGenericDAO;
import com.mycollab.module.crm.domain.MeetingInvitee;
import com.mycollab.module.crm.domain.MeetingInviteeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@SuppressWarnings({ "ucd", "rawtypes" })
public interface MeetingInviteeMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_meeting_invitees
     *
     * @mbg.generated Fri Sep 23 11:52:30 ICT 2016
     */
    long countByExample(MeetingInviteeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_meeting_invitees
     *
     * @mbg.generated Fri Sep 23 11:52:30 ICT 2016
     */
    int deleteByExample(MeetingInviteeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_meeting_invitees
     *
     * @mbg.generated Fri Sep 23 11:52:30 ICT 2016
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_meeting_invitees
     *
     * @mbg.generated Fri Sep 23 11:52:30 ICT 2016
     */
    int insert(MeetingInvitee record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_meeting_invitees
     *
     * @mbg.generated Fri Sep 23 11:52:30 ICT 2016
     */
    int insertSelective(MeetingInvitee record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_meeting_invitees
     *
     * @mbg.generated Fri Sep 23 11:52:30 ICT 2016
     */
    List<MeetingInvitee> selectByExample(MeetingInviteeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_meeting_invitees
     *
     * @mbg.generated Fri Sep 23 11:52:30 ICT 2016
     */
    MeetingInvitee selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_meeting_invitees
     *
     * @mbg.generated Fri Sep 23 11:52:30 ICT 2016
     */
    int updateByExampleSelective(@Param("record") MeetingInvitee record, @Param("example") MeetingInviteeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_meeting_invitees
     *
     * @mbg.generated Fri Sep 23 11:52:30 ICT 2016
     */
    int updateByExample(@Param("record") MeetingInvitee record, @Param("example") MeetingInviteeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_meeting_invitees
     *
     * @mbg.generated Fri Sep 23 11:52:30 ICT 2016
     */
    int updateByPrimaryKeySelective(MeetingInvitee record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_meeting_invitees
     *
     * @mbg.generated Fri Sep 23 11:52:30 ICT 2016
     */
    int updateByPrimaryKey(MeetingInvitee record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_meeting_invitees
     *
     * @mbg.generated Fri Sep 23 11:52:30 ICT 2016
     */
    Integer insertAndReturnKey(MeetingInvitee value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_meeting_invitees
     *
     * @mbg.generated Fri Sep 23 11:52:30 ICT 2016
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_meeting_invitees
     *
     * @mbg.generated Fri Sep 23 11:52:30 ICT 2016
     */
    void massUpdateWithSession(@Param("record") MeetingInvitee record, @Param("primaryKeys") List primaryKeys);
}