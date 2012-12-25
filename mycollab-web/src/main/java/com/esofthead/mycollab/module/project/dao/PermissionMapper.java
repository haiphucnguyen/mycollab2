package com.esofthead.mycollab.module.project.dao;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.module.project.domain.Permission;
import com.esofthead.mycollab.module.project.domain.PermissionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PermissionMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_permission
     *
     * @mbggenerated Tue Dec 25 17:18:14 GMT+07:00 2012
     */
    int countByExample(PermissionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_permission
     *
     * @mbggenerated Tue Dec 25 17:18:14 GMT+07:00 2012
     */
    int deleteByExample(PermissionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_permission
     *
     * @mbggenerated Tue Dec 25 17:18:14 GMT+07:00 2012
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_permission
     *
     * @mbggenerated Tue Dec 25 17:18:14 GMT+07:00 2012
     */
    int insert(Permission record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_permission
     *
     * @mbggenerated Tue Dec 25 17:18:14 GMT+07:00 2012
     */
    int insertSelective(Permission record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_permission
     *
     * @mbggenerated Tue Dec 25 17:18:14 GMT+07:00 2012
     */
    List<Permission> selectByExample(PermissionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_permission
     *
     * @mbggenerated Tue Dec 25 17:18:14 GMT+07:00 2012
     */
    Permission selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_permission
     *
     * @mbggenerated Tue Dec 25 17:18:14 GMT+07:00 2012
     */
    int updateByExampleSelective(@Param("record") Permission record, @Param("example") PermissionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_permission
     *
     * @mbggenerated Tue Dec 25 17:18:14 GMT+07:00 2012
     */
    int updateByExample(@Param("record") Permission record, @Param("example") PermissionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_permission
     *
     * @mbggenerated Tue Dec 25 17:18:14 GMT+07:00 2012
     */
    int updateByPrimaryKeySelective(Permission record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_permission
     *
     * @mbggenerated Tue Dec 25 17:18:14 GMT+07:00 2012
     */
    int updateByPrimaryKey(Permission record);

    Integer insertAndReturnKey(Permission value);
}