package com.esofthead.mycollab.module.user.dao;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.module.user.domain.UserInformation;
import com.esofthead.mycollab.module.user.domain.UserInformationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserInformationMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_information
     *
     * @mbggenerated Mon Apr 01 10:47:00 GMT+07:00 2013
     */
    int countByExample(UserInformationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_information
     *
     * @mbggenerated Mon Apr 01 10:47:00 GMT+07:00 2013
     */
    int deleteByExample(UserInformationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_information
     *
     * @mbggenerated Mon Apr 01 10:47:00 GMT+07:00 2013
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_information
     *
     * @mbggenerated Mon Apr 01 10:47:00 GMT+07:00 2013
     */
    int insert(UserInformation record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_information
     *
     * @mbggenerated Mon Apr 01 10:47:00 GMT+07:00 2013
     */
    int insertSelective(UserInformation record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_information
     *
     * @mbggenerated Mon Apr 01 10:47:00 GMT+07:00 2013
     */
    List<UserInformation> selectByExample(UserInformationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_information
     *
     * @mbggenerated Mon Apr 01 10:47:00 GMT+07:00 2013
     */
    UserInformation selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_information
     *
     * @mbggenerated Mon Apr 01 10:47:00 GMT+07:00 2013
     */
    int updateByExampleSelective(@Param("record") UserInformation record, @Param("example") UserInformationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_information
     *
     * @mbggenerated Mon Apr 01 10:47:00 GMT+07:00 2013
     */
    int updateByExample(@Param("record") UserInformation record, @Param("example") UserInformationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_information
     *
     * @mbggenerated Mon Apr 01 10:47:00 GMT+07:00 2013
     */
    int updateByPrimaryKeySelective(UserInformation record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_information
     *
     * @mbggenerated Mon Apr 01 10:47:00 GMT+07:00 2013
     */
    int updateByPrimaryKey(UserInformation record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_information
     *
     * @mbggenerated Mon Apr 01 10:47:00 GMT+07:00 2013
     */
    Integer insertAndReturnKey(UserInformation value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_information
     *
     * @mbggenerated Mon Apr 01 10:47:00 GMT+07:00 2013
     */
    void removeKeysWithSession(List primaryKeys);
}