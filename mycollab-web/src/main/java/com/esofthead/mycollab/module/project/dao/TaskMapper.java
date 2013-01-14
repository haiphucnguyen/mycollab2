package com.esofthead.mycollab.module.project.dao;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.module.project.domain.Task;
import com.esofthead.mycollab.module.project.domain.TaskExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TaskMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_task
     *
     * @mbggenerated Mon Jan 14 15:29:26 GMT+07:00 2013
     */
    int countByExample(TaskExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_task
     *
     * @mbggenerated Mon Jan 14 15:29:26 GMT+07:00 2013
     */
    int deleteByExample(TaskExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_task
     *
     * @mbggenerated Mon Jan 14 15:29:26 GMT+07:00 2013
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_task
     *
     * @mbggenerated Mon Jan 14 15:29:26 GMT+07:00 2013
     */
    int insert(Task record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_task
     *
     * @mbggenerated Mon Jan 14 15:29:26 GMT+07:00 2013
     */
    int insertSelective(Task record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_task
     *
     * @mbggenerated Mon Jan 14 15:29:26 GMT+07:00 2013
     */
    List<Task> selectByExample(TaskExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_task
     *
     * @mbggenerated Mon Jan 14 15:29:26 GMT+07:00 2013
     */
    Task selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_task
     *
     * @mbggenerated Mon Jan 14 15:29:26 GMT+07:00 2013
     */
    int updateByExampleSelective(@Param("record") Task record, @Param("example") TaskExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_task
     *
     * @mbggenerated Mon Jan 14 15:29:26 GMT+07:00 2013
     */
    int updateByExample(@Param("record") Task record, @Param("example") TaskExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_task
     *
     * @mbggenerated Mon Jan 14 15:29:26 GMT+07:00 2013
     */
    int updateByPrimaryKeySelective(Task record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_task
     *
     * @mbggenerated Mon Jan 14 15:29:26 GMT+07:00 2013
     */
    int updateByPrimaryKey(Task record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_task
     *
     * @mbggenerated Mon Jan 14 15:29:26 GMT+07:00 2013
     */
    Integer insertAndReturnKey(Task value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_task
     *
     * @mbggenerated Mon Jan 14 15:29:26 GMT+07:00 2013
     */
    void removeKeysWithSession(List primaryKeys);
}