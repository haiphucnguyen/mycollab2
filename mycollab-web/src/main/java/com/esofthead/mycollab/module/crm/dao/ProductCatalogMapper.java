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
     * @mbggenerated Fri Nov 16 15:23:00 GMT+07:00 2012
     */
    int countByExample(ProductCatalogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_product_catalog
     *
     * @mbggenerated Fri Nov 16 15:23:00 GMT+07:00 2012
     */
    int deleteByExample(ProductCatalogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_product_catalog
     *
     * @mbggenerated Fri Nov 16 15:23:00 GMT+07:00 2012
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_product_catalog
     *
     * @mbggenerated Fri Nov 16 15:23:00 GMT+07:00 2012
     */
    int insert(ProductCatalog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_product_catalog
     *
     * @mbggenerated Fri Nov 16 15:23:00 GMT+07:00 2012
     */
    int insertSelective(ProductCatalog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_product_catalog
     *
     * @mbggenerated Fri Nov 16 15:23:00 GMT+07:00 2012
     */
    List<ProductCatalog> selectByExample(ProductCatalogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_product_catalog
     *
     * @mbggenerated Fri Nov 16 15:23:00 GMT+07:00 2012
     */
    ProductCatalog selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_product_catalog
     *
     * @mbggenerated Fri Nov 16 15:23:00 GMT+07:00 2012
     */
    int updateByExampleSelective(@Param("record") ProductCatalog record, @Param("example") ProductCatalogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_product_catalog
     *
     * @mbggenerated Fri Nov 16 15:23:00 GMT+07:00 2012
     */
    int updateByExample(@Param("record") ProductCatalog record, @Param("example") ProductCatalogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_product_catalog
     *
     * @mbggenerated Fri Nov 16 15:23:00 GMT+07:00 2012
     */
    int updateByPrimaryKeySelective(ProductCatalog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_product_catalog
     *
     * @mbggenerated Fri Nov 16 15:23:00 GMT+07:00 2012
     */
    int updateByPrimaryKey(ProductCatalog record);
}