package com.mycollab.module.user.dao;

import com.mycollab.db.persistence.ICrudGenericDAO;
import com.mycollab.module.user.domain.AccountTheme;
import com.mycollab.module.user.domain.AccountThemeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@SuppressWarnings({ "ucd", "rawtypes" })
public interface AccountThemeMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_account_theme
     *
     * @mbg.generated Thu Oct 19 13:58:06 ICT 2017
     */
    long countByExample(AccountThemeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_account_theme
     *
     * @mbg.generated Thu Oct 19 13:58:06 ICT 2017
     */
    int deleteByExample(AccountThemeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_account_theme
     *
     * @mbg.generated Thu Oct 19 13:58:06 ICT 2017
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_account_theme
     *
     * @mbg.generated Thu Oct 19 13:58:06 ICT 2017
     */
    int insert(AccountTheme record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_account_theme
     *
     * @mbg.generated Thu Oct 19 13:58:06 ICT 2017
     */
    int insertSelective(AccountTheme record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_account_theme
     *
     * @mbg.generated Thu Oct 19 13:58:06 ICT 2017
     */
    List<AccountTheme> selectByExample(AccountThemeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_account_theme
     *
     * @mbg.generated Thu Oct 19 13:58:06 ICT 2017
     */
    AccountTheme selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_account_theme
     *
     * @mbg.generated Thu Oct 19 13:58:06 ICT 2017
     */
    int updateByExampleSelective(@Param("record") AccountTheme record, @Param("example") AccountThemeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_account_theme
     *
     * @mbg.generated Thu Oct 19 13:58:06 ICT 2017
     */
    int updateByExample(@Param("record") AccountTheme record, @Param("example") AccountThemeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_account_theme
     *
     * @mbg.generated Thu Oct 19 13:58:06 ICT 2017
     */
    int updateByPrimaryKeySelective(AccountTheme record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_account_theme
     *
     * @mbg.generated Thu Oct 19 13:58:06 ICT 2017
     */
    int updateByPrimaryKey(AccountTheme record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_account_theme
     *
     * @mbg.generated Thu Oct 19 13:58:06 ICT 2017
     */
    Integer insertAndReturnKey(AccountTheme value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_account_theme
     *
     * @mbg.generated Thu Oct 19 13:58:06 ICT 2017
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_account_theme
     *
     * @mbg.generated Thu Oct 19 13:58:06 ICT 2017
     */
    void massUpdateWithSession(@Param("record") AccountTheme record, @Param("primaryKeys") List primaryKeys);
}