package com.esofthead.mycollab.module.crm.dao;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.module.crm.domain.Contact;
import com.esofthead.mycollab.module.crm.domain.ContactExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ContactMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contact
     *
     * @mbggenerated Sun Nov 25 22:10:59 GMT+07:00 2012
     */
    int countByExample(ContactExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contact
     *
     * @mbggenerated Sun Nov 25 22:10:59 GMT+07:00 2012
     */
    int deleteByExample(ContactExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contact
     *
     * @mbggenerated Sun Nov 25 22:10:59 GMT+07:00 2012
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contact
     *
     * @mbggenerated Sun Nov 25 22:10:59 GMT+07:00 2012
     */
    int insert(Contact record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contact
     *
     * @mbggenerated Sun Nov 25 22:10:59 GMT+07:00 2012
     */
    int insertSelective(Contact record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contact
     *
     * @mbggenerated Sun Nov 25 22:10:59 GMT+07:00 2012
     */
    List<Contact> selectByExample(ContactExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contact
     *
     * @mbggenerated Sun Nov 25 22:10:59 GMT+07:00 2012
     */
    Contact selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contact
     *
     * @mbggenerated Sun Nov 25 22:10:59 GMT+07:00 2012
     */
    int updateByExampleSelective(@Param("record") Contact record, @Param("example") ContactExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contact
     *
     * @mbggenerated Sun Nov 25 22:10:59 GMT+07:00 2012
     */
    int updateByExample(@Param("record") Contact record, @Param("example") ContactExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contact
     *
     * @mbggenerated Sun Nov 25 22:10:59 GMT+07:00 2012
     */
    int updateByPrimaryKeySelective(Contact record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contact
     *
     * @mbggenerated Sun Nov 25 22:10:59 GMT+07:00 2012
     */
    int updateByPrimaryKey(Contact record);
}