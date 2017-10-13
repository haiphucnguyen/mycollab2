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
     * @mbg.generated Fri Oct 13 12:37:27 ICT 2017
     */
    long countByExample(InvoiceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_invoice
     *
     * @mbg.generated Fri Oct 13 12:37:27 ICT 2017
     */
    int deleteByExample(InvoiceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_invoice
     *
     * @mbg.generated Fri Oct 13 12:37:27 ICT 2017
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_invoice
     *
     * @mbg.generated Fri Oct 13 12:37:27 ICT 2017
     */
    int insert(Invoice record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_invoice
     *
     * @mbg.generated Fri Oct 13 12:37:27 ICT 2017
     */
    int insertSelective(Invoice record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_invoice
     *
     * @mbg.generated Fri Oct 13 12:37:27 ICT 2017
     */
    List<Invoice> selectByExampleWithBLOBs(InvoiceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_invoice
     *
     * @mbg.generated Fri Oct 13 12:37:27 ICT 2017
     */
    List<Invoice> selectByExample(InvoiceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_invoice
     *
     * @mbg.generated Fri Oct 13 12:37:27 ICT 2017
     */
    Invoice selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_invoice
     *
     * @mbg.generated Fri Oct 13 12:37:27 ICT 2017
     */
    int updateByExampleSelective(@Param("record") Invoice record, @Param("example") InvoiceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_invoice
     *
     * @mbg.generated Fri Oct 13 12:37:27 ICT 2017
     */
    int updateByExampleWithBLOBs(@Param("record") Invoice record, @Param("example") InvoiceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_invoice
     *
     * @mbg.generated Fri Oct 13 12:37:27 ICT 2017
     */
    int updateByExample(@Param("record") Invoice record, @Param("example") InvoiceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_invoice
     *
     * @mbg.generated Fri Oct 13 12:37:27 ICT 2017
     */
    int updateByPrimaryKeySelective(Invoice record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_invoice
     *
     * @mbg.generated Fri Oct 13 12:37:27 ICT 2017
     */
    int updateByPrimaryKeyWithBLOBs(Invoice record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_invoice
     *
     * @mbg.generated Fri Oct 13 12:37:27 ICT 2017
     */
    int updateByPrimaryKey(Invoice record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_invoice
     *
     * @mbg.generated Fri Oct 13 12:37:27 ICT 2017
     */
    Integer insertAndReturnKey(Invoice value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_invoice
     *
     * @mbg.generated Fri Oct 13 12:37:27 ICT 2017
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_invoice
     *
     * @mbg.generated Fri Oct 13 12:37:27 ICT 2017
     */
    void massUpdateWithSession(@Param("record") Invoice record, @Param("primaryKeys") List primaryKeys);
}