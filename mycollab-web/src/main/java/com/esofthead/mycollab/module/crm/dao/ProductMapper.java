package com.esofthead.mycollab.module.crm.dao;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.module.crm.domain.Product;
import com.esofthead.mycollab.module.crm.domain.ProductExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProductMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_product
     *
     * @mbggenerated Tue May 21 16:04:01 GMT+07:00 2013
     */
    int countByExample(ProductExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_product
     *
     * @mbggenerated Tue May 21 16:04:01 GMT+07:00 2013
     */
    int deleteByExample(ProductExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_product
     *
     * @mbggenerated Tue May 21 16:04:01 GMT+07:00 2013
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_product
     *
     * @mbggenerated Tue May 21 16:04:01 GMT+07:00 2013
     */
    int insert(Product record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_product
     *
     * @mbggenerated Tue May 21 16:04:01 GMT+07:00 2013
     */
    int insertSelective(Product record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_product
     *
     * @mbggenerated Tue May 21 16:04:01 GMT+07:00 2013
     */
    List<Product> selectByExample(ProductExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_product
     *
     * @mbggenerated Tue May 21 16:04:01 GMT+07:00 2013
     */
    Product selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_product
     *
     * @mbggenerated Tue May 21 16:04:01 GMT+07:00 2013
     */
    int updateByExampleSelective(@Param("record") Product record, @Param("example") ProductExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_product
     *
     * @mbggenerated Tue May 21 16:04:01 GMT+07:00 2013
     */
    int updateByExample(@Param("record") Product record, @Param("example") ProductExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_product
     *
     * @mbggenerated Tue May 21 16:04:01 GMT+07:00 2013
     */
    int updateByPrimaryKeySelective(Product record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_product
     *
     * @mbggenerated Tue May 21 16:04:01 GMT+07:00 2013
     */
    Integer insertAndReturnKey(Product value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_product
     *
     * @mbggenerated Tue May 21 16:04:01 GMT+07:00 2013
     */
    void removeKeysWithSession(List primaryKeys);
}