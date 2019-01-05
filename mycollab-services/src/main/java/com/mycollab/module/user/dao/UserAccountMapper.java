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
     * @mbg.generated Fri Jan 04 18:13:54 CST 2019
     */
    long countByExample(UserAccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_account
     *
     * @mbg.generated Fri Jan 04 18:13:54 CST 2019
     */
    int deleteByExample(UserAccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_account
     *
     * @mbg.generated Fri Jan 04 18:13:54 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_account
     *
     * @mbg.generated Fri Jan 04 18:13:54 CST 2019
     */
    int insert(UserAccount record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_account
     *
     * @mbg.generated Fri Jan 04 18:13:54 CST 2019
     */
    int insertSelective(UserAccount record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_account
     *
     * @mbg.generated Fri Jan 04 18:13:54 CST 2019
     */
    List<UserAccount> selectByExample(UserAccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_account
     *
     * @mbg.generated Fri Jan 04 18:13:54 CST 2019
     */
    UserAccount selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_account
     *
     * @mbg.generated Fri Jan 04 18:13:54 CST 2019
     */
    int updateByExampleSelective(@Param("record") UserAccount record, @Param("example") UserAccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_account
     *
     * @mbg.generated Fri Jan 04 18:13:54 CST 2019
     */
    int updateByExample(@Param("record") UserAccount record, @Param("example") UserAccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_account
     *
     * @mbg.generated Fri Jan 04 18:13:54 CST 2019
     */
    int updateByPrimaryKeySelective(UserAccount record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_account
     *
     * @mbg.generated Fri Jan 04 18:13:54 CST 2019
     */
    int updateByPrimaryKey(UserAccount record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_account
     *
     * @mbg.generated Fri Jan 04 18:13:54 CST 2019
     */
    Integer insertAndReturnKey(UserAccount value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_account
     *
     * @mbg.generated Fri Jan 04 18:13:54 CST 2019
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_account
     *
     * @mbg.generated Fri Jan 04 18:13:54 CST 2019
     */
    void massUpdateWithSession(@Param("record") UserAccount record, @Param("primaryKeys") List primaryKeys);
}