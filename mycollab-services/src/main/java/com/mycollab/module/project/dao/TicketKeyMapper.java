package com.mycollab.module.project.dao;

import com.mycollab.db.persistence.ICrudGenericDAO;
import com.mycollab.module.project.domain.TicketKey;
import com.mycollab.module.project.domain.TicketKeyExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@SuppressWarnings({ "ucd", "rawtypes" })
@Mapper
public interface TicketKeyMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_ticket_key
     *
     * @mbg.generated Sat Apr 06 23:38:37 CDT 2019
     */
    long countByExample(TicketKeyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_ticket_key
     *
     * @mbg.generated Sat Apr 06 23:38:37 CDT 2019
     */
    int deleteByExample(TicketKeyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_ticket_key
     *
     * @mbg.generated Sat Apr 06 23:38:37 CDT 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_ticket_key
     *
     * @mbg.generated Sat Apr 06 23:38:37 CDT 2019
     */
    int insert(TicketKey record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_ticket_key
     *
     * @mbg.generated Sat Apr 06 23:38:37 CDT 2019
     */
    int insertSelective(TicketKey record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_ticket_key
     *
     * @mbg.generated Sat Apr 06 23:38:37 CDT 2019
     */
    List<TicketKey> selectByExample(TicketKeyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_ticket_key
     *
     * @mbg.generated Sat Apr 06 23:38:37 CDT 2019
     */
    TicketKey selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_ticket_key
     *
     * @mbg.generated Sat Apr 06 23:38:37 CDT 2019
     */
    int updateByExampleSelective(@Param("record") TicketKey record, @Param("example") TicketKeyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_ticket_key
     *
     * @mbg.generated Sat Apr 06 23:38:37 CDT 2019
     */
    int updateByExample(@Param("record") TicketKey record, @Param("example") TicketKeyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_ticket_key
     *
     * @mbg.generated Sat Apr 06 23:38:37 CDT 2019
     */
    int updateByPrimaryKeySelective(TicketKey record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_ticket_key
     *
     * @mbg.generated Sat Apr 06 23:38:37 CDT 2019
     */
    int updateByPrimaryKey(TicketKey record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_ticket_key
     *
     * @mbg.generated Sat Apr 06 23:38:37 CDT 2019
     */
    Integer insertAndReturnKey(TicketKey value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_ticket_key
     *
     * @mbg.generated Sat Apr 06 23:38:37 CDT 2019
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_ticket_key
     *
     * @mbg.generated Sat Apr 06 23:38:37 CDT 2019
     */
    void massUpdateWithSession(@Param("record") TicketKey record, @Param("primaryKeys") List primaryKeys);
}