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
package com.mycollab.module.tracker.dao;

import com.mycollab.db.persistence.ICrudGenericDAO;
import com.mycollab.module.tracker.domain.RelatedBug;
import com.mycollab.module.tracker.domain.RelatedBugExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@SuppressWarnings({ "ucd", "rawtypes" })
public interface RelatedBugMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_related_bug
     *
     * @mbg.generated Sat Sep 24 08:44:33 ICT 2016
     */
    long countByExample(RelatedBugExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_related_bug
     *
     * @mbg.generated Sat Sep 24 08:44:33 ICT 2016
     */
    int deleteByExample(RelatedBugExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_related_bug
     *
     * @mbg.generated Sat Sep 24 08:44:33 ICT 2016
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_related_bug
     *
     * @mbg.generated Sat Sep 24 08:44:33 ICT 2016
     */
    int insert(RelatedBug record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_related_bug
     *
     * @mbg.generated Sat Sep 24 08:44:33 ICT 2016
     */
    int insertSelective(RelatedBug record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_related_bug
     *
     * @mbg.generated Sat Sep 24 08:44:33 ICT 2016
     */
    List<RelatedBug> selectByExample(RelatedBugExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_related_bug
     *
     * @mbg.generated Sat Sep 24 08:44:33 ICT 2016
     */
    RelatedBug selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_related_bug
     *
     * @mbg.generated Sat Sep 24 08:44:33 ICT 2016
     */
    int updateByExampleSelective(@Param("record") RelatedBug record, @Param("example") RelatedBugExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_related_bug
     *
     * @mbg.generated Sat Sep 24 08:44:33 ICT 2016
     */
    int updateByExample(@Param("record") RelatedBug record, @Param("example") RelatedBugExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_related_bug
     *
     * @mbg.generated Sat Sep 24 08:44:33 ICT 2016
     */
    int updateByPrimaryKeySelective(RelatedBug record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_related_bug
     *
     * @mbg.generated Sat Sep 24 08:44:33 ICT 2016
     */
    int updateByPrimaryKey(RelatedBug record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_related_bug
     *
     * @mbg.generated Sat Sep 24 08:44:33 ICT 2016
     */
    Integer insertAndReturnKey(RelatedBug value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_related_bug
     *
     * @mbg.generated Sat Sep 24 08:44:33 ICT 2016
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_related_bug
     *
     * @mbg.generated Sat Sep 24 08:44:33 ICT 2016
     */
    void massUpdateWithSession(@Param("record") RelatedBug record, @Param("primaryKeys") List primaryKeys);
}