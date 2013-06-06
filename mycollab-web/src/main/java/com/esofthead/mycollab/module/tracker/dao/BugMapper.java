package com.esofthead.mycollab.module.tracker.dao;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.module.tracker.domain.BugExample;
import com.esofthead.mycollab.module.tracker.domain.BugWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BugMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_bug
     *
     * @mbggenerated Thu Jun 06 11:18:22 GMT+07:00 2013
     */
    int countByExample(BugExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_bug
     *
     * @mbggenerated Thu Jun 06 11:18:22 GMT+07:00 2013
     */
    int deleteByExample(BugExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_bug
     *
     * @mbggenerated Thu Jun 06 11:18:22 GMT+07:00 2013
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_bug
     *
     * @mbggenerated Thu Jun 06 11:18:22 GMT+07:00 2013
     */
    int insert(BugWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_bug
     *
     * @mbggenerated Thu Jun 06 11:18:22 GMT+07:00 2013
     */
    int insertSelective(BugWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_bug
     *
     * @mbggenerated Thu Jun 06 11:18:22 GMT+07:00 2013
     */
    List<BugWithBLOBs> selectByExampleWithBLOBs(BugExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_bug
     *
     * @mbggenerated Thu Jun 06 11:18:22 GMT+07:00 2013
     */
    BugWithBLOBs selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_bug
     *
     * @mbggenerated Thu Jun 06 11:18:22 GMT+07:00 2013
     */
    int updateByExampleSelective(@Param("record") BugWithBLOBs record, @Param("example") BugExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_bug
     *
     * @mbggenerated Thu Jun 06 11:18:22 GMT+07:00 2013
     */
    int updateByExampleWithBLOBs(@Param("record") BugWithBLOBs record, @Param("example") BugExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_bug
     *
     * @mbggenerated Thu Jun 06 11:18:22 GMT+07:00 2013
     */
    int updateByPrimaryKeySelective(BugWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_bug
     *
     * @mbggenerated Thu Jun 06 11:18:22 GMT+07:00 2013
     */
    Integer insertAndReturnKey(BugWithBLOBs value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_bug
     *
     * @mbggenerated Thu Jun 06 11:18:22 GMT+07:00 2013
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_bug
     *
     * @mbggenerated Thu Jun 06 11:18:22 GMT+07:00 2013
     */
    void massUpdateWithSession(@Param("record") BugWithBLOBs record, @Param("primaryKeys") List primaryKeys);
}