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
package com.esofthead.mycollab.module.user.dao;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.module.user.domain.UserInformation;
import com.esofthead.mycollab.module.user.domain.UserInformationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@SuppressWarnings({ "ucd", "rawtypes" })
public interface UserInformationMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_information
     *
     * @mbggenerated Fri Mar 06 17:04:12 ICT 2015
     */
    int countByExample(UserInformationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_information
     *
     * @mbggenerated Fri Mar 06 17:04:12 ICT 2015
     */
    int deleteByExample(UserInformationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_information
     *
     * @mbggenerated Fri Mar 06 17:04:12 ICT 2015
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_information
     *
     * @mbggenerated Fri Mar 06 17:04:12 ICT 2015
     */
    int insert(UserInformation record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_information
     *
     * @mbggenerated Fri Mar 06 17:04:12 ICT 2015
     */
    int insertSelective(UserInformation record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_information
     *
     * @mbggenerated Fri Mar 06 17:04:12 ICT 2015
     */
    List<UserInformation> selectByExample(UserInformationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_information
     *
     * @mbggenerated Fri Mar 06 17:04:12 ICT 2015
     */
    UserInformation selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_information
     *
     * @mbggenerated Fri Mar 06 17:04:12 ICT 2015
     */
    int updateByExampleSelective(@Param("record") UserInformation record, @Param("example") UserInformationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_information
     *
     * @mbggenerated Fri Mar 06 17:04:12 ICT 2015
     */
    int updateByExample(@Param("record") UserInformation record, @Param("example") UserInformationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_information
     *
     * @mbggenerated Fri Mar 06 17:04:12 ICT 2015
     */
    int updateByPrimaryKeySelective(UserInformation record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_information
     *
     * @mbggenerated Fri Mar 06 17:04:12 ICT 2015
     */
    int updateByPrimaryKey(UserInformation record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_information
     *
     * @mbggenerated Fri Mar 06 17:04:12 ICT 2015
     */
    Integer insertAndReturnKey(UserInformation value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_information
     *
     * @mbggenerated Fri Mar 06 17:04:12 ICT 2015
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_information
     *
     * @mbggenerated Fri Mar 06 17:04:12 ICT 2015
     */
    void massUpdateWithSession(@Param("record") UserInformation record, @Param("primaryKeys") List primaryKeys);
}