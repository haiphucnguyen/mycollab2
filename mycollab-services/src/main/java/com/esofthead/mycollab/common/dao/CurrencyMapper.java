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

import com.esofthead.mycollab.common.domain.Currency;
import com.esofthead.mycollab.common.domain.CurrencyExample;
import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@SuppressWarnings({ "ucd", "rawtypes" })
public interface CurrencyMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_currency
     *
     * @mbggenerated Fri Mar 06 17:04:13 ICT 2015
     */
    int countByExample(CurrencyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_currency
     *
     * @mbggenerated Fri Mar 06 17:04:13 ICT 2015
     */
    int deleteByExample(CurrencyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_currency
     *
     * @mbggenerated Fri Mar 06 17:04:13 ICT 2015
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_currency
     *
     * @mbggenerated Fri Mar 06 17:04:13 ICT 2015
     */
    int insert(Currency record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_currency
     *
     * @mbggenerated Fri Mar 06 17:04:13 ICT 2015
     */
    int insertSelective(Currency record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_currency
     *
     * @mbggenerated Fri Mar 06 17:04:13 ICT 2015
     */
    List<Currency> selectByExample(CurrencyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_currency
     *
     * @mbggenerated Fri Mar 06 17:04:13 ICT 2015
     */
    Currency selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_currency
     *
     * @mbggenerated Fri Mar 06 17:04:13 ICT 2015
     */
    int updateByExampleSelective(@Param("record") Currency record, @Param("example") CurrencyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_currency
     *
     * @mbggenerated Fri Mar 06 17:04:13 ICT 2015
     */
    int updateByExample(@Param("record") Currency record, @Param("example") CurrencyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_currency
     *
     * @mbggenerated Fri Mar 06 17:04:13 ICT 2015
     */
    int updateByPrimaryKeySelective(Currency record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_currency
     *
     * @mbggenerated Fri Mar 06 17:04:13 ICT 2015
     */
    int updateByPrimaryKey(Currency record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_currency
     *
     * @mbggenerated Fri Mar 06 17:04:13 ICT 2015
     */
    Integer insertAndReturnKey(Currency value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_currency
     *
     * @mbggenerated Fri Mar 06 17:04:13 ICT 2015
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_currency
     *
     * @mbggenerated Fri Mar 06 17:04:13 ICT 2015
     */
    void massUpdateWithSession(@Param("record") Currency record, @Param("primaryKeys") List primaryKeys);
}