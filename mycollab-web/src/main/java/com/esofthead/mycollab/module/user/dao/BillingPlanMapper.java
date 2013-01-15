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
     * @mbggenerated Tue Jan 15 15:48:49 GMT+07:00 2013
     */
    int countByExample(BillingPlanExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_billing_plan
     *
     * @mbggenerated Tue Jan 15 15:48:49 GMT+07:00 2013
     */
    int deleteByExample(BillingPlanExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_billing_plan
     *
     * @mbggenerated Tue Jan 15 15:48:49 GMT+07:00 2013
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_billing_plan
     *
     * @mbggenerated Tue Jan 15 15:48:49 GMT+07:00 2013
     */
    int insert(BillingPlan record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_billing_plan
     *
     * @mbggenerated Tue Jan 15 15:48:49 GMT+07:00 2013
     */
    int insertSelective(BillingPlan record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_billing_plan
     *
     * @mbggenerated Tue Jan 15 15:48:49 GMT+07:00 2013
     */
    List<BillingPlan> selectByExample(BillingPlanExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_billing_plan
     *
     * @mbggenerated Tue Jan 15 15:48:49 GMT+07:00 2013
     */
    BillingPlan selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_billing_plan
     *
     * @mbggenerated Tue Jan 15 15:48:49 GMT+07:00 2013
     */
    int updateByExampleSelective(@Param("record") BillingPlan record, @Param("example") BillingPlanExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_billing_plan
     *
     * @mbggenerated Tue Jan 15 15:48:49 GMT+07:00 2013
     */
    int updateByExample(@Param("record") BillingPlan record, @Param("example") BillingPlanExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_billing_plan
     *
     * @mbggenerated Tue Jan 15 15:48:49 GMT+07:00 2013
     */
    int updateByPrimaryKeySelective(BillingPlan record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_billing_plan
     *
     * @mbggenerated Tue Jan 15 15:48:49 GMT+07:00 2013
     */
    int updateByPrimaryKey(BillingPlan record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_billing_plan
     *
     * @mbggenerated Tue Jan 15 15:48:49 GMT+07:00 2013
     */
    Integer insertAndReturnKey(BillingPlan value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_billing_plan
     *
     * @mbggenerated Tue Jan 15 15:48:49 GMT+07:00 2013
     */
    void removeKeysWithSession(List primaryKeys);
}