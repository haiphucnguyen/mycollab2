package com.esofthead.mycollab.module.crm.dao;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.module.crm.domain.Lead;
import com.esofthead.mycollab.module.crm.domain.LeadExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LeadMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_lead
     *
     * @mbggenerated Sun Dec 02 12:54:53 GMT+07:00 2012
     */
    int countByExample(LeadExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_lead
     *
     * @mbggenerated Sun Dec 02 12:54:53 GMT+07:00 2012
     */
    int deleteByExample(LeadExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_lead
     *
     * @mbggenerated Sun Dec 02 12:54:53 GMT+07:00 2012
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_lead
     *
     * @mbggenerated Sun Dec 02 12:54:53 GMT+07:00 2012
     */
    int insert(Lead record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_lead
     *
     * @mbggenerated Sun Dec 02 12:54:53 GMT+07:00 2012
     */
    int insertSelective(Lead record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_lead
     *
     * @mbggenerated Sun Dec 02 12:54:53 GMT+07:00 2012
     */
    List<Lead> selectByExample(LeadExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_lead
     *
     * @mbggenerated Sun Dec 02 12:54:53 GMT+07:00 2012
     */
    Lead selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_lead
     *
     * @mbggenerated Sun Dec 02 12:54:53 GMT+07:00 2012
     */
    int updateByExampleSelective(@Param("record") Lead record, @Param("example") LeadExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_lead
     *
     * @mbggenerated Sun Dec 02 12:54:53 GMT+07:00 2012
     */
    int updateByExample(@Param("record") Lead record, @Param("example") LeadExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_lead
     *
     * @mbggenerated Sun Dec 02 12:54:53 GMT+07:00 2012
     */
    int updateByPrimaryKeySelective(Lead record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_lead
     *
     * @mbggenerated Sun Dec 02 12:54:53 GMT+07:00 2012
     */
    int updateByPrimaryKey(Lead record);
}