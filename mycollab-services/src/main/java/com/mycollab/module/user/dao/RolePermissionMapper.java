package com.mycollab.module.user.dao;

import com.mycollab.db.persistence.ICrudGenericDAO;
import com.mycollab.module.user.domain.RolePermission;
import com.mycollab.module.user.domain.RolePermissionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@SuppressWarnings({ "ucd", "rawtypes" })
public interface RolePermissionMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_role_permission
     *
     * @mbg.generated Wed Jan 09 11:32:23 CST 2019
     */
    long countByExample(RolePermissionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_role_permission
     *
     * @mbg.generated Wed Jan 09 11:32:23 CST 2019
     */
    int deleteByExample(RolePermissionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_role_permission
     *
     * @mbg.generated Wed Jan 09 11:32:23 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_role_permission
     *
     * @mbg.generated Wed Jan 09 11:32:23 CST 2019
     */
    int insert(RolePermission record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_role_permission
     *
     * @mbg.generated Wed Jan 09 11:32:23 CST 2019
     */
    int insertSelective(RolePermission record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_role_permission
     *
     * @mbg.generated Wed Jan 09 11:32:23 CST 2019
     */
    List<RolePermission> selectByExampleWithBLOBs(RolePermissionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_role_permission
     *
     * @mbg.generated Wed Jan 09 11:32:23 CST 2019
     */
    List<RolePermission> selectByExample(RolePermissionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_role_permission
     *
     * @mbg.generated Wed Jan 09 11:32:23 CST 2019
     */
    RolePermission selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_role_permission
     *
     * @mbg.generated Wed Jan 09 11:32:23 CST 2019
     */
    int updateByExampleSelective(@Param("record") RolePermission record, @Param("example") RolePermissionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_role_permission
     *
     * @mbg.generated Wed Jan 09 11:32:23 CST 2019
     */
    int updateByExampleWithBLOBs(@Param("record") RolePermission record, @Param("example") RolePermissionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_role_permission
     *
     * @mbg.generated Wed Jan 09 11:32:23 CST 2019
     */
    int updateByExample(@Param("record") RolePermission record, @Param("example") RolePermissionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_role_permission
     *
     * @mbg.generated Wed Jan 09 11:32:23 CST 2019
     */
    int updateByPrimaryKeySelective(RolePermission record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_role_permission
     *
     * @mbg.generated Wed Jan 09 11:32:23 CST 2019
     */
    int updateByPrimaryKeyWithBLOBs(RolePermission record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_role_permission
     *
     * @mbg.generated Wed Jan 09 11:32:23 CST 2019
     */
    int updateByPrimaryKey(RolePermission record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_role_permission
     *
     * @mbg.generated Wed Jan 09 11:32:23 CST 2019
     */
    Integer insertAndReturnKey(RolePermission value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_role_permission
     *
     * @mbg.generated Wed Jan 09 11:32:23 CST 2019
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_role_permission
     *
     * @mbg.generated Wed Jan 09 11:32:23 CST 2019
     */
    void massUpdateWithSession(@Param("record") RolePermission record, @Param("primaryKeys") List primaryKeys);
}