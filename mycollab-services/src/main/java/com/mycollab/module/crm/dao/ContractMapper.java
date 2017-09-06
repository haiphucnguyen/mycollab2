package com.mycollab.module.crm.dao;

import com.mycollab.db.persistence.ICrudGenericDAO;
import com.mycollab.module.crm.domain.Contract;
import com.mycollab.module.crm.domain.ContractExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@SuppressWarnings({ "ucd", "rawtypes" })
public interface ContractMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contract
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    long countByExample(ContractExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contract
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    int deleteByExample(ContractExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contract
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    @Delete({
        "delete from m_crm_contract",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contract
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    @Insert({
        "insert into m_crm_contract (id, contractname, ",
        "status, code, accountid, ",
        "opportunityid, currencyid, ",
        "customersigneddate, companysigneddate, ",
        "type, description, ",
        "startdate, enddate, ",
        "contractvalue, createdTime, ",
        "createdUser, sAccountId, ",
        "assignUser, lastUpdatedTime)",
        "values (#{id,jdbcType=INTEGER}, #{contractname,jdbcType=VARCHAR}, ",
        "#{status,jdbcType=VARCHAR}, #{code,jdbcType=VARCHAR}, #{accountid,jdbcType=INTEGER}, ",
        "#{opportunityid,jdbcType=INTEGER}, #{currencyid,jdbcType=VARCHAR}, ",
        "#{customersigneddate,jdbcType=TIMESTAMP}, #{companysigneddate,jdbcType=TIMESTAMP}, ",
        "#{type,jdbcType=VARCHAR}, #{description,jdbcType=VARCHAR}, ",
        "#{startdate,jdbcType=TIMESTAMP}, #{enddate,jdbcType=TIMESTAMP}, ",
        "#{contractvalue,jdbcType=DECIMAL}, #{createdtime,jdbcType=TIMESTAMP}, ",
        "#{createduser,jdbcType=VARCHAR}, #{saccountid,jdbcType=INTEGER}, ",
        "#{assignuser,jdbcType=VARCHAR}, #{lastupdatedtime,jdbcType=TIMESTAMP})"
    })
    int insert(Contract record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contract
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    int insertSelective(Contract record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contract
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    List<Contract> selectByExample(ContractExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contract
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    @Select({
        "select",
        "id, contractname, status, code, accountid, opportunityid, currencyid, customersigneddate, ",
        "companysigneddate, type, description, startdate, enddate, contractvalue, createdTime, ",
        "createdUser, sAccountId, assignUser, lastUpdatedTime",
        "from m_crm_contract",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("com.mycollab.module.crm.dao.ContractMapper.BaseResultMap")
    Contract selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contract
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    int updateByExampleSelective(@Param("record") Contract record, @Param("example") ContractExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contract
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    int updateByExample(@Param("record") Contract record, @Param("example") ContractExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contract
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    int updateByPrimaryKeySelective(Contract record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contract
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    @Update({
        "update m_crm_contract",
        "set contractname = #{contractname,jdbcType=VARCHAR},",
          "status = #{status,jdbcType=VARCHAR},",
          "code = #{code,jdbcType=VARCHAR},",
          "accountid = #{accountid,jdbcType=INTEGER},",
          "opportunityid = #{opportunityid,jdbcType=INTEGER},",
          "currencyid = #{currencyid,jdbcType=VARCHAR},",
          "customersigneddate = #{customersigneddate,jdbcType=TIMESTAMP},",
          "companysigneddate = #{companysigneddate,jdbcType=TIMESTAMP},",
          "type = #{type,jdbcType=VARCHAR},",
          "description = #{description,jdbcType=VARCHAR},",
          "startdate = #{startdate,jdbcType=TIMESTAMP},",
          "enddate = #{enddate,jdbcType=TIMESTAMP},",
          "contractvalue = #{contractvalue,jdbcType=DECIMAL},",
          "createdTime = #{createdtime,jdbcType=TIMESTAMP},",
          "createdUser = #{createduser,jdbcType=VARCHAR},",
          "sAccountId = #{saccountid,jdbcType=INTEGER},",
          "assignUser = #{assignuser,jdbcType=VARCHAR},",
          "lastUpdatedTime = #{lastupdatedtime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Contract record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contract
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    Integer insertAndReturnKey(Contract value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contract
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contract
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    void massUpdateWithSession(@Param("record") Contract record, @Param("primaryKeys") List primaryKeys);
}