package com.mycollab.pro.module.project.dao;

import com.mycollab.db.persistence.ICrudGenericDAO;
import com.mycollab.module.project.domain.Invoice;
import com.mycollab.module.project.domain.InvoiceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@SuppressWarnings({ "ucd", "rawtypes" })
public interface InvoiceMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_invoice
     *
     * @mbg.generated Fri Jan 04 18:13:55 CST 2019
     */
    long countByExample(InvoiceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_invoice
     *
     * @mbg.generated Fri Jan 04 18:13:55 CST 2019
     */
    int deleteByExample(InvoiceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_invoice
     *
     * @mbg.generated Fri Jan 04 18:13:55 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_invoice
     *
     * @mbg.generated Fri Jan 04 18:13:55 CST 2019
     */
    int insert(Invoice record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_invoice
     *
     * @mbg.generated Fri Jan 04 18:13:55 CST 2019
     */
    int insertSelective(Invoice record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_invoice
     *
     * @mbg.generated Fri Jan 04 18:13:55 CST 2019
     */
    List<Invoice> selectByExampleWithBLOBs(InvoiceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_invoice
     *
     * @mbg.generated Fri Jan 04 18:13:55 CST 2019
     */
    List<Invoice> selectByExample(InvoiceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_invoice
     *
     * @mbg.generated Fri Jan 04 18:13:55 CST 2019
     */
    Invoice selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_invoice
     *
     * @mbg.generated Fri Jan 04 18:13:55 CST 2019
     */
    int updateByExampleSelective(@Param("record") Invoice record, @Param("example") InvoiceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_invoice
     *
     * @mbg.generated Fri Jan 04 18:13:55 CST 2019
     */
    int updateByExampleWithBLOBs(@Param("record") Invoice record, @Param("example") InvoiceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_invoice
     *
     * @mbg.generated Fri Jan 04 18:13:55 CST 2019
     */
    int updateByExample(@Param("record") Invoice record, @Param("example") InvoiceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_invoice
     *
     * @mbg.generated Fri Jan 04 18:13:55 CST 2019
     */
    int updateByPrimaryKeySelective(Invoice record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_invoice
     *
     * @mbg.generated Fri Jan 04 18:13:55 CST 2019
     */
    int updateByPrimaryKeyWithBLOBs(Invoice record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_invoice
     *
     * @mbg.generated Fri Jan 04 18:13:55 CST 2019
     */
    int updateByPrimaryKey(Invoice record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_invoice
     *
     * @mbg.generated Fri Jan 04 18:13:55 CST 2019
     */
    Integer insertAndReturnKey(Invoice value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_invoice
     *
     * @mbg.generated Fri Jan 04 18:13:55 CST 2019
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_invoice
     *
     * @mbg.generated Fri Jan 04 18:13:55 CST 2019
     */
    void massUpdateWithSession(@Param("record") Invoice record, @Param("primaryKeys") List primaryKeys);
}