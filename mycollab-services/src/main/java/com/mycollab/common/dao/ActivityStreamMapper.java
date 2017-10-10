/**
 * mycollab-services - Parent pom providing dependency and plugin management for applications
		built with Maven
 * Copyright © ${project.inceptionYear} MyCollab (support@mycollab.com)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.mycollab.common.dao;

import com.mycollab.common.domain.ActivityStreamExample;
import com.mycollab.common.domain.ActivityStreamWithBLOBs;
import com.mycollab.db.persistence.ICrudGenericDAO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@SuppressWarnings({ "ucd", "rawtypes" })
public interface ActivityStreamMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_activitystream
     *
     * @mbg.generated Tue Aug 01 11:17:32 ICT 2017
     */
    long countByExample(ActivityStreamExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_activitystream
     *
     * @mbg.generated Tue Aug 01 11:17:32 ICT 2017
     */
    int deleteByExample(ActivityStreamExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_activitystream
     *
     * @mbg.generated Tue Aug 01 11:17:32 ICT 2017
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_activitystream
     *
     * @mbg.generated Tue Aug 01 11:17:32 ICT 2017
     */
    int insert(ActivityStreamWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_activitystream
     *
     * @mbg.generated Tue Aug 01 11:17:32 ICT 2017
     */
    int insertSelective(ActivityStreamWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_activitystream
     *
     * @mbg.generated Tue Aug 01 11:17:32 ICT 2017
     */
    List<ActivityStreamWithBLOBs> selectByExampleWithBLOBs(ActivityStreamExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_activitystream
     *
     * @mbg.generated Tue Aug 01 11:17:32 ICT 2017
     */
    ActivityStreamWithBLOBs selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_activitystream
     *
     * @mbg.generated Tue Aug 01 11:17:32 ICT 2017
     */
    int updateByExampleSelective(@Param("record") ActivityStreamWithBLOBs record, @Param("example") ActivityStreamExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_activitystream
     *
     * @mbg.generated Tue Aug 01 11:17:32 ICT 2017
     */
    int updateByExampleWithBLOBs(@Param("record") ActivityStreamWithBLOBs record, @Param("example") ActivityStreamExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_activitystream
     *
     * @mbg.generated Tue Aug 01 11:17:32 ICT 2017
     */
    int updateByPrimaryKeySelective(ActivityStreamWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_activitystream
     *
     * @mbg.generated Tue Aug 01 11:17:32 ICT 2017
     */
    int updateByPrimaryKeyWithBLOBs(ActivityStreamWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_activitystream
     *
     * @mbg.generated Tue Aug 01 11:17:32 ICT 2017
     */
    Integer insertAndReturnKey(ActivityStreamWithBLOBs value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_activitystream
     *
     * @mbg.generated Tue Aug 01 11:17:32 ICT 2017
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_activitystream
     *
     * @mbg.generated Tue Aug 01 11:17:32 ICT 2017
     */
    void massUpdateWithSession(@Param("record") ActivityStreamWithBLOBs record, @Param("primaryKeys") List primaryKeys);
}