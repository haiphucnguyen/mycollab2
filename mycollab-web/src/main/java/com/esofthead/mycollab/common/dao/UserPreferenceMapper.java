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
     * @mbggenerated Wed Feb 13 07:15:53 GMT+07:00 2013
     */
    int countByExample(UserPreferenceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_preference
     *
     * @mbggenerated Wed Feb 13 07:15:53 GMT+07:00 2013
     */
    int deleteByExample(UserPreferenceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_preference
     *
     * @mbggenerated Wed Feb 13 07:15:53 GMT+07:00 2013
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_preference
     *
     * @mbggenerated Wed Feb 13 07:15:53 GMT+07:00 2013
     */
    int insert(UserPreference record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_preference
     *
     * @mbggenerated Wed Feb 13 07:15:53 GMT+07:00 2013
     */
    int insertSelective(UserPreference record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_preference
     *
     * @mbggenerated Wed Feb 13 07:15:53 GMT+07:00 2013
     */
    List<UserPreference> selectByExample(UserPreferenceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_preference
     *
     * @mbggenerated Wed Feb 13 07:15:53 GMT+07:00 2013
     */
    UserPreference selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_preference
     *
     * @mbggenerated Wed Feb 13 07:15:53 GMT+07:00 2013
     */
    int updateByExampleSelective(@Param("record") UserPreference record, @Param("example") UserPreferenceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_preference
     *
     * @mbggenerated Wed Feb 13 07:15:53 GMT+07:00 2013
     */
    int updateByExample(@Param("record") UserPreference record, @Param("example") UserPreferenceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_preference
     *
     * @mbggenerated Wed Feb 13 07:15:53 GMT+07:00 2013
     */
    int updateByPrimaryKeySelective(UserPreference record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_preference
     *
     * @mbggenerated Wed Feb 13 07:15:53 GMT+07:00 2013
     */
    int updateByPrimaryKey(UserPreference record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_preference
     *
     * @mbggenerated Wed Feb 13 07:15:53 GMT+07:00 2013
     */
    Integer insertAndReturnKey(UserPreference value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_preference
     *
     * @mbggenerated Wed Feb 13 07:15:53 GMT+07:00 2013
     */
    void removeKeysWithSession(List primaryKeys);
}