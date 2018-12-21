package com.mycollab.module.user.dao;

import com.mycollab.db.persistence.ICrudGenericDAO;
import com.mycollab.module.user.domain.UserPermission;
import com.mycollab.module.user.domain.UserPermissionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@SuppressWarnings({ "ucd", "rawtypes" })
public interface UserPermissionMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_permission
     *
     * @mbg.generated Fri Dec 21 03:28:33 CST 2018
     */
    long countByExample(UserPermissionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_permission
     *
     * @mbg.generated Fri Dec 21 03:28:33 CST 2018
     */
    int deleteByExample(UserPermissionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_permission
     *
     * @mbg.generated Fri Dec 21 03:28:33 CST 2018
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_permission
     *
     * @mbg.generated Fri Dec 21 03:28:33 CST 2018
     */
    int insert(UserPermission record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_permission
     *
     * @mbg.generated Fri Dec 21 03:28:33 CST 2018
     */
    int insertSelective(UserPermission record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_permission
     *
     * @mbg.generated Fri Dec 21 03:28:33 CST 2018
     */
    List<UserPermission> selectByExample(UserPermissionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_permission
     *
     * @mbg.generated Fri Dec 21 03:28:33 CST 2018
     */
    UserPermission selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_permission
     *
     * @mbg.generated Fri Dec 21 03:28:33 CST 2018
     */
    int updateByExampleSelective(@Param("record") UserPermission record, @Param("example") UserPermissionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_permission
     *
     * @mbg.generated Fri Dec 21 03:28:33 CST 2018
     */
    int updateByExample(@Param("record") UserPermission record, @Param("example") UserPermissionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_permission
     *
     * @mbg.generated Fri Dec 21 03:28:33 CST 2018
     */
    int updateByPrimaryKeySelective(UserPermission record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_permission
     *
     * @mbg.generated Fri Dec 21 03:28:33 CST 2018
     */
    int updateByPrimaryKey(UserPermission record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_permission
     *
     * @mbg.generated Fri Dec 21 03:28:33 CST 2018
     */
    Integer insertAndReturnKey(UserPermission value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_permission
     *
     * @mbg.generated Fri Dec 21 03:28:33 CST 2018
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_permission
     *
     * @mbg.generated Fri Dec 21 03:28:33 CST 2018
     */
    void massUpdateWithSession(@Param("record") UserPermission record, @Param("primaryKeys") List primaryKeys);
}