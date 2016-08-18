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
package com.mycollab.module.crm.dao;

import com.mycollab.db.persistence.ICrudGenericDAO;
import com.mycollab.module.crm.domain.CrmTask;
import com.mycollab.module.crm.domain.CrmTaskExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@SuppressWarnings({ "ucd", "rawtypes" })
public interface CrmTaskMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_task
     *
     * @mbggenerated Wed Aug 17 20:00:14 ICT 2016
     */
    int countByExample(CrmTaskExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_task
     *
     * @mbggenerated Wed Aug 17 20:00:14 ICT 2016
     */
    int deleteByExample(CrmTaskExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_task
     *
     * @mbggenerated Wed Aug 17 20:00:14 ICT 2016
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_task
     *
     * @mbggenerated Wed Aug 17 20:00:14 ICT 2016
     */
    int insert(CrmTask record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_task
     *
     * @mbggenerated Wed Aug 17 20:00:14 ICT 2016
     */
    int insertSelective(CrmTask record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_task
     *
     * @mbggenerated Wed Aug 17 20:00:14 ICT 2016
     */
    List<CrmTask> selectByExampleWithBLOBs(CrmTaskExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_task
     *
     * @mbggenerated Wed Aug 17 20:00:14 ICT 2016
     */
    List<CrmTask> selectByExample(CrmTaskExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_task
     *
     * @mbggenerated Wed Aug 17 20:00:14 ICT 2016
     */
    CrmTask selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_task
     *
     * @mbggenerated Wed Aug 17 20:00:14 ICT 2016
     */
    int updateByExampleSelective(@Param("record") CrmTask record, @Param("example") CrmTaskExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_task
     *
     * @mbggenerated Wed Aug 17 20:00:14 ICT 2016
     */
    int updateByExampleWithBLOBs(@Param("record") CrmTask record, @Param("example") CrmTaskExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_task
     *
     * @mbggenerated Wed Aug 17 20:00:14 ICT 2016
     */
    int updateByExample(@Param("record") CrmTask record, @Param("example") CrmTaskExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_task
     *
     * @mbggenerated Wed Aug 17 20:00:14 ICT 2016
     */
    int updateByPrimaryKeySelective(CrmTask record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_task
     *
     * @mbggenerated Wed Aug 17 20:00:14 ICT 2016
     */
    int updateByPrimaryKeyWithBLOBs(CrmTask record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_task
     *
     * @mbggenerated Wed Aug 17 20:00:14 ICT 2016
     */
    int updateByPrimaryKey(CrmTask record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_task
     *
     * @mbggenerated Wed Aug 17 20:00:14 ICT 2016
     */
    Integer insertAndReturnKey(CrmTask value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_task
     *
     * @mbggenerated Wed Aug 17 20:00:14 ICT 2016
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_task
     *
     * @mbggenerated Wed Aug 17 20:00:14 ICT 2016
     */
    void massUpdateWithSession(@Param("record") CrmTask record, @Param("primaryKeys") List primaryKeys);
}