package com.mycollab.common.dao;

import com.mycollab.common.domain.ActivityStreamExample;
import com.mycollab.common.domain.ActivityStreamWithBLOBs;
import com.mycollab.db.persistence.ICrudGenericDAO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@SuppressWarnings({ "ucd", "rawtypes" })
public interface ActivityStreamMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_activitystream
     *
     * @mbg.generated Fri Dec 21 03:28:34 CST 2018
     */
    long countByExample(ActivityStreamExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_activitystream
     *
     * @mbg.generated Fri Dec 21 03:28:34 CST 2018
     */
    int deleteByExample(ActivityStreamExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_activitystream
     *
     * @mbg.generated Fri Dec 21 03:28:34 CST 2018
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_activitystream
     *
     * @mbg.generated Fri Dec 21 03:28:34 CST 2018
     */
    int insert(ActivityStreamWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_activitystream
     *
     * @mbg.generated Fri Dec 21 03:28:34 CST 2018
     */
    int insertSelective(ActivityStreamWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_activitystream
     *
     * @mbg.generated Fri Dec 21 03:28:34 CST 2018
     */
    List<ActivityStreamWithBLOBs> selectByExampleWithBLOBs(ActivityStreamExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_activitystream
     *
     * @mbg.generated Fri Dec 21 03:28:34 CST 2018
     */
    ActivityStreamWithBLOBs selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_activitystream
     *
     * @mbg.generated Fri Dec 21 03:28:34 CST 2018
     */
    int updateByExampleSelective(@Param("record") ActivityStreamWithBLOBs record, @Param("example") ActivityStreamExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_activitystream
     *
     * @mbg.generated Fri Dec 21 03:28:34 CST 2018
     */
    int updateByExampleWithBLOBs(@Param("record") ActivityStreamWithBLOBs record, @Param("example") ActivityStreamExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_activitystream
     *
     * @mbg.generated Fri Dec 21 03:28:34 CST 2018
     */
    int updateByPrimaryKeySelective(ActivityStreamWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_activitystream
     *
     * @mbg.generated Fri Dec 21 03:28:34 CST 2018
     */
    int updateByPrimaryKeyWithBLOBs(ActivityStreamWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_activitystream
     *
     * @mbg.generated Fri Dec 21 03:28:34 CST 2018
     */
    Integer insertAndReturnKey(ActivityStreamWithBLOBs value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_activitystream
     *
     * @mbg.generated Fri Dec 21 03:28:34 CST 2018
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_activitystream
     *
     * @mbg.generated Fri Dec 21 03:28:34 CST 2018
     */
    void massUpdateWithSession(@Param("record") ActivityStreamWithBLOBs record, @Param("primaryKeys") List primaryKeys);
}