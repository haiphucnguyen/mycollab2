package com.esofthead.mycollab.common.dao;

import com.esofthead.mycollab.common.domain.ActivityStream;
import com.esofthead.mycollab.common.domain.ActivityStreamExample;
import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ActivityStreamMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_activitystream
     *
     * @mbggenerated Mon Dec 31 23:26:04 GMT+07:00 2012
     */
    int countByExample(ActivityStreamExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_activitystream
     *
     * @mbggenerated Mon Dec 31 23:26:04 GMT+07:00 2012
     */
    int deleteByExample(ActivityStreamExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_activitystream
     *
     * @mbggenerated Mon Dec 31 23:26:04 GMT+07:00 2012
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_activitystream
     *
     * @mbggenerated Mon Dec 31 23:26:04 GMT+07:00 2012
     */
    int insert(ActivityStream record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_activitystream
     *
     * @mbggenerated Mon Dec 31 23:26:04 GMT+07:00 2012
     */
    int insertSelective(ActivityStream record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_activitystream
     *
     * @mbggenerated Mon Dec 31 23:26:04 GMT+07:00 2012
     */
    List<ActivityStream> selectByExampleWithBLOBs(ActivityStreamExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_activitystream
     *
     * @mbggenerated Mon Dec 31 23:26:04 GMT+07:00 2012
     */
    List<ActivityStream> selectByExample(ActivityStreamExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_activitystream
     *
     * @mbggenerated Mon Dec 31 23:26:04 GMT+07:00 2012
     */
    ActivityStream selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_activitystream
     *
     * @mbggenerated Mon Dec 31 23:26:04 GMT+07:00 2012
     */
    int updateByExampleSelective(@Param("record") ActivityStream record, @Param("example") ActivityStreamExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_activitystream
     *
     * @mbggenerated Mon Dec 31 23:26:04 GMT+07:00 2012
     */
    int updateByExampleWithBLOBs(@Param("record") ActivityStream record, @Param("example") ActivityStreamExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_activitystream
     *
     * @mbggenerated Mon Dec 31 23:26:04 GMT+07:00 2012
     */
    int updateByExample(@Param("record") ActivityStream record, @Param("example") ActivityStreamExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_activitystream
     *
     * @mbggenerated Mon Dec 31 23:26:04 GMT+07:00 2012
     */
    int updateByPrimaryKeySelective(ActivityStream record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_activitystream
     *
     * @mbggenerated Mon Dec 31 23:26:04 GMT+07:00 2012
     */
    int updateByPrimaryKeyWithBLOBs(ActivityStream record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_activitystream
     *
     * @mbggenerated Mon Dec 31 23:26:04 GMT+07:00 2012
     */
    int updateByPrimaryKey(ActivityStream record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_activitystream
     *
     * @mbggenerated Mon Dec 31 23:26:04 GMT+07:00 2012
     */
    Integer insertAndReturnKey(ActivityStream value);
}