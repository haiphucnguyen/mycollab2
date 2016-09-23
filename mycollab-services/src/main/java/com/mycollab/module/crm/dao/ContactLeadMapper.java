package com.mycollab.module.crm.dao;

import com.mycollab.db.persistence.ICrudGenericDAO;
import com.mycollab.module.crm.domain.ContactLead;
import com.mycollab.module.crm.domain.ContactLeadExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@SuppressWarnings({ "ucd", "rawtypes" })
public interface ContactLeadMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contacts_leads
     *
     * @mbg.generated Fri Sep 23 08:39:36 ICT 2016
     */
    long countByExample(ContactLeadExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contacts_leads
     *
     * @mbg.generated Fri Sep 23 08:39:36 ICT 2016
     */
    int deleteByExample(ContactLeadExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contacts_leads
     *
     * @mbg.generated Fri Sep 23 08:39:36 ICT 2016
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contacts_leads
     *
     * @mbg.generated Fri Sep 23 08:39:36 ICT 2016
     */
    int insert(ContactLead record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contacts_leads
     *
     * @mbg.generated Fri Sep 23 08:39:36 ICT 2016
     */
    int insertSelective(ContactLead record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contacts_leads
     *
     * @mbg.generated Fri Sep 23 08:39:36 ICT 2016
     */
    List<ContactLead> selectByExample(ContactLeadExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contacts_leads
     *
     * @mbg.generated Fri Sep 23 08:39:36 ICT 2016
     */
    ContactLead selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contacts_leads
     *
     * @mbg.generated Fri Sep 23 08:39:36 ICT 2016
     */
    int updateByExampleSelective(@Param("record") ContactLead record, @Param("example") ContactLeadExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contacts_leads
     *
     * @mbg.generated Fri Sep 23 08:39:36 ICT 2016
     */
    int updateByExample(@Param("record") ContactLead record, @Param("example") ContactLeadExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contacts_leads
     *
     * @mbg.generated Fri Sep 23 08:39:36 ICT 2016
     */
    int updateByPrimaryKeySelective(ContactLead record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contacts_leads
     *
     * @mbg.generated Fri Sep 23 08:39:36 ICT 2016
     */
    int updateByPrimaryKey(ContactLead record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contacts_leads
     *
     * @mbg.generated Fri Sep 23 08:39:36 ICT 2016
     */
    Integer insertAndReturnKey(ContactLead value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contacts_leads
     *
     * @mbg.generated Fri Sep 23 08:39:36 ICT 2016
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contacts_leads
     *
     * @mbg.generated Fri Sep 23 08:39:36 ICT 2016
     */
    void massUpdateWithSession(@Param("record") ContactLead record, @Param("primaryKeys") List primaryKeys);
}