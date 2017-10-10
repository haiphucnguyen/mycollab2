/**
 * mycollab-services - Parent pom providing dependency and plugin management for applications
		built with Maven
 * Copyright © ${project.inceptionYear} MyCollab (support@mycollab.com)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.mycollab.module.crm.dao;

import com.mycollab.db.persistence.ICrudGenericDAO;
import com.mycollab.module.crm.domain.Account;
import com.mycollab.module.crm.domain.AccountExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@SuppressWarnings({ "ucd", "rawtypes" })
public interface AccountMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_account
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    long countByExample(AccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_account
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    int deleteByExample(AccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_account
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    @Delete({
        "delete from m_crm_account",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_account
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    @Insert({
        "insert into m_crm_account (id, accountName, ",
        "website, phoneOffice, ",
        "fax, alternatePhone, ",
        "annualRevenue, billingAddress, ",
        "city, postalCode, ",
        "state, email, ownership, ",
        "shippingAddress, shippingCity, ",
        "shippingPostalCode, shippingState, ",
        "numemployees, createdTime, ",
        "createdUser, sAccountId, ",
        "assignUser, type, ",
        "industry, lastUpdatedTime, ",
        "billingCountry, shippingCountry, ",
        "avatarId, description)",
        "values (#{id,jdbcType=INTEGER}, #{accountname,jdbcType=VARCHAR}, ",
        "#{website,jdbcType=VARCHAR}, #{phoneoffice,jdbcType=VARCHAR}, ",
        "#{fax,jdbcType=VARCHAR}, #{alternatephone,jdbcType=VARCHAR}, ",
        "#{annualrevenue,jdbcType=VARCHAR}, #{billingaddress,jdbcType=VARCHAR}, ",
        "#{city,jdbcType=VARCHAR}, #{postalcode,jdbcType=VARCHAR}, ",
        "#{state,jdbcType=VARCHAR}, #{email,jdbcType=VARCHAR}, #{ownership,jdbcType=VARCHAR}, ",
        "#{shippingaddress,jdbcType=VARCHAR}, #{shippingcity,jdbcType=VARCHAR}, ",
        "#{shippingpostalcode,jdbcType=VARCHAR}, #{shippingstate,jdbcType=VARCHAR}, ",
        "#{numemployees,jdbcType=INTEGER}, #{createdtime,jdbcType=TIMESTAMP}, ",
        "#{createduser,jdbcType=VARCHAR}, #{saccountid,jdbcType=INTEGER}, ",
        "#{assignuser,jdbcType=VARCHAR}, #{type,jdbcType=VARCHAR}, ",
        "#{industry,jdbcType=VARCHAR}, #{lastupdatedtime,jdbcType=TIMESTAMP}, ",
        "#{billingcountry,jdbcType=VARCHAR}, #{shippingcountry,jdbcType=VARCHAR}, ",
        "#{avatarid,jdbcType=VARCHAR}, #{description,jdbcType=LONGVARCHAR})"
    })
    int insert(Account record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_account
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    int insertSelective(Account record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_account
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    List<Account> selectByExampleWithBLOBs(AccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_account
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    List<Account> selectByExample(AccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_account
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    @Select({
        "select",
        "id, accountName, website, phoneOffice, fax, alternatePhone, annualRevenue, billingAddress, ",
        "city, postalCode, state, email, ownership, shippingAddress, shippingCity, shippingPostalCode, ",
        "shippingState, numemployees, createdTime, createdUser, sAccountId, assignUser, ",
        "type, industry, lastUpdatedTime, billingCountry, shippingCountry, avatarId, ",
        "description",
        "from m_crm_account",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("com.mycollab.module.crm.dao.AccountMapper.ResultMapWithBLOBs")
    Account selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_account
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    int updateByExampleSelective(@Param("record") Account record, @Param("example") AccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_account
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    int updateByExampleWithBLOBs(@Param("record") Account record, @Param("example") AccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_account
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    int updateByExample(@Param("record") Account record, @Param("example") AccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_account
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    int updateByPrimaryKeySelective(Account record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_account
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    @Update({
        "update m_crm_account",
        "set accountName = #{accountname,jdbcType=VARCHAR},",
          "website = #{website,jdbcType=VARCHAR},",
          "phoneOffice = #{phoneoffice,jdbcType=VARCHAR},",
          "fax = #{fax,jdbcType=VARCHAR},",
          "alternatePhone = #{alternatephone,jdbcType=VARCHAR},",
          "annualRevenue = #{annualrevenue,jdbcType=VARCHAR},",
          "billingAddress = #{billingaddress,jdbcType=VARCHAR},",
          "city = #{city,jdbcType=VARCHAR},",
          "postalCode = #{postalcode,jdbcType=VARCHAR},",
          "state = #{state,jdbcType=VARCHAR},",
          "email = #{email,jdbcType=VARCHAR},",
          "ownership = #{ownership,jdbcType=VARCHAR},",
          "shippingAddress = #{shippingaddress,jdbcType=VARCHAR},",
          "shippingCity = #{shippingcity,jdbcType=VARCHAR},",
          "shippingPostalCode = #{shippingpostalcode,jdbcType=VARCHAR},",
          "shippingState = #{shippingstate,jdbcType=VARCHAR},",
          "numemployees = #{numemployees,jdbcType=INTEGER},",
          "createdTime = #{createdtime,jdbcType=TIMESTAMP},",
          "createdUser = #{createduser,jdbcType=VARCHAR},",
          "sAccountId = #{saccountid,jdbcType=INTEGER},",
          "assignUser = #{assignuser,jdbcType=VARCHAR},",
          "type = #{type,jdbcType=VARCHAR},",
          "industry = #{industry,jdbcType=VARCHAR},",
          "lastUpdatedTime = #{lastupdatedtime,jdbcType=TIMESTAMP},",
          "billingCountry = #{billingcountry,jdbcType=VARCHAR},",
          "shippingCountry = #{shippingcountry,jdbcType=VARCHAR},",
          "avatarId = #{avatarid,jdbcType=VARCHAR},",
          "description = #{description,jdbcType=LONGVARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKeyWithBLOBs(Account record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_account
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    @Update({
        "update m_crm_account",
        "set accountName = #{accountname,jdbcType=VARCHAR},",
          "website = #{website,jdbcType=VARCHAR},",
          "phoneOffice = #{phoneoffice,jdbcType=VARCHAR},",
          "fax = #{fax,jdbcType=VARCHAR},",
          "alternatePhone = #{alternatephone,jdbcType=VARCHAR},",
          "annualRevenue = #{annualrevenue,jdbcType=VARCHAR},",
          "billingAddress = #{billingaddress,jdbcType=VARCHAR},",
          "city = #{city,jdbcType=VARCHAR},",
          "postalCode = #{postalcode,jdbcType=VARCHAR},",
          "state = #{state,jdbcType=VARCHAR},",
          "email = #{email,jdbcType=VARCHAR},",
          "ownership = #{ownership,jdbcType=VARCHAR},",
          "shippingAddress = #{shippingaddress,jdbcType=VARCHAR},",
          "shippingCity = #{shippingcity,jdbcType=VARCHAR},",
          "shippingPostalCode = #{shippingpostalcode,jdbcType=VARCHAR},",
          "shippingState = #{shippingstate,jdbcType=VARCHAR},",
          "numemployees = #{numemployees,jdbcType=INTEGER},",
          "createdTime = #{createdtime,jdbcType=TIMESTAMP},",
          "createdUser = #{createduser,jdbcType=VARCHAR},",
          "sAccountId = #{saccountid,jdbcType=INTEGER},",
          "assignUser = #{assignuser,jdbcType=VARCHAR},",
          "type = #{type,jdbcType=VARCHAR},",
          "industry = #{industry,jdbcType=VARCHAR},",
          "lastUpdatedTime = #{lastupdatedtime,jdbcType=TIMESTAMP},",
          "billingCountry = #{billingcountry,jdbcType=VARCHAR},",
          "shippingCountry = #{shippingcountry,jdbcType=VARCHAR},",
          "avatarId = #{avatarid,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Account record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_account
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    Integer insertAndReturnKey(Account value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_account
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_account
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    void massUpdateWithSession(@Param("record") Account record, @Param("primaryKeys") List primaryKeys);
}