package com.mycollab.pro.common.dao;

import com.mycollab.common.domain.FavoriteItem;
import com.mycollab.common.domain.FavoriteItemExample;
import com.mycollab.db.persistence.ICrudGenericDAO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@SuppressWarnings({ "ucd", "rawtypes" })
public interface FavoriteItemMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_favorite
     *
     * @mbg.generated Wed Jan 16 19:42:02 CST 2019
     */
    long countByExample(FavoriteItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_favorite
     *
     * @mbg.generated Wed Jan 16 19:42:02 CST 2019
     */
    int deleteByExample(FavoriteItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_favorite
     *
     * @mbg.generated Wed Jan 16 19:42:02 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_favorite
     *
     * @mbg.generated Wed Jan 16 19:42:02 CST 2019
     */
    int insert(FavoriteItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_favorite
     *
     * @mbg.generated Wed Jan 16 19:42:02 CST 2019
     */
    int insertSelective(FavoriteItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_favorite
     *
     * @mbg.generated Wed Jan 16 19:42:02 CST 2019
     */
    List<FavoriteItem> selectByExample(FavoriteItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_favorite
     *
     * @mbg.generated Wed Jan 16 19:42:02 CST 2019
     */
    FavoriteItem selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_favorite
     *
     * @mbg.generated Wed Jan 16 19:42:02 CST 2019
     */
    int updateByExampleSelective(@Param("record") FavoriteItem record, @Param("example") FavoriteItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_favorite
     *
     * @mbg.generated Wed Jan 16 19:42:02 CST 2019
     */
    int updateByExample(@Param("record") FavoriteItem record, @Param("example") FavoriteItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_favorite
     *
     * @mbg.generated Wed Jan 16 19:42:02 CST 2019
     */
    int updateByPrimaryKeySelective(FavoriteItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_favorite
     *
     * @mbg.generated Wed Jan 16 19:42:02 CST 2019
     */
    int updateByPrimaryKey(FavoriteItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_favorite
     *
     * @mbg.generated Wed Jan 16 19:42:02 CST 2019
     */
    Integer insertAndReturnKey(FavoriteItem value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_favorite
     *
     * @mbg.generated Wed Jan 16 19:42:02 CST 2019
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_favorite
     *
     * @mbg.generated Wed Jan 16 19:42:02 CST 2019
     */
    void massUpdateWithSession(@Param("record") FavoriteItem record, @Param("primaryKeys") List primaryKeys);
}