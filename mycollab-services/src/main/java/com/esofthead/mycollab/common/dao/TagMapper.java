package com.esofthead.mycollab.common.dao;

import com.esofthead.mycollab.common.domain.Tag;
import com.esofthead.mycollab.common.domain.TagExample;
import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@SuppressWarnings({ "ucd", "rawtypes" })
public interface TagMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_tag
     *
     * @mbggenerated Sun May 15 02:13:10 ICT 2016
     */
    int countByExample(TagExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_tag
     *
     * @mbggenerated Sun May 15 02:13:10 ICT 2016
     */
    int deleteByExample(TagExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_tag
     *
     * @mbggenerated Sun May 15 02:13:10 ICT 2016
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_tag
     *
     * @mbggenerated Sun May 15 02:13:10 ICT 2016
     */
    int insert(Tag record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_tag
     *
     * @mbggenerated Sun May 15 02:13:10 ICT 2016
     */
    int insertSelective(Tag record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_tag
     *
     * @mbggenerated Sun May 15 02:13:10 ICT 2016
     */
    List<Tag> selectByExample(TagExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_tag
     *
     * @mbggenerated Sun May 15 02:13:10 ICT 2016
     */
    Tag selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_tag
     *
     * @mbggenerated Sun May 15 02:13:10 ICT 2016
     */
    int updateByExampleSelective(@Param("record") Tag record, @Param("example") TagExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_tag
     *
     * @mbggenerated Sun May 15 02:13:10 ICT 2016
     */
    int updateByExample(@Param("record") Tag record, @Param("example") TagExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_tag
     *
     * @mbggenerated Sun May 15 02:13:10 ICT 2016
     */
    int updateByPrimaryKeySelective(Tag record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_tag
     *
     * @mbggenerated Sun May 15 02:13:10 ICT 2016
     */
    int updateByPrimaryKey(Tag record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_tag
     *
     * @mbggenerated Sun May 15 02:13:10 ICT 2016
     */
    Integer insertAndReturnKey(Tag value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_tag
     *
     * @mbggenerated Sun May 15 02:13:10 ICT 2016
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_tag
     *
     * @mbggenerated Sun May 15 02:13:10 ICT 2016
     */
    void massUpdateWithSession(@Param("record") Tag record, @Param("primaryKeys") List primaryKeys);
}