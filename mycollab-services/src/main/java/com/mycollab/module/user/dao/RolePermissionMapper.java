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
     * @mbg.generated Thu May 31 21:45:18 ICT 2018
     */
    long countByExample(RolePermissionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_role_permission
     *
     * @mbg.generated Thu May 31 21:45:18 ICT 2018
     */
    int deleteByExample(RolePermissionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_role_permission
     *
     * @mbg.generated Thu May 31 21:45:18 ICT 2018
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_role_permission
     *
     * @mbg.generated Thu May 31 21:45:18 ICT 2018
     */
    int insert(RolePermission record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_role_permission
     *
     * @mbg.generated Thu May 31 21:45:18 ICT 2018
     */
    int insertSelective(RolePermission record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_role_permission
     *
     * @mbg.generated Thu May 31 21:45:18 ICT 2018
     */
    List<RolePermission> selectByExampleWithBLOBs(RolePermissionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_role_permission
     *
     * @mbg.generated Thu May 31 21:45:18 ICT 2018
     */
    List<RolePermission> selectByExample(RolePermissionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_role_permission
     *
     * @mbg.generated Thu May 31 21:45:18 ICT 2018
     */
    RolePermission selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_role_permission
     *
     * @mbg.generated Thu May 31 21:45:18 ICT 2018
     */
    int updateByExampleSelective(@Param("record") RolePermission record, @Param("example") RolePermissionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_role_permission
     *
     * @mbg.generated Thu May 31 21:45:18 ICT 2018
     */
    int updateByExampleWithBLOBs(@Param("record") RolePermission record, @Param("example") RolePermissionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_role_permission
     *
     * @mbg.generated Thu May 31 21:45:18 ICT 2018
     */
    int updateByExample(@Param("record") RolePermission record, @Param("example") RolePermissionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_role_permission
     *
     * @mbg.generated Thu May 31 21:45:18 ICT 2018
     */
    int updateByPrimaryKeySelective(RolePermission record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_role_permission
     *
     * @mbg.generated Thu May 31 21:45:18 ICT 2018
     */
    int updateByPrimaryKeyWithBLOBs(RolePermission record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_role_permission
     *
     * @mbg.generated Thu May 31 21:45:18 ICT 2018
     */
    int updateByPrimaryKey(RolePermission record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_role_permission
     *
     * @mbg.generated Thu May 31 21:45:18 ICT 2018
     */
    Integer insertAndReturnKey(RolePermission value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_role_permission
     *
     * @mbg.generated Thu May 31 21:45:18 ICT 2018
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_role_permission
     *
     * @mbg.generated Thu May 31 21:45:18 ICT 2018
     */
    void massUpdateWithSession(@Param("record") RolePermission record, @Param("primaryKeys") List primaryKeys);
}