package com.mycollab.ondemand.module.billing.dao;

import com.mycollab.db.persistence.ICrudGenericDAO;
import com.mycollab.ondemand.module.billing.domain.BillingSubscriptionHistory;
import com.mycollab.ondemand.module.billing.domain.BillingSubscriptionHistoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@SuppressWarnings({ "ucd", "rawtypes" })
public interface BillingSubscriptionHistoryMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_billing_subscription_history
     *
     * @mbg.generated Sun Jan 06 16:54:13 CST 2019
     */
    long countByExample(BillingSubscriptionHistoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_billing_subscription_history
     *
     * @mbg.generated Sun Jan 06 16:54:13 CST 2019
     */
    int deleteByExample(BillingSubscriptionHistoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_billing_subscription_history
     *
     * @mbg.generated Sun Jan 06 16:54:13 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_billing_subscription_history
     *
     * @mbg.generated Sun Jan 06 16:54:13 CST 2019
     */
    int insert(BillingSubscriptionHistory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_billing_subscription_history
     *
     * @mbg.generated Sun Jan 06 16:54:13 CST 2019
     */
    int insertSelective(BillingSubscriptionHistory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_billing_subscription_history
     *
     * @mbg.generated Sun Jan 06 16:54:13 CST 2019
     */
    List<BillingSubscriptionHistory> selectByExample(BillingSubscriptionHistoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_billing_subscription_history
     *
     * @mbg.generated Sun Jan 06 16:54:13 CST 2019
     */
    BillingSubscriptionHistory selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_billing_subscription_history
     *
     * @mbg.generated Sun Jan 06 16:54:13 CST 2019
     */
    int updateByExampleSelective(@Param("record") BillingSubscriptionHistory record, @Param("example") BillingSubscriptionHistoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_billing_subscription_history
     *
     * @mbg.generated Sun Jan 06 16:54:13 CST 2019
     */
    int updateByExample(@Param("record") BillingSubscriptionHistory record, @Param("example") BillingSubscriptionHistoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_billing_subscription_history
     *
     * @mbg.generated Sun Jan 06 16:54:13 CST 2019
     */
    int updateByPrimaryKeySelective(BillingSubscriptionHistory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_billing_subscription_history
     *
     * @mbg.generated Sun Jan 06 16:54:13 CST 2019
     */
    int updateByPrimaryKey(BillingSubscriptionHistory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_billing_subscription_history
     *
     * @mbg.generated Sun Jan 06 16:54:13 CST 2019
     */
    Integer insertAndReturnKey(BillingSubscriptionHistory value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_billing_subscription_history
     *
     * @mbg.generated Sun Jan 06 16:54:13 CST 2019
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_billing_subscription_history
     *
     * @mbg.generated Sun Jan 06 16:54:13 CST 2019
     */
    void massUpdateWithSession(@Param("record") BillingSubscriptionHistory record, @Param("primaryKeys") List primaryKeys);
}