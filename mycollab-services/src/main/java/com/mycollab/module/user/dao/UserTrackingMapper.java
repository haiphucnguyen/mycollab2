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
     * @mbg.generated Fri Dec 01 20:52:17 ICT 2017
     */
    long countByExample(UserTrackingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_tracking
     *
     * @mbg.generated Fri Dec 01 20:52:17 ICT 2017
     */
    int deleteByExample(UserTrackingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_tracking
     *
     * @mbg.generated Fri Dec 01 20:52:17 ICT 2017
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_tracking
     *
     * @mbg.generated Fri Dec 01 20:52:17 ICT 2017
     */
    int insert(UserTracking record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_tracking
     *
     * @mbg.generated Fri Dec 01 20:52:17 ICT 2017
     */
    int insertSelective(UserTracking record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_tracking
     *
     * @mbg.generated Fri Dec 01 20:52:17 ICT 2017
     */
    List<UserTracking> selectByExampleWithBLOBs(UserTrackingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_tracking
     *
     * @mbg.generated Fri Dec 01 20:52:17 ICT 2017
     */
    List<UserTracking> selectByExample(UserTrackingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_tracking
     *
     * @mbg.generated Fri Dec 01 20:52:17 ICT 2017
     */
    UserTracking selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_tracking
     *
     * @mbg.generated Fri Dec 01 20:52:17 ICT 2017
     */
    int updateByExampleSelective(@Param("record") UserTracking record, @Param("example") UserTrackingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_tracking
     *
     * @mbg.generated Fri Dec 01 20:52:17 ICT 2017
     */
    int updateByExampleWithBLOBs(@Param("record") UserTracking record, @Param("example") UserTrackingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_tracking
     *
     * @mbg.generated Fri Dec 01 20:52:17 ICT 2017
     */
    int updateByExample(@Param("record") UserTracking record, @Param("example") UserTrackingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_tracking
     *
     * @mbg.generated Fri Dec 01 20:52:17 ICT 2017
     */
    int updateByPrimaryKeySelective(UserTracking record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_tracking
     *
     * @mbg.generated Fri Dec 01 20:52:17 ICT 2017
     */
    int updateByPrimaryKeyWithBLOBs(UserTracking record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_tracking
     *
     * @mbg.generated Fri Dec 01 20:52:17 ICT 2017
     */
    int updateByPrimaryKey(UserTracking record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_tracking
     *
     * @mbg.generated Fri Dec 01 20:52:17 ICT 2017
     */
    Integer insertAndReturnKey(UserTracking value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_tracking
     *
     * @mbg.generated Fri Dec 01 20:52:17 ICT 2017
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_tracking
     *
     * @mbg.generated Fri Dec 01 20:52:17 ICT 2017
     */
    void massUpdateWithSession(@Param("record") UserTracking record, @Param("primaryKeys") List primaryKeys);
}