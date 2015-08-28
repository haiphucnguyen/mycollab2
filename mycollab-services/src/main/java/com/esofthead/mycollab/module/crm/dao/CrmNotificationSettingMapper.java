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
import com.esofthead.mycollab.module.crm.domain.CrmNotificationSetting;
import com.esofthead.mycollab.module.crm.domain.CrmNotificationSettingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@SuppressWarnings({ "ucd", "rawtypes" })
public interface CrmNotificationSettingMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_notifications
     *
     * @mbggenerated Fri Aug 28 22:04:31 ICT 2015
     */
    int countByExample(CrmNotificationSettingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_notifications
     *
     * @mbggenerated Fri Aug 28 22:04:31 ICT 2015
     */
    int deleteByExample(CrmNotificationSettingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_notifications
     *
     * @mbggenerated Fri Aug 28 22:04:31 ICT 2015
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_notifications
     *
     * @mbggenerated Fri Aug 28 22:04:31 ICT 2015
     */
    int insert(CrmNotificationSetting record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_notifications
     *
     * @mbggenerated Fri Aug 28 22:04:31 ICT 2015
     */
    int insertSelective(CrmNotificationSetting record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_notifications
     *
     * @mbggenerated Fri Aug 28 22:04:31 ICT 2015
     */
    List<CrmNotificationSetting> selectByExample(CrmNotificationSettingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_notifications
     *
     * @mbggenerated Fri Aug 28 22:04:31 ICT 2015
     */
    CrmNotificationSetting selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_notifications
     *
     * @mbggenerated Fri Aug 28 22:04:31 ICT 2015
     */
    int updateByExampleSelective(@Param("record") CrmNotificationSetting record, @Param("example") CrmNotificationSettingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_notifications
     *
     * @mbggenerated Fri Aug 28 22:04:31 ICT 2015
     */
    int updateByExample(@Param("record") CrmNotificationSetting record, @Param("example") CrmNotificationSettingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_notifications
     *
     * @mbggenerated Fri Aug 28 22:04:31 ICT 2015
     */
    int updateByPrimaryKeySelective(CrmNotificationSetting record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_notifications
     *
     * @mbggenerated Fri Aug 28 22:04:31 ICT 2015
     */
    int updateByPrimaryKey(CrmNotificationSetting record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_notifications
     *
     * @mbggenerated Fri Aug 28 22:04:31 ICT 2015
     */
    Integer insertAndReturnKey(CrmNotificationSetting value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_notifications
     *
     * @mbggenerated Fri Aug 28 22:04:31 ICT 2015
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_notifications
     *
     * @mbggenerated Fri Aug 28 22:04:31 ICT 2015
     */
    void massUpdateWithSession(@Param("record") CrmNotificationSetting record, @Param("primaryKeys") List primaryKeys);
}