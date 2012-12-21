package com.esofthead.mycollab.module.crm.dao;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.module.crm.domain.Meeting;
import com.esofthead.mycollab.module.crm.domain.MeetingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MeetingMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_meeting
     *
     * @mbggenerated Fri Dec 21 13:02:39 GMT+07:00 2012
     */
    int countByExample(MeetingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_meeting
     *
     * @mbggenerated Fri Dec 21 13:02:39 GMT+07:00 2012
     */
    int deleteByExample(MeetingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_meeting
     *
     * @mbggenerated Fri Dec 21 13:02:39 GMT+07:00 2012
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_meeting
     *
     * @mbggenerated Fri Dec 21 13:02:39 GMT+07:00 2012
     */
    int insert(Meeting record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_meeting
     *
     * @mbggenerated Fri Dec 21 13:02:39 GMT+07:00 2012
     */
    int insertSelective(Meeting record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_meeting
     *
     * @mbggenerated Fri Dec 21 13:02:39 GMT+07:00 2012
     */
    List<Meeting> selectByExample(MeetingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_meeting
     *
     * @mbggenerated Fri Dec 21 13:02:39 GMT+07:00 2012
     */
    Meeting selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_meeting
     *
     * @mbggenerated Fri Dec 21 13:02:39 GMT+07:00 2012
     */
    int updateByExampleSelective(@Param("record") Meeting record, @Param("example") MeetingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_meeting
     *
     * @mbggenerated Fri Dec 21 13:02:39 GMT+07:00 2012
     */
    int updateByExample(@Param("record") Meeting record, @Param("example") MeetingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_meeting
     *
     * @mbggenerated Fri Dec 21 13:02:39 GMT+07:00 2012
     */
    int updateByPrimaryKeySelective(Meeting record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_meeting
     *
     * @mbggenerated Fri Dec 21 13:02:39 GMT+07:00 2012
     */
    int updateByPrimaryKey(Meeting record);
}