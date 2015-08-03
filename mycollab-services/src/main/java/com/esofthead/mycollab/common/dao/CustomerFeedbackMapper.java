package com.esofthead.mycollab.common.dao;

import com.esofthead.mycollab.common.domain.CustomerFeedbackExample;
import com.esofthead.mycollab.common.domain.CustomerFeedbackWithBLOBs;
import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@SuppressWarnings({ "ucd", "rawtypes" })
public interface CustomerFeedbackMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_customer_feedback
     *
     * @mbggenerated Mon Aug 03 15:23:14 ICT 2015
     */
    int countByExample(CustomerFeedbackExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_customer_feedback
     *
     * @mbggenerated Mon Aug 03 15:23:14 ICT 2015
     */
    int deleteByExample(CustomerFeedbackExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_customer_feedback
     *
     * @mbggenerated Mon Aug 03 15:23:14 ICT 2015
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_customer_feedback
     *
     * @mbggenerated Mon Aug 03 15:23:14 ICT 2015
     */
    int insert(CustomerFeedbackWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_customer_feedback
     *
     * @mbggenerated Mon Aug 03 15:23:14 ICT 2015
     */
    int insertSelective(CustomerFeedbackWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_customer_feedback
     *
     * @mbggenerated Mon Aug 03 15:23:14 ICT 2015
     */
    List<CustomerFeedbackWithBLOBs> selectByExampleWithBLOBs(CustomerFeedbackExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_customer_feedback
     *
     * @mbggenerated Mon Aug 03 15:23:14 ICT 2015
     */
    CustomerFeedbackWithBLOBs selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_customer_feedback
     *
     * @mbggenerated Mon Aug 03 15:23:14 ICT 2015
     */
    int updateByExampleSelective(@Param("record") CustomerFeedbackWithBLOBs record, @Param("example") CustomerFeedbackExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_customer_feedback
     *
     * @mbggenerated Mon Aug 03 15:23:14 ICT 2015
     */
    int updateByExampleWithBLOBs(@Param("record") CustomerFeedbackWithBLOBs record, @Param("example") CustomerFeedbackExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_customer_feedback
     *
     * @mbggenerated Mon Aug 03 15:23:14 ICT 2015
     */
    int updateByPrimaryKeySelective(CustomerFeedbackWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_customer_feedback
     *
     * @mbggenerated Mon Aug 03 15:23:14 ICT 2015
     */
    int updateByPrimaryKeyWithBLOBs(CustomerFeedbackWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_customer_feedback
     *
     * @mbggenerated Mon Aug 03 15:23:14 ICT 2015
     */
    Integer insertAndReturnKey(CustomerFeedbackWithBLOBs value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_customer_feedback
     *
     * @mbggenerated Mon Aug 03 15:23:14 ICT 2015
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_customer_feedback
     *
     * @mbggenerated Mon Aug 03 15:23:14 ICT 2015
     */
    void massUpdateWithSession(@Param("record") CustomerFeedbackWithBLOBs record, @Param("primaryKeys") List primaryKeys);
}