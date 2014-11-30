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
     * @mbggenerated Sun Nov 30 15:08:34 ICT 2014
     */
    int countByExample(AccountThemeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_account_theme
     *
     * @mbggenerated Sun Nov 30 15:08:34 ICT 2014
     */
    int deleteByExample(AccountThemeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_account_theme
     *
     * @mbggenerated Sun Nov 30 15:08:34 ICT 2014
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_account_theme
     *
     * @mbggenerated Sun Nov 30 15:08:34 ICT 2014
     */
    int insert(AccountTheme record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_account_theme
     *
     * @mbggenerated Sun Nov 30 15:08:34 ICT 2014
     */
    int insertSelective(AccountTheme record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_account_theme
     *
     * @mbggenerated Sun Nov 30 15:08:34 ICT 2014
     */
    List<AccountTheme> selectByExample(AccountThemeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_account_theme
     *
     * @mbggenerated Sun Nov 30 15:08:34 ICT 2014
     */
    AccountTheme selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_account_theme
     *
     * @mbggenerated Sun Nov 30 15:08:34 ICT 2014
     */
    int updateByExampleSelective(@Param("record") AccountTheme record, @Param("example") AccountThemeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_account_theme
     *
     * @mbggenerated Sun Nov 30 15:08:34 ICT 2014
     */
    int updateByExample(@Param("record") AccountTheme record, @Param("example") AccountThemeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_account_theme
     *
     * @mbggenerated Sun Nov 30 15:08:34 ICT 2014
     */
    int updateByPrimaryKeySelective(AccountTheme record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_account_theme
     *
     * @mbggenerated Sun Nov 30 15:08:34 ICT 2014
     */
    int updateByPrimaryKey(AccountTheme record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_account_theme
     *
     * @mbggenerated Sun Nov 30 15:08:34 ICT 2014
     */
    Integer insertAndReturnKey(AccountTheme value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_account_theme
     *
     * @mbggenerated Sun Nov 30 15:08:34 ICT 2014
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_account_theme
     *
     * @mbggenerated Sun Nov 30 15:08:34 ICT 2014
     */
    void massUpdateWithSession(@Param("record") AccountTheme record, @Param("primaryKeys") List primaryKeys);
}