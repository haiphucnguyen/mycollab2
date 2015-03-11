package com.esofthead.mycollab.module.ecm.dao;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.module.ecm.domain.ContentActivityLogExample;
import com.esofthead.mycollab.module.ecm.domain.ContentActivityLogWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@SuppressWarnings({ "ucd", "rawtypes" })
public interface ContentActivityLogMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_ecm_activity_log
     *
     * @mbggenerated Wed Mar 11 09:10:41 ICT 2015
     */
    int countByExample(ContentActivityLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_ecm_activity_log
     *
     * @mbggenerated Wed Mar 11 09:10:41 ICT 2015
     */
    int deleteByExample(ContentActivityLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_ecm_activity_log
     *
     * @mbggenerated Wed Mar 11 09:10:41 ICT 2015
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_ecm_activity_log
     *
     * @mbggenerated Wed Mar 11 09:10:41 ICT 2015
     */
    int insert(ContentActivityLogWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_ecm_activity_log
     *
     * @mbggenerated Wed Mar 11 09:10:41 ICT 2015
     */
    int insertSelective(ContentActivityLogWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_ecm_activity_log
     *
     * @mbggenerated Wed Mar 11 09:10:41 ICT 2015
     */
    List<ContentActivityLogWithBLOBs> selectByExampleWithBLOBs(ContentActivityLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_ecm_activity_log
     *
     * @mbggenerated Wed Mar 11 09:10:41 ICT 2015
     */
    ContentActivityLogWithBLOBs selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_ecm_activity_log
     *
     * @mbggenerated Wed Mar 11 09:10:41 ICT 2015
     */
    int updateByExampleSelective(@Param("record") ContentActivityLogWithBLOBs record, @Param("example") ContentActivityLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_ecm_activity_log
     *
     * @mbggenerated Wed Mar 11 09:10:41 ICT 2015
     */
    int updateByExampleWithBLOBs(@Param("record") ContentActivityLogWithBLOBs record, @Param("example") ContentActivityLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_ecm_activity_log
     *
     * @mbggenerated Wed Mar 11 09:10:41 ICT 2015
     */
    int updateByPrimaryKeySelective(ContentActivityLogWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_ecm_activity_log
     *
     * @mbggenerated Wed Mar 11 09:10:41 ICT 2015
     */
    int updateByPrimaryKeyWithBLOBs(ContentActivityLogWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_ecm_activity_log
     *
     * @mbggenerated Wed Mar 11 09:10:41 ICT 2015
     */
    Integer insertAndReturnKey(ContentActivityLogWithBLOBs value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_ecm_activity_log
     *
     * @mbggenerated Wed Mar 11 09:10:41 ICT 2015
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_ecm_activity_log
     *
     * @mbggenerated Wed Mar 11 09:10:41 ICT 2015
     */
    void massUpdateWithSession(@Param("record") ContentActivityLogWithBLOBs record, @Param("primaryKeys") List primaryKeys);
}