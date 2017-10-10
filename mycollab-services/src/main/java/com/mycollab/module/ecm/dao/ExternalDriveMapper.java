/**
 * mycollab-services - Parent pom providing dependency and plugin management for applications
		built with Maven
 * Copyright © ${project.inceptionYear} MyCollab (support@mycollab.com)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.mycollab.module.ecm.dao;

import com.mycollab.db.persistence.ICrudGenericDAO;
import com.mycollab.module.ecm.domain.ExternalDrive;
import com.mycollab.module.ecm.domain.ExternalDriveExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@SuppressWarnings({ "ucd", "rawtypes" })
public interface ExternalDriveMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_ecm_external_drive
     *
     * @mbg.generated Tue Aug 01 11:17:42 ICT 2017
     */
    long countByExample(ExternalDriveExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_ecm_external_drive
     *
     * @mbg.generated Tue Aug 01 11:17:42 ICT 2017
     */
    int deleteByExample(ExternalDriveExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_ecm_external_drive
     *
     * @mbg.generated Tue Aug 01 11:17:42 ICT 2017
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_ecm_external_drive
     *
     * @mbg.generated Tue Aug 01 11:17:42 ICT 2017
     */
    int insert(ExternalDrive record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_ecm_external_drive
     *
     * @mbg.generated Tue Aug 01 11:17:42 ICT 2017
     */
    int insertSelective(ExternalDrive record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_ecm_external_drive
     *
     * @mbg.generated Tue Aug 01 11:17:42 ICT 2017
     */
    List<ExternalDrive> selectByExample(ExternalDriveExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_ecm_external_drive
     *
     * @mbg.generated Tue Aug 01 11:17:42 ICT 2017
     */
    ExternalDrive selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_ecm_external_drive
     *
     * @mbg.generated Tue Aug 01 11:17:42 ICT 2017
     */
    int updateByExampleSelective(@Param("record") ExternalDrive record, @Param("example") ExternalDriveExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_ecm_external_drive
     *
     * @mbg.generated Tue Aug 01 11:17:42 ICT 2017
     */
    int updateByExample(@Param("record") ExternalDrive record, @Param("example") ExternalDriveExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_ecm_external_drive
     *
     * @mbg.generated Tue Aug 01 11:17:42 ICT 2017
     */
    int updateByPrimaryKeySelective(ExternalDrive record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_ecm_external_drive
     *
     * @mbg.generated Tue Aug 01 11:17:42 ICT 2017
     */
    int updateByPrimaryKey(ExternalDrive record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_ecm_external_drive
     *
     * @mbg.generated Tue Aug 01 11:17:42 ICT 2017
     */
    Integer insertAndReturnKey(ExternalDrive value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_ecm_external_drive
     *
     * @mbg.generated Tue Aug 01 11:17:42 ICT 2017
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_ecm_external_drive
     *
     * @mbg.generated Tue Aug 01 11:17:42 ICT 2017
     */
    void massUpdateWithSession(@Param("record") ExternalDrive record, @Param("primaryKeys") List primaryKeys);
}