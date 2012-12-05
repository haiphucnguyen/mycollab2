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
     * @mbggenerated Wed Dec 05 18:14:03 GMT+07:00 2012
     */
    int countByExample(ContractExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contract
     *
     * @mbggenerated Wed Dec 05 18:14:03 GMT+07:00 2012
     */
    int deleteByExample(ContractExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contract
     *
     * @mbggenerated Wed Dec 05 18:14:03 GMT+07:00 2012
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contract
     *
     * @mbggenerated Wed Dec 05 18:14:03 GMT+07:00 2012
     */
    int insert(Contract record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contract
     *
     * @mbggenerated Wed Dec 05 18:14:03 GMT+07:00 2012
     */
    int insertSelective(Contract record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contract
     *
     * @mbggenerated Wed Dec 05 18:14:03 GMT+07:00 2012
     */
    List<Contract> selectByExample(ContractExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contract
     *
     * @mbggenerated Wed Dec 05 18:14:03 GMT+07:00 2012
     */
    Contract selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contract
     *
     * @mbggenerated Wed Dec 05 18:14:03 GMT+07:00 2012
     */
    int updateByExampleSelective(@Param("record") Contract record, @Param("example") ContractExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contract
     *
     * @mbggenerated Wed Dec 05 18:14:03 GMT+07:00 2012
     */
    int updateByExample(@Param("record") Contract record, @Param("example") ContractExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contract
     *
     * @mbggenerated Wed Dec 05 18:14:03 GMT+07:00 2012
     */
    int updateByPrimaryKeySelective(Contract record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contract
     *
     * @mbggenerated Wed Dec 05 18:14:03 GMT+07:00 2012
     */
    int updateByPrimaryKey(Contract record);
}