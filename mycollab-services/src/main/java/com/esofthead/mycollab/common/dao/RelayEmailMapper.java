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

import com.esofthead.mycollab.common.domain.RelayEmailExample;
import com.esofthead.mycollab.common.domain.RelayEmailWithBLOBs;
import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@SuppressWarnings({ "ucd", "rawtypes" })
public interface RelayEmailMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_relay_mail
     *
     * @mbggenerated Tue Mar 15 17:20:44 ICT 2016
     */
    int countByExample(RelayEmailExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_relay_mail
     *
     * @mbggenerated Tue Mar 15 17:20:44 ICT 2016
     */
    int deleteByExample(RelayEmailExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_relay_mail
     *
     * @mbggenerated Tue Mar 15 17:20:44 ICT 2016
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_relay_mail
     *
     * @mbggenerated Tue Mar 15 17:20:44 ICT 2016
     */
    int insert(RelayEmailWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_relay_mail
     *
     * @mbggenerated Tue Mar 15 17:20:44 ICT 2016
     */
    int insertSelective(RelayEmailWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_relay_mail
     *
     * @mbggenerated Tue Mar 15 17:20:44 ICT 2016
     */
    List<RelayEmailWithBLOBs> selectByExampleWithBLOBs(RelayEmailExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_relay_mail
     *
     * @mbggenerated Tue Mar 15 17:20:44 ICT 2016
     */
    RelayEmailWithBLOBs selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_relay_mail
     *
     * @mbggenerated Tue Mar 15 17:20:44 ICT 2016
     */
    int updateByExampleSelective(@Param("record") RelayEmailWithBLOBs record, @Param("example") RelayEmailExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_relay_mail
     *
     * @mbggenerated Tue Mar 15 17:20:44 ICT 2016
     */
    int updateByExampleWithBLOBs(@Param("record") RelayEmailWithBLOBs record, @Param("example") RelayEmailExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_relay_mail
     *
     * @mbggenerated Tue Mar 15 17:20:44 ICT 2016
     */
    int updateByPrimaryKeySelective(RelayEmailWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_relay_mail
     *
     * @mbggenerated Tue Mar 15 17:20:44 ICT 2016
     */
    int updateByPrimaryKeyWithBLOBs(RelayEmailWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_relay_mail
     *
     * @mbggenerated Tue Mar 15 17:20:44 ICT 2016
     */
    Integer insertAndReturnKey(RelayEmailWithBLOBs value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_relay_mail
     *
     * @mbggenerated Tue Mar 15 17:20:44 ICT 2016
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_relay_mail
     *
     * @mbggenerated Tue Mar 15 17:20:44 ICT 2016
     */
    void massUpdateWithSession(@Param("record") RelayEmailWithBLOBs record, @Param("primaryKeys") List primaryKeys);
}