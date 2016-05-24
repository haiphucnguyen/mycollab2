package com.esofthead.mycollab.module.tracker.dao;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.module.tracker.domain.Version;
import com.esofthead.mycollab.module.tracker.domain.VersionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@SuppressWarnings({ "ucd", "rawtypes" })
public interface VersionMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_version
     *
     * @mbggenerated Tue May 24 18:34:15 ICT 2016
     */
    int countByExample(VersionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_version
     *
     * @mbggenerated Tue May 24 18:34:15 ICT 2016
     */
    int deleteByExample(VersionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_version
     *
     * @mbggenerated Tue May 24 18:34:15 ICT 2016
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_version
     *
     * @mbggenerated Tue May 24 18:34:15 ICT 2016
     */
    int insert(Version record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_version
     *
     * @mbggenerated Tue May 24 18:34:15 ICT 2016
     */
    int insertSelective(Version record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_version
     *
     * @mbggenerated Tue May 24 18:34:15 ICT 2016
     */
    List<Version> selectByExampleWithBLOBs(VersionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_version
     *
     * @mbggenerated Tue May 24 18:34:15 ICT 2016
     */
    List<Version> selectByExample(VersionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_version
     *
     * @mbggenerated Tue May 24 18:34:15 ICT 2016
     */
    Version selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_version
     *
     * @mbggenerated Tue May 24 18:34:15 ICT 2016
     */
    int updateByExampleSelective(@Param("record") Version record, @Param("example") VersionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_version
     *
     * @mbggenerated Tue May 24 18:34:15 ICT 2016
     */
    int updateByExampleWithBLOBs(@Param("record") Version record, @Param("example") VersionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_version
     *
     * @mbggenerated Tue May 24 18:34:15 ICT 2016
     */
    int updateByExample(@Param("record") Version record, @Param("example") VersionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_version
     *
     * @mbggenerated Tue May 24 18:34:15 ICT 2016
     */
    int updateByPrimaryKeySelective(Version record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_version
     *
     * @mbggenerated Tue May 24 18:34:15 ICT 2016
     */
    int updateByPrimaryKeyWithBLOBs(Version record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_version
     *
     * @mbggenerated Tue May 24 18:34:15 ICT 2016
     */
    int updateByPrimaryKey(Version record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_version
     *
     * @mbggenerated Tue May 24 18:34:15 ICT 2016
     */
    Integer insertAndReturnKey(Version value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_version
     *
     * @mbggenerated Tue May 24 18:34:15 ICT 2016
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_version
     *
     * @mbggenerated Tue May 24 18:34:15 ICT 2016
     */
    void massUpdateWithSession(@Param("record") Version record, @Param("primaryKeys") List primaryKeys);
}