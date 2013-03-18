package com.esofthead.mycollab.module.user.dao;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.module.user.domain.Role;
import com.esofthead.mycollab.module.user.domain.RoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RoleMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_roles
     *
     * @mbggenerated Sat Mar 16 12:55:35 GMT+07:00 2013
     */
    int countByExample(RoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_roles
     *
     * @mbggenerated Sat Mar 16 12:55:35 GMT+07:00 2013
     */
    int deleteByExample(RoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_roles
     *
     * @mbggenerated Sat Mar 16 12:55:35 GMT+07:00 2013
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_roles
     *
     * @mbggenerated Sat Mar 16 12:55:35 GMT+07:00 2013
     */
    int insert(Role record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_roles
     *
     * @mbggenerated Sat Mar 16 12:55:35 GMT+07:00 2013
     */
    int insertSelective(Role record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_roles
     *
     * @mbggenerated Sat Mar 16 12:55:35 GMT+07:00 2013
     */
    List<Role> selectByExample(RoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_roles
     *
     * @mbggenerated Sat Mar 16 12:55:35 GMT+07:00 2013
     */
    Role selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_roles
     *
     * @mbggenerated Sat Mar 16 12:55:35 GMT+07:00 2013
     */
    int updateByExampleSelective(@Param("record") Role record, @Param("example") RoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_roles
     *
     * @mbggenerated Sat Mar 16 12:55:35 GMT+07:00 2013
     */
    int updateByExample(@Param("record") Role record, @Param("example") RoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_roles
     *
     * @mbggenerated Sat Mar 16 12:55:35 GMT+07:00 2013
     */
    int updateByPrimaryKeySelective(Role record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_roles
     *
     * @mbggenerated Sat Mar 16 12:55:35 GMT+07:00 2013
     */
    int updateByPrimaryKey(Role record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_roles
     *
     * @mbggenerated Sat Mar 16 12:55:35 GMT+07:00 2013
     */
    Integer insertAndReturnKey(Role value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_roles
     *
     * @mbggenerated Sat Mar 16 12:55:35 GMT+07:00 2013
     */
    void removeKeysWithSession(List primaryKeys);
}