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
     * @mbg.generated Fri Sep 23 03:45:54 ICT 2016
     */
    long countByExample(FavoriteItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_favorite
     *
     * @mbg.generated Fri Sep 23 03:45:54 ICT 2016
     */
    int deleteByExample(FavoriteItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_favorite
     *
     * @mbg.generated Fri Sep 23 03:45:54 ICT 2016
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_favorite
     *
     * @mbg.generated Fri Sep 23 03:45:54 ICT 2016
     */
    int insert(FavoriteItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_favorite
     *
     * @mbg.generated Fri Sep 23 03:45:54 ICT 2016
     */
    int insertSelective(FavoriteItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_favorite
     *
     * @mbg.generated Fri Sep 23 03:45:54 ICT 2016
     */
    List<FavoriteItem> selectByExample(FavoriteItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_favorite
     *
     * @mbg.generated Fri Sep 23 03:45:54 ICT 2016
     */
    FavoriteItem selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_favorite
     *
     * @mbg.generated Fri Sep 23 03:45:54 ICT 2016
     */
    int updateByExampleSelective(@Param("record") FavoriteItem record, @Param("example") FavoriteItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_favorite
     *
     * @mbg.generated Fri Sep 23 03:45:54 ICT 2016
     */
    int updateByExample(@Param("record") FavoriteItem record, @Param("example") FavoriteItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_favorite
     *
     * @mbg.generated Fri Sep 23 03:45:54 ICT 2016
     */
    int updateByPrimaryKeySelective(FavoriteItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_favorite
     *
     * @mbg.generated Fri Sep 23 03:45:54 ICT 2016
     */
    int updateByPrimaryKey(FavoriteItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_favorite
     *
     * @mbg.generated Fri Sep 23 03:45:54 ICT 2016
     */
    Integer insertAndReturnKey(FavoriteItem value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_favorite
     *
     * @mbg.generated Fri Sep 23 03:45:54 ICT 2016
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_favorite
     *
     * @mbg.generated Fri Sep 23 03:45:54 ICT 2016
     */
    void massUpdateWithSession(@Param("record") FavoriteItem record, @Param("primaryKeys") List primaryKeys);
}