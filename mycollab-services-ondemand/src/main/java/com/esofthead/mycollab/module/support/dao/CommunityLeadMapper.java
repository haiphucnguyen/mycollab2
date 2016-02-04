package com.esofthead.mycollab.module.support.dao;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.module.support.domain.CommunityLead;
import com.esofthead.mycollab.module.support.domain.CommunityLeadExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@SuppressWarnings({ "ucd", "rawtypes" })
public interface CommunityLeadMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_customer_lead
     *
     * @mbggenerated Wed Dec 30 11:13:25 ICT 2015
     */
    int countByExample(CommunityLeadExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_customer_lead
     *
     * @mbggenerated Wed Dec 30 11:13:25 ICT 2015
     */
    int deleteByExample(CommunityLeadExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_customer_lead
     *
     * @mbggenerated Wed Dec 30 11:13:25 ICT 2015
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_customer_lead
     *
     * @mbggenerated Wed Dec 30 11:13:25 ICT 2015
     */
    int insert(CommunityLead record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_customer_lead
     *
     * @mbggenerated Wed Dec 30 11:13:25 ICT 2015
     */
    int insertSelective(CommunityLead record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_customer_lead
     *
     * @mbggenerated Wed Dec 30 11:13:25 ICT 2015
     */
    List<CommunityLead> selectByExample(CommunityLeadExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_customer_lead
     *
     * @mbggenerated Wed Dec 30 11:13:25 ICT 2015
     */
    CommunityLead selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_customer_lead
     *
     * @mbggenerated Wed Dec 30 11:13:25 ICT 2015
     */
    int updateByExampleSelective(@Param("record") CommunityLead record, @Param("example") CommunityLeadExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_customer_lead
     *
     * @mbggenerated Wed Dec 30 11:13:25 ICT 2015
     */
    int updateByExample(@Param("record") CommunityLead record, @Param("example") CommunityLeadExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_customer_lead
     *
     * @mbggenerated Wed Dec 30 11:13:25 ICT 2015
     */
    int updateByPrimaryKeySelective(CommunityLead record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_customer_lead
     *
     * @mbggenerated Wed Dec 30 11:13:25 ICT 2015
     */
    int updateByPrimaryKey(CommunityLead record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_customer_lead
     *
     * @mbggenerated Wed Dec 30 11:13:25 ICT 2015
     */
    Integer insertAndReturnKey(CommunityLead value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_customer_lead
     *
     * @mbggenerated Wed Dec 30 11:13:25 ICT 2015
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_customer_lead
     *
     * @mbggenerated Wed Dec 30 11:13:25 ICT 2015
     */
    void massUpdateWithSession(@Param("record") CommunityLead record, @Param("primaryKeys") List primaryKeys);
}