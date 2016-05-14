package com.esofthead.mycollab.common.dao;

import com.esofthead.mycollab.common.domain.RelayEmailExample;
import com.esofthead.mycollab.common.domain.RelayEmailWithBLOBs;
import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@SuppressWarnings({ "ucd", "rawtypes" })
public interface RelayEmailMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_relay_mail
     *
     * @mbggenerated Sun May 15 02:13:10 ICT 2016
     */
    int countByExample(RelayEmailExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_relay_mail
     *
     * @mbggenerated Sun May 15 02:13:10 ICT 2016
     */
    int deleteByExample(RelayEmailExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_relay_mail
     *
     * @mbggenerated Sun May 15 02:13:10 ICT 2016
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_relay_mail
     *
     * @mbggenerated Sun May 15 02:13:10 ICT 2016
     */
    int insert(RelayEmailWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_relay_mail
     *
     * @mbggenerated Sun May 15 02:13:10 ICT 2016
     */
    int insertSelective(RelayEmailWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_relay_mail
     *
     * @mbggenerated Sun May 15 02:13:10 ICT 2016
     */
    List<RelayEmailWithBLOBs> selectByExampleWithBLOBs(RelayEmailExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_relay_mail
     *
     * @mbggenerated Sun May 15 02:13:10 ICT 2016
     */
    RelayEmailWithBLOBs selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_relay_mail
     *
     * @mbggenerated Sun May 15 02:13:10 ICT 2016
     */
    int updateByExampleSelective(@Param("record") RelayEmailWithBLOBs record, @Param("example") RelayEmailExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_relay_mail
     *
     * @mbggenerated Sun May 15 02:13:10 ICT 2016
     */
    int updateByExampleWithBLOBs(@Param("record") RelayEmailWithBLOBs record, @Param("example") RelayEmailExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_relay_mail
     *
     * @mbggenerated Sun May 15 02:13:10 ICT 2016
     */
    int updateByPrimaryKeySelective(RelayEmailWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_relay_mail
     *
     * @mbggenerated Sun May 15 02:13:10 ICT 2016
     */
    int updateByPrimaryKeyWithBLOBs(RelayEmailWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_relay_mail
     *
     * @mbggenerated Sun May 15 02:13:10 ICT 2016
     */
    Integer insertAndReturnKey(RelayEmailWithBLOBs value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_relay_mail
     *
     * @mbggenerated Sun May 15 02:13:10 ICT 2016
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_relay_mail
     *
     * @mbggenerated Sun May 15 02:13:10 ICT 2016
     */
    void massUpdateWithSession(@Param("record") RelayEmailWithBLOBs record, @Param("primaryKeys") List primaryKeys);
}