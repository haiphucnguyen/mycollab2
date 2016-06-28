/**
 * This file is part of mycollab-services.
 *
 * mycollab-services is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-services is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-services.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.module.project.dao;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.module.project.domain.Invoice;
import com.esofthead.mycollab.module.project.domain.InvoiceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@SuppressWarnings({ "ucd", "rawtypes" })
public interface InvoiceMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_invoice
     *
     * @mbggenerated Tue Jun 28 09:46:20 ICT 2016
     */
    int countByExample(InvoiceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_invoice
     *
     * @mbggenerated Tue Jun 28 09:46:20 ICT 2016
     */
    int deleteByExample(InvoiceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_invoice
     *
     * @mbggenerated Tue Jun 28 09:46:20 ICT 2016
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_invoice
     *
     * @mbggenerated Tue Jun 28 09:46:20 ICT 2016
     */
    int insert(Invoice record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_invoice
     *
     * @mbggenerated Tue Jun 28 09:46:20 ICT 2016
     */
    int insertSelective(Invoice record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_invoice
     *
     * @mbggenerated Tue Jun 28 09:46:20 ICT 2016
     */
    List<Invoice> selectByExampleWithBLOBs(InvoiceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_invoice
     *
     * @mbggenerated Tue Jun 28 09:46:20 ICT 2016
     */
    List<Invoice> selectByExample(InvoiceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_invoice
     *
     * @mbggenerated Tue Jun 28 09:46:20 ICT 2016
     */
    Invoice selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_invoice
     *
     * @mbggenerated Tue Jun 28 09:46:20 ICT 2016
     */
    int updateByExampleSelective(@Param("record") Invoice record, @Param("example") InvoiceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_invoice
     *
     * @mbggenerated Tue Jun 28 09:46:20 ICT 2016
     */
    int updateByExampleWithBLOBs(@Param("record") Invoice record, @Param("example") InvoiceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_invoice
     *
     * @mbggenerated Tue Jun 28 09:46:20 ICT 2016
     */
    int updateByExample(@Param("record") Invoice record, @Param("example") InvoiceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_invoice
     *
     * @mbggenerated Tue Jun 28 09:46:20 ICT 2016
     */
    int updateByPrimaryKeySelective(Invoice record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_invoice
     *
     * @mbggenerated Tue Jun 28 09:46:20 ICT 2016
     */
    int updateByPrimaryKeyWithBLOBs(Invoice record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_invoice
     *
     * @mbggenerated Tue Jun 28 09:46:20 ICT 2016
     */
    int updateByPrimaryKey(Invoice record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_invoice
     *
     * @mbggenerated Tue Jun 28 09:46:20 ICT 2016
     */
    Integer insertAndReturnKey(Invoice value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_invoice
     *
     * @mbggenerated Tue Jun 28 09:46:20 ICT 2016
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_invoice
     *
     * @mbggenerated Tue Jun 28 09:46:20 ICT 2016
     */
    void massUpdateWithSession(@Param("record") Invoice record, @Param("primaryKeys") List primaryKeys);
}