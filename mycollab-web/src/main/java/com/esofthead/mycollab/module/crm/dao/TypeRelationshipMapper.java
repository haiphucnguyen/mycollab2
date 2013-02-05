package com.esofthead.mycollab.module.crm.dao;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.module.crm.domain.TypeRelationship;
import com.esofthead.mycollab.module.crm.domain.TypeRelationshipExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TypeRelationshipMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_type_relationship
     *
     * @mbggenerated Tue Feb 05 10:19:31 GMT+07:00 2013
     */
    int countByExample(TypeRelationshipExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_type_relationship
     *
     * @mbggenerated Tue Feb 05 10:19:31 GMT+07:00 2013
     */
    int deleteByExample(TypeRelationshipExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_type_relationship
     *
     * @mbggenerated Tue Feb 05 10:19:31 GMT+07:00 2013
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_type_relationship
     *
     * @mbggenerated Tue Feb 05 10:19:31 GMT+07:00 2013
     */
    int insert(TypeRelationship record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_type_relationship
     *
     * @mbggenerated Tue Feb 05 10:19:31 GMT+07:00 2013
     */
    int insertSelective(TypeRelationship record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_type_relationship
     *
     * @mbggenerated Tue Feb 05 10:19:31 GMT+07:00 2013
     */
    List<TypeRelationship> selectByExample(TypeRelationshipExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_type_relationship
     *
     * @mbggenerated Tue Feb 05 10:19:31 GMT+07:00 2013
     */
    TypeRelationship selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_type_relationship
     *
     * @mbggenerated Tue Feb 05 10:19:31 GMT+07:00 2013
     */
    int updateByExampleSelective(@Param("record") TypeRelationship record, @Param("example") TypeRelationshipExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_type_relationship
     *
     * @mbggenerated Tue Feb 05 10:19:31 GMT+07:00 2013
     */
    int updateByExample(@Param("record") TypeRelationship record, @Param("example") TypeRelationshipExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_type_relationship
     *
     * @mbggenerated Tue Feb 05 10:19:31 GMT+07:00 2013
     */
    int updateByPrimaryKeySelective(TypeRelationship record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_type_relationship
     *
     * @mbggenerated Tue Feb 05 10:19:31 GMT+07:00 2013
     */
    int updateByPrimaryKey(TypeRelationship record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_type_relationship
     *
     * @mbggenerated Tue Feb 05 10:19:31 GMT+07:00 2013
     */
    Integer insertAndReturnKey(TypeRelationship value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_type_relationship
     *
     * @mbggenerated Tue Feb 05 10:19:31 GMT+07:00 2013
     */
    void removeKeysWithSession(List primaryKeys);
}