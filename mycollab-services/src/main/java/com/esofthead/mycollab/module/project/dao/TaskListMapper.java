package com.esofthead.mycollab.module.project.dao;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.module.project.domain.TaskList;
import com.esofthead.mycollab.module.project.domain.TaskListExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@SuppressWarnings({ "ucd", "rawtypes" })
public interface TaskListMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_task_list
     *
     * @mbggenerated Fri Feb 27 09:55:30 ICT 2015
     */
    int countByExample(TaskListExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_task_list
     *
     * @mbggenerated Fri Feb 27 09:55:30 ICT 2015
     */
    int deleteByExample(TaskListExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_task_list
     *
     * @mbggenerated Fri Feb 27 09:55:30 ICT 2015
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_task_list
     *
     * @mbggenerated Fri Feb 27 09:55:30 ICT 2015
     */
    int insert(TaskList record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_task_list
     *
     * @mbggenerated Fri Feb 27 09:55:30 ICT 2015
     */
    int insertSelective(TaskList record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_task_list
     *
     * @mbggenerated Fri Feb 27 09:55:30 ICT 2015
     */
    List<TaskList> selectByExampleWithBLOBs(TaskListExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_task_list
     *
     * @mbggenerated Fri Feb 27 09:55:30 ICT 2015
     */
    List<TaskList> selectByExample(TaskListExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_task_list
     *
     * @mbggenerated Fri Feb 27 09:55:30 ICT 2015
     */
    TaskList selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_task_list
     *
     * @mbggenerated Fri Feb 27 09:55:30 ICT 2015
     */
    int updateByExampleSelective(@Param("record") TaskList record, @Param("example") TaskListExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_task_list
     *
     * @mbggenerated Fri Feb 27 09:55:30 ICT 2015
     */
    int updateByExampleWithBLOBs(@Param("record") TaskList record, @Param("example") TaskListExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_task_list
     *
     * @mbggenerated Fri Feb 27 09:55:30 ICT 2015
     */
    int updateByExample(@Param("record") TaskList record, @Param("example") TaskListExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_task_list
     *
     * @mbggenerated Fri Feb 27 09:55:30 ICT 2015
     */
    int updateByPrimaryKeySelective(TaskList record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_task_list
     *
     * @mbggenerated Fri Feb 27 09:55:30 ICT 2015
     */
    int updateByPrimaryKeyWithBLOBs(TaskList record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_task_list
     *
     * @mbggenerated Fri Feb 27 09:55:30 ICT 2015
     */
    int updateByPrimaryKey(TaskList record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_task_list
     *
     * @mbggenerated Fri Feb 27 09:55:30 ICT 2015
     */
    Integer insertAndReturnKey(TaskList value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_task_list
     *
     * @mbggenerated Fri Feb 27 09:55:30 ICT 2015
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_task_list
     *
     * @mbggenerated Fri Feb 27 09:55:30 ICT 2015
     */
    void massUpdateWithSession(@Param("record") TaskList record, @Param("primaryKeys") List primaryKeys);
}