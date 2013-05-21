package com.esofthead.mycollab.common.dao;

import com.esofthead.mycollab.common.domain.Comment;
import com.esofthead.mycollab.common.domain.CommentExample;
import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CommentMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_comment
     *
     * @mbggenerated Tue May 21 16:04:03 GMT+07:00 2013
     */
    int countByExample(CommentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_comment
     *
     * @mbggenerated Tue May 21 16:04:03 GMT+07:00 2013
     */
    int deleteByExample(CommentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_comment
     *
     * @mbggenerated Tue May 21 16:04:03 GMT+07:00 2013
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_comment
     *
     * @mbggenerated Tue May 21 16:04:03 GMT+07:00 2013
     */
    int insert(Comment record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_comment
     *
     * @mbggenerated Tue May 21 16:04:03 GMT+07:00 2013
     */
    int insertSelective(Comment record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_comment
     *
     * @mbggenerated Tue May 21 16:04:03 GMT+07:00 2013
     */
    List<Comment> selectByExample(CommentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_comment
     *
     * @mbggenerated Tue May 21 16:04:03 GMT+07:00 2013
     */
    Comment selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_comment
     *
     * @mbggenerated Tue May 21 16:04:03 GMT+07:00 2013
     */
    int updateByExampleSelective(@Param("record") Comment record, @Param("example") CommentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_comment
     *
     * @mbggenerated Tue May 21 16:04:03 GMT+07:00 2013
     */
    int updateByExample(@Param("record") Comment record, @Param("example") CommentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_comment
     *
     * @mbggenerated Tue May 21 16:04:03 GMT+07:00 2013
     */
    int updateByPrimaryKeySelective(Comment record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_comment
     *
     * @mbggenerated Tue May 21 16:04:03 GMT+07:00 2013
     */
    Integer insertAndReturnKey(Comment value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_comment
     *
     * @mbggenerated Tue May 21 16:04:03 GMT+07:00 2013
     */
    void removeKeysWithSession(List primaryKeys);
}