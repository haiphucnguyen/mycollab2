package com.esofthead.mycollab.common.dao;

import com.esofthead.mycollab.common.domain.RelayEmailExample;
import com.esofthead.mycollab.common.domain.RelayEmailWithBLOBs;
import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RelayEmailMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_relay_mail
     *
     * @mbggenerated Thu Aug 08 17:37:40 GMT+07:00 2013
     */
    int countByExample(RelayEmailExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_relay_mail
     *
     * @mbggenerated Thu Aug 08 17:37:40 GMT+07:00 2013
     */
    int deleteByExample(RelayEmailExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_relay_mail
     *
     * @mbggenerated Thu Aug 08 17:37:40 GMT+07:00 2013
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_relay_mail
     *
     * @mbggenerated Thu Aug 08 17:37:40 GMT+07:00 2013
     */
    int insert(RelayEmailWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_relay_mail
     *
     * @mbggenerated Thu Aug 08 17:37:40 GMT+07:00 2013
     */
    int insertSelective(RelayEmailWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_relay_mail
     *
     * @mbggenerated Thu Aug 08 17:37:40 GMT+07:00 2013
     */
    List<RelayEmailWithBLOBs> selectByExampleWithBLOBs(RelayEmailExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_relay_mail
     *
     * @mbggenerated Thu Aug 08 17:37:40 GMT+07:00 2013
     */
    RelayEmailWithBLOBs selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_relay_mail
     *
     * @mbggenerated Thu Aug 08 17:37:40 GMT+07:00 2013
     */
    int updateByExampleSelective(@Param("record") RelayEmailWithBLOBs record, @Param("example") RelayEmailExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_relay_mail
     *
     * @mbggenerated Thu Aug 08 17:37:40 GMT+07:00 2013
     */
    int updateByExampleWithBLOBs(@Param("record") RelayEmailWithBLOBs record, @Param("example") RelayEmailExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_relay_mail
     *
     * @mbggenerated Thu Aug 08 17:37:40 GMT+07:00 2013
     */
    int updateByPrimaryKeySelective(RelayEmailWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_relay_mail
     *
     * @mbggenerated Thu Aug 08 17:37:40 GMT+07:00 2013
     */
    Integer insertAndReturnKey(RelayEmailWithBLOBs value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_relay_mail
     *
     * @mbggenerated Thu Aug 08 17:37:40 GMT+07:00 2013
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_relay_mail
     *
     * @mbggenerated Thu Aug 08 17:37:40 GMT+07:00 2013
     */
    void massUpdateWithSession(@Param("record") RelayEmailWithBLOBs record, @Param("primaryKeys") List primaryKeys);
}