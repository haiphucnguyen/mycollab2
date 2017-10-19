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
     * @mbg.generated Thu Oct 19 13:58:22 ICT 2017
     */
    long countByExample(RelatedBugExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_related_bug
     *
     * @mbg.generated Thu Oct 19 13:58:22 ICT 2017
     */
    int deleteByExample(RelatedBugExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_related_bug
     *
     * @mbg.generated Thu Oct 19 13:58:22 ICT 2017
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_related_bug
     *
     * @mbg.generated Thu Oct 19 13:58:22 ICT 2017
     */
    int insert(RelatedBug record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_related_bug
     *
     * @mbg.generated Thu Oct 19 13:58:22 ICT 2017
     */
    int insertSelective(RelatedBug record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_related_bug
     *
     * @mbg.generated Thu Oct 19 13:58:22 ICT 2017
     */
    List<RelatedBug> selectByExample(RelatedBugExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_related_bug
     *
     * @mbg.generated Thu Oct 19 13:58:22 ICT 2017
     */
    RelatedBug selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_related_bug
     *
     * @mbg.generated Thu Oct 19 13:58:22 ICT 2017
     */
    int updateByExampleSelective(@Param("record") RelatedBug record, @Param("example") RelatedBugExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_related_bug
     *
     * @mbg.generated Thu Oct 19 13:58:22 ICT 2017
     */
    int updateByExample(@Param("record") RelatedBug record, @Param("example") RelatedBugExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_related_bug
     *
     * @mbg.generated Thu Oct 19 13:58:22 ICT 2017
     */
    int updateByPrimaryKeySelective(RelatedBug record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_related_bug
     *
     * @mbg.generated Thu Oct 19 13:58:22 ICT 2017
     */
    int updateByPrimaryKey(RelatedBug record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_related_bug
     *
     * @mbg.generated Thu Oct 19 13:58:22 ICT 2017
     */
    Integer insertAndReturnKey(RelatedBug value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_related_bug
     *
     * @mbg.generated Thu Oct 19 13:58:22 ICT 2017
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_related_bug
     *
     * @mbg.generated Thu Oct 19 13:58:22 ICT 2017
     */
    void massUpdateWithSession(@Param("record") RelatedBug record, @Param("primaryKeys") List primaryKeys);
}