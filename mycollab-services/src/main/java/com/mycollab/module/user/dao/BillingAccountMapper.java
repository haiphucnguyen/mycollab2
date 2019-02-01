package com.mycollab.module.user.dao;

import com.mycollab.db.persistence.ICrudGenericDAO;
import com.mycollab.module.user.domain.BillingAccount;
import com.mycollab.module.user.domain.BillingAccountExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@SuppressWarnings({ "ucd", "rawtypes" })
public interface BillingAccountMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_account
     *
     * @mbg.generated Fri Feb 01 07:44:14 CST 2019
     */
    long countByExample(BillingAccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_account
     *
     * @mbg.generated Fri Feb 01 07:44:14 CST 2019
     */
    int deleteByExample(BillingAccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_account
     *
     * @mbg.generated Fri Feb 01 07:44:14 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_account
     *
     * @mbg.generated Fri Feb 01 07:44:14 CST 2019
     */
    int insert(BillingAccount record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_account
     *
     * @mbg.generated Fri Feb 01 07:44:14 CST 2019
     */
    int insertSelective(BillingAccount record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_account
     *
     * @mbg.generated Fri Feb 01 07:44:14 CST 2019
     */
    List<BillingAccount> selectByExample(BillingAccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_account
     *
     * @mbg.generated Fri Feb 01 07:44:14 CST 2019
     */
    BillingAccount selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_account
     *
     * @mbg.generated Fri Feb 01 07:44:14 CST 2019
     */
    int updateByExampleSelective(@Param("record") BillingAccount record, @Param("example") BillingAccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_account
     *
     * @mbg.generated Fri Feb 01 07:44:14 CST 2019
     */
    int updateByExample(@Param("record") BillingAccount record, @Param("example") BillingAccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_account
     *
     * @mbg.generated Fri Feb 01 07:44:14 CST 2019
     */
    int updateByPrimaryKeySelective(BillingAccount record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_account
     *
     * @mbg.generated Fri Feb 01 07:44:14 CST 2019
     */
    int updateByPrimaryKey(BillingAccount record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_account
     *
     * @mbg.generated Fri Feb 01 07:44:14 CST 2019
     */
    Integer insertAndReturnKey(BillingAccount value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_account
     *
     * @mbg.generated Fri Feb 01 07:44:14 CST 2019
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_account
     *
     * @mbg.generated Fri Feb 01 07:44:14 CST 2019
     */
    void massUpdateWithSession(@Param("record") BillingAccount record, @Param("primaryKeys") List primaryKeys);
}