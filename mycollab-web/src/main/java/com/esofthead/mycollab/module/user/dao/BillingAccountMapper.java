package com.esofthead.mycollab.module.user.dao;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.module.user.domain.BillingAccount;
import com.esofthead.mycollab.module.user.domain.BillingAccountExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BillingAccountMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_account
     *
     * @mbggenerated Sun Dec 23 17:29:19 GMT+07:00 2012
     */
    int countByExample(BillingAccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_account
     *
     * @mbggenerated Sun Dec 23 17:29:19 GMT+07:00 2012
     */
    int deleteByExample(BillingAccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_account
     *
     * @mbggenerated Sun Dec 23 17:29:19 GMT+07:00 2012
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_account
     *
     * @mbggenerated Sun Dec 23 17:29:19 GMT+07:00 2012
     */
    int insert(BillingAccount record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_account
     *
     * @mbggenerated Sun Dec 23 17:29:19 GMT+07:00 2012
     */
    int insertSelective(BillingAccount record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_account
     *
     * @mbggenerated Sun Dec 23 17:29:19 GMT+07:00 2012
     */
    List<BillingAccount> selectByExample(BillingAccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_account
     *
     * @mbggenerated Sun Dec 23 17:29:19 GMT+07:00 2012
     */
    BillingAccount selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_account
     *
     * @mbggenerated Sun Dec 23 17:29:19 GMT+07:00 2012
     */
    int updateByExampleSelective(@Param("record") BillingAccount record, @Param("example") BillingAccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_account
     *
     * @mbggenerated Sun Dec 23 17:29:19 GMT+07:00 2012
     */
    int updateByExample(@Param("record") BillingAccount record, @Param("example") BillingAccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_account
     *
     * @mbggenerated Sun Dec 23 17:29:19 GMT+07:00 2012
     */
    int updateByPrimaryKeySelective(BillingAccount record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_account
     *
     * @mbggenerated Sun Dec 23 17:29:19 GMT+07:00 2012
     */
    int updateByPrimaryKey(BillingAccount record);
}