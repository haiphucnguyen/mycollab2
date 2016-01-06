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
     * @mbggenerated Tue Jan 05 23:09:02 ICT 2016
     */
    int countByExample(TargetExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_target
     *
     * @mbggenerated Tue Jan 05 23:09:02 ICT 2016
     */
    int deleteByExample(TargetExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_target
     *
     * @mbggenerated Tue Jan 05 23:09:02 ICT 2016
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_target
     *
     * @mbggenerated Tue Jan 05 23:09:02 ICT 2016
     */
    int insert(Target record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_target
     *
     * @mbggenerated Tue Jan 05 23:09:02 ICT 2016
     */
    int insertSelective(Target record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_target
     *
     * @mbggenerated Tue Jan 05 23:09:02 ICT 2016
     */
    List<Target> selectByExample(TargetExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_target
     *
     * @mbggenerated Tue Jan 05 23:09:02 ICT 2016
     */
    Target selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_target
     *
     * @mbggenerated Tue Jan 05 23:09:02 ICT 2016
     */
    int updateByExampleSelective(@Param("record") Target record, @Param("example") TargetExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_target
     *
     * @mbggenerated Tue Jan 05 23:09:02 ICT 2016
     */
    int updateByExample(@Param("record") Target record, @Param("example") TargetExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_target
     *
     * @mbggenerated Tue Jan 05 23:09:02 ICT 2016
     */
    int updateByPrimaryKeySelective(Target record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_target
     *
     * @mbggenerated Tue Jan 05 23:09:02 ICT 2016
     */
    int updateByPrimaryKey(Target record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_target
     *
     * @mbggenerated Tue Jan 05 23:09:02 ICT 2016
     */
    Integer insertAndReturnKey(Target value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_target
     *
     * @mbggenerated Tue Jan 05 23:09:02 ICT 2016
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_target
     *
     * @mbggenerated Tue Jan 05 23:09:02 ICT 2016
     */
    void massUpdateWithSession(@Param("record") Target record, @Param("primaryKeys") List primaryKeys);
}