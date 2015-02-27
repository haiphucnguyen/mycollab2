package com.esofthead.mycollab.module.crm.dao;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.module.crm.domain.CallExample;
import com.esofthead.mycollab.module.crm.domain.CallWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@SuppressWarnings({ "ucd", "rawtypes" })
public interface CallMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_call
     *
     * @mbggenerated Fri Feb 27 09:55:24 ICT 2015
     */
    int countByExample(CallExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_call
     *
     * @mbggenerated Fri Feb 27 09:55:24 ICT 2015
     */
    int deleteByExample(CallExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_call
     *
     * @mbggenerated Fri Feb 27 09:55:24 ICT 2015
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_call
     *
     * @mbggenerated Fri Feb 27 09:55:24 ICT 2015
     */
    int insert(CallWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_call
     *
     * @mbggenerated Fri Feb 27 09:55:24 ICT 2015
     */
    int insertSelective(CallWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_call
     *
     * @mbggenerated Fri Feb 27 09:55:24 ICT 2015
     */
    List<CallWithBLOBs> selectByExampleWithBLOBs(CallExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_call
     *
     * @mbggenerated Fri Feb 27 09:55:24 ICT 2015
     */
    CallWithBLOBs selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_call
     *
     * @mbggenerated Fri Feb 27 09:55:24 ICT 2015
     */
    int updateByExampleSelective(@Param("record") CallWithBLOBs record, @Param("example") CallExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_call
     *
     * @mbggenerated Fri Feb 27 09:55:24 ICT 2015
     */
    int updateByExampleWithBLOBs(@Param("record") CallWithBLOBs record, @Param("example") CallExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_call
     *
     * @mbggenerated Fri Feb 27 09:55:24 ICT 2015
     */
    int updateByPrimaryKeySelective(CallWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_call
     *
     * @mbggenerated Fri Feb 27 09:55:24 ICT 2015
     */
    int updateByPrimaryKeyWithBLOBs(CallWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_call
     *
     * @mbggenerated Fri Feb 27 09:55:24 ICT 2015
     */
    Integer insertAndReturnKey(CallWithBLOBs value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_call
     *
     * @mbggenerated Fri Feb 27 09:55:24 ICT 2015
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_call
     *
     * @mbggenerated Fri Feb 27 09:55:24 ICT 2015
     */
    void massUpdateWithSession(@Param("record") CallWithBLOBs record, @Param("primaryKeys") List primaryKeys);
}