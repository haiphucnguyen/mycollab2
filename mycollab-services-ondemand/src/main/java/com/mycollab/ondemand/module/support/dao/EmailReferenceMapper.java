package com.mycollab.ondemand.module.support.dao;

import com.mycollab.db.persistence.ICrudGenericDAO;
import com.mycollab.ondemand.module.support.domain.EmailReference;
import com.mycollab.ondemand.module.support.domain.EmailReferenceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@SuppressWarnings({ "ucd", "rawtypes" })
public interface EmailReferenceMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_email_preference
     *
     * @mbggenerated Mon Jul 18 20:40:09 ICT 2016
     */
    int countByExample(EmailReferenceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_email_preference
     *
     * @mbggenerated Mon Jul 18 20:40:09 ICT 2016
     */
    int deleteByExample(EmailReferenceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_email_preference
     *
     * @mbggenerated Mon Jul 18 20:40:09 ICT 2016
     */
    int deleteByPrimaryKey(String email);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_email_preference
     *
     * @mbggenerated Mon Jul 18 20:40:09 ICT 2016
     */
    int insert(EmailReference record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_email_preference
     *
     * @mbggenerated Mon Jul 18 20:40:09 ICT 2016
     */
    int insertSelective(EmailReference record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_email_preference
     *
     * @mbggenerated Mon Jul 18 20:40:09 ICT 2016
     */
    List<EmailReference> selectByExample(EmailReferenceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_email_preference
     *
     * @mbggenerated Mon Jul 18 20:40:09 ICT 2016
     */
    EmailReference selectByPrimaryKey(String email);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_email_preference
     *
     * @mbggenerated Mon Jul 18 20:40:09 ICT 2016
     */
    int updateByExampleSelective(@Param("record") EmailReference record, @Param("example") EmailReferenceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_email_preference
     *
     * @mbggenerated Mon Jul 18 20:40:09 ICT 2016
     */
    int updateByExample(@Param("record") EmailReference record, @Param("example") EmailReferenceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_email_preference
     *
     * @mbggenerated Mon Jul 18 20:40:09 ICT 2016
     */
    int updateByPrimaryKeySelective(EmailReference record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_email_preference
     *
     * @mbggenerated Mon Jul 18 20:40:09 ICT 2016
     */
    int updateByPrimaryKey(EmailReference record);
}