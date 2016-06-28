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
package com.esofthead.mycollab.common.dao;

import com.esofthead.mycollab.common.domain.ActivityStreamExample;
import com.esofthead.mycollab.common.domain.ActivityStreamWithBLOBs;
import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@SuppressWarnings({ "ucd", "rawtypes" })
public interface ActivityStreamMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_activitystream
     *
     * @mbggenerated Tue Jun 28 11:03:21 ICT 2016
     */
    int countByExample(ActivityStreamExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_activitystream
     *
     * @mbggenerated Tue Jun 28 11:03:21 ICT 2016
     */
    int deleteByExample(ActivityStreamExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_activitystream
     *
     * @mbggenerated Tue Jun 28 11:03:21 ICT 2016
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_activitystream
     *
     * @mbggenerated Tue Jun 28 11:03:21 ICT 2016
     */
    int insert(ActivityStreamWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_activitystream
     *
     * @mbggenerated Tue Jun 28 11:03:21 ICT 2016
     */
    int insertSelective(ActivityStreamWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_activitystream
     *
     * @mbggenerated Tue Jun 28 11:03:21 ICT 2016
     */
    List<ActivityStreamWithBLOBs> selectByExampleWithBLOBs(ActivityStreamExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_activitystream
     *
     * @mbggenerated Tue Jun 28 11:03:21 ICT 2016
     */
    ActivityStreamWithBLOBs selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_activitystream
     *
     * @mbggenerated Tue Jun 28 11:03:21 ICT 2016
     */
    int updateByExampleSelective(@Param("record") ActivityStreamWithBLOBs record, @Param("example") ActivityStreamExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_activitystream
     *
     * @mbggenerated Tue Jun 28 11:03:21 ICT 2016
     */
    int updateByExampleWithBLOBs(@Param("record") ActivityStreamWithBLOBs record, @Param("example") ActivityStreamExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_activitystream
     *
     * @mbggenerated Tue Jun 28 11:03:21 ICT 2016
     */
    int updateByPrimaryKeySelective(ActivityStreamWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_activitystream
     *
     * @mbggenerated Tue Jun 28 11:03:21 ICT 2016
     */
    int updateByPrimaryKeyWithBLOBs(ActivityStreamWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_activitystream
     *
     * @mbggenerated Tue Jun 28 11:03:21 ICT 2016
     */
    Integer insertAndReturnKey(ActivityStreamWithBLOBs value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_activitystream
     *
     * @mbggenerated Tue Jun 28 11:03:21 ICT 2016
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_activitystream
     *
     * @mbggenerated Tue Jun 28 11:03:21 ICT 2016
     */
    void massUpdateWithSession(@Param("record") ActivityStreamWithBLOBs record, @Param("primaryKeys") List primaryKeys);
}