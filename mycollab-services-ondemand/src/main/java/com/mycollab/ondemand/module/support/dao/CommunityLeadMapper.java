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
     * @mbggenerated Sat Jul 09 15:44:21 ICT 2016
     */
    int countByExample(CommunityLeadExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_customer_lead
     *
     * @mbggenerated Sat Jul 09 15:44:21 ICT 2016
     */
    int deleteByExample(CommunityLeadExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_customer_lead
     *
     * @mbggenerated Sat Jul 09 15:44:21 ICT 2016
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_customer_lead
     *
     * @mbggenerated Sat Jul 09 15:44:21 ICT 2016
     */
    int insert(CommunityLead record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_customer_lead
     *
     * @mbggenerated Sat Jul 09 15:44:21 ICT 2016
     */
    int insertSelective(CommunityLead record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_customer_lead
     *
     * @mbggenerated Sat Jul 09 15:44:21 ICT 2016
     */
    List<CommunityLead> selectByExample(CommunityLeadExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_customer_lead
     *
     * @mbggenerated Sat Jul 09 15:44:21 ICT 2016
     */
    CommunityLead selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_customer_lead
     *
     * @mbggenerated Sat Jul 09 15:44:21 ICT 2016
     */
    int updateByExampleSelective(@Param("record") CommunityLead record, @Param("example") CommunityLeadExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_customer_lead
     *
     * @mbggenerated Sat Jul 09 15:44:21 ICT 2016
     */
    int updateByExample(@Param("record") CommunityLead record, @Param("example") CommunityLeadExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_customer_lead
     *
     * @mbggenerated Sat Jul 09 15:44:21 ICT 2016
     */
    int updateByPrimaryKeySelective(CommunityLead record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_customer_lead
     *
     * @mbggenerated Sat Jul 09 15:44:21 ICT 2016
     */
    int updateByPrimaryKey(CommunityLead record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_customer_lead
     *
     * @mbggenerated Sat Jul 09 15:44:21 ICT 2016
     */
    Integer insertAndReturnKey(CommunityLead value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_customer_lead
     *
     * @mbggenerated Sat Jul 09 15:44:21 ICT 2016
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_customer_lead
     *
     * @mbggenerated Sat Jul 09 15:44:21 ICT 2016
     */
    void massUpdateWithSession(@Param("record") CommunityLead record, @Param("primaryKeys") List primaryKeys);
}