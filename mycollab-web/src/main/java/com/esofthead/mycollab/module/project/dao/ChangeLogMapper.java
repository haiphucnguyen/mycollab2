package com.esofthead.mycollab.module.project.dao;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.module.project.domain.ChangeLog;
import com.esofthead.mycollab.module.project.domain.ChangeLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ChangeLogMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_change_log
     *
     * @mbggenerated Mon Nov 12 16:09:05 GMT+07:00 2012
     */
    int countByExample(ChangeLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_change_log
     *
     * @mbggenerated Mon Nov 12 16:09:05 GMT+07:00 2012
     */
    int deleteByExample(ChangeLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_change_log
     *
     * @mbggenerated Mon Nov 12 16:09:05 GMT+07:00 2012
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_change_log
     *
     * @mbggenerated Mon Nov 12 16:09:05 GMT+07:00 2012
     */
    int insert(ChangeLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_change_log
     *
     * @mbggenerated Mon Nov 12 16:09:05 GMT+07:00 2012
     */
    int insertSelective(ChangeLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_change_log
     *
     * @mbggenerated Mon Nov 12 16:09:05 GMT+07:00 2012
     */
    List<ChangeLog> selectByExample(ChangeLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_change_log
     *
     * @mbggenerated Mon Nov 12 16:09:05 GMT+07:00 2012
     */
    ChangeLog selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_change_log
     *
     * @mbggenerated Mon Nov 12 16:09:05 GMT+07:00 2012
     */
    int updateByExampleSelective(@Param("record") ChangeLog record, @Param("example") ChangeLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_change_log
     *
     * @mbggenerated Mon Nov 12 16:09:05 GMT+07:00 2012
     */
    int updateByExample(@Param("record") ChangeLog record, @Param("example") ChangeLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_change_log
     *
     * @mbggenerated Mon Nov 12 16:09:05 GMT+07:00 2012
     */
    int updateByPrimaryKeySelective(ChangeLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_change_log
     *
     * @mbggenerated Mon Nov 12 16:09:05 GMT+07:00 2012
     */
    int updateByPrimaryKey(ChangeLog record);
}