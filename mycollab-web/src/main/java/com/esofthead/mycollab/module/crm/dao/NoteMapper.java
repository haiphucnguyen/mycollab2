package com.esofthead.mycollab.module.crm.dao;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.module.crm.domain.Note;
import com.esofthead.mycollab.module.crm.domain.NoteExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NoteMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_note
     *
     * @mbggenerated Thu Nov 29 15:59:46 GMT+07:00 2012
     */
    int countByExample(NoteExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_note
     *
     * @mbggenerated Thu Nov 29 15:59:46 GMT+07:00 2012
     */
    int deleteByExample(NoteExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_note
     *
     * @mbggenerated Thu Nov 29 15:59:46 GMT+07:00 2012
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_note
     *
     * @mbggenerated Thu Nov 29 15:59:46 GMT+07:00 2012
     */
    int insert(Note record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_note
     *
     * @mbggenerated Thu Nov 29 15:59:46 GMT+07:00 2012
     */
    int insertSelective(Note record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_note
     *
     * @mbggenerated Thu Nov 29 15:59:46 GMT+07:00 2012
     */
    List<Note> selectByExample(NoteExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_note
     *
     * @mbggenerated Thu Nov 29 15:59:46 GMT+07:00 2012
     */
    Note selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_note
     *
     * @mbggenerated Thu Nov 29 15:59:46 GMT+07:00 2012
     */
    int updateByExampleSelective(@Param("record") Note record, @Param("example") NoteExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_note
     *
     * @mbggenerated Thu Nov 29 15:59:46 GMT+07:00 2012
     */
    int updateByExample(@Param("record") Note record, @Param("example") NoteExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_note
     *
     * @mbggenerated Thu Nov 29 15:59:46 GMT+07:00 2012
     */
    int updateByPrimaryKeySelective(Note record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_note
     *
     * @mbggenerated Thu Nov 29 15:59:46 GMT+07:00 2012
     */
    int updateByPrimaryKey(Note record);
}