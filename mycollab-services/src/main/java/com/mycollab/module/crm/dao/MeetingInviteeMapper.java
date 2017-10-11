/**
 * mycollab-services - Parent pom providing dependency and plugin management for applications
		built with Maven
 * Copyright © 2017 MyCollab (support@mycollab.com)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
/**
 * mycollab-services - Parent pom providing dependency and plugin management for applications
		built with Maven
 * Copyright © 2017 MyCollab (support@mycollab.com)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.mycollab.module.crm.dao;

import com.mycollab.db.persistence.ICrudGenericDAO;
import com.mycollab.module.crm.domain.MeetingInvitee;
import com.mycollab.module.crm.domain.MeetingInviteeExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@SuppressWarnings({ "ucd", "rawtypes" })
public interface MeetingInviteeMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_meeting_invitees
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    long countByExample(MeetingInviteeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_meeting_invitees
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    int deleteByExample(MeetingInviteeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_meeting_invitees
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    @Delete({
        "delete from m_crm_meeting_invitees",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_meeting_invitees
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    @Insert({
        "insert into m_crm_meeting_invitees (id, meetingId, ",
        "username, isModerator, ",
        "status, source)",
        "values (#{id,jdbcType=INTEGER}, #{meetingid,jdbcType=INTEGER}, ",
        "#{username,jdbcType=VARCHAR}, #{ismoderator,jdbcType=BIT}, ",
        "#{status,jdbcType=VARCHAR}, #{source,jdbcType=VARCHAR})"
    })
    int insert(MeetingInvitee record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_meeting_invitees
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    int insertSelective(MeetingInvitee record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_meeting_invitees
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    List<MeetingInvitee> selectByExample(MeetingInviteeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_meeting_invitees
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    @Select({
        "select",
        "id, meetingId, username, isModerator, status, source",
        "from m_crm_meeting_invitees",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("com.mycollab.module.crm.dao.MeetingInviteeMapper.BaseResultMap")
    MeetingInvitee selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_meeting_invitees
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    int updateByExampleSelective(@Param("record") MeetingInvitee record, @Param("example") MeetingInviteeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_meeting_invitees
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    int updateByExample(@Param("record") MeetingInvitee record, @Param("example") MeetingInviteeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_meeting_invitees
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    int updateByPrimaryKeySelective(MeetingInvitee record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_meeting_invitees
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    @Update({
        "update m_crm_meeting_invitees",
        "set meetingId = #{meetingid,jdbcType=INTEGER},",
          "username = #{username,jdbcType=VARCHAR},",
          "isModerator = #{ismoderator,jdbcType=BIT},",
          "status = #{status,jdbcType=VARCHAR},",
          "source = #{source,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(MeetingInvitee record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_meeting_invitees
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    Integer insertAndReturnKey(MeetingInvitee value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_meeting_invitees
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_crm_meeting_invitees
     *
     * @mbg.generated Tue Aug 01 11:17:27 ICT 2017
     */
    void massUpdateWithSession(@Param("record") MeetingInvitee record, @Param("primaryKeys") List primaryKeys);
}