package com.esofthead.mycollab.module.tracker.dao;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.module.tracker.domain.Component;
import com.esofthead.mycollab.module.tracker.domain.ComponentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ComponentMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_component
     *
     * @mbggenerated Wed Jul 10 21:12:53 GMT+07:00 2013
     */
    int countByExample(ComponentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_component
     *
     * @mbggenerated Wed Jul 10 21:12:53 GMT+07:00 2013
     */
    int deleteByExample(ComponentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_component
     *
     * @mbggenerated Wed Jul 10 21:12:53 GMT+07:00 2013
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_component
     *
     * @mbggenerated Wed Jul 10 21:12:53 GMT+07:00 2013
     */
    int insert(Component record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_component
     *
     * @mbggenerated Wed Jul 10 21:12:53 GMT+07:00 2013
     */
    int insertSelective(Component record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_component
     *
     * @mbggenerated Wed Jul 10 21:12:53 GMT+07:00 2013
     */
    List<Component> selectByExampleWithBLOBs(ComponentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_component
     *
     * @mbggenerated Wed Jul 10 21:12:53 GMT+07:00 2013
     */
    List<Component> selectByExample(ComponentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_component
     *
     * @mbggenerated Wed Jul 10 21:12:53 GMT+07:00 2013
     */
    Component selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_component
     *
     * @mbggenerated Wed Jul 10 21:12:53 GMT+07:00 2013
     */
    int updateByExampleSelective(@Param("record") Component record, @Param("example") ComponentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_component
     *
     * @mbggenerated Wed Jul 10 21:12:53 GMT+07:00 2013
     */
    int updateByExampleWithBLOBs(@Param("record") Component record, @Param("example") ComponentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_component
     *
     * @mbggenerated Wed Jul 10 21:12:53 GMT+07:00 2013
     */
    int updateByExample(@Param("record") Component record, @Param("example") ComponentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_component
     *
     * @mbggenerated Wed Jul 10 21:12:53 GMT+07:00 2013
     */
    int updateByPrimaryKeySelective(Component record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_component
     *
     * @mbggenerated Wed Jul 10 21:12:53 GMT+07:00 2013
     */
    Integer insertAndReturnKey(Component value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_component
     *
     * @mbggenerated Wed Jul 10 21:12:53 GMT+07:00 2013
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_component
     *
     * @mbggenerated Wed Jul 10 21:12:53 GMT+07:00 2013
     */
    void massUpdateWithSession(@Param("record") Component record, @Param("primaryKeys") List primaryKeys);
}