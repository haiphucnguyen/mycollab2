package com.mycollab.module.user.dao;

import com.mycollab.db.persistence.ICrudGenericDAO;
import com.mycollab.module.user.domain.UserTracking;
import com.mycollab.module.user.domain.UserTrackingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@SuppressWarnings({ "ucd", "rawtypes" })
public interface UserTrackingMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_tracking
     *
     * @mbg.generated Fri Jan 04 16:43:08 CST 2019
     */
    long countByExample(UserTrackingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_tracking
     *
     * @mbg.generated Fri Jan 04 16:43:08 CST 2019
     */
    int deleteByExample(UserTrackingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_tracking
     *
     * @mbg.generated Fri Jan 04 16:43:08 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_tracking
     *
     * @mbg.generated Fri Jan 04 16:43:08 CST 2019
     */
    int insert(UserTracking record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_tracking
     *
     * @mbg.generated Fri Jan 04 16:43:08 CST 2019
     */
    int insertSelective(UserTracking record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_tracking
     *
     * @mbg.generated Fri Jan 04 16:43:08 CST 2019
     */
    List<UserTracking> selectByExampleWithBLOBs(UserTrackingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_tracking
     *
     * @mbg.generated Fri Jan 04 16:43:08 CST 2019
     */
    List<UserTracking> selectByExample(UserTrackingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_tracking
     *
     * @mbg.generated Fri Jan 04 16:43:08 CST 2019
     */
    UserTracking selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_tracking
     *
     * @mbg.generated Fri Jan 04 16:43:08 CST 2019
     */
    int updateByExampleSelective(@Param("record") UserTracking record, @Param("example") UserTrackingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_tracking
     *
     * @mbg.generated Fri Jan 04 16:43:08 CST 2019
     */
    int updateByExampleWithBLOBs(@Param("record") UserTracking record, @Param("example") UserTrackingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_tracking
     *
     * @mbg.generated Fri Jan 04 16:43:08 CST 2019
     */
    int updateByExample(@Param("record") UserTracking record, @Param("example") UserTrackingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_tracking
     *
     * @mbg.generated Fri Jan 04 16:43:08 CST 2019
     */
    int updateByPrimaryKeySelective(UserTracking record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_tracking
     *
     * @mbg.generated Fri Jan 04 16:43:08 CST 2019
     */
    int updateByPrimaryKeyWithBLOBs(UserTracking record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_tracking
     *
     * @mbg.generated Fri Jan 04 16:43:08 CST 2019
     */
    int updateByPrimaryKey(UserTracking record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_tracking
     *
     * @mbg.generated Fri Jan 04 16:43:08 CST 2019
     */
    Integer insertAndReturnKey(UserTracking value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_tracking
     *
     * @mbg.generated Fri Jan 04 16:43:08 CST 2019
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_tracking
     *
     * @mbg.generated Fri Jan 04 16:43:08 CST 2019
     */
    void massUpdateWithSession(@Param("record") UserTracking record, @Param("primaryKeys") List primaryKeys);
}