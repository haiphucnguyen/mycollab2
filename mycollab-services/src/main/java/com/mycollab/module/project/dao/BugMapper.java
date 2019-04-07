package com.mycollab.module.project.dao;

import com.mycollab.db.persistence.ICrudGenericDAO;
import com.mycollab.module.project.domain.BugExample;
import com.mycollab.module.project.domain.BugWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@SuppressWarnings({ "ucd", "rawtypes" })
@Mapper
public interface BugMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_bug
     *
     * @mbg.generated Sun Apr 07 08:39:28 CDT 2019
     */
    long countByExample(BugExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_bug
     *
     * @mbg.generated Sun Apr 07 08:39:28 CDT 2019
     */
    int deleteByExample(BugExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_bug
     *
     * @mbg.generated Sun Apr 07 08:39:28 CDT 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_bug
     *
     * @mbg.generated Sun Apr 07 08:39:28 CDT 2019
     */
    int insert(BugWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_bug
     *
     * @mbg.generated Sun Apr 07 08:39:28 CDT 2019
     */
    int insertSelective(BugWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_bug
     *
     * @mbg.generated Sun Apr 07 08:39:28 CDT 2019
     */
    List<BugWithBLOBs> selectByExampleWithBLOBs(BugExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_bug
     *
     * @mbg.generated Sun Apr 07 08:39:28 CDT 2019
     */
    BugWithBLOBs selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_bug
     *
     * @mbg.generated Sun Apr 07 08:39:28 CDT 2019
     */
    int updateByExampleSelective(@Param("record") BugWithBLOBs record, @Param("example") BugExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_bug
     *
     * @mbg.generated Sun Apr 07 08:39:28 CDT 2019
     */
    int updateByExampleWithBLOBs(@Param("record") BugWithBLOBs record, @Param("example") BugExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_bug
     *
     * @mbg.generated Sun Apr 07 08:39:28 CDT 2019
     */
    int updateByPrimaryKeySelective(BugWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_bug
     *
     * @mbg.generated Sun Apr 07 08:39:28 CDT 2019
     */
    int updateByPrimaryKeyWithBLOBs(BugWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_bug
     *
     * @mbg.generated Sun Apr 07 08:39:28 CDT 2019
     */
    Integer insertAndReturnKey(BugWithBLOBs value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_bug
     *
     * @mbg.generated Sun Apr 07 08:39:28 CDT 2019
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_bug
     *
     * @mbg.generated Sun Apr 07 08:39:28 CDT 2019
     */
    void massUpdateWithSession(@Param("record") BugWithBLOBs record, @Param("primaryKeys") List primaryKeys);
}