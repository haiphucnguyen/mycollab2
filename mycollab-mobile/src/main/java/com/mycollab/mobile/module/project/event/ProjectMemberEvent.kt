package com.mycollab.mobile.module.project.event

import com.mycollab.events.ApplicationEvent

/**
 * @author MyCollab Ltd
 * @since 6.0.0
 */
class ProjectMemberEvent {
    class InviteProjectMembers(val inviteEmails: List<String>, val roleId: Int,
                               val inviteMessage: String)

    class GotoList(source: Any, data: Any?) : ApplicationEvent(source, data)

    class GotoInviteMembers(source: Any, data: Any?) : ApplicationEvent(source, data)

    class GotoRead(source: Any, data: Any?) : ApplicationEvent(source, data)

    class GotoEdit(source: Any, data: Any?) : ApplicationEvent(source, data)
}