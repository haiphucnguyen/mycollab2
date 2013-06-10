package com.esofthead.mycollab.module.crm.dao;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.module.crm.domain.OpportunityContact;
import com.esofthead.mycollab.module.crm.domain.OpportunityContactExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OpportunityContactMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunities_contacts
     *
     * @mbggenerated Mon Jun 10 16:08:18 GMT+07:00 2013
     */
    int countByExample(OpportunityContactExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunities_contacts
     *
     * @mbggenerated Mon Jun 10 16:08:18 GMT+07:00 2013
     */
    int deleteByExample(OpportunityContactExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunities_contacts
     *
     * @mbggenerated Mon Jun 10 16:08:18 GMT+07:00 2013
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunities_contacts
     *
     * @mbggenerated Mon Jun 10 16:08:18 GMT+07:00 2013
     */
    int insert(OpportunityContact record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunities_contacts
     *
     * @mbggenerated Mon Jun 10 16:08:18 GMT+07:00 2013
     */
    int insertSelective(OpportunityContact record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunities_contacts
     *
     * @mbggenerated Mon Jun 10 16:08:18 GMT+07:00 2013
     */
    List<OpportunityContact> selectByExample(OpportunityContactExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunities_contacts
     *
     * @mbggenerated Mon Jun 10 16:08:18 GMT+07:00 2013
     */
    OpportunityContact selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunities_contacts
     *
     * @mbggenerated Mon Jun 10 16:08:18 GMT+07:00 2013
     */
    int updateByExampleSelective(@Param("record") OpportunityContact record, @Param("example") OpportunityContactExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunities_contacts
     *
     * @mbggenerated Mon Jun 10 16:08:18 GMT+07:00 2013
     */
    int updateByExample(@Param("record") OpportunityContact record, @Param("example") OpportunityContactExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunities_contacts
     *
     * @mbggenerated Mon Jun 10 16:08:18 GMT+07:00 2013
     */
    int updateByPrimaryKeySelective(OpportunityContact record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunities_contacts
     *
     * @mbggenerated Mon Jun 10 16:08:18 GMT+07:00 2013
     */
    Integer insertAndReturnKey(OpportunityContact value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunities_contacts
     *
     * @mbggenerated Mon Jun 10 16:08:18 GMT+07:00 2013
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunities_contacts
     *
     * @mbggenerated Mon Jun 10 16:08:18 GMT+07:00 2013
     */
    void massUpdateWithSession(@Param("record") OpportunityContact record, @Param("primaryKeys") List primaryKeys);
}