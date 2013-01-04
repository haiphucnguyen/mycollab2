package com.esofthead.mycollab.module.crm.dao;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.module.crm.domain.Target;
import com.esofthead.mycollab.module.crm.domain.TargetExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TargetMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_target
     *
     * @mbggenerated Thu Jan 03 21:02:24 GMT+07:00 2013
     */
    int countByExample(TargetExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_target
     *
     * @mbggenerated Thu Jan 03 21:02:24 GMT+07:00 2013
     */
    int deleteByExample(TargetExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_target
     *
     * @mbggenerated Thu Jan 03 21:02:24 GMT+07:00 2013
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_target
     *
     * @mbggenerated Thu Jan 03 21:02:24 GMT+07:00 2013
     */
    int insert(Target record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_target
     *
     * @mbggenerated Thu Jan 03 21:02:24 GMT+07:00 2013
     */
    int insertSelective(Target record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_target
     *
     * @mbggenerated Thu Jan 03 21:02:24 GMT+07:00 2013
     */
    List<Target> selectByExample(TargetExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_target
     *
     * @mbggenerated Thu Jan 03 21:02:24 GMT+07:00 2013
     */
    Target selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_target
     *
     * @mbggenerated Thu Jan 03 21:02:24 GMT+07:00 2013
     */
    int updateByExampleSelective(@Param("record") Target record, @Param("example") TargetExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_target
     *
     * @mbggenerated Thu Jan 03 21:02:24 GMT+07:00 2013
     */
    int updateByExample(@Param("record") Target record, @Param("example") TargetExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_target
     *
     * @mbggenerated Thu Jan 03 21:02:24 GMT+07:00 2013
     */
    int updateByPrimaryKeySelective(Target record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_target
     *
     * @mbggenerated Thu Jan 03 21:02:24 GMT+07:00 2013
     */
    int updateByPrimaryKey(Target record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_target
     *
     * @mbggenerated Thu Jan 03 21:02:24 GMT+07:00 2013
     */
    Integer insertAndReturnKey(Target value);
}