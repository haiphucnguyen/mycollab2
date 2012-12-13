package com.esofthead.mycollab.module.file.dao;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.module.file.domain.Attachment;
import com.esofthead.mycollab.module.file.domain.AttachmentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AttachmentMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_attachment
     *
     * @mbggenerated Thu Dec 13 17:53:09 GMT+07:00 2012
     */
    int countByExample(AttachmentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_attachment
     *
     * @mbggenerated Thu Dec 13 17:53:09 GMT+07:00 2012
     */
    int deleteByExample(AttachmentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_attachment
     *
     * @mbggenerated Thu Dec 13 17:53:09 GMT+07:00 2012
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_attachment
     *
     * @mbggenerated Thu Dec 13 17:53:09 GMT+07:00 2012
     */
    int insert(Attachment record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_attachment
     *
     * @mbggenerated Thu Dec 13 17:53:09 GMT+07:00 2012
     */
    int insertSelective(Attachment record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_attachment
     *
     * @mbggenerated Thu Dec 13 17:53:09 GMT+07:00 2012
     */
    List<Attachment> selectByExample(AttachmentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_attachment
     *
     * @mbggenerated Thu Dec 13 17:53:09 GMT+07:00 2012
     */
    Attachment selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_attachment
     *
     * @mbggenerated Thu Dec 13 17:53:09 GMT+07:00 2012
     */
    int updateByExampleSelective(@Param("record") Attachment record, @Param("example") AttachmentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_attachment
     *
     * @mbggenerated Thu Dec 13 17:53:09 GMT+07:00 2012
     */
    int updateByExample(@Param("record") Attachment record, @Param("example") AttachmentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_attachment
     *
     * @mbggenerated Thu Dec 13 17:53:09 GMT+07:00 2012
     */
    int updateByPrimaryKeySelective(Attachment record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_attachment
     *
     * @mbggenerated Thu Dec 13 17:53:09 GMT+07:00 2012
     */
    int updateByPrimaryKey(Attachment record);
}