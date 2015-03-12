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

import com.esofthead.mycollab.common.domain.Tag;
import com.esofthead.mycollab.common.domain.TagExample;
import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@SuppressWarnings({ "ucd", "rawtypes" })
public interface TagMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_tag
     *
     * @mbggenerated Wed Mar 11 09:10:40 ICT 2015
     */
    int countByExample(TagExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_tag
     *
     * @mbggenerated Wed Mar 11 09:10:40 ICT 2015
     */
    int deleteByExample(TagExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_tag
     *
     * @mbggenerated Wed Mar 11 09:10:40 ICT 2015
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_tag
     *
     * @mbggenerated Wed Mar 11 09:10:40 ICT 2015
     */
    int insert(Tag record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_tag
     *
     * @mbggenerated Wed Mar 11 09:10:40 ICT 2015
     */
    int insertSelective(Tag record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_tag
     *
     * @mbggenerated Wed Mar 11 09:10:40 ICT 2015
     */
    List<Tag> selectByExample(TagExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_tag
     *
     * @mbggenerated Wed Mar 11 09:10:40 ICT 2015
     */
    Tag selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_tag
     *
     * @mbggenerated Wed Mar 11 09:10:40 ICT 2015
     */
    int updateByExampleSelective(@Param("record") Tag record, @Param("example") TagExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_tag
     *
     * @mbggenerated Wed Mar 11 09:10:40 ICT 2015
     */
    int updateByExample(@Param("record") Tag record, @Param("example") TagExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_tag
     *
     * @mbggenerated Wed Mar 11 09:10:40 ICT 2015
     */
    int updateByPrimaryKeySelective(Tag record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_tag
     *
     * @mbggenerated Wed Mar 11 09:10:40 ICT 2015
     */
    int updateByPrimaryKey(Tag record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_tag
     *
     * @mbggenerated Wed Mar 11 09:10:40 ICT 2015
     */
    Integer insertAndReturnKey(Tag value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_tag
     *
     * @mbggenerated Wed Mar 11 09:10:40 ICT 2015
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_tag
     *
     * @mbggenerated Wed Mar 11 09:10:40 ICT 2015
     */
    void massUpdateWithSession(@Param("record") Tag record, @Param("primaryKeys") List primaryKeys);
}