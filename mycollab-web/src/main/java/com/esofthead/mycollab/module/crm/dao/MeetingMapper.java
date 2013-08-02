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
     * @mbggenerated Wed Jul 10 21:12:49 GMT+07:00 2013
     */
    int countByExample(MeetingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_meeting
     *
     * @mbggenerated Wed Jul 10 21:12:49 GMT+07:00 2013
     */
    int deleteByExample(MeetingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_meeting
     *
     * @mbggenerated Wed Jul 10 21:12:49 GMT+07:00 2013
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_meeting
     *
     * @mbggenerated Wed Jul 10 21:12:49 GMT+07:00 2013
     */
    int insert(Meeting record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_meeting
     *
     * @mbggenerated Wed Jul 10 21:12:49 GMT+07:00 2013
     */
    int insertSelective(Meeting record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_meeting
     *
     * @mbggenerated Wed Jul 10 21:12:49 GMT+07:00 2013
     */
    List<Meeting> selectByExampleWithBLOBs(MeetingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_meeting
     *
     * @mbggenerated Wed Jul 10 21:12:49 GMT+07:00 2013
     */
    List<Meeting> selectByExample(MeetingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_meeting
     *
     * @mbggenerated Wed Jul 10 21:12:49 GMT+07:00 2013
     */
    Meeting selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_meeting
     *
     * @mbggenerated Wed Jul 10 21:12:49 GMT+07:00 2013
     */
    int updateByExampleSelective(@Param("record") Meeting record, @Param("example") MeetingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_meeting
     *
     * @mbggenerated Wed Jul 10 21:12:49 GMT+07:00 2013
     */
    int updateByExampleWithBLOBs(@Param("record") Meeting record, @Param("example") MeetingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_meeting
     *
     * @mbggenerated Wed Jul 10 21:12:49 GMT+07:00 2013
     */
    int updateByExample(@Param("record") Meeting record, @Param("example") MeetingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_meeting
     *
     * @mbggenerated Wed Jul 10 21:12:49 GMT+07:00 2013
     */
    int updateByPrimaryKeySelective(Meeting record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_meeting
     *
     * @mbggenerated Wed Jul 10 21:12:49 GMT+07:00 2013
     */
    Integer insertAndReturnKey(Meeting value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_meeting
     *
     * @mbggenerated Wed Jul 10 21:12:49 GMT+07:00 2013
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_meeting
     *
     * @mbggenerated Wed Jul 10 21:12:49 GMT+07:00 2013
     */
    void massUpdateWithSession(@Param("record") Meeting record, @Param("primaryKeys") List primaryKeys);
}