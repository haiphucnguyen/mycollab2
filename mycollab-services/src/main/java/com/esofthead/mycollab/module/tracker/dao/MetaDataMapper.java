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
     * @mbggenerated Fri Mar 18 20:26:11 ICT 2016
     */
    int countByExample(MetaDataExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_metadata
     *
     * @mbggenerated Fri Mar 18 20:26:11 ICT 2016
     */
    int deleteByExample(MetaDataExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_metadata
     *
     * @mbggenerated Fri Mar 18 20:26:11 ICT 2016
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_metadata
     *
     * @mbggenerated Fri Mar 18 20:26:11 ICT 2016
     */
    int insert(MetaData record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_metadata
     *
     * @mbggenerated Fri Mar 18 20:26:11 ICT 2016
     */
    int insertSelective(MetaData record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_metadata
     *
     * @mbggenerated Fri Mar 18 20:26:11 ICT 2016
     */
    List<MetaData> selectByExampleWithBLOBs(MetaDataExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_metadata
     *
     * @mbggenerated Fri Mar 18 20:26:11 ICT 2016
     */
    List<MetaData> selectByExample(MetaDataExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_metadata
     *
     * @mbggenerated Fri Mar 18 20:26:11 ICT 2016
     */
    MetaData selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_metadata
     *
     * @mbggenerated Fri Mar 18 20:26:11 ICT 2016
     */
    int updateByExampleSelective(@Param("record") MetaData record, @Param("example") MetaDataExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_metadata
     *
     * @mbggenerated Fri Mar 18 20:26:11 ICT 2016
     */
    int updateByExampleWithBLOBs(@Param("record") MetaData record, @Param("example") MetaDataExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_metadata
     *
     * @mbggenerated Fri Mar 18 20:26:11 ICT 2016
     */
    int updateByExample(@Param("record") MetaData record, @Param("example") MetaDataExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_metadata
     *
     * @mbggenerated Fri Mar 18 20:26:11 ICT 2016
     */
    int updateByPrimaryKeySelective(MetaData record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_metadata
     *
     * @mbggenerated Fri Mar 18 20:26:11 ICT 2016
     */
    int updateByPrimaryKeyWithBLOBs(MetaData record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_metadata
     *
     * @mbggenerated Fri Mar 18 20:26:11 ICT 2016
     */
    int updateByPrimaryKey(MetaData record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_metadata
     *
     * @mbggenerated Fri Mar 18 20:26:11 ICT 2016
     */
    Integer insertAndReturnKey(MetaData value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_metadata
     *
     * @mbggenerated Fri Mar 18 20:26:11 ICT 2016
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_metadata
     *
     * @mbggenerated Fri Mar 18 20:26:11 ICT 2016
     */
    void massUpdateWithSession(@Param("record") MetaData record, @Param("primaryKeys") List primaryKeys);
}