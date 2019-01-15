package com.mycollab.pro.common.dao;

import com.mycollab.common.domain.Tag;
import com.mycollab.common.domain.TagExample;
import com.mycollab.db.persistence.ICrudGenericDAO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@SuppressWarnings({ "ucd", "rawtypes" })
public interface TagMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_tag
     *
     * @mbg.generated Tue Jan 15 04:00:32 CST 2019
     */
    long countByExample(TagExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_tag
     *
     * @mbg.generated Tue Jan 15 04:00:32 CST 2019
     */
    int deleteByExample(TagExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_tag
     *
     * @mbg.generated Tue Jan 15 04:00:32 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_tag
     *
     * @mbg.generated Tue Jan 15 04:00:32 CST 2019
     */
    int insert(Tag record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_tag
     *
     * @mbg.generated Tue Jan 15 04:00:32 CST 2019
     */
    int insertSelective(Tag record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_tag
     *
     * @mbg.generated Tue Jan 15 04:00:32 CST 2019
     */
    List<Tag> selectByExample(TagExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_tag
     *
     * @mbg.generated Tue Jan 15 04:00:32 CST 2019
     */
    Tag selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_tag
     *
     * @mbg.generated Tue Jan 15 04:00:32 CST 2019
     */
    int updateByExampleSelective(@Param("record") Tag record, @Param("example") TagExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_tag
     *
     * @mbg.generated Tue Jan 15 04:00:32 CST 2019
     */
    int updateByExample(@Param("record") Tag record, @Param("example") TagExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_tag
     *
     * @mbg.generated Tue Jan 15 04:00:32 CST 2019
     */
    int updateByPrimaryKeySelective(Tag record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_tag
     *
     * @mbg.generated Tue Jan 15 04:00:32 CST 2019
     */
    int updateByPrimaryKey(Tag record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_tag
     *
     * @mbg.generated Tue Jan 15 04:00:32 CST 2019
     */
    Integer insertAndReturnKey(Tag value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_tag
     *
     * @mbg.generated Tue Jan 15 04:00:32 CST 2019
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_tag
     *
     * @mbg.generated Tue Jan 15 04:00:32 CST 2019
     */
    void massUpdateWithSession(@Param("record") Tag record, @Param("primaryKeys") List primaryKeys);
}