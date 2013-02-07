package com.esofthead.mycollab.module.crm.dao;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.module.crm.domain.ProductCatalog;
import com.esofthead.mycollab.module.crm.domain.ProductCatalogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProductCatalogMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_product_catalog
     *
     * @mbggenerated Thu Feb 07 11:09:35 GMT+07:00 2013
     */
    int countByExample(ProductCatalogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_product_catalog
     *
     * @mbggenerated Thu Feb 07 11:09:35 GMT+07:00 2013
     */
    int deleteByExample(ProductCatalogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_product_catalog
     *
     * @mbggenerated Thu Feb 07 11:09:35 GMT+07:00 2013
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_product_catalog
     *
     * @mbggenerated Thu Feb 07 11:09:35 GMT+07:00 2013
     */
    int insert(ProductCatalog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_product_catalog
     *
     * @mbggenerated Thu Feb 07 11:09:35 GMT+07:00 2013
     */
    int insertSelective(ProductCatalog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_product_catalog
     *
     * @mbggenerated Thu Feb 07 11:09:35 GMT+07:00 2013
     */
    List<ProductCatalog> selectByExample(ProductCatalogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_product_catalog
     *
     * @mbggenerated Thu Feb 07 11:09:35 GMT+07:00 2013
     */
    ProductCatalog selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_product_catalog
     *
     * @mbggenerated Thu Feb 07 11:09:35 GMT+07:00 2013
     */
    int updateByExampleSelective(@Param("record") ProductCatalog record, @Param("example") ProductCatalogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_product_catalog
     *
     * @mbggenerated Thu Feb 07 11:09:35 GMT+07:00 2013
     */
    int updateByExample(@Param("record") ProductCatalog record, @Param("example") ProductCatalogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_product_catalog
     *
     * @mbggenerated Thu Feb 07 11:09:35 GMT+07:00 2013
     */
    int updateByPrimaryKeySelective(ProductCatalog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_product_catalog
     *
     * @mbggenerated Thu Feb 07 11:09:35 GMT+07:00 2013
     */
    int updateByPrimaryKey(ProductCatalog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_product_catalog
     *
     * @mbggenerated Thu Feb 07 11:09:35 GMT+07:00 2013
     */
    Integer insertAndReturnKey(ProductCatalog value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_product_catalog
     *
     * @mbggenerated Thu Feb 07 11:09:35 GMT+07:00 2013
     */
    void removeKeysWithSession(List primaryKeys);
}