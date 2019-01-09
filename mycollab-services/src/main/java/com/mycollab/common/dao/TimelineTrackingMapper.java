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
     * @mbg.generated Wed Jan 09 11:32:24 CST 2019
     */
    long countByExample(TimelineTrackingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_timeline_tracking
     *
     * @mbg.generated Wed Jan 09 11:32:24 CST 2019
     */
    int deleteByExample(TimelineTrackingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_timeline_tracking
     *
     * @mbg.generated Wed Jan 09 11:32:24 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_timeline_tracking
     *
     * @mbg.generated Wed Jan 09 11:32:24 CST 2019
     */
    int insert(TimelineTracking record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_timeline_tracking
     *
     * @mbg.generated Wed Jan 09 11:32:24 CST 2019
     */
    int insertSelective(TimelineTracking record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_timeline_tracking
     *
     * @mbg.generated Wed Jan 09 11:32:24 CST 2019
     */
    List<TimelineTracking> selectByExample(TimelineTrackingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_timeline_tracking
     *
     * @mbg.generated Wed Jan 09 11:32:24 CST 2019
     */
    TimelineTracking selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_timeline_tracking
     *
     * @mbg.generated Wed Jan 09 11:32:24 CST 2019
     */
    int updateByExampleSelective(@Param("record") TimelineTracking record, @Param("example") TimelineTrackingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_timeline_tracking
     *
     * @mbg.generated Wed Jan 09 11:32:24 CST 2019
     */
    int updateByExample(@Param("record") TimelineTracking record, @Param("example") TimelineTrackingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_timeline_tracking
     *
     * @mbg.generated Wed Jan 09 11:32:24 CST 2019
     */
    int updateByPrimaryKeySelective(TimelineTracking record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_timeline_tracking
     *
     * @mbg.generated Wed Jan 09 11:32:24 CST 2019
     */
    int updateByPrimaryKey(TimelineTracking record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_timeline_tracking
     *
     * @mbg.generated Wed Jan 09 11:32:24 CST 2019
     */
    Integer insertAndReturnKey(TimelineTracking value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_timeline_tracking
     *
     * @mbg.generated Wed Jan 09 11:32:24 CST 2019
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_timeline_tracking
     *
     * @mbg.generated Wed Jan 09 11:32:24 CST 2019
     */
    void massUpdateWithSession(@Param("record") TimelineTracking record, @Param("primaryKeys") List primaryKeys);
}