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
import com.esofthead.mycollab.module.crm.domain.OpportunityLead;
import com.esofthead.mycollab.module.crm.domain.OpportunityLeadExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@SuppressWarnings({ "ucd", "rawtypes" })
public interface OpportunityLeadMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunities_leads
     *
     * @mbggenerated Tue Jun 23 17:28:43 ICT 2015
     */
    int countByExample(OpportunityLeadExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunities_leads
     *
     * @mbggenerated Tue Jun 23 17:28:43 ICT 2015
     */
    int deleteByExample(OpportunityLeadExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunities_leads
     *
     * @mbggenerated Tue Jun 23 17:28:43 ICT 2015
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunities_leads
     *
     * @mbggenerated Tue Jun 23 17:28:43 ICT 2015
     */
    int insert(OpportunityLead record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunities_leads
     *
     * @mbggenerated Tue Jun 23 17:28:43 ICT 2015
     */
    int insertSelective(OpportunityLead record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunities_leads
     *
     * @mbggenerated Tue Jun 23 17:28:43 ICT 2015
     */
    List<OpportunityLead> selectByExample(OpportunityLeadExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunities_leads
     *
     * @mbggenerated Tue Jun 23 17:28:43 ICT 2015
     */
    OpportunityLead selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunities_leads
     *
     * @mbggenerated Tue Jun 23 17:28:43 ICT 2015
     */
    int updateByExampleSelective(@Param("record") OpportunityLead record, @Param("example") OpportunityLeadExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunities_leads
     *
     * @mbggenerated Tue Jun 23 17:28:43 ICT 2015
     */
    int updateByExample(@Param("record") OpportunityLead record, @Param("example") OpportunityLeadExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunities_leads
     *
     * @mbggenerated Tue Jun 23 17:28:43 ICT 2015
     */
    int updateByPrimaryKeySelective(OpportunityLead record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunities_leads
     *
     * @mbggenerated Tue Jun 23 17:28:43 ICT 2015
     */
    int updateByPrimaryKey(OpportunityLead record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunities_leads
     *
     * @mbggenerated Tue Jun 23 17:28:43 ICT 2015
     */
    Integer insertAndReturnKey(OpportunityLead value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunities_leads
     *
     * @mbggenerated Tue Jun 23 17:28:43 ICT 2015
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunities_leads
     *
     * @mbggenerated Tue Jun 23 17:28:43 ICT 2015
     */
    void massUpdateWithSession(@Param("record") OpportunityLead record, @Param("primaryKeys") List primaryKeys);
}