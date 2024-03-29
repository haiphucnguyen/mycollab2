package com.mycollab.pro.common.dao;

import com.mycollab.common.domain.LiveInstance;
import com.mycollab.common.domain.LiveInstanceExample;
import com.mycollab.db.persistence.ICrudGenericDAO;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@SuppressWarnings({ "ucd", "rawtypes" })
@Mapper
public interface LiveInstanceMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_live_instances
     *
     * @mbg.generated Sat Feb 23 06:34:50 CST 2019
     */
    long countByExample(LiveInstanceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_live_instances
     *
     * @mbg.generated Sat Feb 23 06:34:50 CST 2019
     */
    int deleteByExample(LiveInstanceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_live_instances
     *
     * @mbg.generated Sat Feb 23 06:34:50 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_live_instances
     *
     * @mbg.generated Sat Feb 23 06:34:50 CST 2019
     */
    int insert(LiveInstance record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_live_instances
     *
     * @mbg.generated Sat Feb 23 06:34:50 CST 2019
     */
    int insertSelective(LiveInstance record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_live_instances
     *
     * @mbg.generated Sat Feb 23 06:34:50 CST 2019
     */
    List<LiveInstance> selectByExample(LiveInstanceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_live_instances
     *
     * @mbg.generated Sat Feb 23 06:34:50 CST 2019
     */
    LiveInstance selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_live_instances
     *
     * @mbg.generated Sat Feb 23 06:34:50 CST 2019
     */
    int updateByExampleSelective(@Param("record") LiveInstance record, @Param("example") LiveInstanceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_live_instances
     *
     * @mbg.generated Sat Feb 23 06:34:50 CST 2019
     */
    int updateByExample(@Param("record") LiveInstance record, @Param("example") LiveInstanceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_live_instances
     *
     * @mbg.generated Sat Feb 23 06:34:50 CST 2019
     */
    int updateByPrimaryKeySelective(LiveInstance record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_live_instances
     *
     * @mbg.generated Sat Feb 23 06:34:50 CST 2019
     */
    int updateByPrimaryKey(LiveInstance record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_live_instances
     *
     * @mbg.generated Sat Feb 23 06:34:50 CST 2019
     */
    Integer insertAndReturnKey(LiveInstance value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_live_instances
     *
     * @mbg.generated Sat Feb 23 06:34:50 CST 2019
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_live_instances
     *
     * @mbg.generated Sat Feb 23 06:34:50 CST 2019
     */
    void massUpdateWithSession(@Param("record") LiveInstance record, @Param("primaryKeys") List primaryKeys);
}