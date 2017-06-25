/**
 * This file is part of mycollab-services.
 *
 * mycollab-services is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-services is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-services.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.mycollab.module.crm.dao;

import com.mycollab.db.persistence.ICrudGenericDAO;
import com.mycollab.module.crm.domain.AccountLead;
import com.mycollab.module.crm.domain.AccountLeadExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@SuppressWarnings({ "ucd", "rawtypes" })
public interface AccountLeadMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_accounts_leads
     *
     * @mbg.generated Sun Jun 25 11:59:45 ICT 2017
     */
    long countByExample(AccountLeadExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_accounts_leads
     *
     * @mbg.generated Sun Jun 25 11:59:45 ICT 2017
     */
    int deleteByExample(AccountLeadExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_accounts_leads
     *
     * @mbg.generated Sun Jun 25 11:59:45 ICT 2017
     */
    @Delete({
        "delete from m_crm_accounts_leads",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_accounts_leads
     *
     * @mbg.generated Sun Jun 25 11:59:45 ICT 2017
     */
    @Insert({
        "insert into m_crm_accounts_leads (id, accountId, ",
        "leadId, createTime, ",
        "isConvertRel)",
        "values (#{id,jdbcType=INTEGER}, #{accountid,jdbcType=INTEGER}, ",
        "#{leadid,jdbcType=INTEGER}, #{createtime,jdbcType=TIMESTAMP}, ",
        "#{isconvertrel,jdbcType=BIT})"
    })
    int insert(AccountLead record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_accounts_leads
     *
     * @mbg.generated Sun Jun 25 11:59:45 ICT 2017
     */
    int insertSelective(AccountLead record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_accounts_leads
     *
     * @mbg.generated Sun Jun 25 11:59:45 ICT 2017
     */
    List<AccountLead> selectByExample(AccountLeadExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_accounts_leads
     *
     * @mbg.generated Sun Jun 25 11:59:45 ICT 2017
     */
    @Select({
        "select",
        "id, accountId, leadId, createTime, isConvertRel",
        "from m_crm_accounts_leads",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("com.mycollab.module.crm.dao.AccountLeadMapper.BaseResultMap")
    AccountLead selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_accounts_leads
     *
     * @mbg.generated Sun Jun 25 11:59:45 ICT 2017
     */
    int updateByExampleSelective(@Param("record") AccountLead record, @Param("example") AccountLeadExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_accounts_leads
     *
     * @mbg.generated Sun Jun 25 11:59:45 ICT 2017
     */
    int updateByExample(@Param("record") AccountLead record, @Param("example") AccountLeadExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_accounts_leads
     *
     * @mbg.generated Sun Jun 25 11:59:45 ICT 2017
     */
    int updateByPrimaryKeySelective(AccountLead record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_accounts_leads
     *
     * @mbg.generated Sun Jun 25 11:59:45 ICT 2017
     */
    @Update({
        "update m_crm_accounts_leads",
        "set accountId = #{accountid,jdbcType=INTEGER},",
          "leadId = #{leadid,jdbcType=INTEGER},",
          "createTime = #{createtime,jdbcType=TIMESTAMP},",
          "isConvertRel = #{isconvertrel,jdbcType=BIT}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(AccountLead record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_accounts_leads
     *
     * @mbg.generated Sun Jun 25 11:59:45 ICT 2017
     */
    Integer insertAndReturnKey(AccountLead value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_accounts_leads
     *
     * @mbg.generated Sun Jun 25 11:59:45 ICT 2017
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_accounts_leads
     *
     * @mbg.generated Sun Jun 25 11:59:45 ICT 2017
     */
    void massUpdateWithSession(@Param("record") AccountLead record, @Param("primaryKeys") List primaryKeys);
}