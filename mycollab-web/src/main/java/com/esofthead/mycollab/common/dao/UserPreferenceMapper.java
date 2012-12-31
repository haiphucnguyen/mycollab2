package com.esofthead.mycollab.common.dao;

import com.esofthead.mycollab.common.domain.UserPreference;
import com.esofthead.mycollab.common.domain.UserPreferenceExample;
import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserPreferenceMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_preference
     *
     * @mbggenerated Mon Dec 31 23:26:04 GMT+07:00 2012
     */
    int countByExample(UserPreferenceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_preference
     *
     * @mbggenerated Mon Dec 31 23:26:04 GMT+07:00 2012
     */
    int deleteByExample(UserPreferenceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_preference
     *
     * @mbggenerated Mon Dec 31 23:26:04 GMT+07:00 2012
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_preference
     *
     * @mbggenerated Mon Dec 31 23:26:04 GMT+07:00 2012
     */
    int insert(UserPreference record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_preference
     *
     * @mbggenerated Mon Dec 31 23:26:04 GMT+07:00 2012
     */
    int insertSelective(UserPreference record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_preference
     *
     * @mbggenerated Mon Dec 31 23:26:04 GMT+07:00 2012
     */
    List<UserPreference> selectByExample(UserPreferenceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_preference
     *
     * @mbggenerated Mon Dec 31 23:26:04 GMT+07:00 2012
     */
    UserPreference selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_preference
     *
     * @mbggenerated Mon Dec 31 23:26:04 GMT+07:00 2012
     */
    int updateByExampleSelective(@Param("record") UserPreference record, @Param("example") UserPreferenceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_preference
     *
     * @mbggenerated Mon Dec 31 23:26:04 GMT+07:00 2012
     */
    int updateByExample(@Param("record") UserPreference record, @Param("example") UserPreferenceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_preference
     *
     * @mbggenerated Mon Dec 31 23:26:04 GMT+07:00 2012
     */
    int updateByPrimaryKeySelective(UserPreference record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_preference
     *
     * @mbggenerated Mon Dec 31 23:26:04 GMT+07:00 2012
     */
    int updateByPrimaryKey(UserPreference record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_preference
     *
     * @mbggenerated Mon Dec 31 23:26:04 GMT+07:00 2012
     */
    Integer insertAndReturnKey(UserPreference value);
}