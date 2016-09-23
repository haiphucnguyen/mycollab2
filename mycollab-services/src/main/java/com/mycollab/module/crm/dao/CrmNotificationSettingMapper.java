package com.mycollab.module.crm.dao;

import com.mycollab.db.persistence.ICrudGenericDAO;
import com.mycollab.module.crm.domain.CrmNotificationSetting;
import com.mycollab.module.crm.domain.CrmNotificationSettingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@SuppressWarnings({ "ucd", "rawtypes" })
public interface CrmNotificationSettingMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_notifications
     *
     * @mbg.generated Fri Sep 23 03:45:52 ICT 2016
     */
    long countByExample(CrmNotificationSettingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_notifications
     *
     * @mbg.generated Fri Sep 23 03:45:52 ICT 2016
     */
    int deleteByExample(CrmNotificationSettingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_notifications
     *
     * @mbg.generated Fri Sep 23 03:45:52 ICT 2016
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_notifications
     *
     * @mbg.generated Fri Sep 23 03:45:52 ICT 2016
     */
    int insert(CrmNotificationSetting record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_notifications
     *
     * @mbg.generated Fri Sep 23 03:45:52 ICT 2016
     */
    int insertSelective(CrmNotificationSetting record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_notifications
     *
     * @mbg.generated Fri Sep 23 03:45:52 ICT 2016
     */
    List<CrmNotificationSetting> selectByExample(CrmNotificationSettingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_notifications
     *
     * @mbg.generated Fri Sep 23 03:45:52 ICT 2016
     */
    CrmNotificationSetting selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_notifications
     *
     * @mbg.generated Fri Sep 23 03:45:52 ICT 2016
     */
    int updateByExampleSelective(@Param("record") CrmNotificationSetting record, @Param("example") CrmNotificationSettingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_notifications
     *
     * @mbg.generated Fri Sep 23 03:45:52 ICT 2016
     */
    int updateByExample(@Param("record") CrmNotificationSetting record, @Param("example") CrmNotificationSettingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_notifications
     *
     * @mbg.generated Fri Sep 23 03:45:52 ICT 2016
     */
    int updateByPrimaryKeySelective(CrmNotificationSetting record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_notifications
     *
     * @mbg.generated Fri Sep 23 03:45:52 ICT 2016
     */
    int updateByPrimaryKey(CrmNotificationSetting record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_notifications
     *
     * @mbg.generated Fri Sep 23 03:45:52 ICT 2016
     */
    Integer insertAndReturnKey(CrmNotificationSetting value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_notifications
     *
     * @mbg.generated Fri Sep 23 03:45:52 ICT 2016
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_notifications
     *
     * @mbg.generated Fri Sep 23 03:45:52 ICT 2016
     */
    void massUpdateWithSession(@Param("record") CrmNotificationSetting record, @Param("primaryKeys") List primaryKeys);
}