package com.mycollab.module.project.dao;

import com.mycollab.db.persistence.ICrudGenericDAO;
import com.mycollab.module.project.domain.ProjectNotificationSetting;
import com.mycollab.module.project.domain.ProjectNotificationSettingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@SuppressWarnings({ "ucd", "rawtypes" })
public interface ProjectNotificationSettingMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_notifications
     *
     * @mbg.generated Fri Sep 23 11:52:34 ICT 2016
     */
    long countByExample(ProjectNotificationSettingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_notifications
     *
     * @mbg.generated Fri Sep 23 11:52:34 ICT 2016
     */
    int deleteByExample(ProjectNotificationSettingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_notifications
     *
     * @mbg.generated Fri Sep 23 11:52:34 ICT 2016
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_notifications
     *
     * @mbg.generated Fri Sep 23 11:52:34 ICT 2016
     */
    int insert(ProjectNotificationSetting record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_notifications
     *
     * @mbg.generated Fri Sep 23 11:52:34 ICT 2016
     */
    int insertSelective(ProjectNotificationSetting record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_notifications
     *
     * @mbg.generated Fri Sep 23 11:52:34 ICT 2016
     */
    List<ProjectNotificationSetting> selectByExample(ProjectNotificationSettingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_notifications
     *
     * @mbg.generated Fri Sep 23 11:52:34 ICT 2016
     */
    ProjectNotificationSetting selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_notifications
     *
     * @mbg.generated Fri Sep 23 11:52:34 ICT 2016
     */
    int updateByExampleSelective(@Param("record") ProjectNotificationSetting record, @Param("example") ProjectNotificationSettingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_notifications
     *
     * @mbg.generated Fri Sep 23 11:52:34 ICT 2016
     */
    int updateByExample(@Param("record") ProjectNotificationSetting record, @Param("example") ProjectNotificationSettingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_notifications
     *
     * @mbg.generated Fri Sep 23 11:52:34 ICT 2016
     */
    int updateByPrimaryKeySelective(ProjectNotificationSetting record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_notifications
     *
     * @mbg.generated Fri Sep 23 11:52:34 ICT 2016
     */
    int updateByPrimaryKey(ProjectNotificationSetting record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_notifications
     *
     * @mbg.generated Fri Sep 23 11:52:34 ICT 2016
     */
    Integer insertAndReturnKey(ProjectNotificationSetting value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_notifications
     *
     * @mbg.generated Fri Sep 23 11:52:34 ICT 2016
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_notifications
     *
     * @mbg.generated Fri Sep 23 11:52:34 ICT 2016
     */
    void massUpdateWithSession(@Param("record") ProjectNotificationSetting record, @Param("primaryKeys") List primaryKeys);
}