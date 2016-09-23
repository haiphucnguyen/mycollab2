package com.mycollab.common.dao;

import com.mycollab.common.domain.CommentExample;
import com.mycollab.common.domain.CommentWithBLOBs;
import com.mycollab.db.persistence.ICrudGenericDAO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@SuppressWarnings({ "ucd", "rawtypes" })
public interface CommentMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_comment
     *
     * @mbg.generated Fri Sep 23 11:52:32 ICT 2016
     */
    long countByExample(CommentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_comment
     *
     * @mbg.generated Fri Sep 23 11:52:32 ICT 2016
     */
    int deleteByExample(CommentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_comment
     *
     * @mbg.generated Fri Sep 23 11:52:32 ICT 2016
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_comment
     *
     * @mbg.generated Fri Sep 23 11:52:32 ICT 2016
     */
    int insert(CommentWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_comment
     *
     * @mbg.generated Fri Sep 23 11:52:32 ICT 2016
     */
    int insertSelective(CommentWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_comment
     *
     * @mbg.generated Fri Sep 23 11:52:32 ICT 2016
     */
    List<CommentWithBLOBs> selectByExampleWithBLOBs(CommentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_comment
     *
     * @mbg.generated Fri Sep 23 11:52:32 ICT 2016
     */
    CommentWithBLOBs selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_comment
     *
     * @mbg.generated Fri Sep 23 11:52:32 ICT 2016
     */
    int updateByExampleSelective(@Param("record") CommentWithBLOBs record, @Param("example") CommentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_comment
     *
     * @mbg.generated Fri Sep 23 11:52:32 ICT 2016
     */
    int updateByExampleWithBLOBs(@Param("record") CommentWithBLOBs record, @Param("example") CommentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_comment
     *
     * @mbg.generated Fri Sep 23 11:52:32 ICT 2016
     */
    int updateByPrimaryKeySelective(CommentWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_comment
     *
     * @mbg.generated Fri Sep 23 11:52:32 ICT 2016
     */
    int updateByPrimaryKeyWithBLOBs(CommentWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_comment
     *
     * @mbg.generated Fri Sep 23 11:52:32 ICT 2016
     */
    Integer insertAndReturnKey(CommentWithBLOBs value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_comment
     *
     * @mbg.generated Fri Sep 23 11:52:32 ICT 2016
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_comment
     *
     * @mbg.generated Fri Sep 23 11:52:32 ICT 2016
     */
    void massUpdateWithSession(@Param("record") CommentWithBLOBs record, @Param("primaryKeys") List primaryKeys);
}