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
     * @mbggenerated Mon Apr 01 16:56:55 GMT+07:00 2013
     */
    int countByExample(ContactExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contact
     *
     * @mbggenerated Mon Apr 01 16:56:55 GMT+07:00 2013
     */
    int deleteByExample(ContactExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contact
     *
     * @mbggenerated Mon Apr 01 16:56:55 GMT+07:00 2013
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contact
     *
     * @mbggenerated Mon Apr 01 16:56:55 GMT+07:00 2013
     */
    int insert(Contact record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contact
     *
     * @mbggenerated Mon Apr 01 16:56:55 GMT+07:00 2013
     */
    int insertSelective(Contact record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contact
     *
     * @mbggenerated Mon Apr 01 16:56:55 GMT+07:00 2013
     */
    List<Contact> selectByExampleWithBLOBs(ContactExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contact
     *
     * @mbggenerated Mon Apr 01 16:56:55 GMT+07:00 2013
     */
    List<Contact> selectByExample(ContactExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contact
     *
     * @mbggenerated Mon Apr 01 16:56:55 GMT+07:00 2013
     */
    Contact selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contact
     *
     * @mbggenerated Mon Apr 01 16:56:55 GMT+07:00 2013
     */
    int updateByExampleSelective(@Param("record") Contact record, @Param("example") ContactExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contact
     *
     * @mbggenerated Mon Apr 01 16:56:55 GMT+07:00 2013
     */
    int updateByExampleWithBLOBs(@Param("record") Contact record, @Param("example") ContactExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contact
     *
     * @mbggenerated Mon Apr 01 16:56:55 GMT+07:00 2013
     */
    int updateByExample(@Param("record") Contact record, @Param("example") ContactExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contact
     *
     * @mbggenerated Mon Apr 01 16:56:55 GMT+07:00 2013
     */
    int updateByPrimaryKeySelective(Contact record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contact
     *
     * @mbggenerated Mon Apr 01 16:56:55 GMT+07:00 2013
     */
    int updateByPrimaryKeyWithBLOBs(Contact record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contact
     *
     * @mbggenerated Mon Apr 01 16:56:55 GMT+07:00 2013
     */
    int updateByPrimaryKey(Contact record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contact
     *
     * @mbggenerated Mon Apr 01 16:56:55 GMT+07:00 2013
     */
    Integer insertAndReturnKey(Contact value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contact
     *
     * @mbggenerated Mon Apr 01 16:56:55 GMT+07:00 2013
     */
    void removeKeysWithSession(List primaryKeys);
}