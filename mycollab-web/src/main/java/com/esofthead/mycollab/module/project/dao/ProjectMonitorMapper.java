package com.esofthead.mycollab.module.project.dao;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.module.project.domain.ProjectMonitor;
import com.esofthead.mycollab.module.project.domain.ProjectMonitorExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProjectMonitorMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_monitor
     *
     * @mbggenerated Fri Dec 28 00:40:18 GMT+07:00 2012
     */
    int countByExample(ProjectMonitorExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_monitor
     *
     * @mbggenerated Fri Dec 28 00:40:18 GMT+07:00 2012
     */
    int deleteByExample(ProjectMonitorExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_monitor
     *
     * @mbggenerated Fri Dec 28 00:40:18 GMT+07:00 2012
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_monitor
     *
     * @mbggenerated Fri Dec 28 00:40:18 GMT+07:00 2012
     */
    int insert(ProjectMonitor record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_monitor
     *
     * @mbggenerated Fri Dec 28 00:40:18 GMT+07:00 2012
     */
    int insertSelective(ProjectMonitor record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_monitor
     *
     * @mbggenerated Fri Dec 28 00:40:18 GMT+07:00 2012
     */
    List<ProjectMonitor> selectByExample(ProjectMonitorExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_monitor
     *
     * @mbggenerated Fri Dec 28 00:40:18 GMT+07:00 2012
     */
    ProjectMonitor selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_monitor
     *
     * @mbggenerated Fri Dec 28 00:40:18 GMT+07:00 2012
     */
    int updateByExampleSelective(@Param("record") ProjectMonitor record, @Param("example") ProjectMonitorExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_monitor
     *
     * @mbggenerated Fri Dec 28 00:40:18 GMT+07:00 2012
     */
    int updateByExample(@Param("record") ProjectMonitor record, @Param("example") ProjectMonitorExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_monitor
     *
     * @mbggenerated Fri Dec 28 00:40:18 GMT+07:00 2012
     */
    int updateByPrimaryKeySelective(ProjectMonitor record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_monitor
     *
     * @mbggenerated Fri Dec 28 00:40:18 GMT+07:00 2012
     */
    int updateByPrimaryKey(ProjectMonitor record);

    Integer insertAndReturnKey(ProjectMonitor value);
}