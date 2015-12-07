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
package com.esofthead.mycollab.module.tracker.dao;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.module.tracker.domain.MetaData;
import com.esofthead.mycollab.module.tracker.domain.MetaDataExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@SuppressWarnings({ "ucd", "rawtypes" })
public interface MetaDataMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_metadata
     *
     * @mbggenerated Mon Dec 07 11:25:15 ICT 2015
     */
    int countByExample(MetaDataExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_metadata
     *
     * @mbggenerated Mon Dec 07 11:25:15 ICT 2015
     */
    int deleteByExample(MetaDataExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_metadata
     *
     * @mbggenerated Mon Dec 07 11:25:15 ICT 2015
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_metadata
     *
     * @mbggenerated Mon Dec 07 11:25:15 ICT 2015
     */
    int insert(MetaData record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_metadata
     *
     * @mbggenerated Mon Dec 07 11:25:15 ICT 2015
     */
    int insertSelective(MetaData record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_metadata
     *
     * @mbggenerated Mon Dec 07 11:25:15 ICT 2015
     */
    List<MetaData> selectByExampleWithBLOBs(MetaDataExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_metadata
     *
     * @mbggenerated Mon Dec 07 11:25:15 ICT 2015
     */
    List<MetaData> selectByExample(MetaDataExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_metadata
     *
     * @mbggenerated Mon Dec 07 11:25:15 ICT 2015
     */
    MetaData selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_metadata
     *
     * @mbggenerated Mon Dec 07 11:25:15 ICT 2015
     */
    int updateByExampleSelective(@Param("record") MetaData record, @Param("example") MetaDataExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_metadata
     *
     * @mbggenerated Mon Dec 07 11:25:15 ICT 2015
     */
    int updateByExampleWithBLOBs(@Param("record") MetaData record, @Param("example") MetaDataExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_metadata
     *
     * @mbggenerated Mon Dec 07 11:25:15 ICT 2015
     */
    int updateByExample(@Param("record") MetaData record, @Param("example") MetaDataExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_metadata
     *
     * @mbggenerated Mon Dec 07 11:25:15 ICT 2015
     */
    int updateByPrimaryKeySelective(MetaData record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_metadata
     *
     * @mbggenerated Mon Dec 07 11:25:15 ICT 2015
     */
    int updateByPrimaryKeyWithBLOBs(MetaData record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_metadata
     *
     * @mbggenerated Mon Dec 07 11:25:15 ICT 2015
     */
    int updateByPrimaryKey(MetaData record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_metadata
     *
     * @mbggenerated Mon Dec 07 11:25:15 ICT 2015
     */
    Integer insertAndReturnKey(MetaData value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_metadata
     *
     * @mbggenerated Mon Dec 07 11:25:15 ICT 2015
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_metadata
     *
     * @mbggenerated Mon Dec 07 11:25:15 ICT 2015
     */
    void massUpdateWithSession(@Param("record") MetaData record, @Param("primaryKeys") List primaryKeys);
}