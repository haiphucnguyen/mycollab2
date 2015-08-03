package com.esofthead.mycollab.module.crm.dao;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.module.crm.domain.Target;
import com.esofthead.mycollab.module.crm.domain.TargetExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@SuppressWarnings({ "ucd", "rawtypes" })
public interface TargetMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_target
     *
     * @mbggenerated Mon Aug 03 15:23:13 ICT 2015
     */
    int countByExample(TargetExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_target
     *
     * @mbggenerated Mon Aug 03 15:23:13 ICT 2015
     */
    int deleteByExample(TargetExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_target
     *
     * @mbggenerated Mon Aug 03 15:23:13 ICT 2015
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_target
     *
     * @mbggenerated Mon Aug 03 15:23:13 ICT 2015
     */
    int insert(Target record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_target
     *
     * @mbggenerated Mon Aug 03 15:23:13 ICT 2015
     */
    int insertSelective(Target record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_target
     *
     * @mbggenerated Mon Aug 03 15:23:13 ICT 2015
     */
    List<Target> selectByExample(TargetExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_target
     *
     * @mbggenerated Mon Aug 03 15:23:13 ICT 2015
     */
    Target selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_target
     *
     * @mbggenerated Mon Aug 03 15:23:13 ICT 2015
     */
    int updateByExampleSelective(@Param("record") Target record, @Param("example") TargetExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_target
     *
     * @mbggenerated Mon Aug 03 15:23:13 ICT 2015
     */
    int updateByExample(@Param("record") Target record, @Param("example") TargetExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_target
     *
     * @mbggenerated Mon Aug 03 15:23:13 ICT 2015
     */
    int updateByPrimaryKeySelective(Target record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_target
     *
     * @mbggenerated Mon Aug 03 15:23:13 ICT 2015
     */
    int updateByPrimaryKey(Target record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_target
     *
     * @mbggenerated Mon Aug 03 15:23:13 ICT 2015
     */
    Integer insertAndReturnKey(Target value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_target
     *
     * @mbggenerated Mon Aug 03 15:23:13 ICT 2015
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_target
     *
     * @mbggenerated Mon Aug 03 15:23:13 ICT 2015
     */
    void massUpdateWithSession(@Param("record") Target record, @Param("primaryKeys") List primaryKeys);
}