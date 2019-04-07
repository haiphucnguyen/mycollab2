package com.mycollab.module.project.dao;

import com.mycollab.db.persistence.ICrudGenericDAO;
import com.mycollab.module.project.domain.KanbanBoard;
import com.mycollab.module.project.domain.KanbanBoardExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@SuppressWarnings({ "ucd", "rawtypes" })
@Mapper
public interface KanbanBoardMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_kanban_board
     *
     * @mbg.generated Sun Apr 07 09:54:39 CDT 2019
     */
    long countByExample(KanbanBoardExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_kanban_board
     *
     * @mbg.generated Sun Apr 07 09:54:39 CDT 2019
     */
    int deleteByExample(KanbanBoardExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_kanban_board
     *
     * @mbg.generated Sun Apr 07 09:54:39 CDT 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_kanban_board
     *
     * @mbg.generated Sun Apr 07 09:54:39 CDT 2019
     */
    int insert(KanbanBoard record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_kanban_board
     *
     * @mbg.generated Sun Apr 07 09:54:39 CDT 2019
     */
    int insertSelective(KanbanBoard record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_kanban_board
     *
     * @mbg.generated Sun Apr 07 09:54:39 CDT 2019
     */
    List<KanbanBoard> selectByExample(KanbanBoardExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_kanban_board
     *
     * @mbg.generated Sun Apr 07 09:54:39 CDT 2019
     */
    KanbanBoard selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_kanban_board
     *
     * @mbg.generated Sun Apr 07 09:54:39 CDT 2019
     */
    int updateByExampleSelective(@Param("record") KanbanBoard record, @Param("example") KanbanBoardExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_kanban_board
     *
     * @mbg.generated Sun Apr 07 09:54:39 CDT 2019
     */
    int updateByExample(@Param("record") KanbanBoard record, @Param("example") KanbanBoardExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_kanban_board
     *
     * @mbg.generated Sun Apr 07 09:54:39 CDT 2019
     */
    int updateByPrimaryKeySelective(KanbanBoard record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_kanban_board
     *
     * @mbg.generated Sun Apr 07 09:54:39 CDT 2019
     */
    int updateByPrimaryKey(KanbanBoard record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_kanban_board
     *
     * @mbg.generated Sun Apr 07 09:54:39 CDT 2019
     */
    Integer insertAndReturnKey(KanbanBoard value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_kanban_board
     *
     * @mbg.generated Sun Apr 07 09:54:39 CDT 2019
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_kanban_board
     *
     * @mbg.generated Sun Apr 07 09:54:39 CDT 2019
     */
    void massUpdateWithSession(@Param("record") KanbanBoard record, @Param("primaryKeys") List primaryKeys);
}