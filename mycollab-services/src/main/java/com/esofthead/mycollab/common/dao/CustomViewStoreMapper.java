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

import com.esofthead.mycollab.common.domain.CustomViewStore;
import com.esofthead.mycollab.common.domain.CustomViewStoreExample;
import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@SuppressWarnings({ "ucd", "rawtypes" })
public interface CustomViewStoreMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_table_customize_view
     *
     * @mbggenerated Mon May 18 10:06:58 ICT 2015
     */
    int countByExample(CustomViewStoreExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_table_customize_view
     *
     * @mbggenerated Mon May 18 10:06:58 ICT 2015
     */
    int deleteByExample(CustomViewStoreExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_table_customize_view
     *
     * @mbggenerated Mon May 18 10:06:58 ICT 2015
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_table_customize_view
     *
     * @mbggenerated Mon May 18 10:06:58 ICT 2015
     */
    int insert(CustomViewStore record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_table_customize_view
     *
     * @mbggenerated Mon May 18 10:06:58 ICT 2015
     */
    int insertSelective(CustomViewStore record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_table_customize_view
     *
     * @mbggenerated Mon May 18 10:06:58 ICT 2015
     */
    List<CustomViewStore> selectByExampleWithBLOBs(CustomViewStoreExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_table_customize_view
     *
     * @mbggenerated Mon May 18 10:06:58 ICT 2015
     */
    List<CustomViewStore> selectByExample(CustomViewStoreExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_table_customize_view
     *
     * @mbggenerated Mon May 18 10:06:58 ICT 2015
     */
    CustomViewStore selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_table_customize_view
     *
     * @mbggenerated Mon May 18 10:06:58 ICT 2015
     */
    int updateByExampleSelective(@Param("record") CustomViewStore record, @Param("example") CustomViewStoreExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_table_customize_view
     *
     * @mbggenerated Mon May 18 10:06:58 ICT 2015
     */
    int updateByExampleWithBLOBs(@Param("record") CustomViewStore record, @Param("example") CustomViewStoreExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_table_customize_view
     *
     * @mbggenerated Mon May 18 10:06:58 ICT 2015
     */
    int updateByExample(@Param("record") CustomViewStore record, @Param("example") CustomViewStoreExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_table_customize_view
     *
     * @mbggenerated Mon May 18 10:06:58 ICT 2015
     */
    int updateByPrimaryKeySelective(CustomViewStore record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_table_customize_view
     *
     * @mbggenerated Mon May 18 10:06:58 ICT 2015
     */
    int updateByPrimaryKeyWithBLOBs(CustomViewStore record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_table_customize_view
     *
     * @mbggenerated Mon May 18 10:06:58 ICT 2015
     */
    int updateByPrimaryKey(CustomViewStore record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_table_customize_view
     *
     * @mbggenerated Mon May 18 10:06:58 ICT 2015
     */
    Integer insertAndReturnKey(CustomViewStore value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_table_customize_view
     *
     * @mbggenerated Mon May 18 10:06:58 ICT 2015
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_table_customize_view
     *
     * @mbggenerated Mon May 18 10:06:58 ICT 2015
     */
    void massUpdateWithSession(@Param("record") CustomViewStore record, @Param("primaryKeys") List primaryKeys);
}