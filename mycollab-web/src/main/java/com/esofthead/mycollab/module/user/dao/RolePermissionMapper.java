package com.esofthead.mycollab.module.user.dao;

import com.esofthead.mycollab.core.persistence.CrudGenericDAO;
import com.esofthead.mycollab.module.user.domain.RolePermission;
import com.esofthead.mycollab.module.user.domain.RolePermissionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RolePermissionMapper extends CrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_role_permission
     *
     * @mbggenerated Fri Oct 26 11:17:51 GMT+07:00 2012
     */
    int countByExample(RolePermissionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_role_permission
     *
     * @mbggenerated Fri Oct 26 11:17:51 GMT+07:00 2012
     */
    int deleteByExample(RolePermissionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_role_permission
     *
     * @mbggenerated Fri Oct 26 11:17:51 GMT+07:00 2012
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_role_permission
     *
     * @mbggenerated Fri Oct 26 11:17:51 GMT+07:00 2012
     */
    int insert(RolePermission record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_role_permission
     *
     * @mbggenerated Fri Oct 26 11:17:51 GMT+07:00 2012
     */
    int insertSelective(RolePermission record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_role_permission
     *
     * @mbggenerated Fri Oct 26 11:17:51 GMT+07:00 2012
     */
    List<RolePermission> selectByExample(RolePermissionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_role_permission
     *
     * @mbggenerated Fri Oct 26 11:17:51 GMT+07:00 2012
     */
    RolePermission selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_role_permission
     *
     * @mbggenerated Fri Oct 26 11:17:51 GMT+07:00 2012
     */
    int updateByExampleSelective(@Param("record") RolePermission record, @Param("example") RolePermissionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_role_permission
     *
     * @mbggenerated Fri Oct 26 11:17:51 GMT+07:00 2012
     */
    int updateByExample(@Param("record") RolePermission record, @Param("example") RolePermissionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_role_permission
     *
     * @mbggenerated Fri Oct 26 11:17:51 GMT+07:00 2012
     */
    int updateByPrimaryKeySelective(RolePermission record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_role_permission
     *
     * @mbggenerated Fri Oct 26 11:17:51 GMT+07:00 2012
     */
    int updateByPrimaryKey(RolePermission record);
}