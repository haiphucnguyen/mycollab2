package com.esofthead.mycollab.module.crm.dao;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.module.crm.domain.Call;
import com.esofthead.mycollab.module.crm.domain.CallExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CallMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_call
     *
     * @mbggenerated Sun Dec 23 17:29:20 GMT+07:00 2012
     */
    int countByExample(CallExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_call
     *
     * @mbggenerated Sun Dec 23 17:29:20 GMT+07:00 2012
     */
    int deleteByExample(CallExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_call
     *
     * @mbggenerated Sun Dec 23 17:29:20 GMT+07:00 2012
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_call
     *
     * @mbggenerated Sun Dec 23 17:29:20 GMT+07:00 2012
     */
    int insert(Call record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_call
     *
     * @mbggenerated Sun Dec 23 17:29:20 GMT+07:00 2012
     */
    int insertSelective(Call record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_call
     *
     * @mbggenerated Sun Dec 23 17:29:20 GMT+07:00 2012
     */
    List<Call> selectByExample(CallExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_call
     *
     * @mbggenerated Sun Dec 23 17:29:20 GMT+07:00 2012
     */
    Call selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_call
     *
     * @mbggenerated Sun Dec 23 17:29:20 GMT+07:00 2012
     */
    int updateByExampleSelective(@Param("record") Call record, @Param("example") CallExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_call
     *
     * @mbggenerated Sun Dec 23 17:29:20 GMT+07:00 2012
     */
    int updateByExample(@Param("record") Call record, @Param("example") CallExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_call
     *
     * @mbggenerated Sun Dec 23 17:29:20 GMT+07:00 2012
     */
    int updateByPrimaryKeySelective(Call record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_call
     *
     * @mbggenerated Sun Dec 23 17:29:20 GMT+07:00 2012
     */
    int updateByPrimaryKey(Call record);

    Integer insertAndReturnKey(Call value);
}