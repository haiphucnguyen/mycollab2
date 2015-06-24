package com.esofthead.mycollab.module.crm.dao;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.module.crm.domain.ContactCase;
import com.esofthead.mycollab.module.crm.domain.ContactCaseExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@SuppressWarnings({ "ucd", "rawtypes" })
public interface ContactCaseMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contacts_cases
     *
     * @mbggenerated Tue Jun 23 23:25:31 ICT 2015
     */
    int countByExample(ContactCaseExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contacts_cases
     *
     * @mbggenerated Tue Jun 23 23:25:31 ICT 2015
     */
    int deleteByExample(ContactCaseExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contacts_cases
     *
     * @mbggenerated Tue Jun 23 23:25:31 ICT 2015
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contacts_cases
     *
     * @mbggenerated Tue Jun 23 23:25:31 ICT 2015
     */
    int insert(ContactCase record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contacts_cases
     *
     * @mbggenerated Tue Jun 23 23:25:31 ICT 2015
     */
    int insertSelective(ContactCase record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contacts_cases
     *
     * @mbggenerated Tue Jun 23 23:25:31 ICT 2015
     */
    List<ContactCase> selectByExample(ContactCaseExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contacts_cases
     *
     * @mbggenerated Tue Jun 23 23:25:31 ICT 2015
     */
    ContactCase selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contacts_cases
     *
     * @mbggenerated Tue Jun 23 23:25:31 ICT 2015
     */
    int updateByExampleSelective(@Param("record") ContactCase record, @Param("example") ContactCaseExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contacts_cases
     *
     * @mbggenerated Tue Jun 23 23:25:31 ICT 2015
     */
    int updateByExample(@Param("record") ContactCase record, @Param("example") ContactCaseExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contacts_cases
     *
     * @mbggenerated Tue Jun 23 23:25:31 ICT 2015
     */
    int updateByPrimaryKeySelective(ContactCase record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contacts_cases
     *
     * @mbggenerated Tue Jun 23 23:25:31 ICT 2015
     */
    int updateByPrimaryKey(ContactCase record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contacts_cases
     *
     * @mbggenerated Tue Jun 23 23:25:31 ICT 2015
     */
    Integer insertAndReturnKey(ContactCase value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contacts_cases
     *
     * @mbggenerated Tue Jun 23 23:25:31 ICT 2015
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contacts_cases
     *
     * @mbggenerated Tue Jun 23 23:25:31 ICT 2015
     */
    void massUpdateWithSession(@Param("record") ContactCase record, @Param("primaryKeys") List primaryKeys);
}