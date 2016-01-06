package com.esofthead.mycollab.module.crm.dao;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.module.crm.domain.Contract;
import com.esofthead.mycollab.module.crm.domain.ContractExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@SuppressWarnings({ "ucd", "rawtypes" })
public interface ContractMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contract
     *
     * @mbggenerated Tue Jan 05 23:09:02 ICT 2016
     */
    int countByExample(ContractExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contract
     *
     * @mbggenerated Tue Jan 05 23:09:02 ICT 2016
     */
    int deleteByExample(ContractExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contract
     *
     * @mbggenerated Tue Jan 05 23:09:02 ICT 2016
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contract
     *
     * @mbggenerated Tue Jan 05 23:09:02 ICT 2016
     */
    int insert(Contract record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contract
     *
     * @mbggenerated Tue Jan 05 23:09:02 ICT 2016
     */
    int insertSelective(Contract record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contract
     *
     * @mbggenerated Tue Jan 05 23:09:02 ICT 2016
     */
    List<Contract> selectByExample(ContractExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contract
     *
     * @mbggenerated Tue Jan 05 23:09:02 ICT 2016
     */
    Contract selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contract
     *
     * @mbggenerated Tue Jan 05 23:09:02 ICT 2016
     */
    int updateByExampleSelective(@Param("record") Contract record, @Param("example") ContractExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contract
     *
     * @mbggenerated Tue Jan 05 23:09:02 ICT 2016
     */
    int updateByExample(@Param("record") Contract record, @Param("example") ContractExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contract
     *
     * @mbggenerated Tue Jan 05 23:09:02 ICT 2016
     */
    int updateByPrimaryKeySelective(Contract record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contract
     *
     * @mbggenerated Tue Jan 05 23:09:02 ICT 2016
     */
    int updateByPrimaryKey(Contract record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contract
     *
     * @mbggenerated Tue Jan 05 23:09:02 ICT 2016
     */
    Integer insertAndReturnKey(Contract value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contract
     *
     * @mbggenerated Tue Jan 05 23:09:02 ICT 2016
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_contract
     *
     * @mbggenerated Tue Jan 05 23:09:02 ICT 2016
     */
    void massUpdateWithSession(@Param("record") Contract record, @Param("primaryKeys") List primaryKeys);
}