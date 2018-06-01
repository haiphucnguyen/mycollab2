package com.mycollab.common.dao;

import com.mycollab.common.domain.NotificationItem;
import com.mycollab.common.domain.NotificationItemExample;
import com.mycollab.db.persistence.ICrudGenericDAO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@SuppressWarnings({ "ucd", "rawtypes" })
public interface NotificationItemMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_notification_item
     *
     * @mbg.generated Fri Jun 01 14:16:25 ICT 2018
     */
    long countByExample(NotificationItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_notification_item
     *
     * @mbg.generated Fri Jun 01 14:16:25 ICT 2018
     */
    int deleteByExample(NotificationItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_notification_item
     *
     * @mbg.generated Fri Jun 01 14:16:25 ICT 2018
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_notification_item
     *
     * @mbg.generated Fri Jun 01 14:16:25 ICT 2018
     */
    int insert(NotificationItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_notification_item
     *
     * @mbg.generated Fri Jun 01 14:16:25 ICT 2018
     */
    int insertSelective(NotificationItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_notification_item
     *
     * @mbg.generated Fri Jun 01 14:16:25 ICT 2018
     */
    List<NotificationItem> selectByExample(NotificationItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_notification_item
     *
     * @mbg.generated Fri Jun 01 14:16:25 ICT 2018
     */
    NotificationItem selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_notification_item
     *
     * @mbg.generated Fri Jun 01 14:16:25 ICT 2018
     */
    int updateByExampleSelective(@Param("record") NotificationItem record, @Param("example") NotificationItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_notification_item
     *
     * @mbg.generated Fri Jun 01 14:16:25 ICT 2018
     */
    int updateByExample(@Param("record") NotificationItem record, @Param("example") NotificationItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_notification_item
     *
     * @mbg.generated Fri Jun 01 14:16:25 ICT 2018
     */
    int updateByPrimaryKeySelective(NotificationItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_notification_item
     *
     * @mbg.generated Fri Jun 01 14:16:25 ICT 2018
     */
    int updateByPrimaryKey(NotificationItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_notification_item
     *
     * @mbg.generated Fri Jun 01 14:16:25 ICT 2018
     */
    Integer insertAndReturnKey(NotificationItem value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_notification_item
     *
     * @mbg.generated Fri Jun 01 14:16:25 ICT 2018
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_notification_item
     *
     * @mbg.generated Fri Jun 01 14:16:25 ICT 2018
     */
    void massUpdateWithSession(@Param("record") NotificationItem record, @Param("primaryKeys") List primaryKeys);
}