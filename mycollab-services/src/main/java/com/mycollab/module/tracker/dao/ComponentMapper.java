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
     * @mbg.generated Sat Jan 19 02:08:00 CST 2019
     */
    long countByExample(ComponentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_component
     *
     * @mbg.generated Sat Jan 19 02:08:00 CST 2019
     */
    int deleteByExample(ComponentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_component
     *
     * @mbg.generated Sat Jan 19 02:08:00 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_component
     *
     * @mbg.generated Sat Jan 19 02:08:00 CST 2019
     */
    int insert(Component record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_component
     *
     * @mbg.generated Sat Jan 19 02:08:00 CST 2019
     */
    int insertSelective(Component record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_component
     *
     * @mbg.generated Sat Jan 19 02:08:00 CST 2019
     */
    List<Component> selectByExampleWithBLOBs(ComponentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_component
     *
     * @mbg.generated Sat Jan 19 02:08:00 CST 2019
     */
    List<Component> selectByExample(ComponentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_component
     *
     * @mbg.generated Sat Jan 19 02:08:00 CST 2019
     */
    Component selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_component
     *
     * @mbg.generated Sat Jan 19 02:08:00 CST 2019
     */
    int updateByExampleSelective(@Param("record") Component record, @Param("example") ComponentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_component
     *
     * @mbg.generated Sat Jan 19 02:08:00 CST 2019
     */
    int updateByExampleWithBLOBs(@Param("record") Component record, @Param("example") ComponentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_component
     *
     * @mbg.generated Sat Jan 19 02:08:00 CST 2019
     */
    int updateByExample(@Param("record") Component record, @Param("example") ComponentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_component
     *
     * @mbg.generated Sat Jan 19 02:08:00 CST 2019
     */
    int updateByPrimaryKeySelective(Component record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_component
     *
     * @mbg.generated Sat Jan 19 02:08:00 CST 2019
     */
    int updateByPrimaryKeyWithBLOBs(Component record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_component
     *
     * @mbg.generated Sat Jan 19 02:08:00 CST 2019
     */
    int updateByPrimaryKey(Component record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_component
     *
     * @mbg.generated Sat Jan 19 02:08:00 CST 2019
     */
    Integer insertAndReturnKey(Component value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_component
     *
     * @mbg.generated Sat Jan 19 02:08:00 CST 2019
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_component
     *
     * @mbg.generated Sat Jan 19 02:08:00 CST 2019
     */
    void massUpdateWithSession(@Param("record") Component record, @Param("primaryKeys") List primaryKeys);
}