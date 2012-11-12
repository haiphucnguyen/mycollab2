package com.esofthead.mycollab.module.project.dao;

import com.esofthead.mycollab.core.persistence.CrudGenericDAO;
import com.esofthead.mycollab.module.project.domain.TaskList;
import com.esofthead.mycollab.module.project.domain.TaskListExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TaskListMapper extends CrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_task_list
     *
     * @mbggenerated Mon Nov 12 16:09:06 GMT+07:00 2012
     */
    int countByExample(TaskListExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_task_list
     *
     * @mbggenerated Mon Nov 12 16:09:06 GMT+07:00 2012
     */
    int deleteByExample(TaskListExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_task_list
     *
     * @mbggenerated Mon Nov 12 16:09:06 GMT+07:00 2012
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_task_list
     *
     * @mbggenerated Mon Nov 12 16:09:06 GMT+07:00 2012
     */
    int insert(TaskList record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_task_list
     *
     * @mbggenerated Mon Nov 12 16:09:06 GMT+07:00 2012
     */
    int insertSelective(TaskList record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_task_list
     *
     * @mbggenerated Mon Nov 12 16:09:06 GMT+07:00 2012
     */
    List<TaskList> selectByExample(TaskListExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_task_list
     *
     * @mbggenerated Mon Nov 12 16:09:06 GMT+07:00 2012
     */
    TaskList selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_task_list
     *
     * @mbggenerated Mon Nov 12 16:09:06 GMT+07:00 2012
     */
    int updateByExampleSelective(@Param("record") TaskList record, @Param("example") TaskListExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_task_list
     *
     * @mbggenerated Mon Nov 12 16:09:06 GMT+07:00 2012
     */
    int updateByExample(@Param("record") TaskList record, @Param("example") TaskListExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_task_list
     *
     * @mbggenerated Mon Nov 12 16:09:06 GMT+07:00 2012
     */
    int updateByPrimaryKeySelective(TaskList record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_task_list
     *
     * @mbggenerated Mon Nov 12 16:09:06 GMT+07:00 2012
     */
    int updateByPrimaryKey(TaskList record);
}