package com.esofthead.mycollab.module.crm.dao;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.module.crm.domain.Task;
import com.esofthead.mycollab.module.crm.domain.TaskExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TaskMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_task
     *
     * @mbggenerated Thu Nov 29 15:59:46 GMT+07:00 2012
     */
    int countByExample(TaskExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_task
     *
     * @mbggenerated Thu Nov 29 15:59:46 GMT+07:00 2012
     */
    int deleteByExample(TaskExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_task
     *
     * @mbggenerated Thu Nov 29 15:59:46 GMT+07:00 2012
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_task
     *
     * @mbggenerated Thu Nov 29 15:59:46 GMT+07:00 2012
     */
    int insert(Task record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_task
     *
     * @mbggenerated Thu Nov 29 15:59:46 GMT+07:00 2012
     */
    int insertSelective(Task record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_task
     *
     * @mbggenerated Thu Nov 29 15:59:46 GMT+07:00 2012
     */
    List<Task> selectByExample(TaskExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_task
     *
     * @mbggenerated Thu Nov 29 15:59:46 GMT+07:00 2012
     */
    Task selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_task
     *
     * @mbggenerated Thu Nov 29 15:59:46 GMT+07:00 2012
     */
    int updateByExampleSelective(@Param("record") Task record, @Param("example") TaskExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_task
     *
     * @mbggenerated Thu Nov 29 15:59:46 GMT+07:00 2012
     */
    int updateByExample(@Param("record") Task record, @Param("example") TaskExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_task
     *
     * @mbggenerated Thu Nov 29 15:59:46 GMT+07:00 2012
     */
    int updateByPrimaryKeySelective(Task record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_task
     *
     * @mbggenerated Thu Nov 29 15:59:46 GMT+07:00 2012
     */
    int updateByPrimaryKey(Task record);
}