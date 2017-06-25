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
     * @mbg.generated Sun Jun 25 11:59:48 ICT 2017
     */
    long countByExample(TimelineTrackingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_timeline_tracking
     *
     * @mbg.generated Sun Jun 25 11:59:48 ICT 2017
     */
    int deleteByExample(TimelineTrackingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_timeline_tracking
     *
     * @mbg.generated Sun Jun 25 11:59:48 ICT 2017
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_timeline_tracking
     *
     * @mbg.generated Sun Jun 25 11:59:48 ICT 2017
     */
    int insert(TimelineTracking record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_timeline_tracking
     *
     * @mbg.generated Sun Jun 25 11:59:48 ICT 2017
     */
    int insertSelective(TimelineTracking record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_timeline_tracking
     *
     * @mbg.generated Sun Jun 25 11:59:48 ICT 2017
     */
    List<TimelineTracking> selectByExample(TimelineTrackingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_timeline_tracking
     *
     * @mbg.generated Sun Jun 25 11:59:48 ICT 2017
     */
    TimelineTracking selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_timeline_tracking
     *
     * @mbg.generated Sun Jun 25 11:59:48 ICT 2017
     */
    int updateByExampleSelective(@Param("record") TimelineTracking record, @Param("example") TimelineTrackingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_timeline_tracking
     *
     * @mbg.generated Sun Jun 25 11:59:48 ICT 2017
     */
    int updateByExample(@Param("record") TimelineTracking record, @Param("example") TimelineTrackingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_timeline_tracking
     *
     * @mbg.generated Sun Jun 25 11:59:48 ICT 2017
     */
    int updateByPrimaryKeySelective(TimelineTracking record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_timeline_tracking
     *
     * @mbg.generated Sun Jun 25 11:59:48 ICT 2017
     */
    int updateByPrimaryKey(TimelineTracking record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_timeline_tracking
     *
     * @mbg.generated Sun Jun 25 11:59:48 ICT 2017
     */
    Integer insertAndReturnKey(TimelineTracking value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_timeline_tracking
     *
     * @mbg.generated Sun Jun 25 11:59:48 ICT 2017
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_timeline_tracking
     *
     * @mbg.generated Sun Jun 25 11:59:48 ICT 2017
     */
    void massUpdateWithSession(@Param("record") TimelineTracking record, @Param("primaryKeys") List primaryKeys);
}