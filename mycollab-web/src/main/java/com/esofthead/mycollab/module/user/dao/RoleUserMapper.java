package com.esofthead.mycollab.module.user.dao;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.module.user.domain.RoleUser;
import com.esofthead.mycollab.module.user.domain.RoleUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RoleUserMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_roleuser
     *
     * @mbggenerated Wed Nov 14 15:52:02 GMT+07:00 2012
     */
    int countByExample(RoleUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_roleuser
     *
     * @mbggenerated Wed Nov 14 15:52:02 GMT+07:00 2012
     */
    int deleteByExample(RoleUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_roleuser
     *
     * @mbggenerated Wed Nov 14 15:52:02 GMT+07:00 2012
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_roleuser
     *
     * @mbggenerated Wed Nov 14 15:52:02 GMT+07:00 2012
     */
    int insert(RoleUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_roleuser
     *
     * @mbggenerated Wed Nov 14 15:52:02 GMT+07:00 2012
     */
    int insertSelective(RoleUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_roleuser
     *
     * @mbggenerated Wed Nov 14 15:52:02 GMT+07:00 2012
     */
    List<RoleUser> selectByExample(RoleUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_roleuser
     *
     * @mbggenerated Wed Nov 14 15:52:02 GMT+07:00 2012
     */
    RoleUser selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_roleuser
     *
     * @mbggenerated Wed Nov 14 15:52:02 GMT+07:00 2012
     */
    int updateByExampleSelective(@Param("record") RoleUser record, @Param("example") RoleUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_roleuser
     *
     * @mbggenerated Wed Nov 14 15:52:02 GMT+07:00 2012
     */
    int updateByExample(@Param("record") RoleUser record, @Param("example") RoleUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_roleuser
     *
     * @mbggenerated Wed Nov 14 15:52:02 GMT+07:00 2012
     */
    int updateByPrimaryKeySelective(RoleUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_roleuser
     *
     * @mbggenerated Wed Nov 14 15:52:02 GMT+07:00 2012
     */
    int updateByPrimaryKey(RoleUser record);
}