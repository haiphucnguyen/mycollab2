package com.mycollab.common.dao;

import com.mycollab.common.domain.RelayEmailNotificationExample;
import com.mycollab.common.domain.RelayEmailNotificationWithBLOBs;
import com.mycollab.db.persistence.ICrudGenericDAO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@SuppressWarnings({ "ucd", "rawtypes" })
public interface RelayEmailNotificationMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_relay_email_notification
     *
     * @mbg.generated Thu Oct 19 13:58:11 ICT 2017
     */
    long countByExample(RelayEmailNotificationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_relay_email_notification
     *
     * @mbg.generated Thu Oct 19 13:58:11 ICT 2017
     */
    int deleteByExample(RelayEmailNotificationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_relay_email_notification
     *
     * @mbg.generated Thu Oct 19 13:58:11 ICT 2017
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_relay_email_notification
     *
     * @mbg.generated Thu Oct 19 13:58:11 ICT 2017
     */
    int insert(RelayEmailNotificationWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_relay_email_notification
     *
     * @mbg.generated Thu Oct 19 13:58:11 ICT 2017
     */
    int insertSelective(RelayEmailNotificationWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_relay_email_notification
     *
     * @mbg.generated Thu Oct 19 13:58:11 ICT 2017
     */
    List<RelayEmailNotificationWithBLOBs> selectByExampleWithBLOBs(RelayEmailNotificationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_relay_email_notification
     *
     * @mbg.generated Thu Oct 19 13:58:11 ICT 2017
     */
    RelayEmailNotificationWithBLOBs selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_relay_email_notification
     *
     * @mbg.generated Thu Oct 19 13:58:11 ICT 2017
     */
    int updateByExampleSelective(@Param("record") RelayEmailNotificationWithBLOBs record, @Param("example") RelayEmailNotificationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_relay_email_notification
     *
     * @mbg.generated Thu Oct 19 13:58:11 ICT 2017
     */
    int updateByExampleWithBLOBs(@Param("record") RelayEmailNotificationWithBLOBs record, @Param("example") RelayEmailNotificationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_relay_email_notification
     *
     * @mbg.generated Thu Oct 19 13:58:11 ICT 2017
     */
    int updateByPrimaryKeySelective(RelayEmailNotificationWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_relay_email_notification
     *
     * @mbg.generated Thu Oct 19 13:58:11 ICT 2017
     */
    int updateByPrimaryKeyWithBLOBs(RelayEmailNotificationWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_relay_email_notification
     *
     * @mbg.generated Thu Oct 19 13:58:11 ICT 2017
     */
    Integer insertAndReturnKey(RelayEmailNotificationWithBLOBs value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_relay_email_notification
     *
     * @mbg.generated Thu Oct 19 13:58:11 ICT 2017
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_relay_email_notification
     *
     * @mbg.generated Thu Oct 19 13:58:11 ICT 2017
     */
    void massUpdateWithSession(@Param("record") RelayEmailNotificationWithBLOBs record, @Param("primaryKeys") List primaryKeys);
}