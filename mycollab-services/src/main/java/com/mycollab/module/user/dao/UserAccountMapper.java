package com.mycollab.module.user.dao;

import com.mycollab.db.persistence.ICrudGenericDAO;
import com.mycollab.module.user.domain.UserAccount;
import com.mycollab.module.user.domain.UserAccountExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@SuppressWarnings({ "ucd", "rawtypes" })
public interface UserAccountMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_account
     *
     * @mbg.generated Thu May 31 21:45:18 ICT 2018
     */
    long countByExample(UserAccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_account
     *
     * @mbg.generated Thu May 31 21:45:18 ICT 2018
     */
    int deleteByExample(UserAccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_account
     *
     * @mbg.generated Thu May 31 21:45:18 ICT 2018
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_account
     *
     * @mbg.generated Thu May 31 21:45:18 ICT 2018
     */
    int insert(UserAccount record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_account
     *
     * @mbg.generated Thu May 31 21:45:18 ICT 2018
     */
    int insertSelective(UserAccount record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_account
     *
     * @mbg.generated Thu May 31 21:45:18 ICT 2018
     */
    List<UserAccount> selectByExample(UserAccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_account
     *
     * @mbg.generated Thu May 31 21:45:18 ICT 2018
     */
    UserAccount selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_account
     *
     * @mbg.generated Thu May 31 21:45:18 ICT 2018
     */
    int updateByExampleSelective(@Param("record") UserAccount record, @Param("example") UserAccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_account
     *
     * @mbg.generated Thu May 31 21:45:18 ICT 2018
     */
    int updateByExample(@Param("record") UserAccount record, @Param("example") UserAccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_account
     *
     * @mbg.generated Thu May 31 21:45:18 ICT 2018
     */
    int updateByPrimaryKeySelective(UserAccount record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_account
     *
     * @mbg.generated Thu May 31 21:45:18 ICT 2018
     */
    int updateByPrimaryKey(UserAccount record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_account
     *
     * @mbg.generated Thu May 31 21:45:18 ICT 2018
     */
    Integer insertAndReturnKey(UserAccount value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_account
     *
     * @mbg.generated Thu May 31 21:45:18 ICT 2018
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_account
     *
     * @mbg.generated Thu May 31 21:45:18 ICT 2018
     */
    void massUpdateWithSession(@Param("record") UserAccount record, @Param("primaryKeys") List primaryKeys);
}