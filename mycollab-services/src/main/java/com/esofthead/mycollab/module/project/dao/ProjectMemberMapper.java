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
import com.esofthead.mycollab.module.project.domain.ProjectMember;
import com.esofthead.mycollab.module.project.domain.ProjectMemberExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@SuppressWarnings({ "ucd", "rawtypes" })
public interface ProjectMemberMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_member
     *
     * @mbggenerated Mon Dec 07 11:25:15 ICT 2015
     */
    int countByExample(ProjectMemberExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_member
     *
     * @mbggenerated Mon Dec 07 11:25:15 ICT 2015
     */
    int deleteByExample(ProjectMemberExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_member
     *
     * @mbggenerated Mon Dec 07 11:25:15 ICT 2015
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_member
     *
     * @mbggenerated Mon Dec 07 11:25:15 ICT 2015
     */
    int insert(ProjectMember record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_member
     *
     * @mbggenerated Mon Dec 07 11:25:15 ICT 2015
     */
    int insertSelective(ProjectMember record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_member
     *
     * @mbggenerated Mon Dec 07 11:25:15 ICT 2015
     */
    List<ProjectMember> selectByExample(ProjectMemberExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_member
     *
     * @mbggenerated Mon Dec 07 11:25:15 ICT 2015
     */
    ProjectMember selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_member
     *
     * @mbggenerated Mon Dec 07 11:25:15 ICT 2015
     */
    int updateByExampleSelective(@Param("record") ProjectMember record, @Param("example") ProjectMemberExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_member
     *
     * @mbggenerated Mon Dec 07 11:25:15 ICT 2015
     */
    int updateByExample(@Param("record") ProjectMember record, @Param("example") ProjectMemberExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_member
     *
     * @mbggenerated Mon Dec 07 11:25:15 ICT 2015
     */
    int updateByPrimaryKeySelective(ProjectMember record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_member
     *
     * @mbggenerated Mon Dec 07 11:25:15 ICT 2015
     */
    int updateByPrimaryKey(ProjectMember record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_member
     *
     * @mbggenerated Mon Dec 07 11:25:15 ICT 2015
     */
    Integer insertAndReturnKey(ProjectMember value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_member
     *
     * @mbggenerated Mon Dec 07 11:25:15 ICT 2015
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_member
     *
     * @mbggenerated Mon Dec 07 11:25:15 ICT 2015
     */
    void massUpdateWithSession(@Param("record") ProjectMember record, @Param("primaryKeys") List primaryKeys);
}