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
package com.mycollab.module.project.dao;

import com.mycollab.db.persistence.ICrudGenericDAO;
import com.mycollab.module.project.domain.Task;
import com.mycollab.module.project.domain.TaskExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@SuppressWarnings({ "ucd", "rawtypes" })
public interface TaskMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_task
     *
     * @mbg.generated Sat Sep 24 08:44:29 ICT 2016
     */
    long countByExample(TaskExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_task
     *
     * @mbg.generated Sat Sep 24 08:44:29 ICT 2016
     */
    int deleteByExample(TaskExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_task
     *
     * @mbg.generated Sat Sep 24 08:44:29 ICT 2016
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_task
     *
     * @mbg.generated Sat Sep 24 08:44:29 ICT 2016
     */
    int insert(Task record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_task
     *
     * @mbg.generated Sat Sep 24 08:44:29 ICT 2016
     */
    int insertSelective(Task record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_task
     *
     * @mbg.generated Sat Sep 24 08:44:29 ICT 2016
     */
    List<Task> selectByExampleWithBLOBs(TaskExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_task
     *
     * @mbg.generated Sat Sep 24 08:44:29 ICT 2016
     */
    List<Task> selectByExample(TaskExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_task
     *
     * @mbg.generated Sat Sep 24 08:44:29 ICT 2016
     */
    Task selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_task
     *
     * @mbg.generated Sat Sep 24 08:44:29 ICT 2016
     */
    int updateByExampleSelective(@Param("record") Task record, @Param("example") TaskExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_task
     *
     * @mbg.generated Sat Sep 24 08:44:29 ICT 2016
     */
    int updateByExampleWithBLOBs(@Param("record") Task record, @Param("example") TaskExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_task
     *
     * @mbg.generated Sat Sep 24 08:44:29 ICT 2016
     */
    int updateByExample(@Param("record") Task record, @Param("example") TaskExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_task
     *
     * @mbg.generated Sat Sep 24 08:44:29 ICT 2016
     */
    int updateByPrimaryKeySelective(Task record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_task
     *
     * @mbg.generated Sat Sep 24 08:44:29 ICT 2016
     */
    int updateByPrimaryKeyWithBLOBs(Task record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_task
     *
     * @mbg.generated Sat Sep 24 08:44:29 ICT 2016
     */
    int updateByPrimaryKey(Task record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_task
     *
     * @mbg.generated Sat Sep 24 08:44:29 ICT 2016
     */
    Integer insertAndReturnKey(Task value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_task
     *
     * @mbg.generated Sat Sep 24 08:44:29 ICT 2016
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_task
     *
     * @mbg.generated Sat Sep 24 08:44:29 ICT 2016
     */
    void massUpdateWithSession(@Param("record") Task record, @Param("primaryKeys") List primaryKeys);
}