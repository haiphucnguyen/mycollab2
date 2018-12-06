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
     * @mbg.generated Thu Dec 06 08:34:03 CST 2018
     */
    long countByExample(CommunityLeadExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_customer_lead
     *
     * @mbg.generated Thu Dec 06 08:34:03 CST 2018
     */
    int deleteByExample(CommunityLeadExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_customer_lead
     *
     * @mbg.generated Thu Dec 06 08:34:03 CST 2018
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_customer_lead
     *
     * @mbg.generated Thu Dec 06 08:34:03 CST 2018
     */
    int insert(CommunityLead record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_customer_lead
     *
     * @mbg.generated Thu Dec 06 08:34:03 CST 2018
     */
    int insertSelective(CommunityLead record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_customer_lead
     *
     * @mbg.generated Thu Dec 06 08:34:03 CST 2018
     */
    List<CommunityLead> selectByExample(CommunityLeadExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_customer_lead
     *
     * @mbg.generated Thu Dec 06 08:34:03 CST 2018
     */
    CommunityLead selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_customer_lead
     *
     * @mbg.generated Thu Dec 06 08:34:03 CST 2018
     */
    int updateByExampleSelective(@Param("record") CommunityLead record, @Param("example") CommunityLeadExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_customer_lead
     *
     * @mbg.generated Thu Dec 06 08:34:03 CST 2018
     */
    int updateByExample(@Param("record") CommunityLead record, @Param("example") CommunityLeadExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_customer_lead
     *
     * @mbg.generated Thu Dec 06 08:34:03 CST 2018
     */
    int updateByPrimaryKeySelective(CommunityLead record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_customer_lead
     *
     * @mbg.generated Thu Dec 06 08:34:03 CST 2018
     */
    int updateByPrimaryKey(CommunityLead record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_customer_lead
     *
     * @mbg.generated Thu Dec 06 08:34:03 CST 2018
     */
    Integer insertAndReturnKey(CommunityLead value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_customer_lead
     *
     * @mbg.generated Thu Dec 06 08:34:03 CST 2018
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_customer_lead
     *
     * @mbg.generated Thu Dec 06 08:34:03 CST 2018
     */
    void massUpdateWithSession(@Param("record") CommunityLead record, @Param("primaryKeys") List primaryKeys);
}