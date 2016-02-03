package com.esofthead.mycollab.module.crm.dao;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.module.crm.domain.ProductCatalog;
import com.esofthead.mycollab.module.crm.domain.ProductCatalogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@SuppressWarnings({ "ucd", "rawtypes" })
public interface ProductCatalogMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_product_catalog
     *
     * @mbggenerated Wed Feb 03 14:56:39 ICT 2016
     */
    int countByExample(ProductCatalogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_product_catalog
     *
     * @mbggenerated Wed Feb 03 14:56:39 ICT 2016
     */
    int deleteByExample(ProductCatalogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_product_catalog
     *
     * @mbggenerated Wed Feb 03 14:56:39 ICT 2016
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_product_catalog
     *
     * @mbggenerated Wed Feb 03 14:56:39 ICT 2016
     */
    int insert(ProductCatalog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_product_catalog
     *
     * @mbggenerated Wed Feb 03 14:56:39 ICT 2016
     */
    int insertSelective(ProductCatalog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_product_catalog
     *
     * @mbggenerated Wed Feb 03 14:56:39 ICT 2016
     */
    List<ProductCatalog> selectByExample(ProductCatalogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_product_catalog
     *
     * @mbggenerated Wed Feb 03 14:56:39 ICT 2016
     */
    ProductCatalog selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_product_catalog
     *
     * @mbggenerated Wed Feb 03 14:56:39 ICT 2016
     */
    int updateByExampleSelective(@Param("record") ProductCatalog record, @Param("example") ProductCatalogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_product_catalog
     *
     * @mbggenerated Wed Feb 03 14:56:39 ICT 2016
     */
    int updateByExample(@Param("record") ProductCatalog record, @Param("example") ProductCatalogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_product_catalog
     *
     * @mbggenerated Wed Feb 03 14:56:39 ICT 2016
     */
    int updateByPrimaryKeySelective(ProductCatalog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_product_catalog
     *
     * @mbggenerated Wed Feb 03 14:56:39 ICT 2016
     */
    int updateByPrimaryKey(ProductCatalog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_product_catalog
     *
     * @mbggenerated Wed Feb 03 14:56:39 ICT 2016
     */
    Integer insertAndReturnKey(ProductCatalog value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_product_catalog
     *
     * @mbggenerated Wed Feb 03 14:56:39 ICT 2016
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_product_catalog
     *
     * @mbggenerated Wed Feb 03 14:56:39 ICT 2016
     */
    void massUpdateWithSession(@Param("record") ProductCatalog record, @Param("primaryKeys") List primaryKeys);
}