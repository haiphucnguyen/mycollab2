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
package com.esofthead.mycollab.common.dao;

import com.esofthead.mycollab.common.domain.AuditLog;
import com.esofthead.mycollab.common.domain.AuditLogExample;
import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@SuppressWarnings({ "ucd", "rawtypes" })
public interface AuditLogMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_audit_log
     *
     * @mbggenerated Thu Jun 30 10:50:02 ICT 2016
     */
    int countByExample(AuditLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_audit_log
     *
     * @mbggenerated Thu Jun 30 10:50:02 ICT 2016
     */
    int deleteByExample(AuditLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_audit_log
     *
     * @mbggenerated Thu Jun 30 10:50:02 ICT 2016
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_audit_log
     *
     * @mbggenerated Thu Jun 30 10:50:02 ICT 2016
     */
    int insert(AuditLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_audit_log
     *
     * @mbggenerated Thu Jun 30 10:50:02 ICT 2016
     */
    int insertSelective(AuditLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_audit_log
     *
     * @mbggenerated Thu Jun 30 10:50:02 ICT 2016
     */
    List<AuditLog> selectByExampleWithBLOBs(AuditLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_audit_log
     *
     * @mbggenerated Thu Jun 30 10:50:02 ICT 2016
     */
    List<AuditLog> selectByExample(AuditLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_audit_log
     *
     * @mbggenerated Thu Jun 30 10:50:02 ICT 2016
     */
    AuditLog selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_audit_log
     *
     * @mbggenerated Thu Jun 30 10:50:02 ICT 2016
     */
    int updateByExampleSelective(@Param("record") AuditLog record, @Param("example") AuditLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_audit_log
     *
     * @mbggenerated Thu Jun 30 10:50:02 ICT 2016
     */
    int updateByExampleWithBLOBs(@Param("record") AuditLog record, @Param("example") AuditLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_audit_log
     *
     * @mbggenerated Thu Jun 30 10:50:02 ICT 2016
     */
    int updateByExample(@Param("record") AuditLog record, @Param("example") AuditLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_audit_log
     *
     * @mbggenerated Thu Jun 30 10:50:02 ICT 2016
     */
    int updateByPrimaryKeySelective(AuditLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_audit_log
     *
     * @mbggenerated Thu Jun 30 10:50:02 ICT 2016
     */
    int updateByPrimaryKeyWithBLOBs(AuditLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_audit_log
     *
     * @mbggenerated Thu Jun 30 10:50:02 ICT 2016
     */
    int updateByPrimaryKey(AuditLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_audit_log
     *
     * @mbggenerated Thu Jun 30 10:50:02 ICT 2016
     */
    Integer insertAndReturnKey(AuditLog value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_audit_log
     *
     * @mbggenerated Thu Jun 30 10:50:02 ICT 2016
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_audit_log
     *
     * @mbggenerated Thu Jun 30 10:50:02 ICT 2016
     */
    void massUpdateWithSession(@Param("record") AuditLog record, @Param("primaryKeys") List primaryKeys);
}