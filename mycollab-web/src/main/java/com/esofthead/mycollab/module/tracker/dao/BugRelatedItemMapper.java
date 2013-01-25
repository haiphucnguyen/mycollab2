package com.esofthead.mycollab.module.tracker.dao;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.module.tracker.domain.BugRelatedItem;
import com.esofthead.mycollab.module.tracker.domain.BugRelatedItemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BugRelatedItemMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_bug_related_item
     *
     * @mbggenerated Fri Jan 25 09:46:00 GMT+07:00 2013
     */
    int countByExample(BugRelatedItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_bug_related_item
     *
     * @mbggenerated Fri Jan 25 09:46:00 GMT+07:00 2013
     */
    int deleteByExample(BugRelatedItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_bug_related_item
     *
     * @mbggenerated Fri Jan 25 09:46:00 GMT+07:00 2013
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_bug_related_item
     *
     * @mbggenerated Fri Jan 25 09:46:00 GMT+07:00 2013
     */
    int insert(BugRelatedItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_bug_related_item
     *
     * @mbggenerated Fri Jan 25 09:46:00 GMT+07:00 2013
     */
    int insertSelective(BugRelatedItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_bug_related_item
     *
     * @mbggenerated Fri Jan 25 09:46:00 GMT+07:00 2013
     */
    List<BugRelatedItem> selectByExample(BugRelatedItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_bug_related_item
     *
     * @mbggenerated Fri Jan 25 09:46:00 GMT+07:00 2013
     */
    BugRelatedItem selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_bug_related_item
     *
     * @mbggenerated Fri Jan 25 09:46:00 GMT+07:00 2013
     */
    int updateByExampleSelective(@Param("record") BugRelatedItem record, @Param("example") BugRelatedItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_bug_related_item
     *
     * @mbggenerated Fri Jan 25 09:46:00 GMT+07:00 2013
     */
    int updateByExample(@Param("record") BugRelatedItem record, @Param("example") BugRelatedItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_bug_related_item
     *
     * @mbggenerated Fri Jan 25 09:46:00 GMT+07:00 2013
     */
    int updateByPrimaryKeySelective(BugRelatedItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_bug_related_item
     *
     * @mbggenerated Fri Jan 25 09:46:00 GMT+07:00 2013
     */
    int updateByPrimaryKey(BugRelatedItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_bug_related_item
     *
     * @mbggenerated Fri Jan 25 09:46:00 GMT+07:00 2013
     */
    Integer insertAndReturnKey(BugRelatedItem value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_bug_related_item
     *
     * @mbggenerated Fri Jan 25 09:46:00 GMT+07:00 2013
     */
    void removeKeysWithSession(List primaryKeys);
}