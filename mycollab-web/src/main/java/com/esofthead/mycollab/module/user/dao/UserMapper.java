package com.esofthead.mycollab.module.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.module.user.domain.User;
import com.esofthead.mycollab.module.user.domain.UserExample;

public interface UserMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user
     *
     * @mbggenerated Thu Jun 20 11:08:09 GMT+07:00 2013
     */
    int countByExample(UserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user
     *
     * @mbggenerated Thu Jun 20 11:08:09 GMT+07:00 2013
     */
    int deleteByExample(UserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user
     *
     * @mbggenerated Thu Jun 20 11:08:09 GMT+07:00 2013
     */
    int deleteByPrimaryKey(String username);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user
     *
     * @mbggenerated Thu Jun 20 11:08:09 GMT+07:00 2013
     */
    int insert(User record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user
     *
     * @mbggenerated Thu Jun 20 11:08:09 GMT+07:00 2013
     */
    int insertSelective(User record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user
     *
     * @mbggenerated Thu Jun 20 11:08:09 GMT+07:00 2013
     */
    List<User> selectByExampleWithBLOBs(UserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user
     *
     * @mbggenerated Thu Jun 20 11:08:09 GMT+07:00 2013
     */
    List<User> selectByExample(UserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user
     *
     * @mbggenerated Thu Jun 20 11:08:09 GMT+07:00 2013
     */
    User selectByPrimaryKey(String username);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user
     *
     * @mbggenerated Thu Jun 20 11:08:09 GMT+07:00 2013
     */
    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user
     *
     * @mbggenerated Thu Jun 20 11:08:09 GMT+07:00 2013
     */
    int updateByExampleWithBLOBs(@Param("record") User record, @Param("example") UserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user
     *
     * @mbggenerated Thu Jun 20 11:08:09 GMT+07:00 2013
     */
    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user
     *
     * @mbggenerated Thu Jun 20 11:08:09 GMT+07:00 2013
     */
    int updateByPrimaryKeySelective(User record);
}