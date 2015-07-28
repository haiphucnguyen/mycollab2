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
import com.esofthead.mycollab.module.user.domain.UserAccount;
import com.esofthead.mycollab.module.user.domain.UserAccountExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@SuppressWarnings({ "ucd", "rawtypes" })
public interface UserAccountMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_account
     *
     * @mbggenerated Tue Jul 28 15:35:23 ICT 2015
     */
    int countByExample(UserAccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_account
     *
     * @mbggenerated Tue Jul 28 15:35:23 ICT 2015
     */
    int deleteByExample(UserAccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_account
     *
     * @mbggenerated Tue Jul 28 15:35:23 ICT 2015
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_account
     *
     * @mbggenerated Tue Jul 28 15:35:23 ICT 2015
     */
    int insert(UserAccount record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_account
     *
     * @mbggenerated Tue Jul 28 15:35:23 ICT 2015
     */
    int insertSelective(UserAccount record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_account
     *
     * @mbggenerated Tue Jul 28 15:35:23 ICT 2015
     */
    List<UserAccount> selectByExample(UserAccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_account
     *
     * @mbggenerated Tue Jul 28 15:35:23 ICT 2015
     */
    UserAccount selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_account
     *
     * @mbggenerated Tue Jul 28 15:35:23 ICT 2015
     */
    int updateByExampleSelective(@Param("record") UserAccount record, @Param("example") UserAccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_account
     *
     * @mbggenerated Tue Jul 28 15:35:23 ICT 2015
     */
    int updateByExample(@Param("record") UserAccount record, @Param("example") UserAccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_account
     *
     * @mbggenerated Tue Jul 28 15:35:23 ICT 2015
     */
    int updateByPrimaryKeySelective(UserAccount record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_account
     *
     * @mbggenerated Tue Jul 28 15:35:23 ICT 2015
     */
    int updateByPrimaryKey(UserAccount record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_account
     *
     * @mbggenerated Tue Jul 28 15:35:23 ICT 2015
     */
    Integer insertAndReturnKey(UserAccount value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_account
     *
     * @mbggenerated Tue Jul 28 15:35:23 ICT 2015
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_account
     *
     * @mbggenerated Tue Jul 28 15:35:23 ICT 2015
     */
    void massUpdateWithSession(@Param("record") UserAccount record, @Param("primaryKeys") List primaryKeys);
}