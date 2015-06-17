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
import com.esofthead.mycollab.module.crm.domain.CampaignAccount;
import com.esofthead.mycollab.module.crm.domain.CampaignAccountExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@SuppressWarnings({ "ucd", "rawtypes" })
public interface CampaignAccountMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaigns_accounts
     *
     * @mbggenerated Wed Jun 17 14:56:55 ICT 2015
     */
    int countByExample(CampaignAccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaigns_accounts
     *
     * @mbggenerated Wed Jun 17 14:56:55 ICT 2015
     */
    int deleteByExample(CampaignAccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaigns_accounts
     *
     * @mbggenerated Wed Jun 17 14:56:55 ICT 2015
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaigns_accounts
     *
     * @mbggenerated Wed Jun 17 14:56:55 ICT 2015
     */
    int insert(CampaignAccount record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaigns_accounts
     *
     * @mbggenerated Wed Jun 17 14:56:55 ICT 2015
     */
    int insertSelective(CampaignAccount record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaigns_accounts
     *
     * @mbggenerated Wed Jun 17 14:56:55 ICT 2015
     */
    List<CampaignAccount> selectByExample(CampaignAccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaigns_accounts
     *
     * @mbggenerated Wed Jun 17 14:56:55 ICT 2015
     */
    CampaignAccount selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaigns_accounts
     *
     * @mbggenerated Wed Jun 17 14:56:55 ICT 2015
     */
    int updateByExampleSelective(@Param("record") CampaignAccount record, @Param("example") CampaignAccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaigns_accounts
     *
     * @mbggenerated Wed Jun 17 14:56:55 ICT 2015
     */
    int updateByExample(@Param("record") CampaignAccount record, @Param("example") CampaignAccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaigns_accounts
     *
     * @mbggenerated Wed Jun 17 14:56:55 ICT 2015
     */
    int updateByPrimaryKeySelective(CampaignAccount record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaigns_accounts
     *
     * @mbggenerated Wed Jun 17 14:56:55 ICT 2015
     */
    int updateByPrimaryKey(CampaignAccount record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaigns_accounts
     *
     * @mbggenerated Wed Jun 17 14:56:55 ICT 2015
     */
    Integer insertAndReturnKey(CampaignAccount value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaigns_accounts
     *
     * @mbggenerated Wed Jun 17 14:56:55 ICT 2015
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_campaigns_accounts
     *
     * @mbggenerated Wed Jun 17 14:56:55 ICT 2015
     */
    void massUpdateWithSession(@Param("record") CampaignAccount record, @Param("primaryKeys") List primaryKeys);
}