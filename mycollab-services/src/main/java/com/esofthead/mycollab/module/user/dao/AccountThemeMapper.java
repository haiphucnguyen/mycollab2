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
import com.esofthead.mycollab.module.user.domain.AccountTheme;
import com.esofthead.mycollab.module.user.domain.AccountThemeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@SuppressWarnings({ "ucd", "rawtypes" })
public interface AccountThemeMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_account_theme
     *
     * @mbggenerated Thu Jul 16 10:50:11 ICT 2015
     */
    int countByExample(AccountThemeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_account_theme
     *
     * @mbggenerated Thu Jul 16 10:50:11 ICT 2015
     */
    int deleteByExample(AccountThemeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_account_theme
     *
     * @mbggenerated Thu Jul 16 10:50:11 ICT 2015
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_account_theme
     *
     * @mbggenerated Thu Jul 16 10:50:11 ICT 2015
     */
    int insert(AccountTheme record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_account_theme
     *
     * @mbggenerated Thu Jul 16 10:50:11 ICT 2015
     */
    int insertSelective(AccountTheme record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_account_theme
     *
     * @mbggenerated Thu Jul 16 10:50:11 ICT 2015
     */
    List<AccountTheme> selectByExample(AccountThemeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_account_theme
     *
     * @mbggenerated Thu Jul 16 10:50:11 ICT 2015
     */
    AccountTheme selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_account_theme
     *
     * @mbggenerated Thu Jul 16 10:50:11 ICT 2015
     */
    int updateByExampleSelective(@Param("record") AccountTheme record, @Param("example") AccountThemeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_account_theme
     *
     * @mbggenerated Thu Jul 16 10:50:11 ICT 2015
     */
    int updateByExample(@Param("record") AccountTheme record, @Param("example") AccountThemeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_account_theme
     *
     * @mbggenerated Thu Jul 16 10:50:11 ICT 2015
     */
    int updateByPrimaryKeySelective(AccountTheme record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_account_theme
     *
     * @mbggenerated Thu Jul 16 10:50:11 ICT 2015
     */
    int updateByPrimaryKey(AccountTheme record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_account_theme
     *
     * @mbggenerated Thu Jul 16 10:50:11 ICT 2015
     */
    Integer insertAndReturnKey(AccountTheme value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_account_theme
     *
     * @mbggenerated Thu Jul 16 10:50:11 ICT 2015
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_account_theme
     *
     * @mbggenerated Thu Jul 16 10:50:11 ICT 2015
     */
    void massUpdateWithSession(@Param("record") AccountTheme record, @Param("primaryKeys") List primaryKeys);
}