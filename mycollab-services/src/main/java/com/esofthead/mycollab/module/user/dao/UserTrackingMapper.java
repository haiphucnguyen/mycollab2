package com.esofthead.mycollab.module.user.dao;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.module.user.domain.UserTracking;
import com.esofthead.mycollab.module.user.domain.UserTrackingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@SuppressWarnings({ "ucd", "rawtypes" })
public interface UserTrackingMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_tracking
     *
     * @mbggenerated Tue May 24 18:34:10 ICT 2016
     */
    int countByExample(UserTrackingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_tracking
     *
     * @mbggenerated Tue May 24 18:34:10 ICT 2016
     */
    int deleteByExample(UserTrackingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_tracking
     *
     * @mbggenerated Tue May 24 18:34:10 ICT 2016
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_tracking
     *
     * @mbggenerated Tue May 24 18:34:10 ICT 2016
     */
    int insert(UserTracking record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_tracking
     *
     * @mbggenerated Tue May 24 18:34:10 ICT 2016
     */
    int insertSelective(UserTracking record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_tracking
     *
     * @mbggenerated Tue May 24 18:34:10 ICT 2016
     */
    List<UserTracking> selectByExampleWithBLOBs(UserTrackingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_tracking
     *
     * @mbggenerated Tue May 24 18:34:10 ICT 2016
     */
    List<UserTracking> selectByExample(UserTrackingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_tracking
     *
     * @mbggenerated Tue May 24 18:34:10 ICT 2016
     */
    UserTracking selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_tracking
     *
     * @mbggenerated Tue May 24 18:34:10 ICT 2016
     */
    int updateByExampleSelective(@Param("record") UserTracking record, @Param("example") UserTrackingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_tracking
     *
     * @mbggenerated Tue May 24 18:34:10 ICT 2016
     */
    int updateByExampleWithBLOBs(@Param("record") UserTracking record, @Param("example") UserTrackingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_tracking
     *
     * @mbggenerated Tue May 24 18:34:10 ICT 2016
     */
    int updateByExample(@Param("record") UserTracking record, @Param("example") UserTrackingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_tracking
     *
     * @mbggenerated Tue May 24 18:34:10 ICT 2016
     */
    int updateByPrimaryKeySelective(UserTracking record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_tracking
     *
     * @mbggenerated Tue May 24 18:34:10 ICT 2016
     */
    int updateByPrimaryKeyWithBLOBs(UserTracking record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_tracking
     *
     * @mbggenerated Tue May 24 18:34:10 ICT 2016
     */
    int updateByPrimaryKey(UserTracking record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_tracking
     *
     * @mbggenerated Tue May 24 18:34:10 ICT 2016
     */
    Integer insertAndReturnKey(UserTracking value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_tracking
     *
     * @mbggenerated Tue May 24 18:34:10 ICT 2016
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_tracking
     *
     * @mbggenerated Tue May 24 18:34:10 ICT 2016
     */
    void massUpdateWithSession(@Param("record") UserTracking record, @Param("primaryKeys") List primaryKeys);
}