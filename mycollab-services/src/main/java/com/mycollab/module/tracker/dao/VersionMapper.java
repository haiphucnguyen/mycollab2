package com.mycollab.module.tracker.dao;

import com.mycollab.db.persistence.ICrudGenericDAO;
import com.mycollab.module.tracker.domain.Version;
import com.mycollab.module.tracker.domain.VersionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@SuppressWarnings({ "ucd", "rawtypes" })
public interface VersionMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_version
     *
     * @mbg.generated Sat Jan 19 02:08:00 CST 2019
     */
    long countByExample(VersionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_version
     *
     * @mbg.generated Sat Jan 19 02:08:00 CST 2019
     */
    int deleteByExample(VersionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_version
     *
     * @mbg.generated Sat Jan 19 02:08:00 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_version
     *
     * @mbg.generated Sat Jan 19 02:08:00 CST 2019
     */
    int insert(Version record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_version
     *
     * @mbg.generated Sat Jan 19 02:08:00 CST 2019
     */
    int insertSelective(Version record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_version
     *
     * @mbg.generated Sat Jan 19 02:08:00 CST 2019
     */
    List<Version> selectByExampleWithBLOBs(VersionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_version
     *
     * @mbg.generated Sat Jan 19 02:08:00 CST 2019
     */
    List<Version> selectByExample(VersionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_version
     *
     * @mbg.generated Sat Jan 19 02:08:00 CST 2019
     */
    Version selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_version
     *
     * @mbg.generated Sat Jan 19 02:08:00 CST 2019
     */
    int updateByExampleSelective(@Param("record") Version record, @Param("example") VersionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_version
     *
     * @mbg.generated Sat Jan 19 02:08:00 CST 2019
     */
    int updateByExampleWithBLOBs(@Param("record") Version record, @Param("example") VersionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_version
     *
     * @mbg.generated Sat Jan 19 02:08:00 CST 2019
     */
    int updateByExample(@Param("record") Version record, @Param("example") VersionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_version
     *
     * @mbg.generated Sat Jan 19 02:08:00 CST 2019
     */
    int updateByPrimaryKeySelective(Version record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_version
     *
     * @mbg.generated Sat Jan 19 02:08:00 CST 2019
     */
    int updateByPrimaryKeyWithBLOBs(Version record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_version
     *
     * @mbg.generated Sat Jan 19 02:08:00 CST 2019
     */
    int updateByPrimaryKey(Version record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_version
     *
     * @mbg.generated Sat Jan 19 02:08:00 CST 2019
     */
    Integer insertAndReturnKey(Version value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_version
     *
     * @mbg.generated Sat Jan 19 02:08:00 CST 2019
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_version
     *
     * @mbg.generated Sat Jan 19 02:08:00 CST 2019
     */
    void massUpdateWithSession(@Param("record") Version record, @Param("primaryKeys") List primaryKeys);
}