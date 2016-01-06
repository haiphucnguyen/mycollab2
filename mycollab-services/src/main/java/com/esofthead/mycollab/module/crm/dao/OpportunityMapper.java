package com.esofthead.mycollab.module.crm.dao;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.module.crm.domain.Opportunity;
import com.esofthead.mycollab.module.crm.domain.OpportunityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@SuppressWarnings({ "ucd", "rawtypes" })
public interface OpportunityMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunity
     *
     * @mbggenerated Tue Jan 05 23:09:02 ICT 2016
     */
    int countByExample(OpportunityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunity
     *
     * @mbggenerated Tue Jan 05 23:09:02 ICT 2016
     */
    int deleteByExample(OpportunityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunity
     *
     * @mbggenerated Tue Jan 05 23:09:02 ICT 2016
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunity
     *
     * @mbggenerated Tue Jan 05 23:09:02 ICT 2016
     */
    int insert(Opportunity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunity
     *
     * @mbggenerated Tue Jan 05 23:09:02 ICT 2016
     */
    int insertSelective(Opportunity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunity
     *
     * @mbggenerated Tue Jan 05 23:09:02 ICT 2016
     */
    List<Opportunity> selectByExampleWithBLOBs(OpportunityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunity
     *
     * @mbggenerated Tue Jan 05 23:09:02 ICT 2016
     */
    List<Opportunity> selectByExample(OpportunityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunity
     *
     * @mbggenerated Tue Jan 05 23:09:02 ICT 2016
     */
    Opportunity selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunity
     *
     * @mbggenerated Tue Jan 05 23:09:02 ICT 2016
     */
    int updateByExampleSelective(@Param("record") Opportunity record, @Param("example") OpportunityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunity
     *
     * @mbggenerated Tue Jan 05 23:09:02 ICT 2016
     */
    int updateByExampleWithBLOBs(@Param("record") Opportunity record, @Param("example") OpportunityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunity
     *
     * @mbggenerated Tue Jan 05 23:09:02 ICT 2016
     */
    int updateByExample(@Param("record") Opportunity record, @Param("example") OpportunityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunity
     *
     * @mbggenerated Tue Jan 05 23:09:02 ICT 2016
     */
    int updateByPrimaryKeySelective(Opportunity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunity
     *
     * @mbggenerated Tue Jan 05 23:09:02 ICT 2016
     */
    int updateByPrimaryKeyWithBLOBs(Opportunity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunity
     *
     * @mbggenerated Tue Jan 05 23:09:02 ICT 2016
     */
    int updateByPrimaryKey(Opportunity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunity
     *
     * @mbggenerated Tue Jan 05 23:09:02 ICT 2016
     */
    Integer insertAndReturnKey(Opportunity value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunity
     *
     * @mbggenerated Tue Jan 05 23:09:02 ICT 2016
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_opportunity
     *
     * @mbggenerated Tue Jan 05 23:09:02 ICT 2016
     */
    void massUpdateWithSession(@Param("record") Opportunity record, @Param("primaryKeys") List primaryKeys);
}