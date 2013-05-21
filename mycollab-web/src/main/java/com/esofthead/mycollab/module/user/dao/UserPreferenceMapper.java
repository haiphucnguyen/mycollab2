package com.esofthead.mycollab.module.user.dao;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.module.user.domain.UserPreference;
import com.esofthead.mycollab.module.user.domain.UserPreferenceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserPreferenceMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_preference
     *
     * @mbggenerated Tue May 21 16:04:02 GMT+07:00 2013
     */
    int countByExample(UserPreferenceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_preference
     *
     * @mbggenerated Tue May 21 16:04:02 GMT+07:00 2013
     */
    int deleteByExample(UserPreferenceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_preference
     *
     * @mbggenerated Tue May 21 16:04:02 GMT+07:00 2013
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_preference
     *
     * @mbggenerated Tue May 21 16:04:02 GMT+07:00 2013
     */
    int insert(UserPreference record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_preference
     *
     * @mbggenerated Tue May 21 16:04:02 GMT+07:00 2013
     */
    int insertSelective(UserPreference record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_preference
     *
     * @mbggenerated Tue May 21 16:04:02 GMT+07:00 2013
     */
    List<UserPreference> selectByExample(UserPreferenceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_preference
     *
     * @mbggenerated Tue May 21 16:04:02 GMT+07:00 2013
     */
    UserPreference selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_preference
     *
     * @mbggenerated Tue May 21 16:04:02 GMT+07:00 2013
     */
    int updateByExampleSelective(@Param("record") UserPreference record, @Param("example") UserPreferenceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_preference
     *
     * @mbggenerated Tue May 21 16:04:02 GMT+07:00 2013
     */
    int updateByExample(@Param("record") UserPreference record, @Param("example") UserPreferenceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_preference
     *
     * @mbggenerated Tue May 21 16:04:02 GMT+07:00 2013
     */
    int updateByPrimaryKeySelective(UserPreference record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_preference
     *
     * @mbggenerated Tue May 21 16:04:02 GMT+07:00 2013
     */
    Integer insertAndReturnKey(UserPreference value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_preference
     *
     * @mbggenerated Tue May 21 16:04:02 GMT+07:00 2013
     */
    void removeKeysWithSession(List primaryKeys);
}