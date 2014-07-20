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

import com.esofthead.mycollab.common.domain.ActivityStream;
import com.esofthead.mycollab.common.domain.ActivityStreamExample;
import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@SuppressWarnings({ "ucd", "rawtypes" })
public interface ActivityStreamMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_activitystream
     *
     * @mbggenerated Sun Jul 20 11:11:56 ICT 2014
     */
    int countByExample(ActivityStreamExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_activitystream
     *
     * @mbggenerated Sun Jul 20 11:11:56 ICT 2014
     */
    int deleteByExample(ActivityStreamExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_activitystream
     *
     * @mbggenerated Sun Jul 20 11:11:56 ICT 2014
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_activitystream
     *
     * @mbggenerated Sun Jul 20 11:11:56 ICT 2014
     */
    int insert(ActivityStream record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_activitystream
     *
     * @mbggenerated Sun Jul 20 11:11:56 ICT 2014
     */
    int insertSelective(ActivityStream record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_activitystream
     *
     * @mbggenerated Sun Jul 20 11:11:56 ICT 2014
     */
    List<ActivityStream> selectByExampleWithBLOBs(ActivityStreamExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_activitystream
     *
     * @mbggenerated Sun Jul 20 11:11:56 ICT 2014
     */
    List<ActivityStream> selectByExample(ActivityStreamExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_activitystream
     *
     * @mbggenerated Sun Jul 20 11:11:56 ICT 2014
     */
    ActivityStream selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_activitystream
     *
     * @mbggenerated Sun Jul 20 11:11:56 ICT 2014
     */
    int updateByExampleSelective(@Param("record") ActivityStream record, @Param("example") ActivityStreamExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_activitystream
     *
     * @mbggenerated Sun Jul 20 11:11:56 ICT 2014
     */
    int updateByExampleWithBLOBs(@Param("record") ActivityStream record, @Param("example") ActivityStreamExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_activitystream
     *
     * @mbggenerated Sun Jul 20 11:11:56 ICT 2014
     */
    int updateByExample(@Param("record") ActivityStream record, @Param("example") ActivityStreamExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_activitystream
     *
     * @mbggenerated Sun Jul 20 11:11:56 ICT 2014
     */
    int updateByPrimaryKeySelective(ActivityStream record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_activitystream
     *
     * @mbggenerated Sun Jul 20 11:11:56 ICT 2014
     */
    int updateByPrimaryKeyWithBLOBs(ActivityStream record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_activitystream
     *
     * @mbggenerated Sun Jul 20 11:11:56 ICT 2014
     */
    int updateByPrimaryKey(ActivityStream record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_activitystream
     *
     * @mbggenerated Sun Jul 20 11:11:56 ICT 2014
     */
    Integer insertAndReturnKey(ActivityStream value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_activitystream
     *
     * @mbggenerated Sun Jul 20 11:11:56 ICT 2014
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_activitystream
     *
     * @mbggenerated Sun Jul 20 11:11:56 ICT 2014
     */
    void massUpdateWithSession(@Param("record") ActivityStream record, @Param("primaryKeys") List primaryKeys);
}