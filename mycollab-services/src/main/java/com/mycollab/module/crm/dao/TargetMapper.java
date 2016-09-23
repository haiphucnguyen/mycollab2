package com.mycollab.module.crm.dao;

import com.mycollab.db.persistence.ICrudGenericDAO;
import com.mycollab.module.crm.domain.Target;
import com.mycollab.module.crm.domain.TargetExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@SuppressWarnings({ "ucd", "rawtypes" })
public interface TargetMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_target
     *
     * @mbg.generated Fri Sep 23 08:39:36 ICT 2016
     */
    long countByExample(TargetExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_target
     *
     * @mbg.generated Fri Sep 23 08:39:36 ICT 2016
     */
    int deleteByExample(TargetExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_target
     *
     * @mbg.generated Fri Sep 23 08:39:36 ICT 2016
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_target
     *
     * @mbg.generated Fri Sep 23 08:39:36 ICT 2016
     */
    int insert(Target record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_target
     *
     * @mbg.generated Fri Sep 23 08:39:36 ICT 2016
     */
    int insertSelective(Target record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_target
     *
     * @mbg.generated Fri Sep 23 08:39:36 ICT 2016
     */
    List<Target> selectByExample(TargetExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_target
     *
     * @mbg.generated Fri Sep 23 08:39:36 ICT 2016
     */
    Target selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_target
     *
     * @mbg.generated Fri Sep 23 08:39:36 ICT 2016
     */
    int updateByExampleSelective(@Param("record") Target record, @Param("example") TargetExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_target
     *
     * @mbg.generated Fri Sep 23 08:39:36 ICT 2016
     */
    int updateByExample(@Param("record") Target record, @Param("example") TargetExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_target
     *
     * @mbg.generated Fri Sep 23 08:39:36 ICT 2016
     */
    int updateByPrimaryKeySelective(Target record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_target
     *
     * @mbg.generated Fri Sep 23 08:39:36 ICT 2016
     */
    int updateByPrimaryKey(Target record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_target
     *
     * @mbg.generated Fri Sep 23 08:39:36 ICT 2016
     */
    Integer insertAndReturnKey(Target value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_target
     *
     * @mbg.generated Fri Sep 23 08:39:36 ICT 2016
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_target
     *
     * @mbg.generated Fri Sep 23 08:39:36 ICT 2016
     */
    void massUpdateWithSession(@Param("record") Target record, @Param("primaryKeys") List primaryKeys);
}