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
     * @mbggenerated Sun Nov 30 15:08:34 ICT 2014
     */
    int countByExample(BugRelatedItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_bug_related_item
     *
     * @mbggenerated Sun Nov 30 15:08:34 ICT 2014
     */
    int deleteByExample(BugRelatedItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_bug_related_item
     *
     * @mbggenerated Sun Nov 30 15:08:34 ICT 2014
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_bug_related_item
     *
     * @mbggenerated Sun Nov 30 15:08:34 ICT 2014
     */
    int insert(BugRelatedItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_bug_related_item
     *
     * @mbggenerated Sun Nov 30 15:08:34 ICT 2014
     */
    int insertSelective(BugRelatedItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_bug_related_item
     *
     * @mbggenerated Sun Nov 30 15:08:34 ICT 2014
     */
    List<BugRelatedItem> selectByExample(BugRelatedItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_bug_related_item
     *
     * @mbggenerated Sun Nov 30 15:08:34 ICT 2014
     */
    BugRelatedItem selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_bug_related_item
     *
     * @mbggenerated Sun Nov 30 15:08:34 ICT 2014
     */
    int updateByExampleSelective(@Param("record") BugRelatedItem record, @Param("example") BugRelatedItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_bug_related_item
     *
     * @mbggenerated Sun Nov 30 15:08:34 ICT 2014
     */
    int updateByExample(@Param("record") BugRelatedItem record, @Param("example") BugRelatedItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_bug_related_item
     *
     * @mbggenerated Sun Nov 30 15:08:34 ICT 2014
     */
    int updateByPrimaryKeySelective(BugRelatedItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_bug_related_item
     *
     * @mbggenerated Sun Nov 30 15:08:34 ICT 2014
     */
    int updateByPrimaryKey(BugRelatedItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_bug_related_item
     *
     * @mbggenerated Sun Nov 30 15:08:34 ICT 2014
     */
    Integer insertAndReturnKey(BugRelatedItem value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_bug_related_item
     *
     * @mbggenerated Sun Nov 30 15:08:34 ICT 2014
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_bug_related_item
     *
     * @mbggenerated Sun Nov 30 15:08:34 ICT 2014
     */
    void massUpdateWithSession(@Param("record") BugRelatedItem record, @Param("primaryKeys") List primaryKeys);
}