package com.mycollab.module.user.dao;

import com.mycollab.db.persistence.ICrudGenericDAO;
import com.mycollab.module.user.domain.BillingPlan;
import com.mycollab.module.user.domain.BillingPlanExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@SuppressWarnings({ "ucd", "rawtypes" })
public interface BillingPlanMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_billing_plan
     *
     * @mbg.generated Fri Sep 23 03:45:53 ICT 2016
     */
    long countByExample(BillingPlanExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_billing_plan
     *
     * @mbg.generated Fri Sep 23 03:45:53 ICT 2016
     */
    int deleteByExample(BillingPlanExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_billing_plan
     *
     * @mbg.generated Fri Sep 23 03:45:53 ICT 2016
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_billing_plan
     *
     * @mbg.generated Fri Sep 23 03:45:53 ICT 2016
     */
    int insert(BillingPlan record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_billing_plan
     *
     * @mbg.generated Fri Sep 23 03:45:53 ICT 2016
     */
    int insertSelective(BillingPlan record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_billing_plan
     *
     * @mbg.generated Fri Sep 23 03:45:53 ICT 2016
     */
    List<BillingPlan> selectByExample(BillingPlanExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_billing_plan
     *
     * @mbg.generated Fri Sep 23 03:45:53 ICT 2016
     */
    BillingPlan selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_billing_plan
     *
     * @mbg.generated Fri Sep 23 03:45:53 ICT 2016
     */
    int updateByExampleSelective(@Param("record") BillingPlan record, @Param("example") BillingPlanExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_billing_plan
     *
     * @mbg.generated Fri Sep 23 03:45:53 ICT 2016
     */
    int updateByExample(@Param("record") BillingPlan record, @Param("example") BillingPlanExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_billing_plan
     *
     * @mbg.generated Fri Sep 23 03:45:53 ICT 2016
     */
    int updateByPrimaryKeySelective(BillingPlan record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_billing_plan
     *
     * @mbg.generated Fri Sep 23 03:45:53 ICT 2016
     */
    int updateByPrimaryKey(BillingPlan record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_billing_plan
     *
     * @mbg.generated Fri Sep 23 03:45:53 ICT 2016
     */
    Integer insertAndReturnKey(BillingPlan value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_billing_plan
     *
     * @mbg.generated Fri Sep 23 03:45:53 ICT 2016
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_billing_plan
     *
     * @mbg.generated Fri Sep 23 03:45:53 ICT 2016
     */
    void massUpdateWithSession(@Param("record") BillingPlan record, @Param("primaryKeys") List primaryKeys);
}