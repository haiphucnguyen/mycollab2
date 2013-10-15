package com.esofthead.mycollab.module.user.dao;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.module.user.domain.AccountSettings;
import com.esofthead.mycollab.module.user.domain.AccountSettingsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AccountSettingsMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_account_settings
     *
     * @mbggenerated Tue Oct 15 11:01:51 ICT 2013
     */
    int countByExample(AccountSettingsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_account_settings
     *
     * @mbggenerated Tue Oct 15 11:01:51 ICT 2013
     */
    int deleteByExample(AccountSettingsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_account_settings
     *
     * @mbggenerated Tue Oct 15 11:01:51 ICT 2013
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_account_settings
     *
     * @mbggenerated Tue Oct 15 11:01:51 ICT 2013
     */
    int insert(AccountSettings record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_account_settings
     *
     * @mbggenerated Tue Oct 15 11:01:51 ICT 2013
     */
    int insertSelective(AccountSettings record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_account_settings
     *
     * @mbggenerated Tue Oct 15 11:01:51 ICT 2013
     */
    List<AccountSettings> selectByExample(AccountSettingsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_account_settings
     *
     * @mbggenerated Tue Oct 15 11:01:51 ICT 2013
     */
    AccountSettings selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_account_settings
     *
     * @mbggenerated Tue Oct 15 11:01:51 ICT 2013
     */
    int updateByExampleSelective(@Param("record") AccountSettings record, @Param("example") AccountSettingsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_account_settings
     *
     * @mbggenerated Tue Oct 15 11:01:51 ICT 2013
     */
    int updateByExample(@Param("record") AccountSettings record, @Param("example") AccountSettingsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_account_settings
     *
     * @mbggenerated Tue Oct 15 11:01:51 ICT 2013
     */
    int updateByPrimaryKeySelective(AccountSettings record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_account_settings
     *
     * @mbggenerated Tue Oct 15 11:01:51 ICT 2013
     */
    Integer insertAndReturnKey(AccountSettings value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_account_settings
     *
     * @mbggenerated Tue Oct 15 11:01:51 ICT 2013
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_account_settings
     *
     * @mbggenerated Tue Oct 15 11:01:51 ICT 2013
     */
    void massUpdateWithSession(@Param("record") AccountSettings record, @Param("primaryKeys") List primaryKeys);
}