package com.mycollab.module.project.event

import com.mycollab.events.ApplicationEvent

/**
 * @author MyCollab Ltd
 * @since 6.0.0
 */
object ProjectMemberEvent {
    class InviteProjectMembers(val emails: Collection<String>, val roleId: Int,
                               val roleName: String, val inviteMessage: String)

    class Search(source: Any, data: Any?) : ApplicationEvent(source, data) 

    class GotoList(source: Any, data: Any?) : ApplicationEvent(source, data) 

    class GotoInviteMembers(source: Any, data: Any?) : ApplicationEvent(source, data) 

    class GotoRead(source: Any, data: Any?) : ApplicationEvent(source, data) 

    class GotoEdit(source: Any, data: Any?) : ApplicationEvent(source, data) 
}