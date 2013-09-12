package com.esofthead.mycollab.module.user.dao;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.module.user.domain.UserAccount;
import com.esofthead.mycollab.module.user.domain.UserAccountExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserAccountMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_account
     *
     * @mbggenerated Thu Sep 12 13:29:02 ICT 2013
     */
    int countByExample(UserAccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_account
     *
     * @mbggenerated Thu Sep 12 13:29:02 ICT 2013
     */
    int deleteByExample(UserAccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_account
     *
     * @mbggenerated Thu Sep 12 13:29:02 ICT 2013
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_account
     *
     * @mbggenerated Thu Sep 12 13:29:02 ICT 2013
     */
    int insert(UserAccount record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_account
     *
     * @mbggenerated Thu Sep 12 13:29:02 ICT 2013
     */
    int insertSelective(UserAccount record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_account
     *
     * @mbggenerated Thu Sep 12 13:29:02 ICT 2013
     */
    List<UserAccount> selectByExample(UserAccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_account
     *
     * @mbggenerated Thu Sep 12 13:29:02 ICT 2013
     */
    UserAccount selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_account
     *
     * @mbggenerated Thu Sep 12 13:29:02 ICT 2013
     */
    int updateByExampleSelective(@Param("record") UserAccount record, @Param("example") UserAccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_account
     *
     * @mbggenerated Thu Sep 12 13:29:02 ICT 2013
     */
    int updateByExample(@Param("record") UserAccount record, @Param("example") UserAccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_account
     *
     * @mbggenerated Thu Sep 12 13:29:02 ICT 2013
     */
    int updateByPrimaryKeySelective(UserAccount record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_account
     *
     * @mbggenerated Thu Sep 12 13:29:02 ICT 2013
     */
    Integer insertAndReturnKey(UserAccount value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_account
     *
     * @mbggenerated Thu Sep 12 13:29:02 ICT 2013
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_account
     *
     * @mbggenerated Thu Sep 12 13:29:02 ICT 2013
     */
    void massUpdateWithSession(@Param("record") UserAccount record, @Param("primaryKeys") List primaryKeys);
}