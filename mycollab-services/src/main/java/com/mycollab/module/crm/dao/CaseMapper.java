/**
 * Copyright © MyCollab
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.mycollab.module.crm.dao;

import com.mycollab.db.persistence.ICrudGenericDAO;
import com.mycollab.module.crm.domain.CaseExample;
import com.mycollab.module.crm.domain.CaseWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@SuppressWarnings({ "ucd", "rawtypes" })
public interface CaseMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_case
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    long countByExample(CaseExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_case
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    int deleteByExample(CaseExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_case
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    @Delete({
        "delete from m_crm_case",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_case
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    @Insert({
        "insert into m_crm_case (id, priority, ",
        "status, type, subject, ",
        "accountId, createdTime, ",
        "createdUser, sAccountId, ",
        "assignUser, lastUpdatedTime, ",
        "reason, origin, ",
        "email, phonenumber, ",
        "description, resolution)",
        "values (#{id,jdbcType=INTEGER}, #{priority,jdbcType=VARCHAR}, ",
        "#{status,jdbcType=VARCHAR}, #{type,jdbcType=VARCHAR}, #{subject,jdbcType=VARCHAR}, ",
        "#{accountid,jdbcType=INTEGER}, #{createdtime,jdbcType=TIMESTAMP}, ",
        "#{createduser,jdbcType=VARCHAR}, #{saccountid,jdbcType=INTEGER}, ",
        "#{assignuser,jdbcType=VARCHAR}, #{lastupdatedtime,jdbcType=TIMESTAMP}, ",
        "#{reason,jdbcType=VARCHAR}, #{origin,jdbcType=VARCHAR}, ",
        "#{email,jdbcType=VARCHAR}, #{phonenumber,jdbcType=VARCHAR}, ",
        "#{description,jdbcType=LONGVARCHAR}, #{resolution,jdbcType=LONGVARCHAR})"
    })
    int insert(CaseWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_case
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    int insertSelective(CaseWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_case
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    List<CaseWithBLOBs> selectByExampleWithBLOBs(CaseExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_case
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    @Select({
        "select",
        "id, priority, status, type, subject, accountId, createdTime, createdUser, sAccountId, ",
        "assignUser, lastUpdatedTime, reason, origin, email, phonenumber, description, ",
        "resolution",
        "from m_crm_case",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("com.mycollab.module.crm.dao.CaseMapper.ResultMapWithBLOBs")
    CaseWithBLOBs selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_case
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    int updateByExampleSelective(@Param("record") CaseWithBLOBs record, @Param("example") CaseExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_case
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    int updateByExampleWithBLOBs(@Param("record") CaseWithBLOBs record, @Param("example") CaseExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_case
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    int updateByPrimaryKeySelective(CaseWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_case
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    @Update({
        "update m_crm_case",
        "set priority = #{priority,jdbcType=VARCHAR},",
          "status = #{status,jdbcType=VARCHAR},",
          "type = #{type,jdbcType=VARCHAR},",
          "subject = #{subject,jdbcType=VARCHAR},",
          "accountId = #{accountid,jdbcType=INTEGER},",
          "createdTime = #{createdtime,jdbcType=TIMESTAMP},",
          "createdUser = #{createduser,jdbcType=VARCHAR},",
          "sAccountId = #{saccountid,jdbcType=INTEGER},",
          "assignUser = #{assignuser,jdbcType=VARCHAR},",
          "lastUpdatedTime = #{lastupdatedtime,jdbcType=TIMESTAMP},",
          "reason = #{reason,jdbcType=VARCHAR},",
          "origin = #{origin,jdbcType=VARCHAR},",
          "email = #{email,jdbcType=VARCHAR},",
          "phonenumber = #{phonenumber,jdbcType=VARCHAR},",
          "description = #{description,jdbcType=LONGVARCHAR},",
          "resolution = #{resolution,jdbcType=LONGVARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKeyWithBLOBs(CaseWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_case
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    Integer insertAndReturnKey(CaseWithBLOBs value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_case
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_case
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    void massUpdateWithSession(@Param("record") CaseWithBLOBs record, @Param("primaryKeys") List primaryKeys);
}