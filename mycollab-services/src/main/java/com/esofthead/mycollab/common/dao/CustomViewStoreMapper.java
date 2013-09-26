package com.esofthead.mycollab.common.dao;

import com.esofthead.mycollab.common.domain.CustomViewStore;
import com.esofthead.mycollab.common.domain.CustomViewStoreExample;
import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CustomViewStoreMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_table_customize_view
     *
     * @mbggenerated Thu Sep 26 09:54:43 ICT 2013
     */
    int countByExample(CustomViewStoreExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_table_customize_view
     *
     * @mbggenerated Thu Sep 26 09:54:43 ICT 2013
     */
    int deleteByExample(CustomViewStoreExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_table_customize_view
     *
     * @mbggenerated Thu Sep 26 09:54:43 ICT 2013
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_table_customize_view
     *
     * @mbggenerated Thu Sep 26 09:54:43 ICT 2013
     */
    int insert(CustomViewStore record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_table_customize_view
     *
     * @mbggenerated Thu Sep 26 09:54:43 ICT 2013
     */
    int insertSelective(CustomViewStore record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_table_customize_view
     *
     * @mbggenerated Thu Sep 26 09:54:43 ICT 2013
     */
    List<CustomViewStore> selectByExampleWithBLOBs(CustomViewStoreExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_table_customize_view
     *
     * @mbggenerated Thu Sep 26 09:54:43 ICT 2013
     */
    List<CustomViewStore> selectByExample(CustomViewStoreExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_table_customize_view
     *
     * @mbggenerated Thu Sep 26 09:54:43 ICT 2013
     */
    CustomViewStore selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_table_customize_view
     *
     * @mbggenerated Thu Sep 26 09:54:43 ICT 2013
     */
    int updateByExampleSelective(@Param("record") CustomViewStore record, @Param("example") CustomViewStoreExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_table_customize_view
     *
     * @mbggenerated Thu Sep 26 09:54:43 ICT 2013
     */
    int updateByExampleWithBLOBs(@Param("record") CustomViewStore record, @Param("example") CustomViewStoreExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_table_customize_view
     *
     * @mbggenerated Thu Sep 26 09:54:43 ICT 2013
     */
    int updateByExample(@Param("record") CustomViewStore record, @Param("example") CustomViewStoreExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_table_customize_view
     *
     * @mbggenerated Thu Sep 26 09:54:43 ICT 2013
     */
    int updateByPrimaryKeySelective(CustomViewStore record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_table_customize_view
     *
     * @mbggenerated Thu Sep 26 09:54:43 ICT 2013
     */
    Integer insertAndReturnKey(CustomViewStore value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_table_customize_view
     *
     * @mbggenerated Thu Sep 26 09:54:43 ICT 2013
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_table_customize_view
     *
     * @mbggenerated Thu Sep 26 09:54:43 ICT 2013
     */
    void massUpdateWithSession(@Param("record") CustomViewStore record, @Param("primaryKeys") List primaryKeys);
}