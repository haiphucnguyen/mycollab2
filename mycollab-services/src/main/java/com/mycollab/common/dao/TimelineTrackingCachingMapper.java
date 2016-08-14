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
     * @mbggenerated Sun Aug 14 20:59:43 ICT 2016
     */
    int countByExample(TimelineTrackingCachingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_timeline_tracking_cache
     *
     * @mbggenerated Sun Aug 14 20:59:43 ICT 2016
     */
    int deleteByExample(TimelineTrackingCachingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_timeline_tracking_cache
     *
     * @mbggenerated Sun Aug 14 20:59:43 ICT 2016
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_timeline_tracking_cache
     *
     * @mbggenerated Sun Aug 14 20:59:43 ICT 2016
     */
    int insert(TimelineTrackingCaching record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_timeline_tracking_cache
     *
     * @mbggenerated Sun Aug 14 20:59:43 ICT 2016
     */
    int insertSelective(TimelineTrackingCaching record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_timeline_tracking_cache
     *
     * @mbggenerated Sun Aug 14 20:59:43 ICT 2016
     */
    List<TimelineTrackingCaching> selectByExample(TimelineTrackingCachingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_timeline_tracking_cache
     *
     * @mbggenerated Sun Aug 14 20:59:43 ICT 2016
     */
    TimelineTrackingCaching selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_timeline_tracking_cache
     *
     * @mbggenerated Sun Aug 14 20:59:43 ICT 2016
     */
    int updateByExampleSelective(@Param("record") TimelineTrackingCaching record, @Param("example") TimelineTrackingCachingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_timeline_tracking_cache
     *
     * @mbggenerated Sun Aug 14 20:59:43 ICT 2016
     */
    int updateByExample(@Param("record") TimelineTrackingCaching record, @Param("example") TimelineTrackingCachingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_timeline_tracking_cache
     *
     * @mbggenerated Sun Aug 14 20:59:43 ICT 2016
     */
    int updateByPrimaryKeySelective(TimelineTrackingCaching record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_timeline_tracking_cache
     *
     * @mbggenerated Sun Aug 14 20:59:43 ICT 2016
     */
    int updateByPrimaryKey(TimelineTrackingCaching record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_timeline_tracking_cache
     *
     * @mbggenerated Sun Aug 14 20:59:43 ICT 2016
     */
    Integer insertAndReturnKey(TimelineTrackingCaching value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_timeline_tracking_cache
     *
     * @mbggenerated Sun Aug 14 20:59:43 ICT 2016
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_timeline_tracking_cache
     *
     * @mbggenerated Sun Aug 14 20:59:43 ICT 2016
     */
    void massUpdateWithSession(@Param("record") TimelineTrackingCaching record, @Param("primaryKeys") List primaryKeys);
}