package com.esofthead.mycollab.common.dao;

import com.esofthead.mycollab.common.domain.UserTracking;
import com.esofthead.mycollab.common.domain.UserTrackingExample;
import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserTrackingMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_tracking
     *
     * @mbggenerated Thu Apr 11 07:04:22 GMT+07:00 2013
     */
    int countByExample(UserTrackingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_tracking
     *
     * @mbggenerated Thu Apr 11 07:04:22 GMT+07:00 2013
     */
    int deleteByExample(UserTrackingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_tracking
     *
     * @mbggenerated Thu Apr 11 07:04:22 GMT+07:00 2013
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_tracking
     *
     * @mbggenerated Thu Apr 11 07:04:22 GMT+07:00 2013
     */
    int insert(UserTracking record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_tracking
     *
     * @mbggenerated Thu Apr 11 07:04:22 GMT+07:00 2013
     */
    int insertSelective(UserTracking record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_tracking
     *
     * @mbggenerated Thu Apr 11 07:04:22 GMT+07:00 2013
     */
    List<UserTracking> selectByExampleWithBLOBs(UserTrackingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_tracking
     *
     * @mbggenerated Thu Apr 11 07:04:22 GMT+07:00 2013
     */
    List<UserTracking> selectByExample(UserTrackingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_tracking
     *
     * @mbggenerated Thu Apr 11 07:04:22 GMT+07:00 2013
     */
    UserTracking selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_tracking
     *
     * @mbggenerated Thu Apr 11 07:04:22 GMT+07:00 2013
     */
    int updateByExampleSelective(@Param("record") UserTracking record, @Param("example") UserTrackingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_tracking
     *
     * @mbggenerated Thu Apr 11 07:04:22 GMT+07:00 2013
     */
    int updateByExampleWithBLOBs(@Param("record") UserTracking record, @Param("example") UserTrackingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_tracking
     *
     * @mbggenerated Thu Apr 11 07:04:22 GMT+07:00 2013
     */
    int updateByExample(@Param("record") UserTracking record, @Param("example") UserTrackingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_tracking
     *
     * @mbggenerated Thu Apr 11 07:04:22 GMT+07:00 2013
     */
    int updateByPrimaryKeySelective(UserTracking record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_tracking
     *
     * @mbggenerated Thu Apr 11 07:04:22 GMT+07:00 2013
     */
    Integer insertAndReturnKey(UserTracking value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_tracking
     *
     * @mbggenerated Thu Apr 11 07:04:22 GMT+07:00 2013
     */
    void removeKeysWithSession(List primaryKeys);
}