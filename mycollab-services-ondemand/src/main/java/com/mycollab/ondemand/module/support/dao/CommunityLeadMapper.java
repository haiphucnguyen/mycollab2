package com.mycollab.ondemand.module.support.dao;

import com.mycollab.db.persistence.ICrudGenericDAO;
import com.mycollab.ondemand.module.support.domain.CommunityLead;
import com.mycollab.ondemand.module.support.domain.CommunityLeadExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@SuppressWarnings({ "ucd", "rawtypes" })
public interface CommunityLeadMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_customer_lead
     *
     * @mbg.generated Sun Jan 06 16:54:13 CST 2019
     */
    long countByExample(CommunityLeadExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_customer_lead
     *
     * @mbg.generated Sun Jan 06 16:54:13 CST 2019
     */
    int deleteByExample(CommunityLeadExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_customer_lead
     *
     * @mbg.generated Sun Jan 06 16:54:13 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_customer_lead
     *
     * @mbg.generated Sun Jan 06 16:54:13 CST 2019
     */
    int insert(CommunityLead record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_customer_lead
     *
     * @mbg.generated Sun Jan 06 16:54:13 CST 2019
     */
    int insertSelective(CommunityLead record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_customer_lead
     *
     * @mbg.generated Sun Jan 06 16:54:13 CST 2019
     */
    List<CommunityLead> selectByExample(CommunityLeadExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_customer_lead
     *
     * @mbg.generated Sun Jan 06 16:54:13 CST 2019
     */
    CommunityLead selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_customer_lead
     *
     * @mbg.generated Sun Jan 06 16:54:13 CST 2019
     */
    int updateByExampleSelective(@Param("record") CommunityLead record, @Param("example") CommunityLeadExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_customer_lead
     *
     * @mbg.generated Sun Jan 06 16:54:13 CST 2019
     */
    int updateByExample(@Param("record") CommunityLead record, @Param("example") CommunityLeadExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_customer_lead
     *
     * @mbg.generated Sun Jan 06 16:54:13 CST 2019
     */
    int updateByPrimaryKeySelective(CommunityLead record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_customer_lead
     *
     * @mbg.generated Sun Jan 06 16:54:13 CST 2019
     */
    int updateByPrimaryKey(CommunityLead record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_customer_lead
     *
     * @mbg.generated Sun Jan 06 16:54:13 CST 2019
     */
    Integer insertAndReturnKey(CommunityLead value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_customer_lead
     *
     * @mbg.generated Sun Jan 06 16:54:13 CST 2019
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_customer_lead
     *
     * @mbg.generated Sun Jan 06 16:54:13 CST 2019
     */
    void massUpdateWithSession(@Param("record") CommunityLead record, @Param("primaryKeys") List primaryKeys);
}