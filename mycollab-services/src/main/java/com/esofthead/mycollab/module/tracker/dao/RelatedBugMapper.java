package com.esofthead.mycollab.module.tracker.dao;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.module.tracker.domain.RelatedBug;
import com.esofthead.mycollab.module.tracker.domain.RelatedBugExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@SuppressWarnings({ "ucd", "rawtypes" })
public interface RelatedBugMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_related_bug
     *
     * @mbggenerated Wed Feb 03 14:56:46 ICT 2016
     */
    int countByExample(RelatedBugExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_related_bug
     *
     * @mbggenerated Wed Feb 03 14:56:46 ICT 2016
     */
    int deleteByExample(RelatedBugExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_related_bug
     *
     * @mbggenerated Wed Feb 03 14:56:46 ICT 2016
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_related_bug
     *
     * @mbggenerated Wed Feb 03 14:56:46 ICT 2016
     */
    int insert(RelatedBug record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_related_bug
     *
     * @mbggenerated Wed Feb 03 14:56:46 ICT 2016
     */
    int insertSelective(RelatedBug record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_related_bug
     *
     * @mbggenerated Wed Feb 03 14:56:46 ICT 2016
     */
    List<RelatedBug> selectByExample(RelatedBugExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_related_bug
     *
     * @mbggenerated Wed Feb 03 14:56:46 ICT 2016
     */
    RelatedBug selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_related_bug
     *
     * @mbggenerated Wed Feb 03 14:56:46 ICT 2016
     */
    int updateByExampleSelective(@Param("record") RelatedBug record, @Param("example") RelatedBugExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_related_bug
     *
     * @mbggenerated Wed Feb 03 14:56:46 ICT 2016
     */
    int updateByExample(@Param("record") RelatedBug record, @Param("example") RelatedBugExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_related_bug
     *
     * @mbggenerated Wed Feb 03 14:56:46 ICT 2016
     */
    int updateByPrimaryKeySelective(RelatedBug record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_related_bug
     *
     * @mbggenerated Wed Feb 03 14:56:46 ICT 2016
     */
    int updateByPrimaryKey(RelatedBug record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_related_bug
     *
     * @mbggenerated Wed Feb 03 14:56:46 ICT 2016
     */
    Integer insertAndReturnKey(RelatedBug value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_related_bug
     *
     * @mbggenerated Wed Feb 03 14:56:46 ICT 2016
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_related_bug
     *
     * @mbggenerated Wed Feb 03 14:56:46 ICT 2016
     */
    void massUpdateWithSession(@Param("record") RelatedBug record, @Param("primaryKeys") List primaryKeys);
}