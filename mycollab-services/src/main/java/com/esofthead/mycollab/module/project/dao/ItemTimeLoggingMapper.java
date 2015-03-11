package com.esofthead.mycollab.module.project.dao;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.module.project.domain.ItemTimeLogging;
import com.esofthead.mycollab.module.project.domain.ItemTimeLoggingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@SuppressWarnings({ "ucd", "rawtypes" })
public interface ItemTimeLoggingMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_time_logging
     *
     * @mbggenerated Wed Mar 11 09:10:41 ICT 2015
     */
    int countByExample(ItemTimeLoggingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_time_logging
     *
     * @mbggenerated Wed Mar 11 09:10:41 ICT 2015
     */
    int deleteByExample(ItemTimeLoggingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_time_logging
     *
     * @mbggenerated Wed Mar 11 09:10:41 ICT 2015
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_time_logging
     *
     * @mbggenerated Wed Mar 11 09:10:41 ICT 2015
     */
    int insert(ItemTimeLogging record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_time_logging
     *
     * @mbggenerated Wed Mar 11 09:10:41 ICT 2015
     */
    int insertSelective(ItemTimeLogging record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_time_logging
     *
     * @mbggenerated Wed Mar 11 09:10:41 ICT 2015
     */
    List<ItemTimeLogging> selectByExampleWithBLOBs(ItemTimeLoggingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_time_logging
     *
     * @mbggenerated Wed Mar 11 09:10:41 ICT 2015
     */
    List<ItemTimeLogging> selectByExample(ItemTimeLoggingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_time_logging
     *
     * @mbggenerated Wed Mar 11 09:10:41 ICT 2015
     */
    ItemTimeLogging selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_time_logging
     *
     * @mbggenerated Wed Mar 11 09:10:41 ICT 2015
     */
    int updateByExampleSelective(@Param("record") ItemTimeLogging record, @Param("example") ItemTimeLoggingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_time_logging
     *
     * @mbggenerated Wed Mar 11 09:10:41 ICT 2015
     */
    int updateByExampleWithBLOBs(@Param("record") ItemTimeLogging record, @Param("example") ItemTimeLoggingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_time_logging
     *
     * @mbggenerated Wed Mar 11 09:10:41 ICT 2015
     */
    int updateByExample(@Param("record") ItemTimeLogging record, @Param("example") ItemTimeLoggingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_time_logging
     *
     * @mbggenerated Wed Mar 11 09:10:41 ICT 2015
     */
    int updateByPrimaryKeySelective(ItemTimeLogging record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_time_logging
     *
     * @mbggenerated Wed Mar 11 09:10:41 ICT 2015
     */
    int updateByPrimaryKeyWithBLOBs(ItemTimeLogging record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_time_logging
     *
     * @mbggenerated Wed Mar 11 09:10:41 ICT 2015
     */
    int updateByPrimaryKey(ItemTimeLogging record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_time_logging
     *
     * @mbggenerated Wed Mar 11 09:10:41 ICT 2015
     */
    Integer insertAndReturnKey(ItemTimeLogging value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_time_logging
     *
     * @mbggenerated Wed Mar 11 09:10:41 ICT 2015
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_time_logging
     *
     * @mbggenerated Wed Mar 11 09:10:41 ICT 2015
     */
    void massUpdateWithSession(@Param("record") ItemTimeLogging record, @Param("primaryKeys") List primaryKeys);
}