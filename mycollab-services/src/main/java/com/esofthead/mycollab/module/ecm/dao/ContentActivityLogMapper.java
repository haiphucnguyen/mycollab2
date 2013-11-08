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
package com.esofthead.mycollab.module.ecm.dao;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.module.ecm.domain.ContentActivityLogExample;
import com.esofthead.mycollab.module.ecm.domain.ContentActivityLogWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ContentActivityLogMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_ecm_activity_log
     *
     * @mbggenerated Sat Sep 21 21:19:09 ICT 2013
     */
    int countByExample(ContentActivityLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_ecm_activity_log
     *
     * @mbggenerated Sat Sep 21 21:19:09 ICT 2013
     */
    int deleteByExample(ContentActivityLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_ecm_activity_log
     *
     * @mbggenerated Sat Sep 21 21:19:09 ICT 2013
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_ecm_activity_log
     *
     * @mbggenerated Sat Sep 21 21:19:09 ICT 2013
     */
    int insert(ContentActivityLogWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_ecm_activity_log
     *
     * @mbggenerated Sat Sep 21 21:19:09 ICT 2013
     */
    int insertSelective(ContentActivityLogWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_ecm_activity_log
     *
     * @mbggenerated Sat Sep 21 21:19:09 ICT 2013
     */
    List<ContentActivityLogWithBLOBs> selectByExampleWithBLOBs(ContentActivityLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_ecm_activity_log
     *
     * @mbggenerated Sat Sep 21 21:19:09 ICT 2013
     */
    ContentActivityLogWithBLOBs selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_ecm_activity_log
     *
     * @mbggenerated Sat Sep 21 21:19:09 ICT 2013
     */
    int updateByExampleSelective(@Param("record") ContentActivityLogWithBLOBs record, @Param("example") ContentActivityLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_ecm_activity_log
     *
     * @mbggenerated Sat Sep 21 21:19:09 ICT 2013
     */
    int updateByExampleWithBLOBs(@Param("record") ContentActivityLogWithBLOBs record, @Param("example") ContentActivityLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_ecm_activity_log
     *
     * @mbggenerated Sat Sep 21 21:19:09 ICT 2013
     */
    int updateByPrimaryKeySelective(ContentActivityLogWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_ecm_activity_log
     *
     * @mbggenerated Sat Sep 21 21:19:09 ICT 2013
     */
    Integer insertAndReturnKey(ContentActivityLogWithBLOBs value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_ecm_activity_log
     *
     * @mbggenerated Sat Sep 21 21:19:09 ICT 2013
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_ecm_activity_log
     *
     * @mbggenerated Sat Sep 21 21:19:09 ICT 2013
     */
    void massUpdateWithSession(@Param("record") ContentActivityLogWithBLOBs record, @Param("primaryKeys") List primaryKeys);
}