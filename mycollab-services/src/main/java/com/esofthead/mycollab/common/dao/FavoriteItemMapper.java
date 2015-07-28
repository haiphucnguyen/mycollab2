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

import com.esofthead.mycollab.common.domain.FavoriteItem;
import com.esofthead.mycollab.common.domain.FavoriteItemExample;
import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@SuppressWarnings({ "ucd", "rawtypes" })
public interface FavoriteItemMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_favorite
     *
     * @mbggenerated Tue Jul 28 15:35:24 ICT 2015
     */
    int countByExample(FavoriteItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_favorite
     *
     * @mbggenerated Tue Jul 28 15:35:24 ICT 2015
     */
    int deleteByExample(FavoriteItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_favorite
     *
     * @mbggenerated Tue Jul 28 15:35:24 ICT 2015
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_favorite
     *
     * @mbggenerated Tue Jul 28 15:35:24 ICT 2015
     */
    int insert(FavoriteItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_favorite
     *
     * @mbggenerated Tue Jul 28 15:35:24 ICT 2015
     */
    int insertSelective(FavoriteItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_favorite
     *
     * @mbggenerated Tue Jul 28 15:35:24 ICT 2015
     */
    List<FavoriteItem> selectByExample(FavoriteItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_favorite
     *
     * @mbggenerated Tue Jul 28 15:35:24 ICT 2015
     */
    FavoriteItem selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_favorite
     *
     * @mbggenerated Tue Jul 28 15:35:24 ICT 2015
     */
    int updateByExampleSelective(@Param("record") FavoriteItem record, @Param("example") FavoriteItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_favorite
     *
     * @mbggenerated Tue Jul 28 15:35:24 ICT 2015
     */
    int updateByExample(@Param("record") FavoriteItem record, @Param("example") FavoriteItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_favorite
     *
     * @mbggenerated Tue Jul 28 15:35:24 ICT 2015
     */
    int updateByPrimaryKeySelective(FavoriteItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_favorite
     *
     * @mbggenerated Tue Jul 28 15:35:24 ICT 2015
     */
    int updateByPrimaryKey(FavoriteItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_favorite
     *
     * @mbggenerated Tue Jul 28 15:35:24 ICT 2015
     */
    Integer insertAndReturnKey(FavoriteItem value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_favorite
     *
     * @mbggenerated Tue Jul 28 15:35:24 ICT 2015
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_favorite
     *
     * @mbggenerated Tue Jul 28 15:35:24 ICT 2015
     */
    void massUpdateWithSession(@Param("record") FavoriteItem record, @Param("primaryKeys") List primaryKeys);
}