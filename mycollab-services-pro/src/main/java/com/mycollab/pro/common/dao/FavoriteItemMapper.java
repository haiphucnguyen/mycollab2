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
     * @mbg.generated Thu May 31 21:45:18 ICT 2018
     */
    long countByExample(FavoriteItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_favorite
     *
     * @mbg.generated Thu May 31 21:45:18 ICT 2018
     */
    int deleteByExample(FavoriteItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_favorite
     *
     * @mbg.generated Thu May 31 21:45:18 ICT 2018
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_favorite
     *
     * @mbg.generated Thu May 31 21:45:18 ICT 2018
     */
    int insert(FavoriteItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_favorite
     *
     * @mbg.generated Thu May 31 21:45:18 ICT 2018
     */
    int insertSelective(FavoriteItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_favorite
     *
     * @mbg.generated Thu May 31 21:45:18 ICT 2018
     */
    List<FavoriteItem> selectByExample(FavoriteItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_favorite
     *
     * @mbg.generated Thu May 31 21:45:18 ICT 2018
     */
    FavoriteItem selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_favorite
     *
     * @mbg.generated Thu May 31 21:45:18 ICT 2018
     */
    int updateByExampleSelective(@Param("record") FavoriteItem record, @Param("example") FavoriteItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_favorite
     *
     * @mbg.generated Thu May 31 21:45:18 ICT 2018
     */
    int updateByExample(@Param("record") FavoriteItem record, @Param("example") FavoriteItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_favorite
     *
     * @mbg.generated Thu May 31 21:45:18 ICT 2018
     */
    int updateByPrimaryKeySelective(FavoriteItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_favorite
     *
     * @mbg.generated Thu May 31 21:45:18 ICT 2018
     */
    int updateByPrimaryKey(FavoriteItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_favorite
     *
     * @mbg.generated Thu May 31 21:45:18 ICT 2018
     */
    Integer insertAndReturnKey(FavoriteItem value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_favorite
     *
     * @mbg.generated Thu May 31 21:45:18 ICT 2018
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_favorite
     *
     * @mbg.generated Thu May 31 21:45:18 ICT 2018
     */
    void massUpdateWithSession(@Param("record") FavoriteItem record, @Param("primaryKeys") List primaryKeys);
}