package com.esofthead.mycollab.common.dao;

import com.esofthead.mycollab.common.domain.TimelineTrackingCaching;
import com.esofthead.mycollab.common.domain.TimelineTrackingCachingExample;
import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@SuppressWarnings({ "ucd", "rawtypes" })
public interface TimelineTrackingCachingMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_timeline_tracking_cache
     *
     * @mbggenerated Wed Feb 03 14:56:44 ICT 2016
     */
    int countByExample(TimelineTrackingCachingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_timeline_tracking_cache
     *
     * @mbggenerated Wed Feb 03 14:56:44 ICT 2016
     */
    int deleteByExample(TimelineTrackingCachingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_timeline_tracking_cache
     *
     * @mbggenerated Wed Feb 03 14:56:44 ICT 2016
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_timeline_tracking_cache
     *
     * @mbggenerated Wed Feb 03 14:56:44 ICT 2016
     */
    int insert(TimelineTrackingCaching record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_timeline_tracking_cache
     *
     * @mbggenerated Wed Feb 03 14:56:44 ICT 2016
     */
    int insertSelective(TimelineTrackingCaching record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_timeline_tracking_cache
     *
     * @mbggenerated Wed Feb 03 14:56:44 ICT 2016
     */
    List<TimelineTrackingCaching> selectByExample(TimelineTrackingCachingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_timeline_tracking_cache
     *
     * @mbggenerated Wed Feb 03 14:56:44 ICT 2016
     */
    TimelineTrackingCaching selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_timeline_tracking_cache
     *
     * @mbggenerated Wed Feb 03 14:56:44 ICT 2016
     */
    int updateByExampleSelective(@Param("record") TimelineTrackingCaching record, @Param("example") TimelineTrackingCachingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_timeline_tracking_cache
     *
     * @mbggenerated Wed Feb 03 14:56:44 ICT 2016
     */
    int updateByExample(@Param("record") TimelineTrackingCaching record, @Param("example") TimelineTrackingCachingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_timeline_tracking_cache
     *
     * @mbggenerated Wed Feb 03 14:56:44 ICT 2016
     */
    int updateByPrimaryKeySelective(TimelineTrackingCaching record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_timeline_tracking_cache
     *
     * @mbggenerated Wed Feb 03 14:56:44 ICT 2016
     */
    int updateByPrimaryKey(TimelineTrackingCaching record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_timeline_tracking_cache
     *
     * @mbggenerated Wed Feb 03 14:56:44 ICT 2016
     */
    Integer insertAndReturnKey(TimelineTrackingCaching value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_timeline_tracking_cache
     *
     * @mbggenerated Wed Feb 03 14:56:44 ICT 2016
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_timeline_tracking_cache
     *
     * @mbggenerated Wed Feb 03 14:56:44 ICT 2016
     */
    void massUpdateWithSession(@Param("record") TimelineTrackingCaching record, @Param("primaryKeys") List primaryKeys);
}