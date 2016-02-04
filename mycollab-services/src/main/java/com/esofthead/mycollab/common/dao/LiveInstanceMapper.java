package com.esofthead.mycollab.common.dao;

import com.esofthead.mycollab.common.domain.LiveInstance;
import com.esofthead.mycollab.common.domain.LiveInstanceExample;
import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@SuppressWarnings({ "ucd", "rawtypes" })
public interface LiveInstanceMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_live_instances
     *
     * @mbggenerated Wed Feb 03 14:56:44 ICT 2016
     */
    int countByExample(LiveInstanceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_live_instances
     *
     * @mbggenerated Wed Feb 03 14:56:44 ICT 2016
     */
    int deleteByExample(LiveInstanceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_live_instances
     *
     * @mbggenerated Wed Feb 03 14:56:44 ICT 2016
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_live_instances
     *
     * @mbggenerated Wed Feb 03 14:56:44 ICT 2016
     */
    int insert(LiveInstance record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_live_instances
     *
     * @mbggenerated Wed Feb 03 14:56:44 ICT 2016
     */
    int insertSelective(LiveInstance record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_live_instances
     *
     * @mbggenerated Wed Feb 03 14:56:44 ICT 2016
     */
    List<LiveInstance> selectByExample(LiveInstanceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_live_instances
     *
     * @mbggenerated Wed Feb 03 14:56:44 ICT 2016
     */
    LiveInstance selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_live_instances
     *
     * @mbggenerated Wed Feb 03 14:56:44 ICT 2016
     */
    int updateByExampleSelective(@Param("record") LiveInstance record, @Param("example") LiveInstanceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_live_instances
     *
     * @mbggenerated Wed Feb 03 14:56:44 ICT 2016
     */
    int updateByExample(@Param("record") LiveInstance record, @Param("example") LiveInstanceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_live_instances
     *
     * @mbggenerated Wed Feb 03 14:56:44 ICT 2016
     */
    int updateByPrimaryKeySelective(LiveInstance record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_live_instances
     *
     * @mbggenerated Wed Feb 03 14:56:44 ICT 2016
     */
    int updateByPrimaryKey(LiveInstance record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_live_instances
     *
     * @mbggenerated Wed Feb 03 14:56:44 ICT 2016
     */
    Integer insertAndReturnKey(LiveInstance value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_live_instances
     *
     * @mbggenerated Wed Feb 03 14:56:44 ICT 2016
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_live_instances
     *
     * @mbggenerated Wed Feb 03 14:56:44 ICT 2016
     */
    void massUpdateWithSession(@Param("record") LiveInstance record, @Param("primaryKeys") List primaryKeys);
}