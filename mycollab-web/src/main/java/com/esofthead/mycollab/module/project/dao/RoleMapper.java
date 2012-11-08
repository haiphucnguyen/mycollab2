package com.esofthead.mycollab.module.project.dao;

import com.esofthead.mycollab.core.persistence.CrudGenericDAO;
import com.esofthead.mycollab.module.project.domain.Role;
import com.esofthead.mycollab.module.project.domain.RoleExample;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RoleMapper extends CrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_role
     *
     * @mbggenerated Wed Nov 07 18:15:45 GMT+07:00 2012
     */
    int countByExample(RoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_role
     *
     * @mbggenerated Wed Nov 07 18:15:45 GMT+07:00 2012
     */
    int deleteByExample(RoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_role
     *
     * @mbggenerated Wed Nov 07 18:15:45 GMT+07:00 2012
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_role
     *
     * @mbggenerated Wed Nov 07 18:15:45 GMT+07:00 2012
     */
    int insert(Role record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_role
     *
     * @mbggenerated Wed Nov 07 18:15:45 GMT+07:00 2012
     */
    int insertSelective(Role record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_role
     *
     * @mbggenerated Wed Nov 07 18:15:45 GMT+07:00 2012
     */
    List<Role> selectByExample(RoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_role
     *
     * @mbggenerated Wed Nov 07 18:15:45 GMT+07:00 2012
     */
    Role selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_role
     *
     * @mbggenerated Wed Nov 07 18:15:45 GMT+07:00 2012
     */
    int updateByExampleSelective(@Param("record") Role record, @Param("example") RoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_role
     *
     * @mbggenerated Wed Nov 07 18:15:45 GMT+07:00 2012
     */
    int updateByExample(@Param("record") Role record, @Param("example") RoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_role
     *
     * @mbggenerated Wed Nov 07 18:15:45 GMT+07:00 2012
     */
    int updateByPrimaryKeySelective(Role record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_role
     *
     * @mbggenerated Wed Nov 07 18:15:45 GMT+07:00 2012
     */
    int updateByPrimaryKey(Role record);
}