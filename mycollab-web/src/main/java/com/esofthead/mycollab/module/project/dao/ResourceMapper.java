package com.esofthead.mycollab.module.project.dao;

import com.esofthead.mycollab.core.persistence.CrudGenericDAO;
import com.esofthead.mycollab.module.project.domain.Resource;
import com.esofthead.mycollab.module.project.domain.ResourceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ResourceMapper extends CrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_resource
     *
     * @mbggenerated Fri Nov 09 17:13:41 GMT+07:00 2012
     */
    int countByExample(ResourceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_resource
     *
     * @mbggenerated Fri Nov 09 17:13:41 GMT+07:00 2012
     */
    int deleteByExample(ResourceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_resource
     *
     * @mbggenerated Fri Nov 09 17:13:41 GMT+07:00 2012
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_resource
     *
     * @mbggenerated Fri Nov 09 17:13:41 GMT+07:00 2012
     */
    int insert(Resource record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_resource
     *
     * @mbggenerated Fri Nov 09 17:13:41 GMT+07:00 2012
     */
    int insertSelective(Resource record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_resource
     *
     * @mbggenerated Fri Nov 09 17:13:41 GMT+07:00 2012
     */
    List<Resource> selectByExample(ResourceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_resource
     *
     * @mbggenerated Fri Nov 09 17:13:41 GMT+07:00 2012
     */
    Resource selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_resource
     *
     * @mbggenerated Fri Nov 09 17:13:41 GMT+07:00 2012
     */
    int updateByExampleSelective(@Param("record") Resource record, @Param("example") ResourceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_resource
     *
     * @mbggenerated Fri Nov 09 17:13:41 GMT+07:00 2012
     */
    int updateByExample(@Param("record") Resource record, @Param("example") ResourceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_resource
     *
     * @mbggenerated Fri Nov 09 17:13:41 GMT+07:00 2012
     */
    int updateByPrimaryKeySelective(Resource record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_resource
     *
     * @mbggenerated Fri Nov 09 17:13:41 GMT+07:00 2012
     */
    int updateByPrimaryKey(Resource record);
}