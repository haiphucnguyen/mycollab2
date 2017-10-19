package com.mycollab.module.crm.dao;

import com.mycollab.db.persistence.ICrudGenericDAO;
import com.mycollab.module.crm.domain.Contact;
import com.mycollab.module.crm.domain.ContactExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@SuppressWarnings({ "ucd", "rawtypes" })
public interface ContactMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contact
     *
     * @mbg.generated Thu Oct 19 13:57:50 ICT 2017
     */
    long countByExample(ContactExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contact
     *
     * @mbg.generated Thu Oct 19 13:57:50 ICT 2017
     */
    int deleteByExample(ContactExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contact
     *
     * @mbg.generated Thu Oct 19 13:57:50 ICT 2017
     */
    @Delete({
        "delete from m_crm_contact",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contact
     *
     * @mbg.generated Thu Oct 19 13:57:50 ICT 2017
     */
    @Insert({
        "insert into m_crm_contact (id, prefix, ",
        "firstname, lastname, ",
        "leadSource, campaignId, ",
        "isCallable, officePhone, ",
        "mobile, homePhone, ",
        "otherPhone, fax, ",
        "birthday, assistant, ",
        "primAddress, primCity, ",
        "primState, primPostalCode, ",
        "primCountry, title, ",
        "assistantPhone, email, ",
        "department, createdTime, ",
        "createdUser, sAccountId, ",
        "assignUser, otherAddress, ",
        "otherCity, otherState, ",
        "otherPostalCode, otherCountry, ",
        "lastUpdatedTime, accountId, ",
        "avatarId, description)",
        "values (#{id,jdbcType=INTEGER}, #{prefix,jdbcType=VARCHAR}, ",
        "#{firstname,jdbcType=VARCHAR}, #{lastname,jdbcType=VARCHAR}, ",
        "#{leadsource,jdbcType=VARCHAR}, #{campaignid,jdbcType=INTEGER}, ",
        "#{iscallable,jdbcType=BIT}, #{officephone,jdbcType=VARCHAR}, ",
        "#{mobile,jdbcType=VARCHAR}, #{homephone,jdbcType=VARCHAR}, ",
        "#{otherphone,jdbcType=VARCHAR}, #{fax,jdbcType=VARCHAR}, ",
        "#{birthday,jdbcType=DATE}, #{assistant,jdbcType=VARCHAR}, ",
        "#{primaddress,jdbcType=VARCHAR}, #{primcity,jdbcType=VARCHAR}, ",
        "#{primstate,jdbcType=VARCHAR}, #{primpostalcode,jdbcType=VARCHAR}, ",
        "#{primcountry,jdbcType=VARCHAR}, #{title,jdbcType=VARCHAR}, ",
        "#{assistantphone,jdbcType=VARCHAR}, #{email,jdbcType=VARCHAR}, ",
        "#{department,jdbcType=VARCHAR}, #{createdtime,jdbcType=TIMESTAMP}, ",
        "#{createduser,jdbcType=VARCHAR}, #{saccountid,jdbcType=INTEGER}, ",
        "#{assignuser,jdbcType=VARCHAR}, #{otheraddress,jdbcType=VARCHAR}, ",
        "#{othercity,jdbcType=VARCHAR}, #{otherstate,jdbcType=VARCHAR}, ",
        "#{otherpostalcode,jdbcType=VARCHAR}, #{othercountry,jdbcType=VARCHAR}, ",
        "#{lastupdatedtime,jdbcType=TIMESTAMP}, #{accountid,jdbcType=INTEGER}, ",
        "#{avatarid,jdbcType=VARCHAR}, #{description,jdbcType=LONGVARCHAR})"
    })
    int insert(Contact record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contact
     *
     * @mbg.generated Thu Oct 19 13:57:50 ICT 2017
     */
    int insertSelective(Contact record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contact
     *
     * @mbg.generated Thu Oct 19 13:57:50 ICT 2017
     */
    List<Contact> selectByExampleWithBLOBs(ContactExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contact
     *
     * @mbg.generated Thu Oct 19 13:57:50 ICT 2017
     */
    List<Contact> selectByExample(ContactExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contact
     *
     * @mbg.generated Thu Oct 19 13:57:50 ICT 2017
     */
    @Select({
        "select",
        "id, prefix, firstname, lastname, leadSource, campaignId, isCallable, officePhone, ",
        "mobile, homePhone, otherPhone, fax, birthday, assistant, primAddress, primCity, ",
        "primState, primPostalCode, primCountry, title, assistantPhone, email, department, ",
        "createdTime, createdUser, sAccountId, assignUser, otherAddress, otherCity, otherState, ",
        "otherPostalCode, otherCountry, lastUpdatedTime, accountId, avatarId, description",
        "from m_crm_contact",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("com.mycollab.module.crm.dao.ContactMapper.ResultMapWithBLOBs")
    Contact selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contact
     *
     * @mbg.generated Thu Oct 19 13:57:50 ICT 2017
     */
    int updateByExampleSelective(@Param("record") Contact record, @Param("example") ContactExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contact
     *
     * @mbg.generated Thu Oct 19 13:57:50 ICT 2017
     */
    int updateByExampleWithBLOBs(@Param("record") Contact record, @Param("example") ContactExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contact
     *
     * @mbg.generated Thu Oct 19 13:57:50 ICT 2017
     */
    int updateByExample(@Param("record") Contact record, @Param("example") ContactExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contact
     *
     * @mbg.generated Thu Oct 19 13:57:50 ICT 2017
     */
    int updateByPrimaryKeySelective(Contact record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contact
     *
     * @mbg.generated Thu Oct 19 13:57:50 ICT 2017
     */
    @Update({
        "update m_crm_contact",
        "set prefix = #{prefix,jdbcType=VARCHAR},",
          "firstname = #{firstname,jdbcType=VARCHAR},",
          "lastname = #{lastname,jdbcType=VARCHAR},",
          "leadSource = #{leadsource,jdbcType=VARCHAR},",
          "campaignId = #{campaignid,jdbcType=INTEGER},",
          "isCallable = #{iscallable,jdbcType=BIT},",
          "officePhone = #{officephone,jdbcType=VARCHAR},",
          "mobile = #{mobile,jdbcType=VARCHAR},",
          "homePhone = #{homephone,jdbcType=VARCHAR},",
          "otherPhone = #{otherphone,jdbcType=VARCHAR},",
          "fax = #{fax,jdbcType=VARCHAR},",
          "birthday = #{birthday,jdbcType=DATE},",
          "assistant = #{assistant,jdbcType=VARCHAR},",
          "primAddress = #{primaddress,jdbcType=VARCHAR},",
          "primCity = #{primcity,jdbcType=VARCHAR},",
          "primState = #{primstate,jdbcType=VARCHAR},",
          "primPostalCode = #{primpostalcode,jdbcType=VARCHAR},",
          "primCountry = #{primcountry,jdbcType=VARCHAR},",
          "title = #{title,jdbcType=VARCHAR},",
          "assistantPhone = #{assistantphone,jdbcType=VARCHAR},",
          "email = #{email,jdbcType=VARCHAR},",
          "department = #{department,jdbcType=VARCHAR},",
          "createdTime = #{createdtime,jdbcType=TIMESTAMP},",
          "createdUser = #{createduser,jdbcType=VARCHAR},",
          "sAccountId = #{saccountid,jdbcType=INTEGER},",
          "assignUser = #{assignuser,jdbcType=VARCHAR},",
          "otherAddress = #{otheraddress,jdbcType=VARCHAR},",
          "otherCity = #{othercity,jdbcType=VARCHAR},",
          "otherState = #{otherstate,jdbcType=VARCHAR},",
          "otherPostalCode = #{otherpostalcode,jdbcType=VARCHAR},",
          "otherCountry = #{othercountry,jdbcType=VARCHAR},",
          "lastUpdatedTime = #{lastupdatedtime,jdbcType=TIMESTAMP},",
          "accountId = #{accountid,jdbcType=INTEGER},",
          "avatarId = #{avatarid,jdbcType=VARCHAR},",
          "description = #{description,jdbcType=LONGVARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKeyWithBLOBs(Contact record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contact
     *
     * @mbg.generated Thu Oct 19 13:57:50 ICT 2017
     */
    @Update({
        "update m_crm_contact",
        "set prefix = #{prefix,jdbcType=VARCHAR},",
          "firstname = #{firstname,jdbcType=VARCHAR},",
          "lastname = #{lastname,jdbcType=VARCHAR},",
          "leadSource = #{leadsource,jdbcType=VARCHAR},",
          "campaignId = #{campaignid,jdbcType=INTEGER},",
          "isCallable = #{iscallable,jdbcType=BIT},",
          "officePhone = #{officephone,jdbcType=VARCHAR},",
          "mobile = #{mobile,jdbcType=VARCHAR},",
          "homePhone = #{homephone,jdbcType=VARCHAR},",
          "otherPhone = #{otherphone,jdbcType=VARCHAR},",
          "fax = #{fax,jdbcType=VARCHAR},",
          "birthday = #{birthday,jdbcType=DATE},",
          "assistant = #{assistant,jdbcType=VARCHAR},",
          "primAddress = #{primaddress,jdbcType=VARCHAR},",
          "primCity = #{primcity,jdbcType=VARCHAR},",
          "primState = #{primstate,jdbcType=VARCHAR},",
          "primPostalCode = #{primpostalcode,jdbcType=VARCHAR},",
          "primCountry = #{primcountry,jdbcType=VARCHAR},",
          "title = #{title,jdbcType=VARCHAR},",
          "assistantPhone = #{assistantphone,jdbcType=VARCHAR},",
          "email = #{email,jdbcType=VARCHAR},",
          "department = #{department,jdbcType=VARCHAR},",
          "createdTime = #{createdtime,jdbcType=TIMESTAMP},",
          "createdUser = #{createduser,jdbcType=VARCHAR},",
          "sAccountId = #{saccountid,jdbcType=INTEGER},",
          "assignUser = #{assignuser,jdbcType=VARCHAR},",
          "otherAddress = #{otheraddress,jdbcType=VARCHAR},",
          "otherCity = #{othercity,jdbcType=VARCHAR},",
          "otherState = #{otherstate,jdbcType=VARCHAR},",
          "otherPostalCode = #{otherpostalcode,jdbcType=VARCHAR},",
          "otherCountry = #{othercountry,jdbcType=VARCHAR},",
          "lastUpdatedTime = #{lastupdatedtime,jdbcType=TIMESTAMP},",
          "accountId = #{accountid,jdbcType=INTEGER},",
          "avatarId = #{avatarid,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Contact record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contact
     *
     * @mbg.generated Thu Oct 19 13:57:50 ICT 2017
     */
    Integer insertAndReturnKey(Contact value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contact
     *
     * @mbg.generated Thu Oct 19 13:57:50 ICT 2017
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contact
     *
     * @mbg.generated Thu Oct 19 13:57:50 ICT 2017
     */
    void massUpdateWithSession(@Param("record") Contact record, @Param("primaryKeys") List primaryKeys);
}