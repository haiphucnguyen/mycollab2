package com.esofthead.mycollab.module.crm.dao;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.module.crm.domain.TargetGroup;
import com.esofthead.mycollab.module.crm.domain.TargetGroupExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TargetGroupMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_target_list
     *
     * @mbggenerated Thu Nov 29 15:59:46 GMT+07:00 2012
     */
    int countByExample(TargetGroupExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_target_list
     *
     * @mbggenerated Thu Nov 29 15:59:46 GMT+07:00 2012
     */
    int deleteByExample(TargetGroupExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_target_list
     *
     * @mbggenerated Thu Nov 29 15:59:46 GMT+07:00 2012
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_target_list
     *
     * @mbggenerated Thu Nov 29 15:59:46 GMT+07:00 2012
     */
    int insert(TargetGroup record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_target_list
     *
     * @mbggenerated Thu Nov 29 15:59:46 GMT+07:00 2012
     */
    int insertSelective(TargetGroup record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_target_list
     *
     * @mbggenerated Thu Nov 29 15:59:46 GMT+07:00 2012
     */
    List<TargetGroup> selectByExample(TargetGroupExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_target_list
     *
     * @mbggenerated Thu Nov 29 15:59:46 GMT+07:00 2012
     */
    TargetGroup selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_target_list
     *
     * @mbggenerated Thu Nov 29 15:59:46 GMT+07:00 2012
     */
    int updateByExampleSelective(@Param("record") TargetGroup record, @Param("example") TargetGroupExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_target_list
     *
     * @mbggenerated Thu Nov 29 15:59:46 GMT+07:00 2012
     */
    int updateByExample(@Param("record") TargetGroup record, @Param("example") TargetGroupExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_target_list
     *
     * @mbggenerated Thu Nov 29 15:59:46 GMT+07:00 2012
     */
    int updateByPrimaryKeySelective(TargetGroup record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_target_list
     *
     * @mbggenerated Thu Nov 29 15:59:46 GMT+07:00 2012
     */
    int updateByPrimaryKey(TargetGroup record);
}