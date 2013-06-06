package com.esofthead.mycollab.module.tracker.dao;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.module.tracker.domain.MetaData;
import com.esofthead.mycollab.module.tracker.domain.MetaDataExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MetaDataMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_metadata
     *
     * @mbggenerated Thu Jun 06 11:18:22 GMT+07:00 2013
     */
    int countByExample(MetaDataExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_metadata
     *
     * @mbggenerated Thu Jun 06 11:18:22 GMT+07:00 2013
     */
    int deleteByExample(MetaDataExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_metadata
     *
     * @mbggenerated Thu Jun 06 11:18:22 GMT+07:00 2013
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_metadata
     *
     * @mbggenerated Thu Jun 06 11:18:22 GMT+07:00 2013
     */
    int insert(MetaData record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_metadata
     *
     * @mbggenerated Thu Jun 06 11:18:22 GMT+07:00 2013
     */
    int insertSelective(MetaData record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_metadata
     *
     * @mbggenerated Thu Jun 06 11:18:22 GMT+07:00 2013
     */
    List<MetaData> selectByExampleWithBLOBs(MetaDataExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_metadata
     *
     * @mbggenerated Thu Jun 06 11:18:22 GMT+07:00 2013
     */
    List<MetaData> selectByExample(MetaDataExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_metadata
     *
     * @mbggenerated Thu Jun 06 11:18:22 GMT+07:00 2013
     */
    MetaData selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_metadata
     *
     * @mbggenerated Thu Jun 06 11:18:22 GMT+07:00 2013
     */
    int updateByExampleSelective(@Param("record") MetaData record, @Param("example") MetaDataExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_metadata
     *
     * @mbggenerated Thu Jun 06 11:18:22 GMT+07:00 2013
     */
    int updateByExampleWithBLOBs(@Param("record") MetaData record, @Param("example") MetaDataExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_metadata
     *
     * @mbggenerated Thu Jun 06 11:18:22 GMT+07:00 2013
     */
    int updateByExample(@Param("record") MetaData record, @Param("example") MetaDataExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_metadata
     *
     * @mbggenerated Thu Jun 06 11:18:22 GMT+07:00 2013
     */
    int updateByPrimaryKeySelective(MetaData record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_metadata
     *
     * @mbggenerated Thu Jun 06 11:18:22 GMT+07:00 2013
     */
    Integer insertAndReturnKey(MetaData value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_metadata
     *
     * @mbggenerated Thu Jun 06 11:18:22 GMT+07:00 2013
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_metadata
     *
     * @mbggenerated Thu Jun 06 11:18:22 GMT+07:00 2013
     */
    void massUpdateWithSession(@Param("record") MetaData record, @Param("primaryKeys") List primaryKeys);
}