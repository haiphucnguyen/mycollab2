package com.esofthead.mycollab.module.tracker.dao;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.module.tracker.domain.Query;
import com.esofthead.mycollab.module.tracker.domain.QueryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface QueryMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_query
     *
     * @mbggenerated Wed Apr 17 15:12:12 GMT+07:00 2013
     */
    int countByExample(QueryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_query
     *
     * @mbggenerated Wed Apr 17 15:12:12 GMT+07:00 2013
     */
    int deleteByExample(QueryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_query
     *
     * @mbggenerated Wed Apr 17 15:12:12 GMT+07:00 2013
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_query
     *
     * @mbggenerated Wed Apr 17 15:12:12 GMT+07:00 2013
     */
    int insert(Query record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_query
     *
     * @mbggenerated Wed Apr 17 15:12:12 GMT+07:00 2013
     */
    int insertSelective(Query record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_query
     *
     * @mbggenerated Wed Apr 17 15:12:12 GMT+07:00 2013
     */
    List<Query> selectByExample(QueryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_query
     *
     * @mbggenerated Wed Apr 17 15:12:12 GMT+07:00 2013
     */
    Query selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_query
     *
     * @mbggenerated Wed Apr 17 15:12:12 GMT+07:00 2013
     */
    int updateByExampleSelective(@Param("record") Query record, @Param("example") QueryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_query
     *
     * @mbggenerated Wed Apr 17 15:12:12 GMT+07:00 2013
     */
    int updateByExample(@Param("record") Query record, @Param("example") QueryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_query
     *
     * @mbggenerated Wed Apr 17 15:12:12 GMT+07:00 2013
     */
    int updateByPrimaryKeySelective(Query record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_query
     *
     * @mbggenerated Wed Apr 17 15:12:12 GMT+07:00 2013
     */
    Integer insertAndReturnKey(Query value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_query
     *
     * @mbggenerated Wed Apr 17 15:12:12 GMT+07:00 2013
     */
    void removeKeysWithSession(List primaryKeys);
}