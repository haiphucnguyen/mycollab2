package com.mycollab.module.project.dao;

import com.mycollab.db.persistence.ICrudGenericDAO;
import com.mycollab.module.project.domain.ProjectMember;
import com.mycollab.module.project.domain.ProjectMemberExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@SuppressWarnings({ "ucd", "rawtypes" })
public interface ProjectMemberMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_member
     *
     * @mbg.generated Fri Jan 04 16:43:09 CST 2019
     */
    long countByExample(ProjectMemberExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_member
     *
     * @mbg.generated Fri Jan 04 16:43:09 CST 2019
     */
    int deleteByExample(ProjectMemberExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_member
     *
     * @mbg.generated Fri Jan 04 16:43:09 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_member
     *
     * @mbg.generated Fri Jan 04 16:43:09 CST 2019
     */
    int insert(ProjectMember record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_member
     *
     * @mbg.generated Fri Jan 04 16:43:09 CST 2019
     */
    int insertSelective(ProjectMember record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_member
     *
     * @mbg.generated Fri Jan 04 16:43:09 CST 2019
     */
    List<ProjectMember> selectByExample(ProjectMemberExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_member
     *
     * @mbg.generated Fri Jan 04 16:43:09 CST 2019
     */
    ProjectMember selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_member
     *
     * @mbg.generated Fri Jan 04 16:43:09 CST 2019
     */
    int updateByExampleSelective(@Param("record") ProjectMember record, @Param("example") ProjectMemberExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_member
     *
     * @mbg.generated Fri Jan 04 16:43:09 CST 2019
     */
    int updateByExample(@Param("record") ProjectMember record, @Param("example") ProjectMemberExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_member
     *
     * @mbg.generated Fri Jan 04 16:43:09 CST 2019
     */
    int updateByPrimaryKeySelective(ProjectMember record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_member
     *
     * @mbg.generated Fri Jan 04 16:43:09 CST 2019
     */
    int updateByPrimaryKey(ProjectMember record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_member
     *
     * @mbg.generated Fri Jan 04 16:43:09 CST 2019
     */
    Integer insertAndReturnKey(ProjectMember value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_member
     *
     * @mbg.generated Fri Jan 04 16:43:09 CST 2019
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_member
     *
     * @mbg.generated Fri Jan 04 16:43:09 CST 2019
     */
    void massUpdateWithSession(@Param("record") ProjectMember record, @Param("primaryKeys") List primaryKeys);
}