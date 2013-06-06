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
     * @mbggenerated Thu Jun 06 11:18:18 GMT+07:00 2013
     */
    int countByExample(TargetGroupExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_target_list
     *
     * @mbggenerated Thu Jun 06 11:18:18 GMT+07:00 2013
     */
    int deleteByExample(TargetGroupExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_target_list
     *
     * @mbggenerated Thu Jun 06 11:18:18 GMT+07:00 2013
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_target_list
     *
     * @mbggenerated Thu Jun 06 11:18:18 GMT+07:00 2013
     */
    int insert(TargetGroup record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_target_list
     *
     * @mbggenerated Thu Jun 06 11:18:18 GMT+07:00 2013
     */
    int insertSelective(TargetGroup record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_target_list
     *
     * @mbggenerated Thu Jun 06 11:18:18 GMT+07:00 2013
     */
    List<TargetGroup> selectByExample(TargetGroupExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_target_list
     *
     * @mbggenerated Thu Jun 06 11:18:18 GMT+07:00 2013
     */
    TargetGroup selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_target_list
     *
     * @mbggenerated Thu Jun 06 11:18:18 GMT+07:00 2013
     */
    int updateByExampleSelective(@Param("record") TargetGroup record, @Param("example") TargetGroupExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_target_list
     *
     * @mbggenerated Thu Jun 06 11:18:18 GMT+07:00 2013
     */
    int updateByExample(@Param("record") TargetGroup record, @Param("example") TargetGroupExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_target_list
     *
     * @mbggenerated Thu Jun 06 11:18:18 GMT+07:00 2013
     */
    int updateByPrimaryKeySelective(TargetGroup record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_target_list
     *
     * @mbggenerated Thu Jun 06 11:18:18 GMT+07:00 2013
     */
    Integer insertAndReturnKey(TargetGroup value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_target_list
     *
     * @mbggenerated Thu Jun 06 11:18:18 GMT+07:00 2013
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_target_list
     *
     * @mbggenerated Thu Jun 06 11:18:18 GMT+07:00 2013
     */
    void massUpdateWithSession(@Param("record") TargetGroup record, @Param("primaryKeys") List primaryKeys);
}