package com.mycollab.common.dao;

import com.mycollab.common.domain.TimelineTracking;
import com.mycollab.common.domain.TimelineTrackingExample;
import com.mycollab.db.persistence.ICrudGenericDAO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@SuppressWarnings({ "ucd", "rawtypes" })
public interface TimelineTrackingMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_timeline_tracking
     *
     * @mbg.generated Thu May 31 21:45:18 ICT 2018
     */
    long countByExample(TimelineTrackingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_timeline_tracking
     *
     * @mbg.generated Thu May 31 21:45:18 ICT 2018
     */
    int deleteByExample(TimelineTrackingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_timeline_tracking
     *
     * @mbg.generated Thu May 31 21:45:18 ICT 2018
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_timeline_tracking
     *
     * @mbg.generated Thu May 31 21:45:18 ICT 2018
     */
    int insert(TimelineTracking record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_timeline_tracking
     *
     * @mbg.generated Thu May 31 21:45:18 ICT 2018
     */
    int insertSelective(TimelineTracking record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_timeline_tracking
     *
     * @mbg.generated Thu May 31 21:45:18 ICT 2018
     */
    List<TimelineTracking> selectByExample(TimelineTrackingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_timeline_tracking
     *
     * @mbg.generated Thu May 31 21:45:18 ICT 2018
     */
    TimelineTracking selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_timeline_tracking
     *
     * @mbg.generated Thu May 31 21:45:18 ICT 2018
     */
    int updateByExampleSelective(@Param("record") TimelineTracking record, @Param("example") TimelineTrackingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_timeline_tracking
     *
     * @mbg.generated Thu May 31 21:45:18 ICT 2018
     */
    int updateByExample(@Param("record") TimelineTracking record, @Param("example") TimelineTrackingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_timeline_tracking
     *
     * @mbg.generated Thu May 31 21:45:18 ICT 2018
     */
    int updateByPrimaryKeySelective(TimelineTracking record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_timeline_tracking
     *
     * @mbg.generated Thu May 31 21:45:18 ICT 2018
     */
    int updateByPrimaryKey(TimelineTracking record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_timeline_tracking
     *
     * @mbg.generated Thu May 31 21:45:18 ICT 2018
     */
    Integer insertAndReturnKey(TimelineTracking value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_timeline_tracking
     *
     * @mbg.generated Thu May 31 21:45:18 ICT 2018
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_timeline_tracking
     *
     * @mbg.generated Thu May 31 21:45:18 ICT 2018
     */
    void massUpdateWithSession(@Param("record") TimelineTracking record, @Param("primaryKeys") List primaryKeys);
}