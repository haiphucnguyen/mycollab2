package com.mycollab.module.tracker.dao;

import com.mycollab.db.persistence.ICrudGenericDAO;
import com.mycollab.module.tracker.domain.Component;
import com.mycollab.module.tracker.domain.ComponentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@SuppressWarnings({ "ucd", "rawtypes" })
public interface ComponentMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_component
     *
     * @mbg.generated Fri Oct 13 12:37:28 ICT 2017
     */
    long countByExample(ComponentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_component
     *
     * @mbg.generated Fri Oct 13 12:37:28 ICT 2017
     */
    int deleteByExample(ComponentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_component
     *
     * @mbg.generated Fri Oct 13 12:37:28 ICT 2017
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_component
     *
     * @mbg.generated Fri Oct 13 12:37:28 ICT 2017
     */
    int insert(Component record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_component
     *
     * @mbg.generated Fri Oct 13 12:37:28 ICT 2017
     */
    int insertSelective(Component record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_component
     *
     * @mbg.generated Fri Oct 13 12:37:28 ICT 2017
     */
    List<Component> selectByExampleWithBLOBs(ComponentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_component
     *
     * @mbg.generated Fri Oct 13 12:37:28 ICT 2017
     */
    List<Component> selectByExample(ComponentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_component
     *
     * @mbg.generated Fri Oct 13 12:37:28 ICT 2017
     */
    Component selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_component
     *
     * @mbg.generated Fri Oct 13 12:37:28 ICT 2017
     */
    int updateByExampleSelective(@Param("record") Component record, @Param("example") ComponentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_component
     *
     * @mbg.generated Fri Oct 13 12:37:28 ICT 2017
     */
    int updateByExampleWithBLOBs(@Param("record") Component record, @Param("example") ComponentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_component
     *
     * @mbg.generated Fri Oct 13 12:37:28 ICT 2017
     */
    int updateByExample(@Param("record") Component record, @Param("example") ComponentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_component
     *
     * @mbg.generated Fri Oct 13 12:37:28 ICT 2017
     */
    int updateByPrimaryKeySelective(Component record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_component
     *
     * @mbg.generated Fri Oct 13 12:37:28 ICT 2017
     */
    int updateByPrimaryKeyWithBLOBs(Component record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_component
     *
     * @mbg.generated Fri Oct 13 12:37:28 ICT 2017
     */
    int updateByPrimaryKey(Component record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_component
     *
     * @mbg.generated Fri Oct 13 12:37:28 ICT 2017
     */
    Integer insertAndReturnKey(Component value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_component
     *
     * @mbg.generated Fri Oct 13 12:37:28 ICT 2017
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_component
     *
     * @mbg.generated Fri Oct 13 12:37:28 ICT 2017
     */
    void massUpdateWithSession(@Param("record") Component record, @Param("primaryKeys") List primaryKeys);
}