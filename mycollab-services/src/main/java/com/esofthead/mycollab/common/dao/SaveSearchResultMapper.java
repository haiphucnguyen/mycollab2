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

import com.esofthead.mycollab.common.domain.SaveSearchResult;
import com.esofthead.mycollab.common.domain.SaveSearchResultExample;
import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@SuppressWarnings({ "ucd", "rawtypes" })
public interface SaveSearchResultMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_save_search_result
     *
     * @mbggenerated Thu Jun 30 11:43:01 ICT 2016
     */
    int countByExample(SaveSearchResultExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_save_search_result
     *
     * @mbggenerated Thu Jun 30 11:43:01 ICT 2016
     */
    int deleteByExample(SaveSearchResultExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_save_search_result
     *
     * @mbggenerated Thu Jun 30 11:43:01 ICT 2016
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_save_search_result
     *
     * @mbggenerated Thu Jun 30 11:43:01 ICT 2016
     */
    int insert(SaveSearchResult record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_save_search_result
     *
     * @mbggenerated Thu Jun 30 11:43:01 ICT 2016
     */
    int insertSelective(SaveSearchResult record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_save_search_result
     *
     * @mbggenerated Thu Jun 30 11:43:01 ICT 2016
     */
    List<SaveSearchResult> selectByExampleWithBLOBs(SaveSearchResultExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_save_search_result
     *
     * @mbggenerated Thu Jun 30 11:43:01 ICT 2016
     */
    List<SaveSearchResult> selectByExample(SaveSearchResultExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_save_search_result
     *
     * @mbggenerated Thu Jun 30 11:43:01 ICT 2016
     */
    SaveSearchResult selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_save_search_result
     *
     * @mbggenerated Thu Jun 30 11:43:01 ICT 2016
     */
    int updateByExampleSelective(@Param("record") SaveSearchResult record, @Param("example") SaveSearchResultExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_save_search_result
     *
     * @mbggenerated Thu Jun 30 11:43:01 ICT 2016
     */
    int updateByExampleWithBLOBs(@Param("record") SaveSearchResult record, @Param("example") SaveSearchResultExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_save_search_result
     *
     * @mbggenerated Thu Jun 30 11:43:01 ICT 2016
     */
    int updateByExample(@Param("record") SaveSearchResult record, @Param("example") SaveSearchResultExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_save_search_result
     *
     * @mbggenerated Thu Jun 30 11:43:01 ICT 2016
     */
    int updateByPrimaryKeySelective(SaveSearchResult record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_save_search_result
     *
     * @mbggenerated Thu Jun 30 11:43:01 ICT 2016
     */
    int updateByPrimaryKeyWithBLOBs(SaveSearchResult record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_save_search_result
     *
     * @mbggenerated Thu Jun 30 11:43:01 ICT 2016
     */
    int updateByPrimaryKey(SaveSearchResult record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_save_search_result
     *
     * @mbggenerated Thu Jun 30 11:43:01 ICT 2016
     */
    Integer insertAndReturnKey(SaveSearchResult value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_save_search_result
     *
     * @mbggenerated Thu Jun 30 11:43:01 ICT 2016
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_save_search_result
     *
     * @mbggenerated Thu Jun 30 11:43:01 ICT 2016
     */
    void massUpdateWithSession(@Param("record") SaveSearchResult record, @Param("primaryKeys") List primaryKeys);
}