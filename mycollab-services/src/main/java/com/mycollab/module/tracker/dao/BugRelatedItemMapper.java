package com.mycollab.module.tracker.dao;

import com.mycollab.db.persistence.ICrudGenericDAO;
import com.mycollab.module.tracker.domain.BugRelatedItem;
import com.mycollab.module.tracker.domain.BugRelatedItemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@SuppressWarnings({ "ucd", "rawtypes" })
public interface BugRelatedItemMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_bug_related_item
     *
     * @mbg.generated Thu Jan 03 18:09:39 CST 2019
     */
    long countByExample(BugRelatedItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_bug_related_item
     *
     * @mbg.generated Thu Jan 03 18:09:39 CST 2019
     */
    int deleteByExample(BugRelatedItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_bug_related_item
     *
     * @mbg.generated Thu Jan 03 18:09:39 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_bug_related_item
     *
     * @mbg.generated Thu Jan 03 18:09:39 CST 2019
     */
    int insert(BugRelatedItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_bug_related_item
     *
     * @mbg.generated Thu Jan 03 18:09:39 CST 2019
     */
    int insertSelective(BugRelatedItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_bug_related_item
     *
     * @mbg.generated Thu Jan 03 18:09:39 CST 2019
     */
    List<BugRelatedItem> selectByExample(BugRelatedItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_bug_related_item
     *
     * @mbg.generated Thu Jan 03 18:09:39 CST 2019
     */
    BugRelatedItem selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_bug_related_item
     *
     * @mbg.generated Thu Jan 03 18:09:39 CST 2019
     */
    int updateByExampleSelective(@Param("record") BugRelatedItem record, @Param("example") BugRelatedItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_bug_related_item
     *
     * @mbg.generated Thu Jan 03 18:09:39 CST 2019
     */
    int updateByExample(@Param("record") BugRelatedItem record, @Param("example") BugRelatedItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_bug_related_item
     *
     * @mbg.generated Thu Jan 03 18:09:39 CST 2019
     */
    int updateByPrimaryKeySelective(BugRelatedItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_bug_related_item
     *
     * @mbg.generated Thu Jan 03 18:09:39 CST 2019
     */
    int updateByPrimaryKey(BugRelatedItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_bug_related_item
     *
     * @mbg.generated Thu Jan 03 18:09:39 CST 2019
     */
    Integer insertAndReturnKey(BugRelatedItem value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_bug_related_item
     *
     * @mbg.generated Thu Jan 03 18:09:39 CST 2019
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_bug_related_item
     *
     * @mbg.generated Thu Jan 03 18:09:39 CST 2019
     */
    void massUpdateWithSession(@Param("record") BugRelatedItem record, @Param("primaryKeys") List primaryKeys);
}