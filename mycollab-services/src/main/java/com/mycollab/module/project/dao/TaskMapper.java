package com.mycollab.module.project.dao;

import com.mycollab.db.persistence.ICrudGenericDAO;
import com.mycollab.module.project.domain.Task;
import com.mycollab.module.project.domain.TaskExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@SuppressWarnings({ "ucd", "rawtypes" })
public interface TaskMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_task
     *
     * @mbg.generated Fri Jun 01 14:16:25 ICT 2018
     */
    long countByExample(TaskExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_task
     *
     * @mbg.generated Fri Jun 01 14:16:25 ICT 2018
     */
    int deleteByExample(TaskExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_task
     *
     * @mbg.generated Fri Jun 01 14:16:25 ICT 2018
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_task
     *
     * @mbg.generated Fri Jun 01 14:16:25 ICT 2018
     */
    int insert(Task record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_task
     *
     * @mbg.generated Fri Jun 01 14:16:25 ICT 2018
     */
    int insertSelective(Task record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_task
     *
     * @mbg.generated Fri Jun 01 14:16:25 ICT 2018
     */
    List<Task> selectByExampleWithBLOBs(TaskExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_task
     *
     * @mbg.generated Fri Jun 01 14:16:25 ICT 2018
     */
    List<Task> selectByExample(TaskExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_task
     *
     * @mbg.generated Fri Jun 01 14:16:25 ICT 2018
     */
    Task selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_task
     *
     * @mbg.generated Fri Jun 01 14:16:25 ICT 2018
     */
    int updateByExampleSelective(@Param("record") Task record, @Param("example") TaskExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_task
     *
     * @mbg.generated Fri Jun 01 14:16:25 ICT 2018
     */
    int updateByExampleWithBLOBs(@Param("record") Task record, @Param("example") TaskExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_task
     *
     * @mbg.generated Fri Jun 01 14:16:25 ICT 2018
     */
    int updateByExample(@Param("record") Task record, @Param("example") TaskExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_task
     *
     * @mbg.generated Fri Jun 01 14:16:25 ICT 2018
     */
    int updateByPrimaryKeySelective(Task record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_task
     *
     * @mbg.generated Fri Jun 01 14:16:25 ICT 2018
     */
    int updateByPrimaryKeyWithBLOBs(Task record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_task
     *
     * @mbg.generated Fri Jun 01 14:16:25 ICT 2018
     */
    int updateByPrimaryKey(Task record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_task
     *
     * @mbg.generated Fri Jun 01 14:16:25 ICT 2018
     */
    Integer insertAndReturnKey(Task value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_task
     *
     * @mbg.generated Fri Jun 01 14:16:25 ICT 2018
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_task
     *
     * @mbg.generated Fri Jun 01 14:16:25 ICT 2018
     */
    void massUpdateWithSession(@Param("record") Task record, @Param("primaryKeys") List primaryKeys);
}