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
package com.esofthead.mycollab.module.project.dao;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.module.project.domain.StandupReportExample;
import com.esofthead.mycollab.module.project.domain.StandupReportWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@SuppressWarnings("ucd")
public interface StandupReportMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_standup
     *
     * @mbggenerated Sat Jul 19 21:11:34 ICT 2014
     */
    int countByExample(StandupReportExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_standup
     *
     * @mbggenerated Sat Jul 19 21:11:34 ICT 2014
     */
    int deleteByExample(StandupReportExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_standup
     *
     * @mbggenerated Sat Jul 19 21:11:34 ICT 2014
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_standup
     *
     * @mbggenerated Sat Jul 19 21:11:34 ICT 2014
     */
    int insert(StandupReportWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_standup
     *
     * @mbggenerated Sat Jul 19 21:11:34 ICT 2014
     */
    int insertSelective(StandupReportWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_standup
     *
     * @mbggenerated Sat Jul 19 21:11:34 ICT 2014
     */
    List<StandupReportWithBLOBs> selectByExampleWithBLOBs(StandupReportExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_standup
     *
     * @mbggenerated Sat Jul 19 21:11:34 ICT 2014
     */
    StandupReportWithBLOBs selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_standup
     *
     * @mbggenerated Sat Jul 19 21:11:34 ICT 2014
     */
    int updateByExampleSelective(@Param("record") StandupReportWithBLOBs record, @Param("example") StandupReportExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_standup
     *
     * @mbggenerated Sat Jul 19 21:11:34 ICT 2014
     */
    int updateByExampleWithBLOBs(@Param("record") StandupReportWithBLOBs record, @Param("example") StandupReportExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_standup
     *
     * @mbggenerated Sat Jul 19 21:11:34 ICT 2014
     */
    int updateByPrimaryKeySelective(StandupReportWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_standup
     *
     * @mbggenerated Sat Jul 19 21:11:34 ICT 2014
     */
    int updateByPrimaryKeyWithBLOBs(StandupReportWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_standup
     *
     * @mbggenerated Sat Jul 19 21:11:34 ICT 2014
     */
    Integer insertAndReturnKey(StandupReportWithBLOBs value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_standup
     *
     * @mbggenerated Sat Jul 19 21:11:34 ICT 2014
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_standup
     *
     * @mbggenerated Sat Jul 19 21:11:34 ICT 2014
     */
    void massUpdateWithSession(@Param("record") StandupReportWithBLOBs record, @Param("primaryKeys") List primaryKeys);
}