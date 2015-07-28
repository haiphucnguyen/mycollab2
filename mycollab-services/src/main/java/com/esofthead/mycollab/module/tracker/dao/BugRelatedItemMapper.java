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
package com.esofthead.mycollab.module.tracker.dao;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.module.tracker.domain.BugRelatedItem;
import com.esofthead.mycollab.module.tracker.domain.BugRelatedItemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@SuppressWarnings({ "ucd", "rawtypes" })
public interface BugRelatedItemMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_bug_related_item
     *
     * @mbggenerated Tue Jul 28 15:35:25 ICT 2015
     */
    int countByExample(BugRelatedItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_bug_related_item
     *
     * @mbggenerated Tue Jul 28 15:35:25 ICT 2015
     */
    int deleteByExample(BugRelatedItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_bug_related_item
     *
     * @mbggenerated Tue Jul 28 15:35:25 ICT 2015
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_bug_related_item
     *
     * @mbggenerated Tue Jul 28 15:35:25 ICT 2015
     */
    int insert(BugRelatedItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_bug_related_item
     *
     * @mbggenerated Tue Jul 28 15:35:25 ICT 2015
     */
    int insertSelective(BugRelatedItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_bug_related_item
     *
     * @mbggenerated Tue Jul 28 15:35:25 ICT 2015
     */
    List<BugRelatedItem> selectByExample(BugRelatedItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_bug_related_item
     *
     * @mbggenerated Tue Jul 28 15:35:25 ICT 2015
     */
    BugRelatedItem selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_bug_related_item
     *
     * @mbggenerated Tue Jul 28 15:35:25 ICT 2015
     */
    int updateByExampleSelective(@Param("record") BugRelatedItem record, @Param("example") BugRelatedItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_bug_related_item
     *
     * @mbggenerated Tue Jul 28 15:35:25 ICT 2015
     */
    int updateByExample(@Param("record") BugRelatedItem record, @Param("example") BugRelatedItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_bug_related_item
     *
     * @mbggenerated Tue Jul 28 15:35:25 ICT 2015
     */
    int updateByPrimaryKeySelective(BugRelatedItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_bug_related_item
     *
     * @mbggenerated Tue Jul 28 15:35:25 ICT 2015
     */
    int updateByPrimaryKey(BugRelatedItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_bug_related_item
     *
     * @mbggenerated Tue Jul 28 15:35:25 ICT 2015
     */
    Integer insertAndReturnKey(BugRelatedItem value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_bug_related_item
     *
     * @mbggenerated Tue Jul 28 15:35:25 ICT 2015
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_bug_related_item
     *
     * @mbggenerated Tue Jul 28 15:35:25 ICT 2015
     */
    void massUpdateWithSession(@Param("record") BugRelatedItem record, @Param("primaryKeys") List primaryKeys);
}