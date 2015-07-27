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
import com.esofthead.mycollab.module.project.domain.TaskList;
import com.esofthead.mycollab.module.project.domain.TaskListExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@SuppressWarnings({ "ucd", "rawtypes" })
public interface TaskListMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_task_list
     *
     * @mbggenerated Mon Jul 27 09:45:06 ICT 2015
     */
    int countByExample(TaskListExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_task_list
     *
     * @mbggenerated Mon Jul 27 09:45:06 ICT 2015
     */
    int deleteByExample(TaskListExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_task_list
     *
     * @mbggenerated Mon Jul 27 09:45:06 ICT 2015
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_task_list
     *
     * @mbggenerated Mon Jul 27 09:45:06 ICT 2015
     */
    int insert(TaskList record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_task_list
     *
     * @mbggenerated Mon Jul 27 09:45:06 ICT 2015
     */
    int insertSelective(TaskList record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_task_list
     *
     * @mbggenerated Mon Jul 27 09:45:06 ICT 2015
     */
    List<TaskList> selectByExampleWithBLOBs(TaskListExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_task_list
     *
     * @mbggenerated Mon Jul 27 09:45:06 ICT 2015
     */
    List<TaskList> selectByExample(TaskListExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_task_list
     *
     * @mbggenerated Mon Jul 27 09:45:06 ICT 2015
     */
    TaskList selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_task_list
     *
     * @mbggenerated Mon Jul 27 09:45:06 ICT 2015
     */
    int updateByExampleSelective(@Param("record") TaskList record, @Param("example") TaskListExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_task_list
     *
     * @mbggenerated Mon Jul 27 09:45:06 ICT 2015
     */
    int updateByExampleWithBLOBs(@Param("record") TaskList record, @Param("example") TaskListExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_task_list
     *
     * @mbggenerated Mon Jul 27 09:45:06 ICT 2015
     */
    int updateByExample(@Param("record") TaskList record, @Param("example") TaskListExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_task_list
     *
     * @mbggenerated Mon Jul 27 09:45:06 ICT 2015
     */
    int updateByPrimaryKeySelective(TaskList record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_task_list
     *
     * @mbggenerated Mon Jul 27 09:45:06 ICT 2015
     */
    int updateByPrimaryKeyWithBLOBs(TaskList record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_task_list
     *
     * @mbggenerated Mon Jul 27 09:45:06 ICT 2015
     */
    int updateByPrimaryKey(TaskList record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_task_list
     *
     * @mbggenerated Mon Jul 27 09:45:06 ICT 2015
     */
    Integer insertAndReturnKey(TaskList value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_task_list
     *
     * @mbggenerated Mon Jul 27 09:45:06 ICT 2015
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_task_list
     *
     * @mbggenerated Mon Jul 27 09:45:06 ICT 2015
     */
    void massUpdateWithSession(@Param("record") TaskList record, @Param("primaryKeys") List primaryKeys);
}