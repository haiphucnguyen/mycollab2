package com.esofthead.mycollab.module.crm.dao;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.module.crm.domain.ContactCase;
import com.esofthead.mycollab.module.crm.domain.ContactCaseExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ContactCaseMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contacts_cases
     *
     * @mbggenerated Wed Apr 17 15:12:10 GMT+07:00 2013
     */
    int countByExample(ContactCaseExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contacts_cases
     *
     * @mbggenerated Wed Apr 17 15:12:10 GMT+07:00 2013
     */
    int deleteByExample(ContactCaseExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contacts_cases
     *
     * @mbggenerated Wed Apr 17 15:12:10 GMT+07:00 2013
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contacts_cases
     *
     * @mbggenerated Wed Apr 17 15:12:10 GMT+07:00 2013
     */
    int insert(ContactCase record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contacts_cases
     *
     * @mbggenerated Wed Apr 17 15:12:10 GMT+07:00 2013
     */
    int insertSelective(ContactCase record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contacts_cases
     *
     * @mbggenerated Wed Apr 17 15:12:10 GMT+07:00 2013
     */
    List<ContactCase> selectByExample(ContactCaseExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contacts_cases
     *
     * @mbggenerated Wed Apr 17 15:12:10 GMT+07:00 2013
     */
    ContactCase selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contacts_cases
     *
     * @mbggenerated Wed Apr 17 15:12:10 GMT+07:00 2013
     */
    int updateByExampleSelective(@Param("record") ContactCase record, @Param("example") ContactCaseExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contacts_cases
     *
     * @mbggenerated Wed Apr 17 15:12:10 GMT+07:00 2013
     */
    int updateByExample(@Param("record") ContactCase record, @Param("example") ContactCaseExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contacts_cases
     *
     * @mbggenerated Wed Apr 17 15:12:10 GMT+07:00 2013
     */
    int updateByPrimaryKeySelective(ContactCase record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contacts_cases
     *
     * @mbggenerated Wed Apr 17 15:12:10 GMT+07:00 2013
     */
    Integer insertAndReturnKey(ContactCase value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contacts_cases
     *
     * @mbggenerated Wed Apr 17 15:12:10 GMT+07:00 2013
     */
    void removeKeysWithSession(List primaryKeys);
}