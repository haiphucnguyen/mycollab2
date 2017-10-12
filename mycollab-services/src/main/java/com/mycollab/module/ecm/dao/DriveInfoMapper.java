/**
 * Copyright © MyCollab
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.mycollab.module.ecm.dao;

import com.mycollab.db.persistence.ICrudGenericDAO;
import com.mycollab.module.ecm.domain.DriveInfo;
import com.mycollab.module.ecm.domain.DriveInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@SuppressWarnings({ "ucd", "rawtypes" })
public interface DriveInfoMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_ecm_driveinfo
     *
     * @mbg.generated Tue Aug 01 11:17:42 ICT 2017
     */
    long countByExample(DriveInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_ecm_driveinfo
     *
     * @mbg.generated Tue Aug 01 11:17:42 ICT 2017
     */
    int deleteByExample(DriveInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_ecm_driveinfo
     *
     * @mbg.generated Tue Aug 01 11:17:42 ICT 2017
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_ecm_driveinfo
     *
     * @mbg.generated Tue Aug 01 11:17:42 ICT 2017
     */
    int insert(DriveInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_ecm_driveinfo
     *
     * @mbg.generated Tue Aug 01 11:17:42 ICT 2017
     */
    int insertSelective(DriveInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_ecm_driveinfo
     *
     * @mbg.generated Tue Aug 01 11:17:42 ICT 2017
     */
    List<DriveInfo> selectByExample(DriveInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_ecm_driveinfo
     *
     * @mbg.generated Tue Aug 01 11:17:42 ICT 2017
     */
    DriveInfo selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_ecm_driveinfo
     *
     * @mbg.generated Tue Aug 01 11:17:42 ICT 2017
     */
    int updateByExampleSelective(@Param("record") DriveInfo record, @Param("example") DriveInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_ecm_driveinfo
     *
     * @mbg.generated Tue Aug 01 11:17:42 ICT 2017
     */
    int updateByExample(@Param("record") DriveInfo record, @Param("example") DriveInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_ecm_driveinfo
     *
     * @mbg.generated Tue Aug 01 11:17:42 ICT 2017
     */
    int updateByPrimaryKeySelective(DriveInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_ecm_driveinfo
     *
     * @mbg.generated Tue Aug 01 11:17:42 ICT 2017
     */
    int updateByPrimaryKey(DriveInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_ecm_driveinfo
     *
     * @mbg.generated Tue Aug 01 11:17:42 ICT 2017
     */
    Integer insertAndReturnKey(DriveInfo value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_ecm_driveinfo
     *
     * @mbg.generated Tue Aug 01 11:17:42 ICT 2017
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_ecm_driveinfo
     *
     * @mbg.generated Tue Aug 01 11:17:42 ICT 2017
     */
    void massUpdateWithSession(@Param("record") DriveInfo record, @Param("primaryKeys") List primaryKeys);
}