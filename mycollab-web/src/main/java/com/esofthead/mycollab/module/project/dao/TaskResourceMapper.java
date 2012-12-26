package com.esofthead.mycollab.module.project.dao;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.module.project.domain.TaskResource;
import com.esofthead.mycollab.module.project.domain.TaskResourceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TaskResourceMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_task_resource
     *
     * @mbggenerated Wed Dec 26 18:11:48 GMT+07:00 2012
     */
    int countByExample(TaskResourceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_task_resource
     *
     * @mbggenerated Wed Dec 26 18:11:48 GMT+07:00 2012
     */
    int deleteByExample(TaskResourceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_task_resource
     *
     * @mbggenerated Wed Dec 26 18:11:48 GMT+07:00 2012
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_task_resource
     *
     * @mbggenerated Wed Dec 26 18:11:48 GMT+07:00 2012
     */
    int insert(TaskResource record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_task_resource
     *
     * @mbggenerated Wed Dec 26 18:11:48 GMT+07:00 2012
     */
    int insertSelective(TaskResource record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_task_resource
     *
     * @mbggenerated Wed Dec 26 18:11:48 GMT+07:00 2012
     */
    List<TaskResource> selectByExample(TaskResourceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_task_resource
     *
     * @mbggenerated Wed Dec 26 18:11:48 GMT+07:00 2012
     */
    TaskResource selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_task_resource
     *
     * @mbggenerated Wed Dec 26 18:11:48 GMT+07:00 2012
     */
    int updateByExampleSelective(@Param("record") TaskResource record, @Param("example") TaskResourceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_task_resource
     *
     * @mbggenerated Wed Dec 26 18:11:48 GMT+07:00 2012
     */
    int updateByExample(@Param("record") TaskResource record, @Param("example") TaskResourceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_task_resource
     *
     * @mbggenerated Wed Dec 26 18:11:48 GMT+07:00 2012
     */
    int updateByPrimaryKeySelective(TaskResource record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_task_resource
     *
     * @mbggenerated Wed Dec 26 18:11:48 GMT+07:00 2012
     */
    int updateByPrimaryKey(TaskResource record);

    Integer insertAndReturnKey(TaskResource value);
}