package com.esofthead.mycollab.module.crm.dao;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.module.crm.domain.CrmTask;
import com.esofthead.mycollab.module.crm.domain.CrmTaskExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@SuppressWarnings({ "ucd", "rawtypes" })
public interface CrmTaskMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_task
     *
     * @mbggenerated Tue Jun 23 23:25:31 ICT 2015
     */
    int countByExample(CrmTaskExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_task
     *
     * @mbggenerated Tue Jun 23 23:25:31 ICT 2015
     */
    int deleteByExample(CrmTaskExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_task
     *
     * @mbggenerated Tue Jun 23 23:25:31 ICT 2015
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_task
     *
     * @mbggenerated Tue Jun 23 23:25:31 ICT 2015
     */
    int insert(CrmTask record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_task
     *
     * @mbggenerated Tue Jun 23 23:25:31 ICT 2015
     */
    int insertSelective(CrmTask record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_task
     *
     * @mbggenerated Tue Jun 23 23:25:31 ICT 2015
     */
    List<CrmTask> selectByExampleWithBLOBs(CrmTaskExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_task
     *
     * @mbggenerated Tue Jun 23 23:25:31 ICT 2015
     */
    List<CrmTask> selectByExample(CrmTaskExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_task
     *
     * @mbggenerated Tue Jun 23 23:25:31 ICT 2015
     */
    CrmTask selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_task
     *
     * @mbggenerated Tue Jun 23 23:25:31 ICT 2015
     */
    int updateByExampleSelective(@Param("record") CrmTask record, @Param("example") CrmTaskExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_task
     *
     * @mbggenerated Tue Jun 23 23:25:31 ICT 2015
     */
    int updateByExampleWithBLOBs(@Param("record") CrmTask record, @Param("example") CrmTaskExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_task
     *
     * @mbggenerated Tue Jun 23 23:25:31 ICT 2015
     */
    int updateByExample(@Param("record") CrmTask record, @Param("example") CrmTaskExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_task
     *
     * @mbggenerated Tue Jun 23 23:25:31 ICT 2015
     */
    int updateByPrimaryKeySelective(CrmTask record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_task
     *
     * @mbggenerated Tue Jun 23 23:25:31 ICT 2015
     */
    int updateByPrimaryKeyWithBLOBs(CrmTask record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_task
     *
     * @mbggenerated Tue Jun 23 23:25:31 ICT 2015
     */
    int updateByPrimaryKey(CrmTask record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_task
     *
     * @mbggenerated Tue Jun 23 23:25:31 ICT 2015
     */
    Integer insertAndReturnKey(CrmTask value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_task
     *
     * @mbggenerated Tue Jun 23 23:25:31 ICT 2015
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_task
     *
     * @mbggenerated Tue Jun 23 23:25:31 ICT 2015
     */
    void massUpdateWithSession(@Param("record") CrmTask record, @Param("primaryKeys") List primaryKeys);
}