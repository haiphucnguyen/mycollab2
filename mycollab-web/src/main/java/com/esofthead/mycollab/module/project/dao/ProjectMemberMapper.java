package com.esofthead.mycollab.module.project.dao;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.module.project.domain.ProjectMember;
import com.esofthead.mycollab.module.project.domain.ProjectMemberExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProjectMemberMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_member
     *
     * @mbggenerated Mon Feb 25 15:56:15 GMT+07:00 2013
     */
    int countByExample(ProjectMemberExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_member
     *
     * @mbggenerated Mon Feb 25 15:56:15 GMT+07:00 2013
     */
    int deleteByExample(ProjectMemberExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_member
     *
     * @mbggenerated Mon Feb 25 15:56:15 GMT+07:00 2013
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_member
     *
     * @mbggenerated Mon Feb 25 15:56:15 GMT+07:00 2013
     */
    int insert(ProjectMember record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_member
     *
     * @mbggenerated Mon Feb 25 15:56:15 GMT+07:00 2013
     */
    int insertSelective(ProjectMember record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_member
     *
     * @mbggenerated Mon Feb 25 15:56:15 GMT+07:00 2013
     */
    List<ProjectMember> selectByExample(ProjectMemberExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_member
     *
     * @mbggenerated Mon Feb 25 15:56:15 GMT+07:00 2013
     */
    ProjectMember selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_member
     *
     * @mbggenerated Mon Feb 25 15:56:15 GMT+07:00 2013
     */
    int updateByExampleSelective(@Param("record") ProjectMember record, @Param("example") ProjectMemberExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_member
     *
     * @mbggenerated Mon Feb 25 15:56:15 GMT+07:00 2013
     */
    int updateByExample(@Param("record") ProjectMember record, @Param("example") ProjectMemberExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_member
     *
     * @mbggenerated Mon Feb 25 15:56:15 GMT+07:00 2013
     */
    int updateByPrimaryKeySelective(ProjectMember record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_member
     *
     * @mbggenerated Mon Feb 25 15:56:15 GMT+07:00 2013
     */
    int updateByPrimaryKey(ProjectMember record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_member
     *
     * @mbggenerated Mon Feb 25 15:56:15 GMT+07:00 2013
     */
    Integer insertAndReturnKey(ProjectMember value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_member
     *
     * @mbggenerated Mon Feb 25 15:56:15 GMT+07:00 2013
     */
    void removeKeysWithSession(List primaryKeys);
}