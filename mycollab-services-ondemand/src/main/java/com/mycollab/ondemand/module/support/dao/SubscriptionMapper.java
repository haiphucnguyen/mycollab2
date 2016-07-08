package com.mycollab.ondemand.module.support.dao;

import com.mycollab.db.persistence.ICrudGenericDAO;
import com.mycollab.ondemand.module.support.domain.Subscription;
import com.mycollab.ondemand.module.support.domain.SubscriptionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@SuppressWarnings({ "ucd", "rawtypes" })
public interface SubscriptionMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_billing_subscription
     *
     * @mbggenerated Fri Jul 01 21:48:36 ICT 2016
     */
    int countByExample(SubscriptionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_billing_subscription
     *
     * @mbggenerated Fri Jul 01 21:48:36 ICT 2016
     */
    int deleteByExample(SubscriptionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_billing_subscription
     *
     * @mbggenerated Fri Jul 01 21:48:36 ICT 2016
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_billing_subscription
     *
     * @mbggenerated Fri Jul 01 21:48:36 ICT 2016
     */
    int insert(Subscription record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_billing_subscription
     *
     * @mbggenerated Fri Jul 01 21:48:36 ICT 2016
     */
    int insertSelective(Subscription record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_billing_subscription
     *
     * @mbggenerated Fri Jul 01 21:48:36 ICT 2016
     */
    List<Subscription> selectByExample(SubscriptionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_billing_subscription
     *
     * @mbggenerated Fri Jul 01 21:48:36 ICT 2016
     */
    Subscription selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_billing_subscription
     *
     * @mbggenerated Fri Jul 01 21:48:36 ICT 2016
     */
    int updateByExampleSelective(@Param("record") Subscription record, @Param("example") SubscriptionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_billing_subscription
     *
     * @mbggenerated Fri Jul 01 21:48:36 ICT 2016
     */
    int updateByExample(@Param("record") Subscription record, @Param("example") SubscriptionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_billing_subscription
     *
     * @mbggenerated Fri Jul 01 21:48:36 ICT 2016
     */
    int updateByPrimaryKeySelective(Subscription record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_billing_subscription
     *
     * @mbggenerated Fri Jul 01 21:48:36 ICT 2016
     */
    int updateByPrimaryKey(Subscription record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_billing_subscription
     *
     * @mbggenerated Fri Jul 01 21:48:36 ICT 2016
     */
    Integer insertAndReturnKey(Subscription value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_billing_subscription
     *
     * @mbggenerated Fri Jul 01 21:48:36 ICT 2016
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_billing_subscription
     *
     * @mbggenerated Fri Jul 01 21:48:36 ICT 2016
     */
    void massUpdateWithSession(@Param("record") Subscription record, @Param("primaryKeys") List primaryKeys);
}