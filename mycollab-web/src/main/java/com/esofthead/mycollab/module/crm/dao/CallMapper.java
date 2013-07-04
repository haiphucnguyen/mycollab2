package com.esofthead.mycollab.module.crm.dao;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.module.crm.domain.CallExample;
import com.esofthead.mycollab.module.crm.domain.CallWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CallMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_call
     *
     * @mbggenerated Wed Jul 03 17:12:30 GMT+07:00 2013
     */
    int countByExample(CallExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_call
     *
     * @mbggenerated Wed Jul 03 17:12:30 GMT+07:00 2013
     */
    int deleteByExample(CallExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_call
     *
     * @mbggenerated Wed Jul 03 17:12:30 GMT+07:00 2013
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_call
     *
     * @mbggenerated Wed Jul 03 17:12:30 GMT+07:00 2013
     */
    int insert(CallWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_call
     *
     * @mbggenerated Wed Jul 03 17:12:30 GMT+07:00 2013
     */
    int insertSelective(CallWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_call
     *
     * @mbggenerated Wed Jul 03 17:12:30 GMT+07:00 2013
     */
    List<CallWithBLOBs> selectByExampleWithBLOBs(CallExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_call
     *
     * @mbggenerated Wed Jul 03 17:12:30 GMT+07:00 2013
     */
    CallWithBLOBs selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_call
     *
     * @mbggenerated Wed Jul 03 17:12:30 GMT+07:00 2013
     */
    int updateByExampleSelective(@Param("record") CallWithBLOBs record, @Param("example") CallExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_call
     *
     * @mbggenerated Wed Jul 03 17:12:30 GMT+07:00 2013
     */
    int updateByExampleWithBLOBs(@Param("record") CallWithBLOBs record, @Param("example") CallExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_call
     *
     * @mbggenerated Wed Jul 03 17:12:30 GMT+07:00 2013
     */
    int updateByPrimaryKeySelective(CallWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_call
     *
     * @mbggenerated Wed Jul 03 17:12:30 GMT+07:00 2013
     */
    Integer insertAndReturnKey(CallWithBLOBs value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_call
     *
     * @mbggenerated Wed Jul 03 17:12:30 GMT+07:00 2013
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_call
     *
     * @mbggenerated Wed Jul 03 17:12:30 GMT+07:00 2013
     */
    void massUpdateWithSession(@Param("record") CallWithBLOBs record, @Param("primaryKeys") List primaryKeys);
}