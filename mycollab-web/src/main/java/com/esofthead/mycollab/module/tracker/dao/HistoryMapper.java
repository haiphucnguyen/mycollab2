package com.esofthead.mycollab.module.tracker.dao;

import com.esofthead.mycollab.core.persistence.CrudGenericDAO;
import com.esofthead.mycollab.module.tracker.domain.History;
import com.esofthead.mycollab.module.tracker.domain.HistoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HistoryMapper extends CrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_history
     *
     * @mbggenerated Thu Nov 08 02:34:48 GMT+07:00 2012
     */
    int countByExample(HistoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_history
     *
     * @mbggenerated Thu Nov 08 02:34:48 GMT+07:00 2012
     */
    int deleteByExample(HistoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_history
     *
     * @mbggenerated Thu Nov 08 02:34:48 GMT+07:00 2012
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_history
     *
     * @mbggenerated Thu Nov 08 02:34:48 GMT+07:00 2012
     */
    int insert(History record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_history
     *
     * @mbggenerated Thu Nov 08 02:34:48 GMT+07:00 2012
     */
    int insertSelective(History record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_history
     *
     * @mbggenerated Thu Nov 08 02:34:48 GMT+07:00 2012
     */
    List<History> selectByExample(HistoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_history
     *
     * @mbggenerated Thu Nov 08 02:34:48 GMT+07:00 2012
     */
    History selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_history
     *
     * @mbggenerated Thu Nov 08 02:34:48 GMT+07:00 2012
     */
    int updateByExampleSelective(@Param("record") History record, @Param("example") HistoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_history
     *
     * @mbggenerated Thu Nov 08 02:34:48 GMT+07:00 2012
     */
    int updateByExample(@Param("record") History record, @Param("example") HistoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_history
     *
     * @mbggenerated Thu Nov 08 02:34:48 GMT+07:00 2012
     */
    int updateByPrimaryKeySelective(History record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_history
     *
     * @mbggenerated Thu Nov 08 02:34:48 GMT+07:00 2012
     */
    int updateByPrimaryKey(History record);
}