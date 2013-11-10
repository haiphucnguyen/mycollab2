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
package com.esofthead.mycollab.module.crm.dao;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.module.crm.domain.TargetGroup;
import com.esofthead.mycollab.module.crm.domain.TargetGroupExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TargetGroupMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_target_list
     *
     * @mbggenerated Mon Nov 11 00:19:40 ICT 2013
     */
    int countByExample(TargetGroupExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_target_list
     *
     * @mbggenerated Mon Nov 11 00:19:40 ICT 2013
     */
    int deleteByExample(TargetGroupExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_target_list
     *
     * @mbggenerated Mon Nov 11 00:19:40 ICT 2013
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_target_list
     *
     * @mbggenerated Mon Nov 11 00:19:40 ICT 2013
     */
    int insert(TargetGroup record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_target_list
     *
     * @mbggenerated Mon Nov 11 00:19:40 ICT 2013
     */
    int insertSelective(TargetGroup record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_target_list
     *
     * @mbggenerated Mon Nov 11 00:19:40 ICT 2013
     */
    List<TargetGroup> selectByExample(TargetGroupExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_target_list
     *
     * @mbggenerated Mon Nov 11 00:19:40 ICT 2013
     */
    TargetGroup selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_target_list
     *
     * @mbggenerated Mon Nov 11 00:19:40 ICT 2013
     */
    int updateByExampleSelective(@Param("record") TargetGroup record, @Param("example") TargetGroupExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_target_list
     *
     * @mbggenerated Mon Nov 11 00:19:40 ICT 2013
     */
    int updateByExample(@Param("record") TargetGroup record, @Param("example") TargetGroupExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_target_list
     *
     * @mbggenerated Mon Nov 11 00:19:40 ICT 2013
     */
    int updateByPrimaryKeySelective(TargetGroup record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_target_list
     *
     * @mbggenerated Mon Nov 11 00:19:40 ICT 2013
     */
    Integer insertAndReturnKey(TargetGroup value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_target_list
     *
     * @mbggenerated Mon Nov 11 00:19:40 ICT 2013
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_target_list
     *
     * @mbggenerated Mon Nov 11 00:19:40 ICT 2013
     */
    void massUpdateWithSession(@Param("record") TargetGroup record, @Param("primaryKeys") List primaryKeys);
}