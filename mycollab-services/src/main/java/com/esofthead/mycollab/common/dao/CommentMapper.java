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

import com.esofthead.mycollab.common.domain.Comment;
import com.esofthead.mycollab.common.domain.CommentExample;
import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@SuppressWarnings({ "ucd", "rawtypes" })
public interface CommentMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_comment
     *
     * @mbggenerated Sat Feb 28 16:29:57 ICT 2015
     */
    int countByExample(CommentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_comment
     *
     * @mbggenerated Sat Feb 28 16:29:57 ICT 2015
     */
    int deleteByExample(CommentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_comment
     *
     * @mbggenerated Sat Feb 28 16:29:57 ICT 2015
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_comment
     *
     * @mbggenerated Sat Feb 28 16:29:57 ICT 2015
     */
    int insert(Comment record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_comment
     *
     * @mbggenerated Sat Feb 28 16:29:57 ICT 2015
     */
    int insertSelective(Comment record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_comment
     *
     * @mbggenerated Sat Feb 28 16:29:57 ICT 2015
     */
    List<Comment> selectByExampleWithBLOBs(CommentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_comment
     *
     * @mbggenerated Sat Feb 28 16:29:57 ICT 2015
     */
    List<Comment> selectByExample(CommentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_comment
     *
     * @mbggenerated Sat Feb 28 16:29:57 ICT 2015
     */
    Comment selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_comment
     *
     * @mbggenerated Sat Feb 28 16:29:57 ICT 2015
     */
    int updateByExampleSelective(@Param("record") Comment record, @Param("example") CommentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_comment
     *
     * @mbggenerated Sat Feb 28 16:29:57 ICT 2015
     */
    int updateByExampleWithBLOBs(@Param("record") Comment record, @Param("example") CommentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_comment
     *
     * @mbggenerated Sat Feb 28 16:29:57 ICT 2015
     */
    int updateByExample(@Param("record") Comment record, @Param("example") CommentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_comment
     *
     * @mbggenerated Sat Feb 28 16:29:57 ICT 2015
     */
    int updateByPrimaryKeySelective(Comment record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_comment
     *
     * @mbggenerated Sat Feb 28 16:29:57 ICT 2015
     */
    int updateByPrimaryKeyWithBLOBs(Comment record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_comment
     *
     * @mbggenerated Sat Feb 28 16:29:57 ICT 2015
     */
    int updateByPrimaryKey(Comment record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_comment
     *
     * @mbggenerated Sat Feb 28 16:29:57 ICT 2015
     */
    Integer insertAndReturnKey(Comment value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_comment
     *
     * @mbggenerated Sat Feb 28 16:29:57 ICT 2015
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_comment
     *
     * @mbggenerated Sat Feb 28 16:29:57 ICT 2015
     */
    void massUpdateWithSession(@Param("record") Comment record, @Param("primaryKeys") List primaryKeys);
}