package com.esofthead.mycollab.module.crm.dao;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.module.crm.domain.Contract;
import com.esofthead.mycollab.module.crm.domain.ContractExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ContractMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contract
     *
     * @mbggenerated Mon Apr 01 22:06:54 GMT+07:00 2013
     */
    int countByExample(ContractExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contract
     *
     * @mbggenerated Mon Apr 01 22:06:54 GMT+07:00 2013
     */
    int deleteByExample(ContractExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contract
     *
     * @mbggenerated Mon Apr 01 22:06:54 GMT+07:00 2013
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contract
     *
     * @mbggenerated Mon Apr 01 22:06:54 GMT+07:00 2013
     */
    int insert(Contract record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contract
     *
     * @mbggenerated Mon Apr 01 22:06:54 GMT+07:00 2013
     */
    int insertSelective(Contract record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contract
     *
     * @mbggenerated Mon Apr 01 22:06:54 GMT+07:00 2013
     */
    List<Contract> selectByExample(ContractExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contract
     *
     * @mbggenerated Mon Apr 01 22:06:54 GMT+07:00 2013
     */
    Contract selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contract
     *
     * @mbggenerated Mon Apr 01 22:06:54 GMT+07:00 2013
     */
    int updateByExampleSelective(@Param("record") Contract record, @Param("example") ContractExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contract
     *
     * @mbggenerated Mon Apr 01 22:06:54 GMT+07:00 2013
     */
    int updateByExample(@Param("record") Contract record, @Param("example") ContractExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contract
     *
     * @mbggenerated Mon Apr 01 22:06:54 GMT+07:00 2013
     */
    int updateByPrimaryKeySelective(Contract record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contract
     *
     * @mbggenerated Mon Apr 01 22:06:54 GMT+07:00 2013
     */
    Integer insertAndReturnKey(Contract value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contract
     *
     * @mbggenerated Mon Apr 01 22:06:54 GMT+07:00 2013
     */
    void removeKeysWithSession(List primaryKeys);
}