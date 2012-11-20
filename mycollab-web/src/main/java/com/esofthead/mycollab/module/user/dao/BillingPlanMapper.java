package com.esofthead.mycollab.module.user.dao;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.module.user.domain.BillingPlan;
import com.esofthead.mycollab.module.user.domain.BillingPlanExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BillingPlanMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_billing_plan
     *
     * @mbggenerated Wed Nov 14 15:52:02 GMT+07:00 2012
     */
    int countByExample(BillingPlanExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_billing_plan
     *
     * @mbggenerated Wed Nov 14 15:52:02 GMT+07:00 2012
     */
    int deleteByExample(BillingPlanExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_billing_plan
     *
     * @mbggenerated Wed Nov 14 15:52:02 GMT+07:00 2012
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_billing_plan
     *
     * @mbggenerated Wed Nov 14 15:52:02 GMT+07:00 2012
     */
    int insert(BillingPlan record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_billing_plan
     *
     * @mbggenerated Wed Nov 14 15:52:02 GMT+07:00 2012
     */
    int insertSelective(BillingPlan record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_billing_plan
     *
     * @mbggenerated Wed Nov 14 15:52:02 GMT+07:00 2012
     */
    List<BillingPlan> selectByExample(BillingPlanExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_billing_plan
     *
     * @mbggenerated Wed Nov 14 15:52:02 GMT+07:00 2012
     */
    BillingPlan selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_billing_plan
     *
     * @mbggenerated Wed Nov 14 15:52:02 GMT+07:00 2012
     */
    int updateByExampleSelective(@Param("record") BillingPlan record, @Param("example") BillingPlanExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_billing_plan
     *
     * @mbggenerated Wed Nov 14 15:52:02 GMT+07:00 2012
     */
    int updateByExample(@Param("record") BillingPlan record, @Param("example") BillingPlanExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_billing_plan
     *
     * @mbggenerated Wed Nov 14 15:52:02 GMT+07:00 2012
     */
    int updateByPrimaryKeySelective(BillingPlan record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_billing_plan
     *
     * @mbggenerated Wed Nov 14 15:52:02 GMT+07:00 2012
     */
    int updateByPrimaryKey(BillingPlan record);
}