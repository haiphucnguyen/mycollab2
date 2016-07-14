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
import com.mycollab.module.crm.domain.CampaignContact;
import com.mycollab.module.crm.domain.CampaignContactExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@SuppressWarnings({ "ucd", "rawtypes" })
public interface CampaignContactMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaigns_contacts
     *
     * @mbggenerated Thu Jul 14 19:06:14 ICT 2016
     */
    int countByExample(CampaignContactExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaigns_contacts
     *
     * @mbggenerated Thu Jul 14 19:06:14 ICT 2016
     */
    int deleteByExample(CampaignContactExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaigns_contacts
     *
     * @mbggenerated Thu Jul 14 19:06:14 ICT 2016
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaigns_contacts
     *
     * @mbggenerated Thu Jul 14 19:06:14 ICT 2016
     */
    int insert(CampaignContact record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaigns_contacts
     *
     * @mbggenerated Thu Jul 14 19:06:14 ICT 2016
     */
    int insertSelective(CampaignContact record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaigns_contacts
     *
     * @mbggenerated Thu Jul 14 19:06:14 ICT 2016
     */
    List<CampaignContact> selectByExample(CampaignContactExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaigns_contacts
     *
     * @mbggenerated Thu Jul 14 19:06:14 ICT 2016
     */
    CampaignContact selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaigns_contacts
     *
     * @mbggenerated Thu Jul 14 19:06:14 ICT 2016
     */
    int updateByExampleSelective(@Param("record") CampaignContact record, @Param("example") CampaignContactExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaigns_contacts
     *
     * @mbggenerated Thu Jul 14 19:06:14 ICT 2016
     */
    int updateByExample(@Param("record") CampaignContact record, @Param("example") CampaignContactExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaigns_contacts
     *
     * @mbggenerated Thu Jul 14 19:06:14 ICT 2016
     */
    int updateByPrimaryKeySelective(CampaignContact record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaigns_contacts
     *
     * @mbggenerated Thu Jul 14 19:06:14 ICT 2016
     */
    int updateByPrimaryKey(CampaignContact record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaigns_contacts
     *
     * @mbggenerated Thu Jul 14 19:06:14 ICT 2016
     */
    Integer insertAndReturnKey(CampaignContact value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaigns_contacts
     *
     * @mbggenerated Thu Jul 14 19:06:14 ICT 2016
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaigns_contacts
     *
     * @mbggenerated Thu Jul 14 19:06:14 ICT 2016
     */
    void massUpdateWithSession(@Param("record") CampaignContact record, @Param("primaryKeys") List primaryKeys);
}