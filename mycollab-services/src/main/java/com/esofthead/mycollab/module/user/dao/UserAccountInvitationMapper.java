package com.esofthead.mycollab.module.user.dao;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.module.user.domain.UserAccountInvitation;
import com.esofthead.mycollab.module.user.domain.UserAccountInvitationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserAccountInvitationMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_account_invitation
     *
     * @mbggenerated Thu Sep 12 13:29:02 ICT 2013
     */
    int countByExample(UserAccountInvitationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_account_invitation
     *
     * @mbggenerated Thu Sep 12 13:29:02 ICT 2013
     */
    int deleteByExample(UserAccountInvitationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_account_invitation
     *
     * @mbggenerated Thu Sep 12 13:29:02 ICT 2013
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_account_invitation
     *
     * @mbggenerated Thu Sep 12 13:29:02 ICT 2013
     */
    int insert(UserAccountInvitation record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_account_invitation
     *
     * @mbggenerated Thu Sep 12 13:29:02 ICT 2013
     */
    int insertSelective(UserAccountInvitation record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_account_invitation
     *
     * @mbggenerated Thu Sep 12 13:29:02 ICT 2013
     */
    List<UserAccountInvitation> selectByExample(UserAccountInvitationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_account_invitation
     *
     * @mbggenerated Thu Sep 12 13:29:02 ICT 2013
     */
    UserAccountInvitation selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_account_invitation
     *
     * @mbggenerated Thu Sep 12 13:29:02 ICT 2013
     */
    int updateByExampleSelective(@Param("record") UserAccountInvitation record, @Param("example") UserAccountInvitationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_account_invitation
     *
     * @mbggenerated Thu Sep 12 13:29:02 ICT 2013
     */
    int updateByExample(@Param("record") UserAccountInvitation record, @Param("example") UserAccountInvitationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_account_invitation
     *
     * @mbggenerated Thu Sep 12 13:29:02 ICT 2013
     */
    int updateByPrimaryKeySelective(UserAccountInvitation record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_account_invitation
     *
     * @mbggenerated Thu Sep 12 13:29:02 ICT 2013
     */
    Integer insertAndReturnKey(UserAccountInvitation value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_account_invitation
     *
     * @mbggenerated Thu Sep 12 13:29:02 ICT 2013
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_account_invitation
     *
     * @mbggenerated Thu Sep 12 13:29:02 ICT 2013
     */
    void massUpdateWithSession(@Param("record") UserAccountInvitation record, @Param("primaryKeys") List primaryKeys);
}