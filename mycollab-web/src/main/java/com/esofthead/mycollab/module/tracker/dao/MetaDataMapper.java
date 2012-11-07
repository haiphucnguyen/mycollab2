package com.esofthead.mycollab.module.tracker.dao;

import com.esofthead.mycollab.core.persistence.CrudGenericDAO;
import com.esofthead.mycollab.module.tracker.domain.MetaData;
import com.esofthead.mycollab.module.tracker.domain.MetaDataExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MetaDataMapper extends CrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_metadata
     *
     * @mbggenerated Thu Nov 08 02:34:48 GMT+07:00 2012
     */
    int countByExample(MetaDataExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_metadata
     *
     * @mbggenerated Thu Nov 08 02:34:48 GMT+07:00 2012
     */
    int deleteByExample(MetaDataExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_metadata
     *
     * @mbggenerated Thu Nov 08 02:34:48 GMT+07:00 2012
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_metadata
     *
     * @mbggenerated Thu Nov 08 02:34:48 GMT+07:00 2012
     */
    int insert(MetaData record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_metadata
     *
     * @mbggenerated Thu Nov 08 02:34:48 GMT+07:00 2012
     */
    int insertSelective(MetaData record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_metadata
     *
     * @mbggenerated Thu Nov 08 02:34:48 GMT+07:00 2012
     */
    List<MetaData> selectByExampleWithBLOBs(MetaDataExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_metadata
     *
     * @mbggenerated Thu Nov 08 02:34:48 GMT+07:00 2012
     */
    List<MetaData> selectByExample(MetaDataExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_metadata
     *
     * @mbggenerated Thu Nov 08 02:34:48 GMT+07:00 2012
     */
    MetaData selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_metadata
     *
     * @mbggenerated Thu Nov 08 02:34:48 GMT+07:00 2012
     */
    int updateByExampleSelective(@Param("record") MetaData record, @Param("example") MetaDataExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_metadata
     *
     * @mbggenerated Thu Nov 08 02:34:48 GMT+07:00 2012
     */
    int updateByExampleWithBLOBs(@Param("record") MetaData record, @Param("example") MetaDataExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_metadata
     *
     * @mbggenerated Thu Nov 08 02:34:48 GMT+07:00 2012
     */
    int updateByExample(@Param("record") MetaData record, @Param("example") MetaDataExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_metadata
     *
     * @mbggenerated Thu Nov 08 02:34:48 GMT+07:00 2012
     */
    int updateByPrimaryKeySelective(MetaData record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_metadata
     *
     * @mbggenerated Thu Nov 08 02:34:48 GMT+07:00 2012
     */
    int updateByPrimaryKeyWithBLOBs(MetaData record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_metadata
     *
     * @mbggenerated Thu Nov 08 02:34:48 GMT+07:00 2012
     */
    int updateByPrimaryKey(MetaData record);
}