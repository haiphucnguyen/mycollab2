package com.mycollab.common.dao;

import com.mycollab.common.domain.TimelineTrackingCaching;
import com.mycollab.common.domain.TimelineTrackingCachingExample;
import com.mycollab.db.persistence.ICrudGenericDAO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@SuppressWarnings({ "ucd", "rawtypes" })
public interface TimelineTrackingCachingMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_timeline_tracking_cache
     *
     * @mbg.generated Thu Jan 24 08:25:33 CST 2019
     */
    long countByExample(TimelineTrackingCachingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_timeline_tracking_cache
     *
     * @mbg.generated Thu Jan 24 08:25:33 CST 2019
     */
    int deleteByExample(TimelineTrackingCachingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_timeline_tracking_cache
     *
     * @mbg.generated Thu Jan 24 08:25:33 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_timeline_tracking_cache
     *
     * @mbg.generated Thu Jan 24 08:25:33 CST 2019
     */
    int insert(TimelineTrackingCaching record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_timeline_tracking_cache
     *
     * @mbg.generated Thu Jan 24 08:25:33 CST 2019
     */
    int insertSelective(TimelineTrackingCaching record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_timeline_tracking_cache
     *
     * @mbg.generated Thu Jan 24 08:25:33 CST 2019
     */
    List<TimelineTrackingCaching> selectByExample(TimelineTrackingCachingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_timeline_tracking_cache
     *
     * @mbg.generated Thu Jan 24 08:25:33 CST 2019
     */
    TimelineTrackingCaching selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_timeline_tracking_cache
     *
     * @mbg.generated Thu Jan 24 08:25:33 CST 2019
     */
    int updateByExampleSelective(@Param("record") TimelineTrackingCaching record, @Param("example") TimelineTrackingCachingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_timeline_tracking_cache
     *
     * @mbg.generated Thu Jan 24 08:25:33 CST 2019
     */
    int updateByExample(@Param("record") TimelineTrackingCaching record, @Param("example") TimelineTrackingCachingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_timeline_tracking_cache
     *
     * @mbg.generated Thu Jan 24 08:25:33 CST 2019
     */
    int updateByPrimaryKeySelective(TimelineTrackingCaching record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_timeline_tracking_cache
     *
     * @mbg.generated Thu Jan 24 08:25:33 CST 2019
     */
    int updateByPrimaryKey(TimelineTrackingCaching record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_timeline_tracking_cache
     *
     * @mbg.generated Thu Jan 24 08:25:33 CST 2019
     */
    Integer insertAndReturnKey(TimelineTrackingCaching value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_timeline_tracking_cache
     *
     * @mbg.generated Thu Jan 24 08:25:33 CST 2019
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_timeline_tracking_cache
     *
     * @mbg.generated Thu Jan 24 08:25:33 CST 2019
     */
    void massUpdateWithSession(@Param("record") TimelineTrackingCaching record, @Param("primaryKeys") List primaryKeys);
}