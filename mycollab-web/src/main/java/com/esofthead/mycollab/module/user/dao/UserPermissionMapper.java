package com.esofthead.mycollab.module.user.dao;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.module.user.domain.UserPermission;
import com.esofthead.mycollab.module.user.domain.UserPermissionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserPermissionMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_permission
     *
     * @mbggenerated Tue Jul 23 09:52:47 GMT+07:00 2013
     */
    int countByExample(UserPermissionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_permission
     *
     * @mbggenerated Tue Jul 23 09:52:47 GMT+07:00 2013
     */
    int deleteByExample(UserPermissionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_permission
     *
     * @mbggenerated Tue Jul 23 09:52:47 GMT+07:00 2013
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_permission
     *
     * @mbggenerated Tue Jul 23 09:52:47 GMT+07:00 2013
     */
    int insert(UserPermission record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_permission
     *
     * @mbggenerated Tue Jul 23 09:52:47 GMT+07:00 2013
     */
    int insertSelective(UserPermission record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_permission
     *
     * @mbggenerated Tue Jul 23 09:52:47 GMT+07:00 2013
     */
    List<UserPermission> selectByExample(UserPermissionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_permission
     *
     * @mbggenerated Tue Jul 23 09:52:47 GMT+07:00 2013
     */
    UserPermission selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_permission
     *
     * @mbggenerated Tue Jul 23 09:52:47 GMT+07:00 2013
     */
    int updateByExampleSelective(@Param("record") UserPermission record, @Param("example") UserPermissionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_permission
     *
     * @mbggenerated Tue Jul 23 09:52:47 GMT+07:00 2013
     */
    int updateByExample(@Param("record") UserPermission record, @Param("example") UserPermissionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_permission
     *
     * @mbggenerated Tue Jul 23 09:52:47 GMT+07:00 2013
     */
    int updateByPrimaryKeySelective(UserPermission record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_permission
     *
     * @mbggenerated Tue Jul 23 09:52:47 GMT+07:00 2013
     */
    Integer insertAndReturnKey(UserPermission value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_permission
     *
     * @mbggenerated Tue Jul 23 09:52:47 GMT+07:00 2013
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_permission
     *
     * @mbggenerated Tue Jul 23 09:52:47 GMT+07:00 2013
     */
    void massUpdateWithSession(@Param("record") UserPermission record, @Param("primaryKeys") List primaryKeys);
}