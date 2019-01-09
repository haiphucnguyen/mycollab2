package com.mycollab.module.project.dao;

import com.mycollab.db.persistence.ICrudGenericDAO;
import com.mycollab.module.project.domain.Milestone;
import com.mycollab.module.project.domain.MilestoneExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@SuppressWarnings({ "ucd", "rawtypes" })
public interface MilestoneMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_milestone
     *
     * @mbg.generated Tue Jan 08 20:44:16 CST 2019
     */
    long countByExample(MilestoneExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_milestone
     *
     * @mbg.generated Tue Jan 08 20:44:16 CST 2019
     */
    int deleteByExample(MilestoneExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_milestone
     *
     * @mbg.generated Tue Jan 08 20:44:16 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_milestone
     *
     * @mbg.generated Tue Jan 08 20:44:16 CST 2019
     */
    int insert(Milestone record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_milestone
     *
     * @mbg.generated Tue Jan 08 20:44:16 CST 2019
     */
    int insertSelective(Milestone record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_milestone
     *
     * @mbg.generated Tue Jan 08 20:44:16 CST 2019
     */
    List<Milestone> selectByExampleWithBLOBs(MilestoneExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_milestone
     *
     * @mbg.generated Tue Jan 08 20:44:16 CST 2019
     */
    List<Milestone> selectByExample(MilestoneExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_milestone
     *
     * @mbg.generated Tue Jan 08 20:44:16 CST 2019
     */
    Milestone selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_milestone
     *
     * @mbg.generated Tue Jan 08 20:44:16 CST 2019
     */
    int updateByExampleSelective(@Param("record") Milestone record, @Param("example") MilestoneExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_milestone
     *
     * @mbg.generated Tue Jan 08 20:44:16 CST 2019
     */
    int updateByExampleWithBLOBs(@Param("record") Milestone record, @Param("example") MilestoneExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_milestone
     *
     * @mbg.generated Tue Jan 08 20:44:16 CST 2019
     */
    int updateByExample(@Param("record") Milestone record, @Param("example") MilestoneExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_milestone
     *
     * @mbg.generated Tue Jan 08 20:44:16 CST 2019
     */
    int updateByPrimaryKeySelective(Milestone record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_milestone
     *
     * @mbg.generated Tue Jan 08 20:44:16 CST 2019
     */
    int updateByPrimaryKeyWithBLOBs(Milestone record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_milestone
     *
     * @mbg.generated Tue Jan 08 20:44:16 CST 2019
     */
    int updateByPrimaryKey(Milestone record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_milestone
     *
     * @mbg.generated Tue Jan 08 20:44:16 CST 2019
     */
    Integer insertAndReturnKey(Milestone value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_milestone
     *
     * @mbg.generated Tue Jan 08 20:44:16 CST 2019
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_milestone
     *
     * @mbg.generated Tue Jan 08 20:44:16 CST 2019
     */
    void massUpdateWithSession(@Param("record") Milestone record, @Param("primaryKeys") List primaryKeys);
}