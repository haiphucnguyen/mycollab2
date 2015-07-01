package com.esofthead.mycollab.common.dao;

import com.esofthead.mycollab.common.domain.CommentExample;
import com.esofthead.mycollab.common.domain.CommentWithBLOBs;
import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@SuppressWarnings({ "ucd", "rawtypes" })
public interface CommentMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_comment
     *
     * @mbggenerated Wed Jul 01 11:51:35 ICT 2015
     */
    int countByExample(CommentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_comment
     *
     * @mbggenerated Wed Jul 01 11:51:35 ICT 2015
     */
    int deleteByExample(CommentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_comment
     *
     * @mbggenerated Wed Jul 01 11:51:35 ICT 2015
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_comment
     *
     * @mbggenerated Wed Jul 01 11:51:35 ICT 2015
     */
    int insert(CommentWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_comment
     *
     * @mbggenerated Wed Jul 01 11:51:35 ICT 2015
     */
    int insertSelective(CommentWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_comment
     *
     * @mbggenerated Wed Jul 01 11:51:35 ICT 2015
     */
    List<CommentWithBLOBs> selectByExampleWithBLOBs(CommentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_comment
     *
     * @mbggenerated Wed Jul 01 11:51:35 ICT 2015
     */
    CommentWithBLOBs selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_comment
     *
     * @mbggenerated Wed Jul 01 11:51:35 ICT 2015
     */
    int updateByExampleSelective(@Param("record") CommentWithBLOBs record, @Param("example") CommentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_comment
     *
     * @mbggenerated Wed Jul 01 11:51:35 ICT 2015
     */
    int updateByExampleWithBLOBs(@Param("record") CommentWithBLOBs record, @Param("example") CommentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_comment
     *
     * @mbggenerated Wed Jul 01 11:51:35 ICT 2015
     */
    int updateByPrimaryKeySelective(CommentWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_comment
     *
     * @mbggenerated Wed Jul 01 11:51:35 ICT 2015
     */
    int updateByPrimaryKeyWithBLOBs(CommentWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_comment
     *
     * @mbggenerated Wed Jul 01 11:51:35 ICT 2015
     */
    Integer insertAndReturnKey(CommentWithBLOBs value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_comment
     *
     * @mbggenerated Wed Jul 01 11:51:35 ICT 2015
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_comment
     *
     * @mbggenerated Wed Jul 01 11:51:35 ICT 2015
     */
    void massUpdateWithSession(@Param("record") CommentWithBLOBs record, @Param("primaryKeys") List primaryKeys);
}