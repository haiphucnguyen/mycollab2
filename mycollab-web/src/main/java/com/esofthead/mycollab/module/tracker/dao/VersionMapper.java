package com.esofthead.mycollab.module.tracker.dao;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.module.tracker.domain.Version;
import com.esofthead.mycollab.module.tracker.domain.VersionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface VersionMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_version
     *
     * @mbggenerated Mon Jan 14 15:29:26 GMT+07:00 2013
     */
    int countByExample(VersionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_version
     *
     * @mbggenerated Mon Jan 14 15:29:26 GMT+07:00 2013
     */
    int deleteByExample(VersionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_version
     *
     * @mbggenerated Mon Jan 14 15:29:26 GMT+07:00 2013
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_version
     *
     * @mbggenerated Mon Jan 14 15:29:26 GMT+07:00 2013
     */
    int insert(Version record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_version
     *
     * @mbggenerated Mon Jan 14 15:29:26 GMT+07:00 2013
     */
    int insertSelective(Version record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_version
     *
     * @mbggenerated Mon Jan 14 15:29:26 GMT+07:00 2013
     */
    List<Version> selectByExample(VersionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_version
     *
     * @mbggenerated Mon Jan 14 15:29:26 GMT+07:00 2013
     */
    Version selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_version
     *
     * @mbggenerated Mon Jan 14 15:29:26 GMT+07:00 2013
     */
    int updateByExampleSelective(@Param("record") Version record, @Param("example") VersionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_version
     *
     * @mbggenerated Mon Jan 14 15:29:26 GMT+07:00 2013
     */
    int updateByExample(@Param("record") Version record, @Param("example") VersionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_version
     *
     * @mbggenerated Mon Jan 14 15:29:26 GMT+07:00 2013
     */
    int updateByPrimaryKeySelective(Version record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_version
     *
     * @mbggenerated Mon Jan 14 15:29:26 GMT+07:00 2013
     */
    int updateByPrimaryKey(Version record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_version
     *
     * @mbggenerated Mon Jan 14 15:29:26 GMT+07:00 2013
     */
    Integer insertAndReturnKey(Version value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_version
     *
     * @mbggenerated Mon Jan 14 15:29:26 GMT+07:00 2013
     */
    void removeKeysWithSession(List primaryKeys);
}