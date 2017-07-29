package com.mycollab.module.project.esb

/**
 * @author MyCollab Ltd
 * @since 5.1.0
 */
class InviteProjectMembersEvent(val emails: Array[String], val projectId: Integer, val projectRoleId: Integer,
                                val inviteUser: String, val inviteMessage: String, val sAccountId: Integer) {
    
}
