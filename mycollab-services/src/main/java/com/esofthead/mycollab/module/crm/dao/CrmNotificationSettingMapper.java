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
     * @mbggenerated Wed Jul 01 11:51:33 ICT 2015
     */
    int countByExample(CrmNotificationSettingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_notifications
     *
     * @mbggenerated Wed Jul 01 11:51:33 ICT 2015
     */
    int deleteByExample(CrmNotificationSettingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_notifications
     *
     * @mbggenerated Wed Jul 01 11:51:33 ICT 2015
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_notifications
     *
     * @mbggenerated Wed Jul 01 11:51:33 ICT 2015
     */
    int insert(CrmNotificationSetting record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_notifications
     *
     * @mbggenerated Wed Jul 01 11:51:33 ICT 2015
     */
    int insertSelective(CrmNotificationSetting record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_notifications
     *
     * @mbggenerated Wed Jul 01 11:51:33 ICT 2015
     */
    List<CrmNotificationSetting> selectByExample(CrmNotificationSettingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_notifications
     *
     * @mbggenerated Wed Jul 01 11:51:33 ICT 2015
     */
    CrmNotificationSetting selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_notifications
     *
     * @mbggenerated Wed Jul 01 11:51:33 ICT 2015
     */
    int updateByExampleSelective(@Param("record") CrmNotificationSetting record, @Param("example") CrmNotificationSettingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_notifications
     *
     * @mbggenerated Wed Jul 01 11:51:33 ICT 2015
     */
    int updateByExample(@Param("record") CrmNotificationSetting record, @Param("example") CrmNotificationSettingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_notifications
     *
     * @mbggenerated Wed Jul 01 11:51:33 ICT 2015
     */
    int updateByPrimaryKeySelective(CrmNotificationSetting record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_notifications
     *
     * @mbggenerated Wed Jul 01 11:51:33 ICT 2015
     */
    int updateByPrimaryKey(CrmNotificationSetting record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_notifications
     *
     * @mbggenerated Wed Jul 01 11:51:33 ICT 2015
     */
    Integer insertAndReturnKey(CrmNotificationSetting value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_notifications
     *
     * @mbggenerated Wed Jul 01 11:51:33 ICT 2015
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_notifications
     *
     * @mbggenerated Wed Jul 01 11:51:33 ICT 2015
     */
    void massUpdateWithSession(@Param("record") CrmNotificationSetting record, @Param("primaryKeys") List primaryKeys);
}