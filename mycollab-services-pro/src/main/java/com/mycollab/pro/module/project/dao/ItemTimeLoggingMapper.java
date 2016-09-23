package com.mycollab.pro.module.project.dao;

import com.mycollab.db.persistence.ICrudGenericDAO;
import com.mycollab.module.project.domain.ItemTimeLogging;
import com.mycollab.module.project.domain.ItemTimeLoggingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@SuppressWarnings({ "ucd", "rawtypes" })
public interface ItemTimeLoggingMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_time_logging
     *
     * @mbg.generated Fri Sep 23 03:45:54 ICT 2016
     */
    long countByExample(ItemTimeLoggingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_time_logging
     *
     * @mbg.generated Fri Sep 23 03:45:54 ICT 2016
     */
    int deleteByExample(ItemTimeLoggingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_time_logging
     *
     * @mbg.generated Fri Sep 23 03:45:54 ICT 2016
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_time_logging
     *
     * @mbg.generated Fri Sep 23 03:45:54 ICT 2016
     */
    int insert(ItemTimeLogging record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_time_logging
     *
     * @mbg.generated Fri Sep 23 03:45:54 ICT 2016
     */
    int insertSelective(ItemTimeLogging record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_time_logging
     *
     * @mbg.generated Fri Sep 23 03:45:54 ICT 2016
     */
    List<ItemTimeLogging> selectByExampleWithBLOBs(ItemTimeLoggingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_time_logging
     *
     * @mbg.generated Fri Sep 23 03:45:54 ICT 2016
     */
    List<ItemTimeLogging> selectByExample(ItemTimeLoggingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_time_logging
     *
     * @mbg.generated Fri Sep 23 03:45:54 ICT 2016
     */
    ItemTimeLogging selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_time_logging
     *
     * @mbg.generated Fri Sep 23 03:45:54 ICT 2016
     */
    int updateByExampleSelective(@Param("record") ItemTimeLogging record, @Param("example") ItemTimeLoggingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_time_logging
     *
     * @mbg.generated Fri Sep 23 03:45:54 ICT 2016
     */
    int updateByExampleWithBLOBs(@Param("record") ItemTimeLogging record, @Param("example") ItemTimeLoggingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_time_logging
     *
     * @mbg.generated Fri Sep 23 03:45:54 ICT 2016
     */
    int updateByExample(@Param("record") ItemTimeLogging record, @Param("example") ItemTimeLoggingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_time_logging
     *
     * @mbg.generated Fri Sep 23 03:45:54 ICT 2016
     */
    int updateByPrimaryKeySelective(ItemTimeLogging record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_time_logging
     *
     * @mbg.generated Fri Sep 23 03:45:54 ICT 2016
     */
    int updateByPrimaryKeyWithBLOBs(ItemTimeLogging record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_time_logging
     *
     * @mbg.generated Fri Sep 23 03:45:54 ICT 2016
     */
    int updateByPrimaryKey(ItemTimeLogging record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_time_logging
     *
     * @mbg.generated Fri Sep 23 03:45:54 ICT 2016
     */
    Integer insertAndReturnKey(ItemTimeLogging value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_time_logging
     *
     * @mbg.generated Fri Sep 23 03:45:54 ICT 2016
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_time_logging
     *
     * @mbg.generated Fri Sep 23 03:45:54 ICT 2016
     */
    void massUpdateWithSession(@Param("record") ItemTimeLogging record, @Param("primaryKeys") List primaryKeys);
}