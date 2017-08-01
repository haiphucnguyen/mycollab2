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
     * @mbg.generated Tue Aug 01 11:17:31 ICT 2017
     */
    long countByExample(BillingAccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_account
     *
     * @mbg.generated Tue Aug 01 11:17:31 ICT 2017
     */
    int deleteByExample(BillingAccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_account
     *
     * @mbg.generated Tue Aug 01 11:17:31 ICT 2017
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_account
     *
     * @mbg.generated Tue Aug 01 11:17:31 ICT 2017
     */
    int insert(BillingAccount record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_account
     *
     * @mbg.generated Tue Aug 01 11:17:31 ICT 2017
     */
    int insertSelective(BillingAccount record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_account
     *
     * @mbg.generated Tue Aug 01 11:17:31 ICT 2017
     */
    List<BillingAccount> selectByExample(BillingAccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_account
     *
     * @mbg.generated Tue Aug 01 11:17:31 ICT 2017
     */
    BillingAccount selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_account
     *
     * @mbg.generated Tue Aug 01 11:17:31 ICT 2017
     */
    int updateByExampleSelective(@Param("record") BillingAccount record, @Param("example") BillingAccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_account
     *
     * @mbg.generated Tue Aug 01 11:17:31 ICT 2017
     */
    int updateByExample(@Param("record") BillingAccount record, @Param("example") BillingAccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_account
     *
     * @mbg.generated Tue Aug 01 11:17:31 ICT 2017
     */
    int updateByPrimaryKeySelective(BillingAccount record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_account
     *
     * @mbg.generated Tue Aug 01 11:17:31 ICT 2017
     */
    int updateByPrimaryKey(BillingAccount record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_account
     *
     * @mbg.generated Tue Aug 01 11:17:31 ICT 2017
     */
    Integer insertAndReturnKey(BillingAccount value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_account
     *
     * @mbg.generated Tue Aug 01 11:17:31 ICT 2017
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_account
     *
     * @mbg.generated Tue Aug 01 11:17:31 ICT 2017
     */
    void massUpdateWithSession(@Param("record") BillingAccount record, @Param("primaryKeys") List primaryKeys);
}