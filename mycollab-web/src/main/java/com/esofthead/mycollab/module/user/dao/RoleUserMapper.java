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
     * @mbggenerated Tue Jan 15 15:48:49 GMT+07:00 2013
     */
    int countByExample(RoleUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_roleuser
     *
     * @mbggenerated Tue Jan 15 15:48:49 GMT+07:00 2013
     */
    int deleteByExample(RoleUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_roleuser
     *
     * @mbggenerated Tue Jan 15 15:48:49 GMT+07:00 2013
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_roleuser
     *
     * @mbggenerated Tue Jan 15 15:48:49 GMT+07:00 2013
     */
    int insert(RoleUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_roleuser
     *
     * @mbggenerated Tue Jan 15 15:48:49 GMT+07:00 2013
     */
    int insertSelective(RoleUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_roleuser
     *
     * @mbggenerated Tue Jan 15 15:48:49 GMT+07:00 2013
     */
    List<RoleUser> selectByExample(RoleUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_roleuser
     *
     * @mbggenerated Tue Jan 15 15:48:49 GMT+07:00 2013
     */
    RoleUser selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_roleuser
     *
     * @mbggenerated Tue Jan 15 15:48:49 GMT+07:00 2013
     */
    int updateByExampleSelective(@Param("record") RoleUser record, @Param("example") RoleUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_roleuser
     *
     * @mbggenerated Tue Jan 15 15:48:49 GMT+07:00 2013
     */
    int updateByExample(@Param("record") RoleUser record, @Param("example") RoleUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_roleuser
     *
     * @mbggenerated Tue Jan 15 15:48:49 GMT+07:00 2013
     */
    int updateByPrimaryKeySelective(RoleUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_roleuser
     *
     * @mbggenerated Tue Jan 15 15:48:49 GMT+07:00 2013
     */
    int updateByPrimaryKey(RoleUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_roleuser
     *
     * @mbggenerated Tue Jan 15 15:48:49 GMT+07:00 2013
     */
    Integer insertAndReturnKey(RoleUser value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_roleuser
     *
     * @mbggenerated Tue Jan 15 15:48:49 GMT+07:00 2013
     */
    void removeKeysWithSession(List primaryKeys);
}