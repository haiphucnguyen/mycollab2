package com.esofthead.mycollab.common.dao;

import com.esofthead.mycollab.common.domain.AuditLog;
import com.esofthead.mycollab.common.domain.AuditLogExample;
import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AuditLogMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_audit_log
     *
     * @mbggenerated Fri Jan 04 15:36:04 GMT+07:00 2013
     */
    int countByExample(AuditLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_audit_log
     *
     * @mbggenerated Fri Jan 04 15:36:04 GMT+07:00 2013
     */
    int deleteByExample(AuditLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_audit_log
     *
     * @mbggenerated Fri Jan 04 15:36:04 GMT+07:00 2013
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_audit_log
     *
     * @mbggenerated Fri Jan 04 15:36:04 GMT+07:00 2013
     */
    int insert(AuditLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_audit_log
     *
     * @mbggenerated Fri Jan 04 15:36:04 GMT+07:00 2013
     */
    int insertSelective(AuditLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_audit_log
     *
     * @mbggenerated Fri Jan 04 15:36:04 GMT+07:00 2013
     */
    List<AuditLog> selectByExampleWithBLOBs(AuditLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_audit_log
     *
     * @mbggenerated Fri Jan 04 15:36:04 GMT+07:00 2013
     */
    List<AuditLog> selectByExample(AuditLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_audit_log
     *
     * @mbggenerated Fri Jan 04 15:36:04 GMT+07:00 2013
     */
    AuditLog selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_audit_log
     *
     * @mbggenerated Fri Jan 04 15:36:04 GMT+07:00 2013
     */
    int updateByExampleSelective(@Param("record") AuditLog record, @Param("example") AuditLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_audit_log
     *
     * @mbggenerated Fri Jan 04 15:36:04 GMT+07:00 2013
     */
    int updateByExampleWithBLOBs(@Param("record") AuditLog record, @Param("example") AuditLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_audit_log
     *
     * @mbggenerated Fri Jan 04 15:36:04 GMT+07:00 2013
     */
    int updateByExample(@Param("record") AuditLog record, @Param("example") AuditLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_audit_log
     *
     * @mbggenerated Fri Jan 04 15:36:04 GMT+07:00 2013
     */
    int updateByPrimaryKeySelective(AuditLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_audit_log
     *
     * @mbggenerated Fri Jan 04 15:36:04 GMT+07:00 2013
     */
    int updateByPrimaryKeyWithBLOBs(AuditLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_audit_log
     *
     * @mbggenerated Fri Jan 04 15:36:04 GMT+07:00 2013
     */
    int updateByPrimaryKey(AuditLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_audit_log
     *
     * @mbggenerated Fri Jan 04 15:36:04 GMT+07:00 2013
     */
    Integer insertAndReturnKey(AuditLog value);
}