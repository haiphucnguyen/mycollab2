package com.esofthead.mycollab.common.dao;

import com.esofthead.mycollab.common.domain.CustomerFeedbackExample;
import com.esofthead.mycollab.common.domain.CustomerFeedbackWithBLOBs;
import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CustomerFeedbackMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_customer_feedback
     *
     * @mbggenerated Sun Oct 13 15:52:38 ICT 2013
     */
    int countByExample(CustomerFeedbackExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_customer_feedback
     *
     * @mbggenerated Sun Oct 13 15:52:38 ICT 2013
     */
    int deleteByExample(CustomerFeedbackExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_customer_feedback
     *
     * @mbggenerated Sun Oct 13 15:52:38 ICT 2013
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_customer_feedback
     *
     * @mbggenerated Sun Oct 13 15:52:38 ICT 2013
     */
    int insert(CustomerFeedbackWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_customer_feedback
     *
     * @mbggenerated Sun Oct 13 15:52:38 ICT 2013
     */
    int insertSelective(CustomerFeedbackWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_customer_feedback
     *
     * @mbggenerated Sun Oct 13 15:52:38 ICT 2013
     */
    List<CustomerFeedbackWithBLOBs> selectByExampleWithBLOBs(CustomerFeedbackExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_customer_feedback
     *
     * @mbggenerated Sun Oct 13 15:52:38 ICT 2013
     */
    CustomerFeedbackWithBLOBs selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_customer_feedback
     *
     * @mbggenerated Sun Oct 13 15:52:38 ICT 2013
     */
    int updateByExampleSelective(@Param("record") CustomerFeedbackWithBLOBs record, @Param("example") CustomerFeedbackExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_customer_feedback
     *
     * @mbggenerated Sun Oct 13 15:52:38 ICT 2013
     */
    int updateByExampleWithBLOBs(@Param("record") CustomerFeedbackWithBLOBs record, @Param("example") CustomerFeedbackExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_customer_feedback
     *
     * @mbggenerated Sun Oct 13 15:52:38 ICT 2013
     */
    int updateByPrimaryKeySelective(CustomerFeedbackWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_customer_feedback
     *
     * @mbggenerated Sun Oct 13 15:52:38 ICT 2013
     */
    Integer insertAndReturnKey(CustomerFeedbackWithBLOBs value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_customer_feedback
     *
     * @mbggenerated Sun Oct 13 15:52:38 ICT 2013
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_customer_feedback
     *
     * @mbggenerated Sun Oct 13 15:52:38 ICT 2013
     */
    void massUpdateWithSession(@Param("record") CustomerFeedbackWithBLOBs record, @Param("primaryKeys") List primaryKeys);
}