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
     * @mbg.generated Fri Dec 01 20:52:18 ICT 2017
     */
    long countByExample(FavoriteItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_favorite
     *
     * @mbg.generated Fri Dec 01 20:52:18 ICT 2017
     */
    int deleteByExample(FavoriteItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_favorite
     *
     * @mbg.generated Fri Dec 01 20:52:18 ICT 2017
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_favorite
     *
     * @mbg.generated Fri Dec 01 20:52:18 ICT 2017
     */
    int insert(FavoriteItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_favorite
     *
     * @mbg.generated Fri Dec 01 20:52:18 ICT 2017
     */
    int insertSelective(FavoriteItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_favorite
     *
     * @mbg.generated Fri Dec 01 20:52:18 ICT 2017
     */
    List<FavoriteItem> selectByExample(FavoriteItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_favorite
     *
     * @mbg.generated Fri Dec 01 20:52:18 ICT 2017
     */
    FavoriteItem selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_favorite
     *
     * @mbg.generated Fri Dec 01 20:52:18 ICT 2017
     */
    int updateByExampleSelective(@Param("record") FavoriteItem record, @Param("example") FavoriteItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_favorite
     *
     * @mbg.generated Fri Dec 01 20:52:18 ICT 2017
     */
    int updateByExample(@Param("record") FavoriteItem record, @Param("example") FavoriteItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_favorite
     *
     * @mbg.generated Fri Dec 01 20:52:18 ICT 2017
     */
    int updateByPrimaryKeySelective(FavoriteItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_favorite
     *
     * @mbg.generated Fri Dec 01 20:52:18 ICT 2017
     */
    int updateByPrimaryKey(FavoriteItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_favorite
     *
     * @mbg.generated Fri Dec 01 20:52:18 ICT 2017
     */
    Integer insertAndReturnKey(FavoriteItem value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_favorite
     *
     * @mbg.generated Fri Dec 01 20:52:18 ICT 2017
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_favorite
     *
     * @mbg.generated Fri Dec 01 20:52:18 ICT 2017
     */
    void massUpdateWithSession(@Param("record") FavoriteItem record, @Param("primaryKeys") List primaryKeys);
}