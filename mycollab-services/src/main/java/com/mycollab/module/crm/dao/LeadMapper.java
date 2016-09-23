package com.mycollab.module.crm.dao;

import com.mycollab.db.persistence.ICrudGenericDAO;
import com.mycollab.module.crm.domain.Lead;
import com.mycollab.module.crm.domain.LeadExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@SuppressWarnings({ "ucd", "rawtypes" })
public interface LeadMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_lead
     *
     * @mbg.generated Fri Sep 23 03:45:52 ICT 2016
     */
    long countByExample(LeadExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_lead
     *
     * @mbg.generated Fri Sep 23 03:45:52 ICT 2016
     */
    int deleteByExample(LeadExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_lead
     *
     * @mbg.generated Fri Sep 23 03:45:52 ICT 2016
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_lead
     *
     * @mbg.generated Fri Sep 23 03:45:52 ICT 2016
     */
    int insert(Lead record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_lead
     *
     * @mbg.generated Fri Sep 23 03:45:52 ICT 2016
     */
    int insertSelective(Lead record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_lead
     *
     * @mbg.generated Fri Sep 23 03:45:52 ICT 2016
     */
    List<Lead> selectByExampleWithBLOBs(LeadExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_lead
     *
     * @mbg.generated Fri Sep 23 03:45:52 ICT 2016
     */
    List<Lead> selectByExample(LeadExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_lead
     *
     * @mbg.generated Fri Sep 23 03:45:52 ICT 2016
     */
    Lead selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_lead
     *
     * @mbg.generated Fri Sep 23 03:45:52 ICT 2016
     */
    int updateByExampleSelective(@Param("record") Lead record, @Param("example") LeadExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_lead
     *
     * @mbg.generated Fri Sep 23 03:45:52 ICT 2016
     */
    int updateByExampleWithBLOBs(@Param("record") Lead record, @Param("example") LeadExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_lead
     *
     * @mbg.generated Fri Sep 23 03:45:52 ICT 2016
     */
    int updateByExample(@Param("record") Lead record, @Param("example") LeadExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_lead
     *
     * @mbg.generated Fri Sep 23 03:45:52 ICT 2016
     */
    int updateByPrimaryKeySelective(Lead record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_lead
     *
     * @mbg.generated Fri Sep 23 03:45:52 ICT 2016
     */
    int updateByPrimaryKeyWithBLOBs(Lead record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_lead
     *
     * @mbg.generated Fri Sep 23 03:45:52 ICT 2016
     */
    int updateByPrimaryKey(Lead record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_lead
     *
     * @mbg.generated Fri Sep 23 03:45:52 ICT 2016
     */
    Integer insertAndReturnKey(Lead value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_lead
     *
     * @mbg.generated Fri Sep 23 03:45:52 ICT 2016
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_lead
     *
     * @mbg.generated Fri Sep 23 03:45:52 ICT 2016
     */
    void massUpdateWithSession(@Param("record") Lead record, @Param("primaryKeys") List primaryKeys);
}