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
     * @mbggenerated Mon Aug 03 15:23:15 ICT 2015
     */
    int countByExample(RelatedBugExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_related_bug
     *
     * @mbggenerated Mon Aug 03 15:23:15 ICT 2015
     */
    int deleteByExample(RelatedBugExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_related_bug
     *
     * @mbggenerated Mon Aug 03 15:23:15 ICT 2015
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_related_bug
     *
     * @mbggenerated Mon Aug 03 15:23:15 ICT 2015
     */
    int insert(RelatedBug record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_related_bug
     *
     * @mbggenerated Mon Aug 03 15:23:15 ICT 2015
     */
    int insertSelective(RelatedBug record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_related_bug
     *
     * @mbggenerated Mon Aug 03 15:23:15 ICT 2015
     */
    List<RelatedBug> selectByExample(RelatedBugExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_related_bug
     *
     * @mbggenerated Mon Aug 03 15:23:15 ICT 2015
     */
    RelatedBug selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_related_bug
     *
     * @mbggenerated Mon Aug 03 15:23:15 ICT 2015
     */
    int updateByExampleSelective(@Param("record") RelatedBug record, @Param("example") RelatedBugExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_related_bug
     *
     * @mbggenerated Mon Aug 03 15:23:15 ICT 2015
     */
    int updateByExample(@Param("record") RelatedBug record, @Param("example") RelatedBugExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_related_bug
     *
     * @mbggenerated Mon Aug 03 15:23:15 ICT 2015
     */
    int updateByPrimaryKeySelective(RelatedBug record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_related_bug
     *
     * @mbggenerated Mon Aug 03 15:23:15 ICT 2015
     */
    int updateByPrimaryKey(RelatedBug record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_related_bug
     *
     * @mbggenerated Mon Aug 03 15:23:15 ICT 2015
     */
    Integer insertAndReturnKey(RelatedBug value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_related_bug
     *
     * @mbggenerated Mon Aug 03 15:23:15 ICT 2015
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_related_bug
     *
     * @mbggenerated Mon Aug 03 15:23:15 ICT 2015
     */
    void massUpdateWithSession(@Param("record") RelatedBug record, @Param("primaryKeys") List primaryKeys);
}