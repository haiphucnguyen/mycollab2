package com.mycollab.module.project.dao;

import com.mycollab.db.persistence.ICrudGenericDAO;
import com.mycollab.module.project.domain.Message;
import com.mycollab.module.project.domain.MessageExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@SuppressWarnings({ "ucd", "rawtypes" })
public interface MessageMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_message
     *
     * @mbg.generated Fri Sep 23 08:39:37 ICT 2016
     */
    long countByExample(MessageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_message
     *
     * @mbg.generated Fri Sep 23 08:39:37 ICT 2016
     */
    int deleteByExample(MessageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_message
     *
     * @mbg.generated Fri Sep 23 08:39:37 ICT 2016
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_message
     *
     * @mbg.generated Fri Sep 23 08:39:37 ICT 2016
     */
    int insert(Message record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_message
     *
     * @mbg.generated Fri Sep 23 08:39:37 ICT 2016
     */
    int insertSelective(Message record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_message
     *
     * @mbg.generated Fri Sep 23 08:39:37 ICT 2016
     */
    List<Message> selectByExampleWithBLOBs(MessageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_message
     *
     * @mbg.generated Fri Sep 23 08:39:37 ICT 2016
     */
    List<Message> selectByExample(MessageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_message
     *
     * @mbg.generated Fri Sep 23 08:39:37 ICT 2016
     */
    Message selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_message
     *
     * @mbg.generated Fri Sep 23 08:39:37 ICT 2016
     */
    int updateByExampleSelective(@Param("record") Message record, @Param("example") MessageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_message
     *
     * @mbg.generated Fri Sep 23 08:39:37 ICT 2016
     */
    int updateByExampleWithBLOBs(@Param("record") Message record, @Param("example") MessageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_message
     *
     * @mbg.generated Fri Sep 23 08:39:37 ICT 2016
     */
    int updateByExample(@Param("record") Message record, @Param("example") MessageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_message
     *
     * @mbg.generated Fri Sep 23 08:39:37 ICT 2016
     */
    int updateByPrimaryKeySelective(Message record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_message
     *
     * @mbg.generated Fri Sep 23 08:39:37 ICT 2016
     */
    int updateByPrimaryKeyWithBLOBs(Message record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_message
     *
     * @mbg.generated Fri Sep 23 08:39:37 ICT 2016
     */
    int updateByPrimaryKey(Message record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_message
     *
     * @mbg.generated Fri Sep 23 08:39:37 ICT 2016
     */
    Integer insertAndReturnKey(Message value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_message
     *
     * @mbg.generated Fri Sep 23 08:39:37 ICT 2016
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_message
     *
     * @mbg.generated Fri Sep 23 08:39:37 ICT 2016
     */
    void massUpdateWithSession(@Param("record") Message record, @Param("primaryKeys") List primaryKeys);
}