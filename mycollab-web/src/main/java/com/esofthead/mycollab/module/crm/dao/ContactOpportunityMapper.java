package com.esofthead.mycollab.module.crm.dao;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.module.crm.domain.ContactOpportunity;
import com.esofthead.mycollab.module.crm.domain.ContactOpportunityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ContactOpportunityMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contacts_opportunities
     *
     * @mbggenerated Thu Mar 28 17:33:56 GMT+07:00 2013
     */
    int countByExample(ContactOpportunityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contacts_opportunities
     *
     * @mbggenerated Thu Mar 28 17:33:56 GMT+07:00 2013
     */
    int deleteByExample(ContactOpportunityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contacts_opportunities
     *
     * @mbggenerated Thu Mar 28 17:33:56 GMT+07:00 2013
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contacts_opportunities
     *
     * @mbggenerated Thu Mar 28 17:33:56 GMT+07:00 2013
     */
    int insert(ContactOpportunity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contacts_opportunities
     *
     * @mbggenerated Thu Mar 28 17:33:56 GMT+07:00 2013
     */
    int insertSelective(ContactOpportunity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contacts_opportunities
     *
     * @mbggenerated Thu Mar 28 17:33:56 GMT+07:00 2013
     */
    List<ContactOpportunity> selectByExample(ContactOpportunityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contacts_opportunities
     *
     * @mbggenerated Thu Mar 28 17:33:56 GMT+07:00 2013
     */
    ContactOpportunity selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contacts_opportunities
     *
     * @mbggenerated Thu Mar 28 17:33:56 GMT+07:00 2013
     */
    int updateByExampleSelective(@Param("record") ContactOpportunity record, @Param("example") ContactOpportunityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contacts_opportunities
     *
     * @mbggenerated Thu Mar 28 17:33:56 GMT+07:00 2013
     */
    int updateByExample(@Param("record") ContactOpportunity record, @Param("example") ContactOpportunityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contacts_opportunities
     *
     * @mbggenerated Thu Mar 28 17:33:56 GMT+07:00 2013
     */
    int updateByPrimaryKeySelective(ContactOpportunity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contacts_opportunities
     *
     * @mbggenerated Thu Mar 28 17:33:56 GMT+07:00 2013
     */
    int updateByPrimaryKey(ContactOpportunity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contacts_opportunities
     *
     * @mbggenerated Thu Mar 28 17:33:56 GMT+07:00 2013
     */
    Integer insertAndReturnKey(ContactOpportunity value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contacts_opportunities
     *
     * @mbggenerated Thu Mar 28 17:33:56 GMT+07:00 2013
     */
    void removeKeysWithSession(List primaryKeys);
}